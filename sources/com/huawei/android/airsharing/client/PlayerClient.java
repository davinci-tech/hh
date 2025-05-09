package com.huawei.android.airsharing.client;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.android.airsharing.api.EHwMediaInfoType;
import com.huawei.android.airsharing.api.Event;
import com.huawei.android.airsharing.api.HwMediaInfo;
import com.huawei.android.airsharing.api.HwMediaPosition;
import com.huawei.android.airsharing.api.HwObject;
import com.huawei.android.airsharing.api.HwServer;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.android.airsharing.api.IPlayerManager;
import com.huawei.android.airsharing.api.IStatusListener;
import com.huawei.android.airsharing.api.InitializationInfoEvent;
import com.huawei.android.airsharing.api.ProjectionDevice;
import com.huawei.android.airsharing.client.IAidlHwPlayerManager;
import com.huawei.android.airsharing.client.ISdkAidlHwAuthenManager;
import defpackage.wz;
import defpackage.xa;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PlayerClient implements IPlayerManager, IEventListener {
    private static final xa b = xa.c();
    private static PlayerClient e = null;
    private Context j;
    private IBinder p;
    private IAidlHwPlayerManager d = null;
    private String u = "7";
    private ISdkAidlHwAuthenManager s = null;
    private EServiceConnectStatus w = EServiceConnectStatus.SERVICE_DISCONNECTED;
    private IEventListener f = null;
    private IStatusListener v = null;
    private ContentResolver g = null;
    private final HashMap<IEventListener, EventListenerAgent> t = new HashMap<>();
    private boolean c = false;
    private final Object i = new Object();
    private final Object m = new Object();
    private final Object o = new Object();
    private final Object y = new Object();
    private final ContentObserver h = new ContentObserver(new Handler()) { // from class: com.huawei.android.airsharing.client.PlayerClient.4
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            PlayerClient.this.m();
        }
    };
    private ExecutorService n = Executors.newFixedThreadPool(5);
    private e l = null;
    private HandlerThread k = null;
    private int r = -1;
    private ServiceConnection q = new ServiceConnection() { // from class: com.huawei.android.airsharing.client.PlayerClient.2
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            PlayerClient.b.a("PlayerClient", "bind service disconnected");
            synchronized (PlayerClient.this.i) {
                PlayerClient.this.d = null;
            }
            PlayerClient.this.w = EServiceConnectStatus.SERVICE_DISCONNECTED;
            PlayerClient.this.l.removeMessages(1);
            PlayerClient.this.l.sendEmptyMessageDelayed(1, 5000L);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBinder iBinder2;
            PlayerClient.b.a("PlayerClient", "bind service connected");
            if (PlayerClient.this.j == null) {
                PlayerClient.b.d("PlayerClient", "context is null");
                return;
            }
            try {
                Intent intent = new Intent();
                if (PlayerClient.this.j.checkSelfPermission("com.android.permission.airsharing_play_interface") == 0) {
                    intent.setComponent(new ComponentName("com.huawei.android.airsharing", "com.huawei.android.airsharing.service.PlayerService"));
                } else {
                    intent.setComponent(new ComponentName("com.huawei.android.airsharing", "com.huawei.android.airsharing.service.SdkPlayerService"));
                }
                PlayerClient.b.a("PlayerClient", "start service");
                PlayerClient.this.j.startService(intent);
            } catch (IllegalStateException unused) {
                PlayerClient.b.d("PlayerClient", "startService catch IllegalStateException");
            } catch (SecurityException unused2) {
                PlayerClient.b.d("PlayerClient", "bindService catch SecurityException");
            }
            PlayerClient.this.l.removeMessages(1);
            PlayerClient.this.w = EServiceConnectStatus.SERVICE_CONNECTED;
            if (PlayerClient.this.j.checkSelfPermission("com.android.permission.airsharing_play_interface") != 0) {
                PlayerClient.this.s = ISdkAidlHwAuthenManager.Stub.asInterface(iBinder);
                try {
                    iBinder2 = PlayerClient.this.s.checkPermission();
                } catch (RemoteException e2) {
                    PlayerClient.b.d("PlayerClient", "onServiceConnected checkPermission failed with exception: " + e2.getLocalizedMessage());
                    iBinder2 = iBinder;
                }
                if (iBinder2 == null) {
                    PlayerClient.this.dW_(iBinder);
                    PlayerClient.b.d("PlayerClient", "onServiceConnected but don't have permission");
                    return;
                }
                iBinder = iBinder2;
            }
            synchronized (PlayerClient.this.i) {
                PlayerClient.this.d = IAidlHwPlayerManager.Stub.asInterface(iBinder);
            }
            PlayerClient.this.dW_(iBinder);
            if (PlayerClient.this.u != null) {
                b bVar = new b();
                bVar.f1825a = PlayerClient.this.u;
                PlayerClient.this.n.execute(bVar);
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private IBinder.DeathRecipient f1824a = new d();

    public enum EServiceConnectStatus {
        SERVICE_DISCONNECTED,
        SERVICE_DISCONNECTING,
        SERVICE_CONNECTING,
        SERVICE_CONNECTED
    }

    public static PlayerClient a() {
        PlayerClient playerClient;
        synchronized (PlayerClient.class) {
            if (e == null) {
                e = new PlayerClient();
            }
            playerClient = e;
        }
        return playerClient;
    }

    class e extends Handler {
        e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1 && PlayerClient.this.c && PlayerClient.this.r != -1) {
                PlayerClient.this.f();
            }
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    public boolean init(Context context) {
        xa xaVar = b;
        xaVar.a("PlayerClient", "init in with context, AirSharing Jar version is " + wz.c().a());
        if (context == null) {
            xaVar.b("PlayerClient", "init failed because context is null");
            return false;
        }
        this.j = context;
        this.r = Process.myPid();
        synchronized (this.o) {
            HandlerThread handlerThread = this.k;
            if (handlerThread == null || !handlerThread.isAlive()) {
                HandlerThread handlerThread2 = new HandlerThread("PlayerClientHandleThread");
                this.k = handlerThread2;
                handlerThread2.start();
                this.l = new e(this.k.getLooper());
            }
        }
        return f();
    }

    @Override // com.huawei.android.airsharing.api.IServerManager
    public void deInit() {
        xa xaVar = b;
        xaVar.a("PlayerClient", "deInit in");
        e eVar = this.l;
        if (eVar != null) {
            eVar.removeMessages(1);
        }
        j();
        this.r = -1;
        synchronized (this.o) {
            HandlerThread handlerThread = this.k;
            if (handlerThread != null) {
                xaVar.a("PlayerClient", "handle thread quit return: " + handlerThread.quitSafely());
            }
        }
        a((IEventListener) a());
        this.c = false;
        synchronized (this.m) {
            this.f = null;
        }
        xaVar.a("PlayerClient", "deInit out");
    }

    @Override // com.huawei.android.airsharing.api.IServerManager
    @Deprecated
    public void subscribServers(String str) {
        e(str);
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            b.b("PlayerClient", "subscribeServers failed because type is null");
        } else {
            xa xaVar = b;
            xaVar.a("PlayerClient", "subscribeServers in serverType: " + str);
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                b.d("PlayerClient", "subscribeServers serverType is not number: " + str);
            }
            if (this.w == EServiceConnectStatus.SERVICE_DISCONNECTED || this.w == EServiceConnectStatus.SERVICE_DISCONNECTING) {
                xaVar.b("PlayerClient", "subscribeServers failed, service has not bind");
            } else {
                if (this.w == EServiceConnectStatus.SERVICE_CONNECTING) {
                    xaVar.a("PlayerClient", "service connect status is connecting");
                } else if (this.w == EServiceConnectStatus.SERVICE_CONNECTED) {
                    xaVar.a("PlayerClient", "service connect status is connected, start subServerRunnable");
                    b bVar = new b();
                    bVar.f1825a = str;
                    this.n.execute(bVar);
                } else {
                    xaVar.a("PlayerClient", "service connect status is invalid");
                }
                b.a("PlayerClient", "subscribeServers out");
            }
        }
        d(2010, "EVENT_TYPE_PLAYER_SUBSCRIBE_FAILED");
        onEventHandle(new InitializationInfoEvent(2010, 1, "EVENT_TYPE_PLAYER_SUBSCRIBE_FAILED"));
        b.a("PlayerClient", "subscribeServers out");
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public String f1825a;

        private b() {
            this.f1825a = null;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                IAidlHwPlayerManager iAidlHwPlayerManager = PlayerClient.this.d;
                if (iAidlHwPlayerManager == null) {
                    PlayerClient.b.b("PlayerClient", "subServerRunnable aidlHwPlayerManager is null");
                    return;
                }
                PlayerClient.b.a("PlayerClient", "subServerRunnable run");
                int i = PlayerClient.this.r;
                String str = this.f1825a;
                EventListenerAgent dX_ = PlayerClient.this.dX_(PlayerClient.a(), PlayerClient.this.l.getLooper());
                iAidlHwPlayerManager.clsHwSharingListener(i, dX_);
                iAidlHwPlayerManager.setHwSharingListener(i, dX_);
                iAidlHwPlayerManager.subscribServers(i, str);
                PlayerClient.this.c = true;
                PlayerClient.this.d(13);
                PlayerClient.this.i();
                PlayerClient.this.d(2010, "EVENT_TYPE_PLAYER_SUBSCRIBE_SUCCESS");
                PlayerClient.this.onEventHandle(new InitializationInfoEvent(2010, 2, "EVENT_TYPE_PLAYER_SUBSCRIBE_SUCCESS"));
            } catch (RemoteException unused) {
                PlayerClient.b.d("PlayerClient", "subServerRunnable catch RemoteException");
            }
        }
    }

    @Override // com.huawei.android.airsharing.api.IServerManager
    @Deprecated
    public void unsubscribServers() {
        n();
    }

    private void n() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null) {
            b.b("PlayerClient", "unsubscribeServers aidlHwPlayerManager is null");
            return;
        }
        b.a("PlayerClient", "unsubscribeServers in");
        try {
            iAidlHwPlayerManager.unsubscribServers(this.r, this.u);
            this.c = false;
        } catch (RemoteException unused) {
            b.d("PlayerClient", "unsubscribeServers catch RemoteException");
        }
        b.a("PlayerClient", "unsubscribeServers out");
    }

    @Deprecated
    public void d(IEventListener iEventListener) {
        b(iEventListener);
    }

    @Deprecated
    public void e(IEventListener iEventListener) {
        g();
    }

    public void b(IEventListener iEventListener) {
        if (iEventListener == null) {
            b.b("PlayerClient", "registerListener failed because eventListener is null");
            return;
        }
        synchronized (this.m) {
            this.f = iEventListener;
            b.a("PlayerClient", "registerListener listener: " + this.f);
        }
    }

    public void g() {
        synchronized (this.m) {
            this.f = null;
        }
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null) {
            b.b("PlayerClient", "unregisterListener failed because aidlHwPlayerManager is null");
            return;
        }
        b.a("PlayerClient", "unregisterListener in");
        try {
            iAidlHwPlayerManager.clsHwSharingListener(this.r, dX_(a(), this.l.getLooper()));
        } catch (RemoteException unused) {
            b.d("PlayerClient", "unregisterListener catch RemoteException");
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public boolean playMedia(String str, String str2, EHwMediaInfoType eHwMediaInfoType, String str3, int i) {
        if (str == null || eHwMediaInfoType == null || str2 == null) {
            b.b("PlayerClient", "playMedia url is error, stop playMedia");
            d(2002, IEventListener.EVENT_TYPE_PLAYER_MEDIA_STOP_PUSH_FAILED);
            return false;
        }
        b.a("PlayerClient", "playMedia in, type: " + eHwMediaInfoType + ", position: " + str3);
        HwMediaInfo hwMediaInfo = new HwMediaInfo();
        hwMediaInfo.setUrl(str);
        hwMediaInfo.setName(str2);
        hwMediaInfo.setMediaInfoType(eHwMediaInfoType);
        hwMediaInfo.setPosition(str3);
        hwMediaInfo.setVolume(i);
        return playMedia(hwMediaInfo, false, null);
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public boolean playMedia(HwMediaInfo hwMediaInfo, boolean z, HwObject hwObject) {
        if (hwMediaInfo == null || hwMediaInfo.getUrl() == null || "".equals(hwMediaInfo.getUrl()) || hwMediaInfo.getMediaInfoType() == null || hwMediaInfo.getName() == null) {
            b.b("PlayerClient", "playMedia media is error, stop playMedia");
            d(2002, IEventListener.EVENT_TYPE_PLAYER_MEDIA_STOP_PUSH_FAILED);
            return false;
        }
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "playMedia aidlHwPlayerManager is null or without service init");
            return false;
        }
        int i = this.r;
        b.a("PlayerClient", "playMedia in, mediaInfo: " + hwMediaInfo + ", isHwAirsharing: " + z);
        try {
            return iAidlHwPlayerManager.playMedia(i, hwMediaInfo, z, hwObject);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "playMedia catch RemoteException");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public boolean play(HwMediaInfo hwMediaInfo, ProjectionDevice projectionDevice) {
        if (hwMediaInfo == null || hwMediaInfo.getUrl() == null || "".equals(hwMediaInfo.getUrl()) || hwMediaInfo.getMediaInfoType() == null || hwMediaInfo.getName() == null || projectionDevice == null || projectionDevice.getIndication() == null) {
            xa xaVar = b;
            xaVar.b("PlayerClient", "play media or device is error, stop play");
            if (projectionDevice == null || projectionDevice.getIndication() == null) {
                xaVar.b("PlayerClient", "play device or indication is null");
            }
            d(2002, IEventListener.EVENT_TYPE_PLAYER_MEDIA_STOP_PUSH_FAILED);
            return false;
        }
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "play aidlHwPlayerManager is null or without service init");
            return false;
        }
        int i = this.r;
        b.b("PlayerClient", "play in, mediaInfo: " + hwMediaInfo);
        try {
            return iAidlHwPlayerManager.play(i, hwMediaInfo, projectionDevice);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "play catch RemoteException");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public HwMediaPosition getPosition() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "getPosition aidlHwPlayerManager is null or without service init");
            return null;
        }
        b.a("PlayerClient", "getPosition in");
        try {
            return iAidlHwPlayerManager.getPosition(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "getPosition catch RemoteException");
            return null;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public boolean seek(String str) {
        if (TextUtils.isEmpty(str)) {
            b.b("PlayerClient", "seek failed because targetPosition is null");
            return false;
        }
        int i = this.r;
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "seek aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "seek in, targetPosition: " + str);
        try {
            return iAidlHwPlayerManager.seek(i, str);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "seek catch RemoteException");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    public boolean pause() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "pause aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "pause in");
        try {
            return iAidlHwPlayerManager.pause(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "pause catch RemoteException");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    public boolean resume() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "resume aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "resume in");
        try {
            return iAidlHwPlayerManager.resume(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "resume catch RemoteException");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    public boolean stop() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "stop aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "stop in");
        try {
            return iAidlHwPlayerManager.stop(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "stop catch RemoteException");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public boolean isRendering() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "isRendering aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "isRendering in");
        try {
            return iAidlHwPlayerManager.isRendering(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "isRendering catch RemoteException");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public int getVolume() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "getVolume aidlHwPlayerManager is null or without service init");
            return 0;
        }
        b.a("PlayerClient", "getVolume in");
        try {
            return iAidlHwPlayerManager.getVolume(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "getVolume catch RemoteException");
            return 0;
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    public boolean setVolume(int i) {
        if (i < 0 || i > 100) {
            b.b("PlayerClient", "setVolume failed because volume value is invalid");
            return false;
        }
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "setVolume aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "setVolume in, volume: " + i);
        try {
            return iAidlHwPlayerManager.setVolume(this.r, i);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "setVolume catch RemoteException");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean f() {
        /*
            r5 = this;
            android.content.Context r0 = r5.j
            java.lang.String r1 = "com.huawei.android.airsharing"
            boolean r0 = defpackage.ww.a(r0, r1)
            xa r2 = com.huawei.android.airsharing.client.PlayerClient.b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "bindHwPlayerService in, isAirSharing: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "PlayerClient"
            r2.a(r4, r3)
            if (r0 == 0) goto L20
            goto L22
        L20:
            java.lang.String r1 = "com.huawei.android.mirrorshare"
        L22:
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r0 = r5.w
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r3 = com.huawei.android.airsharing.client.PlayerClient.EServiceConnectStatus.SERVICE_CONNECTED
            if (r0 == r3) goto L94
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r0 = r5.w
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r3 = com.huawei.android.airsharing.client.PlayerClient.EServiceConnectStatus.SERVICE_CONNECTING
            if (r0 != r3) goto L2f
            goto L94
        L2f:
            android.content.Context r0 = r5.j
            if (r0 == 0) goto L6e
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            r0.<init>()     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            android.content.Context r2 = r5.j     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            java.lang.String r3 = "com.android.permission.airsharing_play_interface"
            int r2 = r2.checkSelfPermission(r3)     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            if (r2 != 0) goto L4a
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            java.lang.String r3 = "com.huawei.android.airsharing.service.PlayerService"
            r2.<init>(r1, r3)     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            goto L51
        L4a:
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            java.lang.String r3 = "com.huawei.android.airsharing.service.SdkPlayerService"
            r2.<init>(r1, r3)     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
        L51:
            r0.setComponent(r2)     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            android.content.Context r1 = r5.j     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            android.content.ServiceConnection r2 = r5.q     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            r3 = 65
            boolean r0 = r1.bindService(r0, r2, r3)     // Catch: java.lang.SecurityException -> L5f java.lang.IllegalArgumentException -> L67
            goto L6f
        L5f:
            xa r0 = com.huawei.android.airsharing.client.PlayerClient.b
            java.lang.String r1 = "bindService catch SecurityException"
            r0.d(r4, r1)
            goto L6e
        L67:
            xa r0 = com.huawei.android.airsharing.client.PlayerClient.b
            java.lang.String r1 = "bindService catch IllegalArgumentException"
            r0.d(r4, r1)
        L6e:
            r0 = 0
        L6f:
            if (r0 == 0) goto L7c
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r1 = r5.w
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r2 = com.huawei.android.airsharing.client.PlayerClient.EServiceConnectStatus.SERVICE_CONNECTED
            if (r1 == r2) goto L80
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r1 = com.huawei.android.airsharing.client.PlayerClient.EServiceConnectStatus.SERVICE_CONNECTING
            r5.w = r1
            goto L80
        L7c:
            com.huawei.android.airsharing.client.PlayerClient$EServiceConnectStatus r1 = com.huawei.android.airsharing.client.PlayerClient.EServiceConnectStatus.SERVICE_DISCONNECTED
            r5.w = r1
        L80:
            xa r1 = com.huawei.android.airsharing.client.PlayerClient.b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "bindHwPlayerService out, bindServiceResult: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            r1.a(r4, r2)
            return r0
        L94:
            java.lang.String r0 = r5.u
            r5.e(r0)
            java.lang.String r0 = "bindHwPlayerService service has bind"
            r2.a(r4, r0)
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.android.airsharing.client.PlayerClient.f():boolean");
    }

    private void j() {
        ServiceConnection serviceConnection;
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null) {
            b.b("PlayerClient", "unbindHwPlayerService aidlHwPlayerManager is null");
            return;
        }
        b.a("PlayerClient", "unbindHwPlayerService in");
        try {
            iAidlHwPlayerManager.clsHwSharingListener(this.r, dX_(a(), this.l.getLooper()));
            Context context = this.j;
            if (context == null || (serviceConnection = this.q) == null) {
                return;
            }
            context.unbindService(serviceConnection);
            this.w = EServiceConnectStatus.SERVICE_DISCONNECTED;
            o();
            synchronized (this.i) {
                this.d = null;
            }
        } catch (RemoteException unused) {
            b.d("PlayerClient", "unbindHwPlayerService catch RemoteException");
        } catch (IllegalArgumentException unused2) {
            b.d("PlayerClient", "unbindHwPlayerService catch IllegalArgumentException");
        }
    }

    class d implements IBinder.DeathRecipient {
        private d() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            synchronized (this) {
                PlayerClient.this.o();
                PlayerClient.b.b("PlayerClient", "service binderDied");
                synchronized (PlayerClient.this.i) {
                    PlayerClient.this.d = null;
                    PlayerClient.this.c = false;
                }
                PlayerClient.this.d(IEventListener.EVENT_ID_BINDER_DIED, "");
                PlayerClient.this.w = EServiceConnectStatus.SERVICE_DISCONNECTED;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dW_(IBinder iBinder) {
        synchronized (this) {
            b.a("PlayerClient", "linkToDeath in, service: " + iBinder);
            if (iBinder == null) {
                return;
            }
            try {
                o();
                this.p = iBinder;
                iBinder.linkToDeath(this.f1824a, 0);
            } catch (Exception unused) {
                b.d("PlayerClient", "linkToDeath service catch Exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        synchronized (this) {
            b.a("PlayerClient", "unlinkToDeath in");
            try {
                IBinder iBinder = this.p;
                if (iBinder != null) {
                    iBinder.unlinkToDeath(this.f1824a, 0);
                }
            } catch (Exception unused) {
                b.d("PlayerClient", "unlinkToDeath service catch Exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        a aVar = new a();
        aVar.c = i;
        aVar.d = str;
        this.n.execute(aVar);
    }

    class a implements Runnable {
        public int c;
        public String d;

        private a() {
            this.c = -1;
            this.d = null;
        }

        @Override // java.lang.Runnable
        public void run() {
            PlayerClient.this.onEvent(this.c, this.d);
        }
    }

    @Override // com.huawei.android.airsharing.api.IEventListener
    public boolean onEvent(int i, String str) {
        xa xaVar = b;
        xaVar.a("PlayerClient", "onEvent, eventId: " + i + " with type: " + str);
        synchronized (this.m) {
            IEventListener iEventListener = this.f;
            if (iEventListener != null && i > 0) {
                return iEventListener.onEvent(i, str);
            }
            xaVar.b("PlayerClient", "onEvent failed because invalid event id or without listener init");
            return false;
        }
    }

    @Override // com.huawei.android.airsharing.api.IEventListener
    public void onProjectionDeviceUpdate(int i, ProjectionDevice projectionDevice) {
        b.a("PlayerClient", "onProjectionDeviceUpdate, eventId: " + i);
        synchronized (this.m) {
            IEventListener iEventListener = this.f;
            if (iEventListener != null) {
                iEventListener.onProjectionDeviceUpdate(i, projectionDevice);
            }
        }
    }

    @Override // com.huawei.android.airsharing.api.IEventListener
    public void onEventHandle(Event event) {
        if (event == null) {
            b.b("PlayerClient", "onEventHandle failed because event is null!");
            return;
        }
        b.a("PlayerClient", "onEventHandle, eventId: " + event.getEventId());
        synchronized (this.m) {
            IEventListener iEventListener = this.f;
            if (iEventListener != null) {
                iEventListener.onEventHandle(event);
            }
        }
    }

    @Override // com.huawei.android.airsharing.api.IEventListener
    @Deprecated
    public void onDisplayUpdate(int i, String str, String str2, int i2) {
        synchronized (this.m) {
            IEventListener iEventListener = this.f;
            if (iEventListener != null) {
                iEventListener.onDisplayUpdate(i, str, str2, i2);
            }
        }
    }

    @Override // com.huawei.android.airsharing.api.IEventListener
    @Deprecated
    public void onMirrorUpdate(int i, String str, String str2, int i2, boolean z) {
        synchronized (this.m) {
            IEventListener iEventListener = this.f;
            if (iEventListener != null) {
                iEventListener.onMirrorUpdate(i, str, str2, i2, z);
            }
        }
    }

    @Override // com.huawei.android.airsharing.api.IPlayerManager
    @Deprecated
    public HwServer getRenderingServer() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "getRenderingServer aidlHwPlayerManager is null or without service init");
            return null;
        }
        b.a("PlayerClient", "getRenderingServer in");
        try {
            return iAidlHwPlayerManager.getRenderingServer();
        } catch (RemoteException unused) {
            b.d("PlayerClient", "getRenderingServer catch RemoteException");
            return null;
        }
    }

    @Deprecated
    public boolean a(boolean z) {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "startScanDevice aidlHwPlayerManager is null or without service init");
            return false;
        }
        int i = this.r;
        b.a("PlayerClient", "scanDevice in, pid: " + i + ", isActiveScan: " + z);
        try {
            return iAidlHwPlayerManager.startScanDevice(i, z);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "startScanDevice catch RemoteException");
            return false;
        }
    }

    @Deprecated
    public boolean e(boolean z) {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "stopScanDevice aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "stopScanDevice in, isActiveScan: " + z);
        try {
            return iAidlHwPlayerManager.stopScanDevice(this.r, z);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "stopScanDevice catch RemoteException");
            return false;
        }
    }

    @Deprecated
    public boolean e(ProjectionDevice projectionDevice) {
        if (projectionDevice == null) {
            b.b("PlayerClient", "connectDevice failed because projection device is null");
            return false;
        }
        xa xaVar = b;
        xaVar.a("PlayerClient", "connectDevice in");
        int i = this.r;
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            xaVar.b("PlayerClient", "connectDevice aidlHwPlayerManager is null or without service init");
            return false;
        }
        try {
            return iAidlHwPlayerManager.connectDevice(i, projectionDevice);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "connectDevice catch RemoteException");
            return false;
        }
    }

    public boolean e() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "disconnectDevice aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "disconnectDevice in");
        try {
            return iAidlHwPlayerManager.disconnectDevice(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "disconnectDevice catch RemoteException");
            return false;
        }
    }

    public String c() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "getTargetDevName aidlHwPlayerManager is null or without service init");
            return "";
        }
        b.a("PlayerClient", "getTargetDevName in");
        try {
            return iAidlHwPlayerManager.getTargetDevName(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "getTargetDevName catch RemoteException");
            return "";
        }
    }

    @Deprecated
    public boolean h() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "isConnected aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "isConnected in");
        try {
            return iAidlHwPlayerManager.isConnected(this.r);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "isConnected catch RemoteException");
            return false;
        }
    }

    public int b() {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "getSdkVersion aidlHwPlayerManager is null or without service init");
            return -1;
        }
        b.a("PlayerClient", "getSdkVersion in");
        try {
            return iAidlHwPlayerManager.getSdkVersion();
        } catch (RemoteException unused) {
            b.d("PlayerClient", "getSdkVersion catch RemoteException");
            return -1;
        }
    }

    public boolean d(int i) {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "setUsingCapability aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "setUsingCapability in");
        try {
            return iAidlHwPlayerManager.setUsingCapability(this.r, i);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "setUsingCapability catch RemoteException");
            return false;
        }
    }

    public boolean a(String str, String str2) {
        IAidlHwPlayerManager iAidlHwPlayerManager = this.d;
        if (iAidlHwPlayerManager == null || !this.c) {
            b.b("PlayerClient", "setExtendInfo aidlHwPlayerManager is null or without service init");
            return false;
        }
        b.a("PlayerClient", "setExtendInfo in");
        try {
            return iAidlHwPlayerManager.setExtendInfo(str, str2);
        } catch (RemoteException unused) {
            b.d("PlayerClient", "setExtendInfo catch RemoteException");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EventListenerAgent dX_(IEventListener iEventListener, Looper looper) {
        EventListenerAgent eventListenerAgent;
        if (iEventListener == null) {
            return null;
        }
        synchronized (this.t) {
            this.t.remove(iEventListener);
            eventListenerAgent = new EventListenerAgent(iEventListener, looper);
            this.t.put(iEventListener, eventListenerAgent);
        }
        return eventListenerAgent;
    }

    private void a(IEventListener iEventListener) {
        if (iEventListener == null) {
            return;
        }
        synchronized (this.t) {
            this.t.remove(iEventListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        b.a("PlayerClient", "recordSdkVersion in");
        wz.c().d(b());
        a("SDK_VERSION", String.valueOf(wz.c().a()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        xa xaVar = b;
        xaVar.a("PlayerClient", "updateCastStatus in");
        synchronized (this.y) {
            ContentResolver contentResolver = this.g;
            if (contentResolver != null && this.v != null) {
                String string = Settings.Global.getString(contentResolver, "cast_plus_info");
                xaVar.a("PlayerClient", "updateCastStatus in, castStatus " + string);
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                HashMap hashMap = new HashMap();
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        hashMap.put(Integer.valueOf(Integer.parseInt(next)), Integer.valueOf(jSONObject.getInt(next)));
                    }
                    this.v.onStatusChanged(hashMap);
                } catch (NumberFormatException | JSONException e2) {
                    b.d("PlayerClient", "ERROR : updateCastStatus, JSONException : " + e2);
                }
                return;
            }
            xaVar.e("PlayerClient", "updateCastStatus in, mStatusListener is null");
        }
    }
}

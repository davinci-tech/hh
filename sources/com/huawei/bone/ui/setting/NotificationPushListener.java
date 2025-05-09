package com.huawei.bone.ui.setting;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.RemoteController;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.media.MediaBrowserProtocol;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.framework.common.LimitQueue;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.MusicInfo;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IMusicChangedCallback;
import com.huawei.hwservicesmgr.IMusicControllerAIDL;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.bdk;
import defpackage.bdm;
import defpackage.bdn;
import defpackage.bdo;
import defpackage.bdp;
import defpackage.bds;
import defpackage.bff;
import defpackage.bfg;
import defpackage.bky;
import defpackage.cvx;
import defpackage.cvz;
import defpackage.iyg;
import defpackage.iyv;
import defpackage.jdh;
import defpackage.jdx;
import defpackage.jdz;
import defpackage.jjc;
import defpackage.jrg;
import defpackage.khs;
import defpackage.kiq;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.DeviceUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.EnvironmentInfo;
import health.compact.a.HarmonyBuild;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class NotificationPushListener extends NotificationListenerService implements RemoteController.OnClientUpdateListener {
    private long ac;
    private long af;
    private RemoteController an;
    private a g;
    private BroadcastReceiver i;
    private ContentObserver s;
    private jjc u;
    private b w;
    private static final bds b = new bds();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f1929a = new Object();
    private static final Timer d = new Timer("NotificPush");
    private final NotificationContentProviderUtil.NotificationLiveChangerListener am = new NotificationContentProviderUtil.NotificationLiveChangerListener() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.4
        @Override // com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil.NotificationLiveChangerListener
        public void onChanger(boolean z) {
            ReleaseLogUtil.e("Notify_NotificPush", "liveInfo listener onChanger start: ", Boolean.valueOf(z));
            NotificationPushListener notificationPushListener = NotificationPushListener.this;
            notificationPushListener.e((List<String>) notificationPushListener.e());
            NotificationPushListener.this.a(z);
        }
    };
    private final TimerTask f = new TimerTask() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
        }
    };
    private final TimerTask t = new TimerTask() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.2
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
        }
    };
    private int z = 0;
    private int q = 0;
    private int aa = 0;
    private int k = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
    private final Set<String> l = new HashSet(7);
    private final Set<String> ai = new HashSet(16);
    private final Set<String> ae = new HashSet(16);
    private final Set<String> m = new HashSet(16);
    private final Set<String> x = new HashSet(16);
    private final Set<String> o = new HashSet(16);
    private final Set<Integer> ad = new HashSet(16);
    private final Map<String, String> j = new HashMap(16);
    private Map<String, d> y = null;
    private Map<String, Long> n = null;
    private String ah = "";
    private String ak = "";
    private int aj = 0;
    private boolean c = true;
    private IMusicChangedCallback ag = null;
    private final Map<String, Long> ab = new HashMap(16);
    private final LimitQueue<String> p = new LimitQueue<>(10);
    private final LimitQueue<String> v = new LimitQueue<>(50);
    private final String e = "xxx.xxx.xxx";
    private final Handler r = new Handler(Looper.getMainLooper()) { // from class: com.huawei.bone.ui.setting.NotificationPushListener.9
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 10:
                    LogUtil.a("NotificPush", "handleMessage remote control init!");
                    NotificationPushListener.this.r();
                    break;
                case 11:
                    LogUtil.a("NotificPush", "handleMessage remove sms");
                    NotificationPushListener.this.on_(message);
                    break;
                case 12:
                    LogUtil.a("NotificPush", "handleMessage remove voip calling");
                    NotificationPushListener.this.oo_(message);
                    break;
            }
        }
    };
    private final IMusicControllerAIDL.Stub h = new IMusicControllerAIDL.Stub() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.10
        @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
        public void initMusic() {
            LogUtil.a("NotificPush", "musicInterface initMusic");
            NotificationPushListener.this.r.obtainMessage(10).sendToTarget();
        }

        @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
        public MusicInfo getCurrentMusicInfo() {
            AudioManager audioManager = (AudioManager) BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO);
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            MusicInfo musicInfo = new MusicInfo();
            if (!NotificationPushListener.this.c) {
                musicInfo.setPlayState(NotificationPushListener.this.aj);
                musicInfo.setSingerName(NotificationPushListener.this.ak);
                musicInfo.setSongName(NotificationPushListener.this.ah);
                musicInfo.setMaxVolume(streamMaxVolume);
                musicInfo.setCurrentVolume(streamVolume);
            } else {
                musicInfo.setPlayState(0);
                musicInfo.setSingerName("");
                musicInfo.setSongName("");
                musicInfo.setMaxVolume(0);
                musicInfo.setCurrentVolume(0);
            }
            LogUtil.a("NotificPush", "getCurrentMusicInfo: " + musicInfo.toString());
            return musicInfo;
        }

        @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
        public void controllMusic(int i) {
            LogUtil.a("NotificPush", "musicInterface controllMusic");
            if (NotificationPushListener.this.an == null || NotificationPushListener.this.c) {
                if (NotificationPushListener.this.c) {
                    AudioManager audioManager = (AudioManager) NotificationPushListener.this.getSystemService(PresenterUtils.AUDIO);
                    audioManager.dispatchMediaKeyEvent(new KeyEvent(0, i));
                    audioManager.dispatchMediaKeyEvent(new KeyEvent(1, i));
                    return;
                }
                LogUtil.a("NotificPush", "controllMusic is isPlayEnd!!!");
                return;
            }
            NotificationPushListener.this.an.sendMediaKeyEvent(new KeyEvent(0, i));
            NotificationPushListener.this.an.sendMediaKeyEvent(new KeyEvent(1, i));
            LogUtil.a("NotificPush", "controllMusic end");
        }

        @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
        public void setCallback(IMusicChangedCallback iMusicChangedCallback) {
            LogUtil.a("NotificPush", "musicInterface setCallback");
            NotificationPushListener.this.ag = iMusicChangedCallback;
        }

        @Override // com.huawei.hwservicesmgr.IMusicControllerAIDL
        public void remoteListener() {
            LogUtil.a("NotificPush", "musicInterface remoteListener");
            NotificationPushListener.this.v();
        }
    };

    static /* synthetic */ int j(NotificationPushListener notificationPushListener) {
        int i = notificationPushListener.q;
        notificationPushListener.q = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        boolean a2 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", false);
        if (z && a2) {
            Intent intent = new Intent(this, (Class<?>) HandleIntentService.class);
            intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
            Bundle bundle = new Bundle();
            bundle.putString("notificationSwitchChangeType", "notificationLiveSwitchOpen");
            intent.putExtras(bundle);
            ReleaseLogUtil.e("Notify_NotificPush", "live notification switch open to push live app icon");
            try {
                startService(intent);
            } catch (IllegalStateException | SecurityException e) {
                ReleaseLogUtil.c("Notify_NotificPush", "onNotificationPosted startService: ", ExceptionUtils.d(e));
                sqo.ab("getLiveAppInfo occur exception");
            }
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.a("NotificPush", "onUnbind enter...");
        return super.onUnbind(intent);
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public IBinder onBind(Intent intent) {
        if ("health".equals(intent.getAction())) {
            LogUtil.a("NotificPush", "onBind health !");
            String[] packagesForUid = BaseApplication.getContext().getPackageManager().getPackagesForUid(Binder.getCallingUid());
            if (packagesForUid != null) {
                for (String str : packagesForUid) {
                    if ("com.huawei.health".equals(str)) {
                        LogUtil.a("NotificPush", "Enter verifyPackageNameByUid true");
                        return this.h;
                    }
                }
            }
            return super.onBind(intent);
        }
        ReleaseLogUtil.e("Notify_NotificPush", "onBind super");
        return super.onBind(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        long currentTimeMillis = System.currentTimeMillis();
        boolean n = n();
        bdo.e("authorized", System.currentTimeMillis(), currentTimeMillis);
        ReleaseLogUtil.e("Notify_NotificPush", "onCreate: The master switch of com.huawei.health is turn on? ", Boolean.valueOf(n));
        long currentTimeMillis2 = System.currentTimeMillis();
        boolean a2 = DeviceUtil.a();
        bdo.e("deviceDbInfo", System.currentTimeMillis(), currentTimeMillis2);
        if (a2) {
            if (!n) {
                a();
            }
            long currentTimeMillis3 = System.currentTimeMillis();
            int a3 = cvz.a();
            bdo.e("forbiddenValue", System.currentTimeMillis(), currentTimeMillis3);
            if (a3 == -1) {
                LogUtil.a("NotificPush", "onCreate isForbidden not init");
                long currentTimeMillis4 = System.currentTimeMillis();
                cvz.c((Boolean) true);
                bdo.e("midWareValue", System.currentTimeMillis(), currentTimeMillis4);
            }
            DeviceUtil.fbV_(this, true, null);
            LogUtil.a("NotificPush", "start PhoneService");
            if (EnvironmentInfo.r()) {
                long currentTimeMillis5 = System.currentTimeMillis();
                NotificationContentProviderUtil.a(n ? 1 : 0);
                bdo.e("freezeAuthorized", System.currentTimeMillis(), currentTimeMillis5);
            } else {
                LogUtil.a("NotificPush", "onCreate not hms3.0");
                a();
            }
        }
        b();
        kiq.b("NotificPush");
        x();
    }

    private void x() {
        jdx.b(new Runnable() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    LogUtil.b("NotificPush", "sleep Exception");
                }
                int checkSelfPermission = ActivityCompat.checkSelfPermission(BaseApplication.getContext(), "android.permission.RECEIVE_SENSITIVE_NOTIFICATIONS");
                if (Build.VERSION.SDK_INT < 35 || checkSelfPermission != 0) {
                    return;
                }
                String e = SharedPreferenceManager.e("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", "");
                ReleaseLogUtil.e("Notify_NotificPush", "sensitivePermissionStatus ? ", e);
                if (SensitivePermissionStatus.RESTART.getValue().equals(e)) {
                    SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", String.valueOf(SensitivePermissionStatus.COMPLETE));
                    ReleaseLogUtil.e("Notify_NotificPush", "sensitivePermissionStatus is COMPLETE.");
                }
            }
        });
    }

    private void a() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean g = NotificationContentProviderUtil.g();
        bdo.e("initAuthorized", System.currentTimeMillis(), currentTimeMillis);
        if (!g) {
            LogUtil.h("NotificPush", "initAuthorizeEnabled already set notification authorized");
            return;
        }
        if (jrg.b()) {
            long currentTimeMillis2 = System.currentTimeMillis();
            NotificationContentProviderUtil.a(1);
            bdo.e("initAuthorizedTrue", System.currentTimeMillis(), currentTimeMillis2);
        } else {
            long currentTimeMillis3 = System.currentTimeMillis();
            NotificationContentProviderUtil.a(0);
            bdo.e("initAuthorizedFalse", System.currentTimeMillis(), currentTimeMillis3);
        }
    }

    private void b() {
        m();
        b.b();
        o();
        g();
        d();
        this.y = new HashMap(0);
        f();
        i();
        h();
        j();
        this.n = new ConcurrentHashMap(0);
        this.k = SharedPreferenceManager.a("CONTENT_LENGTH", "CONTENT_LENGTH_SIZE", -1);
        c();
        synchronized (f1929a) {
            p();
        }
    }

    private void i() {
        this.o.add("com.whatsapp");
        this.o.add("com.gbox.com.whatsapp");
        this.o.add("com.viber.voip");
        this.o.add("com.instagram.android");
        this.o.add("com.gbox.com.instagram.android");
    }

    private void j() {
        this.ad.add(1);
        this.ad.add(2);
        this.ad.add(3);
        this.ad.add(11);
    }

    private void f() {
        this.m.add("com.whatsapp");
        this.m.add("com.gbox.com.whatsapp");
        this.m.add("org.thoughtcrime.securesms");
        this.m.add("com.google.android.apps.messaging");
        this.m.add("com.tencent.mobileqq");
        this.m.add("com.huawei.health");
        this.m.add("org.telegram.messenger");
        this.m.add("com.huawei.android.totemweather");
        this.m.add("com.android.mms");
        this.m.add("com.android.deskclock");
        this.m.add("tv.danmaku.bili");
        this.m.add("com.tencent.mm");
        this.m.add("com.hihonor.mms");
        this.m.add("com.eg.android.AlipayGphone");
        this.m.add("com.twitter.android");
        this.m.add("com.sofascore.results");
        this.m.add("es.evobanco.bancamovil");
    }

    private void h() {
        this.x.add("com.tencent.mm");
        this.x.add("com.tencent.qqmusic");
        this.x.add("com.netease.cloudmusic");
        this.x.add("com.spotify.music");
    }

    private void d() {
        this.j.put("com.whatsapp", "silent_notifications_");
        this.j.put("com.gbox.com.whatsapp", "silent_notifications_");
    }

    private void g() {
        this.ae.add("com.whatsapp");
        this.ae.add("com.gbox.com.whatsapp");
        this.ae.add("com.whatsapp.w4b");
        this.ae.add("com.facebook.orca");
        this.ae.add("org.telegram.messenger");
        this.ae.add("org.telegram.messenger.web");
        this.ae.add("org.telegram.messenger.beta");
        this.ae.add("com.viber.voip");
        this.ae.add("jp.naver.line.android");
        this.ae.add("com.gbox.jp.naver.line.android");
        this.ae.add("com.instagram.android");
        this.ae.add("com.vkontakte.android");
        this.ae.add("com.skype.raider");
        this.ae.add("org.thoughtcrime.securesms");
    }

    private void c() {
        jjc b2 = jjc.b(this);
        this.u = b2;
        this.af = b2.e();
        this.ac = this.u.b();
        if (this.af == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            this.af = currentTimeMillis;
            this.u.b(currentTimeMillis);
            LogUtil.a("NotificPush", "init mUpdateReceivedTime");
        }
        if (this.ac == 0) {
            long currentTimeMillis2 = System.currentTimeMillis();
            this.ac = currentTimeMillis2;
            this.u.a(currentTimeMillis2);
            LogUtil.a("NotificPush", "init mReportBiTime");
        }
    }

    private void m() {
        this.l.add("com.android.server.telecom");
        this.l.add("com.android.phone");
        this.l.add("com.samsung.android.dialer");
        this.l.add("com.oneplus.dialer");
        this.l.add("com.android.dialer");
        this.l.add("com.google.android.dialer");
        this.l.add("com.android.contacts");
        this.l.add("com.huawei.contacts");
        this.l.add("com.hihonor.contacts");
        this.l.add("com.android.incallui");
    }

    private void o() {
        this.ai.add("com.whatsapp");
        this.ai.add("com.gbox.com.whatsapp");
        this.ai.add("com.tencent.mm");
        this.ai.add("org.telegram.messenger");
        this.ai.add("jp.naver.line.android");
        this.ai.add("com.gbox.jp.naver.line.android");
        this.ai.add("com.instagram.android");
        this.ai.add("com.gbox.com.instagram.android");
        this.ai.add("com.alibaba.android.rimet.aliding");
        this.ai.add("com.alibaba.android.rimet");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        if (k()) {
            boolean e = e(e());
            ReleaseLogUtil.e("Notify_NotificPush", "onNotification register listenable-apps to phone: isSetFilter status is=", Boolean.valueOf(e));
            if (e) {
                NotificationContentProviderUtil.a(this.am);
                t();
                return;
            }
            return;
        }
        ReleaseLogUtil.e("Notify_NotificPush", "onNotification register listenable-apps to phone Failed: SYSTEM_IS_NOT_HARMONY4!");
    }

    private boolean k() {
        String str;
        return HarmonyBuild.d && (str = HarmonyBuild.b) != null && !str.isEmpty() && str.charAt(0) >= '4' && str.charAt(0) <= '9';
    }

    private void t() {
        if (this.i != null) {
            return;
        }
        this.i = new BroadcastReceiver() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DeviceInfo deviceInfo;
                if (context == null || intent == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null || NotificationPushListener.this.i == null) {
                    return;
                }
                ReleaseLogUtil.e("Notify_NotificPush", "mConnectStateChangedReceiver() status : ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                if (deviceConnectState == 2) {
                    NotificationPushListener notificationPushListener = NotificationPushListener.this;
                    notificationPushListener.e((List<String>) notificationPushListener.e());
                    NotificationContentProviderUtil.a(NotificationPushListener.this.am);
                } else if (deviceConnectState == 3) {
                    NotificationContentProviderUtil.d(NotificationPushListener.this.am);
                } else {
                    LogUtil.h("NotificPush", "Invalid device connect state.");
                }
            }
        };
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.i, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        this.s = new ContentObserver(null) { // from class: com.huawei.bone.ui.setting.NotificationPushListener.6
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.6.5
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean d2 = NotificationContentProviderUtil.d("connect_wear");
                        LogUtil.a("NotificPush", "connect_wear is :", Boolean.valueOf(d2));
                        if (d2) {
                            ReleaseLogUtil.e("Notify_NotificPush", "notificationEnableChangeToSetFilter start.");
                            NotificationPushListener.this.e((List<String>) NotificationPushListener.this.e());
                        } else {
                            ReleaseLogUtil.e("Notify_NotificPush", "setNotificationFilterPackages defaultFilterPackage;");
                            NotificationPushListener.this.e((List<String>) Arrays.asList("xxx.xxx.xxx"));
                        }
                    }
                });
            }
        };
        BaseApplication.getContext().getContentResolver().registerContentObserver(Uri.parse(jdz.j), false, this.s);
        LogUtil.a("NotificPush", "register mDeviceStatusChangeCallback and mIsNotificationEnableChangeObserver success.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(List<String> list) {
        String str = "true";
        try {
            if (list != null) {
                try {
                    if (!list.isEmpty()) {
                        ReleaseLogUtil.e("Notify_NotificPush", "onNotification register listenable-apps to phone:packages size is: ", Integer.valueOf(list.size()));
                        getClass().getMethod("setNotificationFilterApps", List.class).invoke(this, list);
                        try {
                            ReleaseLogUtil.e("Notify_NotificPush", "onNotification register listenable-apps to phone success!");
                            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                            linkedHashMap.put("setNotificationFilterResult", "true");
                            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
                            LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                            return true;
                        } catch (IllegalAccessException e) {
                            e = e;
                            ReleaseLogUtil.c("Notify_NotificPush", "onNotification register listenable-apps to phone throw exception: ", ExceptionUtils.d(e));
                            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
                            linkedHashMap2.put("setNotificationFilterResult", str);
                            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap2);
                            LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                            return false;
                        } catch (NoSuchMethodException e2) {
                            e = e2;
                            ReleaseLogUtil.c("Notify_NotificPush", "onNotification register listenable-apps to phone throw exception: ", ExceptionUtils.d(e));
                            LinkedHashMap<String, String> linkedHashMap22 = new LinkedHashMap<>();
                            linkedHashMap22.put("setNotificationFilterResult", str);
                            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap22);
                            LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                            return false;
                        } catch (InvocationTargetException e3) {
                            e = e3;
                            ReleaseLogUtil.c("Notify_NotificPush", "onNotification register listenable-apps to phone throw exception: ", ExceptionUtils.d(e));
                            LinkedHashMap<String, String> linkedHashMap222 = new LinkedHashMap<>();
                            linkedHashMap222.put("setNotificationFilterResult", str);
                            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap222);
                            LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                            return false;
                        }
                    }
                } catch (IllegalAccessException e4) {
                    e = e4;
                    str = "false";
                    ReleaseLogUtil.c("Notify_NotificPush", "onNotification register listenable-apps to phone throw exception: ", ExceptionUtils.d(e));
                    LinkedHashMap<String, String> linkedHashMap2222 = new LinkedHashMap<>();
                    linkedHashMap2222.put("setNotificationFilterResult", str);
                    OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap2222);
                    LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                    return false;
                } catch (NoSuchMethodException e5) {
                    e = e5;
                    str = "false";
                    ReleaseLogUtil.c("Notify_NotificPush", "onNotification register listenable-apps to phone throw exception: ", ExceptionUtils.d(e));
                    LinkedHashMap<String, String> linkedHashMap22222 = new LinkedHashMap<>();
                    linkedHashMap22222.put("setNotificationFilterResult", str);
                    OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap22222);
                    LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                    return false;
                } catch (InvocationTargetException e6) {
                    e = e6;
                    str = "false";
                    ReleaseLogUtil.c("Notify_NotificPush", "onNotification register listenable-apps to phone throw exception: ", ExceptionUtils.d(e));
                    LinkedHashMap<String, String> linkedHashMap222222 = new LinkedHashMap<>();
                    linkedHashMap222222.put("setNotificationFilterResult", str);
                    OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap222222);
                    LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                    return false;
                } catch (Throwable th) {
                    th = th;
                    str = "false";
                    LinkedHashMap<String, String> linkedHashMap3 = new LinkedHashMap<>();
                    linkedHashMap3.put("setNotificationFilterResult", str);
                    OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap3);
                    LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
                    throw th;
                }
            }
            LogUtil.h("NotificPush", "onNotification register listenable-apps to phone failed: APPLIST_IS_EMPTY!");
            LinkedHashMap<String, String> linkedHashMap4 = new LinkedHashMap<>();
            linkedHashMap4.put("setNotificationFilterResult", "false");
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap4);
            LogUtil.a("NotificPush", "setNotificationFilterResult OpAnalytics finish");
            return false;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> e() {
        List<String> arrayList = new ArrayList<>();
        boolean n = n();
        ReleaseLogUtil.e("Notify_NotificPush", "onNotification get listenable-apps list start:master switch is turn on?(isAuthorized=)", Boolean.valueOf(n));
        if (n) {
            arrayList = NotificationContentProviderUtil.i();
        }
        arrayList.addAll(this.l);
        LogUtil.a("NotificPush", "NotificationContentProviderUtil.isNotificationLiveEnabled()", Boolean.valueOf(NotificationContentProviderUtil.j()));
        a(arrayList);
        LogUtil.a("NotificPush", "onNotification getSubSwitchOnListAndAddLiveInfoApps listenable-apps including:", arrayList.toString());
        return arrayList;
    }

    private void a(List<String> list) {
        if (NotificationContentProviderUtil.j()) {
            boolean a2 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_TYPE", "SUPPORT_NOTIFY_LIVE_TYPE", false);
            boolean a3 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", false);
            String e = SharedPreferenceManager.e("NOTIFY_LIVE_LEVEL_VALUE", "NOTIFY_LIVE_LEVEL_VALUE", "levelDefault");
            LogUtil.a("NotificPush", "getLiveNotificationCloudList hasNotifyLiveCapability is ", Boolean.valueOf(a2), ",  hasNotifyLiveLevelCapability is ", Boolean.valueOf(a3), ",  notifyLiveLevel is " + e);
            if (a3) {
                list.addAll(NotificationContentProviderUtil.c(e));
            } else if (a2) {
                list.addAll(NotificationContentProviderUtil.b());
            }
        }
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("Notify_NotificPush", "onDestroy start");
        synchronized (f1929a) {
            if (this.w != null) {
                BroadcastManagerUtil.bFK_(BaseApplication.getContext(), this.w);
            }
            if (this.g != null) {
                BaseApplication.getContext().unregisterReceiver(this.g);
            }
        }
        stopForeground(true);
        jdh.c().a(20211231);
        if (this.i != null) {
            ReleaseLogUtil.e("Notify_NotificPush", "onDestroy unregister connectChangeReceiver");
            BaseApplication.getContext().unregisterReceiver(this.i);
            this.i = null;
        }
        if (this.s != null) {
            ReleaseLogUtil.e("Notify_NotificPush", "onDestroy unregister notificationEnableChange");
            BaseApplication.getContext().getContentResolver().unregisterContentObserver(this.s);
            this.s = null;
        }
        NotificationContentProviderUtil.NotificationLiveChangerListener notificationLiveChangerListener = this.am;
        if (notificationLiveChangerListener != null) {
            NotificationContentProviderUtil.d(notificationLiveChangerListener);
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        if (statusBarNotification != null) {
            this.z++;
            q();
            s();
            os_(statusBarNotification);
            return;
        }
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted ERROR: StatusBarNotification is Null");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        if (statusBarNotification != null) {
            ot_(statusBarNotification, false);
        } else {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved ERROR: StatusBarNotification is Null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void on_(Message message) {
        String str;
        String str2;
        if (message == null) {
            LogUtil.h("NotificPush", "delaySendRemoveSms message is null");
            return;
        }
        Object obj = message.obj;
        if (!(obj instanceof StatusBarNotification)) {
            LogUtil.h("NotificPush", "delaySendRemoveSms object not instanceof StatusBarNotification");
            return;
        }
        StatusBarNotification statusBarNotification = (StatusBarNotification) obj;
        Notification notification = statusBarNotification.getNotification();
        if (notification != null) {
            Bundle bundle = notification.extras;
            if (bundle != null) {
                str = null;
                str2 = bdo.ph_(bundle, NotificationCompat.EXTRA_TITLE, null);
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence != null) {
                    str = charSequence.toString();
                }
            } else {
                str = "";
                str2 = "";
            }
            this.ab.remove(str2 + str);
        }
        ot_(statusBarNotification, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void oo_(Message message) {
        if (message == null) {
            LogUtil.h("NotificPush", "delaySendRemoveSms message is null");
            return;
        }
        Object obj = message.obj;
        if (!(obj instanceof StatusBarNotification)) {
            LogUtil.h("NotificPush", "delaySendRemoveSms object not instanceof StatusBarNotification");
        } else {
            ot_((StatusBarNotification) obj, true);
        }
    }

    private void ot_(final StatusBarNotification statusBarNotification, final boolean z) {
        jdx.b(new Runnable() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.13
            @Override // java.lang.Runnable
            public void run() {
                long j;
                ReleaseLogUtil.e("Notify_NotificPush", "Start onNotificationRemoved isJumpSmsCheck: ", Boolean.valueOf(z), " ,Notification is: ", statusBarNotification.toString());
                if (bdo.e()) {
                    return;
                }
                String packageName = statusBarNotification.getPackageName();
                int id = statusBarNotification.getId();
                String key = statusBarNotification.getKey();
                Notification notification = statusBarNotification.getNotification();
                String oZ_ = bdo.oZ_(notification);
                if (notification != null) {
                    j = notification.when;
                    ReleaseLogUtil.e("Notify_NotificPush", "onNotificationRemoved: ", packageName, " removed msg when: ", Long.valueOf(j), ",postTime: ", Long.valueOf(statusBarNotification.getPostTime()));
                } else {
                    j = 0;
                }
                if (!z && kiq.b(packageName, id)) {
                    ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved filter EMUI 1390 sms");
                    Message obtain = Message.obtain();
                    obtain.what = 11;
                    obtain.obj = statusBarNotification;
                    NotificationPushListener.this.r.sendMessageDelayed(obtain, 1200L);
                    return;
                }
                Notification.Action[] actionArr = statusBarNotification.getNotification().actions;
                boolean z2 = false;
                int length = actionArr == null ? 0 : actionArr.length;
                if (NotificationCompat.CATEGORY_CALL.equals(oZ_)) {
                    NotificationPushListener.this.a(z, packageName, length);
                    if (!z && (("com.whatsapp".equals(packageName) || "com.gbox.com.whatsapp".equals(packageName)) && length == 2)) {
                        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved FILTERED because VOIP_CALLING!");
                        NotificationPushListener.this.r.removeMessages(12);
                        Message obtain2 = Message.obtain();
                        obtain2.what = 12;
                        obtain2.obj = statusBarNotification;
                        NotificationPushListener.this.r.sendMessageDelayed(obtain2, 1000L);
                        return;
                    }
                }
                if (NotificationCompat.CATEGORY_CALL.equalsIgnoreCase(oZ_) && (NotificationPushListener.this.ai.contains(packageName) || TextUtils.equals(packageName, "com.facebook.orca"))) {
                    z2 = true;
                }
                if (bdo.pi_(notification, z2, packageName)) {
                    return;
                }
                String tag = statusBarNotification.getTag();
                String pa_ = bdo.pa_(notification);
                Intent intent = new Intent();
                intent.putExtra("packagename", packageName);
                intent.putExtra("data_tag", tag);
                intent.putExtra("data_channel_id", pa_);
                intent.putExtra("data_noty_id", id);
                intent.putExtra("data_extra_noty_key", key);
                intent.putExtra("data_category", oZ_);
                if (notification != null && (notification.flags & 2) == 2 && z2 && "com.tencent.mm".equals(packageName)) {
                    intent.putExtra("data_extra_remove_call", true);
                }
                NotificationPushListener.this.oN_(intent, notification, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str, int i) {
        if (!bdo.g(str)) {
            if (!bdo.o(str)) {
                return;
            }
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved clearResendLongVibrateCache param,isJumpSmsCheck is ", Boolean.valueOf(z), ", actionLength is ", Integer.valueOf(i));
            if ((!z || i != 2) && (z || i != 1)) {
                return;
            }
        }
        bds bdsVar = b;
        bds.f338a.remove(str);
        bdsVar.a();
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved clearResendLongVibrateCache!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void oN_(Intent intent, Notification notification, long j) {
        int c;
        if (intent == null) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved intent is null");
            return;
        }
        String e = bdo.e(intent.getStringExtra("packagename"));
        if (TextUtils.isEmpty(e)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved packageName is empty");
            return;
        }
        String stringExtra = intent.getStringExtra("data_tag");
        String stringExtra2 = intent.getStringExtra("data_channel_id");
        boolean e2 = bdo.e(e, j);
        if (bdo.j(e)) {
            c = bdo.c(e, stringExtra, stringExtra2, e2);
        } else if (b(e) || bdm.pu_(notification, e) || (bdo.pj_(notification, e) && bdo.f(e))) {
            c = bdo.c(e, "", "", e2);
        } else {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationRemoved filter type is else");
            return;
        }
        Intent intent2 = new Intent(this, (Class<?>) HandleIntentService.class);
        String stringExtra3 = intent.getStringExtra("data_extra_noty_key");
        int intExtra = intent.getIntExtra("data_noty_id", -1);
        String stringExtra4 = intent.getStringExtra("data_category");
        boolean booleanExtra = intent.getBooleanExtra("data_extra_remove_call", false);
        intent2.setAction("com.huawei.bone.ACTION_NOTIFICATION_DELETE");
        intent2.putExtra("type", c);
        intent2.putExtra("packagename", e);
        intent2.putExtra("data_extra_noty_key", stringExtra3);
        intent2.putExtra("data_noty_id", intExtra);
        intent2.putExtra("data_channel_id", stringExtra2);
        intent2.putExtra("data_extra_remove_call", booleanExtra);
        intent2.putExtra("data_category", stringExtra4);
        ReleaseLogUtil.e("Notify_NotificPush", "onNotificationRemoved start to push notification msg");
        try {
            startService(intent2);
        } catch (IllegalStateException | SecurityException e3) {
            ReleaseLogUtil.c("Notify_NotificPush", "onNotificationRemoved startService: ", ExceptionUtils.d(e3));
            sqo.ab("pushRemoveNotification occur exception");
        }
        LogUtil.a("NotificPush", "onNotificationRemoved startBroadcastToPhoneService success! tag: ", stringExtra, ",msgTypes: ", Integer.valueOf(c));
    }

    private void os_(final StatusBarNotification statusBarNotification) {
        jdx.b(new Runnable() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.3
            @Override // java.lang.Runnable
            public void run() {
                if (NotificationPushListener.this.ox_(statusBarNotification)) {
                    Bundle oK_ = NotificationPushListener.this.oK_(statusBarNotification);
                    boolean oG_ = NotificationPushListener.this.oG_(oK_, statusBarNotification);
                    LogUtil.h("NotificPush", "onNotificationPosted finally filterResult: ", Boolean.valueOf(oG_), ",key: ", statusBarNotification.getKey());
                    if (oG_) {
                        return;
                    }
                    NotificationPushListener.j(NotificationPushListener.this);
                    NotificationPushListener.this.oJ_(oK_);
                    oU_(statusBarNotification, oK_);
                    NotificationPushListener.this.oL_(oK_, statusBarNotification);
                }
            }

            private void oU_(StatusBarNotification statusBarNotification2, Bundle bundle) {
                if (TextUtils.equals(statusBarNotification2.getPackageName(), "com.tencent.mobileqq") || TextUtils.equals(statusBarNotification2.getPackageName(), "com.tencent.mm")) {
                    LogUtil.a("NotificPush", "onNotificationPosted com.tencent.mobileqq or com.tencent.mm push msg DELAY_100MS");
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        LogUtil.b("NotificPush", "onNotificationPosted specialAppMsgHandling ERROR: ", ExceptionUtils.d(e));
                    }
                }
                if (TextUtils.equals(statusBarNotification2.getPackageName(), "com.whatsapp") && 2 == bundle.getInt("voip_type")) {
                    LogUtil.a("NotificPush", "onNotificationPosted com.whatsapp duringCalling push msg DELAY_100MS");
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e2) {
                        LogUtil.b("NotificPush", "onNotificationPosted specialAppMsgHandling ERROR: ", ExceptionUtils.d(e2));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void oJ_(Bundle bundle) {
        String string = bundle.getString(MediaBrowserProtocol.DATA_PACKAGE_NAME);
        long currentTimeMillis = System.currentTimeMillis() - bundle.getLong("data_extra_noty_when");
        if (currentTimeMillis > 60000) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(3);
            linkedHashMap.put("notificationDelayPkg", string);
            linkedHashMap.put("notificationDelayTime", String.valueOf(currentTimeMillis));
            linkedHashMap.put("notificationDelaySystemInfo", kiq.e());
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
            if (CommonUtil.as()) {
                sqo.ab("Notification push delay one minute package:" + string + ", delayTime:" + currentTimeMillis + ", phone:" + kiq.e());
            }
            if (CommonUtil.bh()) {
                return;
            }
            jrg.c(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ox_(StatusBarNotification statusBarNotification) {
        Notification.Action[] actionArr;
        if (statusBarNotification == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder("Start onNotificationPosted: ");
        sb.append(statusBarNotification);
        Notification notification = statusBarNotification.getNotification();
        if (notification != null && (actionArr = notification.actions) != null && actionArr.length > 0) {
            for (int i = 0; i < actionArr.length; i++) {
                if (actionArr[i] != null) {
                    sb.append(" ;actionButton");
                    sb.append(i + 1);
                    sb.append(" name: ");
                    sb.append(actionArr[i].title);
                    sb.append(actionArr[i].getRemoteInputs() == null ? "" : " support quickReply!");
                }
            }
        }
        ReleaseLogUtil.e("Notify_NotificPush", sb.toString());
        String pg_ = bdo.pg_(statusBarNotification);
        if (TextUtils.isEmpty(pg_)) {
            return false;
        }
        if (bfg.e.equalsIgnoreCase(pg_)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because INTELLIGENT_PACKAGE");
            return false;
        }
        boolean oB_ = oB_(pg_, statusBarNotification);
        ReleaseLogUtil.e("Notify_NotificPush", pg_, " onNotificationPosted isMeetPushConditions?(false=filtered): ", Boolean.valueOf(oB_));
        return oB_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle oK_(android.service.notification.StatusBarNotification r19) {
        /*
            Method dump skipped, instructions count: 476
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.bone.ui.setting.NotificationPushListener.oK_(android.service.notification.StatusBarNotification):android.os.Bundle");
    }

    private void oq_(Notification notification, Bundle bundle, Bundle bundle2) {
        boolean az = CommonUtil.az();
        LogUtil.a("NotificPush", "getDeepLinkFromTickerOrExtras isHarmony=", Boolean.valueOf(az));
        if (!az) {
            ReleaseLogUtil.d("NotificPush", "getDeepLinkFromTickerOrExtras phone is not harmony.");
            return;
        }
        CharSequence charSequence = notification.tickerText;
        if (!TextUtils.isEmpty(charSequence) && charSequence.toString().contains("ping_list")) {
            String obj = charSequence.toString();
            LogUtil.a("NotificPush", "getDeepLinkFromTickerOrExtras tickerText=", obj);
            try {
                JSONObject jSONObject = new JSONObject(obj);
                boolean has = jSONObject.has("ping_list");
                LogUtil.a("NotificPush", "getDeepLinkFromTickerOrExtras isHasPingList=" + has);
                if (has) {
                    JSONArray jSONArray = jSONObject.getJSONArray("ping_list");
                    LogUtil.a("NotificPush", "getDeepLinkFromTickerOrExtras pingListValue=" + jSONArray.toString());
                    bundle2.putString("pingList", jSONArray.toString());
                    return;
                }
                return;
            } catch (JSONException e) {
                LogUtil.b("NotificPush", "getDeepLinkFromTickerOrExtras: ", ExceptionUtils.d(e));
                sqo.ab("getDeepLinkFromTickerOrExtras occur exception");
                return;
            }
        }
        LogUtil.h("NotificPush", "getDeepLinkFromExtras original ticker is empty.");
        if (CommonUtil.bv()) {
            return;
        }
        String ph_ = bdo.ph_(bundle, "ping_list", null);
        if (!TextUtils.isEmpty(ph_)) {
            LogUtil.a("NotificPush", "getDeepLinkFromExtras ticker is empty, extras pingList=", ph_);
            bundle2.putString("pingList", ph_);
        } else {
            LogUtil.a("NotificPush", "getDeepLinkFromExtras ticker is empty, extras pingList is empty");
        }
    }

    private void oP_(Bundle bundle, String str, Notification notification) {
        NotificationCompat.MessagingStyle.Message message;
        try {
            NotificationCompat.Style extractStyleFromNotification = NotificationCompat.Style.extractStyleFromNotification(notification);
            if (extractStyleFromNotification instanceof NotificationCompat.MessagingStyle) {
                List<NotificationCompat.MessagingStyle.Message> messages = ((NotificationCompat.MessagingStyle) extractStyleFromNotification).getMessages();
                if (messages.size() >= 1 && (message = messages.get(messages.size() - 1)) != null) {
                    boolean z = message.getPerson() != null;
                    LogUtil.h("NotificPush", "onNotificationPosted packageMessage: MSG_SENDER_EXIST ? ", Boolean.valueOf(z), ",key: ", str);
                    if (z) {
                        bundle.putString("notification_sender", message.getPerson().toString());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            LogUtil.b("NotificPush", "setSenderIntoBundle IllegalArgumentException: ", ExceptionUtils.d(e));
        }
    }

    private String om_(Notification notification, String str, String str2, Bundle bundle) {
        if (!TextUtils.equals(str2, "org.telegram.messenger")) {
            return str;
        }
        LogUtil.h("NotificPush", "onNotificationPosted dealWithTelegramMissedCall Start get androidText!");
        Parcelable[] parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
        if (!CollectionUtils.b(parcelableArray)) {
            String ph_ = bdo.ph_((Bundle) parcelableArray[parcelableArray.length - 1], Constant.TEXT, null);
            LogUtil.h("NotificPush", "onNotificationPosted dealWithTelegramMissedCall androidText is: ", bky.e(ph_));
            if (!TextUtils.isEmpty(ph_)) {
                return ph_;
            }
        }
        LogUtil.h("NotificPush", "onNotificationPosted dealWithTelegramMissedCallandroidText list is null or empty!continue getView");
        View op_ = op_(BaseApplication.getContext(), notification);
        if (op_ == null) {
            LogUtil.h("NotificPush", "onNotificationPosted dealWithTelegramMissedCall view is null");
            return str;
        }
        String or_ = or_(op_);
        boolean equals = TextUtils.equals(or_, str);
        ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted getLastViewContent empty? ", Boolean.valueOf(TextUtils.isEmpty(or_)), ",lastContentValue equals notificationText? ", Boolean.valueOf(equals));
        return equals ? str : or_;
    }

    private static View op_(Context context, Notification notification) {
        RemoteViews remoteViews;
        LogUtil.a("NotificPush", "onNotificationPosted getContentView enter");
        View view = null;
        try {
            if (notification.contentView != null) {
                LogUtil.a("NotificPush", "onNotificationPosted getContentView notification.contentView is not null");
                remoteViews = notification.contentView;
            } else {
                LogUtil.a("NotificPush", "onNotificationPosted getContentView Build.VERSION.SDK_INT >= 24");
                remoteViews = Notification.Builder.recoverBuilder(context, notification).createContentView();
            }
        } catch (SecurityException e) {
            LogUtil.b("NotificPush", "onNotificationPosted getContentView SecurityException: ", ExceptionUtils.d(e));
            remoteViews = null;
        }
        if (remoteViews != null) {
            try {
                view = remoteViews.apply(context, null);
            } catch (Exception unused) {
                LogUtil.b("NotificPush", "onNotificationPosted getContentView occur Exception");
            }
        }
        if (view == null) {
            LogUtil.h("NotificPush", "onNotificationPosted getContentView view is null.");
        }
        return view;
    }

    private static String or_(View view) {
        LogUtil.a("NotificPush", "onNotificationPosted getLastViewContent enter");
        StringBuilder sb = new StringBuilder();
        oS_(view, sb, 0);
        return sb.toString();
    }

    private static void oS_(View view, StringBuilder sb, int i) {
        if (i >= 50) {
            LogUtil.h("NotificPush", "onNotificationPosted getLastViewContent traversalView reach max recursive times");
            return;
        }
        if (view == null) {
            LogUtil.h("NotificPush", "onNotificationPosted getLastViewContent traversalView view is null");
            return;
        }
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return;
            }
            sb.delete(0, sb.length());
            sb.append(text);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                oS_(viewGroup.getChildAt(i2), sb, i + 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean oG_(Bundle bundle, StatusBarNotification statusBarNotification) {
        return ow_(statusBarNotification, oI_(bundle, oH_(bundle, statusBarNotification.getNotification())));
    }

    private boolean oH_(Bundle bundle, Notification notification) {
        String ph_ = bdo.ph_(bundle, MediaBrowserProtocol.DATA_PACKAGE_NAME, null);
        int i = bundle.getInt("liveType", 0);
        String ph_2 = bdo.ph_(bundle, "liveEvent", "OTHER");
        if (SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_TYPE", "SUPPORT_NOTIFY_LIVE_TYPE", false) && i != 0 && bff.b.contains(ph_2.toUpperCase(Locale.ROOT))) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted continue: ", ph_, " is a live type info");
            return false;
        }
        if (TextUtils.equals(ph_, "com.autonavi.minimap") && !TextUtils.isEmpty(bundle.getString("data_extra_miniFocusParam"))) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted FILTERED: taxi live notification");
            return true;
        }
        String ph_3 = bdo.ph_(bundle, "data_category", null);
        if (oC_(bundle, notification, true)) {
            bundle.putBoolean("data_extra_send_call_no_wechat", true);
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted continue: ", ph_, " is voip CALLING");
            return false;
        }
        String ph_4 = bdo.ph_(bundle, "data_tag", null);
        String ph_5 = bdo.ph_(bundle, "data_channel_id", null);
        if (bdo.c(ph_, ph_4, ph_5) || oy_(bundle, ph_3, bundle.getInt("data_flags"), ph_)) {
            return true;
        }
        String ph_6 = bdo.ph_(bundle, "data_extra_noty_type", null);
        int i2 = bundle.getInt("data_noty_id", -1);
        if (TextUtils.equals(ph_6, "hang_up") && i2 != 1390) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted FILTERED: ", ph_, " is repeat");
            return true;
        }
        if (bdo.ph_(bundle, "notification_sender", null) == null && TextUtils.equals("org.thoughtcrime.securesms", ph_)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED by SENDER_IS_NULL");
            return true;
        }
        String ph_7 = bdo.ph_(bundle, "data_extra_noty_key", null);
        String ph_8 = bdo.ph_(bundle, "data_extra_text", null);
        if (this.o.contains(ph_) && b(ph_8, ph_7)) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted FILTERED by KEY_WHEN_500MS,key: ", ph_7);
            return true;
        }
        int i3 = bundle.getInt("data_extra_action_length");
        String str = this.j.get(ph_);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(ph_5) && ph_5.contains(str) && i3 == 1) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because HIT_CHANNEL_BLACK_LIST,pkg=", ph_);
            return true;
        }
        if (!ou_(notification, ph_, ph_3)) {
            return false;
        }
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because ACTION_LENGTH_IS_1,pkg=", ph_);
        return true;
    }

    private boolean ou_(Notification notification, String str, String str2) {
        Notification.Action[] actionArr;
        if (notification == null || (actionArr = notification.actions) == null || actionArr.length != 1 || bdo.h(str) || !this.ae.contains(str)) {
            return false;
        }
        if (CommonUtil.bv()) {
            Notification.Action action = actionArr[0];
            CharSequence charSequence = action == null ? "" : action.title;
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(3);
            linkedHashMap.put("actionsLengthPackageName", str);
            linkedHashMap.put("actionsLengthCategory", str2);
            linkedHashMap.put("actionsLengthTitle", TextUtils.isEmpty(charSequence) ? "" : charSequence.toString());
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
        }
        if (bdo.i(str) && NotificationCompat.CATEGORY_CALL.equalsIgnoreCase(str2)) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted continue,pkg=", str, " is missedCall");
            return false;
        }
        if (TextUtils.equals("org.telegram.messenger", str)) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted continue,Telegram actionLength=1 DO_NOT_FILTER");
            return false;
        }
        if (!TextUtils.equals("org.thoughtcrime.securesms", str)) {
            return true;
        }
        ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted continue,Signal actionLength=1 DO_NOT_FILTER");
        return false;
    }

    private boolean oI_(Bundle bundle, boolean z) {
        if (bundle == null || z) {
            return true;
        }
        String ph_ = bdo.ph_(bundle, "data_extra_title", null);
        String ph_2 = bdo.ph_(bundle, "data_extra_text", null);
        String ph_3 = bdo.ph_(bundle, MediaBrowserProtocol.DATA_PACKAGE_NAME, null);
        String ph_4 = bdo.ph_(bundle, "data_extra_noty_key", null);
        int i = bundle.getInt("data_noty_id", -1);
        if (ov_(bundle)) {
            return true;
        }
        if (TextUtils.isEmpty(ph_2) && i == 123) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because notificationId=123,key: ", ph_4);
            return true;
        }
        if ("Push Service".equalsIgnoreCase(ph_2) && "Push Service".equalsIgnoreCase(ph_)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because TITLE_CONTENT_IS_PUSH_SERVICE,pkg=", ph_3);
            return true;
        }
        if (bdo.b() && e(ph_3, ph_, ph_2)) {
            return true;
        }
        long j = bundle.getLong("data_extra_noty_posttime");
        if (this.p.contains(ph_4 + j)) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted FILTERED by KEY_POSTTIME,postTime: ", Long.valueOf(j), ",key: ", ph_4);
            return true;
        }
        this.p.offer(ph_4 + j);
        long j2 = bundle.getLong("data_extra_noty_when");
        String ph_5 = bdo.ph_(bundle, "log_content", null);
        if (e(i, ph_3) && b(ph_4, j2, ph_2, ph_5)) {
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted FILTERED by KeyWhenContent,when: ", Long.valueOf(j2), ",key: ", ph_4);
            return true;
        }
        if (kiq.c(ph_3)) {
            if (i == 1390) {
                this.ab.put(ph_2 + j2, Long.valueOf(SystemClock.elapsedRealtime()));
                l();
            } else {
                Long remove = this.ab.remove(ph_2 + j2);
                if (remove == null) {
                    LogUtil.h("NotificPush", "onNotificationPosted continue,key: ", ph_4, " lastSendTime is null");
                    return false;
                }
                if (SystemClock.elapsedRealtime() - remove.longValue() < PreConnectManager.CONNECT_INTERNAL) {
                    ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because 1390_REPEAT_SMS", ",key: ", ph_4);
                    this.r.removeMessages(11);
                    return true;
                }
            }
        }
        return false;
    }

    private void l() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("notificationSms1390", kiq.e());
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
    }

    private boolean ow_(StatusBarNotification statusBarNotification, boolean z) {
        String packageName = statusBarNotification.getPackageName();
        if (!z && this.x.contains(packageName) && (z = TextUtils.equals(NotificationCompat.CATEGORY_TRANSPORT, bdo.oZ_(statusBarNotification.getNotification())))) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because MEDIA_PLAY_ONGOING,key: ", statusBarNotification.getKey());
        }
        return z;
    }

    private boolean ov_(Bundle bundle) {
        if (bundle.getInt("liveType") != 0 || !TextUtils.isEmpty(bdo.ph_(bundle, "data_extra_text", ""))) {
            return false;
        }
        bundle.putString("data_extra_text", getString(R.string.IDS_notification_message_prompt));
        if (!TextUtils.isEmpty(bdo.ph_(bundle, "data_extra_title", ""))) {
            return false;
        }
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because TITLE_IS_EMPTY,key: ", bdo.ph_(bundle, "data_extra_noty_key", ""));
        return true;
    }

    private boolean b(String str, long j, String str2, String str3) {
        LogUtil.a("NotificPush", "onNotificationPosted enter KeyWhenContent filter,when: ", Long.valueOf(j), ",content: ", str3, ",key: ", str);
        if (this.v.contains(str + j + str2)) {
            return true;
        }
        this.v.offer(str + j + str2);
        return false;
    }

    private boolean e(int i, String str) {
        return this.m.contains(str) || (i != 1390 && TextUtils.equals(bdo.e(str), "com.android.mms") && CommonUtil.bh());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void oL_(Bundle bundle, StatusBarNotification statusBarNotification) {
        Bundle bundle2 = new Bundle();
        LogUtil.a("NotificPush", "onNotificationPosted startBroadcastToPhoneService method start!");
        oM_(bundle, bundle2);
        String ph_ = bdo.ph_(bundle, MediaBrowserProtocol.DATA_PACKAGE_NAME, null);
        oO_(statusBarNotification, bundle2, ph_);
        int i = bundle.getInt("voip_type");
        oQ_(statusBarNotification, bundle2, ph_, i);
        long j = bundle.getLong("data_extra_noty_when", 0L);
        bundle2.putLong("data_extra_noty_when", j);
        String ph_2 = bdo.ph_(bundle, "data_tag", null);
        int c = bdo.c(ph_, ph_2, bdo.ph_(bundle, "data_channel_id", null), bdo.e(ph_, j));
        bundle2.putInt("type", c);
        if (c == 14) {
            bundle2.putInt("incoming_type", 7);
        }
        bundle2.putString("packagename", ph_);
        bundle2.putString("title", bdo.ph_(bundle, "data_extra_title", null));
        int d2 = d(c);
        bundle2.putInt("title_type", d2);
        bundle2.putString("data_category", bdo.ph_(bundle, "data_category", null));
        if (this.k == -1) {
            this.k = SharedPreferenceManager.a("CONTENT_LENGTH", "CONTENT_LENGTH_SIZE", GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
        }
        bundle2.putString(Constant.TEXT, cvx.e(khs.b(2, cvx.c(bdo.ph_(bundle, "data_extra_text", null)), cvx.c("..."), this.k)));
        bundle2.putInt("text_type", 1);
        bundle2.putInt("data_noty_id", bundle.getInt("data_noty_id", -1));
        String ph_3 = bdo.ph_(bundle, "data_extra_noty_key", null);
        bundle2.putString("data_extra_noty_key", ph_3);
        bundle2.putString("data_extra_noty_harmony_alert", bdo.ph_(bundle, "data_extra_noty_harmony_alert", null));
        bundle2.putBoolean("data_extra_send_call_no_wechat", bundle.getBoolean("data_extra_send_call_no_wechat", false));
        bundle2.putBoolean("data_extra_send_call_wechat", bundle.getBoolean("data_extra_send_call_wechat", false));
        if (bundle.getInt("liveType", 0) != 0) {
            LogUtil.a("NotificPush", "onNotificationPosted contains live body.");
            bundle2.putInt("liveOperation", bundle.getInt("liveOperation"));
            bundle2.putInt("liveKeepTime", bundle.getInt("liveKeepTime"));
            bundle2.putInt("liveType", bundle.getInt("liveType"));
            bundle2.putString("liveEvent", bdo.ph_(bundle, "liveEvent", null));
            bundle2.putString("featureTitleText", bdo.ph_(bundle, "featureTitleText", null));
            bundle2.putString("featureContentText", bdo.ph_(bundle, "featureContentText", null));
            bundle2.putString("titleOverlay", bdo.ph_(bundle, "titleOverlay", null));
            bundle2.putString("contentOverlay", bdo.ph_(bundle, "contentOverlay", null));
            bundle2.putBoolean("externalEnable", bundle.getBoolean("externalEnable"));
            bundle2.putString("externalTitle", bdo.ph_(bundle, "externalTitle", null));
            bundle2.putString("externalBody", bdo.ph_(bundle, "externalBody", null));
        }
        if (i == 1 && TextUtils.isEmpty(bdo.ph_(bundle2, "reject_button_key", null))) {
            bundle2.putInt("voip_type", 0);
        } else {
            bundle2.putInt("voip_type", i);
        }
        String ph_4 = bdo.ph_(bundle, "pingList", null);
        if (!TextUtils.isEmpty(ph_4)) {
            bundle2.putString("pingList", ph_4);
        }
        oR_(bundle2);
        ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted startBroadcastToPhoneService success! tag: ", ph_2, ",msgTypes: ", Integer.valueOf(c), ",titleType: ", Integer.valueOf(d2), ",notificationKey: ", ph_3);
    }

    private void oQ_(StatusBarNotification statusBarNotification, Bundle bundle, String str, int i) {
        if (TextUtils.isEmpty(str) || i != 1) {
            return;
        }
        Notification.Action[] actionArr = statusBarNotification.getNotification().actions;
        if (actionArr == null || actionArr.length == 0) {
            LogUtil.h("NotificPush", "onNotificationPosted VOIP! ", str, " has no action button in statusBar");
            return;
        }
        for (Notification.Action action : actionArr) {
            String pc_ = bdo.pc_(action);
            if (pc_.equals(b.d.get(str))) {
                bundle.putString("reject_button_key", pc_);
                bundle.putInt("incoming_type", 8);
                return;
            }
        }
    }

    private void oR_(Bundle bundle) {
        Intent intent = new Intent(this, (Class<?>) HandleIntentService.class);
        intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
        intent.putExtras(bundle);
        ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted start to push notification msg");
        try {
            startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            ReleaseLogUtil.c("Notify_NotificPush", "onNotificationPosted startService: ", ExceptionUtils.d(e));
            sqo.ab("startSendBroadcastToPhoneService occur exception");
        }
        if (CommonUtil.ai(BaseApplication.getContext())) {
            return;
        }
        LogUtil.a("NotificPush", "onNotificationPosted phoneService not running");
        iyv.c(1001);
    }

    private int d(int i) {
        return this.ad.contains(Integer.valueOf(i)) ? 2 : 3;
    }

    private void oM_(Bundle bundle, Bundle bundle2) {
        int i;
        if (bundle == null || bundle2 == null) {
            LogUtil.h("NotificPush", "onNotificationPosted processChannelPolicy inputBundle or tempBundle is null");
            return;
        }
        String ph_ = bdo.ph_(bundle, "data_channel_id", null);
        bundle2.putString("data_channel_id", ph_);
        Iterator<bdk> it = bdo.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = 15;
                break;
            }
            bdk next = it.next();
            if (next == null) {
                LogUtil.h("NotificPush", "onNotificationPosted processChannelPolicy ChannelPolicy is null");
            } else {
                String a2 = next.a();
                String d2 = next.d();
                if (TextUtils.equals(bdo.ph_(bundle, MediaBrowserProtocol.DATA_PACKAGE_NAME, null), a2) && !TextUtils.isEmpty(ph_) && ph_.contains(d2)) {
                    LogUtil.a("NotificPush", "onNotificationPosted processChannelPolicy matched.");
                    i = next.b();
                    break;
                }
            }
        }
        bundle2.putInt("data_extra_reminder_status", i);
        ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted reminderStatus is(15=0x000F=vibrate): ", Integer.valueOf(i));
    }

    private boolean oB_(String str, StatusBarNotification statusBarNotification) {
        if (!DeviceUtil.a()) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because HAS_NO_DEVICE,pkg=", str);
            return false;
        }
        if (cvz.a() == 1) {
            w();
            this.aa++;
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because SYNERGY_IN_CHARGE,pkg=", str);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
            linkedHashMap.put("notificationFilteredBySynergyChannelPkg", str);
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
            return false;
        }
        if (!NotificationContentProviderUtil.h()) {
            DeviceUtil.fbV_(this, true, null);
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because DEVICE_CAPABILITY_INCAPABLE,pkg=", str);
            return false;
        }
        if (!oE_(statusBarNotification, str)) {
            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(1);
            linkedHashMap2.put("notificationFilteredByAppSubSwitchClosePkg", str);
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap2);
            return false;
        }
        if (n()) {
            return true;
        }
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because MASTER_SWITCH_TURN_OFF,pkg=", str);
        LinkedHashMap<String, String> linkedHashMap3 = new LinkedHashMap<>(1);
        linkedHashMap3.put("notificationFilteredByMainSwitchClosePkg", str);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap3);
        return false;
    }

    private boolean b(String str) {
        if (NotificationContentProviderUtil.d(str)) {
            return true;
        }
        return this.l.contains(str);
    }

    private boolean n() {
        return NotificationContentProviderUtil.e();
    }

    private boolean oE_(StatusBarNotification statusBarNotification, String str) {
        Notification notification = statusBarNotification.getNotification();
        boolean a2 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_TYPE", "SUPPORT_NOTIFY_LIVE_TYPE", false);
        if (bdm.pu_(notification, str)) {
            new bdn().ps_(statusBarNotification);
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because watchIsSupportLive is true,message send by LiveNotificationHandle,pkg=", str);
            return false;
        }
        if (a2 && bdo.pj_(notification, str)) {
            return bdo.f(str);
        }
        if (b(str)) {
            return true;
        }
        ol_(statusBarNotification, str);
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because SUB_SWITCH_TURN_OFF,pkg=", str);
        return false;
    }

    private void ol_(StatusBarNotification statusBarNotification, String str) {
        Notification notification = statusBarNotification.getNotification();
        if (bdm.pu_(notification, str)) {
            String key = statusBarNotification.getKey();
            if (notification != null) {
                long j = notification.when;
                Bundle bundle = notification.extras;
                if (bundle != null) {
                    try {
                        String pf_ = bdo.pf_(bundle);
                        String pd_ = bdo.pd_(bundle);
                        String b2 = bky.b(pf_);
                        String e = bky.e(pd_);
                        ReleaseLogUtil.e("Notify_NotificPush", "addLogPrintForLiveLevelApp pkgName:", str, ", when:", Long.valueOf(j), ", postTime:", Long.valueOf(statusBarNotification.getPostTime()));
                        LogUtil.a("NotificPush", "addLogPrintForLiveLevelApp key:", key, ", title:", b2, ", content:", e);
                    } catch (IllegalArgumentException e2) {
                        LogUtil.b("NotificPush", "packageMessage IllegalArgumentException: ", ExceptionUtils.d(e2));
                    }
                }
            }
        }
    }

    private void q() {
        if (this.u == null) {
            jjc b2 = jjc.b(this);
            this.u = b2;
            this.af = b2.e();
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.af >= 3600000) {
            this.u.b(currentTimeMillis);
            this.af = currentTimeMillis;
            this.u.a(this.z);
            this.u.d(this.q);
            this.u.b(this.aa);
            LogUtil.a("NotificPush", "mReceivedMessageNumber number is: ", Integer.valueOf(this.z), " ,mFilteredMessageNumber is: ", Integer.valueOf(this.q), " ,mSynergyMessageNumber is: ", Integer.valueOf(this.aa));
            this.z = 0;
            this.q = 0;
            this.aa = 0;
        }
    }

    private void s() {
        if (this.u == null) {
            jjc b2 = jjc.b(this);
            this.u = b2;
            this.ac = b2.b();
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.ac >= 86400000) {
            this.u.a(currentTimeMillis);
            this.ac = currentTimeMillis;
            this.u.d();
        }
    }

    private void w() {
        if (CommonUtil.bh() || CommonUtil.bf()) {
            return;
        }
        if (this.u == null) {
            this.u = jjc.b(this);
        }
        String str = Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            str = "1";
        }
        this.u.e("midware", str, OperationKey.NOTIFY_MIDWARE_USE_2129005.value());
    }

    private boolean e(String str, String str2, String str3) {
        boolean z;
        d dVar = this.y.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (dVar != null) {
            String d2 = dVar.d();
            String c = dVar.c();
            if (currentTimeMillis - dVar.e() < 3000 && TextUtils.equals(str3, d2) && TextUtils.equals(str2, c)) {
                ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because SAME_CONTENT_TITLE_IN_3S,pkg=", str);
                z = true;
                d dVar2 = new d();
                dVar2.d(currentTimeMillis);
                dVar2.a(str2);
                dVar2.b(str3);
                this.y.put(str, dVar2);
                a(this.f, this.y, 3000L, "isSameContent");
                return z;
            }
        }
        z = false;
        d dVar22 = new d();
        dVar22.d(currentTimeMillis);
        dVar22.a(str2);
        dVar22.b(str3);
        this.y.put(str, dVar22);
        a(this.f, this.y, 3000L, "isSameContent");
        return z;
    }

    private void a(TimerTask timerTask, Map map, long j, String str) {
        timerTask.cancel();
        LogUtil.a("NotificPush", str, " timerTask been canceled");
        try {
            d.schedule(b(map, str), j);
        } catch (IllegalArgumentException | IllegalStateException e) {
            ReleaseLogUtil.c("NotificPush", "activeClearMapTimerTaskAfterGap exception: ", ExceptionUtils.d(e));
        }
    }

    private TimerTask b(final Map map, final String str) {
        return new TimerTask() { // from class: com.huawei.bone.ui.setting.NotificationPushListener.5
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                map.clear();
                ReleaseLogUtil.e("Notify_NotificPush", str, " map been cleared");
            }
        };
    }

    private boolean b(String str, String str2) {
        Map<String, Long> map = this.n;
        if (map == null || map.size() == 0) {
            return false;
        }
        Long l = this.n.get(str2 + str);
        long currentTimeMillis = System.currentTimeMillis();
        if (l == null || currentTimeMillis - l.longValue() > 500) {
            return false;
        }
        this.n.remove(str2 + str);
        LogUtil.a("NotificPush", "onNotificationPosted WARNING: ", str2 + str, " has same value,when: ", l);
        return true;
    }

    private boolean oy_(Bundle bundle, String str, int i, String str2) {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            z = false;
        } else {
            if (bdo.b(str2) && str.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL)) {
                ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because CATEGORY_IS_CALL,pkg=", str2);
                return true;
            }
            z = oD_(bundle, str, str2, i);
            if (TextUtils.equals(str2, "com.whatsapp") && iyg.c() && z) {
                ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted filter by samsung phone whatsapp voipCalling");
                return true;
            }
            if (TextUtils.equals(str2, "jp.naver.line.android") && iyg.c() && Build.VERSION.SDK_INT > 33 && z) {
                ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted filter by samsung phone Line voipCalling");
                return true;
            }
            if (z && !TextUtils.equals(str2, "com.tencent.mm")) {
                ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted continue: ", str2, " is NOT_WECHAT and received a RING_CALL");
                bundle.putBoolean("data_extra_send_call_no_wechat", true);
                return false;
            }
        }
        if ((i & 32) == 32 && !z) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_NO_CLEAR,pkg=", str2);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
            linkedHashMap.put("notificationFilteredByNoClearPkg", str2);
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
            return true;
        }
        if ("com.sdu.didi.psnger".equalsIgnoreCase(str2) && (i & 256) == 256) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_LOCAL_ONLY,pkg=", str2);
            return true;
        }
        if ((i & 2) == 2) {
            if (z) {
                ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted continue: ", str2, " is WECHAT and received a RING_CALL");
                bundle.putBoolean("data_extra_send_call_wechat", true);
                return false;
            }
            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(1);
            linkedHashMap2.put("notificationFilteredByOngoingPkg", str2);
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap2);
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_ONGOING_EVENT,pkg=", str2);
            return true;
        }
        if (oA_(bundle, str, i, str2) || oz_(bundle, str2)) {
            return true;
        }
        if ((i & 512) == 512 && !bdo.b() && !z) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_GROUP_SUMMARY,pkg=", str2);
            LinkedHashMap<String, String> linkedHashMap3 = new LinkedHashMap<>(1);
            linkedHashMap3.put("notificationFilteredByGroupSummaryPkg", str2);
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap3);
            return true;
        }
        if (!TextUtils.equals(str2, "com.android.server.telecom") || i != 16) {
            return false;
        }
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because PACKAGE_NAME_TELECOM && flags=0x10,pkg=", str2);
        return true;
    }

    private boolean oA_(Bundle bundle, String str, int i, String str2) {
        String ph_ = bdo.ph_(bundle, "data_channel_id", null);
        if ("com.whatsapp".equals(str2) && i == 32768 && !TextUtils.isEmpty(str) && str.toLowerCase(Locale.ROOT).contains("progress") && !TextUtils.isEmpty(ph_) && ph_.toLowerCase(Locale.ROOT).contains("sending_media")) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_ONGOING_EVENT,pkg=", str2);
            return true;
        }
        if (!"com.huawei.photos".equals(str2) || !ph_.toLowerCase(Locale.ROOT).contains("download")) {
            return false;
        }
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str2);
        return true;
    }

    private boolean oz_(Bundle bundle, String str) {
        String ph_ = bdo.ph_(bundle, "data_channel_id", null);
        c(str, ph_);
        if ("com.cdrcbperson.mobilebank".equals(str) && "com.cdrcbperson.mobilebank.download".equals(ph_)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_ONGOING_EVENT,pkg=", str);
            return true;
        }
        if ("com.zeekrlife.mobile".equals(str) && "app_download_channel".equals(ph_)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_ONGOING_EVENT,pkg=", str);
            return true;
        }
        String ph_2 = bdo.ph_(bundle, "data_groupKey", null);
        int i = bundle.getInt("data_flags");
        if ("com.sankuai.meituan.takeoutnew".equals(str) && i == 0 && TextUtils.isEmpty(ph_2)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str);
            return true;
        }
        if ("com.sankuai.meituan".equals(str) && ph_.contains("download")) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str);
            return true;
        }
        if ("com.taobao.taobao".equals(str) && ph_.contains("update_channel")) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str);
            return true;
        }
        if ("com.MobileTicket".equals(str) && ph_.toLowerCase(Locale.ROOT).contains("download")) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str);
            return true;
        }
        if ("cn.damai".equals(str) && ph_.toLowerCase(Locale.ROOT).contains("update")) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str);
            return true;
        }
        if ("com.eg.android.AlipayGphone".equals(str) && ph_.toLowerCase(Locale.ROOT).contains("download")) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str);
            return true;
        }
        if (!"com.baidu.searchbox".equals(str) || !ph_.toLowerCase(Locale.ROOT).contains("download")) {
            return false;
        }
        ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted FILTERED because FLAG_DEFAULT_VALUE,pkg=", str);
        return true;
    }

    private void c(String str, String str2) {
        String obj;
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        String lowerCase = str2.toLowerCase(Locale.ROOT);
        if (lowerCase.contains("_")) {
            obj = Arrays.asList(lowerCase.split("_")).toString();
        } else {
            obj = lowerCase.contains(".") ? Arrays.asList(lowerCase.split("\\.")).toString() : lowerCase;
        }
        if (obj != null) {
            if (obj.contains("download") || obj.contains("update")) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
                linkedHashMap.put("notificationFilterByUpgradePkg", str);
                linkedHashMap.put("notificationFilterByUpgradeValue", lowerCase);
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
            }
        }
    }

    private boolean oC_(Bundle bundle, Notification notification, boolean z) {
        if (!SharedPreferenceManager.a("SUPPORT_NOTIFY_VOIP_TYPE", "SUPPORT_NOTIFY_VOIP_TYPE", false)) {
            LogUtil.h("NotificPush", "device is not supportVoipType.");
            return false;
        }
        if (!SystemInfo.a() || !EmuiBuild.d || !CommonUtil.cl()) {
            LogUtil.h("NotificPush", "this phone is not HuaweiSeaOverlayPhone.");
            return false;
        }
        return bdp.e(bdo.ph_(bundle, MediaBrowserProtocol.DATA_PACKAGE_NAME, null)).isPushVoipCalling(b, this.r, bundle, notification, z);
    }

    private boolean oD_(Bundle bundle, String str, String str2, int i) {
        boolean z = false;
        if (str.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL) && this.ai.contains(str2)) {
            String ph_ = bdo.ph_(bundle, "data_extra_noty_key", null);
            boolean oF_ = bdo.o(str2) ? oF_(bundle, str2) : true;
            if (!bdo.i(str2)) {
                z = oF_;
            } else if ((i & 2) == 2) {
                z = true;
            }
            ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted ", ph_, " isRingCallAndNeedPosted? ", Boolean.valueOf(z));
        }
        return z;
    }

    private boolean oF_(Bundle bundle, String str) {
        bds bdsVar = b;
        if (bds.f338a.containsKey(str)) {
            ReleaseLogUtil.d("Notify_NotificPush", "onNotificationPosted WILL FILTERED because whatsappResendLongVibrate!");
            return false;
        }
        bdsVar.b(str);
        return TextUtils.equals("call_notification_group", bdo.ph_(bundle, "data_groupKey", null));
    }

    private void oO_(StatusBarNotification statusBarNotification, Bundle bundle, String str) {
        if (TextUtils.isEmpty(str) || !this.ae.contains(str)) {
            return;
        }
        Notification.Action[] actionArr = statusBarNotification.getNotification().actions;
        String key = statusBarNotification.getKey();
        if (actionArr == null || actionArr.length == 0) {
            LogUtil.h("NotificPush", "onNotificationPosted quickReply INCAPABLE! ", str, " has no action button in statusBar");
            return;
        }
        for (int i = 0; i < actionArr.length; i++) {
            Notification.Action action = actionArr[i];
            if (action != null) {
                if (action.getRemoteInputs() == null) {
                    ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted quickReply INCAPABLE by No.", Integer.valueOf(i + 1), " button named ", actionArr[i].title, " in ", str);
                } else {
                    ReleaseLogUtil.e("Notify_NotificPush", "onNotificationPosted quickReply SUPPORT by No.", Integer.valueOf(i + 1), " button named ", actionArr[i].title, ",key: ", key);
                    bundle.putString("notification_key", key);
                    return;
                }
            }
        }
    }

    private void p() {
        if (this.w == null) {
            this.w = new b();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.action.ACTION_NOTIFICATION_PUSH");
            intentFilter.addAction("com.huawei.health.action.ACTION_NOTIFICATION_LEVEL");
            BroadcastManagerUtil.bFE_(BaseApplication.getContext(), this.w, intentFilter);
        }
        if (this.g == null) {
            this.g = new a();
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter2.addDataScheme("package");
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.g, intentFilter2, LocalBroadcast.c, null);
        }
    }

    protected static class d {

        /* renamed from: a, reason: collision with root package name */
        private long f1935a = 0;
        private String d = "";
        private String e = "";

        protected d() {
        }

        public long e() {
            return this.f1935a;
        }

        public void d(long j) {
            this.f1935a = j;
        }

        public String d() {
            return this.e;
        }

        public void b(String str) {
            this.e = str;
        }

        public String c() {
            return this.d;
        }

        public void a(String str) {
            this.d = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = this.o.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(str, it.next())) {
                return true;
            }
        }
        return false;
    }

    class b extends BroadcastReceiver {
        private b() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            if (intent == null) {
                LogUtil.h("NotificPush", "deviceReply broadcast get intent is null");
                return;
            }
            if (!"com.huawei.health.action.ACTION_NOTIFICATION_PUSH".equals(intent.getAction()) && !"com.huawei.health.action.ACTION_NOTIFICATION_LEVEL".equals(intent.getAction())) {
                LogUtil.h("NotificPush", "deviceReply broadcast get intent NOT_ACTION_NOTIFICATION_PUSH_WARNING is: ", intent.getAction());
                return;
            }
            String stringExtra = intent.getStringExtra("action_type");
            if (TextUtils.isEmpty(stringExtra)) {
                LogUtil.h("NotificPush", "deviceReply broadcast get type is null.");
                return;
            }
            stringExtra.hashCode();
            switch (stringExtra.hashCode()) {
                case 2640288:
                    if (stringExtra.equals("VOIP")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 484216263:
                    if (stringExtra.equals("key_replay")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 742327692:
                    if (stringExtra.equals("CONTENT_LENGTH")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1183439632:
                    if (stringExtra.equals("NOTIFICATION_LEVEL")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                oX_(intent);
                return;
            }
            if (c == 1) {
                oW_(intent);
                return;
            }
            if (c == 2) {
                NotificationPushListener.this.k = intent.getIntExtra("CONTENT_LENGTH_SIZE", GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
                LogUtil.h("NotificPush", "deviceReply broadcast mContentLength: ", Integer.valueOf(NotificationPushListener.this.k));
            } else {
                if (c == 3) {
                    LogUtil.a("NotificPush", "onReceive notificationLevel register");
                    NotificationPushListener notificationPushListener = NotificationPushListener.this;
                    notificationPushListener.e((List<String>) notificationPushListener.e());
                    return;
                }
                LogUtil.h("NotificPush", "deviceReply broadcast get type is other.");
            }
        }

        private void oX_(Intent intent) {
            String stringExtra = intent.getStringExtra("key_replay_destination");
            String stringExtra2 = intent.getStringExtra("key_replay_content");
            if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
                LogUtil.h("NotificPush", "sendVoipAction broadcast EMPTY_WARNING content: ", stringExtra2, ",key: ", stringExtra);
                return;
            }
            LogUtil.a("NotificPush", "sendVoipAction DaemonService get content: ", stringExtra2, ",key: ", stringExtra);
            StatusBarNotification oV_ = oV_(stringExtra);
            if (oV_ != null) {
                oY_(oV_, stringExtra2);
            }
        }

        private StatusBarNotification oV_(String str) {
            try {
                for (StatusBarNotification statusBarNotification : NotificationPushListener.this.getActiveNotifications()) {
                    if (statusBarNotification != null && str.equals(statusBarNotification.getKey())) {
                        return statusBarNotification;
                    }
                }
            } catch (SecurityException e) {
                LogUtil.b("NotificPush", "packageMessage occur exception:", ExceptionUtils.d(e));
                sqo.ab("getStatusBarNotification occur exception");
            }
            return null;
        }

        public void oY_(StatusBarNotification statusBarNotification, String str) {
            LogUtil.h("NotificPush", "deviceVOIPNotification");
            if (statusBarNotification == null) {
                LogUtil.h("NotificPush", "deviceReply StatusBarNotification is null");
                return;
            }
            Notification.Action[] actionArr = statusBarNotification.getNotification().actions;
            String packageName = statusBarNotification.getPackageName();
            if (actionArr == null || actionArr.length == 0) {
                LogUtil.h("NotificPush", "VOIP " + packageName, " has no action button so that deviceReply INCAPABLE!");
                return;
            }
            for (Notification.Action action : actionArr) {
                if (action != null) {
                    PendingIntent pendingIntent = action.actionIntent;
                    try {
                        Intent intent = (Intent) PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(pendingIntent, new Object[0]);
                        if (intent.getAction() != null && intent.getAction().equals(str)) {
                            Intent intent2 = new Intent();
                            intent2.setAction(str);
                            intent2.setPackage(packageName);
                            pendingIntent.send(BaseApplication.getContext(), 0, intent);
                        }
                    } catch (Exception e) {
                        LogUtil.b("NotificPush", "packageMessage occur exception:", ExceptionUtils.d(e));
                        sqo.ab("deviceVOIPNotification occur exception");
                    }
                }
            }
        }

        private void oW_(Intent intent) {
            String stringExtra = intent.getStringExtra("key_replay_destination");
            String stringExtra2 = intent.getStringExtra("key_replay_content");
            if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
                LogUtil.h("NotificPush", "deviceReply broadcast EMPTY_WARNING content: ", stringExtra2, ",key: ", stringExtra);
                return;
            }
            LogUtil.a("NotificPush", "deviceReply DaemonService get content: ", bky.e(stringExtra2), ",key: ", stringExtra);
            StatusBarNotification oV_ = oV_(stringExtra);
            if (oV_ != null) {
                if (NotificationPushListener.this.a(oV_.getPackageName())) {
                    NotificationPushListener.this.a(stringExtra, stringExtra2);
                }
                jrg.bIY_(oV_, stringExtra2);
                ReleaseLogUtil.e("Notify_NotificPush", "deviceReply broadcast succeed in replying");
                return;
            }
            ReleaseLogUtil.e("Notify_NotificPush", "deviceReply broadcast get StatusBarNotification is null by key: ", stringExtra);
            Intent intent2 = new Intent(NotificationPushListener.this, (Class<?>) HandleIntentService.class);
            intent2.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
            Bundle bundle = new Bundle();
            bundle.putString("notificationSwitchChangeType", "notificationReplayChangeType");
            intent2.putExtras(bundle);
            try {
                NotificationPushListener.this.startService(intent2);
                ReleaseLogUtil.e("Notify_NotificPush", "deviceReply broadcast build one intent successfully");
            } catch (IllegalStateException | SecurityException e) {
                ReleaseLogUtil.c("Notify_NotificPush", "deviceReply broadcast startService ERROR: ", ExceptionUtils.d(e));
                sqo.ab("quickReplyProcedure occur exception");
            }
        }
    }

    class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            if (intent == null) {
                LogUtil.h("NotificPush", "mAppInstalledReceiver intent is null");
                return;
            }
            if (!"android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                LogUtil.h("NotificPush", "mAppInstalledReceiver is:", intent.getAction());
                return;
            }
            String dataString = intent.getDataString();
            LogUtil.a("NotificPush", "intentInfo is :", dataString);
            if (TextUtils.isEmpty(dataString)) {
                LogUtil.h("NotificPush", "appInstalled intentInfo is empty.");
                return;
            }
            String[] split = dataString.split(":");
            if (split.length > 1) {
                str = split[1];
            } else {
                str = split[0];
            }
            LogUtil.a("NotificPush", "mAppInstalledReceiver packageName:", str);
            if (NotificationContentProviderUtil.j()) {
                boolean a2 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_TYPE", "SUPPORT_NOTIFY_LIVE_TYPE", false);
                boolean a3 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", false);
                String e = SharedPreferenceManager.e("NOTIFY_LIVE_LEVEL_VALUE", "NOTIFY_LIVE_LEVEL_VALUE", "levelDefault");
                LogUtil.a("NotificPush", "getLiveNotificationCloudList hasNotifyLiveCapability is ", Boolean.valueOf(a2), ",  hasNotifyLiveLevelCapability is ", Boolean.valueOf(a3), ",  notifyLiveLevel is " + e);
                List arrayList = new ArrayList();
                if (a3) {
                    arrayList = NotificationContentProviderUtil.c(e);
                } else if (a2) {
                    arrayList = NotificationContentProviderUtil.b();
                }
                if (arrayList.contains(str)) {
                    NotificationPushListener notificationPushListener = NotificationPushListener.this;
                    notificationPushListener.e((List<String>) notificationPushListener.e());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        this.n.put(str + str2, Long.valueOf(currentTimeMillis));
        a(this.t, this.n, 500L, "initDeviceReplyResendFilter");
        LogUtil.a("NotificPush", "deviceReply initDeviceReplyResendFilter success,entry.key: ", str + str2, " deviceReplyTime: ", Long.valueOf(currentTimeMillis));
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientChange(boolean z) {
        IMusicChangedCallback iMusicChangedCallback;
        LogUtil.a("NotificPush", "onClientChange result: ", Boolean.valueOf(z));
        this.c = z;
        if (!z || (iMusicChangedCallback = this.ag) == null) {
            return;
        }
        try {
            iMusicChangedCallback.onMusicChanged();
        } catch (RemoteException unused) {
            LogUtil.a("NotificPush", "onClientChange error");
        }
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientPlaybackStateUpdate(int i) {
        LogUtil.a("NotificPush", "onClientPlaybackStateUpdate result: ", Integer.valueOf(i));
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientPlaybackStateUpdate(int i, long j, long j2, float f) {
        LogUtil.a("NotificPush", "onClientPlaybackStateUpdate state: ", Integer.valueOf(i));
        if (this.aj != i) {
            this.aj = i;
            IMusicChangedCallback iMusicChangedCallback = this.ag;
            if (iMusicChangedCallback != null) {
                try {
                    iMusicChangedCallback.onMusicChanged();
                } catch (RemoteException unused) {
                    LogUtil.a("NotificPush", "onClientMetadataUpdate error");
                }
            }
        }
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientTransportControlUpdate(int i) {
        LogUtil.a("NotificPush", "onClientTransportControlUpdate ", Integer.valueOf(i));
    }

    @Override // android.media.RemoteController.OnClientUpdateListener
    public void onClientMetadataUpdate(RemoteController.MetadataEditor metadataEditor) {
        LogUtil.a("NotificPush", "onClientMetadataUpdate");
        if (TextUtils.equals(this.ah, metadataEditor.getString(7, ""))) {
            return;
        }
        LogUtil.a("NotificPush", "onClientMetadataUpdate has name");
        oT_(metadataEditor);
        IMusicChangedCallback iMusicChangedCallback = this.ag;
        if (iMusicChangedCallback != null) {
            try {
                iMusicChangedCallback.onMusicChanged();
            } catch (RemoteException unused) {
                LogUtil.a("NotificPush", "onClientMetadataUpdate error");
            }
        }
    }

    public void oT_(RemoteController.MetadataEditor metadataEditor) {
        String string = metadataEditor.getString(7, "");
        this.ah = string;
        LogUtil.a("NotificPush", "musicTitle is ", string);
        String string2 = metadataEditor.getString(2, "");
        this.ak = string2;
        LogUtil.a("NotificPush", "singerName is ", string2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogUtil.a("NotificPush", "enter registerRemoteController");
        this.an = new RemoteController(this, this);
        LogUtil.a("NotificPush", "registerRemoteController result: ", Boolean.valueOf(((AudioManager) getSystemService(PresenterUtils.AUDIO)).registerRemoteController(this.an)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        LogUtil.a("NotificPush", "enter unregistRemoteController");
        if (this.an != null) {
            ((AudioManager) getSystemService(PresenterUtils.AUDIO)).unregisterRemoteController(this.an);
        }
        this.ag = null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.a("NotificPush", "onStartCommand");
        super.onStartCommand(intent, i, i2);
        LogUtil.a("NotificPush", "onStartCommand notification");
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setAutoCancel(true);
        xf_.setPriority(0);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setGroup("NotificPush");
        startForeground(20211231, xf_.build());
        return 2;
    }
}

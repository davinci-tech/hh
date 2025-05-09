package defpackage;

import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class jyx extends HwBaseManager implements ParserInterface {
    private static volatile jyx g;
    private ConcurrentHashMap<Integer, jyy> f;
    private CopyOnWriteArrayList<jys> h;
    private Map<Integer, Map<Integer, byte[]>> j;
    private int k;
    private Context l;
    private FileChannel m;
    private jys n;
    private BroadcastReceiver o;
    private c p;
    private Queue<DeviceCommand> q;
    private FileInputStream r;
    private final HashMap<Integer, LinkedList<jys>> s;
    private HandlerThread t;
    private ConcurrentHashMap<Integer, jys> v;
    private IAppTransferFileResultAIDLCallback w;
    private ParcelFileDescriptor x;
    private cwl y;

    /* renamed from: a, reason: collision with root package name */
    private static final String f14206a = BaseApplication.getContext().getFilesDir() + "/fileShare/";
    private static final String c = "watchfacePhoto" + File.separator + WatchFaceProvider.BACKGROUND_LABEL;
    private static final String i = "watchfaceVideo" + File.separator + WatchFaceProvider.BACKGROUND_LABEL;
    private static final Object d = new Object();
    private static final Object e = new Object();
    private static final byte[] b = new byte[0];

    private jyx(Context context) {
        super(context);
        this.s = new HashMap<>(16);
        this.k = -1;
        this.r = null;
        this.m = null;
        this.j = new LinkedHashMap(4);
        this.v = new ConcurrentHashMap<>(20);
        this.h = new CopyOnWriteArrayList<>();
        this.q = new LinkedList();
        this.y = new cwl();
        this.f = new ConcurrentHashMap<>(20);
        this.w = null;
        this.o = new BroadcastReceiver() { // from class: jyx.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                DeviceInfo deviceInfo;
                if (context2 == null || intent == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                    return;
                }
                if ((deviceInfo.getRelationship() == null || !"followed_relationship".equals(deviceInfo.getRelationship())) && deviceInfo.getDeviceConnectState() == 3) {
                    jyx.this.j();
                    jyx.this.g();
                }
            }
        };
        this.l = context;
        HandlerThread handlerThread = new HandlerThread("HwCommonFileMgr");
        this.t = handlerThread;
        handlerThread.start();
        this.p = new c(this.t.getLooper());
        BroadcastManagerUtil.bFC_(this.l, this.o, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public void d() {
        if (jsn.c()) {
            return;
        }
        this.l.unregisterReceiver(this.o);
        c();
        e();
    }

    private static void c() {
        synchronized (e) {
            g = null;
        }
    }

    private void e() {
        HandlerThread handlerThread = this.t;
        if (handlerThread != null) {
            handlerThread.quit();
            this.t = null;
        }
    }

    public static jyx a() {
        jyx jyxVar;
        synchronized (e) {
            if (g == null) {
                g = new jyx(BaseApplication.getContext());
            }
            jyxVar = g;
        }
        return jyxVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        i();
        synchronized (b) {
            jys jysVar = this.n;
            if (jysVar != null) {
                if (jysVar.ah() != null) {
                    try {
                        this.n.ah().onUpgradeFailed(141001, "");
                    } catch (RemoteException unused) {
                        LogUtil.b("HwCommonFileMgr", "mCurrentCommonFileInfo appTransferFilCallback() RemoteException");
                    }
                }
                e(this.n);
                this.n = null;
            }
        }
        this.s.clear();
    }

    private void i() {
        for (Map.Entry<Integer, jys> entry : this.v.entrySet()) {
            if (entry.getValue().ah() != null) {
                try {
                    entry.getValue().ah().onUpgradeFailed(141001, "");
                } catch (RemoteException unused) {
                    LogUtil.b("HwCommonFileMgr", "disconnected appTransferFilCallback() RemoteException");
                }
            }
            e(entry.getValue());
            synchronized (b) {
                if (entry.getValue().equals(this.n)) {
                    this.n = null;
                }
            }
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 40;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            return;
        }
        LogUtil.a("HwCommonFileMgr", "getResult(), message :", cvx.d(bArr));
        byte b2 = bArr[1];
        switch (b2) {
            case 1:
                a(bArr, deviceInfo);
                break;
            case 2:
                e(bArr);
                break;
            case 3:
                c(bArr, deviceInfo);
                break;
            case 4:
                e(bArr, deviceInfo);
                break;
            case 5:
                c(bArr);
                break;
            case 6:
            default:
                LogUtil.h("HwCommonFileMgr", "getResult()  default switch ", Byte.valueOf(b2));
                break;
            case 7:
                d(bArr);
                break;
            case 8:
                b(bArr);
                break;
            case 9:
                a(bArr);
                break;
        }
    }

    public void b(final jys jysVar, final UniteDevice uniteDevice) {
        if (jysVar == null || uniteDevice == null || jysVar.j() == null) {
            return;
        }
        if (jsn.c() || jsn.a(uniteDevice.getIdentify())) {
            snt sntVar = new snt();
            sntVar.i(jysVar.i());
            sntVar.f(jysVar.f());
            sntVar.j(jysVar.ab());
            sntVar.c(jysVar.y());
            sntVar.a(jysVar.n());
            sntVar.a(jysVar.s());
            sntVar.e(jysVar.l());
            sntVar.d(jysVar.u());
            sntVar.b(jysVar.b());
            sntVar.ejE_(jysVar.bLe_());
            final IOTAResultAIDLCallback j = jysVar.j();
            snq.c().p2pSendForWearEngine(this.l, uniteDevice, sntVar, new SendCallback() { // from class: jyx.5
                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i2) {
                    jyx.this.c(i2, j);
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j2) {
                    try {
                        j.onFileTransferState((int) j2);
                    } catch (RemoteException unused) {
                        LogUtil.b("HwCommonFileMgr", "callback onFileTransferState ");
                        jyx.this.a(jysVar, (IBaseCallback) null, uniteDevice);
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str) {
                    LogUtil.a("HwCommonFileMgr", "transferFileByQueue onFileTransferReport transferWay: ", str);
                    try {
                        j.onFileTransferReport(str);
                    } catch (RemoteException unused) {
                        LogUtil.b("HwCommonFileMgr", "onFileTransferReport remoteException");
                    }
                }
            });
            LogUtil.a("HwCommonFileMgr", "use unite_HwCommonFileMgr transferFileByQueue");
            return;
        }
        d(jysVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, IOTAResultAIDLCallback iOTAResultAIDLCallback) {
        if (i2 == 207) {
            try {
                iOTAResultAIDLCallback.onFileRespond(1);
            } catch (RemoteException unused) {
                LogUtil.b("HwCommonFileMgr", "callback onSendResult");
            }
        } else {
            try {
                iOTAResultAIDLCallback.onFileRespond(i2);
            } catch (RemoteException unused2) {
                LogUtil.b("HwCommonFileMgr", "callback onFileRespond");
            }
        }
    }

    private void d(jys jysVar) {
        int i2;
        try {
            if (jysVar.bLe_() != null) {
                i2 = bLh_(jysVar.bLe_());
            } else if (!TextUtils.isEmpty(jysVar.l())) {
                i2 = d(new File(jysVar.l()), -1L);
            } else {
                LogUtil.h("HwCommonFileMgr", "transferFileByQueue getFileSize default");
                i2 = 0;
            }
            if (i2 == 0) {
                jysVar.j().onUpgradeFailed(20000, "");
                return;
            }
            if (i2 == -1) {
                jysVar.j().onUpgradeFailed(20005, "");
                return;
            }
            jysVar.g(i2);
            synchronized (this.s) {
                b(jysVar);
            }
        } catch (RemoteException unused) {
            LogUtil.b("HwCommonFileMgr", "transferFileByQueue RemoteException");
        }
    }

    public void c(jqj jqjVar, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        if (iAppTransferFileResultAIDLCallback == null) {
            return;
        }
        if (jqjVar == null) {
            try {
                iAppTransferFileResultAIDLCallback.onUpgradeFailed(20000, "");
                return;
            } catch (RemoteException unused) {
                LogUtil.b("HwCommonFileMgr", "startTransfer RemoteException");
                return;
            }
        }
        if (jsn.c() || jsn.a(jqjVar.d())) {
            d(jqjVar, iAppTransferFileResultAIDLCallback);
            return;
        }
        try {
            TransferFileReqType o = jqjVar.o();
            List<Integer> a2 = jqjVar.a();
            if (o == TransferFileReqType.DEVICE_REGISTRATION && a2.size() > 0) {
                b(iAppTransferFileResultAIDLCallback, a2);
            } else if (o == TransferFileReqType.APP_STOP) {
                e(jqjVar, iAppTransferFileResultAIDLCallback);
            } else if (o == TransferFileReqType.APP_DELIVERY) {
                if (!TextUtils.isEmpty(jqjVar.h())) {
                    int b2 = b(jqjVar, iAppTransferFileResultAIDLCallback);
                    if (b2 != -1 && b2 != 0) {
                        e(jqjVar, iAppTransferFileResultAIDLCallback, b2);
                    }
                }
            } else {
                LogUtil.a("HwCommonFileMgr", "startToDeviceTransFile error");
            }
        } catch (RemoteException unused2) {
            LogUtil.b("HwCommonFileMgr", "startTransfer RemoteException");
        }
    }

    private void e(jqj jqjVar, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, int i2) {
        jys jysVar = new jys();
        jysVar.e(jqjVar.j());
        jysVar.a(jqjVar.h());
        jysVar.h(jqjVar.n());
        jysVar.g(i2);
        jysVar.e(iAppTransferFileResultAIDLCallback);
        if (jqjVar.m() > 0) {
            jysVar.c(jqjVar.m());
        }
        if (!c(jysVar)) {
            this.h.add(jysVar);
        }
        b();
        synchronized (b) {
            this.n = jysVar;
        }
        b(jqjVar.j(), i2, jqjVar.n(), jqjVar.k());
    }

    private int b(jqj jqjVar, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) throws RemoteException {
        String str;
        int d2;
        try {
            str = new File(jqjVar.h()).getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("HwCommonFileMgr", "startTransfer IOException");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        File file = new File(str);
        if (!file.exists()) {
            iAppTransferFileResultAIDLCallback.onUpgradeFailed(20000, "");
            return 0;
        }
        if (jqjVar.m() > 0) {
            d2 = d(file, jqjVar.m());
        } else {
            d2 = d(file, -1L);
        }
        if (d2 == 0) {
            iAppTransferFileResultAIDLCallback.onUpgradeFailed(20000, "");
            return d2;
        }
        if (d2 == -1) {
            iAppTransferFileResultAIDLCallback.onUpgradeFailed(20005, "");
        }
        return d2;
    }

    private void d(jqj jqjVar, final IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        sol e2 = e(jqjVar);
        if (TextUtils.isEmpty(jqjVar.d())) {
            e2.d(sph.e(jyv.a()));
        }
        IResultAIDLCallback.Stub stub = new IResultAIDLCallback.Stub() { // from class: jyx.3
            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferState(int i2, String str) throws RemoteException {
                try {
                    LogUtil.a("HwCommonFileMgr", "onFileTransferState percentage:", Integer.valueOf(i2), ",des", str);
                    iAppTransferFileResultAIDLCallback.onFileTransferState(i2, str);
                } catch (Exception e3) {
                    LogUtil.b("HwCommonFileMgr", "transferState ", this, "callback : ", iAppTransferFileResultAIDLCallback, ExceptionUtils.d(e3));
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onTransferFailed(int i2, String str) throws RemoteException {
                try {
                    LogUtil.a("HwCommonFileMgr", " onUpgradeFailed errorCode:", Integer.valueOf(i2), ",des", str);
                    iAppTransferFileResultAIDLCallback.onUpgradeFailed(i2, str);
                } catch (Exception e3) {
                    LogUtil.b("HwCommonFileMgr", "onUpgradeFailed Exception ", ExceptionUtils.d(e3));
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileRespond(int i2, String str) throws RemoteException {
                try {
                    LogUtil.a("HwCommonFileMgr", "checkResult:", Integer.valueOf(i2), ",des", str);
                    iAppTransferFileResultAIDLCallback.onFileRespond(i2, str);
                } catch (Exception e3) {
                    LogUtil.b("HwCommonFileMgr", "onFileRespond Exception : ", ExceptionUtils.d(e3));
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("HwCommonFileMgr", "transferFileByQueue onFileTransferReport transferWay: ", str);
            }
        };
        snq.c().startTransferFileToWear(e2, stub);
        LogUtil.a("HwCommonFileMgr", "use unite_HWcommonfileMgr startTransferFile : ", stub);
    }

    private sol e(jqj jqjVar) {
        sol solVar = new sol();
        solVar.a(jqjVar.j());
        solVar.e(jqjVar.h());
        solVar.m(jqjVar.n());
        solVar.c(jqjVar.m());
        solVar.j(jqjVar.k());
        solVar.a(jqjVar.o());
        solVar.a(jqjVar.a());
        solVar.ejU_(jqjVar.bIQ_());
        solVar.i(jqjVar.g());
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(jqjVar.d());
        solVar.d(uniteDevice);
        return solVar;
    }

    private sol c(jys jysVar, UniteDevice uniteDevice) {
        sol solVar = new sol();
        solVar.ejU_(jysVar.bLe_());
        solVar.a(jysVar.n());
        solVar.m(jysVar.s());
        solVar.c(jysVar.b());
        solVar.e(jysVar.l());
        solVar.g(jysVar.u());
        solVar.h(jysVar.ab());
        solVar.f(jysVar.y());
        solVar.d(jysVar.i());
        solVar.b(jysVar.f());
        solVar.e(true);
        if (uniteDevice == null) {
            solVar.d(sph.e(jyv.a()));
        } else {
            solVar.d(uniteDevice);
        }
        return solVar;
    }

    private void b(jys jysVar) {
        int s = jysVar.s();
        long currentTimeMillis = System.currentTimeMillis();
        if (this.s.get(Integer.valueOf(s)) == null) {
            this.s.put(Integer.valueOf(s), new LinkedList<>());
        }
        LinkedList<jys> linkedList = this.s.get(Integer.valueOf(s));
        if (linkedList != null && linkedList.isEmpty()) {
            jysVar.a(currentTimeMillis);
            jysVar.d(currentTimeMillis);
            linkedList.add(jysVar);
            i(jysVar);
            return;
        }
        if (linkedList == null) {
            LogUtil.h("HwCommonFileMgr", "putCommonFileInfo fileBeanList is null");
            return;
        }
        LogUtil.a("HwCommonFileMgr", "putCommonFileInfo fileBeanList size :", Integer.valueOf(linkedList.size()));
        Iterator<jys> it = linkedList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            jys next = it.next();
            if ((next.ad() != 0 && currentTimeMillis - next.ad() > 86400000) || (next.ac() != 0 && currentTimeMillis - next.ac() > 3600000)) {
                LogUtil.a("HwCommonFileMgr", "putCommonFileInfo check task is not effective");
                it.remove();
            } else if (next.equals(jysVar)) {
                z = true;
            }
        }
        if (z) {
            return;
        }
        LogUtil.h("HwCommonFileMgr", "putCommonFileInfo task is not exist");
        jysVar.a(currentTimeMillis);
        linkedList.add(jysVar);
    }

    private void d(int i2) {
        LogUtil.a("HwCommonFileMgr", "removeFirstAndSendNext");
        synchronized (this.s) {
            LinkedList<jys> linkedList = this.s.get(Integer.valueOf(i2));
            if (linkedList != null) {
                LogUtil.a("HwCommonFileMgr", "removeFirstAndSendNext fileBeanList size :", Integer.valueOf(linkedList.size()));
                linkedList.poll();
                jys peek = linkedList.peek();
                if (peek != null) {
                    peek.d(System.currentTimeMillis());
                    i(peek);
                }
            }
        }
    }

    private void i(jys jysVar) {
        if (!c(jysVar)) {
            this.h.add(jysVar);
        }
        b();
        synchronized (b) {
            this.n = jysVar;
        }
        if (jysVar.s() == 7) {
            jsz.b(BaseApplication.getContext()).sendDeviceData(jyv.b(jysVar));
        } else {
            b(jysVar.n(), jysVar.q(), jysVar.s(), jysVar.ab());
        }
    }

    public void b(jys jysVar, IBaseCallback iBaseCallback, UniteDevice uniteDevice) {
        if (jysVar == null || uniteDevice == null || iBaseCallback == null) {
            LogUtil.h("HwCommonFileMgr", "stopTransferByQueue param empty");
            return;
        }
        if (jsn.c() || jsn.a(uniteDevice.getIdentify())) {
            LogUtil.a("HwCommonFileMgr", "callStopTransferQueue entry");
            a(jysVar, iBaseCallback, uniteDevice);
            return;
        }
        synchronized (this.s) {
            LinkedList<jys> linkedList = this.s.get(Integer.valueOf(jysVar.s()));
            if (linkedList != null && linkedList.peek() != null) {
                jys peek = linkedList.peek();
                if (peek != null && peek.equals(jysVar)) {
                    LogUtil.a("HwCommonFileMgr", "stopTransfer firstTask");
                    e(peek.n(), peek.s(), iBaseCallback);
                    return;
                }
                try {
                    Iterator<jys> it = linkedList.iterator();
                    while (it.hasNext()) {
                        if (it.next().equals(jysVar)) {
                            it.remove();
                            iBaseCallback.onResponse(20003, "");
                            return;
                        }
                    }
                    iBaseCallback.onResponse(20004, "");
                } catch (RemoteException unused) {
                    LogUtil.b("HwCommonFileMgr", "stopTransferByQueue RemoteException");
                }
                return;
            }
            LogUtil.h("HwCommonFileMgr", "stopTransfer error");
        }
    }

    public void a(jys jysVar, final IBaseCallback iBaseCallback, UniteDevice uniteDevice) {
        snq.c().stopTransferFileToWear(c(jysVar, uniteDevice), new ITransferFileCallback.Stub() { // from class: jyx.4
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i2, String str) throws RemoteException {
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i2, String str) throws RemoteException {
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i2, String str, String str2) throws RemoteException {
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i2, String str) throws RemoteException {
                IBaseCallback iBaseCallback2 = iBaseCallback;
                if (iBaseCallback2 != null) {
                    iBaseCallback2.onResponse(i2, str);
                }
            }
        });
        LogUtil.a("HwCommonFileMgr", "use unite_HwCommonFileMgr stopTransferByQueue");
    }

    public void e(String str, int i2, final IBaseCallback iBaseCallback) {
        if (jsn.c()) {
            sol solVar = new sol();
            solVar.a(str);
            solVar.m(i2);
            solVar.e(false);
            solVar.d(sph.e(jyv.a()));
            snq.c().stopTransferFileToWear(solVar, new ITransferFileCallback.Stub() { // from class: jyx.2
                @Override // com.huawei.unitedevice.callback.ITransferFileCallback
                public void onFailure(int i3, String str2) throws RemoteException {
                }

                @Override // com.huawei.unitedevice.callback.ITransferFileCallback
                public void onProgress(int i3, String str2) throws RemoteException {
                }

                @Override // com.huawei.unitedevice.callback.ITransferFileCallback
                public void onSuccess(int i3, String str2, String str3) throws RemoteException {
                }

                @Override // com.huawei.unitedevice.callback.ITransferFileCallback
                public void onResponse(int i3, String str2) throws RemoteException {
                    iBaseCallback.onResponse(i3, str2);
                }
            });
            return;
        }
        for (Map.Entry<Integer, jys> entry : this.v.entrySet()) {
            if (TextUtils.equals(entry.getValue().n(), str) && entry.getValue().s() == i2) {
                LogUtil.a("HwCommonFileMgr", "stopTransferFile fileId :", entry.getKey());
                entry.getValue().c(iBaseCallback);
                b(entry.getKey().intValue());
            }
        }
    }

    private void e(jqj jqjVar, final IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        e(jqjVar.j(), jqjVar.n(), new IBaseCallback.Stub() { // from class: jyx.6
            @Override // com.huawei.hwservicesmgr.IBaseCallback
            public void onResponse(int i2, String str) throws RemoteException {
                LogUtil.a("HwCommonFileMgr", "stopTransmit, errorCode: ", Integer.valueOf(i2), ", reason: ", str);
                IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback2 = iAppTransferFileResultAIDLCallback;
                if (iAppTransferFileResultAIDLCallback2 != null) {
                    iAppTransferFileResultAIDLCallback2.onFileRespond(i2, str);
                    LogUtil.a("HwCommonFileMgr", "stopTransmit onResponse");
                }
            }
        });
    }

    public void b(IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, List<Integer> list) {
        LogUtil.a("HwCommonFileMgr", "registToDevicesCallback entry");
        this.w = iAppTransferFileResultAIDLCallback;
        LogUtil.a("HwCommonFileMgr", "registToDevicesCallback success ! ");
    }

    private void b() {
        synchronized (this) {
            this.j.clear();
            this.k = -1;
            IoUtils.e(this.m);
            IoUtils.e(this.r);
            IoUtils.e(this.x);
            this.m = null;
            this.r = null;
        }
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo) {
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwCommonFileMgr", "handleDeviceRequest data is error");
            return;
        }
        try {
            List<cwd> e2 = this.y.a(d2.substring(4)).e();
            if (e2 != null && e2.size() > 0) {
                String str = "";
                int i2 = 3;
                int i3 = -1;
                for (cwd cwdVar : e2) {
                    int w = CommonUtil.w(cwdVar.e());
                    String c2 = cwdVar.c();
                    if (w == 1) {
                        str = cvx.e(c2);
                        LogUtil.a("HwCommonFileMgr", "handleDeviceRequest file_name :", str);
                    } else if (w == 2) {
                        i2 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleDeviceRequest file_type:", Integer.valueOf(i2));
                    } else if (w != 5) {
                        LogUtil.a("HwCommonFileMgr", "handleDeviceRequest default type:", Integer.valueOf(w));
                    } else {
                        i3 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleDeviceRequest resource_Type:", Integer.valueOf(i3));
                    }
                }
                b(str, i2, i3, deviceInfo);
                return;
            }
            LogUtil.h("HwCommonFileMgr", "handleDeviceRequest tlvs error");
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleDeviceRequest error");
        }
    }

    private void b(String str, int i2, int i3, DeviceInfo deviceInfo) {
        if (b(i2, str)) {
            a(str, i2, i3, 100000, deviceInfo);
            if (i2 == 3 || i2 == 10 || i2 == 11) {
                e(str, i2, i3);
            }
            if (i2 == 4 || i2 == 5) {
                d(str, i2);
                return;
            }
            return;
        }
        a(str, i2, i3, 140006, deviceInfo);
    }

    private void d(String str, int i2) {
        LogUtil.a("HwCommonFileMgr", "deviceStartPayFileTransfer fileName:", str, ", fileType:", Integer.valueOf(i2));
        if (str == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String str2 = f14206a;
        sb.append(str2);
        sb.append(str);
        String sb2 = sb.toString();
        if (i2 == 5 && str.endsWith(".png")) {
            sb2 = str2 + (str.substring(0, str.lastIndexOf(".")) + WatchFaceConstant.BIN_SUFFIX);
            jrt.b().c(str2 + str, sb2);
        }
        jqj jqjVar = new jqj();
        jqjVar.f(sb2);
        jqjVar.a(str);
        jqjVar.d(i2);
        jqjVar.c(TransferFileReqType.APP_DELIVERY);
        c(jqjVar, new IAppTransferFileResultAIDLCallback.Stub() { // from class: jyx.10
            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileTransferState(int i3, String str3) {
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onUpgradeFailed(int i3, String str3) {
                LogUtil.b("HwCommonFileMgr", "deviceStartPayFileTransfer onUpgradeFailed");
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileRespond(int i3, String str3) {
                LogUtil.b("HwCommonFileMgr", "deviceStartPayFileTransfer onFileRespond");
            }
        });
    }

    private void e(String str, int i2, int i3) {
        try {
            jqj jqjVar = new jqj();
            jqjVar.f(c(str, i2, i3));
            jqjVar.a(str);
            jqjVar.d(i2);
            jqjVar.c(TransferFileReqType.APP_DELIVERY);
            if (this.w != null) {
                LogUtil.a("HwCommonFileMgr", "deviceStartToDeviceTransFile entry");
                a(jqjVar);
            } else {
                LogUtil.a("HwCommonFileMgr", "mToDevicesFileCallback is null");
            }
        } catch (IOException unused) {
            LogUtil.b("HwCommonFileMgr", "deviceStartTransfer IOException");
        }
    }

    private void a(jqj jqjVar) {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ContentResource.FILE_NAME, jqjVar.j());
            jSONObject.put("fileType", jqjVar.n());
        } catch (JSONException unused) {
            LogUtil.a("HwCommonFileMgr", "transfer to JSONObject error");
        }
        c(jqjVar, new IAppTransferFileResultAIDLCallback.Stub() { // from class: jyx.8
            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileTransferState(int i2, String str) {
                LogUtil.a("HwCommonFileMgr", "deviceStartTransfer onFileTransferState ");
                if (jyx.this.w != null) {
                    try {
                        jyx.this.w.onFileTransferState(i2, jSONObject.toString());
                        LogUtil.a("HwCommonFileMgr", "onFileTransferState success, percentage: ", Integer.valueOf(i2), ", des: ", jSONObject.toString());
                    } catch (RemoteException unused2) {
                        LogUtil.b("HwCommonFileMgr", "onFileTransferState remoteException");
                    }
                }
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onUpgradeFailed(int i2, String str) {
                LogUtil.a("HwCommonFileMgr", "deviceStartTransfer onUpgradeFailed ");
                if (jyx.this.w != null) {
                    try {
                        jyx.this.w.onUpgradeFailed(i2, jSONObject.toString());
                        LogUtil.a("HwCommonFileMgr", "onUpgradeFailed success, errorCode: ", Integer.valueOf(i2), ", des: ", jSONObject.toString());
                    } catch (RemoteException unused2) {
                        LogUtil.b("HwCommonFileMgr", "onUpgradeFailed remoteException");
                    }
                }
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileRespond(int i2, String str) {
                LogUtil.a("HwCommonFileMgr", "deviceStartTransfer onFileRespond ");
                if (jyx.this.w != null) {
                    try {
                        jyx.this.w.onFileRespond(i2, jSONObject.toString());
                        LogUtil.a("HwCommonFileMgr", "onFileRespond success, checkResult: ", Integer.valueOf(i2), ", des: ", jSONObject.toString());
                    } catch (RemoteException unused2) {
                        LogUtil.b("HwCommonFileMgr", "onFileRespond remoteException");
                    }
                }
            }
        });
    }

    private String c(String str, int i2, int i3) throws IOException {
        String str2;
        if (i2 == 10) {
            return BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + i + File.separator + str;
        }
        if (i2 == 11) {
            str2 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + i + File.separator + str;
        } else {
            str2 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + c + File.separator + str;
        }
        int lastIndexOf = str2.lastIndexOf(".");
        if (i3 != 1) {
            if (lastIndexOf < str2.length()) {
                str2 = str2.substring(0, lastIndexOf) + WatchFaceConstant.BIN_SUFFIX;
            } else {
                str2 = "";
            }
        }
        LogUtil.a("HwCommonFileMgr", "deviceStartTransfer filePath :", str2);
        return str2;
    }

    private boolean b(int i2, String str) {
        if (str == null) {
            LogUtil.h("HwCommonFileMgr", "checkFileExist fileType :", Integer.valueOf(i2), ", fileName = null");
            return false;
        }
        if (i2 != 3) {
            if (i2 == 4 || i2 == 5) {
                return b(str);
            }
            if (i2 != 10 && i2 != 11) {
                LogUtil.h("HwCommonFileMgr", "checkFileExist fileType :", Integer.valueOf(i2), ", file type is not support.");
                return false;
            }
        }
        return b(str, i2);
    }

    private boolean b(String str) {
        if (str == null || str.isEmpty()) {
            LogUtil.h("HwCommonFileMgr", "checkPayFileExist fileName null or empty");
            return false;
        }
        File file = new File(f14206a + str);
        LogUtil.a("HwCommonFileMgr", " checkPayFileExist file exists :", Boolean.valueOf(file.exists()));
        return file.exists();
    }

    private boolean b(String str, int i2) {
        try {
            File file = new File((i2 == 10 || i2 == 11) ? BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + i : BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + c);
            if (!file.isDirectory()) {
                return false;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (i2 == 10 && name.contains(str)) {
                        LogUtil.h("HwCommonFileMgr", "checkFileExist exists WATCH_VIDEO_FILE_TYPE");
                        return true;
                    }
                    if (name.contains(str) && name.endsWith(".png")) {
                        LogUtil.a("HwCommonFileMgr", "checkFileExist exists");
                        return true;
                    }
                }
                return false;
            }
            LogUtil.h("HwCommonFileMgr", "files is null or length = 0");
            return false;
        } catch (IOException unused) {
            LogUtil.b("HwCommonFileMgr", "checkFileExist IOException");
            return false;
        }
    }

    private void a(String str, int i2, int i3, int i4, DeviceInfo deviceInfo) {
        String str2;
        String c2 = cvx.c(str);
        String str3 = cvx.e(1) + cvx.e(c2.length() / 2) + c2;
        String str4 = cvx.e(2) + cvx.e(1) + cvx.e(i2);
        String str5 = cvx.e(127) + cvx.e(4) + cvx.b(i4);
        DeviceCommand deviceCommand = new DeviceCommand();
        if (i3 != -1) {
            str2 = str3 + str4 + (cvx.e(5) + cvx.e(1) + cvx.e(i3)) + str5;
        } else {
            str2 = str3 + str4 + str5;
        }
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwCommonFileMgr", "reportDeviceRequest, deviceCommand :", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void e(byte[] bArr) {
        LogUtil.a("HwCommonFileMgr", "5.40.2 handleAppSend :", cvx.d(bArr));
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        String substring = d2.substring(4);
        jys jysVar = new jys();
        int i2 = 0;
        try {
            List<cwd> e2 = this.y.a(substring).e();
            if (e2 != null && e2.size() > 0) {
                for (cwd cwdVar : e2) {
                    int w = CommonUtil.w(cwdVar.e());
                    String c2 = cwdVar.c();
                    int e3 = jyv.e(w, c2, jysVar);
                    if (e3 != 0) {
                        i2 = e3;
                    }
                    jyv.b(w, c2, jysVar);
                }
            } else {
                LogUtil.h("HwCommonFileMgr", "handleAppSend tlvs error");
            }
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleAppSend TlvException");
        }
        d(jysVar, i2);
    }

    private void d(jys jysVar, int i2) {
        Iterator<jys> it = this.h.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            jys next = it.next();
            LogUtil.a("HwCommonFileMgr", "handleAppSend has cache file :", next.n(), " , type :", Integer.valueOf(next.s()));
            if (jysVar.equals(next)) {
                jysVar.bLf_(next.bLe_());
                jysVar.f(next.u());
                jysVar.a(next.l());
                jysVar.g(next.q());
                jysVar.b(next.j());
                jysVar.e(next.ah());
                jysVar.c(next.ai());
                LogUtil.a("HwCommonFileMgr", "commonFileInfo.name :", jysVar.n());
                break;
            }
        }
        if (i2 != 100000) {
            if (jysVar.ah() != null) {
                try {
                    jysVar.ah().onUpgradeFailed(i2, "");
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("HwCommonFileMgr", "mAppTransferFilCallback() RemoteException");
                    return;
                }
            }
            LogUtil.h("HwCommonFileMgr", "file callBack is null");
            return;
        }
        LogUtil.a("HwCommonFileMgr", "device support transfer file");
        this.v.put(Integer.valueOf(jysVar.h()), jysVar);
        a(jysVar);
        g();
    }

    private void a(jys jysVar) {
        LogUtil.a("HwCommonFileMgr", "dealWatchFaceTaskHang, getFileId :", Integer.valueOf(jysVar.h()));
        try {
            if (jysVar.s() == 1) {
                LogUtil.a("HwCommonFileMgr", "dealWatchFaceTaskHang, watch face type");
                return;
            }
            for (jys jysVar2 : this.v.values()) {
                if (jysVar2.s() == 1) {
                    LogUtil.a("HwCommonFileMgr", "dealWatchFaceTaskHang, watch face task HANG");
                    if (jysVar2.ah() != null) {
                        jysVar2.ah().onFileTransferState(142000, "");
                        return;
                    } else {
                        LogUtil.h("HwCommonFileMgr", "dealWatchFaceTaskHang, fileCallback is null");
                        return;
                    }
                }
            }
        } catch (RemoteException unused) {
            LogUtil.b("HwCommonFileMgr", "dealWatchFaceTaskHang, RemoteException");
        }
    }

    private void b(String str, long j, int i2, String str2) {
        StringBuilder sb = new StringBuilder(jyv.a(str, j, i2));
        if (i2 == 1) {
            String[] split = str.split("_");
            if (split.length != 2) {
                LogUtil.h("HwCommonFileMgr", "sendFileInfo, deviceCommand error");
                return;
            }
            String c2 = cvx.c(split[0]);
            String str3 = cvx.e(5) + cvx.e(c2.length() / 2) + c2;
            String c3 = cvx.c(split[1]);
            String str4 = cvx.e(6) + cvx.e(c3.length() / 2) + c3;
            sb.append(str3);
            sb.append(str4);
            LogUtil.a("HwCommonFileMgr", "sendFileInfo, get WatchInfo success");
        } else if (i2 == 2 && !TextUtils.isEmpty(str2) && !"huaweiOnlineMusic".equals(str2)) {
            String c4 = cvx.c(str2);
            sb.append(cvx.e(7) + cvx.e(c4.length() / 2) + c4);
        } else {
            LogUtil.h("HwCommonFileMgr", "sendFileInfo error");
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setDataLen(cvx.a(sb.toString()).length);
        deviceCommand.setNeedAck(true);
        LogUtil.a("HwCommonFileMgr", "sendFileInfo, deviceCommand :", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwCommonFileMgr", "5.40.3 handleRequestHash :", cvx.d(bArr));
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        try {
            List<cwd> e2 = this.y.a(d2.substring(4)).e();
            if (e2 != null && e2.size() > 0) {
                int i2 = 0;
                int i3 = 0;
                for (cwd cwdVar : e2) {
                    int w = CommonUtil.w(cwdVar.e());
                    String c2 = cwdVar.c();
                    if (w == 1) {
                        i2 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleRequestHash file_id :", Integer.valueOf(CommonUtil.w(c2)));
                    } else if (w != 2) {
                        LogUtil.h("HwCommonFileMgr", "handleRequestHash default type :", Integer.valueOf(w));
                    } else {
                        i3 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleRequestHash check_mode :", Integer.valueOf(CommonUtil.w(c2)));
                    }
                }
                e(i2, i3, deviceInfo);
                return;
            }
            LogUtil.h("HwCommonFileMgr", "handleRequestHash tlvs error");
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleRequestHash TlvException");
        }
    }

    private void c(int i2, String str, DeviceInfo deviceInfo) {
        String str2 = (cvx.e(1) + cvx.e(1) + cvx.e(i2)) + (cvx.e(3) + cvx.e(str.length() / 2) + str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwCommonFileMgr", "sendFileHashResult, deviceCommand :", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void a(int i2, DeviceInfo deviceInfo) {
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i2)) + (cvx.e(127) + cvx.e(4) + cvx.b(20001L));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwCommonFileMgr", "sendFileHashFailed, deviceCommand :", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void e(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwCommonFileMgr", "5.40.4 handleConsult :", cvx.d(bArr));
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        String substring = d2.substring(4);
        jyy jyyVar = new jyy();
        try {
            List<cwd> e2 = this.y.a(substring).e();
            if (e2 != null && e2.size() > 0) {
                for (cwd cwdVar : e2) {
                    c(jyyVar, CommonUtil.w(cwdVar.e()), cwdVar.c());
                }
                LogUtil.a("HwCommonFileMgr", "5.40.4 fileId :", Integer.valueOf(jyyVar.b()));
                this.f.put(Integer.valueOf(jyyVar.b()), jyyVar);
                b(jyyVar.b(), jyyVar.f(), deviceInfo);
                return;
            }
            LogUtil.h("HwCommonFileMgr", "handleConsult tlvs error");
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleConsult TlvException");
        }
    }

    private void c(jyy jyyVar, int i2, String str) {
        switch (i2) {
            case 1:
                jyyVar.d(CommonUtil.w(str));
                LogUtil.a("HwCommonFileMgr", "handleConsult file_id :", Integer.valueOf(CommonUtil.w(str)));
                break;
            case 2:
                jyyVar.d(cvx.e(str));
                LogUtil.a("HwCommonFileMgr", "handleConsult protocol version :", cvx.e(str));
                break;
            case 3:
                jyyVar.b(CommonUtil.w(str));
                LogUtil.a("HwCommonFileMgr", "handleConsult app_wait_time :", Integer.valueOf(CommonUtil.w(str)));
                break;
            case 4:
                jyyVar.a(CommonUtil.w(str) == 1);
                LogUtil.a("HwCommonFileMgr", "handleConsult bitmap_enable :", Integer.valueOf(CommonUtil.w(str)));
                break;
            case 5:
                jyyVar.i(CommonUtil.w(str));
                LogUtil.a("HwCommonFileMgr", "handleConsult unit_size :", Integer.valueOf(CommonUtil.w(str)));
                break;
            case 6:
                jyyVar.e(CommonUtil.w(str));
                LogUtil.a("HwCommonFileMgr", "handleConsult max_apply_data_size :", Integer.valueOf(CommonUtil.w(str)));
                break;
            case 7:
                jyyVar.c(CommonUtil.w(str));
                LogUtil.a("HwCommonFileMgr", "handleConsult interval :", Integer.valueOf(CommonUtil.w(str)));
                break;
            case 8:
                jyyVar.j(CommonUtil.w(str));
                LogUtil.a("HwCommonFileMgr", "handleConsult received_file_size :", Integer.valueOf(CommonUtil.w(str)));
                break;
            case 9:
                jyyVar.d(CommonUtil.w(str) == 1);
                LogUtil.a("HwCommonFileMgr", "handleConsult not need encrypt :", Boolean.valueOf(jyyVar.f()));
                break;
            default:
                LogUtil.h("HwCommonFileMgr", "handleParamTlv default type :", Integer.valueOf(i2));
                break;
        }
    }

    private void b(int i2, boolean z, DeviceInfo deviceInfo) {
        String str;
        LogUtil.a("HwCommonFileMgr", "enter sendConsultAck fileId :", Integer.valueOf(i2), ", isNormal :", Boolean.valueOf(z));
        String str2 = cvx.e(1) + cvx.e(1) + cvx.e(i2);
        String str3 = cvx.e(127) + cvx.e(4) + cvx.b(100000L);
        if (z) {
            str = str3 + str2 + (cvx.e(9) + cvx.e(1) + cvx.e(1));
        } else {
            str = str3 + str2;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(4);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwCommonFileMgr", "sendConsultAck, deviceCommand :", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void c(byte[] bArr) {
        LogUtil.a("HwCommonFileMgr", "5.40.5 handleDeviceRequestData :", cvx.d(bArr));
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        String substring = d2.substring(4);
        List<Integer> arrayList = new ArrayList<>(20);
        try {
            List<cwd> e2 = this.y.a(substring).e();
            if (e2 == null || e2.size() <= 0) {
                return;
            }
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (cwd cwdVar : e2) {
                int w = CommonUtil.w(cwdVar.e());
                String c2 = cwdVar.c();
                if (w == 1) {
                    i2 = CommonUtil.w(c2);
                    LogUtil.a("HwCommonFileMgr", "handleDeviceRequestData file_id :", Integer.valueOf(CommonUtil.w(c2)));
                } else if (w == 2) {
                    i3 = CommonUtil.w(c2);
                    LogUtil.a("HwCommonFileMgr", "handleDeviceRequestData offset :", Integer.valueOf(CommonUtil.w(c2)));
                } else if (w == 3) {
                    i4 = CommonUtil.w(c2);
                    LogUtil.a("HwCommonFileMgr", "handleDeviceRequestData length :", Integer.valueOf(CommonUtil.w(c2)));
                } else if (w != 4) {
                    LogUtil.h("HwCommonFileMgr", "handleDeviceRequestData default :", Integer.valueOf(w));
                } else {
                    arrayList = kid.d(c2);
                    LogUtil.a("HwCommonFileMgr", "handleDeviceRequestData bitmap :", Integer.valueOf(arrayList.size()));
                }
            }
            d(i2, i3, i4, arrayList);
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleRequestHash TlvException");
        }
    }

    private void d(int i2, int i3, int i4, List<Integer> list) {
        if (this.v.containsKey(Integer.valueOf(i2))) {
            g();
            b(i2, i3, i4, list);
        } else {
            LogUtil.h("HwCommonFileMgr", "fileId is not in mTransferingFileList");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        c cVar = this.p;
        if (cVar == null || !cVar.hasMessages(100)) {
            return;
        }
        this.p.removeMessages(100);
    }

    private void d(int i2, int i3, int i4, byte[] bArr, boolean z) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(6);
        String e2 = cvx.e(i2);
        String e3 = cvx.e(i3);
        String b2 = cvx.b(i4);
        String d2 = cvx.d(bArr);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e2);
        sb.append(e3);
        sb.append(b2);
        sb.append(d2);
        byte[] a2 = cvx.a(sb.toString());
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        if (!z) {
            deviceCommand.setNeedEncrypt(false);
        }
        synchronized (d) {
            this.q.add(deviceCommand);
        }
        f();
    }

    private void f() {
        if (this.p == null) {
            HandlerThread handlerThread = new HandlerThread("HwCommonFileMgr");
            this.t = handlerThread;
            handlerThread.start();
            this.p = new c(this.t.getLooper());
            Message obtain = Message.obtain();
            obtain.what = 400;
            this.p.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 400;
        this.p.sendMessage(obtain2);
    }

    private void d(byte[] bArr) {
        LogUtil.a("HwCommonFileMgr", "5.40.7 handleDeviceResultReport :", cvx.d(bArr));
        b();
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        try {
            List<cwd> e2 = this.y.a(d2.substring(4)).e();
            if (e2 == null || e2.size() <= 0) {
                return;
            }
            int i2 = -1;
            int i3 = 0;
            for (cwd cwdVar : e2) {
                int w = CommonUtil.w(cwdVar.e());
                String c2 = cwdVar.c();
                if (w == 1) {
                    i3 = CommonUtil.w(c2);
                    LogUtil.a("HwCommonFileMgr", "handleDeviceResultReport file_id :", Integer.valueOf(CommonUtil.w(c2)));
                } else if (w != 2) {
                    LogUtil.h("HwCommonFileMgr", "handleDeviceResultReport default type :", Integer.valueOf(w));
                } else {
                    i2 = CommonUtil.w(c2);
                    LogUtil.a("HwCommonFileMgr", "handleDeviceResultReport validity_result :", Integer.valueOf(CommonUtil.w(c2)));
                }
            }
            a(i3, i2);
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleDeviceResultReport TlvException");
        }
    }

    private void a(int i2, int i3) {
        if (this.p == null) {
            HandlerThread handlerThread = new HandlerThread("HwCommonFileMgr");
            this.t = handlerThread;
            handlerThread.start();
            this.p = new c(this.t.getLooper());
            Message obtain = Message.obtain();
            obtain.what = 200;
            obtain.arg1 = i2;
            obtain.arg2 = i3;
            this.p.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 200;
        obtain2.arg1 = i2;
        obtain2.arg2 = i3;
        this.p.sendMessage(obtain2);
    }

    private void c(int i2) {
        String str = (cvx.e(127) + cvx.e(4) + cvx.b(100000L)) + (cvx.e(1) + cvx.e(1) + cvx.e(i2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(7);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwCommonFileMgr", "sendResultAck, deviceCommand :", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void b(byte[] bArr) {
        LogUtil.a("HwCommonFileMgr", "5.40.8 handleDeviceStatusReport :", cvx.d(bArr));
        b();
        g();
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        try {
            List<cwd> e2 = this.y.a(d2.substring(4)).e();
            if (e2 != null && e2.size() > 0) {
                int i2 = 0;
                int i3 = 0;
                for (cwd cwdVar : e2) {
                    int w = CommonUtil.w(cwdVar.e());
                    String c2 = cwdVar.c();
                    if (w == 1) {
                        i2 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleDeviceStatusReport file_id :", Integer.valueOf(CommonUtil.w(c2)));
                    } else if (w != 127) {
                        LogUtil.h("HwCommonFileMgr", "handleDeviceStatusReport default tag :", Integer.valueOf(w));
                    } else {
                        i3 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleDeviceStatusReport status :", Integer.valueOf(CommonUtil.w(c2)));
                    }
                }
                b(i2, i3);
                return;
            }
            LogUtil.h("HwCommonFileMgr", "handleDeviceStatusReport tlvs error");
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleDeviceStatusReport TlvException");
        }
    }

    private void b(int i2, int i3) {
        if (this.p == null) {
            HandlerThread handlerThread = new HandlerThread("HwCommonFileMgr");
            this.t = handlerThread;
            handlerThread.start();
            this.p = new c(this.t.getLooper());
            Message obtain = Message.obtain();
            obtain.what = 300;
            obtain.arg1 = i2;
            obtain.arg2 = i3;
            this.p.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 300;
        obtain2.arg1 = i2;
        obtain2.arg2 = i3;
        this.p.sendMessage(obtain2);
    }

    private void b(int i2) {
        b();
        String str = cvx.e(1) + cvx.e(1) + cvx.e(i2);
        jys jysVar = this.v.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(16);
        sb.append(str);
        if (jysVar != null && jysVar.s() == 7) {
            String c2 = cvx.c(jysVar.n());
            sb.append(cvx.e(2) + cvx.e(c2.length() / 2) + c2);
            String c3 = cvx.c(jysVar.ab());
            sb.append(cvx.e(3) + cvx.e(c3.length() / 2) + c3);
            String c4 = cvx.c(jysVar.i());
            sb.append(cvx.e(4) + cvx.e(c4.length() / 2) + c4);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(9);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setDataLen(cvx.a(sb.toString()).length);
        LogUtil.a("HwCommonFileMgr", "sendCancelCommand, deviceCommand :", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void a(byte[] bArr) {
        LogUtil.a("HwCommonFileMgr", "5.40.9 handleCancelReply :", cvx.d(bArr));
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        try {
            List<cwd> e2 = this.y.a(d2.substring(4)).e();
            if (e2 != null && e2.size() > 0) {
                int i2 = 0;
                int i3 = 0;
                for (cwd cwdVar : e2) {
                    int w = CommonUtil.w(cwdVar.e());
                    String c2 = cwdVar.c();
                    if (w == 1) {
                        i2 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleCancelReply CANCEL_FILE_ID :", Integer.valueOf(CommonUtil.w(c2)));
                    } else if (w != 127) {
                        LogUtil.h("HwCommonFileMgr", "handleCancelReply default type :", Integer.valueOf(w));
                    } else {
                        i3 = CommonUtil.w(c2);
                        LogUtil.a("HwCommonFileMgr", "handleCancelReply validity_result :", Integer.valueOf(CommonUtil.w(c2)));
                    }
                }
                d(i2, i3);
                return;
            }
            LogUtil.h("HwCommonFileMgr", "handleCancelReply tlvs error");
        } catch (cwg unused) {
            LogUtil.b("HwCommonFileMgr", "handleCancelReply TlvException");
        }
    }

    private void d(int i2, int i3) {
        jys jysVar = this.v.get(Integer.valueOf(i2));
        if (jysVar == null || jysVar.c() == null) {
            return;
        }
        try {
            LogUtil.a("HwCommonFileMgr", "handleCancelReply errorCode :", Integer.valueOf(i3));
            if (i3 == 100000) {
                if (jysVar.s() == 1) {
                    jysVar.c().onResponse(20003, String.valueOf(jysVar.n()));
                    LogUtil.a("HwCommonFileMgr", "entry  watchType callback , fileName: ", jysVar.n());
                } else {
                    jysVar.c().onResponse(20003, "");
                }
                g();
                e(jysVar);
                return;
            }
            if (jysVar.s() == 1) {
                jysVar.c().onResponse(20004, String.valueOf(jysVar.n()));
            } else {
                jysVar.c().onResponse(20004, "");
            }
        } catch (RemoteException unused) {
            LogUtil.b("HwCommonFileMgr", "handleCancelReply RemoteException");
        }
    }

    private void e(int i2, int i3, DeviceInfo deviceInfo) {
        LogUtil.a("HwCommonFileMgr", "enter sendFileHashToDevice, fileId :", Integer.valueOf(i2), ", check_mode :", Integer.valueOf(i3));
        if (this.v.get(Integer.valueOf(i2)) == null) {
            LogUtil.h("HwCommonFileMgr", "sendFileHashToDevice, mFileInfoList have not or filePath isEmpty");
            a(i2, deviceInfo);
        } else if (i3 == 3) {
            e(i2, deviceInfo);
        }
    }

    private void e(int i2, DeviceInfo deviceInfo) {
        if (this.v.get(Integer.valueOf(i2)) == null) {
            a(i2, deviceInfo);
            LogUtil.h("HwCommonFileMgr", "handleShare commonFileInfo error");
            return;
        }
        String u = this.v.get(Integer.valueOf(i2)).u();
        if (TextUtils.isEmpty(u)) {
            LogUtil.a("HwCommonFileMgr", "handleShare not from cache :", Integer.valueOf(i2));
            u = j(this.v.get(Integer.valueOf(i2)));
        }
        if (!TextUtils.isEmpty(u)) {
            LogUtil.a("HwCommonFileMgr", "sendFileHashToDevice, get hash success");
            c(i2, u, deviceInfo);
        } else {
            LogUtil.h("HwCommonFileMgr", "sendFileHashToDevice, get hash failed");
            a(i2, deviceInfo);
        }
    }

    private void b(int i2, int i3, int i4, List<Integer> list) {
        LogUtil.a("HwCommonFileMgr", "Enter sendDataToDevice fileId: ", Integer.valueOf(i2), ", offset: ", Integer.valueOf(i3), ", length: ", Integer.valueOf(i4), ", bitmap.size :", Integer.valueOf(list.size()));
        if (!this.v.containsKey(Integer.valueOf(i2))) {
            LogUtil.h("HwCommonFileMgr", "sendDataToDevice, found no fileId");
        } else {
            c(i2, i3, i4, e(this.v.get(Integer.valueOf(i2)), i2, i3, i4));
        }
    }

    private void c(int i2, int i3, int i4, byte[] bArr) {
        g(i2, i3);
        jyy jyyVar = this.f.get(Integer.valueOf(i2));
        if (jyyVar == null) {
            LogUtil.h("HwCommonFileMgr", "fileTransferParameter is null,fileId is :", Integer.valueOf(i2));
            return;
        }
        int c2 = jyyVar.c();
        boolean z = !jyyVar.f();
        LogUtil.a("HwCommonFileMgr", "sendDataToDevice, isNeedEncrypt :", Boolean.valueOf(z), ", frameLength :", Integer.valueOf(c2));
        int e2 = e(i4, c2);
        int i5 = i3;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < e2; i9++) {
            int a2 = a(i4, c2, i8);
            byte[] c3 = c(i7, a2, bArr);
            int i10 = i6 > 255 ? 0 : i6;
            d(i2, i10, i5, c3, z);
            StringBuilder sb = new StringBuilder(16);
            sb.append("transferData psnID ");
            sb.append(i10);
            sb.append("length = ");
            sb.append(a2);
            sb.append("ota_offset = ");
            sb.append(i5);
            sb.append("sended_length = ");
            sb.append(i8);
            LogUtil.a("HwCommonFileMgr", sb.toString());
            i8 += a2;
            i5 += a2;
            i7 += a2;
            i6 = i10 + 1;
        }
        jyy jyyVar2 = this.f.get(Integer.valueOf(i2));
        int e3 = jyyVar2 != null ? jyyVar2.e() : 0;
        LogUtil.a("HwCommonFileMgr", "sendDataToDevice, mWaitTimeout :", Integer.valueOf(e3));
        h(i2, e3);
    }

    private int e(int i2, int i3) {
        int i4;
        if (i2 % i3 == 0) {
            i4 = i2 / i3;
        } else {
            i4 = (i2 / i3) + 1;
        }
        LogUtil.a("HwCommonFileMgr", "sendDataToDevice, file_array :", Integer.valueOf(i4));
        return i4;
    }

    private byte[] c(int i2, int i3, byte[] bArr) {
        byte[] bArr2 = new byte[i3];
        try {
            System.arraycopy(bArr, i2, bArr2, 0, i3);
        } catch (ArrayIndexOutOfBoundsException e2) {
            LogUtil.b("HwCommonFileMgr", "getSendBytes, ArrayIndexOutOfBoundsException,", ExceptionUtils.d(e2));
        }
        return bArr2;
    }

    private int a(int i2, int i3, int i4) {
        if ((i2 - i4) / i3 != 0) {
            LogUtil.a("HwCommonFileMgr", "send max, size :", Integer.valueOf(i3));
            return i3;
        }
        int i5 = i2 % i3;
        LogUtil.a("HwCommonFileMgr", "send not max, size :", Integer.valueOf(i5));
        return i5;
    }

    private void g(int i2, int i3) {
        int q;
        LogUtil.a("HwCommonFileMgr", "reportProgressForUi entry.");
        if (this.v.get(Integer.valueOf(i2)) == null || (q = this.v.get(Integer.valueOf(i2)).q()) == 0) {
            return;
        }
        long j = (i3 * 100) / q;
        if (j >= -2147483648L && j <= 2147483647L) {
            int i4 = (int) j;
            LogUtil.a("HwCommonFileMgr", "fileSize :", Integer.valueOf(q), " , offset :", Integer.valueOf(i3), " , progress :", Integer.valueOf(i4));
            try {
                if (this.v.get(Integer.valueOf(i2)).ah() != null) {
                    this.v.get(Integer.valueOf(i2)).ah().onFileTransferState(i4, "");
                } else {
                    LogUtil.a("HwCommonFileMgr", "CallBack() is null");
                }
                return;
            } catch (RemoteException unused) {
                LogUtil.b("HwCommonFileMgr", "reportProgressForUi, RemoteException");
                return;
            }
        }
        LogUtil.a("HwCommonFileMgr", "progressLong is not Integer.");
    }

    private void h(int i2, int i3) {
        if (this.p != null) {
            if (i3 != 0) {
                Message obtain = Message.obtain();
                obtain.what = 100;
                obtain.arg1 = i2;
                this.p.sendMessageDelayed(obtain, i3 * 1000);
                return;
            }
            return;
        }
        HandlerThread handlerThread = new HandlerThread("HwCommonFileMgr");
        this.t = handlerThread;
        handlerThread.start();
        this.p = new c(this.t.getLooper());
        if (i3 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = 100;
            obtain2.arg1 = i2;
            this.p.sendMessageDelayed(obtain2, i3 * 1000);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00e6 A[Catch: all -> 0x011e, TRY_LEAVE, TryCatch #2 {, blocks: (B:7:0x000b, B:9:0x001a, B:11:0x0024, B:15:0x0045, B:17:0x0049, B:19:0x0067, B:21:0x006b, B:24:0x007f, B:26:0x0083, B:29:0x00a9, B:32:0x00e6, B:35:0x00f5, B:40:0x00d2, B:44:0x004d, B:45:0x010f), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f5 A[Catch: all -> 0x011e, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:7:0x000b, B:9:0x001a, B:11:0x0024, B:15:0x0045, B:17:0x0049, B:19:0x0067, B:21:0x006b, B:24:0x007f, B:26:0x0083, B:29:0x00a9, B:32:0x00e6, B:35:0x00f5, B:40:0x00d2, B:44:0x004d, B:45:0x010f), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte[] e(defpackage.jys r7, int r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyx.e(jys, int, int, int):byte[]");
    }

    private void e(int i2, int i3, byte[] bArr) {
        if (this.j.size() >= 4) {
            this.j.remove(Integer.valueOf(this.j.entrySet().iterator().next().getKey().intValue()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(i3), bArr);
        LogUtil.a("HwCommonFileMgr", "bufferArrayMap add.", Integer.valueOf(hashMap.size()));
        this.j.put(Integer.valueOf(i2), hashMap);
    }

    private boolean e(String str) {
        return str == null || str.indexOf(FeedbackWebConstants.INVALID_FILE_NAME_PRE) < 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.Closeable, java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v5, types: [android.os.ParcelFileDescriptor, java.io.Closeable] */
    private int d(File file, long j) {
        int i2 = 0;
        if (file == null) {
            return 0;
        }
        if (file.exists()) {
            ?? b2 = b(file.getPath(), j);
            if (b2 == 0) {
                LogUtil.h("HwCommonFileMgr", "getFileSizeByFilePath, getFileSize error.");
                IoUtils.e(this.x);
                return 0;
            }
            try {
                try {
                    long size = b2.getChannel().size();
                    LogUtil.a("HwCommonFileMgr", "fileInputStream getChannel( size is: ", Long.valueOf(size));
                    i2 = size > 2147483647L ? -1 : (int) size;
                } catch (IOException unused) {
                    LogUtil.b("HwCommonFileMgr", "getFileSizeByFilePath, IOException");
                }
            } finally {
                IoUtils.e((Closeable) b2);
                IoUtils.e(this.x);
            }
        }
        return i2;
    }

    private int bLh_(ParcelFileDescriptor parcelFileDescriptor) {
        FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
        int i2 = 0;
        try {
            try {
                long size = fileInputStream.getChannel().size();
                LogUtil.a("HwCommonFileMgr", "fileInputStream getChannel( size is: ", Long.valueOf(size));
                i2 = size > 2147483647L ? -1 : (int) size;
            } catch (IOException unused) {
                LogUtil.b("HwCommonFileMgr", "getFileSizeByFileDescriptor, IOException");
            }
            return i2;
        } finally {
            IoUtils.e(fileInputStream);
            IoUtils.e(this.x);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(jys jysVar) {
        if (jysVar == null) {
            LogUtil.h("HwCommonFileMgr", "handleFailed error, fileInfo is null");
            return;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.h.size()) {
                break;
            }
            jys jysVar2 = this.h.get(i2);
            LogUtil.a("HwCommonFileMgr", "handleFailed has cache file name :", jysVar2.n(), " , file type :", Integer.valueOf(jysVar2.s()));
            if (TextUtils.equals(jysVar2.n(), jysVar.n()) && jysVar2.s() == jysVar.s()) {
                LogUtil.a("HwCommonFileMgr", "delete commonFileInfo.name :", jysVar2.n());
                this.h.remove(jysVar2);
                break;
            }
            i2++;
        }
        LogUtil.a("HwCommonFileMgr", "handleFailed has fileInfo file id :", Integer.valueOf(jysVar.h()));
        if (this.v.get(Integer.valueOf(jysVar.h())) != null) {
            this.v.remove(Integer.valueOf(jysVar.h()));
        }
        b();
        synchronized (b) {
            if (jysVar.equals(this.n)) {
                LogUtil.a("HwCommonFileMgr", "handleFailed set mCurrentCommonFileInfo null:", Integer.valueOf(jysVar.h()));
                this.n = null;
            }
        }
        d(jysVar.s());
        if (this.f.get(Integer.valueOf(jysVar.h())) != null) {
            this.f.remove(Integer.valueOf(jysVar.h()));
        }
    }

    private String j(jys jysVar) {
        if (jysVar == null) {
            LogUtil.h("HwCommonFileMgr", "CommonFileInfo is null.");
            return null;
        }
        FileInputStream b2 = b(jysVar, jysVar.ai());
        if (b2 == null) {
            LogUtil.h("HwCommonFileMgr", "sha256File error.");
            IoUtils.e(this.x);
            return null;
        }
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bArr = new byte[1024];
                int read = b2.read(bArr);
                if (read == -1) {
                    messageDigest.update(bArr, 0, 0);
                } else {
                    messageDigest.update(bArr, 0, read);
                    while (true) {
                        int read2 = b2.read(bArr);
                        if (read2 == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read2);
                    }
                }
                return cvx.d(messageDigest.digest());
            } catch (IOException unused) {
                LogUtil.b("HwCommonFileMgr", "sha256File IOException");
                a(b2);
                IoUtils.e(this.x);
                return "";
            } catch (NoSuchAlgorithmException unused2) {
                LogUtil.b("HwCommonFileMgr", "MessageDigest not support");
                a(b2);
                IoUtils.e(this.x);
                return "";
            }
        } finally {
            a(b2);
            IoUtils.e(this.x);
        }
    }

    private FileInputStream b(jys jysVar, long j) {
        LogUtil.a("HwCommonFileMgr", "fileInfo.getFilePath() :", jysVar.l(), "uriId :", Long.valueOf(j));
        Object[] objArr = new Object[2];
        objArr[0] = "getFileInputStream, fileInfo.getParcelFileDescriptor() isNull :";
        objArr[1] = Boolean.valueOf(jysVar.bLe_() == null);
        LogUtil.a("HwCommonFileMgr", objArr);
        if (jysVar.bLe_() != null) {
            LogUtil.a("HwCommonFileMgr", "FileDescriptor is :", jysVar.bLe_().getFileDescriptor());
            return new FileInputStream(jysVar.bLe_().getFileDescriptor());
        }
        return b(jysVar.l(), j);
    }

    private FileInputStream b(String str, long j) {
        FileInputStream fileInputStream;
        if (!e(str)) {
            LogUtil.h("HwCommonFileMgr", "getFileInputStreamByUriId checkFilepath error");
            return null;
        }
        try {
            if (j == -1) {
                String c2 = CommonUtil.c(str);
                if (c2 == null) {
                    return null;
                }
                fileInputStream = new FileInputStream(c2);
            } else {
                Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, j);
                InputStream openInputStream = this.l.getContentResolver().openInputStream(withAppendedId);
                if (openInputStream instanceof FileInputStream) {
                    LogUtil.b("HwCommonFileMgr", "transform type success ");
                    fileInputStream = (FileInputStream) openInputStream;
                } else {
                    LogUtil.b("HwCommonFileMgr", "transform type fail");
                    ParcelFileDescriptor openFileDescriptor = this.l.getContentResolver().openFileDescriptor(withAppendedId, "r");
                    this.x = openFileDescriptor;
                    if (openFileDescriptor != null) {
                        return new FileInputStream(this.x.getFileDescriptor());
                    }
                    return null;
                }
            }
            return fileInputStream;
        } catch (FileNotFoundException unused) {
            LogUtil.b("HwCommonFileMgr", "getFileInputStreamByUriId FileNotFoundException");
            return null;
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                LogUtil.b("HwCommonFileMgr", "close stream IoException");
            }
        }
    }

    private boolean c(jys jysVar) {
        Iterator<jys> it = this.h.iterator();
        while (it.hasNext()) {
            if (it.next().equals(jysVar)) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: classes5.dex */
    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 100) {
                int i2 = message.arg1;
                LogUtil.a("HwCommonFileMgr", "handleMessage wait timeout! fileId :", Integer.valueOf(i2));
                jyx.this.c(i2, 141000);
                jyx jyxVar = jyx.this;
                jyxVar.e((jys) jyxVar.v.get(Integer.valueOf(i2)));
                return;
            }
            if (i == 200) {
                jyx.this.bLi_(message);
                return;
            }
            if (i != 300) {
                if (i == 400) {
                    jyx.this.h();
                    return;
                } else {
                    LogUtil.h("HwCommonFileMgr", "handleMessage default msg.what :", Integer.valueOf(message.what));
                    return;
                }
            }
            int i3 = message.arg1;
            int i4 = message.arg2;
            LogUtil.a("HwCommonFileMgr", "handleMessage device report error, fileId :", Integer.valueOf(i3), " , result :", Integer.valueOf(i4));
            jyx.this.c(i3, i4);
            jyx jyxVar2 = jyx.this;
            jyxVar2.e((jys) jyxVar2.v.get(Integer.valueOf(i3)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bLi_(Message message) {
        int i2 = message.arg1;
        int i3 = message.arg2;
        LogUtil.a("HwCommonFileMgr", "handleMessage receive result! fileId :", Integer.valueOf(i2), " , result :", Integer.valueOf(i3));
        c(i2);
        try {
            jys jysVar = this.v.get(Integer.valueOf(i2));
            if (jysVar != null && jysVar.ah() != null) {
                g();
                e(jysVar);
                if (i3 == 1) {
                    jysVar.ah().onFileTransferState(100, "");
                }
                jysVar.ah().onFileRespond(i3, "");
                LogUtil.a("HwCommonFileMgr", "handleMessage receive result! onFileRespond");
                return;
            }
            LogUtil.a("HwCommonFileMgr", "CallBack() is null");
        } catch (RemoteException unused) {
            LogUtil.b("HwCommonFileMgr", "handleMessage receive result! RemoteException");
        } catch (Exception e2) {
            LogUtil.b("HwCommonFileMgr", LogAnonymous.b((Throwable) e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ArrayList arrayList = new ArrayList(16);
        synchronized (d) {
            DeviceCommand poll = this.q.poll();
            while (poll != null) {
                arrayList.add(poll);
                poll = this.q.poll();
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jsz.b(BaseApplication.getContext()).sendDeviceData((DeviceCommand) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, int i3) {
        try {
            if (this.v.get(Integer.valueOf(i2)) != null && this.v.get(Integer.valueOf(i2)).ah() != null) {
                this.v.get(Integer.valueOf(i2)).ah().onUpgradeFailed(i3, "");
                LogUtil.a("HwCommonFileMgr", "reportFailedForUi fileId :", Integer.valueOf(i2), " , errorCode :", Integer.valueOf(i3));
            } else {
                LogUtil.a("HwCommonFileMgr", "CallBack() is null");
            }
        } catch (RemoteException unused) {
            LogUtil.b("HwCommonFileMgr", "reportFailedForUi RemoteException");
        }
    }
}

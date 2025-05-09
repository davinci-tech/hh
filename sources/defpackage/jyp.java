package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.json.JsonSanitizer;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwcommonfilemgr.bean.Priority;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Sha256;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class jyp extends HwBaseManager implements ParserInterface {
    private static final Object c = new Object();
    private static volatile jyp e;

    /* renamed from: a, reason: collision with root package name */
    private jyq f14201a;
    private BroadcastReceiver b;
    private CopyOnWriteArrayList<jys> d;
    private ConcurrentHashMap<Integer, jyy> f;
    private HandlerThread g;
    private Context h;
    private IBaseResponseCallback i;
    private a j;
    private ConcurrentHashMap<Integer, jys> k;
    private ConcurrentHashMap<Integer, jyt> l;
    private cwl m;
    private boolean n;

    private jyp(Context context) {
        super(context);
        this.k = new ConcurrentHashMap<>(20);
        this.d = new CopyOnWriteArrayList<>();
        this.l = new ConcurrentHashMap<>(20);
        this.m = new cwl();
        this.n = false;
        this.f = new ConcurrentHashMap<>(20);
        this.b = new BroadcastReceiver() { // from class: jyp.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (context2 == null || intent == null) {
                    LogUtil.h("CommonFileRequestManager", "CommonFileRequestManager context or intent is null");
                    return;
                }
                if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    LogUtil.h("CommonFileRequestManager", "CommonFileRequestManager receiver not connect change");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("CommonFileRequestManager", "mConnectStateChangedReceiver deviceInfo null");
                    return;
                }
                LogUtil.a("CommonFileRequestManager", "mConnectStateChangedReceiver() status:", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                if (deviceInfo.getDeviceConnectState() == 3) {
                    jyp.this.m();
                    jyp.this.i();
                    jyp.this.j.removeCallbacksAndMessages(null);
                    return;
                }
                LogUtil.c("CommonFileRequestManager", "mConnectStateChangedReceiver() default");
            }
        };
        this.h = context;
        HandlerThread handlerThread = new HandlerThread("CommonFileRequestManager");
        this.g = handlerThread;
        handlerThread.start();
        this.j = new a(this.g.getLooper());
        this.f14201a = jyq.d();
        BroadcastManagerUtil.bFC_(this.h, this.b, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        this.i = iBaseResponseCallback;
    }

    public void e() {
        this.i = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (!this.d.isEmpty()) {
            Iterator<jys> it = this.d.iterator();
            while (it.hasNext()) {
                jys next = it.next();
                ITransferSleepAndDFXFileCallback t = next.t();
                if (t != null) {
                    try {
                        LogUtil.a("CommonFileRequestManager", "first command not receive, check tasks");
                        t.onFailure(30004, "");
                    } catch (RemoteException unused) {
                        LogUtil.b("CommonFileRequestManager", "removeAllCaches disconnected RemoteException");
                    }
                }
                IBaseCommonCallback w = next.w();
                if (w != null) {
                    try {
                        LogUtil.a("CommonFileRequestManager", "first command not receive, check tasks, to kit.");
                        w.onResponse(300004, "");
                    } catch (RemoteException unused2) {
                        LogUtil.b("CommonFileRequestManager", "removeAllCaches disconnected RemoteException");
                    }
                }
            }
        }
        this.d.clear();
    }

    private void j() {
        LogUtil.a("CommonFileRequestManager", "enter remove kit , size:", Integer.valueOf(this.d.size()));
        if (this.d.size() <= 0 || this.d.get(0).w() == null) {
            return;
        }
        LogUtil.a("CommonFileRequestManager", "remove kit callback");
        this.d.remove(0);
        n();
    }

    public void d() {
        this.h.unregisterReceiver(this.b);
        b();
        a();
    }

    private static void b() {
        synchronized (c) {
            e = null;
        }
    }

    private void a() {
        HandlerThread handlerThread = this.g;
        if (handlerThread != null) {
            handlerThread.quit();
            this.g = null;
        }
    }

    public static jyp c() {
        jyp jypVar;
        synchronized (c) {
            if (e == null) {
                e = new jyp(BaseApplication.getContext());
            }
            jypVar = e;
        }
        return jypVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        for (Map.Entry<Integer, jys> entry : this.k.entrySet()) {
            LogUtil.a("CommonFileRequestManager", "disconnected fileId:", entry.getKey());
            a(entry);
            if (entry.getValue().t() != null) {
                try {
                    entry.getValue().t().onFailure(30004, "");
                } catch (RemoteException unused) {
                    LogUtil.b("CommonFileRequestManager", "disconnected RemoteException");
                }
            }
            d(entry.getValue());
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 44;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("CommonFileRequestManager", bArr, "getResult() message: ");
        if (bArr == null || bArr.length <= 2) {
            return;
        }
        byte b = bArr[1];
        if (b == 1) {
            a(bArr);
            return;
        }
        if (b == 2) {
            d(bArr);
            return;
        }
        if (b == 3) {
            c(bArr);
            return;
        }
        if (b == 5) {
            e(bArr);
            return;
        }
        if (b == 6) {
            b(bArr);
        } else if (b == 7) {
            d(deviceInfo, bArr);
        } else {
            LogUtil.c("CommonFileRequestManager", "getResult() default");
        }
    }

    private void a(jys jysVar, long j) {
        jysVar.d(j);
        jysVar.a(j);
        if (this.d.size() == 0) {
            this.d.add(jysVar);
        }
        a(jysVar);
    }

    private boolean c(jys jysVar, long j) {
        LogUtil.a("CommonFileRequestManager", "prepare putCommonFileInfo to queue");
        boolean z = false;
        for (int i = 0; i < this.d.size(); i++) {
            jys jysVar2 = this.d.get(i);
            if ((jysVar2.ad() != 0 && j - jysVar2.ad() > 86400000) || (jysVar2.ac() != 0 && j - jysVar2.ac() > 3600000)) {
                LogUtil.a("CommonFileRequestManager", "putCommonFileInfo check task is not effective");
                this.d.remove(jysVar2);
            } else if (!jysVar2.equals(jysVar)) {
                continue;
            } else {
                if (jysVar.ag()) {
                    LogUtil.a("CommonFileRequestManager", "cancel line up success:", Integer.valueOf(jysVar.h()));
                    b(jysVar, 3);
                    this.d.remove(jysVar2);
                    return false;
                }
                z = true;
            }
        }
        if (z) {
            LogUtil.h("CommonFileRequestManager", "putCommonFileInfo sameTask exist");
            return false;
        }
        if (jysVar.ag()) {
            LogUtil.a("CommonFileRequestManager", "cancel line up fail:", Integer.valueOf(jysVar.h()));
            b(jysVar, 4);
            return false;
        }
        jysVar.a(j);
        return b(jysVar);
    }

    private boolean b(jys jysVar) {
        jys jysVar2;
        for (int size = this.d.size() - 1; size >= 0; size--) {
            try {
                jysVar2 = this.d.get(size);
                LogUtil.a("CommonFileRequestManager", "putCommonFileInfo:", Integer.valueOf(jysVar2.v().getValue()), ",", Integer.valueOf(jysVar.v().getValue()));
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("CommonFileRequestManager", "mCacheFiles occur exception");
            }
            if (size == this.d.size() - 1 && jysVar.v().getValue() <= jysVar2.v().getValue()) {
                LogUtil.a("CommonFileRequestManager", "putCommonFileInfo index last");
                this.d.add(jysVar);
                return true;
            }
            if (jysVar.v().getValue() <= jysVar2.v().getValue()) {
                LogUtil.a("CommonFileRequestManager", "putCommonFileInfo insert index:", Integer.valueOf(size));
                a(size + 1, jysVar);
                return true;
            }
            if (size == 0 && jysVar.v().getValue() > jysVar2.v().getValue()) {
                LogUtil.a("CommonFileRequestManager", "putCommonFileInfo insert index first");
                this.n = true;
                this.d.add(0, jysVar);
                return true;
            }
        }
        return false;
    }

    private void a(int i, jys jysVar) {
        if (i > this.d.size()) {
            this.d.add(jysVar);
        } else {
            this.d.add(i, jysVar);
        }
    }

    private void a(jys jysVar) {
        if (this.n) {
            LogUtil.a("CommonFileRequestManager", "send 5.44.1 task insert, requestNextTask");
            n();
        } else {
            DeviceCommand e2 = jyv.e(jysVar);
            c(jysVar.s());
            jsz.b(BaseApplication.getContext()).sendDeviceData(e2);
            k();
        }
    }

    public void a(jqj jqjVar, final IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        e(jqjVar, new ITransferSleepAndDFXFileCallback.Stub() { // from class: jyp.1
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "5.44 onFileRespond onSuccess", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onFileRespond(i, str);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "5.44 onFailure : ", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onUpgradeFailed(i, str);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "5.44 onProgress : ", Integer.valueOf(i));
                iAppTransferFileResultAIDLCallback.onFileTransferState(i, str);
            }
        });
    }

    private void e(jqj jqjVar, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        int i;
        int i2;
        String j = jqjVar.j();
        int n = jqjVar.n();
        boolean s = jqjVar.s();
        int[] r = jqjVar.r();
        String d = jqjVar.d();
        if (jsn.a(d)) {
            RequestFileInfo requestFileInfo = new RequestFileInfo();
            requestFileInfo.setFileName(j);
            requestFileInfo.setFileType(n);
            requestFileInfo.setNeedVerify(s);
            requestFileInfo.setTimes(r);
            requestFileInfo.setDeviceMac(d);
            c(requestFileInfo, iTransferSleepAndDFXFileCallback);
            return;
        }
        jys jysVar = new jys();
        jysVar.e(j);
        jysVar.h(n);
        jysVar.c(s);
        jysVar.b(iTransferSleepAndDFXFileCallback);
        jysVar.c(Priority.DEFAULT);
        if (r == null || r.length != 2) {
            i = -1;
            i2 = -1;
        } else {
            i = r[0];
            i2 = r[1];
            jysVar.j(i);
            jysVar.a(i2);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.d.size() > 0) {
            if (c(jysVar, currentTimeMillis)) {
                h();
                return;
            } else {
                a(jysVar, currentTimeMillis);
                return;
            }
        }
        jysVar.d(currentTimeMillis);
        jysVar.a(currentTimeMillis);
        if (!c(jysVar)) {
            this.d.add(jysVar);
        }
        e(j, n, i, i2);
    }

    public void b(jqj jqjVar, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        int i;
        int i2;
        LogUtil.a("CommonFileRequestManager", "startRequestFile0 entry");
        String j = jqjVar.j();
        int n = jqjVar.n();
        boolean s = jqjVar.s();
        int[] r = jqjVar.r();
        String d = jqjVar.d();
        if (jsn.c() || jsn.a(d)) {
            RequestFileInfo requestFileInfo = new RequestFileInfo();
            requestFileInfo.setFileName(j);
            requestFileInfo.setFileType(n);
            requestFileInfo.setNeedVerify(s);
            requestFileInfo.setTimes(r);
            requestFileInfo.setDeviceMac(d);
            if (n == 10) {
                requestFileInfo.setSourcePackageName(jqjVar.q());
                requestFileInfo.setSourceCertificate(jqjVar.l());
                requestFileInfo.setDestinationPackageName(jqjVar.e());
                requestFileInfo.setDestinationCertificate(jqjVar.c());
                requestFileInfo.setDescription(jqjVar.b());
            }
            requestFileInfo.setDictTypeId(jqjVar.f());
            c(requestFileInfo, iTransferSleepAndDFXFileCallback);
            return;
        }
        jys jysVar = new jys();
        jysVar.e(j);
        jysVar.h(n);
        jysVar.c(s);
        jysVar.b(iTransferSleepAndDFXFileCallback);
        jysVar.c(Priority.DEFAULT);
        if (r == null || r.length != 2) {
            i = -1;
            i2 = -1;
        } else {
            i = r[0];
            i2 = r[1];
            jysVar.j(i);
            jysVar.a(i2);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.d.size() > 0) {
            if (c(jysVar, currentTimeMillis)) {
                h();
                return;
            } else {
                a(jysVar, currentTimeMillis);
                return;
            }
        }
        jysVar.d(currentTimeMillis);
        jysVar.a(currentTimeMillis);
        if (!c(jysVar)) {
            this.d.add(jysVar);
        }
        e(j, n, i, i2);
    }

    public void a(jqj jqjVar, IBaseCommonCallback iBaseCommonCallback) {
        int i;
        int i2;
        LogUtil.a("CommonFileRequestManager", "startRequestFile1 entry");
        String d = jqjVar.d();
        if (jsn.c() || jsn.a(d)) {
            d(jqjVar, iBaseCommonCallback);
            return;
        }
        int[] r = jqjVar.r();
        if (r == null || r.length != 2) {
            i = -1;
            i2 = -1;
        } else {
            i = r[0];
            i2 = r[1];
        }
        String j = jqjVar.j();
        int n = jqjVar.n();
        boolean s = jqjVar.s();
        jys jysVar = new jys();
        jysVar.h(n);
        if (!TextUtils.isEmpty(j)) {
            jysVar.e(j);
        }
        jysVar.c(s);
        jysVar.b(iBaseCommonCallback);
        jysVar.j(i);
        jysVar.a(i2);
        jysVar.c(Priority.DEFAULT);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.d.size() > 0) {
            LogUtil.a("CommonFileRequestManager", "mCacheFiles size: ", Integer.valueOf(this.d.size()), " fileInfo name: ", jysVar.n(), " type: ", Integer.valueOf(jysVar.s()));
            c(jysVar, AdapterWithBottomView.TYPE_BOTTOM);
            if (c(jysVar, currentTimeMillis)) {
                h();
                return;
            } else {
                a(jysVar, currentTimeMillis);
                return;
            }
        }
        jysVar.d(currentTimeMillis);
        jysVar.a(currentTimeMillis);
        if (!c(jysVar)) {
            this.d.add(jysVar);
        }
        e(j, n, i, i2);
    }

    private void c(RequestFileInfo requestFileInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        requestFileInfo.setKit(false);
        if (TextUtils.isEmpty(requestFileInfo.getDeviceMac())) {
            requestFileInfo.setDeviceMac(g());
        }
        LogUtil.a("CommonFileRequestManager", " deviceMac is: ", blt.a(requestFileInfo.getDeviceMac()));
        snq.c().startReceiveFileFromWear(requestFileInfo, e(iTransferSleepAndDFXFileCallback));
    }

    private ITransferFileCallback.Stub e(final ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        return new ITransferFileCallback.Stub() { // from class: jyp.2
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback2 = iTransferSleepAndDFXFileCallback;
                if (iTransferSleepAndDFXFileCallback2 != null) {
                    try {
                        iTransferSleepAndDFXFileCallback2.onSuccess(i, str, str2);
                    } catch (Exception e2) {
                        LogUtil.b("CommonFileRequestManager", "ITransferFileCallback onSuccess mTransferDataContent:", str, ", mTransferStateContent:", str2, ", IllegalArgumentException:", ExceptionUtils.d(e2));
                    }
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                try {
                    ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback2 = iTransferSleepAndDFXFileCallback;
                    if (iTransferSleepAndDFXFileCallback2 != null) {
                        iTransferSleepAndDFXFileCallback2.onFailure(i, str);
                    }
                } catch (IllegalArgumentException e2) {
                    LogUtil.b("CommonFileRequestManager", "ITransferFileCallback onFailure errMsg:", str, ", IllegalArgumentException:", ExceptionUtils.d(e2));
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback2 = iTransferSleepAndDFXFileCallback;
                if (iTransferSleepAndDFXFileCallback2 != null) {
                    iTransferSleepAndDFXFileCallback2.onProgress(i, str);
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "ITransferFileCallback onResponse");
            }
        };
    }

    private void d(jqj jqjVar, final IBaseCommonCallback iBaseCommonCallback) {
        RequestFileInfo requestFileInfo = new RequestFileInfo();
        requestFileInfo.setFileName(jqjVar.j());
        requestFileInfo.setFileType(jqjVar.n());
        requestFileInfo.setNeedVerify(jqjVar.s());
        requestFileInfo.setTimes(jqjVar.r());
        requestFileInfo.setKit(true);
        String d = jqjVar.d();
        if (TextUtils.isEmpty(d)) {
            requestFileInfo.setDeviceMac(g());
        } else {
            requestFileInfo.setDeviceMac(d);
        }
        LogUtil.a("CommonFileRequestManager", " deviceMac is: ", blt.a(requestFileInfo.getDeviceMac()));
        snq.c().startReceiveFileFromWear(requestFileInfo, new ITransferFileCallback.Stub() { // from class: jyp.5
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "ITransferFileCallback onSuccess");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                try {
                    IBaseCommonCallback iBaseCommonCallback2 = iBaseCommonCallback;
                    if (iBaseCommonCallback2 != null) {
                        iBaseCommonCallback2.onResponse(i, str);
                    }
                } catch (Exception e2) {
                    LogUtil.a("CommonFileRequestManager", "ITransferFileCallback onFailure Exception ", ExceptionUtils.d(e2));
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "ITransferFileCallback onProgress");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                IBaseCommonCallback iBaseCommonCallback2 = iBaseCommonCallback;
                if (iBaseCommonCallback2 != null) {
                    iBaseCommonCallback2.onResponse(i, str);
                }
            }
        });
    }

    private void h() {
        if (this.d.size() == 1) {
            n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.n = false;
        if (this.d.size() > 0) {
            jys jysVar = this.d.get(0);
            LogUtil.a("CommonFileRequestManager", "request next task:", Integer.valueOf(jysVar.s()));
            jysVar.d(System.currentTimeMillis());
            if (jysVar.ae()) {
                LogUtil.a("CommonFileRequestManager", "request next task:", Integer.valueOf(jysVar.h()));
                d(jysVar.h(), jysVar);
                return;
            } else if (jysVar.s() == 1) {
                b(jysVar.n(), jysVar.s());
                return;
            } else if (jysVar.s() >= 10 && jysVar.s() <= 13) {
                a(jysVar);
                return;
            } else {
                e(jysVar.n(), jysVar.s(), jysVar.z(), jysVar.g());
                return;
            }
        }
        LogUtil.a("CommonFileRequestManager", "task is empty.");
    }

    public void b(jqj jqjVar, IBaseCallback iBaseCallback) {
        String j = jqjVar.j();
        int n = jqjVar.n();
        String d = jqjVar.d();
        if (jsn.c() || jsn.a(d)) {
            RequestFileInfo requestFileInfo = new RequestFileInfo();
            requestFileInfo.setFileName(j);
            requestFileInfo.setFileType(n);
            if (TextUtils.isEmpty(d)) {
                requestFileInfo.setDeviceMac(g());
            } else {
                requestFileInfo.setDeviceMac(d);
            }
            snq.c().stopReceiveFileFromWear(requestFileInfo, c(iBaseCallback));
            return;
        }
        for (Map.Entry<Integer, jys> entry : this.k.entrySet()) {
            if (TextUtils.equals(entry.getValue().n(), j) && n == entry.getValue().s()) {
                LogUtil.a("CommonFileRequestManager", "stopRequestFile fileId:", entry.getKey());
                entry.getValue().c(iBaseCallback);
                e(entry.getKey().intValue(), 4);
            }
        }
    }

    private String g() {
        String e2 = jyv.e();
        return TextUtils.isEmpty(e2) ? jyv.b() : e2;
    }

    private ITransferFileCallback.Stub c(final IBaseCallback iBaseCallback) {
        return new ITransferFileCallback.Stub() { // from class: jyp.4
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "stopRequestFile onSuccess");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "stopRequestFile onFailure");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "stopRequestFile onProgress");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                IBaseCallback iBaseCallback2 = iBaseCallback;
                if (iBaseCallback2 != null) {
                    iBaseCallback2.onResponse(i, str);
                }
            }
        };
    }

    private boolean c(jys jysVar) {
        Iterator<jys> it = this.d.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (jysVar.equals(it.next())) {
                z = true;
            }
        }
        return z;
    }

    private void e(String str, int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(16);
        if (!TextUtils.isEmpty(str)) {
            String c2 = cvx.c(str);
            sb.append(cvx.e(1) + cvx.e(c2.length() / 2) + c2);
        } else {
            LogUtil.a("CommonFileRequestManager", "5.44.1 file name is empty.");
        }
        sb.append(cvx.e(2) + cvx.e(1) + cvx.e(i));
        if (i2 != -1 && i3 != -1) {
            sb.append(cvx.e(5));
            sb.append(cvx.e(4));
            sb.append(cvx.b(i2));
            sb.append(cvx.e(6));
            sb.append(cvx.e(4));
            sb.append(cvx.b(i3));
        } else {
            LogUtil.a("CommonFileRequestManager", "5.44.1 startTime or endTime is -1, startTime:", Integer.valueOf(i2), ", end time:", Integer.valueOf(i3));
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(44);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setDataLen(cvx.a(sb.toString()).length);
        deviceCommand.setNeedAck(true);
        LogUtil.a("CommonFileRequestManager", "sendFileInfo, deviceCommand:", deviceCommand.toString());
        c(i);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        a(i);
    }

    private void c(int i) {
        this.l.put(Integer.valueOf(i), new jyt());
    }

    private void b(String str, int i) {
        String c2 = cvx.c(str);
        String str2 = (cvx.e(1) + cvx.e(c2.length() / 2) + c2) + (cvx.e(2) + cvx.e(1) + cvx.e(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(44);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setNeedAck(true);
        LogUtil.a("CommonFileRequestManager", "sendFileInfo, deviceCommand:", deviceCommand.toString());
        c(i);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void a(int i) {
        if (this.j == null) {
            HandlerThread handlerThread = new HandlerThread("CommonFileRequestManager");
            this.g = handlerThread;
            handlerThread.start();
            this.j = new a(this.g.getLooper());
        }
        Message obtain = Message.obtain();
        obtain.arg1 = 500;
        obtain.what = i;
        this.j.sendMessageDelayed(obtain, 35000L);
    }

    private void f() {
        LogUtil.a("CommonFileRequestManager", "enter removeFileInfoTimeout");
        if (this.d.size() > 0) {
            d(this.d.get(0).s());
        }
    }

    private void a(byte[] bArr) {
        l();
        if (this.n) {
            LogUtil.a("CommonFileRequestManager", "receive 5.44.1 task insert, requestNextTask");
            n();
            return;
        }
        f();
        String d = cvx.d(bArr);
        LogUtil.a("CommonFileRequestManager", "5.44.1 handleRequest:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("CommonFileRequestManager", "handleRequest dataBytes is error");
        } else {
            e(d.substring(4));
        }
    }

    private void e(String str) {
        jys jysVar = new jys();
        int i = 0;
        try {
            List<cwd> e2 = this.m.a(str).e();
            if (e2 != null && e2.size() > 0) {
                for (cwd cwdVar : e2) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c2 = cwdVar.c();
                    int c3 = jyv.c(a2, jysVar, c2);
                    if (c3 != 0) {
                        i = c3;
                    }
                    jyv.e(a2, jysVar, c2);
                }
            } else {
                LogUtil.h("CommonFileRequestManager", "handleRequest tlv list error");
            }
        } catch (cwg unused) {
            LogUtil.b("CommonFileRequestManager", "handleRequest error");
        }
        a(jysVar, i);
    }

    private void a(jys jysVar, int i) {
        Iterator<jys> it = this.d.iterator();
        while (it.hasNext()) {
            jys next = it.next();
            LogUtil.a("CommonFileRequestManager", "handleRequest has cache file name: ", next.n(), " cache type: ", Integer.valueOf(next.s()), " need verify: ", Boolean.valueOf(next.al()));
            if (this.f14201a.c(next.n(), jysVar.n()) && (next.s() == jysVar.s() || jysVar.s() == 0)) {
                jysVar.b(next.t());
                jysVar.b(next.w());
                jysVar.c(next.al());
                jysVar.d(next.k());
                jysVar.e(next.d());
                LogUtil.a("CommonFileRequestManager", "commonFileInfo.name: ", jysVar.n());
                break;
            }
            if (jysVar.n() == null && next.n() == null && i != 100000) {
                jysVar.b(next.w());
            }
        }
        if (i != 100000) {
            d(jysVar, i);
            if (jysVar.t() != null) {
                try {
                    jysVar.t().onFailure(i, "");
                    LogUtil.a("CommonFileRequestManager", "onUpgradeFailed errorCode:", Integer.valueOf(i));
                    d(jysVar);
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("CommonFileRequestManager", "commonFileInfo.getFileRequestCallback RemoteException");
                    return;
                }
            }
            LogUtil.a("CommonFileRequestManager", "file callback is null");
            try {
                if (jysVar.j() != null) {
                    jysVar.t().onFailure(i, "");
                    return;
                }
                return;
            } catch (RemoteException unused2) {
                LogUtil.b("CommonFileRequestManager", "commonFileInfo.getFileRequestCallback RemoteException");
                return;
            }
        }
        d(jysVar.h(), jysVar);
    }

    private void d(int i, jys jysVar) {
        Object[] objArr = new Object[3];
        objArr[0] = "device support transfer file, file type:";
        objArr[1] = Integer.valueOf(jysVar.s());
        objArr[2] = jysVar.s() == 0 ? ", no task deal with this tlv." : "";
        LogUtil.a("CommonFileRequestManager", objArr);
        if (jysVar.s() == 0) {
            return;
        }
        jysVar.b(i);
        this.k.put(Integer.valueOf(jysVar.h()), jysVar);
        LogUtil.a("CommonFileRequestManager", "reset info:file id:", Integer.valueOf(jysVar.h()), ", fileType:", Integer.valueOf(jysVar.s()), ", filename:", jysVar.n(), ", NeedVerify:", Boolean.valueOf(jysVar.al()));
        if (jysVar.al()) {
            c(jysVar.h(), 1);
        } else {
            j(jysVar.h());
        }
    }

    private void c(int i, int i2) {
        if (this.n) {
            LogUtil.a("CommonFileRequestManager", "send 5.44.2 task insert, requestNextTask");
            n();
            return;
        }
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(1) + cvx.e(i2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(44);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setNeedAck(true);
        LogUtil.a("CommonFileRequestManager", "sendFileCheck, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        k();
    }

    private void d(byte[] bArr) {
        l();
        if (this.n) {
            LogUtil.a("CommonFileRequestManager", "receive 5.44.2 task insert, requestNextTask");
            n();
            return;
        }
        String d = cvx.d(bArr);
        LogUtil.a("CommonFileRequestManager", "5.44.2 handleRequestHash:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("CommonFileRequestManager", "handleRequestHash dataBytes is error");
            return;
        }
        try {
            List<cwd> e2 = this.m.a(d.substring(4)).e();
            if (e2 != null && e2.size() > 0) {
                String str = "";
                int i = 0;
                for (cwd cwdVar : e2) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c2 = cwdVar.c();
                    if (a2 == 1) {
                        i = CommonUtil.a(c2, 16);
                        LogUtil.a("CommonFileRequestManager", "handleRequestHash file id:", Integer.valueOf(i));
                    } else if (a2 != 3) {
                        LogUtil.c("CommonFileRequestManager", "handleRequestHash default");
                    } else {
                        str = c2;
                    }
                }
                e(i, str);
                return;
            }
            LogUtil.h("CommonFileRequestManager", "handleRequestHash tlv list error");
        } catch (cwg unused) {
            LogUtil.b("CommonFileRequestManager", "handleRequestHash error");
        }
    }

    private void e(int i, String str) {
        if (this.k.get(Integer.valueOf(i)) != null) {
            this.k.get(Integer.valueOf(i)).h(str);
            j(i);
        } else {
            LogUtil.a("CommonFileRequestManager", "saveFileHash error");
        }
    }

    private void j(int i) {
        if (this.n) {
            LogUtil.a("CommonFileRequestManager", "send 5.44.3 task insert, requestNextTask");
            n();
            return;
        }
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(0)) + (cvx.e(3) + cvx.e(0)) + (cvx.e(4) + cvx.e(0)) + (cvx.e(5) + cvx.e(0));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(44);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setNeedAck(true);
        LogUtil.a("CommonFileRequestManager", "sendRequestParameter, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        k();
    }

    private void c(byte[] bArr) {
        int d;
        l();
        if (this.n) {
            LogUtil.a("CommonFileRequestManager", "receive 5.44.3 task insert, requestNextTask");
            n();
            return;
        }
        String d2 = cvx.d(bArr);
        LogUtil.a("CommonFileRequestManager", "5.44.3 handleConsult:", d2);
        String substring = d2.substring(4);
        jyy jyyVar = new jyy();
        try {
            List<cwd> e2 = this.m.a(substring).e();
            if (e2 != null && e2.size() > 0) {
                for (cwd cwdVar : e2) {
                    b(jyyVar, CommonUtil.a(cwdVar.e(), 16), cwdVar.c());
                }
                if (jyyVar.c() == 0) {
                    LogUtil.h("CommonFileRequestManager", "xxx / 0 will error, check this info.");
                    return;
                }
                LogUtil.a("CommonFileRequestManager", "5.44.3 handleConsult fileId:", Integer.valueOf(jyyVar.b()));
                if (jyyVar.d() % jyyVar.c() == 0) {
                    d = (jyyVar.d() / jyyVar.c()) - 1;
                } else {
                    d = jyyVar.d() / jyyVar.c();
                }
                jyyVar.a(d);
                LogUtil.a("CommonFileRequestManager", "5.44.3 handleConsult psnMax:", Integer.valueOf(d));
                this.f.put(Integer.valueOf(jyyVar.b()), jyyVar);
                if (this.k.get(Integer.valueOf(jyyVar.b())) != null) {
                    i(jyyVar.b());
                    c(jyyVar, this.k.get(Integer.valueOf(jyyVar.b())));
                    return;
                }
                return;
            }
            LogUtil.h("CommonFileRequestManager", "handleConsult tlv list error");
        } catch (cwg unused) {
            LogUtil.b("CommonFileRequestManager", "handleConsult error");
        }
    }

    private void b(jyy jyyVar, int i, String str) {
        if (i == 1) {
            jyyVar.d(CommonUtil.a(str, 16));
            LogUtil.a("CommonFileRequestManager", "handleParamTlv file id:", Integer.valueOf(jyyVar.b()));
            return;
        }
        if (i == 2) {
            jyyVar.b(CommonUtil.a(str, 16));
            LogUtil.a("CommonFileRequestManager", "handleParamTlv device wait timeout:", Integer.valueOf(jyyVar.e()));
            return;
        }
        if (i == 3) {
            jyyVar.i(CommonUtil.a(str, 16));
            LogUtil.a("CommonFileRequestManager", "handleConsult unit size:", Integer.valueOf(jyyVar.c()));
        } else if (i == 4) {
            jyyVar.e(CommonUtil.a(str, 16));
            LogUtil.a("CommonFileRequestManager", "handleConsult max apply:", Integer.valueOf(jyyVar.d()));
        } else if (i == 5) {
            jyyVar.d(CommonUtil.w(str) == 1);
            LogUtil.a("CommonFileRequestManager", "handleConsult not need encrypt:", Boolean.valueOf(jyyVar.f()));
        } else {
            LogUtil.c("CommonFileRequestManager", "handleParamTlv default");
        }
    }

    private void i(int i) {
        LogUtil.a("CommonFileRequestManager", "startRequest fileId:", Integer.valueOf(i));
        jys jysVar = this.k.get(Integer.valueOf(i));
        if (jysVar == null) {
            LogUtil.b("CommonFileRequestManager", "startRequest, commonFileInfo is null");
            return;
        }
        try {
            jysVar.e(ByteBuffer.allocate(jysVar.q() - jysVar.k()));
            LogUtil.a("CommonFileRequestManager", "startRequest offset:", Integer.valueOf(jysVar.k()));
            d(i, jysVar.k(), false);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("CommonFileRequestManager", "startRequest occur OutOfMemoryError. file size = ", Integer.valueOf(jysVar.q()), "file name = ", jysVar.n(), "file id = ", Integer.valueOf(jysVar.h()));
        }
    }

    private void a(int i, int i2, boolean z) {
        jyt jytVar = this.l.get(Integer.valueOf(i));
        if (jytVar == null) {
            LogUtil.h("CommonFileRequestManager", "setRetryInfo info is null.");
            return;
        }
        jytVar.d(i2);
        if (z) {
            jytVar.e(-1);
        }
        this.l.put(Integer.valueOf(i), jytVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2, boolean z) {
        LogUtil.a("CommonFileRequestManager", "doRequest fileId:", Integer.valueOf(i), "offset:", Integer.valueOf(i2));
        jys jysVar = this.k.get(Integer.valueOf(i));
        if (jysVar == null) {
            LogUtil.h("CommonFileRequestManager", "doRequest fileId not in list!");
            return;
        }
        jyy jyyVar = this.f.get(Integer.valueOf(i));
        if (jyyVar == null) {
            LogUtil.h("CommonFileRequestManager", "fileTransferParameter is null");
            return;
        }
        jysVar.d(i2);
        jysVar.f(0);
        a(i, i2, !z);
        if (jyyVar.d() <= jysVar.q() - jysVar.k()) {
            LogUtil.a("CommonFileRequestManager", "doRequest request max:", Integer.valueOf(jyyVar.d()));
            jysVar.c(jyyVar.d());
            jysVar.i(jyyVar.a());
            jysVar.a(ByteBuffer.allocate(jyyVar.d()));
            d(i, jysVar.k(), jyyVar.d());
        } else {
            int q = jysVar.q() - jysVar.k();
            LogUtil.a("CommonFileRequestManager", "doRequest request not max:", Integer.valueOf(q));
            if (jyyVar.c() == 0) {
                LogUtil.h("CommonFileRequestManager", "xxx / 0 will error, check this info.");
                return;
            }
            if (q % jyyVar.c() == 0) {
                jysVar.i((q / jyyVar.c()) - 1);
            } else {
                jysVar.i(q / jyyVar.c());
            }
            jysVar.c(q);
            jysVar.a(ByteBuffer.allocate(q));
            d(i, jysVar.k(), jysVar.o());
        }
        if (!z) {
            LogUtil.a("CommonFileRequestManager", "doRequest first!");
            i(i, jyyVar.e());
        }
        e(i, i2, jyyVar.e() / 3);
    }

    private void d(int i, int i2, int i3) {
        if (this.n) {
            LogUtil.a("CommonFileRequestManager", "send 5.44.4 task insert, requestNextTask");
            n();
            return;
        }
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(4) + cvx.b(i2)) + (cvx.e(3) + cvx.e(4) + cvx.b(i3));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(44);
        deviceCommand.setCommandID(4);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        if (this.f.get(Integer.valueOf(i)) != null && this.f.get(Integer.valueOf(i)).f()) {
            deviceCommand.setNeedEncrypt(false);
        }
        LogUtil.a("CommonFileRequestManager", "sendRequestCommand, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        k();
    }

    private void e(byte[] bArr) {
        if (!i(bArr)) {
            LogUtil.h("CommonFileRequestManager", "data is too big, please watch or band check 5.44.5, might lost data.");
            return;
        }
        l();
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 16) {
            Object[] objArr = new Object[3];
            objArr[0] = "handleDeviceDataReceived dataBytes is error, mIsCurrentTaskStop:";
            objArr[1] = Boolean.valueOf(this.n);
            objArr[2] = this.n ? "empty tlv 5.44.5 task insert, requestNextTask" : "";
            LogUtil.h("CommonFileRequestManager", objArr);
            if (this.n) {
                n();
                return;
            }
            return;
        }
        String substring = d.substring(4);
        int a2 = CommonUtil.a(substring.substring(0, 2), 16);
        int a3 = CommonUtil.a(substring.substring(2, 10), 16);
        int a4 = CommonUtil.a(substring.substring(10, 12), 16);
        LogUtil.a("CommonFileRequestManager", "5.44.5 handleDeviceDataReceived fileID:", Integer.valueOf(a2), ", offset:", Integer.valueOf(a3), ", psn:", Integer.valueOf(a4));
        jys jysVar = this.k.get(Integer.valueOf(a2));
        if (jysVar != null) {
            if (jysVar.k() != a3 || jysVar.m() != a4) {
                Object[] objArr2 = new Object[3];
                objArr2[0] = "handleDeviceDataReceived fileOffset or psn error, mIsCurrentTaskStop:";
                objArr2[1] = Boolean.valueOf(this.n);
                objArr2[2] = this.n ? ", receive 5.44.5 task insert, requestNextTask" : "";
                LogUtil.a("CommonFileRequestManager", objArr2);
                if (this.n) {
                    n();
                    return;
                }
                return;
            }
            e(substring, a2, a4, jysVar);
            if (jysVar.q() == 0) {
                LogUtil.h("CommonFileRequestManager", "getFileSize is zero");
                return;
            }
            int q = (a3 * 100) / jysVar.q();
            LogUtil.a("CommonFileRequestManager", "reportProgressForUi progress:", Integer.valueOf(q));
            b(a2, q);
            return;
        }
        Object[] objArr3 = new Object[3];
        objArr3[0] = "handleDeviceDataReceived fileID error, mIsCurrentTaskStop:";
        objArr3[1] = Boolean.valueOf(this.n);
        objArr3[2] = this.n ? ", receive 5.44.5 task insert, requestNextTask" : "";
        LogUtil.h("CommonFileRequestManager", objArr3);
        if (this.n) {
            n();
        }
    }

    private boolean i(byte[] bArr) {
        if (bArr.length < 8) {
            LogUtil.a("CommonFileRequestManager", "length < 8");
            return false;
        }
        jyy jyyVar = this.f.get(Integer.valueOf(bArr[2]));
        if (jyyVar != null) {
            return bArr.length - 8 <= jyyVar.c();
        }
        LogUtil.h("CommonFileRequestManager", "fileTransferParameter is null");
        return true;
    }

    private void e(String str, int i, int i2, jys jysVar) {
        byte[] a2 = cvx.a(str.substring(12));
        if (jysVar.a() != null) {
            if (jysVar.a().capacity() >= jysVar.a().position() + a2.length) {
                jysVar.a().put(a2);
                d(i);
                a(jysVar, a2, i, i2);
                if (i2 < jysVar.r()) {
                    LogUtil.a("CommonFileRequestManager", "handleDeviceDataReceived keep wait");
                    if (this.f.get(Integer.valueOf(i)) != null) {
                        i(i, this.f.get(Integer.valueOf(i)).e());
                        jyt jytVar = this.l.get(Integer.valueOf(i));
                        if (jytVar != null) {
                            e(i, jytVar.b(), this.f.get(Integer.valueOf(i)).e() / 3);
                        }
                    }
                    jysVar.f(i2 + 1);
                    jysVar.d(jysVar.k() + a2.length);
                    return;
                }
                LogUtil.a("CommonFileRequestManager", "handleDeviceDataReceived one unit all received");
                jysVar.a().clear();
                jysVar.e().put(jysVar.a());
                if (jysVar.e().hasRemaining()) {
                    int k = jysVar.k() + a2.length;
                    Object[] objArr = new Object[3];
                    objArr[0] = "mIsCurrentTaskStop:";
                    objArr[1] = Boolean.valueOf(this.n);
                    objArr[2] = this.n ? "handleRequest next, task insert, requestNextTask" : "start request next";
                    LogUtil.a("CommonFileRequestManager", objArr);
                    if (this.n) {
                        e(jysVar, k);
                        n();
                        return;
                    } else {
                        d(i, k, false);
                        return;
                    }
                }
                e(i);
                return;
            }
            LogUtil.h("CommonFileRequestManager", "device return info is error. too big");
            return;
        }
        LogUtil.h("CommonFileRequestManager", "getByteUnit is null");
    }

    private void e(jys jysVar, int i) {
        Iterator<jys> it = this.d.iterator();
        while (it.hasNext()) {
            jys next = it.next();
            if (next.equals(jysVar)) {
                LogUtil.a("CommonFileRequestManager", "saveThisTask save cut point");
                int d = jysVar.d() + 1;
                jysVar.e(d);
                next.e(d);
                next.d(i);
                break;
            }
        }
        try {
            this.f14201a.d(jysVar, false);
        } catch (IOException unused) {
            LogUtil.b("CommonFileRequestManager", "saveBreakPointTask IOException");
        }
    }

    private void e(int i) {
        jys jysVar = this.k.get(Integer.valueOf(i));
        int i2 = 0;
        int i3 = 1;
        if (jysVar == null) {
            Object[] objArr = new Object[3];
            objArr[0] = "handleRequestOver commonFileInfo is null, mIsCurrentTaskStop:";
            objArr[1] = Boolean.valueOf(this.n);
            objArr[2] = this.n ? "handleRequest next, task insert, requestNextTask" : "";
            LogUtil.h("CommonFileRequestManager", objArr);
            if (this.n) {
                n();
                return;
            }
            return;
        }
        try {
            if (jysVar.al()) {
                String d = cvx.d(Sha256.e(jysVar.e().array()));
                LogUtil.a("CommonFileRequestManager", "app hashValue: ", d, ", device hashValue: ", jysVar.p());
                if (TextUtils.equals(d.toUpperCase(Locale.ENGLISH), jysVar.p().toUpperCase(Locale.ENGLISH))) {
                    if (!this.f14201a.d(jysVar, true)) {
                        i2 = 2;
                    }
                    e(jysVar);
                    e(i, 1);
                } else {
                    i(jysVar);
                    e(i, 2);
                    j();
                    c(jysVar, 30005);
                    jysVar.m(i3);
                }
            } else {
                if (!this.f14201a.d(jysVar, true)) {
                    i2 = 2;
                }
                e(jysVar);
                e(i, 1);
            }
            i3 = i2;
            j();
            c(jysVar, 30005);
            jysVar.m(i3);
        } catch (IOException unused) {
            LogUtil.b("CommonFileRequestManager", "handleRequestOver IOException");
        }
    }

    private void e(int i, jys jysVar) throws IOException, RemoteException {
        LogUtil.a("CommonFileRequestManager", "sendSuccessToCallback transferResult is ", Integer.valueOf(i));
        if (i == 0) {
            if (jysVar.t() != null) {
                String d = this.f14201a.d(jysVar.n(), jysVar.h());
                if (d == null) {
                    LogUtil.h("CommonFileRequestManager", "sendSuccessToCallback path is null");
                    jysVar.t().onFailure(30007, "createFileWithByte path is null");
                    return;
                } else {
                    jysVar.t().onSuccess(30000, d, "");
                    return;
                }
            }
            return;
        }
        if ((i & 1) == 1) {
            if (jysVar.t() != null) {
                jysVar.t().onFailure(30001, "");
                return;
            } else {
                LogUtil.h("CommonFileRequestManager", "check failed fileRequestCallback is null");
                return;
            }
        }
        if ((i & 2) == 2) {
            if (jysVar.t() != null) {
                jysVar.t().onFailure(30007, "");
                return;
            } else {
                LogUtil.h("CommonFileRequestManager", "save failed fileRequestCallback is null");
                return;
            }
        }
        LogUtil.h("CommonFileRequestManager", "transferResult is other status.");
    }

    private void b(int i, int i2) {
        jys jysVar = this.k.get(Integer.valueOf(i));
        if (jysVar == null) {
            LogUtil.h("CommonFileRequestManager", "reportProgressForUi commonFileInfo is null");
        } else if (jysVar.t() != null) {
            try {
                jysVar.t().onProgress(i2, "");
            } catch (RemoteException unused) {
                LogUtil.b("CommonFileRequestManager", "reportProgressForUi RemoteException");
            }
        }
    }

    private void e(int i, int i2) {
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(1) + cvx.e(i2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(44);
        deviceCommand.setCommandID(6);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("CommonFileRequestManager", "sendRequestCommand, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        k();
    }

    private void b(byte[] bArr) {
        l();
        String d = cvx.d(bArr);
        blt.d("CommonFileRequestManager", bArr, "5.44.6 handleDeviceStatusReport: ");
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("CommonFileRequestManager", "handleDeviceStatusReport dataBytes is error");
            return;
        }
        try {
            List<cwd> e2 = this.m.a(d.substring(4)).e();
            if (e2 != null && e2.size() > 0) {
                int i = 0;
                int i2 = 0;
                for (cwd cwdVar : e2) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c2 = cwdVar.c();
                    if (a2 == 1) {
                        i = CommonUtil.a(c2, 16);
                        LogUtil.a("CommonFileRequestManager", "handleDeviceStatusReport file_id:", Integer.valueOf(i));
                    } else if (a2 == 127) {
                        i2 = CommonUtil.a(c2, 16);
                        LogUtil.a("CommonFileRequestManager", "handleDeviceStatusReport status:", Integer.valueOf(i2));
                    } else {
                        LogUtil.c("CommonFileRequestManager", "handleDeviceStatusReport default");
                    }
                }
                d(i, i2);
                return;
            }
            LogUtil.h("CommonFileRequestManager", "handleDeviceStatusReport tlv list error");
        } catch (cwg unused) {
            LogUtil.b("CommonFileRequestManager", "handleDeviceStatusReport error");
        }
    }

    private void d(int i, int i2) {
        jys jysVar = this.k.get(Integer.valueOf(i));
        if (jysVar == null) {
            LogUtil.h("CommonFileRequestManager", "reportCancelResult commonFileInfo is null");
            return;
        }
        try {
            Object[] objArr = new Object[1];
            objArr[0] = i2 == 100000 ? "handleCancelReply success" : "handleCancelReply failed";
            LogUtil.a("CommonFileRequestManager", objArr);
            if (i2 == 100000) {
                if (jysVar.c() != null) {
                    jysVar.c().onResponse(30005, "");
                    c(jysVar, 30005);
                }
                d(i);
            } else if (jysVar.c() != null) {
                jysVar.c().onResponse(30006, "");
                c(jysVar, 30006);
            }
        } catch (RemoteException unused) {
            LogUtil.b("CommonFileRequestManager", "handleCancelReply RemoteException");
        }
        d(jysVar);
        try {
            e(jysVar.af(), jysVar);
        } catch (RemoteException | IOException unused2) {
            LogUtil.b("CommonFileRequestManager", "reportCancelResult RemoteException | IOException");
        }
    }

    private void e(jys jysVar) {
        if (!jysVar.ae() || this.i == null) {
            return;
        }
        try {
            jysVar.a(CommonUtil.c(this.h.getFilesDir().getCanonicalPath() + File.separator + "commonFileRequest" + File.separator + jysVar.n()));
        } catch (IOException unused) {
            LogUtil.b("CommonFileRequestManager", "noticeHiWearFileObtain IOException");
        }
        LogUtil.a("CommonFileRequestManager", "noticeHiWearFileObtain isDeviceReport");
        int s = jysVar.s();
        if (s == 1 || s == 10) {
            this.i.d(0, jysVar);
        } else {
            LogUtil.a("CommonFileRequestManager", "noticeHiWearFileObtain not hiwear type");
        }
    }

    private void d(DeviceInfo deviceInfo, byte[] bArr) {
        String d = cvx.d(bArr);
        blt.d("CommonFileRequestManager", bArr, "5.44.7 handleDeviceSendNotice: ");
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("CommonFileRequestManager", "handleDeviceSendNotice dataBytes error");
            return;
        }
        jys c2 = c(d.substring(4));
        if (TextUtils.isEmpty(c2.n()) || c2.n().contains("..")) {
            LogUtil.b("CommonFileRequestManager", "tlv may hacker report to app, file name has .. may inject patch.");
            return;
        }
        f(c2);
        if ((c2.s() == 1 || c2.s() == 10) && (this.i == null || !tnv.d().d(c2))) {
            if (!c2.i().contains("hw.unitedevice.") || (!jsn.c() && !jsn.a(deviceInfo.getDeviceIdentify()))) {
                LogUtil.a("CommonFileRequestManager", "mFileObtainCallback ,cancel:", c2.n());
                b(c2, 1);
                return;
            } else {
                LogUtil.a("CommonFileRequestManager", "device is using uds connect.");
                return;
            }
        }
        if (jsn.c() || jsn.a(deviceInfo.getDeviceIdentify())) {
            d(deviceInfo, c2);
            return;
        }
        if (this.d.size() > 0) {
            if (!c2.ag()) {
                b(c2, 2);
            }
            if (c(c2, System.currentTimeMillis())) {
                h();
                return;
            }
            return;
        }
        this.d.add(c2);
        b(c2, 0);
        d(c2.h(), c2);
    }

    private jys c(String str) {
        jys jysVar = new jys();
        try {
            d(jysVar, this.m.a(str).e());
        } catch (cwg unused) {
            LogUtil.b("CommonFileRequestManager", "handleDeviceSendNotice error");
        }
        jysVar.c(true);
        jysVar.a(true);
        jysVar.c(Priority.DEFAULT);
        return jysVar;
    }

    private void d(DeviceInfo deviceInfo, jys jysVar) {
        snq.c().startReceiveFileFromWear(jyv.b(deviceInfo, jysVar), new ITransferFileCallback.Stub() { // from class: jyp.10
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "ITransferFileCallback onSuccess");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "ITransferFileCallback onFailure");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.a("CommonFileRequestManager", "ITransferFileCallback onProgress");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                if (jyp.this.i != null) {
                    CommonFileInfoParcel commonFileInfoParcel = (CommonFileInfoParcel) new Gson().fromJson(JsonSanitizer.sanitize(str), CommonFileInfoParcel.class);
                    jys jysVar2 = new jys();
                    jysVar2.e(commonFileInfoParcel.getFileName());
                    jysVar2.h(commonFileInfoParcel.getFileType());
                    jysVar2.a(commonFileInfoParcel.getFilePath());
                    jysVar2.i(commonFileInfoParcel.getSourcePackageName());
                    jysVar2.j(commonFileInfoParcel.getSourceCertificate());
                    jysVar2.b(commonFileInfoParcel.getDestinationPackageName());
                    jysVar2.c(commonFileInfoParcel.getDestinationCertificate());
                    jysVar2.d(commonFileInfoParcel.getDescription());
                    jysVar2.f(commonFileInfoParcel.getSha256Result());
                    jyp.this.i.d(i, jysVar2);
                }
            }
        });
    }

    private void f(jys jysVar) {
        jysVar.b(new ITransferSleepAndDFXFileCallback.Stub() { // from class: jyp.9
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) {
                LogUtil.a("CommonFileRequestManager", "handleDeviceSendNotice receive success");
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) {
                LogUtil.a("CommonFileRequestManager", "handleDeviceSendNotice receive fail");
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) {
                LogUtil.c("CommonFileRequestManager", "handleDeviceSendNotice receive onProgress");
            }
        });
    }

    private void d(jys jysVar, List<cwd> list) {
        for (cwd cwdVar : list) {
            int a2 = CommonUtil.a(cwdVar.e(), 16);
            String c2 = cwdVar.c();
            switch (a2) {
                case 1:
                    jysVar.e(cvx.e(c2));
                    break;
                case 2:
                    jysVar.h(CommonUtil.w(c2));
                    break;
                case 3:
                    jysVar.b(CommonUtil.w(c2));
                    break;
                case 4:
                    jysVar.g(CommonUtil.w(c2));
                    break;
                case 5:
                case 6:
                default:
                    LogUtil.a("CommonFileRequestManager", "entry parseDeviceReportTlv2");
                    c(jysVar, a2, c2);
                    break;
                case 7:
                    jysVar.d(cvx.e(c2));
                    break;
                case 8:
                    jysVar.i(cvx.e(c2));
                    break;
                case 9:
                    jysVar.b(cvx.e(c2));
                    break;
                case 10:
                    jysVar.j(cvx.e(c2));
                    break;
                case 11:
                    jysVar.c(cvx.e(c2));
                    break;
            }
        }
    }

    private void c(jys jysVar, int i, String str) {
        if (i == 12) {
            if (CommonUtil.w(str) == 1) {
                jysVar.d(true);
                return;
            }
            return;
        }
        LogUtil.a("CommonFileRequestManager", "5.44.7 handleDeviceSendNotice default");
    }

    private void b(jys jysVar, int i) {
        LogUtil.a("CommonFileRequestManager", "confirmDeviceFileReport,errorCode:", Integer.valueOf(i));
        StringBuilder sb = new StringBuilder(16);
        String c2 = cvx.c(jysVar.n());
        sb.append(cvx.e(1) + cvx.e(c2.length() / 2) + c2);
        sb.append(cvx.e(2) + cvx.e(1) + cvx.e(jysVar.s()));
        sb.append(cvx.e(3) + cvx.e(1) + cvx.e(jysVar.h()));
        sb.append(cvx.e(4) + cvx.e(4) + cvx.b((long) jysVar.q()));
        String c3 = cvx.c(jysVar.ab());
        sb.append(cvx.e(8) + cvx.e(c3.length() / 2) + c3);
        String c4 = cvx.c(jysVar.i());
        sb.append(cvx.e(9) + cvx.e(c4.length() / 2) + c4);
        String c5 = cvx.c(jysVar.y());
        sb.append(cvx.e(10) + cvx.e(c5.length() / 2) + c5);
        String c6 = cvx.c(jysVar.f());
        sb.append(cvx.e(11) + cvx.e(c6.length() / 2) + c6);
        sb.append(cvx.e(13) + cvx.e(1) + cvx.e(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceId(44);
        deviceCommand.setCommandId(7);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setDataLen(cvx.a(sb.toString()).length);
        LogUtil.a("CommonFileRequestManager", "confirmDeviceFileReport, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        a aVar = this.j;
        if (aVar == null || !aVar.hasMessages(i)) {
            return;
        }
        this.j.removeMessages(i);
    }

    private void i(int i, int i2) {
        if (this.j != null) {
            if (i2 != 0) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.arg1 = 100;
                this.j.sendMessageDelayed(obtain, i2 * 1000);
                return;
            }
            return;
        }
        HandlerThread handlerThread = new HandlerThread("CommonFileRequestManager");
        this.g = handlerThread;
        handlerThread.start();
        this.j = new a(this.g.getLooper());
        if (i2 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = i;
            obtain2.arg1 = 100;
            this.j.sendMessageDelayed(obtain2, i2 * 1000);
        }
    }

    private void e(int i, int i2, int i3) {
        if (this.j != null) {
            if (i3 != 0) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.arg1 = 200;
                obtain.arg2 = i2;
                this.j.sendMessageDelayed(obtain, i3 * 1000);
                return;
            }
            return;
        }
        HandlerThread handlerThread = new HandlerThread("CommonFileRequestManager");
        this.g = handlerThread;
        handlerThread.start();
        this.j = new a(this.g.getLooper());
        if (i3 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = i;
            obtain2.arg1 = 200;
            obtain2.arg2 = i2;
            this.j.sendMessageDelayed(obtain2, i3 * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(jys jysVar) {
        if (jysVar == null) {
            LogUtil.h("CommonFileRequestManager", "handleRequestEnd error, file info is null");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.d.size()) {
                break;
            }
            jys jysVar2 = this.d.get(i);
            LogUtil.a("CommonFileRequestManager", "handleRequestEnd has cache file name:", jysVar2.n(), ", type:", Integer.valueOf(jysVar2.s()));
            if ((TextUtils.equals(jysVar2.n(), jysVar.n()) || TextUtils.isEmpty(jysVar2.n())) && jysVar2.s() == jysVar.s()) {
                LogUtil.a("CommonFileRequestManager", "delete commonFileInfo.name:", jysVar2.n());
                this.d.remove(jysVar2);
                n();
                break;
            }
            i++;
        }
        if (this.k.get(Integer.valueOf(jysVar.h())) != null) {
            LogUtil.a("CommonFileRequestManager", "handleRequestEnd has mTransferringFileList file id:", Integer.valueOf(jysVar.h()));
            this.k.remove(Integer.valueOf(jysVar.h()));
        }
        if (this.f.get(Integer.valueOf(jysVar.h())) != null) {
            LogUtil.a("CommonFileRequestManager", "handleRequestEnd has mFileTypeTransferInfos file id:", Integer.valueOf(jysVar.h()));
            this.f.remove(Integer.valueOf(jysVar.h()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        jys jysVar = this.k.get(Integer.valueOf(i));
        if (jysVar == null) {
            LogUtil.h("CommonFileRequestManager", "reportFailedForUi commonFileInfo is null");
            return;
        }
        try {
            if (jysVar.t() != null) {
                jysVar.t().onFailure(i2, "");
                LogUtil.a("CommonFileRequestManager", "reportFailedForUi fileId:", Integer.valueOf(i), "errorCode:", Integer.valueOf(i2));
            }
        } catch (RemoteException unused) {
            LogUtil.b("CommonFileRequestManager", "reportFailedForUi RemoteException");
        }
    }

    /* loaded from: classes5.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.arg1;
            if (i == 100) {
                LogUtil.c("CommonFileRequestManager", "handleMessage wait timeout!");
                int i2 = message.what;
                LogUtil.a("CommonFileRequestManager", "wait timeout! file id:", Integer.valueOf(i2));
                jyp jypVar = jyp.this;
                jypVar.j((jys) jypVar.k.get(Integer.valueOf(i2)));
                jyp.this.a(i2, OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
                jyp jypVar2 = jyp.this;
                jypVar2.d((jys) jypVar2.k.get(Integer.valueOf(i2)));
                jyp.this.d(i2);
                return;
            }
            if (i == 200) {
                LogUtil.c("CommonFileRequestManager", "handleMessage retry");
                int i3 = message.what;
                int i4 = message.arg2;
                LogUtil.a("CommonFileRequestManager", "retry! file id:", Integer.valueOf(i3), ", offset:", Integer.valueOf(i4));
                jyp.this.d(i3, i4, true);
                return;
            }
            if (i == 500) {
                LogUtil.a("CommonFileRequestManager", "kit NO_MANAGER_CALLBACK");
                jyp.this.b(message.what);
                return;
            }
            if (i == 544) {
                Object[] objArr = new Object[3];
                objArr[0] = "start send next, mIsCurrentTaskStop:";
                objArr[1] = Boolean.valueOf(jyp.this.n);
                objArr[2] = jyp.this.n ? "WAIT_MESSAGE, requestNextTask" : "";
                LogUtil.a("CommonFileRequestManager", objArr);
                if (jyp.this.n) {
                    jyp.this.n();
                    return;
                }
                return;
            }
            LogUtil.c("CommonFileRequestManager", "handleMessage default");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        jys jysVar;
        Iterator<jys> it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                jysVar = null;
                break;
            } else {
                jysVar = it.next();
                if (jysVar.s() == i) {
                    break;
                }
            }
        }
        if (jysVar != null) {
            ITransferSleepAndDFXFileCallback t = jysVar.t();
            if (t != null) {
                LogUtil.a("CommonFileRequestManager", "onResponse callback.");
                try {
                    t.onFailure(100001, "5.44.1 time out");
                } catch (RemoteException | NullPointerException unused) {
                    LogUtil.b("CommonFileRequestManager", "reportFileInfoTimeout onFailure exception.");
                }
            }
            c(jysVar, 100009);
            if (this.d.size() > 0) {
                this.d.remove(0);
                n();
                return;
            }
            return;
        }
        LogUtil.h("CommonFileRequestManager", "toKitTimeoutError is null");
    }

    private void a(Map.Entry<Integer, jys> entry) {
        LogUtil.a("CommonFileRequestManager", "toKitReportNoConnectDevice enter.");
        if (entry.getValue().w() == null || !d(entry.getValue().w())) {
            return;
        }
        try {
            LogUtil.a("CommonFileRequestManager", "to kit error code.");
            entry.getValue().w().onResponse(300004, "");
        } catch (RemoteException unused) {
            LogUtil.b("CommonFileRequestManager", "toKitReportNoConnectDevice disconnected RemoteException");
        }
    }

    private boolean d(IBaseCommonCallback iBaseCommonCallback) {
        if (this.d.size() == 0) {
            return false;
        }
        Iterator<jys> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().w() == iBaseCommonCallback) {
                return true;
            }
        }
        return false;
    }

    private void d(jys jysVar, int i) {
        LogUtil.a("CommonFileRequestManager", "5.44.1 toKitFileMessageErrorCode enter.");
        c(jysVar, i);
        if (this.d.size() <= 0 || i == 30003) {
            return;
        }
        this.d.remove(0);
        n();
    }

    private void i(jys jysVar) {
        LogUtil.a("CommonFileRequestManager", "toKitCheckFailure enter.");
        c(jysVar, 30001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(jys jysVar) {
        LogUtil.a("CommonFileRequestManager", "toKitFileTimeout enter");
        d(jysVar, OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
    }

    private void c(jys jysVar, int i) {
        if (jysVar != null && jysVar.w() != null) {
            LogUtil.a("CommonFileRequestManager", "toKitFailureCode enter. errorCode:", Integer.valueOf(i));
            try {
                jysVar.w().onResponse(i, "");
                return;
            } catch (RemoteException unused) {
                LogUtil.b("CommonFileRequestManager", "toKitFailureCode remote exception");
                return;
            } catch (Exception unused2) {
                LogUtil.b("CommonFileRequestManager", "third part has exception, catch this");
                return;
            }
        }
        LogUtil.h("CommonFileRequestManager", "commonFileInfo.getKitCallback() is null. code:", Integer.valueOf(i));
    }

    private void c(jyy jyyVar, jys jysVar) {
        if (jysVar.w() == null) {
            LogUtil.h("CommonFileRequestManager", "toKitFileConsultInfo callback is null");
            return;
        }
        LogUtil.a("CommonFileRequestManager", "enter toKitFileConsultInfo");
        try {
            try {
                jysVar.w().onResponse(10001, c(0, jyyVar.d(), jysVar.q(), jysVar.p().getBytes("UTF-8")));
            } catch (RemoteException unused) {
                LogUtil.b("CommonFileRequestManager", "toKitFileConsultInfo remote exception");
            }
        } catch (UnsupportedEncodingException unused2) {
            LogUtil.b("CommonFileRequestManager", "UnsupportedEncodingException : no support utf-8");
            try {
                jysVar.w().onResponse(10001, "");
            } catch (RemoteException unused3) {
                LogUtil.b("CommonFileRequestManager", "toKitFileConsultInfo remote exception");
            }
        }
    }

    private void a(jys jysVar, byte[] bArr, int i, int i2) {
        if (jysVar.w() == null) {
            LogUtil.h("CommonFileRequestManager", "toKitFrameData callback is null.");
            return;
        }
        jyt jytVar = this.l.get(Integer.valueOf(i));
        if (jytVar == null) {
            LogUtil.h("CommonFileRequestManager", "toKitFrameData retryInfo is null.");
            return;
        }
        if (i2 != jytVar.d() + 1) {
            LogUtil.h("CommonFileRequestManager", "this frame has return to kit, return.");
            return;
        }
        jytVar.e(i2);
        this.l.put(Integer.valueOf(i), jytVar);
        LogUtil.a("CommonFileRequestManager", "toKitFrameData enter");
        jysVar.n(jysVar.aa() + 1);
        String b = b(0, jysVar.aa(), bArr);
        LogUtil.a("CommonFileRequestManager", "toKitFrameData:", iyq.d(b));
        try {
            jysVar.w().onResponse(10002, b);
        } catch (RemoteException unused) {
            LogUtil.b("CommonFileRequestManager", "toKitFrameData remote exception");
        } catch (Exception unused2) {
            LogUtil.b("CommonFileRequestManager", "toKitFrameData occur exception");
        }
    }

    private String b(int i, int i2, byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
            jSONObject.put("index", i2);
            jSONObject.put("value", cvx.d(bArr));
        } catch (JSONException unused) {
            LogUtil.b("CommonFileRequestManager", "toFileTransferInfoJson exception");
        }
        LogUtil.a("CommonFileRequestManager", "toFileTransferInfoJson json:", jSONObject.toString());
        return jSONObject.toString();
    }

    private String c(int i, int i2, int i3, byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
            jSONObject.put("maxTransferUnit", i2);
            jSONObject.put("fileSize", i3);
            jSONObject.put("crc", cvx.d(bArr));
        } catch (JSONException unused) {
            LogUtil.b("CommonFileRequestManager", "toFileConsultInfoJson exception");
        }
        LogUtil.a("CommonFileRequestManager", "toFileConsultInfoJson json is:", jSONObject.toString());
        return jSONObject.toString();
    }

    private void k() {
        if (this.j == null) {
            HandlerThread handlerThread = new HandlerThread("CommonFileRequestManager");
            this.g = handlerThread;
            handlerThread.start();
            this.j = new a(this.g.getLooper());
            Message obtain = Message.obtain();
            obtain.what = 544;
            this.j.sendMessageDelayed(obtain, PreConnectManager.CONNECT_INTERNAL);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 544;
        this.j.sendMessageDelayed(obtain2, PreConnectManager.CONNECT_INTERNAL);
    }

    private void l() {
        a aVar = this.j;
        if (aVar != null) {
            aVar.removeMessages(544);
        }
    }
}

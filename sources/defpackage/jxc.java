package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jxc implements ParserInterface {
    private Context c;
    private jxe d;
    private int f;
    private ThreadPoolManager g;
    private int h;
    private long i;
    private int j;
    private Handler k;
    private List<jxr> l;
    private int m;
    private List<jxo> n;
    private c o;
    private boolean p;
    private int q;
    private boolean r;
    private boolean s;
    private boolean t;
    private int u;
    private long v;
    private List<jxx> w;
    private List<jxw> x;
    private long y;
    private static final Object b = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, List<IBaseResponseCallback>> f14162a = new HashMap(1);

    private jxc() {
        this.n = new ArrayList(1);
        this.x = new ArrayList(1);
        this.w = new ArrayList(1);
        this.l = new ArrayList(10);
        this.k = null;
        this.s = false;
        this.r = false;
        this.t = true;
        this.m = 0;
        this.j = 0;
        this.q = 0;
        this.h = 0;
        this.u = 0;
        this.f = 0;
        this.v = 0L;
        this.i = 0L;
        LogUtil.a("05", 1, "HwBasicAw70Manager", "HwFitnessAw70Manager.");
        this.c = BaseApplication.getContext();
        this.d = jxe.b();
        ThreadPoolManager e2 = ThreadPoolManager.e(5, 5, "HwBasicAw70Manager");
        this.g = e2;
        e2.execute(new Runnable() { // from class: jxc.2
            @Override // java.lang.Runnable
            public void run() {
                jxc.this.d.c();
            }
        });
        this.o = new c(this, BaseApplication.getContext().getMainLooper());
        HandlerThread handlerThread = new HandlerThread("HwBasicAw70Manager");
        handlerThread.start();
        this.k = new e(handlerThread.getLooper());
    }

    private static Map<Integer, List<IBaseResponseCallback>> j() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (jxc.class) {
            map = f14162a;
        }
        return map;
    }

    public boolean d() {
        return this.s;
    }

    public static jxc b() {
        return a.e;
    }

    private void d(byte[] bArr) {
        LogUtil.a("HwBasicAw70Manager", "btDataCallbackResponseSwitchFirst.");
        if (bArr == null || bArr.length < 3) {
            LogUtil.h("HwBasicAw70Manager", "dataInfos is null or length is 3.");
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 3) {
            g(bArr);
            return;
        }
        if (b2 == 10) {
            j(bArr);
        } else if (b2 == 11) {
            i(bArr);
        } else {
            b(bArr);
        }
    }

    private void b(byte[] bArr) {
        LogUtil.a("HwBasicAw70Manager", "btDataCallbackResponseSwitchSecond.");
        byte b2 = bArr[1];
        if (b2 == 31) {
            c(bArr);
            return;
        }
        if (b2 != 32) {
            switch (b2) {
                case 12:
                    h(bArr);
                    break;
                case 13:
                    f(bArr);
                    break;
                case 14:
                    a(bArr, 14);
                    break;
                case 15:
                    a(bArr);
                    break;
                default:
                    LogUtil.h("HwBasicAw70Manager", "btDataCallbackResponseSwitchSecond, default.");
                    break;
            }
            return;
        }
        e(bArr);
    }

    private void b(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (j()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = f14162a.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList<>(1);
                    f14162a.put(Integer.valueOf(i), list);
                }
                list.add(iBaseResponseCallback);
            }
        }
    }

    private void d(int i, int i2, Object obj) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "processCallback callback cmd: ", Integer.valueOf(i), " errorCode: ", Integer.valueOf(i2));
        synchronized (j()) {
            List<IBaseResponseCallback> list = f14162a.get(Integer.valueOf(i));
            int i3 = 0;
            if (list == null) {
                LogUtil.h("HwBasicAw70Manager", "processCallback, callbackList is null.");
                return;
            }
            while (true) {
                if (list.size() <= 0) {
                    break;
                }
                IBaseResponseCallback iBaseResponseCallback = list.get(i3);
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.d(i2, obj);
                    list.remove(i3);
                    break;
                } else {
                    list.remove(i3);
                    i3++;
                }
            }
        }
    }

    private void o() {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "sendDetailSyncSuccessBroadcastToHealth.");
        Intent intent = new Intent("com.huawei.health.fitness_detail_sync_success");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.c.sendBroadcast(intent, LocalBroadcast.c);
        Intent intent2 = new Intent("com.huawei.health.action.AW70_FITNESS_DETAIL_SYNC_SUCCESS_ACTION");
        intent2.setPackage(BaseApplication.getContext().getPackageName());
        this.c.sendBroadcast(intent2);
    }

    private void l() {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "sendTodaySyncSuccessBroadcast.");
        Intent intent = new Intent("com.huawei.bone.action.FITNESS_DATA_TODAY_SYNC");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.c.sendBroadcast(intent, LocalBroadcast.c);
    }

    private void h() {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "saveFitnessDate.");
        boolean checkHiHealthServiceExist = HiHealthManager.d(this.c).checkHiHealthServiceExist();
        LogUtil.a("05", 1, "HwBasicAw70Manager", "saveFitnessDate, isExistHiHealthService: ", Boolean.valueOf(checkHiHealthServiceExist));
        if (checkHiHealthServiceExist) {
            e(5, 120000L);
            this.r = true;
            e();
            this.d.b(this.x, this.w);
            this.x.clear();
            this.w.clear();
            return;
        }
        b(300001);
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("HwBasicAw70Manager", bArr, "getResult(): ");
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("HwBasicAw70Manager", "processResult data is null.");
        } else {
            d(bArr);
        }
    }

    public void e(int i) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "doDetailSyncComplete, errorCode: ", Integer.valueOf(i));
        b(i);
    }

    public void b(int i) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "processDetailSyncComplete errorCode: ", Integer.valueOf(i));
        d(5);
        this.s = false;
        this.r = false;
        this.g.execute(new b(this, i, i()));
        d(true, "processDetailSyncComplete");
    }

    public void a() {
        synchronized (b) {
            LogUtil.a("HwBasicAw70Manager", "mStartTime: ", Long.valueOf(this.v), ", mEndTime: ", Long.valueOf(this.i));
            e();
            jxb.b(this.v, this.i, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, boolean z) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "syncCompleteRun, isNeedSyncWork: ", Boolean.valueOf(z));
        synchronized (e) {
            d(10009, i, null);
            a("false");
            k();
            o();
            if (z) {
                jwx.c();
            }
        }
    }

    static class b implements Runnable {
        private boolean c;
        WeakReference<jxc> d;
        private int e;

        b(jxc jxcVar, int i, boolean z) {
            LogUtil.a("05", 1, "HwBasicAw70Manager", "SyncCompleteRunRunnable, isNeedSyncWork: ", Boolean.valueOf(z));
            this.d = new WeakReference<>(jxcVar);
            this.e = i;
            this.c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            jxc jxcVar = this.d.get();
            if (jxcVar != null) {
                jxcVar.d(this.e, this.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "notifyDetailSyncComplete, errorCode: ", Integer.valueOf(i));
        d(0);
        if (i == 0) {
            h();
            f(100000);
            return;
        }
        this.s = false;
        d(true, "notifyDetailSyncComplete");
        a("false");
        k();
        d(10009, i, null);
    }

    private void c(byte[] bArr) {
        LogUtil.a("HwBasicAw70Manager", "parseIntensiveFrameCount.");
        this.m = 0;
        this.j = 0;
        this.l.clear();
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "parseIntensiveFrameCount Exception : ", ExceptionUtils.d(e2));
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            LogUtil.h("HwBasicAw70Manager", "parseIntensiveFrameCount return errorCode: ", Integer.valueOf(e3));
            b(e3);
            return;
        }
        int e4 = jxh.e(bArr);
        this.m = e4;
        LogUtil.a("HwBasicAw70Manager", "parseIntensiveFrameCount get sample frame count: ", Integer.valueOf(e4));
        int i = this.m;
        if (i <= 0) {
            LogUtil.a("HwBasicAw70Manager", "mIntensiveDataFrameCount: ", Integer.valueOf(i));
            this.d.e();
            b(0);
        } else {
            LogUtil.a("HwBasicAw70Manager", "parseIntensiveFrameCount get sample frame index: ", Integer.valueOf(this.h), "mIntensiveDataFrameCount: ", Integer.valueOf(this.m));
            jxb.d(this.j);
            this.j++;
        }
    }

    private void e(byte[] bArr) {
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "parseIntensiveData Exception : ", ExceptionUtils.d(e2));
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            return;
        }
        this.l.add(jxh.d(bArr));
        int i = this.j;
        if (i < this.m) {
            jxb.d(i);
            this.j++;
        } else {
            this.r = true;
            this.d.b(this.l);
        }
    }

    private void a(byte[] bArr, int i) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "processSetCmdResult Complete command: ", Integer.valueOf(i));
        int i2 = 201000;
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                i2 = jru.e(bArr);
                LogUtil.h("HwBasicAw70Manager", "processSetCmdResult return errorCode: ", Integer.valueOf(i2));
            }
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "processSetCmdResult Exception : ", ExceptionUtils.d(e2));
        }
        d(i, i2, null);
    }

    private void a(byte[] bArr) {
        int i;
        LogUtil.a("HwBasicAw70Manager", "processDeviceDataReport.");
        try {
            i = jro.a(bArr);
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "processDeviceDataReport Exception : ", ExceptionUtils.d(e2));
            i = 0;
        }
        LogUtil.a("HwBasicAw70Manager", "processDeviceDataReport action: ", Integer.valueOf(i));
        if (i == 1) {
            d(new IBaseResponseCallback() { // from class: jxc.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwBasicAw70Manager", "processDeviceDataReport, syncTotal onResponse errorCode: ", Integer.valueOf(i2));
                }
            });
            return;
        }
        if (i == 2) {
            e(new IBaseResponseCallback() { // from class: jxc.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwBasicAw70Manager", "processDeviceDataReport, syncDetail onResponse errorCode: ", Integer.valueOf(i2));
                }
            }, false);
            return;
        }
        if (i == 3) {
            e(new IBaseResponseCallback() { // from class: jxc.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwBasicAw70Manager", "processDeviceDataReport, syncDetail onResponse errorCode: ", Integer.valueOf(i2));
                }
            }, false);
        } else {
            if (i == 8) {
                LogUtil.a("HwBasicAw70Manager", "5.7.15 notify to sync workout data.");
                d(new IBaseResponseCallback() { // from class: jxc.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.a("HwBasicAw70Manager", "processDeviceDataReport, syncTotal onResponse errorCode: ", Integer.valueOf(i2));
                    }
                });
                jwx.c();
                return;
            }
            LogUtil.h("HwBasicAw70Manager", "processDeviceDataReport, nothing to do.");
        }
    }

    private void j(byte[] bArr) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "processGetSamplePointFrameCount.");
        this.q = 0;
        this.h = 0;
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "processGetSamplePointFrameCount Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            f(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.h("HwBasicAw70Manager", "processGetSamplePointFrameCount, return errorCode: ", Integer.valueOf(e3));
            b(300007);
            f(e3);
            return;
        }
        int f = jxh.f(bArr);
        this.q = f;
        ReleaseLogUtil.e("Step_HwBasicAw70Manager", "aw70 7-10 count : ", Integer.valueOf(f));
        if (this.q > 0) {
            LogUtil.a("05", 1, "HwBasicAw70Manager", "processGetSamplePointFrameCount, get sample frame index: ", Integer.valueOf(this.h));
            jxb.b(this.h);
            this.h++;
            return;
        }
        m();
    }

    private void i(byte[] bArr) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "processGetSamplePointFrame.");
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "processGetSamplePointFrame Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            f(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.a("HwBasicAw70Manager", "processGetSamplePointFrame, return errorCode: ", Integer.valueOf(e3));
            b(300007);
            f(e3);
            return;
        }
        this.x.add(jxh.j(bArr));
        int i = this.h;
        if (i < this.q) {
            jxb.b(i);
            this.h++;
        } else {
            m();
        }
    }

    private void m() {
        LogUtil.a("HwBasicAw70Manager", "syncStatusPoint.");
        long d2 = jww.d();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis - d2 > k.b.l || d2 == 0) {
            d2 = a(currentTimeMillis - k.b.l);
            jww.d(d2);
        } else if (d2 >= currentTimeMillis && d2 - currentTimeMillis <= 300) {
            LogUtil.a("HwBasicAw70Manager", "syncStatusPoint, lastStatusTime is not correct.");
            d2 = currentTimeMillis - 61;
        } else if (d2 - currentTimeMillis > 300) {
            LogUtil.a("HwBasicAw70Manager", "syncStatusPoint, lastStatusTime is not correct and need write back.");
            d2 = currentTimeMillis - 61;
            jww.d(d2);
        } else {
            LogUtil.h("HwBasicAw70Manager", "syncStatusPoint, nothing to do.");
        }
        jxb.e(d2, currentTimeMillis);
    }

    private void h(byte[] bArr) {
        LogUtil.a("HwBasicAw70Manager", "processGetStatusFrameCount.");
        this.u = 0;
        this.f = 0;
        try {
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "processGetStatusFrameCount Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            f(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.a("HwBasicAw70Manager", "processGetStatusFrameCount, return errorCode: ", Integer.valueOf(e3));
            b(300007);
            f(e3);
            return;
        }
        int h = jxh.h(bArr);
        this.u = h;
        ReleaseLogUtil.e("Step_HwBasicAw70Manager", "aw70 7-12 count : ", Integer.valueOf(h));
        if (this.u > 0) {
            jxb.c(this.f);
            this.f++;
        } else {
            a(0);
        }
    }

    private void f(byte[] bArr) {
        int i;
        LogUtil.a("HwBasicAw70Manager", "processGetStatusFrame.");
        try {
            i = 0;
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "processGetStatusFrame Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            f(-1);
            i = 201000;
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.a("HwBasicAw70Manager", "processGetStatusFrame, return errorCode: ", Integer.valueOf(e3));
            b(300007);
            f(e3);
            return;
        }
        this.w.add(jxh.i(bArr));
        int i2 = this.f;
        if (i2 < this.u) {
            jxb.c(i2);
            this.f++;
        } else {
            a(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "doSyncTodayComplete, errorCode: ", Integer.valueOf(i));
        d(10008, i, null);
        l();
    }

    private void g(byte[] bArr) {
        int i;
        LogUtil.a("05", 1, "HwBasicAw70Manager", "processGetTodayFitnessData.");
        d(4);
        int i2 = 201000;
        try {
            i = 0;
        } catch (cwg e2) {
            LogUtil.b("HwBasicAw70Manager", "processGetTodayFitnessData Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            f(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            i2 = jru.e(bArr);
            iyv.b(i2);
            LogUtil.a("HwBasicAw70Manager", "processGetTodayFitnessData, return errorCode:", Integer.valueOf(i2));
            f(i2);
            i = i2;
            c(i);
        }
        this.d.e(a.e, jxh.c(bArr));
        c(i);
    }

    private void e(int i, long j2) {
        c cVar = this.o;
        if (cVar != null) {
            cVar.sendEmptyMessageDelayed(i, j2);
        } else {
            LogUtil.h("HwBasicAw70Manager", "fitnessMgrSendMsgDelay, mHwFitnessMgrHandler is null.");
        }
    }

    private void d(int i) {
        c cVar = this.o;
        if (cVar == null) {
            LogUtil.h("HwBasicAw70Manager", "fitnessMgrRemoveMsg, mHwFitnessMgrHandler is null.");
        } else if (cVar.hasMessages(i)) {
            this.o.removeMessages(i);
        }
    }

    static class c extends Handler {
        WeakReference<jxc> c;

        c(jxc jxcVar, Looper looper) {
            super(looper);
            this.c = new WeakReference<>(jxcVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            jxc jxcVar = this.c.get();
            if (jxcVar == null) {
                LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, hwFitnessAw70Mgr is null.");
                return;
            }
            LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, handleMessage msg: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, sync detail timeout.");
                jxcVar.a(300001);
                jxcVar.f(0);
                return;
            }
            if (i == 1) {
                LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, get frame count timeout.");
                return;
            }
            if (i == 2) {
                LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, get frame timeout.");
                return;
            }
            if (i == 3) {
                LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, Sync Complete msg.");
                jxcVar.b(0);
            } else if (i == 4) {
                LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, Sync today timeout msg.");
                jxcVar.c(300001);
                jxcVar.f(4);
            } else if (i == 5) {
                LogUtil.a("HwBasicAw70Manager", "HwFitnessMgrHandler, Save fitness data timeout msg.");
                jxcVar.b(300001);
            } else {
                LogUtil.h("HwBasicAw70Manager", "HwFitnessMgrHandler, unknown msg type.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            DeviceInfo d2 = jxi.d("HwBasicAw70Manager");
            if (d2 != null && d2.getDeviceConnectState() == 2) {
                e(4, 40000L);
                b(10008, iBaseResponseCallback);
                LogUtil.a("HwBasicAw70Manager", "syncFitnessTodayDataRun, enter thread.");
                jxb.b();
                return;
            }
            LogUtil.h("HwBasicAw70Manager", "syncFitnessTodayDataRun, get device info error.");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(300004, null);
            }
        }
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        this.g.execute(new j(this, iBaseResponseCallback));
    }

    static class j implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<jxc> f14164a;
        IBaseResponseCallback d;

        j(jxc jxcVar, IBaseResponseCallback iBaseResponseCallback) {
            this.f14164a = new WeakReference<>(jxcVar);
            this.d = iBaseResponseCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            jxc jxcVar = this.f14164a.get();
            if (jxcVar != null) {
                jxcVar.b(this.d);
            }
        }
    }

    public void e() {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "registerDeviceToHiHealth.");
        final DeviceInfo d2 = jxi.d("HwBasicAw70Manager");
        if (d2 != null) {
            this.g.execute(new Runnable() { // from class: jxc.9
                @Override // java.lang.Runnable
                public void run() {
                    keg.d(d2);
                }
            });
        } else {
            LogUtil.h("HwBasicAw70Manager", "registerDeviceToHiHealth, deviceInfo is null.");
        }
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwBasicAw70Manager", "handleDeviceConnection, deviceInfo is null.");
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.a("HwBasicAw70Manager", "handleDeviceConnection, device Connect state: ", Integer.valueOf(deviceConnectState));
        if (deviceConnectState == 2) {
            LogUtil.a("HwBasicAw70Manager", "handleDeviceConnection.");
            jxi.e(deviceInfo);
            jqi.a().getSwitchSetting("custom.activity_reminder", new IBaseResponseCallback() { // from class: jxc.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    Message obtainMessage = jxc.this.k.obtainMessage();
                    obtainMessage.obj = obj;
                    obtainMessage.what = 10001;
                    jxc.this.k.sendMessage(obtainMessage);
                }
            });
            return;
        }
        if (deviceConnectState == 3) {
            synchronized (j()) {
                f14162a.clear();
            }
            if (!this.r) {
                LogUtil.a("HwBasicAw70Manager", "handleDeviceConnection, Data sync bt bt disconnect.");
                this.s = false;
                a("false");
                k();
                b(300004);
                Intent intent = new Intent("com.huawei.health.fitness_detail_sync_fail");
                intent.setPackage(BaseApplication.getContext().getPackageName());
                this.c.sendBroadcast(intent, LocalBroadcast.c);
            }
            f(300004);
            return;
        }
        LogUtil.h("HwBasicAw70Manager", "handleDeviceConnection, nothing to do.");
    }

    private long a(long j2) {
        String format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(new Date(j2 * 1000));
        try {
            return new SimpleDateFormat("yyyyMMddhhmm", Locale.ENGLISH).parse(format + AgdConstant.INSTALL_TYPE_DEFAULT).getTime() / 1000;
        } catch (ParseException e2) {
            LogUtil.b("HwBasicAw70Manager", "getBeginOfDate Exception : ", ExceptionUtils.d(e2));
            return j2;
        }
    }

    private void g() {
        this.n.clear();
        this.x.clear();
        this.w.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        synchronized (b) {
            LogUtil.a("05", 1, "HwBasicAw70Manager", "syncFitnessDetailDataRun, thread isNeedSyncWork: ", Boolean.valueOf(z));
            DeviceInfo d2 = jxi.d("HwBasicAw70Manager");
            if (d2 != null && d2.getDeviceConnectState() == 2) {
                if (this.s) {
                    LogUtil.a("05", 1, "HwBasicAw70Manager", "syncFitnessDetailData, data syncing.");
                    iBaseResponseCallback.d(300002, null);
                    return;
                }
                this.s = true;
                d(z, "syncFitnessDetailDataRun");
                a("true");
                e(0, 240000L);
                b(10009, iBaseResponseCallback);
                g();
                this.v = f();
                long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                this.i = currentTimeMillis;
                long j2 = this.v;
                if (currentTimeMillis - j2 <= k.b.l && j2 != 0) {
                    if (j2 >= currentTimeMillis && j2 - currentTimeMillis <= 300) {
                        LogUtil.a("05", 1, "HwBasicAw70Manager", "syncFitnessDetailData, lastSync time is not correct.");
                        this.v = this.i - 61;
                    } else if (j2 - currentTimeMillis > 300) {
                        LogUtil.a("05", 1, "HwBasicAw70Manager", "syncFitnessDetailData, lastSync time is not correct and need write back.");
                        long j3 = this.i - 61;
                        this.v = j3;
                        d(j3);
                    } else {
                        LogUtil.h("05", 1, "HwBasicAw70Manager", "syncFitnessDetailDataRun, nothing to do.");
                    }
                    jxb.c(this.v, this.i);
                    return;
                }
                long a2 = a(currentTimeMillis - k.b.l);
                this.v = a2;
                d(a2);
                jxb.c(this.v, this.i);
                return;
            }
            LogUtil.h("05", 1, "HwBasicAw70Manager", "syncFitnessDetailDataRun, get device info error.");
            iBaseResponseCallback.d(300004, null);
        }
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback, final boolean z) {
        Log.i("HwBasicAw70Manager", "syncFitnessDetailData.");
        this.y = System.currentTimeMillis();
        this.p = z;
        d(new IBaseResponseCallback() { // from class: jxc.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("05", 1, "HwBasicAw70Manager", "syncFitnessDetailData, today total finish need detail errorCode: ", Integer.valueOf(i));
                jxc jxcVar = jxc.this;
                if (jxcVar.e(jxcVar.y, jxc.this.f(), z)) {
                    jxc.this.s = false;
                } else {
                    jxc.this.g.execute(new d(jxc.this, iBaseResponseCallback, true));
                }
            }
        });
    }

    public boolean e(long j2, long j3, boolean z) {
        long j4 = j3 * 1000;
        LogUtil.a("HwBasicAw70Manager", "syncFitnessDetailData startTime:", Long.valueOf(j2), " getLastSyncTime:", Long.valueOf(j4), " isManual:", Boolean.valueOf(z));
        return z && j2 > j4 && j2 - j4 < 60000;
    }

    static class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        IBaseResponseCallback f14163a;
        WeakReference<jxc> d;
        boolean e;

        d(jxc jxcVar, IBaseResponseCallback iBaseResponseCallback, boolean z) {
            LogUtil.a("05", 1, "HwBasicAw70Manager", " SyncFitnessDetailDataRunnable: ", Boolean.valueOf(z));
            this.d = new WeakReference<>(jxcVar);
            this.f14163a = iBaseResponseCallback;
            this.e = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            jxc jxcVar = this.d.get();
            if (jxcVar != null) {
                jxcVar.b(this.f14163a, this.e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long f() {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "getLastSyncTime.");
        return new jxa().a();
    }

    public void d(long j2) {
        LogUtil.a("05", 1, "HwBasicAw70Manager", "setLastSyncTime time: ", Long.valueOf(j2));
        jxa jxaVar = new jxa();
        jxq jxqVar = new jxq();
        jxqVar.a(j2);
        jxaVar.a(jxqVar);
    }

    public static String c() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        return accountInfo == null ? "" : accountInfo;
    }

    private void a(String str) {
        SharedPreferenceManager.e(this.c, String.valueOf(10008), "KEY_SYNCHRONIZING_DATA_FLAG", str, (StorageParams) null);
    }

    private void k() {
        LogUtil.a("HwBasicAw70Manager", "sendDialogDismissBroadcast.");
        Intent intent = new Intent("com.huawei.bone.action.ACTION_DIALOG_DISMISS");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.c.sendBroadcast(intent, LocalBroadcast.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(final int i) {
        LogUtil.a("HwBasicAw70Manager", "sendAw70FitnessSyncEvent, result: ", Integer.valueOf(i));
        ThreadPoolManager.d().execute(new Runnable() { // from class: jxc.10
            @Override // java.lang.Runnable
            public void run() {
                jra.b(i, jxc.this.p, jxc.this.y, jxi.d("HwBasicAw70Manager"));
            }
        });
    }

    private boolean i() {
        return this.t;
    }

    private void d(boolean z, String str) {
        this.t = z;
        LogUtil.a("HwBasicAw70Manager", "setNeedWorkout, mIsNeedWorkout: ", Boolean.valueOf(z), ", from: ", str);
    }

    class e extends Handler {
        e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("HwBasicAw70Manager", "MyHandle, message is null.");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                LogUtil.a("HwBasicAw70Manager", " handleMessage, BasicCommandId.COMMAND_FITNESS_SET_MOTION_GOAL.");
            } else if (i == 10001) {
                LogUtil.a("HwBasicAw70Manager", " handleMessage, BasicCommandId.GET_FITNESS_GET_ACTIVITY_REMINDER.");
                jxc.this.n();
            } else {
                LogUtil.h("HwBasicAw70Manager", "MyHandle, nothing to do.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("HwBasicAw70Manager", "setDefaultDeviceReportThreshold.");
        DeviceInfo d2 = jxi.d("HwBasicAw70Manager");
        if (d2 == null) {
            LogUtil.h("HwBasicAw70Manager", "setDefaultDeviceReportThreshold, deviceInfo is null.");
            return;
        }
        DeviceCapability c2 = jxi.c(d2.getDeviceIdentify());
        if (c2 == null) {
            LogUtil.h("HwBasicAw70Manager", "setDefaultDeviceReportThreshold, deviceCapability is null.");
        } else if (!c2.isSupportThreshold()) {
            LogUtil.h("HwBasicAw70Manager", "setDefaultDeviceReportThreshold, is not support.");
        } else {
            jxb.a(jqe.d(1));
        }
    }

    static class a {
        private static final jxc e = new jxc();
    }
}

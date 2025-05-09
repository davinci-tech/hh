package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.FitnessUserInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.ActivityReminder;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.up.model.UserInfomation;
import defpackage.jik;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
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
public class jik implements BluetoothDataReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private static jik f13866a;
    private long ab;
    private Context f;
    private Handler k;
    private ThreadPoolManager l;
    private jip n;
    private a r;
    private jfq s;
    private boolean w;
    private static final Object d = new Object();
    private static final Object e = new Object();
    private static final Object c = new Object();
    private static Map<Integer, List<IBaseResponseCallback>> b = new HashMap(1);
    private List<jhv> t = new ArrayList(1);
    private List<jic> ac = new ArrayList(1);
    private List<jif> aa = new ArrayList(1);
    private List<jhy> q = new ArrayList(10);
    private boolean u = false;
    private boolean y = false;
    private boolean v = true;
    private int p = 0;
    private int m = 0;
    private int x = 0;
    private int g = 0;
    private int ad = 0;
    private int h = 0;
    private long z = 0;
    private long o = 0;
    private IBaseResponseCallback i = new AnonymousClass1();
    private IBaseResponseCallback j = new IBaseResponseCallback() { // from class: jik.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("HwFitnessAw70Manager", "mCallback, errorCode: ", Integer.valueOf(i), ", reason: ", obj);
            Intent intent = new Intent("com.huawei.phoneservice.sync_workout_broadcast_action");
            intent.setPackage(BaseApplication.getContext().getPackageName());
            jik.this.f.sendBroadcast(intent, LocalBroadcast.c);
        }
    };
    private IBaseResponseCallback ae = new IBaseResponseCallback() { // from class: jik.7
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("HwFitnessAw70Manager", "mWorkoutCallbackConnect, errorCode: ", Integer.valueOf(i), ", reason:", obj);
            Intent intent = new Intent("com.huawei.bone.action.FITNESS_DATA_DETAIL_SYNC");
            intent.setPackage(BaseApplication.getContext().getPackageName());
            jik.this.f.sendBroadcast(intent, LocalBroadcast.c);
        }
    };

    public boolean e() {
        return false;
    }

    /* renamed from: jik$1, reason: invalid class name */
    class AnonymousClass1 implements IBaseResponseCallback {
        AnonymousClass1() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj instanceof FitnessUserInfo) {
                long time = ((FitnessUserInfo) obj).getTime();
                LogUtil.a("HwFitnessAw70Manager", "mBaseResponseCallback enter");
                final long j = time * 1000;
                LoginInit.getInstance(jik.this.f).getUserInfoFromDb(new IBaseResponseCallback() { // from class: jij
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj2) {
                        jik.AnonymousClass1.this.e(j, i2, obj2);
                    }
                });
            }
        }

        /* synthetic */ void e(long j, int i, Object obj) {
            if (i == 0 && (obj instanceof UserInfomation)) {
                UserInfomation userInfomation = (UserInfomation) obj;
                LogUtil.a("HwFitnessAw70Manager", "mBaseResponseCallback, SetTime: ", Long.valueOf(userInfomation.getSetTime()), " deviceTime: ", Long.valueOf(j));
                if (userInfomation.getSetTime() > j) {
                    jik.this.e(userInfomation);
                    return;
                } else {
                    LogUtil.a("HwFitnessAw70Manager", "mBaseResponseCallback others");
                    return;
                }
            }
            LogUtil.a("HwFitnessAw70Manager", "mBaseResponseCallback userInfo is null");
        }
    }

    private jik(Context context) {
        this.k = null;
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "HwFitnessAw70Manager.");
        this.f = context;
        jfq c2 = jfq.c();
        this.s = c2;
        if (c2 == null) {
            LogUtil.h("05", 1, "HwFitnessAw70Manager", "mHwDeviceConfigManager is null.");
            return;
        }
        c2.e(100007, this);
        this.n = jip.a();
        ThreadPoolManager e2 = ThreadPoolManager.e(5, 5, "HwFitnessAw70Manager");
        this.l = e2;
        e2.execute(new Runnable() { // from class: jik.10
            @Override // java.lang.Runnable
            public void run() {
                jik.this.n.d();
            }
        });
        k();
        this.r = new a(this, BaseApplication.getContext().getMainLooper());
        HandlerThread handlerThread = new HandlerThread("HWFitnessMgr");
        handlerThread.start();
        this.k = new e(handlerThread.getLooper());
    }

    private static Map<Integer, List<IBaseResponseCallback>> i() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (jik.class) {
            map = b;
        }
        return map;
    }

    public boolean d() {
        return this.u;
    }

    public static jik d(Context context) {
        jik jikVar;
        synchronized (e) {
            if (f13866a == null) {
                f13866a = new jik(context);
            }
            jikVar = f13866a;
        }
        return jikVar;
    }

    private void d(byte[] bArr) {
        LogUtil.a("HwFitnessAw70Manager", "btDataCallbackResponseSwitchFirst.");
        if (bArr == null || bArr.length < 3) {
            LogUtil.h("HwFitnessAw70Manager", "dataInfos is null or length is 3.");
            return;
        }
        switch (bArr[1]) {
            case 1:
                b(bArr, 1);
                break;
            case 2:
            case 9:
                b(bArr, 10002);
                break;
            case 3:
                m(bArr);
                break;
            case 4:
            case 5:
            case 8:
            default:
                e(bArr);
                break;
            case 6:
                n();
                break;
            case 7:
                b(bArr, 7);
                break;
            case 10:
                g(bArr);
                break;
            case 11:
                i(bArr);
                break;
        }
    }

    private void e(byte[] bArr) {
        LogUtil.a("HwFitnessAw70Manager", "btDataCallbackResponseSwitchSecond.");
        byte b2 = bArr[1];
        if (b2 == 21) {
            h(bArr);
            return;
        }
        if (b2 == 31) {
            c(bArr);
            return;
        }
        if (b2 != 32) {
            switch (b2) {
                case 12:
                    f(bArr);
                    break;
                case 13:
                    j(bArr);
                    break;
                case 14:
                    b(bArr, 14);
                    break;
                case 15:
                    b(bArr);
                    break;
                case 16:
                    b(bArr, 16);
                    break;
                default:
                    LogUtil.h("HwFitnessAw70Manager", "btDataCallbackResponseSwitchSecond, default.");
                    break;
            }
            return;
        }
        a(bArr);
    }

    private void a(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (i()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = b.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList<>(1);
                    b.put(Integer.valueOf(i), list);
                }
                list.add(iBaseResponseCallback);
            }
        }
    }

    private void b(int i, int i2, Object obj) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processCallback callback cmd: ", Integer.valueOf(i), " errorCode: ", Integer.valueOf(i2));
        synchronized (i()) {
            List<IBaseResponseCallback> list = b.get(Integer.valueOf(i));
            int i3 = 0;
            if (list == null) {
                LogUtil.h("HwFitnessAw70Manager", "processCallback, callbackList is null.");
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

    private void s() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "sendDetailSyncSuccessBroadcastToHealth.");
        Intent intent = new Intent("com.huawei.health.fitness_detail_sync_success");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.f.sendBroadcast(intent, LocalBroadcast.c);
        Intent intent2 = new Intent("com.huawei.health.action.AW70_FITNESS_DETAIL_SYNC_SUCCESS_ACTION");
        intent2.setPackage(BaseApplication.getContext().getPackageName());
        this.f.sendBroadcast(intent2);
    }

    private void q() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "sendTodaySyncSuccessBroadcast.");
        Intent intent = new Intent("com.huawei.bone.action.FITNESS_DATA_TODAY_SYNC");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.f.sendBroadcast(intent, LocalBroadcast.c);
    }

    private void m() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "saveFitnessDate.");
        boolean checkHiHealthServiceExist = HiHealthManager.d(this.f).checkHiHealthServiceExist();
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "saveFitnessDate, isExistHiHealthService: ", Boolean.valueOf(checkHiHealthServiceExist));
        if (checkHiHealthServiceExist) {
            a(5, 120000L);
            this.y = true;
            a();
            this.n.c(this.ac, this.aa);
            this.ac.clear();
            this.aa.clear();
            return;
        }
        a(300001);
    }

    public void c() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "setDeviceFitnessGoal.");
        nit.b(new MotionGoal(), new IBaseResponseCallback() { // from class: jik.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                jik.this.l.execute(new d(i, obj));
            }
        });
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (i != 0) {
            LogUtil.h("HwFitnessAw70Manager", "mBluetoothDataCallback, onDataReceived errorCode: ", Integer.valueOf(i));
        } else {
            LogUtil.a("HwFitnessAw70Manager", "mBluetoothDataCallback, onDataReceived dataInfos: ", cvx.d(bArr));
            d(bArr);
        }
    }

    static class d implements Runnable {
        private int b;
        private Object e;

        d(int i, Object obj) {
            this.b = i;
            this.e = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a("HwFitnessAw70Manager", "SetDeviceFitnessGoalResponseRunnable, onResponse object: ", this.e);
            if (this.b == 0) {
                Object obj = this.e;
                if (obj instanceof MotionGoal) {
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add((MotionGoal) obj);
                    jin.c(arrayList);
                }
            }
        }
    }

    public void b(int i) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "doDetailSyncComplete, errorCode: ", Integer.valueOf(i));
        a(i);
    }

    public void a(int i) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processDetailSyncComplete errorCode: ", Integer.valueOf(i));
        d(5);
        this.u = false;
        this.y = false;
        this.l.execute(new c(this, i, l()));
        b(true, "processDetailSyncComplete");
    }

    public void j() {
        synchronized (d) {
            LogUtil.a("HwFitnessAw70Manager", "mStartTime: ", Long.valueOf(this.z), ", mEndTime: ", Long.valueOf(this.o));
            a();
            jin.d(this.z, this.o, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, boolean z) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "syncCompleteRun, isNeedSyncWork: ", Boolean.valueOf(z));
        synchronized (c) {
            b(10009, i, (Object) null);
            e("false");
            t();
            s();
            if (z) {
                this.s.d(this.ae);
            }
        }
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<jik> f13874a;
        private boolean b;
        private int e;

        c(jik jikVar, int i, boolean z) {
            LogUtil.a("05", 1, "HwFitnessAw70Manager", "SyncCompleteRunRunnable, isNeedSyncWork: ", Boolean.valueOf(z));
            this.f13874a = new WeakReference<>(jikVar);
            this.e = i;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            jik jikVar = this.f13874a.get();
            if (jikVar != null) {
                jikVar.d(this.e, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "notifyDetailSyncComplete, errorCode: ", Integer.valueOf(i));
        d(0);
        if (i == 0) {
            m();
            j(100000);
            return;
        }
        this.u = false;
        b(true, "notifyDetailSyncComplete");
        e("false");
        t();
        b(10009, i, (Object) null);
    }

    private void c(byte[] bArr) {
        LogUtil.a("HwFitnessAw70Manager", "parseIntensiveFrameCount.");
        this.p = 0;
        this.m = 0;
        this.q.clear();
        try {
        } catch (cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "parseIntensiveFrameCount Exception : ", ExceptionUtils.d(e2));
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            LogUtil.h("HwFitnessAw70Manager", "parseIntensiveFrameCount return errorCode: ", Integer.valueOf(e3));
            a(e3);
            return;
        }
        int c2 = jhq.c(bArr);
        this.p = c2;
        LogUtil.a("HwFitnessAw70Manager", "parseIntensiveFrameCount get sample frame count: ", Integer.valueOf(c2));
        int i = this.p;
        if (i <= 0) {
            LogUtil.a("HwFitnessAw70Manager", "mIntensiveDataFrameCount: ", Integer.valueOf(i));
            this.n.b();
            a(0);
        } else {
            LogUtil.a("HwFitnessAw70Manager", "parseIntensiveFrameCount get sample frame index: ", Integer.valueOf(this.g), "mIntensiveDataFrameCount: ", Integer.valueOf(this.p));
            jin.a(this.m);
            this.m++;
        }
    }

    private void a(byte[] bArr) {
        try {
        } catch (cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "parseIntensiveData Exception : ", ExceptionUtils.d(e2));
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            return;
        }
        this.q.add(jhq.e(bArr));
        int i = this.m;
        if (i < this.p) {
            jin.a(i);
            this.m++;
        } else {
            this.y = true;
            this.n.a(this.q);
        }
    }

    private void b(byte[] bArr, int i) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processSetCmdResult Complete command: ", Integer.valueOf(i));
        int i2 = 201000;
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                i2 = jru.e(bArr);
                LogUtil.h("HwFitnessAw70Manager", "processSetCmdResult return errorCode: ", Integer.valueOf(i2));
            }
        } catch (cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "processSetCmdResult Exception : ", ExceptionUtils.d(e2));
        }
        b(i, i2, (Object) null);
    }

    private void b(byte[] bArr) {
        int i;
        LogUtil.a("HwFitnessAw70Manager", "processDeviceDataReport.");
        try {
            i = jro.a(bArr);
        } catch (cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "processDeviceDataReport Exception : ", ExceptionUtils.d(e2));
            i = 0;
        }
        LogUtil.a("HwFitnessAw70Manager", "processDeviceDataReport action: ", Integer.valueOf(i));
        if (i == 1) {
            a(new IBaseResponseCallback() { // from class: jik.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwFitnessAw70Manager", "processDeviceDataReport, syncTotal onResponse errorCode: ", Integer.valueOf(i2));
                }
            });
            return;
        }
        if (i == 2) {
            c(new IBaseResponseCallback() { // from class: jik.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwFitnessAw70Manager", "processDeviceDataReport, syncDetail onResponse errorCode: ", Integer.valueOf(i2));
                }
            }, false);
            return;
        }
        if (i == 3) {
            c(new IBaseResponseCallback() { // from class: jik.12
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwFitnessAw70Manager", "processDeviceDataReport, syncDetail onResponse errorCode: ", Integer.valueOf(i2));
                }
            }, false);
        } else if (i == 8) {
            LogUtil.a("HwFitnessAw70Manager", "5.7.15 notify to sync workout data.");
            o();
        } else {
            LogUtil.h("HwFitnessAw70Manager", "processDeviceDataReport, nothing to do.");
        }
    }

    private void g(byte[] bArr) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processGetSamplePointFrameCount.");
        this.x = 0;
        this.g = 0;
        try {
        } catch (bmk | cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "processGetSamplePointFrameCount Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            j(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.h("HwFitnessAw70Manager", "processGetSamplePointFrameCount, return errorCode: ", Integer.valueOf(e3));
            a(300007);
            j(e3);
            return;
        }
        int j = jhq.j(bArr);
        this.x = j;
        ReleaseLogUtil.e("Step_HwFitnessManager", "aw70 7-10 count : ", Integer.valueOf(j));
        if (this.x > 0) {
            LogUtil.a("05", 1, "HwFitnessAw70Manager", "processGetSamplePointFrameCount, get sample frame index: ", Integer.valueOf(this.g));
            jin.d(this.g);
            this.g++;
            return;
        }
        p();
    }

    private void i(byte[] bArr) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processGetSamplePointFrame.");
        try {
        } catch (bmk | cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "processGetSamplePointFrame Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            j(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.a("HwFitnessAw70Manager", "processGetSamplePointFrame, return errorCode: ", Integer.valueOf(e3));
            a(300007);
            j(e3);
            return;
        }
        this.ac.add(jhq.h(bArr));
        int i = this.g;
        if (i < this.x) {
            jin.d(i);
            this.g++;
        } else {
            p();
        }
    }

    private void p() {
        LogUtil.a("HwFitnessAw70Manager", "syncStatusPoint.");
        long b2 = jio.b();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis - b2 > k.b.l || b2 == 0) {
            b2 = b(currentTimeMillis - k.b.l);
            jio.a(b2);
        } else if (b2 >= currentTimeMillis && b2 - currentTimeMillis <= 300) {
            LogUtil.a("HwFitnessAw70Manager", "syncStatusPoint, lastStatusTime is not correct.");
            b2 = currentTimeMillis - 61;
        } else if (b2 - currentTimeMillis > 300) {
            LogUtil.a("HwFitnessAw70Manager", "syncStatusPoint, lastStatusTime is not correct and need write back.");
            b2 = currentTimeMillis - 61;
            jio.a(b2);
        } else {
            LogUtil.h("HwFitnessAw70Manager", "syncStatusPoint, nothing to do.");
        }
        jin.c(b2, currentTimeMillis);
    }

    private void f(byte[] bArr) {
        LogUtil.a("HwFitnessAw70Manager", "processGetStatusFrameCount.");
        this.ad = 0;
        this.h = 0;
        try {
        } catch (bmk | cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "processGetStatusFrameCount Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            j(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.a("HwFitnessAw70Manager", "processGetStatusFrameCount, return errorCode: ", Integer.valueOf(e3));
            a(300007);
            j(e3);
            return;
        }
        int m = jhq.m(bArr);
        this.ad = m;
        ReleaseLogUtil.e("Step_HwFitnessManager", "aw70 7-12 count : ", Integer.valueOf(m));
        if (this.ad > 0) {
            jin.e(this.h);
            this.h++;
        } else {
            e(0);
        }
    }

    private void j(byte[] bArr) {
        int i;
        LogUtil.a("HwFitnessAw70Manager", "processGetStatusFrame.");
        try {
            i = 0;
        } catch (bmk | cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "processGetStatusFrame Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            j(-1);
            i = 201000;
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e3 = jru.e(bArr);
            iyv.b(e3);
            LogUtil.a("HwFitnessAw70Manager", "processGetStatusFrame, return errorCode: ", Integer.valueOf(e3));
            a(300007);
            j(e3);
            return;
        }
        this.aa.add(jhq.i(bArr));
        int i2 = this.h;
        if (i2 < this.ad) {
            jin.e(i2);
            this.h++;
        } else {
            e(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "doSyncTodayComplete, errorCode: ", Integer.valueOf(i));
        b(10008, i, (Object) null);
        q();
    }

    private void m(byte[] bArr) {
        int i;
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processGetTodayFitnessData.");
        d(4);
        int i2 = 201000;
        try {
            i = 0;
        } catch (cwg e2) {
            LogUtil.b("HwFitnessAw70Manager", "processGetTodayFitnessData Exception : ", ExceptionUtils.d(e2));
            iyv.b(100007);
            j(-1);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            i2 = jru.e(bArr);
            iyv.b(i2);
            LogUtil.a("HwFitnessAw70Manager", "processGetTodayFitnessData, return errorCode:", Integer.valueOf(i2));
            j(i2);
            i = i2;
            c(i);
        }
        this.n.a(this, jhq.a(bArr));
        c(i);
    }

    private void a(int i, long j) {
        a aVar = this.r;
        if (aVar != null) {
            aVar.sendEmptyMessageDelayed(i, j);
        } else {
            LogUtil.h("HwFitnessAw70Manager", "fitnessMgrSendMsgDelay, mHwFitnessMgrHandler is null.");
        }
    }

    private void d(int i) {
        a aVar = this.r;
        if (aVar == null) {
            LogUtil.h("HwFitnessAw70Manager", "fitnessMgrRemoveMsg, mHwFitnessMgrHandler is null.");
        } else if (aVar.hasMessages(i)) {
            this.r.removeMessages(i);
        }
    }

    static class a extends Handler {
        WeakReference<jik> e;

        a(jik jikVar, Looper looper) {
            super(looper);
            this.e = new WeakReference<>(jikVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            jik jikVar = this.e.get();
            if (jikVar == null) {
                LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, hwFitnessAw70Mgr is null.");
                return;
            }
            LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, handleMessage msg: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, sync detail timeout.");
                jikVar.e(300001);
                jikVar.j(0);
                return;
            }
            if (i == 1) {
                LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, get frame count timeout.");
                return;
            }
            if (i == 2) {
                LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, get frame timeout.");
                return;
            }
            if (i == 3) {
                LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, Sync Complete msg.");
                jikVar.a(0);
            } else if (i == 4) {
                LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, Sync today timeout msg.");
                jikVar.c(300001);
                jikVar.j(4);
            } else if (i == 5) {
                LogUtil.a("HwFitnessAw70Manager", "HwFitnessMgrHandler, Save fitness data timeout msg.");
                jikVar.a(300001);
            } else {
                LogUtil.h("HwFitnessAw70Manager", "HwFitnessMgrHandler, unknown msg type.");
            }
        }
    }

    private void n() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processGetActivityReminder, do not process.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (d) {
            DeviceInfo d2 = jpu.d("HwFitnessAw70Manager");
            if (d2 != null && d2.getDeviceConnectState() == 2) {
                a(4, 40000L);
                a(10008, iBaseResponseCallback);
                LogUtil.a("HwFitnessAw70Manager", "syncFitnessTodayDataRun, enter thread.");
                jin.e();
                return;
            }
            LogUtil.h("HwFitnessAw70Manager", "syncFitnessTodayDataRun, get device info error.");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(300004, null);
            }
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        this.l.execute(new g(this, iBaseResponseCallback));
    }

    static class g implements Runnable {
        WeakReference<jik> b;
        IBaseResponseCallback e;

        g(jik jikVar, IBaseResponseCallback iBaseResponseCallback) {
            this.b = new WeakReference<>(jikVar);
            this.e = iBaseResponseCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            jik jikVar = this.b.get();
            if (jikVar != null) {
                jikVar.c(this.e);
            }
        }
    }

    private void k() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "handleUserLogin.");
        this.n.e();
    }

    public void a() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "registerDeviceToHiHealth.");
        if (this.s == null) {
            LogUtil.h("HwFitnessAw70Manager", "registerDeviceToHiHealth, mHWDeviceConfigManager is null.");
            return;
        }
        final DeviceInfo d2 = jpu.d("HwFitnessAw70Manager");
        if (d2 != null) {
            this.l.execute(new Runnable() { // from class: jik.15
                @Override // java.lang.Runnable
                public void run() {
                    jpp.i(d2);
                }
            });
        } else {
            LogUtil.h("HwFitnessAw70Manager", "registerDeviceToHiHealth, deviceInfo is null.");
        }
    }

    private void g() {
        LogUtil.a("HwFitnessAw70Manager", "AW70 handleGetUserInfo enter");
        LoginInit.getInstance(this.f).getUserInfoFromDb(new IBaseResponseCallback() { // from class: jii
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jik.this.c(i, obj);
            }
        });
    }

    /* synthetic */ void c(int i, Object obj) {
        if (i == 0 && (obj instanceof UserInfomation)) {
            LogUtil.a("HwFitnessAw70Manager", "AW70 handleGetUserInfo getUserInfo onResponse userInfomation");
            d((UserInfomation) obj, this.i);
        } else {
            LogUtil.a("HwFitnessAw70Manager", "handleGetUserInfo userInfo is null");
        }
    }

    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwFitnessAw70Manager", "handleDeviceConnection, deviceInfo is null.");
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.a("HwFitnessAw70Manager", "handleDeviceConnection, device Connect state: ", Integer.valueOf(deviceConnectState));
        if (deviceConnectState == 2) {
            LogUtil.a("HwFitnessAw70Manager", "handleDeviceConnection.");
            jpp.i(deviceInfo);
            jqi.a().getSwitchSetting("custom.activity_reminder", new IBaseResponseCallback() { // from class: jik.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    Message obtainMessage = jik.this.k.obtainMessage();
                    obtainMessage.obj = obj;
                    obtainMessage.what = 10001;
                    jik.this.k.sendMessage(obtainMessage);
                }
            });
            g();
            c();
            return;
        }
        if (deviceConnectState == 3) {
            synchronized (i()) {
                b.clear();
            }
            return;
        }
        LogUtil.h("HwFitnessAw70Manager", "handleDeviceConnection, nothing to do.");
    }

    private long b(long j) {
        String format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(new Date(j * 1000));
        try {
            return new SimpleDateFormat("yyyyMMddhhmm", Locale.ENGLISH).parse(format + AgdConstant.INSTALL_TYPE_DEFAULT).getTime() / 1000;
        } catch (ParseException e2) {
            LogUtil.b("HwFitnessAw70Manager", "getBeginOfDate Exception : ", ExceptionUtils.d(e2));
            return j;
        }
    }

    private void f() {
        this.t.clear();
        this.ac.clear();
        this.aa.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        synchronized (d) {
            LogUtil.a("05", 1, "HwFitnessAw70Manager", "syncFitnessDetailDataRun, thread isNeedSyncWork: ", Boolean.valueOf(z));
            DeviceInfo d2 = jpu.d("HwFitnessAw70Manager");
            if (d2 != null && d2.getDeviceConnectState() == 2) {
                if (this.u) {
                    LogUtil.a("05", 1, "HwFitnessAw70Manager", "syncFitnessDetailData, data syncing.");
                    iBaseResponseCallback.d(300002, null);
                    return;
                }
                this.u = true;
                b(z, "syncFitnessDetailDataRun");
                e("true");
                a(0, 240000L);
                a(10009, iBaseResponseCallback);
                f();
                this.z = h();
                long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                this.o = currentTimeMillis;
                long j = this.z;
                if (currentTimeMillis - j <= k.b.l && j != 0) {
                    if (j >= currentTimeMillis && j - currentTimeMillis <= 300) {
                        LogUtil.a("05", 1, "HwFitnessAw70Manager", "syncFitnessDetailData, lastSync time is not correct.");
                        this.z = this.o - 61;
                    } else if (j - currentTimeMillis > 300) {
                        LogUtil.a("05", 1, "HwFitnessAw70Manager", "syncFitnessDetailData, lastSync time is not correct and need write back.");
                        long j2 = this.o - 61;
                        this.z = j2;
                        c(j2);
                    } else {
                        LogUtil.h("05", 1, "HwFitnessAw70Manager", "syncFitnessDetailDataRun, nothing to do.");
                    }
                    jin.e(this.z, this.o);
                    return;
                }
                long b2 = b(currentTimeMillis - k.b.l);
                this.z = b2;
                c(b2);
                jin.e(this.z, this.o);
                return;
            }
            LogUtil.h("05", 1, "HwFitnessAw70Manager", "syncFitnessDetailDataRun, get device info error.");
            iBaseResponseCallback.d(300004, null);
        }
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback, final boolean z) {
        Log.i("HwFitnessAw70Manager", "syncFitnessDetailData.");
        this.ab = System.currentTimeMillis();
        this.w = z;
        a(new IBaseResponseCallback() { // from class: jik.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("05", 1, "HwFitnessAw70Manager", "syncFitnessDetailData, today total finish need detail errorCode: ", Integer.valueOf(i));
                jik jikVar = jik.this;
                if (jikVar.e(jikVar.ab, jik.this.h(), z)) {
                    jik.this.u = false;
                } else {
                    jik.this.l.execute(new b(jik.this, iBaseResponseCallback, true));
                }
            }
        });
    }

    public boolean e(long j, long j2, boolean z) {
        long j3 = j2 * 1000;
        LogUtil.a("HwFitnessAw70Manager", "syncFitnessDetailData startTime:", Long.valueOf(j), " getLastSyncTime:", Long.valueOf(j3), " isManual:", Boolean.valueOf(z));
        return z && j > j3 && j - j3 < 60000;
    }

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<jik> f13873a;
        boolean c;
        IBaseResponseCallback e;

        b(jik jikVar, IBaseResponseCallback iBaseResponseCallback, boolean z) {
            LogUtil.a("05", 1, "HwFitnessAw70Manager", " SyncFitnessDetailDataRunnable: ", Boolean.valueOf(z));
            this.f13873a = new WeakReference<>(jikVar);
            this.e = iBaseResponseCallback;
            this.c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            jik jikVar = this.f13873a.get();
            if (jikVar != null) {
                jikVar.a(this.e, this.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long h() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "getLastSyncTime.");
        return new jil().c();
    }

    public void c(long j) {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "setLastSyncTime time: ", Long.valueOf(j));
        jil jilVar = new jil();
        jib jibVar = new jib();
        jibVar.e(j);
        jilVar.a(jibVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final UserInfomation userInfomation) {
        LogUtil.a("HwFitnessAw70Manager", "setDeviceUserInfo.");
        ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getLastVo2maxForMaxMet(new IBaseResponseCallback() { // from class: jik.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (!(obj instanceof String)) {
                    jin.e(userInfomation);
                    LogUtil.h("HwFitnessAw70Manager", "getLastVo2maxForMaxMet is null.");
                    return;
                }
                String str = (String) obj;
                if (!str.contains(Constants.LINK)) {
                    jin.e(userInfomation);
                    LogUtil.a("HwFitnessAw70Manager", "getLastVo2maxForMaxMet not contains split symbol.");
                    return;
                }
                String[] split = str.split(Constants.LINK);
                if (split.length >= 2) {
                    try {
                        userInfomation.setMaxVo2(Integer.parseInt(split[1]));
                        userInfomation.setVo2Time(Long.parseLong(split[0]) / 1000);
                    } catch (NumberFormatException e2) {
                        LogUtil.b("HwFitnessAw70Manager", "setDeviceUserInfo Exception : ", ExceptionUtils.d(e2));
                    }
                }
                jin.e(userInfomation);
            }
        });
    }

    public void d(UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo d2 = jpu.d("HwFitnessAw70Manager");
        if (d2 == null) {
            LogUtil.h("05", 1, "HwFitnessAw70Manager", "getUserInfo, current device is null.");
            return;
        }
        DeviceCapability e2 = cvs.e(d2.getDeviceIdentify());
        if (e2 == null) {
            LogUtil.h("05", 1, "HwFitnessAw70Manager", "getUserInfo, deviceCapability is null.");
            return;
        }
        if (e2.isSupportGetUserInfo()) {
            LogUtil.a("05", 1, "HwFitnessAw70Manager", "getUserInfo, support get user info.");
            a(21, iBaseResponseCallback);
            jin.d();
        } else {
            LogUtil.a("05", 1, "HwFitnessAw70Manager", "getUserInfo, not support get user info.");
            e(userInfomation);
        }
    }

    private void h(byte[] bArr) {
        int i;
        FitnessUserInfo fitnessUserInfo;
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "processGetUserInfoData Complete command: ", 21);
        int i2 = 201000;
        try {
            i = 0;
        } catch (cwg e2) {
            e = e2;
            i = i2;
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            i2 = jru.e(bArr);
            LogUtil.h("HwFitnessAw70Manager", "processSetCmdResult return errorCode: ", Integer.valueOf(i2));
            i = i2;
            fitnessUserInfo = null;
            b(21, i, fitnessUserInfo);
        }
        try {
            fitnessUserInfo = jhq.n(bArr);
        } catch (cwg e3) {
            e = e3;
            LogUtil.b("HwFitnessAw70Manager", "processGetUserInfoData Exception : ", ExceptionUtils.d(e));
            fitnessUserInfo = null;
            b(21, i, fitnessUserInfo);
        }
        b(21, i, fitnessUserInfo);
    }

    public static String b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        return accountInfo == null ? "" : accountInfo;
    }

    private void e(String str) {
        SharedPreferenceManager.e(this.f, String.valueOf(10008), "KEY_SYNCHRONIZING_DATA_FLAG", str, (StorageParams) null);
    }

    private void t() {
        LogUtil.a("HwFitnessAw70Manager", "sendDialogDismissBroadcast.");
        Intent intent = new Intent("com.huawei.bone.action.ACTION_DIALOG_DISMISS");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.f.sendBroadcast(intent, LocalBroadcast.c);
    }

    class e extends Handler {
        e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("HwFitnessAw70Manager", "MyHandle, message is null.");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                LogUtil.a("HwFitnessAw70Manager", " handleMessage, FitnessCommandId.COMMAND_FITNESS_SET_MOTION_GOAL.");
            } else {
                if (i == 10001) {
                    LogUtil.a("HwFitnessAw70Manager", " handleMessage, FitnessCommandId.GET_FITNESS_GET_ACTIVITY_REMINDER.");
                    jik.this.bHo_(message);
                    jik.this.r();
                    return;
                }
                LogUtil.h("HwFitnessAw70Manager", "MyHandle, nothing to do.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bHo_(Message message) {
        JsonSyntaxException e2;
        ActivityReminder activityReminder;
        if (jpu.e("HwFitnessAw70Manager") != null) {
            ActivityReminder activityReminder2 = new ActivityReminder();
            if (message.obj != null && (message.obj instanceof String)) {
                try {
                    String str = (String) message.obj;
                    if (str.contains(k.g)) {
                        LogUtil.a("HwFitnessAw70Manager", "processActivityRemind data contains enable");
                        str = str.replaceAll(k.g, "isEnable");
                    }
                    activityReminder = (ActivityReminder) new Gson().fromJson(str, ActivityReminder.class);
                } catch (JsonSyntaxException e3) {
                    e2 = e3;
                }
                try {
                    if (activityReminder.getEndTime() == 5120) {
                        LogUtil.a("HwFitnessAw70Manager", "processActivityRemind set new end time");
                        activityReminder.setEndTime(5376);
                    }
                    activityReminder2 = activityReminder;
                } catch (JsonSyntaxException e4) {
                    e2 = e4;
                    activityReminder2 = activityReminder;
                    LogUtil.b("HwFitnessAw70Manager", "processActivityRemind Exception : ", ExceptionUtils.d(e2));
                    LogUtil.a("03", 1, "HwFitnessAw70Manager", "processActivityRemind Aw70 reminder = ", activityReminder2);
                    jqi.a().sendSetSwitchSettingCmd(jhn.a(activityReminder2), jin.a(), 7, 7);
                    return;
                }
            }
            LogUtil.a("03", 1, "HwFitnessAw70Manager", "processActivityRemind Aw70 reminder = ", activityReminder2);
            jqi.a().sendSetSwitchSettingCmd(jhn.a(activityReminder2), jin.a(), 7, 7);
            return;
        }
        LogUtil.h("HwFitnessAw70Manager", "sendSetSwitchSettingCmd HwGetAw70DeviceInfoUtil.getConnectDeviceInfo is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogUtil.a("05", 1, "HwFitnessAw70Manager", "setDefaultDeviceReportThreshold.");
        DeviceInfo d2 = jpu.d("HwFitnessAw70Manager");
        if (d2 == null) {
            LogUtil.h("HwFitnessAw70Manager", "setDefaultDeviceReportThreshold, deviceInfo is null.");
            return;
        }
        DeviceCapability e2 = cvs.e(d2.getDeviceIdentify());
        if (e2 == null) {
            LogUtil.h("HwFitnessAw70Manager", "setDefaultDeviceReportThreshold, deviceCapability is null.");
        } else if (!e2.isSupportThreshold()) {
            LogUtil.h("HwFitnessAw70Manager", "setDefaultDeviceReportThreshold, is not support.");
        } else {
            jin.e(jid.e(1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        LogUtil.a("HwFitnessAw70Manager", "sendAw70FitnessSyncEvent, result: ", Integer.valueOf(i));
        jra.b(i, this.w, this.ab, jpu.d("HwFitnessAw70Manager"));
    }

    private void o() {
        LogUtil.a("HwFitnessAw70Manager", "notifyToSyncWorkoutData.");
        this.s.d(this.j);
    }

    private boolean l() {
        return this.v;
    }

    private void b(boolean z, String str) {
        this.v = z;
        LogUtil.a("HwFitnessAw70Manager", "setNeedWorkout, mIsNeedWorkout: ", Boolean.valueOf(z), ", from: ", str);
    }
}

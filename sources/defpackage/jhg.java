package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwcommonmodel.fitnessdatatype.StudentHeartRateZoneMgr;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwfitnessmgr.SportGoalMgr;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.up.model.UserInfomation;
import defpackage.jhc;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jhg extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static DeviceInfo ak = null;
    private static String al = "";
    private static jhg am = null;
    private static boolean an = false;

    /* renamed from: a, reason: collision with root package name */
    protected int f13849a;
    protected long aa;
    protected List<jic> ab;
    protected int ac;
    protected jhz ad;
    protected int af;
    protected jim ag;
    protected List<jif> ai;
    private BroadcastReceiver ao;
    private HandlerThread ap;
    private Context aq;
    private BroadcastReceiver ar;
    private HandlerThread as;
    private List<Integer> at;
    private BroadcastReceiver au;
    private List<Integer> av;
    private List<jhv> aw;
    private HiUnSubscribeListener ax;
    private boolean ay;
    private long az;
    private boolean ba;
    private boolean bb;
    private jik bc;
    private BroadcastReceiver bd;
    private jhk be;
    private long bf;
    private HiSubscribeListener bg;
    private SportGoalMgr bh;
    protected IBaseResponseCallback d;
    protected Map<Integer, List<IBaseResponseCallback>> e;
    protected int f;
    protected int g;
    protected int h;
    protected int i;
    protected int j;
    protected List<jhy> k;
    protected int l;
    protected jha m;
    protected long n;
    protected int o;
    protected int p;
    protected jfq q;
    protected jhc.c r;
    protected Handler s;
    protected int t;
    protected jgx u;
    protected boolean v;
    protected Map<String, Boolean> w;
    protected boolean x;
    protected List<Integer> y;
    protected long z;
    protected static final Object c = new Object();
    protected static final Object b = new Object();
    private static final Object ae = new Object();
    private static final Object ah = new Object();
    private static final Object aj = new Object();

    private jhg(Context context) {
        super(context);
        this.e = new HashMap(16);
        this.v = false;
        this.f = 0;
        this.s = null;
        this.l = 0;
        this.t = 0;
        this.af = 0;
        this.h = 0;
        this.aa = 0L;
        this.ai = new ArrayList(16);
        this.f13849a = 0;
        this.g = 0;
        this.ac = 0;
        this.i = 0;
        this.ab = new ArrayList(16);
        this.o = 0;
        this.j = 0;
        this.k = new ArrayList(16);
        this.w = new HashMap(16);
        this.x = false;
        this.p = 1;
        this.z = 0L;
        this.n = 0L;
        this.d = new IBaseResponseCallback() { // from class: jhg.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HwFitnessManager", "mBaseResponseCallback errorCode :", Integer.valueOf(i), ", objectData :", obj);
                Intent intent = new Intent("com.huawei.phoneservice.sync_workout_broadcast_action");
                intent.setPackage(BaseApplication.getContext().getPackageName());
                jhg.this.aq.sendBroadcast(intent, LocalBroadcast.c);
            }
        };
        this.bb = true;
        this.as = null;
        this.ap = null;
        this.ba = false;
        this.aw = new ArrayList(16);
        this.at = new ArrayList(16);
        this.av = null;
        this.bh = new SportGoalMgr(BaseApplication.getContext());
        this.be = new jhk();
        this.ar = new BroadcastReceiver() { // from class: jhg.10
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String str;
                if (intent == null) {
                    LogUtil.h("HwFitnessManager", "mBroadcastReceiver onReceive intent is null.");
                    return;
                }
                try {
                    str = intent.getAction();
                } catch (Exception e) {
                    LogUtil.b("HwFitnessManager", "mBroadcastReceiver getAction exception : ", ExceptionUtils.d(e));
                    str = null;
                }
                LogUtil.a("05", 1, "HwFitnessManager", "mBroadcastReceiver onReceive action is :", str);
                if (str == null) {
                    LogUtil.h("HwFitnessManager", "mBroadcastReceiver onReceive action is null.");
                    return;
                }
                if ("com.huawei.plugin.account.login".equals(str)) {
                    jhg.this.o();
                    return;
                }
                if ("com.huawei.bone.action.FITNESS_USERINFO_UPDATED".equals(str)) {
                    jhc.b();
                    jhg.this.l();
                } else if ("com.huawei.bone.action.STUDENT_INFO_UPDATE".equals(str)) {
                    jhc.c();
                } else {
                    LogUtil.h("HwFitnessManager", "mBroadcastReceiver onReceive action is error.");
                }
            }
        };
        this.bd = new BroadcastReceiver() { // from class: jhg.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    LogUtil.h("HwFitnessManager", "mCoreSleepStatusReceiver onReceive intent is null.");
                    return;
                }
                String action = intent.getAction();
                if (action == null) {
                    return;
                }
                LogUtil.a("05", 1, "HwFitnessManager", "mCoreSleepStatusReceiver onReceive action :", action);
                if ("action_change_core_sleep_button".equals(action)) {
                    String stringExtra = intent.getStringExtra("status");
                    LogUtil.a("HwFitnessManager", "mCoreSleepStatusReceiver onReceive status :", stringExtra);
                    if (stringExtra != null) {
                        if ("1".equalsIgnoreCase(stringExtra)) {
                            jhc.a(true, jhg.this.aq);
                            return;
                        } else {
                            jhc.a(false, jhg.this.aq);
                            return;
                        }
                    }
                    return;
                }
                if ("com.huawei.health.ACTION_SEND_SPORT_GOAL".equals(action)) {
                    LogUtil.a("HwFitnessManager", "onReceive ACTION_SEND_SPORT_GOAL");
                    String stringExtra2 = intent.getStringExtra("configId");
                    if (jhc.j()) {
                        jhg.this.bh.e(stringExtra2);
                    } else if (jhc.e()) {
                        jhg.this.be.a(stringExtra2);
                    }
                }
            }
        };
        this.ao = new BroadcastReceiver() { // from class: jhg.11
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    LogUtil.h("HwFitnessManager", "mDeviceStatusReceiver onReceive intent is null.");
                    return;
                }
                String action = intent.getAction();
                if (action == null) {
                    LogUtil.h("HwFitnessManager", "mDeviceStatusReceiver onReceive action is null");
                    return;
                }
                LogUtil.a("05", 1, "HwFitnessManager", "mDeviceStatusReceiver onReceive action :", action);
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    try {
                        Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                        DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                        if (deviceInfo != null) {
                            jhg.this.c(deviceInfo);
                        } else {
                            LogUtil.h("05", 1, "HwFitnessManager", "mDeviceStatusReceiver deviceInfo is null.");
                        }
                    } catch (ClassCastException e) {
                        LogUtil.b("HwFitnessManager", "mDeviceStatusReceiver Exception : ", ExceptionUtils.d(e));
                    }
                }
            }
        };
        this.au = new BroadcastReceiver() { // from class: jhg.12
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action;
                if (intent == null || (action = intent.getAction()) == null) {
                    return;
                }
                LogUtil.a("05", 1, "HwFitnessManager", "mHiHealthDataSyncReceiver onReceive action :", action);
                if ("com.huawei.hihealth.action_user_goal_change".equals(action)) {
                    jhg.this.j();
                }
            }
        };
        this.bg = new HiSubscribeListener() { // from class: jhg.15
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                ReleaseLogUtil.e("Step_HwFitnessManager", "mSubscribeListener onResult");
                if (list == null || list.size() <= 0) {
                    jhg.this.ba = false;
                } else {
                    ReleaseLogUtil.e("Step_HwFitnessManager", "successList size ", Integer.valueOf(list.size()));
                    jhg.this.av = list;
                }
                if (list2 != null) {
                    LogUtil.a("HwFitnessManager", "failList size ", Integer.valueOf(list2.size()));
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                ReleaseLogUtil.e("Step_HwFitnessManager", "mSubscribeListener changeKey ", str, " type is ", Integer.valueOf(i));
                if (i == 102 && "custom.UserPreference_RunningPaceZone_Config".equals(str)) {
                    jhc.f();
                } else {
                    ReleaseLogUtil.d("Step_HwFitnessManager", "mSubscribeListener unkonwn changeKey");
                }
            }
        };
        this.ax = new HiUnSubscribeListener() { // from class: jhg.13
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a("HwFitnessManager", "mHiUnSubscribeListener onResult result: ", Boolean.valueOf(z));
            }
        };
        LogUtil.a("05", 1, "HwFitnessManager", "HwFitnessManager Constructor.");
        this.aq = context;
        this.u = new jgx(context, this);
        jfq c2 = jfq.c();
        this.q = c2;
        if (c2 == null) {
            LogUtil.h("05", 1, "HwFitnessManager", "mHwDeviceConfigManager is null.");
            return;
        }
        c2.e(7, this);
        this.m = jha.b();
        this.ag = new jim();
        o();
        this.r = new jhc.c(this, BaseApplication.getContext().getMainLooper());
        registerBroadcast(this.ar, "com.huawei.plugin.account.login");
        registerBroadcast(this.ar, "com.huawei.bone.action.FITNESS_USERINFO_UPDATED");
        registerBroadcast(this.ar, "com.huawei.bone.action.STUDENT_INFO_UPDATE");
        m();
        HandlerThread handlerThread = new HandlerThread("HwFitnessManager");
        this.ap = handlerThread;
        handlerThread.start();
        this.s = new b(this.ap.getLooper());
        this.bc = jik.d(BaseApplication.getContext());
        l();
        jrq.b("HwFitnessManager", new Runnable() { // from class: jhg.14
            @Override // java.lang.Runnable
            public void run() {
                jhg.this.m.c(jhg.this);
                DeviceInfo d2 = jpt.d("HwFitnessManager");
                if (d2 != null && jrp.a(d2.getDeviceIdentify()) != 0) {
                    LogUtil.a("HwFitnessManager", "deviceInfo auto Sync");
                    jhg.this.d(d2);
                }
                DeviceInfo e = jpu.e("HwFitnessManager");
                if (e == null || jrp.a(e.getDeviceIdentify()) == 0) {
                    return;
                }
                LogUtil.a("HwFitnessManager", "deviceAw70Info auto Sync");
                jhg.this.d(e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        ReleaseLogUtil.e("Step_HwFitnessManager", "subscribeHiHealth enter");
        if (TextUtils.isEmpty(b()) || this.ba) {
            return;
        }
        ReleaseLogUtil.e("Step_HwFitnessManager", "user id is not empty");
        this.ba = true;
        this.at.add(102);
        HiHealthNativeApi.a(this.aq).subscribeHiHealthData(this.at, this.bg);
    }

    public boolean d() {
        DeviceInfo d2 = jpt.d("HwFitnessManager");
        if (d2 != null) {
            return jpo.c(d2.getDeviceIdentify()) == 2;
        }
        LogUtil.h("HwFitnessManager", "isFamilyMode deviceInfo is null");
        return false;
    }

    private void f(DeviceInfo deviceInfo) {
        if (!cvt.c(deviceInfo.getProductType())) {
            if (b(deviceInfo)) {
                return;
            }
            LogUtil.h("HwFitnessManager", "initDeviceInfo handleDeviceConnection is not connected");
        } else {
            jik jikVar = this.bc;
            if (jikVar != null) {
                jikVar.b(deviceInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo) {
        if (cvt.c(deviceInfo.getProductType())) {
            jik jikVar = this.bc;
            if (jikVar != null) {
                jikVar.b(deviceInfo);
                return;
            }
            return;
        }
        if (b(deviceInfo)) {
            return;
        }
        LogUtil.h("HwFitnessManager", "changeDeviceInfo handleDeviceConnection is not connected");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwFitnessManager", "automaticSynchronizeFitnessDetailData deviceInfo is null");
        } else {
            f(deviceInfo);
        }
    }

    public static jhg c(Context context) {
        jhg jhgVar;
        synchronized (ae) {
            if (am == null) {
                am = new jhg(BaseApplication.getContext());
            }
            jhgVar = am;
        }
        return jhgVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 7;
    }

    public boolean i() {
        return this.ay;
    }

    protected void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = this.e.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList<>(16);
                    this.e.put(Integer.valueOf(i), list);
                } else {
                    for (IBaseResponseCallback iBaseResponseCallback2 : list) {
                        if (iBaseResponseCallback2 != null && iBaseResponseCallback2.equals(iBaseResponseCallback)) {
                            return;
                        }
                    }
                }
                list.add(iBaseResponseCallback);
            }
        }
    }

    private void a(int i, int i2, Object obj) {
        LogUtil.a("05", 1, "HwFitnessManager", "processCallback callback commandId :", Integer.valueOf(i), ", errorCode :", Integer.valueOf(i2));
        synchronized (b) {
            List<IBaseResponseCallback> list = this.e.get(Integer.valueOf(i));
            if (list != null) {
                int i3 = 0;
                while (true) {
                    if (list.size() <= 0) {
                        break;
                    }
                    IBaseResponseCallback iBaseResponseCallback = list.get(i3);
                    if (iBaseResponseCallback != null) {
                        LogUtil.a("05", 1, "HwFitnessManager", "processCallback errorCode :", Integer.valueOf(i2));
                        list.remove(i3);
                        iBaseResponseCallback.d(i2, obj);
                        break;
                    }
                    list.remove(i3);
                    i3++;
                }
            }
        }
    }

    private void m() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_LIST_CHANGED");
        BroadcastManagerUtil.bFC_(this.aq, this.ao, intentFilter, LocalBroadcast.c, null);
        IntentFilter intentFilter2 = new IntentFilter("com.huawei.hihealth.action_user_goal_change");
        intentFilter2.addAction("com.huawei.hihealth.action_receive_push");
        BroadcastManagerUtil.bFD_(this.aq, this.au, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("action_change_core_sleep_button");
        intentFilter3.addAction("com.huawei.health.ACTION_SEND_SPORT_GOAL");
        BroadcastManagerUtil.bFE_(this.aq, this.bd, intentFilter3);
    }

    private void h(final int i, final DeviceInfo deviceInfo) {
        LogUtil.a("HwFitnessManager", "saveFitnessDate.");
        boolean checkHiHealthServiceExist = HiHealthManager.d(this.aq).checkHiHealthServiceExist();
        LogUtil.a("05", 1, "HwFitnessManager", "saveFitnessDate isExistHiHealthService :", Boolean.valueOf(checkHiHealthServiceExist));
        if (checkHiHealthServiceExist) {
            this.u.b(5, 120000L, deviceInfo);
            this.v = true;
            jhc.h(deviceInfo);
            final ArrayList arrayList = new ArrayList(16);
            final ArrayList arrayList2 = new ArrayList(16);
            arrayList.addAll(this.ab);
            arrayList2.addAll(this.ai);
            this.ab.clear();
            this.ai.clear();
            final ArrayList arrayList3 = new ArrayList(16);
            arrayList3.addAll(this.aw);
            this.aw.clear();
            final int a2 = jhc.a();
            jrq.b("HwFitnessManager", new Runnable() { // from class: jhg.18
                @Override // java.lang.Runnable
                public void run() {
                    if (a2 == 3) {
                        jhg.this.m.e(jhg.am, arrayList, arrayList2, i, deviceInfo);
                    } else {
                        jhg.this.m.c(jhg.am, arrayList3, i, deviceInfo);
                    }
                }
            });
            return;
        }
        d(300001, deviceInfo);
    }

    public void j() {
        DeviceInfo d2 = jpt.d("HwFitnessManager");
        boolean b2 = jhc.b(d2);
        boolean e = jhc.e(d2);
        LogUtil.a("HwFitnessManager", "setDeviceFitnessGoal enter isSupportSportGoalDataSync ", Boolean.valueOf(b2), " isSupportSyncGoalList ", Boolean.valueOf(e));
        if (b2) {
            this.bh.e();
        } else if (e) {
            this.be.b();
        } else {
            h(d2);
        }
    }

    private void h(DeviceInfo deviceInfo) {
        jik jikVar;
        LogUtil.a("05", 1, "HwFitnessManager", "setDeviceFitnessGoal enter.");
        if (deviceInfo != null) {
            nit.b(new MotionGoal(), new IBaseResponseCallback() { // from class: jhg.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    jrq.b("HwFitnessManager", new jhc.a(i, obj));
                }
            });
        }
        if (jpu.e("HwFitnessManager") == null || (jikVar = this.bc) == null) {
            return;
        }
        jikVar.c();
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HwFitnessManager", "onDataReceived errorCode :", Integer.valueOf(i));
        if (i != 0 || bArr == null) {
            return;
        }
        this.u.d((Object) bArr, deviceInfo);
    }

    public void e(int i, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "doDetailSyncComplete errorCode :", Integer.valueOf(i));
        d(i, deviceInfo);
    }

    public void d(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwFitnessManager", "processDetailSyncComplete deviceInfo is null");
            return;
        }
        LogUtil.a("05", 1, "HwFitnessManager", "processDetailSyncComplete errorCode :", Integer.valueOf(i));
        jrq.b("HwFitnessManager", new d(this, i, f(), deviceInfo));
        e(true, "processDetailSyncComplete");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, boolean z, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "syncCompleteRun isNeedSync :", Boolean.valueOf(z));
        DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
        synchronized (aj) {
            a(10009, i, (Object) null);
            if (this.ag.b() == 1) {
                e(5);
                if (e != null && e.isSupportGetHighAndMiddleSport()) {
                    this.u.b(1, deviceInfo);
                    jhc.a(this.aq);
                    ReleaseLogUtil.e("R_HeartRate_HwFitnessManager", "startSyncAlarm");
                    nhu.d().startSyncHeartRateAlarm(deviceInfo);
                    nhu.d().startSyncLowSpo2Alarm(deviceInfo);
                    g();
                }
                LogUtil.h("HwFitnessManager", "syncCompleteRun not support highAndMiddleSport.");
                i(deviceInfo);
                jhc.a(this.aq);
                ReleaseLogUtil.e("R_HeartRate_HwFitnessManager", "startSyncAlarm");
                nhu.d().startSyncHeartRateAlarm(deviceInfo);
                nhu.d().startSyncLowSpo2Alarm(deviceInfo);
                g();
            } else {
                if (e != null && e.isSupportGetHighAndMiddleSport()) {
                    LogUtil.a("HwFitnessManager", "syncCompleteRun syncSecondStageFitnessDetail");
                    this.u.b(7, deviceInfo);
                    e(5);
                    this.w.remove(deviceInfo.getDeviceIdentify());
                    this.v = false;
                }
                LogUtil.h("HwFitnessManager", "syncCompleteRun not support high and middle sport.");
                e(5);
                this.w.remove(deviceInfo.getDeviceIdentify());
                this.v = false;
            }
        }
    }

    static class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<jhg> f13857a;
        private DeviceInfo c;
        private boolean d;
        private int e;

        d(jhg jhgVar, int i, boolean z, DeviceInfo deviceInfo) {
            this.e = 0;
            this.d = true;
            LogUtil.a("05", 1, "HwFitnessManager", "SyncCompleteRunnable isNeedSyncWork :", Boolean.valueOf(z));
            this.f13857a = new WeakReference<>(jhgVar);
            this.e = i;
            this.d = z;
            this.c = deviceInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            jhg jhgVar = this.f13857a.get();
            if (jhgVar != null) {
                jhgVar.d(this.e, this.d, this.c);
            }
        }
    }

    protected void b(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwFitnessManager", "notifyDetailSyncComplete deviceInfo is null");
            return;
        }
        if (i == 300001) {
            this.w.remove(deviceInfo.getDeviceIdentify());
        }
        LogUtil.a("05", 1, "HwFitnessManager", "notifyDetailSyncComplete errorCode :", Integer.valueOf(i), ", currentStage :", Integer.valueOf(this.ag.b()));
        if (this.ag.b() == 2) {
            e(0);
            if (i == 0) {
                h(2, deviceInfo);
            } else {
                this.w.remove(deviceInfo.getDeviceIdentify());
                a(10009, i, (Object) null);
            }
        }
        if (this.ag.b() == 1) {
            if (i == 0) {
                h(1, deviceInfo);
                c(100000, deviceInfo);
            } else {
                e(deviceInfo);
            }
        }
    }

    private void e(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("R_HeartRate_HwFitnessManager", "afterFitnessDataSync enter.");
        e(true, "notifyDetailSyncComplete");
        nhu.d().startSyncHeartRateAlarm(deviceInfo);
        nhu.d().startSyncLowSpo2Alarm(deviceInfo);
        g();
    }

    protected void e(jhv jhvVar) {
        LogUtil.a("HwFitnessManager", "saveRealtimeDataFrame enter.");
        this.aw.add(jhvVar);
    }

    protected void b(jhz jhzVar, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "getRealtimeDataByFrame Enter.");
        this.y = jhzVar.e();
        int b2 = jhzVar.b();
        this.l = b2;
        this.t = 0;
        List<Integer> list = this.y;
        if (list != null && b2 > 0) {
            LogUtil.a("HwFitnessManager", "getRealtimeDataByFrame get frame index :", list.get(0));
            jhc.a(this.y.get(this.t).intValue(), deviceInfo);
        } else {
            b(0, deviceInfo);
        }
    }

    private void e(int i) {
        jhc.c cVar = this.r;
        if (cVar == null || !cVar.hasMessages(i)) {
            return;
        }
        this.r.removeMessages(i);
    }

    public static void a(boolean z) {
        an = z;
    }

    public void c(IBaseResponseCallback iBaseResponseCallback, DeviceInfo deviceInfo) {
        synchronized (c) {
            if (deviceInfo != null) {
                if (deviceInfo.getDeviceConnectState() == 2) {
                    if (a(deviceInfo.getDeviceIdentify())) {
                        ReleaseLogUtil.e("Step_HwFitnessManager", "syncFitnessTodayDataRun data syncing.");
                        iBaseResponseCallback.d(0, null);
                        return;
                    }
                    this.w.put(deviceInfo.getDeviceIdentify(), true);
                    this.u.b(4, 40000L, deviceInfo);
                    c(10008, iBaseResponseCallback);
                    ReleaseLogUtil.e("Step_HwFitnessManager", "syncFitnessTodayDataRun enter thread.");
                    jho.d(deviceInfo);
                    return;
                }
            }
            ReleaseLogUtil.d("Step_HwFitnessManager", "syncFitnessTodayDataRun get device info error.");
            iBaseResponseCallback.d(300004, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        Boolean bool = false;
        if (this.w.containsKey(str)) {
            bool = this.w.get(str);
        }
        return bool != null && bool.booleanValue();
    }

    public void a(IBaseResponseCallback iBaseResponseCallback, DeviceInfo deviceInfo) {
        if (!jpp.d(deviceInfo)) {
            LogUtil.h("HwFitnessManager", "syncFitnessTodayData checkHiHealthType fail.");
            iBaseResponseCallback.d(-1, "");
            return;
        }
        jrq.b("HwFitnessManager", new jhc.h(this, iBaseResponseCallback, deviceInfo));
        jik jikVar = this.bc;
        if (jikVar != null) {
            jikVar.a((IBaseResponseCallback) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("05", 1, "HwFitnessManager", "handleUserLogin enter.");
        this.m.d(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final DeviceInfo deviceInfo) {
        LogUtil.a("HwFitnessManager", "hwfinessManager handleGetUserInfo enter");
        LoginInit.getInstance(this.aq).getUserInfoFromDb(new IBaseResponseCallback() { // from class: jhd
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jhg.this.a(deviceInfo, i, obj);
            }
        });
    }

    /* synthetic */ void a(DeviceInfo deviceInfo, int i, Object obj) {
        if (i == 0 && (obj instanceof UserInfomation)) {
            this.u.a(deviceInfo, (UserInfomation) obj);
            LogUtil.a("HwFitnessManager", "handleDeviceConnection setRunPaceZone");
            jhc.g();
            return;
        }
        LogUtil.a("HwFitnessManager", "processGetUserInfoSuccess userInfo is null");
    }

    private boolean b(DeviceInfo deviceInfo) {
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.a("05", 1, "HwFitnessManager", "handleDeviceConnection connectState :", Integer.valueOf(deviceConnectState));
        if (deviceConnectState != 2) {
            if (deviceConnectState != 3) {
                LogUtil.h("HwFitnessManager", "handleDeviceConnection connectState is error.");
            }
            return false;
        }
        ak = deviceInfo;
        if (an) {
            h();
            return true;
        }
        jez.d(this.aq, null);
        this.r.sendEmptyMessageDelayed(6, 2000L);
        return true;
    }

    public void h() {
        if (ak == null) {
            LogUtil.h("HwFitnessManager", "startDataSync sDeviceInfo is null.");
        } else {
            ThreadPoolManager.d().d("startDataSync", new Runnable() { // from class: jhg.5
                @Override // java.lang.Runnable
                public void run() {
                    jpp.i(jhg.ak);
                    jhg.this.a(jhg.ak);
                    jhc.a(jhg.ak);
                    jog.c().d(jhg.ak);
                }
            });
        }
    }

    protected void e() {
        this.aw.clear();
        this.ab.clear();
        this.ai.clear();
    }

    private void i(DeviceInfo deviceInfo) {
        jrp.c();
        synchronized (c) {
            this.ag.d(2);
            if (jhc.a() != 3 && deviceInfo != null) {
                LogUtil.a("HwFitnessManager", "syncSecondStageFitnessDetail not separated data.");
                this.w.remove(deviceInfo.getDeviceIdentify());
                return;
            }
            long d2 = this.ag.d(this);
            long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            LogUtil.a("HwFitnessManager", "lastSecondStageTime:", Long.valueOf(d2), ",endSecondStageTime:", Long.valueOf(currentTimeMillis));
            if (d2 >= jhc.a(currentTimeMillis)) {
                LogUtil.a("HwFitnessManager", "syncSecondStageFitnessDetail sync sample second stage already ok.");
                this.u.e(deviceInfo);
            } else {
                if (currentTimeMillis - d2 > k.b.l) {
                    d2 = jhc.a(currentTimeMillis - k.b.l);
                }
                this.aa = currentTimeMillis;
                jho.a(d2, currentTimeMillis);
            }
        }
    }

    public void b(boolean z, DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        boolean c2;
        ReleaseLogUtil.e("HwFitnessManager", "syncFitnessDetailData is enter, isManualSync: ", Boolean.valueOf(z));
        if (!ThermalCallback.getInstance().isTriggerTask()) {
            LogUtil.h("HwFitnessManager", "syncFitnessDetailData does not have the conditions to trigger the task.");
            jhc.d(true);
            b(300008, iBaseResponseCallback);
            return;
        }
        jhc.d(false);
        if (z) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwFitnessManager");
            if (deviceList != null && !deviceList.isEmpty()) {
                c2 = false;
                for (int i = 0; i < deviceList.size(); i++) {
                    DeviceInfo deviceInfo2 = deviceList.get(i);
                    if (deviceInfo2 == null) {
                        LogUtil.h("HwFitnessManager", "currentInfo is null");
                    } else {
                        boolean c3 = c(deviceInfo2, z, iBaseResponseCallback);
                        if (!c2) {
                            c2 = c3;
                        }
                    }
                }
            }
            jhc.a(this.aq);
        }
        c2 = c(deviceInfo, z, iBaseResponseCallback);
        if (c2) {
            return;
        }
        jhc.a(this.aq);
    }

    private boolean c(DeviceInfo deviceInfo, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        if (deviceInfo == null) {
            LogUtil.h("HwFitnessManager", "syncDeviceFitnessData deviceInfo is null");
            b(300008, iBaseResponseCallback);
            return false;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (jpo.c(deviceIdentify) == 2) {
            LogUtil.h("HwFitnessManager", "syncDeviceFitnessData mode is FAMILY_PAIR_MODE");
            b(300008, iBaseResponseCallback);
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceIdentify, "HwFitnessManager");
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HwFitnessManager", "syncDeviceFitnessData deviceCapabilityHashMaps is null or empty");
            b(300008, iBaseResponseCallback);
            return false;
        }
        DeviceCapability deviceCapability = a2.get(deviceIdentify);
        if (deviceCapability == null || !deviceCapability.isSupportSportTotal()) {
            LogUtil.h("HwFitnessManager", "syncDeviceFitnessData deviceCapability error");
            b(300008, iBaseResponseCallback);
            e(deviceInfo);
            c(0, deviceInfo);
            return false;
        }
        Boolean bool = this.w.get(deviceIdentify);
        if (bool != null && bool.booleanValue()) {
            LogUtil.a("HwFitnessManager", "syncDeviceFitnessData data syncing");
            b(300008, iBaseResponseCallback);
            return false;
        }
        if (cvt.c(deviceInfo.getProductType()) && this.bc.d()) {
            LogUtil.a("HwFitnessManager", "syncDeviceFitnessData aw70 data syncing.");
            b(300008, iBaseResponseCallback);
            return false;
        }
        a(deviceInfo, z, iBaseResponseCallback);
        return true;
    }

    private void a(final DeviceInfo deviceInfo, boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        final Message obtain = Message.obtain();
        obtain.what = 0;
        c(new IBaseResponseCallback() { // from class: jhg.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("Step_HwFitnessManager", "syncFitnessDetailData errorCode:", Integer.valueOf(i));
                if (jhg.this.r != null && jhg.this.r.hasMessages(obtain.what)) {
                    jhg.this.r.removeMessages(obtain.what);
                }
                if (i != 0) {
                    jhg.this.b(300008, iBaseResponseCallback);
                    jhg.this.b(300001, deviceInfo);
                    jhg.this.c(0, deviceInfo);
                    jhc.d(jhg.this.aq);
                    return;
                }
                jhg.this.b(0, iBaseResponseCallback);
            }
        }, z, deviceInfo);
        this.r.sendMessageDelayed(obtain, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback, boolean z, final DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            LogUtil.a("HwFitnessManager", "syncFitnessDetailData updateSyncStatus");
            jrp.e(deviceInfo.getDeviceIdentify(), 0);
        }
        if (jhc.b(deviceInfo, this.aq)) {
            if (!jpp.d(deviceInfo)) {
                LogUtil.h("HwFitnessManager", "deviceInfo checkHiHealthType fail.");
                iBaseResponseCallback.d(-1, "");
                return;
            }
            this.ay = z;
            ReleaseLogUtil.e("R_HeartRate_HwFitnessManager", "begin start syncFitnessDetailData.");
            b(System.currentTimeMillis());
            this.bf = System.currentTimeMillis();
            if (this.bc == null) {
                this.bc = jik.d(BaseApplication.getContext());
            }
            if (!cvt.c(deviceInfo.getProductType())) {
                a(new IBaseResponseCallback() { // from class: jhg.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        ReleaseLogUtil.e("Step_HwFitnessManager", " today total finish need detail errorCode :", Integer.valueOf(i));
                        if (jhg.this.bc.e(jhg.this.bf, jhc.d(jhg.this), jhg.this.ay)) {
                            if (jhg.this.a(deviceInfo.getDeviceIdentify())) {
                                jhg.this.w.remove(deviceInfo.getDeviceIdentify());
                            }
                            LogUtil.h("HwFitnessManager", "syncFitnessTodayData success not SyncFitnessDetailData.");
                            iBaseResponseCallback.d(-1, "");
                            return;
                        }
                        if (i != 0) {
                            LogUtil.h("HwFitnessManager", "syncFitnessTodayData error");
                            iBaseResponseCallback.d(-1, "");
                        } else {
                            jrq.b("HwFitnessManager", new jhc.e(jhg.this, iBaseResponseCallback, true, deviceInfo));
                        }
                    }
                }, deviceInfo);
            } else {
                this.bc.c(iBaseResponseCallback, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, "");
        }
    }

    public static String b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        al = accountInfo;
        if (accountInfo == null) {
            al = "";
        }
        return al;
    }

    public void a(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        jrq.b("HwFitnessManager", new a(iBaseResponseCallback, z));
    }

    /* loaded from: classes9.dex */
    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f13856a;
        private IBaseResponseCallback b;

        a(IBaseResponseCallback iBaseResponseCallback, boolean z) {
            this.b = iBaseResponseCallback;
            this.f13856a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeviceInfo d;
            HeartZoneConf heartZoneConf = ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getHeartZoneConf();
            LogUtil.a("05", 1, "HwFitnessManager", "setHeartZoneConfig heartZoneConf :", heartZoneConf);
            DeviceCapability d2 = cvs.d();
            if (d2 == null || !d2.isSupportHeartRateZone()) {
                LogUtil.h("HwFitnessManager", "setHeartZoneConfig deviceCapability is null or not support.");
                return;
            }
            if (this.f13856a) {
                jhg.this.c(19, this.b);
            } else {
                this.b.d(0, null);
            }
            if (jhb.e() && (d = jpt.d("HwFitnessManager")) != null && jhb.d(d.getDeviceIdentify())) {
                LogUtil.h("HwFitnessManager", "setHeartZoneConfig Device is family mode");
            } else {
                jho.c(heartZoneConf, (StudentHeartRateZoneMgr) null, false);
            }
        }
    }

    public void b(boolean z) {
        LogUtil.a("DEVMGR_SETTING", "HwFitnessManager", "changeHeartRateBtValue isEnabled :", Boolean.valueOf(z));
        if (d()) {
            LogUtil.a("HwFitnessManager", "isFamilyMode changeHeartRateBtValue isEnabled is:", Boolean.valueOf(z));
            if (z) {
                SharedPreferenceManager.e(this.aq, "102", "heart_rate_button", "1", (StorageParams) null);
            } else {
                SharedPreferenceManager.e(this.aq, "102", "heart_rate_button", "0", (StorageParams) null);
            }
            joy.d().a();
            return;
        }
        jhc.d(z ? 1 : 0, new IBaseResponseCallback() { // from class: jhg.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    LogUtil.a("HwFitnessManager", "changeHeartRateBtValue saveHeartRateButton success.");
                    joy.d().a();
                }
            }
        }, this.aq);
    }

    public void a(int i) {
        LogUtil.a("HwFitnessManager", "changeContinueMeasureHeartRateStatus status :", Integer.valueOf(i));
        if (d()) {
            jho.b(i);
            SharedPreferenceManager.e(this.aq, "101", "continue_heart_rate", Integer.toString(i), (StorageParams) null);
        } else {
            jgz.d(i);
        }
    }

    public void d(int i) {
        LogUtil.a("HwFitnessManager", " enter changeCycleMeasureHeartRateStatus status :", Integer.valueOf(i));
        if (d()) {
            jho.a(i);
            SharedPreferenceManager.e(this.aq, "102", "heart_rate_button", Integer.toString(i), (StorageParams) null);
        } else {
            jgz.a(i);
        }
    }

    public void a(int i, int i2, boolean z) {
        LogUtil.a("HwFitnessManager", "heartRateRaiseRemind enable :", Integer.valueOf(i), "heartRateValue :", Integer.valueOf(i2));
        DeviceCapability d2 = cvs.d();
        if (d2 == null || !d2.isSupportHeartRateRaiseAlarm()) {
            LogUtil.h("HwFitnessManager", "heartRateRaiseRemind deviceCapability is null or deviceCapability is not support");
            return;
        }
        if (!d()) {
            if (i == 0 && !z) {
                jho.b(i, i2);
                return;
            } else {
                jgz.e(i, i2);
                return;
            }
        }
        jho.b(i, i2);
        if (i > 0) {
            SharedPreferenceManager.e(this.aq, "104", "custom.heart_rate_raise_remind", Integer.toString(i2), (StorageParams) null);
        }
        if (i == 0 && !z) {
            LogUtil.a("HwFitnessManager", "familyMode heartRateRaiseRemind not save enable status");
        } else if (i > 0) {
            SharedPreferenceManager.e(this.aq, "102", "heart_rate_button", "1", (StorageParams) null);
        } else {
            SharedPreferenceManager.e(this.aq, "102", "heart_rate_button", "0", (StorageParams) null);
        }
    }

    public void e(int i, int i2, boolean z) {
        LogUtil.a("HwFitnessManager", "heartRateDownRemind enable :", Integer.valueOf(i), "heartRateValue :", Integer.valueOf(i2));
        DeviceCapability d2 = cvs.d();
        if (d2 == null || !d2.isSupportHeartRateDownAlarm()) {
            LogUtil.h("HwFitnessManager", "heartRateDownRemind deviceCapability is null or deviceCapability is not support");
            return;
        }
        if (!d()) {
            if (i == 0 && !z) {
                jho.a(i, i2);
                return;
            } else {
                jgz.b(i, i2);
                return;
            }
        }
        jho.a(i, i2);
        if (i > 0) {
            SharedPreferenceManager.e(this.aq, "105", "custom.heart_rate_down_remind", Integer.toString(i2), (StorageParams) null);
        }
        if (i == 0 && !z) {
            LogUtil.a("HwFitnessManager", "familyMode heartRateDownRemind not save enable status");
        } else if (i > 0) {
            SharedPreferenceManager.e(this.aq, "102", "heart_rate_button", "1", (StorageParams) null);
        } else {
            SharedPreferenceManager.e(this.aq, "102", "heart_rate_button", "0", (StorageParams) null);
        }
    }

    public void b(int i) {
        LogUtil.a("HwFitnessManager", "setBloodOxygenSwitchStatus status: ", Integer.valueOf(i));
        DeviceCapability d2 = cvs.d();
        if (d2 == null || !d2.isSupportCycleBloodOxygenSwitch()) {
            LogUtil.h("HwFitnessManager", "deviceCapability is null or deviceCapability is not support");
        } else if (d()) {
            jho.c(i);
            SharedPreferenceManager.e(this.aq, "100", "custom.blood.oxygen.switch", Integer.toString(i), (StorageParams) null);
        } else {
            jgz.b(i);
        }
    }

    public void b(int i, int i2, boolean z) {
        LogUtil.a("HwFitnessManager", "bloodOxygenDownRemind enable: ", Integer.valueOf(i), " bloodOxygenValue: ", Integer.valueOf(i2));
        DeviceCapability d2 = cvs.d();
        if (d2 == null || !d2.isSupportBloodOxygenDownRemind()) {
            LogUtil.h("HwFitnessManager", "deviceCapability is null or deviceCapability is not support");
            return;
        }
        if (!d()) {
            if (i == 0 && !z) {
                jho.c(i, i2);
                return;
            } else {
                jgz.c(i, i2);
                return;
            }
        }
        LogUtil.a("HwFitnessManager", "setBloodOxygenDownRemind isFamilyMode");
        jho.c(i, i2);
        if (i > 0) {
            SharedPreferenceManager.e(this.aq, "100", "custom.blood.oxygen.switch", "1", (StorageParams) null);
            SharedPreferenceManager.e(this.aq, "103", "custom.blood.oxygen.remind", Integer.toString(i2), (StorageParams) null);
        } else {
            SharedPreferenceManager.e(this.aq, "100", "custom.blood.oxygen.switch", "0", (StorageParams) null);
        }
    }

    public void e(int i, int i2, int i3) {
        LogUtil.a("HwFitnessManager", "changeContinueMeasureHeartRateStatus status: ", Integer.valueOf(i2), " ,switchType: ", Integer.valueOf(i));
        if (i == 1) {
            jgz.c(i2);
        } else if (i == 2) {
            jgz.a(i2, i3);
        } else {
            if (i != 3) {
                return;
            }
            jgz.d(i2, i3);
        }
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            jhg.this.u.bHg_(message);
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        HandlerThread handlerThread = this.ap;
        if (handlerThread != null) {
            handlerThread.quit();
            this.ap = null;
        }
        HandlerThread handlerThread2 = this.as;
        if (handlerThread2 != null) {
            handlerThread2.quit();
            this.as = null;
        }
        BroadcastReceiver broadcastReceiver = this.ar;
        if (broadcastReceiver != null) {
            unregisterBroadcast(broadcastReceiver);
            this.ar = null;
        }
        BroadcastReceiver broadcastReceiver2 = this.au;
        if (broadcastReceiver2 != null) {
            BroadcastManagerUtil.bFJ_(this.aq, broadcastReceiver2);
            this.au = null;
        }
        BroadcastReceiver broadcastReceiver3 = this.bd;
        if (broadcastReceiver3 != null) {
            BroadcastManagerUtil.bFK_(this.aq, broadcastReceiver3);
            this.bd = null;
        }
        if (this.av != null && this.ba) {
            HiHealthManager.d(this.aq).unSubscribeHiHealthData(this.av, this.ax);
            this.at = null;
            this.av = null;
            this.ba = false;
        }
        this.bh.a();
        this.be.a();
    }

    protected void g() {
        LogUtil.a("HwFitnessManager", "notifyToSyncWorkoutData sending broadcast to sync workout data.");
        ThreadPoolManager.d().d("HwFitnessManager", new Runnable() { // from class: jhg.9
            @Override // java.lang.Runnable
            public void run() {
                synchronized (jhg.c) {
                    jhg.this.q.d(jhg.this.d);
                }
            }
        });
    }

    public boolean f() {
        return this.bb;
    }

    protected void e(boolean z, String str) {
        LogUtil.a("HwFitnessManager", "Enter setNeedWorkout isNeedWorkout: ", Boolean.valueOf(z), " isFrom: ", str);
        this.bb = z;
    }

    public void b(long j) {
        this.az = j;
    }

    protected void c(final int i, final DeviceInfo deviceInfo) {
        LogUtil.a("HwFitnessManager", "sendFitnessSyncEvent result :", Integer.valueOf(i));
        ThreadPoolManager.d().d("HwFitnessManager", new Runnable() { // from class: jhg.8
            @Override // java.lang.Runnable
            public void run() {
                jra.b(i, jhg.this.ay, jhg.this.bf, deviceInfo);
            }
        });
    }

    public void a(int i, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "processIntensiveSyncComplete errorCode: ", Integer.valueOf(i), ", intensiveFlag: ", Integer.valueOf(this.p));
        this.x = false;
        if (this.p == 1) {
            i(deviceInfo);
        }
    }
}

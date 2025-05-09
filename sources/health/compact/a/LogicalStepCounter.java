package health.compact.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.PointerIconCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.dfx.memory.MemoryMonitor;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.connectivity.StepCounterManager;
import com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter;
import com.huawei.health.connectivity.extendstepcounter.ExtendStepDataManager;
import com.huawei.health.connectivity.extendstepcounter.MidwareStepCounter;
import com.huawei.health.connectivity.standstepcounter.StandStepDataManager;
import com.huawei.health.icommon.HwHealthSensorEventListener;
import com.huawei.health.icommon.ISimpleResultCallback;
import com.huawei.health.icommon.IStepReport;
import com.huawei.health.icommon.LocalStepDataReport;
import com.huawei.health.icommon.SportIntensity;
import com.huawei.health.manager.LogicalStepCounter$ExtendStepMsg;
import com.huawei.health.manager.LogicalStepCounter$FlushDbMsg;
import com.huawei.health.manager.LogicalStepCounter$ReadStaticDealCallback;
import com.huawei.health.manager.MockStepHelper;
import com.huawei.health.manager.ScreenOnStatisticsUtil;
import com.huawei.health.manager.common.FlushableStepDataCache;
import com.huawei.health.manager.receiver.DaemonBrowseModeReceiver;
import com.huawei.health.manager.receiver.DaemonDynamicBroadcastReceiver;
import com.huawei.health.manager.util.Consts;
import com.huawei.health.manager.util.DeviceClassWatchDog;
import com.huawei.health.manager.util.GoalValue;
import com.huawei.health.manager.util.HiHealthHelper;
import com.huawei.health.manager.util.HuaweiServerTokenNotification;
import com.huawei.health.manager.util.SleepDataDbUtil;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.health.manager.util.TotalDetailStepsCacheBean;
import com.huawei.health.manager.util.UploadUtil;
import com.huawei.health.manager.util.UserInfo;
import com.huawei.health.ui.notification.uihandlers.HealthStepsNotificationHelper;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.threecircle.ThreeCircleConfigUtil;
import com.huawei.utils.FoundationCommonUtil;
import com.huawei.watchface.api.HwWatchFaceApi;
import defpackage.gja;
import defpackage.gjz;
import defpackage.gnj;
import defpackage.gnr;
import defpackage.jdh;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nip;
import defpackage.niv;
import defpackage.nix;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogicalStepCounter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class LogicalStepCounter implements StandStepDataManager.IGetCacheTodaySteps {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f13129a = false;
    private static boolean c = false;
    private static LogicalStepCounter e;
    private static long f;
    private static long g;
    private boolean ae;
    private ProcessAliveMonitor ao;
    private StepsRecordManager bj;
    private ArrayList<Integer> bm;
    private DaemonDynamicBroadcastReceiver l;
    private DaemonBrowseModeReceiver n;
    private Context r;
    private GoalValue x;
    private HealthStepsNotificationHelper z;
    private static final Object b = new Object();
    private static final Object d = new Object();
    private static long i = System.currentTimeMillis();
    private StandStepCounter bd = null;
    private StandStepDataManager bh = null;
    private StepCounterManager bg = null;
    private ExtendStepCounter v = null;
    private ExtendStepDataManager y = null;
    private DistanceManager q = null;
    private CaloriesManager k = null;
    private boolean al = false;
    private int bo = 0;
    private int ay = 0;
    private int o = 0;
    private int am = 0;
    private int be = 0;
    private int ba = 0;
    private int aw = 0;
    private int at = 0;
    private int au = 0;
    private int bc = 0;
    private int av = 0;
    private double h = 0.0d;
    private UserInfo bp = null;
    private String s = "unKnown";
    private int as = 0;
    private boolean ai = true;
    private boolean ak = false;
    private boolean ad = false;
    private HandlerThread bn = new HandlerThread("DaemonLogicStepCounter");
    private volatile Handler br = null;
    private int bb = 0;
    private int aq = 0;
    private long t = 0;
    private ISimpleResultCallback ac = null;
    private DeviceClassWatchDog m = null;
    private UploadUtil bl = null;
    private long bi = System.currentTimeMillis();
    private long ap = 0;
    private long p = 0;
    private long bf = 0;
    private boolean an = false;
    private boolean ag = false;
    private AtomicBoolean j = new AtomicBoolean(false);
    private long aj = -1;
    private ArrayList<IStepReport> ax = new ArrayList<>(16);
    private AtomicBoolean af = new AtomicBoolean(false);
    private List<Integer> az = null;
    private boolean aa = false;
    private boolean w = false;
    private List<Integer> bk = null;
    private boolean ar = false;
    private boolean ab = AuthorizationUtils.a(BaseApplication.getContext());
    private final boolean ah = CommonUtil.bd();
    private BroadcastReceiver u = new BroadcastReceiver() { // from class: com.huawei.health.manager.LogicalStepCounter$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            boolean z2;
            List list;
            Handler handler;
            Context context2;
            boolean z3;
            Handler handler2;
            if (intent != null && HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra("isForeground", false);
                LogUtil.a("Step_LSC", "mForegroundStatusChangeReceiver() isForeground: ", Boolean.valueOf(booleanExtra));
                z = LogicalStepCounter.this.ae;
                if (z == booleanExtra) {
                    return;
                }
                LogicalStepCounter.this.ae = booleanExtra;
                z2 = LogicalStepCounter.this.ae;
                if (z2) {
                    ReleaseLogUtil.e("Step_LSC", "mIsForeground = true");
                    LogicalStepCounter.this.aw();
                    handler = LogicalStepCounter.this.br;
                    if (handler != null) {
                        handler2 = LogicalStepCounter.this.br;
                        handler2.sendEmptyMessageDelayed(1021, 3000L);
                    }
                    context2 = LogicalStepCounter.this.r;
                    if (!niv.a(context2)) {
                        LogicalStepCounter.this.b(false);
                    }
                    boolean a2 = ScreenUtil.a();
                    LogUtil.a("Step_LSC", "isScreenOn", Boolean.valueOf(a2));
                    z3 = LogicalStepCounter.this.ar;
                    if (z3 && a2) {
                        LogicalStepCounter.this.al();
                        return;
                    }
                    return;
                }
                LogicalStepCounter logicalStepCounter = LogicalStepCounter.this;
                list = logicalStepCounter.az;
                logicalStepCounter.b((List<Integer>) list);
            }
        }
    };

    private LogicalStepCounter(Context context) {
        this.bj = null;
        this.x = null;
        this.r = null;
        if (context == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "LogicalStepCounter context is null.");
            this.r = BaseApplication.getContext();
        } else {
            this.r = context;
        }
        this.x = new GoalValue(this.r);
        StepsRecordManager stepsRecordManager = new StepsRecordManager();
        this.bj = stepsRecordManager;
        stepsRecordManager.b();
    }

    public static LogicalStepCounter c(Context context) {
        LogicalStepCounter logicalStepCounter;
        synchronized (b) {
            if (e == null) {
                e = new LogicalStepCounter(context);
            }
            logicalStepCounter = e;
        }
        return logicalStepCounter;
    }

    private void ak() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("com.huawei.health.WIDGET_ENABLE");
        intentFilter.addAction("com.huawei.health.WIDGET_DISABLE");
        intentFilter.addAction("com.huawei.health.WIDGET_FORCE_UPDATE");
        intentFilter.addAction("com.huawei.health.SERVER_TOKEN_INVALIDE");
        intentFilter.addAction("com.huawei.health.start_step_counter");
        intentFilter.addAction("com.huawei.health.check_sensor_state");
        intentFilter.addAction("com.huawei.health.action.ACTION_WEAR_DEVICE_CAPABILITY");
        intentFilter.addAction("com.huawei.health.AUTHORIZATION_CHANGED");
        intentFilter.addAction("com.huawei.health.SYNC_DB_FOR_DEVICE_CHANGE");
        this.l = new DaemonDynamicBroadcastReceiver(this);
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "registerDynamicBroadcastReceiver");
        Context context = this.r;
        if (context != null) {
            BroadcastManagerUtil.bFA_(context, this.l, intentFilter, Consts.f2803a, null);
            f13129a = true;
        }
    }

    private void ax() {
        if (this.r == null || this.l == null || !f13129a) {
            return;
        }
        try {
            try {
                this.r.unregisterReceiver(this.l);
            } catch (IllegalArgumentException e2) {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.c("Step_LSC", "Receiver already unregistered ", LogAnonymous.b((Throwable) e2));
            }
        } finally {
            this.l = null;
            f13129a = false;
        }
    }

    public void akF_(Intent intent) {
        if (this.br == null || intent == null) {
            return;
        }
        Message obtainMessage = this.br.obtainMessage(1007);
        obtainMessage.obj = intent.clone();
        this.br.sendMessage(obtainMessage);
    }

    public void e(boolean z) {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "refreshBrowseModeStatus", Boolean.valueOf(z));
        this.ad = z;
        SharedPerferenceUtils.d(this.r, z);
    }

    public boolean j() {
        return SharedPerferenceUtils.p(this.r);
    }

    public void c(int i2) {
        this.aq = i2;
    }

    public void e(ISimpleResultCallback iSimpleResultCallback) {
        try {
            HandlerThread handlerThread = this.bn;
            if (handlerThread == null) {
                return;
            }
            handlerThread.start();
            final Looper looper = this.bn.getLooper();
            this.br = new Handler(looper) { // from class: com.huawei.health.manager.LogicalStepCounter$2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    boolean akp_;
                    akp_ = LogicalStepCounter.this.akp_(message);
                    if (akp_) {
                        return;
                    }
                    super.handleMessage(message);
                }
            };
            Message obtainMessage = this.br.obtainMessage();
            obtainMessage.what = 1018;
            obtainMessage.obj = iSimpleResultCallback;
            this.br.sendMessage(obtainMessage);
        } catch (IllegalThreadStateException e2) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "WorkThread.start illegalThreadStateException:", e2.getMessage());
        }
    }

    private void akv_(Message message) {
        this.ao = ProcessAliveMonitor.a(this.r);
        if (!(message.obj instanceof ISimpleResultCallback)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "processInit ", "msg.obj invalid type");
            return;
        }
        ISimpleResultCallback iSimpleResultCallback = (ISimpleResultCallback) message.obj;
        Context context = this.r;
        if (context != null) {
            this.aj = SharedPerferenceUtils.k(context);
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "init ....", iSimpleResultCallback);
            this.ac = iSimpleResultCallback;
            UserInfo.c(this.r);
            this.bp = UserInfo.d();
        }
        this.bl = new UploadUtil(this.r, this.s, this.br);
        x();
        this.ad = Utils.f();
        c = StepCounterSupport.c(this.r);
        SharedPerferenceUtils.d(this.r, this.ad);
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "init mIsBrowseMode", Boolean.valueOf(this.ad), " sIsRemoveReduceClass ", Boolean.valueOf(c));
        ak();
        as();
        if (CommonUtil.s(BaseApplication.getContext())) {
            aw();
        }
        BroadcastManager.wj_(this.u);
        boolean w = w();
        DistanceManager a2 = DistanceManager.a(this.r);
        this.q = a2;
        this.k = CaloriesManager.e(this.r, a2, this.y);
        TotalDetailStepsCacheBean u = SharedPerferenceUtils.u(this.r);
        if (u != null && jdl.d(u.f(), System.currentTimeMillis())) {
            this.bc = u.c();
            this.av = u.e();
        }
        if (w) {
            gja.a().c();
            gja.a().b();
            if (this.br != null) {
                this.br.sendEmptyMessage(1008);
                this.br.sendEmptyMessage(1006);
                this.br.sendEmptyMessageDelayed(101010, PreConnectManager.CONNECT_INTERNAL);
            }
        }
    }

    private void x() {
        String[] j;
        Context context = this.r;
        if (context == null || (j = SharedPerferenceUtils.j(context)) == null) {
            return;
        }
        try {
            if (j.length < 2 || !jdl.d(System.currentTimeMillis(), Long.parseLong(j[0]))) {
                return;
            }
            this.h = Double.parseDouble(j[1]);
        } catch (NumberFormatException e2) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "initDiffAltitudefromFile ", e2.getMessage());
        }
    }

    private void e(int i2) {
        Context context = this.r;
        if (context != null) {
            this.h += i2 - this.au;
            SharedPerferenceUtils.a(context, System.currentTimeMillis(), this.h);
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "saveDiffAltitudeToFile total db", LogAnonymous.b(i2), " mAltitudeDiff ", LogAnonymous.b((int) this.h));
        }
    }

    private boolean w() {
        MockStepHelper mockStepHelper;
        this.bg = new StepCounterManager(this.r);
        boolean e2 = MockStepHelper.e(this.r);
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "initStepCounter isSupportMockStepCounter = ", Boolean.valueOf(e2));
        if (e2) {
            mockStepHelper = new MockStepHelper(this.r);
            mockStepHelper.a();
            this.bd = this.bg.d(true);
            ExtendStepCounter a2 = this.bg.a(true);
            this.v = a2;
            mockStepHelper.a(this.bd, a2);
        } else {
            this.bd = this.bg.b();
            this.v = this.bg.a(false);
            mockStepHelper = null;
        }
        if (StepCounterSupport.f(this.r)) {
            this.ak = true;
            this.y = new ExtendStepDataManager(this.r);
            this.m = new DeviceClassWatchDog(this.r);
            this.v.init(this.ac);
        } else if (mockStepHelper != null) {
            if (mockStepHelper.b()) {
                this.ac.onSuccess(null);
            }
        } else if (StepCounterSupport.h(this.r)) {
            this.ac.onSuccess(null);
        } else {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "do nothing");
        }
        StandStepDataManager standStepDataManager = new StandStepDataManager();
        this.bh = standStepDataManager;
        standStepDataManager.c(this.aq);
        return this.bd != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean akp_(Message message) {
        if (message == null) {
            return false;
        }
        boolean akt_ = akt_(message);
        return !akt_ ? aku_(message) : akt_;
    }

    private boolean akt_(Message message) {
        int i2 = message.what;
        if (i2 == 1000) {
            akC_(message);
        } else if (i2 == 1001) {
            akq_(message);
        } else if (i2 == 1003) {
            ad();
        } else if (i2 == 1004) {
            akr_(message);
        } else if (i2 == 1010) {
            ad();
        } else if (i2 == 1016) {
            akA_(message);
        } else {
            if (i2 != 1018) {
                return false;
            }
            akv_(message);
        }
        return true;
    }

    private boolean aku_(Message message) {
        int i2 = message.what;
        if (i2 == 1017) {
            akw_(message);
        } else if (i2 != 101010) {
            switch (i2) {
                case 1005:
                    akD_(message);
                    break;
                case 1006:
                    aky_(message);
                    break;
                case 1007:
                    if (!(message.obj instanceof Intent)) {
                        return false;
                    }
                    aks_((Intent) message.obj);
                    break;
                case 1008:
                    q();
                    break;
                default:
                    switch (i2) {
                        case 1011:
                            ai();
                            break;
                        case 1012:
                            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "sync with db");
                            a(new ISimpleResultCallback(this) { // from class: com.huawei.health.manager.LogicalStepCounter$FlushCallbackInter

                                /* renamed from: a, reason: collision with root package name */
                                private WeakReference<LogicalStepCounter> f2775a;

                                {
                                    this.f2775a = null;
                                    this.f2775a = new WeakReference<>(this);
                                }

                                @Override // com.huawei.health.icommon.ISimpleResultCallback
                                public void onSuccess(Bundle bundle) {
                                    LogicalStepCounter logicalStepCounter;
                                    LogUtil.a("Step_LSC", "FlushCallbackInter onSuccess.");
                                    WeakReference<LogicalStepCounter> weakReference = this.f2775a;
                                    if (weakReference == null || (logicalStepCounter = weakReference.get()) == null) {
                                        return;
                                    }
                                    logicalStepCounter.n();
                                }

                                @Override // com.huawei.health.icommon.ISimpleResultCallback
                                public void onFailed(Bundle bundle) {
                                    LogicalStepCounter logicalStepCounter;
                                    ReleaseLogUtil.d("Step_LSC", "FlushCallbackInter onFailed, continue work");
                                    WeakReference<LogicalStepCounter> weakReference = this.f2775a;
                                    if (weakReference == null || (logicalStepCounter = weakReference.get()) == null) {
                                        return;
                                    }
                                    logicalStepCounter.n();
                                }
                            }, false, false);
                            break;
                        case 1013:
                            al();
                            break;
                        default:
                            switch (i2) {
                                case 1019:
                                    ah();
                                    break;
                                case PointerIconCompat.TYPE_GRAB /* 1020 */:
                                    akB_(message);
                                    break;
                                case 1021:
                                    an();
                                    break;
                                case 1022:
                                    d(message.arg1, message.arg2, ((Long) message.obj).longValue());
                                    break;
                                default:
                                    return false;
                            }
                    }
            }
        } else {
            gja.a().d();
        }
        return true;
    }

    private void ah() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", " processSportIntensity ");
        SportIntensity.a(this.r).e(3);
    }

    private void akA_(Message message) {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "user info change");
        Object obj = message.obj;
        Bundle bundle = obj instanceof Bundle ? (Bundle) obj : null;
        if (bundle == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "processSetUserInfoByMessage userInfo is null.");
            return;
        }
        d(false);
        a(null, false, true);
        this.bp.c(bundle.get("weight") != null ? ((Float) bundle.get("weight")).floatValue() : this.bp.j(), bundle.get("height") != null ? ((Float) bundle.get("height")).floatValue() : this.bp.c(), bundle.get("ownerId") != null ? ((Integer) bundle.get("ownerId")).intValue() : this.bp.a(), bundle.get(CommonConstant.KEY_GENDER) != null ? ((Integer) bundle.get(CommonConstant.KEY_GENDER)).intValue() : this.bp.e(), bundle.get("age") != null ? ((Integer) bundle.get("age")).intValue() : this.bp.b());
    }

    public HwHealthSensorEventListener e() {
        return this.bd;
    }

    private void akC_(Message message) {
        if (this.j.get()) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "onStandStepChanged phone shutdown, return");
            return;
        }
        if (message.obj == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "processStandDealStepDate msg.obj is null.");
            return;
        }
        long longValue = ((Long) message.obj).longValue();
        int i2 = message.arg1;
        int i3 = message.arg2;
        d(i2);
        b(i2);
        this.be = i2;
        if (!this.af.get()) {
            Object obj = d;
            synchronized (obj) {
                this.af.set(true);
                obj.notifyAll();
            }
        }
        isProcessNewDayReport(false);
        long b2 = com.huawei.hwlogsmodel.LogUtil.b(5000, g);
        if (b2 != -1) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "onSStepChgd ", Integer.valueOf(i2), " se ", Integer.valueOf(i3));
            g = b2;
        }
        h(i2);
        a(i2);
        i(i2);
        if (this.bl.b(i2)) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "flushTempCacheToDB by stand");
            b((ISimpleResultCallback) null);
        }
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager != null) {
            standStepDataManager.a(longValue, i2);
            com.huawei.hwlogsmodel.LogUtil.c("Step_LSC", "mPreDaemonSteps :", Integer.valueOf(this.aq));
            if (this.bh.e() > 0) {
                this.bh.c(0);
            }
        }
        if (this.br != null) {
            this.br.sendEmptyMessageDelayed(1006, 300L);
        }
    }

    private void h(int i2) {
        if (System.currentTimeMillis() - i > 86400000) {
            c = StepCounterSupport.c(this.r);
            i = System.currentTimeMillis();
        }
        if (c) {
            return;
        }
        long b2 = com.huawei.hwlogsmodel.LogUtil.b(2000, f);
        if (b2 != -1) {
            j(i2);
            f = b2;
        }
    }

    private void d(int i2) {
        int i3;
        if (this.aa || (i3 = this.be) == 0 || i2 <= i3) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "processStandDealStepDate initModule enter ");
        SportIntensity.a(this.r).a();
        HiHealthNativeApi.a(this.r).subscribeHiHealthData(4, new HiSubscribeListener(this) { // from class: com.huawei.health.manager.LogicalStepCounter$SportIntensityCallback
            private WeakReference<LogicalStepCounter> c;

            {
                this.c = null;
                this.c = new WeakReference<>(this);
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                LogicalStepCounter logicalStepCounter = this.c.get();
                if (logicalStepCounter == null) {
                    LogUtil.b("Step_LSC", "logicalStepCounter == null");
                } else if (koq.c(list)) {
                    logicalStepCounter.bk = list;
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i4, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                Handler handler;
                Handler handler2;
                ReleaseLogUtil.e("Step_LSC", "SportIntensityCallback ");
                WeakReference<LogicalStepCounter> weakReference = this.c;
                if (weakReference != null) {
                    LogicalStepCounter logicalStepCounter = weakReference.get();
                    if (logicalStepCounter != null) {
                        handler = logicalStepCounter.br;
                        if (handler != null) {
                            handler2 = logicalStepCounter.br;
                            handler2.sendEmptyMessage(1019);
                            return;
                        }
                        return;
                    }
                    return;
                }
                ReleaseLogUtil.d("Step_LSC", "mLogicalStepCounterRef == null");
            }
        });
        this.aa = true;
    }

    private void b(int i2) {
        int i3;
        if (this.w || (i3 = this.be) == 0 || i2 <= i3) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "processStandDealStepDate refreshMotion enter ");
        b(false);
        this.w = true;
    }

    private void akr_(Message message) {
        long j;
        long j2;
        int i2;
        int i3;
        int i4;
        Object obj = message.obj;
        LogicalStepCounter$ExtendStepMsg logicalStepCounter$ExtendStepMsg = obj instanceof LogicalStepCounter$ExtendStepMsg ? (LogicalStepCounter$ExtendStepMsg) obj : null;
        if (logicalStepCounter$ExtendStepMsg != null && this.y != null) {
            j = logicalStepCounter$ExtendStepMsg.d;
            new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ").format(new Date(j));
            ExtendStepDataManager extendStepDataManager = this.y;
            int a2 = this.bp.a();
            j2 = logicalStepCounter$ExtendStepMsg.d;
            i2 = logicalStepCounter$ExtendStepMsg.b;
            i3 = logicalStepCounter$ExtendStepMsg.c;
            i4 = logicalStepCounter$ExtendStepMsg.f2774a;
            extendStepDataManager.b(a2, j2, i2, i3, i4);
        }
        if (this.br == null || this.br.hasMessages(1006)) {
            return;
        }
        this.br.sendEmptyMessageDelayed(1006, 300L);
    }

    private void ad() {
        ExtendStepDataManager extendStepDataManager = this.y;
        if (extendStepDataManager != null) {
            extendStepDataManager.b();
        }
    }

    private void akD_(Message message) {
        if (message.arg1 == 100) {
            ar();
        } else if (message.arg1 == 101) {
            ao();
        } else {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "processStepCounterSateChange default.");
        }
    }

    private void akq_(Message message) {
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager != null && standStepDataManager.b() < getCacheTodaySteps()) {
            n();
        } else {
            d(false);
        }
        if (message.obj instanceof LogicalStepCounter$FlushDbMsg) {
            a(((LogicalStepCounter$FlushDbMsg) message.obj).d, false, false);
        } else {
            a(null, false, false);
        }
    }

    public void a(Map<String, Object> map) {
        SleepDataDbUtil.c(this.r, map);
    }

    public void n() {
        if (this.br != null && ScreenUtil.a()) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "ScreenON ,MSG_SYNC_WITH_DB");
            aj();
            this.br.sendEmptyMessage(1013);
            this.aj = -1L;
            SharedPerferenceUtils.e(this.r, -1L);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.aj = currentTimeMillis;
        SharedPerferenceUtils.e(this.r, currentTimeMillis);
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "ScreenOFF ,drop this time MSG_SYNC_WITH_DB");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        this.ar = false;
        ExtendStepCounter extendStepCounter = this.v;
        if ((extendStepCounter instanceof MidwareStepCounter) && !((MidwareStepCounter) extendStepCounter).b() && this.br != null) {
            this.br.sendEmptyMessageDelayed(1013, 500L);
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "processSyncStepsWithHiHealth times check");
        HiHealthNativeApi.a(this.r).aggregateHiHealthData(HiHealthHelper.b(), new LogicalStepCounter$ReadStaticDealCallback(this, 1017));
        if (!CommonUtil.bd()) {
            s();
        }
        this.x.c();
    }

    private void s() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long currentTimeMillis = System.currentTimeMillis();
        hiDataReadOption.setTimeInterval(jdl.t(currentTimeMillis), jdl.e(currentTimeMillis));
        hiDataReadOption.setType(new int[]{901});
        HiHealthNativeApi.a(this.r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener(this) { // from class: com.huawei.health.manager.LogicalStepCounter$ReadDeviceStepCallback

            /* renamed from: a, reason: collision with root package name */
            private WeakReference<LogicalStepCounter> f2776a;

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            {
                this.f2776a = null;
                this.f2776a = new WeakReference<>(this);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                StandStepDataManager standStepDataManager;
                StandStepDataManager standStepDataManager2;
                Object[] objArr = new Object[4];
                objArr[0] = "getStepCounterDetails, errorCode = ";
                objArr[1] = Integer.valueOf(i2);
                objArr[2] = " data ";
                objArr[3] = obj != null ? obj.toString() : null;
                LogUtil.a("Step_LSC", objArr);
                if (i2 != 0) {
                    LogUtil.b("Step_LSC", "Error query step counter, errorCode = ", Integer.valueOf(i2));
                    return;
                }
                List<HiHealthData> list = (List) ((SparseArray) obj).get(901);
                if (koq.b(list)) {
                    return;
                }
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData != null && gnr.a(hiHealthData.getString("device_uniquecode"))) {
                        int floatValue = (int) hiHealthData.getFloatValue();
                        LogicalStepCounter logicalStepCounter = this.f2776a.get();
                        if (logicalStepCounter != null) {
                            standStepDataManager = logicalStepCounter.bh;
                            if (standStepDataManager != null) {
                                standStepDataManager2 = logicalStepCounter.bh;
                                standStepDataManager2.e(floatValue);
                            }
                        }
                    }
                }
            }
        });
    }

    private void e(int i2, int i3, long j) {
        d(false);
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager == null || standStepDataManager.b() == i2) {
            return;
        }
        if (this.bd != null && !this.ah && i2 > this.bh.b() && this.br != null) {
            this.bd.d();
            if (this.br.hasMessages(1022)) {
                this.br.removeMessages(1022);
            }
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "syncWithHiHealth send message MSG_SYNC_DIFF_DATA_DELAY");
            Message obtainMessage = this.br.obtainMessage(1022);
            obtainMessage.arg1 = i2;
            obtainMessage.arg2 = i3;
            obtainMessage.obj = Long.valueOf(j);
            this.br.sendMessageDelayed(obtainMessage, 500L);
            return;
        }
        if (!jdl.d(j, System.currentTimeMillis())) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "syncWithHiHealth data not same day,return dataStartTime:", Long.valueOf(j));
        } else if (this.bh.b(i2)) {
            this.ay = i3;
        } else {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "syncWithHiHealth localTotalSteps > dBTotalSteps");
            t();
        }
    }

    private void d(int i2, int i3, long j) {
        if (!jdl.d(j, System.currentTimeMillis())) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "calculateDiffForDb data not same day,return dataStartTime:", Long.valueOf(j));
            return;
        }
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager == null || !standStepDataManager.b(i2)) {
            return;
        }
        this.ay = i3;
        isProcessNewDayReport(false);
    }

    private void aks_(Intent intent) {
        if (intent != null) {
            try {
                String action = intent.getAction();
                com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "processHandleBroadcastAction action:", action);
                if ("android.intent.action.SCREEN_ON".equals(action)) {
                    aa();
                } else if ("android.intent.action.TIME_TICK".equals(action)) {
                    z();
                } else if ("android.intent.action.ACTION_SHUTDOWN".equals(action)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "ACTION_SHUTDOWN");
                    ag();
                } else if ("com.huawei.health.WIDGET_FORCE_UPDATE".equals(action)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "ACTION_HEALTH_WIDGET_FORCE_UPDATE");
                    ac();
                } else if ("com.huawei.hihealth.action_today_sport_data_refresh".equals(action)) {
                    akx_(intent);
                } else if ("android.intent.action.TIME_SET".equals(action)) {
                    am();
                } else if ("com.huawei.health.action.UPLOAD_STATICS".equals(action)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "ACTION_RESTART_SENSOR_MSG");
                    akz_(intent);
                } else if ("com.huawei.health.SERVER_TOKEN_INVALIDE".equals(action)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "ACTION_HEALTH_SERVER_TOKEN_INVALIDE");
                    HuaweiServerTokenNotification.b(this.r);
                } else if ("event_manual_ui".equals(action)) {
                    SportIntensity.a(this.r).e(4);
                } else if ("com.huawei.health.action.ACTION_WEAR_DEVICE_CAPABILITY".equals(action)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "ACTION_WEAR_DEVICE_CAPABILITY");
                    akE_(intent);
                } else if ("com.huawei.health.start_step_counter".equals(action)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "START_STEP_COUNTER");
                    jdh.c().a(20220112);
                    if (StepCounterSupportUtils.j(this.r)) {
                        m();
                    } else {
                        ae();
                    }
                } else if ("com.huawei.health.check_sensor_state".equals(action)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "CHECK_SENSOR_STATE");
                    r();
                } else if ("com.huawei.health.AUTHORIZATION_CHANGED".equals(action)) {
                    u();
                } else if ("com.huawei.health.SYNC_DB_FOR_DEVICE_CHANGE".equals(action)) {
                    if (this.ae && ScreenUtil.a()) {
                        al();
                    } else {
                        this.ar = true;
                    }
                } else {
                    com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "processHandleBroadcastAction intent does not match");
                }
            } catch (BadParcelableException e2) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", e2.getMessage());
            }
        }
    }

    private void u() {
        boolean a2 = AuthorizationUtils.a(this.r);
        this.ab = a2;
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "processAuthChanged AuthorizationStatus = ", Boolean.valueOf(a2));
        if (a2) {
            if (StepCounterSupportUtils.c(this.r)) {
                m();
                StepCounterSupport.c(this.r, -1);
            }
            q();
            b(this.az);
            aw();
            return;
        }
        if (StepCounterSupportUtils.c(this.r)) {
            o();
        }
    }

    public boolean b() {
        return this.ab;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aw() {
        ArrayList<Integer> arrayList = new ArrayList<>(16);
        this.bm = arrayList;
        arrayList.add(1);
        if (!niv.a(this.r)) {
            this.bm.add(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR.value()));
            this.bm.add(103);
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "mTypeList is ", this.bm);
        HiHealthNativeApi.a(this.r).subscribeHiHealthData(this.bm, new HiSubscribeListener(this) { // from class: com.huawei.health.manager.LogicalStepCounter$SportDataCallback
            private WeakReference<LogicalStepCounter> c;

            {
                this.c = new WeakReference<>(this);
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                LogicalStepCounter logicalStepCounter = this.c.get();
                if (logicalStepCounter == null) {
                    LogUtil.h("Step_LSC", "onResult logicalStepCounter == null");
                } else {
                    if (koq.b(list)) {
                        return;
                    }
                    ReleaseLogUtil.e("Step_LSC", "SportDataCallback success");
                    logicalStepCounter.az = list;
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i2, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                Handler handler;
                Handler handler2;
                Object[] objArr = new Object[10];
                objArr[0] = "SportIntensityCallback ";
                objArr[1] = hiHealthData == null ? null : hiHealthData.toString();
                objArr[2] = " type ";
                objArr[3] = Integer.valueOf(i2);
                objArr[4] = " client ";
                objArr[5] = hiHealthClient != null ? hiHealthClient.getHuid() : null;
                objArr[6] = "changeKey ";
                objArr[7] = str;
                objArr[8] = " time ";
                objArr[9] = Long.valueOf(j);
                LogUtil.a("Step_LSC", objArr);
                if (i2 == 1) {
                    LogicalStepCounter logicalStepCounter = this.c.get();
                    if (logicalStepCounter != null) {
                        handler = logicalStepCounter.br;
                        if (handler.hasMessages(1021)) {
                            return;
                        }
                        handler2 = logicalStepCounter.br;
                        handler2.sendEmptyMessageDelayed(1021, 3000L);
                        return;
                    }
                    LogUtil.h("Step_LSC", "logicalStepCounter == null");
                    return;
                }
                if (i2 == DicDataTypeUtil.DataType.ACTIVE_HOUR.value()) {
                    LogicalStepCounter logicalStepCounter2 = this.c.get();
                    if (logicalStepCounter2 != null) {
                        logicalStepCounter2.an();
                        return;
                    } else {
                        LogUtil.h("Step_LSC", "logicalStepCounter == null");
                        return;
                    }
                }
                if (i2 == 103) {
                    LogicalStepCounter logicalStepCounter3 = this.c.get();
                    if (logicalStepCounter3 == null) {
                        LogUtil.h("Step_LSC", "logicalStepCounter is null");
                        return;
                    }
                    if ("900200006".equals(str) || "900200008".equals(str) || "900200009".equals(str) || "900200007".equals(str)) {
                        logicalStepCounter3.k();
                        logicalStepCounter3.b(true);
                        logicalStepCounter3.c(str);
                    } else if ("900200004".equals(str) || "900200010".equals(str) || "900200011".equals(str)) {
                        logicalStepCounter3.c(str);
                        logicalStepCounter3.e(str);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if ("900200004".equals(str)) {
            SharedPreferenceManager.e("fitness_step_module_id", "active_hours_set_alert_toggle", "1".equals(gjz.c(str)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        HiHealthNativeApi.a(this.r).aggregateHiHealthData(HiHealthHelper.b(), new LogicalStepCounter$ReadStaticDealCallback(this, PointerIconCompat.TYPE_GRAB));
    }

    private void aa() {
        af();
        ThirdLoginDataStorageUtil.getAccessTokenFromDb();
        aq();
        ScreenOnStatisticsUtil.e(this.r);
    }

    private void z() {
        ab();
        if (isProcessNewDayReport(false)) {
            SportIntensityUtil.d();
        }
        y();
        p();
    }

    private void ae() {
        if (!StepCounterSupportUtils.e(this.r)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", " processStartStepCounter failed with cant step counter.");
            return;
        }
        StandStepCounter standStepCounter = this.bd;
        if (standStepCounter != null) {
            standStepCounter.c();
        }
    }

    private void akz_(Intent intent) {
        String stringExtra = intent.getStringExtra("extra_user_intent");
        if (stringExtra != null) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "ACTION_RESTART_SENSOR_MSG userIntent:", stringExtra);
            StandStepCounter standStepCounter = this.bd;
            if (standStepCounter != null) {
                standStepCounter.d();
            }
            if ("extra_user_intent_pre_alarm".equals(stringExtra)) {
                t();
            }
        }
    }

    private void akE_(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("WEAR_DEVICE_CAPBILITY", false);
        String[] ac = SharedPerferenceUtils.ac(this.r);
        long currentTimeMillis = System.currentTimeMillis();
        if (ac != null) {
            try {
                if (ac.length <= 0 || jdl.d(currentTimeMillis, Long.parseLong(ac[0]))) {
                    return;
                }
                com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "processWearDeviceCapability wearDeviceCapability ", ac);
                SharedPerferenceUtils.c(this.r, booleanExtra, currentTimeMillis);
            } catch (NumberFormatException e2) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "processWearDeviceCapability Exception = ", e2.getMessage());
            }
        }
    }

    private void ac() {
        if (this.br != null) {
            this.br.sendMessage(this.br.obtainMessage(1006));
        }
    }

    private void akx_(Intent intent) {
        int intExtra = intent.getIntExtra("refresh_type", 0);
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "ACTION_SYNC_WITH_DB refreshType", Integer.valueOf(intExtra));
        if (intExtra == 0) {
            if (this.br != null) {
                this.br.sendMessage(this.br.obtainMessage(1012));
                v();
                return;
            }
            return;
        }
        if (intExtra == 2) {
            if (this.br != null) {
                this.br.sendMessage(this.br.obtainMessage(1012));
                return;
            }
            return;
        }
        if (intExtra == 5) {
            if (this.br != null) {
                v();
            }
        } else {
            if (intExtra == 7) {
                Intent intent2 = new Intent("DaemonService_LocalBroadcast");
                intent2.setPackage(this.r.getPackageName());
                intent2.putExtra("refresh_type", 7);
                LocalBroadcastManager.getInstance(this.r).sendBroadcast(intent2);
                return;
            }
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "ACTION_SYNC_WITH_DB no refreshType");
        }
    }

    private void am() {
        if (jdl.d(this.bi, System.currentTimeMillis())) {
            return;
        }
        a(null, true, false);
        ExtendStepDataManager extendStepDataManager = this.y;
        if (extendStepDataManager != null) {
            extendStepDataManager.e();
        }
        isProcessNewDayReport(true);
        n();
    }

    private void af() {
        StandStepCounter standStepCounter = this.bd;
        if (standStepCounter != null) {
            standStepCounter.e();
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "processScreenOn :", Long.valueOf(this.aj), Long.valueOf(System.currentTimeMillis()));
        long j = this.aj;
        if (j != -1 && j <= System.currentTimeMillis() && this.br != null) {
            this.aj = -1L;
            if (this.br.hasMessages(1012)) {
                this.br.removeMessages(1012);
            }
            this.br.sendEmptyMessageDelayed(1012, 1000L);
        }
        if (!isProcessNewDayReport(false) && this.br != null) {
            Message obtainMessage = this.br.obtainMessage(1006);
            obtainMessage.arg1 = 200;
            obtainMessage.arg2 = 201;
            this.br.sendMessage(obtainMessage);
        }
        r();
    }

    private void r() {
        if (StepCounterSupportUtils.j(this.r)) {
            if (StandStepCounter.a() || MidwareStepCounter.d()) {
                return;
            }
            boolean z = ContextCompat.checkSelfPermission(this.r, "android.permission.ACTIVITY_RECOGNITION") == 0;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", " isModelControlledByPermission permission is granted ", Boolean.valueOf(z));
            if (z) {
                m();
                return;
            }
            return;
        }
        if (StandStepCounter.a()) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "sensor register successfully");
            return;
        }
        try {
            boolean z2 = ContextCompat.checkSelfPermission(this.r, "android.permission.ACTIVITY_RECOGNITION") == 0;
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", " permission is granted ", Boolean.valueOf(z2));
            if (z2) {
                ae();
            }
        } catch (SecurityException unused) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "checkRegisterSensor SecurityException ");
        }
    }

    private void ag() {
        this.j.set(true);
        if (this.br != null) {
            this.br.sendEmptyMessage(1003);
            this.br.sendEmptyMessage(1012);
        }
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager != null) {
            standStepDataManager.a(this.bo, this.ay, this.o, this.bc, this.av);
        }
    }

    private void akw_(Message message) {
        if (!koq.e(message.obj, HiHealthData.class)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "processReadCallback ", "msg.obj invalid type");
            return;
        }
        List list = (List) message.obj;
        if (list.size() == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        long startTime = hiHealthData.getStartTime();
        if (!jdl.d(startTime, currentTimeMillis)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "data not same day,return dataStartTime:", Long.valueOf(startTime));
            return;
        }
        int i2 = hiHealthData.getInt("run_step");
        int i3 = hiHealthData.getInt(ParsedFieldTag.STEP_SUM);
        int i4 = hiHealthData.getInt("calorie_sum");
        int i5 = hiHealthData.getInt("altitude_sum");
        int i6 = hiHealthData.getInt("distance_sum");
        int i7 = hiHealthData.getInt("intensity_time");
        int i8 = hiHealthData.getInt("active_hours");
        if (this.bh != null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "procRCallback ", Integer.valueOf(i3), " insity ", Integer.valueOf(i7), " ActHs ", Integer.valueOf(i8), "totalCalories ", Integer.valueOf(i4));
            e(i3, i2, startTime);
            this.q.e(i3, i6);
            this.k.c(currentTimeMillis, i3, i4);
            this.bc = i7;
            this.av = Math.min(i8, 24);
            e(i5);
            aj();
            this.bh.a(System.currentTimeMillis());
        }
    }

    private void akB_(Message message) {
        if (!koq.e(message.obj, HiHealthData.class)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "processReadCallback ", "msg.obj invalid type");
            return;
        }
        List list = (List) message.obj;
        if (list.size() == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (!jdl.d(hiHealthData.getStartTime(), currentTimeMillis)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "data not same day,return");
            return;
        }
        int i2 = hiHealthData.getInt(ParsedFieldTag.STEP_SUM);
        hiHealthData.getInt("calorie_sum");
        int i3 = hiHealthData.getInt("intensity_time");
        int i4 = hiHealthData.getInt("active_hours");
        this.bc = i3;
        this.av = Math.min(i4, 24);
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "processSportDataCallback ", Integer.valueOf(i2), " totalIntensity ", Integer.valueOf(i3), " totalActiveHours ", Integer.valueOf(i4));
        d(true);
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager != null) {
            standStepDataManager.a(System.currentTimeMillis());
        }
    }

    private void ab() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "alreadySport", KeyValDbManager.b(this.r).e("already_sport"));
        MemoryMonitor.c(!String.valueOf(true).equals(r0));
    }

    private void aj() {
        if (this.br != null) {
            Message obtainMessage = this.br.obtainMessage(1006);
            obtainMessage.arg1 = 200;
            this.br.sendMessage(obtainMessage);
        }
    }

    private void aky_(Message message) {
        if (this.br != null) {
            this.br.removeMessages(1006);
        }
        boolean z = message.arg2 != 201 ? message.arg1 == 200 : true;
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager == null || standStepDataManager.b() < 0) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "MSG_REPORT_STEP_DATA dropreport ");
            this.ba = 0;
            this.aw = 0;
            this.at = 0;
            this.au = 0;
            return;
        }
        d(z);
    }

    public void e(LocalStepDataReport localStepDataReport) {
        if (localStepDataReport != null) {
            this.bj.d(localStepDataReport);
        }
    }

    public void c(boolean z) {
        this.ak = z;
    }

    private void d(int i2, long j) {
        if (jdl.d(j, i2 * 60000)) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "refreshCheckNewDay: new day, detailDataTimestamp is ", Long.valueOf(j));
        isProcessNewDayReport(false);
    }

    private void d(boolean z) {
        long g2;
        if (this.bh == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("Step_LSC", "refreshReportData: mStandStepDataManager == null");
            return;
        }
        if (!z && isProcessNewDayReport(false)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "isNextDay, not refresh ");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int a2 = (int) TimeUtil.a(currentTimeMillis);
        String[] y = SharedPerferenceUtils.y(this.r);
        TotalDetailStepsCacheBean u = SharedPerferenceUtils.u(this.r);
        if (u != null) {
            g2 = u.f();
        } else {
            g2 = y != null ? CommonUtil.g(y[0]) : 0L;
        }
        d(a2, g2);
        this.ba = this.bh.b();
        this.bb = a2;
        a(a2 - 1, y);
        this.at = this.q.e(this.ba);
        this.aw = this.k.c(currentTimeMillis, this.ba);
        if (this.ak) {
            this.au = (int) this.y.a();
        } else {
            this.au = 0;
        }
        this.au = (int) (this.au + this.h);
        StepsRecord stepsRecord = new StepsRecord();
        stepsRecord.g = this.ba;
        stepsRecord.c = this.bh.a();
        stepsRecord.d = this.aw;
        stepsRecord.f13139a = this.at;
        stepsRecord.b = this.au;
        stepsRecord.i = this.x.e();
        stepsRecord.j = this.bc;
        stepsRecord.e = this.av;
        this.bl.d(stepsRecord);
        this.bj.c(-1L, stepsRecord);
        this.bj.c(z);
        a(stepsRecord);
        ap();
    }

    private void ap() {
        if (this.ba > 0) {
            DaemonServiceSpUtils.j();
            long a2 = DaemonServiceSpUtils.a();
            if (a2 <= 0) {
                return;
            }
            DaemonServiceSpUtils.b(0L);
            gnj.e(a2, this.ba);
        }
    }

    private void a(StepsRecord stepsRecord) {
        if (stepsRecord.e == 0 && stepsRecord.j == 0 && stepsRecord.d == 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("activeHourType", stepsRecord.e);
        bundle.putInt("intensityType", stepsRecord.j);
        bundle.putInt("caloriesType", stepsRecord.d);
        gja.a().aNf_("TodayAchievement", bundle);
    }

    @Override // com.huawei.health.connectivity.standstepcounter.StandStepDataManager.IGetCacheTodaySteps
    public boolean isProcessNewDayReport(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager == null || !standStepDataManager.e(currentTimeMillis, z)) {
            return false;
        }
        this.bl.a(this.bh.c());
        if (this.bd != null && SharedPerferenceUtils.p(this.r)) {
            this.bd.d();
        }
        ExtendStepDataManager extendStepDataManager = this.y;
        if (extendStepDataManager != null) {
            extendStepDataManager.d();
            this.y.e();
        }
        this.bi = currentTimeMillis;
        this.w = false;
        this.bo = 0;
        this.ay = 0;
        this.o = 0;
        this.h = 0.0d;
        this.q.e();
        this.k.a(currentTimeMillis);
        this.aw = 0;
        this.au = 0;
        this.at = 0;
        this.bc = 0;
        this.av = 0;
        SharedPerferenceUtils.a(this.r, System.currentTimeMillis(), 0.0d);
        SharedPerferenceUtils.c(this.r, System.currentTimeMillis(), 0.0d);
        SharedPerferenceUtils.b(this.r, System.currentTimeMillis(), 0.0d);
        SharedPerferenceUtils.d(this.r, System.currentTimeMillis(), 0);
        if (this.br != null) {
            Message obtainMessage = this.br.obtainMessage(1006);
            obtainMessage.arg1 = 200;
            obtainMessage.arg2 = 201;
            this.br.sendMessage(obtainMessage);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("activeHourType", 0);
        bundle.putInt("intensityType", 0);
        bundle.putInt("caloriesType", 0);
        gja.a().aNf_("TodayAchievement", bundle);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final boolean z) {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add("900200006");
        arrayList.add("900200008");
        arrayList.add("900200009");
        arrayList.add("900200007");
        nip.a(arrayList, new IBaseResponseCallback() { // from class: com.huawei.health.manager.LogicalStepCounter$3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            public void onResponse(int i2, Object obj) {
                GoalValue goalValue;
                if (!(obj instanceof HashMap)) {
                    LogUtil.h("Step_LSC", "insertSportAchievementGoalVal objData is not instanceof HashMap");
                    return;
                }
                HashMap hashMap = (HashMap) obj;
                goalValue = LogicalStepCounter.this.x;
                int e2 = nip.e(hashMap, "900200006", goalValue.e());
                int e3 = nip.e(hashMap, "900200008", 25);
                int e4 = nip.e(hashMap, "900200009", 12);
                int e5 = nip.e(hashMap, "900200007", 270000);
                LogUtil.a("Step_LSC", "insertSportAchievementGoalVal stepValue is ", Integer.valueOf(e2), " intensityValue is ", Integer.valueOf(e3), " standValue is ", Integer.valueOf(e4), " calorieGoalValue is ", Integer.valueOf(e5));
                LogicalStepCounter.this.d(e2, e3, e4, e5);
                if (z) {
                    LogicalStepCounter.this.e(e2, e3, e4, e5);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2, int i3, int i4, int i5) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE.value()), Integer.valueOf(HiHealthHelper.c(i2, this.ba)));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE.value()), Integer.valueOf(HiHealthHelper.c(i3, this.bc)));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE.value()), Integer.valueOf(HiHealthHelper.c(i4, this.av)));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE.value()), Integer.valueOf(HiHealthHelper.c(i5, this.aw)));
        nix.c().a(this.r, hashMap, System.currentTimeMillis(), new IBaseResponseCallback() { // from class: com.huawei.health.manager.LogicalStepCounter$4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            public void onResponse(int i6, Object obj) {
                LogUtil.a("Step_LSC", "saveAchieveStatus errorCode ", Integer.valueOf(i6));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        Intent intent = new Intent("com.huawei.health.ACTION_SEND_SPORT_GOAL");
        intent.putExtra("configId", str);
        BroadcastManagerUtil.bFG_(this.r, intent);
    }

    private void d(HashMap<Integer, Integer> hashMap) {
        if (ThreeCircleConfigUtil.d(ThreeCircleConfigUtil.ThreeCircleConfig.CALORIE)) {
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value()), 1);
        }
        if (ThreeCircleConfigUtil.d(ThreeCircleConfigUtil.ThreeCircleConfig.ACTIVE_HOUR)) {
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING.value()), 1);
        }
        if (ThreeCircleConfigUtil.d(ThreeCircleConfigUtil.ThreeCircleConfig.STEP)) {
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING.value()), 1);
        }
        if (ThreeCircleConfigUtil.d(ThreeCircleConfigUtil.ThreeCircleConfig.INTENSITY)) {
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING.value()), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2, int i3, int i4, int i5) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value()), Integer.valueOf(i2));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value()), Integer.valueOf(i3));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value()), Integer.valueOf(i4));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value()), Integer.valueOf(i5));
        d(hashMap);
        nix.c().a(this.r, hashMap, System.currentTimeMillis(), new IBaseResponseCallback() { // from class: com.huawei.health.manager.LogicalStepCounter$5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            public void onResponse(int i6, Object obj) {
                LogUtil.a("Step_LSC", "new day saveSportGoalAchievementData errorCode ", Integer.valueOf(i6));
                if (i6 == 0) {
                    BroadcastManagerUtil.bFG_(BaseApplication.getContext(), new Intent("UPDATE_THREE_GOAL"));
                }
            }
        });
    }

    private void y() {
        ProcessAliveMonitor processAliveMonitor = this.ao;
        if (processAliveMonitor == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "processAliveMonitorCheck mProcessAliveMonitor == null");
        } else {
            processAliveMonitor.a();
        }
    }

    private void ao() {
        StandStepCounter standStepCounter = this.bd;
        if (standStepCounter != null) {
            standStepCounter.b();
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "stopStepCounterInter stand");
        }
        ExtendStepCounter extendStepCounter = this.v;
        if (extendStepCounter != null) {
            extendStepCounter.stopStepCounter();
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "stopStepCounterInter extend");
        }
        this.al = false;
    }

    private void ar() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "startStepCounterInter mStandStepCounter = ", this.bd);
        StandStepCounter standStepCounter = this.bd;
        if (standStepCounter != null) {
            standStepCounter.c();
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "startStepCounterInter stand");
        }
        ExtendStepCounter extendStepCounter = this.v;
        if (extendStepCounter != null) {
            extendStepCounter.startStepCounter();
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "startStepCounterInter extend");
        }
        this.al = true;
    }

    public void b(ISimpleResultCallback iSimpleResultCallback) {
        a(iSimpleResultCallback, false);
    }

    public void a(ISimpleResultCallback iSimpleResultCallback, boolean z) {
        com.huawei.hwlogsmodel.LogUtil.c("Step_LSC", "flushTempCacheToDb enter... callback:", iSimpleResultCallback, " flushByUI:", Boolean.valueOf(z));
        if (this.br != null) {
            Message obtainMessage = this.br.obtainMessage(1001);
            LogicalStepCounter$FlushDbMsg logicalStepCounter$FlushDbMsg = new LogicalStepCounter$FlushDbMsg();
            logicalStepCounter$FlushDbMsg.d = iSimpleResultCallback;
            obtainMessage.obj = logicalStepCounter$FlushDbMsg;
            this.br.sendMessage(obtainMessage);
        }
    }

    public Bundle akH_() {
        StepsRecordManager stepsRecordManager = this.bj;
        if (stepsRecordManager == null) {
            return null;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "getTodaySportData mStepsRecordManager=", stepsRecordManager);
        return this.bj.a().aaD_();
    }

    public Bundle akG_() {
        StepsRecord a2;
        StepsRecordManager stepsRecordManager = this.bj;
        if (stepsRecordManager == null || (a2 = stepsRecordManager.a()) == null) {
            return null;
        }
        Bundle aaD_ = a2.aaD_();
        aaD_.putInt("cacheTotalSteps", getCacheTodaySteps());
        String t = SharedPerferenceUtils.t(this.r);
        if (!TextUtils.isEmpty(t)) {
            aaD_.putString("serviceRestartRecord", t);
        }
        Map<String, Object> d2 = this.bh.d();
        if (d2 != null) {
            aaD_.putInt("standardBase", ((Integer) d2.get("standardBase")).intValue());
            aaD_.putInt("restartSteps", ((Integer) d2.get("restartSteps")).intValue());
            aaD_.putInt("otherSteps", ((Integer) d2.get("otherSteps")).intValue());
            aaD_.putInt("UIShowSteps", ((Integer) d2.get("UIShowSteps")).intValue());
        }
        return aaD_;
    }

    public boolean g() {
        ProcessAliveMonitor processAliveMonitor = this.ao;
        if (processAliveMonitor == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "isNeedPromptKeepAlive mProcessAliveMonitor is null.");
            return false;
        }
        return processAliveMonitor.d();
    }

    public void l() {
        ProcessAliveMonitor processAliveMonitor = this.ao;
        if (processAliveMonitor == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "makePromptNoSense processAliveMonitor null,failed,return");
        } else {
            processAliveMonitor.e();
        }
    }

    private void q() {
        if (!AuthorizationUtils.a(this.r)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "getDeviceId fail, authorization not admitted");
            return;
        }
        String androidId = FoundationCommonUtil.getAndroidId(this.r);
        this.s = androidId;
        this.bl.b(androidId);
        ExtendStepDataManager extendStepDataManager = this.y;
        if (extendStepDataManager != null) {
            extendStepDataManager.b(this.s);
        }
    }

    private void v() {
        HiHealthManager.d(this.r).fetchUserData(new HiCommonListener() { // from class: com.huawei.health.manager.LogicalStepCounter$6
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i2, Object obj) {
                List<HiUserInfo> list;
                Handler handler;
                LogUtil.c("Step_LSC", "getUserInfoFromDb");
                if (obj == null) {
                    ReleaseLogUtil.d("Step_LSC", "getUserInfoFromDb, data is null.");
                    return;
                }
                try {
                    list = (List) obj;
                } catch (ClassCastException e2) {
                    LogUtil.h("Step_LSC", e2.getMessage());
                    list = null;
                }
                if (list == null || list.size() == 0) {
                    ReleaseLogUtil.d("Step_LSC", "getUserInfoFromDb userInfoLists is null or userInfoList.size() == 0");
                    return;
                }
                for (HiUserInfo hiUserInfo : list) {
                    if (hiUserInfo.getRelateType() == 0) {
                        LogUtil.a("Step_LSC", "setUserInfo sync");
                        Bundle bundle = new Bundle();
                        bundle.putFloat("weight", hiUserInfo.getWeightOrDefaultValue());
                        bundle.putFloat("height", hiUserInfo.getHeightOrDefaultValue());
                        bundle.putInt("ownerId", hiUserInfo.getOwnerId());
                        bundle.putString("huid", hiUserInfo.getHuid());
                        bundle.putInt(CommonConstant.KEY_GENDER, hiUserInfo.getGenderOrDefaultValue());
                        bundle.putInt("age", hiUserInfo.getAgeOrDefaultValue());
                        Message obtain = Message.obtain();
                        obtain.what = 1016;
                        obtain.obj = bundle;
                        handler = LogicalStepCounter.this.br;
                        handler.sendMessage(obtain);
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i2, Object obj) {
                LogUtil.b("Step_LSC", "getUserInfo onFailure = ", obj);
            }
        });
    }

    public void akI_(Bundle bundle) {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "setUserInfo userSet ignore!!! from db refresh");
    }

    public void k() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "setStepsTarget");
        this.x.c();
        if (this.br != null) {
            this.br.sendMessage(this.br.obtainMessage(1006));
        }
        Context context = this.r;
        if (context != null) {
            SharedPerferenceUtils.b(context, this.x.e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ISimpleResultCallback iSimpleResultCallback) {
        if (iSimpleResultCallback != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("result", true);
            iSimpleResultCallback.onSuccess(bundle);
        }
    }

    private void as() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "registerBrowseBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        DaemonBrowseModeReceiver daemonBrowseModeReceiver = new DaemonBrowseModeReceiver(this);
        this.n = daemonBrowseModeReceiver;
        Context context = this.r;
        if (context != null) {
            BroadcastManagerUtil.bFC_(context, daemonBrowseModeReceiver, intentFilter, LocalBroadcast.c, null);
        }
    }

    private void at() {
        Context context;
        DaemonBrowseModeReceiver daemonBrowseModeReceiver = this.n;
        if (daemonBrowseModeReceiver == null || (context = this.r) == null) {
            return;
        }
        context.unregisterReceiver(daemonBrowseModeReceiver);
        this.n = null;
    }

    private void a(final ISimpleResultCallback iSimpleResultCallback, boolean z, boolean z2) {
        if (this.ad) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_LSC", "browse mode not support write to db");
            return;
        }
        if (!this.ak) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "not support calss1,then uploadstaticdata");
            t();
            d(iSimpleResultCallback);
            return;
        }
        if (this.bh != null && !z && (this.ba != this.am || z2)) {
            t();
            this.am = this.ba;
        }
        this.y.c(new FlushableStepDataCache.MyFlushCallback() { // from class: com.huawei.health.manager.LogicalStepCounter$7
            @Override // com.huawei.health.manager.common.FlushableStepDataCache.MyFlushCallback
            public void onSuccess() {
                Handler handler;
                ExtendStepDataManager extendStepDataManager;
                Handler handler2;
                Handler handler3;
                Handler handler4;
                handler = LogicalStepCounter.this.br;
                if (handler != null) {
                    handler2 = LogicalStepCounter.this.br;
                    handler2.sendEmptyMessage(1003);
                    handler3 = LogicalStepCounter.this.br;
                    Message obtainMessage = handler3.obtainMessage(1006);
                    obtainMessage.arg1 = 200;
                    handler4 = LogicalStepCounter.this.br;
                    handler4.sendMessage(obtainMessage);
                }
                extendStepDataManager = LogicalStepCounter.this.y;
                extendStepDataManager.d();
                LogicalStepCounter.this.d(iSimpleResultCallback);
                LogUtil.a("Step_LSC", "flush2DB result success");
            }

            @Override // com.huawei.health.manager.common.FlushableStepDataCache.MyFlushCallback
            public void onFailed() {
                LogUtil.a("Step_LSC", "flush2DB result error");
            }
        });
    }

    public void d() {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("R_Step_LSC", "destroy.");
        if (this.br != null) {
            this.br.removeMessages(1000);
            this.br.removeMessages(1001);
            this.br.removeMessages(1006);
            this.br.removeMessages(1003);
            this.br.removeMessages(1011);
        }
        HandlerThread handlerThread = this.bn;
        if (handlerThread != null) {
            handlerThread.quit();
            this.bn = null;
        }
        SportIntensity.a(this.r).e();
        ax();
        at();
        BroadcastManager.wx_(this.u);
        b(this.bk);
        gja.a().e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Integer> list) {
        if (koq.c(list)) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "unSubscribeSportData");
            HiHealthNativeApi.a(this.r).unSubscribeHiHealthData(list, new HiUnSubscribeListener() { // from class: com.huawei.health.manager.LogicalStepCounter$8
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public void onResult(boolean z) {
                    ReleaseLogUtil.e("Step_LSC", "unSubscribeSportData isSuccess = ", Boolean.valueOf(z));
                }
            });
        }
    }

    public void c(LocalStepDataReport localStepDataReport) {
        e(localStepDataReport);
        if (this.br != null) {
            this.br.sendEmptyMessage(1006);
        }
    }

    public void b(long j, int i2, int i3, int i4) {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "setBaseData mWorkerHandler ", this.br, " steps ", LogAnonymous.b(i2));
    }

    public void m() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "startStepCounter enter...");
        if (!StepCounterSupportUtils.e(this.r)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", " processStartStepCounter failed with cant step counter.");
            return;
        }
        SharedPerferenceUtils.b(this.r, true);
        if (this.br != null) {
            Message obtainMessage = this.br.obtainMessage(1005);
            obtainMessage.arg1 = 100;
            this.br.sendMessage(obtainMessage);
        }
    }

    public void o() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "stopStepCounter");
        SharedPerferenceUtils.b(this.r, false);
        if (this.br != null) {
            Message obtainMessage = this.br.obtainMessage(1005);
            obtainMessage.arg1 = 101;
            this.br.sendMessage(obtainMessage);
        }
    }

    public boolean i() {
        return this.al;
    }

    public void b(long j, int i2, int i3) {
        if (this.br != null) {
            Message obtainMessage = this.br.obtainMessage(1000);
            obtainMessage.arg1 = i2;
            obtainMessage.obj = Long.valueOf(j);
            obtainMessage.arg2 = i3;
            this.br.sendMessage(obtainMessage);
        }
    }

    public int c() {
        if (this.af.get()) {
            return this.be;
        }
        Object obj = d;
        synchronized (obj) {
            if (!this.af.get()) {
                try {
                    obj.wait(3000L);
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "getStandSteps timeout");
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), 1002);
                } catch (InterruptedException unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("Step_LSC", "getStandSteps exception");
                }
            }
        }
        return this.be;
    }

    public boolean f() {
        return this.af.get();
    }

    private void j(int i2) {
        DeviceClassWatchDog deviceClassWatchDog = this.m;
        if (deviceClassWatchDog == null || !deviceClassWatchDog.a() || !this.m.d(i2) || this.br == null) {
            return;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_LSC", "reduce class");
        this.br.sendEmptyMessage(1011);
    }

    private void i(int i2) {
        if (this.ai) {
            this.as = i2;
            this.ai = false;
        }
        int i3 = i2 - this.as;
        if (i3 > 100) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "standStepCounter report 100 steps,write fe");
            if (this.br != null) {
                this.br.sendMessage(this.br.obtainMessage(1003));
            }
            this.ai = true;
            return;
        }
        if (i3 > 0) {
            if (this.br != null) {
                this.br.removeMessages(1010);
                this.br.sendEmptyMessageDelayed(1010, OpAnalyticsConstants.H5_LOADING_DELAY);
                return;
            }
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "tryToRecordStepsToFileCache default.");
    }

    public void e(long j, int i2, int i3, int i4) {
        long j2 = j;
        if (this.j.get()) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "onExtendStepChanged phone shutdown, return");
            return;
        }
        this.p = j2;
        if (this.ap != j2) {
            this.ag = false;
            this.ap = j2;
            this.bf = System.currentTimeMillis();
        } else if (this.an) {
            this.ag = true;
        } else if (!this.ag && System.currentTimeMillis() - this.bf >= 60000) {
            this.ag = true;
        }
        if (this.ag) {
            com.huawei.hwlogsmodel.LogUtil.c("Step_LSC", "mIsCalibrateTime true,timestamp before:", Long.valueOf(j));
            j2 = (TimeUtil.c(System.currentTimeMillis()) * 1000) + 7;
            com.huawei.hwlogsmodel.LogUtil.c("Step_LSC", "mIsCalibrateTime after,timestamp before:", Long.valueOf(j2));
        }
        long j3 = j2;
        com.huawei.hwlogsmodel.LogUtil.c("Step_LSC", "onExtend:", Long.valueOf(j3), " ", Integer.valueOf(i2), " ", Integer.valueOf(i3), " ", Integer.valueOf(i4));
        long j4 = this.t;
        if (j3 < j4) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "timestamp back,mExtendReportTimestamp = ", Long.valueOf(j4));
            this.an = true;
        } else {
            this.an = false;
        }
        this.t = j3;
        DeviceClassWatchDog deviceClassWatchDog = this.m;
        if (deviceClassWatchDog != null) {
            deviceClassWatchDog.e();
        }
        e(i2, i3, i4, j3);
    }

    private void e(int i2, int i3, int i4, long j) {
        int i5 = i3 > 0 ? 6 : i4;
        d(j, i2, i3, i4);
        LogicalStepCounter$ExtendStepMsg logicalStepCounter$ExtendStepMsg = new LogicalStepCounter$ExtendStepMsg(j, i2, i3 * 30, i5);
        if (this.br != null) {
            Message obtainMessage = this.br.obtainMessage();
            obtainMessage.what = 1004;
            obtainMessage.obj = logicalStepCounter$ExtendStepMsg;
            this.br.sendMessage(obtainMessage);
        }
        UploadUtil uploadUtil = this.bl;
        if (uploadUtil != null && uploadUtil.e(i2)) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "flushTempCacheToDb by ext");
            b((ISimpleResultCallback) null);
        }
        long j2 = this.aj;
        if (j2 == -1 || j2 > j || this.br == null) {
            return;
        }
        if (com.huawei.hwlogsmodel.LogUtil.b(60000, this.aj) == -1) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "send MSG_FLUSH_AND_SYNC_WITH_DB updateFrequency fast");
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "send MSG_FLUSH_AND_SYNC_WITH_DB message", Long.valueOf(this.aj), Long.valueOf(j));
        this.aj = -1L;
        this.br.sendMessage(this.br.obtainMessage(1012));
    }

    private void a(int i2, String[] strArr) {
        c(i2, strArr);
        ExtendStepDataManager extendStepDataManager = this.y;
        OneMinuteStepData d2 = extendStepDataManager != null ? extendStepDataManager.d(i2) : null;
        if (d2 != null) {
            int d3 = d2.d();
            if (d3 == 20003) {
                this.ay += d2.b();
            } else if (d3 == 20004) {
                this.o += d2.b();
            } else {
                this.bo += d2.b();
            }
        }
        TotalDetailStepsCacheBean.Builder builder = new TotalDetailStepsCacheBean.Builder();
        builder.c(i2 * 60000);
        builder.c(this.bh.b());
        builder.i(this.bo);
        builder.d(this.ay);
        builder.a(this.o);
        builder.a(SystemClock.elapsedRealtime());
        builder.e(this.bc);
        builder.b(this.av);
        SharedPerferenceUtils.e(this.r, builder.c());
    }

    private void c(int i2, String[] strArr) {
        if (strArr == null) {
            return;
        }
        try {
            long parseLong = Long.parseLong(strArr[0]);
            int parseInt = Integer.parseInt(strArr[2]);
            int parseInt2 = Integer.parseInt(strArr[3]);
            int parseInt3 = Integer.parseInt(strArr[4]);
            if (jdl.d(parseLong, i2 * 60000)) {
                this.bo = parseInt;
                this.ay = parseInt2;
                this.o = parseInt3;
            }
        } catch (NumberFormatException e2) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "stepsDetailCalculation Exception", e2.getMessage());
        }
    }

    @Override // com.huawei.health.connectivity.standstepcounter.StandStepDataManager.IGetCacheTodaySteps
    public int getCacheTodaySteps() {
        ExtendStepDataManager extendStepDataManager = this.y;
        if (extendStepDataManager != null) {
            return extendStepDataManager.f();
        }
        return 0;
    }

    private void ai() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "CLASS1 change to CLASS2");
        StepCounterSupport.c(this.r, 2);
        this.ak = false;
        this.bg.e();
        this.v.stopStepCounter();
        ExtendStepCounter d2 = this.bg.d();
        this.v = d2;
        d2.startStepCounter();
        this.q.reduceClass();
        this.k.reduceClass();
    }

    public void t() {
        StandStepDataManager standStepDataManager = this.bh;
        if (standStepDataManager != null) {
            if (standStepDataManager.i()) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_LSC", "today has no steps report");
            } else {
                this.bl.b(this.bh.b(), this.bh.a(), this.aw, this.au, this.at);
            }
        }
    }

    public void e(IStepReport iStepReport) {
        if (this.ax != null) {
            synchronized (b) {
                this.ax.add(iStepReport);
            }
        }
    }

    public void c(IStepReport iStepReport) {
        ArrayList<IStepReport> arrayList = this.ax;
        if (arrayList == null || !arrayList.contains(iStepReport)) {
            return;
        }
        synchronized (b) {
            this.ax.remove(iStepReport);
        }
    }

    private void d(long j, int i2, int i3, int i4) {
        if (koq.b(this.ax)) {
            return;
        }
        synchronized (b) {
            Iterator<IStepReport> it = this.ax.iterator();
            while (it.hasNext()) {
                IStepReport next = it.next();
                if (next != null) {
                    next.onExtendStepChanged(j, i2, i3, i4);
                }
            }
        }
    }

    private void a(int i2) {
        ArrayList<IStepReport> arrayList = this.ax;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        synchronized (b) {
            Iterator<IStepReport> it = this.ax.iterator();
            while (it.hasNext()) {
                IStepReport next = it.next();
                if (next != null) {
                    next.onStandStepChanged(i2);
                }
            }
        }
    }

    public void p() {
        Context context;
        if (!ScreenUtil.a() || (context = this.r) == null) {
            return;
        }
        if (!Boolean.TRUE.toString().equals(SharedPerferenceUtils.s(context))) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_LSC", "updateStepNotifyLayout StepsNotification switch is close or unset");
            return;
        }
        aq();
        StepsRecordManager stepsRecordManager = this.bj;
        if (stepsRecordManager == null || this.z == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "updateStepNotifyLayout data null");
            return;
        }
        StepsRecord a2 = stepsRecordManager.a();
        if (a2 == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_LSC", "updateStepNotifyLayout stepsRecord == null");
        } else {
            this.z.b(a2.g, a2.d, a2.i, a2.e);
        }
    }

    private void aq() {
        if (this.z == null) {
            this.z = HealthStepsNotificationHelper.d(this.r);
        }
        this.z.a(NotificationUtil.j());
        this.z.d(NotificationUtil.f());
    }
}

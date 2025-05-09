package defpackage;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sport.model.CourseEnvParams;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportComponent;
import com.huawei.health.sportservice.SportVoiceEnableListener;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.TrackAltitudeMgr;
import com.huawei.healthcloud.plugintrack.manager.config.TrackDevelopConfig;
import com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater;
import com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportDataFragmentListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportDistanceTimeCallback;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.IStepRateUpdater;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITrackPointDataUpdater;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITreadmillStyleCallback;
import com.huawei.healthcloud.plugintrack.manager.inteface.IUpGpsStatusListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.LoadHotTrackCallBack;
import com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback;
import com.huawei.healthcloud.plugintrack.model.IStepRateCallback;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.model.SportBeat;
import com.huawei.healthcloud.plugintrack.model.TimeUpdater;
import com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicInteratorService;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.network.embedded.c6;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlocationmgr.model.IGpsStatusCallback;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.up.model.UserInfomation;
import defpackage.gsy;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gtx implements TimeUpdater, ITrackPointDataUpdater, IStepRateUpdater, IUpGpsStatusListener, ITargetUpdateListener {
    private static volatile gtx e = null;
    private static boolean f = false;
    private static boolean g = true;
    private static volatile PluginSportTrackAdapter j;
    private Context aa;
    private cab ad;
    private gtj af;
    private ScheduledExecutorService aq;
    private gwd at;
    private ExtendHandler au;
    private gtq az;
    private boolean bk;
    private boolean bl;
    private boolean bo;
    private boolean ck;
    private boolean cm;
    private boolean cu;
    private long cx;
    private IMapDrawingUpdater dm;
    private String dp;
    private String ds;
    private String dx;
    private gvp dz;
    private gui es;
    private cag et;
    private ITargetUpdateListener fh;
    private StorageParams fi;
    private String fm;
    private dwt fu;
    private TrackDevelopConfig fy;
    private dwq fz;
    private gsx gc;
    private gww ge;
    private UserInfomation gk;
    private guz m;
    private String p;
    private String q;

    /* renamed from: a, reason: collision with root package name */
    public static final Object f12936a = new Object();
    private static final Object b = new Object();
    private static final Object d = new Object();
    private static final Object c = new Object();
    private static List<ISportDataFragmentListener> i = new CopyOnWriteArrayList();
    private volatile boolean bb = false;
    private boolean cd = false;

    /* renamed from: do, reason: not valid java name */
    private MotionPath f122do = null;
    private volatile MotionPathSimplify dq = null;
    private List<hiy> ap = new ArrayList(16);
    private Map<Long, double[]> dn = new TreeMap();
    private boolean cj = false;
    private int ey = 0;
    private volatile int de = 0;
    private String er = null;
    private hjd ae = null;
    private hjd df = null;
    private long ag = 0;
    private boolean fs = true;
    private String ac = "";
    private IRidePostureDataCallback ei = new IRidePostureDataCallback() { // from class: gtx.5
        @Override // com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback
        public void onResult() {
        }

        @Override // com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback
        public void onChange(ffn ffnVar) {
            if (ffnVar == null) {
                return;
            }
            LogUtil.a("Track_SportManager", "ridePostureData.getSumCircle()", Long.valueOf(ffnVar.b()));
            gtx.this.g((int) ffnVar.b());
        }
    };
    private float fv = 0.0f;
    private float ak = 0.0f;
    private float fq = 0.0f;
    private dwl ct = null;
    private float fw = 0.0f;
    private float fr = 0.0f;
    private float ft = 0.0f;
    private float bm = 0.0f;
    private long ep = 0;
    private long ex = 0;
    private long as = 0;
    private int ew = 0;
    private int ff = 0;
    private int dr = 0;
    private int av = 0;
    private int eo = 0;
    private int w = 0;
    private List<IMapViewListener> dl = new CopyOnWriteArrayList();
    private int gh = 0;
    private boolean bj = false;
    private volatile boolean ca = false;
    private boolean cq = false;
    private boolean bn = false;
    private gup gg = null;
    private int y = 0;
    private int ay = 1;
    private ktg be = null;
    private gtl z = null;
    private boolean cf = false;
    private boolean cb = true;
    private long dc = 0;
    private long cy = 0;
    private int fd = 5000;
    private long r = 0;
    private long ai = 0;
    private final DecimalFormat x = new DecimalFormat("0.0");
    private long ax = 0;
    private long bc = 0;
    private long bi = 60;
    private long dt = TimeUnit.MINUTES.toMillis(this.bi);
    private long l = -1;
    private StringBuilder dj = new StringBuilder(16);
    private StringBuilder ef = new StringBuilder(16);
    private StringBuilder aw = new StringBuilder(16);
    private StringBuilder fb = new StringBuilder(16);
    private boolean cc = false;
    private boolean ce = false;
    private Map<Long, double[]> fk = new TreeMap();
    private HealthSportingNotificationHelper ev = null;
    private boolean co = false;
    private boolean bv = true;
    private boolean bt = true;
    private boolean cg = false;
    private int ee = 0;
    private int dg = 0;
    private int di = 0;
    private double dk = 0.0d;
    private double dh = 0.0d;
    private List<hiy> n = new ArrayList(16);
    private boolean cr = false;
    private boolean ci = true;
    private List<ISportStateChangeListener> eu = new CopyOnWriteArrayList();
    private boolean bz = false;
    private int gi = 1;
    private float cv = 0.0f;
    private int fc = -1;
    private int eb = 0;
    private int dd = 0;
    private int aj = 0;
    private int db = 0;
    private int am = 0;
    private float an = 0.5f;
    private float gm = 0.5f;
    private float v = 2.88f;
    private boolean bx = false;
    private gtk k = null;
    private float fx = 0.0f;
    private List<gxx> eq = new CopyOnWriteArrayList();
    private boolean cs = true;
    private Handler gl = null;
    private LinkedList<dwl> ea = null;
    private ISportDistanceTimeCallback dw = null;
    private long fo = 0;
    private long fp = 0;
    private int du = -1;
    private gul fa = null;
    private gwn eg = null;
    private gwl en = null;
    private gwm ek = null;
    private int ar = 0;
    private int ab = 0;
    private gum gb = null;
    private long t = -1;
    private long s = -1;
    private boolean bp = true;
    private boolean br = true;
    private guq gd = null;
    private int dy = 0;
    private long dv = 0;
    private long cz = 0;
    private boolean bw = true;
    private boolean bu = true;
    private int o = 0;
    private boolean cn = false;
    private int fl = 0;
    private long fj = 0;
    private long fn = 0;
    private boolean by = false;
    private boolean bq = false;
    private int gf = 1;
    private kvq ej = new kvq();
    private kvq eh = new kvq();
    private TrackAltitudeMgr ga = null;
    private boolean ch = false;
    private double ez = 0.0d;
    private int ah = 0;
    private fer fg = new fer();
    private boolean cl = false;
    private int cw = -1;
    private long da = 0;
    private gvq ec = new gvq();
    private volatile boolean cp = false;
    private volatile boolean bs = false;
    private String al = null;
    private String ed = null;
    private boolean ba = false;
    private volatile boolean h = true;
    private gtu el = new gtu();
    private gto bd = new gto();
    private long bf = -1;
    private Map<String, SportVoiceEnableListener> gj = Collections.synchronizedMap(new LinkedHashMap());
    private final IBaseResponseCallback ao = new IBaseResponseCallback() { // from class: gtz
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i2, Object obj) {
            gtx.this.c(i2, obj);
        }
    };
    private gtr bg = new gtr();
    private StringBuffer fe = new StringBuffer();
    private int bh = 0;
    private String em = "";
    private final guv u = new guv();

    private int l(int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 1) {
            return 0;
        }
        return i2;
    }

    /* synthetic */ void c(int i2, Object obj) {
        LogUtil.a("Track_SportManager", "mDeviceStepRateAlgorithmCallback errorCode: ", Integer.valueOf(i2));
        if (obj.toString().equals("true") || obj.toString().equals("false")) {
            m(Boolean.parseBoolean(obj.toString()));
            s(true);
        } else {
            LogUtil.h("Track_SportManager", "mDeviceStepRateAlgorithmCallback ", "objData =", obj.toString());
            s(false);
        }
    }

    private gtx(Context context) {
        this.aa = null;
        this.bk = true;
        this.fz = null;
        this.fu = null;
        this.dz = null;
        this.az = null;
        this.aa = context;
        dh();
        this.ge = new gww(this.aa, this.fi, this.dp);
        this.fm = this.aa.getResources().getString(R.string._2130839730_res_0x7f0208b2);
        TrackDevelopConfig trackDevelopConfig = new TrackDevelopConfig(this.aa);
        this.fy = trackDevelopConfig;
        this.fu = trackDevelopConfig.a();
        this.fz = new dwq(this.fu, this.dj, 1);
        er();
        gtq gtqVar = new gtq(j);
        this.az = gtqVar;
        gtqVar.i();
        ew();
        this.bk = CommonUtil.aw();
        this.dz = new gvp();
        this.gc = gsx.a();
    }

    public void i(boolean z) {
        this.cd = z;
    }

    public boolean bc() {
        return this.co;
    }

    public void b(boolean z) {
        this.co = z;
    }

    public int r() {
        return this.gh;
    }

    public int b() {
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            return gtqVar.d();
        }
        return 0;
    }

    public kvq f() {
        kvq kvqVar;
        synchronized (b) {
            kvqVar = this.ej;
        }
        return kvqVar;
    }

    public void b(kvq kvqVar) {
        synchronized (b) {
            this.eh = kvqVar;
            if (kvqVar != null) {
                LogUtil.a("Track_SportManager", "registerReportDataListener onChange time", Long.valueOf(kvqVar.m()));
            }
        }
    }

    public boolean u() {
        return this.bl;
    }

    public CopyOnWriteArrayList<koe> i() {
        gvq gvqVar = this.ec;
        if (gvqVar != null) {
            return gvqVar.d();
        }
        return null;
    }

    public CopyOnWriteArrayList<ffs> d() {
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            return gwlVar.e();
        }
        return null;
    }

    public static void e(ISportDataFragmentListener iSportDataFragmentListener) {
        if (iSportDataFragmentListener == null || i.contains(iSportDataFragmentListener)) {
            return;
        }
        i.add(iSportDataFragmentListener);
        LogUtil.a("Track_SportManager", "addSportDataFragmentListener thread: ", Integer.valueOf(Thread.currentThread().hashCode()), " listener: ", iSportDataFragmentListener);
    }

    public static void a(ISportDataFragmentListener iSportDataFragmentListener) {
        if (iSportDataFragmentListener != null) {
            try {
                if (i.contains(iSportDataFragmentListener)) {
                    i.remove(iSportDataFragmentListener);
                    ReleaseLogUtil.e("Track_SportManager", "complete remove listener");
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.h("Track_SportManager", "removeSportDataFragmentListener thread: ", Integer.valueOf(Thread.currentThread().hashCode()), " listener: ", iSportDataFragmentListener);
            }
        }
    }

    public void b(IMapDrawingUpdater iMapDrawingUpdater) {
        this.dm = iMapDrawingUpdater;
    }

    private static void el() {
        synchronized (c) {
            e = null;
        }
    }

    public static gtx c(Context context) {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    if (context == null) {
                        e = new gtx(BaseApplication.getContext());
                    } else {
                        e = new gtx(context.getApplicationContext());
                    }
                    LogUtil.a("Track_SportManager", "SportManager instance hashcode: ", Integer.valueOf(e.hashCode()));
                }
                if (j == null) {
                    j = gso.e().c();
                }
            }
        }
        return e;
    }

    public void e(String str, LoadHotTrackCallBack loadHotTrackCallBack) {
        this.bd.a(str, loadHotTrackCallBack);
    }

    public boolean ap() {
        return j == null;
    }

    public boolean au() {
        return this.cc;
    }

    public boolean at() {
        gtj gtjVar = this.af;
        if (gtjVar != null) {
            return gtjVar.e();
        }
        return false;
    }

    public boolean ar() {
        gtj gtjVar = this.af;
        if (gtjVar != null) {
            return gtjVar.c();
        }
        return false;
    }

    public hjd w() {
        return this.ae;
    }

    public void d(IMapViewListener iMapViewListener) {
        if (this.dl.contains(iMapViewListener)) {
            return;
        }
        this.dl.add(iMapViewListener);
    }

    public void b(IMapViewListener iMapViewListener) {
        this.dl.remove(iMapViewListener);
    }

    public void c(ISportStateChangeListener iSportStateChangeListener) {
        if (iSportStateChangeListener != null && !this.eu.contains(iSportStateChangeListener)) {
            this.eu.add(iSportStateChangeListener);
        }
        LogUtil.a("Track_SportManager", "registerSportStateChangeListener size=", Integer.valueOf(this.eu.size()));
    }

    public void b(ISportStateChangeListener iSportStateChangeListener) {
        if (iSportStateChangeListener != null && this.eu.contains(iSportStateChangeListener)) {
            this.eu.remove(iSportStateChangeListener);
        }
        LogUtil.a("Track_SportManager", "unregisterSportStateChangeListener size=", Integer.valueOf(this.eu.size()));
    }

    private void dh() {
        this.fi = new StorageParams();
        this.dp = Integer.toString(20002);
    }

    private void dp() {
        long j2 = this.ex;
        if (this.bo) {
            j2 = System.currentTimeMillis();
        }
        this.u.d(this.gk, j2, this.ew);
    }

    public int m() {
        return this.ey;
    }

    private void c(hjd hjdVar, long j2) {
        if (this.df == null) {
            ReleaseLogUtil.c("Track_SportManager", " mLastSportLocation == null");
            return;
        }
        LogUtil.c("Track_SportManager", "isHiGeoEnable:" + this.bg.h());
        if (this.bg.d()) {
            this.ak = this.bg.e() - this.bh;
        } else {
            this.ak = gwe.e(this.df, hjdVar);
        }
        if (this.ak > 0.0f) {
            if (this.cf) {
                long ce = this.ep + ce();
                this.ep = ce;
                this.dz.d(ce);
            }
            if (this.bz) {
                ReleaseLogUtil.e("R_Track_SportManager", "countSportDistance recovery from gps lost");
                LogUtil.a("Track_SportManager", "recovery from gps lost(mTotalSportDistance ?= mGpsTotalDistance) ", LogAnonymous.d((long) this.fv), " ?= ", LogAnonymous.b(((int) this.cv) + ((int) this.ak)));
                float f2 = this.fv;
                float f3 = this.cv + this.ak;
                if (f2 < f3) {
                    this.fv = f3;
                    this.ak = f3 - f2;
                } else {
                    this.ak = 0.0f;
                }
                this.cv = this.fv;
                p(false);
            } else {
                float f4 = this.fv;
                float f5 = this.ak;
                this.fv = f4 + f5;
                this.fq += f5;
            }
            ISportDistanceTimeCallback iSportDistanceTimeCallback = this.dw;
            if (iSportDistanceTimeCallback != null) {
                iSportDistanceTimeCallback.onDistanceChange((int) this.fv, j2 + this.fp, this.dn.size());
            }
            if (this.bg.d()) {
                this.bh = this.bg.e();
            }
        }
    }

    private void a(int i2, int i3, float f2) {
        if (i2 >= i3 || i2 == 0) {
            return;
        }
        k(this.ah);
        int i4 = i3 - i2;
        float f3 = i4;
        float f4 = f2 * f3;
        this.ak = f4;
        if (this.ew == 264) {
            this.bm = f3;
            this.ak = gvb.e(i4, ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(), 5, 0);
            LogUtil.a("Track_SportManager", "estimateSportDistanceBySteps before calibration distance: ", Float.valueOf(f4), " after calibration distance : ", Float.valueOf(this.ak), " , step is :", Integer.valueOf(i4));
        }
        if (this.ak > 0.0f) {
            this.l = System.currentTimeMillis();
            float f5 = this.fv + this.ak;
            this.fv = f5;
            LogUtil.a("Track_SportManager", "estimateSportDistanceBySteps curDistance : ", Float.valueOf(f5), " lastGpsDistance : ", Float.valueOf(this.cv));
            ReleaseLogUtil.e("R_Track_SportManager", "estimateSportDistanceBySteps current distance");
            if (this.dw == null || this.cr) {
                return;
            }
            if (!dz() || this.eo >= 5) {
                this.eo = 0;
                LogUtil.c("Track_SportManager", "System.currentTimeMillis() + mTimeDiffBetweenGpsAndSystem = ", Long.valueOf(System.currentTimeMillis() + this.fo));
                br();
            }
        }
    }

    private void k(int i2) {
        if (this.ff == 3) {
            gso.e().c().updateStepPoint(i2, System.currentTimeMillis());
        }
    }

    public void br() {
        if (this.dw == null) {
            LogUtil.b("Track_SportManager", "mPaceCallback is null");
            return;
        }
        gvp gvpVar = this.dz;
        if (gvpVar == null || !gvpVar.e(this.cf)) {
            return;
        }
        ISportDistanceTimeCallback iSportDistanceTimeCallback = this.dw;
        int i2 = (int) this.fv;
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.fo;
        iSportDistanceTimeCallback.onDistanceChange(i2, currentTimeMillis + j2 + this.fp, this.dn.size());
    }

    public float j() {
        return this.fv;
    }

    public float h() {
        if (UnitUtil.h()) {
            return (float) UnitUtil.e((((int) this.fv) * 1.0f) / 1000.0f, 3);
        }
        return (((int) this.fv) * 1.0f) / 1000.0f;
    }

    public float a() {
        fer ferVar = this.fg;
        if (ferVar != null) {
            return ferVar.c();
        }
        return 0.0f;
    }

    public int k() {
        if (dx()) {
            return this.fg.b().f();
        }
        return -1;
    }

    public int g() {
        fer ferVar = this.fg;
        if (ferVar != null) {
            return Math.round(ferVar.e() * 100.0f);
        }
        return 0;
    }

    public boolean bb() {
        return this.fv >= 10.0f;
    }

    private boolean dn() {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.l;
        if (j2 != -1) {
            return a(currentTimeMillis - j2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aTR_(Location location) {
        Bundle extras;
        if (location != null && (extras = location.getExtras()) != null) {
            this.bg.c(extras.getInt("fitDistance", -1) != -1);
            if (this.bg.h()) {
                this.bg.aTE_(extras);
            }
        }
        gwq.a().aVh_(location, this.ew);
        aTP_(location);
        aTZ_(location, true);
        if (this.bv && m968do()) {
            return;
        }
        aTZ_(location, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eq() {
        if (this.be == null || !this.h) {
            return;
        }
        int i2 = this.ew;
        String str = (i2 == 258 || i2 == 257) ? "FitEnableMode:1029" : "";
        if (i2 == 259) {
            str = "FitEnableMode:1030";
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.be.bQS_(GeocodeSearch.GPS, str, null);
        this.h = false;
        this.bg.c(false);
        this.bg.c(0L);
    }

    /* renamed from: do, reason: not valid java name */
    private boolean m968do() {
        if (this.df == null || !be() || !this.bk || this.gi != 5) {
            return false;
        }
        ReleaseLogUtil.e("Track_SportManager", "User stops moving, don't report location");
        return true;
    }

    private void aTZ_(Location location, boolean z) {
        if (this.au == null) {
            if (this.ey != 1) {
                ReleaseLogUtil.c("Track_SportManager", "mExtendHandler == null");
                return;
            } else {
                dj();
                ReleaseLogUtil.e("Track_SportManager", "mExtendHandler == null so init it");
            }
        }
        Message obtain = Message.obtain();
        obtain.what = z ? 103 : 102;
        Bundle bundle = new Bundle();
        bundle.putParcelable("Location", location);
        obtain.setData(bundle);
        this.au.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aTU_(Location location) {
        if (this.dj.length() > 0) {
            StringBuilder sb = this.dj;
            sb.delete(0, sb.length());
        }
        if (this.ef.length() > 0) {
            StringBuilder sb2 = this.ef;
            sb2.delete(0, sb2.length());
        }
        if (location == null) {
            ReleaseLogUtil.c("Track_SportManager", "processLocation: aLocation is null");
            return;
        }
        this.dk = location.getLatitude() + this.di;
        double longitude = location.getLongitude() + this.dg;
        this.dh = longitude;
        if (longitude > 180.0d) {
            this.dh = longitude - 360.0d;
        } else if (longitude < -180.0d) {
            this.dh = longitude + 360.0d;
        }
        double[] a2 = LogAnonymous.a(this.dh, this.dk);
        this.dh = a2[0];
        this.dk = a2[1];
        aTQ_(location);
        dc();
        hjd aTB_ = this.z.aTB_(location);
        hiy hiyVar = new hiy(aTB_.b, aTB_.d, location.getAccuracy(), location.getSpeed(), (float) location.getAltitude(), location.getBearing(), location.getTime(), location.getProvider());
        TrackAltitudeMgr trackAltitudeMgr = this.ga;
        if (trackAltitudeMgr != null) {
            hiyVar.b(trackAltitudeMgr.e());
            StringBuilder sb3 = this.dj;
            sb3.append(",A10=");
            sb3.append(this.ga.e());
        }
        if (gvv.b(aTB_.b, 0.0d) && gvv.b(aTB_.d, 0.0d)) {
            this.dj.append(" similar(0)>>>Filter");
            LogUtil.a("Track_SportManager", this.dj.toString());
            this.ef.append(" similar(0)>>>Filter");
            ReleaseLogUtil.e("Track_SportManager", this.ef.toString());
            return;
        }
        if (HAWebViewInterface.NETWORK.equals(hiyVar.g())) {
            g(hiyVar);
            return;
        }
        c(hiyVar);
        LogUtil.a("Track_SportManager", "processLocation mLogStringBuilder.toString():", this.dj.toString());
        ReleaseLogUtil.e("R_Track_SportManager", "processLocation point");
        StringBuilder sb4 = this.dj;
        sb4.delete(0, sb4.length());
        ReleaseLogUtil.e("Track_SportManager", this.ef.toString());
        StringBuilder sb5 = this.ef;
        sb5.delete(0, sb5.length());
    }

    private void aTQ_(Location location) {
        StringBuilder sb = this.dj;
        sb.append("dispatchLocationChanged");
        sb.append(",A01=");
        sb.append(System.currentTimeMillis());
        sb.append(",A02=");
        sb.append(this.dk);
        sb.append(",A03=");
        sb.append(this.dh);
        sb.append(",A04=");
        sb.append(location.getAccuracy());
        sb.append(",A05=");
        sb.append(location.getSpeed());
        sb.append(",A06=");
        sb.append(location.getAltitude());
        sb.append(",A07=");
        sb.append(location.getBearing());
        sb.append(",A08=");
        sb.append(location.getProvider());
        sb.append(",A09=");
        sb.append(location.getTime());
        StringBuilder sb2 = this.ef;
        sb2.append("dispatchLocationChanged");
        sb2.append(",A01=");
        sb2.append(System.currentTimeMillis());
        sb2.append(",A02=");
        sb2.append(this.dk);
        sb2.append(",A03=");
        sb2.append(this.dh);
        sb2.append(",A04=");
        sb2.append(location.getAccuracy());
    }

    private void g(hiy hiyVar) {
        hiyVar.e(true);
        this.dj.append(">>>network");
        LogUtil.a("Track_SportManager", this.dj.toString());
        StringBuilder sb = this.dj;
        sb.delete(0, sb.length());
        this.ef.append(">>>network");
        ReleaseLogUtil.e("Track_SportManager", this.ef.toString());
        StringBuilder sb2 = this.ef;
        sb2.delete(0, sb2.length());
        i(hiyVar);
    }

    private void c(hiy hiyVar) {
        if (this.fz.b(m(hiyVar)) > 0) {
            LinkedList<dwl> e2 = this.fz.e();
            this.ea = e2;
            Iterator<dwl> it = e2.iterator();
            while (it.hasNext()) {
                dwl next = it.next();
                i(new hiy(next.g(), next.j(), next.c(), next.b(), next.e(), next.a(), next.h(), next.i()));
            }
            this.fz.b();
            return;
        }
        if (this.fz.j()) {
            hiy hiyVar2 = new hiy(hiyVar);
            hiyVar2.e(true);
            this.dj.append(">>>is finding start");
            i(hiyVar2);
        }
    }

    private void i(hiy hiyVar) {
        gwq.a().a(hiyVar, this.cf, this.cj);
        Message obtain = Message.obtain();
        obtain.what = 202;
        Bundle bundle = new Bundle();
        bundle.putSerializable("PointProcessed", hiyVar);
        obtain.setData(bundle);
        Handler handler = this.gl;
        if (handler != null) {
            handler.sendMessage(obtain);
        } else {
            ReleaseLogUtil.c("Track_SportManager", "mUiHandler == null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(hiy hiyVar) {
        if (hiyVar == null) {
            ReleaseLogUtil.c("Track_SportManager", "drawPoint : bp is null");
            return;
        }
        if (this.cd) {
            LogUtil.a("Track_SportManager", " mIsReadingFlag>>>Filter");
            return;
        }
        if (this.cr) {
            LogUtil.a("Track_SportManager", " mIsWritingFlag>>>Filter");
            return;
        }
        hjd e2 = gwf.e(hiyVar);
        if (hiyVar.j()) {
            if (this.cb && this.df == null) {
                d(hiyVar, 0);
                if (HAWebViewInterface.NETWORK.equals(hiyVar.g())) {
                    c(e2);
                    return;
                }
                c(d(e2));
                c(hiyVar, false);
                this.cb = false;
                return;
            }
            return;
        }
        a(hiyVar, e2);
    }

    private void a(hiy hiyVar, hjd hjdVar) {
        int i2 = this.ey;
        if (i2 != 0) {
            if (i2 != 1) {
                return;
            }
            j(hiyVar);
        } else {
            this.ae = hjdVar;
            IMapDrawingUpdater iMapDrawingUpdater = this.dm;
            if (iMapDrawingUpdater != null) {
                iMapDrawingUpdater.addEndMarker(hjdVar);
            }
        }
    }

    private hjd d(hjd hjdVar) {
        return ktl.b(hjdVar.b, hjdVar.d) == 3 ? gwe.c(hjdVar, AMapLocation.COORD_TYPE_WGS84, gwg.b()) : hjdVar;
    }

    private void h(hiy hiyVar) {
        if (ktl.b(hiyVar.h(), hiyVar.f()) == 3) {
            hjd c2 = gwe.c(new hjd(hiyVar.h(), hiyVar.f()), AMapLocation.COORD_TYPE_WGS84, gwg.b());
            hiyVar.c(c2.b);
            hiyVar.b(c2.d);
        }
    }

    private void j(hiy hiyVar) {
        hiy hiyVar2 = new hiy(hiyVar);
        h(hiyVar);
        if (this.cj) {
            this.ap.clear();
        }
        if (this.cf) {
            if (this.cj) {
                b(hiyVar);
                this.cj = false;
                ReleaseLogUtil.e("Track_SportManager", "Recovery ResumeSport TotalSportDistance=", Float.valueOf(this.fv));
            } else {
                f(hiyVar);
                c(gwf.e(hiyVar), hiyVar.i());
                this.fw += dwu.b(this.ct, m(hiyVar));
                ReleaseLogUtil.e("Track_SportManager", " Recovery TotalSportDistance=", Float.valueOf(this.fv));
            }
            this.cf = false;
        } else if (this.cj) {
            gwq.a().b(gwq.a().d(), this.cj);
            c(hiyVar, true);
            b(hiyVar);
            this.cj = false;
            ReleaseLogUtil.e("Track_SportManager", " ResumeSport TotalSportDistance=", Float.valueOf(this.fv));
        } else {
            c(hiyVar, true);
            long i2 = hiyVar.i() - System.currentTimeMillis();
            this.fo = i2;
            this.ge.c(i2);
            c(gwf.e(hiyVar), hiyVar.i());
            this.fw += dwu.b(this.ct, m(hiyVar));
            LogUtil.a("Track_SportManager", "GpsTime=", Long.valueOf(hiyVar.i()), ", TotalSportDistance=", Float.valueOf(this.fv), ", Total3DSportDistance=", Float.valueOf(this.fw));
        }
        e(hiyVar2);
        this.ar = 0;
        p(false);
        if (!this.bx) {
            this.bx = true;
        }
        if (!this.cf && !this.cj) {
            this.ap.add(hiyVar);
            co();
        }
        ff();
        en();
    }

    private void d(hiy hiyVar, int i2) {
        if (i2 == 0) {
            if (this.br) {
                this.br = false;
                this.t = this.ep;
                this.q = hiyVar.g();
                return;
            }
            return;
        }
        if (i2 == 1) {
            if (this.bp) {
                this.bp = false;
                this.s = this.ep;
                String g2 = hiyVar.g();
                this.p = g2;
                if (this.t < 0) {
                    this.t = this.s;
                }
                if (this.q == null) {
                    this.q = g2;
                    return;
                }
                return;
            }
            return;
        }
        if (i2 == 2) {
            if (this.bz) {
                ReleaseLogUtil.e("Track_SportManager", "Not starting point BI when GPS lost");
                return;
            }
            LogUtil.a("Track_SportManager", "start point BI");
            HashMap hashMap = new HashMap(9);
            c(this.ep, hashMap);
            ixx.d().d(this.aa, AnalyticsValue.BI_TRACK_SPORT_START_LOCATION_UPDATA.value(), hashMap, 0);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(8);
            linkedHashMap.put(OpAnalyticsConstants.ORIENTATE_DURATION_MS, String.valueOf(this.ep));
            d(linkedHashMap);
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_GPS_90020001.value(), linkedHashMap);
            return;
        }
        LogUtil.a("Track_SportManager", "startPointBi wrong type");
    }

    private void d(LinkedHashMap<String, String> linkedHashMap) {
        linkedHashMap.put(BleConstants.SPORT_TYPE, String.valueOf(this.ew));
        if (TimeUnit.MILLISECONDS.toMinutes(this.t) <= 3) {
            linkedHashMap.put("isLocationOverTimeLimit", String.valueOf(false));
        } else {
            linkedHashMap.put("isLocationOverTimeLimit", String.valueOf(true));
        }
        linkedHashMap.put("locationPointProvider", this.q);
        linkedHashMap.put("startPointProvider", this.p);
        linkedHashMap.put("startPointGPSstatus", String.valueOf(this.av));
    }

    private void c(long j2, Map<String, Object> map) {
        map.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.ew));
        if (TimeUnit.MILLISECONDS.toMinutes(this.t) <= 3 && TimeUnit.MILLISECONDS.toMinutes(this.s) <= 3) {
            map.put("isLocationOverTimeLimit", false);
            map.put("isStartOverTimeLimit", false);
            map.put("locationPointTime", Long.valueOf(this.t));
            map.put("startPointTime", Long.valueOf(this.s));
            map.put("trueStartTime", Long.valueOf(j2));
        } else if (TimeUnit.MILLISECONDS.toMinutes(this.t) <= 3 && TimeUnit.MILLISECONDS.toMinutes(this.s) > 3) {
            map.put("isLocationOverTimeLimit", false);
            map.put("isStartOverTimeLimit", true);
        } else {
            map.put("isLocationOverTimeLimit", true);
            map.put("isStartOverTimeLimit", true);
        }
        map.put("locationPointProvider", this.q);
        map.put("startPointProvider", this.p);
        map.put("startPointGPSstatus", Integer.valueOf(this.av));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fi() {
        this.u.d(this.ak, this.ep, y() / 3.6f, this.fa != null ? r0.g() / 60.0f : 0.0f);
    }

    private void c(hiy hiyVar, boolean z) {
        if (this.dn == null) {
            ReleaseLogUtil.d("Track_SportManager", "drawFirstStartMarker mMotionPathPointsBuffer is null");
            return;
        }
        ReleaseLogUtil.e("Track_SportManager", "mTrackType: ", Integer.valueOf(this.gh), "mMapUpdater: ", this.dm, "mMotionPathPointsBuffer: ", Integer.valueOf(this.dn.size()));
        if (this.dn.size() == 0) {
            d(hiyVar, 1);
            if (z) {
                bt().b();
                a(hiyVar);
                d(hiyVar, 2);
            }
            IMapDrawingUpdater iMapDrawingUpdater = this.dm;
            if (iMapDrawingUpdater != null) {
                iMapDrawingUpdater.addStartMarker(gwf.e(hiyVar));
            }
        }
    }

    private void a(hiy hiyVar) {
        long currentTimeMillis = System.currentTimeMillis() - hiyVar.i();
        if (Math.abs(currentTimeMillis) > TimeUnit.SECONDS.toMillis(20L)) {
            this.fp = currentTimeMillis;
            this.ge.b(currentTimeMillis);
        }
        ReleaseLogUtil.e("Track_SportManager", "mTimeOnceDiff is ", Long.valueOf(this.fp));
    }

    private void c(hjd hjdVar) {
        IMapDrawingUpdater iMapDrawingUpdater = this.dm;
        if (iMapDrawingUpdater != null) {
            iMapDrawingUpdater.drawFirstLocation(hjdVar);
        }
    }

    private void f(hiy hiyVar) {
        IMapDrawingUpdater iMapDrawingUpdater;
        if (this.gh == 1 || (iMapDrawingUpdater = this.dm) == null) {
            ReleaseLogUtil.c("Track_SportManager", "drawRecoveryLine params invalid!");
        } else {
            iMapDrawingUpdater.drawRecoveryLine(this.df, gwf.e(hiyVar));
        }
    }

    private void b(hiy hiyVar) {
        IMapDrawingUpdater iMapDrawingUpdater = this.dm;
        if (iMapDrawingUpdater == null) {
            return;
        }
        iMapDrawingUpdater.drawPauseLine(this.df, gwf.e(hiyVar));
    }

    private void en() {
        Map<Long, double[]> map = this.dn;
        if (map == null) {
            LogUtil.b("Track_SportManager", "saveLocationPoints mMotionPathPointsBuffer is null !");
            return;
        }
        int size = map.size();
        if (size == 0) {
            LogUtil.h("Track_SportManager", "saveLocationPoints mMotionPathPointsBuffer size is 0 !");
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (size % 8 != 0) {
            if (size <= 8) {
                return;
            }
            if (elapsedRealtime - this.ai <= TimeUnit.SECONDS.toMillis(30L)) {
                return;
            }
        }
        ef();
        this.ai = elapsedRealtime;
    }

    @Override // com.huawei.healthcloud.plugintrack.model.TimeUpdater
    public long getSportDuration() {
        return this.ep;
    }

    @Override // com.huawei.healthcloud.plugintrack.model.TimeUpdater
    public long getSportDurationBySecond() {
        return this.ep / 1000;
    }

    @Override // com.huawei.healthcloud.plugintrack.model.TimeUpdater
    public String getFormattedTime() {
        return UnitUtil.d(Math.round(this.ep / 1000.0f));
    }

    public int n() {
        return this.ew;
    }

    public boolean aq() {
        return this.gh == 1;
    }

    public void a(int i2) {
        this.ew = i2;
    }

    public void bn() {
        mwu.d().a("Track_SportManager", new IBaseResponseCallback() { // from class: guh
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                LogUtil.a("Track_SportManager", "S-TAG Wear Abnormal Status is false");
            }
        });
    }

    public void bz() {
        mwu.d().b("Track_SportManager");
    }

    public void by() {
        gsx gsxVar;
        ISportDistanceTimeCallback iSportDistanceTimeCallback;
        this.cs = gwg.i();
        if (!aq()) {
            SportMusicInteratorService.e();
            guj.d().i();
            if (dq()) {
                SportMusicController.a().b(this.ew);
            }
        }
        db();
        this.dz.e(this.bg);
        this.el.onStartSport();
        if (this.ew != 264) {
            this.at = new gwd();
        } else if (gvv.d(this.aa)) {
            this.ff = 1;
        }
        cd();
        p(false);
        a(this.dz);
        c(this.dz);
        if (!this.bo && (iSportDistanceTimeCallback = this.dw) != null) {
            iSportDistanceTimeCallback.onDistanceChange(0, System.currentTimeMillis(), 0);
        }
        if (this.bo && this.du == 4 && this.af == null) {
            gtj gtjVar = new gtj();
            this.af = gtjVar;
            gtjVar.e((MotionPathSimplify) null);
        }
        dg();
        ReleaseLogUtil.e("R_Track_SportManager", "startSport begin");
        LogAnonymous.d();
        ee();
        cz();
        dd();
        dm();
        dp();
        ed();
        et();
        gwq.a().g();
        if (!this.bl) {
            dl();
        }
        if (j != null) {
            this.fc = j.getCurrentSteps();
            if (!ba() && !this.cm) {
                this.eb = 0;
            }
        }
        this.cp = false;
        this.bs = false;
        dy();
        ReleaseLogUtil.e("Track_SportManager", "startSport reportSportDataToListener");
        aTX_(aUd_());
        if (this.bo) {
            e(gwk.a(this.aa), gwk.d(this.aa));
        }
        if (this.eu.size() > 0) {
            for (ISportStateChangeListener iSportStateChangeListener : this.eu) {
                if (iSportStateChangeListener != null) {
                    iSportStateChangeListener.onStartSport();
                }
            }
        }
        if (this.gg == null) {
            this.gg = new gup(this, this.ew);
        }
        if (!aq() && (gsxVar = this.gc) != null) {
            gsxVar.e(this.aa);
            f(true);
        }
        HealthSportingNotificationHelper healthSportingNotificationHelper = new HealthSportingNotificationHelper(this.aa, this.ew, true, false, true);
        this.ev = healthSportingNotificationHelper;
        e(healthSportingNotificationHelper);
        gve.d();
        bn();
        gvv.c(this.ew);
        cw();
        h(1);
        this.em = String.valueOf(this.ex);
    }

    private void dy() {
        mwu.d().b(this.ao);
    }

    private void m(boolean z) {
        LogUtil.a("Track_SportManager", "checkDevicesIsSupportNewStep = ", Boolean.valueOf(z));
        if (e == null) {
            LogUtil.h("Track_SportManager", "checkDevicesIsSupportNewStep mInstance == null");
        } else {
            e.cp = z;
        }
    }

    private void s(boolean z) {
        LogUtil.a("Track_SportManager", "isDeliverStepRate = ", Boolean.valueOf(z));
        this.bs = z;
    }

    private void h(int i2) {
        int i3 = this.gh;
        if (i3 == 1) {
            LogUtil.h("Track_SportManager", "Sport Track Type ", Integer.valueOf(i3));
            return;
        }
        int i4 = this.ew;
        if (i4 != 257 && i4 != 258 && i4 != 264 && i4 != 282 && i4 != 260) {
            LogUtil.h("Track_SportManager", "mSportType  ", Integer.valueOf(i4));
            return;
        }
        if (!CommonUtil.bh()) {
            LogUtil.h("Track_SportManager", " is not emui ");
            return;
        }
        try {
            Class<?> cls = Class.forName("com.huawei.android.os.PowerManagerEx");
            cls.getMethod("setSportState", Integer.TYPE).invoke(cls, Integer.valueOf(i2));
            LogUtil.a("Track_SportManager", " sportState ", Integer.valueOf(i2));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.h("Track_SportManager", "ClassNotFoundException or NoSuchMethodException");
        }
    }

    private void cw() {
        jdx.b(new Runnable() { // from class: gud
            @Override // java.lang.Runnable
            public final void run() {
                gtx.this.bg();
            }
        });
    }

    /* synthetic */ void bg() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_RunningPaceZone_Config");
        if (userPreference != null) {
            String value = userPreference.getValue();
            this.dx = value;
            LogUtil.a("Track_SportManager", "mPaceZoneConfig:", value);
        }
    }

    private boolean dq() {
        return CommonUtil.bd() && gwg.a(this.aa) && gvv.b(this.aa) == 0 && !at() && !aq();
    }

    private void dg() {
        if (this.gb == null) {
            this.gb = new gum();
        }
        this.gb.d(this.aa, this.bo);
        this.gb.d(this);
    }

    private void db() {
        k(true);
        this.o = 0;
        this.bw = true;
        this.bu = true;
        this.bq = false;
        dk();
        df();
        if (this.er == null) {
            this.er = gwg.e();
        }
        if (this.ex == 0) {
            this.ex = System.currentTimeMillis();
        }
        g = false;
        this.de = this.ey;
        this.ey = 1;
        this.l = System.currentTimeMillis();
    }

    private void k(boolean z) {
        this.co = z;
        KeyValDbManager.b(this.aa).e("already_sport", String.valueOf(z));
    }

    private void df() {
        cab cabVar = new cab();
        this.ad = cabVar;
        cabVar.b(new CourseEnvParams.OnInitCompleteListener() { // from class: gub
            @Override // com.huawei.health.sport.model.CourseEnvParams.OnInitCompleteListener
            public final void onInitComplete() {
                gtx.this.bh();
            }
        });
        this.ad.e();
    }

    /* synthetic */ void bh() {
        this.ba = true;
        LogUtil.a("Track_SportManager", "initConfigParams mHasInitConfig: true");
    }

    private void ee() {
        if (this.ew != 264) {
            ktg bt = bt();
            if (ktj.e(this.aa)) {
                if (bt.e()) {
                    n(bt.a());
                } else {
                    n(3);
                }
            }
            b("sportTrackLocation");
            c("sportTrackLocation");
            eg();
            bt.d("sportTrackTempLocation");
            return;
        }
        n(0);
    }

    private void dd() {
        gtq gtqVar = this.az;
        if (gtqVar == null) {
            this.az = new gtq(j);
        } else {
            gtqVar.d(j);
        }
        this.az.b(this.gh);
        this.az.e(cu());
        if (gwg.i(this.gh)) {
            gwn gwnVar = this.eg;
            if (gwnVar == null) {
                this.eg = new gwn(j, this.az, this.aa);
            } else {
                gwnVar.e(j);
            }
            ek();
            eb();
            ea();
        }
        g = true;
    }

    private void ek() {
        Map<String, DeviceCapability> queryDeviceCapability;
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_SportManager");
        Object[] objArr = new Object[2];
        objArr[0] = "deviceInfo != null ";
        objArr[1] = Boolean.valueOf(deviceInfo != null);
        LogUtil.a("Track_SportManager", objArr);
        if (deviceInfo != null && (queryDeviceCapability = cun.c().queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "Track_SportManager")) != null && queryDeviceCapability.get(deviceInfo.getDeviceIdentify()) != null && queryDeviceCapability.get(deviceInfo.getDeviceIdentify()).isSupportInformCloseOrOpen()) {
            gwn gwnVar = this.eg;
            if (gwnVar != null) {
                gwnVar.d(this.gh);
                this.eg.c(this.ew);
                this.eg.c();
            }
            f = true;
        }
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            gtqVar.b();
            this.az.g();
        }
    }

    private void eb() {
        gwl gwlVar = this.en;
        if (gwlVar == null) {
            this.en = new gwl(j);
        } else {
            gwlVar.a(j);
        }
        dw();
        this.en.c(this.ew);
        this.en.a(this.gh);
        this.en.b();
    }

    private void dw() {
        if (j != null) {
            j.init();
        }
    }

    private void ea() {
        gwm gwmVar = this.ek;
        if (gwmVar == null) {
            this.ek = new gwm(j, this.aa);
        } else {
            gwmVar.d(j);
        }
        dw();
        this.ek.e(this.ew);
        this.ek.d(this.gh);
        this.ek.a(this.ei);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i2) {
        if (i2 < this.db) {
            LogUtil.b("Track_SportManager", "estimateDistanceByLaps laps back");
            return;
        }
        int i3 = this.am;
        this.db = i3;
        this.am = i2;
        if (i2 - i3 > 10) {
            LogUtil.b("Track_SportManager", "laps is too much");
            return;
        }
        if (this.bz) {
            if (this.cu) {
                this.cu = false;
                return;
            }
            LogUtil.a("Track_SportManager", "mCurrentTotalLaps", Integer.valueOf(i2), "mLastTotalLaps", Integer.valueOf(this.db));
            j(this.am, this.db);
            Handler handler = this.gl;
            if (handler != null) {
                handler.sendEmptyMessage(301);
            }
        }
    }

    private void j(int i2, int i3) {
        this.w++;
        int i4 = i2 - i3;
        if (i4 == 0) {
            LogUtil.b("Track_SportManager", "laps no more");
            return;
        }
        float f2 = i4 * this.v;
        this.ak = f2;
        if (f2 > 0.0f) {
            float f3 = this.fv + f2;
            this.fv = f3;
            LogUtil.a("Track_SportManager", "estimateDistanceByLaps curDistance : ", Float.valueOf(f3), " lastGpsDistance : ", Float.valueOf(this.cv));
            ReleaseLogUtil.e("R_Track_SportManager", "estimateDistanceByLaps current distance");
            if (this.dw == null || this.cr || this.w < 5) {
                return;
            }
            this.w = 0;
            LogUtil.a("Track_SportManager", "System.currentTimeMillis() + mTimeDiffBetweenGpsAndSystem = ", Long.valueOf(System.currentTimeMillis() + this.fo));
            this.dw.onDistanceChange((int) this.fv, System.currentTimeMillis() + this.fo + this.fp, this.dn.size());
        }
    }

    private void dl() {
        if (!UnitUtil.h() && !aq()) {
            guq guqVar = new guq(this.ew, this.du);
            this.gd = guqVar;
            guqVar.c(this.ep, (int) this.fv);
            if (dx()) {
                ffd b2 = this.fg.b();
                this.gd.d(b2.f(), this.fv, x() * 1000.0f, this.ep, b2.i() + b2.d());
                if (b2.f() == 1) {
                    this.gd.e(b2.i(), this.fv);
                }
            }
        }
        if (ba() || this.gd == null) {
            return;
        }
        if (!at()) {
            this.gd.g();
        }
        this.cl = true;
    }

    public boolean ah() {
        return this.cl;
    }

    private void eg() {
        LogUtil.a("Track_SportManager", "requestOriginalGpsStatus");
        if (this.at == null) {
            ReleaseLogUtil.d("Track_SportManager", "requestOriginalGpsStatus mGpsPointBiUtil is null");
        } else {
            bt().e(this.at, "sportTrackLocation");
        }
    }

    private void cj() {
        LogUtil.a("Track_SportManager", "cancelOriginalGpsStatus");
        bt().c("sportTrackLocation");
    }

    private void ed() {
        if (be()) {
            ev();
            ep();
            gul gulVar = this.fa;
            if (gulVar == null) {
                this.fa = new gul(j, this);
            } else {
                gulVar.b(j);
                this.fa.b(this);
            }
            if (j != null) {
                j.setStepInterval(this.ci);
            }
            this.fa.b(this.ff);
            return;
        }
        this.fa = null;
        ReleaseLogUtil.e("Track_SportManager", "Don't registerRealStepListener when not supported");
    }

    public boolean ba() {
        return this.bo;
    }

    private void cz() {
        switch (this.ew) {
            case 257:
                this.fz.b(0);
                break;
            case 258:
                this.fz.b(1);
                break;
            case 259:
                this.fz.b(2);
                break;
            default:
                this.fz.b(1);
                break;
        }
    }

    private void ep() {
        this.eo = 0;
        if (j == null) {
            ReleaseLogUtil.c("Track_SportManager", "startGetStepRateData mPluginTrackAdapter is null!");
            return;
        }
        int i2 = this.ff == 1 ? 1000 : 5000;
        this.fd = i2;
        LogUtil.a("Track_SportManager", "startGetStepRateData regStepRateListener!");
        j.regStepRateListener(new IStepRateCallback() { // from class: gtx.3
            @Override // com.huawei.healthcloud.plugintrack.model.IStepRateCallback
            public void report(int i3, int i4) {
                gtx gtxVar = gtx.this;
                gtxVar.c(i3, gtxVar.fd);
                if (gtx.this.ff == 2 || gtx.this.ff == 3) {
                    i3 = gtw.e().a();
                }
                StringBuilder sb = gtx.this.fb;
                sb.append("step: ");
                sb.append(i3);
                sb.append(" motion: ");
                sb.append(i4);
                sb.append(" stepType: ");
                sb.append(gtx.this.ff);
                sb.append(" ");
                long b2 = LogUtil.b(5000, gtx.this.da);
                if (b2 != -1) {
                    ReleaseLogUtil.e("Track_SportManager", gtx.this.fb.toString());
                    gtx.this.fb.delete(0, gtx.this.fb.length());
                    gtx.this.da = b2;
                }
                gtx.this.g(i3, i4);
            }
        }, i2, this.ff);
    }

    private void fc() {
        if (be()) {
            int i2 = this.ff;
            if (i2 == 2 || i2 == 3) {
                this.ff = this.dr;
                this.dr = 0;
                gul gulVar = this.fa;
                if (gulVar != null) {
                    gulVar.d(0);
                }
                LogUtil.a("Track_SportManager", "stopLinkageWearStep switch from SPORT_LINKAGE_WEAR_STEP to ", Integer.valueOf(this.ff));
            }
            gtw.e().b();
            this.bj = false;
            this.cq = false;
        }
    }

    private boolean ci() {
        List<gsy.b> e2;
        if (!this.cq) {
            if (!this.bn && (e2 = gsy.b().e(this.ew)) != null && e2.size() > 0) {
                this.bn = true;
            }
            if ((cvs.d() == null || !cvs.d().isSupportWorkout()) && !this.bn) {
                ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy device not support Workout! ConnectedSensor = false");
                return false;
            }
            if (this.bj) {
                return false;
            }
            if (gvv.c(this.aa)) {
                ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy AutoPause is open, not support Linkage Wear Step!");
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), 1002);
                this.bj = true;
                return false;
            }
            int i2 = this.ew;
            if (i2 != 264) {
                ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy mSportType is ", Integer.valueOf(i2), ", not TREADMILL");
                return false;
            }
            this.cq = true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, int i3) {
        if (ci()) {
            if (this.bn) {
                if (this.ff == 3) {
                    return;
                }
            } else if (this.ff == 2) {
                return;
            }
            int i4 = this.ey;
            if (i4 != 1) {
                ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy mSportStatus is ", Integer.valueOf(i4), ", not SPORTING");
                return;
            }
            if (this.cy != 0 && System.currentTimeMillis() - this.cy < PreConnectManager.CONNECT_INTERNAL) {
                ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy just paused!");
                return;
            }
            if (this.fa == null || gtw.e() == null) {
                ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy null mStepRateUtils ", this.fa);
                return;
            }
            if (System.currentTimeMillis() - gtw.e().c(20) > j.getDefaultDelayInterval() * 2) {
                if (System.currentTimeMillis() - gtw.e().c(10) > j.getDefaultDelayInterval() * 10) {
                    ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy wear step report not continuous! wearLastUpdateTime = ", Long.valueOf(gtw.e().c(20)), " sensorLastUpdateTime", Long.valueOf(gtw.e().c(10)));
                    return;
                }
            }
            int defaultDelayInterval = 10000 / j.getDefaultDelayInterval();
            if (!gtw.e().h(defaultDelayInterval + 1)) {
                ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy linkage wear step array not enough!");
                return;
            }
            int i5 = i3 != 0 ? 10000 / i3 : 2;
            if (this.ff == 2) {
                int a2 = gtw.e().a(20);
                if (a2 >= i5) {
                    int i6 = a2 - i5;
                    if (this.aj == gtw.e().e(20, i6)) {
                        gtw.e().e(20, i6);
                        b(this.aj, defaultDelayInterval);
                    }
                }
            } else if (this.fa.h() >= i5) {
                gul gulVar = this.fa;
                if (i2 == gulVar.a(gulVar.h() - i5)) {
                    b(i2, defaultDelayInterval);
                }
            }
            ReleaseLogUtil.e("Track_SportManager", "checkStepStrategy not satisfy ");
        }
    }

    private void b(int i2, int i3) {
        if (this.ff == 2 || !d(i2, i3, 20, 2)) {
            d(i2, i3 / 5, 10, 3);
        }
    }

    private boolean d(int i2, int i3, int i4, int i5) {
        if (gtw.e().b(i3, i4) == 0) {
            return false;
        }
        this.dr = this.ff;
        this.ff = i5;
        gul gulVar = this.fa;
        if (gulVar != null) {
            gulVar.d(i5);
        }
        gtw.e().g(i4);
        gtw.e().b(i2);
        ReleaseLogUtil.e("Track_SportManager", "startLinkageWearStep switch from ", Integer.valueOf(this.dr), " to ", Integer.valueOf(this.ff));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i2, int i3) {
        f(i3);
        this.gi = i3;
        int i4 = this.aj;
        this.dd = i4;
        this.aj = i2;
        a(i4, i2);
        if (dz()) {
            this.eo++;
        }
        if (this.ew == 264 && this.ey == 1) {
            if (!this.bw) {
                a(this.dd, this.aj, this.gm * gwa.e(this.aj - this.dd));
                Handler handler = this.gl;
                if (handler != null) {
                    handler.sendEmptyMessage(301);
                }
            }
            this.bw = false;
            return;
        }
        int i5 = this.aj - this.dd;
        if (i5 > 30) {
            LogUtil.h("Track_SportManager", "Steps in 5s is to large don't estimateSportDistanceBySteps");
            return;
        }
        if (!this.bz && this.ey == 1) {
            this.ar += i5;
        }
        if (i(i3)) {
            int i6 = this.dd;
            int i7 = this.ar;
            int i8 = i6 - i7;
            if (i8 > 0 && i7 < 150) {
                i6 = i8;
            }
            this.ar = 0;
            a(i6, this.aj, this.gm);
            Handler handler2 = this.gl;
            if (handler2 != null) {
                handler2.sendEmptyMessage(301);
            }
        }
    }

    private void f(int i2) {
        if (i2 == 5) {
            if (this.by) {
                return;
            }
            ReleaseLogUtil.e("Track_SportManager", "User stop moving");
            this.by = true;
            this.fn = System.currentTimeMillis();
            return;
        }
        if (this.by) {
            ReleaseLogUtil.e("Track_SportManager", "User resume moving");
            this.fj += System.currentTimeMillis() - this.fn;
            this.fl++;
            this.by = false;
        }
    }

    private boolean i(int i2) {
        return i2 != 5 && this.ey == 1 && this.bt && this.bz;
    }

    private boolean dz() {
        return this.ff == 1 || this.dr == 1;
    }

    private void a(int i2, int i3) {
        if (this.bu) {
            LogUtil.a("Track_SportManager", "developIndoorRunningInfo,mIsDevelopFirstIndoorRun=true");
            this.bu = false;
            return;
        }
        this.ez = this.fa.d(i3, (this.ff == 1 || this.dr == 1) ? 6 : 1);
        if (i2 >= i3 || i2 == 0) {
            return;
        }
        if (this.ey != 1) {
            LogUtil.a("Track_SportManager", "developIndoorRunningInfo, mSportStatus != MapTrackingConstants.SPORTS_STATUS_SPORTING");
            return;
        }
        int i4 = i3 - i2;
        float f2 = this.an;
        float e2 = gwa.e(i4);
        this.ah += i4;
        this.fr += i4 * f2 * e2;
        this.ft += gvb.e(i4, ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(), 5, 0);
    }

    private void cf() {
        this.eo = 0;
        if (j == null) {
            LogUtil.b("Track_SportManager", "cancelGetStepRateData mPluginTrackAdapter is null!");
            return;
        }
        int i2 = this.ff;
        if (i2 == 2 || i2 == 3) {
            j.unregStepRateListener(this.dr);
        } else {
            j.unregStepRateListener(this.ff);
        }
    }

    private void cd() {
        if (this.et == null) {
            this.et = new cag(this.ew);
        }
        this.et.a();
    }

    private void fd() {
        if (this.et == null) {
            this.et = new cag(this.ew);
        }
        this.et.e();
    }

    public void bi() {
        LogUtil.a("Track_SportManager", "pauseFromStopSport");
        this.ce = true;
        bk();
        this.ce = false;
    }

    public void bk() {
        this.l = -1L;
        this.ey = 2;
        this.el.onPauseSport();
        this.as = cr();
        List<hiy> list = this.ap;
        if (list != null && !list.isEmpty()) {
            cn();
        }
        p();
        if (dq() && this.ge.f(this.ew) == 1) {
            SportMusicController.a().a(1, (String) null);
        }
        ReleaseLogUtil.e("Track_SportManager", "pauseSport", " begin");
        unregisterGps();
        gwq.a().i();
        gul gulVar = this.fa;
        if (gulVar != null) {
            gulVar.o();
        }
        guq guqVar = this.gd;
        if (guqVar != null && !this.ce) {
            guqVar.b();
        }
        IMapDrawingUpdater iMapDrawingUpdater = this.dm;
        if (iMapDrawingUpdater != null) {
            iMapDrawingUpdater.pauseSportClear();
        }
        if (this.eu.size() > 0) {
            for (ISportStateChangeListener iSportStateChangeListener : this.eu) {
                if (iSportStateChangeListener != null) {
                    iSportStateChangeListener.onPauseSport();
                }
            }
        }
        ReleaseLogUtil.e("Track_SportManager", "pauseSport reportSportDataToListener");
        aTX_(aUd_());
        fb();
        fd();
        cg();
        ef();
        gsx gsxVar = this.gc;
        if (gsxVar != null) {
            gsxVar.c(this.aa);
        }
        gwd gwdVar = this.at;
        if (gwdVar != null) {
            gwdVar.c();
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.cz = currentTimeMillis;
        this.u.d(currentTimeMillis, -1);
        b(2);
        h(0);
        d(1, gso.e().g());
    }

    public void d(int i2, int i3) {
        LogUtil.a("Track_SportManager", "doSportStatusBi event:", Integer.valueOf(i2), " statusSource:", Integer.valueOf(i3));
        if (this.af != null) {
            gge.a(i2, gso.e().g() == 1, this.af.d());
        }
    }

    private void cg() {
        if (this.dn == null) {
            return;
        }
        long da = da();
        if (da > 0) {
            double[] dArr = this.dn.get(Long.valueOf(da - 1));
            if (dArr == null || dArr.length < 2 || !gwe.d(dArr[0], dArr[1])) {
                double[] dArr2 = {90.0d, -80.0d, 0.0d, System.currentTimeMillis()};
                this.dn.put(Long.valueOf(da), dArr2);
                synchronized (f12936a) {
                    this.fk.put(Long.valueOf(da), dArr2);
                }
            }
        }
    }

    private void fb() {
        this.w = 0;
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            gwlVar.a();
        }
        gwm gwmVar = this.ek;
        if (gwmVar != null) {
            gwmVar.c();
        }
        gwn gwnVar = this.eg;
        if (gwnVar != null && f) {
            gwnVar.b();
        }
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            gtqVar.a();
        }
    }

    private void cn() {
        IMapDrawingUpdater iMapDrawingUpdater = this.dm;
        if (iMapDrawingUpdater != null) {
            iMapDrawingUpdater.forceDrawLineToMap();
        }
    }

    public void bq() {
        cd();
        this.l = System.currentTimeMillis();
        this.dc = SystemClock.elapsedRealtime();
        this.cy = System.currentTimeMillis();
        this.ey = 1;
        this.el.onResumeSport();
        this.cj = true;
        this.cu = true;
        this.bw = true;
        this.bu = true;
        gul gulVar = this.fa;
        if (gulVar != null) {
            gulVar.k();
        }
        ReleaseLogUtil.e("Track_SportManager", "resumeSport", "begin");
        registerGps();
        if (dq() && this.ge.f(this.ew) == 1 && !SportMusicController.a().b()) {
            SportMusicController.a().a(2, (String) null);
        }
        guq guqVar = this.gd;
        if (guqVar != null) {
            guqVar.h();
        }
        ec();
        int i2 = this.aj;
        this.fc = i2;
        ReleaseLogUtil.e("Track_SportManager", "resumeSport reportSportDataToListener ", Integer.valueOf(i2));
        aTX_(aUd_());
        if (this.eu.size() > 0) {
            for (ISportStateChangeListener iSportStateChangeListener : this.eu) {
                if (iSportStateChangeListener != null) {
                    iSportStateChangeListener.onResumeSport();
                }
            }
        }
        gwq.a().f();
        gsx gsxVar = this.gc;
        if (gsxVar != null) {
            gsxVar.e(this.aa);
        }
        gwd gwdVar = this.at;
        if (gwdVar != null) {
            gwdVar.d();
        }
        this.dy++;
        this.dv += System.currentTimeMillis() - this.cz;
        this.u.d(System.currentTimeMillis(), this.ew);
        b(1);
        h(1);
        d(2, gso.e().g());
    }

    private void ec() {
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            gwlVar.b();
        }
        gwm gwmVar = this.ek;
        if (gwmVar != null && this.ew == 259) {
            gwmVar.a(this.ei);
        }
        if (f) {
            gwn gwnVar = this.eg;
            if (gwnVar != null) {
                gwnVar.c();
                this.az.b();
                return;
            }
            return;
        }
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            gtqVar.b();
        }
    }

    public void bx() {
        if (this.m != null || this.bl) {
            if (!this.bl && this.k != null) {
                StringBuilder sb = new StringBuilder(16);
                sb.append("stopBackgroundTrack time ");
                sb.append(this.ep);
                sb.append(" dis ");
                sb.append(this.fv);
                e(sb.toString());
                this.k.a(true);
            }
            TrackMainMapActivity.b(BaseApplication.getContext());
            ca();
            gul gulVar = this.fa;
            if (gulVar != null) {
                gulVar.e(true);
            }
            bs();
        }
    }

    public void o(boolean z) {
        TrackMainMapActivity.b(BaseApplication.getContext());
        n(z);
        bm();
    }

    public void n(boolean z) {
        int i2;
        if (z && (i2 = this.ey) == 3) {
            ReleaseLogUtil.d("Track_SportManager", "stopSport failed with mSportStatus is:", Integer.valueOf(i2));
            return;
        }
        this.el.m134x32b3e3a1();
        l(z);
        b((ISportDistanceTimeCallback) this.dz);
        b((ISportStateChangeListener) this.dz);
        c(false);
        fh();
        if (this.gd != null && z) {
            if (LanguageUtil.j(this.aa)) {
                this.gd.l();
            } else {
                this.gd.j();
            }
        }
        ej();
        this.ey = 3;
        ei();
        this.bh = 0;
        LogAnonymous.b();
        if (this.dn == null) {
            LogUtil.b("Track_SportManager", "stopSport() mMotionPathPointsBuffer is null!");
            return;
        }
        fk();
        ReleaseLogUtil.e("Track_SportManager", "stopSport reportSportDataToListener");
        cy();
        r(z && this.fs);
        es();
        gwq.a().e(this.ep, this.gh, this.dq, z);
        this.dn.clear();
        this.ge.a(0L);
        this.ge.b(0L);
        this.fp = 0L;
        this.fs = true;
        gtk gtkVar = this.k;
        if (gtkVar != null) {
            gtkVar.a(z, this.ex, cr(), h());
        }
        cb();
        gum gumVar = this.gb;
        if (gumVar != null) {
            gumVar.b();
        }
        gtj gtjVar = this.af;
        if (gtjVar != null) {
            gtjVar.b();
        }
        ch();
        fc();
        bz();
        ck();
        h(0);
        fd();
        if (z) {
            d(3, gso.e().g());
        } else {
            d(6, gso.e().g());
        }
        this.au.sendEmptyMessage(402);
    }

    private void l(boolean z) {
        gul gulVar;
        SportMusicController.a().a(false);
        if (this.ew == 264 && this.gf == 1 && (gulVar = this.fa) != null) {
            gulVar.a();
            this.ez = 0.0d;
        }
        k(false);
        ReleaseLogUtil.e("Track_SportManager", "stopSport() start, toSaveData ", Boolean.valueOf(z));
        this.as = cr();
        gsx gsxVar = this.gc;
        if (gsxVar != null) {
            gsxVar.c(this.aa);
        }
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            gtqVar.h();
        }
        gum gumVar = this.gb;
        if (gumVar != null) {
            gumVar.e();
        }
    }

    private void fh() {
        if (be()) {
            cf();
            gul gulVar = this.fa;
            if (gulVar != null) {
                gulVar.e(true);
                this.fa.l();
            }
            ez();
            return;
        }
        LogUtil.a("Track_SportManager", "Don't unregisterRealStepListener when not supported");
    }

    private void ei() {
        cj();
        i("sportTrackLocation");
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            gwlVar.a();
        }
        gwm gwmVar = this.ek;
        if (gwmVar != null) {
            gwmVar.c();
        }
        ex();
    }

    private void cy() {
        aTX_(aUd_());
        if (f) {
            gwn gwnVar = this.eg;
            if (gwnVar != null) {
                gwnVar.b();
                this.az.a();
                return;
            }
            return;
        }
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            gtqVar.a();
        }
    }

    private void ch() {
        LogUtil.h("Track_SportManager", "clear restart sp cache");
        gso.e().a();
    }

    private void fk() {
        if (this.eu.size() > 0) {
            for (ISportStateChangeListener iSportStateChangeListener : this.eu) {
                if (iSportStateChangeListener != null) {
                    iSportStateChangeListener.m134x32b3e3a1();
                }
            }
        }
    }

    void c(boolean z) {
        if (this.bq) {
            return;
        }
        Map<Long, double[]> map = this.dn;
        if (map != null && map.size() > 0) {
            this.dz.c((int) this.fv, this.dn.size() - 1, z);
        } else {
            this.dz.c((int) this.fv, 0, z);
        }
    }

    private void r(boolean z) {
        if (z) {
            long da = da();
            if (da > 0) {
                long j2 = da - 1;
                this.dn.remove(Long.valueOf(j2));
                synchronized (f12936a) {
                    this.fk.remove(Long.valueOf(j2));
                }
            } else {
                LogUtil.b("Track_SportManager", "stopSport() mMotionPathPointsBuffer size is 0!");
            }
            this.as = cr();
            e(false);
            eo();
            fn();
            if (this.ck) {
                this.ck = false;
                bm();
            }
            fm();
            return;
        }
        f(false);
        fn();
        aTY_("action_stop_play_sport_music", null, 0);
        this.bd.destroy();
    }

    private boolean cm() {
        return !"false".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "dev_abnormal_track"));
    }

    public void bo() {
        k(false);
        LogUtil.a("Track_SportManager", "saveLastSportData() start !");
        ej();
        this.ey = 3;
        if (this.dn == null) {
            ReleaseLogUtil.c("Track_SportManager", "saveLastSportData() mMotionPathPointsBuffer is null!");
            return;
        }
        long da = da();
        if (da > 0) {
            this.dz.c((int) this.fv, this.dn.size(), false);
            long j2 = da - 1;
            this.dn.remove(Long.valueOf(j2));
            synchronized (f12936a) {
                this.fk.remove(Long.valueOf(j2));
            }
        } else {
            LogUtil.b("Track_SportManager", "saveLastSportData() mMotionPathPointsBuffer size is 0!");
        }
        e(false);
        eo();
        bm();
        this.dn.clear();
    }

    public float y() {
        return this.dz.a();
    }

    public void as() {
        if (this.ca) {
            LogUtil.h("Track_SportManager", "inited already");
            return;
        }
        if (!aq()) {
            j(al());
        }
        di();
        dc();
        if (this.aq == null) {
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.aq = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleAtFixedRate(new c(), 0L, 1000L, TimeUnit.MILLISECONDS);
        } else {
            LogUtil.a("Track_SportManager", "timer init timer has initialized");
        }
        gui guiVar = new gui();
        this.es = guiVar;
        e("SportServiceHelper", guiVar);
        this.el.init();
        this.es.onPreSport();
        this.ca = true;
    }

    public void d(SportBeat sportBeat) {
        gui guiVar = this.es;
        if (guiVar != null) {
            guiVar.a(sportBeat);
        }
    }

    private void dc() {
        if (this.z == null) {
            this.z = new gtl(this.aa, this.ab);
        }
    }

    private void dk() {
        if (this.du == 4) {
            this.y = 1;
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gtx.2
                @Override // java.lang.Runnable
                public void run() {
                    HwSportTypeInfo d2 = hln.c(BaseApplication.getContext()).d(gtx.this.ew);
                    gtx.this.ay = d2 == null ? 1 : d2.getHeartPostureType();
                    gtx gtxVar = gtx.this;
                    gtxVar.y = ffw.a(gtxVar.ay);
                    LogUtil.a("Track_SportManager", "mHeartRatePosture", Integer.valueOf(gtx.this.ay), "mClassifyMethod ", Integer.valueOf(gtx.this.y));
                }
            });
        }
    }

    private void e(String str) {
        guz guzVar = this.m;
        if (guzVar == null || str == null || !guzVar.j()) {
            return;
        }
        gvv.e(this.aa, str);
    }

    public void ao() {
        TrackDevelopConfig trackDevelopConfig = this.fy;
        if (trackDevelopConfig == null) {
            return;
        }
        trackDevelopConfig.d();
        this.bv = this.fy.b();
        this.bt = this.fy.e();
        this.bi = this.fy.e("auto_close_track_time", 60L);
        this.dt = TimeUnit.MINUTES.toMillis(this.bi);
        this.ab = this.fy.b("correct_location_shifting_enable", 0);
        this.cg = this.fy.c("indoor_running_developer_running", false);
        LogUtil.a("Track_SportManager", "mIsDiscardWhenStationary :", Boolean.valueOf(this.bv), " -- mIsEstimateDistanceBySteps :", Boolean.valueOf(this.bt), "-- mIntervalTime :", Long.valueOf(this.bi), "-- mConvertMode ", Integer.valueOf(this.ab), " -- mIsOpenIndoorRunningInfo :", Boolean.valueOf(this.cg));
    }

    public void b(int i2, guz guzVar, boolean z) {
        g(z);
        this.ew = i2;
        d(-1, -1.0f);
        List<hiy> list = this.n;
        if (list != null) {
            list.clear();
        }
        gvv.c(this.aa, "motion_path2.txt");
        gvv.c(this.aa, "simplemotion.txt");
        gvv.c(this.aa, "motion_cadence.txt");
        this.m = guzVar;
        c(1);
        e("initAutoTrack");
    }

    public void c(int i2) {
        this.gh = i2;
    }

    public void e(int i2) {
        this.ew = i2;
        this.bl = true;
        d(-1, -1.0f);
    }

    public void j(int i2) {
        this.fs = i2 == 1;
    }

    private void ej() {
        ScheduledExecutorService scheduledExecutorService = this.aq;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.aq = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(int i2) {
        int a2 = ktr.a(i2);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.dl.size() != 0 && this.av != i2 && currentTimeMillis - this.ax >= 2000) {
            StringBuilder sb = this.aw;
            sb.append(gwg.c(currentTimeMillis));
            sb.append(":");
            sb.append(a2);
            sb.append(";");
            this.av = i2;
            this.ax = currentTimeMillis;
            Iterator<IMapViewListener> it = this.dl.iterator();
            while (it.hasNext()) {
                it.next().updateGpsStatus(a2);
            }
            if (this.bc == 0) {
                this.bc = currentTimeMillis;
            }
        }
        if (currentTimeMillis - this.bc < 60000 || this.aw.length() <= 0) {
            return;
        }
        LogUtil.a("Track_SportManager", "gps-s ", this.aw.toString());
        this.aw.setLength(0);
        this.bc = currentTimeMillis;
    }

    private void eo() {
        MotionPath c2 = gwk.c(this.aa, this.ew);
        if (c2 == null) {
            return;
        }
        this.f122do = c2;
        a(c2);
        a("motion_path2.txt", false);
        this.dq = cx();
        this.dq.printReleaseSimplifyLog("Track_SportManager");
        int i2 = this.ew;
        if (i2 == 258 || i2 == 257 || i2 == 264 || i2 == 282 || i2 == 260) {
            gul gulVar = this.fa;
            if (gulVar == null) {
                e((ArrayList<StepRateData>) null);
            } else {
                e(gulVar.i());
            }
        } else {
            e((ArrayList<StepRateData>) null);
        }
        eh();
    }

    gul ak() {
        return this.fa;
    }

    int af() {
        return this.eb;
    }

    void a(String str, boolean z) {
        gwm gwmVar;
        List<ffn> b2 = gwk.b(this.aa);
        if (koq.b(b2) || (gwmVar = this.ek) == null) {
            LogUtil.b("Track_SportManager", "handleCadenceData ridePostureDataList == null");
            return;
        }
        gwmVar.d(b2);
        if (!z) {
            SharedPreferenceManager.e(this.aa, Integer.toString(20002), "saved_maximum_cadence", (String) null, (StorageParams) null);
        }
        List<String> b3 = gwm.b(b2);
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<String> it = b3.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        if (!TextUtils.isEmpty(stringBuffer.toString())) {
            gwo.b(this.aa, stringBuffer.toString(), str);
        } else {
            LogUtil.b("Track_SportManager", "stringBuffer.toString() is null:");
        }
    }

    private void a(MotionPath motionPath) {
        boolean z;
        if (this.cr) {
            return;
        }
        Map<Integer, Float> a2 = this.dz.a(false);
        boolean z2 = true;
        if (a2 == null || a2.size() <= 0) {
            z = false;
        } else {
            LogUtil.a("Track_SportManager", "saveCacheData：save remain pace ", a2);
            motionPath.requestPaceMap().putAll(a2);
            z = true;
        }
        Map<Integer, Float> b2 = this.dz.b(false);
        if (b2 == null || b2.size() <= 0) {
            z2 = z;
        } else {
            LogUtil.a("Track_SportManager", "saveCacheData：save remain britishpace ", b2);
            motionPath.requestBritishPaceMap().putAll(b2);
        }
        if (b(motionPath, z2)) {
            ef();
        }
    }

    private boolean b(MotionPath motionPath, boolean z) {
        boolean j2 = this.fa != null ? j(motionPath, z) : z;
        if (this.az != null) {
            j2 = d(motionPath, j2);
        }
        if (this.ga != null) {
            j2 = a(motionPath, j2);
        }
        if (this.en != null) {
            z = e(motionPath, j2);
        }
        if (this.ek != null) {
            z = c(motionPath, j2);
        }
        gvq gvqVar = this.ec;
        if (gvqVar != null) {
            ArrayList<koe> d2 = gvqVar.d(false);
            if (koq.c(d2)) {
                motionPath.saveSpeedList(d2);
            }
        }
        return z;
    }

    private boolean e(MotionPath motionPath, boolean z) {
        gwl gwlVar = this.en;
        if (gwlVar == null) {
            return z;
        }
        ArrayList<ffs> d2 = gwlVar.d(false);
        if (!koq.c(d2)) {
            return z;
        }
        LogUtil.a("Track_SportManager", "saveCacheRunningPosture：save remain runningPostureList ", Integer.valueOf(d2.size()));
        motionPath.requestRunningPostureList().addAll(d2);
        return true;
    }

    private boolean c(MotionPath motionPath, boolean z) {
        gwm gwmVar = this.ek;
        if (gwmVar == null) {
            return z;
        }
        ArrayList<ffn> b2 = gwmVar.b(false);
        if (!koq.c(b2)) {
            return z;
        }
        LogUtil.a("Track_SportManager", "saveCacheRidePostureData：save remain ridePostureDataList ", Integer.valueOf(b2.size()));
        motionPath.requestRidePostureDataList().addAll(b2);
        return true;
    }

    private boolean j(MotionPath motionPath, boolean z) {
        ArrayList<StepRateData> a2 = this.fa.a(false);
        if (!koq.c(a2)) {
            return z;
        }
        LogUtil.a("Track_SportManager", "saveCacheData：save remain StepRateList ", a2);
        motionPath.requestStepRateList().addAll(a2);
        return true;
    }

    private boolean d(MotionPath motionPath, boolean z) {
        ArrayList<HeartRateData> d2 = this.az.d(false);
        if (koq.c(d2)) {
            LogUtil.a("Track_SportManager", "saveCacheData：save remain heartRateList ", Integer.valueOf(d2.size()));
            motionPath.requestHeartRateList().addAll(d2);
            z = true;
        }
        ArrayList<HeartRateData> b2 = this.az.b(false);
        if (koq.c(b2)) {
            LogUtil.a("Track_SportManager", "saveCacheData：save remain heartRateList ", Integer.valueOf(b2.size()));
            motionPath.requestInvalidHeartRateList().addAll(b2);
        }
        return z;
    }

    private boolean a(MotionPath motionPath, boolean z) {
        ArrayList<knz> e2 = this.ga.e(false);
        if (!koq.c(e2)) {
            return z;
        }
        LogUtil.a("Track_SportManager", "saveCacheData：save remain tmpAltitudeList ", Integer.valueOf(e2.size()));
        motionPath.requestAltitudeList().addAll(e2);
        return true;
    }

    private void eh() {
        f(false);
        if (j == null) {
            j = gso.e().c();
        }
        final PluginSportTrackAdapter pluginSportTrackAdapter = j;
        gum gumVar = this.gb;
        if (gumVar != null && this.o == 0) {
            pluginSportTrackAdapter.saveTrackPointData(gumVar.c(), this.ew);
        }
        ffd b2 = this.fg.b();
        if (this.gh == 1) {
            gwo.d(this.aa, "motion_path2.txt", "auto_detect_motion_path.txt");
            new Thread(new Runnable() { // from class: guc
                @Override // java.lang.Runnable
                public final void run() {
                    gtx.this.d(pluginSportTrackAdapter);
                }
            }).start();
            return;
        }
        if (b2 != null && guj.d().e() && b2.f() == 1 && Math.round((this.fv - b2.i()) / 10.0f) >= 1) {
            e(pluginSportTrackAdapter, 60);
            return;
        }
        gtj gtjVar = this.af;
        if (gtjVar != null && gtjVar.i()) {
            this.af.b(this.dq, this.f122do);
        }
        h(this.dq);
        new Thread(new Runnable() { // from class: gue
            @Override // java.lang.Runnable
            public final void run() {
                gtx.this.a(pluginSportTrackAdapter);
            }
        }).start();
    }

    /* synthetic */ void d(PluginSportTrackAdapter pluginSportTrackAdapter) {
        Object[] objArr = new Object[2];
        objArr[0] = "mPluginTrackAdapter is null,";
        objArr[1] = Boolean.valueOf(pluginSportTrackAdapter == null);
        ReleaseLogUtil.e("Track_SportManager", objArr);
        if (pluginSportTrackAdapter != null) {
            LogUtil.a("Track_SportManager", "mPluginTrackAdapter.saveTrackData is not null");
            pluginSportTrackAdapter.saveTrackData(this.dq, "auto_detect_motion_path.txt");
        }
        this.bb = true;
    }

    /* synthetic */ void a(PluginSportTrackAdapter pluginSportTrackAdapter) {
        Object[] objArr = new Object[2];
        objArr[0] = "mPluginTrackAdapter is null,";
        objArr[1] = Boolean.valueOf(pluginSportTrackAdapter == null);
        ReleaseLogUtil.e("Track_SportManager", objArr);
        d(this.f122do, this.dq);
        if (pluginSportTrackAdapter != null) {
            LogUtil.a("Track_SportManager", "mPluginTrackAdapter.saveTrackData is not null");
            pluginSportTrackAdapter.saveTrackData(this.dq, "motion_path2.txt");
        }
        this.bb = true;
    }

    private void e(final PluginSportTrackAdapter pluginSportTrackAdapter, final int i2) {
        ExtendHandler extendHandler;
        ReleaseLogUtil.e("Track_SportManager", "saveTargetData:", Boolean.valueOf(guj.d().a()));
        if (!guj.d().a() && i2 > 0 && (extendHandler = this.au) != null) {
            extendHandler.postTask(new Runnable() { // from class: gty
                @Override // java.lang.Runnable
                public final void run() {
                    gtx.this.a(pluginSportTrackAdapter, i2);
                }
            }, 50L);
            return;
        }
        ReleaseLogUtil.e("Track_SportManager", "showTrackMap wait time:", Long.valueOf((60 - i2) * 50));
        final MotionPathSimplify e2 = gwk.e(this.aa, "target_motion_simplify.txt");
        Object[] objArr = new Object[2];
        objArr[0] = "saveDataToDb motionPathSimplify:";
        objArr[1] = Boolean.valueOf(e2 == null);
        ReleaseLogUtil.e("Track_SportManager", objArr);
        a(e2);
        gtj gtjVar = this.af;
        if (gtjVar != null && gtjVar.i()) {
            this.af.b(e2, this.f122do);
        }
        h(e2);
        new Thread(new Runnable() { // from class: gua
            @Override // java.lang.Runnable
            public final void run() {
                gtx.this.b(e2, pluginSportTrackAdapter);
            }
        }).start();
    }

    /* synthetic */ void a(PluginSportTrackAdapter pluginSportTrackAdapter, int i2) {
        e(pluginSportTrackAdapter, i2 - 1);
    }

    /* synthetic */ void b(MotionPathSimplify motionPathSimplify, PluginSportTrackAdapter pluginSportTrackAdapter) {
        d(this.f122do, motionPathSimplify);
        Object[] objArr = new Object[2];
        objArr[0] = "saveTargetData mPluginTrackAdapter is null,";
        objArr[1] = Boolean.valueOf(pluginSportTrackAdapter == null);
        ReleaseLogUtil.e("Track_SportManager", objArr);
        if (pluginSportTrackAdapter != null) {
            pluginSportTrackAdapter.saveTrackData(motionPathSimplify, "target_motion_path.txt");
            gwo.a(this.aa, motionPathSimplify, "target_motion_simplify.txt");
        }
        this.bb = true;
    }

    private void d(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        this.bf = this.bd.a(motionPath, motionPathSimplify, this.o == 0);
        this.bd.destroy();
    }

    private void h(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h("Track_SportManager", "saveSportTrainPoint motionPathSimplify == null");
            return;
        }
        int i2 = this.ew;
        if (i2 == 264 || i2 == 258 || i2 == 280) {
            int d2 = caz.d(this.f122do, this.ad, motionPathSimplify.requestStartTime());
            motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_TRAINING_POINTS, String.valueOf(d2));
            LogUtil.a("Track_SportManager", "saveSportTrainPoint trainPoint:", Integer.valueOf(d2), "mHasInitConfig:", Boolean.valueOf(this.ba));
        }
    }

    public void a(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.b("Track_SportManager", "correctTotalTime motionPathSimplify is null");
        } else {
            motionPathSimplify.correctTotalTime("Track_SportManager");
        }
    }

    public MotionPath ad() {
        return this.f122do;
    }

    public long ab() {
        return this.bf;
    }

    public MotionPathSimplify ae() {
        return this.dq;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MotionPathSimplify cx() {
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        c(motionPathSimplify);
        TrackAltitudeMgr trackAltitudeMgr = this.ga;
        if (trackAltitudeMgr != null) {
            motionPathSimplify.saveCreepingWave(((float) trackAltitudeMgr.d()) * 10.0f);
            motionPathSimplify.saveTotalDescent(((float) this.ga.c()) * 10.0f);
        }
        gwg.b(this.aa);
        i(motionPathSimplify);
        int d2 = gwg.d();
        if (d2 == 1) {
            motionPathSimplify.saveMapType(1);
        } else if (d2 == 2) {
            motionPathSimplify.saveMapType(2);
        } else {
            motionPathSimplify.saveMapType(3);
        }
        de();
        if (!TextUtils.isEmpty(this.ac)) {
            motionPathSimplify.saveMapCoordinate(this.ac);
        }
        motionPathSimplify.saveIsNewCoordinate(true);
        if (this.ew == 264 || this.dn.isEmpty()) {
            motionPathSimplify.saveHasTrackPoint(false);
        } else {
            motionPathSimplify.saveHasTrackPoint(true);
        }
        e(motionPathSimplify, this.f122do, false);
        MotionPath motionPath = this.f122do;
        if (motionPath != null && motionPath.isValidHeartRateList()) {
            motionPathSimplify.saveAvgHeartRate(gvv.c(this.f122do.requestHeartRateList()));
            motionPathSimplify.saveMaxHeartRate(gvv.d(this.f122do.requestHeartRateList()));
        }
        MotionPath motionPath2 = this.f122do;
        if (motionPath2 != null && motionPath2.isValidStepRateList()) {
            int[] d3 = gvv.d(this.f122do.requestStepRateList(), this.ep);
            try {
                motionPathSimplify.saveBestStepRate(d3[1]);
                motionPathSimplify.saveAvgStepRate(d3[0]);
                motionPathSimplify.saveTotalSteps(d3[2]);
            } catch (IndexOutOfBoundsException e2) {
                LogUtil.h("Track_SportManager", "indexOutOfBoundsException ", e2.getMessage());
            }
        }
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            gwlVar.c(motionPathSimplify);
        }
        gtj gtjVar = this.af;
        if (gtjVar != null) {
            gtjVar.c(motionPathSimplify);
        }
        motionPathSimplify.saveStartSteps(this.fc);
        motionPathSimplify.saveRealTimeSteps(this.eb);
        motionPathSimplify.saveDeviceType(32);
        gto gtoVar = this.bd;
        if (gtoVar != null) {
            gtoVar.b(motionPathSimplify);
        }
        return motionPathSimplify;
    }

    private void de() {
        MotionPath motionPath;
        if (!TextUtils.isEmpty(this.ac) || (motionPath = this.f122do) == null || motionPath.requestLbsDataMap() == null) {
            return;
        }
        double[] dArr = this.f122do.requestLbsDataMap().get(0L);
        if (dArr == null || dArr.length < 2) {
            LogUtil.b("Track_SportManager", "firstLbs is ", dArr);
        } else if (ktl.b(dArr[0], dArr[1]) == 1) {
            this.ac = AMapLocation.COORD_TYPE_GCJ02;
        } else {
            this.ac = AMapLocation.COORD_TYPE_WGS84;
        }
    }

    private void i(MotionPathSimplify motionPathSimplify) {
        String str = this.al;
        if ((str == null && this.ed == null) || (this.ey == 3 && TextUtils.isEmpty(str) && TextUtils.isEmpty(this.ed))) {
            hpx.c(motionPathSimplify);
            this.al = motionPathSimplify.getExtendDataString("localDeviceId", "");
            String extendDataString = motionPathSimplify.getExtendDataString("localProdId", "");
            this.ed = extendDataString;
            LogUtil.a("Track_SportManager", "saveShareLocalDevice mDeviceId ", this.al, " mProdId ", extendDataString);
            return;
        }
        motionPathSimplify.addExtendDataMap("localDeviceId", this.al);
        motionPathSimplify.addExtendDataMap("localProdId", this.ed);
    }

    private void c(MotionPathSimplify motionPathSimplify) {
        motionPathSimplify.saveStartTime(this.ex);
        motionPathSimplify.saveEndTime(this.as);
        motionPathSimplify.saveTotalCalories(Math.round(x()) * 1000);
        motionPathSimplify.saveActiveCalories(Math.round(getActiveCalorie()) * 1000);
        motionPathSimplify.saveTotalDistance((int) this.fv);
        motionPathSimplify.saveSportId(this.er);
        motionPathSimplify.saveSportType(this.ew);
        motionPathSimplify.saveTrackType(this.gh);
        motionPathSimplify.saveAvgPace(v());
        motionPathSimplify.saveTotalTime(this.ep);
        motionPathSimplify.saveSportDataSource(z());
        motionPathSimplify.saveHeartRateZoneType(this.y);
        motionPathSimplify.addExtendDataMap("sportHeartPosture", String.valueOf(this.ay));
        int i2 = this.ew;
        if ((i2 == 264 || i2 == 258 || i2 == 280) && !TextUtils.isEmpty(this.dx)) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("runPaceZoneConfig", this.dx);
            motionPathSimplify.saveExtendDataMap(hashMap);
        }
    }

    public int z() {
        return cu() > 0 ? 4 : 0;
    }

    void e(MotionPathSimplify motionPathSimplify, MotionPath motionPath, boolean z) {
        synchronized (this) {
            if (z) {
                motionPathSimplify.savePaceMap(this.dz.h());
                motionPathSimplify.saveBritishPaceMap(this.dz.j());
            } else {
                motionPathSimplify.savePaceMap(this.dz.f());
                motionPathSimplify.saveBritishPaceMap(this.dz.c());
            }
            motionPathSimplify.savePartTimeMap(this.dz.i());
            motionPathSimplify.saveBritishPartTimeMap(this.dz.e());
            if (motionPath != null && motionPath.isValidPaceMap()) {
                if (this.bq) {
                    motionPathSimplify.saveBestPace(gvv.e(gvv.a(this.dz.f()))[0].floatValue());
                } else {
                    motionPathSimplify.savePaceMap(motionPath.requestPaceMap());
                    motionPathSimplify.saveBestPace(gvv.e(gvv.a(motionPath.requestPaceMap()))[0].floatValue());
                }
                motionPathSimplify.saveAbnormalTrack(this.o);
            }
            motionPathSimplify.addExtendDataMap("STEP_STATICS_EXCEPTION", this.cn ? "1" : "0");
        }
    }

    public float v() {
        if (this.fv <= 0.0f) {
            return 0.0f;
        }
        return ((this.ep * 1.0f) / 1000.0f) / ((((int) r0) * 1.0f) / 1000.0f);
    }

    public void a(MotionData motionData) {
        this.dn.clear();
        if (motionData == null) {
            ReleaseLogUtil.c("Track_SportManager", "motionData is null");
            return;
        }
        Map<Long, double[]> lbsDataMap = motionData.getLbsDataMap();
        synchronized (lbsDataMap) {
            if (lbsDataMap.size() > 0) {
                for (Map.Entry<Long, double[]> entry : lbsDataMap.entrySet()) {
                    if (entry instanceof Map.Entry) {
                        Map.Entry<Long, double[]> entry2 = entry;
                        if (entry2.getValue().length >= 2) {
                            hjd hjdVar = new hjd(entry2.getValue()[0], entry2.getValue()[1]);
                            if (gvv.b(hjdVar.b, 90.0d) && gvv.b(hjdVar.d, -80.0d)) {
                                this.cj = true;
                            } else {
                                this.df = hjdVar;
                                this.ae = hjdVar;
                                this.cj = false;
                            }
                        }
                    }
                }
            }
        }
        this.dn = lbsDataMap;
        LogUtil.a("Track_SportManager", " setMotionPath mMotionPathPointsBuffer size:", Integer.valueOf(lbsDataMap.size()));
        b(motionData);
        d(motionData);
        if (this.fa == null) {
            this.fa = new gul(j, this);
        }
        this.fa.c(motionData.getStepRateList());
        this.ec.b();
        LogUtil.a("Track_SportManager", "Recovery steplist ", Integer.valueOf(this.fa.i().size()));
        if (this.ga == null) {
            this.ga = new TrackAltitudeMgr(this.aa);
        }
        this.ga.c(motionData.requestAltitudeList());
        this.eb = motionData.getRealTimeSteps();
        this.ah = motionData.getRealTimeSteps();
    }

    private void d(MotionData motionData) {
        if (this.en == null) {
            this.en = new gwl(j);
        }
        this.en.a(motionData.getRunningPostureList());
        if (this.az == null) {
            this.az = new gtq(j);
        }
        this.az.d(motionData.getHeartRateList(), motionData.getInvalidHeartRateList());
        if (this.ek == null) {
            this.ek = new gwm(j, this.aa);
        }
        this.ek.c(motionData.getRidePostureDataList());
        this.ek.d();
    }

    private void b(MotionData motionData) {
        float totalDistance = motionData.getTotalDistance();
        this.fv = totalDistance;
        this.ft = totalDistance;
        this.fq = totalDistance;
        this.u.c((motionData.getTotalCalories() * 1.0f) / 1000.0f);
        this.ep = motionData.getTotalTime();
        this.l = System.currentTimeMillis();
    }

    private void dm() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        this.gk = userInfo;
        if (userInfo != null) {
            int heightOrDefaultValue = userInfo.getHeightOrDefaultValue();
            if (heightOrDefaultValue == 0) {
                this.gm = 0.714f;
            } else {
                this.gm = ((heightOrDefaultValue * 1.0f) / 100.0f) * 0.42f;
            }
            gwa.d(userInfo, this.ge.b("calibrate_distance_indoor_running_data_duration"), this.ge.b("calibrate_distance_indoor_running_data_step"), this.ge.b("calibrate_distance_indoor_running_data_actual_distance"));
            this.an = gwa.a(heightOrDefaultValue);
            if (this.ew == 264) {
                this.gm = gwa.a(heightOrDefaultValue);
            }
        } else {
            LogUtil.h("Track_SportManager", "accountInfo is null");
        }
        if (this.ew == 259) {
            this.v = gvv.a(this.aa);
        }
    }

    public boolean bd() {
        return gwk.e(this.aa) && this.fv >= 100.0f;
    }

    public String an() {
        return this.er;
    }

    public void q() {
        synchronized (d) {
            HealthSportingNotificationHelper healthSportingNotificationHelper = this.ev;
            if (healthSportingNotificationHelper != null) {
                a(healthSportingNotificationHelper);
                this.ev.d();
                this.ev = null;
                ReleaseLogUtil.e("Track_SportManager", "complete clear Notification");
            } else {
                ReleaseLogUtil.d("Track_SportManager", "clearNotification is fail");
            }
        }
    }

    public void bm() {
        LogUtil.a("Track_SportManager", "SportManager onDestroy");
        el();
        this.ac = "";
        this.el.destroy();
        this.gj.clear();
        h(0);
        q();
        ez();
        cl();
        ej();
        fa();
        j = null;
        gum gumVar = this.gb;
        if (gumVar != null) {
            gumVar.e();
        }
        SportMusicInteratorService.b();
        gup gupVar = this.gg;
        if (gupVar != null) {
            gupVar.a();
        }
        a("Track_SportManagerTargetUpdate");
        fe();
        TrackAltitudeMgr trackAltitudeMgr = this.ga;
        if (trackAltitudeMgr != null && trackAltitudeMgr.b()) {
            LogUtil.a("Track_SportManager", "onDestary Track_TrackAltitudeMgr");
            this.ga.i();
        }
        gve.b();
    }

    private void fe() {
        ExtendHandler extendHandler = this.au;
        if (extendHandler == null) {
            LogUtil.h("Track_SportManager", "unregisterDataReports mExtendHandler is null!");
        } else {
            extendHandler.postTask(new Runnable() { // from class: guf
                @Override // java.lang.Runnable
                public final void run() {
                    gtx.this.bl();
                }
            });
        }
    }

    /* synthetic */ void bl() {
        ey();
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            gtqVar.a();
        }
        gwm gwmVar = this.ek;
        if (gwmVar != null) {
            gwmVar.c();
        }
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            gwlVar.a();
        }
        gwn gwnVar = this.eg;
        if (gwnVar != null) {
            gwnVar.b();
        }
        HandlerExecutor.a(new Runnable() { // from class: gug
            @Override // java.lang.Runnable
            public final void run() {
                gtx.this.bf();
            }
        });
    }

    /* synthetic */ void bf() {
        this.fa = null;
        this.az = null;
        this.ek = null;
        this.en = null;
        this.eg = null;
        if (this.dm != null) {
            this.dm = null;
        }
        if (this.af != null) {
            this.af = null;
        }
        if (this.ad != null) {
            this.ad = null;
        }
    }

    private void cl() {
        i.clear();
        this.dl.clear();
        this.eu.clear();
        this.eq.clear();
    }

    public int ac() {
        return this.av;
    }

    public void p() {
        gul gulVar;
        Bundle bundle = new Bundle();
        bundle.putString("duration", getFormattedTime());
        bundle.putLong("duration_digital", this.ep);
        aTT_(bundle);
        bundle.putInt(BleConstants.SPORT_TYPE, this.ew);
        aTV_(bundle);
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            bundle = gtqVar.aTD_(bundle);
        }
        bundle.putString("calorie", String.valueOf(x()));
        if (getActiveCalorie() > 0.0f) {
            bundle.putString(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, String.valueOf(getActiveCalorie()));
        }
        bundle.putString("stepRate", "");
        aTW_(bundle);
        aUa_(bundle);
        if (this.ew == 264) {
            int i2 = this.ah;
            bundle.putString(MedalConstants.EVENT_STEPS, i2 > 0 ? String.valueOf(i2) : "");
            double d2 = this.ez;
            if (d2 < 250.0d) {
                bundle.putString("stepRate", String.valueOf(Math.round(d2)));
            } else {
                bundle.putString("stepRate", gvv.e(BaseApplication.getContext()));
            }
        }
        bundle.putString("indoor_run_distance", String.valueOf(this.fr));
        bundle.putString("indoor_run_normal_distance", String.valueOf(this.ft));
        bundle.putString("indoor_run_step", String.valueOf(this.bm));
        bundle.putString("indoor_running_number_data", String.valueOf(gwa.b()));
        bundle.putString("climb_3d_dis", String.valueOf(this.fw));
        bundle.putString("climb_2d_dis", String.valueOf(this.fv));
        bundle.putInt("ihealth_free_indoor_running_style", this.gf);
        int i3 = this.ff;
        if ((i3 == 2 || i3 == 3) && this.gf == 1) {
            bundle.putInt("ihealth_free_indoor_running_style", 2);
        }
        TrackAltitudeMgr trackAltitudeMgr = this.ga;
        if (trackAltitudeMgr != null) {
            bundle.putString("climb_3d_pressure", String.valueOf(trackAltitudeMgr.e()));
        }
        gwm gwmVar = this.ek;
        if (gwmVar != null) {
            gwmVar.aUV_(bundle);
        }
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            gwlVar.aVc_(bundle);
        }
        gwn gwnVar = this.eg;
        if (gwnVar != null) {
            gwnVar.aUU_(bundle);
        }
        if (gvv.a(this.ew) && (gulVar = this.fa) != null) {
            int g2 = gulVar.g();
            if (g2 > 0) {
                bundle.putString("stepRate", String.valueOf(Math.round(g2)));
            } else {
                bundle.putString("stepRate", "--");
            }
        }
        try {
            for (ISportDataFragmentListener iSportDataFragmentListener : i) {
                if (iSportDataFragmentListener != null) {
                    iSportDataFragmentListener.updateSportViewFragment(bundle);
                }
            }
        } catch (NumberFormatException e2) {
            LogUtil.h("Track_SportManager", "countdownSportDefaultFragment ", e2.getMessage());
        }
    }

    private void aTT_(Bundle bundle) {
        if (this.ew == 261) {
            bundle.putString("distance", "0.00");
        } else {
            bundle.putString("distance", String.valueOf(h()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j2) {
        this.dz.b();
        this.ec.a(Math.round(j2 / 1000.0f), y());
    }

    private void aTV_(Bundle bundle) {
        float y = y();
        if (y <= 0.0f || y >= 200.0f) {
            bundle.putString("speed", "--");
        } else if (this.fv < 10.0f) {
            bundle.putString("speed", "--");
        } else {
            int round = Math.round((((y * 1.0f) * 1000.0f) * 10.0f) / 3600.0f);
            bundle.putString("speed", gwg.c((((UnitUtil.h() ? (float) UnitUtil.e(round, 3) : round) * 1.0f) * 3600.0f) / 10000.0f, 1));
        }
        if (bb() && y > 1.0E-4f) {
            int seconds = (int) (TimeUnit.HOURS.toSeconds(1L) / y);
            if (UnitUtil.h()) {
                seconds = (int) UnitUtil.d(seconds, 3);
            }
            r5 = seconds < 6000 ? seconds : 0;
            bundle.putString("pace", String.valueOf(r5));
        } else {
            bundle.putString("pace", "--");
        }
        if (this.ew != 258 || r5 <= 0 || !hab.g() || hab.i()) {
            return;
        }
        hac.a().e.add(Integer.valueOf(r5 / 60));
    }

    private void aTW_(Bundle bundle) {
        if (dx()) {
            ffd b2 = this.fg.b();
            int a2 = (int) (b2.a() * 100.0f);
            if (b2.f() == 0) {
                bundle.putString("countdownValue", cv());
                bundle.putInt("progreeRate", a2);
            } else if (b2.f() == 1) {
                bundle.putString("countdownValue", cs());
                bundle.putInt("progreeRate", a2);
            } else if (b2.f() == 2) {
                bundle.putString("countdownValue", cp());
                bundle.putInt("progreeRate", a2);
            } else if (b2.f() == 5 || b2.f() == 4) {
                bundle.putInt("progreeRate", a2);
            } else if (b2.f() == 200) {
                bundle.putInt("progreeRate", 100);
            } else {
                bundle.putString("countdownValue", "");
            }
            int i2 = this.du;
            if (i2 == 3 || i2 == 4) {
                bundle.putString("planProgress", e(b2.c(), b2.g() - 1));
                bundle.putInt("sport_target_type_sportting", b2.f());
            }
            bundle.putBoolean("planIsLastTarget", b2.c() == b2.g());
            bundle.putString("extroInfo", b2.h());
            fhq.axN_(b2, bundle);
        }
    }

    private boolean dx() {
        fer ferVar = this.fg;
        return (ferVar == null || ferVar.b() == null) ? false : true;
    }

    private static String e(int i2, int i3) {
        return i2 > i3 ? "" : BaseApplication.getContext().getString(R.string._2130842706_res_0x7f021452, Integer.valueOf(i2), Integer.valueOf(i3));
    }

    private String cs() {
        ffd b2 = this.fg.b();
        if (b2.a() < 1.0f) {
            return gwg.c(((1.0f - b2.a()) * b2.i()) / 1000.0f, 2);
        }
        return this.fm;
    }

    public void d(int i2, float f2) {
        ffd ffdVar = new ffd("", i2, gwg.c(i2, f2));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(ffdVar);
        this.fg.e(arrayList);
    }

    private String cv() {
        ffd b2 = this.fg.b();
        if (b2.a() >= 1.0f) {
            ReleaseLogUtil.e("Track_SportManager", "Time goal done");
            return this.fm;
        }
        float a2 = (1.0f - b2.a()) * b2.i();
        return gwg.a((int) ((a2 / 3600000.0f) % 100.0f)) + ":" + gwg.a((int) ((a2 / 60000.0f) % 60.0f)) + ":" + gwg.a((int) ((a2 / 1000.0f) % 60.0f));
    }

    private String cp() {
        String format;
        if (this.fg.b().a() >= 1.0f) {
            ReleaseLogUtil.e("Track_SportManager", "Calorie goal done");
            return this.fm;
        }
        synchronized (this.x) {
            format = this.x.format((((1.0f - r0.a()) * r0.i()) * 1.0f) / 1000.0f);
        }
        return format;
    }

    public boolean al() {
        if (this.gh == 1) {
            LogUtil.a("Track_SportManager", "getVoiceEnable() is false");
            return false;
        }
        if (gww.e() == 0) {
            LogUtil.a("Track_SportManager", "getVoiceEnable() is false");
            return false;
        }
        LogUtil.a("Track_SportManager", "getVoiceEnable() is true ");
        return true;
    }

    public void j(boolean z) {
        LogUtil.a("Track_SportManager", "setVoiceEnable() ", Boolean.valueOf(z));
        if (z) {
            gww.e(1);
        } else {
            gww.e(0);
        }
        d(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dv() {
        gtq gtqVar;
        Map<Double, Double> treeMap;
        if (this.gd != null) {
            gvp gvpVar = this.dz;
            long b2 = gvpVar != null ? gvpVar.b((int) this.fv) : -1L;
            if (b2 == 1000) {
                if (this.ch) {
                    this.ch = false;
                } else {
                    this.ch = true;
                }
            } else {
                this.ch = false;
            }
            int floor = dx() ? (int) Math.floor(this.fg.b().a() * 100.0f) : 0;
            if (!this.ch) {
                int i2 = this.cw;
                if (b() != -1) {
                    i2 = b();
                    this.cw = i2;
                }
                this.gd.d(this.ep, (int) this.fv, b2, i2);
                gvp gvpVar2 = this.dz;
                if (gvpVar2 != null) {
                    treeMap = gvpVar2.i();
                } else {
                    treeMap = new TreeMap<>();
                }
                if (!this.cc && LanguageUtil.j(this.aa)) {
                    this.gd.c(this.fv, floor, treeMap);
                }
            }
            if (this.gd.a(floor) && !this.cc) {
                this.gd.a();
                this.gd.e(true);
            }
            if (!LanguageUtil.j(this.aa) || (gtqVar = this.az) == null) {
                return;
            }
            this.gd.d(gtqVar.e());
        }
    }

    private void e(hiy hiyVar) {
        if (hiyVar == null) {
            return;
        }
        if (this.dn == null) {
            ReleaseLogUtil.c("Track_SportManager", " doAddPoint mMotionPathPointsBuffer is null!");
            return;
        }
        hiy hiyVar2 = new hiy(hiyVar);
        h(hiyVar);
        this.cx = System.currentTimeMillis();
        this.l = System.currentTimeMillis();
        this.ag = hiyVar.i();
        this.ae = gwf.e(hiyVar);
        this.df = gwf.e(hiyVar);
        this.ct = m(hiyVar);
        this.fo = this.ag - this.cx;
        long da = da();
        hjd e2 = gwf.e(hiyVar2);
        synchronized (f12936a) {
            this.fk.put(Long.valueOf(da), new double[]{e2.b, e2.d, hiyVar2.c(), this.ag});
        }
        this.dn.put(Long.valueOf(da), new double[]{e2.b, e2.d, hiyVar2.c(), this.ag});
    }

    private long da() {
        if (this.dn.size() == 0) {
            return 0L;
        }
        return ((Long) ((TreeMap) this.dn).lastKey()).longValue() + 1;
    }

    private void co() {
        IMapDrawingUpdater iMapDrawingUpdater = this.dm;
        if (iMapDrawingUpdater != null) {
            iMapDrawingUpdater.drawLineToMap(this.ap);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.model.TimeUpdater
    public void increaseOrDecreaseSportDuration() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = this.dc;
        long j3 = elapsedRealtime - j2;
        this.r = j3;
        if (j2 == 0) {
            this.ep += 1000;
        } else {
            this.ep += j3;
        }
        this.dc = elapsedRealtime;
        if (hab.g()) {
            hac.a().b(this.ep);
        }
    }

    private void er() {
        LogUtil.a("Track_SportManager", "startHandlerThread() enter");
        if (this.au == null) {
            dj();
        }
    }

    private void dj() {
        this.au = HandlerCenter.yt_(new a(this), "SportManagerHandlerThread");
    }

    private void ey() {
        LogUtil.a("Track_SportManager", "stopHandlerThread() enter");
        ExtendHandler extendHandler = this.au;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.au.quit(true);
        }
    }

    public void d(int i2) {
        this.du = i2;
    }

    public ktg aa() {
        return bt();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IUpGpsStatusListener
    public void registerGps() {
        if (this.ew != 264) {
            ktg bt = bt();
            Map<Long, double[]> map = this.dn;
            if (map != null && map.size() != 0) {
                bt.b();
                bt.b(true);
            }
            b("sportTrackLocation");
            c("sportTrackLocation");
            eg();
            gwq.a().f();
        }
    }

    public void aUf_(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (this.fa == null) {
            this.fa = new gul(j, this);
        }
        this.fa.e(bundle.getParcelableArrayList("autoTrackStepList"));
        float f2 = bundle.getInt("autoTrackDistance", 0);
        this.fv = f2;
        this.ft = f2;
        this.fq = f2;
        this.u.a(bundle.getFloat("autoTrackCalories", 0.0f));
        this.u.b(bundle.getFloat("autoTrackActiveCalories", 0.0f));
        this.ep = bundle.getLong("autoTrackTime");
        long currentTimeMillis = System.currentTimeMillis();
        this.as = currentTimeMillis;
        this.ex = currentTimeMillis - this.ep;
        this.eb = bundle.getInt("autoTrackStep");
        this.ah = bundle.getInt("autoTrackStep");
        this.cm = true;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IUpGpsStatusListener
    public void unregisterGps() {
        i("sportTrackLocation");
        gwq.a().i();
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        ITargetUpdateListener iTargetUpdateListener = this.fh;
        if (iTargetUpdateListener != null) {
            iTargetUpdateListener.onTargetDataUpdate(ffdVar);
        }
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i2, String str) {
        guq guqVar;
        if (i2 == 200 && (guqVar = this.gd) != null) {
            guqVar.a();
            this.gd.e(true);
        }
        ITargetUpdateListener iTargetUpdateListener = this.fh;
        if (iTargetUpdateListener != null) {
            iTargetUpdateListener.onStateUpdate(i2, str);
        }
    }

    public void t() {
        this.fg.a();
    }

    public void bw() {
        this.ey = 6;
    }

    public void b(ITargetUpdateListener iTargetUpdateListener) {
        this.fh = iTargetUpdateListener;
    }

    public void b(String str, ITargetUpdateListener iTargetUpdateListener) {
        fer ferVar = this.fg;
        if (ferVar == null) {
            return;
        }
        ferVar.c(str, iTargetUpdateListener);
    }

    public void a(String str) {
        fer ferVar = this.fg;
        if (ferVar == null) {
            return;
        }
        ferVar.e(str);
    }

    static class d extends Handler {
        private WeakReference<gtx> c;

        d(Looper looper, gtx gtxVar) {
            super(looper);
            this.c = new WeakReference<>(gtxVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            gtx gtxVar = this.c.get();
            if (gtxVar == null) {
                LogUtil.a("Track_SportManager", "UIHandler()  manager is null");
                return;
            }
            if (message == null) {
                return;
            }
            int i = message.what;
            if (i == 4) {
                LogUtil.a("Track_SportManager", "handleMessage() enter, command = pauseSport");
                gtxVar.m(2);
            } else if (i == 5) {
                LogUtil.a("Track_SportManager", "handleMessage() enter, command = resumeSport");
                gtxVar.m(1);
            } else if (i == 6) {
                LogUtil.a("Track_SportManager", "handleMessage() enter, command = stopSport");
                gtxVar.m(3);
            } else if (i == 202) {
                Serializable serializable = message.getData().getSerializable("PointProcessed");
                if (serializable instanceof hiy) {
                    gtxVar.d((hiy) serializable);
                }
            } else if (i == 301) {
                gtxVar.ef();
            }
            super.handleMessage(message);
        }
    }

    private void ew() {
        LogUtil.a("Track_SportManager", "startUiHandlerThread() enter");
        if (this.gl == null) {
            LogUtil.a("Track_SportManager", "startUiHandlerThread() mUiHandler is null");
            this.gl = new d(Looper.getMainLooper(), this);
        }
    }

    private void fa() {
        LogUtil.a("Track_SportManager", "stopUiHandlerThread() enter");
        Handler handler = this.gl;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.gl = null;
        }
    }

    private void es() {
        LogUtil.a("Track_SportManager", "showTrackMap mMapViewListenerList.size = ", Integer.valueOf(this.dl.size()));
        if (this.dl.size() > 0) {
            for (IMapViewListener iMapViewListener : this.dl) {
                if (iMapViewListener != null) {
                    if (bd()) {
                        iMapViewListener.stopSport(true);
                        LogUtil.a("Track_SportManager", "showTrackMap hava SportData");
                    } else {
                        iMapViewListener.stopSport(false);
                        LogUtil.a("Track_SportManager", "showTrackMap no isValidSportData");
                    }
                }
            }
        }
    }

    private void e(ArrayList<StepRateData> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            int n = this.fa.n();
            this.dq.saveTotalSteps(n);
            this.dq.saveBestStepRate(this.fa.j());
            this.dq.saveAvgStepRate(this.fa.a(this.ep));
            k(n);
            return;
        }
        this.dq.saveTotalSteps(0);
    }

    private boolean a(long j2) {
        boolean z = false;
        if (j2 < this.dt || this.bi == 0) {
            return false;
        }
        boolean bd = bd();
        if (this.gh != 1) {
            z = bd;
        } else if (bd && this.k.c()) {
            z = true;
        }
        n(z);
        return true;
    }

    public void c(boolean z, kvq kvqVar) {
        if (z) {
            if (this.ga == null) {
                this.ga = new TrackAltitudeMgr(this.aa);
            }
            if (this.ga.b()) {
                return;
            }
            this.ga.c(kvqVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ef() {
        LogUtil.a("Track_SportManager", "saveDataToFile ");
        ExtendHandler extendHandler = this.au;
        if (extendHandler == null) {
            ReleaseLogUtil.c("Track_SportManager", "saveDataToFile mExtendHandler is null!");
        } else {
            this.cr = true;
            extendHandler.postTask(new Runnable() { // from class: gtx.4
                @Override // java.lang.Runnable
                public void run() {
                    if (gtx.this.dr()) {
                        if (gtx.this.ga != null) {
                            gtx.this.ga.h();
                        }
                        MotionPath c2 = gwk.c(gtx.this.aa, gtx.this.ew);
                        if (c2 != null) {
                            gtx.this.f122do = c2;
                        }
                    }
                    MotionPathSimplify cx = gtx.this.cx();
                    gwo.a(gtx.this.aa, cx, "simplemotion_temp.txt");
                    if (gtx.this.fk != null) {
                        gtx.this.c(cx, gtx.this.fa != null ? gtx.this.fa.a(true) : null, gtx.this.ga != null ? gtx.this.ga.e(true) : null, gtx.this.ec != null ? gtx.this.ec.d(true) : null);
                        if (gtx.this.fg.b() != null) {
                            guj.d().c(gtx.this.aa, gtx.this.fg.b().f(), gtx.this.fg.b().i(), gtx.this.fv);
                            return;
                        }
                        return;
                    }
                    LogUtil.b("Track_SportManager", "saveDataToFile mTempNewPointsBuffer is null!");
                    gwo.a(gtx.this.aa, cx, "simplemotion.txt");
                    gtx.this.cr = false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dr() {
        return this.fg.b() != null && this.fg.b().f() == 1 && !guj.d().c() && this.fv > this.fg.b().i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MotionPathSimplify motionPathSimplify, ArrayList<StepRateData> arrayList, ArrayList<knz> arrayList2, ArrayList<koe> arrayList3) {
        MotionPath d2;
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            ArrayList<HeartRateData> d3 = gtqVar.d(true);
            if (this.en != null) {
                d2 = gwg.d(this.fk, d3, arrayList, this.dz.a(true), this.dz.b(true), arrayList2, this.az.b(true), this.en.d(true));
            } else {
                d2 = gwg.d(this.fk, d3, arrayList, this.dz.a(true), this.dz.b(true), arrayList2, this.az.b(true), null);
            }
        } else {
            d2 = gwg.d(this.fk, null, arrayList, this.dz.a(true), this.dz.b(true), arrayList2, null, null);
        }
        if (arrayList3 != null) {
            d2.saveSpeedList(arrayList3);
        }
        gwm gwmVar = this.ek;
        if (gwmVar != null) {
            gwmVar.a();
            if (gwo.e(this.aa, this.ek.b(false))) {
                this.ek.b(true);
            }
        }
        boolean a2 = gwo.a(this.aa, d2, "motion_path2.txt");
        gwo.a(this.aa, motionPathSimplify, "simplemotion.txt");
        if (a2) {
            synchronized (f12936a) {
                this.fk.clear();
            }
            gtq gtqVar2 = this.az;
            if (gtqVar2 != null) {
                gtqVar2.b(true);
                this.az.d(true);
            }
            gwl gwlVar = this.en;
            if (gwlVar != null) {
                gwlVar.d(true);
            }
        }
        this.cr = false;
    }

    public MotionData a(boolean z) {
        LogUtil.a("Track_SportManager", "obtainMotionPathForFile");
        MotionPath a2 = gwk.a(this.aa);
        MotionPathSimplify d2 = gwk.d(this.aa);
        if (a2 == null || d2 == null) {
            ReleaseLogUtil.d("Track_SportManager", "obtainMotionPathForFile motionPath or motionPathSimplify empty");
            return null;
        }
        LogUtil.a("Track_SportManager", "motionPathSimplify.requestEndTime() = ", Long.valueOf(d2.requestEndTime()), " System.currentTimeMillis() == ", Long.valueOf(System.currentTimeMillis()));
        e(d2);
        d(d2);
        b(d2);
        this.fo = this.ge.m();
        if (!z && d2.requestTrackType() == 1) {
            f(false);
            LogUtil.c("Track_SportManager", "obtainMotionPathForFile is AUTO_TRACK, no need to recovery!");
            this.ex = 0L;
            this.as = 0L;
            this.fo = 0L;
            return null;
        }
        MotionData c2 = gvz.c(a2, d2);
        if (c2 == null || c2.getLbsDataMap() == null) {
            ReleaseLogUtil.d("Track_SportManager", "motionData or motionData.getLbsDataMap is null or empty");
            this.ex = 0L;
            this.as = 0L;
            this.fo = 0L;
            return null;
        }
        double[] dArr = c2.getLbsDataMap().get(0L);
        if (dArr != null && dArr.length >= 2) {
            c2 = gwe.d(c2, gwg.b());
        }
        this.fp = this.ge.h();
        this.bx = true;
        this.cf = true;
        if (this.dz == null) {
            this.dz = new gvp();
        }
        e(d2, a2);
        a(c2);
        return c2;
    }

    public enc bj() {
        return gwk.a(this.aa, "hotPathDetailInfo.txt");
    }

    public void b(enc encVar) {
        this.bd.a(encVar);
    }

    private void b(MotionPathSimplify motionPathSimplify) {
        gto gtoVar = this.bd;
        if (gtoVar != null) {
            gtoVar.c(motionPathSimplify);
        }
    }

    private void aUa_(Bundle bundle) {
        gto gtoVar = this.bd;
        if (gtoVar != null) {
            gtoVar.aTG_(bundle);
        }
    }

    private void aTP_(Location location) {
        gto gtoVar = this.bd;
        if (gtoVar != null) {
            gtoVar.aTF_(location);
        }
    }

    private void ff() {
        gto gtoVar;
        IMapDrawingUpdater iMapDrawingUpdater = this.dm;
        if (iMapDrawingUpdater == null || (gtoVar = this.bd) == null) {
            return;
        }
        iMapDrawingUpdater.updateCpMarker(gtoVar.b());
    }

    public boolean av() {
        gto gtoVar = this.bd;
        return gtoVar != null && gtoVar.c();
    }

    private void e(MotionPathSimplify motionPathSimplify) {
        this.ex = motionPathSimplify.requestStartTime();
        this.as = motionPathSimplify.requestEndTime();
        int requestSportType = motionPathSimplify.requestSportType();
        this.ew = requestSportType;
        if (requestSportType == 264 && gvv.d(this.aa)) {
            this.ff = 1;
        }
        int requestActiveCalories = motionPathSimplify.requestActiveCalories();
        if (requestActiveCalories > 0) {
            this.u.d((requestActiveCalories * 1.0f) / 1000.0f);
        }
    }

    private void d(MotionPathSimplify motionPathSimplify) {
        if (this.af == null && this.du == 4) {
            this.af = new gtj();
        }
        gtj gtjVar = this.af;
        if (gtjVar != null) {
            gtjVar.e(motionPathSimplify);
        }
        if (this.ad == null) {
            df();
        }
    }

    private int cu() {
        gtj gtjVar = this.af;
        if (gtjVar != null) {
            return gtjVar.a();
        }
        return 0;
    }

    private void e(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (j == null || motionPath == null || motionPathSimplify == null) {
            return;
        }
        Iterator<StepRateData> it = motionPath.requestStepRateList().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().acquireStepRate();
        }
        double requestTotalTime = ((motionPathSimplify.requestTotalTime() / 1000) % 60) * 1.0d;
        j.recoveryStep((int) requestTotalTime, requestTotalTime, this.eb - i2);
    }

    private void e(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        if (motionPath != null) {
            this.dz.c(motionPath.requestPaceMap(), motionPath.requestBritishPaceMap());
        }
        if (motionPathSimplify != null) {
            this.dz.d(motionPathSimplify.requestTotalTime());
            this.dz.b(motionPathSimplify.requestPartTimeMap(), motionPathSimplify.requestBritishPartTimeMap());
        }
    }

    public void c(boolean z, int i2) {
        if (this.au == null) {
            ReleaseLogUtil.c("Track_SportManager", "sendPauseSportMsgWhenLockScreen mExtendHandler is null!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 4;
        if (i2 == 2) {
            this.ce = z;
        }
        this.au.sendMessage(obtain);
    }

    public void bu() {
        if (this.au == null) {
            ReleaseLogUtil.c("Track_SportManager", "sendResumeSportMsgWhenLockScreen mExtendHandler is null!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 5;
        this.au.sendMessage(obtain);
    }

    public void bv() {
        if (this.au == null) {
            ReleaseLogUtil.c("Track_SportManager", "sendStopSportMsgWhenLockScreen mExtendHandler is null!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 6;
        this.au.sendMessage(obtain);
    }

    public ktg bt() {
        if (this.be == null) {
            this.be = ktg.c();
        }
        return this.be;
    }

    public void ca() {
        ktg bt = bt();
        bt.c("sportTrackLocation");
        bt.d("sportTrackLocation");
        bt.a("sportTrackLocation");
        this.be = null;
    }

    public void aUe_(Bundle bundle) {
        if (bundle.getParcelable("runCourseDetail") != null) {
            if (this.af == null) {
                this.af = new gtj();
            }
            this.af.aTC_(bundle);
        }
    }

    public void a(List<ffd> list) {
        this.fg.e(list);
        this.cc = true;
        b("Track_SportManagerTargetUpdate", this);
    }

    public List<ffd> l() {
        return this.fg.d();
    }

    public void h(boolean z) {
        LogUtil.a("Track_SportManager", "setScreenOnOrForegrand() : ", Boolean.valueOf(z));
        this.ci = z;
        if (j != null) {
            j.setStepInterval(this.ci);
        }
    }

    static class a extends BaseHandlerCallback<gtx> {
        public a(gtx gtxVar) {
            super(gtxVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: aUh_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(gtx gtxVar, Message message) {
            LogUtil.a("Track_SportManager", "InnerCallback()  handleMessage ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 4 || i == 5 || i == 6) {
                if (gtxVar.gl == null) {
                    return true;
                }
                gtxVar.gl.sendEmptyMessage(message.what);
                return true;
            }
            if (i == 102) {
                gtxVar.aTU_((Location) message.getData().getParcelable("Location"));
                return true;
            }
            if (i == 103) {
                if (gtxVar.at == null) {
                    return true;
                }
                gtxVar.at.aUM_((Location) message.getData().getParcelable("Location"));
                return true;
            }
            if (i == 401) {
                gtxVar.eq();
                return true;
            }
            if (i != 402) {
                return false;
            }
            gwy.e(gtxVar.em, gtxVar.fe.toString(), gtxVar.aa);
            gtxVar.fe = new StringBuffer();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(int i2) {
        if (this.gh == 1 || this.bl) {
            ReleaseLogUtil.e("Track_SportManager", "LockScreen and AutoTrack, to pause/resume sport ", Integer.valueOf(i2));
            if (i2 == 1) {
                bq();
            } else if (i2 == 2) {
                bk();
            } else if (i2 == 3) {
                n(bd());
                bm();
            } else {
                LogUtil.h("Track_SportManager", "Wrong Command");
            }
        }
        if (this.dl.size() != 0) {
            LogUtil.a("Track_SportManager", "updateSportStatusWhenLockScreen, to pause/resume sport ", Integer.valueOf(i2));
            for (IMapViewListener iMapViewListener : this.dl) {
                if (iMapViewListener != null) {
                    iMapViewListener.updateSportStatusWhenLockScreen(i2);
                }
            }
        }
    }

    class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (gtx.e == null) {
                    return;
                }
                if (gtx.e.ey == 1) {
                    gtx gtxVar = gtx.this;
                    gtxVar.as = gtxVar.cr();
                    gtx.this.dv();
                    if (gtx.this.ds()) {
                        gtx gtxVar2 = gtx.this;
                        gtxVar2.e(gtxVar2.fv);
                    }
                    gtx.e.increaseOrDecreaseSportDuration();
                    gtx.e.aUg_(gtx.this.aUd_());
                    gtx.this.fg.b(gtx.this.fv, gtx.this.ep, gtx.this.x(), gtx.this.b());
                    gtx gtxVar3 = gtx.this;
                    gtxVar3.e(gtxVar3.ep);
                    gtx.this.fg();
                    gtx.this.fi();
                }
                if (gtx.this.gh == 1 && gtx.this.k != null && gtx.this.k.c(gtx.this.fq, gtx.this.aj)) {
                    gtx.this.bx();
                }
                gtx.this.em();
                if (gtx.this.du() && gtx.e.ey == 1) {
                    gtx.this.p();
                }
            } catch (Exception unused) {
                LogUtil.b("Track_SportManager", "TimeRunner Exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void em() {
        if ((!CommonUtil.as() && !CommonUtil.aq()) || this.dz == null || this.bg == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String format = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss", Locale.ENGLISH).format(new Date());
        if (TextUtils.isEmpty(format)) {
            return;
        }
        this.fe.append(format);
        this.fe.append(",");
        this.fe.append(currentTimeMillis);
        this.fe.append(",");
        float g2 = this.dz.g();
        this.fe.append(g2 == 0.0f ? 0 : (int) (3600.0f / g2));
        this.fe.append(",");
        this.fe.append(g2);
        this.fe.append(",");
        this.fe.append(this.fv);
        this.fe.append(",");
        this.fe.append(this.bg.b());
        this.fe.append(",");
        this.fe.append(this.dz.d());
        this.fe.append(",");
        this.fe.append(this.bg.e());
        this.fe.append(System.lineSeparator());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fg() {
        int i2;
        if (j != null) {
            int currentSteps = j.getCurrentSteps();
            if (currentSteps < 0) {
                return;
            }
            int i3 = this.fc;
            if (i3 > 0 && (i2 = currentSteps - i3) > 0) {
                this.eb += i2;
            }
            gul gulVar = this.fa;
            if (gulVar != null) {
                gulVar.c(this.eb);
                this.fa.c();
            }
            if (currentSteps > this.fc || System.currentTimeMillis() - this.cy > 1000) {
                this.fc = currentSteps;
            }
            LogUtil.a("Track_SportManager", "updateRealTimeSteps mRealTimeTotalSteps:", Integer.valueOf(this.eb), " currentSteps:", Integer.valueOf(currentSteps), "  mStartTotalSteps ", Integer.valueOf(this.fc));
        }
        ReleaseLogUtil.e("Track_SportManager", "updateRealTimeSteps mRealTimeTotalSteps");
    }

    public boolean ay() {
        return this.ey == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean du() {
        gtq gtqVar;
        boolean z = this.ey == 1;
        if (z && dn()) {
            return false;
        }
        gsx gsxVar = this.gc;
        if (gsxVar != null) {
            gsxVar.b();
        }
        if (z) {
            cd();
        }
        if (z && (gtqVar = this.az) != null) {
            gtqVar.f();
        }
        if (this.ci) {
            return true;
        }
        if (!ScreenUtil.a()) {
            return false;
        }
        this.ci = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(float f2) {
        this.bz = true;
        this.cv = f2;
        int a2 = ktr.a(this.av);
        if (this.dl.size() != 0) {
            for (IMapViewListener iMapViewListener : this.dl) {
                if (iMapViewListener != null) {
                    iMapViewListener.updateGpsStatus(a2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ds() {
        if (this.bz || this.ew == 264) {
            return false;
        }
        if (this.df == null) {
            int a2 = ktr.a(4);
            if (this.av <= a2 && this.ep > TimeUnit.SECONDS.toMillis(10L)) {
                LogUtil.a("Track_SportManager", "mGpsState is ", Integer.valueOf(this.av), " ,mSportDuration is ", LogAnonymous.d(this.ep), ", isGPSPointLost is true");
            } else {
                if (this.av <= a2 || this.ep <= TimeUnit.SECONDS.toMillis(20L)) {
                    return false;
                }
                LogUtil.a("Track_SportManager", "mGpsState is ", Integer.valueOf(this.av), " ,mSportDuration is ", LogAnonymous.d(this.ep), ", isGPSPointLost is true");
            }
        } else {
            if (System.currentTimeMillis() - this.cx <= TimeUnit.SECONDS.toMillis(20L)) {
                return false;
            }
            ReleaseLogUtil.e("R_Track_SportManager", "isStartEstimateStepState the GPS signal is lost");
        }
        return true;
    }

    private void p(boolean z) {
        this.bz = z;
    }

    public String aj() {
        return String.valueOf(this.ex);
    }

    public boolean am() {
        if (this.ge == null) {
            ReleaseLogUtil.c("Track_SportManager", "getTrackCrashState mTrackSharedPreferenceUtil is null!");
            return false;
        }
        boolean c2 = gww.c();
        this.bo = c2;
        LogUtil.a("Track_SportManager", "getTrackCrashState crash =", Boolean.valueOf(c2));
        return this.bo;
    }

    public void f(boolean z) {
        if (this.ge == null) {
            LogUtil.b("Track_SportManager", "setTrackCrashState mTrackSharedPreferenceUtil is null!");
            return;
        }
        ReleaseLogUtil.e("Track_SportManager", "setTrackCrashState crash =", Boolean.valueOf(z));
        gww.a(z);
        if (!z) {
            this.ge.f("");
        }
        this.bo = z;
        if (z) {
            return;
        }
        ch();
    }

    private String ct() {
        gww gwwVar = this.ge;
        if (gwwVar == null) {
            LogUtil.b("Track_SportManager", "getLogOffset mTrackSharedPreferenceUtil is null!");
            return "";
        }
        return gwwVar.w();
    }

    private void g(String str) {
        gww gwwVar = this.ge;
        if (gwwVar == null) {
            LogUtil.b("Track_SportManager", "setLogOffset mTrackSharedPreferenceUtil is null!");
        } else {
            gwwVar.f(str);
        }
    }

    public boolean aw() {
        return this.bz;
    }

    public void bp() {
        if (this.k != null) {
            float f2 = this.fv;
            this.fx = f2;
            LogUtil.a("Track_SportManager", "mTotalSnapshotSportDistance is ", LogAnonymous.b((int) f2));
            guz guzVar = this.m;
            if (guzVar == null || this.fv < guzVar.c()) {
                return;
            }
            this.k.b(true);
            this.as = cr();
            ef();
        }
    }

    public void s() {
        synchronized (f12936a) {
            this.fk.clear();
        }
        gwl gwlVar = this.en;
        if (gwlVar != null) {
            gwlVar.d(true);
        }
        gul gulVar = this.fa;
        if (gulVar != null) {
            gulVar.a(true);
        }
        gtq gtqVar = this.az;
        if (gtqVar != null) {
            gtqVar.d(true);
            this.az.b(true);
        }
        gvq gvqVar = this.ec;
        if (gvqVar != null) {
            gvqVar.d(true);
        }
        this.dz.a(true);
        this.dz.b(true);
        this.dn.clear();
    }

    private boolean dt() {
        guz guzVar = this.m;
        if (guzVar == null) {
            return false;
        }
        if (this.fv >= guzVar.c()) {
            return true;
        }
        LogUtil.a("Track_SportManager", "isNeedSaveAutoTrack() ,", LogAnonymous.b((int) this.fx), " less than ", Float.valueOf(this.m.c()), ", do not need to save this track");
        return false;
    }

    public void e(StepRateData stepRateData) {
        gul gulVar;
        if (stepRateData == null || (gulVar = this.fa) == null) {
            return;
        }
        gulVar.e(stepRateData);
        LogUtil.a("Track_SportManager", "recoveryAutoTrackSnapshotInfo() save current step success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long cr() {
        if (this.k == null || this.dq == null) {
            return System.currentTimeMillis();
        }
        if (this.k.a()) {
            return this.dq.requestEndTime();
        }
        return System.currentTimeMillis();
    }

    private void di() {
        TrackDevelopConfig trackDevelopConfig = this.fy;
        if (trackDevelopConfig == null || trackDevelopConfig.c()) {
            String ct = ct();
            this.ds = ct;
            if (ct == null || "".equals(ct)) {
                int nextInt = new SecureRandom().nextInt(70);
                this.ee = nextInt;
                String valueOf = String.valueOf(nextInt);
                this.ds = valueOf;
                g(valueOf);
            } else {
                try {
                    try {
                        this.ee = Integer.parseInt(this.ds);
                    } catch (NumberFormatException unused) {
                        LogUtil.b("Track_SportManager", "initLogOffset exception");
                    }
                } catch (NumberFormatException unused2) {
                    this.ee = (int) Double.parseDouble(this.ds);
                }
            }
            this.dg = this.ee + 20;
            this.di = 17;
            return;
        }
        this.dg = 0;
        this.di = 0;
    }

    public boolean be() {
        if (!this.cs) {
            return false;
        }
        int i2 = this.ew;
        return i2 == 258 || i2 == 257 || i2 == 264 || i2 == 282 || i2 == 260;
    }

    public int a(ISportDataCallback iSportDataCallback, long j2) {
        if (iSportDataCallback != null && d(iSportDataCallback) == null) {
            this.eq.add(new gxx(iSportDataCallback, j2));
            LogUtil.a("Track_SportManager", "registerSportDataCallback success ", iSportDataCallback, " ", Integer.valueOf(this.eq.size()));
            return 0;
        }
        LogUtil.h("Track_SportManager", "registerSportDataCallback failed ", Integer.valueOf(this.eq.size()));
        return 5;
    }

    public int e(ISportDataCallback iSportDataCallback) {
        if (iSportDataCallback == null) {
            LogUtil.h("Track_SportManager", "unregisterSportDataCallback failed, cb is null");
            return 5;
        }
        gxx d2 = d(iSportDataCallback);
        if (d2 != null) {
            this.eq.remove(d2);
            LogUtil.a("Track_SportManager", "unregisterSportDataCallback success ", iSportDataCallback);
            return 0;
        }
        ReleaseLogUtil.d("Track_SportManager", "unregisterSportDataCallback failed, the proxy is not exist");
        return 5;
    }

    public gxx d(ISportDataCallback iSportDataCallback) {
        List<gxx> list = this.eq;
        if (list == null || list.size() == 0) {
            return null;
        }
        for (gxx gxxVar : this.eq) {
            if (gxxVar.e() == iSportDataCallback) {
                return gxxVar;
            }
        }
        return null;
    }

    public Bundle aUd_() {
        Bundle bundle;
        kvq kvqVar;
        synchronized (b) {
            if (this.ej != null && (kvqVar = this.eh) != null) {
                this.ej = kvqVar;
                d(kvqVar);
            }
            bundle = new Bundle();
            bundle.putInt(GeocodeSearch.GPS, ktr.a(this.ey));
            gtq gtqVar = this.az;
            if (gtqVar != null) {
                bundle.putLong("heartRateTime", gtqVar.c());
            }
            kvq kvqVar2 = this.ej;
            if (kvqVar2 != null) {
                bundle.putInt(MedalConstants.EVENT_STEPS, kvqVar2.w());
                bundle.putInt("distance", this.ej.l());
                bundle.putInt("duration", this.ej.k());
                bundle.putInt("calorie", this.ej.c());
                bundle.putInt(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, this.ej.a());
                bundle.putInt("pace", this.ej.h());
                bundle.putFloat("speed", this.ej.g());
                if (!g) {
                    ReleaseLogUtil.d("Track_SportManager", " getSportDataBundle SportStatus is ", Integer.valueOf(this.de));
                    bundle.putInt("sportState", this.de);
                } else {
                    bundle.putInt("sportState", this.ej.r());
                }
                bundle.putInt("altitude", this.ej.e());
            }
            aUc_(bundle);
            if (dx()) {
                aTS_(bundle);
            }
            bundle.putInt("origintarget", this.du);
            bundle.putInt(BleConstants.SPORT_TYPE, this.ew);
            bundle.putInt("trackType", this.gh);
            aUb_(bundle);
            gwl gwlVar = this.en;
            if (gwlVar != null) {
                gwlVar.aVb_(bundle);
            }
        }
        return bundle;
    }

    private void aUc_(Bundle bundle) {
        kvq kvqVar;
        if (!f || (kvqVar = this.ej) == null) {
            return;
        }
        bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, kvqVar.j());
        bundle.putInt("stepRate", this.ej.s());
        bundle.putInt("totalCreep", this.ej.q());
        bundle.putInt(BleConstants.TOTAL_DESCENT, this.ej.t());
        bundle.putInt("aerobicExercise", this.ej.b());
        bundle.putInt("anaerobicExercise", this.ej.d());
        bundle.putInt("performanceIndicator", this.ej.o());
        bundle.putInt("linkType", this.ej.f());
        bundle.putInt("heartZone", this.ej.i());
        bundle.putLong("sportStartTime", this.ej.p());
        if (gvv.a(this.ew) && this.fa != null && this.bs) {
            int g2 = this.fa.g();
            if (!this.cp) {
                g2 = Math.round(g2 / 12.0f);
            }
            bundle.putInt("stepRate", g2);
        }
    }

    private void aTS_(Bundle bundle) {
        ffd b2 = this.fg.b();
        int f2 = b2.f();
        float d2 = b2.d() + b2.i();
        bundle.putInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, f2);
        bundle.putFloat(WorkoutRecord.Extend.COURSE_TARGET_VALUE, d2);
        if (this.du == 4) {
            bundle.putInt("runningCourseVersion", 1);
            bundle.putInt("runningActionIndex", b2.c());
            bundle.putInt("runningCourseNumber", b2.g());
            bundle.putString("runningActionId", b2.b());
            bundle.putInt("goalType", l(f2));
            bundle.putInt("goalGapValue", d(d2, f2));
        }
    }

    private int d(float f2, int i2) {
        if (i2 == 0) {
            return Math.round((f2 - this.ep) / 1000.0f);
        }
        if (i2 == 1) {
            return Math.round(f2 - this.fv);
        }
        LogUtil.c("Track_SportManager", "getGoalGapValue other targetType");
        return 0;
    }

    private void aUb_(Bundle bundle) {
        if (dx()) {
            ffd b2 = this.fg.b();
            float d2 = b2.d() + b2.i();
            int f2 = b2.f();
            if (f2 == -1) {
                bundle.putInt("distanceTarget", (int) d2);
                return;
            }
            if (f2 == 0) {
                bundle.putInt("timeTarget", ((int) d2) / 1000);
                return;
            }
            if (f2 == 1) {
                bundle.putInt("distanceTarget", (int) d2);
                return;
            }
            if (f2 == 2) {
                bundle.putInt("calorieTarget", (int) d2);
            } else if (f2 == 3 || f2 == 4) {
                bundle.putInt("distanceTarget", (int) d2);
            } else {
                bundle.putInt("distanceTarget", (int) d2);
            }
        }
    }

    public void aUg_(Bundle bundle) {
        for (gxx gxxVar : this.eq) {
            if (gxxVar != null) {
                gxxVar.aVL_(bundle);
            }
        }
    }

    private void aTX_(Bundle bundle) {
        for (gxx gxxVar : this.eq) {
            if (gxxVar != null) {
                gxxVar.aVK_(bundle);
            }
        }
    }

    private void fn() {
        LogUtil.a("Track_SportManager", "updateSportDataSummaryToListener enter");
        if (this.dq == null) {
            LogUtil.h("Track_SportManager", " updateSportDataSummaryToListener mMotionPathSimplify is null");
        }
        for (gxx gxxVar : this.eq) {
            if (gxxVar != null) {
                gxxVar.c(this.dq);
            }
        }
    }

    public void bs() {
        boolean dt = dt();
        if (this.bl) {
            dt = bd();
        }
        o(dt);
    }

    private void b(String str) {
        ktg bt = bt();
        boolean z = this.cf;
        if (z) {
            bt.b(z);
        }
        bt.a(new IGpsStatusCallback() { // from class: gtx.1
            @Override // com.huawei.hwlocationmgr.model.IGpsStatusCallback
            public void updateGpsStatus(int i2) {
                gtx.this.n(i2);
            }
        }, str);
    }

    private void c(String str) {
        bt().e(new ILoactionCallback() { // from class: gtx.9
            @Override // com.huawei.hwlocationmgr.model.ILoactionCallback
            public void dispatchLocationChanged(Location location) {
                gtx.this.aTR_(location);
            }
        }, str);
        this.au.sendEmptyMessage(401, 500L);
    }

    private void i(String str) {
        ktg bt = bt();
        if (this.bg.h() && bt != null) {
            bt.bQS_(GeocodeSearch.GPS, "FitEnableMode:-1", null);
            this.h = true;
        }
        bt.a(str);
        bt.d(str);
        bt.c(str);
        bt.d("sportTrackTempLocation");
    }

    public void g(boolean z) {
        if (this.k == null) {
            LogUtil.a("Track_SportManager", "init mAutoTrackManager, isClassOne is ", Boolean.valueOf(z));
            this.k = new gtk(this.aa, z);
        }
    }

    public void cb() {
        gtk gtkVar = this.k;
        if (gtkVar != null) {
            gtkVar.e();
            this.k = null;
        }
    }

    public int a(ISportDistanceTimeCallback iSportDistanceTimeCallback) {
        if (iSportDistanceTimeCallback == null) {
            return 5;
        }
        this.dw = iSportDistanceTimeCallback;
        return 0;
    }

    private void b(ISportDistanceTimeCallback iSportDistanceTimeCallback) {
        if (iSportDistanceTimeCallback != null) {
            this.dw = null;
        }
    }

    private long ce() {
        long currentTimeMillis = (System.currentTimeMillis() - this.ex) - this.ep;
        long v = (long) (this.ak * v());
        return (currentTimeMillis >= 0 && currentTimeMillis < v) ? currentTimeMillis : v;
    }

    public float x() {
        return this.u.c();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackPointDataUpdater
    public float getActiveCalorie() {
        return this.u.b();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackPointDataUpdater
    public float getDistance() {
        return this.fv;
    }

    public void d(String str) {
        if (str == null) {
            ReleaseLogUtil.d("Track_SportManager", "distance is null");
            return;
        }
        float j2 = CommonUtils.j(str) * 1000.0f;
        this.bq = true;
        if (UnitUtil.h()) {
            j2 = (float) UnitUtil.d(j2, 3);
        }
        gvp gvpVar = this.dz;
        if (gvpVar != null) {
            gvpVar.c((int) this.fv, 0, false);
            Map<Integer, Float> d2 = gwa.d(this.dz.f(), this.fv, j2);
            Map<Integer, Float> d3 = gwa.d(this.dz.c(), (float) UnitUtil.e(this.fv, 3), (float) UnitUtil.e(j2, 3));
            this.dz.e(d2);
            this.dz.b(d3);
            this.dz.d(gwa.a(d2, false));
            this.dz.a(gwa.a(d3, true));
        }
        float f2 = j2 / this.fv;
        LogUtil.a("Track_SportManager", "changeDistance:", Float.valueOf(f2), ", originDistance is ", LogAnonymous.b((int) j2), ", mTotalSportDistance is ", LogAnonymous.b((int) this.fv));
        this.fv = j2;
        this.u.e(f2);
        b(f2);
    }

    private void b(float f2) {
        gum gumVar = this.gb;
        if (gumVar != null) {
            gumVar.a(f2);
        } else {
            LogUtil.h("Track_SportManager", "changeTrackPointDataByRatio mTrackPointDataUtils is null");
        }
    }

    private void ck() {
        gwd gwdVar = this.at;
        if (gwdVar != null) {
            gwdVar.c(this.aa);
        }
        if (this.fz != null) {
            HashMap hashMap = new HashMap(1);
            hashMap.putAll(this.fz.c());
            LogUtil.a("Track_SportManager", "doEndBi ", hashMap);
            ixx.d().d(this.aa, AnalyticsValue.BI_TRACK_SPORT_ALGO_FILTER.value(), hashMap, 0);
        }
        if (!Utils.o()) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("userPauseDuration", Long.valueOf(this.dv));
            hashMap2.put("userPauseTimes", Integer.valueOf(this.dy));
            hashMap2.put("userStaticDuration", Long.valueOf(this.fj));
            hashMap2.put("userStaticTimes", Integer.valueOf(this.fl));
            LogUtil.a("Track_SportManager", "doEndBi ", hashMap2);
            ixx.d().d(this.aa, AnalyticsValue.BI_TRACK_SPORT_USER_BEHAVIOR.value(), hashMap2, 0);
        }
        int e2 = gvv.e(this.ew);
        if (e2 != 0) {
            OpAnalyticsUtil.getInstance().setEventWithActionType(e2, OperationKey.HEALTH_APP_RUN_END_2050006.value());
        }
        cq();
    }

    private void cq() {
        gto gtoVar = this.bd;
        if (gtoVar == null) {
            return;
        }
        String e2 = gtoVar.e();
        if (TextUtils.isEmpty(e2)) {
            return;
        }
        iyb iybVar = new iyb();
        HashMap hashMap = new HashMap(5);
        hashMap.put("event", 1);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, c6.f5201a);
        hashMap.put("action", 0);
        hashMap.put("moduleName", "motion_control");
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.ew));
        iybVar.d(hashMap);
        HashMap hashMap2 = new HashMap(2);
        hashMap2.put("map_area", e2);
        hashMap2.put("distance", Float.valueOf(getDistance()));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(hashMap2);
        iybVar.d(arrayList);
        ixx.d().a(BaseApplication.getContext(), AnalyticsValue.BI_TRACK_SPORT_START_SPORT_KEY.value(), iybVar, 0);
    }

    public int o() {
        gul gulVar = this.fa;
        if (gulVar == null) {
            return 0;
        }
        return gulVar.n() + this.fa.b();
    }

    public boolean e() {
        return this.cg;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IStepRateUpdater
    public void updateStepRate(StepRateData stepRateData) {
        if (stepRateData == null) {
            aTY_("action_send_steprate_to_hwmusic", null, 0);
        } else {
            aTY_("action_send_steprate_to_hwmusic", null, stepRateData.acquireStepRate());
        }
    }

    private void aTY_(String str, Bundle bundle, int i2) {
        if (!gwg.i(this.aa)) {
            LogUtil.a("Track_SportManager", "not supportMusic");
            return;
        }
        Intent intent = new Intent(str);
        if (bundle != null) {
            intent.putExtra("extroInfo", bundle);
        }
        intent.putExtra("stepRate", i2);
        gut.aUo_(this.aa, intent);
    }

    private void fm() {
        LogUtil.a("Track_SportManager", "updateSportDataToMusic enter");
        if (this.dq == null) {
            LogUtil.h("Track_SportManager", " updateSportDataToMusic mMotionPathSimplify is null");
            aTY_("action_stop_play_sport_music", null, 0);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("duration", this.dq.requestTotalTime());
        bundle.putInt("stepRate", this.dq.requestAvgStepRate());
        bundle.putInt(MedalConstants.EVENT_STEPS, this.dq.requestTotalSteps());
        aTY_("action_stop_play_sport_music", bundle, 0);
    }

    public void b(int i2) {
        gup gupVar = this.gg;
        if (gupVar != null) {
            gupVar.e(i2);
        } else {
            LogUtil.a("Track_SportManager", "notifyUserOperateSportState is null");
        }
    }

    private void et() {
        if (az()) {
            if (this.ga == null) {
                this.ga = new TrackAltitudeMgr(this.aa);
            }
            this.ga.g();
            c(this.ga);
        }
    }

    public boolean az() {
        int i2 = this.ew;
        return i2 == 259 || i2 == 258 || i2 == 257 || i2 == 282 || i2 == 260;
    }

    private void ex() {
        TrackAltitudeMgr trackAltitudeMgr = this.ga;
        if (trackAltitudeMgr != null) {
            trackAltitudeMgr.i();
        }
        b(this.ga);
    }

    public void a(gxw gxwVar) {
        dwq dwqVar = this.fz;
        if (dwqVar != null) {
            dwqVar.e(gxwVar);
        }
    }

    public void cc() {
        dwq dwqVar = this.fz;
        if (dwqVar != null) {
            dwqVar.g();
        }
    }

    public void e(boolean z) {
        if (cm()) {
            us usVar = new us();
            usVar.c(this.dz.i());
            usVar.c(this.ew);
            if (z) {
                usVar.b(this.dz.h());
            } else {
                usVar.b(this.dz.f());
            }
            usVar.c(v());
            usVar.d((int) this.fv);
            usVar.b(getSportDurationBySecond());
            gul gulVar = this.fa;
            int n = gulVar != null ? gulVar.n() : 0;
            if (n == 0) {
                n = this.eb;
            }
            usVar.e(n);
            usVar.d(ur.d(this.aa));
            usVar.a(this.gh);
            this.o = ur.a(usVar);
            this.cn = ur.e(usVar);
            if (!z) {
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_ABNORMAL_TRAJECTORY_85070016.value(), this.o);
            }
            LogUtil.a("Track_SportManager", "mAbnormalTrack:", Integer.valueOf(this.o), ", abnormalData", usVar.toString(), " mIsStepAbnormal ", Boolean.valueOf(this.cn));
        }
    }

    private void d(kvq kvqVar) {
        kvqVar.m(Math.round((this.ep * 1.0f) / 1000.0f));
        kvqVar.i((int) this.fv);
        LogUtil.c("Track_SportManager", "old distance:" + this.fv);
        kvqVar.e(Math.round(x()) * 1000);
        kvqVar.a(Math.round(getActiveCalorie()) * 1000);
        kvqVar.j(b());
        float y = y();
        if (y <= 0.0f || y >= 200.0f) {
            kvqVar.b(0.0f);
        } else if (this.fv < 10.0f) {
            kvqVar.b(0.0f);
        } else {
            kvqVar.b((y() * 1000.0f) / 3600.0f);
        }
        if (bb() && y > 1.0E-4f) {
            int i2 = (int) (3600.0f / y);
            kvqVar.g(i2 < 6000 ? i2 : 0);
        } else {
            kvqVar.g(0);
        }
        kvqVar.e(this.ex);
        kvqVar.t(this.eb);
        LogUtil.a("Track_SportManager", "saveAndSetReportData mRealTimeTotalSteps:", Integer.valueOf(this.eb));
        kvqVar.l(this.ey);
        if (this.ga == null || Math.abs(r0.e()) <= 1.0E-6d) {
            return;
        }
        kvqVar.d(((int) this.ga.e()) * 10);
        kvqVar.n(((int) this.ga.d()) * 10);
        kvqVar.o(((int) this.ga.c()) * 10);
    }

    private void ev() {
        LogUtil.a("Track_SportManager", "startTreadmillStep");
        if (j == null || this.ff != 1) {
            return;
        }
        j.startTreadmillStep();
        eu();
    }

    private void ez() {
        LogUtil.a("Track_SportManager", "stopTreadmillStep");
        if (j != null) {
            if (this.ff == 1 || this.dr == 1) {
                j.unregisterFreeIndoorRunningStyle();
                j.stopTreadmillStep();
                LogUtil.a("Track_SportManager", "stopTreadmillStep sucess");
            }
        }
    }

    public gvp ai() {
        return this.dz;
    }

    private void eu() {
        if (j == null) {
            ReleaseLogUtil.c("Track_SportManager", "startTreadmillStyle mPluginTrackAdapter is null!");
        } else {
            LogUtil.a("Track_SportManager", "startTreadmillStyle");
            j.registerFreeIndoorRunningStyle(new ITreadmillStyleCallback() { // from class: gtx.7
                @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITreadmillStyleCallback
                public void onTreadmillStyleChange(int i2, long j2) {
                    LogUtil.a("Track_SportManager", "onTreadmillStyleChange style is ", Integer.valueOf(i2), " , time is ", Long.valueOf(j2));
                    gtx.this.gf = i2;
                }
            });
        }
    }

    private dwl m(hiy hiyVar) {
        return new dwl(hiyVar.h(), hiyVar.f(), hiyVar.d(), hiyVar.a(), hiyVar.c(), hiyVar.e(), hiyVar.i(), hiyVar.g());
    }

    public boolean ax() {
        return this.bb;
    }

    public void e(String str, SportComponent sportComponent) {
        this.el.e(str, sportComponent);
    }

    public void b(String str, SportVoiceEnableListener sportVoiceEnableListener) {
        if (StringUtils.i(str) && sportVoiceEnableListener != null) {
            this.gj.put(str, sportVoiceEnableListener);
            ReleaseLogUtil.e("Track_SportManager", "addVoiceEnableListener tag:", str, " listener:", sportVoiceEnableListener, " component size:", Integer.valueOf(this.gj.size()));
        } else {
            ReleaseLogUtil.d("Track_SportManager", "addVoiceEnableListener failed with error params. tag:", str, " listener:", sportVoiceEnableListener);
        }
    }

    public void d(boolean z) {
        LogUtil.a("Track_SportManager", "dispachVoiceEnableChange() begin. enabled:", Boolean.valueOf(z));
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.gj);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportVoiceEnableListener) it.next()).onChange(z);
        }
        LogUtil.a("Track_SportManager", "dispachVoiceEnableChange() finished. listeners:", linkedHashMap.keySet());
    }
}

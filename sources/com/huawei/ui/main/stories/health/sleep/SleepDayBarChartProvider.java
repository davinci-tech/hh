package com.huawei.ui.main.stories.health.sleep;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.chart.CoreSleepDayDetailView;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil;
import com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider;
import defpackage.cun;
import defpackage.cwi;
import defpackage.ecu;
import defpackage.fda;
import defpackage.fdp;
import defpackage.gge;
import defpackage.jdl;
import defpackage.jdn;
import defpackage.jec;
import defpackage.koq;
import defpackage.kor;
import defpackage.mht;
import defpackage.nhu;
import defpackage.nrq;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.pob;
import defpackage.pom;
import defpackage.pqg;
import defpackage.pvz;
import defpackage.pwb;
import defpackage.pwd;
import defpackage.pxa;
import defpackage.pxz;
import defpackage.qmu;
import defpackage.qnj;
import defpackage.qnl;
import defpackage.scn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class SleepDayBarChartProvider extends MajorProvider<fdp> {
    public static pwd c;
    private boolean aa;
    private Boolean ac;
    private boolean ae;
    private boolean ah;
    private Observer aj;
    private SectionBean ap;
    private String at;
    private String au;
    private long av;
    private int aw;
    private int ax;
    private long ay;
    private f bb;
    private boolean j;
    private d l;
    private HealthCalendar m;
    private WeakReference<Context> p;
    private CustomViewDialog q;
    private Date r;
    private h s;
    private String u;
    private Handler w;
    private int x;
    private int y;
    public static List<String> e = new CopyOnWriteArrayList();

    /* renamed from: a, reason: collision with root package name */
    public static List<String> f10225a = new CopyOnWriteArrayList();
    public static List<String> b = new CopyOnWriteArrayList();
    private static final Object f = new Object();
    private static String g = "0";
    public Map<String, Object> i = new ConcurrentHashMap();
    public fdp d = new fdp(SleepViewConstants.ViewTypeEnum.DAY);
    private List<String> ak = new ArrayList(16);
    private final Handler ba = new a(this);
    private long k = 0;
    private boolean ai = false;
    private boolean z = true;
    private CopyOnWriteArrayList<pvz> ao = new CopyOnWriteArrayList<>();
    private float al = 0.0f;
    private float an = 0.0f;
    private boolean h = true;
    private boolean af = false;
    private List<ecu> o = new CopyOnWriteArrayList();
    private List<pwb> v = new ArrayList();
    private HandlerThread az = null;
    private BroadcastReceiver aq = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("SleepDayBarChartProvider", "receiver mRefreshReceiver");
            SleepDayBarChartProvider.this.ba.sendEmptyMessage(25);
        }
    };
    private BroadcastReceiver am = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("SleepDayBarChartProvider", "mProcessRateReceiver.intent == null.");
                return;
            }
            int intExtra = intent.getIntExtra("core_sleep_sync_status", 0);
            if (jec.n(SleepDayBarChartProvider.this.r) == jec.n(jec.e())) {
                Message obtain = Message.obtain();
                LogUtil.a("SleepDayBarChartProvider", "mProcessRateReceiver results = ", Integer.valueOf(intExtra));
                obtain.what = 5000;
                obtain.obj = Integer.valueOf(intExtra);
                SleepDayBarChartProvider.this.ba.sendMessageDelayed(obtain, intExtra == 1010 ? 0L : 1000L);
            }
        }
    };
    private boolean ad = false;
    private boolean ag = false;
    private boolean ab = false;
    private Set<String> ar = Collections.synchronizedSet(new HashSet());
    private int n = 0;
    private IBaseResponseCallback as = new c(this);
    private BroadcastReceiver t = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("SleepDayBarChartProvider", "receive commonSleep success.");
            int e2 = nrq.a().e();
            LogUtil.a("SleepDayBarChartProvider", "currentRate = ", Integer.valueOf(e2));
            if (e2 > 0 && e2 < 100) {
                LogUtil.a("SleepDayBarChartProvider", "isSyncing coreSleep now, don't refresh page.");
                return;
            }
            if (SleepDayBarChartProvider.this.e() instanceof KnitSleepDetailActivity) {
                boolean c2 = ((KnitSleepDetailActivity) SleepDayBarChartProvider.this.e()).c();
                LogUtil.a("SleepDayBarChartProvider", "goto refresh common sleep data. isHasCoreSleepData: ", Boolean.valueOf(c2));
                boolean isSyncing = nhu.c().isSyncing();
                LogUtil.a("SleepDayBarChartProvider", "isSyncing: ", Boolean.valueOf(isSyncing));
                if (c2 || isSyncing) {
                    return;
                }
                nrq.a().d(100);
                SleepDayBarChartProvider.this.b(false);
            }
        }
    };

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        e(context, (HashMap<String, Object>) hashMap, (fdp) obj);
    }

    public int d() {
        return this.y;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.a("SleepDayBarChartProvider", "loadData");
        this.p = new WeakReference<>(context);
        this.ap = sectionBean;
        this.s = new h(this);
        this.l = new d(this);
        c = new pwd();
        this.af = false;
        this.ai = false;
        if (this.z) {
            y();
            g();
            t();
            as();
            ab();
            ag();
            n();
            qnl.dGr_(e(), this.ba);
            av();
            ObserverManagerUtil.d(new Observer() { // from class: qna
                @Override // com.huawei.haf.design.pattern.Observer
                public final void notify(String str, Object[] objArr) {
                    SleepDayBarChartProvider.this.e(str, objArr);
                }
            }, "FINISH_SLEEP_TAG");
            Observer observer = new Observer() { // from class: qmy
                @Override // com.huawei.haf.design.pattern.Observer
                public final void notify(String str, Object[] objArr) {
                    SleepDayBarChartProvider.this.a(str, objArr);
                }
            };
            this.aj = observer;
            ObserverManagerUtil.d(observer, "SLEEP_MESSAGE_JUMP_TAG");
        }
    }

    public /* synthetic */ void a(String str, Object[] objArr) {
        LogUtil.a("SleepDayBarChartProvider", "notify mMessageJumpObserver!");
        if (!koq.e(objArr, 0)) {
            LogUtil.a("SleepDayBarChartProvider", "null args!");
            return;
        }
        Date e2 = jec.e();
        Object obj = objArr[0];
        if (obj instanceof String) {
            try {
                e2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) obj);
            } catch (ParseException e3) {
                LogUtil.a("SleepDayBarChartProvider", "parse exception = ", e3.getMessage());
            }
            a(e2);
            return;
        }
        if (obj instanceof Long) {
            long d2 = TimeUtil.d(((Long) obj).longValue());
            if (koq.e(objArr, 1)) {
                Object obj2 = objArr[1];
                if ((obj2 instanceof Boolean) && ((Boolean) obj2).booleanValue()) {
                    LogUtil.a("SleepDayBarChartProvider", "notify mMessageJumpObserver, isFromDeleteData");
                    Date date = new Date();
                    date.setTime(d2);
                    a(date);
                }
            }
        }
    }

    public Context e() {
        WeakReference<Context> weakReference = this.p;
        if (weakReference == null) {
            return BaseApplication.getContext();
        }
        Context context = weakReference.get();
        return context == null ? BaseApplication.getContext() : context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(String str, Object[] objArr) {
        LogUtil.a("SleepDayBarChartProvider", "notify received! label: ", str);
        Date e2 = jec.e();
        if (!koq.e(objArr, 0)) {
            LogUtil.a("SleepDayBarChartProvider", "null args!");
            return;
        }
        Object obj = objArr[0];
        if (obj instanceof String) {
            try {
                e2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) obj);
            } catch (ParseException e3) {
                LogUtil.a("SleepDayBarChartProvider", "parse exception = ", e3.getMessage());
            }
            a(e2);
            return;
        }
        if (obj instanceof Long) {
            a(new Date(TimeUtil.d(((Long) obj).longValue())));
            Context context = this.p.get();
            if (context instanceof KnitSleepDetailActivity) {
                final KnitSleepDetailActivity knitSleepDetailActivity = (KnitSleepDetailActivity) context;
                HandlerExecutor.e(new Runnable() { // from class: qnd
                    @Override // java.lang.Runnable
                    public final void run() {
                        KnitSleepDetailActivity.this.getViewPager().setCurrentItem(0);
                    }
                });
                return;
            }
            return;
        }
        LogUtil.a("SleepDayBarChartProvider", "wrong args!");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.k = System.currentTimeMillis();
        ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "LoadDefaultData");
        LogUtil.a("SleepDayBarChartProvider", "LoadDefaultData:", this);
        m();
        sectionBean.e(this.d);
    }

    private void m() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qnf
            @Override // java.lang.Runnable
            public final void run() {
                bzs.e().initH5Pro();
            }
        });
    }

    public void e(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        qnl.c(hashMap, this);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("SleepDayBarChartProvider", "onDestroy");
        HashMap hashMap = new HashMap(16);
        hashMap.put("startTime", Long.valueOf(System.currentTimeMillis()));
        hashMap.put("time", Long.valueOf(SystemClock.elapsedRealtime() - this.k));
        d(hashMap);
        gge.e(AnalyticsValue.HEALTH_SLEEP_CORE_SLEEP_TIME_21300031.value(), hashMap);
        ObserverManagerUtil.e("SLEEP_SYNC_RATE");
        ObserverManagerUtil.e(this.aj, "SLEEP_MESSAGE_JUMP_TAG");
        qnl.dGt_(this.ag, this.am, e(), this.aq, this.t);
        Map<String, Object> map = this.i;
        if (map != null) {
            map.clear();
        }
        z();
        this.ba.removeCallbacksAndMessages(null);
        f fVar = this.bb;
        if (fVar == null || !koq.c(fVar.e)) {
            return;
        }
        HiHealthNativeApi.a(e()).unSubscribeHiHealthData(this.bb.e, null);
    }

    private void z() {
        Handler handler = this.w;
        if (handler == null || this.az == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.az.quitSafely();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        super.onResume();
        LogUtil.a("SleepDayBarChartProvider", "onresume");
        if (!this.z || !jec.ab(this.r)) {
            c(this.r, "TYPE_ON_RESUME", pob.a((Boolean) false).booleanValue());
        }
        ObserverManagerUtil.c("CHART_START_END_TIME", Long.valueOf(jdl.e(jdl.b(this.r.getTime(), -1), 20, 0)), Long.valueOf(jdl.e(this.r.getTime(), 20, 0)));
    }

    static class g implements SleepDailyProcessResultCallback<fda> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SleepDayBarChartProvider> f10228a;
        private final Date b;
        private final Set<String> c;

        public g(SleepDayBarChartProvider sleepDayBarChartProvider, Set<String> set, Date date) {
            this.f10228a = new WeakReference<>(sleepDayBarChartProvider);
            this.b = date;
            this.c = set;
        }

        @Override // com.huawei.hwbasemgr.SleepDailyProcessResultCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, fda fdaVar) {
            Date date;
            Object[] objArr = new Object[6];
            objArr[0] = "Get result,mDate is ";
            objArr[1] = this.b;
            objArr[2] = " errorCode:";
            objArr[3] = Integer.valueOf(i);
            objArr[4] = " result is null, ";
            objArr[5] = Boolean.valueOf(fdaVar == null);
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", objArr);
            Set<String> set = this.c;
            if (set != null) {
                set.add("TYPE_DAILY_RESULT");
            }
            SleepDayBarChartProvider sleepDayBarChartProvider = this.f10228a.get();
            if (sleepDayBarChartProvider == null) {
                ReleaseLogUtil.c("R_Sleep_SleepDayBarChartProvider", "WeakReference is null");
                return;
            }
            if (i != 0 || fdaVar == null) {
                LogUtil.h("SleepDayBarChartProvider", "SleepDailyProcessResult failed!!");
                Date date2 = this.b;
                if (date2 == null || !date2.equals(sleepDayBarChartProvider.r)) {
                    return;
                }
                sleepDayBarChartProvider.c();
                return;
            }
            fdp fdpVar = sleepDayBarChartProvider.d;
            if (fdpVar != null && (date = this.b) != null && date.equals(sleepDayBarChartProvider.r)) {
                LogUtil.a("SleepDayBarChartProvider", "getDailyProcessResult, currentDay is ", sleepDayBarChartProvider.r);
                qnl.d(fdpVar.l(), "MGMT_DAILY_SLEEP_PROCESS", fdaVar);
                qnl.d(sleepDayBarChartProvider.i, "MGMT_DAILY_SLEEP_PROCESS", fdaVar);
                sleepDayBarChartProvider.c();
                return;
            }
            Object[] objArr2 = new Object[5];
            objArr2[0] = "Data or Date is null";
            objArr2[1] = Boolean.valueOf(fdpVar == null);
            objArr2[2] = Boolean.valueOf(this.b == null);
            objArr2[3] = " currentDay = ";
            objArr2[4] = sleepDayBarChartProvider.r;
            ReleaseLogUtil.d("R_Sleep_SleepDayBarChartProvider", objArr2);
        }
    }

    public static class i implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Date f10229a;
        private WeakReference<SleepDayBarChartProvider> c;
        private final String d;

        public i(SleepDayBarChartProvider sleepDayBarChartProvider, String str, Date date) {
            this.c = new WeakReference<>(sleepDayBarChartProvider);
            this.d = str;
            this.f10229a = date;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "Get open status,type is ", this.d);
            final SleepDayBarChartProvider sleepDayBarChartProvider = this.c.get();
            if (sleepDayBarChartProvider == null) {
                ReleaseLogUtil.c("R_Sleep_SleepDayBarChartProvider", "SleepManagerStateCallBack provider is null.");
                return;
            }
            final boolean z = i == 0 && (obj instanceof Boolean) && ((Boolean) obj).booleanValue();
            sleepDayBarChartProvider.d(z);
            HandlerExecutor.e(new Runnable() { // from class: qnk
                @Override // java.lang.Runnable
                public final void run() {
                    SleepDayBarChartProvider.i.this.d(sleepDayBarChartProvider, z);
                }
            });
        }

        public /* synthetic */ void d(SleepDayBarChartProvider sleepDayBarChartProvider, boolean z) {
            sleepDayBarChartProvider.c(this.f10229a, this.d, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Date date, String str, boolean z) {
        fdp fdpVar = this.d;
        Map<String, Object> l = fdpVar.l();
        str.hashCode();
        if (str.equals("TYPE_ON_RESUME")) {
            boolean z2 = !Boolean.valueOf(z).equals(l.get("OPEN_STATUS"));
            boolean z3 = !Boolean.FALSE.equals(l.get("BAR_CHART_IS_TODAY"));
            qnl.d(l, "OPEN_STATUS", Boolean.valueOf(z));
            if (z2 && z && !z3) {
                a(jec.e());
            } else if (z2 && z3) {
                notifyMinorProviders(fdpVar);
            } else {
                LogUtil.a("SleepDayBarChartProvider", "activity to query the lastest sleep day");
            }
            LogUtil.a("SleepDayBarChartProvider", "isChange:", Boolean.valueOf(z2), "isOpen:", Boolean.valueOf(z), "isToday:", Boolean.valueOf(z3));
            return;
        }
        if (str.equals("TYPE_GET_SLEEP_DATA")) {
            qnl.d(l, "OPEN_STATUS", Boolean.valueOf(z));
            Set<String> set = this.ar;
            if (set != null) {
                set.add("TYPE_OPEN_STATUS");
            }
            LogUtil.a("SleepDayBarChartProvider", "mDate is ", date);
            if (date.equals(this.r)) {
                LogUtil.a("SleepDayBarChartProvider", "get open status ,current day is ", this.r);
                c();
                return;
            }
            return;
        }
        LogUtil.a("SleepDayBarChartProvider", "others");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (!z || Boolean.TRUE.equals(this.ac)) {
            return;
        }
        HandlerExecutor.d(new Runnable() { // from class: qnh
            @Override // java.lang.Runnable
            public final void run() {
                SleepDayBarChartProvider.this.b();
            }
        }, 1000L);
    }

    public /* synthetic */ void b() {
        if (this.ac == null) {
            Boolean valueOf = Boolean.valueOf(SharedPreferenceManager.a(String.valueOf(10006), "sleep_manage_private_dg_show_key", false));
            this.ac = valueOf;
            LogUtil.a("SleepDayBarChartProvider", "checkToShowPrivateDialog: get mHasShowPrivateOpenDialog", valueOf);
        }
        if (this.ac.booleanValue()) {
            return;
        }
        List<Integer> needOpenPrivate = SleepManagementJsUtil.getNeedOpenPrivate();
        if (needOpenPrivate.isEmpty()) {
            return;
        }
        Boolean bool = true;
        this.ac = bool;
        SharedPreferenceManager.e(String.valueOf(10006), "sleep_manage_private_dg_show_key", bool.booleanValue());
        LogUtil.a("SleepDayBarChartProvider", "checkToShowPrivateDialog: set mHasShowPrivateOpenDialog", this.ac);
        SleepManagementJsUtil.showPrivateOpenDialog(needOpenPrivate, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public void c() {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: qmw
                @Override // java.lang.Runnable
                public final void run() {
                    SleepDayBarChartProvider.this.c();
                }
            });
            return;
        }
        LogUtil.a("SleepDayBarChartProvider", "notifyCustomMinorProviders: responses is ", this.ar);
        if (this.ar.size() == 3 || (this.ar.contains("TYPE_GET_SLEEP_DATA") && !this.d.m())) {
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "got all data or unvalid");
            LogUtil.a("SleepDayBarChartProvider", "notifyCustomMinorProviders,already got open status and daily result");
            ad();
            x();
        }
    }

    private void ad() {
        if (this.ar.size() != 3) {
            LogUtil.a("SleepDayBarChartProvider", "mResponses is not 3");
            return;
        }
        Bundle extra = getExtra();
        boolean z = extra != null ? extra.getBoolean("key_bundle_health_red_dot", false) : false;
        HashMap hashMap = new HashMap();
        fda fdaVar = this.d.l().get("MGMT_DAILY_SLEEP_PROCESS") instanceof fda ? (fda) this.d.l().get("MGMT_DAILY_SLEEP_PROCESS") : null;
        int h2 = fdaVar != null ? fdaVar.h() : -1;
        hashMap.put("dataType", Integer.valueOf(this.n));
        hashMap.put("OPEN_STATUS", this.d.l().get("OPEN_STATUS"));
        hashMap.put("MGMT_DAILY_SLEEP_PROCESS", Integer.valueOf(h2));
        hashMap.put("key_bundle_health_red_dot", Boolean.valueOf(z));
        LogUtil.a("SleepDayBarChartProvider", "bimap is", hashMap.toString());
        ObserverManagerUtil.c("SLEEP_DAY_TYPE_BI_TYPE", hashMap);
    }

    private void x() {
        if (this.d.g() == this.r.getTime()) {
            notifyMinorProviders(this.d);
            this.ab = true;
        } else {
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "not today data");
        }
    }

    private void d(Map<String, Object> map) {
        fdp fdpVar = this.d;
        if (fdpVar == null) {
            LogUtil.a("SleepDayBarChartProvider", "mTotalSleepData is null");
            return;
        }
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            map.put("type", 3);
        } else if (this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            map.put("type", 2);
        } else {
            map.put("type", 1);
        }
    }

    private void g() {
        long j2;
        LogUtil.a("SleepDayBarChartProvider", "getCoreSleepData");
        long b2 = jdl.b(jdl.t(System.currentTimeMillis()), -2);
        String d2 = pqg.d(e(), "goToEcgTipTime");
        if (!"default_value".equals(d2)) {
            try {
                j2 = Long.parseLong(d2);
            } catch (NumberFormatException unused) {
                LogUtil.h("SleepDayBarChartProvider", "getCoreSleepData lastTime parseLong exception");
                j2 = 0;
            }
            if (b2 < j2) {
                return;
            }
        }
        LogUtil.a("SleepDayBarChartProvider", "requestCoreSleepDetail: ");
        c.a(new Date(b2), new Date(System.currentTimeMillis()), new e(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<fdp> list) {
        LogUtil.a("SleepDayBarChartProvider", "showGoToEcg");
        int i2 = kor.a().n().getAgeOrDefaultValue() < 65 ? 6 : 5;
        Iterator<fdp> it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            int h2 = it.next().j().h();
            if (h2 == 0) {
                z = false;
            }
            if (h2 >= i2 * 60) {
                z = false;
            }
        }
        if (z && pxz.d()) {
            this.ba.sendEmptyMessage(6009);
            pqg.d(e(), "goToEcgTipTime", String.valueOf(TimeUtil.b(new Date().getTime())));
        }
    }

    public void a(Date date) {
        LogUtil.a("SleepDayBarChartProvider", "requestSpecifiedDayDatas date is " + date);
        this.r = (Date) date.clone();
        av();
        w();
    }

    private boolean r() {
        if (koq.b(this.v)) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (pwb pwbVar : this.v) {
            if (pwbVar.c() == 32) {
                z = true;
            } else if (pwbVar.b() instanceof Boolean) {
                if (((Boolean) pwbVar.b()).booleanValue()) {
                    z3 = true;
                } else {
                    z2 = true;
                }
            }
        }
        if (z && z2) {
            return true;
        }
        return z && z3;
    }

    private void h() {
        LogUtil.a("SleepDayBarChartProvider", "enter dismissLoading...");
        qnl.d(this.i, "BAR_CHART_IS_DISMISS_LOADING", true);
    }

    public void b(boolean z) {
        synchronized (f) {
            if (e() instanceof KnitSleepDetailActivity) {
                ((KnitSleepDetailActivity) e()).d(true);
            }
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "RequestDatas mCurrentDay is ", this.r);
            ObserverManagerUtil.c("SLEEP_CHART_NOW_DATE", Long.valueOf(this.r.getTime()));
            ObserverManagerUtil.c("CHART_START_END_TIME", Long.valueOf(jdl.e(jdl.b(this.r.getTime(), -1), 20, 0)), Long.valueOf(jdl.e(this.r.getTime(), 20, 0)));
            this.ba.removeMessages(20);
            this.ba.sendEmptyMessageDelayed(20, 900000L);
            qnl.d(this.i, "BAR_CHART_IS_DISMISS_LOADING", false);
            if (this.r != null) {
                a(z);
            }
            this.d.b(this.i);
            this.ap.e(this.d);
        }
    }

    private void a(boolean z) {
        if (!jdl.ac(this.r.getTime())) {
            qnl.d(this.i, "BAR_CHART_IS_TODAY", false);
        }
        c((Date) this.r.clone(), z);
        if (VersionControlUtil.isSupportSleepManagement()) {
            this.ab = false;
            this.ar = Collections.synchronizedSet(new HashSet());
            qnl.b(this.d.l(), "MGMT_DAILY_SLEEP_PROCESS");
            if (this.d.l() != null) {
                this.d.l().put("from", Integer.valueOf(this.x));
            }
            a(this.r, this.ar);
            if (pob.a((Boolean) null) != null) {
                c(this.r, "TYPE_GET_SLEEP_DATA", pob.a((Boolean) false).booleanValue());
            } else {
                pob.d(new i(this, "TYPE_GET_SLEEP_DATA", this.r));
            }
            this.ba.postDelayed(new Runnable() { // from class: qnb
                @Override // java.lang.Runnable
                public final void run() {
                    SleepDayBarChartProvider.this.f();
                }
            }, 1000L);
        }
    }

    public /* synthetic */ void f() {
        if (!this.ar.contains("TYPE_GET_SLEEP_DATA") || this.ab) {
            return;
        }
        LogUtil.a("SleepDayBarChartProvider", "notifyCustomMinorProviders, already get sleep data but not get open status and daily result");
        x();
    }

    private void a(final Date date, final Set<String> set) {
        if (date == null || set == null) {
            LogUtil.h("SleepDayBarChartProvider", " requestDailyResult ");
        }
        j();
        this.w.removeCallbacksAndMessages(null);
        this.w.post(new Runnable() { // from class: qnc
            @Override // java.lang.Runnable
            public final void run() {
                SleepDayBarChartProvider.this.c(date, set);
            }
        });
    }

    public /* synthetic */ void c(Date date, Set set) {
        LogUtil.a("SleepDayBarChartProvider", " sleepDailyResult start.", date);
        pob.b(date, new g(this, set, date));
        LogUtil.a("SleepDayBarChartProvider", " sleepDailyResult end.");
    }

    private void j() {
        if (this.w == null) {
            HandlerThread handlerThread = new HandlerThread("sleepDailyResult");
            this.az = handlerThread;
            handlerThread.start();
            this.w = new Handler(this.az.getLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        LogUtil.a("SleepDayBarChartProvider", "mFitnessOriginListData is ", this.v);
        if (koq.c(this.v)) {
            String b2 = SharedPreferenceManager.b(e(), String.valueOf(10006), "SLEEP_DEVICE_ICON_RED_DOT_KEY");
            LogUtil.a("SleepDayBarChartProvider", "showRedDot is ", b2);
            if ("".equals(b2)) {
                qnl.d(e(), this.i);
            } else if ("false".equals(b2)) {
                qnl.d(this.i, "BAR_CHART_HELP_ICON", nsf.cKq_(R.drawable._2131430282_res_0x7f0b0b8a));
            }
            qnl.d(this.i, "BAR_CHART_HELP_ICON_VISIBILITY", 0);
        } else {
            qnl.d(this.i, "BAR_CHART_HELP_ICON_VISIBILITY", 8);
        }
        if (koq.b(this.v) || TextUtils.isEmpty(this.u) || this.y == 0) {
            LogUtil.h("SleepDayBarChartProvider", "msg.obj not instanceof List");
        } else {
            this.d.b(this.i);
            this.ap.e(this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ar() {
        LogUtil.a("SleepDayBarChartProvider", "showFitnessDataOriginDialog()");
        String str = this.u;
        if (str == null || str.equals("") || this.y == 0) {
            return;
        }
        String b2 = SharedPreferenceManager.b(e(), String.valueOf(10006), "SLEEP_DEVICE_ICON_RED_DOT_KEY");
        LogUtil.a("SleepDayBarChartProvider", "showFitnessDataOriginDialog showRedDot is ", b2);
        if ("".equals(b2)) {
            qnl.d(this.i, "BAR_CHART_HELP_ICON", nsf.cKq_(R.drawable._2131430282_res_0x7f0b0b8a));
            this.d.b(this.i);
            this.ap.e(this.d);
            SharedPreferenceManager.e(e(), String.valueOf(10006), "SLEEP_DEVICE_ICON_RED_DOT_KEY", "false", new StorageParams());
        }
        CustomViewDialog customViewDialog = this.q;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.q.dismiss();
            return;
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(e());
        qnj.c(this.u, this.y, this.v, builder, e());
        CustomViewDialog e2 = builder.e();
        this.q = e2;
        e2.show();
    }

    private int c(long j2, long j3) {
        return qnl.a(j2, j3, this.ay, this.av);
    }

    private int dGd_(SparseArray<Object> sparseArray, int i2) {
        return qnl.dGp_(sparseArray, i2, this.ay, this.av);
    }

    private void c(Date date, boolean z) {
        c = new pwd();
        h hVar = new h(this);
        this.s = hVar;
        c.d(new Date[]{date, date}, "SleepDayBarChartProvider", 1, z, hVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HealthCalendar healthCalendar) {
        Date date = new Date(jdl.t(healthCalendar.transformCalendar().getTimeInMillis()));
        this.r = date;
        ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "prossCalendarSelect mCurrentDay ", date, " calendar ", healthCalendar);
        av();
        w();
    }

    private void ab() {
        this.ba.postDelayed(new Runnable() { // from class: qmz
            @Override // java.lang.Runnable
            public final void run() {
                SleepDayBarChartProvider.this.i();
            }
        }, 1L);
    }

    public /* synthetic */ void i() {
        if (this.af) {
            return;
        }
        LogUtil.a("SleepDayBarChartProvider", "requestSleepDataDelayed, requestYearDatas.");
        b(true);
    }

    private void t() {
        LogUtil.a("SleepDayBarChartProvider", "loadingAnimation.start by sleepDayBarChartProvider initViewPagerData");
        this.ba.removeMessages(20);
        this.ba.sendEmptyMessageDelayed(20, 900000L);
        qmu qmuVar = new qmu();
        qmuVar.b(this.ao);
        qmuVar.c(0);
        qmuVar.d(this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE);
        qmuVar.a(0.0d);
        qmuVar.b(false);
        qmuVar.c(this.r);
        qnl.d(this.i, "BAR_CHART_LEGEND_ONE_DRAWABLE", nsf.cKq_(R.drawable._2131427991_res_0x7f0b0297));
        qnl.d(this.i, "BAR_CHART_LEGEND_ONE_TEXT", e().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep));
        qnl.d(this.i, "BAR_CHART_LEGEND_TWO_DRAWABLE", nsf.cKq_(R.drawable._2131430785_res_0x7f0b0d81));
        qnl.d(this.i, "BAR_CHART_LEGEND_TWO_TEXT", e().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep));
        qnl.d(this.i, "BAR_CHART_LEGEND_THREE_DRAWABLE", nsf.cKq_(R.drawable._2131431175_res_0x7f0b0f07));
        qnl.d(this.i, "BAR_CHART_LEGEND_THREE_TEXT", e().getString(R$string.IDS_fitness_core_sleep_rem_sleep));
        qnl.d(this.i, "BAR_CHART_LEGEND_FIVE_DRAWABLE", nsf.cKq_(R.drawable._2131432003_res_0x7f0b1243));
        qnl.d(this.i, "BAR_CHART_LEGEND_FIVE_TEXT", e().getString(R$string.IDS_details_sleep_sleep_latency));
        qnl.d(this.i, "BAR_CHART_LEGEND_FOUR_DRAWABLE", nsf.cKq_(R.drawable._2131431987_res_0x7f0b1233));
        qnl.d(this.i, "BAR_CHART_LEGEND_FOUR_TEXT", e().getString(R$string.IDS_fitness_core_sleep_noontime_sleep));
        qnl.d(this.i, "BAR_CHART_LEGEND_SIX_DRAWABLE", nsf.cKq_(R.drawable._2131430814_res_0x7f0b0d9e));
        qnl.d(this.i, "BAR_CHART_LEGEND_SIX_TEXT", e().getString(R$string.IDS_manual_sleep_bed));
        qnl.d(this.i, "BAR_CHART_LEGEND_SEVEN_DRAWABLE", nsf.cKq_(R.drawable._2131430816_res_0x7f0b0da0));
        qnl.d(this.i, "BAR_CHART_LEGEND_SEVEN_TEXT", e().getString(R$string.IDS_manual_sleep_sleep));
        b(qmuVar);
        LogUtil.a("SleepDayBarChartProvider", "initViewPagerData mSleepDataList: ", this.ao.toString());
    }

    private void ag() {
        ae();
        qnl.d(this.i, "BAR_CHART_VIEWPAGER_TOUCH_EVENT", new HealthViewPager.OnViewPagerTouchEvent() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.2
            @Override // com.huawei.ui.commonui.viewpager.HealthViewPager.OnViewPagerTouchEvent
            public void onTouchDown(MotionEvent motionEvent) {
                if (SleepDayBarChartProvider.this.e() instanceof KnitSleepDetailActivity) {
                    ((KnitSleepDetailActivity) SleepDayBarChartProvider.this.e()).getViewPager().setIsScroll(false);
                    if (motionEvent != null) {
                        SleepDayBarChartProvider.this.al = motionEvent.getRawX();
                        SleepDayBarChartProvider.this.an = motionEvent.getRawY();
                    }
                }
            }

            @Override // com.huawei.ui.commonui.viewpager.HealthViewPager.OnViewPagerTouchEvent
            public void onTouchUp(MotionEvent motionEvent) {
                SleepDayBarChartProvider.this.dGh_(motionEvent);
            }

            @Override // com.huawei.ui.commonui.viewpager.HealthViewPager.OnViewPagerTouchEvent
            public void setIsViewTouch(Boolean bool) {
                SleepDayBarChartProvider.this.aa = bool.booleanValue();
            }
        });
        boolean c2 = cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "SleepDayBarChartProvider"), 143);
        ReleaseLogUtil.d("R_Sleep_SleepDayBarChartProvider", "isSupportCoreSequence：", Boolean.valueOf(c2));
        nhu.c().registerProgressListener(this.as);
        if (c2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_DETAILS.value()));
            HiHealthNativeApi.a(e()).subscribeHiHealthData(arrayList, new b(this));
        }
    }

    static class b implements HiSubscribeListener {
        private WeakReference e;

        public b(SleepDayBarChartProvider sleepDayBarChartProvider) {
            this.e = new WeakReference(sleepDayBarChartProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a("SleepDayBarChartProvider", "successList: " + list + ", failList: " + list2);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            WeakReference weakReference = this.e;
            if (weakReference == null || !(weakReference.get() instanceof SleepDayBarChartProvider)) {
                LogUtil.a("SleepDayBarChartProvider", "mWeakReference is null or not instance of SleepDayBarChartProvider");
                return;
            }
            LogUtil.a("SleepDayBarChartProvider", "CoreSleepSequenceSubscribeListtener type is", Integer.valueOf(i), " changeKey is ", str);
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "CSSSL is ", Integer.valueOf(i), " CK is ", str);
            SleepDayBarChartProvider sleepDayBarChartProvider = (SleepDayBarChartProvider) this.e.get();
            if (sleepDayBarChartProvider == null) {
                LogUtil.a("SleepDayBarChartProvider", "sleepDayBarChartProvider is null");
            } else if (ArkUIXConstants.INSERT.equals(str)) {
                ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "CSSSL");
                sleepDayBarChartProvider.a(jec.e());
            }
        }
    }

    private void n() {
        qnl.d(this.i, "BAR_CHART_CALENDAR_CLICK_EVENT", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.3
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2, int i3) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", SleepDayBarChartProvider.this.m);
                bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(new int[]{44105, 44004, 44108, 44109}));
                bundle.putBoolean("isSetGrayUnmarkedDate", true);
                if (SleepDayBarChartProvider.this.e() instanceof Activity) {
                    HealthCalendarActivity.cxj_((Activity) SleepDayBarChartProvider.this.e(), bundle, SleepDayBarChartProvider.this.l);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.d.b(this.i);
        this.ap.e(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dGh_(MotionEvent motionEvent) {
        if (e() instanceof KnitSleepDetailActivity) {
            ((KnitSleepDetailActivity) e()).getViewPager().setIsScroll(true);
            if (this.af) {
                LogUtil.a("SleepDayBarChartProvider", "onTouchUp mIsLoading is true.");
                return;
            }
            float abs = Math.abs(motionEvent.getRawX() - this.al);
            if (abs < Math.abs(motionEvent.getRawY() - this.an)) {
                return;
            }
            float x = motionEvent.getX();
            boolean bc = LanguageUtil.bc(BaseApplication.getContext());
            if (abs <= 100.0f || !this.aa) {
                return;
            }
            if (x > this.al) {
                if (!bc) {
                    s();
                    return;
                } else {
                    if (this.j) {
                        return;
                    }
                    aa();
                    return;
                }
            }
            if (bc) {
                s();
            } else {
                if (this.j) {
                    return;
                }
                aa();
            }
        }
    }

    private void f(final boolean z) {
        this.ba.postDelayed(new Runnable() { // from class: qne
            @Override // java.lang.Runnable
            public final void run() {
                SleepDayBarChartProvider.this.e(z);
            }
        }, 500L);
    }

    public /* synthetic */ void e(boolean z) {
        this.ai = z;
    }

    private void ae() {
        b(new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2, int i3) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("BAR_LEFT_ARROW_CLICK_EVENT".equals(str)) {
                    SleepDayBarChartProvider.this.s();
                    return;
                }
                if ("BAR_RIGHT_ARROW_CLICK_EVENT".equals(str)) {
                    SleepDayBarChartProvider.this.aa();
                } else if ("BAR_HELP_ICON_CLICK_EVENT".equals(str)) {
                    SleepDayBarChartProvider.this.ar();
                } else {
                    LogUtil.a("SleepDayBarChartProvider", "onClick wrong");
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        LogUtil.a("SleepDayBarChartProvider", "day calendar is " + calendar);
        if (this.m == null) {
            this.m = new HealthCalendar();
        }
        this.m = this.m.transformFromCalendar(calendar);
    }

    private void an() {
        this.d.a(this.u);
        this.d.a(this.ak);
        this.d.b(this.i);
        this.d.c(this.y);
        this.d.c(this.r.getTime());
        LogUtil.a("SleepDayBarChartProvider", "setSleepDataUi mTotalSleepData = ", this.d.toString());
        a(this.d);
    }

    private void aq() {
        LogUtil.a("SleepDayBarChartProvider", "recommend id : ", g);
        if (this.d.j().bb() || this.d.f().ad()) {
            LogUtil.a("SleepDayBarChartProvider", "isNoonSleepOnly.");
            c(8, "BAR_CHART_PROCESS_TEXT_VISIBILITY");
            c(8, "BAR_CHART_CORE_LEGEND_VISIBILITY");
            c(8, "BAR_CHART_REM_LEGEND_VISIBILITY");
            c(0, "BAR_CHART_NOON_LEGEND_VISIBILITY");
            this.d.b(this.i);
            this.ap.e(this.d);
        } else {
            c(0, "BAR_CHART_CORE_LEGEND_VISIBILITY");
            c(0, "BAR_CHART_REM_LEGEND_VISIBILITY");
            c(8, "BAR_CHART_NOON_LEGEND_VISIBILITY");
            if (this.d.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
                c(8, "BAR_CHART_REM_LEGEND_VISIBILITY");
            }
            if (this.d.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
                c(8, "BAR_CHART_PROCESS_TEXT_VISIBILITY");
                c(8, "BAR_CHART_CORE_LEGEND_VISIBILITY");
                c(8, "BAR_CHART_REM_LEGEND_VISIBILITY");
                c(0, "BAR_CHART_NOON_LEGEND_VISIBILITY");
                if (!this.d.d().o()) {
                    LogUtil.a("SleepDayBarChartProvider", "manual is sus");
                    qnl.d(this.i, "IS_INCOMPLETE_DATA", true);
                } else {
                    LogUtil.a("SleepDayBarChartProvider", "manual is not sus");
                    qnl.d(this.i, "IS_INCOMPLETE_DATA", false);
                }
            }
            this.d.b(this.i);
            this.ap.e(this.d);
        }
        am();
    }

    private void c(int i2, String str) {
        qnl.d(this.i, str, Integer.valueOf(i2));
    }

    private void am() {
        LogUtil.a("SleepDayBarChartProvider", "mTotalSleepData : ", this.d.toString());
        if (this.d.m()) {
            c(4, "BAR_CHART_PROCESS_TEXT_VISIBILITY");
            this.d.b(this.i);
            this.ap.e(this.d);
        } else {
            au();
        }
        this.d.c(this.r.getTime());
        this.d.a(r());
        qnl.d(this.i, "FITNESSCORE_SLEEP_DETAIL_INTERACTOR", c);
        this.d.b(this.i);
        this.ar.add("TYPE_GET_SLEEP_DATA");
        c();
    }

    private void b(OnClickSectionListener onClickSectionListener) {
        CoreSleepDayDetailView.OnClickViewDefaultListener onClickViewDefaultListener = new CoreSleepDayDetailView.OnClickViewDefaultListener() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.9
            @Override // com.huawei.health.knit.section.chart.CoreSleepDayDetailView.OnClickViewDefaultListener
            public void onClickViewDefaultListener(String str, String str2, int i2, boolean z, int i3, ArrayList<ecu> arrayList, int i4) {
                String str3;
                int i5;
                int i6;
                int i7;
                String str4;
                if (SleepDayBarChartProvider.this.d.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
                    SleepDayBarChartProvider.this.a(str, str2, i2, i3, arrayList, i4);
                    return;
                }
                if (i2 == -1) {
                    str3 = "";
                    if (SleepDayBarChartProvider.this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
                        if (SleepDayBarChartProvider.this.d.j().bb()) {
                            i7 = SleepDayBarChartProvider.this.d.j().ap();
                            str4 = SleepDayBarChartProvider.this.e().getString(R$string.IDS_fitness_core_sleep_noontime_sleep);
                            i5 = i7;
                        } else {
                            int h2 = SleepDayBarChartProvider.this.d.j().h() + SleepDayBarChartProvider.this.d.j().ap();
                            int ap = SleepDayBarChartProvider.this.d.j().ap();
                            LogUtil.a("SleepDayBarChartProvider", "sleepTime = ", Integer.valueOf(SleepDayBarChartProvider.this.d.j().h()), ", noonSleepTime = ", Integer.valueOf(SleepDayBarChartProvider.this.d.j().ap()));
                            i5 = h2;
                            i7 = h2 - ap;
                            str4 = SleepDayBarChartProvider.this.e().getString(R$string.IDS_fitness_core_sleep_night_sleep);
                        }
                        i6 = SleepDayBarChartProvider.this.d.j().ap();
                    } else if (SleepDayBarChartProvider.this.d.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
                        if (SleepDayBarChartProvider.this.d.f().ad()) {
                            i7 = SleepDayBarChartProvider.this.d.f().w();
                            str4 = SleepDayBarChartProvider.this.e().getString(R$string.IDS_fitness_core_sleep_noontime_sleep);
                            i5 = i7;
                        } else {
                            int h3 = SleepDayBarChartProvider.this.d.f().h() + SleepDayBarChartProvider.this.d.f().w();
                            i5 = h3;
                            i7 = h3 - SleepDayBarChartProvider.this.d.f().w();
                            str4 = SleepDayBarChartProvider.this.e().getString(R$string.IDS_fitness_total_sleep_data_title);
                        }
                        i6 = SleepDayBarChartProvider.this.d.f().w();
                    } else if (SleepDayBarChartProvider.this.d.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
                        i7 = SleepDayBarChartProvider.this.d.c().h();
                        str4 = SleepDayBarChartProvider.this.e().getString(R$string.IDS_fitness_total_sleep_data_title);
                        i5 = i7;
                        i6 = 0;
                    }
                    boolean m = SleepDayBarChartProvider.this.d.m();
                    if (str4.equals(SleepDayBarChartProvider.this.au) || !str3.equals(SleepDayBarChartProvider.this.at) || SleepDayBarChartProvider.this.ax != i7) {
                        qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_PERIOD_STRING", str4);
                        qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_TYPE_STRING", str3);
                        qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_TIME_INT", Integer.valueOf(i7));
                        qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_DATA_VALID", Boolean.valueOf(m));
                        qnl.d(SleepDayBarChartProvider.this.i, "SLEEP_HYBRID_NIGHT_TIME", Integer.valueOf(i5));
                        qnl.d(SleepDayBarChartProvider.this.i, "SLEEP_HYBRID_NOON_TIME", Integer.valueOf(i6));
                        qnl.d(SleepDayBarChartProvider.this.i, "is_need_refresh_chart", false);
                        SleepDayBarChartProvider.this.au = str4;
                        SleepDayBarChartProvider.this.at = str3;
                        SleepDayBarChartProvider.this.ax = i7;
                        SleepDayBarChartProvider.this.d.b(SleepDayBarChartProvider.this.i);
                        SleepDayBarChartProvider.this.ap.e(SleepDayBarChartProvider.this.d);
                    }
                    SleepDayBarChartProvider sleepDayBarChartProvider = SleepDayBarChartProvider.this;
                    sleepDayBarChartProvider.d(sleepDayBarChartProvider.r.getTime());
                }
                str3 = str2;
                i5 = 0;
                i6 = 0;
                i7 = i2;
                str4 = str;
                boolean m2 = SleepDayBarChartProvider.this.d.m();
                if (str4.equals(SleepDayBarChartProvider.this.au)) {
                }
                qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_PERIOD_STRING", str4);
                qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_TYPE_STRING", str3);
                qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_TIME_INT", Integer.valueOf(i7));
                qnl.d(SleepDayBarChartProvider.this.i, "BAR_CHART_DATA_VALID", Boolean.valueOf(m2));
                qnl.d(SleepDayBarChartProvider.this.i, "SLEEP_HYBRID_NIGHT_TIME", Integer.valueOf(i5));
                qnl.d(SleepDayBarChartProvider.this.i, "SLEEP_HYBRID_NOON_TIME", Integer.valueOf(i6));
                qnl.d(SleepDayBarChartProvider.this.i, "is_need_refresh_chart", false);
                SleepDayBarChartProvider.this.au = str4;
                SleepDayBarChartProvider.this.at = str3;
                SleepDayBarChartProvider.this.ax = i7;
                SleepDayBarChartProvider.this.d.b(SleepDayBarChartProvider.this.i);
                SleepDayBarChartProvider.this.ap.e(SleepDayBarChartProvider.this.d);
                SleepDayBarChartProvider sleepDayBarChartProvider2 = SleepDayBarChartProvider.this;
                sleepDayBarChartProvider2.d(sleepDayBarChartProvider2.r.getTime());
            }
        };
        qnl.d(this.i, "BAR_CHART_ARROW_COMMON_EVENT", onClickSectionListener);
        qnl.d(this.i, "BAR_CHART_CLICK_EVENT", onClickViewDefaultListener);
        this.d.b(this.i);
        this.ap.e(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, int i2, int i3, ArrayList<ecu> arrayList, int i4) {
        qnl.d(this.i, "BAR_CHART_TIME_INT", Integer.valueOf(i2));
        if (i2 == -1) {
            ai();
        } else {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            if (i4 == 66) {
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TITLE", "");
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TITLE", e().getString(R$string.IDS_manual_sleep_sleep_time));
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TIME", 0);
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TIME", Integer.valueOf(i2));
                            } else if (i4 == 67) {
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TITLE", e().getString(R$string.IDS_manual_sleep_bed_time));
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TITLE", "");
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TIME", Integer.valueOf(i2));
                                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TIME", 0);
                            } else {
                                LogUtil.a("SleepDayBarChartProvider", "wrong data type!");
                                ai();
                            }
                        }
                    }
                }
                if (koq.d(arrayList, 1)) {
                    ecu ecuVar = arrayList.get(0);
                    int a2 = ecuVar.a() - ecuVar.c();
                    qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TIME", Integer.valueOf(a2));
                    ecu ecuVar2 = arrayList.get(1);
                    qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TIME", Integer.valueOf(i2 + (ecuVar2.a() - ecuVar2.c()) + a2));
                    qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TITLE", e().getString(R$string.IDS_manual_sleep_bed_time));
                    qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TITLE", e().getString(R$string.IDS_manual_sleep_sleep_time));
                }
            }
            if (koq.c(arrayList)) {
                ecu ecuVar3 = arrayList.get(0);
                int a3 = ecuVar3.a() - ecuVar3.c();
                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TIME", Integer.valueOf(a3));
                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TIME", Integer.valueOf(i2 + a3));
                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TITLE", e().getString(R$string.IDS_manual_sleep_bed_time));
                qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TITLE", e().getString(R$string.IDS_manual_sleep_sleep_time));
            }
        }
        this.d.b(this.i);
        this.ap.e(this.d);
        d(this.r.getTime());
    }

    private void ai() {
        String string;
        String string2;
        qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_BED_TIME", Integer.valueOf(this.d.d().k()));
        qnl.d(this.i, "BAR_CHART_TOTAL_MANUAL_SLEEP_TIME", Integer.valueOf(this.d.d().h()));
        Map<String, Object> map = this.i;
        if (this.ae) {
            string = e().getString(R$string.IDS_manual_sleep_total_bed_time);
        } else {
            string = e().getString(R$string.IDS_manual_sleep_bed_time);
        }
        qnl.d(map, "BAR_CHART_TOTAL_MANUAL_BED_TITLE", string);
        Map<String, Object> map2 = this.i;
        if (this.ae) {
            string2 = e().getString(R$string.IDS_manual_sleep_total_sleep_time);
        } else {
            string2 = e().getString(R$string.IDS_manual_sleep_sleep_time);
        }
        qnl.d(map2, "BAR_CHART_TOTAL_MANUAL_SLEEP_TITLE", string2);
    }

    private void av() {
        LogUtil.a("SleepDayBarChartProvider", "updateDateArrowVisible enter: ");
        long n = jec.n(this.r);
        long n2 = jec.n(jec.e());
        LogUtil.a("SleepDayBarChartProvider", "currentDay: ", Long.valueOf(n), ", today = ", Long.valueOf(n2));
        boolean z = n == n2;
        this.j = z;
        qnl.d(this.i, "BAR_CHART_IS_TODAY", Boolean.valueOf(z));
        this.d.b(this.i);
        this.ap.e(this.d);
    }

    private void as() {
        long j2;
        Bundle extra = getExtra();
        this.r = jec.e();
        if (extra != null) {
            this.x = extra.getInt("from", -1);
            long j3 = extra.getLong("endTime", 0L);
            boolean z = extra.getBoolean("key_is_open_sleep_management", false);
            String string = extra.getString("jumpFromFileSyncNotify");
            if (e() instanceof Activity) {
                j2 = ((Activity) e()).getIntent().getLongExtra("key_bundle_health_last_data_time", 0L);
                LogUtil.a("SleepDayBarChartProvider", "lastTimestamp = ", Long.valueOf(j2));
                if (j2 == 0) {
                    j2 = extra.getLong("key_bundle_health_last_data_time", 0L);
                }
            } else {
                j2 = extra.getLong("key_bundle_health_last_data_time", 0L);
            }
            LogUtil.a("SleepDayBarChartProvider", "Op :", Boolean.valueOf(z), "lastTt:", Long.valueOf(j2), "fm:", Integer.valueOf(this.x), "wakeUT:", Long.valueOf(j3));
            if (z) {
                LogUtil.a("R_Sleep_SleepDayBarChartProvider", "already open sleep mangement, just show today, even if no data");
            } else if (j3 > 0 && "jumpFromFileSyncNotify".equals(string)) {
                this.r.setTime(j3);
            } else if (j2 > 0) {
                this.r.setTime(j2);
            } else {
                LogUtil.a("SleepDayBarChartProvider", "jumpTag is wrong");
            }
        }
        ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "Current_day :", this.r);
        qnl.d(this.i, "BAR_CHART_DATE_TEXT", l());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("SleepDayBarChartProvider", "leftProcess");
        if (this.af) {
            LogUtil.a("SleepDayBarChartProvider", "leftProcess OnClickListener mIsLoading is true.");
            return;
        }
        this.r = jec.s(this.r);
        av();
        LogUtil.a("SleepDayBarChartProvider", "leftProcess, mCurrentDay = ", jec.x(this.r));
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        LogUtil.a("SleepDayBarChartProvider", "rightProcess");
        if (this.af) {
            LogUtil.a("SleepDayBarChartProvider", "rightProcess OnClickListener mIsLoading is true.");
            return;
        }
        this.r = jec.p(this.r);
        av();
        LogUtil.a("SleepDayBarChartProvider", "rightProcess, mCurrentDay = ", jec.x(this.r));
        w();
    }

    private void w() {
        this.ai = false;
        qnl.d(this.i, "BAR_CHART_DATE_TEXT", l());
        this.d.b(this.i);
        this.ap.e(this.d);
        au();
        b(false);
    }

    private void v() {
        LogUtil.a("SleepDayBarChartProvider", "refreshNoSleepData");
        if (c == null) {
            return;
        }
        this.ba.removeMessages(20);
        av();
        this.d = new fdp(SleepViewConstants.ViewTypeEnum.DAY);
        c(8, "BAR_CHART_NOON_LEGEND_VISIBILITY");
        c(8, "BAR_CHART_REM_LEGEND_VISIBILITY");
        c(8, "BAR_CHART_CORE_LEGEND_VISIBILITY");
        c(0, "BAR_CHART_PROCESS_TEXT_VISIBILITY");
        this.d.c(SleepViewConstants.SleepTypeEnum.UNKNOWN);
        this.d.c(this.r.getTime());
        this.d.a(r());
        qnl.d(this.i, "FITNESSCORE_SLEEP_DETAIL_INTERACTOR", c);
        qnl.d(this.i, "BAR_COMMON_IS_PHONE", false);
        this.d.b(this.i);
        this.ar.add("TYPE_GET_SLEEP_DATA");
        c();
        this.ap.e(this.d);
    }

    private void a(boolean z, boolean z2) {
        if (c == null || z) {
            return;
        }
        this.ba.removeMessages(20);
        av();
        this.ba.sendEmptyMessage(6007);
        c(z2);
    }

    private void b(boolean z, boolean z2) {
        if (c == null) {
            return;
        }
        if (!z) {
            this.ba.removeMessages(20);
            av();
            qmu qmuVar = new qmu();
            qmuVar.b(this.ao);
            qmuVar.d(true);
            qmuVar.a(this.d.j().at());
            qmuVar.b(true);
            qmuVar.c(this.r);
            if (z2) {
                qmuVar.c(0);
            }
            b(qmuVar);
            f(true);
            ao();
        }
        LogUtil.a("SleepDayBarChartProvider", "refreshOnlyNoonSleep isChart ", Boolean.valueOf(z));
    }

    private void ao() {
        an();
        aq();
    }

    private void au() {
        al();
        this.ao.clear();
        qmu qmuVar = new qmu();
        qmuVar.c(0);
        qmuVar.b(this.ao);
        qmuVar.d(this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE);
        qmuVar.a(this.d.j().at());
        qmuVar.b(false);
        qmuVar.c(this.r);
        b(qmuVar);
        f(true);
        c(8, "BAR_CHART_CORE_LEGEND_VISIBILITY");
        c(8, "BAR_CHART_REM_LEGEND_VISIBILITY");
        c(8, "BAR_CHART_NOON_LEGEND_VISIBILITY");
        this.d.b(this.i);
        this.ap.e(this.d);
    }

    private void al() {
        qnl.d(this.i, "BAR_CHART_IS_NO_DATA_REC", true);
        this.d.b(this.i);
        this.ap.e(this.d);
    }

    /* renamed from: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[SleepViewConstants.SleepTypeEnum.values().length];
            d = iArr;
            try {
                iArr[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void a(fdp fdpVar) {
        int h2;
        int i2 = AnonymousClass10.d[fdpVar.i().ordinal()];
        if (i2 == 1) {
            h2 = fdpVar.f().h();
        } else if (i2 == 2) {
            h2 = fdpVar.d().h();
        } else if (i2 == 3) {
            h2 = fdpVar.j().h();
        } else if (i2 == 4) {
            h2 = fdpVar.c().h();
        } else {
            LogUtil.a("SleepDayBarChartProvider", "other sleepType");
            h2 = 0;
        }
        qnl.d(h2);
    }

    static class d implements CommonUiBaseResponse {
        private WeakReference<SleepDayBarChartProvider> c;

        d(SleepDayBarChartProvider sleepDayBarChartProvider) {
            this.c = new WeakReference<>(sleepDayBarChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            SleepDayBarChartProvider sleepDayBarChartProvider = this.c.get();
            if (sleepDayBarChartProvider != null && i == 1 && (obj instanceof HealthCalendar)) {
                sleepDayBarChartProvider.c((HealthCalendar) obj);
            }
        }
    }

    static class h implements CommonUiBaseResponse {
        private WeakReference<SleepDayBarChartProvider> c;

        h(SleepDayBarChartProvider sleepDayBarChartProvider) {
            this.c = new WeakReference<>(sleepDayBarChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "All back now ");
            SleepDayBarChartProvider sleepDayBarChartProvider = this.c.get();
            if (sleepDayBarChartProvider == null) {
                return;
            }
            e(obj, sleepDayBarChartProvider);
            if (i == 100) {
                sleepDayBarChartProvider.u();
            } else {
                sleepDayBarChartProvider.q();
            }
            d(sleepDayBarChartProvider);
        }

        private void e(Object obj, SleepDayBarChartProvider sleepDayBarChartProvider) {
            if (!(obj instanceof pxa)) {
                LogUtil.b("SleepDayBarChartProvider", "object is not SleepDataFormInteractor");
                return;
            }
            pxa pxaVar = (pxa) obj;
            qnl.d(sleepDayBarChartProvider.i, "is_need_refresh_chart", Boolean.valueOf(b((fdp) jdn.a(sleepDayBarChartProvider.d), (fdp) jdn.a(pxaVar.f()))));
            sleepDayBarChartProvider.d = pxaVar.f();
            sleepDayBarChartProvider.ao = pxaVar.c();
            sleepDayBarChartProvider.u = pxaVar.d();
            sleepDayBarChartProvider.y = pxaVar.e();
            sleepDayBarChartProvider.ak = pxaVar.a();
            sleepDayBarChartProvider.ah = pxaVar.j();
            sleepDayBarChartProvider.v = pxaVar.b();
            LogUtil.a("SleepDayBarChartProvider", "mSleepViewData: ", sleepDayBarChartProvider.d, " mSleepDataList: ", sleepDayBarChartProvider.ao, " mDeviceId: ", sleepDayBarChartProvider.u, " mDeviceType: ", Integer.valueOf(sleepDayBarChartProvider.y), " mNoonSleepList: ", sleepDayBarChartProvider.ak, " mIsSuspicious: ", Boolean.valueOf(sleepDayBarChartProvider.ah), " mFitnessOriginListData:", sleepDayBarChartProvider.v);
        }

        private boolean b(fdp fdpVar, fdp fdpVar2) {
            if (fdpVar == null || fdpVar2 == null) {
                ReleaseLogUtil.d("R_Sleep_SleepDayBarChartProvider", "data is null");
                return false;
            }
            return !fdpVar.toString().equals(fdpVar2.toString());
        }

        private void d(SleepDayBarChartProvider sleepDayBarChartProvider) {
            qnl.c(sleepDayBarChartProvider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        String str;
        this.n = 0;
        ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "Judge data");
        Object[] objArr = new Object[6];
        objArr[0] = "mSleepViewData:";
        fdp fdpVar = this.d;
        if (fdpVar == null) {
            fdpVar = null;
        }
        objArr[1] = fdpVar;
        objArr[2] = " mDeviceId:";
        objArr[3] = this.u;
        objArr[4] = " mDeviceType:";
        objArr[5] = Integer.valueOf(this.y);
        LogUtil.a("SleepDayBarChartProvider", objArr);
        fdp fdpVar2 = this.d;
        if (fdpVar2 == null || !fdpVar2.m() || (str = this.u) == null || str.equals("") || this.y == 0) {
            ReleaseLogUtil.e("R_Sleep_SleepDayBarChartProvider", "Unvalid data");
            LogUtil.a("SleepDayBarChartProvider", "1、Data platform access to numerous");
            nrq.a().e(5001);
            this.af = false;
            this.n = 0;
            this.d = new fdp(SleepViewConstants.ViewTypeEnum.DAY);
        } else if (this.d.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            LogUtil.a("SleepDayBarChartProvider", "9、data is manual");
            nrq.a().e(FitnessStatusCodes.AGGREGATION_NOT_SUPPORTED);
            this.af = false;
            this.n = 5;
        } else if (this.d.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            if (this.d.f().u() > 0 && this.d.f().p() == 0) {
                qnl.b(this.y);
            } else if (this.d.f().ad()) {
                qnl.c(this.y);
            } else {
                qnl.a(this.y);
            }
            this.af = false;
            this.n = 3;
        } else if (this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            if (this.d.j().aw() > 0 && this.d.j().x() == 0 && this.d.j().aq() == 0) {
                qnl.b(this.y);
            } else if (this.d.j().bb()) {
                qnl.c(this.y);
            } else {
                qnl.a(this.y);
            }
            this.af = false;
            this.n = 1;
        } else {
            this.n = 4;
            LogUtil.a("SleepDayBarChartProvider", "5、Data platform gets to normal sleep");
            nrq.a().e(FitnessStatusCodes.APP_MISMATCH);
            this.af = false;
        }
        qnl.d(this.i, "WAKEUP_FEELING", -1);
        qnl.d(this.i, "IS_INCOMPLETE_DATA", false);
        if (this.y == 3) {
            ap();
            ak();
        } else {
            boolean z = nrq.a().b() == 5003;
            if (this.d.j().ar() == 0 && this.ah && this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE && !z) {
                qnl.d(this.i, "IS_INCOMPLETE_DATA", true);
            }
            qnl.d(this.i, "BAR_CHART_HIDE_TYPE", 5);
        }
        this.ba.sendEmptyMessage(5000);
        LogUtil.a("SleepDayBarChartProvider", "wearList is ", b, " smartPillowList is ", f10225a, " phoneList is ", e);
        ac();
    }

    private void ak() {
        long e2 = jec.e(this.r) * 1000;
        this.ay = e2;
        this.av = ((e2 / 1000) + k.b.m) * 1000;
        qnl.d(this.i, "BAR_CHART_HIDE_TYPE", Integer.valueOf(c(this.d.f().d(), this.d.f().a())));
        qnl.d(this.i, "BAR_COMMON_PHONE_START_TIME", Long.valueOf(this.d.f().d()));
        qnl.d(this.i, "BAR_COMMON_PHONE_END_TIME", Long.valueOf(this.d.f().a()));
        qnl.d(this.i, "time_zone", this.d.f().m());
    }

    private void ap() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()));
        if (TimeUtil.b(this.r.getTime(), System.currentTimeMillis()) && this.bb == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.r);
            calendar.add(5, 1);
            Date time = calendar.getTime();
            calendar.setTime(this.r);
            calendar.add(5, -1);
            this.bb = new f(this, jec.n(calendar.getTime()) * 1000, jec.k(time) * 1000);
            HiHealthNativeApi.a(e()).subscribeHiHealthData(arrayList, this.bb);
        }
    }

    private void ac() {
        if (this.h) {
            this.h = false;
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider.8
                @Override // java.lang.Runnable
                public void run() {
                    ObserverManagerUtil.c("SCORE_PROVIDER_DATA_ARRIVED", new Object[0]);
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int dGe_(SparseArray<Object> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.a("SleepDayBarChartProvider", "map is null");
            return -1;
        }
        LogUtil.a("SleepDayBarChartProvider", "SLEEP_WAKEUP_FEEL");
        long e2 = jec.e(this.r) * 1000;
        this.ay = e2;
        this.av = ((e2 / 1000) + k.b.m) * 1000;
        int dGd_ = dGd_(sparseArray, DicDataTypeUtil.DataType.WAKE_UP_FEELING.value());
        LogUtil.a("SleepDayBarChartProvider", "wakeUpFeeling: " + dGd_);
        return dGd_;
    }

    private void b(qmu qmuVar) {
        if (qmuVar == null || qmuVar.b() == null) {
            return;
        }
        this.o.clear();
        boolean z = false;
        this.ae = false;
        Iterator<pvz> it = qmuVar.b().iterator();
        int i2 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            pvz next = it.next();
            if (next != null) {
                if (next.e() > i2) {
                    this.ae = true;
                }
                i2 = next.c();
                ecu ecuVar = new ecu();
                ecuVar.d(next.e());
                ecuVar.a(next.c());
                ecuVar.e(next.b());
                ecuVar.e(next.a());
                ecuVar.b(next.d());
                LogUtil.a("SleepDayBarChartProvider", "mData ", ecuVar.toString());
                this.o.add(ecuVar);
            }
        }
        qnl.d(this.i, "BAR_CHART_DATA_LIST", this.o);
        qnl.d(this.i, "BAR_CHART_IS_SCIENCE_SLEEP", Boolean.valueOf(qmuVar.a()));
        qnl.d(this.i, "BAR_CHART_VALID_DATA", Double.valueOf(qmuVar.c()));
        qnl.d(this.i, "BAR_CHART_IS_TYPE", Boolean.valueOf(qmuVar.h()));
        qnl.d(this.i, "BAR_CHART_CURRENT_DAY", qmuVar.e());
        qnl.d(this.i, "BAR_CHART_COLOR_TYPE", Integer.valueOf(qmuVar.d()));
        qnl.d(this.i, "BAR_COMMON_IS_PHONE", Boolean.valueOf(this.d.i() == SleepViewConstants.SleepTypeEnum.PHONE));
        qnl.d(this.i, "BAR_COMMON_IS_MANUAL", Boolean.valueOf(this.d.i() == SleepViewConstants.SleepTypeEnum.MANUAL));
        qnl.d(this.i, "BAR_COMMON_IS_MANUAL_INCOMPLETE", Boolean.valueOf(this.ah));
        Map<String, Object> map = this.i;
        if (this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE && this.d.j().k() > 0) {
            z = true;
        }
        qnl.d(map, "BAR_SCIENCE_IS_SUPPORT_BEDTIME", Boolean.valueOf(z));
        this.d.b(this.i);
        this.ap.e(this.d);
    }

    private static boolean e(SleepDayBarChartProvider sleepDayBarChartProvider, pom pomVar, int i2) {
        if (i2 != 0) {
            return false;
        }
        if (jec.n(sleepDayBarChartProvider.r) == jec.n(jec.e())) {
            if (sleepDayBarChartProvider.z) {
                sleepDayBarChartProvider.ai = true;
                pomVar.c(false, false);
            } else {
                int e2 = nrq.a().e();
                LogUtil.a("SleepDayBarChartProvider", "coreSleepProcRate: ", Integer.valueOf(e2));
                if (e2 >= 0 && e2 < 100) {
                    sleepDayBarChartProvider.f(true);
                } else {
                    ad(sleepDayBarChartProvider);
                }
            }
            sleepDayBarChartProvider.z = false;
        } else {
            c(sleepDayBarChartProvider, 5);
            sleepDayBarChartProvider.h();
            sleepDayBarChartProvider.v();
        }
        return true;
    }

    private static void ad(SleepDayBarChartProvider sleepDayBarChartProvider) {
        sleepDayBarChartProvider.h();
        if (pom.d().b()) {
            c(sleepDayBarChartProvider, 1);
        } else {
            c(sleepDayBarChartProvider, -1);
        }
        sleepDayBarChartProvider.v();
    }

    private static void c(SleepDayBarChartProvider sleepDayBarChartProvider, int i2) {
        LogUtil.a("SleepDayBarChartProvider", " setSyncText " + i2);
        qnl.d(sleepDayBarChartProvider.i, "BAR_CHART_SYNC_TEXT", sleepDayBarChartProvider.e().getString(scn.m(i2)));
        sleepDayBarChartProvider.d.b(sleepDayBarChartProvider.i);
        sleepDayBarChartProvider.ap.e(sleepDayBarChartProvider.d);
    }

    private static void c(SleepDayBarChartProvider sleepDayBarChartProvider, int i2, int i3) {
        boolean c2 = cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "SleepDayBarChartProvider"), 143);
        ReleaseLogUtil.d("R_Sleep_SleepDayBarChartProvider", "isSupportCoreSequence：", Boolean.valueOf(c2));
        if (c2 && i2 == 1001) {
            sleepDayBarChartProvider.h();
            LogUtil.a("SleepDayBarChartProvider", "mIsSupportCoreSequence is true and results is 1001");
            return;
        }
        if (i2 == 1001) {
            switch (i3) {
                case 5002:
                    sleepDayBarChartProvider.a(false, false);
                    break;
                case FitnessStatusCodes.DATA_TYPE_NOT_FOUND /* 5003 */:
                    sleepDayBarChartProvider.b(false, false);
                    break;
                case FitnessStatusCodes.APP_MISMATCH /* 5004 */:
                    sleepDayBarChartProvider.d(false, false);
                    break;
                case FitnessStatusCodes.MISSING_BLE_PERMISSION /* 5006 */:
                case FitnessStatusCodes.UNSUPPORTED_PLATFORM /* 5007 */:
                    sleepDayBarChartProvider.c(false, false);
                    break;
            }
            sleepDayBarChartProvider.b(false);
            sleepDayBarChartProvider.h();
            return;
        }
        LogUtil.a("SleepDayBarChartProvider", "coreSleepSyncResults results = ", Integer.valueOf(i2));
    }

    private void d(boolean z, boolean z2) {
        if (c == null) {
            return;
        }
        LogUtil.a("SleepDayBarChartProvider", "refreshNormalSleep isChart ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        av();
        this.ba.sendEmptyMessage(6007);
        g(z2);
        aj();
        a(this.aw);
        this.d.c(this.r.getTime());
        this.d.a(r());
        qnl.d(this.i, "FITNESSCORE_SLEEP_DETAIL_INTERACTOR", c);
        this.d.b(this.i);
        this.ar.add("TYPE_GET_SLEEP_DATA");
        c();
        this.ap.e(this.d);
    }

    private void aj() {
        if (this.d.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            this.aw = this.d.d().h();
            return;
        }
        if (this.d.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            this.aw = this.d.f().h() + this.d.f().w();
        } else if (this.d.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            this.aw = this.d.c().h();
        } else if (this.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            this.aw = this.d.j().h() + this.d.j().ap();
        }
    }

    private void g(boolean z) {
        qmu qmuVar = new qmu();
        qmuVar.b(this.ao);
        qmuVar.d(false);
        qmuVar.a(this.d.j().at());
        qmuVar.b(false);
        qmuVar.c(this.r);
        if (z) {
            qmuVar.c(0);
        }
        b(qmuVar);
        f(true);
        c(8, "BAR_CHART_NOON_LEGEND_VISIBILITY");
        c(8, "BAR_CHART_REM_LEGEND_VISIBILITY");
        c(0, "BAR_CHART_CORE_LEGEND_VISIBILITY");
        c(4, "BAR_CHART_PROCESS_TEXT_VISIBILITY");
    }

    private void c(boolean z, boolean z2) {
        LogUtil.a("SleepDayBarChartProvider", " refreshCoreSleep isChart" + z + "isSetHighLight " + z2);
        if (c == null) {
            return;
        }
        if (!z) {
            this.ba.removeMessages(20);
            av();
            c(z2);
        }
        LogUtil.a("SleepDayBarChartProvider", "refreshCoreSleep isChart ", Boolean.valueOf(z));
    }

    private void a(int i2) {
        if (i2 / 60 != 0) {
            c(0, "DURATION_TIME_LAYOUT_VISIBILITY");
        } else if (i2 % 60 == 0) {
            c(4, "DURATION_TIME_LAYOUT_VISIBILITY");
        } else {
            c(0, "DURATION_TIME_LAYOUT_VISIBILITY");
        }
    }

    private static void c(SleepDayBarChartProvider sleepDayBarChartProvider, pom pomVar) {
        if (jec.n(sleepDayBarChartProvider.r) == jec.n(jec.e()) && sleepDayBarChartProvider.z) {
            sleepDayBarChartProvider.c(false, false);
            sleepDayBarChartProvider.h();
            pomVar.c(true, true);
            sleepDayBarChartProvider.z = false;
            return;
        }
        sleepDayBarChartProvider.c(false, true);
        sleepDayBarChartProvider.h();
    }

    private void y() {
        LogUtil.a("SleepDayBarChartProvider", "registerBroadcast !!!");
        this.ag = false;
        BroadcastManagerUtil.bFz_(e(), this.am, new IntentFilter("action_send_core_sleep_sync_rate"));
        BroadcastManagerUtil.bFC_(e(), this.aq, new IntentFilter("com.huawei.health.action.ACTION_CALLBACKSERVICE_PHONESERVICE_DEAD"), LocalBroadcast.c, null);
        LocalBroadcastManager.getInstance(e()).registerReceiver(this.t, new IntentFilter("com.huawei.health.action.COMMON_DATA_SAVE_COMPLETED"));
        this.ag = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dGj_(SleepDayBarChartProvider sleepDayBarChartProvider, Message message, pom pomVar) {
        int b2 = nrq.a().b();
        int intValue = (!(message.obj instanceof Integer) || ((Integer) message.obj).intValue() == 0) ? 0 : ((Integer) message.obj).intValue();
        sleepDayBarChartProvider.ad = false;
        LogUtil.a("SleepDayBarChartProvider", "syncLogicProcessing type = ", Integer.valueOf(b2));
        switch (b2) {
            case 5001:
                sleepDayBarChartProvider.ad = true;
                c(sleepDayBarChartProvider, pomVar, intValue);
                break;
            case 5002:
                a(sleepDayBarChartProvider, pomVar, intValue);
                break;
            case FitnessStatusCodes.DATA_TYPE_NOT_FOUND /* 5003 */:
                sleepDayBarChartProvider.ad = true;
                d(sleepDayBarChartProvider, pomVar, intValue);
                break;
            case FitnessStatusCodes.APP_MISMATCH /* 5004 */:
                b(sleepDayBarChartProvider, pomVar, intValue);
                break;
            case FitnessStatusCodes.UNKNOWN_AUTH_ERROR /* 5005 */:
                if (intValue != 1004 && intValue != 1003 && intValue != 1008) {
                    dGf_(sleepDayBarChartProvider, message, pomVar, intValue);
                    sleepDayBarChartProvider.af = false;
                    break;
                } else {
                    LogUtil.h("SleepDayBarChartProvider", "requestSuggestData not need update ui, result = ", Integer.valueOf(intValue));
                    return;
                }
            case FitnessStatusCodes.MISSING_BLE_PERMISSION /* 5006 */:
                dGf_(sleepDayBarChartProvider, message, pomVar, intValue);
                break;
            case FitnessStatusCodes.UNSUPPORTED_PLATFORM /* 5007 */:
                dGg_(sleepDayBarChartProvider, message, pomVar, intValue);
                break;
            case FitnessStatusCodes.TRANSIENT_ERROR /* 5008 */:
                sleepDayBarChartProvider.ad = true;
                d(sleepDayBarChartProvider, pomVar, intValue);
                break;
            case FitnessStatusCodes.EQUIVALENT_SESSION_ENDED /* 5009 */:
                dGf_(sleepDayBarChartProvider, message, pomVar, intValue);
                break;
            case FitnessStatusCodes.APP_NOT_FIT_ENABLED /* 5010 */:
                sleepDayBarChartProvider.ad = true;
                d(sleepDayBarChartProvider, pomVar, intValue);
                break;
            case FitnessStatusCodes.API_EXCEPTION /* 5011 */:
                dGf_(sleepDayBarChartProvider, message, pomVar, intValue);
                break;
            case FitnessStatusCodes.AGGREGATION_NOT_SUPPORTED /* 5012 */:
                dGf_(sleepDayBarChartProvider, message, pomVar, intValue);
                break;
        }
        if (this.p.get() instanceof KnitSleepDetailActivity) {
            if (this.d.i() == SleepViewConstants.SleepTypeEnum.MANUAL && this.d.d().h() <= 0) {
                this.ad = true;
            }
            ((KnitSleepDetailActivity) this.p.get()).d(sleepDayBarChartProvider.ad);
        }
    }

    private static void c(SleepDayBarChartProvider sleepDayBarChartProvider, pom pomVar, int i2) {
        if (e(sleepDayBarChartProvider, pomVar, i2)) {
        }
        LogUtil.a("SleepDayBarChartProvider", "setDataPlatformsType1 " + i2);
        boolean c2 = cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "SleepDayBarChartProvider"), 143);
        ReleaseLogUtil.d("R_Sleep_SleepDayBarChartProvider", "isSupportCoreSequence：", Boolean.valueOf(c2));
        switch (i2) {
            case 1001:
                sleepDayBarChartProvider.h();
                if (c2) {
                    ad(sleepDayBarChartProvider);
                    break;
                } else {
                    sleepDayBarChartProvider.b(false);
                    break;
                }
            case 1002:
                sleepDayBarChartProvider.h();
                c(sleepDayBarChartProvider, 6);
                sleepDayBarChartProvider.v();
                break;
            case 1003:
                ad(sleepDayBarChartProvider);
                break;
            case 1004:
            case 1008:
                break;
            case 1005:
                sleepDayBarChartProvider.h();
                c(sleepDayBarChartProvider, -1);
                sleepDayBarChartProvider.v();
                break;
            case 1006:
                ad(sleepDayBarChartProvider);
                break;
            case 1007:
                sleepDayBarChartProvider.h();
                c(sleepDayBarChartProvider, 3);
                sleepDayBarChartProvider.v();
                break;
            default:
                ad(sleepDayBarChartProvider);
                break;
        }
    }

    private static void a(SleepDayBarChartProvider sleepDayBarChartProvider, pom pomVar, int i2) {
        if (i2 != 0) {
            c(sleepDayBarChartProvider, i2, 5002);
            return;
        }
        if (jec.n(sleepDayBarChartProvider.r) == jec.n(jec.e()) && sleepDayBarChartProvider.z) {
            pomVar.c(false, false);
            sleepDayBarChartProvider.a(false, false);
            sleepDayBarChartProvider.h();
            sleepDayBarChartProvider.z = false;
            return;
        }
        sleepDayBarChartProvider.a(false, true);
        sleepDayBarChartProvider.h();
    }

    private static void d(SleepDayBarChartProvider sleepDayBarChartProvider, pom pomVar, int i2) {
        if (i2 != 0) {
            c(sleepDayBarChartProvider, i2, FitnessStatusCodes.DATA_TYPE_NOT_FOUND);
            return;
        }
        if (jec.n(sleepDayBarChartProvider.r) == jec.n(jec.e()) && sleepDayBarChartProvider.z) {
            sleepDayBarChartProvider.b(false, false);
            sleepDayBarChartProvider.h();
            pomVar.c(true, true);
            sleepDayBarChartProvider.z = false;
            return;
        }
        sleepDayBarChartProvider.b(false, true);
        sleepDayBarChartProvider.h();
    }

    private static void b(SleepDayBarChartProvider sleepDayBarChartProvider, pom pomVar, int i2) {
        if (i2 != 0) {
            c(sleepDayBarChartProvider, i2, FitnessStatusCodes.APP_MISMATCH);
            return;
        }
        if (jec.n(sleepDayBarChartProvider.r) == jec.n(jec.e()) && sleepDayBarChartProvider.z) {
            sleepDayBarChartProvider.d(false, false);
            sleepDayBarChartProvider.h();
            pomVar.c(false, true);
            sleepDayBarChartProvider.z = false;
            return;
        }
        sleepDayBarChartProvider.d(false, true);
        sleepDayBarChartProvider.h();
    }

    private static void dGf_(SleepDayBarChartProvider sleepDayBarChartProvider, Message message, pom pomVar, int i2) {
        g = message.arg2 + "";
        StringBuilder sb = new StringBuilder("setDataPlatformsType6 results");
        sb.append(i2);
        LogUtil.a("SleepDayBarChartProvider", sb.toString());
        if (i2 != 0) {
            c(sleepDayBarChartProvider, i2, FitnessStatusCodes.MISSING_BLE_PERMISSION);
        } else {
            c(sleepDayBarChartProvider, pomVar);
        }
    }

    private static void dGg_(SleepDayBarChartProvider sleepDayBarChartProvider, Message message, pom pomVar, int i2) {
        g = message.arg2 + "";
        if (i2 != 0) {
            c(sleepDayBarChartProvider, i2, FitnessStatusCodes.UNSUPPORTED_PLATFORM);
        } else {
            c(sleepDayBarChartProvider, pomVar);
        }
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<SleepDayBarChartProvider> c;

        c(SleepDayBarChartProvider sleepDayBarChartProvider) {
            this.c = new WeakReference<>(sleepDayBarChartProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.b("SleepDayBarChartProvider", "mProgressCallBack errCode=", Integer.valueOf(i));
            SleepDayBarChartProvider sleepDayBarChartProvider = this.c.get();
            if (sleepDayBarChartProvider == null) {
                LogUtil.b("SleepDayBarChartProvider", "handler is null");
                return;
            }
            if (i == 20000) {
                int b = mht.b(obj == null ? "0" : obj.toString());
                nrq.a().d(b);
                LogUtil.a("SleepDayBarChartProvider", "progress rate = ", Integer.valueOf(b));
                qnl.dGs_(b, sleepDayBarChartProvider.r, sleepDayBarChartProvider.ba, sleepDayBarChartProvider.ai);
                return;
            }
            if (i != 10000) {
                sleepDayBarChartProvider.ba.sendEmptyMessage(3);
            } else {
                LogUtil.a("SleepDayBarChartProvider", "mProgressCallBack normal status");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dGi_(SleepDayBarChartProvider sleepDayBarChartProvider, Message message) {
        int i2 = message.arg1;
        LogUtil.a("SleepDayBarChartProvider", "mCustomTransDialogBuilder set rate = ", Integer.valueOf(i2));
        ObserverManagerUtil.c("SLEEP_SYNC_RATE", Integer.valueOf(i2));
        qmu qmuVar = new qmu();
        if (jec.n(sleepDayBarChartProvider.r) == jec.n(jec.e())) {
            qmuVar.c(1);
        } else {
            qmuVar.c(0);
        }
        boolean z = nrq.a().b() == 5003;
        Object[] objArr = new Object[4];
        objArr[0] = "isOnlySleep: ";
        objArr[1] = Boolean.valueOf(z);
        objArr[2] = ", isScienceSleep: ";
        objArr[3] = Boolean.valueOf(sleepDayBarChartProvider.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE);
        LogUtil.a("SleepDayBarChartProvider", objArr);
        qmuVar.b(sleepDayBarChartProvider.ao);
        qmuVar.d(sleepDayBarChartProvider.d.i() == SleepViewConstants.SleepTypeEnum.SCIENCE);
        qmuVar.a(sleepDayBarChartProvider.d.j().at());
        qmuVar.b(z);
        qmuVar.c(sleepDayBarChartProvider.r);
        sleepDayBarChartProvider.b(qmuVar);
        ab(sleepDayBarChartProvider);
    }

    private static void ab(SleepDayBarChartProvider sleepDayBarChartProvider) {
        switch (nrq.a().b()) {
            case 5001:
                sleepDayBarChartProvider.ai = true;
                break;
            case 5002:
                sleepDayBarChartProvider.a(true, false);
                break;
            case FitnessStatusCodes.DATA_TYPE_NOT_FOUND /* 5003 */:
                sleepDayBarChartProvider.b(true, false);
                break;
            case FitnessStatusCodes.APP_MISMATCH /* 5004 */:
                sleepDayBarChartProvider.d(true, false);
                break;
            case FitnessStatusCodes.MISSING_BLE_PERMISSION /* 5006 */:
                sleepDayBarChartProvider.c(true, false);
                break;
            case FitnessStatusCodes.UNSUPPORTED_PLATFORM /* 5007 */:
                sleepDayBarChartProvider.c(true, false);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ac(SleepDayBarChartProvider sleepDayBarChartProvider) {
        LogUtil.a("SleepDayBarChartProvider", "show sync end loadingAnimation.stop()");
        nrq.a().d(100);
        ObserverManagerUtil.c("SLEEP_SYNC_RATE", 100);
        sleepDayBarChartProvider.w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("SleepDayBarChartProvider", "handleWhenSyncTimeOut() loadingAnimation.stop()");
        h();
        nrq.a().d(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("SleepDayBarChartProvider", "handleSyncTimeOut");
        h();
        ObserverManagerUtil.c("SLEEP_SYNC_RATE", 101);
    }

    class a extends BaseHandler<SleepDayBarChartProvider> {
        a(SleepDayBarChartProvider sleepDayBarChartProvider) {
            super(sleepDayBarChartProvider);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dGk_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SleepDayBarChartProvider sleepDayBarChartProvider, Message message) {
            if (sleepDayBarChartProvider == null) {
                return;
            }
            pom d = pom.d();
            int i = message.what;
            if (i == 3) {
                sleepDayBarChartProvider.o();
                return;
            }
            if (i == 20) {
                sleepDayBarChartProvider.k();
                return;
            }
            if (i == 5000) {
                SleepDayBarChartProvider.this.dGj_(sleepDayBarChartProvider, message, d);
                return;
            }
            if (i == 6008) {
                sleepDayBarChartProvider.ah();
                return;
            }
            if (i != 6009) {
                switch (i) {
                    case 23:
                        SleepDayBarChartProvider.dGi_(sleepDayBarChartProvider, message);
                        break;
                    case 24:
                        SleepDayBarChartProvider.ac(sleepDayBarChartProvider);
                        break;
                    case 25:
                        LogUtil.a("SleepDayBarChartProvider", "sync MSG_PROC_TO_REFRESH_CORE_SLEEP");
                        nrq.a().d(100);
                        ObserverManagerUtil.c("SLEEP_SYNC_RATE", 100);
                        sleepDayBarChartProvider.b(false);
                        break;
                }
                return;
            }
            sleepDayBarChartProvider.af();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        qnl.e(this.i, e(), this.d);
        this.ap.e(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        qnl.e(this.i, e(), this.d, this.ap);
        this.ap.e(this.d);
    }

    public void c(boolean z) {
        qmu qmuVar = new qmu();
        qmuVar.b(this.ao);
        qmuVar.d(true);
        qmuVar.a(this.d.j().at());
        qmuVar.b(false);
        qmuVar.c(this.r);
        if (z) {
            qmuVar.c(0);
        }
        b(qmuVar);
        f(true);
        ao();
    }

    private String l() {
        return this.r != null ? nsj.d(e(), this.r.getTime()) : "--";
    }

    public static class e implements IBaseResponseCallback {
        private WeakReference<SleepDayBarChartProvider> e;

        public e(SleepDayBarChartProvider sleepDayBarChartProvider) {
            this.e = new WeakReference<>(sleepDayBarChartProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            WeakReference<SleepDayBarChartProvider> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.a("SleepDayBarChartProvider", "weakReference is null");
                return;
            }
            final SleepDayBarChartProvider sleepDayBarChartProvider = weakReference.get();
            if (sleepDayBarChartProvider == null) {
                LogUtil.a("SleepDayBarChartProvider", "sleepDayBarChartProvider is null");
                return;
            }
            if (i != 0 || obj == null) {
                return;
            }
            LogUtil.a("SleepDayBarChartProvider", "errorCode: " + i);
            if (koq.e(obj, fdp.class)) {
                final List list = (List) obj;
                LogUtil.a("SleepDayBarChartProvider", "onResponse: ", list.toString());
                LogUtil.a("SleepDayBarChartProvider", "size: ", Integer.valueOf(list.size()));
                if (list.size() == 3) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: qni
                        @Override // java.lang.Runnable
                        public final void run() {
                            SleepDayBarChartProvider.this.d((List<fdp>) list);
                        }
                    });
                }
            }
        }
    }

    static class j implements HiDataReadResultListener {
        private WeakReference<SleepDayBarChartProvider> d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public j(SleepDayBarChartProvider sleepDayBarChartProvider) {
            this.d = new WeakReference<>(sleepDayBarChartProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("SleepDayBarChartProvider", "read wakeup feeling");
            WeakReference<SleepDayBarChartProvider> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.a("SleepDayBarChartProvider", "weakReference is null");
                return;
            }
            SleepDayBarChartProvider sleepDayBarChartProvider = weakReference.get();
            if (sleepDayBarChartProvider != null) {
                int dGe_ = sleepDayBarChartProvider.dGe_((SparseArray) obj);
                if (sleepDayBarChartProvider.d == null || sleepDayBarChartProvider.d.l() == null || dGe_ == -1) {
                    return;
                }
                qnl.d(sleepDayBarChartProvider.i, "WAKEUP_FEELING", Integer.valueOf(dGe_));
                ObserverManagerUtil.c(ObserveLabels.REFRESH_REPORT_BEAN, Integer.valueOf(dGe_));
            }
        }
    }

    static class f implements HiSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private long f10227a;
        private long b;
        private WeakReference<SleepDayBarChartProvider> d;
        private List<Integer> e;

        public f(SleepDayBarChartProvider sleepDayBarChartProvider, long j, long j2) {
            this.d = new WeakReference<>(sleepDayBarChartProvider);
            this.f10227a = j;
            this.b = j2;
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a("SleepDayBarChartProvider", "successList: " + list + ", failList: " + list2);
            this.e = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            WeakReference<SleepDayBarChartProvider> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.a("SleepDayBarChartProvider", "weakReference is null");
                return;
            }
            SleepDayBarChartProvider sleepDayBarChartProvider = weakReference.get();
            if (sleepDayBarChartProvider != null) {
                if (sleepDayBarChartProvider.r == null || !TimeUtil.b(sleepDayBarChartProvider.r.getTime(), System.currentTimeMillis())) {
                    return;
                }
                qnl.e(this.f10227a, this.b, 3, new j(sleepDayBarChartProvider));
                return;
            }
            LogUtil.a("SleepDayBarChartProvider", "sleepDayBarChartProvider is null");
        }
    }
}

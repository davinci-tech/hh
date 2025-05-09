package com.huawei.ui.main.stories.fitness.activity.step;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.section.view.CustomH5ProWebview;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthNestedScrollView;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.view.KakaClaimAnimation;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity;
import com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity;
import com.huawei.ui.main.stories.fitness.interactors.FitnessStepDetailInteractor;
import com.huawei.ui.main.stories.fitness.views.base.chart.DaySingleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ICustomMotionTypeCalculator;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.UserEvent;
import com.huawei.ui.main.stories.fitness.views.fitnessdata.StepSourceAdapter;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.dss;
import defpackage.gso;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.koq;
import defpackage.mde;
import defpackage.met;
import defpackage.nhj;
import defpackage.nip;
import defpackage.nrn;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.pvk;
import defpackage.pvn;
import defpackage.pwb;
import defpackage.pwe;
import defpackage.pwm;
import defpackage.pxp;
import defpackage.pxy;
import defpackage.qak;
import defpackage.rud;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FitnessStepDetailActivity extends BaseStepDetailActivity implements StepSourceAdapter.OnStepSourceItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final Context f9897a;
    private int aa;
    private HealthRecycleView ab;
    private LinearLayout ac;
    private BaseStepDetailActivity.TextShowFormatter ad;
    private final j ae;
    private final int[] af;
    private ProportionView.ProportionItem ag;
    private Handler ah;
    private int ai;
    private ProportionView.ProportionItem b;
    private Boolean c;
    private Date d;
    private double e;
    private HealthOpenSDK f;
    private Date g;
    private CustomH5ProWebview h;
    private int i;
    private pvn j;
    private boolean k;
    private boolean l;
    private RelativeLayout m;
    private FitnessStepDetailInteractor n;
    private KakaClaimAnimation o;
    private ProportionView.ProportionItem p;
    private IBaseResponseCallback q;
    private List<ProportionView.ProportionItem> r;
    private Handler s;
    private ProportionView t;
    private long u;
    private DaySingleViewDataObserverView v;
    private StepSourceAdapter w;
    private IBaseResponseCallback x;
    private List<pwe> y;
    private BroadcastReceiver z;

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_sport_step;
    }

    public FitnessStepDetailActivity() {
        int[] iArr = new int[2];
        iArr[0] = R$string.IDS_privacy_all_data;
        iArr[1] = Utils.o() ? R$string.IDS_fitness_step_description : R$string.IDS_knowledge;
        this.af = iArr;
        this.f9897a = BaseApplication.e();
        this.ae = new j(this);
        this.i = 0;
        this.ai = 3;
        this.s = new i();
        this.n = new FitnessStepDetailInteractor(this);
        this.t = null;
        this.r = new ArrayList();
        this.p = null;
        this.ag = null;
        this.b = null;
        this.e = 0.0d;
        this.l = false;
        this.g = new Date();
        this.j = new pvn(this);
        this.ah = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    return;
                }
                super.handleMessage(message);
                if (message.what == 100 && FitnessStepDetailActivity.this.ai == 3) {
                    if (FitnessStepDetailActivity.this.d == null) {
                        FitnessStepDetailActivity.this.d = new Date(System.currentTimeMillis());
                    }
                    FitnessStepDetailActivity.this.e();
                    pvk.a((float) FitnessStepDetailActivity.this.e, FitnessStepDetailActivity.this.d.getTime());
                }
            }
        };
        this.ad = new BaseStepDetailActivity.TextShowFormatter() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.4
            @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.TextShowFormatter
            public String convertFloat2TextShow(float f2) {
                return UnitUtil.e(f2, 1, 0);
            }
        };
        this.c = null;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
        this.mScrollView = (HealthNestedScrollView) findViewById(R.id.fitness_scroll_view);
        if (nhj.n()) {
            h();
        }
        b();
        g();
        ScrollUtil.cKx_(this.mScrollView, getWindow().getDecorView(), 3021);
        jcf.bEj_(this.ae);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.l = false;
    }

    private void h() {
        i();
        final long currentTimeMillis = System.currentTimeMillis();
        c(currentTimeMillis);
        HandlerCenter.d().e(new Runnable() { // from class: pvg
            @Override // java.lang.Runnable
            public final void run() {
                FitnessStepDetailActivity.e(currentTimeMillis);
            }
        }, 500L);
    }

    public static /* synthetic */ void e(long j2) {
        ReleaseLogUtil.e("SCUI_FitnessStepDetailActivity", "initSummary synCloud syncTime ", Long.valueOf(j2));
        ArrayList arrayList = new ArrayList();
        arrayList.add(2);
        arrayList.add(40002);
        nhj.b(arrayList, j2);
    }

    private void i() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.h = (CustomH5ProWebview) findViewById(R.id.health_summary_web_view);
        bzs.e().loadEmbeddedH5(this.h, "com.huawei.health.h5.health-trend", new H5ProLaunchOption.Builder().addPath("#/highLightCardView?domain=step"));
        final int b2 = nsf.b(R.dimen._2131362885_res_0x7f0a0445);
        boolean cKw_ = ScrollUtil.cKw_(this.mScrollView, this.h, b2);
        ReleaseLogUtil.e("SCUI_FitnessStepDetailActivity", "initSummaryView loadEmbeddedH5 time ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime), " isSummaryVisible ", Boolean.valueOf(cKw_));
        if (cKw_) {
            n();
        } else {
            this.mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity$$ExternalSyntheticLambda0
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public final void onScrollChange(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5) {
                    FitnessStepDetailActivity.this.b(b2, nestedScrollView, i2, i3, i4, i5);
                }
            });
        }
    }

    /* synthetic */ void b(int i2, NestedScrollView nestedScrollView, int i3, int i4, int i5, int i6) {
        if (this.k || !ScrollUtil.cKw_(this.mScrollView, this.h, i2)) {
            return;
        }
        n();
    }

    private void n() {
        this.k = true;
        nhj.c("Walk_0001", false);
    }

    private void c(final long j2) {
        this.z = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", "mSyncStatusReceiver receiver intent is null.");
                    return;
                }
                if (!"com.huawei.hihealth.action_sync_data_result".equals(intent.getAction())) {
                    ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", "mSyncStatusReceiver receiver intent.action is error.");
                    return;
                }
                long longExtra = intent.getLongExtra("sync_data_result_id", 0L);
                if (intent.getBooleanExtra("sync_data_result_success", true) && longExtra == j2) {
                    nhj.b("SCUI_FitnessStepDetailActivity", FitnessStepDetailActivity.this.h);
                }
            }
        };
        BroadcastManagerUtil.bFE_(BaseApplication.e(), this.z, new IntentFilter("com.huawei.hihealth.action_sync_data_result"));
        ReleaseLogUtil.e("SCUI_FitnessStepDetailActivity", "registerSyncResultBroadcast");
    }

    private void b() {
        this.mMarketingLayout = (LinearLayout) findViewById(R.id.base_step_marketing);
        cancelLayoutById(this.mMarketingLayout);
        if (this.mMarketingApi != null) {
            this.mMarketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(this).setPageId(10).setLayoutMap(l()).build());
        }
    }

    private void g() {
        setViewSafeRegion(false, this.mMarketingLayout);
        setViewSafeRegion(false, this.h);
    }

    private Map<Integer, LinearLayout> l() {
        HashMap hashMap = new HashMap();
        hashMap.put(4017, this.mMarketingLayout);
        return hashMap;
    }

    private void c() {
        this.n.duG_(this, isStepDetail());
        initStatus();
        if (this.f == null) {
            this.f = dss.c(this).d();
        }
        m();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout instanceof DayStepProgressBarScrollView) {
            ((DayStepProgressBarScrollView) relativeLayout).a();
        }
        pvn pvnVar = this.j;
        if (pvnVar != null) {
            pvnVar.dup_(configuration);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        LogUtil.a("SCUI_FitnessStepDetailActivity", "initViewTahiti");
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("SCUI_FitnessStepDetailActivity", "onDestroy");
        jcf.bEu_(this.ae);
        this.n.e();
        BroadcastManagerUtil.bFK_(BaseApplication.e(), this.z);
        CustomH5ProWebview customH5ProWebview = this.h;
        if (customH5ProWebview != null) {
            customH5ProWebview.d();
        }
        Handler handler = this.s;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.ah;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(this, AnalyticsValue.HEALTH_DETAIL_DAY_SHARE_21300005.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public View getExtensionView() {
        if (!Utils.o()) {
            return LayoutInflater.from(this).inflate(R.layout.step_detail_extension, (ViewGroup) null);
        }
        ProportionView proportionView = this.t;
        if (proportionView != null) {
            return proportionView;
        }
        this.t = new ProportionView(this);
        this.p = new ProportionView.d(this, Color.argb(255, 145, 189, 255));
        this.ag = new ProportionView.c(this, Color.argb(255, 95, 155, 255));
        this.b = new ProportionView.b(this, Color.argb(255, 53, 126, 255));
        this.r.add(this.p);
        this.r.add(this.ag);
        this.r.add(this.b);
        this.t.b(this.r);
        this.t.setSubHeaderText(nsf.b(R$string.IDS_fitness_subheader_title, nsf.h(R$string.IDS_settings_steps)));
        return this.t;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartListener() {
        if (!Utils.o()) {
            this.mClassifiedViewList.b(new IOnDataShowListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.3
                @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
                public void onUserEvent(UserEvent userEvent) {
                }

                @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
                public void onDataShowChanged(DataInfos dataInfos, int i2, int i3, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
                    FitnessStepDetailActivity.this.e(dataInfos, i2, i3, hwHealthBaseBarLineChart);
                    FitnessStepDetailActivity.this.a(dataInfos, i2);
                }
            });
        } else {
            this.mClassifiedViewList.b(new IOnDataShowListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.10
                @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
                public void onUserEvent(UserEvent userEvent) {
                }

                @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
                public void onDataShowChanged(DataInfos dataInfos, int i2, int i3, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
                    FitnessStepDetailActivity.this.e(dataInfos, i2, i3, hwHealthBaseBarLineChart);
                    if (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) {
                        FitnessStepDetailActivity.this.d((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart, dataInfos);
                    }
                    FitnessStepDetailActivity.this.a(dataInfos, i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
        ICustomMotionTypeCalculator c2 = this.mChartLayerHolder.c();
        this.ag.setDataValue((c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.SUM) - c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.RUN)) - c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.CLIMB));
        this.p.setDataValue(c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.RUN));
        this.b.setDataValue(c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.CLIMB));
        this.t.b(this.r);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initStartArguement(Intent intent) {
        if (intent != null && intent.hasExtra("today_current_steps_total")) {
            this.mCurrentDayValueMinimum = intent.getIntExtra("today_current_steps_total", (int) this.mCurrentDayValueMinimum);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initActivityData() {
        j();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartLayerHolderData(IChartLayerHolder iChartLayerHolder) {
        iChartLayerHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.6
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public boolean isAccept(DataInfos dataInfos) {
                if (dataInfos != null) {
                    return dataInfos.isStepData();
                }
                return false;
            }
        }, getString(R$string.IDS_settings_steps_unit));
    }

    private void a() {
        HealthOpenSDK healthOpenSDK = this.f;
        if (healthOpenSDK != null) {
            healthOpenSDK.b((IExecuteResult) new h(this));
            return;
        }
        LogUtil.h("SCUI_FitnessStepDetailActivity", "mHealthOpenSdk ==null ,get data failed!");
        this.ah.sendMessage(this.ah.obtainMessage(100));
    }

    /* loaded from: classes6.dex */
    static class j implements AccessibilityManager.TouchExplorationStateChangeListener {
        private final WeakReference<FitnessStepDetailActivity> b;

        j(FitnessStepDetailActivity fitnessStepDetailActivity) {
            this.b = new WeakReference<>(fitnessStepDetailActivity);
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.b.get();
            if (fitnessStepDetailActivity == null || fitnessStepDetailActivity.isFinishing() || fitnessStepDetailActivity.isDestroyed()) {
                ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", "TouchExplorationStateChangeListener onTouchExplorationStateChanged activity ", fitnessStepDetailActivity);
                return;
            }
            LogUtil.a("SCUI_FitnessStepDetailActivity", "TouchExplorationStateChangeListener onTouchExplorationStateChanged enabled ", Boolean.valueOf(z), " mTargetLayoutVisibility ", Integer.valueOf(fitnessStepDetailActivity.aa), " mStepSingleViewDataObserverView ", fitnessStepDetailActivity.v);
            if (fitnessStepDetailActivity.v == null) {
                return;
            }
            fitnessStepDetailActivity.v.setEditTargetAccessibilityVisibility(fitnessStepDetailActivity.aa);
        }
    }

    /* loaded from: classes6.dex */
    static class h implements IExecuteResult {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<FitnessStepDetailActivity> f9901a;

        protected h(FitnessStepDetailActivity fitnessStepDetailActivity) {
            this.f9901a = null;
            this.f9901a = new WeakReference<>(fitnessStepDetailActivity);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.f9901a.get();
            if (fitnessStepDetailActivity == null) {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "onSuccess shareActivity == null");
                return;
            }
            LogUtil.a("SCUI_FitnessStepDetailActivity", "get today steps success");
            if (obj == null) {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "get today steps success but boj==null");
                fitnessStepDetailActivity.ah.sendMessage(fitnessStepDetailActivity.ah.obtainMessage(100));
            } else if (obj instanceof Bundle) {
                fitnessStepDetailActivity.e = ((Bundle) obj).getInt("step");
                LogUtil.a("SCUI_FitnessStepDetailActivity", "mAverSteps", Double.valueOf(fitnessStepDetailActivity.e));
                fitnessStepDetailActivity.ah.sendMessage(fitnessStepDetailActivity.ah.obtainMessage(100));
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.f9901a.get();
            if (fitnessStepDetailActivity != null) {
                fitnessStepDetailActivity.ah.sendMessage(fitnessStepDetailActivity.ah.obtainMessage(100));
            } else {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "onFailed shareActivity == null");
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.f9901a.get();
            if (fitnessStepDetailActivity != null) {
                fitnessStepDetailActivity.ah.sendMessage(fitnessStepDetailActivity.ah.obtainMessage(100));
            } else {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "onServiceException shareActivity == null");
            }
        }
    }

    private void c(Context context, Date date) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        int b2 = DateFormatUtil.b(date.getTime());
        hiAggregateOption.setTimeInterval(String.valueOf(b2), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(new int[]{40002});
        hiAggregateOption.setConstantsKey(new String[]{ParsedFieldTag.STEP_SUM});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        HiHealthNativeApi.a(context).aggregateHiHealthData(hiAggregateOption, new c(this));
    }

    /* loaded from: classes6.dex */
    static class c implements HiAggregateListener {
        WeakReference<FitnessStepDetailActivity> e;

        protected c(FitnessStepDetailActivity fitnessStepDetailActivity) {
            this.e = new WeakReference<>(fitnessStepDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.e.get();
            if (fitnessStepDetailActivity == null) {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "shareActivity == null");
                return;
            }
            if (list != null && !list.isEmpty()) {
                FitnessStepDetailActivity.c(list, fitnessStepDetailActivity);
                return;
            }
            LogUtil.h("SCUI_FitnessStepDetailActivity", "getDailySportDataWithDataBase ReadStaticDealCallback onResult data is null");
            fitnessStepDetailActivity.ah.sendMessage(fitnessStepDetailActivity.ah.obtainMessage(100));
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.e.get();
            if (fitnessStepDetailActivity == null) {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "shareActivity == null");
                return;
            }
            LogUtil.a("SCUI_FitnessStepDetailActivity", "onResultIntent ", Integer.valueOf(i), list, Integer.valueOf(i2), Integer.valueOf(i3));
            fitnessStepDetailActivity.ah.sendMessage(fitnessStepDetailActivity.ah.obtainMessage(100));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(List<HiHealthData> list, FitnessStepDetailActivity fitnessStepDetailActivity) {
        double d2 = list.get(0).getInt(ParsedFieldTag.STEP_SUM);
        fitnessStepDetailActivity.e = d2;
        LogUtil.a("SCUI_FitnessStepDetailActivity", "getDailySportDataWithDataBase mAverSteps", Double.valueOf(d2));
        fitnessStepDetailActivity.ah.sendMessage(fitnessStepDetailActivity.ah.obtainMessage(100));
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initTitleBar() {
        this.mTitleBar.setTitleText(getString(R$string.IDS_settings_steps));
        this.mTitleBar.setRightSoftkeyBackground(nsf.cKq_(LanguageUtil.bc(this.f9897a) ? R.drawable._2131430036_res_0x7f0b0a94 : R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R$string.accessibility_share));
        this.mTitleBar.setRightSoftkeyVisibility(0);
        this.mTitleBar.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.a(500)) {
                    FitnessStepDetailActivity.this.k();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        ArrayList<String> arrayList = new ArrayList<>(this.af.length);
        for (int i2 : this.af) {
            arrayList.add(getString(i2));
        }
        setTitleBarRightButton(this, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("SCUI_FitnessStepDetailActivity", "mCurrentDay=", Long.valueOf(this.d.getTime()), " System.currentTimeMillis()=", Long.valueOf(System.currentTimeMillis()));
        this.e = 0.0d;
        if (jdl.ac(this.d.getTime())) {
            a();
        } else {
            c(getApplicationContext(), this.d);
        }
    }

    public static Date a(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return calendar.getTime();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public BaseStepDetailActivity.TextShowFormatter getTextShowFormatter() {
        return this.ad;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateDayClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateDayClassifiedView = super.onCreateDayClassifiedView(textShowFormatter);
        enableStepClassifiedViewObserver(onCreateDayClassifiedView);
        onCreateDayClassifiedView.setObserverContainerBackgroundColor(ContextCompat.getColor(this.f9897a, R.color._2131296971_res_0x7f0902cb));
        ScrollChartObserverView acquireScrollChartObserverView = onCreateDayClassifiedView.acquireScrollChartObserverView();
        if (!(acquireScrollChartObserverView instanceof DaySingleViewDataObserverView)) {
            return onCreateDayClassifiedView;
        }
        DaySingleViewDataObserverView daySingleViewDataObserverView = (DaySingleViewDataObserverView) acquireScrollChartObserverView;
        this.v = daySingleViewDataObserverView;
        this.mScrollChartObserverView = daySingleViewDataObserverView.a();
        this.m = this.v.dvw_();
        this.ac = this.v.getSourceLayout();
        this.ab = this.v.e();
        f();
        if (!(this.mScrollChartObserverView instanceof ScrollChartObserverTotalDataView)) {
            return onCreateDayClassifiedView;
        }
        ((ScrollChartObserverTotalDataView) this.mScrollChartObserverView).b(new ScrollChartObserverTotalDataView.b(this.mCurrentDayValueMinimum, 40002, DataInfos.query(getClassType(), DateType.DATE_DAY)));
        o();
        return onCreateDayClassifiedView;
    }

    private void m() {
        if (!HuaweiLoginManager.checkIsInstallHuaweiAccount(this.f9897a)) {
            ReleaseLogUtil.e("SCUI_FitnessStepDetailActivity", "jumpToDeviceManagementPage not install hmscore");
            this.v.getSourceManagement().setVisibility(8);
        } else {
            this.v.getSourceManagement().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwid://com.huawei.hwid/DeviceManager"));
                        intent.setPackage(HMSPackageManager.getInstance(FitnessStepDetailActivity.this.f9897a).getHMSPackageName());
                        FitnessStepDetailActivity.this.startActivityForResult(intent, 0);
                    } catch (Exception e2) {
                        LogUtil.b("SCUI_FitnessStepDetailActivity", "exception getClassName: ", e2.getClass().getSimpleName());
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 2000 && i3 == -1) {
            int intExtra = intent.getIntExtra("StepGoal", 0);
            LogUtil.a("SCUI_FitnessStepDetailActivity", "Target Activity Result StepGoal=", Integer.valueOf(intExtra));
            RelativeLayout relativeLayout = this.m;
            if (relativeLayout instanceof DayStepProgressBarScrollView) {
                ((DayStepProgressBarScrollView) relativeLayout).e(intExtra);
            }
            d(intExtra);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateWeekClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateWeekClassifiedView = super.onCreateWeekClassifiedView(textShowFormatter);
        enableWeekMonthStepClassifiedViewObserver(onCreateWeekClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_step_data_title), getString(R$string.IDS_settings_steps_unit), getString(R$string.IDS_fitness_average_step_data_title), getString(R$string.IDS_settings_steps_unit)));
        return onCreateWeekClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateMonthClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateMonthClassifiedView = super.onCreateMonthClassifiedView(textShowFormatter);
        enableWeekMonthStepClassifiedViewObserver(onCreateMonthClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_step_data_title), getString(R$string.IDS_settings_steps_unit), getString(R$string.IDS_fitness_average_step_data_title), getString(R$string.IDS_settings_steps_unit)));
        return onCreateMonthClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateYearClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateYearClassifiedView = super.onCreateYearClassifiedView(textShowFormatter);
        enableStepClassifiedViewObserver(onCreateYearClassifiedView, new HwHealthBaseScrollBarLineChart.DataRatioProvider() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.8
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.DataRatioProvider
            public float computeRatio(int i2) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(i2 * 60000);
                calendar.set(5, 1);
                calendar.roll(5, -1);
                return calendar.get(5);
            }
        }, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_step_data_title), getString(R$string.IDS_settings_steps_unit), getString(R$string.IDS_fitness_average_step_data_title), getString(R$string.IDS_settings_steps_unit)));
        return onCreateYearClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ClassType getClassType() {
        return ClassType.TYPE_STEP;
    }

    /* loaded from: classes6.dex */
    static class i extends Handler {
        WeakReference<FitnessStepDetailActivity> b;

        private i(FitnessStepDetailActivity fitnessStepDetailActivity) {
            this.b = new WeakReference<>(fitnessStepDetailActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
            }
            super.handleMessage(message);
            FitnessStepDetailActivity fitnessStepDetailActivity = this.b.get();
            if (fitnessStepDetailActivity == null) {
                return;
            }
            switch (message.what) {
                case 103:
                    fitnessStepDetailActivity.a(8);
                    break;
                case 104:
                    fitnessStepDetailActivity.e(message.obj);
                    break;
                case 105:
                    fitnessStepDetailActivity.c(message.obj);
                    break;
                case 106:
                    fitnessStepDetailActivity.a(message.obj);
                    break;
                default:
                    LogUtil.h("SCUI_FitnessStepDetailActivity", "RequestHandler handleMessage default");
                    break;
            }
        }
    }

    private void j() {
        long t = jdl.t(System.currentTimeMillis());
        this.u = t;
        this.d = a(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DataInfos dataInfos, int i2, int i3, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        int i4;
        long j2 = i2 * 60000;
        if (!dataInfos.isDayData() && (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart)) {
            hwHealthBaseBarLineChart.enableManualReferenceLine(this.i, nrn.d(R.color._2131299141_res_0x7f090b45));
            ((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart).reCalculateDynamicBoardForManualRefLine();
        }
        if (dataInfos.isMonthData()) {
            this.mTitleBar.setRightSoftkeyVisibility(8);
            c(8);
            i4 = 5;
        } else if (dataInfos.isYearData()) {
            this.mTitleBar.setRightSoftkeyVisibility(8);
            c(8);
            i4 = 6;
        } else if (dataInfos.isWeekData()) {
            this.mTitleBar.setRightSoftkeyVisibility(8);
            c(8);
            i4 = 4;
        } else {
            this.mTitleBar.setRightSoftkeyVisibility(0);
            this.d = a(j2);
            c(0);
            if (jdl.ac(j2)) {
                this.v.setTipVisibility(0);
                RelativeLayout relativeLayout = this.m;
                if (relativeLayout instanceof DayStepProgressBarScrollView) {
                    ((DayStepProgressBarScrollView) relativeLayout).setKakaListLayoutVisibility(0);
                }
            } else {
                this.v.setTipVisibility(8);
                RelativeLayout relativeLayout2 = this.m;
                if (relativeLayout2 instanceof DayStepProgressBarScrollView) {
                    ((DayStepProgressBarScrollView) relativeLayout2).setKakaListLayoutVisibility(8);
                }
            }
            i4 = 3;
        }
        this.u = j2;
        this.ai = i4;
    }

    private void c(int i2) {
        DaySingleViewDataObserverView daySingleViewDataObserverView = this.v;
        if (daySingleViewDataObserverView == null) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "setVisibilityForTargetLayout mStepSingleViewDataObserverView is null visibility ", Integer.valueOf(i2));
            return;
        }
        this.aa = i2;
        daySingleViewDataObserverView.setTargetLayoutVisibility(i2);
        this.v.setEditTargetAccessibilityVisibility(i2);
    }

    private void o() {
        LogUtil.a("SCUI_FitnessStepDetailActivity", "onCustomChartStyle enter ");
        nip.d("900200006", new f());
    }

    /* loaded from: classes6.dex */
    static class f implements IBaseResponseCallback {
        private WeakReference<FitnessStepDetailActivity> b;

        private f(FitnessStepDetailActivity fitnessStepDetailActivity) {
            this.b = new WeakReference<>(fitnessStepDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "SportAchievementGoalDataCallback ";
            objArr[1] = obj == null ? null : obj.toString();
            LogUtil.a("SCUI_FitnessStepDetailActivity", objArr);
            FitnessStepDetailActivity fitnessStepDetailActivity = this.b.get();
            if (fitnessStepDetailActivity == null) {
                ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", " SportAchievementGoalDataCallback stepDetailActivity == null ");
                return;
            }
            Message obtain = Message.obtain();
            obtain.obj = obj;
            obtain.what = 106;
            fitnessStepDetailActivity.s.sendMessageDelayed(obtain, 1000L);
        }
    }

    public void a(int i2, String str, int i3) {
        if (this.l) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "kaka button click too fast");
            return;
        }
        Task<mde> claimKakaTasksByScenario = bzw.e().claimKakaTasksByScenario(this.f9897a, i3, str);
        if (claimKakaTasksByScenario == null) {
            ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", "updataKaka updateTaskKaka is null");
        } else {
            this.l = true;
            claimKakaTasksByScenario.addOnSuccessListener(new e(i2)).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity.2
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    FitnessStepDetailActivity.this.l = false;
                    LogUtil.h("SCUI_FitnessStepDetailActivity", "updateTaskKaka == null");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        if (this.o == null) {
            d();
        }
        KakaClaimAnimation kakaClaimAnimation = this.o;
        if (kakaClaimAnimation != null) {
            kakaClaimAnimation.setVisibility(0);
            this.o.e(i2);
            RelativeLayout relativeLayout = this.m;
            if (relativeLayout instanceof DayStepProgressBarScrollView) {
                ((DayStepProgressBarScrollView) relativeLayout).b();
                return;
            }
            return;
        }
        LogUtil.h("SCUI_FitnessStepDetailActivity", "processKakaSuccess failed with null mKakaClick.");
    }

    private void d() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.step_kaka_convert_anim);
        if (viewStub == null) {
            LogUtil.a("SCUI_FitnessStepDetailActivity", "initConvertAnimLayout ViewStub is loaded fail.");
            return;
        }
        View inflate = viewStub.inflate();
        if (inflate instanceof KakaClaimAnimation) {
            this.o = (KakaClaimAnimation) inflate;
        }
    }

    /* loaded from: classes6.dex */
    static class e implements OnSuccessListener<mde> {

        /* renamed from: a, reason: collision with root package name */
        private int f9900a;
        private WeakReference<FitnessStepDetailActivity> c;

        private e(FitnessStepDetailActivity fitnessStepDetailActivity, int i) {
            this.c = new WeakReference<>(fitnessStepDetailActivity);
            this.f9900a = i;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(mde mdeVar) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.c.get();
            if (fitnessStepDetailActivity != null) {
                fitnessStepDetailActivity.l = false;
                if (mdeVar == null) {
                    ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", "kakaUpdateReturnBody == null");
                    return;
                }
                String d = mdeVar.d();
                ReleaseLogUtil.e("SCUI_FitnessStepDetailActivity", "showKakaAnimation resultCode ", d);
                if ("0".equals(d)) {
                    fitnessStepDetailActivity.b(this.f9900a);
                    return;
                } else {
                    LogUtil.h("SCUI_FitnessStepDetailActivity", "kaka Exchange failed.");
                    return;
                }
            }
            LogUtil.h("SCUI_FitnessStepDetailActivity", "fitnessStepDetailActivity is null");
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueMinimum() {
        return this.mScrollChartObserverView.getContentTextView().getText().toString();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueUnit() {
        return getString(R$string.IDS_settings_steps_unit);
    }

    public void b(String str) {
        DaySingleViewDataObserverView daySingleViewDataObserverView = this.v;
        if (daySingleViewDataObserverView != null) {
            daySingleViewDataObserverView.setTipText(str);
        }
    }

    public void d(int i2, int i3) {
        DaySingleViewDataObserverView daySingleViewDataObserverView = this.v;
        if (daySingleViewDataObserverView == null) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "updateTargetProgress mStepSingleViewDataObserverView is null value ", Integer.valueOf(i2), " target ", Integer.valueOf(i3));
            return;
        }
        updateTargetProgress(daySingleViewDataObserverView.getTargetTextView(), i2, i3, R.plurals._2130903359_res_0x7f03013f);
        if (this.c == null) {
            this.c = Boolean.valueOf(met.a(AchieveMedalDialogScenario.STEPS));
            LogUtil.a("SCUI_FitnessStepDetailActivity", "updateTargetProgress: isShowStepsMedalDialog -> " + this.c);
        }
        if (this.j == null || this.c.booleanValue()) {
            return;
        }
        LogUtil.a("SCUI_FitnessStepDetailActivity", "updateTargetProgress mStartTime", Long.valueOf(this.u));
        this.j.b(this.u);
        this.j.e(i3, i2);
    }

    private void f() {
        this.ab.setLayoutManager(new HealthLinearLayoutManager(this.f9897a));
        this.y = new ArrayList(10);
        StepSourceAdapter stepSourceAdapter = new StepSourceAdapter(this.f9897a, this.y);
        this.w = stepSourceAdapter;
        stepSourceAdapter.a(this);
        this.ab.setAdapter(this.w);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.fitnessdata.StepSourceAdapter.OnStepSourceItemClickListener
    public void onItemClick(int i2) {
        if (koq.b(this.y, i2)) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "onItemClick position is out of mStepSourceItemList.");
            return;
        }
        String c2 = this.y.get(i2).c();
        boolean b2 = this.y.get(i2).b();
        LogUtil.a("SCUI_FitnessStepDetailActivity", "onItemClick position is ", Integer.valueOf(i2), " steps ", c2, " hasPointStep ", Boolean.valueOf(b2));
        if (TextUtils.isEmpty(c2) || !b2) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "onItemClick current device is not point steps data");
            return;
        }
        Intent intent = new Intent(this.f9897a, (Class<?>) DayStepSourceListActivity.class);
        intent.putExtra("device_id", this.y.get(i2).a());
        intent.putExtra("point_step_time", this.g.getTime());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DataInfos dataInfos, int i2) {
        if (!dataInfos.isDayData()) {
            a(8);
            LogUtil.h("SCUI_FitnessStepDetailActivity", "getCurrentDayStepSource current page is not day view");
            return;
        }
        long t = jdl.t(i2 * 60000);
        Date date = new Date(t);
        if (!jdl.d(this.g.getTime(), date.getTime()) || jdl.ac(this.g.getTime())) {
            this.g = date;
            LogUtil.a("SCUI_FitnessStepDetailActivity", "getCurrentDayStepSource mDayViewCurrentDay is ", DateFormatUtil.a(date.getTime(), DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
            pwm.a().c(t, new a());
        }
    }

    /* loaded from: classes6.dex */
    static class a implements CommonUiBaseResponse {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<FitnessStepDetailActivity> f9899a;

        private a(FitnessStepDetailActivity fitnessStepDetailActivity) {
            this.f9899a = new WeakReference<>(fitnessStepDetailActivity);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.f9899a.get();
            if (fitnessStepDetailActivity == null) {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "fitnessStepDetailActivity is null");
                return;
            }
            if (obj == null) {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "getFitnessStepDataOrigin objData is null");
                fitnessStepDetailActivity.s.sendEmptyMessage(103);
            } else {
                Message obtain = Message.obtain();
                obtain.what = 104;
                obtain.obj = obj;
                fitnessStepDetailActivity.s.sendMessage(obtain);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj) {
        if (!koq.e(obj, pwb.class)) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "showStepSourceList obj not instanceof List");
            a(8);
            return;
        }
        List<pwb> list = (List) obj;
        if (list.size() == 0) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "showStepSourceList stepSourceList content is empty");
            a(8);
            return;
        }
        a(0);
        this.y.clear();
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        ArrayList arrayList3 = new ArrayList();
        for (pwb pwbVar : list) {
            pwe pweVar = new pwe();
            pweVar.d(pwbVar.e());
            pweVar.c(qak.a(pwbVar.c(), pwbVar.d()));
            pweVar.e(TextUtils.isEmpty(pwbVar.a()) ? pwbVar.d() : pwbVar.a());
            pweVar.a(a(pwbVar.e()));
            pweVar.a("");
            if (pwbVar.c() == 32) {
                arrayList.add(pweVar);
            } else {
                arrayList2.add(pweVar);
            }
            arrayList3.add(pwbVar.e());
        }
        this.y.addAll(arrayList);
        this.y.addAll(arrayList2);
        this.w.notifyDataSetChanged();
        LogUtil.a("SCUI_FitnessStepDetailActivity", "showStepSourceList mDayViewCurrentDay is ", this.g);
        HashMap hashMap = new HashMap(16);
        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
            hashMap.put(Integer.valueOf(i2), (String) arrayList3.get(i2));
        }
        e((Map<Integer, String>) hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        this.ac.setVisibility(i2);
        this.ab.setVisibility(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        int intValue;
        if (!(obj instanceof Integer)) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "setRefreshGaolText is not instanceof HashMap");
            intValue = 10000;
        } else {
            intValue = ((Integer) obj).intValue();
        }
        d(intValue);
    }

    private void d(int i2) {
        this.v.setEditTargetOnClickListener(new View.OnClickListener() { // from class: pvf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessStepDetailActivity.this.dui_(view);
            }
        });
        this.i = i2;
    }

    public /* synthetic */ void dui_(View view) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        AppRouter.b("/HWUserProfileMgr/MyTargetActivity").c("from", 5).zD_(this, 2000);
        ixx.d().d(this.f9897a, AnalyticsValue.HEALTH_MINE_TARGET_2040004.value(), hashMap, 0);
        pxp.d(5, 1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(int i2) {
        if (this.mCurrentDayValueMinimum > i2) {
            this.v.setTipButton(nsf.h(R$string.IDS_fitness_share_now), new View.OnClickListener() { // from class: pvi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessStepDetailActivity.this.dug_(view);
                }
            });
            return;
        }
        this.v.setTipButton(nsf.h(R$string.IDS_fitness_walk), new View.OnClickListener() { // from class: pve
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessStepDetailActivity.this.duh_(view);
            }
        });
        if (this.i != i2) {
            pxp.b(0, 1, 0, null);
        }
    }

    public /* synthetic */ void dug_(View view) {
        k();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void duh_(View view) {
        pxp.b(1, 1, 0, null);
        int c2 = gso.e().c(0, 257, -1, -1.0f, null, this.f9897a);
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.f9897a, AnalyticsValue.HEALTH_TAKE_WALK_NOW_2040230.value(), hashMap, 0);
        if (c2 == 0) {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(Map<Integer, String> map) {
        LogUtil.a("SCUI_FitnessStepDetailActivity", "getDayTotalStepDataById deviceIdMap size is ", Integer.valueOf(map.size()));
        d dVar = new d(map);
        this.q = dVar;
        this.n.d(2, map, this.g, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<String, Integer> map, Map<Integer, String> map2) {
        b bVar = new b(map, map2);
        this.x = bVar;
        this.n.d(901, map2, this.g, bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, Map<Integer, String> map) {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            e(map, hashMap, it.next(), hashMap2);
        }
        LogUtil.a("SCUI_FitnessStepDetailActivity", "handlePointStepData needQueryDeviceStepMap ", hashMap2.toString());
        if (hashMap2.isEmpty()) {
            d(hashMap);
        } else {
            d(hashMap, hashMap2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, Map<Integer, String> map, Map<String, Integer> map2) {
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            c(map, map2, it.next());
        }
        LogUtil.a("SCUI_FitnessStepDetailActivity", "handleSingleDeviceStepData stepsMap ", map2.toString());
        d(map2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<String, Integer> map) {
        if (map.isEmpty()) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "refreshStepSourceList stepsMap.isEmpty ");
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = map;
        obtain.what = 105;
        this.s.sendMessage(obtain);
    }

    private void e(Map<Integer, String> map, Map<String, Integer> map2, HiHealthData hiHealthData, Map<Integer, String> map3) {
        int i2 = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int h2 = CommonUtil.h(hiHealthData.getString("dayStepSum_" + entry.getKey()));
            if (h2 > 0) {
                map2.put(entry.getValue(), Integer.valueOf(h2));
            } else {
                map3.put(Integer.valueOf(i2), entry.getValue());
                i2++;
            }
        }
        for (pwe pweVar : this.y) {
            Integer num = map2.get(pweVar.a());
            pweVar.e(num != null && num.intValue() > 0);
        }
    }

    private void c(Map<Integer, String> map, Map<String, Integer> map2, HiHealthData hiHealthData) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int h2 = CommonUtil.h(hiHealthData.getString("dayStepSum_" + entry.getKey()));
            if (h2 > 0) {
                map2.put(entry.getValue(), Integer.valueOf(h2));
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        if (!(obj instanceof Map)) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "notifyDataSetChangedSourceList obj is not instanceof Map");
            return;
        }
        Map map = (Map) obj;
        for (pwe pweVar : this.y) {
            Integer num = (Integer) map.get(pweVar.a());
            if (num == null || num.intValue() <= 0) {
                pweVar.a("");
            } else {
                pweVar.a(nsf.a(R.plurals._2130903205_res_0x7f0300a5, num.intValue(), UnitUtil.e(num.intValue(), 1, 0)));
            }
        }
        this.w.notifyDataSetChanged();
    }

    private boolean a(String str) {
        String androidId = FoundationCommonUtil.getAndroidId(this.f9897a);
        if (TextUtils.isEmpty(androidId)) {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "showStepSourceList isLocalDevice phoneId is error");
            return false;
        }
        return androidId.equals(str);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void handleTitleBarRightButtonOnClick(int i2) {
        LogUtil.a("SCUI_FitnessStepDetailActivity", "PopViewList setOnClick position is ", Integer.valueOf(i2));
        if (i2 == 0) {
            rud.c(100, this);
        } else if (i2 == 1) {
            pxy.a(this, "/hwtips/topic/health_help_all/%s/SF-10190178_f6174.html?channel=04");
        } else {
            LogUtil.h("SCUI_FitnessStepDetailActivity", "PopViewList setOnClick position is else");
        }
    }

    /* loaded from: classes6.dex */
    static class b implements IBaseResponseCallback {
        private final Map<Integer, String> b;
        private final Map<String, Integer> d;
        private final WeakReference<FitnessStepDetailActivity> e;

        private b(FitnessStepDetailActivity fitnessStepDetailActivity, Map<String, Integer> map, Map<Integer, String> map2) {
            this.e = new WeakReference<>(fitnessStepDetailActivity);
            this.d = map;
            this.b = map2;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.e.get();
            if (fitnessStepDetailActivity == null) {
                ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", "activity is null in InnerSingleDeviceStepCallback");
                return;
            }
            if (!(obj instanceof List)) {
                fitnessStepDetailActivity.d(this.d);
                LogUtil.h("SCUI_FitnessStepDetailActivity", "getSingleDeviceStepDataById objData is not instanceof List");
                return;
            }
            List list = (List) obj;
            if (list.size() == 0) {
                fitnessStepDetailActivity.d(this.d);
                LogUtil.h("SCUI_FitnessStepDetailActivity", "getSingleDeviceStepDataById dataList is empty");
            } else {
                fitnessStepDetailActivity.b(list, this.b, this.d);
            }
        }
    }

    /* loaded from: classes6.dex */
    static class d implements IBaseResponseCallback {
        private final Map<Integer, String> c;
        private final WeakReference<FitnessStepDetailActivity> e;

        private d(FitnessStepDetailActivity fitnessStepDetailActivity, Map<Integer, String> map) {
            this.e = new WeakReference<>(fitnessStepDetailActivity);
            this.c = map;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            FitnessStepDetailActivity fitnessStepDetailActivity = this.e.get();
            if (fitnessStepDetailActivity == null) {
                ReleaseLogUtil.d("SCUI_FitnessStepDetailActivity", "activity is null in InnerSingleDeviceStepCallback");
                return;
            }
            if (!(obj instanceof List)) {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "getDayTotalStepDataById objData is not instanceof List");
                fitnessStepDetailActivity.d(new HashMap(16), this.c);
                return;
            }
            List list = (List) obj;
            if (list.size() != 0) {
                fitnessStepDetailActivity.b((List<HiHealthData>) list, this.c);
            } else {
                LogUtil.h("SCUI_FitnessStepDetailActivity", "getDayTotalStepDataById dataList is empty");
                fitnessStepDetailActivity.d(new HashMap(16), this.c);
            }
        }
    }
}

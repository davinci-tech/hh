package com.huawei.ui.main.stories.fitness.activity.sportintensity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hms.mlsdk.asr.engine.AsrError;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.scrollview.HealthNestedScrollView;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.ActiveTargetActivity;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity;
import com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity;
import com.huawei.ui.main.stories.fitness.common.IChartLayerHolderProvider;
import com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper;
import com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder;
import com.huawei.ui.main.stories.fitness.views.base.chart.DayProgressBarScrollView;
import com.huawei.ui.main.stories.fitness.views.base.chart.DaySingleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.UserEvent;
import defpackage.gso;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nip;
import defpackage.nix;
import defpackage.nrn;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.pxp;
import health.compact.a.CommonUtils;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class FitnessSportIntensityDetailActivity extends BaseStepDetailActivity implements IChartLayerHolderProvider<StepModuleBarChartHolder> {

    /* renamed from: a, reason: collision with root package name */
    private DaySingleViewDataObserverView f9881a;
    private int d;
    private DayProgressBarScrollView f;
    private long h;
    private List<Integer> i;
    private MarketingOption m;
    private int o;
    private final Context c = BaseApplication.e();
    private final AtomicInteger g = new AtomicInteger();
    private final SparseIntArray b = new SparseIntArray();
    private final a l = new a(this);
    private SportIntensityDataInteractor e = new SportIntensityDataInteractor(this);
    private BaseStepDetailActivity.TextShowFormatter j = new BaseStepDetailActivity.TextShowFormatter() { // from class: com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity.1
        @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.TextShowFormatter
        public String convertFloat2TextShow(float f) {
            return UnitUtil.e(f, 1, 0);
        }
    };

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_sport_intensity;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public int getContentView() {
        return R.layout.activity_time_strength_detail_layout;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public boolean isStepDetail() {
        return false;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initStartArguement(Intent intent) {
        if (intent == null) {
            LogUtil.b("Step_FitnessSportIntensityDetailActivity", "intent is null");
        } else if (intent.hasExtra("today_current_middle_and_high_total")) {
            this.mCurrentDayValueMinimum = intent.getIntExtra("today_current_middle_and_high_total", (int) this.mCurrentDayValueMinimum);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(7);
        if (this.mChartLayerHolder.acquireStorageHelper() instanceof StepModuleChartStorageHelper) {
            ((StepModuleChartStorageHelper) this.mChartLayerHolder.acquireStorageHelper()).e(arrayList);
        }
        d();
        a();
        this.mScrollView = (HealthNestedScrollView) findViewById(R.id.intensity_scroll_view);
        ScrollUtil.cKx_(this.mScrollView, getWindow().getDecorView(), AsrError.SERVICE_OUT_CREDIT);
        c();
        h();
        j();
        ArrayList arrayList2 = new ArrayList(1);
        arrayList2.add(103);
        HiHealthNativeApi.a(this.c).subscribeHiHealthData(arrayList2, new c(this));
        jcf.bEj_(this.l);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        MarketingOption marketingOption = this.m;
        if (marketingOption != null) {
            marketingOption.cancel();
        }
        super.onDestroy();
        ReleaseLogUtil.e("Step_FitnessSportIntensityDetailActivity", "onDestroy");
        jcf.bEu_(this.l);
        if (koq.c(this.i)) {
            HiHealthNativeApi.a(this.c).unSubscribeHiHealthData(this.i, new HiUnSubscribeListener() { // from class: puy
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("Step_FitnessSportIntensityDetailActivity", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z));
                }
            });
        }
    }

    private void c() {
        this.mMarketingLayout = (LinearLayout) findViewById(R.id.intensity_marketing_layout);
        BaseActivity.cancelLayoutById(this.mMarketingLayout);
        if (this.mMarketingApi != null) {
            this.m = new MarketingOption.Builder().setContext(this).setPageId(11).setLayoutMap(f()).build();
            this.mMarketingApi.requestMarketingResource(this.m);
        }
    }

    private void h() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.view_proportionSubHeader_title);
        HealthCardView healthCardView = (HealthCardView) findViewById(R.id.card_view_detail_data);
        HealthCardView healthCardView2 = (HealthCardView) findViewById(R.id.card_view_what_sports);
        setViewSafeRegion(true, healthTextView);
        setViewSafeRegion(true, healthCardView);
        setViewSafeRegion(true, healthCardView2);
        setViewSafeRegion(false, this.mMarketingLayout);
    }

    private Map<Integer, LinearLayout> f() {
        HashMap hashMap = new HashMap();
        hashMap.put(4018, this.mMarketingLayout);
        return hashMap;
    }

    private void j() {
        HashMap hashMap = new HashMap();
        hashMap.put("intensity_total_time", Integer.valueOf((int) this.mCurrentDayValueMinimum));
        this.mMarketingApi.triggerMarketingResourceEvent(new MarketingOption.Builder().setContext(this).setPageId(11).setTypeId(100).setTriggerEventParams(hashMap).build());
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartLayerHolderData(IChartLayerHolder iChartLayerHolder) {
        iChartLayerHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity.2
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public boolean isAccept(DataInfos dataInfos) {
                if (dataInfos != null) {
                    return dataInfos.isTimeStrengthData();
                }
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "isAccept failed, dataInfos is null");
                return false;
            }
        }, getString(R$string.IDS_hw_show_main_home_page_minutes));
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initSportIntensityData() {
        this.e.dtU_(this);
        e();
        b();
        i();
    }

    private void i() {
        Intent intent = new Intent(this, (Class<?>) DaemonService.class);
        intent.setAction("event_manual_ui");
        try {
            startService(intent);
        } catch (Exception e) {
            LogUtil.b("Step_FitnessSportIntensityDetailActivity", "update Sport Intensity calc", LogAnonymous.b((Throwable) e));
        }
    }

    protected void b() {
        LogUtil.a("Step_FitnessSportIntensityDetailActivity", " requestDayData ");
        this.e.a(jdl.t(System.currentTimeMillis()), jdl.e(System.currentTimeMillis()), 3);
    }

    private void d() {
        if (LanguageUtil.bc(this)) {
            ((ImageView) findViewById(R.id.fitness_detail_second_type_iv)).setBackground(nrz.cKn_(this, R.mipmap._2131820917_res_0x7f110175));
            ((ImageView) findViewById(R.id.fitness_detail_first_type_iv)).setBackground(nrz.cKn_(this, R.mipmap._2131820918_res_0x7f110176));
            ((ImageView) findViewById(R.id.fitness_detail_third_type_iv)).setBackground(nrz.cKn_(this, R.mipmap._2131820915_res_0x7f110173));
            ((ImageView) findViewById(R.id.fitness_detail_fourth_type_iv)).setBackground(nrz.cKn_(this, R.mipmap._2131821135_res_0x7f11024f));
            ((ImageView) findViewById(R.id.fitness_detail_five_type_iv)).setBackground(nrz.cKn_(this, R.mipmap._2131820916_res_0x7f110174));
        }
    }

    private void e() {
        this.mClassifiedViewList.b(new IOnDataShowListener() { // from class: com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity.5
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onUserEvent(UserEvent userEvent) {
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onDataShowChanged(DataInfos dataInfos, int i, int i2, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
                int i3;
                if (dataInfos == null) {
                    LogUtil.h("Step_FitnessSportIntensityDetailActivity", "onDataShowChanged failed, dataType is null");
                    return;
                }
                LogUtil.a("Step_FitnessSportIntensityDetailActivity", "onDataShowChanged，startX = ", Integer.valueOf(i), ",endX = ", Integer.valueOf(i2));
                long j = i;
                long j2 = j * 60000;
                long j3 = (i2 * 60000) - 1;
                LogUtil.a("Step_FitnessSportIntensityDetailActivity", "startTime ", Long.valueOf(j2), "endTime ", Long.valueOf(j3));
                if (dataInfos.isMonthData()) {
                    i3 = 5;
                } else if (dataInfos.isYearData()) {
                    i3 = 6;
                } else {
                    i3 = dataInfos.isDayData() ? 3 : 4;
                }
                FitnessSportIntensityDetailActivity.this.e.a(j2, j3, i3);
                FitnessSportIntensityDetailActivity.this.b(dataInfos, j, hwHealthBaseBarLineChart);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DataInfos dataInfos, long j, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        if (dataInfos != null && dataInfos.isDayData()) {
            this.f9881a.setTargetLayoutVisibility(0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j * 60000);
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
            long timeInMillis = calendar.getTimeInMillis();
            this.h = timeInMillis;
            if (jdl.ac(timeInMillis)) {
                this.d = 0;
                this.f9881a.setEditTargetVisibility(0);
                int i = this.g.get();
                if (i > 0) {
                    c(i);
                    return;
                } else {
                    nip.d("900200008", new d(this));
                    return;
                }
            }
            this.d = 8;
            this.f9881a.setEditTargetVisibility(8);
            this.f9881a.setTipVisibility(8);
            int i2 = this.b.get(HiDateUtil.c(this.h));
            if (i2 > 0) {
                c(i2);
                return;
            } else {
                nix.b(this.h, "900200008", new b(this));
                return;
            }
        }
        this.f9881a.setTargetLayoutVisibility(8);
        this.f9881a.setTipVisibility(8);
        int i3 = this.o;
        if (i3 <= 0 || !(hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart)) {
            return;
        }
        hwHealthBaseBarLineChart.enableManualReferenceLine(i3, nrn.d(R.color._2131296453_res_0x7f0900c5));
        ((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart).reCalculateDynamicBoardForManualRefLine();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public BaseStepDetailActivity.TextShowFormatter getTextShowFormatter() {
        return this.j;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateDayClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateDayClassifiedView = super.onCreateDayClassifiedView(textShowFormatter);
        enableProgressClassifiedViewObserver(onCreateDayClassifiedView);
        ScrollChartObserverView acquireScrollChartObserverView = onCreateDayClassifiedView.acquireScrollChartObserverView();
        if (!(acquireScrollChartObserverView instanceof DaySingleViewDataObserverView)) {
            return onCreateDayClassifiedView;
        }
        DaySingleViewDataObserverView daySingleViewDataObserverView = (DaySingleViewDataObserverView) acquireScrollChartObserverView;
        this.f9881a = daySingleViewDataObserverView;
        this.mScrollChartObserverView = daySingleViewDataObserverView.a();
        RelativeLayout dvw_ = this.f9881a.dvw_();
        if (dvw_ instanceof DayProgressBarScrollView) {
            this.f = (DayProgressBarScrollView) dvw_;
        }
        if (!(this.mScrollChartObserverView instanceof ScrollChartObserverTotalDataView)) {
            return onCreateDayClassifiedView;
        }
        ((ScrollChartObserverTotalDataView) this.mScrollChartObserverView).b(new ScrollChartObserverTotalDataView.b(this.mCurrentDayValueMinimum, 47101, DataInfos.query(getClassType(), DateType.DATE_DAY)));
        return onCreateDayClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateWeekClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateWeekClassifiedView = super.onCreateWeekClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateWeekClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_user_profile_achieve_sum), getString(R$string.IDS_hw_show_main_home_page_minutes), nsf.h(R$string.IDS_daily_average), getString(R$string.IDS_hw_show_main_home_page_minutes)));
        return onCreateWeekClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateMonthClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateMonthClassifiedView = super.onCreateMonthClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateMonthClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_user_profile_achieve_sum), getString(R$string.IDS_hw_show_main_home_page_minutes), nsf.h(R$string.IDS_daily_average), getString(R$string.IDS_hw_show_main_home_page_minutes)));
        return onCreateMonthClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateYearClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateYearClassifiedView = super.onCreateYearClassifiedView(textShowFormatter);
        enableClassifiedViewObserver((FitnessSportIntensityDetailActivity) onCreateYearClassifiedView, new HwHealthBaseScrollBarLineChart.DataRatioProvider() { // from class: com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity.3
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.DataRatioProvider
            public float computeRatio(int i) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(i * 60000);
                calendar.set(5, 1);
                calendar.roll(5, -1);
                return calendar.get(5);
            }
        }, new BaseStepDetailActivity.b(getString(R$string.IDS_user_profile_achieve_sum), getString(R$string.IDS_hw_show_main_home_page_minutes), nsf.h(R$string.IDS_daily_average), getString(R$string.IDS_hw_show_main_home_page_minutes)));
        return onCreateYearClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initTitleBar() {
        this.mTitleBar.setTitleText(getString(R$string.IDS_active_workout));
        this.mTitleBar.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430067_res_0x7f0b0ab3), nsf.h(R$string.IDS_lactate_tittlebar_top));
        this.mTitleBar.setRightButtonClickable(true);
        this.mTitleBar.setRightButtonVisibility(8);
        this.mTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.a("Step_FitnessSportIntensityDetailActivity", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                FitnessSportIntensityDetailActivity.this.startActivity(new Intent(FitnessSportIntensityDetailActivity.this, (Class<?>) SportIntensityExplain.class));
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(FitnessSportIntensityDetailActivity.this.getApplicationContext(), AnalyticsValue.HEALTH_TIME_STRENGTH_CLICK_2010090.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ClassType getClassType() {
        return ClassType.TYPE_STRENGTH_TIME;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initActivityData() {
        nip.d("900200008", new d(this));
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueMinimum() {
        return this.mScrollChartObserverView.getContentTextView().getText().toString();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueUnit() {
        return getString(R$string.IDS_hw_show_main_home_page_minutes);
    }

    private void a() {
        if (LanguageUtil.ai(this.c) || LanguageUtil.o(this.c)) {
            ((HealthTextView) findViewById(R.id.fitness_detail_total_run_unit_tv)).setTextSize(0, nsn.c(this.c, 10.0f));
            ((HealthTextView) findViewById(R.id.fitness_detail_total_walk_unit_tv)).setTextSize(0, nsn.c(this.c, 10.0f));
            ((HealthTextView) findViewById(R.id.fitness_detail_total_ride_unit_tv)).setTextSize(0, nsn.c(this.c, 10.0f));
            ((HealthTextView) findViewById(R.id.fitness_detail_total_train_unit_tv)).setTextSize(0, nsn.c(this.c, 10.0f));
            ((HealthTextView) findViewById(R.id.fitness_detail_total_other_unit_tv)).setTextSize(0, nsn.c(this.c, 10.0f));
        }
    }

    public void a(int i, int i2) {
        DaySingleViewDataObserverView daySingleViewDataObserverView = this.f9881a;
        if (daySingleViewDataObserverView == null || i < 0) {
            LogUtil.h("Step_FitnessSportIntensityDetailActivity", "updateTargetProgress mDayObserverView is null value ", Integer.valueOf(i), " target ", Integer.valueOf(i2));
        } else {
            updateTargetProgress(daySingleViewDataObserverView.getTargetTextView(), i, i2, R.plurals._2130903370_res_0x7f03014a);
            this.f9881a.setEditTargetOnClickListener(new View.OnClickListener() { // from class: puv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessSportIntensityDetailActivity.this.dtR_(view);
                }
            });
        }
    }

    public /* synthetic */ void dtR_(View view) {
        if (nsn.a(500)) {
            LogUtil.h("Step_FitnessSportIntensityDetailActivity", "jumpTarget isFastClick");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ActiveTargetActivity.e(this, 3, 2);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void d(int i, int i2) {
        DaySingleViewDataObserverView daySingleViewDataObserverView = this.f9881a;
        if (daySingleViewDataObserverView == null || i2 <= 0 || i < 0) {
            LogUtil.h("Step_FitnessSportIntensityDetailActivity", "updateTip mDayObserverView ", daySingleViewDataObserverView, " mTodayTarget ", Integer.valueOf(this.o), " target ", Integer.valueOf(i2), " value ", Integer.valueOf(i));
            return;
        }
        if (!jdl.ac(this.h)) {
            this.f9881a.setTipVisibility(8);
            return;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            this.f9881a.setTipVisibility(8);
            this.o = i2;
        } else {
            if (isStopUpdateTargetTip(i, this.f9881a)) {
                return;
            }
            b(i2, i3);
        }
    }

    private void b(int i, int i2) {
        HashMap<String, String> e = ActiveTipStringUtils.e(i2, ActiveTipStringUtils.TipType.WORKOUT);
        String str = e.get("description_key");
        if (TextUtils.isEmpty(str)) {
            this.f9881a.setTipVisibility(8);
            this.o = i;
            return;
        }
        final int h = CommonUtils.h(e.get("sport_type_key"));
        this.f9881a.setTipVisibility(0);
        this.f9881a.setTipText(str);
        this.f9881a.setTipButton(R.color._2131296450_res_0x7f0900c2, e.get("button_key"), new View.OnClickListener() { // from class: puw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessSportIntensityDetailActivity.this.dtQ_(h, view);
            }
        });
        if (i == this.o) {
            return;
        }
        this.o = i;
        pxp.b(0, 3, h, ActiveTipStringUtils.TipType.WORKOUT);
    }

    public /* synthetic */ void dtQ_(int i, View view) {
        pxp.b(1, 3, i, ActiveTipStringUtils.TipType.WORKOUT);
        if (gso.e().aTs_(0, gso.e().aTn_(i, -1, -1.0f, -1, 10), null, this, null) == 0) {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        HandlerExecutor.a(new Runnable() { // from class: puu
            @Override // java.lang.Runnable
            public final void run() {
                FitnessSportIntensityDetailActivity.this.b(i);
            }
        });
    }

    public /* synthetic */ void b(int i) {
        DayProgressBarScrollView dayProgressBarScrollView = this.f;
        if (dayProgressBarScrollView == null) {
            return;
        }
        if (i <= 0) {
            i = this.o;
        }
        dayProgressBarScrollView.e(i);
    }

    /* loaded from: classes6.dex */
    static class a implements AccessibilityManager.TouchExplorationStateChangeListener {
        private final WeakReference<FitnessSportIntensityDetailActivity> c;

        a(FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity) {
            this.c = new WeakReference<>(fitnessSportIntensityDetailActivity);
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity = this.c.get();
            if (fitnessSportIntensityDetailActivity == null || fitnessSportIntensityDetailActivity.isFinishing() || fitnessSportIntensityDetailActivity.isDestroyed()) {
                ReleaseLogUtil.d("Step_FitnessSportIntensityDetailActivity", "TouchExplorationStateChangeListener onTouchExplorationStateChanged activity ", fitnessSportIntensityDetailActivity);
                return;
            }
            LogUtil.a("Step_FitnessSportIntensityDetailActivity", "TouchExplorationStateChangeListener onTouchExplorationStateChanged enabled ", Boolean.valueOf(z), " mEditTargetVisibility ", Integer.valueOf(fitnessSportIntensityDetailActivity.d), " mDayObserverView ", fitnessSportIntensityDetailActivity.f9881a);
            if (fitnessSportIntensityDetailActivity.f9881a == null) {
                return;
            }
            fitnessSportIntensityDetailActivity.f9881a.setEditTargetAccessibilityVisibility(fitnessSportIntensityDetailActivity.d);
        }
    }

    /* loaded from: classes6.dex */
    static class d implements IBaseResponseCallback {
        private final WeakReference<FitnessSportIntensityDetailActivity> d;

        d(FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity) {
            this.d = new WeakReference<>(fitnessSportIntensityDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (!(obj instanceof Integer)) {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "TargetCallback onResponse object ", obj);
                return;
            }
            FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity = this.d.get();
            if (fitnessSportIntensityDetailActivity == null || fitnessSportIntensityDetailActivity.isFinishing() || fitnessSportIntensityDetailActivity.isDestroyed()) {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "TargetCallback onResponse activity ", fitnessSportIntensityDetailActivity);
                return;
            }
            fitnessSportIntensityDetailActivity.g.set(((Integer) obj).intValue());
            fitnessSportIntensityDetailActivity.c(fitnessSportIntensityDetailActivity.g.get());
        }
    }

    /* loaded from: classes6.dex */
    static class b implements ResponseCallback<List<HiHealthData>> {
        private final WeakReference<FitnessSportIntensityDetailActivity> d;

        b(FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity) {
            this.d = new WeakReference<>(fitnessSportIntensityDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<HiHealthData> list) {
            if (koq.b(list)) {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "HistoryTargetCallback onResponse list ", list);
                return;
            }
            HiHealthData hiHealthData = list.get(0);
            if (hiHealthData == null) {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "HistoryTargetCallback onResponse data is null");
                return;
            }
            FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity = this.d.get();
            if (fitnessSportIntensityDetailActivity != null && !fitnessSportIntensityDetailActivity.isFinishing() && !fitnessSportIntensityDetailActivity.isDestroyed()) {
                fitnessSportIntensityDetailActivity.b.append(HiDateUtil.c(hiHealthData.getStartTime()), hiHealthData.getInt("durationGoalValue"));
                fitnessSportIntensityDetailActivity.c(fitnessSportIntensityDetailActivity.b.get(HiDateUtil.c(fitnessSportIntensityDetailActivity.h)));
            } else {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "HistoryTargetCallback onResponse activity ", fitnessSportIntensityDetailActivity);
            }
        }
    }

    /* loaded from: classes6.dex */
    static class c implements HiSubscribeListener {
        private final WeakReference<FitnessSportIntensityDetailActivity> c;

        c(FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity) {
            this.c = new WeakReference<>(fitnessSportIntensityDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.b(list)) {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "ConfigGoalCallback onResult successList ", list);
                return;
            }
            FitnessSportIntensityDetailActivity fitnessSportIntensityDetailActivity = this.c.get();
            if (fitnessSportIntensityDetailActivity != null && !fitnessSportIntensityDetailActivity.isFinishing() && !fitnessSportIntensityDetailActivity.isDestroyed()) {
                fitnessSportIntensityDetailActivity.i = list;
            } else {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "ConfigGoalCallback onResult activity ", fitnessSportIntensityDetailActivity);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (i != 103) {
                LogUtil.h("Step_FitnessSportIntensityDetailActivity", "ConfigGoalCallback onChange type ", Integer.valueOf(i));
            } else if ("900200008".equals(str)) {
                nip.d("900200008", new d(this.c.get()));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

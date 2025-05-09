package com.huawei.ui.main.stories.fitness.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.scrollview.HealthNestedScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.step.CalorieDescriptionConvertView;
import com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView;
import com.huawei.ui.main.stories.fitness.activity.step.DistanceDescriptionConvertView;
import com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity;
import com.huawei.ui.main.stories.fitness.common.IChartLayerHolderProvider;
import com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedButtonList;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList;
import com.huawei.ui.main.stories.fitness.views.base.chart.DayBarClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.DayProgressBarScrollView;
import com.huawei.ui.main.stories.fitness.views.base.chart.DaySingleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.DoubleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.MonthBarClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverAverageDataView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.SingleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.StepDoubleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.WeekBarClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.YearBarClassifiedView;
import defpackage.jec;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes.dex */
public abstract class BaseStepDetailActivity extends BaseActivity implements IChartLayerHolderProvider<StepModuleBarChartHolder> {
    public static final int DAY_PAGE_INDEX = 0;
    private static final String MEDIUM_ASSETS_PATH = "font/HarmonyOSCondensedClockProportional-Medium.ttf";
    private static final float NUMBER_FONT_SIZE_DEFAULT = 24.0f;
    private static final String SPORT_PAGER_TYPE = "sportPageIndex";
    private static final String TAG = "Main_BaseStepDetailActivity";
    private static final float UNIT_FONT_SIZE_DEFAULT = 12.0f;
    private static final float UNIT_TO_NUMBER_DISTANCE = 4.0f;
    public static final int YEAR_PAGE_INDEX = 3;
    protected ClassifiedButtonList mBtnList;
    protected StepModuleBarChartHolder mChartLayerHolder;
    protected ClassifiedViewList mClassifiedViewList;
    private ObserveredClassifiedView mCurrentClassifiedView;
    protected LinearLayout mMarketingLayout;
    protected ScrollChartObserverView mScrollChartObserverView;
    protected HealthNestedScrollView mScrollView;
    protected CustomTitleBar mTitleBar;
    protected HealthViewPager mViewPagerLayerClass;
    protected int mDefaultPagerIndex = 0;
    protected MarketingApi mMarketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
    protected float mCurrentDayValueMinimum = -1.0f;
    protected HwHealthBaseScrollBarLineChart.DataRatioProvider mDefaultDataRatioProvider = new HwHealthBaseScrollBarLineChart.DataRatioProvider() { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.2
        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.DataRatioProvider
        public float computeRatio(int i) {
            return 1.0f;
        }
    };

    /* loaded from: classes6.dex */
    public interface TextShowFormatter {
        String convertFloat2TextShow(float f);
    }

    private boolean isDefaultContentView(int i) {
        return i == R.layout.activity_stepmodule_detail_layout;
    }

    protected int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_default;
    }

    public abstract ClassType getClassType();

    protected int getContentView() {
        return R.layout.activity_stepmodule_detail_layout;
    }

    public abstract String getCurrentDayValueMinimum();

    public abstract String getCurrentDayValueUnit();

    protected View getExtensionView() {
        return null;
    }

    protected abstract TextShowFormatter getTextShowFormatter();

    protected void handleTitleBarRightButtonOnClick(int i) {
    }

    protected abstract void initActivityData();

    protected abstract void initChartLayerHolderData(IChartLayerHolder iChartLayerHolder);

    protected void initChartListener() {
    }

    protected void initConstString() {
    }

    protected void initSportIntensityData() {
    }

    protected void initStartArguement(Intent intent) {
    }

    protected abstract void initTitleBar();

    public boolean isStepDetail() {
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreate(bundle);
        int contentView = getContentView();
        setContentView(contentView);
        Intent intent = getIntent();
        initStartArguement(intent);
        View extensionView = getExtensionView();
        setViewSafeRegion(false, extensionView);
        if (isDefaultContentView(contentView) && extensionView != null) {
            ((LinearLayout) findViewById(R.id.extension)).addView(extensionView, -1, -2);
        }
        initConstString();
        this.mChartLayerHolder = new StepModuleBarChartHolder(getApplicationContext());
        setDefaultTimeMillis(intent);
        initViews();
        initTitleBar();
        initSportIntensityData();
        initChartLayerHolderData(this.mChartLayerHolder);
        initClassifiedViewList();
        initActivityData();
        initChartListener();
        LogUtil.a(TAG, getClass().getName(), " onCreate cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void setDefaultTimeMillis(Intent intent) {
        if (intent == null || this.mChartLayerHolder == null) {
            ReleaseLogUtil.a(TAG, "setDefaultTimeMillis intent ", intent, " mChartLayerHolder ", this.mChartLayerHolder);
            return;
        }
        long longExtra = intent.getLongExtra("default_time_millis", 0L);
        LogUtil.a(TAG, "setDefaultTimeMillis timeMillis ", Long.valueOf(longExtra));
        if (longExtra > 0) {
            this.mChartLayerHolder.d(longExtra);
        }
    }

    protected void initStatus() {
        int intExtra;
        Intent intent = getIntent();
        if (intent != null && (intExtra = intent.getIntExtra(SPORT_PAGER_TYPE, 0)) >= 0 && intExtra <= 3) {
            this.mDefaultPagerIndex = intExtra;
        }
        int i = this.mDefaultPagerIndex;
        if (i != 0) {
            this.mBtnList.d(i);
        }
    }

    private void initViews() {
        clearBackgroundDrawable();
        this.mTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.fitness_detail_titlebar);
        ClassifiedButtonList classifiedButtonList = (ClassifiedButtonList) findViewById(R.id.classified_button_list);
        this.mBtnList = classifiedButtonList;
        if (classifiedButtonList.a()) {
            this.mBtnList.c(configSubTabStyle());
        }
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.classified_view_place);
        this.mViewPagerLayerClass = healthViewPager;
        cancelLayoutById(healthViewPager);
        ClassifiedViewList classifiedViewList = new ClassifiedViewList(this, this.mBtnList, this.mViewPagerLayerClass);
        this.mClassifiedViewList = classifiedViewList;
        classifiedViewList.setOnClassifiedViewChangeListener(new ClassifiedViewList.OnClassifiedViewChangeListener() { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.1
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.OnClassifiedViewChangeListener
            public void onClassifiedViewSelected(View view, int i) {
                if (view instanceof ObserveredClassifiedView) {
                    BaseStepDetailActivity.this.mCurrentClassifiedView = (ObserveredClassifiedView) view;
                }
            }
        });
    }

    @Override // com.huawei.ui.main.stories.fitness.common.IChartLayerHolderProvider
    public StepModuleBarChartHolder acquireChartLayerHolder() {
        return this.mChartLayerHolder;
    }

    protected void initClassifiedViewList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(onCreateDayClassifiedView(getTextShowFormatter()));
        arrayList.add(onCreateWeekClassifiedView(getTextShowFormatter()));
        arrayList.add(onCreateMonthClassifiedView(getTextShowFormatter()));
        arrayList.add(onCreateYearClassifiedView(getTextShowFormatter()));
        this.mClassifiedViewList.d(arrayList, this.mChartLayerHolder);
    }

    protected <T extends ObserveredClassifiedView> void enableClassifiedViewObserver(T t, HwHealthBaseScrollBarLineChart.DataRatioProvider dataRatioProvider, b bVar) {
        if (t == null || bVar == null) {
            LogUtil.h(TAG, "enableClassifiedViewObserver classifiedView || bottomViewBean == null");
            return;
        }
        final ScrollChartObserverTotalDataView scrollChartObserverTotalDataView = new ScrollChartObserverTotalDataView(this, t, bVar.e(), bVar.c(), dataRatioProvider);
        scrollChartObserverTotalDataView.setCustomCalculator(acquireChartLayerHolder().b());
        final ScrollChartObserverAverageDataView scrollChartObserverAverageDataView = new ScrollChartObserverAverageDataView(this, t, bVar.d(), bVar.b());
        scrollChartObserverAverageDataView.setCustomCalculator(acquireChartLayerHolder().a());
        DoubleViewDataObserverView doubleViewDataObserverView = new DoubleViewDataObserverView(this, t);
        doubleViewDataObserverView.d(scrollChartObserverTotalDataView, scrollChartObserverAverageDataView);
        setViewSafeRegion(false, doubleViewDataObserverView);
        t.enableObserverView(doubleViewDataObserverView);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), MEDIUM_ASSETS_PATH);
        scrollChartObserverTotalDataView.setContentTypeface(createFromAsset);
        scrollChartObserverAverageDataView.setContentTypeface(createFromAsset);
        scrollChartObserverTotalDataView.getContentTextView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: pvx
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                BaseStepDetailActivity.this.m821xe27dbd2f(scrollChartObserverTotalDataView, scrollChartObserverAverageDataView);
            }
        });
    }

    protected <T extends ObserveredClassifiedView> void enableStepClassifiedViewObserver(T t, HwHealthBaseScrollBarLineChart.DataRatioProvider dataRatioProvider, b bVar) {
        if (t == null || bVar == null) {
            LogUtil.h(TAG, "enableClassifiedViewObserver classifiedView || bottomViewBean == null");
            return;
        }
        final ScrollChartObserverTotalDataView scrollChartObserverTotalDataView = new ScrollChartObserverTotalDataView(this, t, bVar.e(), bVar.c(), dataRatioProvider);
        CalorieDescriptionConvertView calorieDescriptionConvertView = new CalorieDescriptionConvertView(this);
        scrollChartObserverTotalDataView.setOnDataChange(calorieDescriptionConvertView);
        DistanceDescriptionConvertView distanceDescriptionConvertView = new DistanceDescriptionConvertView(this);
        scrollChartObserverTotalDataView.setOnDataChange(distanceDescriptionConvertView);
        final ScrollChartObserverAverageDataView scrollChartObserverAverageDataView = new ScrollChartObserverAverageDataView(this, t, bVar.d(), bVar.b());
        scrollChartObserverTotalDataView.setCustomCalculator(acquireChartLayerHolder().b());
        scrollChartObserverAverageDataView.setCustomCalculator(acquireChartLayerHolder().a());
        StepDoubleViewDataObserverView stepDoubleViewDataObserverView = new StepDoubleViewDataObserverView(this, t);
        stepDoubleViewDataObserverView.dvB_(scrollChartObserverTotalDataView, scrollChartObserverAverageDataView, calorieDescriptionConvertView, distanceDescriptionConvertView);
        setViewSafeRegion(false, stepDoubleViewDataObserverView);
        t.enableObserverView(stepDoubleViewDataObserverView);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), MEDIUM_ASSETS_PATH);
        scrollChartObserverTotalDataView.setContentTypeface(createFromAsset);
        scrollChartObserverAverageDataView.setContentTypeface(createFromAsset);
        scrollChartObserverTotalDataView.getContentTextView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: pvt
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                BaseStepDetailActivity.this.m822x1f439ca2(scrollChartObserverTotalDataView, scrollChartObserverAverageDataView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGlobalLayoutContent, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m822x1f439ca2(ScrollChartObserverTotalDataView scrollChartObserverTotalDataView, ScrollChartObserverAverageDataView scrollChartObserverAverageDataView) {
        HealthTextView contentTextView = scrollChartObserverTotalDataView.getContentTextView();
        HealthTextView contentTextView2 = scrollChartObserverAverageDataView.getContentTextView();
        HealthTextView unitTextView = scrollChartObserverTotalDataView.getUnitTextView();
        HealthTextView unitTextView2 = scrollChartObserverAverageDataView.getUnitTextView();
        if ((contentTextView.getTag() instanceof CharSequence) && TextUtils.equals(contentTextView.getText(), (CharSequence) contentTextView.getTag())) {
            return;
        }
        changeTextSize(contentTextView, contentTextView2, contentTextView.getMaxWidth(), nsn.c(this, NUMBER_FONT_SIZE_DEFAULT));
        changeTextSize(unitTextView, unitTextView2, (scrollChartObserverTotalDataView.getWidth() - contentTextView.getWidth()) - nsn.c(this, UNIT_TO_NUMBER_DISTANCE), nsn.c(this, UNIT_FONT_SIZE_DEFAULT));
        contentTextView.setTag(contentTextView.getText().toString());
    }

    protected <T extends ObserveredClassifiedView> void enableClassifiedViewObserver(T t, String str, String str2) {
        if (t == null) {
            LogUtil.h(TAG, "enableClassifiedViewObserver classifiedView is null");
            return;
        }
        ScrollChartObserverTotalDataView scrollChartObserverTotalDataView = new ScrollChartObserverTotalDataView(this, t, str, str2, this.mDefaultDataRatioProvider);
        scrollChartObserverTotalDataView.setCustomCalculator(acquireChartLayerHolder().b());
        SingleViewDataObserverView singleViewDataObserverView = new SingleViewDataObserverView(this);
        singleViewDataObserverView.b(scrollChartObserverTotalDataView);
        setViewSafeRegion(false, singleViewDataObserverView);
        t.enableObserverView(singleViewDataObserverView);
        scrollChartObserverTotalDataView.setContentTypeface(Typeface.createFromAsset(getAssets(), MEDIUM_ASSETS_PATH));
    }

    protected <T extends ObserveredClassifiedView> void enableProgressClassifiedViewObserver(T t) {
        if (t == null) {
            LogUtil.h(TAG, "enableProgressClassifiedViewObserver classifiedView is null");
            return;
        }
        ScrollChartObserverTotalDataView scrollChartObserverTotalDataView = new ScrollChartObserverTotalDataView(this, t, "", "", this.mDefaultDataRatioProvider);
        DayProgressBarScrollView dayProgressBarScrollView = new DayProgressBarScrollView(this);
        scrollChartObserverTotalDataView.setOnDataChange(dayProgressBarScrollView);
        scrollChartObserverTotalDataView.setCustomCalculator(acquireChartLayerHolder().b());
        DaySingleViewDataObserverView daySingleViewDataObserverView = new DaySingleViewDataObserverView(this);
        daySingleViewDataObserverView.dvx_(scrollChartObserverTotalDataView, null, null, dayProgressBarScrollView);
        setViewSafeRegion(false, daySingleViewDataObserverView);
        t.enableObserverView(daySingleViewDataObserverView);
        t.setObserverContainerBackgroundColor(nsf.c(R.color._2131296971_res_0x7f0902cb));
        scrollChartObserverTotalDataView.setContentTypeface(Typeface.createFromAsset(getAssets(), MEDIUM_ASSETS_PATH));
    }

    protected <T extends ObserveredClassifiedView> void enableStepClassifiedViewObserver(T t) {
        if (t == null) {
            LogUtil.h(TAG, "enableClassifiedViewObserver classifiedView is null");
            return;
        }
        ScrollChartObserverTotalDataView scrollChartObserverTotalDataView = new ScrollChartObserverTotalDataView(this, t, "", "", this.mDefaultDataRatioProvider);
        CalorieDescriptionConvertView calorieDescriptionConvertView = new CalorieDescriptionConvertView(this);
        DistanceDescriptionConvertView distanceDescriptionConvertView = new DistanceDescriptionConvertView(this);
        scrollChartObserverTotalDataView.setOnDataChange(distanceDescriptionConvertView);
        scrollChartObserverTotalDataView.setOnDataChange(calorieDescriptionConvertView);
        DayStepProgressBarScrollView dayStepProgressBarScrollView = new DayStepProgressBarScrollView(this);
        scrollChartObserverTotalDataView.setOnDataChange(dayStepProgressBarScrollView);
        scrollChartObserverTotalDataView.setCustomCalculator(acquireChartLayerHolder().b());
        DaySingleViewDataObserverView daySingleViewDataObserverView = new DaySingleViewDataObserverView(this);
        daySingleViewDataObserverView.dvx_(scrollChartObserverTotalDataView, calorieDescriptionConvertView, distanceDescriptionConvertView, dayStepProgressBarScrollView);
        setViewSafeRegion(false, daySingleViewDataObserverView);
        t.enableObserverView(daySingleViewDataObserverView);
        scrollChartObserverTotalDataView.setContentTypeface(Typeface.createFromAsset(getAssets(), MEDIUM_ASSETS_PATH));
    }

    protected <T extends ObserveredClassifiedView> void enableClassifiedViewObserver(T t, b bVar) {
        enableClassifiedViewObserver((BaseStepDetailActivity) t, this.mDefaultDataRatioProvider, bVar);
    }

    protected <T extends ObserveredClassifiedView> void enableWeekMonthStepClassifiedViewObserver(T t, b bVar) {
        enableStepClassifiedViewObserver(t, this.mDefaultDataRatioProvider, bVar);
    }

    public ObserveredClassifiedView onCreateDayClassifiedView(final TextShowFormatter textShowFormatter) {
        DayBarClassifiedView dayBarClassifiedView = new DayBarClassifiedView(this) { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.3
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                TextShowFormatter textShowFormatter2 = textShowFormatter;
                if (textShowFormatter2 != null) {
                    return textShowFormatter2.convertFloat2TextShow(f);
                }
                LogUtil.h(BaseStepDetailActivity.TAG, "onCreateDayClassifiedView textShowFormatter is null");
                return null;
            }
        };
        dayBarClassifiedView.setStepDatatype(DataInfos.query(getClassType(), DateType.DATE_DAY));
        dayBarClassifiedView.initCalendarView(this, null);
        this.mCurrentClassifiedView = dayBarClassifiedView;
        return dayBarClassifiedView;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ObserveredClassifiedView observeredClassifiedView;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (!(serializableExtra instanceof HealthCalendar) || (observeredClassifiedView = this.mCurrentClassifiedView) == null) {
            return;
        }
        observeredClassifiedView.processCalendarSelect((HealthCalendar) serializableExtra);
    }

    public ObserveredClassifiedView onCreateWeekClassifiedView(final TextShowFormatter textShowFormatter) {
        WeekBarClassifiedView weekBarClassifiedView = new WeekBarClassifiedView(this) { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.4
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                TextShowFormatter textShowFormatter2 = textShowFormatter;
                if (textShowFormatter2 != null) {
                    return textShowFormatter2.convertFloat2TextShow(f);
                }
                LogUtil.h(BaseStepDetailActivity.TAG, "onCreateWeekClassifiedView textShowFormatter is null");
                return null;
            }
        };
        weekBarClassifiedView.setStepDatatype(DataInfos.query(getClassType(), DateType.DATE_WEEK));
        weekBarClassifiedView.initCalendarView(this, null);
        return weekBarClassifiedView;
    }

    public ObserveredClassifiedView onCreateMonthClassifiedView(final TextShowFormatter textShowFormatter) {
        MonthBarClassifiedView monthBarClassifiedView = new MonthBarClassifiedView(this) { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.5
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                TextShowFormatter textShowFormatter2 = textShowFormatter;
                if (textShowFormatter2 != null) {
                    return textShowFormatter2.convertFloat2TextShow(f);
                }
                LogUtil.h(BaseStepDetailActivity.TAG, "onCreateMonthClassifiedView textShowFormatter is null");
                return null;
            }
        };
        monthBarClassifiedView.setStepDatatype(DataInfos.query(getClassType(), DateType.DATE_MONTH));
        monthBarClassifiedView.initCalendarView(this, null);
        return monthBarClassifiedView;
    }

    public ObserveredClassifiedView onCreateYearClassifiedView(final TextShowFormatter textShowFormatter) {
        YearBarClassifiedView yearBarClassifiedView = new YearBarClassifiedView(this) { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.6
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                TextShowFormatter textShowFormatter2 = textShowFormatter;
                if (textShowFormatter2 != null) {
                    return textShowFormatter2.convertFloat2TextShow(f);
                }
                LogUtil.h(BaseStepDetailActivity.TAG, "onCreateYearClassifiedView textShowFormatter is null");
                return null;
            }
        };
        yearBarClassifiedView.setStepDatatype(DataInfos.query(getClassType(), DateType.DATE_YEAR));
        yearBarClassifiedView.initCalendarView(this, null);
        return yearBarClassifiedView;
    }

    protected void updateTargetProgress(HealthTextView healthTextView, int i, int i2, int i3) {
        if (healthTextView == null) {
            LogUtil.h(TAG, "updateTargetProgress textView is null value ", Integer.valueOf(i), " target ", Integer.valueOf(i2));
            return;
        }
        String e = UnitUtil.e(i, 1, 0);
        SpannableString spannableString = new SpannableString(nsf.b(R$string.IDS_current_total_time, e, nsf.a(i3, i2, UnitUtil.e(i2, 1, 0))));
        nsi.cKI_(spannableString, e, R.color._2131299236_res_0x7f090ba4);
        nsi.cKJ_(spannableString, e, nsf.b(R.dimen._2131362955_res_0x7f0a048b));
        nsi.cKL_(spannableString, e, R$string.textFontFamilyMedium);
        healthTextView.setText(spannableString);
    }

    private void changeTextSize(HealthTextView healthTextView, HealthTextView healthTextView2, float f, float f2) {
        float textSize = healthTextView.getTextSize();
        String obj = healthTextView.getText().toString();
        float measureText = healthTextView.getPaint().measureText(obj);
        if (measureText == 0.0f) {
            return;
        }
        if (measureText >= f || textSize >= f2) {
            while (measureText > f) {
                textSize -= 1.0f;
                healthTextView.setTextSize(0, textSize);
                measureText = healthTextView.getPaint().measureText(obj);
                if (measureText <= 0.0f) {
                    break;
                }
            }
            f2 = textSize;
        } else {
            while (true) {
                if (measureText >= f) {
                    f2 = textSize;
                    break;
                }
                textSize += 1.0f;
                healthTextView.setTextSize(0, textSize);
                measureText = healthTextView.getPaint().measureText(obj);
                if (textSize >= f2) {
                    break;
                }
            }
            if (measureText > f) {
                f2 -= 1.0f;
            }
            healthTextView.setTextSize(0, f2);
        }
        healthTextView2.setTextSize(0, f2);
    }

    /* loaded from: classes6.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f9911a;
        private String c;
        private String d;
        private String e;

        public b(String str, String str2, String str3, String str4) {
            this.f9911a = str;
            this.e = str2;
            this.d = str3;
            this.c = str4;
        }

        public String e() {
            return this.f9911a;
        }

        public String c() {
            return this.e;
        }

        public String d() {
            return this.d;
        }

        public String b() {
            return this.c;
        }
    }

    protected void setTitleBarRightButton(final Context context, final ArrayList<String> arrayList) {
        CustomTitleBar customTitleBar = this.mTitleBar;
        if (customTitleBar != null) {
            customTitleBar.setRightButtonDrawable(context.getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
            this.mTitleBar.setRightButtonClickable(true);
            this.mTitleBar.setRightButtonVisibility(0);
            this.mTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new PopViewList(context, BaseStepDetailActivity.this.mTitleBar, arrayList).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.8.5
                        @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
                        public void setOnClick(int i) {
                            BaseStepDetailActivity.this.handleTitleBarRightButtonOnClick(i);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    protected boolean isStopUpdateTargetTip(float f, DaySingleViewDataObserverView daySingleViewDataObserverView) {
        return jec.ab(new Date(getIntent().getLongExtra("default_time_millis", System.currentTimeMillis()))) && (f < this.mCurrentDayValueMinimum || daySingleViewDataObserverView.getTipVisibility() == 0);
    }
}

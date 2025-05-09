package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartView;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseMvpChartView;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDiffActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarFeedbackActivity;
import com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface;
import com.huawei.ui.main.stories.health.views.charteye.ChartEyeView;
import com.huawei.ui.main.stories.health.views.charteye.MultiViewDataObserverView;
import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.ComponentParam;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.health.HealthMvpActivity;
import com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract;
import com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter;
import defpackage.eil;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.pzb;
import defpackage.pzg;
import defpackage.pzp;
import defpackage.pzq;
import defpackage.pzr;
import defpackage.pzy;
import defpackage.qac;
import defpackage.qjv;
import defpackage.qmc;
import defpackage.qqu;
import defpackage.qrp;
import defpackage.rzh;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class BloodSugarLineChartView extends CommonBaseMvpChartView<pzq> implements BaseComponent, View.OnClickListener, MultiViewDataObserverView.OnSelectListener, OnActivityResultInterface {
    private static final int AFTER_MEAL_POSITION = 4;
    private static final int BEFORE_AFTER_MEAL_DIFFERENCE_POSITION = 5;
    private static final int BEFORE_MEAL_POSITION = 3;
    private static final int BEFORE_SLEEP_BLOOD_GLUCOSE_POSITION = 6;
    private static final String BLOOD_SUGAR_TO_DEVICE_MEASURE_KEY = "entrance";
    private static final String BLOOD_SUGAR_TO_DEVICE_MEASURE_PERIOD_KEY = "time_period";
    private static final String BLOOD_SUGAR_TO_DEVICE_MEASURE_START_TIME_KEY = "start_time";
    private static final String BLOOD_SUGAR_TO_DEVICE_MEASURE_VALUE = "jump_from_blood_sugar_home";
    private static final String CONNECTING_LINE = " - ";
    private static final int CONTINUOUS_GLUCOSE_POSITION = 8;
    private static final long CURSOR_SIZE = 20;
    private static final int ERROR_POSITION = -1;
    private static final int FINGER_TIP_POSITION = 1;
    private static final int HOUR_UNIT = 3600000;
    private static final int MONTH = 2;
    private static final int NIGHT_BLOOD_GLUGLUCOSE_POSITION = 7;
    private static final long SECOND_UNIT = 1000;
    private static final int SUGAR_LIMOSIS_POSITION = 2;
    private static final String TAG = "BloodSugarLineChartView";
    private static final int TEN_MINUTE = 600000;
    private static final int THERE_MINUTE = 180000;
    private static final int WEEK = 1;
    private qac mBarChartPagerAdapter;
    private long mBiStartTime;
    private BloodSugarAnalysisView mBloodSugarAnalysisView;
    private BloodSugarLineChart mBloodSugarLineChart;
    private pzg mBloodSugarLineChartHolder;
    private BloodSugarNocturnalHypoglycemiaCardView mBloodSugarNocturnalHypoglycemiaCardView;
    private BloodSugarSportMonitoringView mBloodSugarSportMonitoringView;
    private ArrayList<View> mBloodSugarViewList;
    private HealthViewPager mBloodSugarViewPager;
    private ImageView mButtonLift;
    private ImageView mButtonRight;
    private HealthCalendar mCalendar;
    private ChartEyeView mChartEyeView;
    private Context mContext;
    private int mCurrentCardType;
    private HealthTextView mCursorCommit;
    private HealthTextView mCursorCommitHigh;
    private View mCursorCommitLine;
    private View mCursorCommitLineHigh;
    private View mCursorCommitLineSingle;
    private HealthTextView mCursorCommitSingle;
    private HealthTextView mCursorStatusArrow;
    private HealthTextView mCursorStatusArrowHigh;
    private HealthTextView mCursorStatusArrowSingle;
    private HealthTextView mCursorStatusFood;
    private HealthTextView mCursorStatusFoodHigh;
    private HealthTextView mCursorStatusFoodSingle;
    private ConstraintLayout mCursorStatusLayout;
    private ConstraintLayout mCursorStatusLayoutHigh;
    private RelativeLayout mCursorStatusLayoutSingle;
    private HealthTextView mCursorStatusValue;
    private HealthTextView mCursorStatusValueHigh;
    private HealthTextView mCursorStatusValueSingle;
    private View mCursorStatusVerticalLineSingle;
    private HealthTextView mCursorTime;
    private HealthTextView mCursorValue;
    private HealthTextView mCursorValueHigh;
    private HealthTextView mCursorValueSingle;
    private HealthTextView mCursorValueSingleUnit;
    private HealthTextView mCursorValueTitle;
    private HealthTextView mCursorValueTitleHigh;
    private DataInfos mDataInfos;
    private qmc mDetailFragmentPresenter;
    private LinearLayout mDietMonitorView;
    private pzb mDietMonitorViewHolder;
    private long mEndTime;
    private Fragment mFragment;
    private RelativeLayout mGlucoseLevelLayout;
    private c mHandle;
    private boolean mIsChinese;
    private boolean mIsFirstRefreshCardView;
    private boolean mIsFirstShowHypoglycemiaCardView;
    private boolean mIsIntoMonth;
    private boolean mIsIntoWeek;
    private boolean mIsJumpFromNotify;
    private boolean mIsLeaveDay;
    private long mLastTimestamp;
    private List<pzy> mMarkSelectedModelList;
    private int mNewEndX;
    private int mNewStartX;
    private final AtomicBoolean mNewerSwitchBtnClickable;
    private String mOldShowDataType;
    private int mOldStartX;
    private final AtomicBoolean mOlderSwitchBtnClickable;
    private Constants.PageType mPageType;
    private List<ComponentParam> mParams;
    private final Runnable mRefreshRunnable;
    private String mShowDataType;
    private long mStartTime;
    private HealthTextView mTextDate;
    private ConstraintLayout mValueLayoutDouble;
    private ConstraintLayout mValueLayoutSingle;
    private Map<Long, IStorageModel> resultMap;

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public HwHealthBarChart getBarChart() {
        return null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyMaxAndMin(int i, List<HiHealthData> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyRemindData(int i, List<HiHealthData> list) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onCreate() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onStop() {
    }

    public BloodSugarLineChartView(Context context) {
        this(context, null);
    }

    public BloodSugarLineChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BloodSugarLineChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOlderSwitchBtnClickable = new AtomicBoolean(false);
        this.mNewerSwitchBtnClickable = new AtomicBoolean(false);
        this.mRefreshRunnable = new Runnable() { // from class: com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartView.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(BloodSugarLineChartView.TAG, "refreshAnalysisView");
                if (BloodSugarLineChartView.this.mBloodSugarLineChart != null) {
                    BloodSugarLineChartView.this.mBloodSugarLineChart.c();
                }
            }
        };
        this.mShowDataType = "BLOOD_SUGAR_FINGER_TIP";
        this.mStartTime = 0L;
        this.mBiStartTime = 0L;
        this.mEndTime = 0L;
        this.mIsLeaveDay = false;
        this.mIsIntoWeek = false;
        this.mIsIntoMonth = false;
        this.mIsChinese = false;
        this.resultMap = new HashMap(16);
        this.mMarkSelectedModelList = new ArrayList(16);
        this.mHandle = new c(this);
        this.mIsFirstRefreshCardView = true;
        this.mIsFirstShowHypoglycemiaCardView = true;
        initConstructor(context);
    }

    private void initConstructor(Context context) {
        if (context == null) {
            LogUtil.h(TAG, "BloodSugarLineChartView context or dataType is null");
        } else {
            this.mContext = context;
            initLayout(context, this);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public int getLayoutId() {
        return nsn.r() ? R.layout.layout_blood_sugar_line_chart_view_large : R.layout.layout_blood_sugar_line_chart_view;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public void initView(View view) {
        initViewElement(view);
        initCursorView(view);
        setLargeCursorViewStyle();
        setCursorViewData(view);
        setLiftAndRightImage();
        initViewPager();
    }

    private void initViewElement(View view) {
        this.mButtonLift = (ImageView) view.findViewById(R.id.image_up_arrow_left);
        this.mButtonRight = (ImageView) view.findViewById(R.id.image_up_arrow_right);
        this.mTextDate = (HealthTextView) view.findViewById(R.id.blood_sugar_detail_time_date_tv);
        this.mBloodSugarViewPager = (HealthViewPager) view.findViewById(R.id.blood_sugar_detail_viewpager);
        this.mGlucoseLevelLayout = (RelativeLayout) view.findViewById(R.id.glucose_level_layout);
    }

    private void setLargeCursorViewStyle() {
        if (this.mCursorValue == null || this.mCursorValueHigh == null || !nsn.r()) {
            return;
        }
        this.mCursorValue.setTextSize(1, 20.0f);
        this.mCursorValueHigh.setTextSize(1, 20.0f);
    }

    private void initCursorView(View view) {
        this.mCursorTime = (HealthTextView) view.findViewById(R.id.cursor_time);
        this.mCursorValue = (HealthTextView) view.findViewById(R.id.cursor_value);
        this.mCursorValueTitle = (HealthTextView) view.findViewById(R.id.cursor_value_title);
        this.mCursorStatusValue = (HealthTextView) view.findViewById(R.id.cursor_status_value);
        this.mCursorStatusFood = (HealthTextView) view.findViewById(R.id.cursor_status_food);
        this.mCursorCommitLine = view.findViewById(R.id.cursor_status_verticalLine);
        this.mCursorStatusLayout = (ConstraintLayout) view.findViewById(R.id.cursor_status_layout);
        this.mCursorStatusLayoutHigh = (ConstraintLayout) view.findViewById(R.id.cursor_status_layout_high);
        this.mChartEyeView = (ChartEyeView) view.findViewById(R.id.blood_sugar_detail_chart_eye_view);
        this.mBloodSugarSportMonitoringView = (BloodSugarSportMonitoringView) view.findViewById(R.id.blood_sugar_sport_monitoring_view);
        this.mBloodSugarNocturnalHypoglycemiaCardView = (BloodSugarNocturnalHypoglycemiaCardView) view.findViewById(R.id.blood_sugar_nocturnal_hypoglycemia_card_view);
        this.mBloodSugarAnalysisView = (BloodSugarAnalysisView) view.findViewById(R.id.blood_sugar_analysis_view);
        this.mDietMonitorView = (LinearLayout) view.findViewById(R.id.blood_sugar_diet_monitor_view);
        this.mCursorValueTitleHigh = (HealthTextView) view.findViewById(R.id.cursor_value_title_high);
        this.mCursorValueHigh = (HealthTextView) view.findViewById(R.id.cursor_value_high);
        this.mCursorStatusValueHigh = (HealthTextView) view.findViewById(R.id.cursor_status_value_high);
        this.mCursorStatusFoodHigh = (HealthTextView) view.findViewById(R.id.cursor_status_food_high);
        this.mCursorCommitLineHigh = view.findViewById(R.id.cursor_status_verticalLine_high);
        this.mCursorValueSingle = (HealthTextView) view.findViewById(R.id.cursor_value_single);
        this.mCursorValueSingleUnit = (HealthTextView) view.findViewById(R.id.cursor_value_single_unit);
        this.mCursorStatusValueSingle = (HealthTextView) view.findViewById(R.id.cursor_status_value_single);
        this.mCursorStatusVerticalLineSingle = view.findViewById(R.id.cursor_status_verticalLine_one_single);
        this.mCursorStatusArrowSingle = (HealthTextView) view.findViewById(R.id.cursor_status_arrow_single);
        this.mCursorStatusFoodSingle = (HealthTextView) view.findViewById(R.id.cursor_status_food_single);
        this.mCursorCommitSingle = (HealthTextView) view.findViewById(R.id.cursor_status_commit_single);
        this.mCursorCommitLineSingle = view.findViewById(R.id.cursor_status_verticalLine_single);
        this.mValueLayoutSingle = (ConstraintLayout) view.findViewById(R.id.value_layout_single);
        this.mValueLayoutDouble = (ConstraintLayout) view.findViewById(R.id.value_layout_double);
        this.mCursorStatusLayoutSingle = (RelativeLayout) view.findViewById(R.id.cursor_status_layout_single);
    }

    private void setCardView(int i, int i2) {
        String str;
        String str2 = "BLOOD_SUGAR_CONTINUE";
        if (i == 12109) {
            this.mChartEyeView.setVisibility(0);
            this.mChartEyeView.setIsNeedGuide(true);
        } else if (i == 2108) {
            this.mChartEyeView.setVisibility(8);
        } else {
            if (i == 10001) {
                this.mChartEyeView.setVisibility(0);
                str = "BLOOD_SUGAR_FINGER_TIP";
            } else {
                LogUtil.h(TAG, "initCardView other type");
                str = "";
            }
            this.mChartEyeView.setCardType(str);
            if (i2 != 2108 && (!this.mIsFirstRefreshCardView || !this.mIsJumpFromNotify || !str.equals("BLOOD_SUGAR_CONTINUE"))) {
                str2 = "BLOOD_SUGAR_FINGER_TIP";
            }
            this.mIsFirstRefreshCardView = false;
            switchShowDataType(str2);
            this.mChartEyeView.setCurrentSelected(str2);
            this.mChartEyeView.setDataInfos(this.mDataInfos);
            this.mChartEyeView.b(str2);
        }
        str = "BLOOD_SUGAR_CONTINUE";
        this.mChartEyeView.setCardType(str);
        if (i2 != 2108) {
            str2 = "BLOOD_SUGAR_FINGER_TIP";
        }
        this.mIsFirstRefreshCardView = false;
        switchShowDataType(str2);
        this.mChartEyeView.setCurrentSelected(str2);
        this.mChartEyeView.setDataInfos(this.mDataInfos);
        this.mChartEyeView.b(str2);
    }

    private void switchShowDataType(String str) {
        if (this.mDataInfos.isDayData()) {
            rzh.e("blood_sugar_data_type", str);
        }
        this.mShowDataType = str;
        if ("BLOOD_SUGAR_CONTINUE".equals(str)) {
            this.mCursorStatusVerticalLineSingle.setVisibility(8);
            this.mCursorStatusFoodSingle.setVisibility(8);
            this.mCursorStatusArrowSingle.setVisibility(8);
            this.mGlucoseLevelLayout.setVisibility(8);
        }
        if ("BLOOD_SUGAR_FINGER_TIP".equals(this.mShowDataType)) {
            this.mCursorStatusVerticalLineSingle.setVisibility(0);
            this.mCursorStatusFoodSingle.setVisibility(0);
            this.mCursorStatusArrowSingle.setVisibility(0);
            this.mGlucoseLevelLayout.setVisibility(0);
        }
        BloodSugarLineChart bloodSugarLineChart = this.mBloodSugarLineChart;
        if (bloodSugarLineChart != null) {
            bloodSugarLineChart.setDataType(str);
            long h = nom.h((int) this.mBloodSugarLineChart.acquireShowRangeMinimum());
            showSportDifferView();
            showContinueBloodSugarCard(h);
        }
    }

    private void setCursorViewData(View view) {
        float dimension;
        boolean equals = this.mContext.getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage());
        this.mIsChinese = equals;
        if (equals) {
            this.mCursorCommit = (HealthTextView) view.findViewById(R.id.cursor_status_commit_chinese);
            this.mCursorStatusArrow = (HealthTextView) view.findViewById(R.id.cursor_status_arrow_chinese);
            this.mCursorCommitHigh = (HealthTextView) view.findViewById(R.id.cursor_status_commit_high_chinese);
            this.mCursorStatusArrowHigh = (HealthTextView) view.findViewById(R.id.cursor_status_arrow_high_chinese);
            view.findViewById(R.id.cursor_status_commit).setVisibility(8);
            view.findViewById(R.id.cursor_status_arrow).setVisibility(8);
            view.findViewById(R.id.cursor_status_commit_high).setVisibility(8);
            view.findViewById(R.id.cursor_status_arrow_high).setVisibility(8);
            dimension = getResources().getDimension(R.dimen._2131363726_res_0x7f0a078e);
        } else {
            this.mCursorCommit = (HealthTextView) view.findViewById(R.id.cursor_status_commit);
            this.mCursorStatusArrow = (HealthTextView) view.findViewById(R.id.cursor_status_arrow);
            this.mCursorCommitHigh = (HealthTextView) view.findViewById(R.id.cursor_status_commit_high);
            this.mCursorStatusArrowHigh = (HealthTextView) view.findViewById(R.id.cursor_status_arrow_high);
            view.findViewById(R.id.cursor_status_commit_chinese).setVisibility(8);
            view.findViewById(R.id.cursor_status_arrow_chinese).setVisibility(8);
            view.findViewById(R.id.cursor_status_commit_high_chinese).setVisibility(8);
            view.findViewById(R.id.cursor_status_arrow_high_chinese).setVisibility(8);
            dimension = getResources().getDimension(R.dimen._2131363717_res_0x7f0a0785);
        }
        this.mCursorStatusValue.setTextSize(0, dimension);
        this.mCursorStatusFood.setTextSize(0, dimension);
        this.mCursorStatusValueHigh.setTextSize(0, dimension);
        this.mCursorStatusFoodHigh.setTextSize(0, dimension);
    }

    private void setLastTimeForDataPlatform() {
        int f;
        if (this.mLastTimestamp <= 0) {
            LogUtil.h(TAG, "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        BloodSugarLineChart bloodSugarLineChart = this.mBloodSugarLineChart;
        if (bloodSugarLineChart == null || bloodSugarLineChart.acquireScrollAdapter() == null) {
            LogUtil.h(TAG, "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        LogUtil.a(TAG, "mLastTimestamp=", Long.valueOf(this.mLastTimestamp));
        if (this.mDataInfos.isDayData()) {
            f = nom.f(nom.a(this.mLastTimestamp));
        } else if (this.mDataInfos.isWeekData()) {
            f = nom.f(nom.m(this.mLastTimestamp));
        } else if (this.mDataInfos.isMonthData()) {
            f = nom.f(nom.f(this.mLastTimestamp));
        } else {
            LogUtil.a(TAG, "setLastTimeForDataPlatform dataInfos is not day / month / year");
            return;
        }
        LogUtil.a(TAG, "startTimestamp=", Integer.valueOf(f));
        BloodSugarLineChart bloodSugarLineChart2 = this.mBloodSugarLineChart;
        bloodSugarLineChart2.setShowRange(f, bloodSugarLineChart2.acquireScrollAdapter().acquireRange());
    }

    public void updatePopGuide() {
        this.mChartEyeView.b();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public void initData() {
        if (this.mPresenter != 0) {
            ((pzq) this.mPresenter).initPageParams();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseMvpChartView
    public pzq createPresenter() {
        return new pzq();
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public View getView(Context context) {
        return this.mView;
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void initComponent(List<ComponentParam> list) {
        this.mParams = list;
        if (koq.b(list)) {
            return;
        }
        Iterator<ComponentParam> it = this.mParams.iterator();
        while (it.hasNext()) {
            if ("jump_from_notify".equals(it.next().getmValue())) {
                this.mIsJumpFromNotify = true;
                return;
            }
        }
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPresenter(DataDetailFragmentContract.DetailFragmentPresenter detailFragmentPresenter) {
        if (detailFragmentPresenter instanceof qmc) {
            this.mDetailFragmentPresenter = (qmc) detailFragmentPresenter;
        }
        initChart();
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void refreshView(boolean z) {
        ChartEyeView chartEyeView = this.mChartEyeView;
        if (chartEyeView != null) {
            chartEyeView.d();
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.chart_constraint_layout);
        if (constraintLayout != null) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.setDimensionRatio(R.id.blood_sugar_detail_viewpager, z ? "w,16:6" : "w,16:9");
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setDateStamp(long j) {
        if (j == 1) {
            resetChart();
            return;
        }
        setBloodSugarDietMonitorView(true);
        this.mBloodSugarLineChartHolder.dvW_(this.mContext, this.mHandle);
        if (this.mLastTimestamp != j) {
            this.mLastTimestamp = j;
            this.mBloodSugarLineChart.e();
            setLastTimeForDataPlatform();
        }
        if (this.mLastTimestamp > 0) {
            onSelect(this.mShowDataType);
        }
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPageType(Constants.PageType pageType) {
        if (pageType == Constants.PageType.DAY) {
            this.mDataInfos = DataInfos.BloodSugarDayDetail;
        } else if (pageType == Constants.PageType.WEEK) {
            this.mDataInfos = DataInfos.BloodSugarWeekDetail;
        } else {
            this.mDataInfos = DataInfos.BloodSugarMonthDetail;
        }
        this.mPageType = pageType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCardView(int i, int i2) {
        LogUtil.c(TAG, Integer.valueOf(this.mCurrentCardType), " refreshCardView cardType ", Integer.valueOf(i));
        if (this.mCurrentCardType != i) {
            this.mCurrentCardType = i;
            setCardView(i, i2);
            refreshDayView();
            return;
        }
        LogUtil.a(TAG, "refreshCardView no data to refresh");
    }

    private void refreshDayView() {
        DataInfos dataInfos = this.mDataInfos;
        if (dataInfos != null) {
            LogUtil.c(TAG, "refreshDayView mDataInfos.isDayData() ", Boolean.valueOf(dataInfos.isDayData()));
            if ("BLOOD_SUGAR_FINGER_TIP".equals(this.mChartEyeView.getCardType()) && this.mDataInfos.isDayData()) {
                this.mChartEyeView.setVisibility(8);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onResume() {
        setBloodSugarDietMonitorView(true);
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onPause() {
        this.mBloodSugarAnalysisView.setRefreshDiet(true);
        this.mBloodSugarAnalysisView.setRefreshSport(true);
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDestory() {
        if ((this.mIsIntoMonth && this.mDataInfos.isMonthData()) || (this.mIsIntoWeek && this.mDataInfos.isWeekData())) {
            setBiClickTime(this.mShowDataType, this.mBiStartTime, System.currentTimeMillis());
        }
        super.onDestroy();
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDayWeekYear(int i) {
        if (i == 2) {
            setMonthClick();
        } else if (i == 1) {
            setWeekClick();
        } else {
            LogUtil.h(TAG, "is not in month or week");
        }
        this.mChartEyeView.a();
        if ("BLOOD_SUGAR_FINGER_TIP".equals(this.mChartEyeView.getCardType())) {
            return;
        }
        this.mChartEyeView.setRefreshViewType(i);
        if (this.mDataInfos.isDayData()) {
            return;
        }
        this.mChartEyeView.c(i);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void setLiftAndRightImage() {
        if (LanguageUtil.bc(this.mContext)) {
            this.mButtonLift.setBackground(this.mContext.getResources().getDrawable(R.drawable._2131427831_res_0x7f0b01f7));
            this.mButtonRight.setBackground(this.mContext.getResources().getDrawable(R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.mButtonLift.setBackground(this.mContext.getResources().getDrawable(R.drawable._2131427825_res_0x7f0b01f1));
            this.mButtonRight.setBackground(this.mContext.getResources().getDrawable(R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void initViewPager() {
        this.mButtonLift.setOnClickListener(this);
        this.mButtonRight.setOnClickListener(this);
        this.mTextDate.setOnClickListener(this);
        this.mChartEyeView.setListener(this);
        this.mChartEyeView.setVisibility(8);
        this.mBloodSugarViewList = new ArrayList<>(16);
        this.mBarChartPagerAdapter = new qac(this.mBloodSugarViewList);
        this.mBloodSugarViewPager.setIsCompatibleScrollView(true);
        this.mBloodSugarViewPager.setIsScroll(false);
        this.mBloodSugarViewPager.setAdapter(this.mBarChartPagerAdapter);
    }

    @Override // com.huawei.ui.main.stories.health.views.charteye.MultiViewDataObserverView.OnSelectListener
    public void onSelect(String str) {
        BloodSugarLineChart bloodSugarLineChart;
        LogUtil.a(TAG, "onSelect");
        showDietRemindLayout(str);
        this.mBloodSugarAnalysisView.setShowType(str);
        if (!this.mShowDataType.equals(str)) {
            long currentTimeMillis = System.currentTimeMillis();
            setBiClickTime(str, this.mBiStartTime, currentTimeMillis);
            this.mBiStartTime = currentTimeMillis;
            setBiClick(str);
        }
        switchShowDataType(str);
        if (this.mBloodSugarLineChartHolder == null || (bloodSugarLineChart = this.mBloodSugarLineChart) == null || bloodSugarLineChart.acquireScrollAdapter() == null) {
            return;
        }
        this.mBloodSugarAnalysisView.c();
        this.mBloodSugarLineChart.setWillNotDraw(false);
        this.mBloodSugarLineChart.setShowDataType(str);
        this.mBloodSugarLineChartHolder.b(str);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.mBloodSugarLineChart.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
        this.mBloodSugarLineChart.setMarkerViewPosition(null);
        this.mBloodSugarLineChart.refresh();
    }

    private void showDietRemindLayout(String str) {
        if (this.mDataInfos.isDayData()) {
            LinearLayout linearLayout = null;
            for (ViewParent parent = getParent(); linearLayout == null && (parent instanceof ViewGroup); parent = parent.getParent()) {
                linearLayout = (LinearLayout) ((ViewGroup) parent).findViewById(R.id.layout_blood_sugar_remind);
            }
            Integer num = (Integer) rzh.d("blood_sugar_remind_count", Integer.class);
            if (linearLayout == null || num == null) {
                return;
            }
            if (str.equals("BLOOD_SUGAR_CONTINUE") && qqu.e(this.mContext) && num.intValue() > 0) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
        }
    }

    private void setWeekClick() {
        if (this.mIsLeaveDay || !this.mDataInfos.isWeekData()) {
            return;
        }
        setCommonBiClick(true);
    }

    private void setMonthClick() {
        if (this.mIsLeaveDay || !this.mDataInfos.isMonthData()) {
            return;
        }
        setCommonBiClick(false);
    }

    private void setCommonBiClick(boolean z) {
        setBiClick(this.mShowDataType);
        this.mBiStartTime = System.currentTimeMillis();
        this.mIsLeaveDay = true;
        if (z) {
            this.mIsIntoWeek = true;
        } else {
            this.mIsIntoMonth = true;
        }
    }

    private void setBiClick(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (this.mDataInfos.isMonthData()) {
            hashMap.put("type", 4);
        } else if (!this.mDataInfos.isWeekData()) {
            return;
        } else {
            hashMap.put("type", 3);
        }
        setBiPosition(str, hashMap);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), hashMap, 0);
    }

    private void setBiClickTime(String str, long j, long j2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (this.mDataInfos.isMonthData()) {
            hashMap.put("type", 4);
        } else if (!this.mDataInfos.isWeekData()) {
            return;
        } else {
            hashMap.put("type", 3);
        }
        setBiPosition(str, hashMap);
        hashMap.put("startTime", Long.valueOf(j));
        hashMap.put("endTime", Long.valueOf(j2));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), hashMap, 0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void setBiPosition(String str, Map<String, Object> map) {
        boolean z;
        str.hashCode();
        int i = 6;
        switch (str.hashCode()) {
            case -2010807240:
                if (str.equals("BLOOD_SUGAR_NIGHT_BLOOD_GLUGLUCOSE")) {
                    z = false;
                    break;
                }
                z = -1;
                break;
            case -1381998449:
                if (str.equals("BLOOD_SUGAR_BEFORE_SLEEP_BlOOD_GLUCOSE")) {
                    z = true;
                    break;
                }
                z = -1;
                break;
            case -714580043:
                if (str.equals("BLOOD_SUGAR_BEFORE_MEAL")) {
                    z = 2;
                    break;
                }
                z = -1;
                break;
            case -495139084:
                if (str.equals("BLOOD_SUGAR_AFTER_MEAL")) {
                    z = 3;
                    break;
                }
                z = -1;
                break;
            case 93616304:
                if (str.equals("BLOOD_SUGAR_LIMOSIS")) {
                    z = 4;
                    break;
                }
                z = -1;
                break;
            case 165952772:
                if (str.equals("BLOOD_SUGAR_BEFORE_AFTER_MEAL_DIFFERENCE")) {
                    z = 5;
                    break;
                }
                z = -1;
                break;
            case 1164722869:
                if (str.equals("BLOOD_SUGAR_CONTINUE")) {
                    z = 6;
                    break;
                }
                z = -1;
                break;
            case 1214310707:
                if (str.equals("BLOOD_SUGAR_FINGER_TIP")) {
                    z = 7;
                    break;
                }
                z = -1;
                break;
            default:
                z = -1;
                break;
        }
        switch (z) {
            case false:
                i = 7;
                break;
            case true:
                break;
            case true:
                i = 3;
                break;
            case true:
                i = 4;
                break;
            case true:
                i = 2;
                break;
            case true:
                i = 5;
                break;
            case true:
                i = 8;
                break;
            case true:
                i = 1;
                break;
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            map.put(FunctionSetBeanReader.BI_ELEMENT, Integer.valueOf(i));
        }
    }

    private void notifyView(List<HwHealthMarkerView.a> list) {
        if (this.mMarkSelectedModelList == null) {
            this.mMarkSelectedModelList = new ArrayList(16);
        }
        filterSelectedEntries(list);
        if (this.mMarkSelectedModelList.size() <= 0) {
            this.mValueLayoutDouble.setVisibility(8);
            this.mValueLayoutSingle.setVisibility(0);
            this.mCursorValueTitleHigh.setVisibility(8);
            this.mCursorValueTitle.setVisibility(8);
            this.mCursorValueSingle.setText("--");
            this.mCursorValueSingleUnit.setText("");
            this.mCursorStatusLayoutSingle.setVisibility(4);
            this.mCursorStatusLayoutHigh.setVisibility(8);
            this.mCursorStatusLayout.setVisibility(8);
            return;
        }
        if (this.mMarkSelectedModelList.size() == 1) {
            this.mValueLayoutDouble.setVisibility(8);
            this.mValueLayoutSingle.setVisibility(0);
            this.mCursorValueTitleHigh.setVisibility(8);
            this.mCursorValueTitle.setVisibility(8);
            this.mCursorStatusLayoutSingle.setVisibility(0);
            this.mCursorStatusLayoutHigh.setVisibility(8);
            this.mCursorStatusLayout.setVisibility(8);
            notifySingleSugar(this.mMarkSelectedModelList.get(0));
            if (this.mDataInfos.isDayData()) {
                return;
            }
            this.mCursorTime.setText(getDateToString(this.mMarkSelectedModelList.get(0).k(), 0L));
            return;
        }
        this.mValueLayoutDouble.setVisibility(0);
        this.mValueLayoutSingle.setVisibility(8);
        this.mCursorValueTitleHigh.setVisibility(0);
        this.mCursorValueTitle.setVisibility(0);
        this.mCursorStatusLayoutSingle.setVisibility(8);
        this.mCursorStatusLayoutHigh.setVisibility(0);
        this.mCursorStatusLayout.setVisibility(0);
        Collections.sort(this.mMarkSelectedModelList, new a());
        List<pzy> list2 = this.mMarkSelectedModelList;
        notifyLowSugar(list2.get(list2.size() - 1));
        notifyHighSugar(this.mMarkSelectedModelList.get(0));
        HealthTextView healthTextView = this.mCursorTime;
        List<pzy> list3 = this.mMarkSelectedModelList;
        healthTextView.setText(getDateToString(list3.get(list3.size() - 1).k(), this.mMarkSelectedModelList.get(0).k()));
    }

    private void filterSelectedEntries(List<HwHealthMarkerView.a> list) {
        this.mMarkSelectedModelList.clear();
        if (koq.b(list)) {
            LogUtil.h(TAG, "The MarkerView notification data is null or empty");
            return;
        }
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList(16);
        for (HwHealthMarkerView.a aVar : list) {
            HwHealthBaseEntry hwHealthBaseEntry = aVar.b;
            if (aVar.f8900a && hwHealthBaseEntry != null && aVar.e != null) {
                ArrayList<HwHealthBaseEntry> arrayList2 = new ArrayList(16);
                if (koq.e(aVar.e.getValues(), HwHealthBaseEntry.class)) {
                    arrayList2.addAll(aVar.e.getValues());
                }
                for (HwHealthBaseEntry hwHealthBaseEntry2 : arrayList2) {
                    if (hwHealthBaseEntry2.getX() == hwHealthBaseEntry.getX()) {
                        arrayList.add(hwHealthBaseEntry2);
                    }
                }
            }
        }
        for (HwHealthBaseEntry hwHealthBaseEntry3 : arrayList) {
            if (hwHealthBaseEntry3.getData() instanceof pzy) {
                pzy pzyVar = (pzy) hwHealthBaseEntry3.getData();
                if (!pzyVar.f() && pzyVar.c().equals(this.mShowDataType)) {
                    this.mMarkSelectedModelList.add(pzyVar);
                }
            }
        }
    }

    private void setToolbarReport(BasePresenter basePresenter) {
        if (this.mDataInfos.isWeekData() && (basePresenter instanceof HealthDetailCommonActivityPresenter)) {
            ((HealthDetailCommonActivityPresenter) basePresenter).setChartStartTimeAndEndTime(this.mStartTime, this.mEndTime - 1000);
        }
    }

    private void setToolbarHistory(BasePresenter basePresenter) {
        if (basePresenter instanceof HealthDetailCommonActivityPresenter) {
            DateType dateType = DateType.DATE_DAY;
            if (this.mDataInfos.isWeekData()) {
                dateType = DateType.DATE_WEEK;
            } else if (this.mDataInfos.isMonthData()) {
                dateType = DateType.DATE_MONTH;
            } else {
                LogUtil.a(TAG, "BloodSugar is not support DateType");
            }
            ((HealthDetailCommonActivityPresenter) basePresenter).notifyChartDateStatus(dateType, false, false);
        }
    }

    static class a implements Comparator<pzy>, Serializable {
        private static final long serialVersionUID = 3968849440072396774L;

        private a() {
        }

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(pzy pzyVar, pzy pzyVar2) {
            return Float.compare(pzyVar2.a(), pzyVar.a());
        }
    }

    private void setLayoutOnClickListener(final View view, final pzy pzyVar) {
        if (pzyVar == null || view == null) {
            LogUtil.a(TAG, "setLayoutOnClickListener bloodSugarStorageModel or layout is null");
        } else {
            view.setOnClickListener(new View.OnClickListener() { // from class: pzl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    BloodSugarLineChartView.this.m827xecbfc46b(view, pzyVar, view2);
                }
            });
        }
    }

    /* renamed from: lambda$setLayoutOnClickListener$0$com-huawei-ui-main-stories-fitness-views-bloodsugar-BloodSugarLineChartView, reason: not valid java name */
    public /* synthetic */ void m827xecbfc46b(View view, pzy pzyVar, View view2) {
        if (this.mMarkSelectedModelList.isEmpty() || "BLOOD_SUGAR_CONTINUE".equals(this.mShowDataType)) {
            ViewClickInstrumentation.clickOnView(view2);
            return;
        }
        if ("BLOOD_SUGAR_BEFORE_AFTER_MEAL_DIFFERENCE".equals(this.mShowDataType)) {
            if (view.getId() == R.id.cursor_status_layout_high || view.getId() == R.id.cursor_value_high) {
                startActivityWithParams(this.mCursorStatusFoodHigh.getText().toString(), pzyVar);
            } else if (view.getId() == R.id.cursor_status_layout || view.getId() == R.id.cursor_value) {
                startActivityWithParams(this.mCursorStatusFood.getText().toString(), pzyVar);
            } else {
                startActivityWithParams(this.mCursorStatusFoodSingle.getText().toString(), pzyVar);
            }
            ViewClickInstrumentation.clickOnView(view2);
            return;
        }
        Intent intent = new Intent();
        if (pzyVar.l()) {
            intent.setClass(this.mContext, BloodSugarDeviceMeasureActivity.class);
            intent.putExtra(BLOOD_SUGAR_TO_DEVICE_MEASURE_KEY, BLOOD_SUGAR_TO_DEVICE_MEASURE_VALUE);
            intent.putExtra("start_time", pzyVar.k());
            intent.putExtra(BLOOD_SUGAR_TO_DEVICE_MEASURE_PERIOD_KEY, pzyVar.o());
        } else {
            intent.setClass(this.mContext, BloodSugarFeedbackActivity.class);
            intent.putExtra("bloodTimePeriod", pzyVar.o());
            intent.putExtra("time", pzyVar.k());
            intent.putExtra("bloodNum", new BigDecimal(String.valueOf(pzyVar.a())).doubleValue());
            intent.putExtra("isEdit", true);
            intent.putExtra("clientId", pzyVar.b());
            intent.putExtra(ParsedFieldTag.TASK_MODIFY_TIME, pzyVar.i());
            intent.putExtra("pageFrom", 1);
            if (isDataFromBloodDevice(pzyVar.g())) {
                intent.putExtra("bloodSugarDataIsFromMeter", false);
                intent.putExtra("titleName", this.mContext.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_history));
            } else {
                intent.putExtra("bloodSugarDataIsFromMeter", true);
                intent.putExtra("titleName", this.mContext.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_measurement_record));
            }
        }
        gnm.aPB_(this.mContext, intent);
        ViewClickInstrumentation.clickOnView(view2);
    }

    private void startActivityWithParams(String str, pzy pzyVar) {
        Intent intent = new Intent(this.mContext, (Class<?>) BloodSugarDiffActivity.class);
        if (this.mContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_breakfast_difference).equals(str)) {
            constructIntent(intent, R$string.IDS_hw_show_healthdata_bloodsugar_breakfast_difference, R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast, R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast);
        } else if (this.mContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_lunch_difference).equals(str)) {
            constructIntent(intent, R$string.IDS_hw_show_healthdata_bloodsugar_lunch_difference, R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch, R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch);
        } else if (this.mContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_dinner_difference).equals(str)) {
            constructIntent(intent, R$string.IDS_hw_show_healthdata_bloodsugar_dinner_difference, R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner, R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner);
        } else {
            LogUtil.h(TAG, "startActivityWithParams param can not null");
            return;
        }
        if (pzyVar == null || pzyVar.e() == null || pzyVar.d() == null) {
            LogUtil.h(TAG, "startActivityWithParams model , beforeModel or afterModel can not null");
            return;
        }
        pzy e = pzyVar.e();
        pzy d = pzyVar.d();
        intent.putExtra("bloodSugar_diff_time", timestampToString(Long.valueOf(pzyVar.k()), true));
        intent.putExtra("bloodSugar_diff_number", pzyVar.a());
        intent.putExtra("bloodSugar_diff_status", pzyVar.j());
        intent.putExtra("bloodSugar_diff_befor_title", e.a());
        intent.putExtra("bloodSugar_diff_befor_status", e.j());
        intent.putExtra("bloodSUgar_diff_befor_time", timestampToString(Long.valueOf(e.k()), false));
        intent.putExtra("bloodSugar_diff_after_title", d.a());
        intent.putExtra("bloodSugar_diff_after_status", d.j());
        intent.putExtra("bloodSugar_diff_after_time", timestampToString(Long.valueOf(d.k()), false));
        intent.putExtra("clientId", pzyVar.b());
        intent.putExtra(ParsedFieldTag.TASK_MODIFY_TIME, pzyVar.i());
        gnm.aPB_(this.mContext, intent);
    }

    private String timestampToString(Long l, boolean z) {
        return new SimpleDateFormat(z ? "yyyy/MM/dd HH:mm" : "HH:mm").format(l);
    }

    private void constructIntent(Intent intent, int i, int i2, int i3) {
        intent.putExtra("bloodSugar_diff_title", this.mContext.getString(i));
        intent.putExtra("bloodSugar_diff_befor_message", this.mContext.getString(i2));
        intent.putExtra("bloodSugar_diff_after_message", this.mContext.getString(i3));
    }

    private static boolean isDataFromBloodDevice(String str) {
        return str == null || "-1".equals(str);
    }

    private void setBloodSugarTypeValue(int i, HealthTextView healthTextView) {
        String str = this.mShowDataType;
        str.hashCode();
        if (str.equals("BLOOD_SUGAR_LIMOSIS")) {
            healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast));
        } else if (str.equals("BLOOD_SUGAR_BEFORE_AFTER_MEAL_DIFFERENCE")) {
            bloodSugarDiffTypeValue(i, healthTextView);
        } else {
            bloodSugarTypeValue(i, healthTextView);
        }
    }

    private void setDigitalLocalization(HealthTextView healthTextView, HealthTextView healthTextView2, pzy pzyVar) {
        if (healthTextView == null || pzyVar == null) {
            LogUtil.h(TAG, "setDigitalLocalization view or bloodSugarStorageModel is null");
            return;
        }
        double a2 = UnitUtil.a(Math.abs(pzyVar.a()), 1);
        String e = UnitUtil.e(a2, 1, 1);
        if (healthTextView2 == null) {
            e = this.mContext.getResources().getQuantityString(R.plurals._2130903076_res_0x7f030024, UnitUtil.e(a2), e);
        } else {
            healthTextView2.setText(this.mContext.getResources().getQuantityString(R.plurals._2130903076_res_0x7f030024, UnitUtil.e(a2), ""));
        }
        LogUtil.c(TAG, "setDigitalLocalization sugarData is ", e);
        healthTextView.setText(UnitUtil.bCR_(this.mContext, "[\\.\\d\\,]", e, R.style.health_blood_card_cursor_value, R.style.health_blood_card_cursor_unit));
    }

    private void notifySingleSugar(pzy pzyVar) {
        setDigitalLocalization(this.mCursorValueSingle, this.mCursorValueSingleUnit, pzyVar);
        setBloodSugarTypeValue(pzyVar.o(), this.mCursorStatusFoodSingle);
        bloodSugarStatusValue(pzyVar.j(), this.mCursorStatusValueSingle);
        setLayoutOnClickListener(this.mValueLayoutSingle, pzyVar);
        if (pzyVar.l()) {
            this.mCursorCommitSingle.setVisibility(0);
            this.mCursorCommitLineSingle.setVisibility(0);
        } else {
            this.mCursorCommitSingle.setVisibility(8);
            this.mCursorCommitLineSingle.setVisibility(8);
        }
    }

    private void notifyLowSugar(pzy pzyVar) {
        setDigitalLocalization(this.mCursorValue, null, pzyVar);
        setBloodSugarTypeValue(pzyVar.o(), this.mCursorStatusFood);
        bloodSugarStatusValue(pzyVar.j(), this.mCursorStatusValue);
        setLayoutOnClickListener(this.mCursorStatusLayout, pzyVar);
        setLayoutOnClickListener(this.mCursorValue, pzyVar);
        if (pzyVar.l()) {
            this.mCursorCommit.setVisibility(0);
            this.mCursorStatusArrow.setVisibility(0);
            if (this.mIsChinese) {
                this.mCursorCommitLine.setVisibility(0);
                return;
            }
            return;
        }
        this.mCursorCommit.setVisibility(8);
        this.mCursorStatusArrow.setVisibility(8);
        if (this.mIsChinese) {
            this.mCursorCommitLine.setVisibility(8);
        }
    }

    private void notifyHighSugar(pzy pzyVar) {
        setDigitalLocalization(this.mCursorValueHigh, null, pzyVar);
        setBloodSugarTypeValue(pzyVar.o(), this.mCursorStatusFoodHigh);
        bloodSugarStatusValue(pzyVar.j(), this.mCursorStatusValueHigh);
        setLayoutOnClickListener(this.mCursorStatusLayoutHigh, pzyVar);
        setLayoutOnClickListener(this.mCursorValueHigh, pzyVar);
        if (pzyVar.l()) {
            this.mCursorCommitHigh.setVisibility(0);
            this.mCursorStatusArrowHigh.setVisibility(0);
            if (this.mIsChinese) {
                this.mCursorCommitLineHigh.setVisibility(0);
                return;
            }
            return;
        }
        this.mCursorCommitHigh.setVisibility(8);
        this.mCursorStatusArrowHigh.setVisibility(8);
        if (this.mIsChinese) {
            this.mCursorCommitLineHigh.setVisibility(8);
        }
    }

    public String getDateToString(long j, long j2) {
        String formatDateTime;
        String formatDateTime2 = DateUtils.formatDateTime(getContext(), j, 153);
        if (j2 <= 0) {
            return formatDateTime2;
        }
        if (this.mDataInfos.isDayData()) {
            return DateUtils.formatDateTime(getContext(), j, 129) + CONNECTING_LINE + getFormatDateTime(j, THERE_MINUTE);
        }
        if (this.mDataInfos.isWeekData()) {
            formatDateTime = getFormatDateTime(j, 600000);
        } else if (this.mDataInfos.isMonthData()) {
            formatDateTime = getFormatDateTime(j, 3600000);
        } else {
            formatDateTime = DateUtils.formatDateTime(getContext(), j2, 129);
        }
        return formatDateTime2 + CONNECTING_LINE + formatDateTime;
    }

    private String getFormatDateTime(long j, int i) {
        long j2 = j + i;
        long time = new Date().getTime();
        if (j2 > time) {
            j2 = time;
        }
        return DateUtils.formatDateTime(getContext(), j2, 129);
    }

    private void bloodSugarStatusValue(int i, HealthTextView healthTextView) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 1001:
                        setFootText(healthTextView, R$string.IDS_hw_show_healthdata_bloodsugar_status_too_low, R.color._2131296797_res_0x7f09021d);
                        break;
                    case 1002:
                        setFootText(healthTextView, R$string.IDS_hw_health_show_healthdata_status_low, R.color._2131296797_res_0x7f09021d);
                        break;
                    case 1003:
                        break;
                    case 1004:
                        break;
                    case 1005:
                    case 1006:
                        setFootText(healthTextView, R$string.IDS_hw_show_healthdata_bloodsugar_status_too_high, R.color._2131296795_res_0x7f09021b);
                        break;
                    default:
                        LogUtil.a(TAG, "no support blood type.");
                        break;
                }
            }
            setFootText(healthTextView, R$string.IDS_hw_health_show_healthdata_status_high, R.color._2131296795_res_0x7f09021b);
            return;
        }
        setFootText(healthTextView, R$string.IDS_hw_health_show_healthdata_status_normal, R.color._2131296801_res_0x7f090221);
    }

    private void setFootText(HealthTextView healthTextView, int i, int i2) {
        healthTextView.setText(getResources().getString(i));
        healthTextView.setTextColor(getResources().getColor(i2));
    }

    private void bloodSugarDiffTypeValue(int i, HealthTextView healthTextView) {
        if (i == 2008) {
            healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_breakfast_difference));
            return;
        }
        if (i == 2010) {
            healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_lunch_difference));
        } else if (i == 2012) {
            healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_dinner_difference));
        } else {
            LogUtil.a(TAG, "no support blood type");
        }
    }

    private void bloodSugarTypeValue(int i, HealthTextView healthTextView) {
        if (i != 2106) {
            switch (i) {
                case 2008:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast));
                    break;
                case 2009:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast));
                    break;
                case 2010:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch));
                    break;
                case 2011:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch));
                    break;
                case 2012:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner));
                    break;
                case 2013:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner));
                    break;
                case 2014:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep));
                    break;
                case 2015:
                    healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_early_morning));
                    break;
                default:
                    LogUtil.a(TAG, "no support blood type");
                    break;
            }
        }
        healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_random_time));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void initChart() {
        this.mBloodSugarLineChartHolder = new pzg(this.mContext, this.mDataInfos);
        if (this.mBloodSugarLineChart == null) {
            BloodSugarLineChart bloodSugarLineChart = new BloodSugarLineChart(this.mContext, this.mDataInfos);
            this.mBloodSugarLineChart = bloodSugarLineChart;
            bloodSugarLineChart.setLayerType(1, null);
            initPagerNoMoreListener();
            this.mBloodSugarViewList.clear();
            this.mBloodSugarViewList.add(this.mBloodSugarLineChart);
            this.mBloodSugarLineChartHolder.addDataLayer((pzg) this.mBloodSugarLineChart, this.mDataInfos);
            this.mBarChartPagerAdapter.notifyDataSetChanged();
            if (this.mBloodSugarLineChart.acquireScrollAdapter() != null) {
                this.mBloodSugarLineChart.acquireScrollAdapter().acquireXAxisValueFormatter().setHealthType(HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_BLOOD_SUGAR);
                if (!this.mDataInfos.isDayData()) {
                    this.mBloodSugarLineChart.acquireScrollAdapter().setBuffRang(1);
                } else {
                    this.mBloodSugarLineChart.acquireScrollAdapter().setBuffRang(0);
                }
            }
            setLastTimeForDataPlatform();
            setBloodSugarLineChartCallback();
        }
        this.mButtonLift.setVisibility(0);
        this.mOlderSwitchBtnClickable.set(true);
        this.mButtonLift.setClickable(true);
        this.mButtonRight.setVisibility(4);
        this.mNewerSwitchBtnClickable.set(false);
        this.mButtonRight.setClickable(false);
    }

    private void setBloodSugarLineChartCallback() {
        this.mBloodSugarLineChart.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: pzn
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public final void onRangeShow(int i, int i2) {
                BloodSugarLineChartView.this.m824xcfc5272c(i, i2);
            }
        });
        this.mBloodSugarLineChart.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: pzk
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public final void onTextChanged(String str, List list) {
                BloodSugarLineChartView.this.m825xd093a5ad(str, list);
            }
        });
        this.mBloodSugarLineChart.setBloodSugarLineChartInterface(new BloodSugarLineChartInterface() { // from class: pzm
            @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartInterface
            public final void onDrawDataCallback(Map map) {
                BloodSugarLineChartView.this.m826xd162242e(map);
            }
        });
    }

    /* renamed from: lambda$setBloodSugarLineChartCallback$1$com-huawei-ui-main-stories-fitness-views-bloodsugar-BloodSugarLineChartView, reason: not valid java name */
    public /* synthetic */ void m824xcfc5272c(int i, int i2) {
        this.mNewStartX = i;
        this.mNewEndX = i2;
        if (nsj.a(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
            this.mCalendar = qrp.a(this.mCalendar, i);
        }
        ((pzq) this.mPresenter).notifyData(i, i2);
    }

    /* renamed from: lambda$setBloodSugarLineChartCallback$2$com-huawei-ui-main-stories-fitness-views-bloodsugar-BloodSugarLineChartView, reason: not valid java name */
    public /* synthetic */ void m825xd093a5ad(String str, List list) {
        this.mCalendar = qrp.a(this.mCalendar, nom.h((int) this.mBloodSugarLineChart.fetchMarkViewMinuteValue()));
        ((pzq) this.mPresenter).notifyCursorDataAndTime(str, list);
        if (this.mBloodSugarLineChart.isAnimating()) {
            return;
        }
        if (this.mOldStartX == this.mNewStartX && this.mShowDataType.equals(this.mOldShowDataType)) {
            return;
        }
        this.mOldStartX = this.mNewStartX;
        this.mOldShowDataType = this.mShowDataType;
    }

    private void resetChart() {
        this.mBloodSugarLineChartHolder = null;
        BloodSugarLineChart bloodSugarLineChart = this.mBloodSugarLineChart;
        if (bloodSugarLineChart != null) {
            bloodSugarLineChart.a();
        }
        this.mBloodSugarLineChart = null;
        this.mLastTimestamp = System.currentTimeMillis();
        initChart();
        notifyView(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fetchChartStorageModel, reason: merged with bridge method [inline-methods] */
    public void m826xd162242e(Map<Long, IStorageModel> map) {
        this.mStartTime = this.mNewStartX * 60000;
        this.mEndTime = this.mNewEndX * 60000;
        final HashMap hashMap = new HashMap(10);
        if (map != null) {
            for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
                IStorageModel value = entry.getValue();
                if (value instanceof pzy) {
                    long longValue = entry.getKey().longValue();
                    if (((pzy) value).c().equals(this.mShowDataType) && this.mStartTime <= longValue && longValue <= this.mEndTime) {
                        hashMap.put(Long.valueOf(longValue), value);
                    }
                }
            }
        }
        Context context = this.mContext;
        if (context instanceof HealthMvpActivity) {
            ((HealthMvpActivity) context).runOnUiThread(new Runnable() { // from class: pzo
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarLineChartView.this.m823xd7eb31d0(hashMap);
                }
            });
        }
    }

    /* renamed from: lambda$fetchChartStorageModel$4$com-huawei-ui-main-stories-fitness-views-bloodsugar-BloodSugarLineChartView, reason: not valid java name */
    public /* synthetic */ void m823xd7eb31d0(Map map) {
        setToolbarReport(((HealthMvpActivity) this.mContext).getPresenter());
        setToolbarHistory(((HealthMvpActivity) this.mContext).getPresenter());
        setDetectionDistributionView(map);
        setDifferView(map);
    }

    private void setDifferView(Map<Long, IStorageModel> map) {
        if (this.mDataInfos.isDayData() && "BLOOD_SUGAR_FINGER_TIP".equals(this.mShowDataType) && !map.isEmpty()) {
            this.mBloodSugarAnalysisView.setDiffData(map, nom.h((int) this.mBloodSugarLineChart.acquireShowRangeMinimum()));
        } else {
            this.mBloodSugarAnalysisView.e();
        }
    }

    private void showSportDifferView() {
        if (this.mDataInfos.isDayData() && "BLOOD_SUGAR_FINGER_TIP".equals(this.mShowDataType)) {
            this.mBloodSugarAnalysisView.setSportDiffView(nom.h((int) this.mBloodSugarLineChart.acquireShowRangeMinimum()));
        } else {
            this.mBloodSugarAnalysisView.e();
            this.mBloodSugarAnalysisView.c();
        }
    }

    private void getPointMap() {
        this.mHandle.removeCallbacks(this.mRefreshRunnable);
        this.mHandle.postDelayed(this.mRefreshRunnable, 500L);
    }

    private void setDetectionDistributionView(Map<Long, IStorageModel> map) {
        if (this.mDataInfos.isDayData() && "BLOOD_SUGAR_FINGER_TIP".equals(this.mShowDataType)) {
            this.mDetailFragmentPresenter.a(this.mShowDataType, null);
        } else {
            this.mDetailFragmentPresenter.a(this.mShowDataType, map);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void clickLeftArrow() {
        BloodSugarLineChart bloodSugarLineChart = this.mBloodSugarLineChart;
        Objects.requireNonNull(bloodSugarLineChart);
        bloodSugarLineChart.scrollOnePageOlder(new pzr(this, bloodSugarLineChart));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void clickRightArrow() {
        BloodSugarLineChart bloodSugarLineChart = this.mBloodSugarLineChart;
        Objects.requireNonNull(bloodSugarLineChart);
        bloodSugarLineChart.scrollOnePageNewer(new pzp(this, bloodSugarLineChart));
    }

    private void showContinueBloodSugarCard(long j) {
        if (this.mDataInfos.isDayData() && "BLOOD_SUGAR_CONTINUE".equals(this.mShowDataType)) {
            this.mBloodSugarSportMonitoringView.setDate(j);
            this.mBloodSugarNocturnalHypoglycemiaCardView.setDate(j);
            if (this.mIsFirstShowHypoglycemiaCardView && this.mIsJumpFromNotify) {
                this.mIsFirstShowHypoglycemiaCardView = false;
                ViewParent parent = getParent();
                while (!(parent instanceof ScrollView)) {
                    parent = parent.getParent();
                    if (parent == null) {
                        return;
                    }
                }
                final ScrollView scrollView = (ScrollView) parent;
                scrollView.post(new Runnable() { // from class: pzf
                    @Override // java.lang.Runnable
                    public final void run() {
                        BloodSugarLineChartView.this.m828x9489a4ef(scrollView);
                    }
                });
                return;
            }
            return;
        }
        this.mBloodSugarSportMonitoringView.setVisibility(8);
        this.mBloodSugarSportMonitoringView.a();
        this.mBloodSugarNocturnalHypoglycemiaCardView.setVisibility(8);
        this.mBloodSugarNocturnalHypoglycemiaCardView.c();
    }

    /* renamed from: lambda$showContinueBloodSugarCard$5$com-huawei-ui-main-stories-fitness-views-bloodsugar-BloodSugarLineChartView, reason: not valid java name */
    public /* synthetic */ void m828x9489a4ef(ScrollView scrollView) {
        int i;
        int height;
        int[] iArr = new int[2];
        if (this.mChartEyeView.getVisibility() == 0) {
            this.mChartEyeView.getLocationOnScreen(iArr);
            i = iArr[1];
            height = this.mChartEyeView.getHeight();
        } else {
            this.mBloodSugarLineChart.getLocationOnScreen(iArr);
            i = iArr[1];
            height = this.mBloodSugarLineChart.getHeight();
        }
        scrollView.scrollTo(0, i + height);
    }

    private void setBloodSugarDietMonitorView(boolean z) {
        if (!this.mDataInfos.isDayData() || this.mBloodSugarLineChart == null) {
            return;
        }
        if (this.mDietMonitorViewHolder == null) {
            this.mDietMonitorViewHolder = new pzb(this.mContext, this.mDietMonitorView);
        }
        long h = nom.h((int) this.mBloodSugarLineChart.acquireShowRangeMinimum());
        if ("BLOOD_SUGAR_CONTINUE".equals(this.mShowDataType)) {
            this.mDietMonitorViewHolder.e(h, z);
        } else {
            this.mDietMonitorViewHolder.a();
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void setDayAndWeek(String str, String str2, boolean z) {
        showContinueBloodSugarCard(nom.h((int) this.mBloodSugarLineChart.acquireShowRangeMinimum()));
        showSportDifferView();
        setBloodSugarDietMonitorView(false);
        getPointMap();
        this.mTextDate.setText(str);
        if (this.mDataInfos.isDayData()) {
            if (z) {
                this.mButtonRight.setVisibility(4);
            } else {
                this.mButtonRight.setVisibility(0);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyNumerical(String str, List<HwHealthMarkerView.a> list) {
        HealthTextView healthTextView = this.mCursorTime;
        if (healthTextView != null) {
            healthTextView.setText(str);
        } else {
            LogUtil.h(TAG, "notifyNumerical, mCursorTime is null");
        }
        notifyView(list);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public HwHealthLineChart getLineChart() {
        return this.mBloodSugarLineChart;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mButtonLift) {
            if (!this.mOlderSwitchBtnClickable.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ((pzq) this.mPresenter).initLeftArrowClick();
        } else if (view == this.mButtonRight) {
            if (!this.mNewerSwitchBtnClickable.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ((pzq) this.mPresenter).initRightArrowClick();
        } else if (view.getId() == R.id.blood_sugar_detail_time_date_tv) {
            LogUtil.a(TAG, "v.getId() == R.id.blood_sugar_detail_time_date_tv");
            ((pzq) this.mPresenter).startCalendarWithDataType(this.mFragment, this.mCalendar, this.mShowDataType, this.mDataInfos);
        } else {
            LogUtil.h(TAG, "click view unknow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initPagerNoMoreListener() {
        this.mBloodSugarLineChart.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartView.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                BloodSugarLineChartView.this.refreshBtnImage();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                BloodSugarLineChartView.this.refreshBtnImage();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBtnImage() {
        if (this.mBloodSugarLineChart.canScrollOlderPager()) {
            this.mButtonLift.setVisibility(0);
            this.mButtonLift.setClickable(true);
            this.mOlderSwitchBtnClickable.set(true);
        } else {
            this.mButtonLift.setVisibility(4);
            this.mButtonLift.setClickable(false);
            this.mOlderSwitchBtnClickable.set(false);
        }
        if (this.mBloodSugarLineChart.canScrollNewerPager()) {
            this.mButtonRight.setVisibility(0);
            this.mButtonRight.setClickable(true);
            this.mNewerSwitchBtnClickable.set(true);
        } else {
            this.mButtonRight.setVisibility(4);
            this.mButtonRight.setClickable(false);
            this.mNewerSwitchBtnClickable.set(false);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseMvpChartView
    public void onDestroy() {
        if ((this.mIsIntoMonth && this.mDataInfos.isMonthData()) || (this.mIsIntoWeek && this.mDataInfos.isWeekData())) {
            setBiClickTime(this.mShowDataType, this.mBiStartTime, System.currentTimeMillis());
        }
        BloodSugarLineChart bloodSugarLineChart = this.mBloodSugarLineChart;
        if (bloodSugarLineChart != null) {
            bloodSugarLineChart.clear();
            this.mBloodSugarLineChart = null;
        }
        pzg pzgVar = this.mBloodSugarLineChartHolder;
        if (pzgVar != null) {
            pzgVar.c();
            this.mBloodSugarLineChartHolder = null;
        }
        Map<Long, IStorageModel> map = this.resultMap;
        if (map != null) {
            map.clear();
            this.resultMap = null;
        }
        List<pzy> list = this.mMarkSelectedModelList;
        if (list != null) {
            list.clear();
            this.mMarkSelectedModelList = null;
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshOperationsView(HiHealthData hiHealthData) {
        if (!eil.e(this.mContext)) {
            LogUtil.a(TAG, "RecommendSwitch is close.");
            return;
        }
        if (this.mDataInfos == DataInfos.BloodSugarDayDetail) {
            String str = qjv.a(this.mContext, hiHealthData.getType(), hiHealthData.getFloatValue()).get("HEALTH_BLOOD_SUGAR_LEVEL_KEY");
            if (String.valueOf(1002).equals(str)) {
                setBloodSugarEvent(22);
            } else if (String.valueOf(1004).equals(str)) {
                setBloodSugarEvent(21);
            } else {
                LogUtil.a(TAG, "Not vaild blood sugar event");
            }
        }
    }

    private void setBloodSugarEvent(int i) {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.triggerMarketingResourceEvent(new MarketingOption.Builder().setContext(this.mContext).setPageId(8).setTypeId(i).build());
        }
    }

    static class c extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<BloodSugarLineChartView> f9961a;

        c(BloodSugarLineChartView bloodSugarLineChartView) {
            super(Looper.getMainLooper());
            this.f9961a = new WeakReference<>(bloodSugarLineChartView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            BloodSugarLineChartView bloodSugarLineChartView = this.f9961a.get();
            if (bloodSugarLineChartView == null || !(message.obj instanceof HiHealthData)) {
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) message.obj;
            bloodSugarLineChartView.refreshCardView(message.arg1, hiHealthData.getType());
            bloodSugarLineChartView.refreshOperationsView(hiHealthData);
        }
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1 || intent == null) {
            return;
        }
        LogUtil.a(TAG, "mCalendar=" + this.mCalendar);
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (serializableExtra instanceof HealthCalendar) {
            this.mCalendar = (HealthCalendar) serializableExtra;
            this.mBloodSugarLineChart.reflesh(((pzq) this.mPresenter).prossCalendarSelect(this.mCalendar));
        }
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface
    public void setFragment(Fragment fragment) {
        LogUtil.a(TAG, "setFragment");
        this.mFragment = fragment;
    }
}

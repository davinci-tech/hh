package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.view.linechart.HorizontalMarkerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.MarkDateTrigger;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.anchor.Layout;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity;
import com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity;
import defpackage.eet;
import defpackage.gvv;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public abstract class JumpTableChart<ChartT extends HwHealthBaseScrollBarLineChart> extends LinearLayout {
    private static final float CURSOR_MARGIN_Y_AXIS_MARGIN = 32.0f;
    private static final float CURSOR_MARGIN_Y_AXIS_MARGIN_DAY = 50.0f;
    private static final String TAG = "JumpTableChart";
    private HealthCalendar mCalendar;
    private ChartT mChart;
    private LinearLayout mChartContainer;
    private RelativeLayout mFitnessDetailLayout;
    private ImageView mHorizontalJumpButton;
    private View mHorizontalJumpRect;
    private HorizontalMarkerView mHorizontalMarkerView;
    private ObserveredClassifiedView mHost;
    private boolean mIsShowMarkerTime;
    protected LinearLayout mMarkerTextViewPlaceHolder;
    private RelativeLayout mMarkerViewRootPlaceHolder;
    private ImageView mNewerSwitchBtn;
    private String mNoDataStr;
    private ImageView mOlderSwitchBtn;
    protected JumpableStyleMarkerTextView mSylableMarkerTextView;
    private HealthTextView mTimeArea;
    private String mTimeRange;
    protected HealthTextView mTimeTextView;
    private String mType;

    protected abstract ChartT constructChart();

    protected boolean isHorizontal() {
        return false;
    }

    public JumpTableChart(Context context) {
        super(context);
        this.mTimeTextView = null;
        this.mMarkerTextViewPlaceHolder = null;
        this.mSylableMarkerTextView = null;
        this.mTimeRange = "--";
        this.mFitnessDetailLayout = null;
        this.mIsShowMarkerTime = true;
        this.mType = "";
        init();
    }

    public JumpTableChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTimeTextView = null;
        this.mMarkerTextViewPlaceHolder = null;
        this.mSylableMarkerTextView = null;
        this.mTimeRange = "--";
        this.mFitnessDetailLayout = null;
        this.mIsShowMarkerTime = true;
        this.mType = "";
        init();
    }

    public JumpTableChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTimeTextView = null;
        this.mMarkerTextViewPlaceHolder = null;
        this.mSylableMarkerTextView = null;
        this.mTimeRange = "--";
        this.mFitnessDetailLayout = null;
        this.mIsShowMarkerTime = true;
        this.mType = "";
        init();
    }

    public JumpTableChart(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTimeTextView = null;
        this.mMarkerTextViewPlaceHolder = null;
        this.mSylableMarkerTextView = null;
        this.mTimeRange = "--";
        this.mFitnessDetailLayout = null;
        this.mIsShowMarkerTime = true;
        this.mType = "";
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.jump_table_chart, this);
        this.mNoDataStr = gvv.e(BaseApplication.getContext());
        if (this.mFitnessDetailLayout == null) {
            LogUtil.b(TAG, "mFitnessDetailLayout is null");
            this.mFitnessDetailLayout = (RelativeLayout) findViewById(R.id.fitness_detail_up_layout);
        }
        BaseActivity.setViewSafeRegion(false, this.mFitnessDetailLayout);
        this.mOlderSwitchBtn = (ImageView) findViewById(R.id.older_switch_btn);
        this.mNewerSwitchBtn = (ImageView) findViewById(R.id.newer_switch_btn);
        this.mTimeArea = (HealthTextView) findViewById(R.id.time_area);
        this.mChartContainer = (LinearLayout) findViewById(R.id.barlinechart_container);
        if (isHorizontal()) {
            this.mChartContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        }
        this.mMarkerViewRootPlaceHolder = (RelativeLayout) findViewById(R.id.marker_view_root_place_holder);
        View findViewById = findViewById(R.id.horizontal_jump_outer_rect);
        this.mHorizontalJumpRect = findViewById;
        if (findViewById.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mHorizontalJumpRect.getLayoutParams();
            layoutParams.width += nsn.c(BaseApplication.getContext(), ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
            this.mHorizontalJumpRect.setLayoutParams(layoutParams);
        }
        ImageView imageView = (ImageView) findViewById(R.id.horizontal_jump_btn);
        this.mHorizontalJumpButton = imageView;
        if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mHorizontalJumpButton.getLayoutParams();
            layoutParams2.setMarginEnd(layoutParams2.getMarginEnd() + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
            this.mHorizontalJumpButton.setLayoutParams(layoutParams2);
        }
        disablingHardwareAcceleration();
        this.mTimeTextView = (HealthTextView) findViewById(R.id.detail_markview_time);
        this.mMarkerTextViewPlaceHolder = (LinearLayout) findViewById(R.id.marker_textview_place_holder);
        if (isHorizontal()) {
            this.mTimeTextView.setVisibility(8);
            HorizontalMarkerView horizontalMarkerView = new HorizontalMarkerView(getContext());
            this.mHorizontalMarkerView = horizontalMarkerView;
            this.mMarkerTextViewPlaceHolder.addView(horizontalMarkerView, -1, -2);
        } else {
            JumpableStyleMarkerTextView jumpableStyleMarkerTextView = new JumpableStyleMarkerTextView(getContext());
            this.mSylableMarkerTextView = jumpableStyleMarkerTextView;
            this.mMarkerTextViewPlaceHolder.addView(jumpableStyleMarkerTextView, -1, -2);
        }
        initSwitchButton();
        initRTLStatus();
        initLinkChart2Text();
        initMarkViewAreaLinker();
        initPagerNoMoreListener();
    }

    private void disablingHardwareAcceleration() {
        ChartT constructChart = constructChart();
        this.mChart = constructChart;
        constructChart.setLayerType(1, null);
        this.mChart.registerExternalTopArea(this.mMarkerViewRootPlaceHolder);
        this.mChartContainer.addView(this.mChart, -1, -1);
    }

    private void initPagerNoMoreListener() {
        this.mChart.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.1
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                JumpTableChart.this.refreshBtnImage();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                JumpTableChart.this.refreshBtnImage();
            }
        });
    }

    private void initMarkViewAreaLinker() {
        if (isHorizontal()) {
            this.mChart.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.2
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                    if (JumpTableChart.this.mHorizontalMarkerView == null) {
                        return;
                    }
                    JumpTableChart.this.updateCalendar(nom.h((int) JumpTableChart.this.mChart.fetchMarkViewMinuteValue()));
                    if (!JumpTableChart.this.mIsShowMarkerTime) {
                        str = "";
                    }
                    if (koq.b(list)) {
                        JumpTableChart.this.mHorizontalMarkerView.c(str, JumpTableChart.this.mNoDataStr, JumpTableChart.this.mNoDataStr);
                    } else {
                        JumpTableChart.this.mHorizontalMarkerView.c(str, JumpTableChart.this.mHost.parse(list.get(list.size() - 1).b), list.get(list.size() - 1).d);
                    }
                }
            });
        } else {
            setOnMarkViewTextNotify();
            this.mChart.setOnMarkViewGlobalPoint(new HwHealthMarkerView.OnMarkViewGlobalPoint() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.8
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewGlobalPoint
                public void onTextGlobalPoint() {
                    JumpTableChart.this.setGlobalPointValue();
                }
            });
        }
    }

    private void setOnMarkViewTextNotify() {
        this.mChart.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.7
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                JumpTableChart.this.updateCalendar(nom.h((int) JumpTableChart.this.mChart.fetchMarkViewMinuteValue()));
                JumpTableChart.this.mTimeTextView.setVisibility(JumpTableChart.this.mIsShowMarkerTime ? 0 : 4);
                if (JumpTableChart.this.mIsShowMarkerTime) {
                    JumpTableChart jumpTableChart = JumpTableChart.this;
                    jumpTableChart.setText(jumpTableChart.mTimeTextView, str);
                } else {
                    JumpTableChart jumpTableChart2 = JumpTableChart.this;
                    jumpTableChart2.setText(jumpTableChart2.mTimeTextView, "");
                }
                int color = ContextCompat.getColor(JumpTableChart.this.getContext(), R.color._2131299236_res_0x7f090ba4);
                if (koq.b(list)) {
                    String e = gvv.e(JumpTableChart.this.getContext());
                    JumpTableChart.this.mSylableMarkerTextView.setMarkerText(e, e);
                } else {
                    JumpTableChart.this.mSylableMarkerTextView.setMarkerText(JumpTableChart.this.mHost.parse(list.get(list.size() - 1).b), list.get(list.size() - 1).d);
                }
                JumpTableChart.this.mSylableMarkerTextView.setHost(JumpTableChart.this.mHost);
                JumpTableChart.this.mSylableMarkerTextView.b(color);
                JumpTableChart.this.mSylableMarkerTextView.setTextJumpable(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGlobalPointValue() {
        String str;
        String str2;
        Context context = getContext();
        if (!(context instanceof HeartRateDetailActivity)) {
            LogUtil.a(TAG, "not heart rate, set global point");
            setText(this.mTimeTextView, getGlobalPointTime());
        }
        if (context instanceof BaseStepDetailActivity) {
            BaseStepDetailActivity baseStepDetailActivity = (BaseStepDetailActivity) context;
            str = baseStepDetailActivity.getCurrentDayValueMinimum();
            str2 = baseStepDetailActivity.getCurrentDayValueUnit();
        } else {
            str = null;
            str2 = null;
        }
        this.mSylableMarkerTextView.setMarkerText(str, str2);
    }

    private String getGlobalPointTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return DateUtils.formatDateRange(getContext(), timeInMillis, calendar.getTimeInMillis(), 129);
    }

    public void setMarkerTimeShowFlag(boolean z) {
        this.mIsShowMarkerTime = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setText(HealthTextView healthTextView, String str) {
        if (TextUtils.equals(healthTextView.getText(), str)) {
            return;
        }
        healthTextView.setText(str);
    }

    public void setDataTextViewOnClickListener(View.OnClickListener onClickListener) {
        this.mSylableMarkerTextView.setContentOnClickListener(onClickListener);
    }

    private void initLinkChart2Text() {
        this.mChart.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.6
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public void onRangeShow(int i, int i2) {
                JumpTableChart jumpTableChart = JumpTableChart.this;
                jumpTableChart.mTimeRange = jumpTableChart.mChart.formatRangeText(i, i2);
                String formatRangeText = JumpTableChart.this.mChart.formatRangeText(i, i2);
                JumpTableChart jumpTableChart2 = JumpTableChart.this;
                jumpTableChart2.setText(jumpTableChart2.mTimeArea, formatRangeText);
                if (nsj.a(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
                    JumpTableChart.this.updateCalendar(i);
                }
            }
        });
    }

    private void initSwitchButton() {
        this.mOlderSwitchBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean isAnimating = JumpTableChart.this.mChart.isAnimating();
                eet.b(2, JumpTableChart.this.mType);
                LogUtil.c(JumpTableChart.TAG, "mChart.isAnimating:", Boolean.valueOf(isAnimating));
                if (!isAnimating) {
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = JumpTableChart.this.mChart;
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart2 = JumpTableChart.this.mChart;
                    Objects.requireNonNull(hwHealthBaseScrollBarLineChart2);
                    hwHealthBaseScrollBarLineChart.scrollOnePageOlder(new HwHealthBaseScrollBarLineChart.e(hwHealthBaseScrollBarLineChart2) { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.10.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                            Objects.requireNonNull(hwHealthBaseScrollBarLineChart2);
                        }

                        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
                        public void d() {
                            super.d();
                        }
                    });
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mNewerSwitchBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean isAnimating = JumpTableChart.this.mChart.isAnimating();
                eet.b(1, JumpTableChart.this.mType);
                LogUtil.c(JumpTableChart.TAG, "mChart.isAnimating:", Boolean.valueOf(isAnimating));
                if (!isAnimating) {
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = JumpTableChart.this.mChart;
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart2 = JumpTableChart.this.mChart;
                    Objects.requireNonNull(hwHealthBaseScrollBarLineChart2);
                    hwHealthBaseScrollBarLineChart.scrollOnePageNewer(new HwHealthBaseScrollBarLineChart.e(hwHealthBaseScrollBarLineChart2) { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.9.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                            Objects.requireNonNull(hwHealthBaseScrollBarLineChart2);
                        }

                        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
                        public void d() {
                            super.d();
                        }
                    });
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCalendar(int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (this.mCalendar == null) {
            this.mCalendar = new HealthCalendar();
        }
        this.mCalendar = this.mCalendar.transformFromCalendar(calendar);
    }

    private void initRTLStatus() {
        if (LanguageUtil.bc(getContext())) {
            this.mOlderSwitchBtn.setImageResource(R.mipmap._2131820906_res_0x7f11016a);
            this.mNewerSwitchBtn.setImageResource(R.mipmap._2131820905_res_0x7f110169);
        } else {
            this.mOlderSwitchBtn.setImageResource(R.mipmap._2131820905_res_0x7f110169);
            this.mNewerSwitchBtn.setImageResource(R.mipmap._2131820906_res_0x7f11016a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBtnImage() {
        ChartT chartt = this.mChart;
        if (chartt == null) {
            ReleaseLogUtil.a(TAG, "refreshBtnImage mChart is null");
            return;
        }
        if (chartt.canScrollOlderPager()) {
            this.mOlderSwitchBtn.setVisibility(0);
        } else {
            this.mOlderSwitchBtn.setVisibility(4);
        }
        if (this.mChart.canScrollNewerPager()) {
            this.mNewerSwitchBtn.setVisibility(0);
        } else {
            this.mNewerSwitchBtn.setVisibility(4);
        }
    }

    public ChartT aquireChart() {
        return this.mChart;
    }

    public void setHost(ObserveredClassifiedView observeredClassifiedView) {
        this.mHost = observeredClassifiedView;
        this.mChart.setParseTool(observeredClassifiedView);
    }

    public Bitmap drawChart() {
        ChartT chartt = this.mChart;
        int width = chartt.getWidth();
        int height = chartt.getHeight();
        if (width > 0 && height > 0) {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            chartt.draw(new Canvas(createBitmap));
            return createBitmap;
        }
        LogUtil.b(TAG, "width or height is 0");
        return null;
    }

    public String acquireTimeRangeText() {
        return this.mTimeRange;
    }

    private void setContentDescriptionForArrow(DataInfos dataInfos) {
        int i;
        int i2;
        if (dataInfos == null) {
            ReleaseLogUtil.a(TAG, "setContentDescriptionForArrow dataInfos is null");
            return;
        }
        if (dataInfos.isDayData()) {
            i = R$string.accessibility_last_day;
            i2 = R$string.accessibility_next_day;
        } else if (dataInfos.isWeekData()) {
            i = R$string.accessibility_last_week;
            i2 = R$string.accessibility_next_week;
        } else if (dataInfos.isMonthData()) {
            i = R$string.accessibility_last_month;
            i2 = R$string.accessibility_next_month;
        } else if (dataInfos.isYearData()) {
            i = R$string.accessibility_last_year;
            i2 = R$string.accessibility_next_year;
        } else {
            LogUtil.h(TAG, "setContentDescriptionForArrow dataInfos ", dataInfos);
            return;
        }
        jcf.bEz_(this.mOlderSwitchBtn, nsf.j(i));
        jcf.bEz_(this.mNewerSwitchBtn, nsf.j(i2));
    }

    public void customByDataType(DataInfos dataInfos) {
        setContentDescriptionForArrow(dataInfos);
        Layout acquireLayout = this.mChart.acquireLayout();
        switch (AnonymousClass3.c[dataInfos.ordinal()]) {
            case 1:
                this.mType = "calorie";
                this.mChart.setGlobalPoint(true, R.color._2131298169_res_0x7f090779);
                acquireLayout.a(Utils.convertDpToPixel(50.0f));
                break;
            case 2:
                this.mType = "climbs";
                this.mChart.setGlobalPoint(true, R.color._2131296626_res_0x7f090172);
                acquireLayout.a(Utils.convertDpToPixel(50.0f));
                break;
            case 3:
                this.mType = "MVPA";
                this.mChart.setGlobalPoint(true, R.color._2131297738_res_0x7f0905ca);
                acquireLayout.a(Utils.convertDpToPixel(50.0f));
                break;
            case 4:
                this.mType = "distance";
                this.mChart.setGlobalPoint(true, R.color._2131298668_res_0x7f09096c);
                acquireLayout.a(Utils.convertDpToPixel(50.0f));
                break;
            case 5:
                this.mType = "walk";
                this.mChart.setGlobalPoint(true, R.color._2131299134_res_0x7f090b3e);
                acquireLayout.a(Utils.convertDpToPixel(50.0f));
                break;
            case 6:
            case 7:
            case 8:
                this.mType = "walk";
                break;
            case 9:
            case 10:
            case 11:
                this.mType = "distance";
                break;
            case 12:
            case 13:
            case 14:
                this.mType = "MVPA";
                break;
            case 15:
            case 16:
            case 17:
                this.mType = "calorie";
                break;
            case 18:
            case 19:
            case 20:
                this.mType = "climbs";
                break;
        }
        if (!dataInfos.isStepData() || dataInfos == DataInfos.StepDayDetail) {
            return;
        }
        float f = getContext().getResources().getDisplayMetrics().density;
        if (f != 0.0f) {
            acquireLayout.b(Utils.convertDpToPixel(getContext().getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b) / f) + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue()).a(Utils.convertDpToPixel(CURSOR_MARGIN_Y_AXIS_MARGIN));
        }
        this.mChart.customLayout(acquireLayout);
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[DataInfos.values().length];
            c = iArr;
            try {
                iArr[DataInfos.CaloriesDayDetail.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[DataInfos.ClimbDayDetail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[DataInfos.TimeStrengthDayDetail.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[DataInfos.DistanceDayDetail.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[DataInfos.StepDayDetail.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[DataInfos.StepWeekDetail.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                c[DataInfos.StepMonthDetail.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                c[DataInfos.StepYearDetail.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[DataInfos.DistanceWeekDetail.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[DataInfos.DistanceMonthDetail.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[DataInfos.DistanceYearDetail.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                c[DataInfos.TimeStrengthWeekDetail.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                c[DataInfos.TimeStrengthMonthDetail.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                c[DataInfos.TimeStrengthYearDetail.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                c[DataInfos.CaloriesWeekDetail.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                c[DataInfos.CaloriesMonthDetail.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                c[DataInfos.CaloriesYearDetail.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                c[DataInfos.ClimbWeekDetail.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                c[DataInfos.ClimbMonthDetail.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                c[DataInfos.ClimbYearDetail.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    public void setHorizontalOnClickListener(View.OnClickListener onClickListener) {
        View view = this.mHorizontalJumpRect;
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public void openHorizontalJump() {
        View view = this.mHorizontalJumpRect;
        if (view != null) {
            view.setVisibility(0);
        }
        ImageView imageView = this.mHorizontalJumpButton;
        if (imageView != null) {
            imageView.setImageResource(R.drawable._2131429959_res_0x7f0b0a47);
        }
    }

    public void initCalendarView(final Activity activity, final HealthCalendar healthCalendar) {
        Drawable drawable = getResources().getDrawable(R.drawable._2131429717_res_0x7f0b0955);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        this.mTimeArea.setCompoundDrawables(null, null, drawable, null);
        this.mTimeArea.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen._2131365106_res_0x7f0a0cf2));
        this.mTimeArea.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JumpTableChart.this.mCalendar == null) {
                    JumpTableChart.this.mCalendar = healthCalendar;
                }
                HealthCalendarActivity.cxk_(activity, JumpTableChart.this.mCalendar);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void initCalendarView(final Activity activity, final HealthCalendar healthCalendar, final MarkDateTrigger markDateTrigger, final boolean z) {
        Drawable drawable = getResources().getDrawable(R.drawable._2131429717_res_0x7f0b0955);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        this.mTimeArea.setCompoundDrawables(null, null, drawable, null);
        this.mTimeArea.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen._2131365106_res_0x7f0a0cf2));
        this.mTimeArea.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JumpTableChart.this.mCalendar == null) {
                    JumpTableChart.this.mCalendar = healthCalendar;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", JumpTableChart.this.mCalendar);
                bundle.putBoolean("isSetGrayUnmarkedDate", z);
                bundle.putParcelable("markDateTrigger", markDateTrigger);
                HealthCalendarActivity.cxi_(activity, bundle);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void initLandscapeCalendarView(final Activity activity, final HealthCalendar healthCalendar) {
        Drawable drawable = getResources().getDrawable(R.drawable._2131429717_res_0x7f0b0955);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        this.mTimeArea.setCompoundDrawables(null, null, drawable, null);
        this.mTimeArea.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen._2131365106_res_0x7f0a0cf2));
        this.mTimeArea.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JumpTableChart.this.mCalendar == null) {
                    JumpTableChart.this.mCalendar = healthCalendar;
                }
                HealthCalendarActivity.cxn_(activity, JumpTableChart.this.mCalendar);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void initLandscapeCalendarView(final Activity activity, final HealthCalendar healthCalendar, final MarkDateTrigger markDateTrigger, final boolean z) {
        Drawable drawable = getResources().getDrawable(R.drawable._2131429717_res_0x7f0b0955);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        this.mTimeArea.setCompoundDrawables(null, null, drawable, null);
        this.mTimeArea.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen._2131365106_res_0x7f0a0cf2));
        this.mTimeArea.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JumpTableChart.this.mCalendar == null) {
                    JumpTableChart.this.mCalendar = healthCalendar;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", JumpTableChart.this.mCalendar);
                bundle.putBoolean("isSetGrayUnmarkedDate", z);
                bundle.putParcelable("markDateTrigger", markDateTrigger);
                HealthCalendarActivity.cxm_(activity, bundle);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void onProcessCalendarSelect(HealthCalendar healthCalendar) {
        this.mCalendar = healthCalendar;
        this.mChart.reflesh(healthCalendar.transformCalendar().getTimeInMillis());
    }
}

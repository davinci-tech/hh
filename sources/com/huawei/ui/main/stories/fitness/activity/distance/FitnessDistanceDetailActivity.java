package com.huawei.ui.main.stories.fitness.activity.distance;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity;
import com.huawei.ui.main.stories.fitness.views.base.chart.ICustomMotionTypeCalculator;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.SingleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.UserEvent;
import defpackage.rud;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes.dex */
public class FitnessDistanceDetailActivity extends BaseStepDetailActivity {
    private String i;
    private ProportionView e = null;
    private List<ProportionView.ProportionItem> d = new ArrayList();
    private ProportionView.ProportionItem b = null;
    private ProportionView.ProportionItem f = null;
    private ProportionView.ProportionItem c = null;

    /* renamed from: a, reason: collision with root package name */
    private ProportionView.ProportionItem f9845a = null;
    private BaseStepDetailActivity.TextShowFormatter g = new BaseStepDetailActivity.TextShowFormatter() { // from class: com.huawei.ui.main.stories.fitness.activity.distance.FitnessDistanceDetailActivity.3
        @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.TextShowFormatter
        public String convertFloat2TextShow(float f) {
            return UnitUtil.e(f, 1, 2);
        }
    };

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initActivityData() {
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public View getExtensionView() {
        ProportionView proportionView = this.e;
        if (proportionView != null) {
            return proportionView;
        }
        this.e = new ProportionView(this);
        this.b = new ProportionView.d(this, Color.argb(255, 80, 230, 80));
        this.f = new ProportionView.c(this, Color.argb(255, 76, 217, 76));
        this.c = new ProportionView.b(this, Color.argb(255, 71, 204, 71));
        this.f9845a = new ProportionView.e(this, Color.argb(255, 67, 191, 67));
        this.d.add(this.b);
        this.d.add(this.f);
        this.d.add(this.c);
        this.d.add(this.f9845a);
        this.e.b(this.d);
        this.e.setSubHeaderText(getResources().getString(R$string.IDS_fitness_subheader_title, getResources().getString(R$string.IDS_sport_distance)));
        return this.e;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initStartArguement(Intent intent) {
        if (intent != null && intent.hasExtra("today_current_distance_total")) {
            this.mCurrentDayValueMinimum = intent.getIntExtra("today_current_distance_total", (int) this.mCurrentDayValueMinimum);
            this.mCurrentDayValueMinimum *= UnitUtil.h() ? 0.001f * ((float) UnitUtil.e(1.0d, 3)) : 0.001f;
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initConstString() {
        if (UnitUtil.h()) {
            this.i = getString(R$string.IDS_band_data_sport_distance_unit_en);
        } else {
            this.i = getString(R$string.IDS_band_data_sport_distance_unit);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartLayerHolderData(IChartLayerHolder iChartLayerHolder) {
        iChartLayerHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: com.huawei.ui.main.stories.fitness.activity.distance.FitnessDistanceDetailActivity.1
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public boolean isAccept(DataInfos dataInfos) {
                return dataInfos.isDistanceData();
            }
        }, this.i);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initTitleBar() {
        this.mTitleBar.setTitleText(getString(R$string.IDS_sport_distance));
        ArrayList<String> arrayList = new ArrayList<>(1);
        arrayList.add(getString(R$string.IDS_privacy_all_data));
        setTitleBarRightButton(this, arrayList);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartListener() {
        this.mClassifiedViewList.b(new IOnDataShowListener() { // from class: com.huawei.ui.main.stories.fitness.activity.distance.FitnessDistanceDetailActivity.2
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onUserEvent(UserEvent userEvent) {
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onDataShowChanged(DataInfos dataInfos, int i, int i2, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
                if (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) {
                    FitnessDistanceDetailActivity.this.e((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart, dataInfos);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
        ICustomMotionTypeCalculator c = this.mChartLayerHolder.c();
        this.f.setDataValue(c.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.WALK));
        this.b.setDataValue(c.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.RUN));
        this.c.setDataValue(c.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.CLIMB));
        ProportionView.ProportionItem proportionItem = this.f9845a;
        float calculate = c.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.SUM);
        float calculate2 = c.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.WALK);
        proportionItem.setDataValue(((calculate - calculate2) - c.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.RUN)) - c.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.CLIMB));
        this.e.b(this.d);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public BaseStepDetailActivity.TextShowFormatter getTextShowFormatter() {
        return this.g;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateDayClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateDayClassifiedView = super.onCreateDayClassifiedView(textShowFormatter);
        enableClassifiedViewObserver((FitnessDistanceDetailActivity) onCreateDayClassifiedView, getString(R$string.IDS_fitness_total_distance_data_title), this.i);
        ScrollChartObserverView acquireScrollChartObserverView = onCreateDayClassifiedView.acquireScrollChartObserverView();
        if (!(acquireScrollChartObserverView instanceof SingleViewDataObserverView)) {
            return onCreateDayClassifiedView;
        }
        this.mScrollChartObserverView = ((SingleViewDataObserverView) acquireScrollChartObserverView).b();
        if (!(this.mScrollChartObserverView instanceof ScrollChartObserverTotalDataView)) {
            return onCreateDayClassifiedView;
        }
        ((ScrollChartObserverTotalDataView) this.mScrollChartObserverView).b(new ScrollChartObserverTotalDataView.b(this.mCurrentDayValueMinimum, 40004, DataInfos.query(getClassType(), DateType.DATE_DAY)));
        return onCreateDayClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateWeekClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateWeekClassifiedView = super.onCreateWeekClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateWeekClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_distance_data_title), this.i, getString(R$string.IDS_fitness_average_distance_data_title), this.i));
        return onCreateWeekClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateMonthClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateMonthClassifiedView = super.onCreateMonthClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateMonthClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_distance_data_title), this.i, getString(R$string.IDS_fitness_average_distance_data_title), this.i));
        return onCreateMonthClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateYearClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateYearClassifiedView = super.onCreateYearClassifiedView(textShowFormatter);
        enableClassifiedViewObserver((FitnessDistanceDetailActivity) onCreateYearClassifiedView, new HwHealthBaseScrollBarLineChart.DataRatioProvider() { // from class: com.huawei.ui.main.stories.fitness.activity.distance.FitnessDistanceDetailActivity.4
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.DataRatioProvider
            public float computeRatio(int i) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(i * 60000);
                calendar.set(5, 1);
                calendar.roll(5, -1);
                return calendar.get(5);
            }
        }, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_distance_data_title), this.i, getString(R$string.IDS_fitness_average_distance_data_title), this.i));
        return onCreateYearClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ClassType getClassType() {
        return ClassType.TYPE_DISTANCE;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueMinimum() {
        return this.mScrollChartObserverView.getContentTextView().getText().toString();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueUnit() {
        return this.i;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void handleTitleBarRightButtonOnClick(int i) {
        LogUtil.a("SCUI_FitnessDistanceDetailActivity", "PopViewList setOnClick position is ", Integer.valueOf(i));
        if (i == 0) {
            rud.c(101, this);
        } else {
            LogUtil.h("SCUI_FitnessDistanceDetailActivity", "PopViewList setOnClick position is else");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

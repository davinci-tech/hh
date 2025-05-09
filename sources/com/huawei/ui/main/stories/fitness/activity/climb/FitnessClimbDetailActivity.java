package com.huawei.ui.main.stories.fitness.activity.climb;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.SingleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.UserEvent;
import defpackage.jct;
import defpackage.nsn;
import defpackage.rud;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class FitnessClimbDetailActivity extends BaseStepDetailActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f9770a;
    private BaseStepDetailActivity.TextShowFormatter b = new BaseStepDetailActivity.TextShowFormatter() { // from class: com.huawei.ui.main.stories.fitness.activity.climb.FitnessClimbDetailActivity.5
        @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.TextShowFormatter
        public String convertFloat2TextShow(float f) {
            if (UnitUtil.h()) {
                return UnitUtil.e(f, 1, 2);
            }
            return UnitUtil.e(f, 1, 1);
        }
    };
    private HealthTextView e;

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initActivityData() {
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initStartArguement(Intent intent) {
        if (intent != null && intent.hasExtra("today_current_climb_total")) {
            this.mCurrentDayValueMinimum = intent.getIntExtra("today_current_climb_total", (int) this.mCurrentDayValueMinimum);
            this.mCurrentDayValueMinimum *= UnitUtil.h() ? 0.1f * ((float) UnitUtil.e(1.0d, 1)) : 0.1f;
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initTitleBar() {
        this.mTitleBar.setTitleText(getString(R$string.IDS_motiontrack_climb_stairs_tip));
        ArrayList<String> arrayList = new ArrayList<>(1);
        arrayList.add(getString(R$string.IDS_privacy_all_data));
        setTitleBarRightButton(this, arrayList);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public View getExtensionView() {
        String string;
        View inflate = LayoutInflater.from(this).inflate(R.layout.climb_view_extension, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.climb_floor_height_tips);
        try {
            String format = String.format(getResources().getString(R$string.IDS_one_story_equal_to_three_meters_describe), Integer.valueOf(Integer.parseInt(UnitUtil.e(3.0d, 1, 0))), Integer.valueOf(Integer.parseInt(UnitUtil.e(10.0d, 1, 0))), Integer.valueOf(Integer.parseInt(UnitUtil.e(16.0d, 1, 0))));
            if (LanguageUtil.j(this)) {
                healthTextView.setText(UnitUtil.b(format));
            } else {
                healthTextView.setText(format);
            }
        } catch (NumberFormatException e) {
            LogUtil.a("Step_FitnessClimbDetailActivity", e.getMessage());
        }
        this.e = (HealthTextView) inflate.findViewById(R.id.tv_explain);
        if ((this.mCurrentDayValueMinimum <= 0.0f && !jct.b()) || !CommonUtil.bh()) {
            if (nsn.ae(BaseApplication.e())) {
                string = getString(R$string.IDS_climb_pad_not_support_record);
            } else {
                string = getString(R$string.IDS_climb_isnot_support_floor_tips);
            }
            this.e.setText(string);
        }
        if (nsn.ag(this)) {
            healthTextView.setGravity(17);
            this.e.setGravity(17);
        }
        return inflate;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initConstString() {
        if (UnitUtil.h()) {
            this.f9770a = getString(R$string.IDS_ft);
        } else {
            this.f9770a = getString(R$string.IDS_fitness_data_list_activity_meter_unit);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartLayerHolderData(IChartLayerHolder iChartLayerHolder) {
        iChartLayerHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: com.huawei.ui.main.stories.fitness.activity.climb.FitnessClimbDetailActivity.1
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public boolean isAccept(DataInfos dataInfos) {
                return dataInfos.isClimbData();
            }
        }, this.f9770a);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public BaseStepDetailActivity.TextShowFormatter getTextShowFormatter() {
        return this.b;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateDayClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateDayClassifiedView = super.onCreateDayClassifiedView(textShowFormatter);
        enableClassifiedViewObserver((FitnessClimbDetailActivity) onCreateDayClassifiedView, getString(R$string.IDS_fitness_total_height_data_title), this.f9770a);
        ScrollChartObserverView acquireScrollChartObserverView = onCreateDayClassifiedView.acquireScrollChartObserverView();
        if (!(acquireScrollChartObserverView instanceof SingleViewDataObserverView)) {
            return onCreateDayClassifiedView;
        }
        this.mScrollChartObserverView = ((SingleViewDataObserverView) acquireScrollChartObserverView).b();
        if (!(this.mScrollChartObserverView instanceof ScrollChartObserverTotalDataView)) {
            return onCreateDayClassifiedView;
        }
        ((ScrollChartObserverTotalDataView) this.mScrollChartObserverView).b(new ScrollChartObserverTotalDataView.b(this.mCurrentDayValueMinimum, SmartMsgConstant.MSG_TYPE_RIDE_USER, DataInfos.query(getClassType(), DateType.DATE_DAY)));
        return onCreateDayClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateWeekClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateWeekClassifiedView = super.onCreateWeekClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateWeekClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_height_data_title), this.f9770a, getString(R$string.IDS_fitness_average_height_data_title), this.f9770a));
        return onCreateWeekClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateMonthClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateMonthClassifiedView = super.onCreateMonthClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateMonthClassifiedView, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_height_data_title), this.f9770a, getString(R$string.IDS_fitness_average_height_data_title), this.f9770a));
        return onCreateMonthClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateYearClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateYearClassifiedView = super.onCreateYearClassifiedView(textShowFormatter);
        enableClassifiedViewObserver((FitnessClimbDetailActivity) onCreateYearClassifiedView, new HwHealthBaseScrollBarLineChart.DataRatioProvider() { // from class: com.huawei.ui.main.stories.fitness.activity.climb.FitnessClimbDetailActivity.4
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.DataRatioProvider
            public float computeRatio(int i) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(i * 60000);
                calendar.set(5, 1);
                calendar.roll(5, -1);
                return calendar.get(5);
            }
        }, new BaseStepDetailActivity.b(getString(R$string.IDS_fitness_total_height_data_title), this.f9770a, getString(R$string.IDS_fitness_average_height_data_title), this.f9770a));
        return onCreateYearClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ClassType getClassType() {
        return ClassType.TYPE_CLIMB;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartListener() {
        this.mClassifiedViewList.b(new IOnDataShowListener() { // from class: com.huawei.ui.main.stories.fitness.activity.climb.FitnessClimbDetailActivity.2
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onUserEvent(UserEvent userEvent) {
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onDataShowChanged(DataInfos dataInfos, int i, int i2, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
                if (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) {
                    FitnessClimbDetailActivity.this.e((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart, dataInfos);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
        String string;
        if ((this.mChartLayerHolder.c().calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.SUM) <= 0.0f && !jct.b()) || !CommonUtil.bh()) {
            if (nsn.ae(BaseApplication.e())) {
                string = getString(R$string.IDS_climb_pad_not_support_record);
            } else {
                string = getString(R$string.IDS_climb_isnot_support_floor_tips);
            }
        } else if (nsn.ae(BaseApplication.e())) {
            string = getString(R$string.IDS_climb_pad_not_support_floor);
        } else {
            string = getString(R$string.IDS_climb_is_support_floor_tips);
        }
        this.e.setText(string);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueMinimum() {
        return this.mScrollChartObserverView.getContentTextView().getText().toString();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueUnit() {
        return this.f9770a;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void handleTitleBarRightButtonOnClick(int i) {
        LogUtil.a("Step_FitnessClimbDetailActivity", "PopViewList setOnClick position is ", Integer.valueOf(i));
        if (i == 0) {
            rud.c(110, this);
        } else {
            LogUtil.h("Step_FitnessClimbDetailActivity", "PopViewList setOnClick position is else");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

package com.huawei.ui.main.stories.fitness.views.heartrate.classified;

import android.app.Activity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.interactors.ClassifiedViewsBIMarker;
import com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart;
import com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableCombinedChart;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class HeartRateWeekView extends ObserveredClassifiedView {
    private Activity b;

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public ObserveredClassifiedView onCreateView() {
        return this;
    }

    public HeartRateWeekView(Activity activity) {
        super(activity);
        this.b = activity;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
    public JumpTableChart constructJumpTableChart() {
        return new JumpTableCombinedChart(getContext());
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
    public String convertFloat2TextShow(float f) {
        return UnitUtil.e((int) f, 1, 0);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public String getClassStr() {
        return getContext().getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_week);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
    public void onSelected() {
        super.onSelected();
        ClassifiedViewsBIMarker.duw_(this.b, ClassifiedViewsBIMarker.Type.WeekView);
    }
}

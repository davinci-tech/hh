package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.main.stories.fitness.views.heartrate.linechart.RestHeartRateLineChart;

/* loaded from: classes6.dex */
public class JumpTableLineChart extends JumpTableChart<HwHealthLineChart> {
    public JumpTableLineChart(Context context) {
        super(context);
    }

    public JumpTableLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JumpTableLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public HwHealthLineChart constructChart() {
        return new RestHeartRateLineChart(BaseApplication.getContext());
    }
}

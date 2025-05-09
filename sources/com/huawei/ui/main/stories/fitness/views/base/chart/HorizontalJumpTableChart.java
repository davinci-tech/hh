package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.main.stories.fitness.views.heartrate.linechart.HeartRateHorizontalLineChart;

/* loaded from: classes6.dex */
public class HorizontalJumpTableChart extends JumpTableChart<HwHealthLineChart> {
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart
    protected boolean isHorizontal() {
        return true;
    }

    public HorizontalJumpTableChart(Context context) {
        super(context);
    }

    public HorizontalJumpTableChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HorizontalJumpTableChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HwHealthLineChart constructChart() {
        return new HeartRateHorizontalLineChart(BaseApplication.getContext());
    }
}

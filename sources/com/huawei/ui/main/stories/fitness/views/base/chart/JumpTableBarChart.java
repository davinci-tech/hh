package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.knit.section.chart.HwHealthDetailBarChart;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes6.dex */
public class JumpTableBarChart extends JumpTableChart<HwHealthDetailBarChart> {
    public JumpTableBarChart(Context context) {
        super(context);
    }

    public JumpTableBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JumpTableBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HwHealthDetailBarChart constructChart() {
        return new HwHealthDetailBarChart(BaseApplication.getContext());
    }
}

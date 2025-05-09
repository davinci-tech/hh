package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes6.dex */
public class JumpTableCombinedChart extends JumpTableChart<HwHealthDetailCombinedChart> {
    public JumpTableCombinedChart(Context context) {
        super(context);
    }

    public JumpTableCombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JumpTableCombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.JumpTableChart
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public HwHealthDetailCombinedChart constructChart() {
        return new HwHealthDetailCombinedChart(BaseApplication.getContext());
    }
}

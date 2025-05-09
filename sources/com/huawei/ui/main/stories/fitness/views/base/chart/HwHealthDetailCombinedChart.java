package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;

/* loaded from: classes6.dex */
public class HwHealthDetailCombinedChart extends HwHealthCombinedChart {
    public HwHealthDetailCombinedChart(Context context) {
        super(context);
        b();
    }

    public HwHealthDetailCombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public HwHealthDetailCombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
    }
}

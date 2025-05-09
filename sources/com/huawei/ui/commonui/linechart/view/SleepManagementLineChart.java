package com.huawei.ui.commonui.linechart.view;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes6.dex */
public class SleepManagementLineChart extends HwHealthLineChart {
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.Chart
    public boolean valuesToHighlight() {
        return true;
    }

    public SleepManagementLineChart(Context context) {
        super(context);
    }

    public SleepManagementLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SleepManagementLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}

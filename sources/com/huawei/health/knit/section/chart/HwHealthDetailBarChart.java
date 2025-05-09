package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import defpackage.jcf;

/* loaded from: classes3.dex */
public class HwHealthDetailBarChart extends HwHealthBarChart {
    public HwHealthDetailBarChart(Context context) {
        super(context);
        a();
    }

    public HwHealthDetailBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HwHealthDetailBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        jcf.bEM_(this, 1, false);
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
        b(true, false);
    }
}

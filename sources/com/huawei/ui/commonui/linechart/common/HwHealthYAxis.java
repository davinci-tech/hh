package com.huawei.ui.commonui.linechart.common;

import android.graphics.Color;
import android.graphics.Paint;
import com.github.mikephil.charting.components.YAxis;

/* loaded from: classes6.dex */
public class HwHealthYAxis extends YAxis {
    private HwHealthBaseBarLineChart b;
    private HwHealthAxisDependency c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f8880a = true;
    private int e = Color.argb(0, 0, 0, 0);
    private int d = Color.argb(0, 0, 0, 0);

    public enum HwHealthAxisDependency {
        FIRST_PARTY,
        SECOND_PARTY,
        THIRD_PARTY
    }

    @Override // com.github.mikephil.charting.components.YAxis
    public YAxis.AxisDependency getAxisDependency() {
        return null;
    }

    public HwHealthYAxis(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthAxisDependency hwHealthAxisDependency) {
        this.b = hwHealthBaseBarLineChart;
        this.c = hwHealthAxisDependency;
        this.mYOffset = 0.0f;
    }

    public HwHealthAxisDependency e() {
        return this.c;
    }

    public void e(int i, int i2) {
        this.f8880a = false;
        this.e = i;
        this.d = i2;
    }

    public boolean c() {
        return this.f8880a;
    }

    public int a() {
        return this.e;
    }

    public int d() {
        return this.d;
    }

    @Override // com.github.mikephil.charting.components.YAxis
    public boolean needsOffset() {
        return isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxis.YAxisLabelPosition.OUTSIDE_CHART;
    }

    @Override // com.github.mikephil.charting.components.YAxis
    public float getRequiredWidthSpace(Paint paint) {
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.b;
        if (hwHealthBaseBarLineChart != null) {
            return hwHealthBaseBarLineChart.resolveYAxisWidth(this);
        }
        return 0.0f;
    }

    @Override // com.github.mikephil.charting.components.AxisBase
    public void setAxisMaximum(float f) {
        super.setAxisMaximum(f);
    }

    @Override // com.github.mikephil.charting.components.ComponentBase
    public void setXOffset(float f) {
        throw new RuntimeException("pls use LayoutBuilder and ChartAnchor");
    }
}

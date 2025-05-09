package defpackage;

import android.graphics.Color;
import com.github.mikephil.charting.components.YAxis;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;

/* loaded from: classes9.dex */
public class qan extends HwHealthYAxis {
    private boolean b;
    private int c;
    private int d;
    private HwHealthYAxis.HwHealthAxisDependency e;

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis, com.github.mikephil.charting.components.YAxis
    public YAxis.AxisDependency getAxisDependency() {
        return null;
    }

    public qan(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency) {
        super(hwHealthBaseBarLineChart, hwHealthAxisDependency);
        this.b = true;
        this.d = Color.argb(0, 0, 0, 0);
        this.c = Color.argb(0, 0, 0, 0);
        this.e = hwHealthAxisDependency;
        this.mYOffset = 0.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public HwHealthYAxis.HwHealthAxisDependency e() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public boolean c() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public int a() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public int d() {
        return this.c;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis, com.github.mikephil.charting.components.YAxis
    public boolean needsOffset() {
        return isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxis.YAxisLabelPosition.OUTSIDE_CHART;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis, com.github.mikephil.charting.components.AxisBase
    public void setAxisMaximum(float f) {
        super.setAxisMaximum(f);
    }
}

package defpackage;

import android.graphics.Color;
import com.github.mikephil.charting.components.YAxis;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;

/* loaded from: classes3.dex */
public class edh extends HwHealthYAxis {

    /* renamed from: a, reason: collision with root package name */
    private HwHealthYAxis.HwHealthAxisDependency f11962a;
    private boolean b;
    private int d;
    private int e;

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis, com.github.mikephil.charting.components.YAxis
    public YAxis.AxisDependency getAxisDependency() {
        return null;
    }

    public edh(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency) {
        super(hwHealthBaseBarLineChart, hwHealthAxisDependency);
        this.b = true;
        this.e = Color.argb(0, 0, 0, 0);
        this.d = Color.argb(0, 0, 0, 0);
        this.f11962a = hwHealthAxisDependency;
        this.mYOffset = 0.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public HwHealthYAxis.HwHealthAxisDependency e() {
        return this.f11962a;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public boolean c() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public int a() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthYAxis
    public int d() {
        return this.d;
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

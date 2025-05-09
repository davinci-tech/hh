package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;

/* loaded from: classes6.dex */
public class nnk extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ HwHealthBaseScrollBarLineChart d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nnk(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        super();
        this.d = hwHealthBaseScrollBarLineChart;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
        this.d.animateBorderYAuto(2);
    }
}

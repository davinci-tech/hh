package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;

/* loaded from: classes9.dex */
public class nni extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ HwHealthBaseScrollBarLineChart e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nni(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        super();
        this.e = hwHealthBaseScrollBarLineChart;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
        this.e.animateBorderYAuto(2);
    }
}

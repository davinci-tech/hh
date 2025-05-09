package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun;

/* loaded from: classes6.dex */
public class nnm extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ HwHealthBaseScrollBarLineChart c;
    final /* synthetic */ AsyncSelectorMultiRun.Action d;
    final /* synthetic */ AsyncSelectorMultiRun e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nnm(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, AsyncSelectorMultiRun asyncSelectorMultiRun, AsyncSelectorMultiRun.Action action) {
        super();
        this.c = hwHealthBaseScrollBarLineChart;
        this.e = asyncSelectorMultiRun;
        this.d = action;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
        this.e.actionEnd(this.d);
    }
}

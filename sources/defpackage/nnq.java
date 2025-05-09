package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun;

/* loaded from: classes6.dex */
public class nnq extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ HwHealthBaseScrollBarLineChart b;
    final /* synthetic */ AsyncSelectorMultiRun d;
    final /* synthetic */ AsyncSelectorMultiRun.e e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nnq(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, AsyncSelectorMultiRun asyncSelectorMultiRun, AsyncSelectorMultiRun.e eVar) {
        super();
        this.b = hwHealthBaseScrollBarLineChart;
        this.d = asyncSelectorMultiRun;
        this.e = eVar;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
        this.d.actionEnd(this.e);
    }
}

package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun;

/* loaded from: classes6.dex */
public class nno extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ AsyncSelectorMultiRun.e b;
    final /* synthetic */ AsyncSelectorMultiRun c;
    final /* synthetic */ HwHealthBaseScrollBarLineChart d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nno(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, AsyncSelectorMultiRun asyncSelectorMultiRun, AsyncSelectorMultiRun.e eVar) {
        super();
        this.d = hwHealthBaseScrollBarLineChart;
        this.c = asyncSelectorMultiRun;
        this.b = eVar;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
        this.c.actionEnd(this.b);
    }
}

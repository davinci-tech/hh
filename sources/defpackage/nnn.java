package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun;

/* loaded from: classes6.dex */
public class nnn extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ AsyncSelectorMultiRun c;
    final /* synthetic */ AsyncSelectorMultiRun.e d;
    final /* synthetic */ HwHealthBaseScrollBarLineChart e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nnn(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, AsyncSelectorMultiRun asyncSelectorMultiRun, AsyncSelectorMultiRun.e eVar) {
        super();
        this.e = hwHealthBaseScrollBarLineChart;
        this.c = asyncSelectorMultiRun;
        this.d = eVar;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
        this.c.actionEnd(this.d);
    }
}

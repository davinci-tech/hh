package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun;

/* loaded from: classes6.dex */
public class nnp extends HwHealthBaseScrollBarLineChart.e {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ HwHealthBaseScrollBarLineChart f15401a;
    final /* synthetic */ AsyncSelectorMultiRun.e b;
    final /* synthetic */ AsyncSelectorMultiRun d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nnp(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, AsyncSelectorMultiRun asyncSelectorMultiRun, AsyncSelectorMultiRun.e eVar) {
        super();
        this.f15401a = hwHealthBaseScrollBarLineChart;
        this.d = asyncSelectorMultiRun;
        this.b = eVar;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
        this.d.actionEnd(this.b);
    }
}

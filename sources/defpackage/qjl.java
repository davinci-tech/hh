package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqHorizontalLineChart;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineHorizontalChartHolderView;
import java.util.Objects;

/* loaded from: classes6.dex */
public class qjl extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ RqLineHorizontalChartHolderView b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qjl(RqLineHorizontalChartHolderView rqLineHorizontalChartHolderView, RqHorizontalLineChart rqHorizontalLineChart) {
        super();
        this.b = rqLineHorizontalChartHolderView;
        Objects.requireNonNull(rqHorizontalLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

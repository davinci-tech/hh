package defpackage;

import com.huawei.health.knit.section.chart.LacticLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.health.lactatethreshold.view.LacticChartHolderView;
import java.util.Objects;

/* loaded from: classes9.dex */
public class qkt extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ LacticChartHolderView c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qkt(LacticChartHolderView lacticChartHolderView, LacticLineChart lacticLineChart) {
        super();
        this.c = lacticChartHolderView;
        Objects.requireNonNull(lacticLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

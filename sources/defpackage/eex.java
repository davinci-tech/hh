package defpackage;

import com.huawei.health.knit.section.chart.CoreSleepBarChartView;
import com.huawei.health.knit.section.view.BarChartCommonSection;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class eex extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ BarChartCommonSection.AnonymousClass9 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public eex(BarChartCommonSection.AnonymousClass9 anonymousClass9, CoreSleepBarChartView coreSleepBarChartView) {
        super();
        this.b = anonymousClass9;
        Objects.requireNonNull(coreSleepBarChartView);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

package defpackage;

import com.huawei.health.knit.section.chart.CoreSleepBarChartView;
import com.huawei.health.knit.section.view.BarChartCommonSection;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class eey extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ BarChartCommonSection.AnonymousClass6 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public eey(BarChartCommonSection.AnonymousClass6 anonymousClass6, CoreSleepBarChartView coreSleepBarChartView) {
        super();
        this.e = anonymousClass6;
        Objects.requireNonNull(coreSleepBarChartView);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

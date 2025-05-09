package defpackage;

import com.huawei.health.knit.section.chart.BloodOxygenBarChartView;
import com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class egz extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ SectionBloodOxygenBaseBarChart.AnonymousClass3 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public egz(SectionBloodOxygenBaseBarChart.AnonymousClass3 anonymousClass3, BloodOxygenBarChartView bloodOxygenBarChartView) {
        super();
        this.b = anonymousClass3;
        Objects.requireNonNull(bloodOxygenBarChartView);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

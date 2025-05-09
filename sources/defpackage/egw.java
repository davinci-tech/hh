package defpackage;

import com.huawei.health.knit.section.chart.BloodOxygenBarChartView;
import com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class egw extends HwHealthBaseScrollBarLineChart.e {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ SectionBloodOxygenBaseBarChart.AnonymousClass1 f12009a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public egw(SectionBloodOxygenBaseBarChart.AnonymousClass1 anonymousClass1, BloodOxygenBarChartView bloodOxygenBarChartView) {
        super();
        this.f12009a = anonymousClass1;
        Objects.requireNonNull(bloodOxygenBarChartView);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

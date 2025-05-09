package defpackage;

import com.huawei.health.knit.section.chart.BloodOxygenLineChart;
import com.huawei.health.knit.section.view.SectionBloodOxygenBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class egq extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ SectionBloodOxygenBarChart.AnonymousClass7 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public egq(SectionBloodOxygenBarChart.AnonymousClass7 anonymousClass7, BloodOxygenLineChart bloodOxygenLineChart) {
        super();
        this.e = anonymousClass7;
        Objects.requireNonNull(bloodOxygenLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

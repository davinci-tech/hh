package defpackage;

import com.huawei.health.knit.section.chart.BloodOxygenLineChart;
import com.huawei.health.knit.section.view.SectionBloodOxygenBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class egn extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ SectionBloodOxygenBarChart.AnonymousClass3 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public egn(SectionBloodOxygenBarChart.AnonymousClass3 anonymousClass3, BloodOxygenLineChart bloodOxygenLineChart) {
        super();
        this.c = anonymousClass3;
        Objects.requireNonNull(bloodOxygenLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

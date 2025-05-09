package defpackage;

import com.huawei.health.knit.section.chart.HwHealthDetailBarChart;
import com.huawei.health.knit.section.view.SectionActiveHoursBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ega extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ SectionActiveHoursBarChart.AnonymousClass1 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ega(SectionActiveHoursBarChart.AnonymousClass1 anonymousClass1, HwHealthDetailBarChart hwHealthDetailBarChart) {
        super();
        this.d = anonymousClass1;
        Objects.requireNonNull(hwHealthDetailBarChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

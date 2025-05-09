package defpackage;

import com.huawei.health.knit.section.chart.HwHealthPressureBarChart;
import com.huawei.health.knit.section.view.SectionLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ehd extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ SectionLineChart b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ehd(SectionLineChart sectionLineChart, HwHealthPressureBarChart hwHealthPressureBarChart) {
        super();
        this.b = sectionLineChart;
        Objects.requireNonNull(hwHealthPressureBarChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

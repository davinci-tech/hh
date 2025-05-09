package defpackage;

import com.huawei.health.knit.section.chart.TemperatureBarChart;
import com.huawei.health.knit.section.view.SectionBarChart_01;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class egj extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ SectionBarChart_01 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public egj(SectionBarChart_01 sectionBarChart_01, TemperatureBarChart temperatureBarChart) {
        super();
        this.b = sectionBarChart_01;
        Objects.requireNonNull(temperatureBarChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

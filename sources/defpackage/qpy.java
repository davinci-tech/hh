package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import com.huawei.ui.main.stories.health.temperature.view.TemperatureLineChartView;
import java.util.Objects;

/* loaded from: classes7.dex */
public class qpy extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ TemperatureLineChartView d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qpy(TemperatureLineChartView temperatureLineChartView, HwHealthCommonLineChart hwHealthCommonLineChart) {
        super();
        this.d = temperatureLineChartView;
        Objects.requireNonNull(hwHealthCommonLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

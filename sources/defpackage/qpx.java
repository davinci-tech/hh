package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.health.temperature.chart.TemperatureBarChart;
import com.huawei.ui.main.stories.health.temperature.view.TemperatureBarChartView;
import java.util.Objects;

/* loaded from: classes7.dex */
public class qpx extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ TemperatureBarChartView c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qpx(TemperatureBarChartView temperatureBarChartView, TemperatureBarChart temperatureBarChart) {
        super();
        this.c = temperatureBarChartView;
        Objects.requireNonNull(temperatureBarChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import com.huawei.ui.main.stories.health.temperature.view.TemperatureLineChartView;
import java.util.Objects;

/* loaded from: classes7.dex */
public class qpv extends HwHealthBaseScrollBarLineChart.e {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ TemperatureLineChartView f16538a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qpv(TemperatureLineChartView temperatureLineChartView, HwHealthCommonLineChart hwHealthCommonLineChart) {
        super();
        this.f16538a = temperatureLineChartView;
        Objects.requireNonNull(hwHealthCommonLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

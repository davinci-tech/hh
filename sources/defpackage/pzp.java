package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChart;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartView;
import java.util.Objects;

/* loaded from: classes6.dex */
public class pzp extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ BloodSugarLineChartView d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pzp(BloodSugarLineChartView bloodSugarLineChartView, BloodSugarLineChart bloodSugarLineChart) {
        super();
        this.d = bloodSugarLineChartView;
        Objects.requireNonNull(bloodSugarLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

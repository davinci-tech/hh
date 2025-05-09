package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalFragment;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.chart.BloodOxygenHorizontalLineChart;
import java.util.Objects;

/* loaded from: classes9.dex */
public class pjy extends HwHealthBaseScrollBarLineChart.e {
    final /* synthetic */ BloodOxygenHorizontalFragment c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pjy(BloodOxygenHorizontalFragment bloodOxygenHorizontalFragment, BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart) {
        super();
        this.c = bloodOxygenHorizontalFragment;
        Objects.requireNonNull(bloodOxygenHorizontalLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalFragment;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.chart.BloodOxygenHorizontalLineChart;
import java.util.Objects;

/* loaded from: classes9.dex */
public class pjx extends HwHealthBaseScrollBarLineChart.e {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ BloodOxygenHorizontalFragment f16163a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pjx(BloodOxygenHorizontalFragment bloodOxygenHorizontalFragment, BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart) {
        super();
        this.f16163a = bloodOxygenHorizontalFragment;
        Objects.requireNonNull(bloodOxygenHorizontalLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

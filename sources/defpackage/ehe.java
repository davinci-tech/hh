package defpackage;

import com.huawei.health.knit.section.view.SectionLineChart_01;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ehe extends HwHealthBaseScrollBarLineChart.e {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ SectionLineChart_01 f12011a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ehe(SectionLineChart_01 sectionLineChart_01, HwHealthCommonLineChart hwHealthCommonLineChart) {
        super();
        this.f12011a = sectionLineChart_01;
        Objects.requireNonNull(hwHealthCommonLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

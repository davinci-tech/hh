package defpackage;

import com.huawei.health.knit.section.view.SectionLineChart_01;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ehc extends HwHealthBaseScrollBarLineChart.e {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ SectionLineChart_01 f12010a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ehc(SectionLineChart_01 sectionLineChart_01, HwHealthCommonLineChart hwHealthCommonLineChart) {
        super();
        this.f12010a = sectionLineChart_01;
        Objects.requireNonNull(hwHealthCommonLineChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

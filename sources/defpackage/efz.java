package defpackage;

import com.huawei.health.knit.section.chart.HwHealthDetailBarChart;
import com.huawei.health.knit.section.view.SectionActiveHoursBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.Objects;

/* loaded from: classes3.dex */
public class efz extends HwHealthBaseScrollBarLineChart.e {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ SectionActiveHoursBarChart.AnonymousClass2 f12002a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public efz(SectionActiveHoursBarChart.AnonymousClass2 anonymousClass2, HwHealthDetailBarChart hwHealthDetailBarChart) {
        super();
        this.f12002a = anonymousClass2;
        Objects.requireNonNull(hwHealthDetailBarChart);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.e
    public void d() {
        super.d();
    }
}

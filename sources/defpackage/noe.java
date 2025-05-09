package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.scrolladapter.BaseScrollAdapter;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class noe extends BaseScrollAdapter {

    /* renamed from: a, reason: collision with root package name */
    private HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter f15410a;
    private boolean e;

    public noe(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DynamicBorderSupportable dynamicBorderSupportable) {
        super(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        this.f15410a = null;
        this.e = true;
        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
        this.f15410a = new HwHealthBaseScrollBarLineChart.b();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireDefaultStartX() {
        return nom.f(nom.b((int) TimeUnit.MILLISECONDS.toMinutes(getDefaultTime())));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireRange() {
        return (int) TimeUnit.MILLISECONDS.toMinutes(nom.o(getDefaultTime()) - nom.e(getDefaultTime()));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter acquireXAxisValueFormatter() {
        return this.f15410a;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public boolean querySupportTouchScroll() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireValuePresentRangeMin(int i) {
        return e(i);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireValuePresentRangeMax(int i) {
        return a(i);
    }

    protected int e(int i) {
        return nom.f(nom.r(nom.h(i)));
    }

    protected int a(int i) {
        return nom.f(nom.s(nom.h(i)) - 1);
    }
}

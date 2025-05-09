package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.scrolladapter.BaseScrollAdapter;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class noj extends BaseScrollAdapter {

    /* renamed from: a, reason: collision with root package name */
    private HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter f15412a;
    private boolean b;

    public noj(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DynamicBorderSupportable dynamicBorderSupportable) {
        super(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        this.f15412a = null;
        this.b = true;
        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
        this.f15412a = new HwHealthBaseScrollBarLineChart.h();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireDefaultStartX() {
        return nom.f(nom.p((int) TimeUnit.MILLISECONDS.toMinutes(getDefaultTime())));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireRange() {
        return ((int) TimeUnit.MILLISECONDS.toMinutes(nom.a())) - 1;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter acquireXAxisValueFormatter() {
        return this.f15412a;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public boolean querySupportTouchScroll() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireValuePresentRangeMin(int i) {
        return a(i);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireValuePresentRangeMax(int i) {
        return e(i);
    }

    protected int a(int i) {
        return nom.f(nom.c(nom.h(i)));
    }

    protected int e(int i) {
        return nom.f(nom.o(nom.h(i)));
    }
}

package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.scrolladapter.BaseScrollAdapter;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nog extends BaseScrollAdapter {
    private boolean d;
    private HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter e;

    public nog(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DynamicBorderSupportable dynamicBorderSupportable) {
        super(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        this.e = null;
        this.d = true;
        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
        this.e = new HwHealthBaseScrollBarLineChart.f();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireDefaultStartX() {
        return nom.f(nom.l((int) TimeUnit.MILLISECONDS.toMinutes(getDefaultTime())));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireRange() {
        return ((int) TimeUnit.MILLISECONDS.toMinutes(nom.h(getDefaultTime()))) - 1;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.BaseScrollAdapter, com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireRange(long j) {
        return ((int) TimeUnit.MILLISECONDS.toMinutes(nom.h(j))) - 1;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter acquireXAxisValueFormatter() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public boolean querySupportTouchScroll() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireValuePresentRangeMin(int i) {
        return d(i);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireValuePresentRangeMax(int i) {
        return b(i);
    }

    protected int d(int i) {
        return nom.f(nom.c(nom.h(i)));
    }

    protected int b(int i) {
        return nom.f(nom.o(nom.h(i)));
    }
}

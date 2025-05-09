package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.scrolladapter.BaseScrollAdapter;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nob extends BaseScrollAdapter {
    private HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter c;
    private boolean d;

    public nob(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DynamicBorderSupportable dynamicBorderSupportable) {
        super(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        this.c = null;
        this.d = false;
        Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
        this.c = new HwHealthBaseScrollBarLineChart.i();
        this.mScrollBuffer.d(false);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireDefaultStartX() {
        return nom.f(nom.c((int) TimeUnit.MILLISECONDS.toMinutes(getDefaultTime())));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireRange() {
        return (int) TimeUnit.MILLISECONDS.toMinutes(nom.d());
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter acquireXAxisValueFormatter() {
        return this.c;
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
        return a(i);
    }

    protected int d(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h(i)));
        if (calendar.get(12) < 30) {
            calendar.set(12, 0);
        } else {
            calendar.set(12, 29);
        }
        return nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeInMillis()));
    }

    protected int a(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h(i)));
        if (calendar.get(12) < 30) {
            calendar.set(12, 30);
        } else {
            calendar.set(12, 59);
        }
        return nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeInMillis()));
    }
}

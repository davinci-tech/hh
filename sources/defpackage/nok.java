package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.UnixChartType;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;

/* loaded from: classes6.dex */
public class nok {
    public static HwHealthBaseScrollBarLineChart.ScrollAdapterInterface b(UnixChartType unixChartType, HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DynamicBorderSupportable dynamicBorderSupportable) {
        if (unixChartType == UnixChartType.DAY) {
            return new nob(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        }
        if (unixChartType == UnixChartType.WEEK) {
            return new noj(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        }
        if (unixChartType == UnixChartType.MONTH) {
            return new nog(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        }
        if (unixChartType == UnixChartType.YEAR) {
            return new noi(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        }
        if (unixChartType == UnixChartType.ALL) {
            return new noe(hwHealthBaseScrollBarLineChart, dynamicBorderSupportable);
        }
        throw new RuntimeException("getAdapter unknown type");
    }
}

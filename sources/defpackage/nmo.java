package defpackage;

import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.UnixChartType;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;

/* loaded from: classes6.dex */
public class nmo<T extends HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry>> {
    public void d(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos, DynamicBorderSupportable dynamicBorderSupportable) {
        if (dataInfos == null || dataInfos.isNoneData()) {
            LogUtils.e("ScrollChartInteractor", "dataType is null");
            return;
        }
        if (dataInfos.isDayData()) {
            hwHealthBaseScrollBarLineChart.enableScrollMode(UnixChartType.DAY, dynamicBorderSupportable);
            return;
        }
        if (dataInfos.isWeekData()) {
            hwHealthBaseScrollBarLineChart.enableScrollMode(UnixChartType.WEEK, dynamicBorderSupportable);
            return;
        }
        if (dataInfos.isMonthData()) {
            hwHealthBaseScrollBarLineChart.enableScrollMode(UnixChartType.MONTH, dynamicBorderSupportable);
        } else if (dataInfos.isYearData()) {
            hwHealthBaseScrollBarLineChart.enableScrollMode(UnixChartType.YEAR, dynamicBorderSupportable);
        } else {
            if (dataInfos.isAllData()) {
                hwHealthBaseScrollBarLineChart.enableScrollMode(UnixChartType.ALL, dynamicBorderSupportable);
                return;
            }
            throw new IllegalArgumentException("enableScroll dataType not support");
        }
    }
}

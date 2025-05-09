package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle;

/* loaded from: classes6.dex */
public class nqr implements IHwHealthDataSetStyle {
    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle
    public void initStyle(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (hwHealthBaseBarLineChart == null || hwHealthBaseBarLineDataSet == null) {
            return;
        }
        HwHealthYAxis.HwHealthAxisDependency axisDependencyExt = hwHealthBaseBarLineDataSet.getAxisDependencyExt();
        nsn.c(hwHealthBaseBarLineChart, axisDependencyExt, false);
        nnj axisDataRenderArg = hwHealthBaseBarLineChart.getAxisDataRenderArg(axisDependencyExt);
        axisDataRenderArg.a(false);
        axisDataRenderArg.d(this);
    }
}

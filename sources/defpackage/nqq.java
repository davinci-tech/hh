package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle;

/* loaded from: classes6.dex */
public class nqq implements IHwHealthDataSetStyle {
    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle
    public void initStyle(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (hwHealthBaseBarLineChart == null || hwHealthBaseBarLineDataSet == null) {
            LogUtil.h("RunningPostureDefaultSetStyle", "initStyle chart or datset is null");
            return;
        }
        HwHealthYAxis.HwHealthAxisDependency axisDependencyExt = hwHealthBaseBarLineDataSet.getAxisDependencyExt();
        nsn.a(hwHealthBaseBarLineChart, hwHealthBaseBarLineDataSet, axisDependencyExt, e(hwHealthBaseBarLineDataSet));
        nnj axisDataRenderArg = hwHealthBaseBarLineChart.getAxisDataRenderArg(axisDependencyExt);
        axisDataRenderArg.a(false);
        axisDataRenderArg.d(this);
    }

    private int e(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        String string = BaseApplication.e().getResources().getString(R$string.IDS_bolt_balance_left_right_touches);
        String string2 = BaseApplication.e().getResources().getString(R$string.IDS_running_posture_ground_contact_time);
        String string3 = BaseApplication.e().getResources().getString(R$string.IDS_aw_version2_duration_of_passage);
        if (hwHealthBaseBarLineDataSet.getLabel().equals(string)) {
            return 1;
        }
        return (hwHealthBaseBarLineDataSet.getLabel().equals(string2) || hwHealthBaseBarLineDataSet.getLabel().equals(string3)) ? 2 : 0;
    }
}

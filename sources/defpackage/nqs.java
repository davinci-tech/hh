package defpackage;

import android.graphics.Matrix;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle;

/* loaded from: classes6.dex */
public class nqs implements IHwHealthDataSetStyle {
    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle
    public void initStyle(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (hwHealthBaseBarLineChart == null || hwHealthBaseBarLineDataSet == null) {
            return;
        }
        HwHealthYAxis.HwHealthAxisDependency axisDependencyExt = hwHealthBaseBarLineDataSet.getAxisDependencyExt();
        nnj axisDataRenderArg = hwHealthBaseBarLineChart.getAxisDataRenderArg(axisDependencyExt);
        hwHealthBaseBarLineChart.getTransformer(axisDependencyExt).a(new HwHealthTransformer.BusinessMatrixGenerator() { // from class: nqs.4
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthTransformer.BusinessMatrixGenerator
            public Matrix generateBusinessMatrix(Matrix matrix) {
                if (matrix != null) {
                    matrix.reset();
                }
                return matrix;
            }
        });
        axisDataRenderArg.a(true);
        hwHealthBaseBarLineChart.getAxis(axisDependencyExt).setValueFormatter(null);
        axisDataRenderArg.d(this);
    }
}

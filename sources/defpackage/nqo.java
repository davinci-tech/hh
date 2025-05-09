package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class nqo implements IHwHealthDataSetStyle {
    public static final String d(float f) {
        return b(f, false);
    }

    public static final String b(float f, boolean z) {
        int round = Math.round(f);
        if (round < 1 && !z) {
            round = 1;
        }
        Context context = BaseApplication.getContext();
        int i = round / 60;
        if (context == null) {
            return "";
        }
        if (LanguageUtil.r(context)) {
            return UnitUtil.d(round);
        }
        String string = context.getResources().getString(R$string.IDS_hwh_motiontrack_show_pace, UnitUtil.e(i, 1, 0), Integer.valueOf(round % 60));
        return LanguageUtil.b(context) ? string.substring(0, string.length() - 2).replace("'", ":") : string;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle
    public void initStyle(final HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (hwHealthBaseBarLineChart == null || hwHealthBaseBarLineDataSet == null) {
            return;
        }
        HwHealthYAxis.HwHealthAxisDependency axisDependencyExt = hwHealthBaseBarLineDataSet.getAxisDependencyExt();
        hwHealthBaseBarLineChart.getTransformer(axisDependencyExt).a(new HwHealthTransformer.BusinessMatrixGenerator() { // from class: nqo.5
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthTransformer.BusinessMatrixGenerator
            public Matrix generateBusinessMatrix(Matrix matrix) {
                if (matrix != null) {
                    matrix.reset();
                    matrix.postScale(1.0f, -1.0f);
                    matrix.postTranslate(0.0f, -hwHealthBaseBarLineChart.getViewPortHandler().getContentRect().height());
                }
                return matrix;
            }
        });
        hwHealthBaseBarLineChart.getAxis(axisDependencyExt).setValueFormatter(new IAxisValueFormatter() { // from class: nqo.3
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                return nqo.d(f);
            }
        });
        nnj axisDataRenderArg = hwHealthBaseBarLineChart.getAxisDataRenderArg(axisDependencyExt);
        axisDataRenderArg.a(false);
        axisDataRenderArg.d(this);
    }
}

package defpackage;

import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthFillFormatter;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;

/* loaded from: classes6.dex */
public class non implements IHwHealthFillFormatter {
    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthFillFormatter
    public float getFillLinePosition(IHwHealthLineDataSet iHwHealthLineDataSet, HwHealthLineDataProvider hwHealthLineDataProvider) {
        if (hwHealthLineDataProvider == null || iHwHealthLineDataSet == null) {
            LogUtil.h("DefaultHwHealthFillFormatter", "getFillLinePosition dataProvider == null || dataSet == null");
            return 0.0f;
        }
        float yChartMax = hwHealthLineDataProvider.getYChartMax();
        float yChartMin = hwHealthLineDataProvider.getYChartMin();
        BarLineScatterCandleBubbleData data = hwHealthLineDataProvider.getData();
        if (!(data instanceof now)) {
            LogUtil.h("DefaultHwHealthFillFormatter", "!(getData instanceof HwHealthLineData)");
            return 0.0f;
        }
        now nowVar = (now) data;
        if (iHwHealthLineDataSet.getYMax() > 0.0f && iHwHealthLineDataSet.getYMin() < 0.0f) {
            return 0.0f;
        }
        if (nowVar.getYMax() > 0.0f) {
            yChartMax = 0.0f;
        }
        if (nowVar.getYMin() < 0.0f) {
            yChartMin = 0.0f;
        }
        return iHwHealthLineDataSet.getYMin() >= 0.0f ? yChartMin : yChartMax;
    }
}

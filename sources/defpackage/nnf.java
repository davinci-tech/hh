package defpackage;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.IHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.HwHealthCombinedDataProvider;
import java.util.List;

/* loaded from: classes6.dex */
public class nnf extends ChartHighlighter<HwHealthCombinedDataProvider> implements IHighlighter {
    protected nna b;

    public nnf(HwHealthCombinedDataProvider hwHealthCombinedDataProvider, HwHealthBarDataProvider hwHealthBarDataProvider) {
        super(hwHealthCombinedDataProvider);
        this.b = hwHealthBarDataProvider.getBarData() == null ? null : new nna(hwHealthBarDataProvider);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    public List<Highlight> getHighlightsAtXValue(float f, float f2, float f3) {
        this.mHighlightBuffer.clear();
        List<HwHealthBaseBarLineData> d = ((HwHealthCombinedDataProvider) this.mChart).getCombinedData().d();
        for (int i = 0; i < d.size(); i++) {
            HwHealthBaseBarLineData hwHealthBaseBarLineData = d.get(i);
            nna nnaVar = this.b;
            if (nnaVar != null && (hwHealthBaseBarLineData instanceof BarData)) {
                Highlight highlight = nnaVar.getHighlight(f2, f3);
                if (highlight != null) {
                    highlight.setDataIndex(i);
                    this.mHighlightBuffer.add(highlight);
                }
            } else {
                int dataSetCount = hwHealthBaseBarLineData.getDataSetCount();
                for (int i2 = 0; i2 < dataSetCount; i2++) {
                    IDataSet dataSetByIndex = d.get(i).getDataSetByIndex(i2);
                    if (dataSetByIndex.isHighlightEnabled()) {
                        for (Highlight highlight2 : buildHighlights(dataSetByIndex, i2, f, DataSet.Rounding.CLOSEST)) {
                            highlight2.setDataIndex(i);
                            this.mHighlightBuffer.add(highlight2);
                        }
                    }
                }
            }
        }
        return this.mHighlightBuffer;
    }
}

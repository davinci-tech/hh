package defpackage;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthBaseLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nnv<T extends BarLineScatterCandleBubbleDataProvider> extends ChartHighlighter<T> {
    public nnv(T t) {
        super(t);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    public MPPointD getValsForTouch(float f, float f2) {
        return ((HwHealthBaseLineChart) this.mChart).getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY).getValuesByTouchPoint(f, f2);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    public Highlight getHighlightForX(float f, float f2, float f3) {
        List<Highlight> highlightsAtXValue = getHighlightsAtXValue(f, f2, f3);
        if (highlightsAtXValue.isEmpty()) {
            return null;
        }
        return a(highlightsAtXValue, f2, f3);
    }

    public Highlight a(List<Highlight> list, float f, float f2) {
        if (list.isEmpty()) {
            LogUtil.b("HwHealthChartHighlighter", "getClosestHighlightByPixel closestValues is empty");
            return null;
        }
        Highlight highlight = list.get(0);
        float abs = Math.abs(f - list.get(0).getXPx());
        for (Highlight highlight2 : list) {
            float d = d(f, highlight2.getXPx());
            if (d < abs) {
                highlight = highlight2;
                abs = d;
            }
        }
        return highlight;
    }

    protected float d(float f, float f2) {
        return Math.abs(f - f2);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    public List<Highlight> buildHighlights(IDataSet iDataSet, int i, float f, DataSet.Rounding rounding) {
        Entry entryForXValue;
        ArrayList arrayList = new ArrayList();
        if (iDataSet == null) {
            LogUtil.b("HwHealthChartHighlighter", "buildHighlights set == null");
            return arrayList;
        }
        List<Entry> entriesForXValue = iDataSet.getEntriesForXValue(f);
        if (entriesForXValue == null) {
            entriesForXValue = new ArrayList();
        }
        if (entriesForXValue.size() == 0 && (entryForXValue = iDataSet.getEntryForXValue(f, Float.NaN, rounding)) != null) {
            entriesForXValue = iDataSet.getEntriesForXValue(entryForXValue.getX());
        }
        if (entriesForXValue.size() == 0) {
            return arrayList;
        }
        for (Entry entry : entriesForXValue) {
            if ((this.mChart instanceof HwHealthBaseLineChart) && (iDataSet instanceof HwHealthLineDataSet)) {
                MPPointD pixelForValues = ((HwHealthBaseLineChart) this.mChart).getTransformer(((HwHealthLineDataSet) iDataSet).getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY());
                arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, i, YAxis.AxisDependency.LEFT));
            }
        }
        return arrayList;
    }
}

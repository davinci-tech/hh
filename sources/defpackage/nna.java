package defpackage;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nna extends nnv<HwHealthBarDataProvider> {
    public nna(HwHealthBarDataProvider hwHealthBarDataProvider) {
        super(hwHealthBarDataProvider);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter, com.github.mikephil.charting.highlight.IHighlighter
    public Highlight getHighlight(float f, float f2) {
        Highlight highlight = super.getHighlight(f, f2);
        if (highlight == null || !(((HwHealthBarDataProvider) this.mChart).getData() instanceof nmz)) {
            return null;
        }
        MPPointD valsForTouch = getValsForTouch(f, f2);
        IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) ((nmz) ((HwHealthBarDataProvider) this.mChart).getData()).getDataSetByIndex(highlight.getDataSetIndex());
        if (iHwHealthBarDataSet.isStacked()) {
            return c(highlight, iHwHealthBarDataSet, (float) valsForTouch.x, (float) valsForTouch.y);
        }
        MPPointD.recycleInstance(valsForTouch);
        return highlight;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Highlight c(Highlight highlight, IHwHealthBarDataSet iHwHealthBarDataSet, float f, float f2) {
        if (highlight == null || iHwHealthBarDataSet == null) {
            LogUtil.h("HwHealthBarHighlighter", "high == null || set == null");
            return null;
        }
        if (((HwHealthBarEntry) iHwHealthBarDataSet.getEntryForXValue(f, f2)) == null) {
            return null;
        }
        return highlight;
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    public float getDistance(float f, float f2, float f3, float f4) {
        return Math.abs(f - f3);
    }

    @Override // defpackage.nnv
    protected float d(float f, float f2) {
        return Math.abs(f - f2);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    public BarLineScatterCandleBubbleData getData() {
        return ((HwHealthBarDataProvider) this.mChart).getData();
    }

    @Override // defpackage.nnv, com.github.mikephil.charting.highlight.ChartHighlighter
    public MPPointD getValsForTouch(float f, float f2) {
        return ((HwHealthBarDataProvider) this.mChart).getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY).getValuesByTouchPoint(f, f2);
    }

    @Override // defpackage.nnv, com.github.mikephil.charting.highlight.ChartHighlighter
    public Highlight getHighlightForX(float f, float f2, float f3) {
        List<Highlight> highlightsAtXValue = getHighlightsAtXValue(f, f2, f3);
        if (highlightsAtXValue.isEmpty()) {
            return null;
        }
        return a(highlightsAtXValue, f2, f3);
    }

    @Override // defpackage.nnv
    public Highlight a(List<Highlight> list, float f, float f2) {
        if (koq.b(list)) {
            LogUtil.h("HwHealthBarHighlighter", "closestValues == null");
            return null;
        }
        Highlight highlight = list.get(0);
        float abs = Math.abs(f - list.get(0).getXPx());
        for (Highlight highlight2 : list) {
            if (highlight2 != null) {
                float d = d(f, highlight2.getXPx());
                if (d < abs) {
                    highlight = highlight2;
                    abs = d;
                }
            }
        }
        return highlight;
    }

    @Override // defpackage.nnv, com.github.mikephil.charting.highlight.ChartHighlighter
    public List<Highlight> buildHighlights(IDataSet iDataSet, int i, float f, DataSet.Rounding rounding) {
        if (iDataSet == null) {
            LogUtil.h("HwHealthBarHighlighter", "set == null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<Entry> entriesForXValue = iDataSet.getEntriesForXValue(f);
        if (entriesForXValue == null) {
            entriesForXValue = new ArrayList();
        }
        if (entriesForXValue.size() == 0) {
            return arrayList;
        }
        for (Entry entry : entriesForXValue) {
            if (entry != null && (iDataSet instanceof HwHealthBarDataSet)) {
                MPPointD pixelForValues = ((HwHealthBarDataProvider) this.mChart).getTransformer(((HwHealthBarDataSet) iDataSet).getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY());
                arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, i, YAxis.AxisDependency.LEFT));
            }
        }
        return arrayList;
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    public List<Highlight> getHighlightsAtXValue(float f, float f2, float f3) {
        return super.getHighlightsAtXValue(f, f2, f3);
    }
}

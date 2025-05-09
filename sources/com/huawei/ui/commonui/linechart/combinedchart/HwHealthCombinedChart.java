package com.huawei.ui.commonui.linechart.combinedchart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.HwHealthCombinedDataProvider;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import defpackage.koq;
import defpackage.nmz;
import defpackage.nnd;
import defpackage.nnf;
import defpackage.now;
import java.util.List;

/* loaded from: classes6.dex */
public class HwHealthCombinedChart extends HwHealthBaseScrollBarLineChart<nnd> implements HwHealthCombinedDataProvider {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f8857a;
    private boolean b;
    protected DrawOrder[] c;
    private boolean d;

    public enum DrawOrder {
        BAR,
        BUBBLE,
        LINE,
        CANDLE,
        SCATTER
    }

    public HwHealthCombinedChart(Context context) {
        super(context);
        this.f8857a = false;
        this.d = false;
        this.b = true;
        b();
    }

    public HwHealthCombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8857a = false;
        this.d = false;
        this.b = true;
        b();
    }

    public HwHealthCombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8857a = false;
        this.d = false;
        this.b = true;
        b();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        this.c = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
        setHighlighter(new nnf(this, this));
        setHighlightFullBarEnabled(true);
        this.mRenderer = new HwHealthCombinedChartRenderer(this, this.mAnimator, this.mViewPortHandler);
    }

    private void b() {
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setAxisMinimum(0.0f);
        this.mAxisSecondParty.setEnabled(false);
        getXAxis().setDrawGridLines(false);
        this.mAxisFirstParty.setAxisMaximum(12000.0f);
        makeReverse(true);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthCombinedDataProvider
    public nnd getCombinedData() {
        return (nnd) this.mData;
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void setData(nnd nndVar) {
        super.setData((HwHealthCombinedChart) nndVar);
        setHighlighter(new nnf(this, this));
        if (this.mRenderer instanceof HwHealthCombinedChartRenderer) {
            ((HwHealthCombinedChartRenderer) this.mRenderer).c();
        }
        this.mRenderer.initBuffers();
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public Highlight getHighlightByTouchPoint(float f, float f2) {
        if (this.mData == 0) {
            Log.e(Chart.LOG_TAG, "Can't select by touch. No data set.");
            return null;
        }
        Highlight highlight = getHighlighter().getHighlight(f, f2);
        return (highlight == null || !isHighlightFullBarEnabled()) ? highlight : new Highlight(highlight.getX(), highlight.getY(), highlight.getXPx(), highlight.getYPx(), highlight.getDataSetIndex(), -1, highlight.getAxis());
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider
    public now getLineData() {
        if (this.mData == 0) {
            return null;
        }
        return ((nnd) this.mData).f();
    }

    public void e() {
        if (this.mData == 0) {
            return;
        }
        ((nnd) this.mData).a();
    }

    public void d() {
        if (this.mData == 0) {
            return;
        }
        ((nnd) this.mData).e();
    }

    public void a() {
        if (this.mData == 0) {
            return;
        }
        ((nnd) this.mData).g();
    }

    public void c() {
        if (this.mData == 0) {
            return;
        }
        ((nnd) this.mData).l();
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public nmz getBarData() {
        if (this.mData == 0) {
            return null;
        }
        return ((nnd) this.mData).j();
    }

    public void e(HwHealthBarDataSet hwHealthBarDataSet) {
        if (this.mData == 0) {
            return;
        }
        ((nnd) this.mData).a(hwHealthBarDataSet);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isDrawBarShadowEnabled() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isDrawValueAboveBarEnabled() {
        return this.b;
    }

    public void setHighlightFullBarEnabled(boolean z) {
        this.f8857a = z;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isHighlightFullBarEnabled() {
        return this.f8857a;
    }

    public DrawOrder[] getDrawOrder() {
        return (DrawOrder[]) this.c.clone();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        ((nnd) this.mData).c();
        for (T t : ((nnd) this.mData).getDataSets()) {
            if (t != null) {
                t.setValues(t.acquireEntryVals());
                t.makeDataCalculated(true);
                if (t instanceof IHwHealthLineDataSet) {
                    ((IHwHealthLineDataSet) t).checkIfNeedReload();
                }
            }
        }
        notifyDataSetChanged();
        invalidate();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public long queryMarkerViewTimeRangeMin() {
        long queryMarkerViewTimeMills = queryMarkerViewTimeMills();
        List<T> dataSets = ((nnd) this.mData).getDataSets();
        if (koq.b(dataSets)) {
            return queryMarkerViewTimeMills;
        }
        return !(((HwHealthBaseBarLineDataSet) dataSets.get(0)) instanceof HwHealthBaseBarDataSet) ? queryMarkerViewTimeMills : ((HwHealthBaseBarDataSet) r2).acquireValuePresentRangeMin((int) (queryMarkerViewTimeMills / 60000)) * 60000;
    }
}

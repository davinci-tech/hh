package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;
import java.util.List;

/* loaded from: classes6.dex */
public class nov extends HwHealthLineChartRenderLayerOriginal implements IHwHealthDataRender {
    private HwHealthRenderMode c;
    private npj d;
    private Path e;

    public nov(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler);
        this.c = null;
        this.d = null;
        this.e = new Path();
        if (this.mChart instanceof HwHealthBaseBarLineChart) {
            this.c = new HwHealthRenderMode((HwHealthBaseBarLineChart) this.mChart, this.mViewPortHandler);
        }
        this.d = new npj(this.c);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length <= 0 || lineData == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            if (highlight != null) {
                List<IHwHealthLineDataSet> dataSets = lineData.getDataSets();
                if (!koq.b(dataSets)) {
                    cCy_(canvas, highlight, dataSets, Color.argb(0, 0, 0, 0));
                }
            }
        }
    }

    private void cCy_(Canvas canvas, Highlight highlight, List<IHwHealthLineDataSet> list, int i) {
        if (list.size() == 1) {
            cCz_(canvas, highlight, list.get(0), i);
            return;
        }
        MPPointD pixelForValues = this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(highlight.getX(), 0.0f);
        cCA_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, list.get(0), true, false, i);
        for (IHwHealthLineDataSet iHwHealthLineDataSet : list) {
            if (!(this.mChart instanceof HwHealthBaseBarLineChart)) {
                return;
            }
            IHwHealthDatasContainer cacheDataContainer = iHwHealthLineDataSet.cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
            if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                List<HwHealthBaseEntry> searchEntryForXValue = ((IHwHealthLineDatasContainer) cacheDataContainer).searchEntryForXValue(highlight.getX());
                if (!koq.b(searchEntryForXValue)) {
                    HwHealthBaseEntry hwHealthBaseEntry = searchEntryForXValue.get(0);
                    MPPointD pixelForValues2 = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues2.x, (float) pixelForValues2.y);
                    cCA_(canvas, (float) pixelForValues2.x, (float) pixelForValues2.y, iHwHealthLineDataSet, false, true, i);
                }
            }
        }
    }

    private void cCz_(Canvas canvas, Highlight highlight, IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        boolean z;
        if (this.mChart instanceof HwHealthBaseBarLineChart) {
            IHwHealthDatasContainer cacheDataContainer = iHwHealthLineDataSet.cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
            if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                List<HwHealthBaseEntry> searchEntryForXValue = ((IHwHealthLineDatasContainer) cacheDataContainer).searchEntryForXValue(highlight.getX());
                if (koq.b(searchEntryForXValue)) {
                    return;
                }
                HwHealthBaseEntry hwHealthBaseEntry = searchEntryForXValue.get(0);
                if ((this.c.a() instanceof HwHealthRenderMode.a) && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) {
                    z = false;
                    hwHealthBaseEntry = new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue());
                } else {
                    z = true;
                }
                MPPointD pixelForValues = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY() * this.mAnimator.getPhaseY());
                highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                cCA_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, true, z, i);
            }
        }
    }

    protected void cCA_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(i);
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.e.reset();
            this.e.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.e.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.e, this.mHighlightPaint);
        }
        if (z2) {
            float convertDpToPixel = Utils.convertDpToPixel(4.0f);
            float convertDpToPixel2 = Utils.convertDpToPixel(6.0f);
            if (this.mChart instanceof HwHealthCombinedChart) {
                if (b() != 0.0f) {
                    convertDpToPixel = b();
                }
                convertDpToPixel2 = 1.5f * convertDpToPixel;
            }
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(1.0f);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(nrn.d(R$color.colorBackground));
            canvas.drawCircle(f, f2, convertDpToPixel2, this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(c(iLineScatterCandleRadarDataSet));
            canvas.drawCircle(f, f2, convertDpToPixel, this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        }
    }

    private float b() {
        List<T> dataSets = this.mChart.getData().getDataSets();
        if (!koq.c(dataSets) || !(dataSets.get(0) instanceof HwHealthBarDataSet) || ((HwHealthCombinedChart) this.mChart).getBarData() == null) {
            return 0.0f;
        }
        float[] fArr = {0.0f, 0.0f, ((HwHealthCombinedChart) this.mChart).getBarData().d(), 0.0f};
        this.mChart.getTransformer(((HwHealthCombinedChart) this.mChart).getAxisFirstParty().e()).pointValuesToPixel(fArr);
        return (fArr[2] - fArr[0]) / 2.0f;
    }

    protected int c(ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet) {
        if (this.c.a() instanceof HwHealthRenderMode.a) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iLineScatterCandleRadarDataSet.getColor();
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawCubicBezier(IHwHealthLineDataSet iHwHealthLineDataSet) {
        throw new RuntimeException("needs intercept,not support bezier");
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (!(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            LogUtil.h("HealthChart_HwHealthLineChartRenderer", "drawLinear !(dataSetArg instanceof HwHealthLineDataSet)");
            return;
        }
        HwHealthLineDataSet hwHealthLineDataSet = (HwHealthLineDataSet) iHwHealthLineDataSet;
        Canvas canvas2 = this.mBitmapCanvas;
        if (hwHealthLineDataSet.isDataCalculated()) {
            this.c.d(hwHealthLineDataSet);
            this.c.d();
            HwHealthRenderMode.IRenderMode a2 = this.c.a();
            nnj axisDataRenderArg = this.mChart.getAxisDataRenderArg(hwHealthLineDataSet.getAxisDependencyExt());
            if (axisDataRenderArg != null && axisDataRenderArg.a()) {
                if (!hwHealthLineDataSet.h()) {
                    throw new RuntimeException("drawLadderLinear needs displayIntervalSeted");
                }
                a2.setLadderMode(hwHealthLineDataSet.a());
            } else {
                a2.setStandMode();
            }
            if (this.mChart instanceof HwHealthBaseBarLineChart) {
                IHwHealthDatasContainer cacheDataContainer = hwHealthLineDataSet.cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
                if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                    this.d.cDs_(canvas2, (IHwHealthLineDatasContainer) cacheDataContainer, this.mChart.getTransformer(hwHealthLineDataSet.getAxisDependencyExt()));
                }
            }
        }
    }

    public HwHealthLineDataProvider e() {
        return this.mChart;
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        if (z) {
            this.c.b("render_background");
        } else {
            this.c.b("render_foreground");
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.c.a() instanceof HwHealthRenderMode.a;
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean hasData() {
        now lineData = this.mChart.getLineData();
        return (lineData == null || lineData.getDataSets() == null || lineData.getDataSets().size() == 0) ? false : true;
    }

    public void a(int i) {
        if (this.c.a() instanceof HwHealthRenderMode.RenderModeCommon) {
            ((HwHealthRenderMode.RenderModeCommon) this.c.a()).setPointStyle(i);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void focusArea(List<nnl> list) {
        boolean z;
        if (this.c.a() instanceof HwHealthRenderMode.c) {
            z = false;
        } else {
            this.c.b("render_focus_area");
            z = true;
        }
        HwHealthRenderMode.c cVar = (HwHealthRenderMode.c) this.c.a();
        if (cVar.b(list) && !z) {
            return;
        }
        cVar.d(list);
        ((HwHealthBaseBarLineChart) this.mChart).invalidate();
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void disableFocusArea() {
        this.c.b("render_foreground");
        if (this.mChart instanceof HwHealthBaseBarLineChart) {
            ((HwHealthBaseBarLineChart) this.mChart).invalidate();
        }
    }
}

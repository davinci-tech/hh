package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class npa extends nox {
    private Paint b;
    protected Path h;

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return true;
    }

    public npa(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.h = new Path();
        this.b = new Paint();
        b();
        this.j = 1;
    }

    private void b() {
        this.b.setAntiAlias(true);
        this.b.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.b.setStyle(Paint.Style.STROKE);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        List dataSets;
        if (isDrawingValuesAllowed(this.mChart)) {
            if (this.mChart instanceof HwHealthBaseCombinedChart) {
                dataSets = getDataset().getDataSets();
            } else {
                dataSets = this.mChart.getData().getDataSets();
            }
            if (dataSets == null) {
                LogUtil.h("WeightLineChartRender", "drawValues dataSets == null");
                return;
            }
            for (int i = 0; i < dataSets.size(); i++) {
                Object obj = dataSets.get(i);
                if (obj instanceof IHwHealthLineDataSet) {
                    IHwHealthLineDataSet iHwHealthLineDataSet = (IHwHealthLineDataSet) obj;
                    if (iHwHealthLineDataSet.isDataCalculated() && (shouldDrawValues(iHwHealthLineDataSet) || iHwHealthLineDataSet.isShowMaxMinValue())) {
                        applyValueTextStyle(iHwHealthLineDataSet);
                        HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
                        this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
                        float[] e = transformer.e(iHwHealthLineDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                        MPPointF mPPointF = MPPointF.getInstance(iHwHealthLineDataSet.getIconsOffset());
                        mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                        mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                        cDb_(e, iHwHealthLineDataSet, canvas, mPPointF, i);
                        MPPointF.recycleInstance(mPPointF);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void cDb_(float[] r29, com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet r30, android.graphics.Canvas r31, com.github.mikephil.charting.utils.MPPointF r32, int r33) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.npa.cDb_(float[], com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet, android.graphics.Canvas, com.github.mikephil.charting.utils.MPPointF, int):void");
    }

    private void cCW_(Canvas canvas, float f, float f2, MPPointF mPPointF, MPPointF mPPointF2) {
        this.mValuePaint.setColor(nrn.d(R$color.textColorSecondary));
        this.mValuePaint.setTextSize(BaseApplication.getContext().getResources().getDimension(R.dimen._2131365066_res_0x7f0a0cca));
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        canvas.drawText(UnitUtil.e(f2, 1, 1), mPPointF2.x, (mPPointF2.y - convertDpToPixel) - Utils.convertDpToPixel(2.0f), this.mValuePaint);
        if (f != f2) {
            canvas.drawText(UnitUtil.e(f, 1, 1), mPPointF.x, mPPointF.y + (convertDpToPixel * 3.0f), this.mValuePaint);
        }
    }

    private void a(IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (iHwHealthLineDataSet instanceof npc) {
            this.b.setColor(nrn.d(R$color.emui_color_tips_bg));
        } else {
            this.b.setColor(iHwHealthLineDataSet.getColor());
        }
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (iHwHealthLineDataSet.isDataCalculated()) {
            a(iHwHealthLineDataSet);
            d();
            HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
            Canvas canvas2 = this.mBitmapCanvas;
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            int i = iHwHealthLineDataSet.isDrawSteppedEnabled() ? 4 : 2;
            if (iHwHealthLineDataSet.getColors().size() > 1) {
                if (this.i.length <= i * 2) {
                    this.i = new float[i * 4];
                }
                cCX_(iHwHealthLineDataSet, iHwHealthLineDataSet.isDrawSteppedEnabled(), transformer, this.mAnimator.getPhaseY(), canvas2);
            } else {
                c(iHwHealthLineDataSet.getEntryCount(), i);
                cCN_(iHwHealthLineDataSet, i, transformer, canvas2);
            }
            this.mRenderPaint.setPathEffect(null);
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.github.mikephil.charting.data.Entry] */
    private void cCX_(IHwHealthLineDataSet iHwHealthLineDataSet, boolean z, Transformer transformer, float f, Canvas canvas) {
        for (int i = this.mXBounds.min; i <= this.mXBounds.range + this.mXBounds.min; i++) {
            ?? entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i);
            if (entryForIndex != 0) {
                this.i[0] = entryForIndex.getX();
                this.i[1] = entryForIndex.getY() * f;
                if (i < this.mXBounds.max) {
                    Entry entryForIndex2 = iHwHealthLineDataSet.getEntryForIndex(i + 1);
                    if (entryForIndex2 == null) {
                        return;
                    } else {
                        c(z, f, entryForIndex2);
                    }
                } else {
                    this.i[2] = this.i[0];
                    this.i[3] = this.i[1];
                }
                transformer.pointValuesToPixel(this.i);
                if (!this.mViewPortHandler.isInBoundsRight(this.i[0])) {
                    return;
                }
                boolean z2 = (this.mViewPortHandler.isInBoundsTop(this.i[1]) || this.mViewPortHandler.isInBoundsBottom(this.i[3])) ? false : true;
                if (this.mViewPortHandler.isInBoundsLeft(this.i[2]) && !z2) {
                    this.mRenderPaint.setColor(iHwHealthLineDataSet.getColor(i));
                    canvas.drawLines(this.i, 0, (z ? 4 : 2) * 2, this.mRenderPaint);
                }
            }
        }
    }

    private void c(boolean z, float f, Entry entry) {
        if (z) {
            this.i[2] = entry.getX();
            this.i[3] = this.i[1];
            this.i[4] = this.i[2];
            this.i[5] = this.i[3];
            this.i[6] = entry.getX();
            this.i[7] = entry.getY() * f;
            return;
        }
        this.i[2] = entry.getX();
        this.i[3] = entry.getY() * f;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public now getDataset() {
        return this.mChart.getLineData();
    }

    @Override // defpackage.nox
    protected void d(IHwHealthLineDataSet iHwHealthLineDataSet) {
        a(iHwHealthLineDataSet);
    }

    @Override // defpackage.nox
    protected int c(IHwHealthLineDataSet iHwHealthLineDataSet) {
        return iHwHealthLineDataSet.getColor();
    }

    protected boolean e(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-7f;
    }

    @Override // defpackage.nox
    protected void cCZ_(Canvas canvas, int i, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet) {
        cDa_(canvas, new Paint(), list, iHwHealthLineDataSet, new d());
    }

    protected void cDa_(Canvas canvas, Paint paint, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet, d dVar) {
        paint.setAntiAlias(true);
        if (this.j == 1) {
            paint.setStyle(Paint.Style.STROKE);
            float[] fArr = new float[list.size() * 2];
            float[] fArr2 = new float[list.size() * 2];
            int i = 0;
            int i2 = 0;
            for (PointF pointF : list) {
                int i3 = i + 1;
                fArr[i] = pointF.x;
                i += 2;
                fArr[i3] = pointF.y;
                int i4 = i2 + 1;
                fArr2[i2] = pointF.x;
                i2 += 2;
                fArr2[i4] = pointF.y;
            }
            this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
            dVar.cDg_(paint);
            dVar.c(fArr);
            dVar.e(fArr2);
            cDd_(canvas, iHwHealthLineDataSet, dVar);
        }
    }

    @Override // defpackage.nox
    protected void cCY_(Canvas canvas, float f, float f2, IHwHealthLineDataSet iHwHealthLineDataSet, Path path) {
        int save = canvas.save();
        canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
        canvas.drawPath(path, this.b);
        canvas.restoreToCount(save);
        path.reset();
    }

    protected void cDd_(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet, d dVar) {
        for (int i = 0; i < dVar.a().length; i += 2) {
            dVar.cDf_().setAntiAlias(true);
            dVar.cDf_().setStyle(Paint.Style.FILL);
            cDe_(canvas, dVar, iHwHealthLineDataSet, i);
        }
    }

    protected void cDe_(Canvas canvas, d dVar, IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        float circleHoleRadius = iHwHealthLineDataSet.getCircleHoleRadius();
        float circleRadius = iHwHealthLineDataSet.getCircleRadius();
        if (i < 0) {
            return;
        }
        float f = dVar.a()[i];
        int i2 = i + 1;
        float f2 = dVar.a()[i2];
        if (iHwHealthLineDataSet.getNodeCircleBackground()) {
            dVar.cDf_().setColor(nrn.d(R$color.chart_background_color));
            canvas.drawCircle(f, f2, circleRadius, dVar.cDf_());
        }
        if (iHwHealthLineDataSet instanceof npc) {
            npc npcVar = (npc) iHwHealthLineDataSet;
            if (npcVar.n()) {
                dVar.cDf_().setColor(c(npcVar, dVar.d()[i2]));
                canvas.drawCircle(f, f2, circleHoleRadius, dVar.cDf_());
            }
        }
        dVar.cDf_().setColor(iHwHealthLineDataSet.getColor());
        canvas.drawCircle(f, f2, circleHoleRadius, dVar.cDf_());
    }

    protected int c(npc npcVar, float f) {
        int i;
        float[] m = npcVar.m();
        int[] e = npcVar.e();
        if (f >= m[m.length - 1]) {
            return e[e.length - 1];
        }
        if (f < m[0]) {
            return e[0];
        }
        int d2 = d(m, f, 0, m.length - 1);
        if (d2 > -1 && (i = d2 + 1) < e.length - 1) {
            return e[i];
        }
        return e[0];
    }

    private int d(float[] fArr, float f, int i, int i2) {
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            float f2 = fArr[i4];
            int i5 = i4 + 1;
            float f3 = fArr[i5];
            if (Float.compare(f, f2) >= 0 && f < f3) {
                return i4;
            }
            if (f2 < f) {
                i = i5;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        boolean z;
        Entry entry;
        now dataset = getDataset();
        if (highlightArr == null || highlightArr.length <= 0 || dataset == null || canvas == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            List<T> dataSets = dataset.getDataSets();
            if (dataSets != 0 && dataSets.size() != 0) {
                if (dataSets.size() == 1) {
                    List<T> entriesForXValue = ((IHwHealthLineDataSet) dataSets.get(0)).getEntriesForXValue(highlight.getX());
                    if (entriesForXValue != 0 && entriesForXValue.size() != 0) {
                        Entry entry2 = (Entry) entriesForXValue.get(0);
                        if (isUsePaintAsBackground() && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) {
                            entry = new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue());
                            z = false;
                        } else {
                            z = true;
                            entry = entry2;
                        }
                        cDc_(canvas, entry, (IHwHealthLineDataSet) dataSets.get(0), true, z);
                    }
                } else {
                    for (T t : dataSets) {
                        if (!(this.mChart instanceof HwHealthBaseBarLineChart)) {
                            break;
                        }
                        IHwHealthDatasContainer cacheDataContainer = t.cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
                        if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                            List<HwHealthBaseEntry> searchEntryForXValue = ((IHwHealthLineDatasContainer) cacheDataContainer).searchEntryForXValue(highlight.getX());
                            if (!koq.b(searchEntryForXValue)) {
                                cDc_(canvas, searchEntryForXValue.get(0), t, true, true);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void cDc_(Canvas canvas, Entry entry, IHwHealthLineDataSet iHwHealthLineDataSet, boolean z, boolean z2) {
        int argb = Color.argb(0, 0, 0, 0);
        MPPointD pixelForValues = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
        float f = (float) pixelForValues.x;
        this.mHighlightPaint.setStrokeWidth(iHwHealthLineDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(argb);
        this.mHighlightPaint.setPathEffect(iHwHealthLineDataSet.getDashPathEffectHighlight());
        if (iHwHealthLineDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.h.reset();
            if ((this.mChart instanceof Chart) && (((Chart) this.mChart).getMarker() instanceof MarkerView)) {
                this.h.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
                this.h.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
                canvas.drawPath(this.h, this.mHighlightPaint);
            }
        }
        float x = entry.getX();
        float lowestVisibleX = this.mChart.getLowestVisibleX();
        float highestVisibleX = this.mChart.getHighestVisibleX();
        if (!z2 || x <= lowestVisibleX || x >= highestVisibleX) {
            return;
        }
        float circleHoleRadius = iHwHealthLineDataSet.getCircleHoleRadius() + Utils.convertDpToPixel(1.0f);
        float circleRadius = iHwHealthLineDataSet.getCircleRadius() + Utils.convertDpToPixel(1.0f);
        if (this.mChart instanceof HwHealthCombinedChart) {
            List<T> dataSets = this.mChart.getData().getDataSets();
            if (dataSets.size() > 0 && (dataSets.get(0) instanceof HwHealthBarDataSet)) {
                float[] fArr = {0.0f, 0.0f, b(0.0f), 0.0f};
                this.mChart.getTransformer(((HwHealthCombinedChart) this.mChart).getAxisFirstParty().e()).pointValuesToPixel(fArr);
                circleHoleRadius = (fArr[2] - fArr[0]) / 2.0f;
                circleRadius = circleHoleRadius * 1.5f;
            }
        }
        float f2 = (float) pixelForValues.y;
        e();
        canvas.drawCircle(f, f2, circleRadius, this.mHighlightPaint);
        a(entry, iHwHealthLineDataSet);
        canvas.drawCircle(f, f2, circleHoleRadius, this.mHighlightPaint);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
    }

    private void e() {
        this.mHighlightPaint.setAntiAlias(true);
        this.mHighlightPaint.setStrokeWidth(1.0f);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(nrn.d(R$color.chart_background_color));
        this.mHighlightPaint.setShadowLayer(8.0f, 0.0f, 6.0f, nrn.d(R$color.chart_shadow_color));
    }

    private void a(Entry entry, IHwHealthLineDataSet iHwHealthLineDataSet) {
        this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        if (!(this.mChart.getData().getDataSets().get(0) instanceof npc) || !((npc) this.mChart.getData().getDataSets().get(0)).n()) {
            this.mHighlightPaint.setColor(a((ILineScatterCandleRadarDataSet) iHwHealthLineDataSet));
        } else {
            this.mHighlightPaint.setColor(c((npc) this.mChart.getData().getDataSets().get(0), entry.getY()));
        }
    }

    protected float b(float f) {
        nmz barData = ((HwHealthCombinedChart) this.mChart).getBarData();
        return barData != null ? barData.d() : f;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private float[] f15418a;
        private int b;
        private float[] c;
        private Paint e;

        public int b() {
            return this.b;
        }

        public void d(int i) {
            this.b = i;
        }

        public float[] a() {
            return this.c;
        }

        public void c(float[] fArr) {
            this.c = fArr;
        }

        public float[] d() {
            return this.f15418a;
        }

        public void e(float[] fArr) {
            this.f15418a = fArr;
        }

        public Paint cDf_() {
            return this.e;
        }

        public void cDg_(Paint paint) {
            this.e = paint;
        }
    }
}

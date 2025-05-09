package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nox extends HwHealthLineChartRenderLayerOriginal implements IHwHealthDataRender {

    /* renamed from: a, reason: collision with root package name */
    private boolean f15416a;
    private Path b;
    private List<nnl> c;
    protected Path d;
    private boolean e;
    protected float[] f;
    protected float[] i;
    protected int j;

    protected boolean c() {
        return true;
    }

    public nox(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler);
        this.j = 0;
        this.i = new float[4];
        this.f = new float[4];
        this.d = new Path();
        this.e = false;
        this.f15416a = false;
        this.c = new ArrayList();
        this.b = new Path();
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        now dataset = getDataset();
        if (highlightArr == null || highlightArr.length <= 0 || dataset == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            if (highlight != null) {
                List<T> dataSets = dataset.getDataSets();
                if (!koq.b(dataSets)) {
                    c((List<IHwHealthLineDataSet>) dataSets, dataSets.size());
                    cCF_(canvas, highlight, dataSets, Color.argb(0, 0, 0, 0));
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public now getDataset() {
        return this.mChart.getLineData();
    }

    private void cCF_(Canvas canvas, Highlight highlight, List<IHwHealthLineDataSet> list, int i) {
        boolean z;
        if (list.size() == 1) {
            List<T> entriesForXValue = list.get(0).getEntriesForXValue(highlight.getX());
            if (koq.b(entriesForXValue)) {
                return;
            }
            Entry entry = (Entry) entriesForXValue.get(0);
            if (this.e && (this.mChart instanceof HwHealthBaseBarLineChart) && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) {
                entry = new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue());
                z = false;
            } else {
                z = true;
            }
            MPPointD pixelForValues = this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
            highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
            dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, list.get(0), true, z, i);
            return;
        }
        MPPointD pixelForValues2 = this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(highlight.getX(), 0.0f);
        dwf_(canvas, (float) pixelForValues2.x, (float) pixelForValues2.y, list.get(0), true, false, i);
        for (IHwHealthLineDataSet iHwHealthLineDataSet : list) {
            if (iHwHealthLineDataSet != null) {
                List<T> entriesForXValue2 = iHwHealthLineDataSet.getEntriesForXValue(highlight.getX());
                if (!koq.b(entriesForXValue2)) {
                    Entry entry2 = (Entry) entriesForXValue2.get(0);
                    MPPointD pixelForValues3 = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(entry2.getX(), entry2.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues3.x, (float) pixelForValues3.y);
                    dwf_(canvas, (float) pixelForValues3.x, (float) pixelForValues3.y, iHwHealthLineDataSet, false, true, i);
                }
            }
        }
    }

    protected void dwf_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(i);
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.d.reset();
            this.d.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.d.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.d, this.mHighlightPaint);
        }
        if (z2) {
            float convertDpToPixel = Utils.convertDpToPixel(4.0f);
            float convertDpToPixel2 = Utils.convertDpToPixel(6.0f);
            if (this.mChart instanceof HwHealthCombinedChart) {
                if (a() != 0.0f) {
                    convertDpToPixel = a();
                }
                convertDpToPixel2 = 1.5f * convertDpToPixel;
            }
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(1.0f);
            this.mHighlightPaint.setShadowLayer(Utils.convertDpToPixel(2.0f), 0.0f, 0.0f, Color.argb(127, 0, 0, 0));
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(-1);
            canvas.drawCircle(f, f2, convertDpToPixel2, this.mHighlightPaint);
            this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(a(iLineScatterCandleRadarDataSet));
            canvas.drawCircle(f, f2, convertDpToPixel, this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        }
    }

    private float a() {
        List<T> dataSets = this.mChart.getData().getDataSets();
        if (dataSets.size() <= 0 || !(dataSets.get(0) instanceof HwHealthBarDataSet) || !(this.mChart instanceof HwHealthCombinedChart) || ((HwHealthCombinedChart) this.mChart).getBarData() == null) {
            return 0.0f;
        }
        float[] fArr = {0.0f, 0.0f, ((HwHealthCombinedChart) this.mChart).getBarData().d(), 0.0f};
        this.mChart.getTransformer(((HwHealthCombinedChart) this.mChart).getAxisFirstParty().e()).pointValuesToPixel(fArr);
        return (fArr[2] - fArr[0]) / 2.0f;
    }

    protected int a(ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet) {
        if (this.e) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iLineScatterCandleRadarDataSet.getColor();
    }

    protected int c(List<IHwHealthLineDataSet> list, int i) {
        if (i == 1) {
            return list.get(0).getColor();
        }
        return Color.argb(127, 0, 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v5, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r8v1, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawCubicBezier(IHwHealthLineDataSet iHwHealthLineDataSet) {
        IHwHealthLineDataSet iHwHealthLineDataSet2 = iHwHealthLineDataSet;
        float phaseY = this.mAnimator.getPhaseY();
        HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
        this.mXBounds.set(this.mChart, iHwHealthLineDataSet2);
        float cubicIntensity = iHwHealthLineDataSet.getCubicIntensity();
        this.mCubicPath.reset();
        if (this.mXBounds.range >= 1) {
            int i = this.mXBounds.min;
            ?? entryForIndex = iHwHealthLineDataSet2.getEntryForIndex(Math.max(i, 0));
            if (entryForIndex != 0) {
                this.mCubicPath.moveTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
                int i2 = -1;
                T entryForIndex2 = iHwHealthLineDataSet2.getEntryForIndex(Math.max(i - 1, 0));
                int i3 = this.mXBounds.min + 1;
                Entry entry = entryForIndex;
                Entry entry2 = entryForIndex2;
                Entry entry3 = entryForIndex;
                while (true) {
                    Entry entry4 = entry;
                    if (i3 > this.mXBounds.range + this.mXBounds.min) {
                        break;
                    }
                    if (i2 != i3) {
                        entry4 = iHwHealthLineDataSet2.getEntryForIndex(i3);
                    }
                    int i4 = i3 + 1;
                    if (i4 < iHwHealthLineDataSet.getEntryCount()) {
                        i3 = i4;
                    }
                    ?? entryForIndex3 = iHwHealthLineDataSet2.getEntryForIndex(i3);
                    float x = entry4.getX();
                    float x2 = entry2.getX();
                    float y = entry4.getY();
                    float y2 = entry2.getY();
                    float x3 = entryForIndex3.getX();
                    float x4 = entry3.getX();
                    float y3 = entryForIndex3.getY();
                    float y4 = entry3.getY();
                    this.mCubicPath.cubicTo(entry3.getX() + ((x - x2) * cubicIntensity), (entry3.getY() + ((y - y2) * cubicIntensity)) * phaseY, entry4.getX() - ((x3 - x4) * cubicIntensity), (entry4.getY() - ((y3 - y4) * cubicIntensity)) * phaseY, entry4.getX(), entry4.getY() * phaseY);
                    iHwHealthLineDataSet2 = iHwHealthLineDataSet;
                    entry2 = entry3;
                    entry3 = entry4;
                    entry = entryForIndex3;
                    i2 = i3;
                    i3 = i4;
                }
            } else {
                return;
            }
        }
        if (iHwHealthLineDataSet.isDrawFilledEnabled()) {
            this.mCubicFillPath.reset();
            this.mCubicFillPath.addPath(this.mCubicPath);
            drawCubicFill();
        }
        this.mRenderPaint.setColor(c(iHwHealthLineDataSet));
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        transformer.pathValueToPixel(this.mCubicPath);
        this.mBitmapCanvas.drawPath(this.mCubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        nnj axisDataRenderArg = this.mChart.getAxisDataRenderArg(iHwHealthLineDataSet.getAxisDependencyExt());
        e();
        if (axisDataRenderArg != null) {
            if (axisDataRenderArg.a()) {
                a(iHwHealthLineDataSet, axisDataRenderArg);
            } else {
                d(iHwHealthLineDataSet, axisDataRenderArg);
            }
        }
    }

    private boolean e(IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (!iHwHealthLineDataSet.isDataCalculated()) {
            return false;
        }
        if (!(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            LogUtil.b("HealthChart_HwHealthLineChartRendererSepBranch", "!(dataSet instanceof HwHealthLineDataSet)");
            return false;
        }
        if (((HwHealthLineDataSet) iHwHealthLineDataSet).h()) {
            return true;
        }
        LogUtil.b("HealthChart_HwHealthLineChartRendererSepBranch", "drawLadderLinear needs isDisplayIntervalSetted");
        return false;
    }

    private void b(IHwHealthLineDataSet iHwHealthLineDataSet) {
        int entryCount = iHwHealthLineDataSet.getEntryCount() * 8;
        if (this.i.length < Math.max(entryCount, 8)) {
            this.i = new float[Math.max(entryCount, 8)];
        }
        if (this.f.length < Math.max(entryCount, 8)) {
            this.f = new float[Math.max(entryCount, 8)];
        }
    }

    private void d(IHwHealthLineDataSet iHwHealthLineDataSet, Transformer transformer, nnj nnjVar) {
        if (!iHwHealthLineDataSet.isDrawFilledEnabled() || iHwHealthLineDataSet.getEntryCount() <= 0) {
            return;
        }
        cCL_(this.mBitmapCanvas, iHwHealthLineDataSet, transformer, this.mXBounds, nnjVar);
    }

    protected void a(IHwHealthLineDataSet iHwHealthLineDataSet, nnj nnjVar) {
        if (e(iHwHealthLineDataSet)) {
            d();
            HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
            Canvas canvas = this.mBitmapCanvas;
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            d(iHwHealthLineDataSet, transformer, nnjVar);
            b(iHwHealthLineDataSet);
            if (iHwHealthLineDataSet.getEntryForIndex(this.mXBounds.min) != 0) {
                ArrayList arrayList = new ArrayList();
                int[] iArr = {0, 0};
                int[] iArr2 = {0, 0};
                for (int i = this.mXBounds.min; i <= this.mXBounds.range + this.mXBounds.min; i++) {
                    Entry entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i);
                    if (entryForIndex != null) {
                        Entry d = d(iHwHealthLineDataSet, i);
                        Entry c = c(iHwHealthLineDataSet, i);
                        if (this.j != 0) {
                            arrayList.add(new PointF(entryForIndex.getX(), entryForIndex.getY() * this.mAnimator.getPhaseY()));
                        }
                        float a2 = ((HwHealthLineDataSet) iHwHealthLineDataSet).a();
                        boolean c2 = c(iHwHealthLineDataSet, d, entryForIndex);
                        boolean c3 = c(iHwHealthLineDataSet, entryForIndex, c);
                        if (!c2 && !c3) {
                            e(iArr2, entryForIndex, a2);
                        }
                        if (d != null) {
                            c(iArr, d, entryForIndex, c != null, a2, c2);
                        }
                    }
                }
                cCM_(canvas, iArr, iHwHealthLineDataSet, transformer, 2);
                cCG_(canvas, transformer, iHwHealthLineDataSet, iArr2, arrayList);
            }
            this.mRenderPaint.setPathEffect(null);
        }
    }

    protected void d(IHwHealthLineDataSet iHwHealthLineDataSet) {
        this.mRenderPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.mRenderPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mRenderPaint.setColor(c(iHwHealthLineDataSet));
    }

    protected void cCM_(Canvas canvas, int[] iArr, IHwHealthLineDataSet iHwHealthLineDataSet, Transformer transformer, int i) {
        if (iArr[0] <= 0) {
            return;
        }
        d(iHwHealthLineDataSet);
        transformer.pointValuesToPixel(this.i);
        int max = Math.max(iArr[1] * i, i);
        Path path = new Path();
        float[] fArr = this.i;
        path.moveTo(fArr[0], fArr[1]);
        float[] fArr2 = this.i;
        float f = fArr2[0];
        float f2 = fArr2[1];
        int i2 = 0;
        float f3 = f;
        while (true) {
            int i3 = (i2 * 2) + 1;
            if (i3 < max) {
                float[] fArr3 = this.i;
                int i4 = i2 * 4;
                float f4 = fArr3[i4];
                float f5 = fArr3[i4 + 1];
                int i5 = i3 * 2;
                float f6 = fArr3[i5];
                float f7 = fArr3[i5 + 1];
                if (b(f3, f4) && b(f2, f5)) {
                    path.lineTo(f6, f7);
                } else {
                    cCY_(canvas, f, f3, iHwHealthLineDataSet, path);
                    path.moveTo(f4, f5);
                    path.lineTo(f6, f7);
                    f = f4;
                }
                i2++;
                f3 = f6;
                f2 = f7;
            } else {
                cCY_(canvas, f, f3, iHwHealthLineDataSet, path);
                return;
            }
        }
    }

    protected boolean c(IHwHealthLineDataSet iHwHealthLineDataSet, Entry entry, Entry entry2) {
        if (entry == null || entry2 == null || !(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            return false;
        }
        return ((HwHealthLineDataSet) iHwHealthLineDataSet).b((int) entry.getX(), (int) entry2.getX());
    }

    private void c(int[] iArr, Entry entry, Entry entry2, boolean z, float f, boolean z2) {
        float phaseY = this.mAnimator.getPhaseY();
        int i = iArr[0];
        if (!z2) {
            this.i[i] = entry.getX();
            this.i[i + 1] = entry.getY() * phaseY;
            this.i[i + 2] = entry.getX() + f;
            this.i[i + 3] = entry.getY() * phaseY;
            iArr[1] = iArr[1] + 1;
            iArr[0] = i + 4;
            return;
        }
        this.i[i] = entry.getX();
        this.i[i + 1] = entry.getY() * phaseY;
        this.i[i + 2] = entry2.getX();
        this.i[i + 3] = entry.getY() * phaseY;
        iArr[1] = iArr[1] + 1;
        this.i[i + 4] = entry2.getX();
        this.i[i + 5] = entry.getY() * phaseY;
        this.i[i + 6] = entry2.getX();
        int i2 = i + 8;
        this.i[i + 7] = entry2.getY() * phaseY;
        iArr[1] = iArr[1] + 1;
        if (!z) {
            this.i[i2] = entry2.getX();
            this.i[i + 9] = entry2.getY() * phaseY;
            this.i[i + 10] = entry2.getX() + f;
            i2 = i + 12;
            this.i[i + 11] = entry2.getY() * phaseY;
            iArr[1] = iArr[1] + 1;
        }
        iArr[0] = i2;
    }

    protected void e(int[] iArr, Entry entry, float f) {
        float phaseY = this.mAnimator.getPhaseY();
        int i = iArr[0];
        this.f[i] = entry.getX();
        this.f[i + 1] = entry.getY() * phaseY;
        this.f[i + 2] = entry.getX() + f;
        this.f[i + 3] = entry.getY() * phaseY;
        iArr[0] = i + 4;
        iArr[1] = iArr[1] + 1;
    }

    protected void c(int i, int i2) {
        int i3 = i * i2;
        int max = Math.max(i3, i2) * 2;
        int max2 = Math.max(i3, i2) * 4;
        if (this.i.length < max) {
            this.i = new float[max2];
        }
        if (this.f.length < max) {
            this.f = new float[max2];
        }
    }

    protected void d(IHwHealthLineDataSet iHwHealthLineDataSet, nnj nnjVar) {
        if (iHwHealthLineDataSet.isDataCalculated()) {
            d();
            HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            d(iHwHealthLineDataSet, transformer, nnjVar);
            int i = iHwHealthLineDataSet.isDrawSteppedEnabled() ? 4 : 2;
            c(iHwHealthLineDataSet.getEntryCount(), i);
            cCN_(iHwHealthLineDataSet, i, transformer, this.mBitmapCanvas);
            this.mRenderPaint.setPathEffect(null);
        }
    }

    protected void cCN_(IHwHealthLineDataSet iHwHealthLineDataSet, int i, Transformer transformer, Canvas canvas) {
        if (iHwHealthLineDataSet.getEntryForIndex(this.mXBounds.min) == 0) {
            return;
        }
        int[] iArr = {0, 0};
        int[] iArr2 = {0, 0};
        ArrayList arrayList = new ArrayList();
        for (int i2 = this.mXBounds.min; i2 <= this.mXBounds.range + this.mXBounds.min; i2++) {
            Entry entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i2);
            if (entryForIndex != null) {
                if (this.j != 0) {
                    float x = entryForIndex.getX();
                    if (x > this.mChart.getLowestVisibleX() && x < this.mChart.getHighestVisibleX()) {
                        arrayList.add(new PointF(entryForIndex.getX(), entryForIndex.getY() * this.mAnimator.getPhaseY()));
                    }
                }
                Entry d = d(iHwHealthLineDataSet, i2);
                Entry c = c(iHwHealthLineDataSet, i2);
                boolean c2 = c(iHwHealthLineDataSet, d, entryForIndex);
                boolean c3 = c(iHwHealthLineDataSet, entryForIndex, c);
                if (!c2 && !c3) {
                    e(iArr, entryForIndex, 0.0f);
                }
                if (d != null && c2) {
                    e(iArr2, d, entryForIndex);
                }
            }
        }
        cCM_(canvas, iArr2, iHwHealthLineDataSet, transformer, i);
        cCG_(canvas, transformer, iHwHealthLineDataSet, iArr, arrayList);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.github.mikephil.charting.data.Entry] */
    private Entry d(IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        if (i != 0) {
            return iHwHealthLineDataSet.getEntryForIndex(i - 1);
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.github.mikephil.charting.data.Entry] */
    private Entry c(IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        if (i != this.mXBounds.range + this.mXBounds.min) {
            return iHwHealthLineDataSet.getEntryForIndex(i + 1);
        }
        return null;
    }

    protected void d() {
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        this.mRenderPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    protected void e(int[] iArr, Entry entry, Entry entry2) {
        float phaseY = this.mAnimator.getPhaseY();
        int i = iArr[0];
        this.i[i] = entry.getX();
        this.i[i + 1] = entry.getY() * phaseY;
        this.i[i + 2] = entry2.getX();
        this.i[i + 3] = entry2.getY() * phaseY;
        iArr[0] = i + 4;
        iArr[1] = iArr[1] + 1;
    }

    protected void cCY_(Canvas canvas, float f, float f2, IHwHealthLineDataSet iHwHealthLineDataSet, Path path) {
        int save = canvas.save();
        canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
        if (this.f15416a) {
            canvas.clipPath(this.b, Region.Op.DIFFERENCE);
        }
        canvas.drawPath(path, this.mRenderPaint);
        canvas.restoreToCount(save);
        if (this.f15416a) {
            cCC_(canvas, f, f2, iHwHealthLineDataSet, path);
        }
        path.reset();
    }

    private void cCC_(Canvas canvas, float f, float f2, IHwHealthLineDataSet iHwHealthLineDataSet, Path path) {
        int save = canvas.save();
        canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
        int color = this.mRenderPaint.getColor();
        this.mRenderPaint.setColor(b(iHwHealthLineDataSet, true));
        canvas.clipPath(this.b);
        canvas.drawPath(path, this.mRenderPaint);
        this.mRenderPaint.setColor(color);
        canvas.restoreToCount(save);
    }

    public void cCG_(Canvas canvas, Transformer transformer, IHwHealthLineDataSet iHwHealthLineDataSet, int[] iArr, List<PointF> list) {
        if (iArr[0] > 0) {
            this.mRenderPaint.setStrokeCap(Paint.Cap.ROUND);
            transformer.pointValuesToPixel(this.f);
            this.mRenderPaint.setColor(c(iHwHealthLineDataSet));
            float strokeWidth = this.mRenderPaint.getStrokeWidth();
            if (iHwHealthLineDataSet instanceof HwHealthLineDataSet) {
                this.mRenderPaint.setStrokeWidth(((HwHealthLineDataSet) iHwHealthLineDataSet).d(iHwHealthLineDataSet.isDrawFilledEnabled()));
            }
            canvas.drawLines(this.f, 0, iArr[1] * 4, this.mRenderPaint);
            this.mRenderPaint.setStrokeWidth(strokeWidth);
        }
        if (this.j == 0 || list == null || list.size() <= 0) {
            return;
        }
        cCZ_(canvas, c(iHwHealthLineDataSet), list, iHwHealthLineDataSet);
    }

    protected void cCZ_(Canvas canvas, int i, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float convertDpToPixel = Utils.convertDpToPixel(4.0f);
        if (this.mChart instanceof HwHealthCombinedChart) {
            List<T> dataSets = this.mChart.getData().getDataSets();
            if (dataSets.size() > 0 && (dataSets.get(0) instanceof HwHealthBarDataSet) && (this.mChart instanceof HwHealthCombinedChart) && ((HwHealthCombinedChart) this.mChart).getBarData() != null) {
                float[] fArr = {0.0f, 0.0f, ((HwHealthCombinedChart) this.mChart).getBarData().d(), 0.0f};
                this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
                convertDpToPixel = (fArr[2] - fArr[0]) / 2.0f;
            }
        }
        if (this.j == 1) {
            paint.setStyle(Paint.Style.STROKE);
            int size = list.size() * 2;
            float[] fArr2 = new float[size];
            int i2 = 0;
            for (PointF pointF : list) {
                if (pointF != null) {
                    int i3 = i2 + 1;
                    fArr2[i2] = pointF.x;
                    i2 += 2;
                    fArr2[i3] = pointF.y;
                }
            }
            this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr2);
            for (int i4 = 0; i4 < size; i4 += 2) {
                paint.setAntiAlias(true);
                paint.setStrokeWidth(1.0f);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(i);
                float f = fArr2[i4];
                float f2 = fArr2[i4 + 1];
                canvas.drawCircle(f, f2, convertDpToPixel, paint);
                paint.setColor(-1);
                canvas.drawCircle(f, f2, convertDpToPixel / 2.0f, paint);
            }
        }
    }

    private boolean b(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-7f;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00bc A[LOOP:0: B:2:0x0015->B:22:0x00bc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00bf A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void cCL_(android.graphics.Canvas r18, com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet r19, com.github.mikephil.charting.utils.Transformer r20, com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer.XBounds r21, defpackage.nnj r22) {
        /*
            r17 = this;
            r6 = r17
            r7 = r18
            r8 = r19
            r9 = r20
            r0 = r21
            android.graphics.Path r10 = r6.mGenerateFilledPathBuffer
            int r1 = r0.min
            int r2 = r0.range
            int r0 = r0.min
            int r11 = r2 + r0
            r12 = r1
        L15:
            if (r12 > r11) goto Lbf
            com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider r0 = r6.mChart
            boolean r0 = r0 instanceof com.huawei.ui.commonui.linechart.view.HwHealthLineChart
            if (r0 != 0) goto L1f
            goto Lbf
        L1f:
            com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider r0 = r6.mChart
            com.huawei.ui.commonui.linechart.view.HwHealthLineChart r0 = (com.huawei.ui.commonui.linechart.view.HwHealthLineChart) r0
            com.huawei.ui.commonui.linechart.common.HwHealthYAxis r0 = r0.getAxisFirstParty()
            float[] r0 = r0.mEntries
            int r1 = r0.length
            r2 = 2
            if (r1 >= r2) goto L2f
            goto Lbf
        L2f:
            int r1 = r0.length
            int r1 = r1 - r2
            r0 = r0[r1]
            float[] r13 = new float[r2]
            r1 = 0
            r2 = 0
            r13[r2] = r1
            r14 = 1
            r13[r14] = r0
            r9.pointValuesToPixel(r13)
            int r15 = r6.cCD_(r8, r12, r10, r11)
            r9.pathValueToPixel(r10)
            int r0 = r12 + 1
            if (r0 != r15) goto L64
            boolean r0 = r8 instanceof com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet
            if (r0 == 0) goto L64
            r0 = r8
            com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet r0 = (com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet) r0
            boolean r0 = r0.a(r14)
            if (r0 == 0) goto L64
            r0 = r17
            r1 = r19
            r2 = r12
            r3 = r10
            r4 = r20
            r5 = r22
            r0.cCE_(r1, r2, r3, r4, r5)
        L64:
            boolean r0 = r8 instanceof com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet
            if (r0 == 0) goto Lb0
            r0 = r8
            com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet r0 = (com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet) r0
            boolean r1 = r0.g()
            if (r1 == 0) goto Lb0
            android.graphics.drawable.GradientDrawable r3 = r0.cCP_()
            android.graphics.drawable.GradientDrawable r16 = r0.cCO_()
            boolean r0 = r6.e
            if (r0 == 0) goto L98
            r0 = 128(0x80, float:1.8E-43)
            r1 = 204(0xcc, float:2.86E-43)
            int r2 = android.graphics.Color.argb(r0, r1, r1, r1)
            int r0 = android.graphics.Color.argb(r0, r1, r1, r1)
            int[] r0 = new int[]{r2, r0}
            android.graphics.drawable.GradientDrawable r1 = new android.graphics.drawable.GradientDrawable
            android.graphics.drawable.GradientDrawable$Orientation r2 = android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM
            r1.<init>(r2, r0)
            r6.drawFilledPath(r7, r10, r1)
            goto Lb9
        L98:
            if (r3 == 0) goto Lb9
            if (r16 == 0) goto Lb9
            r4 = r13[r14]
            r5 = 1
            r0 = r17
            r1 = r18
            r2 = r10
            r0.cCJ_(r1, r2, r3, r4, r5)
            r4 = r13[r14]
            r5 = 0
            r3 = r16
            r0.cCJ_(r1, r2, r3, r4, r5)
            goto Lb9
        Lb0:
            android.graphics.drawable.Drawable r0 = r19.getFillDrawable()
            if (r0 == 0) goto Lb9
            r6.drawFilledPath(r7, r10, r0)
        Lb9:
            if (r15 != r12) goto Lbc
            goto Lbf
        Lbc:
            r12 = r15
            goto L15
        Lbf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nox.cCL_(android.graphics.Canvas, com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet, com.github.mikephil.charting.utils.Transformer, com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds, nnj):void");
    }

    private int cCD_(IHwHealthLineDataSet iHwHealthLineDataSet, int i, Path path, int i2) {
        Entry entry;
        if (!(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            LogUtil.b("HealthChart_HwHealthLineChartRendererSepBranch", "!(dataSet instanceof HwHealthLineDataSet");
            return i;
        }
        boolean a2 = this.mChart.getAxisDataRenderArg(iHwHealthLineDataSet.getAxisDependencyExt()).a();
        float a3 = ((HwHealthLineDataSet) iHwHealthLineDataSet).a();
        int i3 = i + 128;
        if (i3 > i2) {
            i3 = i2;
        }
        float[] fArr = {0.0f, this.mChart.getContentRect().bottom + this.mChart.getXAxis().getYOffset()};
        this.mChart.getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY).pixelsToValue(fArr);
        float f = fArr[1];
        float phaseY = this.mAnimator.getPhaseY();
        path.reset();
        Entry entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i);
        path.moveTo(entryForIndex.getX(), f);
        path.lineTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
        Entry entry2 = null;
        int i4 = i;
        int i5 = i + 1;
        Entry entry3 = entryForIndex;
        while (true) {
            if (i5 > i3) {
                i5 = i4;
                break;
            }
            entry2 = iHwHealthLineDataSet.getEntryForIndex(i5);
            if (c(iHwHealthLineDataSet, entry3, entry2)) {
                if (!a2) {
                    path.lineTo(entry2.getX(), entry2.getY() * phaseY);
                } else {
                    path.lineTo(entry2.getX(), entry3.getY() * phaseY);
                    path.lineTo(entry2.getX(), entry2.getY() * phaseY);
                }
                if (i5 == i2) {
                    i5 = i2 + 1;
                    break;
                }
                entry3 = entry2;
                i4 = i5;
                i5++;
            } else {
                if (a2) {
                    path.lineTo(entry3.getX() + a3, entry3.getY() * phaseY);
                }
                entry = entry3;
            }
        }
        entry = entry2;
        cCB_(path, entry, f, a3, a2);
        return i5;
    }

    private void cCB_(Path path, Entry entry, float f, float f2, boolean z) {
        if (entry != null) {
            if (!z) {
                path.lineTo(entry.getX(), f);
            } else {
                path.lineTo(entry.getX() + f2, entry.getY() * this.mAnimator.getPhaseY());
                path.lineTo(entry.getX() + f2, f);
            }
        }
        path.close();
    }

    /* JADX WARN: Type inference failed for: r10v1, types: [com.github.mikephil.charting.data.Entry] */
    private void cCE_(IHwHealthLineDataSet iHwHealthLineDataSet, int i, Path path, Transformer transformer, nnj nnjVar) {
        if (!(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            LogUtil.b("HealthChart_HwHealthLineChartRendererSepBranch", "!(dataSet instanceof HwHealthLineDataSet)");
            return;
        }
        float[] fArr = {0.0f, this.mChart.getContentRect().bottom + this.mChart.getXAxis().getYOffset()};
        this.mChart.getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY).pixelsToValue(fArr);
        float f = fArr[1];
        float phaseY = this.mAnimator.getPhaseY();
        path.reset();
        HwHealthLineDataSet hwHealthLineDataSet = (HwHealthLineDataSet) iHwHealthLineDataSet;
        float d = hwHealthLineDataSet.d(true);
        ?? entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i);
        float[] fArr2 = {entryForIndex.getX(), f};
        float[] fArr3 = {entryForIndex.getX(), entryForIndex.getY() * phaseY};
        float[] fArr4 = {entryForIndex.getX() + hwHealthLineDataSet.a(), entryForIndex.getY() * phaseY};
        transformer.pointValuesToPixel(fArr2);
        transformer.pointValuesToPixel(fArr3);
        transformer.pointValuesToPixel(fArr4);
        if (!nnjVar.a()) {
            float f2 = d / 2.0f;
            path.moveTo(fArr3[0] - f2, fArr2[1]);
            path.lineTo(fArr3[0] - f2, fArr3[1]);
            path.lineTo(fArr3[0] + f2, fArr3[1]);
            path.lineTo(fArr3[0] + f2, fArr2[1]);
        } else {
            path.moveTo(fArr3[0], fArr2[1]);
            path.lineTo(fArr3[0], fArr3[1]);
            path.lineTo(fArr4[0], fArr4[1]);
            path.lineTo(fArr4[0], fArr2[1]);
        }
        path.close();
    }

    protected void cCJ_(Canvas canvas, Path path, Drawable drawable, float f, boolean z) {
        boolean z2 = this.f15416a;
        int save = canvas.save();
        canvas.clipPath(path);
        if (z2) {
            canvas.clipPath(this.b);
        }
        if (z) {
            drawable.setBounds((int) this.mViewPortHandler.contentLeft(), (int) this.mViewPortHandler.contentTop(), (int) this.mViewPortHandler.contentRight(), (int) f);
        } else {
            int contentBottom = (int) this.mViewPortHandler.contentBottom();
            if (c()) {
                contentBottom = (int) (contentBottom + this.mChart.getXAxis().getYOffset());
            }
            drawable.setBounds((int) this.mViewPortHandler.contentLeft(), (int) f, (int) this.mViewPortHandler.contentRight(), contentBottom);
        }
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // com.github.mikephil.charting.renderer.LineRadarRenderer
    public void drawFilledPath(Canvas canvas, Path path, Drawable drawable) {
        int save = canvas.save();
        canvas.clipPath(path);
        drawable.setBounds((int) this.mViewPortHandler.contentLeft(), (int) this.mViewPortHandler.contentTop(), (int) this.mViewPortHandler.contentRight(), (int) (this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset()));
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    public void usePaintAsBackground(boolean z) {
        this.e = z;
    }

    public boolean isUsePaintAsBackground() {
        return this.e;
    }

    protected int c(IHwHealthLineDataSet iHwHealthLineDataSet) {
        return b(iHwHealthLineDataSet, false);
    }

    private int b(IHwHealthLineDataSet iHwHealthLineDataSet, boolean z) {
        if (z) {
            return iHwHealthLineDataSet.getColor();
        }
        if (this.e) {
            return Color.argb(51, 0, 0, 0);
        }
        if (this.f15416a) {
            return Color.argb(51, 0, 0, 0);
        }
        return iHwHealthLineDataSet.getColor();
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean hasData() {
        now dataset = getDataset();
        return (dataset == null || dataset.getDataSets() == null || dataset.getDataSets().size() == 0) ? false : true;
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void focusArea(List<nnl> list) {
        boolean z;
        int i = 0;
        if (this.f15416a) {
            z = false;
        } else {
            z = true;
            this.f15416a = true;
        }
        if (list != null && list.size() == this.c.size()) {
            while (true) {
                if (i < this.c.size()) {
                    if (list.get(i).c() != this.c.get(i).c() || list.get(i).e() != this.c.get(i).e()) {
                        break;
                    } else {
                        i++;
                    }
                } else if (!z) {
                    return;
                }
            }
        }
        this.c.clear();
        if (list != null) {
            this.c.addAll(list);
        }
        if (this.mChart instanceof HwHealthBaseBarLineChart) {
            ((HwHealthBaseBarLineChart) this.mChart).invalidate();
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void disableFocusArea() {
        this.f15416a = false;
        this.c.clear();
        this.b.reset();
        if (this.mChart instanceof HwHealthBaseBarLineChart) {
            ((HwHealthBaseBarLineChart) this.mChart).invalidate();
        }
    }

    private void e() {
        if (this.f15416a) {
            this.b.reset();
            for (nnl nnlVar : this.c) {
                if (nnlVar != null) {
                    float[] fArr = {nnlVar.c(), 0.0f, nnlVar.e(), 0.0f};
                    if (this.mChart instanceof HwHealthBaseBarLineChart) {
                        this.mChart.getTransformer(((HwHealthBaseBarLineChart) this.mChart).getAxisFirstParty().e()).pointValuesToPixel(fArr);
                    }
                    this.b.addRect(fArr[0], this.mViewPortHandler.contentTop(), fArr[2], (int) (this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset()), Path.Direction.CW);
                }
            }
        }
    }
}

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
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class edf extends nox {

    /* renamed from: a, reason: collision with root package name */
    private int f11960a;
    private boolean b;
    private Path c;
    private Paint e;
    private int g;
    private Paint h;

    @Override // defpackage.nox
    protected boolean c() {
        return false;
    }

    public edf(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.g = 1;
        this.h = new Paint();
        this.b = false;
        this.c = new Path();
        this.e = new Paint();
        this.f11960a = context.getResources().getColor(R.color._2131299086_res_0x7f090b0e);
        e();
    }

    private void e() {
        this.h.setColor(this.f11960a);
        this.h.setAntiAlias(true);
        this.h.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.h.setStyle(Paint.Style.STROKE);
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (iHwHealthLineDataSet.isDataCalculated()) {
            int entryCount = iHwHealthLineDataSet.getEntryCount();
            boolean isDrawSteppedEnabled = iHwHealthLineDataSet.isDrawSteppedEnabled();
            int i = isDrawSteppedEnabled ? 4 : 2;
            HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
            float phaseY = this.mAnimator.getPhaseY();
            this.mRenderPaint.setStyle(Paint.Style.STROKE);
            Canvas canvas2 = this.mBitmapCanvas;
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            if (iHwHealthLineDataSet.getColors().size() > 1) {
                if (this.i.length <= i * 2) {
                    this.i = new float[i * 4];
                }
                agc_(iHwHealthLineDataSet, isDrawSteppedEnabled, transformer, phaseY, canvas2);
            } else {
                b(entryCount, i);
                afZ_((HwHealthLineDataSet) iHwHealthLineDataSet, i, transformer, phaseY, canvas2);
            }
            this.mRenderPaint.setPathEffect(null);
        }
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [com.github.mikephil.charting.data.Entry] */
    private void agc_(IHwHealthLineDataSet iHwHealthLineDataSet, boolean z, Transformer transformer, float f, Canvas canvas) {
        int i = z ? 4 : 2;
        for (int i2 = this.mXBounds.min; i2 <= this.mXBounds.range + this.mXBounds.min; i2++) {
            ?? entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i2);
            if (entryForIndex != 0) {
                this.i[0] = entryForIndex.getX();
                this.i[1] = entryForIndex.getY() * f;
                if (i2 < this.mXBounds.max) {
                    Entry entryForIndex2 = iHwHealthLineDataSet.getEntryForIndex(i2 + 1);
                    if (entryForIndex2 == null) {
                        return;
                    } else {
                        b(z, f, entryForIndex2);
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
                    this.mRenderPaint.setColor(iHwHealthLineDataSet.getColor(i2));
                    canvas.drawLines(this.i, 0, i * 2, this.mRenderPaint);
                }
            }
        }
    }

    private void b(int i, int i2) {
        int i3 = i * i2;
        if (this.i.length < Math.max(i3, i2) * 2) {
            this.i = new float[Math.max(i3, i2) * 4];
        }
        if (this.f.length < Math.max(i3, i2) * 2) {
            this.f = new float[Math.max(i3, i2) * 4];
        }
    }

    private void b(boolean z, float f, Entry entry) {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.github.mikephil.charting.data.Entry] */
    private void afZ_(HwHealthLineDataSet hwHealthLineDataSet, int i, Transformer transformer, float f, Canvas canvas) {
        T entryForIndex = hwHealthLineDataSet.getEntryForIndex(this.mXBounds.min);
        ArrayList arrayList = new ArrayList(31);
        if (entryForIndex != 0) {
            int i2 = this.mXBounds.min;
            int i3 = 0;
            int i4 = 0;
            while (i2 <= this.mXBounds.range + this.mXBounds.min) {
                Entry entryForIndex2 = i2 != 0 ? hwHealthLineDataSet.getEntryForIndex(i2 - 1) : null;
                ?? entryForIndex3 = hwHealthLineDataSet.getEntryForIndex(i2);
                if (entryForIndex3 != 0) {
                    if (this.g != 0) {
                        arrayList.add(new PointF(entryForIndex3.getX(), entryForIndex3.getY() * f));
                    }
                    if (entryForIndex2 != null) {
                        this.i[i3] = entryForIndex2.getX();
                        this.i[i3 + 1] = entryForIndex2.getY() * f;
                        this.i[i3 + 2] = entryForIndex3.getX();
                        this.i[i3 + 3] = entryForIndex3.getY() * f;
                        i4++;
                        i3 += 4;
                    }
                }
                i2++;
            }
            aga_(i, transformer, canvas, i3, i4);
            if (this.g == 0 || arrayList.size() <= 0) {
                return;
            }
            afX_(canvas, arrayList, hwHealthLineDataSet);
        }
    }

    private void aga_(int i, Transformer transformer, Canvas canvas, int i2, int i3) {
        if (i2 <= 0) {
            return;
        }
        transformer.pointValuesToPixel(this.i);
        int max = Math.max(i3 * i, i);
        Path path = new Path();
        int i4 = 0;
        path.moveTo(this.i[0], this.i[1]);
        float f = this.i[0];
        float f2 = this.i[0];
        float f3 = this.i[0];
        float f4 = this.i[1];
        while (true) {
            int i5 = (i4 * 2) + 1;
            if (i5 < max) {
                int i6 = i4 * 4;
                float f5 = this.i[i6];
                float f6 = this.i[i6 + 1];
                int i7 = i5 * 2;
                float f7 = this.i[i7];
                int i8 = max;
                float f8 = this.i[i7 + 1];
                if (e(f3, f5) && e(f4, f6)) {
                    path.lineTo(f7, f8);
                } else {
                    int save = canvas.save();
                    LogUtil.c("SleepManagementLineChartRender", "save is ：", Integer.valueOf(save));
                    canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
                    canvas.drawPath(path, this.h);
                    canvas.restoreToCount(save);
                    path.reset();
                    path.moveTo(f5, f6);
                    path.lineTo(f7, f8);
                    f = f5;
                }
                i4++;
                f4 = f8;
                f2 = f7;
                f3 = f2;
                max = i8;
            } else {
                int save2 = canvas.save();
                LogUtil.c("SleepManagementLineChartRender", "save is ：", Integer.valueOf(save2));
                canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
                canvas.drawPath(path, this.h);
                canvas.restoreToCount(save2);
                path.reset();
                return;
            }
        }
    }

    private boolean e(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-7f;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        this.b = z;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.b;
    }

    private void afX_(Canvas canvas, List<PointF> list, HwHealthLineDataSet hwHealthLineDataSet) {
        this.e.setAntiAlias(true);
        float convertDpToPixel = Utils.convertDpToPixel(8.0f);
        if (this.g == 1) {
            this.e.setStyle(Paint.Style.STROKE);
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
            this.mChart.getTransformer(hwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
            c cVar = new c();
            this.e.setColor(this.f11960a);
            cVar.agf_(this.e);
            cVar.b(fArr);
            cVar.c(fArr2);
            agb_(canvas, hwHealthLineDataSet, convertDpToPixel, cVar);
            this.e.reset();
        }
    }

    private void agb_(Canvas canvas, HwHealthLineDataSet hwHealthLineDataSet, float f, c cVar) {
        String[] strArr;
        edf edfVar = this;
        int i = 0;
        int i2 = 0;
        while (i < cVar.e().length) {
            cVar.age_().setAntiAlias(true);
            cVar.age_().setStyle(Paint.Style.FILL);
            float f2 = cVar.e()[i];
            float f3 = cVar.e()[i + 1];
            List<Integer> circleColors = hwHealthLineDataSet.getCircleColors();
            afW_(canvas, f, cVar, i, circleColors, i2);
            if (i2 == (cVar.e().length / 2) - 1) {
                cVar.age_().setTextAlign(LanguageUtil.bc(BaseApplication.getContext()) ? Paint.Align.LEFT : Paint.Align.RIGHT);
                cVar.age_().setColor(circleColors.get(circleColors.size() - 1).intValue());
                cVar.age_().setTextSize(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
                String[] c2 = edfVar.c(hwHealthLineDataSet);
                float b = edfVar.b(hwHealthLineDataSet);
                if (b > Math.pow(10.0d, -6.0d)) {
                    float f4 = ((f2 / b) * 96.0f) - f2;
                    if (c2.length == 1) {
                        strArr = c2;
                        canvas.drawText(strArr[0], f2 + f4, afY_(c2, canvas, f3, f, 1), cVar.age_());
                    } else {
                        strArr = c2;
                    }
                    if (strArr.length == 2) {
                        float f5 = f2 + f4;
                        canvas.drawText(strArr[0], f5, afY_(strArr, canvas, f3, f, 1), cVar.age_());
                        canvas.drawText(strArr[1], f5, afY_(strArr, canvas, f3, f, 2), cVar.age_());
                    }
                }
            }
            i += 2;
            i2++;
            edfVar = this;
        }
    }

    private int afY_(String[] strArr, Canvas canvas, float f, float f2, int i) {
        float f3 = strArr.length == 1 ? f < ((float) canvas.getHeight()) / 2.0f ? (f2 * 3.0f) + f : f - (f2 * 2.0f) : 0.0f;
        if (strArr.length == 2) {
            if (f < canvas.getHeight() / 2.0f) {
                if (i == 1) {
                    f3 = f + (3.0f * f2);
                }
                if (i == 2) {
                    f3 = f + (f2 * 5.0f);
                }
            } else {
                if (i == 1) {
                    f3 = f - (4.0f * f2);
                }
                if (i == 2) {
                    f3 = f - (f2 * 2.0f);
                }
            }
        }
        return (int) f3;
    }

    private String[] c(HwHealthLineDataSet hwHealthLineDataSet) {
        HwHealthBaseEntry hwHealthBaseEntry;
        String[] strArr = new String[0];
        List<T> acquireEntryVals = hwHealthLineDataSet.acquireEntryVals();
        if (koq.b(acquireEntryVals) || (hwHealthBaseEntry = (HwHealthBaseEntry) acquireEntryVals.get(acquireEntryVals.size() - 1)) == null) {
            return strArr;
        }
        Object data = hwHealthBaseEntry.getData();
        if (!(data instanceof String)) {
            LogUtil.a("SleepManagementLineChartRender", "des is not string");
            return strArr;
        }
        return ((String) data).split(",");
    }

    private float b(HwHealthLineDataSet hwHealthLineDataSet) {
        HwHealthBaseEntry hwHealthBaseEntry;
        List<T> acquireEntryVals = hwHealthLineDataSet.acquireEntryVals();
        if (koq.b(acquireEntryVals) || (hwHealthBaseEntry = (HwHealthBaseEntry) acquireEntryVals.get(acquireEntryVals.size() - 1)) == null) {
            return 0.0f;
        }
        return hwHealthBaseEntry.getX();
    }

    private void afW_(Canvas canvas, float f, c cVar, int i, List<Integer> list, int i2) {
        if (i < 0) {
            return;
        }
        float f2 = cVar.e()[i];
        float f3 = cVar.e()[i + 1];
        if (koq.b(list, i2)) {
            cVar.age_().setColor(this.f11960a);
        } else {
            cVar.age_().setColor(list.get(i2).intValue());
        }
        canvas.drawCircle(f2, f3, f / 2.0f, cVar.age_());
        cVar.age_().setColor(nrn.d(R.color._2131298652_res_0x7f09095c));
        canvas.drawCircle(f2, f3, f / 4.0f, cVar.age_());
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        boolean z;
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length <= 0 || lineData == null || canvas == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            List<T> dataSets = lineData.getDataSets();
            if (dataSets != 0 && dataSets.size() != 0) {
                int argb = Color.argb(0, 0, 0, 0);
                if (dataSets.size() == 1) {
                    List<T> entriesForXValue = ((IHwHealthLineDataSet) dataSets.get(0)).getEntriesForXValue(highlight.getX());
                    if (entriesForXValue != 0 && entriesForXValue.size() != 0) {
                        Entry entry = (Entry) entriesForXValue.get(0);
                        if (this.b && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) {
                            entry = new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue());
                            z = false;
                        } else {
                            z = true;
                        }
                        MPPointD pixelForValues = this.mChart.getTransformer(((IHwHealthLineDataSet) dataSets.get(0)).getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
                        highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                        dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, (ILineScatterCandleRadarDataSet) dataSets.get(0), true, z, argb);
                    }
                } else {
                    MPPointD pixelForValues2 = this.mChart.getTransformer(((IHwHealthLineDataSet) dataSets.get(0)).getAxisDependencyExt()).getPixelForValues(highlight.getX(), 0.0f);
                    double d = pixelForValues2.x;
                    double d2 = pixelForValues2.y;
                    dwf_(canvas, (float) pixelForValues2.x, (float) pixelForValues2.y, (ILineScatterCandleRadarDataSet) dataSets.get(0), true, false, argb);
                    for (T t : dataSets) {
                        List<T> entriesForXValue2 = t.getEntriesForXValue(highlight.getX());
                        if (entriesForXValue2 != 0 && entriesForXValue2.size() != 0) {
                            Entry entry2 = (Entry) entriesForXValue2.get(0);
                            MPPointD pixelForValues3 = this.mChart.getTransformer(t.getAxisDependencyExt()).getPixelForValues(entry2.getX(), entry2.getY() * this.mAnimator.getPhaseY());
                            highlight.setDraw((float) pixelForValues3.x, (float) pixelForValues3.y);
                            dwf_(canvas, (float) pixelForValues3.x, (float) pixelForValues3.y, t, false, true, argb);
                        }
                    }
                }
            }
        }
    }

    @Override // defpackage.nox
    protected void dwf_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(i);
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.c.reset();
            this.c.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.c.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.c, this.mHighlightPaint);
        }
        if (z2) {
            float convertDpToPixel = Utils.convertDpToPixel(8.0f);
            float convertDpToPixel2 = Utils.convertDpToPixel(12.0f);
            if (this.mChart instanceof HwHealthCombinedChart) {
                List<T> dataSets = this.mChart.getData().getDataSets();
                if (dataSets.size() > 0 && (dataSets.get(0) instanceof HwHealthBarDataSet)) {
                    float[] fArr = {0.0f, 0.0f, d(0.0f), 0.0f};
                    this.mChart.getTransformer(((HwHealthCombinedChart) this.mChart).getAxisFirstParty().e()).pointValuesToPixel(fArr);
                    convertDpToPixel = (fArr[2] - fArr[0]) / 2.0f;
                    convertDpToPixel2 = 1.5f * convertDpToPixel;
                }
            }
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(3.0f);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(nrn.d(R.color._2131298596_res_0x7f090924));
            canvas.drawCircle(f, f2, convertDpToPixel2 / 2.0f, this.mHighlightPaint);
            this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            List<Integer> circleColors = ((HwHealthLineDataSet) iLineScatterCandleRadarDataSet).getCircleColors();
            this.mHighlightPaint.setColor(circleColors.get(circleColors.size() - 1).intValue());
            canvas.drawCircle(f, f2, convertDpToPixel / 2.0f, this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        }
    }

    private float d(float f) {
        nmz barData = ((HwHealthCombinedChart) this.mChart).getBarData();
        return barData != null ? barData.d() : f;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private Paint f11961a;
        private float[] c;
        private float[] e;

        c() {
        }

        public float[] e() {
            return this.c;
        }

        public void b(float[] fArr) {
            this.c = fArr;
        }

        public void c(float[] fArr) {
            this.e = fArr;
        }

        public Paint age_() {
            return this.f11961a;
        }

        public void agf_(Paint paint) {
            this.f11961a = paint;
        }
    }
}

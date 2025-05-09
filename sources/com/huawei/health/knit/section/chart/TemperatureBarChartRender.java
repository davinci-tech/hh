package com.huawei.health.knit.section.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import defpackage.edj;
import defpackage.edm;
import defpackage.nmz;
import defpackage.not;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.List;

/* loaded from: classes3.dex */
public class TemperatureBarChartRender extends BarChartRender implements IHwHealthDataRender {
    private float f;
    private boolean g;
    private String h;
    private float i;
    private float k;
    private float l;
    private final Paint m;
    private float n;
    private OnTemperatureTimeChangedListener o;
    private float s;

    public interface OnTemperatureTimeChangedListener {
        void onTemperatureTimeChangedListener(edm edmVar);
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    public TemperatureBarChartRender(HwHealthBarChart hwHealthBarChart, HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
        this.g = false;
        this.m = new Paint();
        this.h = "TEMPERATURE_MIN_MAX";
        c(hwHealthBarChart, hwHealthBarDataProvider);
        this.k = b(37.0f);
        this.f = b(37.15f);
        this.l = b(37.5f);
        this.s = b(37.95f);
    }

    public static float b(float f) {
        return UnitUtil.d() ? f : a(f);
    }

    public static float a(float f) {
        return new BigDecimal((f * 1.8f) + 32.0f).setScale(1, 4).floatValue();
    }

    public void d(String str) {
        this.h = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.nnb
    public void dGJ_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, int i) {
        IHwHealthBarDataSet iHwHealthBarDataSet2;
        nmz barData = this.e.getBarData();
        if (canvas == null || barData == null || i < 0 || (iHwHealthBarDataSet2 = (IHwHealthBarDataSet) barData.getDataSetByIndex(i)) == null || !iHwHealthBarDataSet2.isHighlightEnabled() || iHwHealthBarDataSet == null) {
            return;
        }
        this.b.setColor(iHwHealthBarDataSet.getBarBorderColor());
        this.b.setStrokeWidth(Utils.convertDpToPixel(iHwHealthBarDataSet.getBarBorderWidth()));
        not notVar = this.f15395a[i];
        notVar.setPhases(this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY());
        notVar.setDataSet(i);
        notVar.setInverted(false);
        notVar.setBarWidth(this.e.getBarData().d());
        notVar.b(iHwHealthBarDataSet);
        HwHealthTransformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
        transformer.pointValuesToPixel(notVar.buffer);
        e(transformer);
        boolean z = iHwHealthBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(c(iHwHealthBarDataSet));
        }
        for (int i2 = 0; i2 < notVar.size(); i2 += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(notVar.buffer[i2])) {
                int i3 = i2 + 2;
                if (this.mViewPortHandler.isInBoundsRight(notVar.buffer[i3])) {
                    if (!z) {
                        this.mRenderPaint.setColor(iHwHealthBarDataSet.getColor(i2 / 4));
                    }
                    this.c.set(notVar.buffer[i2], notVar.buffer[i2 + 1], notVar.buffer[i3], notVar.buffer[i2 + 3]);
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i2 / 4);
                    if (hwHealthBarEntry == null) {
                        return;
                    }
                    IStorageModel acquireModel = hwHealthBarEntry.acquireModel();
                    if (!(acquireModel instanceof edm)) {
                        return;
                    } else {
                        ago_(canvas, this.c, 2, ((edm) acquireModel).j());
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private void e(Transformer transformer) {
        float[] fArr = {0.0f, this.s};
        float[] fArr2 = {0.0f, this.f};
        transformer.pointValuesToPixel(fArr);
        transformer.pointValuesToPixel(fArr2);
        this.n = fArr2[1];
        this.i = fArr[1];
    }

    private void ago_(Canvas canvas, RectF rectF, int i, edj edjVar) {
        float e = edjVar.e();
        float b = edjVar.b();
        if ((e >= 1.0E-7f || b >= 1.0E-7f) && !d(e)) {
            float abs = Math.abs(rectF.right - rectF.left);
            float f = rectF.top;
            float f2 = rectF.bottom;
            agt_(rectF, i);
            if (f2 - f >= abs) {
                agq_(rectF, canvas, edjVar, i);
                return;
            }
            float f3 = (f2 + f) / 2.0f;
            float f4 = abs / 2.0f;
            RectF rectF2 = new RectF(rectF.left, Math.abs(f3 - f4), rectF.right, f3 + f4);
            agr_(rectF2, canvas, edjVar, i);
            agp_(rectF2, canvas, edjVar, i);
        }
    }

    private void agq_(RectF rectF, Canvas canvas, edj edjVar, int i) {
        float b = edjVar.b();
        float e = edjVar.e();
        canvas.save();
        Path path = new Path();
        float abs = Math.abs(rectF.right - rectF.left) / 2.0f;
        path.addRoundRect(rectF, abs, abs, Path.Direction.CW);
        canvas.clipPath(path);
        boolean z = false;
        boolean z2 = b >= this.s;
        boolean z3 = Math.max(this.f, e) <= Math.min(this.s, b);
        boolean z4 = e < this.f;
        if (rectF.top <= this.i && rectF.bottom >= this.i) {
            RectF rectF2 = new RectF(rectF.left, rectF.top, rectF.right, rectF.bottom);
            rectF2.bottom = this.i;
            rectF.top = this.i;
            a(b, i);
            canvas.drawRect(rectF2, this.m);
            z2 = false;
        }
        if (rectF.bottom <= this.n || rectF.top >= this.n) {
            z = z4;
        } else {
            RectF rectF3 = new RectF(rectF.left, rectF.top, rectF.right, rectF.bottom);
            rectF3.top = this.n;
            rectF.bottom = this.n;
            a(e, i);
            canvas.drawRect(rectF3, this.m);
        }
        if (z2) {
            a(b, i);
            canvas.drawRect(rectF, this.m);
        }
        if (z) {
            a(e, i);
            canvas.drawRect(rectF, this.m);
        }
        if (z3) {
            a(this.l, i);
            canvas.drawRect(rectF, this.m);
        }
        canvas.restore();
    }

    private void agr_(RectF rectF, Canvas canvas, edj edjVar, int i) {
        a(edjVar.b(), i);
        canvas.drawArc(rectF, 0.0f, -180.0f, false, this.m);
    }

    private void agp_(RectF rectF, Canvas canvas, edj edjVar, int i) {
        a(edjVar.e(), i);
        canvas.drawArc(rectF, 0.0f, 180.0f, false, this.m);
    }

    private void a(float f, int i) {
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.h)) {
            this.m.setColor(Color.argb(255, 86, 187, 191));
            a(i);
            return;
        }
        float f2 = this.f;
        if (f >= f2 && f < this.s) {
            this.m.setColor(Color.argb(255, 255, 117, 0));
        } else if (f < f2) {
            this.m.setColor(Color.argb(255, 76, 186, 191));
        } else {
            this.m.setColor(Color.argb(255, 217, 65, 66));
        }
        a(i);
    }

    private void a(int i) {
        if (i == 1) {
            this.m.setAlpha(255);
        } else {
            this.m.setAlpha(153);
        }
    }

    private void agt_(RectF rectF, int i) {
        if (i == 1 || !LanguageUtil.bc(BaseApplication.getContext())) {
            return;
        }
        float f = rectF.left;
        float f2 = rectF.right;
        rectF.right = f;
        rectF.left = f2;
    }

    @Override // defpackage.nnb
    public void b(float f, float f2, float f3, float f4, Transformer transformer) {
        this.d.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.d, this.mAnimator.getPhaseY());
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        if (canvas != null && isDrawingValuesAllowed(this.e)) {
            if (this.e.getBarData() == null) {
                LogUtil.h("TemperatureBarChartRender", "drawValues mChart.getBarData() is null");
                return;
            }
            List<T> dataSets = this.e.getBarData().getDataSets();
            for (int i = 0; i < this.e.getBarData().getDataSetCount(); i++) {
                IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) dataSets.get(i);
                if (shouldDrawValues(iHwHealthBarDataSet)) {
                    applyValueTextStyle(iHwHealthBarDataSet);
                    BarBuffer barBuffer = this.f15395a[i];
                    MPPointF mPPointF = MPPointF.getInstance(iHwHealthBarDataSet.getIconsOffset());
                    mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                    mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                    if (!iHwHealthBarDataSet.isStacked()) {
                        agu_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                    } else {
                        ags_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                    }
                    MPPointF.recycleInstance(mPPointF);
                }
            }
        }
    }

    private void ags_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        if (barBuffer == null || barBuffer.buffer == null) {
            LogUtil.h("TemperatureBarChartRender", "setIsStacked buffer or buffer.buffer is null");
            return;
        }
        int i = 0;
        int i2 = 0;
        while (i < iHwHealthBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i);
            int i3 = i2 + 2;
            if (barBuffer.buffer.length <= i3) {
                LogUtil.h("TemperatureBarChartRender", "setIsStacked valid parameter");
                return;
            }
            float f = (barBuffer.buffer[i2] + barBuffer.buffer[i3]) / 2.0f;
            int valueTextColor = iHwHealthBarDataSet.getValueTextColor(i);
            if (!this.mViewPortHandler.isInBoundsRight(f)) {
                return;
            }
            int i4 = i2 + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i4]) && this.mViewPortHandler.isInBoundsLeft(f)) {
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    cBv_(canvas, iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i, this.mViewPortHandler), f, barBuffer.buffer[i4], valueTextColor);
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    Utils.drawImage(canvas, icon, (int) (f + mPPointF.x), (int) (barBuffer.buffer[i4] + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
                i2 += 4;
                i++;
            }
        }
    }

    private void agu_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        for (int i = 0; i < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i += 4) {
            float f = (barBuffer.buffer[i] + barBuffer.buffer[i + 2]) / 2.0f;
            if (!this.mViewPortHandler.isInBoundsRight(f)) {
                return;
            }
            int i2 = i + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i2]) && this.mViewPortHandler.isInBoundsLeft(f)) {
                int i3 = i / 4;
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i3);
                float y = hwHealthBarEntry.getY();
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    cBv_(canvas, iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i3, this.mViewPortHandler), f, y >= 0.0f ? barBuffer.buffer[i2] : barBuffer.buffer[i + 3], iHwHealthBarDataSet.getValueTextColor(i3));
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    Utils.drawImage(canvas, icon, (int) (f + mPPointF.x), (int) ((y >= 0.0f ? barBuffer.buffer[i2] : barBuffer.buffer[i + 3]) + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
            }
        }
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        if (this.g) {
            return;
        }
        nmz barData = this.e.getBarData();
        if (canvas == null || highlightArr == null || barData == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iHwHealthBarDataSet != null && iHwHealthBarDataSet.isHighlightEnabled()) {
                this.mHighlightPaint.setColor(e(iHwHealthBarDataSet));
                List<T> entriesForXValue = iHwHealthBarDataSet.getEntriesForXValue(highlight.getX());
                if ((entriesForXValue == 0 || entriesForXValue.size() == 0) && this.o != null) {
                    this.o.onTemperatureTimeChangedListener(new edm(42.0f, new edj()));
                    return;
                }
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) entriesForXValue.get(0);
                if (isInBoundsX(hwHealthBarEntry, iHwHealthBarDataSet) && (hwHealthBarEntry.acquireModel() instanceof edm)) {
                    Transformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
                    e(transformer);
                    edm edmVar = (edm) hwHealthBarEntry.acquireModel();
                    float b = edmVar.b();
                    float e = edmVar.e();
                    edj j = edmVar.j();
                    OnTemperatureTimeChangedListener onTemperatureTimeChangedListener = this.o;
                    if (onTemperatureTimeChangedListener != null && j != null) {
                        onTemperatureTimeChangedListener.onTemperatureTimeChangedListener(edmVar);
                        b(hwHealthBarEntry.getX(), b, e, barData.d() / 2.0f, transformer);
                        dGK_(highlight, this.d);
                        ago_(canvas, this.d, 1, j);
                    }
                }
            }
        }
    }

    public void b(OnTemperatureTimeChangedListener onTemperatureTimeChangedListener) {
        this.o = onTemperatureTimeChangedListener;
    }

    @Override // defpackage.nnb
    public void dGK_(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }

    @Override // defpackage.nnb
    public HwHealthBarDataProvider b() {
        return this.e;
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        this.g = z;
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.g;
    }

    private int c(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.g) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.getColor();
    }

    private int e(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.g) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.acquireFocusColor();
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean hasData() {
        nmz barData = this.e.getBarData();
        return (barData == null || barData.getDataSets() == null || barData.getDataSets().size() == 0) ? false : true;
    }
}

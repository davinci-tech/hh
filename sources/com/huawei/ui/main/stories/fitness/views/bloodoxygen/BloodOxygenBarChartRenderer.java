package com.huawei.ui.main.stories.fitness.views.bloodoxygen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.main.stories.BarChartRender;
import defpackage.nmz;
import defpackage.not;
import defpackage.pjm;
import defpackage.pyg;
import defpackage.pyl;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class BloodOxygenBarChartRenderer extends BarChartRender implements IHwHealthDataRender {
    private BarChartRender.OnTimeChangedListener f;
    private OnMostValueChangedListener g;
    private boolean h;
    private Paint i;

    public interface OnMostValueChangedListener {
        void onMostValueChangedListener(float f, float f2);
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    public BloodOxygenBarChartRenderer(HwHealthBarChart hwHealthBarChart, HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
        this.h = false;
        this.i = new Paint();
        LogUtil.b("BloodOxygenBarChartRenderer", "BloodOxygenBarChartRenderer");
        e(hwHealthBarChart, hwHealthBarDataProvider);
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
        this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt()).pointValuesToPixel(notVar.buffer);
        boolean z = iHwHealthBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(e(iHwHealthBarDataSet));
        }
        ArrayList arrayList = new ArrayList(16);
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
                    pyl c = ((pyg) hwHealthBarEntry.acquireModel()).c();
                    arrayList.add(Float.valueOf(c.b()));
                    arrayList.add(Float.valueOf(c.d()));
                    dvD_(canvas, this.c, 2, c);
                } else {
                    continue;
                }
            }
        }
        b(arrayList);
    }

    private void b(List<Float> list) {
        OnMostValueChangedListener onMostValueChangedListener;
        if (list != null && list.size() > 0) {
            Collections.sort(list);
            if (list.get(0) != null && list.get(list.size() - 1) != null && (onMostValueChangedListener = this.g) != null) {
                onMostValueChangedListener.onMostValueChangedListener(list.get(0).floatValue(), list.get(list.size() - 1).floatValue());
                return;
            }
        }
        OnMostValueChangedListener onMostValueChangedListener2 = this.g;
        if (onMostValueChangedListener2 != null) {
            onMostValueChangedListener2.onMostValueChangedListener(0.0f, 0.0f);
        }
    }

    private void dvD_(Canvas canvas, RectF rectF, int i, pyl pylVar) {
        float abs;
        float f;
        float f2;
        float f3;
        float f4;
        b(i);
        float d = pylVar.d();
        float b = pylVar.b();
        if ((d >= 1.0E-7f || b >= 1.0E-7f) && !c(pjm.c(d))) {
            float abs2 = Math.abs(rectF.right - rectF.left);
            float abs3 = Math.abs(rectF.bottom - rectF.top);
            float f5 = (100.0f - b) / 40.0f;
            float f6 = rectF.top;
            float f7 = rectF.bottom;
            if (f7 - f6 >= abs2) {
                f = Math.abs(f6 + abs2);
                float f8 = abs2 / 2.0f;
                f2 = Math.abs(f6 + f8);
                float abs4 = Math.abs(f7 - f8);
                abs = Math.abs(f7 - abs2);
                f4 = abs4;
                f3 = f7;
            } else {
                if (f5 * abs3 <= abs2 / 2.0f) {
                    float f9 = abs2 + f6;
                    f = Math.abs(f9);
                    f7 = Math.abs(f9);
                    abs = f6;
                } else {
                    float f10 = f7 - abs2;
                    f6 = Math.abs(f10);
                    abs = Math.abs(f10);
                    f = f7;
                }
                f2 = 0.0f;
                f3 = f7;
                f4 = 0.0f;
            }
            dvF_(rectF, i);
            canvas.drawArc(new RectF(rectF.left, f6, rectF.right, f), 0.0f, -180.0f, false, this.i);
            canvas.drawRect(Math.abs(rectF.left), f2, Math.abs(rectF.right), f4, this.i);
            canvas.drawArc(new RectF(rectF.left, abs, rectF.right, f3), 0.0f, 180.0f, false, this.i);
        }
    }

    private void b(int i) {
        if (i == 1) {
            this.i.setColor(Color.argb(255, 255, 64, 101));
        } else {
            this.i.setColor(Color.argb(255, 253, 152, 172));
        }
    }

    private void dvF_(RectF rectF, int i) {
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
        if (canvas == null || !isDrawingValuesAllowed(this.e)) {
            LogUtil.h("BloodOxygenBarChartRenderer", "drawValues canvas is null");
            return;
        }
        if (this.e.getBarData() == null) {
            LogUtil.h("BloodOxygenBarChartRenderer", "drawValues mChart.getBarData() is null");
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
                    dvG_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                } else {
                    dvE_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                }
                MPPointF.recycleInstance(mPPointF);
            }
        }
    }

    private void dvE_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        if (barBuffer == null || barBuffer.buffer == null) {
            LogUtil.h("BloodOxygenBarChartRenderer", "setIsStacked buffer or buffer.buffer is null");
            return;
        }
        int i = 0;
        int i2 = 0;
        while (i < iHwHealthBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i);
            int i3 = i2 + 2;
            if (barBuffer.buffer.length <= i3) {
                LogUtil.h("BloodOxygenBarChartRenderer", "setIsStacked valid parameter");
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

    private void dvG_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        float f;
        for (int i = 0; i < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i += 4) {
            float f2 = (barBuffer.buffer[i] + barBuffer.buffer[i + 2]) / 2.0f;
            if (!this.mViewPortHandler.isInBoundsRight(f2)) {
                return;
            }
            int i2 = i + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i2]) && this.mViewPortHandler.isInBoundsLeft(f2)) {
                int i3 = i / 4;
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i3);
                float y = hwHealthBarEntry.getY();
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    cBv_(canvas, iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i3, this.mViewPortHandler), f2, y >= 0.0f ? barBuffer.buffer[i2] : barBuffer.buffer[i + 3], iHwHealthBarDataSet.getValueTextColor(i3));
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    if (y >= 0.0f) {
                        f = barBuffer.buffer[i2];
                    } else {
                        f = barBuffer.buffer[i + 3];
                    }
                    Utils.drawImage(canvas, icon, (int) (f2 + mPPointF.x), (int) (f + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
            }
        }
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        if (this.h) {
            return;
        }
        nmz barData = this.e.getBarData();
        if (canvas == null || highlightArr == null || barData == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iHwHealthBarDataSet != null && iHwHealthBarDataSet.isHighlightEnabled()) {
                this.mHighlightPaint.setColor(a(iHwHealthBarDataSet));
                List<T> entriesForXValue = iHwHealthBarDataSet.getEntriesForXValue(highlight.getX());
                if (entriesForXValue == 0 || entriesForXValue.size() == 0) {
                    BarChartRender.OnTimeChangedListener onTimeChangedListener = this.f;
                    if (onTimeChangedListener != null) {
                        onTimeChangedListener.onTimeChangedListener(0.0f, 0.0f);
                        return;
                    }
                    return;
                }
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) entriesForXValue.get(0);
                if (isInBoundsX(hwHealthBarEntry, iHwHealthBarDataSet)) {
                    pyg pygVar = (pyg) hwHealthBarEntry.acquireModel();
                    float b = pygVar.b();
                    float e = pygVar.e();
                    pyl c = pygVar.c();
                    Transformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
                    BarChartRender.OnTimeChangedListener onTimeChangedListener2 = this.f;
                    if (onTimeChangedListener2 != null && c != null) {
                        onTimeChangedListener2.onTimeChangedListener(c.d(), c.b());
                        b(hwHealthBarEntry.getX(), b, e, barData.d() / 2.0f, transformer);
                        dGK_(highlight, this.d);
                        dvD_(canvas, this.d, 1, c);
                    }
                }
            }
        }
    }

    public void d(BarChartRender.OnTimeChangedListener onTimeChangedListener) {
        this.f = onTimeChangedListener;
    }

    public void d(OnMostValueChangedListener onMostValueChangedListener) {
        this.g = onMostValueChangedListener;
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
        this.h = z;
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.h;
    }

    private int e(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.h) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.getColor();
    }

    private int a(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.h) {
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

package com.huawei.health.knit.section.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.BarChartRender;
import com.huawei.health.servicesui.R$string;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;
import com.huawei.hwcommonmodel.accessibility.ChartTouchHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import defpackage.ech;
import defpackage.eck;
import defpackage.nmz;
import defpackage.not;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class BloodOxygenBarChartRenderer extends BarChartRender implements IHwHealthDataRender {
    private boolean f;
    private OnMostValueChangedListener g;
    private int h;
    private BarChartRender.OnTimeChangedListener i;
    private int k;
    private int l;
    private int m;
    private int n;
    private Paint o;
    private int q;

    public interface OnMostValueChangedListener {
        void onMostValueChangedListener(float f, float f2);
    }

    private int b(float f, float f2, float f3, float f4) {
        if (f4 >= f && f4 <= f2) {
            return 1;
        }
        if (f4 < f2 || f4 > f3) {
            return f4 >= f3 ? 3 : 0;
        }
        return 2;
    }

    private int c(float f, float f2, float f3, float f4) {
        if (f4 >= f && f4 <= f2) {
            return 1;
        }
        if (f4 < f2 || f4 > f3) {
            return f4 >= f3 ? 3 : 0;
        }
        return 2;
    }

    public static int e(float f) {
        if (f <= 100.0f && f < 90.0f) {
            return f >= 80.0f ? 70 : 60;
        }
        return 85;
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    public BloodOxygenBarChartRenderer(HwHealthBarChart hwHealthBarChart, HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
        this.f = false;
        this.o = new Paint();
        this.l = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296511_res_0x7f0900ff);
        this.m = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296509_res_0x7f0900fd);
        this.h = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296505_res_0x7f0900f9);
        this.q = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296512_res_0x7f090100);
        this.n = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296510_res_0x7f0900fe);
        this.k = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296506_res_0x7f0900fa);
        LogUtil.b("BloodOxygenBarChartRenderer", "BloodOxygenBarChartRenderer");
        c(hwHealthBarChart, hwHealthBarDataProvider);
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
        float[] fArr = {0.0f, 70.0f, 0.0f, 90.0f, 0.0f, 100.0f};
        transformer.pointValuesToPixel(fArr);
        transformer.pointValuesToPixel(notVar.buffer);
        boolean z = iHwHealthBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(b(iHwHealthBarDataSet));
        }
        ArrayList arrayList = new ArrayList(16);
        ChartTouchHelper accessibilityHelper = ((HwHealthBaseBarLineChart) this.e).getAccessibilityHelper();
        if (accessibilityHelper != null) {
            accessibilityHelper.e().d();
        }
        for (int i2 = 0; i2 < notVar.size(); i2 += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(notVar.buffer[i2])) {
                int i3 = i2 + 2;
                if (this.mViewPortHandler.isInBoundsRight(notVar.buffer[i3])) {
                    if (!z) {
                        this.mRenderPaint.setColor(iHwHealthBarDataSet.getColor(i2 / 4));
                    }
                    this.c.set(notVar.buffer[i2], notVar.buffer[i2 + 1], notVar.buffer[i3], notVar.buffer[i2 + 3]);
                    int i4 = i2 / 4;
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i4);
                    if (hwHealthBarEntry == null) {
                        return;
                    }
                    eck a2 = ((ech) hwHealthBarEntry.acquireModel()).a();
                    arrayList.add(Float.valueOf(a2.c()));
                    arrayList.add(Float.valueOf(a2.a()));
                    aeH_(canvas, this.c, 2, a2, fArr);
                    if (accessibilityHelper != null) {
                        AbstractTouchNode a3 = accessibilityHelper.e().a(i4);
                        Rect rect = new Rect();
                        this.c.round(rect);
                        a3.setRect(rect);
                        a3.setDescription(e(hwHealthBarEntry));
                    }
                } else {
                    continue;
                }
            }
        }
        e(arrayList);
    }

    private String e(HwHealthBarEntry hwHealthBarEntry) {
        String formattedValue;
        String str;
        if (hwHealthBarEntry == null) {
            LogUtil.h("BloodOxygenBarChartRenderer", "entry is null");
            return "";
        }
        IAxisValueFormatter valueFormatter = ((HwHealthBaseScrollBarLineChart) this.e).getXAxis().getValueFormatter();
        if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) {
            formattedValue = ((HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) valueFormatter).getFormattedValueForMarkerView(hwHealthBarEntry.getX(), ((HwHealthBaseScrollBarLineChart) this.e).getXAxis());
        } else {
            formattedValue = valueFormatter.getFormattedValue(hwHealthBarEntry.getX(), ((HwHealthBaseScrollBarLineChart) this.e).getXAxis());
        }
        eck a2 = ((ech) hwHealthBarEntry.acquireModel()).a();
        this.i.onTimeChangedListener(a2.a(), a2.c());
        double a3 = a2.a();
        double c = a2.c();
        if (((int) a3) == 0 || ((int) c) == 0) {
            str = "--";
        } else {
            str = nsf.b(R$string.accessibility_range, UnitUtil.e(a3, 2, 0), UnitUtil.e(c, 2, 0));
        }
        return nsf.b(com.huawei.ui.commonui.R$string.IDS_two_parts, formattedValue, str);
    }

    private void e(List<Float> list) {
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

    private void aeH_(Canvas canvas, RectF rectF, int i, eck eckVar, float[] fArr) {
        float abs;
        float abs2;
        float f;
        float f2;
        float f3;
        float f4;
        a(i);
        float f5 = fArr[5];
        float f6 = fArr[3];
        float f7 = fArr[1];
        float a2 = eckVar.a();
        float c = eckVar.c();
        if ((a2 >= 1.0E-7f || c >= 1.0E-7f) && !d(e(a2))) {
            float abs3 = Math.abs(rectF.right - rectF.left);
            Math.abs(rectF.bottom - rectF.top);
            float f8 = rectF.top;
            float f9 = rectF.bottom;
            int c2 = c(f5, f6, f7, f8);
            int b = b(f5, f6, f7, f9);
            if (f9 > f8) {
                float f10 = abs3 / 2.0f;
                abs = Math.abs(f8 - f10);
                float abs4 = Math.abs(f8 + f10);
                float abs5 = Math.abs(f9 - f10);
                abs2 = Math.abs(f10 + f9);
                f2 = f8;
                f3 = f9;
                f4 = abs5;
                f = abs4;
            } else {
                float f11 = abs3 / 2.0f;
                abs = Math.abs(f8 - f11);
                float abs6 = Math.abs(f8 + f11);
                float abs7 = Math.abs(f9 - f11);
                abs2 = Math.abs(f9 + f11);
                f = abs6;
                f2 = 0.0f;
                f3 = 0.0f;
                f4 = abs7;
            }
            aeS_(rectF, i);
            aeQ_(canvas, rectF, i, f6, f7, c2, b, abs, f, f2, f3, f4, abs2);
        }
    }

    private void aeQ_(Canvas canvas, RectF rectF, int i, float f, float f2, int i2, int i3, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i2 == 1 && i3 == 1) {
            aeJ_(canvas, rectF, i, f3, f4, f5, f6, f7, f8);
            return;
        }
        if (i2 == 1 && i3 == 2) {
            aeL_(canvas, rectF, i, f, f3, f4, f5, f6, f7, f8);
            return;
        }
        if (i2 == 1 && i3 == 3) {
            aeO_(canvas, rectF, i, f, f2, f3, f4, f5, f6, f7, f8);
            return;
        }
        if (i2 == 2 && i3 == 2) {
            aeK_(canvas, rectF, i, f3, f4, f5, f6, f7, f8);
            return;
        }
        if (i2 == 2 && i3 == 3) {
            aeI_(canvas, rectF, i, f2, f3, f4, f5, f6, f7, f8);
        } else if (i2 == 3 && i3 == 3) {
            aeN_(canvas, rectF, i, f3, f4, f5, f6, f7, f8);
        } else {
            aeM_(canvas, rectF, i, f3, f4, f5, f6, f7, f8);
        }
    }

    private void aeM_(Canvas canvas, RectF rectF, int i, float f, float f2, float f3, float f4, float f5, float f6) {
        if (i == 2) {
            this.o.setColor(this.l);
        } else {
            this.o.setColor(this.q);
        }
        aeP_(canvas, rectF, f, f2, f3, f4, f5, f6);
    }

    private void aeN_(Canvas canvas, RectF rectF, int i, float f, float f2, float f3, float f4, float f5, float f6) {
        if (i == 2) {
            this.o.setColor(this.l);
        } else {
            this.o.setColor(this.q);
        }
        aeP_(canvas, rectF, f, f2, f3, f4, f5, f6);
    }

    private void aeI_(Canvas canvas, RectF rectF, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (i == 2) {
            this.o.setColor(this.m);
        } else {
            this.o.setColor(this.n);
        }
        canvas.drawRect(Math.abs(rectF.left), f4, Math.abs(rectF.right), f, this.o);
        canvas.drawArc(new RectF(rectF.left, f2, rectF.right, f3), 0.0f, -180.0f, false, this.o);
        if (i == 2) {
            this.o.setColor(this.l);
        } else {
            this.o.setColor(this.q);
        }
        canvas.drawRect(Math.abs(rectF.left), f, Math.abs(rectF.right), f5, this.o);
        canvas.drawArc(new RectF(rectF.left, f6, rectF.right, f7), 0.0f, 180.0f, false, this.o);
    }

    private void aeK_(Canvas canvas, RectF rectF, int i, float f, float f2, float f3, float f4, float f5, float f6) {
        if (i == 2) {
            this.o.setColor(this.m);
        } else {
            this.o.setColor(this.n);
        }
        aeP_(canvas, rectF, f, f2, f3, f4, f5, f6);
    }

    private void aeO_(Canvas canvas, RectF rectF, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i == 2) {
            this.o.setColor(this.h);
        } else {
            this.o.setColor(this.k);
        }
        canvas.drawRect(Math.abs(rectF.left), f5, Math.abs(rectF.right), f, this.o);
        canvas.drawArc(new RectF(rectF.left, f3, rectF.right, f4), 0.0f, -180.0f, false, this.o);
        if (i == 2) {
            this.o.setColor(this.m);
        } else {
            this.o.setColor(this.n);
        }
        canvas.drawRect(Math.abs(rectF.left), f, Math.abs(rectF.right), f2, this.o);
        if (i == 2) {
            this.o.setColor(this.l);
        } else {
            this.o.setColor(this.q);
        }
        canvas.drawRect(Math.abs(rectF.left), f2, Math.abs(rectF.right), f6, this.o);
        canvas.drawArc(new RectF(rectF.left, f7, rectF.right, f8), 0.0f, 180.0f, false, this.o);
    }

    private void aeL_(Canvas canvas, RectF rectF, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (i == 2) {
            this.o.setColor(this.h);
        } else {
            this.o.setColor(this.k);
        }
        canvas.drawRect(Math.abs(rectF.left), f4, Math.abs(rectF.right), f, this.o);
        canvas.drawArc(new RectF(rectF.left, f2, rectF.right, f3), 0.0f, -180.0f, false, this.o);
        if (i == 2) {
            this.o.setColor(this.m);
        } else {
            this.o.setColor(this.n);
        }
        canvas.drawRect(Math.abs(rectF.left), f, Math.abs(rectF.right), f5, this.o);
        canvas.drawArc(new RectF(rectF.left, f6, rectF.right, f7), 0.0f, 180.0f, false, this.o);
    }

    private void aeJ_(Canvas canvas, RectF rectF, int i, float f, float f2, float f3, float f4, float f5, float f6) {
        if (i == 2) {
            this.o.setColor(this.h);
        } else {
            this.o.setColor(this.k);
        }
        aeP_(canvas, rectF, f, f2, f3, f4, f5, f6);
    }

    private void aeP_(Canvas canvas, RectF rectF, float f, float f2, float f3, float f4, float f5, float f6) {
        canvas.drawArc(new RectF(rectF.left, f, rectF.right, f2), 0.0f, -180.0f, false, this.o);
        canvas.drawRect(Math.abs(rectF.left), f3, Math.abs(rectF.right), f4, this.o);
        canvas.drawArc(new RectF(rectF.left, f5, rectF.right, f6), 0.0f, 180.0f, false, this.o);
    }

    private void a(int i) {
        if (i == 1) {
            this.o.setColor(Color.argb(255, 255, 64, 101));
        } else {
            this.o.setColor(Color.argb(255, 253, 152, 172));
        }
    }

    private void aeS_(RectF rectF, int i) {
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
                    aeT_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                } else {
                    aeR_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                }
                MPPointF.recycleInstance(mPPointF);
            }
        }
    }

    private void aeR_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
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

    private void aeT_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        float f;
        float f2;
        for (int i = 0; i < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i += 4) {
            float f3 = (barBuffer.buffer[i] + barBuffer.buffer[i + 2]) / 2.0f;
            if (!this.mViewPortHandler.isInBoundsRight(f3)) {
                return;
            }
            int i2 = i + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i2]) && this.mViewPortHandler.isInBoundsLeft(f3)) {
                int i3 = i / 4;
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i3);
                float y = hwHealthBarEntry.getY();
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    String formattedValue = iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i3, this.mViewPortHandler);
                    if (y >= 0.0f) {
                        f2 = barBuffer.buffer[i2];
                    } else {
                        f2 = barBuffer.buffer[i + 3];
                    }
                    cBv_(canvas, formattedValue, f3, f2, iHwHealthBarDataSet.getValueTextColor(i3));
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    if (y >= 0.0f) {
                        f = barBuffer.buffer[i2];
                    } else {
                        f = barBuffer.buffer[i + 3];
                    }
                    Utils.drawImage(canvas, icon, (int) (f3 + mPPointF.x), (int) (f + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
            }
        }
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        if (this.f) {
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
                if (entriesForXValue == 0 || entriesForXValue.size() == 0) {
                    BarChartRender.OnTimeChangedListener onTimeChangedListener = this.i;
                    if (onTimeChangedListener != null) {
                        onTimeChangedListener.onTimeChangedListener(0.0f, 0.0f);
                        return;
                    }
                    return;
                }
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) entriesForXValue.get(0);
                if (isInBoundsX(hwHealthBarEntry, iHwHealthBarDataSet)) {
                    ech echVar = (ech) hwHealthBarEntry.acquireModel();
                    float b = echVar.b();
                    float e = echVar.e();
                    eck a2 = echVar.a();
                    Transformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
                    BarChartRender.OnTimeChangedListener onTimeChangedListener2 = this.i;
                    if (onTimeChangedListener2 != null && a2 != null) {
                        onTimeChangedListener2.onTimeChangedListener(a2.a(), a2.c());
                        b(hwHealthBarEntry.getX(), b, e, barData.d() / 2.0f, transformer);
                        dGK_(highlight, this.d);
                        float[] fArr = {0.0f, 70.0f, 0.0f, 90.0f, 0.0f, 100.0f};
                        transformer.pointValuesToPixel(fArr);
                        aeH_(canvas, this.d, 1, a2, fArr);
                    }
                }
            }
        }
    }

    public void b(BarChartRender.OnTimeChangedListener onTimeChangedListener) {
        this.i = onTimeChangedListener;
    }

    public void e(OnMostValueChangedListener onMostValueChangedListener) {
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
        this.f = z;
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.f;
    }

    private int b(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.f) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.getColor();
    }

    private int e(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.f) {
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

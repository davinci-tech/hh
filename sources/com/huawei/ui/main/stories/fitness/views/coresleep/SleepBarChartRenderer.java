package com.huawei.ui.main.stories.fitness.views.coresleep;

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
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.main.stories.BarChartRender;
import defpackage.nmz;
import defpackage.not;
import defpackage.nrn;
import defpackage.qah;
import defpackage.qai;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class SleepBarChartRenderer extends BarChartRender implements IHwHealthDataRender {
    private Paint aa;
    private Paint ab;
    private float ac;
    private float ad;
    private float ae;
    private float f;
    private HwHealthBarChart g;
    private float h;
    private int i;
    private float k;
    private boolean l;
    private boolean m;
    private float n;
    private boolean o;
    private float p;
    private float q;
    private float r;
    private String s;
    private float t;
    private Paint u;
    private Paint v;
    private Paint w;
    private float x;
    private OnSleepTimeChangedListener y;
    private float z;

    public interface OnSleepTimeChangedListener {
        void onSleepTimeChangedListener(float f);
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    public SleepBarChartRenderer(HwHealthBarChart hwHealthBarChart, HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, String... strArr) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
        this.l = true;
        this.m = true;
        this.o = false;
        this.i = 0;
        this.s = "";
        this.u = new Paint();
        this.w = new Paint();
        this.v = new Paint();
        this.aa = new Paint();
        this.ab = new Paint();
        e(hwHealthBarChart, hwHealthBarDataProvider);
        this.g = hwHealthBarChart;
        if (strArr.length > 0) {
            this.s = strArr[1];
        }
    }

    @Override // defpackage.nnb
    public void dGJ_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, int i) {
        IHwHealthBarDataSet iHwHealthBarDataSet2;
        nmz barData = this.e.getBarData();
        if (canvas == null || iHwHealthBarDataSet == null || barData == null || (iHwHealthBarDataSet2 = (IHwHealthBarDataSet) barData.getDataSetByIndex(i)) == null || !iHwHealthBarDataSet2.isHighlightEnabled() || i < 0) {
            return;
        }
        this.b.setColor(iHwHealthBarDataSet.getBarBorderColor());
        this.b.setStrokeWidth(Utils.convertDpToPixel(iHwHealthBarDataSet.getBarBorderWidth()));
        if (this.f15395a == null || this.f15395a.length <= i) {
            LogUtil.h("SleepBarChartRenderer", "drawDataSet invalid parameter");
            return;
        }
        not notVar = this.f15395a[i];
        notVar.setPhases(this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY());
        notVar.setDataSet(i);
        notVar.setInverted(false);
        notVar.setBarWidth(this.e.getBarData().d());
        notVar.b(iHwHealthBarDataSet);
        HwHealthTransformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
        transformer.pointValuesToPixel(notVar.buffer);
        boolean z = iHwHealthBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(a(iHwHealthBarDataSet));
        }
        dwZ_(canvas, iHwHealthBarDataSet, notVar, z, transformer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void dwZ_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, not notVar, boolean z, Transformer transformer) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        for (int i = 0; i < notVar.size(); i += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(notVar.buffer[i])) {
                int i2 = i + 2;
                if (this.mViewPortHandler.isInBoundsRight(notVar.buffer[i2])) {
                    if (!z) {
                        this.mRenderPaint.setColor(iHwHealthBarDataSet.getColor(i / 4));
                    }
                    this.c.set(notVar.buffer[i], notVar.buffer[i + 1], notVar.buffer[i2], notVar.buffer[i + 3]);
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i / 4);
                    if (hwHealthBarEntry == null) {
                        return;
                    }
                    qah d = ((qai) hwHealthBarEntry.acquireModel()).d();
                    c(arrayList, arrayList2, arrayList3, d);
                    dwY_(canvas, this.c, transformer, 2, d);
                } else {
                    continue;
                }
            }
        }
        b(c(arrayList, arrayList2, arrayList3));
    }

    private void b(int i) {
        if (this.i != i) {
            this.i = i;
            String format = String.format(this.s, UnitUtil.e(i / 60, 1, 0), UnitUtil.e(this.i % 60, 1, 0));
            if (i > 0) {
                ((CoreSleepBarChartView) this.g).d(i, nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color), format);
                this.g.reCalculateDynamicBoardForManualRefLine();
                this.g.refresh();
            } else {
                ((CoreSleepBarChartView) this.g).d(i, nrn.d(BaseApplication.getContext(), R$color.colorBackground), "");
                this.g.reCalculateDynamicBoardForManualRefLine();
                this.g.refresh();
            }
        }
    }

    private void c(List<Float> list, List<Float> list2, List<Float> list3, qah qahVar) {
        if (qahVar == null || qahVar.b() == 30) {
            return;
        }
        if (qahVar.e() > 0.0f) {
            list.add(Float.valueOf(qahVar.e()));
        }
        if (qahVar.d() > 0.0f) {
            list2.add(Float.valueOf(qahVar.d()));
        }
        if (qahVar.c() > 0.0f) {
            list3.add(Float.valueOf(qahVar.c()));
        }
    }

    private int c(List<Float> list, List<Float> list2, List<Float> list3) {
        return e(list) + e(list2) + e(list3);
    }

    private int e(List<Float> list) {
        Iterator<Float> it = list.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            f += it.next().floatValue();
        }
        return list.size() > 0 ? Math.round(f / list.size()) : (int) f;
    }

    private void dwY_(Canvas canvas, RectF rectF, Transformer transformer, int i, qah qahVar) {
        float f;
        float[] fArr = {0.0f, 0.0f};
        transformer.pointValuesToPixel(fArr);
        float f2 = fArr[1];
        float abs = Math.abs(rectF.top - rectF.bottom);
        float abs2 = Math.abs(rectF.right - rectF.left);
        if (abs >= 1.0E-7f || rectF.top != f2) {
            float f3 = this.l ? (abs2 / 2.0f) + 0.0f : 0.0f;
            if (this.m) {
                f3 += abs2 / 2.0f;
            }
            if (abs < f3) {
                float f4 = (rectF.top + rectF.bottom) / 2.0f;
                float f5 = f3 / 2.0f;
                rectF.top = f4 - f5;
                rectF.bottom = f4 + f5;
                if (rectF.bottom > f2) {
                    float f6 = rectF.bottom - f2;
                    rectF.bottom -= f6;
                    rectF.top -= f6;
                }
            }
            float f7 = rectF.bottom - rectF.top;
            e(i);
            if (f7 <= 0.0f || !this.l) {
                f = 0.0f;
            } else {
                float f8 = abs2 / 2.0f;
                f7 -= f8;
                f = f8 + 0.0f;
            }
            if (f7 > 0.0f) {
                d(qahVar);
                dxc_(rectF, f, 0.0f, (rectF.bottom - 0.0f) - (rectF.top + f));
                if (i != 1 && LanguageUtil.bc(BaseApplication.getContext())) {
                    float f9 = rectF.left;
                    float f10 = rectF.right;
                    rectF.right = f9;
                    rectF.left = f10;
                }
                dwX_(canvas, rectF, abs2, f);
            }
        }
    }

    private void dwX_(Canvas canvas, RectF rectF, float f, float f2) {
        boolean z;
        if (this.x > 0.0f) {
            float f3 = f / 2.0f;
            canvas.drawArc(new RectF(rectF.left, (rectF.top + f2) - f3, rectF.right, rectF.top + f2 + f3), 0.0f, -180.0f, false, this.u);
            canvas.drawRect(rectF.left, rectF.top + f2, rectF.right, this.r, this.u);
        }
        boolean z2 = true;
        if (this.ae > 0.0f) {
            float f4 = f / 2.0f;
            canvas.drawArc(new RectF(rectF.left, this.r - f4, rectF.right, this.r + f4), 0.0f, -180.0f, false, this.w);
            canvas.drawRect(rectF.left, this.r, rectF.right, this.ac, this.w);
            z = true;
        } else {
            z = false;
        }
        if (this.n > 0.0f) {
            if (!z) {
                float f5 = f / 2.0f;
                canvas.drawArc(new RectF(rectF.left, this.ac - f5, rectF.right, this.ac + f5), 0.0f, -180.0f, false, this.v);
                z = true;
            }
            canvas.drawRect(rectF.left, this.ac, rectF.right, this.k, this.v);
        }
        if (this.p > 0.0f) {
            if (z) {
                z2 = z;
            } else {
                float f6 = f / 2.0f;
                canvas.drawArc(new RectF(rectF.left, this.k - f6, rectF.right, this.k + f6), 0.0f, -180.0f, false, this.aa);
            }
            canvas.drawRect(rectF.left, this.k, rectF.right, this.t, this.aa);
            z = z2;
        }
        if (this.h > 0.0f) {
            if (!z) {
                float f7 = f / 2.0f;
                canvas.drawArc(new RectF(rectF.left, this.t - f7, rectF.right, this.t + f7), 0.0f, -180.0f, false, this.ab);
            }
            canvas.drawRect(rectF.left, this.t, rectF.right, this.f, this.ab);
        }
    }

    private void dxc_(RectF rectF, float f, float f2, float f3) {
        boolean z = false;
        boolean z2 = this.h <= 0.0f && this.p <= 0.0f;
        if (this.n <= 0.0f && this.ae <= 0.0f) {
            z = true;
        }
        if (z2 && z) {
            this.r = rectF.bottom - f2;
        } else {
            this.r = ((this.x / this.ad) * f3) + rectF.top + f;
        }
        if (this.h <= 0.0f && this.p <= 0.0f && this.n <= 0.0f) {
            this.ac = rectF.bottom - f2;
        } else {
            this.ac = ((this.ae / this.ad) * f3) + this.r;
        }
        if (this.h <= 0.0f && this.p <= 0.0f) {
            this.k = rectF.bottom - f2;
        } else {
            this.k = ((this.n / this.ad) * f3) + this.ac;
        }
        if (this.h <= 0.0f) {
            this.t = rectF.bottom - f2;
        } else {
            this.t = ((this.p / this.ad) * f3) + this.k;
        }
        this.f = rectF.bottom - f2;
    }

    private void d(qah qahVar) {
        this.x = qahVar.a();
        this.ae = qahVar.j();
        this.n = qahVar.c();
        this.p = qahVar.d();
        float e = qahVar.e();
        this.h = e;
        this.ad = e + this.p + this.n + this.ae + this.x;
    }

    private void e(int i) {
        if (i == 1) {
            this.u.setColor(Color.argb(255, 0, 143, 255));
            this.w.setColor(Color.argb(255, 255, 217, 69));
            this.v.setColor(Color.argb(255, 252, OldToNewMotionPath.SPORT_TYPE_TENNIS, OldToNewMotionPath.SPORT_TYPE_TENNIS));
            this.aa.setColor(Color.argb(255, 181, 109, 249));
            this.ab.setColor(Color.argb(255, OldToNewMotionPath.SPORT_TYPE_PILATES, 43, 226));
            return;
        }
        this.u.setColor(d(R$color.color_unselected_noon_sleep));
        this.w.setColor(d(R$color.color_unselected_awake));
        this.v.setColor(d(R$color.color_unselected_rem));
        this.aa.setColor(d(R$color.color_unselected_shallow));
        this.ab.setColor(d(R$color.color_unselected_deep));
    }

    private int d(int i) {
        HwHealthBarChart hwHealthBarChart = this.g;
        if (hwHealthBarChart instanceof CoreSleepBarChartView) {
            return ((CoreSleepBarChartView) hwHealthBarChart).getResources().getColor(i);
        }
        return -1;
    }

    @Override // defpackage.nnb
    public void b(float f, float f2, float f3, float f4, Transformer transformer) {
        this.d.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.d, this.mAnimator.getPhaseY());
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        if (canvas == null || !isDrawingValuesAllowed(this.e) || this.e.getBarData() == null) {
            return;
        }
        List<T> dataSets = this.e.getBarData().getDataSets();
        float convertDpToPixel = Utils.convertDpToPixel(4.5f);
        boolean isDrawValueAboveBarEnabled = this.e.isDrawValueAboveBarEnabled();
        for (int i = 0; i < this.e.getBarData().getDataSetCount(); i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) dataSets.get(i);
            if (shouldDrawValues(iHwHealthBarDataSet)) {
                applyValueTextStyle(iHwHealthBarDataSet);
                float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "8");
                this.z = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                this.q = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
                BarBuffer barBuffer = this.f15395a[i];
                MPPointF mPPointF = MPPointF.getInstance(iHwHealthBarDataSet.getIconsOffset());
                mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                if (!iHwHealthBarDataSet.isStacked()) {
                    dxb_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                } else {
                    dxa_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                }
                MPPointF.recycleInstance(mPPointF);
            }
        }
    }

    private void dxa_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        if (barBuffer == null || barBuffer.buffer == null) {
            LogUtil.h("SleepBarChartRenderer", "setIsStacked buffer or buffer.buffer is null");
            return;
        }
        int i = 0;
        int i2 = 0;
        while (i < iHwHealthBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i);
            int i3 = i2 + 2;
            if (barBuffer.buffer.length <= i3) {
                LogUtil.h("SleepBarChartRenderer", "setIsStacked invalid parameter");
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
                    cBv_(canvas, iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i, this.mViewPortHandler), f, barBuffer.buffer[i4] + (hwHealthBarEntry.getY() >= 0.0f ? this.z : this.q), valueTextColor);
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    Utils.drawImage(canvas, icon, (int) (f + mPPointF.x), (int) (barBuffer.buffer[i4] + (hwHealthBarEntry.getY() >= 0.0f ? this.z : this.q) + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
                i2 += 4;
                i++;
            }
        }
    }

    private void dxb_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        float f;
        float f2;
        float f3;
        float f4;
        for (int i = 0; i < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i += 4) {
            float f5 = (barBuffer.buffer[i] + barBuffer.buffer[i + 2]) / 2.0f;
            if (!this.mViewPortHandler.isInBoundsRight(f5)) {
                return;
            }
            int i2 = i + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i2]) && this.mViewPortHandler.isInBoundsLeft(f5)) {
                int i3 = i / 4;
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i3);
                float y = hwHealthBarEntry.getY();
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    String formattedValue = iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i3, this.mViewPortHandler);
                    if (y >= 0.0f) {
                        f3 = barBuffer.buffer[i2];
                        f4 = this.z;
                    } else {
                        f3 = barBuffer.buffer[i + 3];
                        f4 = this.q;
                    }
                    cBv_(canvas, formattedValue, f5, f3 + f4, iHwHealthBarDataSet.getValueTextColor(i3));
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    if (y >= 0.0f) {
                        f = barBuffer.buffer[i2];
                        f2 = this.z;
                    } else {
                        f = barBuffer.buffer[i + 3];
                        f2 = this.q;
                    }
                    Utils.drawImage(canvas, icon, (int) (f5 + mPPointF.x), (int) (f + f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
            }
        }
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        if (this.o) {
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
                    return;
                }
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) entriesForXValue.get(0);
                if (isInBoundsX(hwHealthBarEntry, iHwHealthBarDataSet)) {
                    Transformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
                    qai qaiVar = (qai) hwHealthBarEntry.acquireModel();
                    float b = qaiVar.b();
                    float e = qaiVar.e();
                    qah d = qaiVar.d();
                    if (this.y != null && d != null) {
                        this.y.onSleepTimeChangedListener(b - d.j());
                        b(hwHealthBarEntry.getX(), b, e, barData.d() / 2.0f, transformer);
                        dGK_(highlight, this.d);
                        dwY_(canvas, this.d, transformer, 1, d);
                    }
                }
            }
        }
    }

    public void b(OnSleepTimeChangedListener onSleepTimeChangedListener) {
        this.y = onSleepTimeChangedListener;
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
        this.o = z;
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.o;
    }

    private int a(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.o) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.getColor();
    }

    private int e(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.o) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.acquireFocusColor();
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean hasData() {
        nmz barData = this.e.getBarData();
        return (barData == null || barData.getDataSets() == null || barData.getDataSets().size() == 0) ? false : true;
    }

    @Override // defpackage.nnb
    public void d(boolean z, boolean z2) {
        this.l = z;
        this.m = z2;
    }
}

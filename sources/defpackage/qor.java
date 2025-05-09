package defpackage;

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
import com.huawei.ui.main.stories.BarChartRender;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class qor extends BarChartRender implements IHwHealthDataRender {
    private float f;
    private String g;
    private boolean h;
    private float i;
    private BarChartRender.OnTimeChangedListener k;
    private float l;
    private float m;
    private final Paint n;
    private float o;

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    public qor(HwHealthBarChart hwHealthBarChart, HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
        this.h = false;
        this.n = new Paint();
        this.g = "TEMPERATURE_MIN_MAX";
        e(hwHealthBarChart, hwHealthBarDataProvider);
        this.o = qpr.d(35.0f);
        this.i = qpr.d(37.2f);
        this.l = qpr.d(37.0f);
    }

    public void a(String str) {
        this.g = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.nnb
    protected void dGJ_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, int i) {
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
        d(transformer);
        boolean z = iHwHealthBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(b(iHwHealthBarDataSet));
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
                    if (!(acquireModel instanceof pyg)) {
                        return;
                    } else {
                        dGC_(canvas, this.c, 2, ((pyg) acquireModel).c());
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private void d(Transformer transformer) {
        float[] fArr = {0.0f, this.i};
        float[] fArr2 = {0.0f, this.o};
        transformer.pointValuesToPixel(fArr);
        transformer.pointValuesToPixel(fArr2);
        this.m = fArr2[1];
        this.f = fArr[1];
    }

    private void dGC_(Canvas canvas, RectF rectF, int i, pyl pylVar) {
        float d = pylVar.d();
        float b = pylVar.b();
        if ((d >= 1.0E-7f || b >= 1.0E-7f) && !c(d)) {
            float abs = Math.abs(rectF.right - rectF.left);
            float abs2 = Math.abs(rectF.bottom - rectF.top);
            float f = (100.0f - b) / 40.0f;
            float f2 = rectF.top;
            float f3 = rectF.bottom;
            dGH_(rectF, i);
            if (f3 - f2 >= abs) {
                dGE_(rectF, canvas, pylVar, i);
                return;
            }
            if (f * abs2 <= abs / 2.0f) {
                f3 = Math.abs(abs + f2);
            } else {
                f2 = Math.abs(f3 - abs);
            }
            RectF rectF2 = new RectF(rectF.left, f2, rectF.right, f3);
            dGF_(rectF2, canvas, pylVar, i);
            dGD_(rectF2, canvas, pylVar, i);
        }
    }

    private void dGE_(RectF rectF, Canvas canvas, pyl pylVar, int i) {
        float b = pylVar.b();
        float d = pylVar.d();
        canvas.save();
        Path path = new Path();
        float abs = Math.abs(rectF.right - rectF.left) / 2.0f;
        path.addRoundRect(rectF, abs, abs, Path.Direction.CW);
        canvas.clipPath(path);
        boolean z = false;
        boolean z2 = b >= this.i;
        boolean z3 = Math.max(this.o, d) <= Math.min(this.i, b);
        boolean z4 = d < this.o;
        if (rectF.top <= this.f && rectF.bottom >= this.f) {
            RectF rectF2 = new RectF(rectF.left, rectF.top, rectF.right, rectF.bottom);
            rectF2.bottom = this.f;
            rectF.top = this.f;
            e(b, i);
            canvas.drawRect(rectF2, this.n);
            z2 = false;
        }
        if (rectF.bottom <= this.m || rectF.top >= this.m) {
            z = z4;
        } else {
            RectF rectF3 = new RectF(rectF.left, rectF.top, rectF.right, rectF.bottom);
            rectF3.top = this.m;
            rectF.bottom = this.m;
            e(d, i);
            canvas.drawRect(rectF3, this.n);
        }
        if (z2) {
            e(b, i);
            canvas.drawRect(rectF, this.n);
        }
        if (z) {
            e(d, i);
            canvas.drawRect(rectF, this.n);
        }
        if (z3) {
            e(this.l, i);
            canvas.drawRect(rectF, this.n);
        }
        canvas.restore();
    }

    private void dGF_(RectF rectF, Canvas canvas, pyl pylVar, int i) {
        e(pylVar.b(), i);
        canvas.drawArc(rectF, 0.0f, -180.0f, false, this.n);
    }

    private void dGD_(RectF rectF, Canvas canvas, pyl pylVar, int i) {
        e(pylVar.d(), i);
        canvas.drawArc(rectF, 0.0f, 180.0f, false, this.n);
    }

    private void e(float f, int i) {
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.g)) {
            this.n.setColor(Color.argb(255, 86, 187, 191));
            c(i);
            return;
        }
        if (f >= this.i) {
            this.n.setColor(Color.argb(255, 251, 101, 34));
        } else if (f < this.o) {
            this.n.setColor(Color.argb(255, 51, 119, 255));
        } else {
            this.n.setColor(Color.argb(255, 86, 187, 191));
        }
        c(i);
    }

    private void c(int i) {
        if (i == 1) {
            this.n.setAlpha(255);
        } else {
            this.n.setAlpha(153);
        }
    }

    private void dGH_(RectF rectF, int i) {
        if (i == 1 || !LanguageUtil.bc(BaseApplication.getContext())) {
            return;
        }
        float f = rectF.left;
        float f2 = rectF.right;
        rectF.right = f;
        rectF.left = f2;
    }

    @Override // defpackage.nnb
    protected void b(float f, float f2, float f3, float f4, Transformer transformer) {
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
                        dGI_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                    } else {
                        dGG_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                    }
                    MPPointF.recycleInstance(mPPointF);
                }
            }
        }
    }

    private void dGG_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
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

    private void dGI_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
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
        BarChartRender.OnTimeChangedListener onTimeChangedListener;
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
                this.mHighlightPaint.setColor(d(iHwHealthBarDataSet));
                List<T> entriesForXValue = iHwHealthBarDataSet.getEntriesForXValue(highlight.getX());
                if ((entriesForXValue == 0 || entriesForXValue.size() == 0) && (onTimeChangedListener = this.k) != null) {
                    onTimeChangedListener.onTimeChangedListener(0.0f, 0.0f);
                    return;
                }
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) entriesForXValue.get(0);
                if (isInBoundsX(hwHealthBarEntry, iHwHealthBarDataSet) && (hwHealthBarEntry.acquireModel() instanceof pyg)) {
                    Transformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
                    d(transformer);
                    pyg pygVar = (pyg) hwHealthBarEntry.acquireModel();
                    float b = pygVar.b();
                    float e = pygVar.e();
                    pyl c = pygVar.c();
                    BarChartRender.OnTimeChangedListener onTimeChangedListener2 = this.k;
                    if (onTimeChangedListener2 != null && c != null) {
                        onTimeChangedListener2.onTimeChangedListener(c.d(), c.b());
                        b(hwHealthBarEntry.getX(), b, e, barData.d() / 2.0f, transformer);
                        dGK_(highlight, this.d);
                        dGC_(canvas, this.d, 1, c);
                    }
                }
            }
        }
    }

    public void b(BarChartRender.OnTimeChangedListener onTimeChangedListener) {
        this.k = onTimeChangedListener;
    }

    @Override // defpackage.nnb
    protected void dGK_(Highlight highlight, RectF rectF) {
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

    private int b(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.h) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.getColor();
    }

    private int d(IHwHealthBarDataSet iHwHealthBarDataSet) {
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

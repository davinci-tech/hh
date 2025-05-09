package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class nnr extends YAxisRenderer {

    /* renamed from: a, reason: collision with root package name */
    protected HwHealthBaseBarLineChart f15402a;
    protected Context b;
    public List<nns> c;
    protected boolean d;
    protected nol e;
    public Paint f;
    public float g;
    public int h;
    public boolean i;
    private float j;
    private float k;
    private float l;
    private AtomicInteger m;
    private float n;
    private float o;
    private String p;
    private AtomicBoolean q;
    private float r;
    private int t;

    public nnr(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(viewPortHandler, yAxis, transformer);
        this.i = false;
        this.d = false;
        this.t = -1;
        this.j = Float.MAX_VALUE;
        this.o = Float.MAX_VALUE;
        this.l = Float.MAX_VALUE;
        this.n = Float.MAX_VALUE;
        this.k = 0.0f;
        this.r = -3.4028235E38f;
        this.q = new AtomicBoolean(false);
        this.m = new AtomicInteger(0);
        this.b = context;
        this.f15402a = hwHealthBaseBarLineChart;
        this.e = new nol();
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        if (!(this.mYAxis instanceof HwHealthYAxis)) {
            LogUtil.h("HwHealthYAxisRenderer", "renderGridLines mYAxis not instanceof HwHealthYAxis");
            return;
        }
        HwHealthYAxis hwHealthYAxis = (HwHealthYAxis) this.mYAxis;
        if (hwHealthYAxis.isEnabled()) {
            boolean isDrawGridLinesEnabled = hwHealthYAxis.isDrawGridLinesEnabled();
            if (isDrawGridLinesEnabled || this.i) {
                int save = canvas.save();
                canvas.clipRect(new RectF(0.0f, 0.0f, this.mViewPortHandler.getChartWidth(), this.mViewPortHandler.getChartHeight()));
                this.mGridPaint.setColor(hwHealthYAxis.getGridColor());
                this.mGridPaint.setStrokeWidth(hwHealthYAxis.getGridLineWidth());
                this.mGridPaint.setPathEffect(hwHealthYAxis.getGridDashPathEffect());
                Path path = this.mRenderGridLinesPath;
                path.reset();
                float[] transformedPositions = getTransformedPositions();
                if (isDrawGridLinesEnabled) {
                    for (int i = 0; i < transformedPositions.length; i += 2) {
                        if (i == transformedPositions.length - 1 || i == transformedPositions.length - 2 || i == 0 || i == 1) {
                            cBK_(transformedPositions, i, path, canvas);
                        } else {
                            this.mGridPaint.setColor(hwHealthYAxis.c() ? hwHealthYAxis.getGridColor() : hwHealthYAxis.d());
                            canvas.drawPath(linePath(path, i, transformedPositions), this.mGridPaint);
                            path.reset();
                            this.mGridPaint.setColor(hwHealthYAxis.getGridColor());
                        }
                    }
                }
                if (this.i) {
                    this.mGridPaint.setColor(this.h);
                    float[] fArr = {0.0f, this.g};
                    this.mTrans.pointValuesToPixel(fArr);
                    Path dxA_ = dxA_(path, fArr[1]);
                    Paint paint = this.f;
                    if (paint == null) {
                        paint = this.mGridPaint;
                    }
                    canvas.drawPath(dxA_, paint);
                    path.reset();
                }
                canvas.restoreToCount(save);
            }
            if (hwHealthYAxis.isDrawZeroLineEnabled()) {
                drawZeroLine(canvas);
            }
            cBJ_(canvas);
        }
    }

    private void cBK_(float[] fArr, int i, Path path, Canvas canvas) {
        int gridColor = this.mYAxis.getGridColor();
        if (!(this.mYAxis instanceof HwHealthYAxis)) {
            LogUtil.h("HwHealthYAxisRenderer", "mYAxis not instanceof HwHealthYAxis");
            return;
        }
        int a2 = ((HwHealthYAxis) this.mYAxis).c() ? gridColor : ((HwHealthYAxis) this.mYAxis).a();
        this.mGridPaint.setPathEffect(null);
        this.mGridPaint.setColor(a2);
        canvas.drawPath(linePath(path, i, fArr), this.mGridPaint);
        path.reset();
        this.mGridPaint.setPathEffect(this.mYAxis.getGridDashPathEffect());
        this.mGridPaint.setColor(gridColor);
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    public Path linePath(Path path, int i, float[] fArr) {
        return dxA_(path, fArr[i + 1]);
    }

    protected Path dxA_(Path path, float f) {
        float f2 = this.f15402a.acquireChartAnchor().f();
        float j = this.f15402a.acquireChartAnchor().j();
        path.moveTo(f2, f);
        path.lineTo(j, f);
        return path;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        float d = d();
        for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i2 < i; i2++) {
            float f3 = this.mYAxis.mEntries[i2];
            if (!this.i || Math.abs(this.r - f3) >= 1.0E-6d) {
                canvas.drawText(this.mYAxis.getFormattedLabel(i2), f, fArr[(i2 * 2) + 1] + d, this.mAxisLabelPaint);
            }
        }
    }

    protected float d() {
        Paint.FontMetrics fontMetrics = this.mAxisLabelPaint.getFontMetrics();
        return ((fontMetrics.bottom - fontMetrics.top) * (-1.0f)) + (fontMetrics.top * (-1.0f));
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        if (!(this.mYAxis instanceof HwHealthYAxis)) {
            LogUtil.h("HwHealthYAxisRenderer", "renderAxisLabels mYAxis not instanceof HwHealthYAxis");
            return;
        }
        HwHealthYAxis hwHealthYAxis = (HwHealthYAxis) this.mYAxis;
        if (!hwHealthYAxis.isEnabled() || hwHealthYAxis.getAxisMaximum() == 2000000.0f) {
            return;
        }
        h();
        HwHealthYAxis.HwHealthAxisDependency e = hwHealthYAxis.e();
        if (e == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
            return;
        }
        boolean isDrawLabelsEnabled = hwHealthYAxis.isDrawLabelsEnabled();
        if (isDrawLabelsEnabled || this.i) {
            float requiredWidthSpace = hwHealthYAxis.getRequiredWidthSpace(null);
            boolean d = nng.d(this.b);
            if (this.d) {
                d = !d;
            }
            float e2 = e(e, requiredWidthSpace, d);
            float[] transformedPositions = getTransformedPositions();
            float calcTextHeight = Utils.calcTextHeight(this.mAxisLabelPaint, "A") / 2.5f;
            float yOffset = hwHealthYAxis.getYOffset();
            if (isDrawLabelsEnabled) {
                drawYLabels(canvas, e2, transformedPositions, calcTextHeight + yOffset);
            }
            if (!this.i || this.g == -2.1474836E9f) {
                return;
            }
            float d2 = d();
            Rect rect = new Rect();
            String formattedValue = TextUtils.isEmpty(this.p) ? hwHealthYAxis.getValueFormatter().getFormattedValue(this.g, hwHealthYAxis) : this.p;
            this.mAxisLabelPaint.getTextBounds(formattedValue, 0, formattedValue.length(), rect);
            float[] fArr = {0.0f, this.g};
            this.mTrans.pointValuesToPixel(fArr);
            float f = fArr[1];
            if (this.f != null) {
                this.mAxisLabelPaint.setColor(this.f.getColor());
            } else {
                this.mAxisLabelPaint.setColor(j());
            }
            canvas.drawText(formattedValue, e2, f + d2, this.mAxisLabelPaint);
        }
    }

    private int j() {
        int i = this.t;
        return i != -1 ? i : this.h;
    }

    private void h() {
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
    }

    protected float e(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency, float f, boolean z) {
        if ((hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && !z) || (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && z)) {
            this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
            return this.mViewPortHandler.offsetLeft() - f;
        }
        if ((hwHealthAxisDependency != HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY || z) && !(hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && z)) {
            return 0.0f;
        }
        this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
        return this.mViewPortHandler.contentRight() + f;
    }

    public void b(boolean z) {
        this.d = z;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLine(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawAxisLineEnabled()) {
            this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
            if (!(this.mYAxis instanceof HwHealthYAxis)) {
                LogUtil.h("HwHealthYAxisRenderer", "renderAxisLine mYAxis not instanceof HwHealthYAxis");
                return;
            }
            if (((HwHealthYAxis) this.mYAxis).e() == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
                return;
            }
            HwHealthYAxis.HwHealthAxisDependency e = ((HwHealthYAxis) this.mYAxis).e();
            boolean d = nng.d(this.b);
            if (this.d) {
                d = !d;
            }
            if ((e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && !d) || (e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && d)) {
                canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            } else {
                if ((e != HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY || d) && !(e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && d)) {
                    return;
                }
                canvas.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float f, float f2, boolean z) {
        if (Math.abs(f2 - Float.MAX_VALUE) < 1.0E-6d || Math.abs(Float.MAX_VALUE + f) < 1.0E-6d) {
            super.computeAxis(f, f2, z);
            return;
        }
        if (!(this.mAxis instanceof HwHealthYAxis)) {
            LogUtil.h("HwHealthYAxisRenderer", "mAxis not instanceof HwHealthYAxis");
            return;
        }
        HwHealthYAxis.HwHealthAxisDependency e = ((HwHealthYAxis) this.mAxis).e();
        if (!(this.f15402a.getData() instanceof HwHealthBaseBarLineData)) {
            LogUtil.h("HwHealthYAxisRenderer", "mChart.getData() not instanceof HwHealthBaseBarLineData");
            return;
        }
        HwHealthBaseBarLineData hwHealthBaseBarLineData = (HwHealthBaseBarLineData) this.f15402a.getData();
        if (e == null || hwHealthBaseBarLineData == null) {
            LogUtil.h("HwHealthYAxisRenderer", "computeAxis dependency null or chart chartData null,return");
            return;
        }
        IHwHealthBarLineDataSet dataSet = hwHealthBaseBarLineData.getDataSet(e);
        if (dataSet == null) {
            super.computeAxis(f, f2, z);
            return;
        }
        if (dataSet.getForcedLabels() == null || dataSet.getForcedLabels().length == 0) {
            this.mAxis.setLabelCount(dataSet.getLabelCount(), true);
            super.computeAxis(f, f2, z);
            return;
        }
        b(dataSet.getForcedLabels());
    }

    private void b(float[] fArr) {
        this.mAxis.mEntryCount = fArr.length;
        this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
        System.arraycopy(fArr, 0, this.mAxis.mEntries, 0, fArr.length);
    }

    public void a(float f, float f2, boolean z, HwHealthBaseScrollBarLineChart.e eVar, HwHealthBaseScrollBarLineChart.AnimateValueTransfer animateValueTransfer, int i, int i2) {
        if (Math.abs(f - 0.0f) < 1.0E-6d) {
            eVar.d();
            return;
        }
        if (Math.abs(f - this.o) < 1.0E-6d && Math.abs(f2 - this.l) < 1.0E-6d) {
            eVar.d();
            return;
        }
        if (this.q.get() && i2 < this.m.get()) {
            eVar.d();
            return;
        }
        this.mYAxis.setAxisMinimum(f2);
        this.o = f;
        this.l = f2;
        if (Math.abs(this.n - Float.MAX_VALUE) < 1.0E-6d) {
            float f3 = this.o;
            this.n = f3;
            this.k = f3;
            this.j = 0.0f;
            this.mYAxis.setAxisMaximum(this.n);
            this.f15402a.postInvalidate();
            eVar.d();
            return;
        }
        float f4 = this.o;
        float f5 = f4 - this.n;
        if (f5 > 0.0f) {
            this.n = f5 + f4;
        }
        float f6 = f4 - this.n;
        this.j = f6;
        LogUtil.c("HwHealthYAxisRenderer", "set Velocity:", Float.valueOf(f6));
        this.k = this.n;
        if (z) {
            i = 0;
        }
        this.q.set(true);
        this.m.set(i2);
        b(animateValueTransfer, eVar, i);
    }

    private void b(final HwHealthBaseScrollBarLineChart.AnimateValueTransfer animateValueTransfer, final HwHealthBaseScrollBarLineChart.e eVar, int i) {
        this.e.cCx_(new ValueAnimator.AnimatorUpdateListener() { // from class: nnr.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float transferValue = animateValueTransfer.transferValue(nnr.this.e.d());
                float f = nnr.this.k + (nnr.this.j * transferValue);
                nnr.this.mYAxis.setAxisMaximum(f);
                nnr.this.n = f;
                nnr.this.f15402a.postInvalidate();
                if (Float.compare(transferValue, 1.0f) == 0) {
                    eVar.d();
                    nnr.this.q.set(false);
                    nnr.this.m.set(0);
                }
            }
        }, i);
    }

    public void i() {
        this.o = Float.MAX_VALUE;
        this.l = Float.MAX_VALUE;
    }

    public void e(float f, int i, String str) {
        c(f, i);
        this.p = str;
    }

    public void a(int i, int i2) {
        this.i = true;
        this.g = i;
        this.h = i2;
    }

    public void c(float f, int i) {
        this.i = true;
        this.g = f;
        this.h = i;
    }

    public void d(int i) {
        this.i = true;
        this.t = i;
    }

    public void dxz_(int i, Paint paint) {
        this.i = true;
        this.g = i;
        this.f = paint;
    }

    public void a() {
        this.i = false;
        this.f = null;
    }

    public boolean f() {
        return this.i;
    }

    public float b() {
        return this.g;
    }

    public void c() {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled() && this.mAxis.getAxisMaximum() != 2000000.0f && this.i) {
            float[] transformedPositions = getTransformedPositions();
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            if (!(this.mYAxis instanceof HwHealthYAxis)) {
                LogUtil.h("HwHealthYAxisRenderer", "considerGridLinesAndManualRefLine mYAxis not instanceof HwHealthYAxis");
                return;
            }
            if (((HwHealthYAxis) this.mYAxis).e() == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
                return;
            }
            int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
            float d = d();
            Rect rect = new Rect();
            String formattedValue = this.mYAxis.getValueFormatter().getFormattedValue(this.g, this.mYAxis);
            this.mAxisLabelPaint.getTextBounds(formattedValue, 0, formattedValue.length(), rect);
            float[] fArr = {0.0f, this.g};
            this.mTrans.pointValuesToPixel(fArr);
            float f = fArr[1] + d;
            float height = rect.height() + f;
            this.r = -3.4028235E38f;
            for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i2 < i; i2++) {
                String formattedLabel = this.mYAxis.getFormattedLabel(i2);
                this.mAxisLabelPaint.getTextBounds(formattedLabel, 0, formattedLabel.length(), rect);
                float f2 = transformedPositions[(i2 * 2) + 1] + d;
                float height2 = rect.height() + f2;
                if ((f2 <= height || height2 <= height) && (f2 >= f || height2 >= f)) {
                    if (this.mYAxis instanceof HwHealthYAxis) {
                        this.r = ((HwHealthYAxis) this.mYAxis).mEntries[i2];
                        return;
                    }
                    return;
                }
            }
        }
    }

    public float e_() {
        return this.g;
    }

    public void e(List<nns> list) {
        this.c = list;
    }

    private void cBJ_(Canvas canvas) {
        if (koq.b(this.c)) {
            return;
        }
        canvas.save();
        float axisMaximum = this.mYAxis.getAxisMaximum();
        float axisMinimum = this.mYAxis.getAxisMinimum();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        for (nns nnsVar : this.c) {
            if (nnsVar.a() < 0.0f || nnsVar.c() > axisMaximum || nnsVar.d() < axisMinimum) {
                LogUtil.h("HwHealthYAxisRenderer", "drawRecommendInterval area data invalid");
            } else {
                paint.setColor(nnsVar.e());
                float[] fArr = {nnsVar.a(), Math.min(nnsVar.d(), axisMaximum), nnsVar.b(), Math.max(nnsVar.c(), axisMinimum)};
                this.mTrans.pointValuesToPixel(fArr);
                canvas.drawRect(new RectF(fArr[0], fArr[1], fArr[2], fArr[3]), paint);
            }
        }
    }
}

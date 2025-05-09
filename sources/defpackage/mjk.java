package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class mjk extends BarChartRenderer {

    /* renamed from: a, reason: collision with root package name */
    private Paint f15021a;
    private float b;
    private Context c;
    private IBarDataSet d;
    private int e;
    private float f;
    private float g;
    private Path h;
    private float[] i;
    private float j;
    private Transformer n;

    public mjk(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.i = new float[2];
    }

    public Transformer b() {
        return this.n;
    }

    private int d() {
        Paint paint = new Paint();
        paint.setTextSize(10.0f);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((int) Math.ceil(fontMetrics.descent - fontMetrics.top)) + 2;
    }

    private float a() {
        float f = this.j;
        return (f / 40.0f <= 10.0f || f % 40.0f == 0.0f) ? f : ((((int) f) / 40) + 1) * 40;
    }

    public void ciK_(Canvas canvas) {
        LogUtil.a("WisdomBarChartRenderer", "drawChart()");
        ciJ_(canvas, this.d, this.e);
    }

    private void ciI_(Canvas canvas, Transformer transformer) {
        Paint paint = new Paint();
        paint.setColor(this.c.getResources().getColor(R.color._2131298083_res_0x7f090723));
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint(1);
        paint2.setColor(this.c.getResources().getColor(R.color._2131296380_res_0x7f09007c));
        paint2.setTextSize(Utils.convertDpToPixel(10.0f));
        ciH_(canvas, transformer, a(), paint2, paint);
        ciH_(canvas, transformer, (a() * 3.0f) / 4.0f, paint2, paint);
        ciH_(canvas, transformer, a() / 2.0f, paint2, paint);
        ciH_(canvas, transformer, a() / 4.0f, paint2, paint);
        ciH_(canvas, transformer, 0.0f, paint2, paint);
        c();
        float[] fArr = this.i;
        fArr[0] = 0.0f;
        fArr[1] = this.g;
        transformer.pointValuesToPixel(fArr);
        Path path = new Path();
        this.h = path;
        float[] fArr2 = this.i;
        if (fArr2[1] <= 0.0f) {
            fArr2[1] = 1.0f;
        }
        path.moveTo(fArr2[0], fArr2[1]);
        this.h.lineTo(canvas.getWidth(), this.i[1]);
        this.f15021a.setColor(this.c.getResources().getColor(R.color._2131296371_res_0x7f090073));
        canvas.drawPath(this.h, this.f15021a);
        this.h.reset();
    }

    private void ciH_(Canvas canvas, Transformer transformer, float f, Paint paint, Paint paint2) {
        float convertDpToPixel = Utils.convertDpToPixel(1.5f);
        Path path = new Path();
        transformer.pointValuesToPixel(new float[]{0.0f, f});
        path.moveTo(0.0f, f);
        path.lineTo(canvas.getWidth(), f);
        canvas.drawText(d(f), 0.0f, f - convertDpToPixel, paint);
        canvas.drawPath(path, paint2);
        path.reset();
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        LogUtil.a("WisdomBarChartRenderer", "mDataSet = null");
        this.d = iBarDataSet;
        this.e = i;
    }

    private void ciJ_(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        LogUtil.a("WisdomBarChartRenderer", "resetDrawDataSet()");
        if (iBarDataSet == null) {
            LogUtil.a("WisdomBarChartRenderer", "resetDrawDataSet() mDataSet = null");
            return;
        }
        this.n = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        this.n.pointValuesToPixel(barBuffer.buffer);
        boolean z = iBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        float f = barBuffer.buffer.length > 0 ? barBuffer.buffer[1] : 0.0f;
        for (int i2 = 1; i2 < barBuffer.size(); i2 += 4) {
            if (barBuffer.buffer[i2] < f) {
                f = barBuffer.buffer[i2];
            }
        }
        LogUtil.c("WisdomBarChartRenderer", "tmpData = ", Float.valueOf(f));
        this.j = (this.f * (((canvas.getHeight() - f) + d()) + Utils.convertDpToPixel(6.0f))) / (canvas.getHeight() - f);
        ciI_(canvas, this.n);
        float f2 = barBuffer.buffer.length > 1 ? ((barBuffer.buffer[2] - barBuffer.buffer[0]) - this.b) / 2.0f : 0.0f;
        for (int i3 = 0; i3 < barBuffer.size(); i3 += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i3 + 2])) {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i3])) {
                    return;
                }
                if (!z) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i3 / 4));
                }
                ciG_(barBuffer, canvas, f2, i3);
            }
        }
    }

    private void ciG_(BarBuffer barBuffer, Canvas canvas, float f, int i) {
        float[] fArr = {0.0f, 0.0f};
        this.n.pointValuesToPixel(fArr);
        int i2 = i + 1;
        int i3 = i + 2;
        if (fArr[1] > barBuffer.buffer[i2] + (((barBuffer.buffer[i3] - barBuffer.buffer[i]) - (f * 2.0f)) / 2.0f)) {
            canvas.drawArc(new RectF(barBuffer.buffer[i] + f, barBuffer.buffer[i2], barBuffer.buffer[i3] - f, barBuffer.buffer[i2] + this.b), 180.0f, 180.0f, false, this.mRenderPaint);
            canvas.drawRect(barBuffer.buffer[i] + f, (barBuffer.buffer[i2] + (this.b / 2.0f)) - 1.0f, barBuffer.buffer[i3] - f, barBuffer.buffer[i + 3], this.mRenderPaint);
            return;
        }
        Path path = new Path();
        int i4 = i + 3;
        path.moveTo(barBuffer.buffer[i] + f, barBuffer.buffer[i4]);
        path.quadTo((barBuffer.buffer[i] + barBuffer.buffer[i3]) / 2.0f, (barBuffer.buffer[i2] * 2.0f) - barBuffer.buffer[i4], barBuffer.buffer[i3] - f, barBuffer.buffer[i4]);
        canvas.drawPath(path, this.mRenderPaint);
    }

    private String d(float f) {
        return UnitUtil.e(f, 2, 0);
    }

    private void c() {
        Paint paint = new Paint(1);
        this.f15021a = paint;
        paint.setAntiAlias(true);
        this.f15021a.setStyle(Paint.Style.STROKE);
        this.f15021a.setStrokeWidth(1.0f);
        this.f15021a.setPathEffect(new DashPathEffect(new float[]{0.0f, 0.0f}, 1.0f));
    }
}

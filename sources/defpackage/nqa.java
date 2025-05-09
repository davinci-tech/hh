package defpackage;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.view.InputDeviceCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class nqa extends BarChartRenderer {

    /* renamed from: a, reason: collision with root package name */
    private IBarDataSet f15436a;
    private boolean b;
    private int c;
    private float d;
    private boolean e;
    private float f;
    private Path g;
    private Paint h;
    private boolean i;
    private Paint j;
    private int k;
    private float n;
    private float[] o;

    public nqa(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.h = new Paint(1);
        this.i = false;
        this.b = true;
        this.e = false;
        this.o = new float[2];
    }

    public void cEV_(Canvas canvas) {
        cER_(canvas, this.f15436a, this.c);
    }

    public void cEW_(Canvas canvas, Transformer transformer) {
        Paint paint = new Paint();
        paint.setColor(this.k);
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        a();
        float[] fArr = this.o;
        fArr[0] = 0.0f;
        fArr[1] = this.f;
        transformer.pointValuesToPixel(fArr);
        if (this.f > 0.0f) {
            Path path = new Path();
            this.g = path;
            path.moveTo(0.0f, this.o[1]);
            this.g.lineTo(canvas.getWidth(), this.o[1]);
            this.j.setColor(this.k);
            canvas.drawPath(this.g, this.j);
            this.g.reset();
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        this.f15436a = iBarDataSet;
        this.c = i;
    }

    private void cER_(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        if (iBarDataSet == null) {
            return;
        }
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        if (this.mBarBuffers == null || i >= this.mBarBuffers.length || i < 0) {
            return;
        }
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
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
        cEW_(canvas, transformer);
        cEU_(barBuffer, z, f, canvas, transformer, barBuffer.buffer.length > 1 ? ((barBuffer.buffer[2] - barBuffer.buffer[0]) - this.d) / 2.0f : 0.0f);
        this.b = true;
    }

    private String c(float f) {
        if (this.i) {
            return UnitUtil.e(f, 1, 2);
        }
        return UnitUtil.e(f, 1, 0);
    }

    private void a() {
        Paint paint = new Paint(1);
        this.j = paint;
        paint.setAntiAlias(true);
        this.j.setColor(InputDeviceCompat.SOURCE_ANY);
        this.j.setStyle(Paint.Style.STROKE);
        this.j.setStrokeWidth(1.0f);
        this.j.setPathEffect(new DashPathEffect(new float[]{6.0f, 6.0f}, 1.0f));
        this.h.setColor(BaseApplication.getContext().getResources().getColor(R$color.textColorPrimary));
        this.h.setTextSize(Utils.convertDpToPixel(10.0f));
    }

    public void e(int i, float f, float f2, float f3, boolean z) {
        this.k = i;
        this.f = f;
        this.n = f2;
        this.d = f3;
        this.i = z;
    }

    public boolean c() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void cEU_(BarBuffer barBuffer, boolean z, float f, Canvas canvas, Transformer transformer, float f2) {
        for (int i = 0; i < barBuffer.size(); i += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i + 2])) {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i])) {
                    return;
                }
                if (!z) {
                    this.mRenderPaint.setColor(this.f15436a.getColor(i / 4));
                }
                if (((BarEntry) this.f15436a.getEntryForIndex(i / 4)).getY() >= this.f) {
                    this.mRenderPaint.setColor(this.k);
                } else {
                    this.mRenderPaint.setColor(this.f15436a.getColor());
                }
                if (f == barBuffer.buffer[i + 1] && this.b && this.n > 0.0f) {
                    cET_(canvas, barBuffer, i, f2);
                    this.b = false;
                }
                cES_(canvas, transformer, barBuffer, i, f2);
            }
        }
    }

    private void cET_(Canvas canvas, BarBuffer barBuffer, int i, float f) {
        float measureText = this.h.measureText(c(this.n));
        if (c()) {
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                canvas.drawText(c(this.n), (((barBuffer.buffer[i] + f) - (measureText / 2.0f)) + (this.d / 2.0f)) - Utils.convertDpToPixel(13.0f), barBuffer.buffer[i + 1] - Utils.convertDpToPixel(2.0f), this.h);
                return;
            } else {
                canvas.drawText(c(this.n), ((barBuffer.buffer[i] + f) - (measureText / 2.0f)) + (this.d / 2.0f) + Utils.convertDpToPixel(13.0f), barBuffer.buffer[i + 1] - Utils.convertDpToPixel(2.0f), this.h);
                return;
            }
        }
        canvas.drawText(c(this.n), ((barBuffer.buffer[i] + f) - (measureText / 2.0f)) + (this.d / 2.0f), barBuffer.buffer[i + 1] - Utils.convertDpToPixel(2.0f), this.h);
    }

    private void cES_(Canvas canvas, Transformer transformer, BarBuffer barBuffer, int i, float f) {
        float[] fArr = {0.0f, 0.0f};
        transformer.pointValuesToPixel(fArr);
        int i2 = i + 1;
        int i3 = i + 2;
        if (fArr[1] > barBuffer.buffer[i2] + (((barBuffer.buffer[i3] - barBuffer.buffer[i]) - (f * 2.0f)) / 2.0f)) {
            canvas.drawArc(new RectF(barBuffer.buffer[i] + f, barBuffer.buffer[i2], barBuffer.buffer[i3] - f, barBuffer.buffer[i2] + this.d), 180.0f, 180.0f, false, this.mRenderPaint);
            canvas.drawRect(barBuffer.buffer[i] + f, barBuffer.buffer[i2] + (this.d / 2.0f), barBuffer.buffer[i3] - f, barBuffer.buffer[i + 3], this.mRenderPaint);
            return;
        }
        Path path = new Path();
        int i4 = i + 3;
        path.moveTo(barBuffer.buffer[i] + f, barBuffer.buffer[i4]);
        path.quadTo((barBuffer.buffer[i] + barBuffer.buffer[i3]) / 2.0f, (barBuffer.buffer[i2] * 2.0f) - barBuffer.buffer[i4], barBuffer.buffer[i3] - f, barBuffer.buffer[i4]);
        canvas.drawPath(path, this.mRenderPaint);
    }
}

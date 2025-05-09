package defpackage;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class lbr extends YAxisRenderer {

    /* renamed from: a, reason: collision with root package name */
    private float f14752a;
    private float b;
    private float c;
    private float d;
    private List<nns> e;
    private float[] h;

    public lbr(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super(viewPortHandler, yAxis, transformer);
        this.h = new float[5];
        yAxis.setLabelCount(5, true);
    }

    public void b(List<nns> list) {
        this.e = list;
    }

    public void d(float f, float f2, float f3, float f4) {
        this.d = f;
        this.b = f2;
        this.c = f3;
        this.f14752a = f4;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        if (canvas == null || this.mYAxis == null) {
            LogUtil.h("HeartRateControlSportYaxisRender", "renderGridLines base is null");
            return;
        }
        if (!this.mYAxis.isEnabled()) {
            LogUtil.h("HeartRateControlSportYaxisRender", "Yaxis invalid");
            return;
        }
        if (this.mYAxis.isDrawGridLinesEnabled()) {
            canvas.clipRect(new RectF(0.0f, 0.0f, this.mViewPortHandler.getChartWidth(), this.mViewPortHandler.getChartHeight()));
            this.mGridPaint.setColor(this.mYAxis.getGridColor());
            this.mGridPaint.setStrokeWidth(this.b);
            this.mGridPaint.setStyle(Paint.Style.STROKE);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{this.c, this.f14752a}, 0.0f);
            this.h = new float[5];
            if (this.mYAxis.mEntries.length < 2) {
                LogUtil.a("HeartRateControlSportYaxisRender", "mYAxis.mEntries.length is invalid ", Integer.valueOf(this.mYAxis.mEntries.length));
                return;
            }
            float f = (this.mYAxis.mEntries[this.mYAxis.mEntries.length - 1] - this.mYAxis.mEntries[0]) / 4.0f;
            for (int i = 0; i < 5; i++) {
                this.h[i] = this.mYAxis.mEntries[0] + (i * f);
                float[] fArr = {0.0f, (float) Math.floor(this.h[i])};
                this.mTrans.pointValuesToPixel(fArr);
                this.mRenderGridLinesPath.reset();
                if (i == 0) {
                    this.mGridPaint.setPathEffect(null);
                } else {
                    this.mGridPaint.setPathEffect(dashPathEffect);
                }
                canvas.drawPath(bVF_(this.mRenderGridLinesPath, fArr[1]), this.mGridPaint);
            }
            bVE_(canvas);
        }
    }

    private void bVE_(Canvas canvas) {
        List<nns> list = this.e;
        if (list == null || list.size() < 1) {
            LogUtil.h("HeartRateControlSportYaxisRender", "HeartRateIntervalArea is null");
            return;
        }
        float axisMaximum = this.mYAxis.getAxisMaximum();
        float axisMinimum = this.mYAxis.getAxisMinimum();
        this.mGridPaint.setStyle(Paint.Style.FILL);
        this.mGridPaint.setPathEffect(null);
        for (nns nnsVar : this.e) {
            if (nnsVar.a() < 0.0f || nnsVar.d() > axisMaximum || nnsVar.c() < axisMinimum) {
                LogUtil.h("HeartRateControlSportYaxisRender", "drawRecommendInterval area data invalid");
            } else {
                this.mGridPaint.setColor(nnsVar.e());
                float[] fArr = {nnsVar.a(), nnsVar.d(), nnsVar.b(), nnsVar.c()};
                this.mTrans.pointValuesToPixel(fArr);
                canvas.drawRect(new RectF(fArr[0], fArr[1], fArr[2], fArr[3]), this.mGridPaint);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        if (this.mGetTransformedPositionsBuffer.length != 10) {
            this.mGetTransformedPositionsBuffer = new float[10];
        }
        float[] fArr2 = this.mGetTransformedPositionsBuffer;
        for (int i = 0; i < fArr2.length; i += 2) {
            fArr2[i + 1] = this.h[i / 2];
        }
        this.mTrans.pointValuesToPixel(fArr2);
        float e = e();
        this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
        float f3 = this.d;
        for (int i2 = 0; i2 < 5; i2++) {
            canvas.drawText(c(i2), f3, fArr2[(i2 * 2) + 1] + e, this.mAxisLabelPaint);
        }
    }

    private String c(int i) {
        return (i < 0 || i >= this.h.length) ? "" : this.mYAxis.getValueFormatter().getFormattedValue(this.h[i], this.mAxis);
    }

    protected Path bVF_(Path path, float f) {
        float f2 = this.d;
        float contentRight = this.mViewPortHandler.contentRight();
        path.moveTo(f2, f);
        path.lineTo(contentRight, f);
        return path;
    }

    protected float e() {
        Paint.FontMetrics fontMetrics = this.mAxisLabelPaint.getFontMetrics();
        return ((fontMetrics.bottom - fontMetrics.top) * (-1.0f)) + (fontMetrics.top * (-1.0f));
    }
}

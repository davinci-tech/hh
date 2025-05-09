package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import health.compact.a.UnitUtil;

/* loaded from: classes3.dex */
public class ecp extends npe {
    private int j;
    private float k;
    private float l;
    private int m;
    private float n;
    private boolean o;
    private float q;
    private float[] s;

    private boolean d(float f) {
        return f >= 30.0f && f <= 300.0f;
    }

    public ecp(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
        this.o = true;
        this.j = nrn.d(R$color.emuiColor10);
        this.m = nrn.d(R$color.emui_color_fg_2);
        this.s = new float[5];
        j();
    }

    @Override // defpackage.npe, defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        if (canvas == null || this.mYAxis == null || !this.mYAxis.isEnabled()) {
            return;
        }
        if (this.mYAxis.isDrawGridLinesEnabled()) {
            canvas.clipRect(new RectF(0.0f, 0.0f, this.mViewPortHandler.getChartWidth(), this.mViewPortHandler.getChartHeight()));
            this.mGridPaint.setColor(this.mYAxis.getGridColor());
            this.mGridPaint.setStrokeWidth(nrr.e(this.b, 0.5f));
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{nrr.e(this.b, 2.0f), nrr.e(this.b, 1.0f)}, 0.0f);
            Path path = this.mRenderGridLinesPath;
            int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? 5 : 4;
            this.s = new float[5];
            float f = (this.mYAxis.mEntries[this.mYAxis.mEntries.length - 1] - this.mYAxis.mEntries[0]) / 4.0f;
            for (int i2 = 0; i2 < 5; i2++) {
                this.s[i2] = this.mYAxis.mEntries[0] + (i2 * f);
            }
            for (int i3 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i3 < i; i3++) {
                float[] fArr = {0.0f, (float) Math.floor(this.s[i3])};
                this.mTrans.pointValuesToPixel(fArr);
                path.reset();
                if (i3 == 0) {
                    this.mGridPaint.setPathEffect(null);
                } else {
                    this.mGridPaint.setPathEffect(dashPathEffect);
                }
                canvas.drawPath(dxA_(path, fArr[1]), this.mGridPaint);
                path.reset();
            }
            float f2 = this.k;
            if (f2 > 0.0f) {
                float f3 = this.l;
                if (f3 > 0.0f) {
                    afs_(canvas, f2, f3, this.j);
                    afs_(canvas, this.n, this.q, this.m);
                    this.mGridPaint.setPathEffect(null);
                    this.mGridPaint.setAlpha(R.dimen._2131364817_res_0x7f0a0bd1);
                    this.mGridPaint.setColor(this.j);
                    aft_(canvas, this.k);
                    this.mGridPaint.setColor(this.m);
                    aft_(canvas, this.n);
                }
            }
            this.mGridPaint.setColor(this.mYAxis.getGridColor());
        }
        if (this.mYAxis.isDrawZeroLineEnabled()) {
            drawZeroLine(canvas);
        }
    }

    @Override // defpackage.npe, defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        if (this.mGetTransformedPositionsBuffer.length != 10) {
            this.mGetTransformedPositionsBuffer = new float[10];
        }
        float[] fArr2 = this.mGetTransformedPositionsBuffer;
        for (int i = 0; i < fArr2.length; i += 2) {
            fArr2[i + 1] = this.s[i / 2];
        }
        this.mTrans.pointValuesToPixel(fArr2);
        int i2 = this.mYAxis.isDrawTopYLabelEntryEnabled() ? 5 : 4;
        float d = d();
        float f3 = this.mAxisLabelPaint.getFontMetrics().bottom - this.mAxisLabelPaint.getFontMetrics().top;
        float[] fArr3 = {0.0f, this.k};
        this.mTrans.pointValuesToPixel(fArr3);
        float f4 = fArr3[1];
        float[] fArr4 = {0.0f, this.n};
        this.mTrans.pointValuesToPixel(fArr4);
        float f5 = fArr4[1];
        for (int i3 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i3 < i2; i3++) {
            float f6 = fArr2[(i3 * 2) + 1];
            if (!this.o || (Math.abs(f4 - f6) >= f3 && Math.abs(f5 - f6) >= f3)) {
                canvas.drawText(a(i3), f, f6 + d, this.mAxisLabelPaint);
            }
        }
    }

    private String a(int i) {
        return (i < 0 || i >= this.s.length) ? "" : this.mYAxis.getValueFormatter().getFormattedValue(this.s[i], this.mAxis);
    }

    private void aft_(Canvas canvas, float f) {
        if (!d(f)) {
            LogUtil.h("BloodPressureLineYaxisRender", "The reference blood sugar value is not within the reasonable range.");
            return;
        }
        float[] fArr = {0.0f, f};
        this.mTrans.pointValuesToPixel(fArr);
        this.mRenderGridLinesPath.reset();
        canvas.drawPath(dxA_(this.mRenderGridLinesPath, fArr[1]), this.mGridPaint);
        this.mRenderGridLinesPath.reset();
    }

    private void afs_(Canvas canvas, float f, float f2, int i) {
        if (!d(f) || !d(f2)) {
            LogUtil.h("BloodPressureLineYaxisRender", "The reference blood sugar value is not within the reasonable range.");
            return;
        }
        float[] fArr = {0.0f, f};
        this.mTrans.pointValuesToPixel(fArr);
        float[] fArr2 = {0.0f, f2};
        this.mTrans.pointValuesToPixel(fArr2);
        Path path = new Path();
        float f3 = this.f15402a.acquireChartAnchor().f();
        float j = this.f15402a.acquireChartAnchor().j();
        path.moveTo(f3, fArr[1]);
        path.lineTo(j, fArr[1]);
        path.lineTo(j, fArr2[1]);
        path.lineTo(f3, fArr2[1]);
        path.lineTo(f3, fArr[1]);
        Paint paint = new Paint();
        paint.setColor(i);
        if (nrt.a(BaseApplication.getContext())) {
            paint.setAlpha(26);
        } else {
            paint.setAlpha(12);
        }
        canvas.drawPath(path, paint);
        afu_(canvas, f, i);
    }

    private void afu_(Canvas canvas, float f, int i) {
        HwHealthYAxis.HwHealthAxisDependency e;
        if (!this.o || f == -2.1474836E9f || !(this.mYAxis instanceof HwHealthYAxis) || (e = ((HwHealthYAxis) this.mYAxis).e()) == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
            return;
        }
        if (!(this.mAxis instanceof HwHealthYAxis)) {
            LogUtil.h("BloodPressureLineYaxisRender", "renderAxisLabels mAxis not instanceof HwHealthYAxis");
            return;
        }
        float requiredWidthSpace = ((HwHealthYAxis) this.mAxis).getRequiredWidthSpace(null);
        boolean d = nng.d(this.b);
        if (this.d) {
            d = !d;
        }
        float e2 = e(e, requiredWidthSpace, d);
        String valueOf = String.valueOf(UnitUtil.e(f, 1, 0));
        float[] fArr = {0.0f, f};
        this.mTrans.pointValuesToPixel(fArr);
        float f2 = fArr[1];
        this.mAxisLabelPaint.setColor(i);
        canvas.drawText(valueOf, e2, f2, this.mAxisLabelPaint);
        this.mAxisLabelPaint.setColor(this.mYAxis.getGridColor());
    }

    private void j() {
        this.k = 140.0f;
        this.l = 90.0f;
        this.n = 90.0f;
        this.q = 60.0f;
    }
}

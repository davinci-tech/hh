package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class pzw extends nnr {
    private boolean j;
    private String k;
    private int l;
    private boolean m;
    private float n;
    private float o;

    private boolean c(float f) {
        return f >= 0.0f && f <= 34.0f;
    }

    public pzw(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
        this.l = BaseApplication.getContext().getResources().getColor(R.color._2131296799_res_0x7f09021f);
        this.j = false;
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
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
            int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
            for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i2 < i; i2++) {
                float[] fArr = {0.0f, (float) Math.floor(this.mYAxis.mEntries[i2])};
                this.mTrans.pointValuesToPixel(fArr);
                path.reset();
                if (i2 == 0) {
                    this.mGridPaint.setPathEffect(null);
                } else {
                    this.mGridPaint.setPathEffect(dashPathEffect);
                }
                canvas.drawPath(dxA_(path, fArr[1]), this.mGridPaint);
                path.reset();
            }
            this.mGridPaint.setColor(this.l);
            if (this.m) {
                float f = this.o;
                if (f > 0.0f) {
                    float f2 = this.n;
                    if (f2 > 0.0f) {
                        dwu_(canvas, f, f2);
                    }
                }
                this.mGridPaint.setPathEffect(null);
                dwv_(canvas, this.o);
                dwv_(canvas, this.n);
            }
            this.mGridPaint.setColor(this.mYAxis.getGridColor());
        }
        if (this.mYAxis.isDrawZeroLineEnabled()) {
            drawZeroLine(canvas);
        }
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        float d = d();
        float abs = (this.mAxisLabelPaint.getFontMetrics().bottom - this.mAxisLabelPaint.getFontMetrics().top) + Math.abs(d);
        float[] fArr2 = {0.0f, this.o};
        this.mTrans.pointValuesToPixel(fArr2);
        float f3 = fArr2[1];
        float[] fArr3 = {0.0f, this.n};
        this.mTrans.pointValuesToPixel(fArr3);
        float f4 = fArr3[1];
        for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i2 < i; i2++) {
            float f5 = fArr[(i2 * 2) + 1];
            if (!this.m || (Math.abs(f3 - f5) >= abs && Math.abs(f4 - f5) >= abs)) {
                canvas.drawText(this.mYAxis.getFormattedLabel(i2), f, f5 + d, this.mAxisLabelPaint);
            }
        }
    }

    private void dwv_(Canvas canvas, float f) {
        if (!c(f)) {
            LogUtil.h("BloodSugarYaxisRender", "The reference blood sugar value is not within the reasonable range.");
            return;
        }
        float[] fArr = {0.0f, f};
        this.mTrans.pointValuesToPixel(fArr);
        this.mRenderGridLinesPath.reset();
        canvas.drawPath(dxA_(this.mRenderGridLinesPath, fArr[1]), this.mGridPaint);
        this.mRenderGridLinesPath.reset();
        dww_(canvas, f);
    }

    private void dwu_(Canvas canvas, float f, float f2) {
        if (!c(f) || !c(f2)) {
            LogUtil.h("BloodSugarYaxisRender", "The reference blood sugar value is not within the reasonable range.");
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
        paint.setColor(this.l);
        if (nrt.a(BaseApplication.getContext())) {
            paint.setAlpha(30);
        } else {
            paint.setAlpha(15);
        }
        canvas.drawPath(path, paint);
        dww_(canvas, this.o);
        if (Math.abs(this.o - this.n) < 1.5f) {
            this.j = true;
        }
        dww_(canvas, this.n);
    }

    private void dww_(Canvas canvas, float f) {
        HwHealthYAxis.HwHealthAxisDependency e;
        if (!this.m || f == -2.1474836E9f || !(this.mYAxis instanceof HwHealthYAxis) || (e = ((HwHealthYAxis) this.mYAxis).e()) == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
            return;
        }
        if (!(this.mAxis instanceof HwHealthYAxis)) {
            LogUtil.h("BloodSugarYaxisRender", "renderAxisLabels mAxis not instanceof HwHealthYAxis");
            return;
        }
        float requiredWidthSpace = ((HwHealthYAxis) this.mAxis).getRequiredWidthSpace(null);
        boolean d = nng.d(this.b);
        if (this.d) {
            d = !d;
        }
        float e2 = e(e, requiredWidthSpace, d);
        String valueOf = String.valueOf(UnitUtil.e(f, 1, 1));
        float[] fArr = {0.0f, f};
        this.mTrans.pointValuesToPixel(fArr);
        float d2 = fArr[1] + d();
        this.mAxisLabelPaint.setColor(this.l);
        LogUtil.a("BloodSugarYaxisRender", "drawManualReferenceLineLabel manualText = ", valueOf);
        if (this.j) {
            this.mAxisLabelPaint.getTextBounds(valueOf, 0, valueOf.length(), new Rect());
            d2 += r1.bottom - r1.top;
            this.j = false;
        }
        canvas.drawText(valueOf, e2, d2, this.mAxisLabelPaint);
        this.mAxisLabelPaint.setColor(this.mYAxis.getGridColor());
    }

    public void e(String str) {
        this.k = str;
        g();
    }

    private void g() {
        boolean z = !"BLOOD_SUGAR_FINGER_TIP".equals(this.k);
        this.m = z;
        if (z) {
            if ("BLOOD_SUGAR_CONTINUE".equals(this.k)) {
                float[] b = deb.b();
                this.o = b[0];
                this.n = b[1];
            } else if ("BLOOD_SUGAR_LIMOSIS".equals(this.k)) {
                this.o = 7.0f;
                this.n = 4.4f;
            } else if ("BLOOD_SUGAR_BEFORE_AFTER_MEAL_DIFFERENCE".equals(this.k)) {
                this.o = 3.3f;
                this.n = -1.0f;
            } else {
                this.o = 10.0f;
                this.n = 4.4f;
            }
        }
    }
}

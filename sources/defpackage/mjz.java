package defpackage;

import android.graphics.Canvas;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class mjz extends XAxisRenderer {
    private float b;
    private double c;

    public mjz(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(viewPortHandler, xAxis, transformer);
        this.c = -3.4028234663852886E38d;
        this.b = -3.4028235E38f;
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    public void drawLabels(Canvas canvas, float f, MPPointF mPPointF) {
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        boolean isCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        int i = this.mXAxis.mEntryCount * 2;
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2 += 2) {
            if (isCenterAxisLabelsEnabled) {
                fArr[i2] = this.mXAxis.mCenteredEntries[i2 / 2];
            } else {
                fArr[i2] = this.mXAxis.mEntries[i2 / 2];
            }
        }
        this.mTrans.pointValuesToPixel(fArr);
        for (int i3 = 0; i3 < i; i3 += 2) {
            float f2 = fArr[i3];
            if (this.mViewPortHandler.isInBoundsX(f2)) {
                String formattedValue = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i3 / 2], this.mXAxis);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled()) {
                    if (i3 == this.mXAxis.mEntryCount - 1 && this.mXAxis.mEntryCount > 1) {
                        float calcTextWidth = Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue);
                        if (calcTextWidth > this.mViewPortHandler.offsetRight() * 2.0f && f2 + calcTextWidth > this.mViewPortHandler.getChartWidth()) {
                            f2 -= calcTextWidth / 2.0f;
                        }
                    } else if (i3 == 0) {
                        f2 += Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue) / 2.0f;
                    } else {
                        LogUtil.c("PLGACHIEVE_HwHealthAchieveReportXAxisRenderer", "drawLabels i is not matching");
                    }
                }
                float f3 = f2;
                double abs = Math.abs(this.c - f3);
                if (abs < Utils.convertDpToPixel(17.0f)) {
                    drawLabel(canvas, formattedValue, f3, f, mPPointF, labelRotationAngle);
                } else if (abs < Utils.convertDpToPixel(34.0f)) {
                    drawLabel(canvas, formattedValue, f3, f + Utils.convertPixelsToDp((float) abs), mPPointF, labelRotationAngle);
                } else {
                    drawLabel(canvas, formattedValue, f3, f + Utils.convertDpToPixel(17.0f), mPPointF, labelRotationAngle);
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    public void drawLabel(Canvas canvas, String str, float f, float f2, MPPointF mPPointF, float f3) {
        int alpha = this.mAxisLabelPaint.getAlpha();
        Typeface typeface = this.mAxisLabelPaint.getTypeface();
        int color = this.mAxisLabelPaint.getColor();
        if (this.b == f) {
            this.mAxisLabelPaint.setColor(BaseApplication.getContext().getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.mAxisLabelPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
            this.mAxisLabelPaint.setAlpha(255);
        }
        Utils.drawXAxisValue(canvas, str, f, f2, this.mAxisLabelPaint, mPPointF, f3);
        this.mAxisLabelPaint.setColor(color);
        this.mAxisLabelPaint.setTypeface(typeface);
        this.mAxisLabelPaint.setAlpha(alpha);
    }

    public void c(double d) {
        this.c = d;
    }

    public void c(float f) {
        this.b = f;
    }
}

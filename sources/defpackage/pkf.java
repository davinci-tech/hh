package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class pkf extends nnr {
    public pkf(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        int d;
        if (this.mYAxis == null || !this.mYAxis.isEnabled()) {
            return;
        }
        if (this.mYAxis.isDrawGridLinesEnabled()) {
            canvas.clipRect(new RectF(0.0f, 0.0f, this.mViewPortHandler.getChartWidth(), this.mViewPortHandler.getChartHeight()));
            this.mGridPaint.setColor(this.mYAxis.getGridColor());
            this.mGridPaint.setStrokeWidth(nrr.e(this.b, 0.5f));
            Path path = this.mRenderGridLinesPath;
            path.reset();
            float[] transformedPositions = getTransformedPositions();
            boolean z = true;
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{nrr.e(this.b, 2.0f), nrr.e(this.b, 1.0f)}, 0.0f);
            for (int i = 0; i < transformedPositions.length; i += 2) {
                int i2 = i + 1;
                if (i2 >= transformedPositions.length || transformedPositions[i2] <= this.f15402a.getContentRect().bottom) {
                    float f = this.mYAxis.mEntries[i / 2];
                    if (this.mYAxis.getAxisMinimum() > 70.0f || (f != 95.0f && f != 85.0f)) {
                        if (z) {
                            this.mGridPaint.setPathEffect(null);
                            z = false;
                        } else {
                            this.mGridPaint.setPathEffect(dashPathEffect);
                        }
                        int gridColor = this.mYAxis.getGridColor();
                        if (((HwHealthYAxis) this.mYAxis).c()) {
                            d = Color.argb(13, Color.red(gridColor), Color.green(gridColor), Color.blue(gridColor));
                        } else {
                            d = ((HwHealthYAxis) this.mYAxis).d();
                        }
                        this.mGridPaint.setColor(d);
                        canvas.drawPath(linePath(path, i, transformedPositions), this.mGridPaint);
                        path.reset();
                        this.mGridPaint.setColor(gridColor);
                    }
                }
            }
        }
        dqu_(canvas);
    }

    private void dqu_(Canvas canvas) {
        if (this.i) {
            this.mGridPaint.setColor(this.h);
            float[] fArr = {0.0f, this.g};
            this.mTrans.pointValuesToPixel(fArr);
            if (this.f != null) {
                canvas.drawPath(dxA_(this.mRenderGridLinesPath, fArr[1]), this.f);
            } else {
                canvas.drawPath(dxA_(this.mRenderGridLinesPath, fArr[1]), this.mGridPaint);
            }
            this.mRenderGridLinesPath.reset();
        }
        if (this.mYAxis.isDrawZeroLineEnabled()) {
            drawZeroLine(canvas);
        }
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        float f;
        if (this.mYAxis == null || this.mAxisLabelPaint == null || !this.mYAxis.isEnabled() || !this.mYAxis.isDrawLabelsEnabled() || this.mAxis.getAxisMaximum() == 2000000.0f) {
            return;
        }
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
        HwHealthYAxis.HwHealthAxisDependency e = ((HwHealthYAxis) this.mYAxis).e();
        if (e == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
            return;
        }
        float requiredWidthSpace = ((HwHealthYAxis) this.mAxis).getRequiredWidthSpace(null);
        boolean d = nng.d(this.b);
        if (this.d) {
            d = !d;
        }
        if ((e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && !d) || (e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && d)) {
            this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
            f = this.mViewPortHandler.offsetLeft() - requiredWidthSpace;
        } else if ((e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && !d) || (e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && d)) {
            this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
            f = this.mViewPortHandler.contentRight() + requiredWidthSpace;
        } else {
            LogUtil.b("R_BloodOxygen_BloodOxygenYAxisRender", "renderAxisLabels dependency err");
            f = 0.0f;
        }
        drawYLabels(canvas, f, getTransformedPositions(), (Utils.calcTextHeight(this.mAxisLabelPaint, "A") / 2.5f) + this.mYAxis.getYOffset());
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        float d = d();
        for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i2 < i; i2++) {
            float f3 = this.mYAxis.mEntries[i2];
            int i3 = (i2 * 2) + 1;
            if (fArr[i3] < this.f15402a.getContentRect().bottom && ((this.mYAxis.getAxisMinimum() > 70.0f || (f3 != 95.0f && f3 != 85.0f)) && (!this.i || Math.abs((-3.4028235E38f) - f3) >= 1.0E-7f))) {
                String str = this.mYAxis.getFormattedLabel(i2) + "%";
                if (LanguageUtil.b(this.b) || LanguageUtil.y(this.b)) {
                    str = UnitUtil.e(CommonUtil.m(this.b, this.mYAxis.getFormattedLabel(i2)), 2, 0);
                }
                if (LanguageUtil.bp(this.b) || LanguageUtil.ac(this.b)) {
                    str = this.mYAxis.getFormattedLabel(i2) + "%";
                }
                if (LanguageUtil.w(this.b)) {
                    str = "%" + this.mYAxis.getFormattedLabel(i2);
                }
                canvas.drawText(str, f, fArr[i3] + d, this.mAxisLabelPaint);
            }
        }
    }
}

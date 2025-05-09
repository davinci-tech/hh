package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
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
public class pyk extends nnr {
    private static final String[] j = {"60", "70", "80", "85", "90", "95", "100"};

    public pyk(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
        this.b = context;
        this.f15402a = hwHealthBaseBarLineChart;
        this.e = new nol();
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    public float[] getTransformedPositions() {
        return super.getTransformedPositions();
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
                    if (this.mYAxis.getAxisMinimum() <= 70.0f) {
                        float f = this.mYAxis.mEntries[i / 2];
                        if (f != 95.0f) {
                            if (f == 85.0f) {
                            }
                        }
                    }
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
        if (this.mYAxis.isDrawZeroLineEnabled()) {
            drawZeroLine(canvas);
        }
    }

    @Override // defpackage.nnr
    protected Path dxA_(Path path, float f) {
        float xOffset;
        float xOffset2;
        HwHealthYAxis axisFirstParty = this.f15402a.getAxisFirstParty();
        HwHealthYAxis axisSecondParty = this.f15402a.getAxisSecondParty();
        float convertDpToPixel = Utils.convertDpToPixel(16.0f);
        float convertDpToPixel2 = Utils.convertDpToPixel(16.0f);
        if (!this.d) {
            xOffset = convertDpToPixel + axisFirstParty.getXOffset();
            xOffset2 = axisSecondParty.getXOffset();
        } else {
            xOffset = convertDpToPixel + axisSecondParty.getXOffset();
            xOffset2 = axisFirstParty.getXOffset();
        }
        float convertDpToPixel3 = Utils.convertDpToPixel(0.0f);
        path.moveTo(xOffset + convertDpToPixel3, f);
        path.lineTo((this.mViewPortHandler.getChartWidth() - (convertDpToPixel2 + xOffset2)) - convertDpToPixel3, f);
        return path;
    }

    @Override // defpackage.nnr
    protected float d() {
        return TypedValue.applyDimension(1, 5.0f, this.b.getResources().getDisplayMetrics()) * (-1.0f);
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        float offsetLeft;
        if (this.mYAxis == null || this.mAxisLabelPaint == null || !this.mYAxis.isDrawLabelsEnabled() || !this.mYAxis.isEnabled()) {
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
        boolean z = false;
        boolean z2 = e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && !d;
        boolean z3 = e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && d;
        boolean z4 = e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && !d;
        if (e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && d) {
            z = true;
        }
        if (z2 || z3) {
            this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
            offsetLeft = this.mViewPortHandler.offsetLeft() - requiredWidthSpace;
        } else if (z4 || z) {
            this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
            offsetLeft = this.mViewPortHandler.contentRight() + requiredWidthSpace;
        } else {
            LogUtil.a("BloodOxygenAxisRenderer", "dependency = ", e, "isDrawReverse = ", Boolean.valueOf(d));
            offsetLeft = 0.0f;
        }
        drawYLabels(canvas, offsetLeft, getTransformedPositions(), (Utils.calcTextHeight(this.mAxisLabelPaint, "A") / 2.5f) + this.mYAxis.getYOffset());
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float f, float f2, boolean z) {
        this.mYAxis.mEntries = pjm.a();
        this.mYAxis.mEntryCount = this.mYAxis.mEntries.length;
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        float d = d();
        int i = 0;
        while (true) {
            String[] strArr = j;
            if (i >= strArr.length) {
                return;
            }
            int i2 = (i * 2) + 1;
            if (fArr[i2] < this.f15402a.getContentRect().bottom) {
                float f3 = this.mYAxis.mEntries[i];
                if (this.mYAxis.getAxisMinimum() > 70.0f || (f3 != 95.0f && f3 != 85.0f)) {
                    String str = strArr[i] + "%";
                    if (LanguageUtil.b(this.b) || LanguageUtil.y(this.b)) {
                        str = UnitUtil.e(CommonUtil.m(this.b, strArr[i]), 2, 0);
                    }
                    if (LanguageUtil.bp(this.b) || LanguageUtil.ac(this.b)) {
                        str = strArr[i] + "%";
                    }
                    if (LanguageUtil.w(this.b)) {
                        str = "%" + strArr[i];
                    }
                    canvas.drawText(str, f, fArr[i2] + d, this.mAxisLabelPaint);
                }
            }
            i++;
        }
    }

    @Override // defpackage.nnr
    public void b(boolean z) {
        this.d = z;
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLine(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawAxisLineEnabled()) {
            this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
            if (((HwHealthYAxis) this.mYAxis).e() == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
                return;
            }
            HwHealthYAxis.HwHealthAxisDependency e = ((HwHealthYAxis) this.mYAxis).e();
            boolean d = nng.d(this.b);
            if (this.d) {
                d = !d;
            }
            boolean z = false;
            boolean z2 = e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && !d;
            boolean z3 = e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && d;
            boolean z4 = e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && !d;
            if (e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && d) {
                z = true;
            }
            if (z2 || z3) {
                canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            } else if (z4 || z) {
                canvas.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            } else {
                LogUtil.a("BloodOxygenAxisRenderer", "dependency = ", e, ",isDrawReverse = ", Boolean.valueOf(d));
            }
        }
    }

    @Override // defpackage.nnr
    public float b() {
        return this.g;
    }

    @Override // defpackage.nnr
    public void c() {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            if (((HwHealthYAxis) this.mYAxis).e() == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
                return;
            }
            Rect rect = new Rect();
            String formattedValue = this.mYAxis.getValueFormatter().getFormattedValue(this.g, this.mYAxis);
            LogUtil.a("BloodOxygenAxisRenderer", "considerGridLinesAndManualRefLine() manualText = ", formattedValue);
            this.mAxisLabelPaint.getTextBounds(formattedValue, 0, formattedValue.length(), rect);
            this.mTrans.pointValuesToPixel(new float[]{0.0f, this.g});
        }
    }
}

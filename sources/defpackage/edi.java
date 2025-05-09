package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.TypedValue;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;

/* loaded from: classes3.dex */
public class edi extends nnr {
    private static boolean j = true;
    private boolean k;
    private String[] l;
    private Paint m;
    private int o;

    public edi(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
        this.k = false;
        this.b = context;
        this.f15402a = hwHealthBaseBarLineChart;
        this.e = new nol();
    }

    public static void c(boolean z) {
        j = z;
    }

    private boolean g() {
        return j;
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        if (this.mYAxis.isEnabled()) {
            if (this.mYAxis.isDrawGridLinesEnabled()) {
                canvas.clipRect(new RectF(0.0f, 0.0f, this.mViewPortHandler.getChartWidth(), this.mViewPortHandler.getChartHeight()));
                this.mGridPaint.setColor(this.mYAxis.getGridColor());
                this.mGridPaint.setStrokeWidth(this.mYAxis.getGridLineWidth());
                this.mGridPaint.setPathEffect(this.mYAxis.getGridDashPathEffect());
                Path path = this.mRenderGridLinesPath;
                path.reset();
                agh_(canvas, path);
                canvas.restoreToCount(canvas.save());
            }
            if (this.mYAxis.isDrawZeroLineEnabled()) {
                drawZeroLine(canvas);
            }
        }
    }

    private void agh_(Canvas canvas, Path path) {
        float xOffset;
        float f;
        if (this.k) {
            this.mGridPaint.setColor(this.h);
            float[] fArr = {0.0f, this.o};
            this.mTrans.pointValuesToPixel(fArr);
            if (this.m != null) {
                canvas.drawPath(dxA_(path, fArr[1]), this.m);
            } else {
                canvas.drawPath(dxA_(path, fArr[1]), this.mGridPaint);
            }
            path.reset();
            if (g()) {
                int color = this.b.getResources().getColor(R.color._2131297796_res_0x7f090604);
                this.mGridPaint.setPathEffect(null);
                this.mGridPaint.setColor(color);
                HwHealthYAxis axisFirstParty = this.f15402a.getAxisFirstParty();
                HwHealthYAxis axisSecondParty = this.f15402a.getAxisSecondParty();
                if (!this.d) {
                    f = axisFirstParty.getXOffset() + 0.0f;
                    xOffset = axisSecondParty.getXOffset() + 0.0f;
                } else {
                    float xOffset2 = axisSecondParty.getXOffset() + 0.0f;
                    xOffset = axisFirstParty.getXOffset() + 0.0f;
                    f = xOffset2;
                }
                float convertDpToPixel = Utils.convertDpToPixel(16.0f);
                Path path2 = new Path();
                path2.moveTo(f + convertDpToPixel, this.f15402a.getViewPortHandler().contentBottom());
                path2.lineTo((this.mViewPortHandler.getChartWidth() - xOffset) - convertDpToPixel, this.f15402a.getViewPortHandler().contentBottom());
                canvas.drawPath(path2, this.mGridPaint);
                path.reset();
                this.mGridPaint.setPathEffect(this.mYAxis.getGridDashPathEffect());
                this.mGridPaint.setColor(this.mYAxis.getGridColor());
            }
        }
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer
    public Path linePath(Path path, int i, float[] fArr) {
        return dxA_(path, fArr[i + 1]);
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
        float contentLeft;
        float convertDpToPixel;
        if (this.mAxis.getAxisMaximum() != 2000000.0f && this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            HwHealthYAxis.HwHealthAxisDependency e = ((HwHealthYAxis) this.mYAxis).e();
            if (e == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
                return;
            }
            float requiredWidthSpace = ((HwHealthYAxis) this.mAxis).getRequiredWidthSpace(null);
            boolean d = nng.d(this.b);
            boolean z = false;
            boolean z2 = d || this.d;
            boolean z3 = e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && !z2;
            boolean z4 = e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && z2;
            boolean z5 = e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && !z2;
            if (e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && z2) {
                z = true;
            }
            if (z3 || z4) {
                this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
                offsetLeft = (this.mViewPortHandler.offsetLeft() - requiredWidthSpace) + Utils.convertDpToPixel(16.0f);
            } else if (z5 || z) {
                if (!d) {
                    this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
                    contentLeft = this.mViewPortHandler.contentRight() + requiredWidthSpace;
                    convertDpToPixel = Utils.convertDpToPixel(16.0f);
                } else {
                    this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
                    contentLeft = this.mViewPortHandler.contentLeft();
                    convertDpToPixel = Utils.convertDpToPixel(10.0f);
                }
                offsetLeft = contentLeft - convertDpToPixel;
            } else {
                LogUtil.a("SleepYAxisRenderer", "dependency = ", e, "isDrawReverse = ", Boolean.valueOf(z2));
                offsetLeft = 0.0f;
            }
            agg_(canvas, offsetLeft);
        }
    }

    private void agg_(Canvas canvas, float f) {
        String formattedValue;
        if (!this.k || this.o == Integer.MIN_VALUE) {
            return;
        }
        Rect rect = new Rect();
        String[] strArr = this.l;
        if (strArr != null && strArr.length != 0) {
            formattedValue = TextUtils.isEmpty(strArr[0]) ? "" : this.l[0];
        } else {
            formattedValue = this.mYAxis.getValueFormatter().getFormattedValue(this.o, this.mYAxis);
        }
        this.mAxisLabelPaint.getTextBounds(formattedValue, 0, formattedValue.length(), rect);
        float[] fArr = {0.0f, this.o};
        this.mTrans.pointValuesToPixel(fArr);
        float d = d();
        float f2 = fArr[1];
        if (this.m != null) {
            this.mAxisLabelPaint.setColor(this.m.getColor());
        } else {
            String[] strArr2 = this.l;
            if (strArr2 != null && strArr2.length != 0) {
                this.mAxisLabelPaint.setColor(this.b.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                this.mAxisLabelPaint.setTextSize(d(10.0f));
            } else {
                this.mAxisLabelPaint.setColor(this.h);
            }
        }
        Paint.FontMetrics fontMetrics = this.mAxisLabelPaint.getFontMetrics();
        Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        nmy nmyVar = (nmy) this.f15402a.getData().getDataSets().get(0);
        if (this.o != 0) {
            ObserverManagerUtil.c("SLEEP_AVERAGE_ICON_OFFSET", Float.valueOf(fArr[1] - 0.5f), nmyVar.a());
        } else {
            ObserverManagerUtil.c("SLEEP_AVERAGE_ICON_OFFSET", Float.valueOf(0.0f), nmyVar.a());
        }
        canvas.drawText(formattedValue, f, f2 + d, this.mAxisLabelPaint);
    }

    private int d(float f) {
        return (int) ((this.b.getResources().getDisplayMetrics().density * f) + ((f >= 0.0f ? 1 : -1) * 0.5f));
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
                LogUtil.a("SleepYAxisRenderer", "dependency = ", e, ",isDrawReverse = ", Boolean.valueOf(d));
            }
        }
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float f, float f2, boolean z) {
        if (Math.abs(f2 - Float.MAX_VALUE) < 1.0E-7f || Math.abs(Float.MAX_VALUE + f) < 1.0E-7f) {
            super.computeAxis(f, f2, z);
            return;
        }
        HwHealthYAxis.HwHealthAxisDependency e = ((HwHealthYAxis) this.mAxis).e();
        HwHealthBaseBarLineData hwHealthBaseBarLineData = (HwHealthBaseBarLineData) this.f15402a.getData();
        if (e == null || hwHealthBaseBarLineData == null) {
            LogUtil.b("R_Sleep_SleepYAxisRenderer", "computeAxis dependency null or chart chartData null, return");
            return;
        }
        IHwHealthBarLineDataSet dataSet = hwHealthBaseBarLineData.getDataSet(e);
        if (dataSet == null) {
            super.computeAxis(f, f2, z);
            return;
        }
        this.mAxis.setLabelCount(dataSet.getLabelCount(), true);
        super.computeAxis(f, f2, z);
    }

    public void e(int i, int i2, String... strArr) {
        this.k = true;
        this.o = i;
        this.h = i2;
        this.l = strArr;
    }

    @Override // defpackage.nnr
    public void dxz_(int i, Paint paint) {
        this.k = true;
        this.o = i;
        this.m = paint;
    }

    @Override // defpackage.nnr
    public void a() {
        this.k = false;
        this.m = null;
    }

    @Override // defpackage.nnr
    public boolean f() {
        return this.k;
    }

    @Override // defpackage.nnr
    public float b() {
        return this.o;
    }

    @Override // defpackage.nnr
    public void c() {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled() && this.mAxis.getAxisMaximum() != 2000000.0f && this.k) {
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            if (((HwHealthYAxis) this.mYAxis).e() == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
                return;
            }
            Rect rect = new Rect();
            String formattedValue = this.mYAxis.getValueFormatter().getFormattedValue(this.o, this.mYAxis);
            this.mAxisLabelPaint.getTextBounds(formattedValue, 0, formattedValue.length(), rect);
            this.mTrans.pointValuesToPixel(new float[]{0.0f, this.o});
        }
    }

    @Override // defpackage.nnr
    public float e_() {
        return this.o;
    }
}

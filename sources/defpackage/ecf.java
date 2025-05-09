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
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.model.HwHealthLineEntry;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class ecf extends nnr {
    private boolean j;

    public static int a(double d) {
        if ((d * 100.0d) % 100.0d == 0.0d) {
            return (int) d;
        }
        return 100;
    }

    private boolean a(boolean z, int i) {
        if (i == 0 || i == Integer.MAX_VALUE) {
            return z;
        }
        return true;
    }

    private int b(int i, int i2) {
        return i2 > i ? i2 : i;
    }

    private boolean b(boolean z, int i) {
        if (i != 0) {
            return true;
        }
        return z;
    }

    private int e(int i, int i2) {
        return i2 < i ? i2 : i;
    }

    public ecf(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
        ObserverManagerUtil.d(new Observer() { // from class: ecf.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (koq.e(objArr, 0)) {
                    Object obj = objArr[0];
                    if (obj instanceof Integer) {
                        if (((Integer) obj).intValue() == 0) {
                            ecf.this.j = true;
                        } else {
                            ecf.this.j = false;
                        }
                    }
                }
            }
        }, "BLOOD_AND_ALTITUDE_DATA");
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
            float f = (this.f15402a.getContentRect().bottom - this.f15402a.getContentRect().top) / 4.0f;
            boolean z = true;
            float[] fArr = {0.0f, this.f15402a.getContentRect().bottom, 0.0f, this.f15402a.getContentRect().bottom - f, 0.0f, this.f15402a.getContentRect().bottom - (f * 2.0f), 0.0f, this.f15402a.getContentRect().bottom - (3.0f * f), 0.0f, this.f15402a.getContentRect().bottom - (f * 4.0f)};
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{nrr.e(this.b, 2.0f), nrr.e(this.b, 1.0f)}, 0.0f);
            for (int i = 0; i < 10; i += 2) {
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
                canvas.drawPath(linePath(path, i, fArr), this.mGridPaint);
                path.reset();
                this.mGridPaint.setColor(gridColor);
            }
        }
        aeF_(canvas);
    }

    private void aeF_(Canvas canvas) {
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
        boolean z = !nng.d(this.b);
        if ((e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && !z) || (e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && z)) {
            this.mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
            f = this.mViewPortHandler.offsetLeft() - requiredWidthSpace;
        } else if ((e == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY && !z) || (e == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY && z)) {
            this.mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
            f = this.mViewPortHandler.contentRight() + requiredWidthSpace;
        } else {
            LogUtil.b("R_BloodOxygen_BloodOxygenYAxisRender", "renderAxisLabels dependency err");
            f = 0.0f;
        }
        float f2 = (this.f15402a.getContentRect().bottom - this.f15402a.getContentRect().top) / 4.0f;
        drawYLabels(canvas, f, new float[]{0.0f, this.f15402a.getContentRect().bottom, 0.0f, this.f15402a.getContentRect().bottom - f2, 0.0f, this.f15402a.getContentRect().bottom - (2.0f * f2), 0.0f, this.f15402a.getContentRect().bottom - (3.0f * f2), 0.0f, this.f15402a.getContentRect().bottom - (f2 * 4.0f)}, (Utils.calcTextHeight(this.mAxisLabelPaint, "A") / 2.5f) + this.mYAxis.getYOffset());
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        float convertDpToPixel;
        List<HwHealthBaseEntry> acquireEntryVals = ((HwHealthBaseBarLineDataSet) this.f15402a.getData().getDataSets().get(0)).acquireEntryVals();
        if (acquireEntryVals == null) {
            LogUtil.h("BloodOxygenYAxisRender", "getRemindSet baseEntries is null or is horizontal");
            return;
        }
        LogUtil.a("BloodOxygenYAxisRender", "drawLinear baseEntries.size = ", Integer.valueOf(acquireEntryVals.size()));
        String[] b = b(acquireEntryVals);
        if (b == null) {
            return;
        }
        float d = d();
        float f3 = 0.0f;
        float f4 = fArr.length >= 3 ? fArr[1] - fArr[3] : 0.0f;
        float requiredWidthSpace = ((HwHealthYAxis) this.mAxis).getRequiredWidthSpace(null);
        if (nng.d(this.b)) {
            convertDpToPixel = 0.0f;
        } else {
            convertDpToPixel = Utils.convertDpToPixel(2.0f);
            f3 = requiredWidthSpace;
        }
        for (int i = 0; i < 3; i++) {
            canvas.drawText(b[i], this.mViewPortHandler.contentRight() + f3 + convertDpToPixel, (fArr[1] + d) - (i * f4), this.mAxisLabelPaint);
        }
        int i2 = 2;
        while (i2 < 5) {
            int i3 = i2 + 1;
            canvas.drawText(b[i3], this.mViewPortHandler.offsetLeft() - convertDpToPixel, (fArr[1] + d) - (i2 * f4), this.mAxisLabelPaint);
            i2 = i3;
        }
    }

    private String[] b(List<HwHealthBaseEntry> list) {
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        boolean z = false;
        boolean z2 = false;
        for (HwHealthBaseEntry hwHealthBaseEntry : list) {
            if (hwHealthBaseEntry instanceof HwHealthLineEntry) {
                HwHealthLineEntry hwHealthLineEntry = (HwHealthLineEntry) hwHealthBaseEntry;
                if (hwHealthLineEntry.acquireModel() instanceof ecm) {
                    int y = (int) hwHealthLineEntry.getY();
                    z = b(z, y);
                    int d = ((ecm) hwHealthLineEntry.acquireModel()).d();
                    z2 = a(z2, d);
                    if (d != Integer.MAX_VALUE) {
                        int b = b(i, d);
                        i3 = e(i3, d);
                        i = b;
                    }
                    if (y != 0) {
                        int b2 = b(i2, y);
                        i4 = e(i4, y);
                        i2 = b2;
                    }
                }
            }
        }
        return d(i, i3, i2, i4, z, z2);
    }

    private String[] d(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int c = c(i2);
        int b = b(i);
        int i5 = (c + b) / 2;
        int e = e(i4);
        int i6 = i(i3);
        int i7 = (i3 == 100 || i6 > 100) ? 100 : i6;
        return b(z, z2, c, b, i5, e, i7, (e + i7) / 2);
    }

    private int i(int i) {
        int i2 = i % 4;
        int i3 = i + 4;
        return i2 == 0 ? i3 : i3 - i2;
    }

    private int e(int i) {
        int i2 = i % 4;
        return i2 == 0 ? i - 4 : i - i2;
    }

    private int b(int i) {
        int i2 = i % 50;
        int i3 = i + 50;
        return i2 == 0 ? i3 : i3 - i2;
    }

    private int c(int i) {
        int i2 = i % 50;
        return i2 == 0 ? i - 50 : i - i2;
    }

    private String[] b(boolean z, boolean z2, int i, int i2, int i3, int i4, int i5, int i6) {
        String[] strArr = {c(i4, 2, null), c(i6, 2, null), c(i5, 2, null), a(i), a(i3), a(i2)};
        if (!z || this.j) {
            strArr[0] = c(80, 2, null);
            strArr[1] = c(90, 2, null);
            strArr[2] = c(100, 2, null);
            strArr[3] = a(0);
            strArr[4] = a(100);
            strArr[5] = a(200);
        }
        if (!z2) {
            strArr[3] = a(0);
            strArr[4] = a(100);
            strArr[5] = a(200);
        }
        return strArr;
    }

    private String c(int i, int i2, String str) {
        if (str == null) {
            return UnitUtil.e(i, i2, 0);
        }
        if (LanguageUtil.bc(this.b)) {
            return str + UnitUtil.e(i, i2, 0);
        }
        return UnitUtil.e(i, i2, 0) + str;
    }

    public static String a(int i) {
        if (i == Integer.MIN_VALUE) {
            return String.valueOf(Integer.MIN_VALUE);
        }
        if (UnitUtil.h()) {
            int i2 = UnitUtil.j(i)[0];
            String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903217_res_0x7f0300b1, a(i2), String.valueOf(i2));
            return (LanguageUtil.m(BaseApplication.getContext()) && quantityString.contains(" ")) ? quantityString.replaceAll(" ", "") : quantityString;
        }
        String e = UnitUtil.e(i, 1, 0);
        StringBuilder sb = new StringBuilder();
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            sb.append(string);
            sb.append(e);
        } else {
            sb.append(e);
            sb.append(string);
        }
        return sb.toString();
    }
}

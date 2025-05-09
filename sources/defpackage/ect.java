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
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.model.HwHealthLineEntry;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class ect extends nnr {
    private boolean j;
    private final DataInfos k;
    private final int m;

    public ect(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, DataInfos dataInfos) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
        this.k = dataInfos;
        this.m = nrr.e(BaseApplication.getContext(), h());
        ObserverManagerUtil.d(new Observer() { // from class: ect.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (koq.e(objArr, 0)) {
                    Object obj = objArr[0];
                    if (obj instanceof Integer) {
                        if (((Integer) obj).intValue() == 0) {
                            ect.this.j = true;
                        } else {
                            ect.this.j = false;
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
            float f = (this.f15402a.getContentRect().bottom - (this.f15402a.getContentRect().top + this.m)) / 4.0f;
            boolean z = true;
            float[] fArr = {0.0f, this.f15402a.getContentRect().bottom, 0.0f, this.f15402a.getContentRect().bottom - f, 0.0f, this.f15402a.getContentRect().bottom - (f * 2.0f), 0.0f, this.f15402a.getContentRect().bottom - (3.0f * f), 0.0f, this.f15402a.getContentRect().bottom - (f * 4.0f)};
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{nrr.e(this.b, 2.0f), nrr.e(this.b, 1.0f)}, 0.0f);
            for (int i = 0; i < 10; i += 2) {
                int i2 = i + 1;
                if (i2 >= 10 || fArr[i2] <= this.f15402a.getContentRect().bottom) {
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
        }
        afh_(canvas);
    }

    private void afh_(Canvas canvas) {
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

    private float h() {
        return this.k == DataInfos.BloodOxygenDayHorizontal ? 1.0f : 2.0f;
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
        float f2 = (this.f15402a.getContentRect().bottom - (this.f15402a.getContentRect().top + this.m)) / 4.0f;
        drawYLabels(canvas, f, new float[]{0.0f, this.f15402a.getContentRect().bottom, 0.0f, this.f15402a.getContentRect().bottom - f2, 0.0f, this.f15402a.getContentRect().bottom - (2.0f * f2), 0.0f, this.f15402a.getContentRect().bottom - (3.0f * f2), 0.0f, this.f15402a.getContentRect().bottom - (f2 * 4.0f)}, (Utils.calcTextHeight(this.mAxisLabelPaint, "A") / 2.5f) + this.mYAxis.getYOffset());
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        float d = d();
        List<HwHealthBaseEntry> acquireEntryVals = ((HwHealthBaseBarLineDataSet) this.f15402a.getData().getDataSets().get(0)).acquireEntryVals();
        if (acquireEntryVals == null) {
            LogUtil.h("BloodOxygenYAxisRender", "getRemindSet baseEntries is null or is horizontal");
            return;
        }
        LogUtil.a("BloodOxygenYAxisRender", "drawLinear baseEntries.size = ", Integer.valueOf(acquireEntryVals.size()));
        String[] a2 = a(acquireEntryVals);
        if (a2 == null) {
            return;
        }
        float convertDpToPixel = Utils.convertDpToPixel(2.0f);
        for (int i = 0; i < 5; i++) {
            String str = a2[i];
            if (LanguageUtil.b(this.b) || LanguageUtil.y(this.b)) {
                str = UnitUtil.e(CommonUtil.m(this.b, this.mYAxis.getFormattedLabel(i)), 2, 0);
            }
            if (LanguageUtil.bp(this.b) || LanguageUtil.ac(this.b)) {
                str = this.mYAxis.getFormattedLabel(i) + "%";
            }
            if (LanguageUtil.w(this.b)) {
                str = "%" + this.mYAxis.getFormattedLabel(i);
            }
            canvas.drawText(str, f + convertDpToPixel, fArr[(i * 2) + 1] + d, this.mAxisLabelPaint);
        }
    }

    private String[] a(List<HwHealthBaseEntry> list) {
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        boolean z = false;
        for (HwHealthBaseEntry hwHealthBaseEntry : list) {
            if (hwHealthBaseEntry instanceof HwHealthLineEntry) {
                HwHealthLineEntry hwHealthLineEntry = (HwHealthLineEntry) hwHealthBaseEntry;
                if (hwHealthLineEntry.acquireModel() instanceof ecm) {
                    int y = (int) hwHealthLineEntry.getY();
                    if (y != 0) {
                        z = true;
                    }
                    if (y != 0) {
                        if (y > i) {
                            i = y;
                        }
                        if (y < i2) {
                            i2 = y;
                        }
                    }
                }
            }
        }
        int i3 = i2 % 4;
        int i4 = i3 == 0 ? i2 - 4 : i2 - i3;
        int i5 = i % 4;
        int i6 = i5 == 0 ? i + 4 : (i + 4) - i5;
        if (i == 100 || i6 > 100) {
            i6 = 100;
        }
        int i7 = (i4 + i6) / 2;
        int i8 = (i7 + i6) / 2;
        String[] strArr = {i4 + "%", ((i7 + i4) / 2) + "%", i7 + "%", i8 + "%", i6 + "%"};
        if (!z || this.j) {
            strArr[0] = "60%";
            strArr[1] = "70%";
            strArr[2] = "80%";
            strArr[3] = "90%";
            strArr[4] = "100%";
        }
        return strArr;
    }
}

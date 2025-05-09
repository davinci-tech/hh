package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import com.huawei.health.knit.section.chart.SleepBarChartRenderer;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import defpackage.edh;
import defpackage.edi;

/* loaded from: classes3.dex */
public class CoreSleepBarChartView extends HwHealthBarChart {

    /* renamed from: a, reason: collision with root package name */
    private OnBarChartViewDataChangedListener f2600a;

    public interface OnBarChartViewDataChangedListener {
        void onBarChartViewDataChangedListener(float f, float f2);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public boolean isDrawYaxisLabelsOnHighLight() {
        return true;
    }

    public CoreSleepBarChartView(Context context) {
        super(context);
        e();
    }

    private void e() {
        this.mRenderer = new SleepBarChartRenderer(this, this, this.mAnimator, this.mViewPortHandler);
        this.mAxisFirstParty = new edh(this, HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setAxisMinimum(0.0f);
        this.mAxisSecondParty = new edh(this, HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisThirdParty = new edh(this, HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY);
        this.mAxisRendererFirstParty = new edi(getContext(), this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new edi(getContext(), this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new edi(getContext(), this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
        ((edh) this.mAxisFirstParty).setTextColor(Color.argb(255, 255, 255, 255));
        this.mAxisThirdParty.setDrawGridLines(false);
        this.mAxisThirdParty.setDrawLabels(false);
        this.mAxisThirdParty.setEnabled(false);
        this.mAxisThirdParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setTextColor(Color.argb(127, 0, 0, 0));
        this.mAxisSecondParty.setTextColor(Color.argb(127, 0, 0, 0));
        this.mAxisThirdParty.setTextColor(Color.argb(127, 0, 0, 0));
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
        b(true, false);
        ((SleepBarChartRenderer) this.mRenderer).c(new SleepBarChartRenderer.OnSleepTimeChangedListener() { // from class: com.huawei.health.knit.section.chart.CoreSleepBarChartView.4
            @Override // com.huawei.health.knit.section.chart.SleepBarChartRenderer.OnSleepTimeChangedListener
            public void onSleepTimeChangedListener(float f, float f2) {
                if (CoreSleepBarChartView.this.f2600a != null) {
                    CoreSleepBarChartView.this.f2600a.onBarChartViewDataChangedListener(f, f2);
                }
            }
        });
    }

    public void setOnBarChartViewDataChangedListener(OnBarChartViewDataChangedListener onBarChartViewDataChangedListener) {
        this.f2600a = onBarChartViewDataChangedListener;
    }

    public void c(int i, int i2, String... strArr) {
        ((edi) this.mAxisRendererFirstParty).e(i, i2, strArr);
    }

    public void b(boolean z) {
        edi.c(z);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void clipDataArea(Canvas canvas) {
        RectF rectF = new RectF(this.mViewPortHandler.getContentRect());
        rectF.bottom += this.mXAxis.getYOffset();
        rectF.top = 0.0f;
        rectF.right += rectF.left;
        rectF.left = 0.0f;
        canvas.clipRect(rectF);
    }
}

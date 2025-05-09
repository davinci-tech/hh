package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.huawei.health.knit.section.chart.BarChartRender;
import com.huawei.health.knit.section.chart.BloodOxygenBarChartRenderer;
import com.huawei.health.knit.section.chart.BloodOxygenBarChartView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import defpackage.ecj;

/* loaded from: classes3.dex */
public class BloodOxygenBarChartView extends HwHealthBarChart {

    /* renamed from: a, reason: collision with root package name */
    private OnDataChangedListener f2599a;
    private OnMostChangedListener c;

    public interface OnDataChangedListener {
        void onDataChangedListener(float f, float f2);
    }

    public interface OnMostChangedListener {
        void onMostChangedListener(float f, float f2);
    }

    public BloodOxygenBarChartView(Context context) {
        super(context);
        e();
    }

    private void e() {
        LogUtil.b("BloodOxygenBarChartView", "BloodOxygenBarChartView initStyle!");
        this.mRenderer = new BloodOxygenBarChartRenderer(this, this, this.mAnimator, this.mViewPortHandler);
        this.mAxisRendererFirstParty = new ecj(getContext(), this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new ecj(getContext(), this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
        ((BloodOxygenBarChartRenderer) this.mRenderer).b(new BarChartRender.OnTimeChangedListener() { // from class: ecg
            @Override // com.huawei.health.knit.section.chart.BarChartRender.OnTimeChangedListener
            public final void onTimeChangedListener(float f, float f2) {
                BloodOxygenBarChartView.this.c(f, f2);
            }
        });
        ((BloodOxygenBarChartRenderer) this.mRenderer).e(new BloodOxygenBarChartRenderer.OnMostValueChangedListener() { // from class: ecn
            @Override // com.huawei.health.knit.section.chart.BloodOxygenBarChartRenderer.OnMostValueChangedListener
            public final void onMostValueChangedListener(float f, float f2) {
                BloodOxygenBarChartView.this.e(f, f2);
            }
        });
    }

    public /* synthetic */ void c(float f, float f2) {
        OnDataChangedListener onDataChangedListener = this.f2599a;
        if (onDataChangedListener != null) {
            onDataChangedListener.onDataChangedListener(f, f2);
        }
    }

    public /* synthetic */ void e(float f, float f2) {
        OnMostChangedListener onMostChangedListener = this.c;
        if (onMostChangedListener != null) {
            onMostChangedListener.onMostChangedListener(f, f2);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void clipDataArea(Canvas canvas) {
        RectF rectF = new RectF(this.mViewPortHandler.getContentRect());
        rectF.bottom += this.mXAxis.getYOffset();
        rectF.top -= this.mXAxis.getYOffset();
        canvas.clipRect(rectF);
    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.f2599a = onDataChangedListener;
    }

    public void setOnMostChangedListener(OnMostChangedListener onMostChangedListener) {
        this.c = onMostChangedListener;
    }
}

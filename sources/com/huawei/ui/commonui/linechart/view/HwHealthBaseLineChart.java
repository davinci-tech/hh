package com.huawei.ui.commonui.linechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.common.UnixChartType;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;
import defpackage.nng;
import defpackage.nnv;
import defpackage.nov;
import defpackage.now;

/* loaded from: classes6.dex */
public abstract class HwHealthBaseLineChart extends HwHealthBaseScrollBarLineChart<now> implements HwHealthLineDataProvider {
    public HwHealthBaseLineChart(Context context) {
        super(context);
    }

    public HwHealthBaseLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HwHealthBaseLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        this.mRenderer = new nov(this, this.mAnimator, this.mViewPortHandler);
        setHighlighter(new nnv(this));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public void enableScrollMode(UnixChartType unixChartType, DynamicBorderSupportable dynamicBorderSupportable) {
        if (this.mChartShowMode != HwHealthBaseScrollBarLineChart.ChartShowMode.NORMAL) {
            return;
        }
        super.enableScrollMode(unixChartType, dynamicBorderSupportable);
        this.mScrollAdapter.acquireXAxisValueFormatter().enableMarkerViewShowRange(false);
    }

    public void setAvoidFirstLastClipping(boolean z) {
        this.mXAxis.setAvoidFirstLastClipping(z);
    }

    public void setScaletMaxima(float f) {
        this.mViewPortHandler.setMaximumScaleX(f);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public void moveViewToX(float f) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, f, 0.0f, getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY), this));
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void drawDescription(Canvas canvas) {
        float convertDpToPixel;
        if (this.mDescription == null || !this.mDescription.isEnabled()) {
            return;
        }
        this.mDescPaint.setTypeface(this.mDescription.getTypeface());
        this.mDescPaint.setTextSize(this.mDescription.getTextSize());
        this.mDescPaint.setColor(this.mDescription.getTextColor());
        this.mDescPaint.setTextAlign(this.mDescription.getTextAlign());
        if (!nng.d(getContext())) {
            this.mDescPaint.setTextAlign(Paint.Align.RIGHT);
            convertDpToPixel = getWidth() - Utils.convertDpToPixel(1.0f);
        } else {
            this.mDescPaint.setTextAlign(Paint.Align.LEFT);
            convertDpToPixel = Utils.convertDpToPixel(1.0f);
        }
        canvas.drawText(this.mDescription.getText(), convertDpToPixel, this.mChartAnchor.cBm_().top + (-this.mDescPaint.getFontMetrics().top), this.mDescPaint);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.Chart, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        if (this.mRenderer instanceof LineChartRenderer) {
            ((LineChartRenderer) this.mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider
    public now getLineData() {
        return (now) this.mData;
    }
}

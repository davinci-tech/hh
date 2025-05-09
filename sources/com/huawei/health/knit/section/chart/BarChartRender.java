package com.huawei.health.knit.section.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import defpackage.nmz;
import defpackage.nnb;
import defpackage.not;

/* loaded from: classes3.dex */
public class BarChartRender extends nnb {

    public interface OnTimeChangedListener {
        void onTimeChangedListener(float f, float f2);
    }

    public BarChartRender(HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
    }

    public void c(HwHealthBarChart hwHealthBarChart, HwHealthBarDataProvider hwHealthBarDataProvider) {
        if (hwHealthBarChart == null || hwHealthBarDataProvider == null) {
            return;
        }
        this.e = hwHealthBarDataProvider;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        this.j = new Paint(1);
        this.j.setStyle(Paint.Style.FILL);
        this.b = new Paint(1);
        this.b.setStyle(Paint.Style.STROKE);
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.b("BarChartRender", "initBuffers() barData is null!");
            return;
        }
        this.f15395a = new not[barData.getDataSetCount()];
        for (int i = 0; i < this.f15395a.length; i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(i);
            this.f15395a[i] = new not(iHwHealthBarDataSet.getEntryCount() * 4 * (iHwHealthBarDataSet.isStacked() ? iHwHealthBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iHwHealthBarDataSet.isStacked());
        }
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.b("BarChartRender", "drawData() barData is null!");
            return;
        }
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(i);
            if (iHwHealthBarDataSet.isVisible()) {
                dGJ_(canvas, iHwHealthBarDataSet, i);
            }
        }
    }

    protected boolean d(float f) {
        float axisMinimum = (!(this.e instanceof HwHealthBarChart) || ((HwHealthBarChart) this.e).getAxisFirstParty() == null) ? 0.0f : ((HwHealthBarChart) this.e).getAxisFirstParty().getAxisMinimum();
        LogUtil.c("BarChartRender", "isOutOfChart min = ", Float.valueOf(f), " axisMin = ", Float.valueOf(axisMinimum));
        return f < axisMinimum;
    }
}

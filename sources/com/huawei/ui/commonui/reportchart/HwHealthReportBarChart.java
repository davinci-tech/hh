package com.huawei.ui.commonui.reportchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.BarHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.ui.commonui.R$color;
import defpackage.nqa;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class HwHealthReportBarChart extends BarChart {

    /* renamed from: a, reason: collision with root package name */
    private RectF f8927a;
    private int b;
    private nqa d;

    public HwHealthReportBarChart(Context context) {
        super(context);
        this.f8927a = new RectF();
        d();
    }

    public HwHealthReportBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8927a = new RectF();
        d();
    }

    public HwHealthReportBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8927a = new RectF();
        d();
    }

    public void setCustomValue(Context context, ArrayList<String> arrayList, int i, float f, float f2, float f3, boolean z) {
        boolean z2;
        List entriesForXValue;
        this.b = i;
        if (getData() != null) {
            z2 = false;
            if (((BarData) getData()).getDataSets().get(0) != null) {
                IBarDataSet iBarDataSet = (IBarDataSet) ((BarData) getData()).getDataSets().get(0);
                if (LanguageUtil.bc(getContext())) {
                    entriesForXValue = iBarDataSet.getEntriesForXValue(iBarDataSet.getXMax());
                } else {
                    entriesForXValue = iBarDataSet.getEntriesForXValue(iBarDataSet.getXMin());
                }
                if (entriesForXValue != null && entriesForXValue.size() > 0) {
                    for (int i2 = 0; i2 < entriesForXValue.size(); i2++) {
                        if (((BarEntry) entriesForXValue.get(i2)).getY() != f2) {
                            break;
                        }
                    }
                }
            }
        }
        z2 = true;
        this.d.c(z2);
        this.d.e(this.b, f, f2, Utils.convertDpToPixel(f3), z);
        if (LanguageUtil.bc(context)) {
            Collections.reverse(arrayList);
        }
        if (arrayList != null) {
            setXAxisValueFormatter(arrayList);
            getXAxis().setLabelCount(arrayList.size());
        }
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void calculateOffsets() {
        float convertDpToPixel = Utils.convertDpToPixel(10.0f);
        calculateLegendOffsets(this.f8927a);
        float f = this.f8927a.left + 0.0f;
        float f2 = convertDpToPixel + this.f8927a.top;
        float f3 = this.f8927a.right + 0.0f;
        float f4 = this.f8927a.bottom + 0.0f;
        if (this.mAxisLeft.needsOffset()) {
            f += this.mAxisLeft.getRequiredWidthSpace(this.mAxisRendererLeft.getPaintAxisLabels());
        }
        if (this.mAxisRight.needsOffset()) {
            f3 += this.mAxisRight.getRequiredWidthSpace(this.mAxisRendererRight.getPaintAxisLabels());
        }
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float yOffset = this.mXAxis.mLabelHeight + this.mXAxis.getYOffset();
            if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                f4 += yOffset;
            } else {
                if (this.mXAxis.getPosition() != XAxis.XAxisPosition.TOP) {
                    if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                        f4 += yOffset;
                    }
                }
                f2 += yOffset;
            }
        }
        float extraTopOffset = getExtraTopOffset();
        float extraRightOffset = getExtraRightOffset();
        float extraBottomOffset = getExtraBottomOffset();
        float extraLeftOffset = getExtraLeftOffset();
        float convertDpToPixel2 = Utils.convertDpToPixel(this.mMinOffset);
        this.mViewPortHandler.restrainViewPort(Math.max(convertDpToPixel2, f + extraLeftOffset), Math.max(convertDpToPixel2, f2 + extraTopOffset), Math.max(convertDpToPixel2, f3 + extraRightOffset), Math.max(convertDpToPixel2, f4 + extraBottomOffset));
        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    private void setXAxisValueFormatter(final List<String> list) {
        if (list == null) {
            return;
        }
        final int size = list.size();
        getXAxis().setValueFormatter(new IAxisValueFormatter() { // from class: com.huawei.ui.commonui.reportchart.HwHealthReportBarChart.3
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                int i = (int) f;
                return (i >= 0 && i < size) ? (String) list.get(i) : "";
            }
        });
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.d.cEV_(canvas);
    }

    @Override // com.github.mikephil.charting.charts.BarChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        nqa nqaVar = new nqa(this, this.mAnimator, this.mViewPortHandler);
        this.d = nqaVar;
        this.mRenderer = nqaVar;
        setHighlighter(new BarHighlighter(this));
    }

    private void d() {
        getDescription().setEnabled(false);
        setScaleEnabled(false);
        setDrawGridBackground(false);
        setTouchEnabled(false);
        setDragXEnabled(false);
        getXAxis().setDrawGridLines(false);
        getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        getXAxis().setTextSize(10.0f);
        getXAxis().setTextColor(getContext().getResources().getColor(R$color.textColorTertiary));
        getXAxis().setDrawAxisLine(true);
        getXAxis().setAxisLineColor(Color.parseColor("#B1B8DC"));
        getLegend().setEnabled(false);
        getAxisLeft().setEnabled(false);
        getAxisLeft().setDrawAxisLine(false);
        getAxisRight().setDrawAxisLine(false);
        getAxisRight().setEnabled(false);
        YAxis yAxis = getYAxis();
        yAxis.setDrawAxisLine(false);
        yAxis.setLabelCount(5, true);
        yAxis.setTextSize(10.0f);
        yAxis.setTextColor(getContext().getResources().getColor(R$color.textColorTertiary));
        yAxis.setAxisMinimum(0.0f);
        yAxis.setSpaceTop(12.0f);
        yAxis.setDrawGridLines(false);
        yAxis.setDrawLabels(false);
    }

    private YAxis getYAxis() {
        return getAxisLeft();
    }
}

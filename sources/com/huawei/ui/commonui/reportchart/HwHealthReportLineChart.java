package com.huawei.ui.commonui.reportchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import defpackage.koq;
import defpackage.nqi;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class HwHealthReportLineChart extends LineChart {

    /* renamed from: a, reason: collision with root package name */
    private int f8929a;
    private int b;
    private int c;
    private int d;
    private boolean e;

    public HwHealthReportLineChart(Context context) {
        super(context);
        this.b = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.d = Color.parseColor("#FFFDB290");
        this.c = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.f8929a = R$drawable.report_line_chart_shadow;
        this.e = false;
        e();
    }

    public HwHealthReportLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.d = Color.parseColor("#FFFDB290");
        this.c = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.f8929a = R$drawable.report_line_chart_shadow;
        this.e = false;
        e();
    }

    public HwHealthReportLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.d = Color.parseColor("#FFFDB290");
        this.c = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.f8929a = R$drawable.report_line_chart_shadow;
        this.e = false;
        e();
    }

    @Override // com.github.mikephil.charting.charts.LineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        this.mRenderer = new nqi(this, this.mAnimator, this.mViewPortHandler);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setChartNumberWithInt(boolean z) {
        this.e = z;
    }

    private void e() {
        a();
    }

    private void a() {
        getDescription().setEnabled(false);
        setPinchZoom(false);
        setDrawBorders(false);
        setTouchEnabled(false);
        setDragEnabled(true);
        setScaleEnabled(false);
        setLogEnabled(false);
        getLegend().setEnabled(false);
    }

    public void d(int i, int i2, int i3, int i4) {
        this.b = i;
        this.d = i2;
        this.c = i3;
        this.f8929a = i4;
    }

    private ArrayList<Entry> c(ArrayList<Entry> arrayList, ArrayList<Entry> arrayList2) {
        int size = koq.c(arrayList2) ? arrayList2.size() - 1 : 0;
        ArrayList<Entry> arrayList3 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            Entry entry = new Entry(size + i, arrayList.get((arrayList.size() - i) - 1).getY());
            entry.setIcon(arrayList.get((arrayList.size() - i) - 1).getIcon());
            entry.setData(arrayList.get((arrayList.size() - i) - 1).getData());
            arrayList3.add(entry);
        }
        return arrayList3;
    }

    public void setChartData(Context context, ArrayList<String> arrayList, ArrayList<Entry> arrayList2, ArrayList<Entry> arrayList3, ArrayList<Entry> arrayList4) {
        if (LanguageUtil.bc(context)) {
            if (koq.c(arrayList2)) {
                arrayList2 = c(arrayList2, arrayList3);
            }
            if (koq.c(arrayList3)) {
                Collections.reverse(arrayList3);
            }
            if (koq.c(arrayList4)) {
                Collections.reverse(arrayList4);
            }
            if (koq.c(arrayList)) {
                Collections.reverse(arrayList);
            }
        }
        XAxis xAxis = getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGridLineWidth(0.5f);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setTextColor(context.getResources().getColor(R$color.textColorTertiary));
        xAxis.setTextSize(10.0f);
        xAxis.setDrawAxisLine(false);
        xAxis.setYOffset(10.0f);
        getAxisRight().setEnabled(false);
        YAxis axisLeft = getAxisLeft();
        axisLeft.setEnabled(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setStartAtZero(false);
        axisLeft.setSpaceTop(12.0f);
        axisLeft.setDrawGridLines(false);
        ArrayList<String> arrayList5 = new ArrayList<>();
        e(arrayList5, arrayList);
        setXAxisValueFormatter(xAxis, arrayList5);
        d(context, arrayList2, arrayList3, arrayList4);
    }

    public static void setXAxisValueFormatter(XAxis xAxis, final List<String> list) {
        if (list == null) {
            return;
        }
        final int size = list.size();
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.huawei.ui.commonui.reportchart.HwHealthReportLineChart.4
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                int i = (int) f;
                return (i >= 0 && i < size) ? (String) list.get(i) : "";
            }
        });
    }

    private void d(Context context, ArrayList<Entry> arrayList, ArrayList<Entry> arrayList2, ArrayList<Entry> arrayList3) {
        ArrayList arrayList4 = new ArrayList();
        boolean z = arrayList3 == null;
        LineDataSet d = d(context, 1, arrayList, z);
        LineDataSet d2 = d(context, 2, arrayList2, z);
        LineDataSet d3 = d(context, 3, arrayList3, z);
        if (LanguageUtil.bc(context)) {
            if (d3 != null) {
                arrayList4.add(d3);
            }
            if (d2 != null) {
                arrayList4.add(d2);
            }
            if (d != null) {
                arrayList4.add(d);
            }
        } else {
            if (d != null) {
                arrayList4.add(d);
            }
            if (d2 != null) {
                arrayList4.add(d2);
            }
            if (d3 != null) {
                arrayList4.add(d3);
            }
        }
        LogUtil.a("dataSets", Integer.valueOf(arrayList4.size()));
        setData(new LineData(arrayList4));
    }

    private LineDataSet d(Context context, int i, List<Entry> list, boolean z) {
        if (koq.b(list)) {
            return null;
        }
        LineDataSet lineDataSet = new LineDataSet(list, "");
        lineDataSet.disableDashedLine();
        if (z) {
            lineDataSet.setValueFormatter(new IValueFormatter() { // from class: com.huawei.ui.commonui.reportchart.HwHealthReportLineChart.2
                @Override // com.github.mikephil.charting.formatter.IValueFormatter
                public String getFormattedValue(float f, Entry entry, int i2, ViewPortHandler viewPortHandler) {
                    return UnitUtil.e(f, 1, 2);
                }
            });
        }
        e(lineDataSet);
        d(context, i, lineDataSet);
        return lineDataSet;
    }

    private void d(Context context, int i, LineDataSet lineDataSet) {
        if (i == 1) {
            lineDataSet.setLineWidth(2.0f);
            lineDataSet.setColor(this.b);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setCircleColor(this.b);
            lineDataSet.setDrawCircleHole(true);
            lineDataSet.setValueTextSize(10.0f);
            lineDataSet.setValueTextColor(context.getResources().getColor(R$color.textColorTertiary));
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(context.getResources().getDrawable(this.f8929a));
            return;
        }
        if (i == 2) {
            lineDataSet.setColor(this.d);
            lineDataSet.setLineWidth(3.0f);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setCircleColor(this.d);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(10.0f);
            lineDataSet.setValueTextColor(context.getResources().getColor(R$color.textColorPrimary));
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(context.getResources().getDrawable(this.f8929a));
            return;
        }
        if (i != 3) {
            return;
        }
        lineDataSet.setColor(this.c);
        lineDataSet.setLineWidth(1.0f);
        lineDataSet.enableDashedLine(10.0f, 10.0f, 0.0f);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setCircleColor(this.c);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setValueTextSize(10.0f);
        lineDataSet.setValueTextColor(context.getResources().getColor(R$color.textColorTertiary));
    }

    private void e(LineDataSet lineDataSet) {
        LogUtil.a("HwHealthReportLineChart", "obtainIntLineData mIsShowWithIntNumber" + this.e);
        if (this.e) {
            lineDataSet.setValueFormatter(new IValueFormatter() { // from class: com.huawei.ui.commonui.reportchart.HwHealthReportLineChart.3
                @Override // com.github.mikephil.charting.formatter.IValueFormatter
                public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    int i2 = (int) f;
                    LogUtil.a("obtainIntLineData", "mChartNumberWithInt valueInt = ", Integer.valueOf(i2));
                    return UnitUtil.e(i2, 1, 0);
                }
            });
        }
    }

    public void e(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(i, arrayList2.get(i));
        }
    }
}

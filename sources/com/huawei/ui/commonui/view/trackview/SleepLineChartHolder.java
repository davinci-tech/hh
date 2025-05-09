package com.huawei.ui.commonui.view.trackview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepLineChartHolder {

    /* renamed from: a, reason: collision with root package name */
    private Context f8981a;
    private Drawable e = null;
    private List<Integer> c = null;

    public SleepLineChartHolder(Context context) {
        if (context != null) {
            this.f8981a = context;
        }
    }

    public static class a {
        boolean e = true;

        public a e(boolean z) {
            this.e = z;
            return this;
        }
    }

    public LineDataSet e(LineChart lineChart, a aVar) {
        if (lineChart == null || this.f8981a == null) {
            LogUtil.h("SleepLineChartHolder", "chart or mContext is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(16);
        if (koq.b(this.c)) {
            LogUtil.h("SleepLineChartHolder", "environmentalList empty,return");
            return null;
        }
        for (int i = 0; i < this.c.size(); i++) {
            arrayList.add(new Entry(i, this.c.get(i).intValue()));
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, "单位dB");
        lineDataSet.setLineWidth(1.0f);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setColor(Color.argb(153, 19, 57, 252));
        Drawable drawable = this.f8981a.getResources().getDrawable(R$drawable.environmental_nosie_gradient_fill);
        this.e = drawable;
        lineDataSet.setFillDrawable(drawable);
        a(lineChart, aVar);
        c(lineChart, lineDataSet, aVar);
        return lineDataSet;
    }

    private void c(LineChart lineChart, LineDataSet lineDataSet, a aVar) {
        d(lineChart, aVar);
        b(lineChart, lineDataSet);
        a(lineChart);
    }

    private void b(LineChart lineChart, LineDataSet lineDataSet) {
        if (lineChart == null) {
            LogUtil.a("environmental noise chart is null", new Object[0]);
            return;
        }
        lineChart.setData(new LineData(lineDataSet));
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    private void d(LineChart lineChart, a aVar) {
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setDragEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setExtraLeftOffset(30.0f);
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);
        lineChart.getLegend().setEnabled(false);
    }

    private void a(LineChart lineChart) {
        LogUtil.a("SleepLineChartHolder", "axis is to be customed!");
        XAxis xAxis = lineChart.getXAxis();
        YAxis axisLeft = lineChart.getAxisLeft();
        YAxis axisRight = lineChart.getAxisRight();
        xAxis.setEnabled(false);
        axisRight.setEnabled(false);
        axisLeft.setAxisMinimum(0.0f);
        axisLeft.setAxisMaximum(100.0f);
        axisLeft.setLabelCount(5, true);
        axisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeft.setXOffset(nsn.c(this.f8981a, -6.0f));
        xAxis.setDrawGridLines(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(true);
        axisLeft.enableGridDashedLine(10.0f, 10.0f, 0.0f);
    }

    public void a(List<Integer> list) {
        this.c = list;
    }

    private void a(LineChart lineChart, a aVar) {
        YAxis axisLeft = lineChart.getAxisLeft();
        if (aVar.e) {
            return;
        }
        axisLeft.setGridColor(Color.argb(51, 255, 255, 255));
        axisLeft.setTextColor(Color.argb(51, 255, 255, 255));
    }
}

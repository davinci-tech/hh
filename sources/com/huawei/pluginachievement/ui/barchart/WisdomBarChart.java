package com.huawei.pluginachievement.ui.barchart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.BarHighlighter;
import com.github.mikephil.charting.utils.Transformer;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.mjk;
import java.util.List;

/* loaded from: classes9.dex */
public class WisdomBarChart extends BarChart {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8435a;
    private CustomMarkerView b;
    private mjk c;
    private float[] e;

    public WisdomBarChart(Context context) {
        super(context);
        this.f8435a = false;
        this.e = new float[2];
        c(context);
    }

    public WisdomBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8435a = false;
        this.e = new float[2];
        c(context);
    }

    public WisdomBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8435a = false;
        this.e = new float[2];
        c(context);
    }

    private void setXAxisValueFormatter(final List<String> list) {
        if (list == null) {
            return;
        }
        final int size = list.size();
        getXAxis().setValueFormatter(new IAxisValueFormatter() { // from class: com.huawei.pluginachievement.ui.barchart.WisdomBarChart.5
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
        this.c.ciK_(canvas);
        Transformer b = this.c.b();
        float[] fArr = this.e;
        float[] fArr2 = {fArr[0], fArr[1]};
        if (b == null) {
            LogUtil.a("WisdomBarChart", "onDraw() trans = null");
            return;
        }
        b.pointValuesToPixel(fArr2);
        this.b.setIsRun(this.f8435a);
        this.b.draw(canvas, fArr2[0], fArr2[1]);
    }

    @Override // com.github.mikephil.charting.charts.BarChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        mjk mjkVar = new mjk(this, this.mAnimator, this.mViewPortHandler);
        this.c = mjkVar;
        this.mRenderer = mjkVar;
        setHighlighter(new BarHighlighter(this));
    }

    private void c(Context context) {
        setDrawMarkers(true);
        CustomMarkerView customMarkerView = new CustomMarkerView(context, R.layout.pop_maekerview);
        this.b = customMarkerView;
        customMarkerView.setChartView(this);
        setMarker(this.b);
        getDescription().setEnabled(false);
        setScaleEnabled(false);
        setDrawGridBackground(false);
        setTouchEnabled(false);
        setDragXEnabled(false);
        getXAxis().setDrawGridLines(false);
        getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        getXAxis().setTextSize(10.0f);
        getXAxis().setTextColor(context.getResources().getColor(R.color._2131296884_res_0x7f090274));
        getXAxis().setDrawAxisLine(false);
        getLegend().setEnabled(false);
        getAxisRight().setDrawAxisLine(false);
        getAxisRight().setEnabled(false);
        getAxisLeft().setEnabled(false);
        getAxisLeft().setDrawAxisLine(false);
        getAxisLeft().setLabelCount(5, true);
        getAxisLeft().setTextSize(10.0f);
        getAxisLeft().setTextColor(context.getResources().getColor(R.color._2131296884_res_0x7f090274));
        getAxisLeft().setAxisMinimum(0.0f);
        getAxisLeft().setSpaceTop(22.0f);
        getAxisLeft().setDrawGridLines(true);
    }
}

package com.huawei.indoorequip.ui.hrcontrol;

import android.content.Context;
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
import com.github.mikephil.charting.utils.Utils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.lbn;
import defpackage.lbr;
import defpackage.nns;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class HeartRateControlSportChart extends LineChart {
    private static final int d = Color.parseColor("#33FFFFFF");

    /* renamed from: a, reason: collision with root package name */
    private static final int f6436a = Color.parseColor("#99FFFFFF");
    private static final int c = Color.parseColor("#FFEB4667");
    private static final int e = Color.parseColor("#FFEB4667");

    public HeartRateControlSportChart(Context context) {
        super(context);
        d();
        e();
    }

    public HeartRateControlSportChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
        e();
    }

    public HeartRateControlSportChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
        e();
    }

    private void d() {
        this.mRenderer = new lbn(this, this.mAnimator, this.mViewPortHandler);
        this.mAxisRendererLeft = new lbr(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
    }

    private void e() {
        getDescription().setEnabled(false);
        setTouchEnabled(false);
        setDragEnabled(false);
        setScaleEnabled(false);
        setExtraLeftOffset(24.0f);
        setExtraRightOffset(24.0f);
        setExtraTopOffset(10.0f);
        XAxis xAxis = getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLinesBehindData(false);
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new b());
        int i = f6436a;
        xAxis.setTextColor(i);
        xAxis.setTextSize(10.0f);
        getAxisRight().setEnabled(false);
        YAxis axisLeft = getAxisLeft();
        axisLeft.setDrawZeroLine(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawTopYLabelEntry(true);
        axisLeft.setTextColor(i);
        axisLeft.setTextSize(10.0f);
        axisLeft.setGridColor(d);
        getLegend().setEnabled(false);
        if (this.mRenderer instanceof lbn) {
            ((lbn) this.mRenderer).c(e(2.0f), e(4.0f), e(3.0f));
        }
        if (this.mAxisRendererLeft instanceof lbr) {
            ((lbr) this.mAxisRendererLeft).d(e(24.0f), e(0.5f), e(2.0f), e(1.0f));
        }
    }

    public void setIntervalAreaList(List<nns> list) {
        if (this.mAxisRendererLeft instanceof lbr) {
            ((lbr) this.mAxisRendererLeft).b(list);
        }
    }

    public void a() {
        LogUtil.a("HeartRateControlSportChart", "initTvChart");
        Utils.init(BaseApplication.e());
        e();
    }

    public void setChartData(List<Entry> list) {
        LineDataSet lineDataSet = new LineDataSet(list, "");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setDrawIcons(false);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setColor(c);
        lineDataSet.setValueTextSize(10.0f);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setCircleColor(-16777216);
        lineDataSet.setCircleHoleColor(e);
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineDataSet);
        setData(new LineData(arrayList));
        invalidate();
    }

    static class b implements IAxisValueFormatter {
        private b() {
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            int i = (int) f;
            int i2 = i / 3600;
            return decimalFormat.format(i2) + ":" + decimalFormat.format((i - (i2 * 3600)) / 60) + ":" + decimalFormat.format(r6 - (r1 * 60));
        }
    }

    private float e(float f) {
        return Utils.convertDpToPixel(f);
    }
}

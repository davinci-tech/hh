package com.huawei.ui.main.stories.fitness.views.heartrate.linechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.R$string;
import defpackage.jcf;
import defpackage.nnr;
import defpackage.now;
import defpackage.nsf;
import defpackage.qay;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.List;

/* loaded from: classes6.dex */
public class RestHeartRateLineChart extends HwHealthLineChart {
    private Float c;
    private Context d;
    private boolean e;

    public RestHeartRateLineChart(Context context) {
        super(context);
        this.d = null;
        this.e = true;
        this.c = Float.valueOf(0.0f);
        LogUtil.c("RestHeartRateLineChart", "construct chart");
        this.d = context;
        a();
    }

    private void a() {
        this.mLegend.setEnabled(false);
        getDescription().setEnabled(false);
        XAxis xAxis = getXAxis();
        xAxis.setValueFormatter(new d());
        xAxis.setAxisMinimum(0.0f);
        xAxis.setAxisMaximum(1440.0f);
        this.mAxisRendererFirstParty = new nnr(this.d, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new nnr(this.d, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new nnr(this.d, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
        injectDataContainer(new HwHealthBaseBarLineChart.HwHealthDataContainerGenerator() { // from class: com.huawei.ui.main.stories.fitness.views.heartrate.linechart.RestHeartRateLineChart.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart.HwHealthDataContainerGenerator
            public boolean typeOf(Class cls) {
                Class<qay> cls2 = qay.class;
                while (true) {
                    if (cls2 == null) {
                        return false;
                    }
                    if (cls2.equals(cls)) {
                        return true;
                    }
                    for (Class<?> cls3 : cls2.getInterfaces()) {
                        if (cls3.equals(cls)) {
                            return true;
                        }
                    }
                    cls2 = cls2.getSuperclass();
                }
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart.HwHealthDataContainerGenerator
            public IHwHealthDatasContainer newDataContainer() {
                return new qay(RestHeartRateLineChart.this);
            }
        });
        setExtraTopOffset(5.0f);
        setExtraBottomOffset(7.0f);
        setBackgroundColor(Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE));
        enableMarkerView(true);
        setMarkerSlidingMode(HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA);
        enableSpacePreserveForDataOverlay(true);
    }

    static class d implements IAxisValueFormatter {
        private d() {
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            return decimalFormat.format(((int) f) / 60) + ":" + decimalFormat.format(r5 - (r0 * 60));
        }
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        LogUtil.c("RestHeartRateLineChart", "refresh chart");
        super.refresh();
    }

    private boolean c() {
        List<T> dataSets = ((now) this.mData).getDataSets();
        if (dataSets == 0 || dataSets.size() == 0) {
            return true;
        }
        LogUtil.a("RestHeartRateLineChart", "fillOriginalData mLineData size = ", Integer.valueOf(dataSets.size()));
        return false;
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart
    public void customAxisShow() {
        if (!c()) {
            super.customAxisShow();
            return;
        }
        this.mAxisFirstParty.setEnabled(true);
        this.mAxisSecondParty.setEnabled(false);
        this.mAxisThirdParty.setEnabled(false);
        this.mAxisFirstParty.setDrawLabels(true);
        this.mAxisSecondParty.setDrawLabels(false);
        this.mAxisThirdParty.setDrawLabels(false);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.Chart
    public void drawMarkers(Canvas canvas) {
        super.drawMarkers(canvas);
        if (jcf.c()) {
            d();
        }
    }

    private void d() {
        String d2;
        if (((HwHealthMarkerView) this.mMarker).getData().size() == 0) {
            ReleaseLogUtil.d("RestHeartRateLineChart", "data is null");
            return;
        }
        HwHealthMarkerView.a aVar = ((HwHealthMarkerView) this.mMarker).getData().get(0);
        if (aVar.b == null) {
            ReleaseLogUtil.d("RestHeartRateLineChart", "entry is null");
            return;
        }
        float y = aVar.b.getY();
        if (this.e) {
            d2 = nsf.b(R$string.accessibility_heart_rate_day_chart, d(aVar));
            jcf.bEz_(this, d2);
            this.c = Float.valueOf(y);
            this.e = false;
        } else {
            d2 = d(aVar);
        }
        if (Float.compare(y, this.c.floatValue()) == 0) {
            ReleaseLogUtil.e("RestHeartRateLineChart", "val not change");
        } else {
            this.c = Float.valueOf(y);
            jcf.bEk_(this, d2);
        }
    }

    private String d(HwHealthMarkerView.a aVar) {
        Object parseTool = getParseTool();
        String str = "";
        if (parseTool == null) {
            ReleaseLogUtil.d("RestHeartRateLineChart", "cant setAccessibilityDescription,", parseTool);
            return "";
        }
        String spannableString = ((HwHealthMarkerView) this.mMarker).getTime().toString();
        try {
            str = (String) Class.forName("com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView").getDeclaredMethod("parse", HwHealthBaseEntry.class).invoke(parseTool, aVar.b);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            ReleaseLogUtil.c("RestHeartRateLineChart", "reflect error");
        }
        String str2 = aVar.d;
        if (!TextUtils.isEmpty(str)) {
            spannableString = nsf.b(com.huawei.ui.commonui.R$string.IDS_two_parts, spannableString, str);
        }
        return (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) ? spannableString : nsf.b(com.huawei.ui.commonui.R$string.IDS_two_parts, spannableString, str2);
    }
}

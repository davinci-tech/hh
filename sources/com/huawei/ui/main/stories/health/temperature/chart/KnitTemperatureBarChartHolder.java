package com.huawei.ui.main.stories.health.temperature.chart;

import android.content.Context;
import android.graphics.Color;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import defpackage.nmz;
import defpackage.pyp;
import defpackage.qom;
import defpackage.qpr;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class KnitTemperatureBarChartHolder extends HwHealthBarScrollChartHolder {
    private final qom b;
    private String c;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos) {
    }

    public KnitTemperatureBarChartHolder(Context context) {
        super(context);
        this.c = "TEMPERATURE_MIN_MAX";
        this.b = new qom();
    }

    public void e(String str) {
        this.b.c(str);
        this.c = str;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return qpr.a("TEMPERATURE_MIN_MAX".equals(this.c));
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return qpr.b("TEMPERATURE_MIN_MAX".equals(this.c));
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        pyp pypVar = new pyp(new ArrayList(), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), dataInfos);
        if (dataInfos.isBloodOxygenData()) {
            pypVar.setColor(Color.argb(255, 253, 178, 144));
            pypVar.e(Color.argb(255, 255, 64, 101));
        }
        pypVar.setLabelCount(5, true);
        nmz nmzVar = (nmz) hwHealthBarChart.getData();
        nmzVar.d(dataInfos);
        if (dataInfos.isDayData()) {
            nmzVar.b(30.0f);
        }
        return pypVar;
    }
}

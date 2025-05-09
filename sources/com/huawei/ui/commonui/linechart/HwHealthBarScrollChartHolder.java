package com.huawei.ui.commonui.linechart;

import android.content.Context;
import android.graphics.Color;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import defpackage.nmy;
import defpackage.nmz;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public abstract class HwHealthBarScrollChartHolder extends HwHealthScrollChartHolder<HwHealthBarDataSet, HwHealthBarChart> {
    public HwHealthBarScrollChartHolder(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        nmy nmyVar = new nmy(new ArrayList(), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), dataInfos);
        nmyVar.setColor(Color.argb(255, 253, 178, 144));
        nmyVar.e(Color.argb(255, 250, 101, 33));
        nmyVar.setLabelCount(5, true);
        ((nmz) hwHealthBarChart.getData()).d(dataInfos);
        return nmyVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public void initChartData(HwHealthBarChart hwHealthBarChart) {
        if (((nmz) hwHealthBarChart.getData()) == null) {
            hwHealthBarChart.setData(new nmz(new ArrayList()));
        }
    }
}

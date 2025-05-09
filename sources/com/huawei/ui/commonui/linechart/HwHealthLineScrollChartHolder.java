package com.huawei.ui.commonui.linechart;

import android.content.Context;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.now;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public abstract class HwHealthLineScrollChartHolder extends HwHealthScrollChartHolder<HwHealthLineDataSet, HwHealthLineChart> {
    public HwHealthLineScrollChartHolder(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        return new HwHealthLineDataSet(this.mContext, new ArrayList(), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public void initChartData(HwHealthLineChart hwHealthLineChart) {
        if (((now) hwHealthLineChart.getData()) == null) {
            hwHealthLineChart.setData(new now(new ArrayList()));
        }
    }
}

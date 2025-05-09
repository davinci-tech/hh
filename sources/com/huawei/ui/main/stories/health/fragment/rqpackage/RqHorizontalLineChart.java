package com.huawei.ui.main.stories.health.fragment.rqpackage;

import android.content.Context;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import defpackage.now;

/* loaded from: classes6.dex */
public class RqHorizontalLineChart extends HwHealthCommonLineChart {
    public RqHorizontalLineChart(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart, com.huawei.ui.commonui.linechart.view.HwHealthLineChart
    public void customAxisShow() {
        if (((now) this.mData).getDataSets().size() == 0) {
            return;
        }
        this.mAxisFirstParty.setEnabled(true);
        this.mAxisSecondParty.setEnabled(true);
        this.mAxisThirdParty.setEnabled(true);
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisThirdParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setDrawLabels(true);
        this.mAxisSecondParty.setDrawLabels(false);
        this.mAxisThirdParty.setDrawLabels(false);
    }
}

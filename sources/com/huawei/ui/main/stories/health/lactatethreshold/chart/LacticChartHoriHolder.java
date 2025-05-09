package com.huawei.ui.main.stories.health.lactatethreshold.chart;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.ecy;
import defpackage.nmt;
import defpackage.nmv;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class LacticChartHoriHolder extends LacticChartHolder {
    private boolean d;

    public LacticChartHoriHolder(Context context) {
        super(context);
    }

    public LacticChartHoriHolder(Context context, String str) {
        super(context);
        setShowDataType(str);
    }

    @Override // com.huawei.ui.main.stories.health.lactatethreshold.chart.LacticChartHolder, com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        ecy ecyVar = new ecy(BaseApplication.getContext(), new ArrayList(16), "", "", "");
        ecyVar.setShowType(this.c);
        ecyVar.setColor(b(ecyVar));
        ecyVar.setLabelCount(5, true);
        ecyVar.setLineWidth(1.5f);
        ecyVar.setShowMaxMinValue(false);
        if (this.d) {
            ecyVar.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY);
        }
        HwHealthYAxis axisSecondParty = this.d ? hwHealthLineChart.getAxisSecondParty() : hwHealthLineChart.getAxisFirstParty();
        axisSecondParty.setAxisMaximum(c(ecyVar));
        axisSecondParty.setAxisMinimum(e(ecyVar));
        axisSecondParty.setValueFormatter(a(ecyVar) ? new nmt() : new nmv());
        return ecyVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.health.lactatethreshold.chart.LacticChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos) {
        super.onCustomChartStyle(hwHealthLineChart, dataInfos);
        hwHealthLineChart.getAxisSecondParty().setEnabled(true);
    }

    public void a(boolean z) {
        this.d = z;
    }
}

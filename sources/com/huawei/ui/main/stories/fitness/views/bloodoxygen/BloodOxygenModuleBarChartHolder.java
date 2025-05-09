package com.huawei.ui.main.stories.fitness.views.bloodoxygen;

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
import defpackage.pjm;
import defpackage.pym;
import defpackage.pyp;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class BloodOxygenModuleBarChartHolder extends HwHealthBarScrollChartHolder {
    private IChartStorageHelper e;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos) {
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 100.0f;
    }

    public BloodOxygenModuleBarChartHolder(Context context) {
        super(context);
        this.e = new pym();
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return pjm.c(f2);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        pyp pypVar = new pyp(new ArrayList(), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), dataInfos);
        if (dataInfos.isBloodOxygenData()) {
            pypVar.setColor(Color.argb(255, 253, 178, 144));
            pypVar.e(Color.argb(255, 255, 64, 101));
        }
        pypVar.setLabelCount(pjm.a().length, true);
        ((nmz) hwHealthBarChart.getData()).d(dataInfos);
        hwHealthBarChart.getAxisFirstParty().setAxisMinimum(60.0f);
        hwHealthBarChart.getAxisFirstParty().setAxisMaximum(100.0f);
        return pypVar;
    }
}

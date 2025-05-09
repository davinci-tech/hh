package com.huawei.ui.main.stories.fitness.util.chart;

import android.content.Context;
import android.graphics.Color;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import defpackage.pxf;
import defpackage.pyh;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepModuleBarChartHolder extends HwHealthBarScrollChartHolder {
    private IChartStorageHelper d;
    private Map<DataInfos, HwHealthBarDataSet> e;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos) {
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 0.0f;
    }

    public SleepModuleBarChartHolder(Context context) {
        super(context);
        this.e = new HashMap();
        this.d = new pxf();
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return pyh.c((int) Math.ceil(f), 5);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        HwHealthBarDataSet onCreateDataSet = super.onCreateDataSet(hwHealthBarChart, dataInfos, bVar);
        this.e.put(dataInfos, onCreateDataSet);
        if (dataInfos.isCoreSleepData()) {
            onCreateDataSet.e(Color.argb(255, OldToNewMotionPath.SPORT_TYPE_PILATES, 43, 226));
        }
        return onCreateDataSet;
    }
}

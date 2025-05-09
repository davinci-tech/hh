package com.huawei.health.knit.section.model;

import android.content.Context;
import android.graphics.Color;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import defpackage.eee;
import defpackage.eeq;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class SleepModuleBarChartHolder extends HwHealthBarScrollChartHolder {
    private IChartStorageHelper d;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos) {
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 0.0f;
    }

    public SleepModuleBarChartHolder(Context context) {
        super(context);
        this.d = new eee();
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return eeq.a((int) Math.ceil(f), 5);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        HwHealthBarDataSet onCreateDataSet = super.onCreateDataSet(hwHealthBarChart, dataInfos, bVar);
        if (dataInfos.isCoreSleepData()) {
            onCreateDataSet.e(Color.argb(255, OldToNewMotionPath.SPORT_TYPE_PILATES, 43, 226));
        }
        return onCreateDataSet;
    }

    public String c(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 0);
    }
}

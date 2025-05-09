package com.huawei.ui.main.stories.fitness.util.chart;

import android.content.Context;
import android.graphics.Color;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DataLayerType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.main.stories.fitness.interactors.TrackModuleChartStorageHelper;
import defpackage.nmz;
import defpackage.noq;
import defpackage.pye;
import defpackage.pyh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class TrackModuleBarChartHolder extends HwHealthBarScrollChartHolder implements DataTypeListener {

    /* renamed from: a, reason: collision with root package name */
    private List<DataSetLayerListener> f9927a;
    private Map<DataLayerType, noq> b;
    private IChartStorageHelper c;

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 0.0f;
    }

    public TrackModuleBarChartHolder(Context context) {
        super(context);
        this.c = new TrackModuleChartStorageHelper();
        this.f9927a = new ArrayList();
        this.b = new HashMap();
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return pyh.c((int) Math.ceil(f), 5);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.c;
    }

    public void b(DataLayerType dataLayerType, int i, int i2, String str) {
        noq noqVar = new noq();
        noqVar.e(i);
        noqVar.a(i2);
        noqVar.d(str);
        this.b.put(dataLayerType, noqVar);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        pye pyeVar = new pye(new ArrayList(), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), dataInfos);
        pyeVar.setLabelCount(5, true);
        ((nmz) hwHealthBarChart.getData()).d(dataInfos);
        if (dataInfos.isMultiData()) {
            this.f9927a.add(pyeVar);
        }
        pyeVar.setColor(Color.argb(255, 137, 222, OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL));
        pyeVar.e(Color.argb(255, 18, 184, 74));
        return pyeVar;
    }

    @Override // com.huawei.ui.main.stories.fitness.util.chart.DataTypeListener
    public void onChange(DataLayerType dataLayerType) {
        if (dataLayerType == null) {
            return;
        }
        noq noqVar = this.b.get(dataLayerType);
        Iterator<DataSetLayerListener> it = this.f9927a.iterator();
        while (it.hasNext()) {
            it.next().onChange(dataLayerType, noqVar);
        }
    }

    public void b() {
        this.f9927a.clear();
    }
}

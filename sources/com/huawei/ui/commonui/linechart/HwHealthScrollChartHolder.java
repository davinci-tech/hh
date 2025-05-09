package com.huawei.ui.commonui.linechart;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;
import defpackage.nmo;

/* loaded from: classes6.dex */
public abstract class HwHealthScrollChartHolder<T extends HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry>, ChartT extends HwHealthBaseScrollBarLineChart> extends HwHealthChartHolder<T, ChartT> implements DynamicBorderSupportable {
    private static final String TAG = "HealthChart_HwHealthScrollChartHolder";
    private nmo mScrollChartInteractor;

    public abstract IChartStorageHelper acquireStorageHelper();

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    protected void initChartData(ChartT chartt) {
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    protected T onCreateDataSet(ChartT chartt, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        return null;
    }

    public HwHealthScrollChartHolder(Context context) {
        super(context);
        this.mScrollChartInteractor = new nmo();
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    protected T onDataLayerAdded(ChartT chartt, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        prepareScrollMode(chartt, dataInfos);
        if (chartt.acquireScrollAdapter() != null) {
            chartt.acquireScrollAdapter().invalidateBuff();
        }
        return (T) super.onDataLayerAdded(chartt, dataInfos, bVar);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder, com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public T addDataLayer(ChartT chartt, HwHealthChartHolder.b bVar) {
        T t = (T) super.addDataLayer((HwHealthScrollChartHolder<T, ChartT>) chartt, bVar);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = chartt.acquireScrollAdapter();
        if (acquireScrollAdapter != null) {
            acquireScrollAdapter.injectStorage(t, acquireStorageHelper(), bVar.d(), bVar);
        }
        return t;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder, com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public T addDataLayer(ChartT chartt, T t, HwHealthChartHolder.b bVar) {
        super.addDataLayer(chartt, t, bVar);
        chartt.acquireScrollAdapter().injectStorage(t, acquireStorageHelper(), bVar.d(), bVar);
        return t;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    protected T onFakeDataSet(HwHealthChartHolder.b bVar) {
        LogUtil.b(TAG, "onFakeDataSet not Support,needs override!!!");
        return null;
    }

    protected void prepareScrollMode(ChartT chartt, DataInfos dataInfos) {
        this.mScrollChartInteractor.d(chartt, dataInfos, this);
    }
}

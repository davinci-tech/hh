package com.huawei.ui.commonui.linechart.scrolladapter;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import defpackage.nnh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public abstract class BaseScrollAdapter extends HwHealthBaseScrollBarLineChart.ScrollAdapter {
    private HwHealthBaseScrollBarLineChart mChart;
    private c mChartDataSetPropManager;
    private long mDefaultTime;
    private DynamicBorderSupportable mDynamicBorderSupportable;
    private c mProxyDataSetPropManager;

    public BaseScrollAdapter(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DynamicBorderSupportable dynamicBorderSupportable) {
        super(hwHealthBaseScrollBarLineChart);
        this.mChartDataSetPropManager = new c();
        this.mProxyDataSetPropManager = new c();
        this.mDefaultTime = System.currentTimeMillis();
        this.mChart = hwHealthBaseScrollBarLineChart;
        this.mDynamicBorderSupportable = dynamicBorderSupportable;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public List<IChartStorageHelper> parseAssociatedStorageHelper() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mChartDataSetPropManager.b());
        arrayList.addAll(this.mProxyDataSetPropManager.b());
        return arrayList;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private Map<HwHealthBaseBarLineDataSet, b> f8887a;

        private c() {
            this.f8887a = new HashMap();
        }

        public Set<HwHealthBaseBarLineDataSet> a() {
            return this.f8887a.keySet();
        }

        public void d(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, b bVar) {
            this.f8887a.put(hwHealthBaseBarLineDataSet, bVar);
        }

        public b b(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            b bVar = this.f8887a.get(hwHealthBaseBarLineDataSet);
            if (bVar != null) {
                return bVar;
            }
            throw new RuntimeException("dataSetProp null");
        }

        public void d(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            this.f8887a.remove(hwHealthBaseBarLineDataSet);
        }

        public IChartStorageHelper e(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            b b = b(hwHealthBaseBarLineDataSet);
            if (hwHealthBaseBarLineDataSet == null || b == null) {
                return null;
            }
            return b.b;
        }

        public List<IChartStorageHelper> b() {
            b next;
            ArrayList arrayList = new ArrayList();
            Iterator<b> it = this.f8887a.values().iterator();
            while (it.hasNext() && (next = it.next()) != null) {
                arrayList.add(next.b);
            }
            return arrayList;
        }

        public HwHealthChartHolder.b c(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            b b = b(hwHealthBaseBarLineDataSet);
            if (hwHealthBaseBarLineDataSet == null) {
                return null;
            }
            return b.f8886a;
        }

        public DataInfos a(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            b b = b(hwHealthBaseBarLineDataSet);
            if (hwHealthBaseBarLineDataSet == null || b == null) {
                return null;
            }
            return b.d;
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        HwHealthChartHolder.b f8886a;
        IChartStorageHelper b;
        DataInfos d;

        b(DataInfos dataInfos, HwHealthChartHolder.b bVar, IChartStorageHelper iChartStorageHelper) {
            this.d = dataInfos;
            this.f8886a = bVar;
            this.b = iChartStorageHelper;
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public void injectStorage(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, IChartStorageHelper iChartStorageHelper, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        injectStorage(this.mChartDataSetPropManager, hwHealthBaseBarLineDataSet, iChartStorageHelper, dataInfos, bVar);
    }

    private void injectStorage(c cVar, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, IChartStorageHelper iChartStorageHelper, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        cVar.d(hwHealthBaseBarLineDataSet, new b(dataInfos, bVar, iChartStorageHelper));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public void reverseInjectStorage(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        this.mChartDataSetPropManager.d(hwHealthBaseBarLineDataSet);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public IChartStorageHelper queryStorage(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        return this.mChartDataSetPropManager.e(hwHealthBaseBarLineDataSet);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public HwHealthChartHolder.b queryShowMode(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        return this.mChartDataSetPropManager.c(hwHealthBaseBarLineDataSet);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public DataInfos queryDataInfos(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        return this.mChartDataSetPropManager.a(hwHealthBaseBarLineDataSet);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return this.mDynamicBorderSupportable.computeDynamicBorderMax(hwHealthBaseBarLineDataSet, f, f2);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return this.mDynamicBorderSupportable.computeDynamicBorderMin(hwHealthBaseBarLineDataSet, f, f2);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapter
    public void fillBuffer(HwHealthBaseScrollBarLineChart.ScrollAdapter.b bVar, List<? extends IHwHealthBarLineDataSet<? extends HwHealthBaseEntry>> list, HwHealthBaseScrollBarLineChart.OnDataFillCallback onDataFillCallback) {
        HwHealthBaseBarLineDataSet next;
        if (bVar == null || this.mProxyDataSetPropManager == null) {
            return;
        }
        this.mChart.fillBuffer(bVar, onDataFillCallback);
        Iterator<HwHealthBaseBarLineDataSet> it = this.mProxyDataSetPropManager.a().iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            next.fillSetByDatabase(BaseApplication.getContext(), null, new nnh(bVar.e(), bVar.d(), bVar.c(), bVar.a(), this.mProxyDataSetPropManager.c(next), this.mProxyDataSetPropManager.a(next)), new HwHealthBaseScrollBarLineChart.OnDataFillCallback() { // from class: com.huawei.ui.commonui.linechart.scrolladapter.BaseScrollAdapter.2
                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnDataFillCallback
                public void onResult() {
                    BaseScrollAdapter baseScrollAdapter = BaseScrollAdapter.this;
                    baseScrollAdapter.setFlag(baseScrollAdapter.getFlag() | 1);
                    BaseScrollAdapter.this.mChart.refresh();
                }
            }, this.mProxyDataSetPropManager.e(next));
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public void manageDataSetAsProxy(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, IChartStorageHelper iChartStorageHelper, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        injectStorage(this.mProxyDataSetPropManager, hwHealthBaseBarLineDataSet, iChartStorageHelper, dataInfos, bVar);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public void unmanageDataSetAsProxy(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        this.mProxyDataSetPropManager.d(hwHealthBaseBarLineDataSet);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireRangeCenterValue(int i) {
        return (int) ((acquireValuePresentRangeMax(i) + acquireValuePresentRangeMin(i)) / 2.0f);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public int acquireRange(long j) {
        return acquireRange();
    }

    public long getDefaultTime() {
        return this.mDefaultTime;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
    public void setDefaultTime(long j) {
        this.mDefaultTime = j;
    }
}

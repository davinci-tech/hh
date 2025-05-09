package com.huawei.ui.commonui.linechart.icommon;

import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;

/* loaded from: classes6.dex */
public interface IChartLayerHolder<T extends HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry>, C extends HwHealthBaseScrollBarLineChart> {

    public interface DataTypeFilter {
        boolean isAccept(DataInfos dataInfos);
    }

    T addDataLayer(C c, HwHealthChartHolder.b bVar);

    T addDataLayer(C c, DataInfos dataInfos);

    T addDataLayer(C c, T t, HwHealthChartHolder.b bVar);

    T fakeDataLayer(HwHealthChartHolder.b bVar);

    void rmDataLayer(C c, T t);

    void spetifiyDataTypeUnit(DataTypeFilter dataTypeFilter, String str);
}

package com.huawei.ui.commonui.linechart.icommon;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.npf;
import java.util.List;

/* loaded from: classes6.dex */
public interface IHwHealthLineDatasContainer extends IHwHealthDatasContainer {
    HwHealthBaseBarLineChart acquireChart();

    HwHealthBaseEntry acquireNearestPoint(float f);

    void load(HwHealthLineDataSet hwHealthLineDataSet);

    List<npf> queryNewNodes();

    List<npf> queryNodes();

    void reset();

    List<HwHealthBaseEntry> searchEntryForXValue(float f);
}

package com.huawei.ui.main.stories.fitness.views.base.chart;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;

/* loaded from: classes6.dex */
public interface IScrollChartOuterObserver {
    IFocusObserverItem acquireFocusItem();

    void initChartLinkage();

    void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2);
}

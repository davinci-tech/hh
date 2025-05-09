package com.huawei.ui.main.stories.fitness.common;

import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;

/* loaded from: classes.dex */
public interface IChartLayerHolderProvider<T extends IChartLayerHolder> {
    T acquireChartLayerHolder();
}

package com.huawei.ui.main.stories.fitness.views.base.chart;

import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.MotionType;

/* loaded from: classes6.dex */
public interface ICustomMotionTypeCalculator {
    float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos, MotionType motionType);
}

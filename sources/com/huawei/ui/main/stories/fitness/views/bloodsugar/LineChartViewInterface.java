package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseView;
import java.util.List;

/* loaded from: classes6.dex */
public interface LineChartViewInterface extends CommonBaseView {
    void clickLeftArrow();

    void clickRightArrow();

    HwHealthBarChart getBarChart();

    HwHealthLineChart getLineChart();

    void initChart();

    void initViewPager();

    void notifyMaxAndMin(int i, List<HiHealthData> list);

    void notifyNumerical(String str, List<HwHealthMarkerView.a> list);

    void notifyRemindData(int i, List<HiHealthData> list);

    void setDayAndWeek(String str, String str2, boolean z);

    void setLiftAndRightImage();
}

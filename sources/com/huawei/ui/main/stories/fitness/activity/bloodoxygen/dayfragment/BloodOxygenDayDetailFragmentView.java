package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment;

import com.huawei.health.knit.section.chart.BloodOxygenLineChart;
import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseView;
import defpackage.pkb;
import java.util.List;

/* loaded from: classes9.dex */
public interface BloodOxygenDayDetailFragmentView extends CommonBaseView {
    BloodOxygenLineChart getBloodOxygenLineChart();

    pkb getBloodOxygenLineChartHolder();

    void initChart();

    void initViewPager();

    void leftArrowClick();

    void notifyNumerical(String str, String str2, String str3);

    void rightArrowClick();

    void setBloodOxygenData(List<HiHealthData> list);

    void setBloodOxygenInterval(List<String> list);

    void setBloodOxygenLatest(String str, long j);

    void setBloodOxygenMaxAndMin(String str, String str2);

    void setDayAndWeek(String str, String str2, boolean z, long j);

    void setLiftAndRightImage();
}

package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment;

import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import java.util.List;

/* loaded from: classes9.dex */
public interface BloodOxygenDayDetailFragmentPresenter {
    void initBloodOxygenInterval();

    void initLeftArrowClick();

    void initPageParams();

    void initRightArrowClick();

    void notifyAltitude(int i, List<HiHealthData> list);

    void notifyData(int i, int i2);

    void notifyLakelouise(int i, List<HiHealthData> list);

    void notifyLatestBloodOxygen(int i, List<HiHealthData> list);

    void notifyMaxAndMinBloodOxy(int i, List<HiHealthData> list);

    void notifyRemindBloodOxygen(int i, List<HiHealthData> list);

    void notifySourceAndTime(String str, List<HwHealthMarkerView.a> list);
}

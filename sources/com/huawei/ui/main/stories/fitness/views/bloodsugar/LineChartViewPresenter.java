package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import androidx.fragment.app.Fragment;
import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import java.util.List;

/* loaded from: classes6.dex */
public interface LineChartViewPresenter {
    void initLeftArrowClick();

    void initPageParams();

    void initRightArrowClick();

    void notifyCursorDataAndTime(String str, List<HwHealthMarkerView.a> list);

    void notifyData(int i, int i2);

    void notifyMaxAndMin(int i, List<HiHealthData> list);

    void notifyRemindData(int i, List<HiHealthData> list);

    long prossCalendarSelect(HealthCalendar healthCalendar);

    void startCalendar(Fragment fragment, HealthCalendar healthCalendar);

    void startCalendarWithDataType(Fragment fragment, HealthCalendar healthCalendar, String str, DataInfos dataInfos);
}

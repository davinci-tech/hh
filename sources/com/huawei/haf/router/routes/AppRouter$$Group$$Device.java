package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.ui.device.activity.adddevice.AllDeviceListActivity;
import com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity;
import com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity;
import com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity;
import com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity;
import com.huawei.ui.device.activity.menstrualpredict.MenstrualPredictActivity;
import com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity;
import com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity;
import com.huawei.ui.device.activity.questionsuggestions.WeightQuestionSuggestionActivity;
import com.huawei.ui.device.activity.sleepbreathe.SleepBreatheActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$Device implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/Device/AllDeviceListActivity", RouteMeta.build(RouteType.ACTIVITY, AllDeviceListActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/Device/ContinueHeartRateSettingActivity", RouteMeta.build(RouteType.ACTIVITY, ContinueHeartRateSettingActivity.class, -1, 83886080, null));
        map.put("/Device/CoreSleepSelectorActivity", RouteMeta.build(RouteType.ACTIVITY, CoreSleepSelectorActivity.class, -1, 83886080, null));
        map.put("/Device/CycleBloodOxygenSettingActivity", RouteMeta.build(RouteType.ACTIVITY, CycleBloodOxygenSettingActivity.class, -1, 83886080, null));
        map.put("/Device/EmotionAutoMonitorActivity", RouteMeta.build(RouteType.ACTIVITY, EmotionAutoMonitorActivity.class, -1, 16777216, null));
        map.put("/Device/MenstrualPredictActivity", RouteMeta.build(RouteType.ACTIVITY, MenstrualPredictActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/Device/PressAutoMonitorActivity", RouteMeta.build(RouteType.ACTIVITY, PressAutoMonitorActivity.class, -1, 83886080, null));
        map.put("/Device/QuestionSuggestionActivity", RouteMeta.build(RouteType.ACTIVITY, QuestionSuggestionActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/Device/SleepBreatheActivity", RouteMeta.build(RouteType.ACTIVITY, SleepBreatheActivity.class, -1, 83886080, null));
        map.put("/Device/WeightQuestionSuggestionActivity", RouteMeta.build(RouteType.ACTIVITY, WeightQuestionSuggestionActivity.class, -1, Integer.MIN_VALUE, null));
    }
}

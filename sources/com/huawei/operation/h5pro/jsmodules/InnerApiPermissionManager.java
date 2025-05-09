package com.huawei.operation.h5pro.jsmodules;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes9.dex */
public class InnerApiPermissionManager {
    private static final Set<String> ROUTE_TO_NATIVE_PAGE_SET;

    static {
        HashSet hashSet = new HashSet();
        ROUTE_TO_NATIVE_PAGE_SET = hashSet;
        hashSet.add("/RecognizeKit/HealthRecognizeActivity");
        hashSet.add("/Main/BarCodeScanningActivity");
        hashSet.add("/HWUserProfileMgr/UserInfoActivity");
        hashSet.add("/TradeService/RedeemCodeScanActivity");
        hashSet.add("/Main/WeightBodyDataActivity");
        hashSet.add("/Main/WeightAiBodyDataActivity");
        hashSet.add("/PluginDevice/DeviceMainActivity");
        hashSet.add("/Device/AllDeviceListActivity");
        hashSet.add("/Main/WeightUnitSettingActivity");
        hashSet.add("/Main/ClaimMeasureDataActivity");
        hashSet.add("/Main/ClaimMeasureNoDataActivity");
        hashSet.add("/Main/HealthDataHistoryActivity");
        hashSet.add("/Main/WeightUserManagerActivity");
        hashSet.add("/Main/AddOrEditWeightUserActivity");
        hashSet.add("/Main/InputWeightActivity");
        hashSet.add("/Main/WeightDetailActivity");
        hashSet.add("/Main/WeightGoalActivity");
        hashSet.add("/Main/WeightGoalPreviewActivity");
        hashSet.add("/Main/LightFastingStatusActivity");
        hashSet.add("/Main/WeightEditShareActivity");
        hashSet.add("/Main/FitnessSportIntensityDetailActivity");
        hashSet.add("/Main/FitnessStepDetailActivity");
        hashSet.add("/Main/FitnessCalorieDetailActivity");
        hashSet.add("/Main/KnitActiveHoursActivity");
        hashSet.add("/HomeWear21/WearHomeActivity");
        hashSet.add("/PluginFitnessAdvice/SportAllCourseActivity");
    }

    private InnerApiPermissionManager() {
    }

    public static boolean checkAccessToTargetPage(String str) {
        return ROUTE_TO_NATIVE_PAGE_SET.contains(str);
    }
}

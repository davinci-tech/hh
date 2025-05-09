package com.huawei.operation.utils;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class AbilitySetUtils {
    private static final int ABILITY_SUPPORT_VALUE = 1;
    private static final String ACTIVITY_VIP_ABILITY = "activityVip";
    private static final int DEFAULT_APP_TYPE = 0;
    private static final String FITNESS_DATA_ABILITY = "fitness";
    private static final String HEALTH_DETAIL_ABILITY = "healthDetail";
    private static final String HEALTH_LIFE_ABILITY = "healthLife";
    private static final String HEALTH_STAT_ABILITY = "healthStat";
    private static final int MOTION_PATH = 3;
    private static final String MOTION_PATH_ABILITY = "motionPath";
    private static final String PAY_ABILITY = "pay";
    private static final String SCHEME_ABILITY = "scheme";
    private static final String SECURITY_ABILITY = "security";
    private static final String SOCIAL_ABILITY = "sociality";
    private static final String SYSTEM_ABILITY = "system";

    private AbilitySetUtils() {
    }

    public static String getAbilitySet() {
        AbilityObj abilityObj = new AbilityObj();
        abilityObj.add(MOTION_PATH_ABILITY, 3);
        abilityObj.add(SOCIAL_ABILITY, 1);
        abilityObj.add(PAY_ABILITY, 1);
        abilityObj.add(SCHEME_ABILITY, 1);
        abilityObj.add(FITNESS_DATA_ABILITY, 1);
        abilityObj.add(SYSTEM_ABILITY, 1);
        abilityObj.add("security", 1);
        abilityObj.add(HEALTH_DETAIL_ABILITY, 1);
        abilityObj.add(HEALTH_STAT_ABILITY, 1);
        abilityObj.add(HEALTH_LIFE_ABILITY, 1);
        abilityObj.add(ACTIVITY_VIP_ABILITY, 1);
        abilityObj.setAppType(0);
        return new Gson().toJson(abilityObj);
    }

    static class AbilityObj {
        private Map<String, Integer> abilityMap = new HashMap(16);
        private int appType;

        AbilityObj() {
        }

        public void add(String str, int i) {
            this.abilityMap.put(str, Integer.valueOf(i));
        }

        public void setAppType(int i) {
            this.appType = i;
        }

        public int getAppType() {
            return this.appType;
        }
    }
}

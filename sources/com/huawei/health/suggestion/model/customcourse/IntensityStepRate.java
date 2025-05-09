package com.huawei.health.suggestion.model.customcourse;

import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class IntensityStepRate {
    public static final int DEFAULT_HIGH_VALUE = 180;
    public static final int DEFAULT_LOW_VALUE = 170;
    private static final int INTERVAL = 1;
    private static final int MAX_VALUE = 300;
    private static final int MIN_VALUE = 100;

    public static int getPositionByStepRateValue(int i) {
        return i - 100;
    }

    public static int getStepRateValueByPosition(int i) {
        return i + 100;
    }

    public static String[] getStepRateArray() {
        String[] strArr = new String[201];
        for (int i = 0; i < 201; i++) {
            strArr[i] = UnitUtil.e(i + 100, 1, 0);
        }
        return strArr;
    }
}

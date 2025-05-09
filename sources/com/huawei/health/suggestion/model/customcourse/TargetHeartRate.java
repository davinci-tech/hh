package com.huawei.health.suggestion.model.customcourse;

import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class TargetHeartRate {
    private static final int DEFAULT_VALUE = 50;
    private static final int MAX_VALUE = 100;
    private static final int MIN_VALUE = 20;

    public static int getDefaultValuePosition() {
        return 30;
    }

    public static int getHeartRateValueByPosition(int i) {
        return i + 20;
    }

    public static int getPositionByHeartRateValue(int i) {
        return i - 20;
    }

    public static String[] getHearRateArray() {
        String[] strArr = new String[81];
        for (int i = 0; i < 81; i++) {
            strArr[i] = UnitUtil.e(i + 20, 2, 0);
        }
        return strArr;
    }
}

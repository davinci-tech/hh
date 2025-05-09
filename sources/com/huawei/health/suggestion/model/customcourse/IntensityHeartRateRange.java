package com.huawei.health.suggestion.model.customcourse;

import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class IntensityHeartRateRange {
    public static final int DEFAULT_HIGH_VALUE = 70;
    public static final int DEFAULT_LOW_VALUE = 60;
    private static final int MAX_VALUE = 100;
    public static final int MIN_VALUE = 20;

    public static String[] getHearRateRangeArray() {
        String[] strArr = new String[81];
        for (int i = 0; i < 81; i++) {
            strArr[i] = UnitUtil.e(i + 20, 2, 0);
        }
        return strArr;
    }
}

package com.huawei.health.suggestion.model.customcourse;

import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes4.dex */
public class IntensityPace {
    public static final int DEFAULT_HIGH_MINUTE = 7;
    public static final int DEFAULT_HIGH_MINUTE_INCH = 12;
    public static final int DEFAULT_HIGH_SECOND = 30;
    public static final int DEFAULT_HIGH_SECOND_INCH = 0;
    public static final int DEFAULT_LOW_MINUTE = 6;
    public static final int DEFAULT_LOW_MINUTE_INCH = 10;
    public static final int DEFAULT_LOW_SECOND = 30;
    public static final int DEFAULT_LOW_SECOND_INCH = 30;
    private static final int MAX_MINUTE = 15;
    private static final int MAX_MINUTE_INCH = 24;
    private static final int MIN_MINUTE = 1;
    private static final int MIN_MINUTE_INCH = 2;
    private static final int SECOND = 60;

    public static String[] getPaceMinuteArray() {
        String[] strArr = new String[15];
        int i = 0;
        while (i < 15) {
            int i2 = i + 1;
            strArr[i] = String.format(Locale.ENGLISH, "%s'", UnitUtil.e(i2, 1, 0));
            i = i2;
        }
        return strArr;
    }

    public static String[] getPaceSecondArray() {
        String[] strArr = new String[60];
        for (int i = 0; i < 60; i++) {
            strArr[i] = String.format(Locale.ENGLISH, "%s''", UnitUtil.e(i, 1, 0));
        }
        return strArr;
    }

    public static String[] getPaceMinuteInchArray() {
        String[] strArr = new String[23];
        int i = 0;
        while (i < 23) {
            int i2 = i + 1;
            strArr[i] = String.format(Locale.ENGLISH, "%s'", UnitUtil.e(i2, 1, 0));
            i = i2;
        }
        return strArr;
    }
}

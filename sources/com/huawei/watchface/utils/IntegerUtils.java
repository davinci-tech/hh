package com.huawei.watchface.utils;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class IntegerUtils {
    public static final int DEFAULT_SIZE_VALUE = 32;

    private IntegerUtils() {
    }

    public static int a(String str, int i) {
        if (str == null) {
            HwLog.e("parseIntCheck", "Value is null");
            return -1;
        }
        try {
            return Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            HwLog.e("parseIntCheck", "Value is invalid :" + str);
            return -1;
        }
    }

    public static int a(String str) {
        return getInteger(str, 0);
    }

    public static int getInteger(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        String trim = str.trim();
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException e) {
            HwLog.e("parseIntCheck", "getInteger()->Integer.valueOf(" + trim + ") Exception=" + HwLog.printException((Exception) e));
            try {
                return (int) Float.parseFloat(trim);
            } catch (NumberFormatException e2) {
                HwLog.e("parseIntCheck", "getInteger()->Float.valueOf(" + trim + ") Exception=" + HwLog.printException((Exception) e2));
                return i;
            }
        }
    }

    public static int b(String str) {
        return b(str, 16);
    }

    public static long c(String str) {
        return c(str, 16);
    }

    public static int b(String str, int i) {
        try {
            return Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            HwLog.e("parseIntCheck", "parseIntByRadix exception, input is : " + str);
            return -1;
        }
    }

    public static long c(String str, int i) {
        try {
            return Long.parseLong(str, i);
        } catch (NumberFormatException unused) {
            HwLog.e("parseIntCheck", "parseInt64ByRadix exception, input is : " + str);
            return -1L;
        }
    }
}

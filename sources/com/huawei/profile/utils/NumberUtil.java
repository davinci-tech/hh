package com.huawei.profile.utils;

import android.text.TextUtils;
import com.huawei.profile.utils.logger.DsLog;

/* loaded from: classes6.dex */
public abstract class NumberUtil {
    private static final String TAG = "NumberUtil";

    public static Long parseLong(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            DsLog.et(TAG, "cant get long from string", new Object[0]);
            return null;
        }
    }

    public static Integer parseInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            DsLog.et(TAG, "cant get int from string", new Object[0]);
            return null;
        }
    }

    public static Double parseDouble(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            DsLog.et(TAG, "cant get double from string", new Object[0]);
            return null;
        }
    }
}

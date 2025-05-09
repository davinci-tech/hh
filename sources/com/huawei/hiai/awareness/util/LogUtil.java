package com.huawei.hiai.awareness.util;

import android.util.Log;

/* loaded from: classes4.dex */
public class LogUtil {
    public static final String LOG_TAG = "CAWARENESS_CLIENT_";

    private LogUtil() {
    }

    public static void i(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        Log.i(LOG_TAG + str, str2);
    }

    public static void e(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        Log.e(LOG_TAG + str, str2);
    }
}

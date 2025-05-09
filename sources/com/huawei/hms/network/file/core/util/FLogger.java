package com.huawei.hms.network.file.core.util;

import com.huawei.hms.framework.common.Logger;

/* loaded from: classes4.dex */
public class FLogger {
    private static final boolean DEBUG = false;
    private static final String FLOG_TAG = "file_request";

    public static void w(String str, String str2, Object... objArr) {
        Logger.w("file_request-" + str, str2, objArr);
    }

    public static void v(String str, String str2) {
        Logger.v(FLOG_TAG + str, str2);
    }

    public static void logException(Exception exc) {
        Logger.v(FLOG_TAG, "logException :" + exc.getMessage());
    }

    public static void i(String str, String str2, Object... objArr) {
        Logger.i("file_request-" + str, str2, objArr);
    }

    public static void e(String str, String str2, Throwable th) {
        Logger.e("file_request-" + str, str2, th);
    }

    public static void e(String str, String str2) {
        Logger.e("file_request-" + str, str2);
    }

    public static void d(String str, String str2, Object... objArr) {
        Logger.d("file_request-" + str, str2, objArr);
    }
}

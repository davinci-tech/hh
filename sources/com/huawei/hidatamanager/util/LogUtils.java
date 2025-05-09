package com.huawei.hidatamanager.util;

import android.util.Log;

/* loaded from: classes4.dex */
public final class LogUtils {
    private static boolean mIsILogCanPrint = true;
    private static boolean mSystemDebugOn = true;

    public static void setSystemDebugOn(boolean z) {
        mSystemDebugOn = z;
    }

    public static boolean getSystemDebugOn() {
        return mSystemDebugOn;
    }

    public static final void v(String str, String str2) {
        if (mSystemDebugOn) {
            Log.v(str, str2);
        }
    }

    public static final void d(String str, String str2) {
        if (mSystemDebugOn) {
            Log.d(str, str2);
        }
    }

    public static final void i(String str, String str2) {
        if (mIsILogCanPrint) {
            Log.i(str, str2);
        }
    }

    public static final void w(String str, String str2) {
        Log.w(str, str2);
    }

    public static final void e(String str, String str2) {
        Log.e(replaceBlank(str), str2);
    }

    public static final void e(String str, String str2, Throwable th) {
        Log.e(replaceBlank(str), str2, th);
    }

    public static String replaceBlank(String str) {
        return str.replaceAll("\t|\r|\n", "");
    }
}

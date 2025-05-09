package com.huawei.hms.framework.common;

import android.content.Context;
import android.os.Process;

/* loaded from: classes4.dex */
public class ContextCompat {
    private static final String TAG = "ContextCompat";

    public static boolean checkSelfPermission(Context context, String str) {
        if (context == null || str == null) {
            Logger.w(TAG, "param is null");
            return false;
        }
        try {
            return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
        } catch (RuntimeException e) {
            Logger.e(TAG, "dealType rethrowFromSystemServer:", e);
            return false;
        }
    }

    public static Context getProtectedStorageContext(Context context) {
        if (context == null) {
            Logger.w(TAG, "context is null");
            return null;
        }
        return context.createDeviceProtectedStorageContext();
    }

    public static Object getSystemService(Context context, String str) {
        if (context == null) {
            Logger.w(TAG, "context is null");
            return null;
        }
        try {
            return context.getSystemService(str);
        } catch (RuntimeException e) {
            Logger.e(TAG, "SystemServer error:", e);
            return null;
        }
    }
}

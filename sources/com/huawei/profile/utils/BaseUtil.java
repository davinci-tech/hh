package com.huawei.profile.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/* loaded from: classes6.dex */
public class BaseUtil {
    public static final int CURRENT_USER = -2;
    private static final int END_NUM = 5;
    private static final int FRONT_NUM = 5;
    public static final String HEADLESS_SYTEM_USER = "ro.fw.mu.headless_system_user";
    public static final String HEALTH_PACKAGE_NAME = "com.huawei.health";
    private static final String INVALID_VERSION_NAME = "10.0.0.200";
    public static final String IS_HOST = "__isHost";
    public static final String IS_NEAR_FIELD = "__isNearField";
    public static final String PROFILE_PACKAGE_NAME = "com.huawei.profile";
    private static final String TAG = "ProfileBaseUtil";
    public static final String UNEXPECTED_EXCEPTION = " with Unexpected runtimeException";
    public static final String UNINSTALL_ALL_USERS = "android.intent.extra.UNINSTALL_ALL_USERS";
    public static final int USER0 = 0;
    private static boolean isInApk = false;

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj) {
        return obj;
    }

    private BaseUtil() {
    }

    public static <T> T cast(Object obj, Class<T> cls) {
        if (cls == null) {
            Log.e(TAG, "result object is null");
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (ClassCastException unused) {
            Log.e(TAG, "failed to cast class " + cls.getName());
            return null;
        }
    }

    public static void markCallInterface(String str, String str2) {
        Log.i(TAG, "start to call " + str + " function, caller is " + str2);
    }

    public static boolean isProfileApkInstalled(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (context.getPackageManager() == null) {
                Log.e(TAG, "get package manager failed.");
                return false;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.profile", 0);
            if (packageInfo == null) {
                Log.w(TAG, "there's no matched pkg.");
                return false;
            }
            if (INVALID_VERSION_NAME.equals(packageInfo.versionName)) {
                Log.e(TAG, "profile apk, not to connect.");
                return false;
            }
            Log.i(TAG, "profile apk is installed.");
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "profile package name not found, profile apk may not installed");
            return false;
        } catch (RuntimeException unused2) {
            Log.e(TAG, "Failed to get installed package with Unexpected runtimeException");
            return false;
        }
    }

    public static String anonymousContent(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length <= 10) {
            return str;
        }
        return str.substring(0, 5) + "*******" + str.substring(length - 5);
    }

    public static boolean isInApk() {
        return isInApk;
    }

    public static void setIsInApk(Context context) {
        if (context == null || !context.getPackageName().equals("com.huawei.profile")) {
            return;
        }
        isInApk = true;
    }
}

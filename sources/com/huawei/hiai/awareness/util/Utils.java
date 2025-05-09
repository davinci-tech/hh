package com.huawei.hiai.awareness.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.hiai.awareness.AwarenessConstants;

/* loaded from: classes4.dex */
public class Utils {
    public static final String CAWARENESS_PACKAGE_NAME = "com.huawei.hiai";
    private static final String COMMA_KEY = ",";
    private static final String TAG = "sdk_Utils";

    private Utils() {
    }

    public static boolean checkApkExist(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    return packageManager.getApplicationInfo(str, 8192) != null;
                }
                LogUtil.e(TAG, "packageManager == null");
                return false;
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.e(TAG, "checkApkExist false PackageManager.NameNotFoundException");
            }
        }
        return false;
    }

    public static String getFenceKey(int i, int i2, int i3, String str) {
        if (i == 0 || i2 == -1 || i3 == -1) {
            return "";
        }
        String str2 = i + "," + i2 + "," + i3;
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return str2 + AwarenessConstants.SECOND_ACTION_SPLITE_TAG + str;
    }
}

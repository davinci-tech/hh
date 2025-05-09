package com.huawei.watchface.utils;

import android.content.Context;
import android.os.Build;

/* loaded from: classes9.dex */
public class PowerThemeHelper {
    private static final String TAG = "PowerThemeHelper";

    public static boolean isDarkMode(Context context) {
        if (context == null) {
            HwLog.w(TAG, "isDarkMode() contxet is null");
            return false;
        }
        HwLog.w(TAG, "isDarkMode() enter.");
        return Build.VERSION.SDK_INT > 28 && (context.getResources().getConfiguration().uiMode & 48) == 32;
    }
}

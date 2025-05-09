package com.huawei.ui.openservice.db;

import android.content.Context;

/* loaded from: classes7.dex */
public class SpUtil {
    private static final String SP = "OpenServiceSpUtil";
    private static final String UPGRADE = "Upgrade";

    public static boolean isUpgrade(Context context) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(SP, 0).getBoolean(UPGRADE, false);
    }

    public static void setUpgrade(Context context, boolean z) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences(SP, 0).edit().putBoolean(UPGRADE, z).apply();
    }
}

package com.huawei.watchface.flavor;

import android.app.Activity;
import com.huawei.watchface.cv;

/* loaded from: classes7.dex */
public class FlavorConfig {
    public static final boolean IS_TEST = false;
    public static final String MCP_APP_ID = "100160093";
    public static final String SERVICE_WATCH_FACE = "com.huawei.watchface";
    private static final String TAG = "FlavorConfig";

    public static void addAodTestData() {
    }

    public static void openWebviewDebugMode() {
    }

    public static void printAnalyticsLog(int i, String str, String str2) {
    }

    public static void printNetworkLog(String str, String str2, String str3, String str4) {
    }

    public static void safeHwLog(String str, String str2) {
    }

    public static void irrupt(Activity activity) {
        cv.a(activity);
    }
}

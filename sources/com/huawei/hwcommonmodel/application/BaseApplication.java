package com.huawei.hwcommonmodel.application;

import android.app.Activity;
import android.content.Context;
import com.huawei.haf.application.BaseApplicationCompat;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public final class BaseApplication {
    public static final String APP_PACKAGE_GOOGLE_HEALTH = "com.betterme.health";
    public static final String APP_PACKAGE_HEALTH = "com.huawei.health";
    public static final String APP_PACKAGE_HEALTH_TV = "com.huawei.aifitness";
    public static final String APP_PACKAGE_WEAR = "com.huawei.bone";
    private static final String TAG = "BaseApplication";

    private BaseApplication() {
    }

    public static void setContext(Context context) {
        if (context == null) {
            LogUtil.a(TAG, "setContext fail, context == null");
        } else {
            BaseApplicationCompat.e(context);
        }
    }

    public static Context getContext() {
        return com.huawei.haf.application.BaseApplication.e();
    }

    public static boolean isTv() {
        return APP_PACKAGE_HEALTH_TV.equals(getAppPackage());
    }

    public static String getAppPackage() {
        return com.huawei.haf.application.BaseApplication.d();
    }

    public static Activity getActivity() {
        return com.huawei.haf.application.BaseApplication.wa_();
    }

    public static boolean isRunningForeground() {
        return com.huawei.haf.application.BaseApplication.j();
    }
}

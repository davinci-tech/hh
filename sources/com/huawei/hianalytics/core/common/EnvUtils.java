package com.huawei.hianalytics.core.common;

import android.content.Context;

/* loaded from: classes4.dex */
public class EnvUtils {
    public static Context appContext;

    public static void setAppContext(Context context) {
        appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }
}

package com.huawei.hms.ads.identifier;

import android.content.Context;

/* loaded from: classes9.dex */
public abstract class AdsIdentifierSdk {
    private static Context appContext;

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        appContext = context.getApplicationContext();
        d.a(context);
        c.b(context);
    }

    public static Context getContext() {
        return appContext;
    }

    public static void destroy(Context context) {
        appContext = null;
        d.b(context);
    }
}

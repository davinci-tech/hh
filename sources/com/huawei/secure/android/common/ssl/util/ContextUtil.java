package com.huawei.secure.android.common.ssl.util;

import android.content.Context;

/* loaded from: classes6.dex */
public class ContextUtil {

    /* renamed from: a, reason: collision with root package name */
    private static Context f8633a;

    public static Context getInstance() {
        return f8633a;
    }

    public static void setContext(Context context) {
        if (context == null || f8633a != null) {
            return;
        }
        f8633a = context.getApplicationContext();
    }
}

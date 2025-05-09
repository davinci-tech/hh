package com.huawei.haf.router;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.router.core.AppRouterHelper;

/* loaded from: classes.dex */
public final class AppRouter {
    private AppRouter() {
    }

    public static Guidepost b(String str) {
        return AppRouterHelper.b(str);
    }

    public static Guidepost zi_(Uri uri) {
        return AppRouterHelper.zK_(uri);
    }

    public static <T> T a(Class<? extends T> cls) {
        return (T) AppRouterHelper.b(null, cls, null, null, false);
    }

    public static <T> T e(String str, Class<? extends T> cls) {
        return (T) AppRouterHelper.b(null, cls, str, null, false);
    }

    public static <T> T e(String str, Class<? extends T> cls, Context context, NaviConsumer naviConsumer, boolean z) {
        return (T) AppRouterHelper.b(context, cls, str, naviConsumer, z);
    }

    public static boolean c(Context context, String str) {
        return AppRouterHelper.c(context, str, true);
    }
}

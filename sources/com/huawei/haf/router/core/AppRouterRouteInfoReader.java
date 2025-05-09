package com.huawei.haf.router.core;

import com.huawei.haf.common.utils.CommonConstant;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;

/* loaded from: classes.dex */
final class AppRouterRouteInfoReader {
    private final Class<?> c;
    private final String e;

    AppRouterRouteInfoReader(String str, ClassLoader classLoader) {
        this.e = str;
        this.c = ReflectionUtils.d("com.huawei.haf.router.routes.AppRoute$$Info$$" + str, classLoader);
    }

    String[] h() {
        return this.c != null ? a("R_", "Roots") : CommonConstant.e;
    }

    String[] c() {
        return this.c != null ? a("P_", "Providers") : CommonConstant.e;
    }

    String[] d() {
        return this.c != null ? a("I_", "Interceptors") : CommonConstant.e;
    }

    String[] a() {
        return this.c != null ? a("A_", "Autowireds") : CommonConstant.e;
    }

    String[] b() {
        return this.c != null ? a("B_", "Bootstraps") : CommonConstant.e;
    }

    String[] e() {
        return this.c != null ? a("M_", "Names") : CommonConstant.e;
    }

    private String[] a(String str, String str2) {
        int d = d(str + "N", 0, str2);
        if (d == 0) {
            return CommonConstant.e;
        }
        String[] strArr = new String[d];
        for (int i = 0; i < d; i++) {
            strArr[i] = c(str + i, "", str2);
        }
        return strArr;
    }

    private String c(String str, String str2, String str3) {
        Object b = b(str, str3);
        return b instanceof String ? ((String) b).intern() : str2;
    }

    private int d(String str, int i, String str2) {
        Object b = b(str, str2);
        return b instanceof Integer ? ((Integer) b).intValue() : i;
    }

    private Object b(String str, String str2) {
        try {
            return ReflectionUtils.e(this.c, str).get(null);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            LogUtil.d("HAF_RouteInfo", "getRouteInfoValue, pluginName[", this.e, "] not exist ", str2);
            return null;
        }
    }
}

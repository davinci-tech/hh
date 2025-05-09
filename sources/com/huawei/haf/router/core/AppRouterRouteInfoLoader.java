package com.huawei.haf.router.core;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.router.facade.template.InterceptorGroup;
import com.huawei.haf.router.facade.template.RouteRoot;
import com.huawei.haf.router.facade.template.ServiceGroup;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
final class AppRouterRouteInfoLoader {
    private static volatile AppRouterEngine e;
    private static final Set<String> d = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final Map<String, String> b = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2152a = new Object();

    private AppRouterRouteInfoLoader() {
    }

    static AppRouterEngine d() {
        if (e == null) {
            synchronized (f2152a) {
                if (e == null) {
                    e = new AppRouterEngine();
                    LogUtil.c("HAF_AppRouter", "AppRouter init start");
                    a();
                    LogUtil.c("HAF_AppRouter", "AppRouter init success");
                }
            }
        }
        return e;
    }

    static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (d.contains(str) || a(str)) {
            return str;
        }
        Map<String, String> map = b;
        String str2 = map.get(str);
        if (TextUtils.isEmpty(str2)) {
            a(BaseApplication.e());
            str2 = map.get(str);
        }
        return TextUtils.isEmpty(str2) ? str : str2;
    }

    static boolean c(String str) {
        return AppBundle.c().isBuiltInModule(BaseApplication.e(), str);
    }

    static boolean a(String str) {
        return AppBundle.c().isBundleModule(str);
    }

    static boolean e(String str) {
        return d.contains(str) || AppBundle.c().isExistLocalModule(str);
    }

    static boolean c(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str) || d.contains(str)) {
            return true;
        }
        if (!a(str)) {
            String b2 = b(str);
            if (d(context, str, b2)) {
                return true;
            }
            str = b2;
        }
        if (d(context, str)) {
            return true;
        }
        return z && e(context, str);
    }

    static void a(Context context, String str) {
        synchronized (f2152a) {
            b(context, str);
        }
    }

    static void a(String str, String[] strArr) {
        if (a(str)) {
            synchronized (f2152a) {
                b(str, strArr);
            }
        }
    }

    private static void a() {
        String f = AppBundleBuildConfig.f();
        Set<String> set = d;
        if (set.contains(f)) {
            return;
        }
        b(BaseApplication.e(), f, true);
        String[] strArr = {"router", "approuter"};
        b(f, strArr);
        set.addAll(Arrays.asList(strArr));
        LogUtil.c("HAF_AppRouter", "initMainModuleRouteInfo success. mainModule=", f);
    }

    private static void a(Context context) {
        Set<String> installedModules = AppBundle.e().getInstalledModules();
        if (installedModules.isEmpty()) {
            return;
        }
        synchronized (f2152a) {
            Iterator<String> it = installedModules.iterator();
            while (it.hasNext()) {
                b(context, it.next());
            }
        }
    }

    private static boolean d(Context context, String str, String str2) {
        if (str.equals(str2) || !a(str2)) {
            if (d.contains(str2)) {
                return true;
            }
            a(context);
            return true;
        }
        return d.contains(str2);
    }

    private static boolean d(Context context, String str) {
        a(context);
        Set<String> set = d;
        if (set.contains(str)) {
            return true;
        }
        if (!AppBundle.c().loadLocalModules(Collections.singletonList(str))) {
            return false;
        }
        if (AppBundle.e().getInstalledModules().contains(str)) {
            a(context, str);
        }
        return set.contains(str);
    }

    private static boolean e(Context context, String str) {
        if (!c(str)) {
            return false;
        }
        AppBundle.e().loadPlugins(context, Collections.singletonList(str), new InnerInstallCallback(str));
        return d.contains(str);
    }

    private static void b(Context context, String str) {
        if (TextUtils.isEmpty(str) || !a(str)) {
            return;
        }
        b(context, str, false);
    }

    private static void b(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Set<String> set = d;
        if (set.contains(str)) {
            return;
        }
        ClassLoader classLoader = context.getClassLoader();
        AppRouterRouteInfoReader appRouterRouteInfoReader = new AppRouterRouteInfoReader(str, classLoader);
        String[] h = appRouterRouteInfoReader.h();
        if (h.length > 0) {
            c(str, h, classLoader);
        }
        String[] c = appRouterRouteInfoReader.c();
        if (c.length > 0) {
            b(str, c, classLoader);
        }
        String[] d2 = appRouterRouteInfoReader.d();
        if (d2.length > 0) {
            d(str, d2, classLoader);
        }
        String[] e2 = appRouterRouteInfoReader.e();
        if (e2.length > 0) {
            b(str, e2);
        }
        String[] a2 = appRouterRouteInfoReader.a();
        AppRouterHelper.e().a(a2);
        String[] b2 = appRouterRouteInfoReader.b();
        AppRouterHelper.c().c(classLoader, str, c);
        set.add(str);
        Object[] objArr = new Object[7];
        objArr[0] = "registerRouteInfo(R, P, I, A, B)=";
        objArr[1] = Arrays.asList(Integer.valueOf(h.length), Integer.valueOf(c.length), Integer.valueOf(d2.length), Integer.valueOf(a2.length), Integer.valueOf(b2.length));
        objArr[2] = ", apkModuleName=";
        objArr[3] = str;
        objArr[4] = ", moduleNames=";
        objArr[5] = z ? Integer.valueOf(e2.length) : Arrays.toString(e2);
        objArr[6] = " success.";
        LogUtil.c("HAF_AppRouter", objArr);
    }

    private static void c(String str, String[] strArr, ClassLoader classLoader) {
        try {
            for (String str2 : strArr) {
                Object newInstance = ReflectionUtils.c(str2, classLoader).newInstance();
                if (newInstance instanceof RouteRoot) {
                    ((RouteRoot) newInstance).loadInto(AppRouterStore.f2154a);
                }
            }
        } catch (Exception e2) {
            c("registerRouteRoot pluginName=", str, e2);
        }
    }

    private static void b(String str, String[] strArr, ClassLoader classLoader) {
        try {
            for (String str2 : strArr) {
                Object newInstance = ReflectionUtils.c(str2, classLoader).newInstance();
                if (newInstance instanceof ServiceGroup) {
                    ((ServiceGroup) newInstance).loadInto(AppRouterStore.e);
                }
            }
        } catch (Exception e2) {
            c("registerProvider pluginName=", str, e2);
        }
    }

    private static void d(String str, String[] strArr, ClassLoader classLoader) {
        try {
            for (String str2 : strArr) {
                Object newInstance = ReflectionUtils.c(str2, classLoader).newInstance();
                if (newInstance instanceof InterceptorGroup) {
                    ((InterceptorGroup) newInstance).loadInto(AppRouterStore.d);
                }
            }
        } catch (Exception e2) {
            c("registerInterceptors pluginName=", str, e2);
        }
    }

    private static void c(String str, String str2, Exception exc) {
        LogUtil.e("HAF_AppRouter", str, str2, ", ex=", LogUtil.a(exc));
    }

    private static void b(String str, String[] strArr) {
        String put;
        String intern = str.intern();
        for (String str2 : strArr) {
            if (!TextUtils.isEmpty(str2) && (put = b.put(str2.intern(), intern)) != null && !intern.equals(put)) {
                LogUtil.a("HAF_AppRouter", "registerModuleNames moduleName=", str2, " in multiple plugins[", intern, ", ", put, "]");
            }
        }
    }

    static class InnerInstallCallback implements AppBundleLauncher.InstallCallback {

        /* renamed from: a, reason: collision with root package name */
        private final String f2153a;

        InnerInstallCallback(String str) {
            this.f2153a = str;
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public boolean call(Context context, Intent intent) {
            AppRouterRouteInfoLoader.a(context, this.f2153a);
            return false;
        }
    }
}

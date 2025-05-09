package com.huawei.haf.router.core;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.haf.router.NaviConsumer;
import health.compact.a.LogUtil;
import java.util.Collections;

/* loaded from: classes.dex */
public final class AppRouterHelper {

    /* renamed from: a, reason: collision with root package name */
    private static volatile AppRouterAutowiredService f2144a;
    private static volatile AppRouterRouteConfigService c;
    private static volatile AppRouterServiceProviderService d;

    private AppRouterHelper() {
    }

    public static Guidepost b(String str) {
        return AppRouterRouteInfoLoader.d().e(str);
    }

    public static Guidepost d(String str, String str2) {
        return AppRouterRouteInfoLoader.d().b(str, str2);
    }

    public static Guidepost zK_(Uri uri) {
        return AppRouterRouteInfoLoader.d().zJ_(uri);
    }

    public static boolean c(Context context, String str, boolean z) {
        AppRouterRouteInfoLoader.d();
        return AppRouterRouteInfoLoader.c(context, AppRouterRouteInfoLoader.b(str), z);
    }

    public static Object d(Context context, Guidepost guidepost, int i, NaviCallback naviCallback) {
        if (i >= 0 || naviCallback != null) {
            guidepost.e(new Holder(i, naviCallback));
        }
        return AppRouterRouteInfoLoader.d().e(context, guidepost, i, naviCallback);
    }

    public static <T> T b(Context context, Class<? extends T> cls, String str, NaviConsumer naviConsumer, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (context == null) {
                context = BaseApplication.e();
            }
            if (str.charAt(0) != '/') {
                return (T) d(context, cls, str, naviConsumer, z);
            }
            return (T) c(context, cls, AppRouterRouteInfoLoader.d().e(str), naviConsumer, z);
        }
        return (T) AppRouterRouteInfoLoader.d().a(cls);
    }

    public static <T> T e(Class<? extends T> cls) {
        return (T) AppRouterRouteInfoLoader.d().a(cls);
    }

    static AppRouterRouteConfigService a() {
        if (c == null) {
            c = new AppRouterRouteConfigService();
        }
        return c;
    }

    static AppRouterAutowiredService e() {
        if (f2144a == null) {
            f2144a = new AppRouterAutowiredService();
        }
        return f2144a;
    }

    static AppRouterServiceProviderService c() {
        if (d == null) {
            d = new AppRouterServiceProviderService();
        }
        return d;
    }

    static Object d(Guidepost guidepost) {
        Object k = guidepost.k();
        if (!(k instanceof Holder)) {
            return null;
        }
        Holder holder = (Holder) k;
        guidepost.e(null);
        return holder;
    }

    static boolean d(Context context, Guidepost guidepost, String str, Object obj, boolean z) {
        RenavigateCallback renavigateCallback = new RenavigateCallback(context, guidepost, obj);
        Holder holder = obj instanceof Holder ? (Holder) obj : null;
        if (z || (holder != null && holder.f2145a)) {
            d(context, str, new InnerInstallCallback(str, renavigateCallback, true));
        } else {
            e(context, str, new InnerInstallCallback(str, renavigateCallback, false));
        }
        return false;
    }

    static void d(Context context, Guidepost guidepost, Object obj) {
        int i;
        NaviCallback naviCallback = null;
        Holder holder = obj instanceof Holder ? (Holder) obj : null;
        if (holder != null) {
            guidepost.e(holder);
            i = holder.d;
            naviCallback = holder.e;
        } else {
            i = -1;
        }
        AppRouterRouteInfoLoader.d().e(context, guidepost, i, naviCallback);
    }

    static NaviCallback a(Object obj) {
        Holder holder = obj instanceof Holder ? (Holder) obj : null;
        if (holder != null) {
            return holder.e;
        }
        return null;
    }

    private static <T> T d(Context context, Class<? extends T> cls, String str, NaviConsumer naviConsumer, boolean z) {
        String str2;
        String str3;
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            str3 = indexOf != str.length() + (-1) ? str.substring(indexOf + 1) : null;
            str2 = substring;
        } else {
            str2 = str;
            str3 = null;
        }
        String b = AppRouterRouteInfoLoader.b(str2);
        if (AppRouterRouteInfoLoader.c(context, b, false)) {
            return (T) d(cls, str2, str3, naviConsumer);
        }
        Holder holder = new Holder(cls, naviConsumer, z);
        if (z) {
            d(context, b, new InnerInstallCallback(b, str2, str3, holder, true));
        } else {
            e(context, b, new InnerInstallCallback(b, str2, str3, holder, false));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T d(Class<? extends T> cls, String str, String str2, NaviConsumer naviConsumer) {
        T t = (T) c().c(str, cls, str2);
        if (naviConsumer != null && t != null) {
            naviConsumer.accept(t);
        }
        return t;
    }

    private static <T> T c(Context context, Class<? extends T> cls, Guidepost guidepost, NaviConsumer naviConsumer, boolean z) {
        String g = guidepost.g();
        String b = AppRouterRouteInfoLoader.b(g);
        if (AppRouterRouteInfoLoader.c(context, b, false)) {
            return (T) AppRouterRouteInfoLoader.d().d(cls, guidepost, naviConsumer);
        }
        Holder holder = new Holder(cls, naviConsumer, z);
        if (z) {
            d(context, b, new InnerInstallCallback(b, g, guidepost, holder, true));
            return null;
        }
        e(context, b, new InnerInstallCallback(b, g, guidepost, holder, false));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context, String str, AppBundleLauncher.InstallCallback installCallback) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new RenavigateCallback(context, str, installCallback));
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.putExtra("moduleName", str);
        AppBundle.e().launchActivity(context, intent, installCallback);
    }

    private static void e(Context context, String str, AppBundleLauncher.InstallCallback installCallback) {
        AppBundle.e().loadPlugins(context, Collections.singletonList(str), installCallback);
    }

    static class RenavigateCallback implements AppBundleLauncher.InstallCallback, Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final AppBundleLauncher.InstallCallback f2147a;
        private final Object b;
        private final String c;
        private final Guidepost d;
        private final Context e;

        RenavigateCallback(Context context, Guidepost guidepost, Object obj) {
            this.e = context;
            this.d = guidepost;
            this.b = obj;
            this.c = null;
            this.f2147a = null;
        }

        RenavigateCallback(Context context, String str, AppBundleLauncher.InstallCallback installCallback) {
            this.e = context;
            this.d = null;
            this.b = null;
            this.c = str;
            this.f2147a = installCallback;
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public boolean call(Context context, Intent intent) {
            AppRouterHelper.d(this.e, this.d, this.b);
            return false;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppRouterHelper.d(this.e, this.c, this.f2147a);
        }
    }

    static class InnerInstallCallback<T> implements AppBundleLauncher.InstallCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Holder<T> f2146a;
        private final boolean b;
        private final AppBundleLauncher.InstallCallback c;
        private final String d;
        private final Guidepost e;
        private final String g;
        private final String j;

        InnerInstallCallback(String str, String str2, Guidepost guidepost, Holder<T> holder, boolean z) {
            this.j = str;
            this.c = null;
            this.b = z;
            this.f2146a = holder;
            this.e = guidepost;
            this.g = str2;
            this.d = null;
        }

        InnerInstallCallback(String str, String str2, String str3, Holder<T> holder, boolean z) {
            this.j = str;
            this.c = null;
            this.b = z;
            this.f2146a = holder;
            this.e = null;
            this.g = str2;
            this.d = str3;
        }

        InnerInstallCallback(String str, AppBundleLauncher.InstallCallback installCallback, boolean z) {
            this.j = str;
            this.c = installCallback;
            this.b = z;
            this.f2146a = null;
            this.e = null;
            this.g = null;
            this.d = null;
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public boolean call(Context context, Intent intent) {
            AppRouterRouteInfoLoader.a(context, this.j);
            Object[] objArr = new Object[2];
            objArr[0] = this.b ? "gotoInstallActivity" : "gotoInstallPlugin";
            objArr[1] = " InstallCallback success.";
            LogUtil.c("HAF_AppRouter", objArr);
            AppBundleLauncher.InstallCallback installCallback = this.c;
            if (installCallback != null) {
                installCallback.call(context, intent);
                return false;
            }
            Holder<T> holder = this.f2146a;
            if (holder == null) {
                return false;
            }
            if (holder.d >= 0 || this.f2146a.e != null) {
                AppRouterRouteInfoLoader.d().e(context, this.e, this.f2146a.d, this.f2146a.e);
                return false;
            }
            if (this.f2146a.c == null) {
                return false;
            }
            if (this.e == null) {
                AppRouterHelper.d(this.f2146a.c, this.g, this.d, this.f2146a.b);
            } else {
                AppRouterRouteInfoLoader.d().d(this.f2146a.c, this.e, this.f2146a.b);
            }
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public void onFailed(Context context, Intent intent) {
            Object[] objArr = new Object[2];
            objArr[0] = this.b ? "gotoInstallActivity" : "gotoInstallPlugin";
            objArr[1] = " InstallCallback failed.";
            LogUtil.c("HAF_AppRouter", objArr);
            AppBundleLauncher.InstallCallback installCallback = this.c;
            if (installCallback != null) {
                installCallback.onFailed(context, intent);
            }
        }
    }

    static class Holder<T> {

        /* renamed from: a, reason: collision with root package name */
        final boolean f2145a;
        final NaviConsumer<T> b;
        final Class<? extends T> c;
        final int d;
        final NaviCallback e;

        Holder(int i, NaviCallback naviCallback) {
            this.d = i;
            this.e = naviCallback;
            this.c = null;
            this.b = null;
            this.f2145a = false;
        }

        Holder(Class<? extends T> cls, NaviConsumer<T> naviConsumer, boolean z) {
            this.d = -1;
            this.e = null;
            this.c = cls;
            this.b = naviConsumer;
            this.f2145a = z;
        }
    }
}

package com.huawei.haf.router.core;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouterResultReceiver;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.haf.router.NaviConsumer;
import com.huawei.haf.router.NaviPluginCallback;
import com.huawei.haf.router.facade.service.ExtendRouteService;
import com.huawei.haf.router.facade.service.PretreatmentService;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;

/* loaded from: classes.dex */
final class AppRouterPretreatmentService {
    private final AppRouterRouteConfigService d = AppRouterHelper.a();
    private final ExtendRouteService c = (ExtendRouteService) AppRouterHelper.e(ExtendRouteService.class);

    private boolean a(int i) {
        return (i & 2097152) == 2097152;
    }

    private boolean c(int i) {
        return (i & 131072) == 131072;
    }

    private boolean d(int i) {
        return (i & 1048576) == 1048576;
    }

    private boolean e(int i) {
        return (i & 4194304) == 4194304;
    }

    AppRouterPretreatmentService() {
    }

    boolean a(Guidepost guidepost) {
        int i;
        Object d = AppRouterHelper.d(guidepost);
        String g = guidepost.g();
        if ("approuter".equals(g)) {
            return true;
        }
        String m = guidepost.m();
        if (TextUtils.isEmpty(g)) {
            LogUtil.a("HAF_Pretreatment", "without group information, it is illegal, path=", m);
            c(guidepost, d);
            return false;
        }
        String b = ProcessUtil.b(this.d.e(m));
        ExtendRouteService extendRouteService = this.c;
        ExtendRouteService.RouteInfo tryExtractRouteInfo = extendRouteService != null ? extendRouteService.tryExtractRouteInfo(guidepost) : null;
        if (tryExtractRouteInfo != null) {
            String moduleOrGroup = tryExtractRouteInfo.getModuleOrGroup();
            if (!TextUtils.isEmpty(moduleOrGroup)) {
                g = moduleOrGroup;
            }
            i = tryExtractRouteInfo.getPathExtras();
            String runningProcess = tryExtractRouteInfo.getRunningProcess();
            if (!TextUtils.isEmpty(runningProcess)) {
                b = runningProcess;
            }
        } else {
            i = 0;
        }
        String c = c(m, AppRouterRouteInfoLoader.b(g));
        int b2 = b(m, i);
        int f = guidepost.f();
        try {
            guidepost.c(b2);
            if (!d(guidepost, c, b, d)) {
                return false;
            }
            guidepost.c(f);
            Context a2 = guidepost.a();
            boolean b3 = b(b2);
            if (AppRouterRouteInfoLoader.c(a2, c, false)) {
                return b(a2, guidepost, c, d, b3);
            }
            return AppRouterHelper.d(a2, guidepost, c, d, b3);
        } finally {
            guidepost.c(f);
        }
    }

    private boolean d(Guidepost guidepost, String str, String str2, Object obj) {
        NaviCallback a2 = AppRouterHelper.a(obj);
        if (a2 == null) {
            a2 = new AppRouterResultReceiver();
        }
        if (TextUtils.isEmpty(str) || !AppRouterRouteInfoLoader.a(str)) {
            d(guidepost, a2);
            return e(guidepost, str, str2, a2);
        }
        if (AppRouterRouteInfoLoader.e(str) || AppRouterRouteInfoLoader.c(str)) {
            a(guidepost, a2, str);
            return e(guidepost, str, str2, a2);
        }
        b(guidepost, a2, str);
        return e(guidepost, str, str2, a2);
    }

    private boolean e(Guidepost guidepost, String str, String str2, NaviCallback naviCallback) {
        if (this.c == null) {
            return true;
        }
        if (d(guidepost, naviCallback, str2) && this.c.handleStartComponent(guidepost, naviCallback, str)) {
            return this.c.handleRoute(guidepost, naviCallback, str);
        }
        return false;
    }

    private boolean d(Guidepost guidepost, NaviCallback naviCallback, String str) {
        if (c(guidepost.f()) || ProcessUtil.b().equals(str)) {
            return true;
        }
        if (e(guidepost, naviCallback, str)) {
            return false;
        }
        return this.c.handleRunningProcess(guidepost, naviCallback, str);
    }

    private boolean e(Guidepost guidepost, NaviCallback naviCallback, String str) {
        Uri zS_ = this.d.zS_(str);
        if (zS_ == null) {
            return false;
        }
        guidepost.a().getContentResolver().call(zS_, "navigate", (String) null, AppRouterResultReceiver.zr_(guidepost, naviCallback));
        LogUtil.c("HAF_Pretreatment", "remote navigate, path=", guidepost.m(), ", target=", str);
        return true;
    }

    private boolean b(Context context, Guidepost guidepost, String str, Object obj, boolean z) {
        String a2 = this.d.a(guidepost.m());
        if (TextUtils.isEmpty(a2)) {
            return c(context, guidepost, obj, null);
        }
        PretreatmentService pretreatmentService = (PretreatmentService) AppRouterHelper.b(context, PretreatmentService.class, a2, null, false);
        if (pretreatmentService != null) {
            return c(context, guidepost, obj, pretreatmentService);
        }
        if (!AppRouterRouteInfoLoader.e(str)) {
            AppRouterHelper.b(context, PretreatmentService.class, a2, new RenavigateConsumer(context, guidepost, obj), z);
        }
        return false;
    }

    private boolean c(Context context, Guidepost guidepost, Object obj, PretreatmentService pretreatmentService) {
        if (pretreatmentService != null && !pretreatmentService.onPretreatment(context, guidepost)) {
            e(guidepost, obj);
            return false;
        }
        if (this.d.a("HAF_Pretreatment", guidepost.m(), null)) {
            return true;
        }
        c(guidepost, obj);
        return false;
    }

    private String c(String str, String str2) {
        boolean a2 = AppRouterRouteInfoLoader.a(str2);
        String str3 = a2 ? str2 : null;
        String a3 = this.d.a(str);
        String b = AppRouterUtils.b(a3);
        if (TextUtils.isEmpty(b)) {
            return str3;
        }
        String b2 = AppRouterRouteInfoLoader.b(b);
        if (str2.equals(b2) || !AppRouterRouteInfoLoader.a(b2)) {
            return str3;
        }
        if (a2 && AppRouterRouteInfoLoader.e(b2)) {
            if (a2 && !AppRouterRouteInfoLoader.e(str2)) {
                return str2;
            }
            LogUtil.a("HAF_Pretreatment", "getRealPluginName path=", str, " and pretreatmentPath=", a3, " is not in same plugin.");
        }
        return b2;
    }

    private void d(Guidepost guidepost, NaviCallback naviCallback) {
        ExtendRouteService extendRouteService = this.c;
        if (extendRouteService != null) {
            extendRouteService.onPluginNone(guidepost);
        }
        if (naviCallback instanceof NaviPluginCallback) {
            ((NaviPluginCallback) naviCallback).onPluginNone(guidepost);
        }
    }

    private void a(Guidepost guidepost, NaviCallback naviCallback, String str) {
        ExtendRouteService extendRouteService = this.c;
        if (extendRouteService != null) {
            extendRouteService.onPluginFound(guidepost, str);
        }
        if (naviCallback instanceof NaviPluginCallback) {
            ((NaviPluginCallback) naviCallback).onPluginFound(guidepost, str);
        }
    }

    private void b(Guidepost guidepost, NaviCallback naviCallback, String str) {
        ExtendRouteService extendRouteService = this.c;
        if (extendRouteService != null) {
            extendRouteService.onPluginLost(guidepost, str);
        }
        if (naviCallback instanceof NaviPluginCallback) {
            ((NaviPluginCallback) naviCallback).onPluginLost(guidepost, str);
        }
    }

    private void c(Guidepost guidepost, Object obj) {
        NaviCallback a2 = AppRouterHelper.a(obj);
        if (a2 != null) {
            a2.onLost(guidepost);
        }
    }

    private void e(Guidepost guidepost, Object obj) {
        NaviCallback a2 = AppRouterHelper.a(obj);
        if (a2 != null) {
            a2.onFound(guidepost);
        }
    }

    private int b(String str, int i) {
        int c = i | this.d.c(str);
        if (!this.d.g(str)) {
            c |= 131072;
        } else if (!TextUtils.isEmpty(this.d.e(str))) {
            c &= -131073;
        }
        if (!e(c)) {
            return c;
        }
        int i2 = c & (-6291457);
        return (BaseApplication.j() || d(i2)) ? i2 : i2 | 2097152;
    }

    private boolean b(int i) {
        return (a(i) || d(i)) ? false : true;
    }

    static class RenavigateConsumer<T> implements NaviConsumer<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Context f2149a;
        private final Guidepost c;
        private final Object d;

        RenavigateConsumer(Context context, Guidepost guidepost, Object obj) {
            this.f2149a = context;
            this.c = guidepost;
            this.d = obj;
        }

        @Override // com.huawei.haf.router.NaviConsumer
        public void accept(T t) {
            AppRouterHelper.d(this.f2149a, this.c, this.d);
        }
    }
}

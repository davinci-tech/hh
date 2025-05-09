package com.huawei.haf.router.core;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.haf.router.NaviConsumer;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.model.TypeKind;
import com.huawei.haf.router.facade.template.InterceptorHandler;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.haf.router.facade.template.ServiceProvider;
import com.huawei.haf.router.facade.template.SingleServiceProvider;
import com.huawei.openalliance.ad.constant.ParamConstants;
import health.compact.a.LogUtil;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
final class AppRouterEngine {

    /* renamed from: a, reason: collision with root package name */
    private static volatile AppRouterPathReplaceService f2141a;
    private static volatile AppRouterInterceptorService b;
    private static volatile AppRouterDegradeService c;
    private static volatile AppRouterPretreatmentService d;

    AppRouterEngine() {
    }

    Guidepost e(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new AppRouterHandleException("path parameter is invalid!");
        }
        String b2 = a().b(str);
        return c(b2, b(b2), true);
    }

    Guidepost b(String str, String str2) {
        return c(str, str2, false);
    }

    Guidepost zJ_(Uri uri) {
        if (uri == null || TextUtils.isEmpty(uri.toString())) {
            throw new AppRouterHandleException("uri parameter invalid!");
        }
        Uri zM_ = a().zM_(uri);
        return new Guidepost(zM_.getPath(), b(zM_.getPath()), zM_);
    }

    private static Guidepost c(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new AppRouterHandleException("path parameter is invalid!");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new AppRouterHandleException("group parameter is invalid!");
        }
        if (!z) {
            str = a().b(str);
        }
        return new Guidepost(str, str2, null);
    }

    private static String b(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str) && str.startsWith("/") && (indexOf = str.indexOf("/", 1)) != -1) {
            String substring = str.substring(1, indexOf);
            if (TextUtils.isEmpty(substring)) {
                throw new AppRouterHandleException("Extract the default group failed! There's nothing between 2 '/'!");
            }
            return substring;
        }
        throw new AppRouterHandleException("Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!");
    }

    <T> T a(Class<? extends T> cls) {
        if (!SingleServiceProvider.class.isAssignableFrom(cls)) {
            LogUtil.a("HAF_AppRouter", "The '", cls.getName(), "' does not implements SingleServiceProvider. It's recommended use navigate with path to obtain.");
        }
        String str = AppRouterStore.e.get(cls.getName());
        if (str != null) {
            return (T) d(cls, c(str, b(str), true), null);
        }
        return null;
    }

    <T> T d(Class<? extends T> cls, Guidepost guidepost, NaviConsumer naviConsumer) {
        T t = null;
        try {
            c(guidepost);
            T t2 = (T) guidepost.n();
            if (t2 == null) {
                return null;
            }
            if (!cls.isAssignableFrom(t2.getClass())) {
                LogUtil.a("HAF_AppRouter", "path:", guidepost.m(), ", ", t2.getClass().getName(), " is not ", cls.getName(), " class type.");
                return null;
            }
            if (naviConsumer != null) {
                try {
                    naviConsumer.accept(t2);
                } catch (AppRouterNotFoundException e) {
                    e = e;
                    t = t2;
                    LogUtil.a("HAF_AppRouter", "navigate service, ex=", LogUtil.a(e));
                    return t;
                }
            }
            return t2;
        } catch (AppRouterNotFoundException e2) {
            e = e2;
        }
    }

    Object e(Context context, Guidepost guidepost, int i, NaviCallback naviCallback) {
        if (!b().a(guidepost)) {
            return null;
        }
        try {
            c(guidepost);
            if (naviCallback != null) {
                naviCallback.onFound(guidepost);
            }
            if (guidepost.s()) {
                return e(guidepost, i, naviCallback);
            }
            e().a(guidepost, new InnerInterceptorCallback(guidepost, i, naviCallback));
            return null;
        } catch (AppRouterNotFoundException e) {
            LogUtil.a("HAF_AppRouter", "navigation path=", guidepost.m(), " fail, ex=", LogUtil.a(e));
            if (d()) {
                HandlerExecutor.e(new InnerDebugToastRunning(context, String.format(Locale.ENGLISH, "There's no route matched!%s Path = [%s]%s Group = [%s]", System.lineSeparator(), guidepost.m(), System.lineSeparator(), guidepost.g())));
            }
            if (naviCallback != null) {
                naviCallback.onLost(guidepost);
            } else {
                c().d(context, guidepost);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object e(final Guidepost guidepost, final int i, final NaviCallback naviCallback) {
        final Context a2 = guidepost.a();
        int i2 = AnonymousClass2.c[guidepost.l().getType().ordinal()];
        if (i2 == 1) {
            final Intent intent = new Intent(a2, guidepost.l().getRouteClass());
            intent.putExtras(guidepost.zB_());
            int i3 = guidepost.i();
            if (i3 != 0) {
                intent.setFlags(i3);
            }
            if (!(a2 instanceof Activity)) {
                intent.addFlags(268435456);
            }
            String e = guidepost.e();
            if (!TextUtils.isEmpty(e)) {
                intent.setAction(e);
            }
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.haf.router.core.AppRouterEngine.1
                @Override // java.lang.Runnable
                public void run() {
                    AppRouterEngine.zI_(i, a2, intent, guidepost, naviCallback);
                }
            });
        } else if (i2 == 2) {
            try {
                Object newInstance = guidepost.l().getRouteClass().newInstance();
                if (newInstance instanceof Fragment) {
                    ((Fragment) newInstance).setArguments(guidepost.zB_());
                } else if (newInstance instanceof androidx.fragment.app.Fragment) {
                    ((androidx.fragment.app.Fragment) newInstance).setArguments(guidepost.zB_());
                }
                return newInstance;
            } catch (IllegalAccessException | InstantiationException e2) {
                LogUtil.e("HAF_AppRouter", "Fetch fragment instance error, ex=", LogUtil.a(e2));
            }
        } else if (i2 == 3) {
            return guidepost.n();
        }
        return null;
    }

    /* renamed from: com.huawei.haf.router.core.AppRouterEngine$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[RouteType.values().length];
            c = iArr;
            try {
                iArr[RouteType.ACTIVITY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[RouteType.FRAGMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[RouteType.HANDLER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[RouteType.SERVICE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zI_(int i, Context context, Intent intent, Guidepost guidepost, NaviCallback naviCallback) {
        if (i >= 0) {
            if (context instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) context, intent, i, guidepost.zC_());
            } else {
                LogUtil.a("HAF_AppRouter", "Must use [navigate(activity, ...)] to support [startActivityForResult]");
            }
        } else {
            ActivityCompat.startActivity(context, intent, guidepost.zC_());
        }
        if (-1 != guidepost.c() && -1 != guidepost.b() && (context instanceof Activity)) {
            ((Activity) context).overridePendingTransition(guidepost.c(), guidepost.b());
        }
        if (naviCallback != null) {
            naviCallback.onArrival(guidepost);
        }
    }

    private void b(String str, RouteGroup routeGroup) throws IllegalAccessException, InstantiationException {
        Class<? extends RouteGroup> cls = AppRouterStore.f2154a.get(str);
        if (cls != null) {
            cls.newInstance().loadInto(AppRouterStore.b);
            AppRouterStore.f2154a.remove(str);
        }
        if (routeGroup != null) {
            routeGroup.loadInto(AppRouterStore.b);
        }
    }

    private void c(Guidepost guidepost) {
        synchronized (this) {
            RouteMeta routeMeta = AppRouterStore.b.get(guidepost.m());
            if (routeMeta != null) {
                d(guidepost, routeMeta);
                return;
            }
            if (!AppRouterStore.f2154a.containsKey(guidepost.g())) {
                throw new AppRouterNotFoundException("There is no route match the path [" + guidepost.m() + "], in group [" + guidepost.g() + "]");
            }
            try {
                try {
                    b(guidepost.g(), (RouteGroup) null);
                    if (d()) {
                        LogUtil.c("HAF_AppRouter", String.format(Locale.ENGLISH, "The group [%s] load %s, trigger by [%s]", guidepost.g(), "success", guidepost.m()));
                    }
                    c(guidepost);
                } catch (Throwable th) {
                    if (d()) {
                        LogUtil.c("HAF_AppRouter", String.format(Locale.ENGLISH, "The group [%s] load %s, trigger by [%s]", guidepost.g(), ParamConstants.CallbackMethod.ON_FAIL, guidepost.m()));
                    }
                    throw th;
                }
            } catch (IllegalAccessException | InstantiationException e) {
                throw new AppRouterHandleException("Fatal exception when loading group meta. [" + e.getMessage() + "]");
            }
        }
    }

    private static void d(Guidepost guidepost, RouteMeta routeMeta) {
        ServiceProvider serviceProvider;
        guidepost.d(routeMeta);
        Uri zN_ = guidepost.zN_();
        if (zN_ != null) {
            Map<String, Integer> paramsType = routeMeta.getParamsType();
            if (!CollectionUtils.e(paramsType)) {
                a(guidepost, paramsType);
            }
            guidepost.zG_("NTwe23AtQb8U", zN_);
        }
        int i = AnonymousClass2.c[routeMeta.getType().ordinal()];
        if (i == 2) {
            guidepost.r();
            return;
        }
        if (i != 3) {
            return;
        }
        Class<?> routeClass = routeMeta.getRouteClass();
        if (c(routeMeta)) {
            serviceProvider = d((Class<? extends ServiceProvider>) routeClass);
        } else {
            serviceProvider = AppRouterStore.h.get(routeClass);
            if (serviceProvider == null) {
                serviceProvider = d((Class<? extends ServiceProvider>) routeClass);
                AppRouterStore.h.put(routeClass, serviceProvider);
            }
        }
        guidepost.c(serviceProvider);
        guidepost.r();
    }

    private static boolean c(RouteMeta routeMeta) {
        return (routeMeta.getExtra() & 65536) == 65536;
    }

    private static ServiceProvider d(Class<? extends ServiceProvider> cls) {
        try {
            ServiceProvider newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            newInstance.init(BaseApplication.e());
            return newInstance;
        } catch (Exception e) {
            LogUtil.e("HAF_AppRouter", "Init provider failed, ex=", LogUtil.a(e));
            throw new AppRouterHandleException("Init provider failed!");
        }
    }

    private static void a(Guidepost guidepost, Map<String, Integer> map) {
        Map<String, String> zv_ = AppRouterUtils.zv_(guidepost.zN_());
        if (zv_.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            a(guidepost, entry.getValue(), entry.getKey(), zv_.get(entry.getKey()));
        }
    }

    private static void a(Guidepost guidepost, Integer num, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (num == null) {
            guidepost.e(str, str2);
            return;
        }
        int intValue = num.intValue();
        try {
            if (intValue == TypeKind.BOOLEAN.type()) {
                guidepost.e(str, Boolean.parseBoolean(str2));
            } else if (intValue == TypeKind.BYTE.type()) {
                guidepost.a(str, Byte.parseByte(str2));
            } else if (intValue == TypeKind.SHORT.type()) {
                guidepost.b(str, Short.parseShort(str2));
            } else if (intValue == TypeKind.INT.type()) {
                guidepost.c(str, Integer.parseInt(str2));
            } else if (intValue == TypeKind.LONG.type()) {
                guidepost.c(str, Long.parseLong(str2));
            } else if (intValue == TypeKind.CHAR.type()) {
                guidepost.d(str, d(str2));
            } else if (intValue == TypeKind.FLOAT.type()) {
                guidepost.d(str, Float.parseFloat(str2));
            } else if (intValue == TypeKind.DOUBLE.type()) {
                guidepost.d(str, Double.parseDouble(str2));
            } else if (intValue == TypeKind.STRING.type()) {
                guidepost.e(str, str2);
            } else if (intValue != TypeKind.OBJECT.type()) {
                LogUtil.a("HAF_AppRouter", "setValue not support key=", str, ", type=", Integer.valueOf(intValue), ", value=", str2);
            } else {
                guidepost.e(str, str2);
            }
        } catch (Exception e) {
            LogUtil.a("HAF_AppRouter", "setValue failed! key=", str, ", ex=", LogUtil.a(e));
        }
    }

    private static char d(String str) {
        if (TextUtils.isEmpty(str)) {
            return (char) 0;
        }
        if (str.length() != 1) {
            LogUtil.a("HAF_AppRouter", "parseChar value=", str);
        }
        return str.charAt(0);
    }

    private static boolean d() {
        return !LogUtil.a();
    }

    private static AppRouterPathReplaceService a() {
        if (f2141a == null) {
            synchronized (AppRouterEngine.class) {
                if (f2141a == null) {
                    f2141a = new AppRouterPathReplaceService();
                }
            }
        }
        return f2141a;
    }

    private static AppRouterPretreatmentService b() {
        if (d == null) {
            synchronized (AppRouterEngine.class) {
                if (d == null) {
                    d = new AppRouterPretreatmentService();
                }
            }
        }
        return d;
    }

    private static AppRouterInterceptorService e() {
        if (b == null) {
            synchronized (AppRouterEngine.class) {
                if (b == null) {
                    b = new AppRouterInterceptorService();
                }
            }
        }
        return b;
    }

    private static AppRouterDegradeService c() {
        if (c == null) {
            synchronized (AppRouterEngine.class) {
                if (c == null) {
                    c = new AppRouterDegradeService();
                }
            }
        }
        return c;
    }

    static class InnerInterceptorCallback implements InterceptorHandler.InterceptorCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Guidepost f2143a;
        private final NaviCallback c;
        private final int d;

        InnerInterceptorCallback(Guidepost guidepost, int i, NaviCallback naviCallback) {
            this.f2143a = guidepost;
            this.d = i;
            this.c = naviCallback;
        }

        @Override // com.huawei.haf.router.facade.template.InterceptorHandler.InterceptorCallback
        public void onContinue(Guidepost guidepost) {
            AppRouterEngine.e(guidepost, this.d, this.c);
        }

        @Override // com.huawei.haf.router.facade.template.InterceptorHandler.InterceptorCallback
        public void onInterrupt(Throwable th) {
            NaviCallback naviCallback = this.c;
            if (naviCallback != null) {
                naviCallback.onInterrupt(this.f2143a);
            }
            LogUtil.c("HAF_AppRouter", "Navigation failed, termination by interceptor, ex=", LogUtil.a(th));
        }
    }

    static class InnerDebugToastRunning implements Runnable {
        private final Context c;
        private final String e;

        InnerDebugToastRunning(Context context, String str) {
            this.c = context;
            this.e = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.c, this.e, 1).show();
        }
    }
}

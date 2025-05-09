package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.AuthorizationUtils;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public final class fbi {
    public static boolean a(Context context) {
        return !LoginInit.getInstance(context).isBrowseMode();
    }

    public static boolean d(Context context) {
        return AuthorizationUtils.a(context);
    }

    public static boolean c(Guidepost guidepost) {
        boolean z = guidepost.zB_().getBoolean("needLogin", false);
        return z ? z : AppRouterUtils.b(guidepost);
    }

    public static boolean b(Guidepost guidepost) {
        return AppRouterUtils.e(guidepost);
    }

    public static boolean d(Guidepost guidepost) {
        return AppRouterUtils.d(guidepost) && !MainInteractors.a();
    }

    public static Uri avx_(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return Uri.parse("huaweischeme://healthapp" + str);
        }
        return Uri.parse("huaweischeme://healthapp" + str + "?" + str2);
    }

    public static Uri avv_(Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        return zN_ == null ? avx_(guidepost.m(), null) : zN_;
    }

    public static String a(String str) {
        int indexOf;
        return (!str.contains("?") || (indexOf = str.indexOf("?")) <= 0) ? str : str.substring(0, indexOf);
    }

    public static void c(Context context) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setClassName(context, "com.huawei.health.MainActivity");
        intent.setFlags(536870912);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        } else {
            intent.addFlags(AppRouterExtras.COLDSTART);
        }
        context.startActivity(intent);
        LogUtil.c("Login_RouterUtils", "startMainActivity flags=0x", Integer.toHexString(intent.getFlags()), ", topActivity=", BaseApplication.wa_());
    }

    public static void b(Guidepost guidepost, NaviCallback naviCallback, boolean z) {
        Context a2 = guidepost.a();
        Intent intent = new Intent();
        intent.setPackage(a2.getPackageName());
        intent.setClassName(a2, "com.huawei.health.MainActivity");
        intent.setData(avv_(guidepost));
        intent.putExtra("needLogin", z);
        AppRouterUtils.zw_(intent, guidepost, naviCallback);
        intent.setFlags(536870912);
        if (!(a2 instanceof Activity)) {
            intent.addFlags(268435456);
        } else {
            intent.addFlags(AppRouterExtras.COLDSTART);
        }
        a2.startActivity(intent);
        LogUtil.c("Login_RouterUtils", "startMainActivity targetPath=", guidepost.m(), ", flags=0x", Integer.toHexString(intent.getFlags()), ", topActivity=", BaseApplication.wa_());
    }

    public static Uri avu_(Intent intent) {
        if (intent == null) {
            return null;
        }
        Uri data = AppRouterUtils.zt_(intent) ? intent.getData() : null;
        if (data == null || !"/home/main".equals(data.getPath())) {
            return data;
        }
        return null;
    }

    public static void avw_(Context context, Uri uri, Intent intent) {
        if (intent != null) {
            if (intent.getBooleanExtra("needLogin", false)) {
                boolean a2 = a(context);
                boolean d = d(context);
                if (!a2 || !d) {
                    LogUtil.c("Login_RouterUtils", "jumpToDeepLinkTarget return, isLogined=", Boolean.valueOf(a2), ", isAuthorizationStatus=", Boolean.valueOf(d));
                    return;
                }
            }
        } else {
            LogUtil.a("Login_RouterUtils", "jumpToDeepLinkTarget intent == null");
        }
        if (AppRouterUtils.zt_(intent)) {
            AppRouterUtils.zu_(context, intent);
        } else {
            AppRouter.zi_(uri).c(context);
            LogUtil.c("Login_RouterUtils", "jumpToDeepLinkTarget uri=", uri);
        }
    }
}

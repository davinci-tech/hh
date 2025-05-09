package com.huawei.wear.wallet.ui.dialog;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.framework.common.ReflectionUtils;
import defpackage.stq;
import defpackage.tll;

/* loaded from: classes9.dex */
public class WidgetBuilder {

    /* renamed from: a, reason: collision with root package name */
    private static IBuilder f11218a = null;
    private static boolean b = false;
    private static boolean c = false;
    private static final String d = "com.huawei.wear.wallet.ui.dialog.WidgetBuilder";

    public interface IBuilder {
        HwDialogInterface createDialog(Context context);

        HwProgressDialogInterface createProgressDialog(Context context);
    }

    static {
        d("com.huawei.cp3.widget.custom.BuilderCustom");
        c = "HONOR".equalsIgnoreCase(SystemProperties.get(SystemUtils.PRODUCT_BRAND, "")) && !SystemProperties.getBoolean("ro.config.hw_themeInsulate", false);
        b = SystemProperties.getBoolean("ro.config.hw_novaThemeSupport", false);
    }

    public static void d(IBuilder iBuilder) {
        f11218a = iBuilder;
    }

    public static HwDialogInterface d(Context context) {
        return e().createDialog(context);
    }

    public static HwProgressDialogInterface c(Context context) {
        return e().createProgressDialog(context);
    }

    private static boolean d(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static IBuilder e() {
        if (f11218a == null) {
            f11218a = new tll();
        }
        return f11218a;
    }

    public static boolean a(Context context) {
        if (d()) {
            stq.b(d, "getPhoneIsPad isSupportOrientation: false & isHuaweiMateX");
            return false;
        }
        if (context == null) {
            stq.e(d, "getPhoneIsPad context == null & false");
            return false;
        }
        boolean e = e(context);
        stq.b(d, "getPhoneIsPad = " + e);
        return e;
    }

    public static boolean d() {
        return "HUAWEI".equalsIgnoreCase(Build.BRAND) && (Build.DEVICE.contains("RLI") || Build.DEVICE.contains("HWTAH") || Build.DEVICE.contains("TAH-AN00"));
    }

    private static boolean e(Context context) {
        String str = d;
        stq.b(str, "getPhoneIsPad isPad start");
        Object invokeStaticMethod = ReflectionUtils.invokeStaticMethod("android.os.SystemProperties", "get", new Class[]{String.class}, "ro.build.characteristics");
        boolean equals = "tablet".equals(invokeStaticMethod instanceof String ? (String) invokeStaticMethod : "default");
        stq.b(str, "getPhoneIsPad check isPad = " + equals);
        return equals;
    }
}

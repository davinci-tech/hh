package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.hihonor.android.os.Build;
import com.huawei.hms.android.SystemUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class agq {
    private static Map<String, Object> c = new HashMap();

    public static boolean g() {
        String str;
        try {
            return agv.e("hw_sc.product.useBrandCust", false);
        } catch (RuntimeException unused) {
            str = "get isUseBrandCust fail: RuntimeException";
            agr.c("DeviceUtil", str);
            return false;
        } catch (Exception unused2) {
            str = "get isUseBrandCust fail: Exception";
            agr.c("DeviceUtil", str);
            return false;
        }
    }

    private static void f(Context context) {
        DisplayMetrics hF_ = hF_(context);
        if (hF_ != null) {
            c.put("DPI", String.valueOf(hF_.densityDpi));
            int i = hF_.widthPixels;
            int i2 = hF_.heightPixels;
            c.put("RESOLUTION", String.valueOf(i) + "_" + String.valueOf(i2));
        }
    }

    public static boolean g(Context context) {
        return context.getPackageManager().hasSystemFeature("com.huawei.software.features.car");
    }

    public static int i() {
        Integer num = (Integer) c.get("SystemBit");
        if (num != null) {
            return num.intValue();
        }
        Integer valueOf = Integer.valueOf(agv.c("ro.product.cpu.abi", "").contains("arm64") ? 2 : 1);
        c.put("SystemBit", valueOf);
        return valueOf.intValue();
    }

    public static boolean j(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    public static int f() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean a(Context context) {
        String str;
        if (context == null) {
            str = "isTelevision: context is null!";
        } else {
            Resources resources = context.getResources();
            if (resources != null) {
                return (resources.getConfiguration().uiMode & 15) == 4;
            }
            str = "isTelevision: resource is null!";
        }
        agr.e("DeviceUtil", str);
        return false;
    }

    public static String d() {
        String d = agv.d("ro.product.hw_model");
        if (TextUtils.isEmpty(d)) {
            d = agv.d("ro.product.hn_model");
            if (TextUtils.isEmpty(d)) {
                d = Build.MODEL;
            }
        }
        agr.c("DeviceUtil", "phoneType:" + d);
        return d;
    }

    public static String b(Context context) {
        String str = (String) c.get("DPI");
        if (str != null) {
            return str;
        }
        f(context);
        return (String) c.get("DPI");
    }

    public static String c() {
        String c2;
        try {
            c2 = com.hihonor.android.os.Build.MAGIC_VERSION;
        } catch (Error | Exception unused) {
            c2 = agv.c("ro.build.version.magic", "");
        }
        agr.c("DeviceUtil", "magicVer:" + c2);
        return c2;
    }

    public static String c(Context context) {
        String str = (String) c.get("RESOLUTION");
        if (str != null) {
            return str;
        }
        f(context);
        return (String) c.get("RESOLUTION");
    }

    public static int a() {
        int e;
        try {
            e = Build.VERSION.MAGIC_SDK_INT;
        } catch (Error | Exception unused) {
            e = agv.e("ro.build.magic_api_level", 0);
        }
        agr.c("DeviceUtil", "magicApiLevel:" + e);
        return e;
    }

    public static String b() {
        return String.valueOf(android.os.Build.DISPLAY);
    }

    public static DisplayMetrics hF_(Context context) {
        Display defaultDisplay;
        WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String e() {
        return agv.d(SystemUtils.PRODUCT_BRAND);
    }

    public static int e(Context context) {
        if (a(context)) {
            return 1;
        }
        if (j(context)) {
            return 2;
        }
        return g(context) ? 7 : 0;
    }
}

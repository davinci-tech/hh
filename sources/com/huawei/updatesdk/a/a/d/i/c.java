package com.huawei.updatesdk.a.a.d.i;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.android.app.PackageManagerEx;
import com.huawei.hms.android.SystemUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.SystemProperties;
import com.huawei.system.BuildEx;
import com.huawei.updatesdk.a.a.d.f;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class c {
    private static int e = -1;

    /* renamed from: a, reason: collision with root package name */
    private int f10809a;
    private int b;
    private int c;
    private static c f = new c();
    private static String g = null;
    public static final String d = a(SystemUtils.PRODUCT_BRAND, "");
    public static final boolean h = a("hw_sc.product.useBrandCust", false);
    public static final int i = a(SystemProperties.HW_SC_BUILD_OS_API_VERSION, 0);
    public static final String j = h();

    public int c() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0048 A[Catch: all -> 0x0073, TRY_LEAVE, TryCatch #0 {all -> 0x0073, blocks: (B:10:0x001d, B:12:0x0024, B:13:0x0026, B:16:0x003f, B:19:0x0048, B:22:0x0033), top: B:9:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.view.Window r6) {
        /*
            r5 = this;
            com.huawei.updatesdk.b.h.a r0 = com.huawei.updatesdk.b.h.a.f()
            int r0 = r0.a()
            r1 = 21
            if (r0 >= r1) goto L16
            com.huawei.updatesdk.b.h.a r0 = com.huawei.updatesdk.b.h.a.f()
            int r0 = r0.b()
            if (r0 <= 0) goto L18
        L16:
            if (r6 != 0) goto L19
        L18:
            return
        L19:
            android.view.WindowManager$LayoutParams r6 = r6.getAttributes()
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L73
            r1 = 28
            r2 = 1
            if (r0 < r1) goto L26
            r6.layoutInDisplayCutoutMode = r2     // Catch: java.lang.Throwable -> L73
        L26:
            com.huawei.updatesdk.b.h.a r0 = com.huawei.updatesdk.b.h.a.f()     // Catch: java.lang.Throwable -> L73
            int r0 = r0.a()     // Catch: java.lang.Throwable -> L73
            if (r0 <= 0) goto L33
            java.lang.String r0 = "com.huawei.android.view.WindowManagerEx$LayoutParamsEx"
            goto L3f
        L33:
            com.huawei.updatesdk.b.h.a r0 = com.huawei.updatesdk.b.h.a.f()     // Catch: java.lang.Throwable -> L73
            int r0 = r0.b()     // Catch: java.lang.Throwable -> L73
            if (r0 <= 0) goto L44
            java.lang.String r0 = "com.hihonor.android.view.WindowManagerEx$LayoutParamsEx"
        L3f:
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Throwable -> L73
            goto L45
        L44:
            r0 = 0
        L45:
            if (r0 != 0) goto L48
            return
        L48:
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L73
            java.lang.Class<android.view.WindowManager$LayoutParams> r3 = android.view.WindowManager.LayoutParams.class
            r4 = 0
            r1[r4] = r3     // Catch: java.lang.Throwable -> L73
            java.lang.reflect.Constructor r1 = r0.getConstructor(r1)     // Catch: java.lang.Throwable -> L73
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch: java.lang.Throwable -> L73
            java.lang.Object r6 = r1.newInstance(r6)     // Catch: java.lang.Throwable -> L73
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L73
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Throwable -> L73
            r1[r4] = r3     // Catch: java.lang.Throwable -> L73
            java.lang.String r3 = "setDisplaySideMode"
            java.lang.reflect.Method r0 = r0.getMethod(r3, r1)     // Catch: java.lang.Throwable -> L73
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L73
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L73
            r1[r4] = r2     // Catch: java.lang.Throwable -> L73
            r0.invoke(r6, r1)     // Catch: java.lang.Throwable -> L73
            goto L8b
        L73:
            r6 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "setWindowDisplaySideMode exception: "
            r0.<init>(r1)
            java.lang.String r6 = r6.getMessage()
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r0 = "DeviceUtil"
            com.huawei.updatesdk.a.a.c.a.a.a.b(r0, r6)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.a.d.i.c.a(android.view.Window):void");
    }

    public int a() {
        return this.f10809a;
    }

    private static boolean m() {
        return a("com.huawei.software.features.kidpad");
    }

    private static boolean l(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    public static boolean l() {
        return com.huawei.updatesdk.b.h.a.f().a() > 0 || com.huawei.updatesdk.b.h.a.f().b() > 0;
    }

    private static boolean k(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        com.huawei.updatesdk.a.a.a.b("DeviceUtil", "UI mode: " + uiModeManager.getCurrentModeType());
        return uiModeManager.getCurrentModeType() == 4;
    }

    public static String k() {
        String str;
        String str2;
        String str3;
        StringBuilder sb;
        Locale locale = Locale.getDefault();
        if (locale != null) {
            str = locale.getLanguage();
            str2 = locale.getScript();
            str3 = locale.getCountry();
        } else {
            str = "en";
            str2 = "";
            str3 = "US";
        }
        if (TextUtils.isEmpty(str2)) {
            sb = new StringBuilder();
            sb.append(str);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("_");
            sb2.append(str2);
            sb = sb2;
        }
        sb.append("_");
        sb.append(str3);
        return sb.toString();
    }

    public static boolean j(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo("com.google.android.gsf.login", 16);
            packageManager.getPackageInfo("com.google.android.gsf", 16);
            return (packageManager.getPackageInfo("com.google.android.gms", 16).applicationInfo.flags & 1) != 0;
        } catch (Exception e2) {
            com.huawei.updatesdk.a.a.c.a.a.a.d("DeviceUtil", e2.getMessage());
            return false;
        }
    }

    public static int j() {
        return a("ro.product.cpu.abi", "").contains("arm64") ? 2 : 1;
    }

    public static String[] i() {
        String[] strArr = Build.SUPPORTED_ABIS;
        return strArr == null ? new String[0] : strArr;
    }

    private static boolean i(Context context) {
        return context.getPackageManager().hasSystemFeature("com.huawei.software.features.car");
    }

    private static boolean h(Context context) {
        boolean hasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
        return !hasSystemFeature ? com.huawei.updatesdk.b.h.b.d(context, "com.huawei.appmarket.car.bmw") != null : hasSystemFeature;
    }

    private static String h() {
        try {
            return BuildEx.getOsBrand();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName + "";
        } catch (Exception unused) {
            return null;
        }
    }

    public static String g() {
        StringBuilder sb;
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String variant = locale.getVariant();
        String script = locale.getScript();
        if (TextUtils.isEmpty(script) && TextUtils.isEmpty(variant)) {
            sb = new StringBuilder();
            sb.append(language);
            sb.append("_");
            sb.append(country);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(language);
            sb2.append("_");
            sb2.append(script);
            sb2.append("_");
            sb2.append(country);
            sb2.append("_");
            sb2.append(variant);
            sb = sb2;
        }
        return sb.toString();
    }

    public static c f() {
        return f;
    }

    public static int f(Context context) {
        try {
            return Integer.parseInt(context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionCode + "");
        } catch (Exception unused) {
            return 1;
        }
    }

    public static String e(Context context) {
        DisplayMetrics b = b(context);
        return b != null ? String.valueOf(b.densityDpi) : "";
    }

    public static int e() {
        int i2 = e;
        if (i2 >= 0) {
            return i2;
        }
        Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
        int i3 = k(a2) ? 1 : l(a2) ? 2 : h(a2) ? 3 : m() ? 4 : i(a2) ? 7 : 0;
        e = i3;
        return i3;
    }

    public static String d(Context context) {
        DisplayMetrics b = b(context);
        if (b == null) {
            return "";
        }
        return String.valueOf(b.widthPixels) + "_" + String.valueOf(b.heightPixels);
    }

    public static String d() {
        try {
            Object obj = Class.forName("com.huawei.system.BuildEx").getField("DISPLAY").get(null);
            return obj == null ? Build.DISPLAY : obj.toString();
        } catch (Throwable unused) {
            return Build.DISPLAY;
        }
    }

    public static List<String> c(Context context) {
        ArrayList arrayList = new ArrayList();
        LocaleList locales = context.getResources().getConfiguration().getLocales();
        for (int i2 = 0; i2 < locales.size(); i2++) {
            String language = locales.get(i2).getLanguage();
            if (!TextUtils.isEmpty(language) && !arrayList.contains(language)) {
                arrayList.add(language);
            }
        }
        return arrayList;
    }

    public static String b(Context context, String str) {
        String a2 = a(context, str);
        int i2 = 0;
        int i3 = 0;
        while (i2 < a2.length() && i3 < 3) {
            if (a2.charAt(i2) == '.') {
                i3++;
            }
            i2++;
        }
        return 3 == i3 ? a2.substring(0, i2 - 1) : a2;
    }

    private static DisplayMetrics b(Context context) {
        Display defaultDisplay;
        Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    static boolean a(String str, String[] strArr) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (String str2 : strArr) {
            if (str2.startsWith("config.")) {
                i2 = 1;
            } else if (!str2.contains(".config.")) {
                i3++;
            }
            if (str2.endsWith("config." + str)) {
                i4++;
            }
        }
        return i2 + i3 == i4;
    }

    private static boolean a(String str, boolean z) {
        try {
            return ((Boolean) Class.forName("android.os.SystemProperties").getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("DeviceUtil", "Exception while getting system property: ", th);
            return z;
        }
    }

    private static boolean a(String str) {
        try {
            return PackageManagerEx.hasHwSystemFeature(str);
        } catch (Throwable unused) {
            return false;
        }
    }

    private static void a(FeatureInfo[] featureInfoArr, List<String> list) {
        if (featureInfoArr != null) {
            for (FeatureInfo featureInfo : featureInfoArr) {
                String str = featureInfo.name;
                if (str != null) {
                    list.add(com.huawei.updatesdk.a.a.d.c.a(str));
                }
            }
        }
    }

    private static void a(List<String> list) {
        try {
            a(PackageManagerEx.getHwSystemAvailableFeatures(), list);
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.c.a.a.a.c("DeviceUtil", "initHwDeviceFeatures: " + th.getMessage());
        }
    }

    private static void a(Context context, List<String> list) {
        a(context.getPackageManager().getSystemAvailableFeatures(), list);
    }

    public static List<String> a(List<String> list, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!a(str, strArr)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("DeviceUtil", "Exception while getting system property: ", th);
            return str2;
        }
    }

    public static String a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 16).versionName + "";
        } catch (Exception unused) {
            return "";
        }
    }

    static String a(Context context) {
        if (g == null) {
            ArrayList arrayList = new ArrayList();
            a(context, arrayList);
            a(arrayList);
            g = f.a(arrayList, ",");
        }
        return g;
    }

    public static int a(String str, int i2) {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i2))).intValue();
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("DeviceUtil", "Exception while getting system property: ", th);
            return i2;
        }
    }

    private c() {
        this.f10809a = 0;
        if (a("ro.maple.enable", 0) == 1) {
            this.f10809a = 1;
        }
        this.b = a("ro.build.version.ark", 0);
        this.c = a("ro.kernel.evox", 0);
    }
}

package com.huawei.hms.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.wear.oversea.util.SystemPropertyValues;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/* loaded from: classes.dex */
public class SystemUtils {
    public static final String PRODUCT_BRAND = "ro.product.brand";
    public static final String PRODUCT_HONOR = "HONOR";
    public static final String PRODUCT_HUAWEI = "HUAWEI";
    public static final String UNKNOWN = "unknown";

    private static String a() {
        return getSystemProperties(SystemPropertyValues.PROP_LOCALE_KEY, "");
    }

    private static String b() {
        return getSystemProperties("ro.product.locale.region", "");
    }

    public static String getAndoridVersion() {
        return getSystemProperties("ro.build.version.release", "unknown");
    }

    public static String getManufacturer() {
        return getSystemProperties("ro.product.manufacturer", "unknown");
    }

    public static long getMegabyte(double d) {
        return (long) (d * 1000.0d * 1000.0d);
    }

    public static String getPhoneModel() {
        return getSystemProperties("ro.product.model", "unknown");
    }

    public static String getSystemProperties(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            HMSLog.e("SystemUtils", "An exception occurred while reading: getSystemProperties:" + str);
            return str2;
        }
    }

    @Deprecated
    public static boolean isChinaROM() {
        String b = b();
        if (!TextUtils.isEmpty(b)) {
            return "cn".equalsIgnoreCase(b);
        }
        String a2 = a();
        if (!TextUtils.isEmpty(a2)) {
            return a2.toLowerCase(Locale.US).contains("cn");
        }
        String localCountry = getLocalCountry();
        if (TextUtils.isEmpty(localCountry)) {
            return false;
        }
        return "cn".equalsIgnoreCase(localCountry);
    }

    public static boolean isEMUI() {
        StringBuilder sb = new StringBuilder("is Emui :");
        int i = HwBuildEx.VERSION.EMUI_SDK_INT;
        sb.append(i);
        HMSLog.i("SystemUtils", sb.toString());
        return i > 0;
    }

    public static boolean isHuawei() {
        String systemProperties = getSystemProperties(PRODUCT_BRAND, "unknown");
        return "HUAWEI".equalsIgnoreCase(systemProperties) || "HONOR".equalsIgnoreCase(systemProperties);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isSystemApp(android.content.Context r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "SystemUtils"
            r1 = 0
            if (r3 != 0) goto Lb
            java.lang.String r3 = "isSystemApp context is null"
            com.huawei.hms.support.log.HMSLog.w(r0, r3)
            return r1
        Lb:
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: java.lang.RuntimeException -> L16 android.util.AndroidException -> L29
            r2 = 16384(0x4000, float:2.2959E-41)
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r2)     // Catch: java.lang.RuntimeException -> L16 android.util.AndroidException -> L29
            goto L3c
        L16:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r2 = "isSystemApp RuntimeException:"
            r4.<init>(r2)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.huawei.hms.support.log.HMSLog.e(r0, r3)
            goto L3b
        L29:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r2 = "isSystemApp Exception: "
            r4.<init>(r2)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.huawei.hms.support.log.HMSLog.e(r0, r3)
        L3b:
            r3 = 0
        L3c:
            if (r3 == 0) goto L47
            android.content.pm.ApplicationInfo r3 = r3.applicationInfo
            int r3 = r3.flags
            r4 = 1
            r3 = r3 & r4
            if (r3 <= 0) goto L47
            r1 = r4
        L47:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.android.SystemUtils.isSystemApp(android.content.Context, java.lang.String):boolean");
    }

    public static boolean isTVDevice() {
        return getSystemProperties("ro.build.characteristics", "default").equalsIgnoreCase("tv");
    }

    public static String getLocalCountry() {
        Locale locale = Locale.getDefault();
        return locale != null ? locale.getCountry() : "";
    }

    public static String getNetType(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? "" : activeNetworkInfo.getTypeName();
    }
}

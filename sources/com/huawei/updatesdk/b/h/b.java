package com.huawei.updatesdk.b.h;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.update.UpdateConstants;
import com.huawei.updatesdk.a.a.d.e;
import java.io.File;
import java.lang.reflect.Field;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static Integer f10846a = null;
    private static boolean b = false;
    private static boolean c = false;
    private static Field d;

    private static boolean j(Context context, String str) {
        Intent intent = new Intent(UpdateConstants.ACTION_NAME_HIAPP_SILENT_DOWNLOAD);
        intent.setPackage(str);
        try {
            return context.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "isSupportToHiApp: " + e.getMessage());
            return false;
        }
    }

    public static boolean i(Context context, String str) {
        return f(context, str) >= 70203000 && j(context, str);
    }

    public static boolean h(Context context, String str) {
        String str2;
        if (context == null || TextUtils.isEmpty(str)) {
            str2 = "isMetaDataSupport invalid arguments.";
        } else {
            int a2 = a(context, str, "appgallery_support_function");
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "UpdateSDK ag_support_function = " + a2);
            if (a2 == -1) {
                a2 = 0;
            }
            r1 = (a2 & 2) == 2;
            str2 = "UpdateSDK isMetaDataSupport = " + r1;
        }
        com.huawei.updatesdk.a.a.a.b("PackageUtils", str2);
        return r1;
    }

    public static boolean g(Context context, String str) {
        return f(context, str) > 0;
    }

    public static int f(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "pkgName: " + str + ", versionCode: " + packageInfo.versionCode + ", versionName: " + packageInfo.versionName);
            return packageInfo.versionCode;
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.c.a.a.a.d("PackageUtils", "getVersioncode:" + e.getMessage());
            return 0;
        }
    }

    public static PackageInfo e(Context context, String str) {
        PackageInfo a2 = a(str, context);
        if (a2 == null) {
            a2 = new PackageInfo();
            a2.packageName = str;
            a2.versionName = "1.0";
            a2.versionCode = 1;
            ApplicationInfo applicationInfo = new ApplicationInfo();
            applicationInfo.targetSdkVersion = 19;
            a2.applicationInfo = applicationInfo;
        }
        return e.a(a2);
    }

    public static PackageInfo d(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 2097280);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Integer c() {
        String exc;
        StringBuilder sb;
        String message;
        if (b) {
            return f10846a;
        }
        try {
            Class<?> cls = Class.forName("android.content.pm.PackageParser");
            f10846a = Integer.valueOf(cls.getDeclaredField("PARSE_IS_REMOVABLE_PREINSTALLED_APK").getInt(cls));
        } catch (ClassNotFoundException e) {
            sb = new StringBuilder("isDelApp error ClassNotFoundException:");
            message = e.getMessage();
            sb.append(message);
            exc = sb.toString();
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", exc);
            b = true;
            return f10846a;
        } catch (IllegalAccessException e2) {
            sb = new StringBuilder("isDelApp error IllegalAccessException:");
            message = e2.getMessage();
            sb.append(message);
            exc = sb.toString();
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", exc);
            b = true;
            return f10846a;
        } catch (IllegalArgumentException e3) {
            sb = new StringBuilder("isDelApp error IllegalArgumentException:");
            message = e3.getMessage();
            sb.append(message);
            exc = sb.toString();
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", exc);
            b = true;
            return f10846a;
        } catch (NoSuchFieldException e4) {
            sb = new StringBuilder("isDelApp error NoSuchFieldException:");
            message = e4.getMessage();
            sb.append(message);
            exc = sb.toString();
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", exc);
            b = true;
            return f10846a;
        } catch (Exception e5) {
            exc = e5.toString();
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", exc);
            b = true;
            return f10846a;
        }
        b = true;
        return f10846a;
    }

    public static int c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "getMetaDataServiceType invalid arguments.");
            return -1;
        }
        int a2 = a(context, str, "ag_service_type");
        com.huawei.updatesdk.a.a.a.b("PackageUtils", "UpdateSDK ag_service_type = " + a2);
        return a2;
    }

    public static Field b() {
        if (c) {
            return d;
        }
        try {
            d = ApplicationInfo.class.getField("hwFlags");
        } catch (NoSuchFieldException unused) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", "can not find hwFlags");
        }
        c = true;
        return d;
    }

    public static String b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "getMetaDataDistRule invalid arguments.");
            return "";
        }
        Object b2 = b(context, str, "ag_dist_rule");
        com.huawei.updatesdk.a.a.a.b("PackageUtils", "UpdateSDK ag_dist_rule = " + b2);
        return b2 instanceof String ? (String) b2 : "";
    }

    private static Object b(Context context, String str, String str2) {
        PackageInfo d2;
        try {
            d2 = d(context, str);
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.a.a("PackageUtils", "getAppMetaData " + str2 + ": " + e.getMessage());
        }
        if (d2 == null) {
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "getAppMetaData no packageInfo: " + str);
            return null;
        }
        ApplicationInfo applicationInfo = d2.applicationInfo;
        if (applicationInfo == null) {
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "getAppMetaData no applicationInfo: " + str);
            return null;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null && bundle.containsKey(str2)) {
            return bundle.get(str2);
        }
        return null;
    }

    public static int b(String str) {
        ApplicationInfo applicationInfo;
        try {
            PackageInfo packageInfo = com.huawei.updatesdk.a.b.a.a.c().a().getPackageManager().getPackageInfo(str, 128);
            return (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || applicationInfo.metaData == null || !packageInfo.applicationInfo.metaData.containsKey("com.huawei.maple.flag")) ? 0 : 1;
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.c.a.a.a.d("PackageUtils", "getMapleStatus not found: " + str);
            return 0;
        }
    }

    public static boolean a(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
        Uri parse = Uri.parse("content://" + str + ".commondata/item/7");
        if (!d.a(a2, parse, str)) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = a2.getContentResolver().query(parse, null, str, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    z = Boolean.parseBoolean(cursor.getString(0));
                }
            } catch (Exception e) {
                com.huawei.updatesdk.a.a.a.a("PackageUtils", "getAgreeProtocolStatus : " + e.getMessage());
            }
            return z;
        } finally {
            d.a(cursor);
        }
    }

    private static boolean a(ApplicationInfo applicationInfo) {
        StringBuilder sb;
        String message;
        int i = applicationInfo.flags;
        Integer c2 = c();
        if (c2 != null && (i & c2.intValue()) != 0) {
            return true;
        }
        Field b2 = b();
        if (b2 == null) {
            return false;
        }
        try {
            return (b2.getInt(applicationInfo) & 33554432) != 0;
        } catch (IllegalAccessException e) {
            sb = new StringBuilder("can not get hwflags");
            message = e.getMessage();
            sb.append(message);
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", sb.toString());
            return false;
        } catch (IllegalArgumentException e2) {
            sb = new StringBuilder("can not get hwflags");
            message = e2.getMessage();
            sb.append(message);
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageUtils", sb.toString());
            return false;
        }
    }

    public static void a() {
        com.huawei.updatesdk.b.g.c.f10843a.execute(new RunnableC0279b());
    }

    /* renamed from: com.huawei.updatesdk.b.h.b$b, reason: collision with other inner class name */
    static class RunnableC0279b implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            com.huawei.updatesdk.a.a.c.a.a.a.a("PackageManagerRunnable", "PackageManagerRunnable run!!!!");
            com.huawei.updatesdk.a.a.d.d.a(new File(com.huawei.updatesdk.b.d.d.b()));
        }

        private RunnableC0279b() {
        }
    }

    public static String a(Context context, String str) {
        String str2;
        try {
            str2 = context.getPackageManager().getInstallerPackageName(str);
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.a.b("PackageUtils", "can not find installer pkg." + str);
            str2 = null;
        }
        com.huawei.updatesdk.a.a.c.a.a.a.c("PackageUtils", "installer pkg: " + str2);
        return str2;
    }

    public static PackageInfo a(String str, Context context) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64);
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.c.a.a.a.d("PackageUtils", "not found: " + str);
            return null;
        }
    }

    public static int a(PackageInfo packageInfo) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if ((applicationInfo.flags & 1) == 0) {
            return 0;
        }
        return a(applicationInfo) ? 1 : 2;
    }

    private static int a(Context context, String str, String str2) {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey(str2)) {
                return bundle.getInt(str2);
            }
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.a.a("PackageUtils", "getAgMetaData " + str2 + ": " + e.toString());
        }
        return -1;
    }
}

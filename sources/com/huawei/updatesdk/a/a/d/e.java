package com.huawei.updatesdk.a.a.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.android.content.pm.ApplicationInfoEx;
import com.huawei.ohos.localability.BundleAdapter;
import com.huawei.ohos.localability.base.BundleInfo;
import com.huawei.ohos.localability.base.DeviceInfo;
import java.lang.reflect.Field;
import java.util.Optional;

/* loaded from: classes7.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f10805a = false;
    private static boolean b = false;

    public static boolean d(String str) {
        if (!d() || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return BundleAdapter.isHarmonyApp(str);
        } catch (Throwable unused) {
            com.huawei.updatesdk.a.a.a.a("HarmonyUtils", "get isHarmonyApp fail");
            return false;
        }
    }

    private static boolean d() {
        if (!b) {
            f10805a = a(com.huawei.updatesdk.a.b.a.a.c().a(), com.huawei.updatesdk.a.b.a.a.c().a().getPackageName()) && c();
            b = true;
        }
        return f10805a;
    }

    private static boolean c() {
        try {
            new BundleAdapter();
            return true;
        } catch (Throwable unused) {
            com.huawei.updatesdk.a.a.a.a("HarmonyUtils", "not Integrate HarmonySdk ");
            return false;
        }
    }

    public static int c(String str) {
        try {
            if (d()) {
                Pair sdkVersionInfo = BundleAdapter.getSdkVersionInfo(str);
                if (sdkVersionInfo != null) {
                    return ((Integer) sdkVersionInfo.second).intValue();
                }
                com.huawei.updatesdk.a.a.a.a("HarmonyUtils", "pair is null getHarmonySdkVersionInfo fail");
            }
        } catch (Throwable unused) {
            com.huawei.updatesdk.a.a.a.a("HarmonyUtils", "getHarmonySdkVersionInfo fail");
        }
        return 0;
    }

    public static String b() {
        DeviceInfo a2;
        return (d() && (a2 = a()) != null) ? a2.getDeviceType() : "";
    }

    private static BundleInfo b(String str) {
        try {
            Optional bundleInfo = BundleAdapter.getBundleInfo(str, 0);
            if (bundleInfo.isPresent()) {
                return (BundleInfo) bundleInfo.get();
            }
            return null;
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.a.a("HarmonyUtils", "get BundleInfo exception, pkg:" + str + ", e:" + th.getMessage());
            return null;
        }
    }

    private static boolean a(Context context, String str) {
        Class<?> cls;
        Field declaredField;
        ApplicationInfo applicationInfo;
        try {
            cls = Class.forName("android.content.pm.AbsApplicationInfo");
            declaredField = cls.getDeclaredField("PARSE_IS_ZIDANE_APK");
            PackageInfo a2 = com.huawei.updatesdk.b.h.b.a(str, context);
            applicationInfo = a2 != null ? a2.applicationInfo : null;
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.a.a("HarmonyUtils", "isHarmonyByHwFlag exception for pkg: " + str + ", throwable : " + th.getMessage());
        }
        if (applicationInfo != null) {
            int hwFlags = new ApplicationInfoEx(applicationInfo).getHwFlags();
            int i = declaredField.getInt(cls);
            boolean z = (hwFlags & i) == i;
            com.huawei.updatesdk.a.a.a.b("HarmonyUtils", "isHarmonyByHwFlag: " + z + ", packageName: " + str);
            return z;
        }
        com.huawei.updatesdk.a.a.a.c("HarmonyUtils", "applicationInfo is null for pkg: " + str);
        return false;
    }

    private static DeviceInfo a() {
        try {
            return BundleAdapter.getDeviceInfo();
        } catch (Throwable unused) {
            com.huawei.updatesdk.a.a.a.a("HarmonyDeviceInfo", "get DeviceInfo fail");
            return null;
        }
    }

    private static BundleInfo a(String str) {
        if (d()) {
            return b(str);
        }
        return null;
    }

    public static PackageInfo a(PackageInfo packageInfo) {
        BundleInfo a2;
        if (d() && (a2 = a(packageInfo.packageName)) != null) {
            packageInfo.versionName = a2.getVersionName();
            packageInfo.versionCode = a2.getVersionCode();
            packageInfo.applicationInfo.targetSdkVersion = c(packageInfo.packageName);
            if (a2.isMultiFrameworkBundle()) {
                packageInfo.baseRevisionCode = packageInfo.versionCode;
            }
        }
        return packageInfo;
    }
}

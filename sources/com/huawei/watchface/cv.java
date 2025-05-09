package com.huawei.watchface;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.secure.android.common.detect.SecurityDetect;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class cv {

    /* renamed from: a, reason: collision with root package name */
    public static final String f10977a = "cv";
    private static String b;

    public static String a() {
        Application applicationContext = Environment.getApplicationContext();
        try {
            return applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 16384).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            HwLog.w(f10977a, "getVersionName() Exception = " + HwLog.printException((Exception) e));
            return null;
        } catch (RuntimeException e2) {
            HwLog.e(f10977a, "getVersionName() runtimeException " + HwLog.printException((Exception) e2));
            return null;
        }
    }

    public static String b() {
        if (TextUtils.isEmpty(b)) {
            String a2 = a();
            if (TextUtils.isEmpty(a2)) {
                return "1.0.0.0";
            }
            b = a2;
        }
        return b;
    }

    public static int c() {
        return TextUtils.equals("com.hihonor.android.thememanager", Environment.b()) ? 2 : 1;
    }

    public static boolean d() {
        return 2 == c();
    }

    public static int e() {
        String str = Build.BRAND;
        String str2 = Build.MANUFACTURER;
        if (TextUtils.equals(str, "HUAWEI") && TextUtils.equals(str2, "HUAWEI")) {
            return 1;
        }
        if (TextUtils.equals(str, "HONOR") && TextUtils.equals(str2, "HUAWEI")) {
            return 3;
        }
        return (TextUtils.equals(str, "HONOR") && TextUtils.equals(str2, "HONOR")) ? 2 : 99;
    }

    public static String f() {
        return d() ? "com.hihonor.android.thememanager" : "com.huawei.android.thememanager";
    }

    public static String g() {
        return "file:///data/data/" + Environment.b() + "/";
    }

    public static String h() {
        String b2 = dp.b("cache_device_id", "");
        if (TextUtils.isEmpty(b2)) {
            b2 = db.a(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdentify());
            dp.c("cache_device_id", b2);
        }
        return (b2 == null || b2.length() <= 256) ? b2 : SafeString.substring(b2, 0, 255);
    }

    public static String i() {
        String str = !HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).isSportGTModel() ? "AP" : "GT";
        HwLog.d(f10977a, "getDeviceNameId model:".concat(str));
        return dp.b("cache_device_id", "") + str;
    }

    public static String k() {
        return Build.MANUFACTURER;
    }

    public static void a(Activity activity) {
        if (activity != null && SecurityDetect.irpj()) {
            HwLog.i(f10977a, "SecurityDetect irpj");
            activity.finish();
        }
    }

    public static String j() {
        return "1.0.23.105";
    }
}

package com.huawei.secure.android.common.detect;

import android.os.Build;
import com.huawei.secure.android.common.detect.b.c;
import com.huawei.secure.android.common.detect.b.d;
import java.io.File;

/* loaded from: classes6.dex */
public final class RootDetect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8574a = "RootDetect";
    private static final String b = "/sbin:/vendor/bin:/system/sbin:/system/bin:/system/xbin:/system/bin/.ext:/system/sd/xbin:/system/usr/we-need-root:/cache:/data:/dev:/system/vendor/bin:/vendor/xbin:/system/vendor/xbin:";
    private static final String c = "su";
    private static final String d = "magisk";

    private RootDetect() {
    }

    private static boolean a(String str) {
        for (String str2 : b.split(":")) {
            if (new File(str2 + File.separator + str).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean b() {
        try {
        } catch (Exception e) {
            c.b(f8574a, "Check emulator " + e.getMessage());
        }
        if (!Build.HARDWARE.contains("goldfish") && !Build.FINGERPRINT.contains("generic") && !Build.MODEL.contains("google_sdk") && !Build.MODEL.contains("Emulator") && !Build.MODEL.contains("Android SDK built for x86") && !Build.MODEL.contains("Android SDK built for arm64")) {
            if (!"google_sdk".equals(Build.PRODUCT)) {
                return false;
            }
        }
        return true;
    }

    private static boolean c() {
        return a(d);
    }

    private static boolean d() {
        return "0".equals(d.b("ro.secure"));
    }

    private static boolean e() {
        return a(c);
    }

    private static boolean f() {
        if (e()) {
            c.b(f8574a, "su file exists");
            return true;
        }
        if (d()) {
            c.b(f8574a, "SecureProperty is wrong");
            return true;
        }
        if (b()) {
            c.c(f8574a, "app run in emulator");
            return true;
        }
        if (a()) {
            c.c(f8574a, "build.tags is wrong");
            return true;
        }
        if (!c()) {
            return false;
        }
        c.b(f8574a, "Magisk exists");
        return true;
    }

    public static boolean isRoot() {
        if (Build.VERSION.SDK_INT < 27) {
            return f();
        }
        try {
            if (!SD.irtj()) {
                return false;
            }
            c.b(f8574a, "root exists");
            return true;
        } catch (Throwable th) {
            c.b(f8574a, "SD.irtj isRoot exception : " + th.getMessage());
            return f();
        }
    }

    private static boolean a() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }
}

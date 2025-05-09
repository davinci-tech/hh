package defpackage;

import android.os.Build;
import com.huawei.openalliance.ad.constant.OsType;
import java.io.File;

/* renamed from: if, reason: invalid class name and case insensitive filesystem */
/* loaded from: classes7.dex */
public final class C0337if {
    public static C0337if e = new C0337if();

    public static String p() {
        return b("ro.kernel.qemu", "0");
    }

    public static String m() {
        return Build.TAGS;
    }

    public static String l() {
        return Build.VERSION.SDK;
    }

    public static String n() {
        return Build.VERSION.RELEASE;
    }

    public static String k() {
        return Build.PRODUCT;
    }

    public static String o() {
        return Build.MODEL;
    }

    public static String g() {
        return Build.MANUFACTURER;
    }

    public static String j() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String i() {
        return Build.DISPLAY;
    }

    public static String f() {
        return Build.DEVICE;
    }

    public static String h() {
        return Build.BRAND;
    }

    public static String b() {
        return Build.BOARD;
    }

    public static boolean a() {
        try {
            if (!Build.HARDWARE.contains("goldfish") && !Build.PRODUCT.contains("sdk")) {
                if (!Build.FINGERPRINT.contains("generic")) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean d() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static String e() {
        return OsType.ANDROID;
    }

    public static String b(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static C0337if c() {
        return e;
    }
}

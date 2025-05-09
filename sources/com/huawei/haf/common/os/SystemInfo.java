package com.huawei.haf.common.os;

import android.content.pm.PackageManager;
import android.os.Build;
import com.huawei.agconnect.apms.Agent;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.EmuiBuild;
import health.compact.a.HarmonyBuild;
import health.compact.a.MagicBuild;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SystemProperties;
import java.util.Locale;

/* loaded from: classes.dex */
public class SystemInfo {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f2102a;

    static {
        g();
        f2102a = (SystemProperties.b("ro.config.hw_fold_disp", "").isEmpty() && SystemProperties.b("persist.sys.fold.disp.size", "").isEmpty()) ? false : true;
    }

    private static void g() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("initSystemInfo:");
        a(sb);
        ReleaseLogUtil.b("HAF_SystemInfo", sb.toString());
    }

    public static void a(StringBuilder sb) {
        if (EmuiBuild.d) {
            sb.append("sysName=");
            sb.append("EMUI");
            sb.append(", sysVer=");
            sb.append(EmuiBuild.c);
            sb.append(", apiVer=");
            sb.append(EmuiBuild.f13113a);
        } else if (HarmonyBuild.d) {
            sb.append("sysName=");
            sb.append("HarmonyOS");
            sb.append(", sysVer=");
            sb.append(HarmonyBuild.b);
            sb.append(", apiVer=");
            sb.append(HarmonyBuild.c);
        } else if (MagicBuild.f13130a) {
            sb.append("sysName=");
            sb.append("MagicOS");
            sb.append(", sysVer=");
            sb.append(MagicBuild.b);
            sb.append(", apiVer=");
            sb.append(MagicBuild.d);
        } else {
            sb.append("sysName=");
            sb.append(Agent.OS_NAME);
            sb.append(", sysVer=");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append(", manufacturer=");
        sb.append(Build.MANUFACTURER);
        sb.append(", brand=");
        sb.append(Build.BRAND);
        sb.append(", model=");
        sb.append(Build.MODEL);
        sb.append(", locale=");
        sb.append(Locale.getDefault());
        sb.append(", sdkVer=");
        sb.append(Build.VERSION.SDK_INT);
    }

    public static boolean b() {
        return HarmonyBuild.d;
    }

    public static boolean d() {
        return "HONOR".equalsIgnoreCase(Build.BRAND);
    }

    public static boolean h() {
        return EmuiBuild.e();
    }

    public static boolean a() {
        return "HUAWEI".equalsIgnoreCase(Build.BRAND);
    }

    public static boolean e() {
        return f2102a;
    }

    public static boolean b(boolean z) {
        if ("tablet".equals(SystemProperties.b("ro.build.characteristics", ""))) {
            return true;
        }
        PackageManager packageManager = BaseApplication.e().getPackageManager();
        if (packageManager.hasSystemFeature("com.huawei.software.features.pad")) {
            return true;
        }
        return z && packageManager.hasSystemFeature("com.hihonor.software.features.pad");
    }

    public static String c() {
        return SystemProperties.b("ro.vendor.hiaiversion");
    }
}

package health.compact.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.common.os.SystemInfo;

/* loaded from: classes.dex */
public final class EnvironmentInfo extends SystemInfo {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f13114a = false;
    private static Boolean b = null;
    private static Boolean c = null;
    private static Boolean d = null;
    private static boolean e = false;
    private static Boolean j;

    private EnvironmentInfo() {
    }

    static String a(String str) {
        return SystemProperties.b(str, "");
    }

    public static String b(String str, String str2) {
        return SystemProperties.b(str, str2);
    }

    public static boolean b(String str, boolean z) {
        return SystemProperties.e(str, z);
    }

    public static String f() {
        return SystemProperties.b("hbc.country", "");
    }

    public static boolean c(Context context) {
        if (b == null) {
            b = Boolean.valueOf(!CommonUtil.r(context));
        }
        return !b.booleanValue();
    }

    public static boolean p() {
        String f = f();
        if (TextUtils.isEmpty(f)) {
            com.huawei.hwlogsmodel.LogUtil.a("EnvironmentInfo", "isTaiwanRom country is empty");
            return false;
        }
        if ("tw".equalsIgnoreCase(f)) {
            com.huawei.hwlogsmodel.LogUtil.a("EnvironmentInfo", "isTaiwanRom country is tw");
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.a("EnvironmentInfo", "isTaiwanRom country is not tw");
        return false;
    }

    public static boolean l() {
        String b2 = SystemProperties.b("gsm.sim.operator.numeric", "");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        boolean z = false;
        for (String str : b2.split(",")) {
            if (str.startsWith("466")) {
                com.huawei.hwlogsmodel.LogUtil.a("EnvironmentInfo", "has taiwan sim");
                z = true;
            }
        }
        return z;
    }

    public static boolean o() {
        String b2 = SystemProperties.b("gsm.sim.operator.numeric", "");
        return !TextUtils.isEmpty(b2) && b2.split(",").length > 0;
    }

    public static boolean k() {
        if (!f13114a) {
            f13114a = true;
            e = "1".equals(SystemProperties.b("ro.kernel.evox"));
        }
        return e;
    }

    public static boolean m() {
        return SystemProperties.e("hw_mc.systemui.live_notification_enabled", false);
    }

    public static boolean r() {
        if (j == null) {
            String b2 = SystemProperties.b("hw_sc.build.platform.version");
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 29 && CommonUtil.bh() && CommonUtils.h(b2.split("\\.")[0]) >= 3) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            j = valueOf;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("EnvironmentInfo", "sIsSystemSupportFreezing = ", valueOf);
        }
        return j.booleanValue();
    }

    public static boolean j() {
        if (d == null) {
            String b2 = SystemProperties.b("hw_sc.build.platform.version");
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 30 && CommonUtil.bh() && CommonUtils.h(b2.split("\\.")[0]) >= 4) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            d = valueOf;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("EnvironmentInfo", "isHarmony4OrLater = ", valueOf);
        }
        return d.booleanValue();
    }

    public static boolean i() {
        if (c == null) {
            String b2 = SystemProperties.b("hw_sc.build.platform.version");
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 29 && CommonUtil.bh() && CommonUtils.h(b2.split("\\.")[0]) >= 4) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            c = valueOf;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("EnvironmentInfo", "isHarmony4OrLater = ", valueOf);
        }
        return c.booleanValue();
    }

    public static boolean g() {
        String b2 = SystemProperties.b("hw_sc.build.platform.version");
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 29 && CommonUtil.bh() && CommonUtils.h(b2.split("\\.")[0]) == 3) {
            z = true;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("EnvironmentInfo", "isHarmonyOs3 = ", Boolean.valueOf(z));
        return z;
    }

    public static boolean n() {
        String b2 = SystemProperties.b("hw_sc.build.platform.version");
        boolean z = false;
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        String[] split = b2.split("\\.");
        if (split.length < 2) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29 && CommonUtil.bh() && CommonUtils.h(split[0]) == 3 && CommonUtils.h(split[1]) == 1) {
            z = true;
        }
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("EnvironmentInfo", "isHarmonyOs31 = ", Boolean.valueOf(z));
        return z;
    }
}

package health.compact.a;

/* loaded from: classes.dex */
public final class HarmonyBuild extends BaseBuild {
    public static final boolean d = b();
    public static final String b = g();
    public static final boolean e = h();
    public static final int c = d();

    private HarmonyBuild() {
    }

    private static String g() {
        return !b() ? "" : SystemProperties.b("hw_sc.build.platform.version");
    }

    private static boolean h() {
        if (b()) {
            return SystemProperties.e(com.huawei.openalliance.ad.constant.SystemProperties.HW_SC_BUILD_OS_ENABLE, false);
        }
        return false;
    }

    private static int d() {
        if (b()) {
            return SystemProperties.b(com.huawei.openalliance.ad.constant.SystemProperties.HW_SC_BUILD_OS_API_VERSION, 0);
        }
        return 0;
    }
}

package health.compact.a;

import com.huawei.haf.application.BuildConfigProperties$MainBuildConfig;

/* loaded from: classes.dex */
public final class BuildConfigProperties {
    private static final BuildConfigProperties$MainBuildConfig e = new BuildConfigProperties$MainBuildConfig();

    private BuildConfigProperties() {
    }

    public static void c(String str) {
        e.d(str);
    }

    public static String b() {
        return e.get("BUILD_TYPE", "release");
    }

    public static String b(String str) {
        return e.get(str, "");
    }

    public static String e(String str, String str2) {
        return e.get(str, str2);
    }

    public static boolean e(String str, boolean z) {
        return e.getBoolean(str, z);
    }
}

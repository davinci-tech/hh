package health.compact.a;

/* loaded from: classes.dex */
public class CompileParameterUtil {
    private CompileParameterUtil() {
    }

    public static String c(String str) {
        return BuildConfigProperties.b(str);
    }

    public static String c(String str, String str2) {
        return BuildConfigProperties.e(str, str2);
    }

    public static boolean a(String str) {
        return BuildConfigProperties.e(str, false);
    }

    public static boolean a(String str, boolean z) {
        return BuildConfigProperties.e(str, z);
    }
}

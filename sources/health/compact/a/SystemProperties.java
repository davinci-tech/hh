package health.compact.a;

/* loaded from: classes.dex */
public final class SystemProperties {
    private static SystemPropertiesReader e;

    static {
        try {
            e = new SystemPropertiesReader("android.os.SystemProperties");
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            LogUtil.e("HAF_SystemProperties", "init SystemProperties ex=", LogUtil.a(e2));
        }
    }

    private SystemProperties() {
    }

    public static String b(String str) {
        return b(str, "");
    }

    public static String b(String str, String str2) {
        SystemPropertiesReader systemPropertiesReader = e;
        return systemPropertiesReader != null ? systemPropertiesReader.d(str, str2) : str2;
    }

    public static boolean e(String str, boolean z) {
        SystemPropertiesReader systemPropertiesReader = e;
        return systemPropertiesReader != null ? systemPropertiesReader.c(str, z) : z;
    }

    public static int b(String str, int i) {
        SystemPropertiesReader systemPropertiesReader = e;
        return systemPropertiesReader != null ? systemPropertiesReader.c(str, i) : i;
    }
}

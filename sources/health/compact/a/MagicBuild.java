package health.compact.a;

/* loaded from: classes.dex */
public final class MagicBuild extends BaseBuild {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f13130a;
    public static final String b;
    public static final int d;

    static {
        boolean z = ReflectionUtils.b("com.hihonor.android.os.Build") != null;
        f13130a = z;
        d = a(z ? ReflectionUtils.b("com.hihonor.android.os.Build$VERSION") : null, "MAGIC_SDK_INT", 0);
        b = z ? SystemProperties.b("ro.build.version.magic") : "";
    }

    private MagicBuild() {
    }

    private static int a(Class cls, String str, int i) {
        if (cls != null) {
            Object b2 = ReflectionUtils.b((Class<?>) cls, str);
            if (b2 instanceof Integer) {
                return ((Integer) b2).intValue();
            }
        }
        return i;
    }
}

package health.compact.a;

/* loaded from: classes.dex */
public final class ReleaseLogUtil {
    private ReleaseLogUtil() {
    }

    public static void b(String str, Object... objArr) {
        LogUtil.e.i(str, true, objArr);
    }

    public static void a(String str, Object... objArr) {
        LogUtil.e.w(str, true, objArr);
    }

    public static void c(String str, Object... objArr) {
        LogUtil.e.e(str, true, objArr);
    }
}

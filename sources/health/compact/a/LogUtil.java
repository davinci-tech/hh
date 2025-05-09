package health.compact.a;

import com.huawei.haf.common.log.LogBuilder;
import com.huawei.haf.common.log.Logger;

/* loaded from: classes.dex */
public final class LogUtil {
    private static final LogImpl c;
    static Logger e;

    static {
        LogImpl logImpl = new LogImpl();
        c = logImpl;
        e = logImpl;
        LogConfig.b(true, 2, false, true);
    }

    private LogUtil() {
    }

    public static void b(Logger logger, boolean z, int i, boolean z2, boolean z3) {
        if (logger != null) {
            e = logger;
            LogBuilder.d();
        } else {
            e = c;
        }
        LogConfig.b(z, i, z2, z3);
    }

    public static void b(String str, Object... objArr) {
        e.v(str, LogConfig.c(0, str), objArr);
    }

    public static void d(String str, Object... objArr) {
        e.d(str, LogConfig.c(1, str), objArr);
    }

    public static void c(String str, Object... objArr) {
        e.i(str, LogConfig.c(2, str), objArr);
    }

    public static void a(String str, Object... objArr) {
        e.w(str, LogConfig.c(3, str), objArr);
    }

    public static void e(String str, Object... objArr) {
        e.e(str, LogConfig.c(4, str), objArr);
    }

    public static String a(Throwable th) {
        return e.toString(th);
    }

    public static boolean a() {
        return e.isReleaseVersion();
    }

    public static boolean c() {
        return e.isBetaVersion();
    }

    public static boolean e() {
        return e.isDebugVersion();
    }
}

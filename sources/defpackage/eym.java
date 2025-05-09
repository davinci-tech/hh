package defpackage;

import com.huawei.health.pluginlocation.logger.Logger;

/* loaded from: classes3.dex */
public class eym {
    private static Logger b;

    public static void c(Logger logger) {
        if (b == null) {
            b = logger;
        }
    }

    public static void a(String str, Object... objArr) {
        Logger logger = b;
        if (logger != null) {
            logger.d(str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        Logger logger = b;
        if (logger != null) {
            logger.i(str, objArr);
        }
    }

    public static void e(String str, Object... objArr) {
        Logger logger = b;
        if (logger != null) {
            logger.w(str, objArr);
        }
    }

    public static void c(String str, Object... objArr) {
        Logger logger = b;
        if (logger != null) {
            logger.e(str, objArr);
        }
    }
}

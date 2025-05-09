package com.huawei.profile.utils.logger;

/* loaded from: classes6.dex */
public final class AndroidLogger {
    private static final Printer LOG_PRINTER = new LoggerPrinter();

    private AndroidLogger() {
    }

    public static Settings init(String str) {
        return LOG_PRINTER.init(str);
    }

    public static void verbose(String str, Object... objArr) {
        LOG_PRINTER.verbose(str, objArr);
    }

    public static void debug(String str, Object... objArr) {
        LOG_PRINTER.debug(str, objArr);
    }

    public static void info(String str, Object... objArr) {
        LOG_PRINTER.info(str, objArr);
    }

    public static void warn(String str, Object... objArr) {
        LOG_PRINTER.warn(str, objArr);
    }

    public static void error(String str, Object... objArr) {
        LOG_PRINTER.error(null, str, objArr);
    }

    public static void error(Throwable th, String str, Object... objArr) {
        LOG_PRINTER.error(th, str, objArr);
    }
}

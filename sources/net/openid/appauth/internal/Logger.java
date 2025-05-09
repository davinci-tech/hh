package net.openid.appauth.internal;

import android.util.Log;
import defpackage.utq;

/* loaded from: classes7.dex */
public final class Logger {
    private static Logger c;
    private final LogWrapper b;
    private final int d;

    public interface LogWrapper {
        String getStackTraceString(Throwable th);

        boolean isLoggable(String str, int i);

        void println(int i, String str, String str2);
    }

    public static Logger c() {
        Logger logger;
        synchronized (Logger.class) {
            if (c == null) {
                c = new Logger(b.d);
            }
            logger = c;
        }
        return logger;
    }

    Logger(LogWrapper logWrapper) {
        this.b = (LogWrapper) utq.a(logWrapper);
        int i = 7;
        while (i >= 2 && this.b.isLoggable("AppAuth", i)) {
            i--;
        }
        this.d = i + 1;
    }

    public static void b(String str, Object... objArr) {
        c().a(3, null, str, objArr);
    }

    public static void d(Throwable th, String str, Object... objArr) {
        c().a(3, th, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        c().a(5, null, str, objArr);
    }

    public static void e(String str, Object... objArr) {
        c().a(6, null, str, objArr);
    }

    public static void b(Throwable th, String str, Object... objArr) {
        c().a(6, th, str, objArr);
    }

    public void a(int i, Throwable th, String str, Object... objArr) {
        if (this.d > i) {
            return;
        }
        if (objArr != null && objArr.length >= 1) {
            str = String.format(str, objArr);
        }
        if (th != null) {
            str = str + "\n" + this.b.getStackTraceString(th);
        }
        this.b.println(i, "AppAuth", str);
    }

    static final class b implements LogWrapper {
        private static final b d = new b();

        private b() {
        }

        @Override // net.openid.appauth.internal.Logger.LogWrapper
        public void println(int i, String str, String str2) {
            Log.println(i, str, str2);
        }

        @Override // net.openid.appauth.internal.Logger.LogWrapper
        public boolean isLoggable(String str, int i) {
            return Log.isLoggable(str, i);
        }

        @Override // net.openid.appauth.internal.Logger.LogWrapper
        public String getStackTraceString(Throwable th) {
            return Log.getStackTraceString(th);
        }
    }
}

package com.huawei.haf.common.dfx.crash;

import android.os.Process;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.Thread;

/* loaded from: classes.dex */
public final class CrashMonitor implements Thread.UncaughtExceptionHandler {
    private static Thread.UncaughtExceptionHandler b;
    private static Thread.UncaughtExceptionHandler d;
    private final CrashCallback c;

    private CrashMonitor(CrashCallback crashCallback) {
        this.c = crashCallback == null ? new DefaultCrashHandler("HAF_CrashMonitor") : crashCallback;
    }

    public static void e(CrashCallback crashCallback) {
        d = Thread.getDefaultUncaughtExceptionHandler();
        CrashMonitor crashMonitor = new CrashMonitor(crashCallback);
        b = crashMonitor;
        Thread.setDefaultUncaughtExceptionHandler(crashMonitor);
    }

    public static void e() {
        if (Thread.getDefaultUncaughtExceptionHandler() == b) {
            Thread.setDefaultUncaughtExceptionHandler(d);
        }
        b = null;
        d = null;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            if (!e(thread, th)) {
                return;
            }
        } catch (Throwable th2) {
            LogUtil.e("HAF_CrashMonitor", "uncaughtException, ex=", LogUtil.a(th2));
        }
        ReleaseLogUtil.b("HAF_CrashMonitor", "exit by app crash kill self");
        Process.killProcess(Process.myPid());
    }

    private boolean e(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        ReleaseLogUtil.b("HAF_CrashMonitor", "## handleException()");
        this.c.handleCrash(thread, th);
        if (this.c.isAllowRethrow() && (uncaughtExceptionHandler = d) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return false;
        }
        return this.c.isAllowSelfKill();
    }
}

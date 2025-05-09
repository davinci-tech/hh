package com.huawei.watchface;

import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;
import java.lang.Thread;

/* loaded from: classes7.dex */
public class fi implements Thread.UncaughtExceptionHandler {
    private static volatile fi b;

    /* renamed from: a, reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f11042a;

    public static fi a() {
        if (b == null) {
            synchronized (fi.class) {
                if (b == null) {
                    b = new fi();
                }
            }
        }
        return b;
    }

    public void b() {
        if (this.f11042a == null) {
            this.f11042a = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            HwLog.e("ThemeManagerCrashHandler", "CrashException" + HwLog.printException(th));
            HwWatchFaceApi.getInstance(Environment.getApplicationContext()).reportInterceptedCrash(th, Boolean.TRUE);
        }
        this.f11042a.uncaughtException(thread, th);
    }
}

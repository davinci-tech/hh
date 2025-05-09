package com.huawei.hianalytics.visual;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class l0 implements ThreadFactory {
    public static final Thread.UncaughtExceptionHandler d = new Thread.UncaughtExceptionHandler() { // from class: com.huawei.hianalytics.visual.l0$$ExternalSyntheticLambda0
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            HiLog.w("ThreadNamedFactory", "uncaught exception " + th.getMessage() + " occurred in thread [" + thread.getName() + "]");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    public final ThreadGroup f3935a;
    public final String b;
    public final AtomicInteger c = new AtomicInteger(1);

    public l0(String str) {
        SecurityManager securityManager = System.getSecurityManager();
        this.f3935a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.b = "HA_" + str + Constants.LINK;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f3935a, runnable, this.b + this.c.getAndIncrement(), 0L);
        thread.setUncaughtExceptionHandler(d);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }
}

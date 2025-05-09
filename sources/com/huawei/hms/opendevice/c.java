package com.huawei.hms.opendevice;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes4.dex */
public class c implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final ThreadGroup f5651a;
    private int b = 1;
    private final String c;

    public c(String str) {
        SecurityManager securityManager = System.getSecurityManager();
        this.f5651a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.c = str + "-pool-";
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        synchronized (this) {
            this.b++;
        }
        Thread thread = new Thread(this.f5651a, runnable, this.c + this.b, 0L);
        thread.setUncaughtExceptionHandler(null);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }
}

package com.huawei.openalliance.ad.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class n implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final ThreadGroup f7713a;
    private final AtomicInteger b;
    private final String c;
    private final int d;

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f7713a, runnable, this.c + this.b.getAndIncrement(), 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        int priority = thread.getPriority();
        int i = this.d;
        if (priority != i) {
            thread.setPriority(i);
        }
        return thread;
    }

    public n(String str, int i) {
        this.b = new AtomicInteger(1);
        this.d = i;
        this.f7713a = Thread.currentThread().getThreadGroup();
        this.c = "PPS-" + str + "-pool-thread-";
    }

    public n(String str) {
        this(str, 5);
    }
}

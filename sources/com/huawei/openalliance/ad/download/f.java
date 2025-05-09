package com.huawei.openalliance.ad.download;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class f implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private static final AtomicInteger f6807a = new AtomicInteger(1);
    private final AtomicInteger c = new AtomicInteger(1);
    private final ThreadGroup b = Thread.currentThread().getThreadGroup();
    private final String d = "PPS-download-pool-" + f6807a.getAndIncrement() + "-thread-";

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        thread.setPriority(1);
        return thread;
    }
}

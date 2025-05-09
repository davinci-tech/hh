package com.huawei.agconnect.credential.obs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public final class ak {

    /* renamed from: a, reason: collision with root package name */
    private static final int f1749a;
    private static final int b;
    private static final int c;
    private static final long d = 1;

    static class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private static AtomicInteger f1750a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "AGC-Thread-network-" + f1750a.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }

        a() {
        }
    }

    public static ExecutorService a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(c, b, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f1749a = availableProcessors;
        b = (availableProcessors * 2) + 1;
        c = availableProcessors + 1;
    }
}

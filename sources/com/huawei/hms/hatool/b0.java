package com.huawei.hms.hatool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class b0 {
    private static b0 b;
    private static b0 c;
    private static b0 d;

    /* renamed from: a, reason: collision with root package name */
    private ThreadPoolExecutor f4579a = new ThreadPoolExecutor(0, 1, 60000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(5000), new b());

    public void a(g gVar) {
        try {
            this.f4579a.execute(new a(gVar));
        } catch (RejectedExecutionException unused) {
            v.e("hmsSdk", "addToQueue() Exception has happened!Form rejected execution");
        }
    }

    public static b0 c() {
        return b;
    }

    static class b implements ThreadFactory {
        private static final AtomicInteger d = new AtomicInteger(1);

        /* renamed from: a, reason: collision with root package name */
        private final ThreadGroup f4581a;
        private final AtomicInteger b = new AtomicInteger(1);
        private final String c;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(this.f4581a, runnable, this.c + this.b.getAndIncrement(), 0L);
        }

        b() {
            SecurityManager securityManager = System.getSecurityManager();
            this.f4581a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.c = "FormalHASDK-base-" + d.getAndIncrement();
        }
    }

    public static b0 b() {
        return c;
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Runnable f4580a;

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = this.f4580a;
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Exception unused) {
                    v.e("hmsSdk", "InnerTask : Exception has happened,From internal operations!");
                }
            }
        }

        public a(Runnable runnable) {
            this.f4580a = runnable;
        }
    }

    public static b0 a() {
        return d;
    }

    private b0() {
    }

    static {
        new b0();
        new b0();
        b = new b0();
        c = new b0();
        d = new b0();
    }
}

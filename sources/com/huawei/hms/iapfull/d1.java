package com.huawei.hms.iapfull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class d1 {

    /* renamed from: a, reason: collision with root package name */
    private final ExecutorService f4699a;

    public void a(Runnable runnable) {
        this.f4699a.execute(runnable);
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static d1 f4700a = new d1();
    }

    static class c implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f4701a = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "Pay-Message-" + this.f4701a.getAndIncrement());
        }

        c() {
        }
    }

    public static d1 a() {
        return b.f4700a;
    }

    private d1() {
        y0.b("ThreadpoolManager", "threadpool init.");
        this.f4699a = Executors.newCachedThreadPool(new c());
    }
}

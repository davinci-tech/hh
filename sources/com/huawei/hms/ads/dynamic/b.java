package com.huawei.hms.ads.dynamic;

import com.huawei.hms.ads.uiengineloader.af;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4303a = "ExecutorsManager";
    private static final long b = 60;

    static final class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f4304a = new AtomicInteger(1);
        private final String b;

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.b + this.f4304a.getAndIncrement());
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.huawei.hms.ads.dynamic.b.a.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread2, Throwable th) {
                    af.d(b.f4303a, thread2.getName() + " : " + th.getMessage());
                }
            });
            return thread;
        }

        a(String str) {
            this.b = str + "-thread-";
        }
    }

    public static ExecutorService a(String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a(str));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}

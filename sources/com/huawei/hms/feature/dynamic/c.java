package com.huawei.hms.feature.dynamic;

import com.huawei.hms.common.util.Logger;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4511a = "ExecutorsManager";
    private static final long b = 60;

    public static class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f4512a = new AtomicInteger(1);
        private final String b;

        /* renamed from: com.huawei.hms.feature.dynamic.c$a$a, reason: collision with other inner class name */
        public class C0094a implements Thread.UncaughtExceptionHandler {
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                Logger.e(c.f4511a, thread.getName() + " : " + th.getMessage());
            }

            public C0094a() {
            }
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.b + this.f4512a.getAndIncrement());
            thread.setUncaughtExceptionHandler(new C0094a());
            return thread;
        }

        public a(String str) {
            this.b = str + "-thread-";
        }
    }

    public static ExecutorService a(int i, String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a(str));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}

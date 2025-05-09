package defpackage;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class aft {
    private static final ScheduledExecutorService d;

    static class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f195a;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "Core thread #" + this.f195a.getAndIncrement());
        }

        private a() {
            this.f195a = new AtomicInteger();
        }
    }

    public static void b(Runnable runnable, long j) {
        d.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    static {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new a());
        scheduledThreadPoolExecutor.setKeepAliveTime(10L, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        d = scheduledThreadPoolExecutor;
    }
}

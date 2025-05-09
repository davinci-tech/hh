package defpackage;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.util.ClockUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vbr {

    /* renamed from: a, reason: collision with root package name */
    private static final Boolean f17650a;
    private static final Boolean d;
    private static final Logger b = vha.a((Class<?>) vbr.class);
    private static final Runnable c = new Runnable() { // from class: vbr.5
        @Override // java.lang.Runnable
        public void run() {
            vbr.b.trace("warmup ...");
        }
    };
    public static final ThreadGroup e = new ThreadGroup("Timer");

    static {
        d = r1;
        Boolean d2 = vcb.d("EXECUTER_REMOVE_ON_CANCEL");
        r1 = d2 != null ? d2 : true;
        if (r1 != null) {
            try {
                ScheduledThreadPoolExecutor.class.getMethod("setRemoveOnCancelPolicy", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                r1 = null;
            }
        }
        f17650a = r1;
    }

    public static ExecutorService a(int i, ThreadFactory threadFactory) {
        b.trace("create thread pool of {} threads", Integer.valueOf(i));
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(i, threadFactory);
        newFixedThreadPool.execute(c);
        return newFixedThreadPool;
    }

    public static ScheduledExecutorService b(ThreadFactory threadFactory) {
        b.trace("create scheduled single thread pool");
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(threadFactory);
        c(newSingleThreadScheduledExecutor);
        newSingleThreadScheduledExecutor.execute(c);
        return newSingleThreadScheduledExecutor;
    }

    public static void b(long j, ExecutorService... executorServiceArr) {
        long millis;
        if (executorServiceArr.length == 0) {
            return;
        }
        long d2 = ClockUtil.d();
        for (ExecutorService executorService : executorServiceArr) {
            executorService.shutdown();
        }
        long millis2 = TimeUnit.NANOSECONDS.toMillis(ClockUtil.d() - d2);
        if (millis2 > j) {
            b.warn("shutdown {} ms exceeded the maximum {} ms", Long.valueOf(millis2), Long.valueOf(j));
        }
        try {
            try {
                long length = (j / executorServiceArr.length) / 2;
                for (ExecutorService executorService2 : executorServiceArr) {
                    if (!executorService2.awaitTermination(length, TimeUnit.MILLISECONDS)) {
                        List<Runnable> shutdownNow = executorService2.shutdownNow();
                        if (shutdownNow.size() > 0) {
                            b.debug("ignoring remaining {} scheduled task(s)", Integer.valueOf(shutdownNow.size()));
                        }
                        executorService2.awaitTermination(length, TimeUnit.MILLISECONDS);
                    }
                }
                millis = TimeUnit.NANOSECONDS.toMillis(ClockUtil.d() - d2);
                if (millis <= j) {
                    return;
                }
            } catch (InterruptedException unused) {
                for (ExecutorService executorService3 : executorServiceArr) {
                    executorService3.shutdownNow();
                }
                Thread.currentThread().interrupt();
                millis = TimeUnit.NANOSECONDS.toMillis(ClockUtil.d() - d2);
                if (millis <= j) {
                    return;
                }
            }
            b.warn("await termination {} ms exceeded the maximum {} ms", Long.valueOf(millis), Long.valueOf(j));
        } catch (Throwable th) {
            long millis3 = TimeUnit.NANOSECONDS.toMillis(ClockUtil.d() - d2);
            if (millis3 > j) {
                b.warn("await termination {} ms exceeded the maximum {} ms", Long.valueOf(millis3), Long.valueOf(j));
            }
            throw th;
        }
    }

    public static void c(List<Runnable> list) {
        Iterator<Runnable> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().run();
            } catch (Throwable th) {
                b.warn("Ignoring error:", th);
            }
        }
    }

    private static void c(ScheduledExecutorService scheduledExecutorService) {
        Boolean bool = f17650a;
        if (bool == null || !(scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
            return;
        }
        ((ScheduledThreadPoolExecutor) scheduledExecutorService).setRemoveOnCancelPolicy(bool.booleanValue());
    }
}

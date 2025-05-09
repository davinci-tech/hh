package defpackage;

import java.lang.Thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class nco extends ThreadPoolExecutor {

    /* renamed from: a, reason: collision with root package name */
    private static final int f15252a;
    private static final int b;
    private static final int c;
    private static nco e;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f15252a = availableProcessors;
        int i = availableProcessors + 1;
        c = i;
        b = i;
    }

    private nco(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, new RejectedExecutionHandler() { // from class: nco.3
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                ncx.e("Task rejected, too many task!");
            }
        });
    }

    public static nco b() {
        if (e == null) {
            synchronized (nco.class) {
                if (e == null) {
                    e = new nco(c, b, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue(64), new b());
                }
            }
        }
        return e;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            try {
                ((Future) runnable).get();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (CancellationException e2) {
                th = e2;
            } catch (ExecutionException e3) {
                th = e3.getCause();
            }
        }
        if (th != null) {
            ncx.e("Running task appeared exception! Thread [" + Thread.currentThread().getName() + "], because [" + th.getMessage() + "]\n" + th.getStackTrace());
        }
    }

    static class b implements ThreadFactory {
        private static final AtomicInteger e = new AtomicInteger(1);

        /* renamed from: a, reason: collision with root package name */
        private final String f15253a;
        private final AtomicInteger b = new AtomicInteger(1);
        private final ThreadGroup c;

        public b() {
            SecurityManager securityManager = System.getSecurityManager();
            this.c = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.f15253a = "HwSkinner task pool No." + e.getAndIncrement() + ", thread No.";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            String str = this.f15253a + this.b.getAndIncrement();
            ncx.c("Thread production, name is [" + str + "]");
            Thread thread = new Thread(this.c, runnable, str, 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: nco.b.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread2, Throwable th) {
                    ncx.c("Running task appeared exception! Thread [" + thread2.getName() + "], because [" + th.getMessage() + "]");
                }
            });
            return thread;
        }
    }
}

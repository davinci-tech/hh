package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mcs extends ThreadPoolExecutor {
    public mcs(int i, int i2, long j, TimeUnit timeUnit) {
        super(i, i2, j, timeUnit, e());
    }

    protected static PriorityBlockingQueue<Runnable> e() {
        return new PriorityBlockingQueue<>();
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new mcn(runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new mcn(callable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        e(0, runnable);
    }

    public void e(int i, Runnable runnable) {
        if (runnable instanceof mcn) {
            super.execute(runnable);
        } else {
            super.execute(b(i, runnable));
        }
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return d(0, runnable);
    }

    public Future<?> d(int i, Runnable runnable) {
        return super.submit(b(i, runnable));
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return b(0, runnable, t);
    }

    public <T> Future<T> b(int i, Runnable runnable, T t) {
        return super.submit(b(i, runnable), t);
    }

    private mck b(int i, Runnable runnable) {
        if (runnable instanceof mck) {
            return (mck) runnable;
        }
        return new mck(runnable, i);
    }
}

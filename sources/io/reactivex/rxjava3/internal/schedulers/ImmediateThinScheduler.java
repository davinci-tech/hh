package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class ImmediateThinScheduler extends Scheduler {
    static final Disposable DISPOSED;
    public static final Scheduler INSTANCE = new ImmediateThinScheduler();
    static final Scheduler.Worker WORKER = new ImmediateThinWorker();

    static {
        Disposable empty = Disposable.empty();
        DISPOSED = empty;
        empty.dispose();
    }

    private ImmediateThinScheduler() {
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable scheduleDirect(Runnable runnable) {
        runnable.run();
        return DISPOSED;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable schedulePeriodicallyDirect(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Scheduler.Worker createWorker() {
        return WORKER;
    }

    /* loaded from: classes10.dex */
    static final class ImmediateThinWorker extends Scheduler.Worker {
        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return false;
        }

        ImmediateThinWorker() {
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable runnable) {
            runnable.run();
            return ImmediateThinScheduler.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedulePeriodically(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
        }
    }
}

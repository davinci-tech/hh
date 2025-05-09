package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes10.dex */
public final class TestScheduler extends Scheduler {
    long counter;
    final Queue<TimedRunnable> queue;
    volatile long time;
    final boolean useOnScheduleHook;

    public TestScheduler() {
        this(false);
    }

    public TestScheduler(boolean z) {
        this.queue = new PriorityBlockingQueue(11);
        this.useOnScheduleHook = z;
    }

    public TestScheduler(long j, TimeUnit timeUnit) {
        this(j, timeUnit, false);
    }

    public TestScheduler(long j, TimeUnit timeUnit, boolean z) {
        this.queue = new PriorityBlockingQueue(11);
        this.time = timeUnit.toNanos(j);
        this.useOnScheduleHook = z;
    }

    static final class TimedRunnable implements Comparable<TimedRunnable> {
        final long count;
        final Runnable run;
        final TestWorker scheduler;
        final long time;

        TimedRunnable(TestWorker testWorker, long j, Runnable runnable, long j2) {
            this.time = j;
            this.run = runnable;
            this.scheduler = testWorker;
            this.count = j2;
        }

        public String toString() {
            return String.format("TimedRunnable(time = %d, run = %s)", Long.valueOf(this.time), this.run.toString());
        }

        @Override // java.lang.Comparable
        public int compareTo(TimedRunnable timedRunnable) {
            long j = this.time;
            long j2 = timedRunnable.time;
            if (j == j2) {
                return Long.compare(this.count, timedRunnable.count);
            }
            return Long.compare(j, j2);
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public long now(TimeUnit timeUnit) {
        return timeUnit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public void advanceTimeBy(long j, TimeUnit timeUnit) {
        advanceTimeTo(this.time + timeUnit.toNanos(j), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long j, TimeUnit timeUnit) {
        triggerActions(timeUnit.toNanos(j));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long j) {
        while (true) {
            TimedRunnable peek = this.queue.peek();
            if (peek == null || peek.time > j) {
                break;
            }
            this.time = peek.time == 0 ? this.time : peek.time;
            this.queue.remove(peek);
            if (!peek.scheduler.disposed) {
                peek.run.run();
            }
        }
        this.time = j;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Scheduler.Worker createWorker() {
        return new TestWorker();
    }

    final class TestWorker extends Scheduler.Worker {
        volatile boolean disposed;

        TestWorker() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            if (TestScheduler.this.useOnScheduleHook) {
                runnable = RxJavaPlugins.onSchedule(runnable);
            }
            Runnable runnable2 = runnable;
            long j2 = TestScheduler.this.time;
            long nanos = timeUnit.toNanos(j);
            TestScheduler testScheduler = TestScheduler.this;
            long j3 = testScheduler.counter;
            testScheduler.counter = 1 + j3;
            TimedRunnable timedRunnable = new TimedRunnable(this, j2 + nanos, runnable2, j3);
            TestScheduler.this.queue.add(timedRunnable);
            return new QueueRemove(timedRunnable);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable runnable) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            if (TestScheduler.this.useOnScheduleHook) {
                runnable = RxJavaPlugins.onSchedule(runnable);
            }
            Runnable runnable2 = runnable;
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedRunnable = new TimedRunnable(this, 0L, runnable2, j);
            TestScheduler.this.queue.add(timedRunnable);
            return new QueueRemove(timedRunnable);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public long now(TimeUnit timeUnit) {
            return TestScheduler.this.now(timeUnit);
        }

        final class QueueRemove extends AtomicReference<TimedRunnable> implements Disposable {
            private static final long serialVersionUID = -7874968252110604360L;

            QueueRemove(TimedRunnable timedRunnable) {
                lazySet(timedRunnable);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                TimedRunnable andSet = getAndSet(null);
                if (andSet != null) {
                    TestScheduler.this.queue.remove(andSet);
                }
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return get() == null;
            }
        }
    }
}

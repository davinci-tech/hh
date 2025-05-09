package org.eclipse.californium.elements.util;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import defpackage.vha;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class SerialExecutor extends AbstractExecutorService {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f15885a = vha.a((Class<?>) SerialExecutor.class);
    private final Executor b;
    private Runnable c;
    private final ReentrantLock e;
    private boolean g;
    private final Condition h;
    private final AtomicReference<Thread> i = new AtomicReference<>();
    private final AtomicReference<ExecutionListener> d = new AtomicReference<>();
    private final BlockingQueue<Runnable> f = new LinkedBlockingQueue();

    public interface ExecutionListener {
        void afterExecution();

        void beforeExecution();
    }

    public SerialExecutor(Executor executor) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.e = reentrantLock;
        this.h = reentrantLock.newCondition();
        if (executor == null) {
            this.g = true;
        } else if (executor instanceof SerialExecutor) {
            throw new IllegalArgumentException("Sequences of SerialExecutors are not supported!");
        }
        this.b = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.e.lock();
        try {
            if (this.g) {
                throw new RejectedExecutionException("SerialExecutor already shutdown!");
            }
            this.f.offer(runnable);
            if (this.c == null) {
                b();
            }
        } finally {
            this.e.unlock();
        }
    }

    public void d() {
        if (this.i.get() != Thread.currentThread()) {
            Thread thread = this.i.get();
            if (thread == null) {
                throw new ConcurrentModificationException(this + " is not owned!");
            }
            throw new ConcurrentModificationException(this + " owned by " + thread.getName() + "!");
        }
    }

    public boolean a() {
        return this.i.get() == Thread.currentThread();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        Thread thread = this.i.get();
        if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.i, null, Thread.currentThread())) {
            return;
        }
        if (thread == null) {
            throw new ConcurrentModificationException(this + " was already owned!");
        }
        throw new ConcurrentModificationException(this + " already owned by " + thread.getName() + "!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.i, Thread.currentThread(), null)) {
            return;
        }
        Thread thread = this.i.get();
        if (thread == null) {
            throw new ConcurrentModificationException(this + " is not owned, clear failed!");
        }
        throw new ConcurrentModificationException(this + " owned by " + thread.getName() + ", clear failed!");
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.e.lock();
        try {
            this.g = true;
        } finally {
            this.e.unlock();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        this.e.lock();
        try {
            ArrayList arrayList = new ArrayList(this.f.size());
            a(arrayList);
            return arrayList;
        } finally {
            this.e.unlock();
        }
    }

    public int a(Collection<Runnable> collection) {
        this.e.lock();
        try {
            shutdown();
            return this.f.drainTo(collection);
        } finally {
            this.e.unlock();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        this.e.lock();
        try {
            return this.g;
        } finally {
            this.e.unlock();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        boolean z;
        this.e.lock();
        try {
            if (this.g) {
                if (this.c == null) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            this.e.unlock();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        this.e.lock();
        try {
            long nanos = timeUnit.toNanos(j);
            do {
                if (this.g && this.c == null) {
                    break;
                }
                nanos = this.h.awaitNanos(nanos);
            } while (nanos > 0);
            if (this.g) {
                if (this.c == null) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            this.e.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b() {
        this.e.lock();
        try {
            final Runnable poll = this.f.poll();
            this.c = poll;
            if (poll != null) {
                this.b.execute(new Runnable() { // from class: org.eclipse.californium.elements.util.SerialExecutor.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            SerialExecutor.this.h();
                            ExecutionListener executionListener = (ExecutionListener) SerialExecutor.this.d.get();
                            if (executionListener != null) {
                                try {
                                    executionListener.beforeExecution();
                                } catch (Throwable th) {
                                    try {
                                        SerialExecutor.f15885a.error("unexpected error occurred:", th);
                                        if (executionListener != null) {
                                            try {
                                                executionListener.afterExecution();
                                            } catch (Throwable th2) {
                                                SerialExecutor.f15885a.error("unexpected error occurred after execution:", th2);
                                            }
                                        }
                                    } finally {
                                    }
                                }
                            }
                            poll.run();
                            if (executionListener != null) {
                                try {
                                    executionListener.afterExecution();
                                } catch (Throwable th3) {
                                    SerialExecutor.f15885a.error("unexpected error occurred after execution:", th3);
                                }
                            }
                            SerialExecutor.this.e();
                            try {
                                SerialExecutor.this.b();
                            } catch (RejectedExecutionException e) {
                                SerialExecutor.f15885a.debug("shutdown?", (Throwable) e);
                            }
                        } catch (Throwable th4) {
                            try {
                                SerialExecutor.this.b();
                            } catch (RejectedExecutionException e2) {
                                SerialExecutor.f15885a.debug("shutdown?", (Throwable) e2);
                            }
                            throw th4;
                        }
                    }
                });
            } else if (this.g) {
                this.h.signalAll();
            }
        } finally {
            this.e.unlock();
        }
    }

    public ExecutionListener c(ExecutionListener executionListener) {
        return this.d.getAndSet(executionListener);
    }
}

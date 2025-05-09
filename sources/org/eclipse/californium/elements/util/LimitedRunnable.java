package org.eclipse.californium.elements.util;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public abstract class LimitedRunnable implements Runnable {
    private final AtomicInteger counter;
    private volatile boolean overflow;

    public LimitedRunnable(AtomicInteger atomicInteger) {
        this.counter = atomicInteger;
    }

    public void onQueueing() {
        if (this.counter.decrementAndGet() >= 0) {
            return;
        }
        this.overflow = true;
        throw new RejectedExecutionException("queue overflow!");
    }

    public void onDequeueing() {
        this.counter.incrementAndGet();
    }

    public boolean isOverflown() {
        return this.overflow;
    }

    public void execute(Executor executor) {
        try {
            onQueueing();
            executor.execute(this);
        } catch (RejectedExecutionException e) {
            onDequeueing();
            throw e;
        }
    }
}

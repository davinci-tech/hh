package com.huawei.profile.profile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes9.dex */
public class ProfileFuture<V> implements Future<V> {
    private CountDownLatch latch = new CountDownLatch(1);
    private volatile V value;

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException {
        this.latch.await();
        return this.value;
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        if (!this.latch.await(j, timeUnit)) {
            throw new TimeoutException();
        }
        return this.value;
    }

    public void set(V v) {
        this.value = v;
        this.latch.countDown();
    }
}

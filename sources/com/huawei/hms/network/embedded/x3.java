package com.huawei.hms.network.embedded;

import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class x3 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f5566a;
    public long b;
    public long c;

    public long timeoutNanos() {
        return this.c;
    }

    public x3 timeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        this.c = timeUnit.toNanos(j);
        return this;
    }

    public boolean hasDeadline() {
        return this.f5566a;
    }

    public x3 deadlineNanoTime(long j) {
        this.f5566a = true;
        this.b = j;
        return this;
    }

    public long deadlineNanoTime() {
        if (this.f5566a) {
            return this.b;
        }
        throw new IllegalStateException("No deadline");
    }

    public final x3 deadline(long j, TimeUnit timeUnit) {
        if (j > 0) {
            if (timeUnit != null) {
                return deadlineNanoTime(System.nanoTime() + timeUnit.toNanos(j));
            }
            throw new IllegalArgumentException("unit == null");
        }
        throw new IllegalArgumentException("duration <= 0: " + j);
    }

    public x3 clearTimeout() {
        this.c = 0L;
        return this;
    }

    public x3 clearDeadline() {
        this.f5566a = false;
        return this;
    }
}

package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class ac {
    public static final ac d = new a();

    /* renamed from: a, reason: collision with root package name */
    public boolean f5164a;
    public long b;
    public long c;

    public final class a extends ac {
        @Override // com.huawei.hms.network.embedded.ac
        public ac deadlineNanoTime(long j) {
            return this;
        }

        @Override // com.huawei.hms.network.embedded.ac
        public void throwIfReached() throws IOException {
        }

        @Override // com.huawei.hms.network.embedded.ac
        public ac timeout(long j, TimeUnit timeUnit) {
            return this;
        }
    }

    public static long a(long j, long j2) {
        return j == 0 ? j2 : (j2 != 0 && j >= j2) ? j2 : j;
    }

    public ac timeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        this.c = timeUnit.toNanos(j);
        return this;
    }

    public void throwIfReached() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f5164a && this.b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public long e() {
        return this.c;
    }

    public ac deadlineNanoTime(long j) {
        this.f5164a = true;
        this.b = j;
        return this;
    }

    public boolean d() {
        return this.f5164a;
    }

    public long c() {
        if (this.f5164a) {
            return this.b;
        }
        throw new IllegalStateException("No deadline");
    }

    public ac b() {
        this.c = 0L;
        return this;
    }

    public final void a(Object obj) throws InterruptedIOException {
        try {
            boolean d2 = d();
            long e = e();
            long j = 0;
            if (!d2 && e == 0) {
                obj.wait();
                return;
            }
            long nanoTime = System.nanoTime();
            if (d2 && e != 0) {
                e = Math.min(e, c() - nanoTime);
            } else if (d2) {
                e = c() - nanoTime;
            }
            if (e > 0) {
                long j2 = e / 1000000;
                obj.wait(j2, (int) (e - (1000000 * j2)));
                j = System.nanoTime() - nanoTime;
            }
            if (j >= e) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public final ac a(long j, TimeUnit timeUnit) {
        if (j > 0) {
            if (timeUnit != null) {
                return deadlineNanoTime(System.nanoTime() + timeUnit.toNanos(j));
            }
            throw new IllegalArgumentException("unit == null");
        }
        throw new IllegalArgumentException("duration <= 0: " + j);
    }

    public ac a() {
        this.f5164a = false;
        return this;
    }
}

package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class ib extends ac {
    public ac e;

    @Override // com.huawei.hms.network.embedded.ac
    public ac timeout(long j, TimeUnit timeUnit) {
        return this.e.timeout(j, timeUnit);
    }

    @Override // com.huawei.hms.network.embedded.ac
    public void throwIfReached() throws IOException {
        this.e.throwIfReached();
    }

    public final ac f() {
        return this.e;
    }

    @Override // com.huawei.hms.network.embedded.ac
    public long e() {
        return this.e.e();
    }

    @Override // com.huawei.hms.network.embedded.ac
    public ac deadlineNanoTime(long j) {
        return this.e.deadlineNanoTime(j);
    }

    @Override // com.huawei.hms.network.embedded.ac
    public boolean d() {
        return this.e.d();
    }

    @Override // com.huawei.hms.network.embedded.ac
    public long c() {
        return this.e.c();
    }

    @Override // com.huawei.hms.network.embedded.ac
    public ac b() {
        return this.e.b();
    }

    public final ib a(ac acVar) {
        if (acVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.e = acVar;
        return this;
    }

    @Override // com.huawei.hms.network.embedded.ac
    public ac a() {
        return this.e.a();
    }

    public ib(ac acVar) {
        if (acVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.e = acVar;
    }
}

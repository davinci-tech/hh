package com.huawei.hms.network.embedded;

import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class sb extends ac {
    public ac e;
    public boolean f;
    public long g;
    public long h;

    public void f() {
        this.e.timeout(this.h, TimeUnit.NANOSECONDS);
        if (this.f) {
            this.e.deadlineNanoTime(this.g);
        } else {
            this.e.a();
        }
    }

    public void a(ac acVar) {
        long c;
        this.e = acVar;
        boolean d = acVar.d();
        this.f = d;
        this.g = d ? acVar.c() : -1L;
        long e = acVar.e();
        this.h = e;
        acVar.timeout(ac.a(e, e()), TimeUnit.NANOSECONDS);
        if (this.f && d()) {
            c = Math.min(c(), this.g);
        } else if (!d()) {
            return;
        } else {
            c = c();
        }
        acVar.deadlineNanoTime(c);
    }
}

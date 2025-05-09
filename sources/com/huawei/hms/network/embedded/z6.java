package com.huawei.hms.network.embedded;

import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class z6 {
    public final z8 delegate;

    public interface a {
        void a(String str, int i, String str2);
    }

    public int c() {
        return this.delegate.c();
    }

    public boolean b(String str, int i, String str2) {
        return this.delegate.b(str, i, str2);
    }

    public void b(a aVar) {
        this.delegate.b(aVar);
    }

    public void b() {
        this.delegate.b();
    }

    public void a(a aVar) {
        this.delegate.a(aVar);
    }

    public int a(String str, int i, String str2) {
        return this.delegate.a(str, i, str2);
    }

    public int a() {
        return this.delegate.a();
    }

    public z6(int i, long j, TimeUnit timeUnit) {
        this.delegate = new z8(i, j, timeUnit);
    }

    public z6() {
        this(5, 5L, TimeUnit.MINUTES);
    }
}

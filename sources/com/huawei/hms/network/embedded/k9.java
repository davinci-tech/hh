package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.n7;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class k9 implements n7.a {

    /* renamed from: a, reason: collision with root package name */
    public final List<n7> f5349a;
    public final d9 b;

    @Nullable
    public final v8 c;
    public final int d;
    public final t7 e;
    public final t6 f;
    public final int g;
    public final int h;
    public final int i;
    public int j;

    @Override // com.huawei.hms.network.embedded.n7.a
    public t7 request() {
        return this.e;
    }

    public d9 f() {
        return this.b;
    }

    public v8 e() {
        v8 v8Var = this.c;
        if (v8Var != null) {
            return v8Var;
        }
        throw new IllegalStateException();
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public int d() {
        return this.g;
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public t6 call() {
        return this.f;
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public n7.a c(int i, TimeUnit timeUnit) {
        return new k9(this.f5349a, this.b, this.c, this.d, this.e, this.f, f8.a("timeout", i, timeUnit), this.h, this.i);
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public int c() {
        return this.h;
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public n7.a b(int i, TimeUnit timeUnit) {
        return new k9(this.f5349a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, f8.a("timeout", i, timeUnit));
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public int b() {
        return this.i;
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    @Nullable
    public y6 a() {
        v8 v8Var = this.c;
        if (v8Var != null) {
            return v8Var.b();
        }
        return null;
    }

    public v7 a(t7 t7Var, d9 d9Var, @Nullable v8 v8Var) throws IOException {
        if (this.d >= this.f5349a.size()) {
            throw new AssertionError();
        }
        this.j++;
        v8 v8Var2 = this.c;
        if (v8Var2 != null && !v8Var2.b().a(t7Var.k())) {
            throw new IllegalStateException("network interceptor " + this.f5349a.get(this.d - 1) + " must retain the same host and port");
        }
        if (this.c != null && this.j > 1) {
            throw new IllegalStateException("network interceptor " + this.f5349a.get(this.d - 1) + " must call proceed() exactly once");
        }
        k9 k9Var = new k9(this.f5349a, d9Var, v8Var, this.d + 1, t7Var, this.f, this.g, this.h, this.i);
        n7 n7Var = this.f5349a.get(this.d);
        v7 intercept = n7Var.intercept(k9Var);
        if (v8Var != null && this.d + 1 < this.f5349a.size() && k9Var.j != 1) {
            throw new IllegalStateException("network interceptor " + n7Var + " must call proceed() exactly once");
        }
        if (intercept == null) {
            throw new NullPointerException("interceptor " + n7Var + " returned null");
        }
        if (intercept.s() != null || t7Var.f()) {
            return intercept;
        }
        throw new IllegalStateException("interceptor " + n7Var + " returned a response with no body");
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public v7 a(t7 t7Var) throws IOException {
        return a(t7Var, this.b, this.c);
    }

    @Override // com.huawei.hms.network.embedded.n7.a
    public n7.a a(int i, TimeUnit timeUnit) {
        return new k9(this.f5349a, this.b, this.c, this.d, this.e, this.f, this.g, f8.a("timeout", i, timeUnit), this.i);
    }

    public k9(List<n7> list, d9 d9Var, @Nullable v8 v8Var, int i, t7 t7Var, t6 t6Var, int i2, int i3, int i4) {
        this.f5349a = list;
        this.b = d9Var;
        this.c = v8Var;
        this.d = i;
        this.e = t7Var;
        this.f = t6Var;
        this.g = i2;
        this.h = i3;
        this.i = i4;
    }
}

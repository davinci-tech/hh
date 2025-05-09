package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.c9;
import com.huawei.hms.network.embedded.n7;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/* loaded from: classes9.dex */
public final class w8 {
    public static final /* synthetic */ boolean m = true;

    /* renamed from: a, reason: collision with root package name */
    public final d9 f5560a;
    public final p6 b;
    public final z8 c;
    public final t6 d;
    public final g7 e;
    public c9.a f;
    public c9.a g;
    public final c9 h;
    public y8 i;
    public boolean j;
    public x7 k;
    public int l;

    public void e() {
        if (!m && Thread.holdsLock(this.c)) {
            throw new AssertionError();
        }
        synchronized (this.c) {
            this.j = true;
        }
    }

    public boolean d() {
        boolean z;
        synchronized (this.c) {
            z = this.j;
        }
        return z;
    }

    public boolean c() {
        synchronized (this.c) {
            boolean z = true;
            if (this.k != null) {
                return true;
            }
            if (f()) {
                this.k = this.f5560a.i.b();
                return true;
            }
            c9.a aVar = this.f;
            if ((aVar == null || !aVar.f()) && !this.h.a()) {
                z = false;
            }
            return z;
        }
    }

    public c9.a b() {
        return this.f;
    }

    public void a(q7 q7Var, t7 t7Var) throws IOException {
        y8 y8Var;
        c9.a aVar;
        int e = q7Var.e();
        int z = q7Var.z();
        int D = q7Var.D();
        int u = q7Var.u();
        int o = q7Var.o();
        boolean A = q7Var.A();
        if (!t7Var.f()) {
            throw new IllegalArgumentException("a normal Request without http2ConnectionIndex");
        }
        if (Integer.parseInt(t7Var.a("Http2ConnectionIndex")) > this.c.a(this.b)) {
            if (this.k == null && ((aVar = this.f) == null || !aVar.f())) {
                this.f = this.h.b();
            }
            synchronized (this.c) {
                x7 g = this.f.g();
                this.k = g;
                y8Var = new y8(this.c, g);
                if (this.f.a()) {
                    y8Var.a(this.f.b(), this.l, o);
                    y8Var.a(this.f);
                }
            }
            y8Var.a(e, z, D, u, o, A, this.d, this.e);
            this.f.a(y8Var.b());
            y8Var.x = System.nanoTime();
            synchronized (this.c) {
                this.c.c(y8Var);
            }
        }
    }

    public y8 a() {
        if (m || Thread.holdsLock(this.c)) {
            return this.i;
        }
        throw new AssertionError();
    }

    public g9 a(q7 q7Var, n7.a aVar, boolean z) {
        try {
            y8 a2 = a(aVar.d(), aVar.c(), aVar.b(), q7Var.u(), q7Var.o(), q7Var.A(), z);
            if (a2.g() && a2.m == 0) {
                m7 k = this.f5560a.getRequest().k();
                q7Var.a(k.h(), k.n(), k.s());
            }
            return a2.a(q7Var, aVar);
        } catch (b9 e) {
            e();
            throw e;
        } catch (IOException e2) {
            e();
            throw new b9(e2);
        }
    }

    private boolean f() {
        y8 y8Var = this.f5560a.i;
        return y8Var != null && y8Var.l == 0 && f8.a(y8Var.b().a().l(), this.b.l());
    }

    private y8 a(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) throws IOException {
        while (true) {
            y8 a2 = a(i, i2, i3, i4, i5, z);
            synchronized (this.c) {
                if (a2.m == 0 && !a2.g()) {
                    return a2;
                }
                if (a2.a(z2)) {
                    return a2;
                }
                a2.h();
            }
        }
    }

    private y8 a(int i, int i2, int i3, int i4, int i5, boolean z) throws IOException {
        Socket socket;
        Socket releaseConnectionNoEvents;
        y8 y8Var;
        y8 y8Var2;
        boolean z2;
        x7 x7Var;
        boolean z3;
        List<x7> list;
        c9.a aVar;
        c9.a f;
        synchronized (this.c) {
            if (this.f5560a.isCanceled()) {
                throw new IOException("Canceled");
            }
            this.j = false;
            y8 y8Var3 = this.f5560a.i;
            socket = null;
            releaseConnectionNoEvents = (this.f5560a.i == null || !this.f5560a.i.k) ? null : this.f5560a.releaseConnectionNoEvents();
            if (this.f5560a.i != null) {
                y8Var2 = this.f5560a.i;
                y8Var = null;
            } else {
                y8Var = y8Var3;
                y8Var2 = null;
            }
            if (y8Var2 == null) {
                if (this.c.a(this.b, this.f5560a, null, false)) {
                    y8Var2 = this.f5560a.i;
                    x7Var = null;
                    z2 = true;
                } else {
                    x7Var = this.k;
                    if (x7Var != null) {
                        this.k = null;
                        c9.a aVar2 = this.g;
                        if (aVar2 != null) {
                            this.f = aVar2;
                        }
                    } else if (f()) {
                        x7Var = this.f5560a.i.b();
                    }
                    z2 = false;
                }
            }
            z2 = false;
            x7Var = null;
        }
        f8.a(releaseConnectionNoEvents);
        if (y8Var != null) {
            this.e.connectionReleased(this.d, y8Var);
        }
        if (z2) {
            this.e.connectionAcquired(this.d, y8Var2);
        }
        if (y8Var2 != null && (f = this.f5560a.i.f()) != null) {
            this.g = f;
            return y8Var2;
        }
        if (x7Var != null || ((aVar = this.f) != null && aVar.f())) {
            z3 = false;
        } else {
            this.f = this.h.b();
            z3 = true;
        }
        synchronized (this.c) {
            if (this.f5560a.isCanceled()) {
                throw new IOException("Canceled");
            }
            if (z3) {
                list = this.f.d();
                if (this.c.a(this.b, this.f5560a, list, false)) {
                    y8Var2 = this.f5560a.i;
                    z2 = true;
                }
            } else {
                list = null;
            }
            if (!z2) {
                if (x7Var == null) {
                    x7Var = this.f.g();
                }
                y8Var2 = new y8(this.c, x7Var);
                y8Var2.a(this.f);
                if (this.f.a()) {
                    y8Var2.a(this.f.b(), this.l, i5);
                }
                this.i = y8Var2;
            }
        }
        if (!z2) {
            y8Var2.a(i, i2, i3, i4, i5, z, this.d, this.e);
            this.f.a(y8Var2.b());
            synchronized (this.c) {
                this.i = null;
                if (this.c.a(this.b, this.f5560a, list, true)) {
                    y8Var2.k = true;
                    socket = y8Var2.c();
                    y8Var2 = this.f5560a.i;
                    this.k = x7Var;
                } else {
                    this.c.c(y8Var2);
                    this.f5560a.acquireConnectionNoEvents(y8Var2);
                }
            }
            f8.a(socket);
        }
        this.e.connectionAcquired(this.d, y8Var2);
        return y8Var2;
    }

    public w8(d9 d9Var, z8 z8Var, p6 p6Var, t6 t6Var, g7 g7Var, int i) {
        this(d9Var, z8Var, p6Var, t6Var, g7Var);
        this.l = i;
    }

    public w8(d9 d9Var, z8 z8Var, p6 p6Var, t6 t6Var, g7 g7Var) {
        this.f5560a = d9Var;
        this.c = z8Var;
        this.b = p6Var;
        this.d = t6Var;
        this.e = g7Var;
        this.h = new c9(p6Var, z8Var.routeDatabase, t6Var, g7Var).a(d9Var.getRequest());
        this.l = 0;
    }
}

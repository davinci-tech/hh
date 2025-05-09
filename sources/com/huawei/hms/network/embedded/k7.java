package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.s7;
import com.huawei.hms.network.embedded.s7.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/* loaded from: classes9.dex */
public final class k7 extends e7 {
    public static final int o = 5;
    public static final int p = 2;
    public static final int q = 32;
    public static final /* synthetic */ boolean r = true;
    public int j = 5;
    public int k = 2;
    public int l = 32;
    public final List<b> m = new ArrayList();
    public int n = 2;

    public int k() {
        int i;
        synchronized (this) {
            i = this.l;
        }
        return i;
    }

    public int j() {
        int i;
        synchronized (this) {
            i = this.j;
        }
        return i;
    }

    @Override // com.huawei.hms.network.embedded.e7, com.huawei.hms.network.embedded.o6
    public int h() {
        return this.k;
    }

    public void e(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        synchronized (this) {
            this.l = i;
        }
        l();
    }

    public void d(int i) {
        if (i >= 1) {
            this.k = i;
        } else {
            throw new IllegalArgumentException("max < 1: " + i);
        }
    }

    public void c(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        synchronized (this) {
            this.j = i;
        }
        l();
    }

    @Override // com.huawei.hms.network.embedded.e7, com.huawei.hms.network.embedded.o6
    public void b(String str, int i, String str2) {
        synchronized (this) {
            Iterator<b> it = this.m.iterator();
            while (it.hasNext()) {
                b next = it.next();
                if (next.f5346a.equals(str) && next.b == i && next.c.equals(str2)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    @Override // com.huawei.hms.network.embedded.e7, com.huawei.hms.network.embedded.o6
    public void b(s7 s7Var) {
        a((Deque<Deque<s7>>) this.g, (Deque<s7>) s7Var);
    }

    @Override // com.huawei.hms.network.embedded.e7, com.huawei.hms.network.embedded.o6
    public void b(s7.a aVar) {
        synchronized (this) {
            b a2 = a(aVar.h());
            if (a2 != null) {
                a2.d--;
                a2.e--;
                if (aVar.e()) {
                    m7 k = aVar.h().k();
                    a2.f = aVar.d().b(k.h(), k.n(), k.s());
                }
            }
        }
        aVar.c().decrementAndGet();
        a((Deque<Deque<s7.a>>) this.f, (Deque<s7.a>) aVar);
    }

    @Override // com.huawei.hms.network.embedded.e7, com.huawei.hms.network.embedded.o6
    public void a(String str, int i, String str2) {
        synchronized (this) {
            if (c(str, i, str2) != null) {
                return;
            }
            this.m.add(new b(str, i, str2));
            l();
        }
    }

    @Override // com.huawei.hms.network.embedded.e7, com.huawei.hms.network.embedded.o6
    public void a(s7.a aVar) {
        boolean z;
        s7.a a2;
        synchronized (this) {
            if (aVar == null) {
                return;
            }
            b a3 = a(aVar.h());
            if (a3 != null) {
                z = a(aVar, a3);
            } else {
                c(aVar);
                z = false;
            }
            if (!aVar.f().d && (a2 = a(aVar.g())) != null) {
                aVar.a(a2);
            }
            l();
            if (z) {
                b(aVar, a3);
            }
        }
    }

    private boolean l() {
        boolean z;
        int i;
        int d;
        if (!r && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<s7.a> it = this.e.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                s7.a next = it.next();
                if (this.f.size() >= this.f5231a) {
                    break;
                }
                b a2 = a(next.h());
                if (a2 != null) {
                    i = this.l;
                    d = e(next);
                } else {
                    i = this.j;
                    d = d(next);
                }
                if (d < i) {
                    it.remove();
                    next.c().incrementAndGet();
                    arrayList.add(next);
                    this.f.add(next);
                    if (a2 != null) {
                        a2.e++;
                    }
                }
            }
            if (d() <= 0) {
                z = false;
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((s7.a) it2.next()).a(i());
        }
        return z;
    }

    private int e(s7.a aVar) {
        int i = 0;
        for (b bVar : this.m) {
            if (bVar.f5346a.equals(aVar.g())) {
                i += bVar.e;
            }
        }
        return i;
    }

    private int d(s7.a aVar) {
        return aVar.c().get() - e(aVar);
    }

    private void c(s7.a aVar) {
        this.e.add(aVar);
    }

    private b c(String str, int i, String str2) {
        for (b bVar : this.m) {
            if (bVar.f5346a.equals(str) && bVar.b == i && bVar.c.equals(str2)) {
                return bVar;
            }
        }
        return null;
    }

    private void b(s7.a aVar, b bVar) {
        int i;
        boolean z;
        synchronized (this) {
            if (bVar.d == this.l + 1) {
                m7 k = aVar.h().k();
                bVar.f = aVar.d().b(k.h(), k.n(), k.s());
            }
            i = bVar.f;
            if (this.l * i * this.n >= bVar.d || i >= this.k) {
                z = false;
            } else {
                bVar.f++;
                bVar.e++;
                z = true;
            }
        }
        if (z) {
            t6 a2 = aVar.d().a(aVar.h().i().b("Http2ConnectionIndex", Integer.toString(i + 1)).a());
            if (a2 instanceof s7) {
                s7 s7Var = (s7) a2;
                a aVar2 = new a();
                Objects.requireNonNull(s7Var);
                s7.a aVar3 = s7Var.new a(aVar2);
                aVar3.i();
                synchronized (this) {
                    this.f.add(aVar3);
                }
                aVar3.a(i());
            }
        }
    }

    private boolean a(s7.a aVar, b bVar) {
        if (bVar.d == 0) {
            m7 k = aVar.h().k();
            if (!aVar.d().c(k.h(), k.n(), k.s())) {
                b(k.h(), k.n(), k.s());
                c(aVar);
                return false;
            }
        }
        bVar.d++;
        this.e.add(aVar);
        return true;
    }

    private <T> void a(Deque<T> deque, T t) {
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = this.c;
        }
        if (l() || runnable == null) {
            return;
        }
        runnable.run();
    }

    public class a implements u6 {
        @Override // com.huawei.hms.network.embedded.u6
        public void onFailure(t6 t6Var, IOException iOException) {
        }

        @Override // com.huawei.hms.network.embedded.u6
        public void onResponse(t6 t6Var, v7 v7Var) throws IOException {
            if (v7Var != null) {
                v7Var.close();
            }
        }

        public a() {
        }
    }

    public final class b {
        public static final /* synthetic */ boolean h = true;

        /* renamed from: a, reason: collision with root package name */
        public String f5346a;
        public int b;
        public String c;
        public int d;
        public int e;
        public int f = 1;

        private void a(String str, int i, String str2) {
            if (!h && !Thread.holdsLock(k7.this)) {
                throw new AssertionError();
            }
            Iterator<s7.a> it = k7.this.f.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                m7 k = it.next().h().k();
                if (k.h().equals(str) && k.n() == i && k.s().equals(str2)) {
                    i2++;
                }
            }
            this.e = i2;
            Iterator<s7.a> it2 = k7.this.e.iterator();
            while (it2.hasNext()) {
                m7 k2 = it2.next().h().k();
                if (k2.h().equals(str) && k2.n() == i && k2.s().equals(str2)) {
                    i2++;
                }
            }
            this.d = i2;
        }

        public b(String str, int i, String str2) {
            this.f5346a = str;
            this.b = i;
            this.c = str2;
            a(str, i, str2);
        }
    }

    private b a(t7 t7Var) {
        return c(t7Var.k().h(), t7Var.k().n(), t7Var.k().s());
    }

    public k7(ExecutorService executorService) {
        this.d = executorService;
    }

    public k7() {
    }
}

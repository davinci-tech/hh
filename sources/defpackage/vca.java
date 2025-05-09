package defpackage;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes7.dex */
public class vca {
    private final a d;
    private final String e;

    /* renamed from: a, reason: collision with root package name */
    private final AtomicLong f17658a = new AtomicLong();
    private final AtomicLong c = new AtomicLong();
    private final AtomicLong h = new AtomicLong(-1);
    private final int b = 0;

    public vca(String str, a aVar) {
        this.e = str;
        this.d = aVar.c(this);
    }

    public void h() {
        synchronized (this.c) {
            this.c.addAndGet(this.f17658a.getAndSet(0L));
        }
    }

    public String c(int i) {
        long j;
        long j2;
        synchronized (this.c) {
            j = this.f17658a.get();
            j2 = this.c.get();
        }
        return c(i, this.e, j) + String.format(" (%8d overall).", Long.valueOf(j2));
    }

    public String b() {
        return this.e;
    }

    public void a(long j) {
        synchronized (this.c) {
            long j2 = this.h.get();
            if (j2 < 0) {
                this.h.set(0L);
                j2 = 0;
            }
            this.f17658a.set((j - this.c.get()) - j2);
        }
    }

    public long a() {
        return this.f17658a.incrementAndGet();
    }

    public long e() {
        long j;
        long j2;
        synchronized (this.c) {
            j = this.c.get();
            j2 = this.f17658a.get();
        }
        return j + j2;
    }

    public long g() {
        long andSet;
        synchronized (this.c) {
            this.c.addAndGet(this.f17658a.getAndSet(0L));
            andSet = this.c.getAndSet(0L);
            long j = this.h.get();
            if (j > 0) {
                this.h.set(j + andSet);
            } else {
                this.h.set(andSet);
            }
        }
        return andSet;
    }

    public boolean d() {
        boolean z;
        synchronized (this.c) {
            z = this.f17658a.get() > 0 || this.c.get() > 0;
        }
        return z;
    }

    public boolean c() {
        return this.h.get() >= 0;
    }

    public String toString() {
        a aVar = this.d;
        return c(aVar == null ? this.b : aVar.b());
    }

    public static String c(int i, String str, long j) {
        if (i == 0) {
            return String.format("%s: %8d", str, Long.valueOf(j));
        }
        return String.format("%" + i + "s: %8d", str, Long.valueOf(j));
    }

    public static class a {
        int c;

        public a c(vca vcaVar) {
            return e(vcaVar.b());
        }

        public a e(String str) {
            int length = str.length();
            if (length > this.c) {
                this.c = length;
            }
            return this;
        }

        public int b() {
            return -(this.c + 1);
        }
    }
}

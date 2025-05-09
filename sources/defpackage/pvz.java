package defpackage;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class pvz implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public long f16288a;
    public long d;
    public long g;
    public long i;
    public long m;
    public int o = -1;
    public int b = -1;
    public int j = -1;
    public int f = -1;
    public int h = -1;
    public int e = -1;
    public int c = -1;

    public long a() {
        return ((Long) sdn.c(Long.valueOf(this.m))).longValue();
    }

    public void a(long j) {
        this.m = ((Long) sdn.c(Long.valueOf(j))).longValue();
    }

    public long d() {
        return ((Long) sdn.c(Long.valueOf(this.i))).longValue();
    }

    public void c(long j) {
        this.i = ((Long) sdn.c(Long.valueOf(j))).longValue();
    }

    public void a(int i) {
        this.o = ((Integer) sdn.c(Integer.valueOf(i))).intValue();
    }

    public int e() {
        return ((Integer) sdn.c(Integer.valueOf(this.o))).intValue();
    }

    public void e(int i) {
        this.b = ((Integer) sdn.c(Integer.valueOf(i))).intValue();
    }

    public int c() {
        return ((Integer) sdn.c(Integer.valueOf(this.b))).intValue();
    }

    public void b(int i) {
        this.j = ((Integer) sdn.c(Integer.valueOf(i))).intValue();
    }

    public int b() {
        return ((Integer) sdn.c(Integer.valueOf(this.j))).intValue();
    }

    public void d(long j) {
        this.g = ((Long) sdn.c(Long.valueOf(j))).longValue();
    }

    public void b(long j) {
        this.f16288a = ((Long) sdn.c(Long.valueOf(j))).longValue();
    }

    public void e(long j) {
        this.d = ((Long) sdn.c(Long.valueOf(j))).longValue();
    }

    public String toString() {
        return " FitnessHistogramData mHistogramHeight = " + this.j + "  mStartPoint = " + this.o + "  mEndPoint = " + this.b;
    }
}

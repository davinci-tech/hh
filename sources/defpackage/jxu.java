package defpackage;

import java.util.Date;

/* loaded from: classes5.dex */
public class jxu {

    /* renamed from: a, reason: collision with root package name */
    private int f14177a;
    private int b;
    private long c;
    private int d;
    private int e;

    public long d() {
        return this.c;
    }

    public void e(long j) {
        this.c = (j / 60) * 60;
    }

    public int a() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public int b() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public int c() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int e() {
        return this.f14177a;
    }

    public void e(int i) {
        this.f14177a = i;
    }

    public String toString() {
        return "DataRawSportData{startTime=" + new Date(this.c * 1000) + ", currentStatus=" + this.e + ", totalSteps=" + this.d + ", totalCalorie=" + this.b + ", totalDistance=" + this.f14177a + '}';
    }
}

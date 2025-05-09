package defpackage;

import java.util.Date;

/* loaded from: classes5.dex */
public class jhx {

    /* renamed from: a, reason: collision with root package name */
    private int f13859a;
    private int b;
    private int c;
    private long d;
    private int e;

    public long d() {
        return this.d;
    }

    public void a(long j) {
        this.d = (j / 60) * 60;
    }

    public int a() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int c() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int e() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public int b() {
        return this.f13859a;
    }

    public void d(int i) {
        this.f13859a = i;
    }

    public String toString() {
        return "DataRawSportData{startTime=" + new Date(this.d * 1000) + ", currentStatus=" + this.c + ", totalSteps=" + this.b + ", totalCalorie=" + this.e + ", totalDistance=" + this.f13859a + '}';
    }
}

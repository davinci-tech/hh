package defpackage;

import java.util.Date;

/* loaded from: classes5.dex */
public class jxy {

    /* renamed from: a, reason: collision with root package name */
    private int f14180a;
    private int b;
    private long c;
    private long d;
    private long e;

    public void e(long j) {
        this.c = j;
    }

    public void a(int i) {
        long j = i;
        this.d = j;
        this.e = this.c + j;
    }

    public void e(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.f14180a = i;
    }

    public long d() {
        return this.c;
    }

    public long b() {
        return this.e;
    }

    public int e() {
        return this.b;
    }

    public int c() {
        return this.f14180a;
    }

    public void d(int i) {
        long j = i;
        this.d += j;
        this.e += j;
    }

    public String toString() {
        return "StatusPoint{startTime=" + new Date(this.c * 1000) + "endTime=" + new Date(this.e * 1000) + ", duration=" + this.d + ", type=" + this.b + '}';
    }
}

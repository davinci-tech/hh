package defpackage;

import java.util.Date;

/* loaded from: classes5.dex */
public class jih {

    /* renamed from: a, reason: collision with root package name */
    private long f13865a;
    private long b;
    private long c;
    private int d;
    private int e;

    public void c(long j) {
        this.b = j;
    }

    public void d(int i) {
        long j = i;
        this.f13865a = j;
        this.c = this.b + j;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public long c() {
        return this.b;
    }

    public long a() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public void e(int i) {
        long j = i;
        this.f13865a += j;
        this.c += j;
    }

    public String toString() {
        return "StatusPoint{startTime=" + new Date(this.b * 1000) + "endTime=" + new Date(this.c * 1000) + ", duration=" + this.f13865a + ", type=" + this.d + '}';
    }
}

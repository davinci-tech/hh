package defpackage;

import java.util.Date;

/* loaded from: classes5.dex */
public class jia {

    /* renamed from: a, reason: collision with root package name */
    private long f13860a;
    private int d;
    private int e;

    public long b() {
        return this.f13860a;
    }

    public void b(long j) {
        this.f13860a = (j / 60) * 60;
    }

    public int a() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public int e() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public String toString() {
        return "DataRawSleepData{startTime=" + new Date(this.f13860a * 1000) + ", currentStatus=" + this.e + ", totalCalorie=" + this.d + '}';
    }
}

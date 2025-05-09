package defpackage;

import java.util.Date;

/* loaded from: classes5.dex */
public class jxn {

    /* renamed from: a, reason: collision with root package name */
    private int f14172a;
    private long d;
    private int e;

    public long c() {
        return this.d;
    }

    public void e(long j) {
        this.d = (j / 60) * 60;
    }

    public int d() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public int b() {
        return this.f14172a;
    }

    public void c(int i) {
        this.f14172a = i;
    }

    public String toString() {
        return "DataRawSleepData{startTime=" + new Date(this.d * 1000) + ", currentStatus=" + this.f14172a + ", totalCalorie=" + this.e + '}';
    }
}

package defpackage;

import health.compact.a.UnitUtil;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class fdi implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    protected int f12460a;
    protected long b;
    protected long c;
    protected long d;
    protected long e;
    protected String f = "";
    protected int h;
    protected int i;
    protected long j;

    public boolean q() {
        int i = this.i;
        return i < 180 && i > 0;
    }

    public int i() {
        return this.f12460a;
    }

    public void d(int i) {
        this.f12460a = i;
    }

    public int k() {
        return this.h;
    }

    public void e(int i) {
        this.h = i;
    }

    public int h() {
        return this.i;
    }

    public void b(int i) {
        this.i = i;
    }

    public long f() {
        return this.b;
    }

    public void e(long j) {
        this.b = j;
    }

    public long e() {
        return this.d;
    }

    public int c() {
        long j = this.d;
        if (j > 0) {
            long j2 = this.b;
            if (j2 > 0) {
                return fcj.b(j, fcj.e(j2, this.f, -1));
            }
        }
        return -1;
    }

    public void b(long j) {
        this.d = j;
    }

    public long n() {
        return this.j;
    }

    public int l() {
        long j = this.j;
        if (j > 0) {
            long j2 = this.b;
            if (j2 > 0) {
                return fcj.b(j, fcj.e(j2, this.f, -1));
            }
        }
        return -1;
    }

    public void c(long j) {
        this.j = j;
    }

    public long a() {
        return this.e;
    }

    public int g() {
        long j = this.e;
        if (j > 0) {
            long j2 = this.b;
            if (j2 > 0) {
                return fcj.b(j, fcj.e(j2, this.f, -1));
            }
        }
        return -1;
    }

    public void a(long j) {
        this.e = j;
    }

    public long d() {
        return this.c;
    }

    public int b() {
        long j = this.c;
        if (j > 0) {
            long j2 = this.b;
            if (j2 > 0) {
                return fcj.b(j, fcj.e(j2, this.f, -1));
            }
        }
        return -1;
    }

    public void d(long j) {
        this.c = j;
    }

    public String m() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    public boolean o() {
        return this.i > 0;
    }

    public int j() {
        int i;
        if (this.i < 0 || (i = this.h) <= 0) {
            return -1;
        }
        return (int) UnitUtil.a((r0 / i) * 100.0f, 0);
    }

    public String toString() {
        return "BaseSleepData{mSleepTime=" + this.i + ", mFallAsSleepTime=" + this.d + ", mWakeupTime=" + this.j + ", mRisingTime=" + this.e + ", mGoBedTime=" + this.c + ", mTimeZone='" + this.f + "', mSleepDayTime=" + this.b + ", mStayInBedTime=" + this.h + ", mSleepLatencyTime=" + this.f12460a + ", getFallAsSleepTimeOffset=" + c() + ", getGoBedTimeOffset=" + b() + ", getRisingTimeOffset=" + g() + ", getWakeupTimeOffset=" + l() + ", getSleepEfficiency=" + j() + '}';
    }
}

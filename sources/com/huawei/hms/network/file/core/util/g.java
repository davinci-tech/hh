package com.huawei.hms.network.file.core.util;

/* loaded from: classes4.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private long f5642a;
    private long b;
    private long c = 0;
    private long d = -1;

    public long a() {
        long b;
        long j;
        long j2;
        long j3;
        synchronized (this) {
            b = b();
            if (b < this.d || b < 1418357532000L) {
                FLogger.w("SnowFlakeGen", "time changed ,nextId may conflicted. currTimeStamp:" + b + ", lastTimeStamp:" + this.d + ",START_TIMESTAMP:1418357532000", new Object[0]);
            }
            long j4 = (this.c + 1) & 4095;
            this.c = j4;
            if (j4 == 0 && this.d == b) {
                FLogger.w("SnowFlakeGen", "nextId for nextMill, currTimeStamp :" + b, new Object[0]);
                b = c();
            }
            this.d = b;
            FLogger.v("SnowFlakeGen", "nextId, processId:" + this.f5642a + ",randomId:" + this.b + ",sequence:" + this.c);
            j = this.f5642a;
            j2 = this.b;
            j3 = this.c;
        }
        return ((b - 1418357532000L) << 22) | (j << 17) | (j2 << 12) | j3;
    }

    private long c() {
        long b;
        do {
            b = b();
        } while (b <= this.d);
        return b;
    }

    private long b() {
        return System.currentTimeMillis();
    }

    public g(long j, long j2) {
        this.f5642a = j & 31;
        this.b = j2 & 31;
    }
}

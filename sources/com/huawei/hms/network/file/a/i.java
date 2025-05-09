package com.huawei.hms.network.file.a;

import android.os.SystemClock;
import com.huawei.hms.network.file.core.util.FLogger;

/* loaded from: classes4.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    int f5604a;
    long b;
    long c;
    long d;

    public int a(int i) {
        if (i < 0) {
            return 0;
        }
        if (this.c == 0) {
            this.c = SystemClock.elapsedRealtime();
        }
        long j = i;
        if (j > this.b) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j2 = (int) (((elapsedRealtime - this.c) / 1000.0d) * this.f5604a);
            if (j2 < 0) {
                j2 = this.d;
            }
            if (j2 > 0) {
                long j3 = this.b + j2;
                this.b = j3 < 0 ? this.d : Math.min(j3, this.d);
                this.c = elapsedRealtime;
            }
            long j4 = this.b;
            if (j > j4) {
                long j5 = (j - j4) + 1;
                long j6 = (long) ((j5 * 1000.0d) / this.f5604a);
                if (j6 <= 0) {
                    j6 = 10;
                }
                try {
                    Thread.sleep(j6);
                    this.b += j5;
                    this.c = SystemClock.elapsedRealtime();
                } catch (InterruptedException unused) {
                    FLogger.e("TokenBucketSpeedLimiter", "acquireSync InterruptedException");
                    return 0;
                }
            }
        }
        this.b -= j;
        return i;
    }

    public i(int i) {
        this.f5604a = i;
        this.d = i * 2;
    }
}

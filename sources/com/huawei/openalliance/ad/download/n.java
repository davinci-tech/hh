package com.huawei.openalliance.ad.download;

import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
class n {

    /* renamed from: a, reason: collision with root package name */
    private final a f6813a;
    private final int b;

    void a(int i) {
        this.f6813a.e += i;
        if (this.f6813a.b) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.f6813a.d;
            if (j >= 10) {
                ho.a("DownloadWorker.SpeedAdjuster", "totalReadLengthDuringCheckPoints: %d checkDuration: %d", Long.valueOf(this.f6813a.e), Long.valueOf(j));
                this.f6813a.d = currentTimeMillis;
                long j2 = ((this.f6813a.e * 100000) / j) / 100;
                long abs = Math.abs(j2 - this.f6813a.c);
                ho.a("DownloadWorker.SpeedAdjuster", "current speed: %d target speed: %d diff: %d maxReadDSize: %d", Long.valueOf(j2), Integer.valueOf(this.f6813a.c), Long.valueOf(abs), Integer.valueOf(this.f6813a.f6814a));
                if (abs > 1024) {
                    if (j2 > this.f6813a.c) {
                        if (this.f6813a.f6814a <= 1) {
                            long j3 = (((j * abs) * 100) / j2) / 100;
                            if (j3 > 120000) {
                                j3 = 120000;
                            }
                            ho.a("DownloadWorker.SpeedAdjuster", "sleep time: %d", Long.valueOf(j3));
                            try {
                                Thread.sleep(j3);
                            } catch (InterruptedException e) {
                                ho.a("DownloadWorker.SpeedAdjuster", "adjustSpeedDynamically occur error %s", e.getClass().getSimpleName());
                            }
                        } else {
                            a aVar = this.f6813a;
                            aVar.f6814a -= 30;
                            a aVar2 = this.f6813a;
                            aVar2.f6814a = aVar2.f6814a >= 1 ? this.f6813a.f6814a : 1;
                        }
                    } else {
                        this.f6813a.f6814a += 30;
                        a aVar3 = this.f6813a;
                        int i2 = aVar3.f6814a;
                        int i3 = this.b;
                        if (i2 <= i3) {
                            i3 = this.f6813a.f6814a;
                        }
                        aVar3.f6814a = i3;
                    }
                }
                ho.a("DownloadWorker.SpeedAdjuster", "max read size: %d", Integer.valueOf(this.f6813a.f6814a));
                this.f6813a.e = 0L;
            }
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        int f6814a;
        boolean b;
        int c;
        long d;
        long e;

        a() {
        }
    }

    a a() {
        return this.f6813a;
    }

    n(b bVar, int i) {
        this.b = i;
        a aVar = new a();
        this.f6813a = aVar;
        aVar.b = bVar.f();
        aVar.f6814a = aVar.b ? 100 : i;
        aVar.c = bVar.g();
        aVar.d = System.currentTimeMillis();
        aVar.e = 0L;
    }
}

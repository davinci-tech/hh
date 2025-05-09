package defpackage;

import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bkc {
    private static final long c;
    private static long d;
    private static long e;

    static {
        long j = bky.a() ? 256000L : 64000L;
        c = j;
        e = j;
        d = 0L;
    }

    public static long e() {
        if (bld.d() && !bky.f()) {
            long j = e;
            if (j == 4000 || j == 1200000) {
                e = 1200000L;
            } else {
                e = 4000L;
            }
        } else {
            long j2 = e;
            if (j2 == 0) {
                e = 4000L;
            } else {
                long j3 = c;
                if (j2 < j3) {
                    e = j2 * 2;
                } else {
                    e = j3;
                }
            }
        }
        return e;
    }

    public static void b() {
        if (!bky.e() || !bky.g() || !bld.d() || bky.f()) {
            e = 0L;
        } else if (System.currentTimeMillis() > d) {
            d = System.currentTimeMillis() + 1800000;
            e = 0L;
            LogUtil.c("ScanTimeUtil", "resetScanDelay freezing and iconnect");
        }
    }
}

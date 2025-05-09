package defpackage;

import health.compact.a.UnitUtil;

/* loaded from: classes5.dex */
public class kpj {
    public static double a(int i, boolean z, int i2, double d) {
        if (i == 274) {
            return UnitUtil.h() ? UnitUtil.d(d, 2) / 5.0d : d;
        }
        return d(z, i2, d);
    }

    public static double d(boolean z, int i, double d) {
        if (!UnitUtil.h()) {
            return d;
        }
        if (z && i == 3) {
            return UnitUtil.d(d, 3);
        }
        if (i == 3) {
            return UnitUtil.e(d, 3);
        }
        return -1.0d;
    }
}

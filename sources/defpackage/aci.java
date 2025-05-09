package defpackage;

/* loaded from: classes8.dex */
public class aci {
    public static acf b(acf acfVar, int i) {
        boolean z = i <= 6;
        int i2 = z ? 6 - i : i - 6;
        return new acf(b(acfVar, i2, z), e(acfVar, i2, z), a(acfVar, i2, z), acfVar.a());
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0029, code lost:
    
        r3 = r3 - r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0025, code lost:
    
        if (r5 != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r5 != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0027, code lost:
    
        r3 = r3 + r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static float b(defpackage.acf r3, int r4, boolean r5) {
        /*
            float r0 = r3.e()
            r1 = 1114636288(0x42700000, float:60.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r1 = 1073741824(0x40000000, float:2.0)
            if (r0 < 0) goto L1f
            float r0 = r3.e()
            r2 = 1131413504(0x43700000, float:240.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L1f
            float r3 = r3.e()
            float r4 = (float) r4
            float r4 = r4 * r1
            if (r5 == 0) goto L27
            goto L29
        L1f:
            float r3 = r3.e()
            float r4 = (float) r4
            float r4 = r4 * r1
            if (r5 == 0) goto L29
        L27:
            float r3 = r3 + r4
            goto L2a
        L29:
            float r3 = r3 - r4
        L2a:
            r4 = 0
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            r5 = 1135869952(0x43b40000, float:360.0)
            if (r4 >= 0) goto L33
            float r3 = r3 + r5
            goto L38
        L33:
            int r4 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r4 < 0) goto L38
            float r3 = r3 - r5
        L38:
            int r3 = java.lang.Math.round(r3)
            float r3 = (float) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aci.b(acf, int, boolean):float");
    }

    private static float e(acf acfVar, int i, boolean z) {
        float d;
        float f;
        float f2 = 0.16f;
        if (z) {
            f = acfVar.d() - (i * 0.16f);
        } else {
            if (i == 4) {
                d = acfVar.d();
            } else {
                d = acfVar.d();
                f2 = i * 0.05f;
            }
            f = d + f2;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        if (z && i == 5 && f > 0.1f) {
            f = 0.1f;
        }
        if (f < 0.06f) {
            return 0.06f;
        }
        return f;
    }

    private static float a(acf acfVar, int i, boolean z) {
        float b;
        if (z) {
            b = acfVar.b() + (i * 0.05f);
        } else {
            b = acfVar.b() - (i * 0.15f);
        }
        if (b > 1.0f) {
            return 1.0f;
        }
        return b;
    }
}

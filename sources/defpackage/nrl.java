package defpackage;

/* loaded from: classes6.dex */
public class nrl {
    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int c(float[] r9, float[] r10, java.lang.Boolean r11, java.lang.Boolean r12) {
        /*
            r0 = 3
            float[] r0 = new float[r0]
            boolean r11 = r11.booleanValue()
            r1 = 2
            r2 = 0
            r3 = 1
            if (r11 == 0) goto L80
            boolean r10 = r12.booleanValue()
            if (r10 == 0) goto L28
            r10 = r9[r1]
            r11 = 1064514355(0x3f733333, float:0.95)
            int r12 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r12 < 0) goto L1c
            r10 = r11
        L1c:
            r0[r1] = r10
            r10 = r9[r2]
            r0[r2] = r10
            r9 = r9[r3]
            r0[r3] = r9
            goto Laa
        L28:
            r10 = r9[r2]
            r11 = 1128136704(0x433e0000, float:190.0)
            int r12 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            r4 = 1060320051(0x3f333333, float:0.7)
            if (r12 <= 0) goto L3b
            r12 = 1132888064(0x43868000, float:269.0)
            int r12 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r12 > 0) goto L3b
            goto L47
        L3b:
            r12 = 1116471296(0x428c0000, float:70.0)
            int r12 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r12 < 0) goto L49
            int r11 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r11 > 0) goto L49
            r4 = 1056964608(0x3f000000, float:0.5)
        L47:
            r12 = r4
            goto L5a
        L49:
            r11 = 1099956224(0x41900000, float:18.0)
            int r11 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            r12 = 1058642330(0x3f19999a, float:0.6)
            if (r11 < 0) goto L59
            r11 = 1116340224(0x428a0000, float:69.0)
            int r11 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r11 > 0) goto L59
            goto L5a
        L59:
            r4 = r12
        L5a:
            r11 = r9[r3]
            r9 = r9[r1]
            float r4 = r4 + r12
            float r12 = r11 + r9
            int r5 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r5 >= 0) goto L6c
            r0[r2] = r10
            r0[r3] = r11
            r0[r1] = r9
            goto Laa
        L6c:
            float r4 = r12 - r4
            float r4 = r4 / r12
            r0[r2] = r10
            double r10 = (double) r11
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = (double) r4
            double r5 = r5 - r7
            double r10 = r10 * r5
            float r10 = (float) r10
            r0[r3] = r10
            double r9 = (double) r9
            double r9 = r9 * r5
            float r9 = (float) r9
            r0[r1] = r9
            goto Laa
        L80:
            r11 = r9[r3]
            r4 = r10[r3]
            int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r11 >= 0) goto L8b
            r9 = r10[r2]
            goto L8d
        L8b:
            r9 = r9[r2]
        L8d:
            r0[r2] = r9
            boolean r9 = r12.booleanValue()
            if (r9 == 0) goto La0
            r9 = 1028443341(0x3d4ccccd, float:0.05)
            r0[r3] = r9
            r9 = 1063675494(0x3f666666, float:0.9)
            r0[r1] = r9
            goto Laa
        La0:
            r9 = 1050253722(0x3e99999a, float:0.3)
            r0[r3] = r9
            r9 = 1053609165(0x3ecccccd, float:0.4)
            r0[r1] = r9
        Laa:
            int r9 = android.graphics.Color.HSVToColor(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nrl.c(float[], float[], java.lang.Boolean, java.lang.Boolean):int");
    }

    public static Boolean a(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        if (fArr[2] < 0.8d) {
            return false;
        }
        if (f >= 20.0f && f < 50.0f) {
            return Boolean.valueOf(f2 < 0.6f);
        }
        if (f >= 50.0f && f < 190.0f) {
            return Boolean.valueOf(f2 < 0.4f);
        }
        if (f >= 190.0f && f < 269.0f) {
            return Boolean.valueOf(f2 < 0.4f);
        }
        return Boolean.valueOf(f2 < 0.5f);
    }
}

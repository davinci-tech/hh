package com.huawei.hms.scankit.p;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class g5 implements o6 {
    private s6 b(p pVar, Map<l1, ?> map) throws a {
        int e = pVar.e();
        int c = pVar.c();
        r rVar = new r(e);
        int max = Math.max(1, c >> 5);
        int i = c / 2;
        if (map != null) {
            l1 l1Var = l1.PHOTO_MODE_NUM;
            if (map.containsKey(l1Var)) {
                i += (((Integer) map.get(l1Var)).intValue() * max) / 3;
            }
        }
        int i2 = i;
        int i3 = 0;
        while (i3 < 15) {
            int i4 = i3 + 1;
            int i5 = i4 / 2;
            if ((i3 & 1) != 0) {
                i5 = -i5;
            }
            int i6 = i2 + (i5 * max);
            if (i6 < 0 || i6 >= c) {
                break;
            }
            s6 a2 = a(pVar, rVar, map, i6, e);
            if (a2 != null && a2.k() != null) {
                return a2;
            }
            i3 = i4;
        }
        throw a.a();
    }

    public abstract s6 a(int i, r rVar, Map<l1, ?> map) throws a;

    @Override // com.huawei.hms.scankit.p.o6
    public s6 a(p pVar, Map<l1, ?> map) throws a {
        return b(pVar, map);
    }

    private s6 a(p pVar, r rVar, Map<l1, ?> map, int i, int i2) throws a {
        int i3 = 0;
        while (true) {
            int i4 = 3;
            if (i3 >= 3) {
                return null;
            }
            if (i3 == 0) {
                try {
                    rVar = pVar.a(i, rVar);
                } catch (a unused) {
                    continue;
                }
            } else if (i3 == 1) {
                rVar = pVar.b().a(i, rVar);
                i4 = 1;
            } else if (i3 == 2) {
                if (r3.t) {
                    rVar = pVar.a(i, 1);
                } else {
                    continue;
                    i3++;
                }
            }
            if (a(rVar.d())) {
                s6 a2 = a(rVar, !r3.c ? 1 : i4, map, i, i2);
                if (a2 != null && a2.k() != null) {
                    return a2;
                }
            } else {
                continue;
            }
            i3++;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(2:14|(2:16|(6:18|19|20|(1:24)|25|(2:30|31)(2:27|28))))|37|19|20|(2:22|24)|25|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008f, code lost:
    
        if (r7 == 1) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0091, code lost:
    
        r19.h();
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.scankit.p.s6 a(com.huawei.hms.scankit.p.r r19, int r20, java.util.Map<com.huawei.hms.scankit.p.l1, ?> r21, int r22, int r23) {
        /*
            r18 = this;
            r0 = r23
            r1 = 0
            r3 = r20
            r2 = r21
            r4 = r1
        L8:
            if (r4 >= r3) goto La2
            if (r4 != 0) goto Lf
            r19.c()
        Lf:
            r5 = 1
            if (r4 != r5) goto L15
            r19.i()
        L15:
            r6 = 2
            if (r4 != r6) goto L1e
            r19.g()
            r19.j()
        L1e:
            r7 = r1
        L1f:
            if (r7 >= r6) goto L98
            if (r7 != r5) goto L45
            r19.h()
            if (r2 == 0) goto L45
            com.huawei.hms.scankit.p.l1 r8 = com.huawei.hms.scankit.p.l1.NEED_RESULT_POINT_CALLBACK
            boolean r9 = r2.containsKey(r8)
            if (r9 == 0) goto L45
            java.util.EnumMap r9 = new java.util.EnumMap
            java.lang.Class<com.huawei.hms.scankit.p.l1> r10 = com.huawei.hms.scankit.p.l1.class
            r9.<init>(r10)
            r9.putAll(r2)
            r9.remove(r8)
            r8 = r18
            r10 = r22
            r2 = r9
            r9 = r19
            goto L4b
        L45:
            r8 = r18
            r9 = r19
            r10 = r22
        L4b:
            com.huawei.hms.scankit.p.s6 r11 = r8.a(r10, r9, r2)     // Catch: com.huawei.hms.scankit.p.a -> L8f
            if (r7 != r5) goto L87
            com.huawei.hms.scankit.p.u6[] r12 = r11.j()     // Catch: com.huawei.hms.scankit.p.a -> L8f
            if (r12 == 0) goto L87
            com.huawei.hms.scankit.p.u6 r13 = new com.huawei.hms.scankit.p.u6     // Catch: com.huawei.hms.scankit.p.a -> L8f
            float r14 = (float) r0     // Catch: com.huawei.hms.scankit.p.a -> L8f
            r15 = r12[r1]     // Catch: com.huawei.hms.scankit.p.a -> L8f
            float r15 = r15.b()     // Catch: com.huawei.hms.scankit.p.a -> L8f
            float r15 = r14 - r15
            r16 = 1065353216(0x3f800000, float:1.0)
            float r15 = r15 - r16
            r17 = r12[r1]     // Catch: com.huawei.hms.scankit.p.a -> L8f
            float r6 = r17.c()     // Catch: com.huawei.hms.scankit.p.a -> L8f
            r13.<init>(r15, r6)     // Catch: com.huawei.hms.scankit.p.a -> L8f
            r12[r1] = r13     // Catch: com.huawei.hms.scankit.p.a -> L8f
            com.huawei.hms.scankit.p.u6 r6 = new com.huawei.hms.scankit.p.u6     // Catch: com.huawei.hms.scankit.p.a -> L8f
            r13 = r12[r5]     // Catch: com.huawei.hms.scankit.p.a -> L8f
            float r13 = r13.b()     // Catch: com.huawei.hms.scankit.p.a -> L8f
            float r14 = r14 - r13
            float r14 = r14 - r16
            r13 = r12[r5]     // Catch: com.huawei.hms.scankit.p.a -> L8f
            float r13 = r13.c()     // Catch: com.huawei.hms.scankit.p.a -> L8f
            r6.<init>(r14, r13)     // Catch: com.huawei.hms.scankit.p.a -> L8f
            r12[r5] = r6     // Catch: com.huawei.hms.scankit.p.a -> L8f
        L87:
            boolean r6 = a(r11, r0)     // Catch: com.huawei.hms.scankit.p.a -> L8f
            if (r6 != 0) goto L8e
            goto L94
        L8e:
            return r11
        L8f:
            if (r7 != r5) goto L94
            r19.h()
        L94:
            int r7 = r7 + 1
            r6 = 2
            goto L1f
        L98:
            r8 = r18
            r9 = r19
            r10 = r22
            int r4 = r4 + 1
            goto L8
        La2:
            r8 = r18
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.g5.a(com.huawei.hms.scankit.p.r, int, java.util.Map, int, int):com.huawei.hms.scankit.p.s6");
    }

    private static boolean a(int[] iArr) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length && i < 20; i3++) {
            int i4 = iArr[i3];
            i += Integer.bitCount((i2 | (i4 << 1)) ^ i4);
            i2 = (iArr[i3] >> 31) & 1;
        }
        return i >= 20;
    }

    private static boolean a(s6 s6Var, int i) {
        u6[] j = s6Var.j();
        return Math.abs(((double) j[1].b()) - ((double) j[0].b())) / ((double) i) > 0.4d;
    }

    protected static void a(r rVar, int i, int[] iArr) throws a {
        int length = iArr.length;
        int i2 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int e = rVar.e();
        if (i < e) {
            boolean z = !rVar.b(i);
            while (i < e) {
                if (rVar.b(i) == z) {
                    i2++;
                    if (i2 == length) {
                        break;
                    }
                    if (i2 >= 0 && i2 < iArr.length) {
                        iArr[i2] = 1;
                        z = !z;
                    } else {
                        throw a.a();
                    }
                } else if (i2 >= 0 && i2 < iArr.length) {
                    iArr[i2] = iArr[i2] + 1;
                } else {
                    throw a.a();
                }
                i++;
            }
            if (i2 != length) {
                if (i2 != length - 1 || i != e) {
                    throw a.a();
                }
                return;
            }
            return;
        }
        throw a.a();
    }

    protected static float a(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i;
        float f3 = f2 / i2;
        float f4 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f5 = iArr2[i4] * f3;
            float f6 = iArr[i4];
            float f7 = f6 > f5 ? f6 - f5 : f5 - f6;
            if (f7 > f * f3) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f7;
        }
        return f4 / f2;
    }
}

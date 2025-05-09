package com.huawei.hms.scankit.p;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public final class f2 {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f5776a = {0, 4, 1, 5};
    private static final int[] b = {6, 2, 7, 3};
    private static final int[] c = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] d = {7, 1, 1, 3, 1, 1, 1, 2, 1};
    private static boolean e = false;

    public static o5 a(p pVar, Map<l1, ?> map, boolean z) throws a {
        s b2 = pVar.b();
        a(false);
        List<u6[]> a2 = a(z, b2);
        if (a2.isEmpty()) {
            b2 = b2.clone();
            b2.f();
            a2 = a(z, b2);
            a(true);
        }
        return new o5(b2, a2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001d, code lost:
    
        if (r4 != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
    
        r3 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
    
        if (r3.hasNext() == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002a, code lost:
    
        r4 = (com.huawei.hms.scankit.p.u6[]) r3.next();
        r7 = r4[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
    
        if (r7 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
    
        r2 = (int) java.lang.Math.max(r2, r7.c());
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
    
        r4 = r4[3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
    
        if (r4 == null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0042, code lost:
    
        r2 = java.lang.Math.max(r2, (int) r4.c());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<com.huawei.hms.scankit.p.u6[]> a(boolean r8, com.huawei.hms.scankit.p.s r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = r1
        L7:
            r3 = r1
            r4 = r3
        L9:
            int r5 = r9.c()
            if (r2 >= r5) goto L78
            com.huawei.hms.scankit.p.u6[] r3 = a(r9, r2, r3)
            r5 = r3[r1]
            r6 = 1
            if (r5 != 0) goto L4f
            r5 = 3
            r7 = r3[r5]
            if (r7 != 0) goto L4f
            if (r4 != 0) goto L20
            goto L78
        L20:
            java.util.Iterator r3 = r0.iterator()
        L24:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L4c
            java.lang.Object r4 = r3.next()
            com.huawei.hms.scankit.p.u6[] r4 = (com.huawei.hms.scankit.p.u6[]) r4
            r7 = r4[r6]
            if (r7 == 0) goto L3e
            float r2 = (float) r2
            float r7 = r7.c()
            float r2 = java.lang.Math.max(r2, r7)
            int r2 = (int) r2
        L3e:
            r4 = r4[r5]
            if (r4 == 0) goto L24
            float r4 = r4.c()
            int r4 = (int) r4
            int r2 = java.lang.Math.max(r2, r4)
            goto L24
        L4c:
            int r2 = r2 + 5
            goto L7
        L4f:
            r0.add(r3)
            if (r8 != 0) goto L55
            goto L78
        L55:
            r2 = 2
            r4 = r3[r2]
            if (r4 == 0) goto L66
            float r4 = r4.b()
            int r4 = (int) r4
            r2 = r3[r2]
            float r2 = r2.c()
            goto L74
        L66:
            r2 = 4
            r4 = r3[r2]
            float r4 = r4.b()
            int r4 = (int) r4
            r2 = r3[r2]
            float r2 = r2.c()
        L74:
            r3 = r4
            int r2 = (int) r2
            r4 = r6
            goto L9
        L78:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.f2.a(boolean, com.huawei.hms.scankit.p.s):java.util.List");
    }

    private static u6[] a(s sVar, int i, int i2) {
        int c2 = sVar.c();
        int e2 = sVar.e();
        u6[] u6VarArr = new u6[8];
        a(u6VarArr, a(sVar, c2, e2, i, i2, c), f5776a);
        u6 u6Var = u6VarArr[4];
        if (u6Var != null) {
            i2 = (int) u6Var.b();
            i = (int) u6VarArr[4].c();
        }
        a(u6VarArr, a(sVar, c2, e2, i, i2, d), b);
        return u6VarArr;
    }

    private static void a(u6[] u6VarArr, u6[] u6VarArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            u6VarArr[iArr[i]] = u6VarArr2[i];
        }
    }

    private static u6[] a(s sVar, int i, int i2, int i3, int i4, int[] iArr) {
        boolean z;
        int i5;
        u6[] u6VarArr = new u6[4];
        int[] iArr2 = new int[iArr.length];
        int i6 = i3;
        while (true) {
            if (i6 >= i) {
                z = false;
                break;
            }
            int[] a2 = a(sVar, i4, i6, i2, false, iArr, iArr2);
            if (a2 != null) {
                int i7 = i6;
                int[] iArr3 = a2;
                while (i7 > 0) {
                    int i8 = i7 - 1;
                    int[] a3 = a(sVar, i4, i8, i2, false, iArr, iArr2);
                    if (a3 == null) {
                        break;
                    }
                    iArr3 = a3;
                    i7 = i8;
                }
                float f = i7;
                u6VarArr[0] = new u6(iArr3[0], f);
                u6VarArr[1] = new u6(iArr3[1], f);
                z = true;
                i6 = i7;
            } else {
                i6 += 5;
            }
        }
        int i9 = i6 + 1;
        if (z) {
            int[] iArr4 = {(int) u6VarArr[0].b(), (int) u6VarArr[1].b()};
            int i10 = i9;
            int i11 = 0;
            while (true) {
                if (i10 >= i) {
                    i5 = i11;
                    break;
                }
                int i12 = i11;
                int[] a4 = a(sVar, iArr4[0], i10, i2, false, iArr, iArr2);
                if (a4 == null || Math.abs(iArr4[0] - a4[0]) >= 5 || Math.abs(iArr4[1] - a4[1]) >= 5) {
                    i5 = i12;
                    if (i5 > 25) {
                        break;
                    }
                    i11 = i5 + 1;
                } else {
                    iArr4 = a4;
                    i11 = 0;
                }
                i10++;
            }
            i9 = i10 - (i5 + 1);
            float f2 = i9;
            u6VarArr[2] = new u6(iArr4[0], f2);
            u6VarArr[3] = new u6(iArr4[1], f2);
        }
        if (i9 - i6 < 10) {
            Arrays.fill(u6VarArr, (Object) null);
        }
        return u6VarArr;
    }

    private static int[] a(s sVar, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        for (int i4 = 0; sVar.b(i, i2) && i > 0 && i4 < 3; i4++) {
            i--;
        }
        int length = iArr.length;
        boolean z2 = z;
        int i5 = 0;
        int i6 = i;
        while (i < i3) {
            if (sVar.b(i, i2) != z2) {
                iArr2[i5] = iArr2[i5] + 1;
            } else {
                if (i5 != length - 1) {
                    i5++;
                } else {
                    if (a(iArr2, iArr, 0.8f) < 0.42f) {
                        return new int[]{i6, i};
                    }
                    i6 += iArr2[0] + iArr2[1];
                    int i7 = i5 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i7);
                    iArr2[i7] = 0;
                    iArr2[i5] = 0;
                    i5--;
                }
                iArr2[i5] = 1;
                z2 = !z2;
            }
            i++;
        }
        if (i5 != length - 1 || a(iArr2, iArr, 0.8f) >= 0.42f) {
            return null;
        }
        return new int[]{i6, i - 1};
    }

    private static float a(int[] iArr, int[] iArr2, float f) {
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

    public static void a(boolean z) {
        e = z;
    }

    public static boolean a() {
        return e;
    }
}

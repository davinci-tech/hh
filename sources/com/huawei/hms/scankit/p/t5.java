package com.huawei.hms.scankit.p;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes9.dex */
public final class t5 {

    /* renamed from: a, reason: collision with root package name */
    private static final a3 f5885a = new a3();

    public static w1 a(s sVar, u6 u6Var, u6 u6Var2, u6 u6Var3, u6 u6Var4, int i, int i2, Map<l1, ?> map) throws a {
        a0 a0Var;
        z1 b;
        a0 a0Var2 = new a0(sVar, u6Var, u6Var2, u6Var3, u6Var4);
        b2 b2Var = null;
        b2 b2Var2 = null;
        boolean z = true;
        while (true) {
            a0Var = a0Var2;
            if (u6Var != null) {
                b2Var = a(sVar, a0Var, u6Var, true, i, i2);
            }
            if (u6Var3 != null) {
                b2Var2 = a(sVar, a0Var, u6Var3, false, i, i2);
            }
            b = b(b2Var, b2Var2);
            if (b == null) {
                throw a.a();
            }
            a0Var2 = b.i();
            if (!z || a0Var2 == null || (a0Var2.f() >= a0Var.f() && a0Var2.d() <= a0Var.d())) {
                break;
            }
            z = false;
        }
        b.a(a0Var);
        int f = b.f() + 1;
        b.a(0, b2Var);
        b.a(f, b2Var2);
        a(b, b2Var, a0Var, f, sVar, i, i2);
        return a(b, map);
    }

    private static boolean a(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    private static z1 b(b2 b2Var, b2 b2Var2) throws a {
        k a2;
        if ((b2Var == null && b2Var2 == null) || (a2 = a(b2Var, b2Var2)) == null) {
            return null;
        }
        return new z1(a2, a0.a(a(b2Var), a(b2Var2)));
    }

    private static int c(int i) {
        return 2 << i;
    }

    private static int b(int[] iArr) {
        int i = -1;
        for (int i2 : iArr) {
            i = Math.max(i, i2);
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0027 A[EDGE_INSN: B:17:0x0027->B:18:0x0027 BREAK  A[LOOP:0: B:5:0x000c->B:13:0x000c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int[] b(com.huawei.hms.scankit.p.s r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r10 == 0) goto L9
            r3 = r2
            goto La
        L9:
            r3 = -1
        La:
            r4 = 0
            r5 = r10
        Lc:
            if (r10 == 0) goto L11
            if (r11 >= r9) goto L27
            goto L13
        L11:
            if (r11 < r8) goto L27
        L13:
            if (r4 >= r0) goto L27
            boolean r6 = r7.b(r11, r12)
            if (r6 != r5) goto L22
            r6 = r1[r4]
            int r6 = r6 + r2
            r1[r4] = r6
            int r11 = r11 + r3
            goto Lc
        L22:
            int r4 = r4 + 1
            r5 = r5 ^ 1
            goto Lc
        L27:
            if (r4 == r0) goto L34
            if (r10 == 0) goto L2c
            r8 = r9
        L2c:
            if (r11 != r8) goto L32
            r7 = 7
            if (r4 != r7) goto L32
            goto L34
        L32:
            r7 = 0
            return r7
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.t5.b(com.huawei.hms.scankit.p.s, int, int, boolean, int, int):int[]");
    }

    private static int b(int i) {
        return a(a(i));
    }

    private static void a(z1 z1Var, b2 b2Var, a0 a0Var, int i, s sVar, int i2, int i3) {
        a2 b2Var2;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        a2 a2Var;
        boolean z = b2Var != null;
        int i9 = i2;
        int i10 = i3;
        for (int i11 = 1; i11 <= i; i11++) {
            int i12 = z ? i11 : i - i11;
            if (z1Var.a(i12) == null) {
                if (i12 != 0 && i12 != i) {
                    b2Var2 = new a2(a0Var);
                } else {
                    b2Var2 = new b2(a0Var, i12 == 0);
                }
                z1Var.a(i12, b2Var2);
                int i13 = -1;
                int i14 = i9;
                int i15 = i10;
                int f = a0Var.f();
                int i16 = -1;
                while (f <= a0Var.d()) {
                    int a2 = a(z1Var, i12, f, z);
                    if (a2 >= 0 && a2 <= a0Var.c()) {
                        i4 = a2;
                    } else if (i16 == i13) {
                        i5 = i16;
                        i6 = f;
                        i7 = i14;
                        i8 = i13;
                        a2Var = b2Var2;
                        i14 = i7;
                        i4 = i5;
                        f = i6 + 1;
                        b2Var2 = a2Var;
                        i16 = i4;
                        i13 = i8;
                    } else {
                        i4 = i16;
                    }
                    i5 = i16;
                    int i17 = f;
                    int i18 = i15;
                    int i19 = i14;
                    i8 = i13;
                    a2Var = b2Var2;
                    x0 a3 = a(sVar, a0Var.e(), a0Var.c(), z, i4, i17, i19, i18);
                    i6 = i17;
                    if (a3 != null) {
                        a2Var.a(i6, a3);
                        int min = Math.min(i19, a3.f());
                        i15 = Math.max(i18, a3.f());
                        i14 = min;
                        f = i6 + 1;
                        b2Var2 = a2Var;
                        i16 = i4;
                        i13 = i8;
                    } else {
                        i15 = i18;
                        i7 = i19;
                        i14 = i7;
                        i4 = i5;
                        f = i6 + 1;
                        b2Var2 = a2Var;
                        i16 = i4;
                        i13 = i8;
                    }
                }
                i9 = i14;
                i10 = i15;
            }
        }
    }

    private static a0 a(b2 b2Var) throws a {
        int[] d;
        if (b2Var == null || (d = b2Var.d()) == null) {
            return null;
        }
        int b = b(d);
        int i = 0;
        int i2 = 0;
        for (int i3 : d) {
            i2 += b - i3;
            if (i3 > 0) {
                break;
            }
        }
        x0[] b2 = b2Var.b();
        for (int i4 = 0; i2 > 0 && b2[i4] == null; i4++) {
            i2--;
        }
        for (int length = d.length - 1; length >= 0; length--) {
            int i5 = d[length];
            i += b - i5;
            if (i5 > 0) {
                break;
            }
        }
        for (int length2 = b2.length - 1; i > 0 && b2[length2] == null; length2--) {
            i--;
        }
        return b2Var.a().a(i2, i, b2Var.e());
    }

    private static k a(b2 b2Var, b2 b2Var2) throws a {
        k c;
        k c2;
        if (b2Var == null || (c = b2Var.c()) == null) {
            if (b2Var2 == null) {
                return null;
            }
            return b2Var2.c();
        }
        if (b2Var2 == null || (c2 = b2Var2.c()) == null || c.a() == c2.a() || c.b() == c2.b() || c.c() == c2.c()) {
            return c;
        }
        return null;
    }

    private static b2 a(s sVar, a0 a0Var, u6 u6Var, boolean z, int i, int i2) {
        int b;
        b2 b2Var = new b2(a0Var, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int b2 = (int) u6Var.b();
            for (int c = (int) u6Var.c(); c <= a0Var.d() && c >= a0Var.f(); c += i4) {
                x0 a2 = a(sVar, 0, sVar.e(), z, b2, c, i, i2);
                if (a2 != null) {
                    b2Var.a(c, a2);
                    if (z) {
                        b = a2.d();
                    } else {
                        b = a2.b();
                    }
                    b2 = b;
                }
            }
            i3++;
        }
        return b2Var;
    }

    private static void a(z1 z1Var, m[][] mVarArr) throws a {
        m mVar = mVarArr[0][1];
        int[] a2 = mVar.a();
        int f = (z1Var.f() * z1Var.h()) - c(z1Var.g());
        if (a2.length != 0) {
            if (a2[0] != f) {
                mVar.a(f);
            }
        } else {
            if (f >= 1 && f <= 928) {
                mVar.a(f);
                return;
            }
            throw a.a();
        }
    }

    private static w1 a(z1 z1Var, Map<l1, ?> map) throws a {
        m[][] a2 = a(z1Var);
        a(z1Var, a2);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[z1Var.h() * z1Var.f()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < z1Var.h(); i++) {
            int i2 = 0;
            while (i2 < z1Var.f()) {
                int i3 = i2 + 1;
                int[] a3 = a2[i][i3].a();
                int f = (z1Var.f() * i) + i2;
                if (a3.length == 0) {
                    arrayList.add(Integer.valueOf(f));
                } else if (a3.length == 1) {
                    iArr[f] = a3[0];
                } else {
                    arrayList3.add(Integer.valueOf(f));
                    arrayList2.add(a3);
                }
                i2 = i3;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size][];
        for (int i4 = 0; i4 < size; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return a(z1Var.g(), iArr, n5.a(arrayList), n5.a(arrayList3), iArr2, map);
    }

    private static w1 a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4, Map<l1, ?> map) throws a {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        for (int i2 = 100; i2 > 0; i2--) {
            for (int i3 = 0; i3 < length; i3++) {
                iArr[iArr3[i3]] = iArr4[i3][iArr5[i3]];
            }
            try {
                return a(iArr, i, iArr2, map);
            } catch (a unused) {
                if (length == 0) {
                    throw a.a();
                }
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    int i5 = iArr5[i4];
                    if (i5 < iArr4[i4].length - 1) {
                        iArr5[i4] = i5 + 1;
                        break;
                    }
                    iArr5[i4] = 0;
                    if (i4 == length - 1) {
                        throw a.a();
                    }
                    i4++;
                }
            }
        }
        throw a.a();
    }

    private static m[][] a(z1 z1Var) throws a {
        int c;
        m[][] mVarArr = (m[][]) Array.newInstance((Class<?>) m.class, z1Var.h(), z1Var.f() + 2);
        for (m[] mVarArr2 : mVarArr) {
            int i = 0;
            while (true) {
                if (i < mVarArr2.length) {
                    mVarArr2[i] = new m();
                    i++;
                }
            }
        }
        int i2 = 0;
        for (a2 a2Var : z1Var.j()) {
            if (a2Var != null) {
                for (x0 x0Var : a2Var.b()) {
                    if (x0Var != null && (c = x0Var.c()) >= 0 && c < mVarArr.length) {
                        mVarArr[c][i2].a(x0Var.e());
                    }
                }
            }
            i2++;
        }
        return mVarArr;
    }

    private static boolean a(z1 z1Var, int i) {
        return i >= 0 && i <= z1Var.f() + 1;
    }

    private static int a(z1 z1Var, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        int i4 = i - i3;
        x0 a2 = a(z1Var, i4) ? z1Var.a(i4).a(i2) : null;
        if (a2 != null) {
            return z ? a2.b() : a2.d();
        }
        x0 b = z1Var.a(i).b(i2);
        if (b != null) {
            return z ? b.d() : b.b();
        }
        if (a(z1Var, i4)) {
            b = z1Var.a(i4).b(i2);
        }
        if (b != null) {
            return z ? b.b() : b.d();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (!a(z1Var, i)) {
                return z ? z1Var.i().e() : z1Var.i().c();
            }
            for (x0 x0Var : z1Var.a(i).b()) {
                if (x0Var != null) {
                    return (z ? x0Var.b() : x0Var.d()) + (i3 * i5 * (x0Var.b() - x0Var.d()));
                }
            }
            i5++;
        }
    }

    private static x0 a(s sVar, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int d;
        int a2;
        int a3 = a(sVar, i, i2, z, i3, i4);
        int[] b = b(sVar, i, i2, z, a3, i4);
        if (b == null) {
            return null;
        }
        int a4 = s4.a(b);
        if (z) {
            i7 = a3 + a4;
        } else {
            for (int i8 = 0; i8 < b.length / 2; i8++) {
                int i9 = b[i8];
                b[i8] = b[(b.length - 1) - i8];
                b[(b.length - 1) - i8] = i9;
            }
            a3 -= a4;
            i7 = a3;
        }
        if (a(a4, i5, i6) && (a2 = n5.a((d = m5.d(b)))) != -1) {
            return new x0(a3, i7, b(d), a2);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(com.huawei.hms.scankit.p.s r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L4
            r0 = -1
            goto L5
        L4:
            r0 = 1
        L5:
            r1 = 0
            r2 = r9
        L7:
            r3 = 2
            if (r1 >= r3) goto L28
        La:
            if (r8 == 0) goto Lf
            if (r2 < r6) goto L22
            goto L11
        Lf:
            if (r2 >= r7) goto L22
        L11:
            boolean r4 = r5.b(r2, r10)
            if (r8 != r4) goto L22
            int r4 = r9 - r2
            int r4 = java.lang.Math.abs(r4)
            if (r4 <= r3) goto L20
            return r9
        L20:
            int r2 = r2 + r0
            goto La
        L22:
            int r0 = -r0
            r8 = r8 ^ 1
            int r1 = r1 + 1
            goto L7
        L28:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.t5.a(com.huawei.hms.scankit.p.s, int, int, boolean, int, int):int");
    }

    private static w1 a(int[] iArr, int i, int[] iArr2, Map<l1, ?> map) throws a, a {
        if (iArr.length != 0) {
            int i2 = 1 << (i + 1);
            int a2 = a(iArr, iArr2, i2);
            a(iArr, i2);
            w1 a3 = q1.a(iArr, String.valueOf(i), map);
            a3.b(Integer.valueOf(a2));
            a3.a(Integer.valueOf(iArr2.length));
            return a3;
        }
        throw a.a();
    }

    private static int a(int[] iArr, int[] iArr2, int i) throws a {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return f5885a.a(iArr, i, iArr2);
        }
        throw a.a();
    }

    private static void a(int[] iArr, int i) throws a {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw a.a();
            }
            if (i2 == 0) {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw a.a();
            }
            return;
        }
        throw a.a();
    }

    private static int[] a(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    private static int a(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }
}

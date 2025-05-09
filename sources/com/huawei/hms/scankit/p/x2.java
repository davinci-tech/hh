package com.huawei.hms.scankit.p;

import java.util.Locale;

/* loaded from: classes4.dex */
public final class x2 {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f5917a = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int a(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static f a(byte[] bArr, int i, int i2) {
        r rVar;
        int i3;
        boolean z;
        int i4;
        int i5;
        int i6;
        r a2 = new c4(bArr).a();
        int e = ((a2.e() * i) / 100) + 11;
        int e2 = a2.e();
        int i7 = 0;
        int i8 = 1;
        if (i2 == 0) {
            r rVar2 = null;
            int i9 = 0;
            int i10 = 0;
            while (i9 <= 32) {
                boolean z2 = i9 <= 3 ? i8 : i7;
                int i11 = z2 != 0 ? i9 + 1 : i9;
                int a3 = a(i11, z2);
                if (e2 + e <= a3) {
                    if (rVar2 == null || i10 != f5917a[i11]) {
                        int i12 = f5917a[i11];
                        i10 = i12;
                        rVar2 = a(a2, i12);
                    }
                    if ((z2 == 0 || rVar2.e() <= i10 * 64) && rVar2.e() + e <= a3 - (a3 % i10)) {
                        rVar = rVar2;
                        i3 = i10;
                        z = z2;
                        i4 = i11;
                        i5 = a3;
                    }
                }
                i9++;
                i8 = i8;
                i7 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        z = i2 < 0;
        i4 = Math.abs(i2);
        if (i4 > (z ? 4 : 32)) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Illegal value %s for layers", Integer.valueOf(i2)));
        }
        i5 = a(i4, z);
        i3 = f5917a[i4];
        rVar = a(a2, i3);
        if (rVar.e() + e > i5 - (i5 % i3)) {
            throw new IllegalArgumentException("Data to large for user specified layer");
        }
        if (z && rVar.e() > i3 * 64) {
            throw new IllegalArgumentException("Data to large for user specified layer");
        }
        r b = b(rVar, i5, i3);
        int e3 = rVar.e() / i3;
        r a4 = a(z, i4, e3);
        int i13 = (z ? 11 : 14) + (i4 * 4);
        int[] iArr = new int[i13];
        int i14 = 2;
        if (z) {
            for (int i15 = i7; i15 < i13; i15++) {
                iArr[i15] = i15;
            }
            i6 = i13;
        } else {
            int i16 = i13 / 2;
            i6 = i13 + 1 + (((i16 - 1) / 15) * 2);
            int i17 = i6 / 2;
            for (int i18 = i7; i18 < i16; i18++) {
                iArr[(i16 - i18) - 1] = (i17 - r15) - 1;
                iArr[i16 + i18] = (i18 / 15) + i18 + i17 + i8;
            }
        }
        s sVar = new s(i6);
        int i19 = i7;
        int i20 = i19;
        while (i19 < i4) {
            int i21 = ((i4 - i19) * 4) + (z ? 9 : 12);
            int i22 = i7;
            while (i22 < i21) {
                int i23 = i22 * 2;
                while (i7 < i14) {
                    if (b.b(i20 + i23 + i7)) {
                        int i24 = i19 * 2;
                        sVar.c(iArr[i24 + i7], iArr[i24 + i22]);
                    }
                    if (b.b((i21 * 2) + i20 + i23 + i7)) {
                        int i25 = i19 * 2;
                        sVar.c(iArr[i25 + i22], iArr[((i13 - 1) - i25) - i7]);
                    }
                    if (b.b((i21 * 4) + i20 + i23 + i7)) {
                        int i26 = (i13 - 1) - (i19 * 2);
                        sVar.c(iArr[i26 - i7], iArr[i26 - i22]);
                    }
                    if (b.b((i21 * 6) + i20 + i23 + i7)) {
                        int i27 = i19 * 2;
                        sVar.c(iArr[((i13 - 1) - i27) - i22], iArr[i27 + i7]);
                    }
                    i7++;
                    i14 = 2;
                }
                i22++;
                i7 = 0;
                i14 = 2;
            }
            i20 += i21 * 8;
            i19++;
            i7 = 0;
            i14 = 2;
        }
        a(sVar, z, i6, a4);
        if (z) {
            a(sVar, i6 / 2, 5);
        } else {
            int i28 = i6 / 2;
            a(sVar, i28, 7);
            int i29 = 0;
            int i30 = 0;
            while (i30 < (i13 / 2) - 1) {
                for (int i31 = i28 & 1; i31 < i6; i31 += 2) {
                    int i32 = i28 - i29;
                    sVar.c(i32, i31);
                    int i33 = i28 + i29;
                    sVar.c(i33, i31);
                    sVar.c(i31, i32);
                    sVar.c(i31, i33);
                }
                i30 += 15;
                i29 += 16;
            }
        }
        f fVar = new f();
        fVar.a(z);
        fVar.c(i6);
        fVar.b(i4);
        fVar.a(e3);
        fVar.a(sVar);
        return fVar;
    }

    private static r b(r rVar, int i, int i2) {
        int e = rVar.e() / i2;
        q6 q6Var = new q6(a(i2));
        int i3 = i / i2;
        int[] a2 = a(rVar, i2, i3);
        q6Var.a(a2, i3 - e);
        r rVar2 = new r();
        rVar2.a(0, i % i2);
        for (int i4 : a2) {
            rVar2.a(i4, i2);
        }
        return rVar2;
    }

    private static void a(s sVar, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    sVar.c(i5, i4);
                    sVar.c(i5, i6);
                    sVar.c(i4, i5);
                    sVar.c(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        sVar.c(i7, i7);
        int i8 = i7 + 1;
        sVar.c(i8, i7);
        sVar.c(i7, i8);
        int i9 = i + i2;
        sVar.c(i9, i7);
        sVar.c(i9, i8);
        sVar.c(i9, i9 - 1);
    }

    static r a(boolean z, int i, int i2) {
        r rVar = new r();
        if (z) {
            rVar.a(i - 1, 2);
            rVar.a(i2 - 1, 6);
            return b(rVar, 28, 4);
        }
        rVar.a(i - 1, 5);
        rVar.a(i2 - 1, 11);
        return b(rVar, 40, 4);
    }

    private static void a(s sVar, boolean z, int i, r rVar) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (rVar.b(i3)) {
                    sVar.c(i4, i2 - 5);
                }
                if (rVar.b(i3 + 7)) {
                    sVar.c(i2 + 5, i4);
                }
                if (rVar.b(20 - i3)) {
                    sVar.c(i4, i2 + 5);
                }
                if (rVar.b(27 - i3)) {
                    sVar.c(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (rVar.b(i3)) {
                sVar.c(i5, i2 - 7);
            }
            if (rVar.b(i3 + 10)) {
                sVar.c(i2 + 7, i5);
            }
            if (rVar.b(29 - i3)) {
                sVar.c(i5, i2 + 7);
            }
            if (rVar.b(39 - i3)) {
                sVar.c(i2 - 7, i5);
            }
            i3++;
        }
    }

    private static int[] a(r rVar, int i, int i2) {
        int[] iArr = new int[i2];
        int e = rVar.e() / i;
        for (int i3 = 0; i3 < e; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= rVar.b((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static o3 a(int i) {
        if (i == 4) {
            return o3.k;
        }
        if (i == 6) {
            return o3.j;
        }
        if (i == 8) {
            return o3.n;
        }
        if (i == 10) {
            return o3.i;
        }
        if (i == 12) {
            return o3.h;
        }
        throw new IllegalArgumentException("Unsupported word size " + i);
    }

    static r a(r rVar, int i) {
        r rVar2 = new r();
        int e = rVar.e();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < e) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= e || rVar.b(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                rVar2.a(i7, i);
            } else if (i7 == 0) {
                rVar2.a(i4 | 1, i);
            } else {
                rVar2.a(i4, i);
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return rVar2;
    }
}

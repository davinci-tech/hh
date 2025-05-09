package com.huawei.hms.scankit.p;

import java.lang.reflect.Array;

/* loaded from: classes9.dex */
public final class e4 extends q3 {
    private static int f = 3;
    private static int g = 8;
    private static int h = 7;
    private static int i = 40;
    private static int j = 24;
    private s e;

    public e4(p4 p4Var) {
        super(p4Var);
        a(r3.n);
    }

    private static int a(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    private void a(boolean z) {
        if (z) {
            f = 2;
            g = 4;
            h = 3;
            i = 20;
            return;
        }
        f = 3;
        g = 8;
        h = 7;
        i = 40;
    }

    @Override // com.huawei.hms.scankit.p.q3, com.huawei.hms.scankit.p.o
    public s a() throws a {
        s sVar = this.e;
        if (sVar != null) {
            return sVar;
        }
        p4 c = c();
        int c2 = c.c();
        int a2 = c.a();
        int i2 = i;
        if (c2 >= i2 && a2 >= i2) {
            byte[] b = c.b();
            int i3 = f;
            int i4 = c2 >> i3;
            int i5 = h;
            if ((c2 & i5) != 0) {
                i4++;
            }
            int i6 = a2 >> i3;
            if ((i5 & a2) != 0) {
                i6++;
            }
            int i7 = i6;
            this.e = a(b, i4, i7, c2, a2, a(b, i4, i7, c2, a2));
        } else {
            this.e = super.a();
        }
        return this.e;
    }

    @Override // com.huawei.hms.scankit.p.q3, com.huawei.hms.scankit.p.o
    public o a(p4 p4Var) {
        return new e4(p4Var);
    }

    private static s a(byte[] bArr, int i2, int i3, int i4, int i5, int[][] iArr) {
        int i6;
        int i7;
        int i8;
        int[] iArr2 = new int[i2 * i3];
        for (int i9 = 0; i9 < i3; i9++) {
            int a2 = a(i9, 2, i3 - 3);
            for (int i10 = 0; i10 < i2; i10++) {
                int a3 = a(i10, 2, i2 - 3);
                int[] iArr3 = iArr[a2 + 2];
                int i11 = a3 + 2;
                int i12 = iArr3[i11];
                if (a2 == 2 && a3 == 2) {
                    i8 = 0;
                } else {
                    if (a2 == 2) {
                        i6 = 0;
                        i7 = iArr3[a3 - 3];
                        i8 = 0;
                    } else if (a3 == 2) {
                        i8 = iArr[a2 - 3][i11];
                    } else {
                        int[] iArr4 = iArr[a2 - 3];
                        int i13 = a3 - 3;
                        i6 = iArr4[i13];
                        int i14 = iArr4[i11];
                        i7 = iArr3[i13];
                        i8 = i14;
                    }
                    iArr2[(i9 * i2) + i10] = (((i12 + i6) - i8) - i7) / 25;
                }
                i7 = 0;
                i6 = 0;
                iArr2[(i9 * i2) + i10] = (((i12 + i6) - i8) - i7) / 25;
            }
        }
        return new s(i4, i5, (i4 + 31) / 32, a(bArr, iArr2, i2, i3, i4, i5));
    }

    private static int[] a(byte[] bArr, int[] iArr, int i2, int i3, int i4, int i5) {
        int i6;
        int i7 = (i4 + 31) / 32;
        int i8 = i7 * i5;
        int[] iArr2 = new int[i8];
        for (int i9 = 0; i9 < i8; i9++) {
            iArr2[i9] = 0;
        }
        int i10 = g;
        for (int i11 = 0; i11 < i5; i11++) {
            int i12 = i11 / i10;
            for (int i13 = 0; i13 < i4; i13++) {
                if ((bArr[(i11 * i4) + i13] & 255) <= iArr[(i12 * i2) + (i13 / i10)] && (i6 = (i11 * i7) + (i13 / 32)) < i8) {
                    iArr2[i6] = iArr2[i6] | (1 << (i13 & 31));
                }
            }
        }
        return iArr2;
    }

    private static int[][] a(byte[] bArr, int i2, int i3, int i4, int i5) {
        int i6 = g;
        int i7 = i5 - i6;
        int i8 = i4 - i6;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i3, i2);
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i3, i2);
        int i9 = 0;
        int i10 = 0;
        while (i10 < i3) {
            int i11 = i10 << f;
            if (i11 > i7) {
                i11 = i7;
            }
            int i12 = i9;
            int i13 = i12;
            while (i12 < i2) {
                int i14 = i12 << f;
                if (i14 > i8) {
                    i14 = i8;
                }
                int[] a2 = a(i14, i11, i4, bArr);
                int i15 = a2[i9];
                int i16 = a2[1];
                int i17 = a2[2];
                int i18 = i15 >> (f * 2);
                if (i17 - i16 <= j) {
                    i18 = i16 / 2;
                    if (i10 > 0 && i12 > 0) {
                        int[] iArr3 = iArr2[i10 - 1];
                        int i19 = i12 - 1;
                        int i20 = ((iArr3[i12] + (iArr2[i10][i19] * 2)) + iArr3[i19]) / 4;
                        if (i16 < i20) {
                            i18 = i20;
                        }
                    }
                }
                i13 += i18;
                iArr2[i10][i12] = i18;
                if (i10 == 0 && i12 == 0) {
                    iArr[i10][i12] = i18;
                } else if (i10 == 0) {
                    iArr[i10][i12] = i13;
                } else {
                    iArr[i10][i12] = iArr[i10 - 1][i12] + i13;
                }
                i12++;
                i9 = 0;
            }
            i10++;
            i9 = 0;
        }
        return iArr;
    }

    private static int[] a(int i2, int i3, int i4, byte[] bArr) {
        int i5 = (i3 * i4) + i2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 255;
        while (i6 < g) {
            for (int i10 = 0; i10 < g; i10++) {
                int i11 = bArr[i5 + i10] & 255;
                i7 += i11;
                if (i11 < i9) {
                    i9 = i11;
                }
                if (i11 > i8) {
                    i8 = i11;
                }
            }
            if (i8 - i9 > j) {
                while (true) {
                    i6++;
                    i5 += i4;
                    if (i6 < g) {
                        for (int i12 = 0; i12 < g; i12++) {
                            i7 += bArr[i5 + i12] & 255;
                        }
                    }
                }
            }
            i6++;
            i5 += i4;
        }
        return new int[]{i7, i9, i8};
    }
}

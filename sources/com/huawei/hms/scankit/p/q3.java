package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public class q3 extends o {
    private static final byte[] d = new byte[0];
    private byte[] b;
    private final int[] c;

    public q3(p4 p4Var) {
        super(p4Var);
        this.b = d;
        this.c = new int[32];
    }

    @Override // com.huawei.hms.scankit.p.o
    public r a(int i, r rVar) throws a {
        p4 c = c();
        int c2 = c.c();
        if (rVar == null || rVar.e() < c2) {
            rVar = new r(c2);
        } else {
            rVar.a();
        }
        a(c2);
        byte[] a2 = c.a(i, this.b);
        int[] iArr = this.c;
        for (int i2 = 0; i2 < c2; i2++) {
            int i3 = (a2[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        int a3 = a(iArr, false);
        if (c2 < 3) {
            for (int i4 = 0; i4 < c2; i4++) {
                if ((a2[i4] & 255) < a3) {
                    rVar.g(i4);
                }
            }
        } else {
            int i5 = 1;
            int i6 = a2[0] & 255;
            int i7 = a2[1] & 255;
            while (i5 < c2 - 1) {
                int i8 = i5 + 1;
                int i9 = a2[i8] & 255;
                if ((((i7 * 4) - i6) - i9) / 2 < a3) {
                    rVar.g(i5);
                }
                i6 = i7;
                i5 = i8;
                i7 = i9;
            }
        }
        return rVar;
    }

    @Override // com.huawei.hms.scankit.p.o
    public s a() throws a {
        p4 c = c();
        int c2 = c.c();
        int a2 = c.a();
        a(c2);
        int[] iArr = this.c;
        for (int i = 1; i < 5; i++) {
            byte[] a3 = c.a((a2 * i) / 5, this.b);
            int i2 = (c2 * 4) / 5;
            for (int i3 = c2 / 5; i3 < i2; i3++) {
                int i4 = (a3[i3] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int a4 = a(iArr, true);
        byte[] b = c.b();
        int i5 = (c2 + 31) / 32;
        int[] iArr2 = new int[i5 * a2];
        for (int i6 = 0; i6 < a2; i6++) {
            for (int i7 = 0; i7 < c2; i7++) {
                if ((b[(i6 * c2) + i7] & 255) < a4) {
                    int i8 = (i6 * i5) + (i7 >> 5);
                    iArr2[i8] = iArr2[i8] | (1 << (i7 & 31));
                }
            }
        }
        return new s(c2, a2, i5, iArr2);
    }

    @Override // com.huawei.hms.scankit.p.o
    public o a(p4 p4Var) {
        return new q3(p4Var);
    }

    private void a(int i) {
        if (this.b.length < i) {
            this.b = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.c[i2] = 0;
        }
    }

    private static int a(int[] iArr, boolean z) throws a {
        int length = iArr.length;
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = iArr[i4];
            if (i5 > i) {
                i3 = i4;
                i = i5;
            }
            if (i5 > i2) {
                i2 = i5;
            }
        }
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = i8 - i3;
            int i10 = iArr[i8] * i9 * i9;
            if (i10 > i7) {
                i6 = i8;
                i7 = i10;
            }
        }
        if (i3 <= i6) {
            int i11 = i3;
            i3 = i6;
            i6 = i11;
        }
        int i12 = i3 - i6;
        if (i12 <= length / 16) {
            throw a.a();
        }
        int i13 = i3 - 1;
        int i14 = -1;
        int i15 = i13;
        while (i13 > i6) {
            int i16 = i13 - i6;
            int i17 = i16 * i16 * (i3 - i13) * (i2 - iArr[i13]);
            if (i17 > i14) {
                i15 = i13;
                i14 = i17;
            }
            i13--;
        }
        if (z) {
            if (i15 < 10 && i14 < 100000 && i12 < 10) {
                z2 = true;
            }
            r3.t = z2;
        }
        return i15 << 3;
    }
}

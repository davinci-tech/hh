package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class k5 extends q3 {
    private s e;
    private p4 f;

    public k5(p4 p4Var) {
        super(p4Var);
        this.f = p4Var;
    }

    @Override // com.huawei.hms.scankit.p.q3, com.huawei.hms.scankit.p.o
    public s a() {
        s sVar = this.e;
        if (sVar != null) {
            return sVar;
        }
        p4 c = c();
        int c2 = c.c();
        int a2 = c.a();
        byte[] b = c.b();
        s sVar2 = new s(c2, a2);
        byte[] b2 = this.f.b();
        int c3 = this.f.c();
        int a3 = this.f.a();
        int i = 256;
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < a3; i2++) {
            for (int i3 = 0; i3 < c3; i3++) {
                int i4 = b2[(i2 * c3) + i3] & 255;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int i5 = 0;
        int i6 = 0;
        double d = 0.0d;
        while (i5 < i) {
            int i7 = 0;
            int i8 = 0;
            double d2 = 0.0d;
            while (i7 < i5) {
                i8 += iArr[i7];
                d2 += r12 * i7;
                i7++;
                iArr = iArr;
                i = 256;
            }
            int[] iArr2 = iArr;
            int i9 = 0;
            double d3 = 0.0d;
            int i10 = i5;
            for (int i11 = i; i10 < i11; i11 = 256) {
                i9 += iArr2[i10];
                d3 += r12 * i10;
                i10++;
                i8 = i8;
            }
            int i12 = i8;
            double d4 = i12;
            int i13 = c3;
            int i14 = a3;
            double d5 = c3 * a3;
            double d6 = d4 / d5;
            byte[] bArr = b;
            double d7 = i9;
            double d8 = d7 / d5;
            double d9 = i12 > 0 ? d2 / d4 : 0.0d;
            double d10 = i9 > 0 ? d3 / d7 : 0.0d;
            double d11 = (d6 * d9) + (d8 * d10);
            double d12 = d9 - d11;
            double d13 = d10 - d11;
            double d14 = (d6 * d12 * d12) + (d8 * d13 * d13);
            if (d14 > d) {
                i6 = i5;
                d = d14;
            }
            i5++;
            b = bArr;
            c3 = i13;
            iArr = iArr2;
            a3 = i14;
            i = 256;
        }
        byte[] bArr2 = b;
        for (int i15 = 0; i15 < a2; i15++) {
            for (int i16 = 0; i16 < c2; i16++) {
                if ((bArr2[(i15 * c2) + i16] & 255) <= i6) {
                    sVar2.c(i16, i15);
                }
            }
        }
        this.e = sVar2;
        return sVar2;
    }
}

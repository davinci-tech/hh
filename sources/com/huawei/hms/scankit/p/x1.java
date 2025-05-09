package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class x1 extends s3 {
    @Override // com.huawei.hms.scankit.p.s3
    public s a(s sVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws a {
        return a(sVar, i, i2, d6.a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16), false);
    }

    public s b(s sVar, int i, int i2, d6 d6Var) throws a {
        if (i <= 0 || i2 <= 0) {
            throw a.a();
        }
        s sVar2 = new s(i, i2);
        int i3 = i * 2;
        float[] fArr = new float[i3];
        for (int i4 = 0; i4 < i2; i4++) {
            float f = i4;
            for (int i5 = 0; i5 < i3; i5 += 2) {
                fArr[i5] = (i5 / 2) + 0.5f;
                fArr[i5 + 1] = 0.5f + f;
            }
            if (r3.p && r3.m) {
                d6Var.b(fArr);
            } else {
                d6Var.a(fArr);
            }
            int e = sVar.e();
            int c = sVar.c();
            for (int i6 = 0; i6 < i3; i6 += 2) {
                try {
                    int i7 = (int) fArr[i6];
                    int i8 = (int) fArr[i6 + 1];
                    if (i7 >= -1 && i7 <= e && i8 >= -1 && i8 <= c) {
                        if (sVar.b(i7, i8)) {
                            sVar2.c(i6 / 2, i4);
                        }
                    }
                    sVar2.c(i6 / 2, i4);
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw a.a();
                }
            }
        }
        return sVar2;
    }

    @Override // com.huawei.hms.scankit.p.s3
    public s a(s sVar, int i, int i2, d6 d6Var, boolean z) throws a {
        boolean z2 = r3.n;
        if ((z2 && z) || ((!z2 && !z) || r3.u)) {
            return b(sVar, i, i2, d6Var);
        }
        return a(sVar, i, i2, d6Var);
    }

    public s a(s sVar, int i, int i2, d6 d6Var) throws a {
        if (i > 0 && i2 > 0) {
            s sVar2 = new s(i, i2);
            float[] fArr = new float[10];
            int i3 = 0;
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i3;
                while (i5 < i) {
                    float f = i5;
                    fArr[i3] = (f - 0.2f) + 0.5f;
                    float f2 = i4;
                    float f3 = f2 + 0.5f;
                    fArr[1] = f3;
                    fArr[2] = f + 0.2f + 0.5f;
                    fArr[3] = f3;
                    float f4 = f + 0.5f;
                    fArr[4] = f4;
                    fArr[5] = (f2 - 0.2f) + 0.5f;
                    fArr[6] = f4;
                    fArr[7] = f2 + 0.2f + 0.5f;
                    fArr[8] = f4;
                    fArr[9] = f3;
                    if (r3.p && r3.m) {
                        d6Var.b(fArr);
                    } else {
                        d6Var.a(fArr);
                    }
                    int e = sVar.e();
                    int c = sVar.c();
                    int i6 = i3;
                    int i7 = i6;
                    while (i6 < 5) {
                        int i8 = i6 * 2;
                        try {
                            int i9 = (int) fArr[i8];
                            int i10 = (int) fArr[i8 + 1];
                            if (i9 >= -1 && i9 <= e && i10 >= -1 && i10 <= c) {
                                if (sVar.b(i9, i10)) {
                                    i7++;
                                }
                                i6++;
                            }
                            i6++;
                        } catch (ArrayIndexOutOfBoundsException unused) {
                            throw a.a();
                        }
                    }
                    if (i7 >= 3) {
                        sVar2.c(i5, i4);
                    }
                    i5++;
                    i3 = 0;
                }
                i4++;
                i3 = 0;
            }
            return sVar2;
        }
        throw a.a();
    }
}

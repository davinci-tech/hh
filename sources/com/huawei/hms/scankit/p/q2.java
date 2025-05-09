package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;

/* loaded from: classes9.dex */
public final class q2 extends q7 {
    private final int[] h = new int[4];

    private int b(r rVar, int[] iArr, int i, int[][] iArr2) throws a {
        g5.a(rVar, i, iArr);
        int length = iArr2.length;
        float f = 0.43f;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float a2 = g5.a(iArr, iArr2[i3], 0.7f);
            if (a2 < f) {
                i2 = i3;
                f = a2;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw a.a();
    }

    @Override // com.huawei.hms.scankit.p.q7
    public boolean a(int i, int i2, r rVar) {
        return rVar.a(i2, ((int) ((i2 - i) * 1.5d)) + i2, false, true);
    }

    @Override // com.huawei.hms.scankit.p.q7
    protected int a(r rVar, int[] iArr, StringBuilder sb) throws a {
        int[] iArr2 = this.h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int e = rVar.e();
        int i = iArr[1];
        for (int i2 = 0; i2 < 4 && i < e; i2++) {
            sb.append((char) (b(rVar, iArr2, i, q7.f) + 48));
            for (int i3 : iArr2) {
                i += i3;
            }
        }
        int i4 = q7.a(rVar, i, true, q7.d)[1];
        for (int i5 = 0; i5 < 4 && i4 < e; i5++) {
            sb.append((char) (b(rVar, iArr2, i4, q7.f) + 48));
            for (int i6 : iArr2) {
                i4 += i6;
            }
        }
        return i4;
    }

    @Override // com.huawei.hms.scankit.p.q7
    BarcodeFormat a() {
        return BarcodeFormat.EAN_8;
    }

    @Override // com.huawei.hms.scankit.p.q7
    boolean a(int[] iArr, int[] iArr2) throws a {
        int i = iArr[1];
        int i2 = iArr[0];
        int i3 = i - i2;
        double d = i3 / 3.0d;
        int i4 = iArr2[1];
        int i5 = i4 - iArr2[0];
        return ((double) Math.abs(((int) Math.round(((double) (i4 - i2)) / (((double) (i5 + i3)) / 6.0d))) + (-67))) <= 6.7d && Math.abs(1.0d - (d / (((double) i5) / 3.0d))) < 0.2d;
    }
}

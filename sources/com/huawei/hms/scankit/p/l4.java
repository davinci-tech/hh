package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.util.LoadOpencvJNIUtil;

/* loaded from: classes9.dex */
public class l4 {
    public static int a(int i, int i2, int i3) {
        return i >= i2 ? i2 : i <= i3 ? i3 : i;
    }

    public static p a(p pVar) {
        int e = pVar.e();
        int c = pVar.c();
        byte[] d = pVar.d();
        byte[] bArr = new byte[e * c];
        for (int i = 0; i < c; i++) {
            for (int i2 = 0; i2 < e; i2++) {
                bArr[(((i2 * c) + c) - i) - 1] = d[(i * e) + i2];
            }
        }
        return new p(new e4(new e6(bArr, c, e, 0, 0, c, e, false)));
    }

    public static p a(p pVar, float f) {
        if (f == 1.0f) {
            return pVar;
        }
        int c = pVar.c();
        int e = pVar.e();
        int i = (int) (e / f);
        int i2 = (int) (c / f);
        byte[] d = pVar.d();
        int i3 = i * i2;
        byte[] bArr = new byte[i3];
        int i4 = 0;
        int i5 = 0;
        while (i5 < i3) {
            double a2 = a(i5 % i, i - 1, i4) * f;
            double a3 = a(i5 / i, i2 - 1, i4) * f;
            double floor = Math.floor(a2);
            int i6 = i5;
            double floor2 = Math.floor(a3);
            double d2 = a2 - floor;
            double d3 = a3 - floor2;
            int i7 = i3;
            int a4 = a((int) floor, e - 1, 0);
            int a5 = a((int) floor2, c - 1, 0);
            int i8 = c;
            int i9 = i;
            double d4 = d[(a5 * e) + a4] & 255;
            double d5 = 1.0d - d2;
            double d6 = 1.0d - d3;
            int i10 = a4 + 1;
            byte[] bArr2 = bArr;
            int i11 = a5 + 1;
            bArr2[i6] = (byte) (((int) ((d4 * d5 * d6) + ((d[r3 + a(i10, r14, 0)] & 255) * d2 * d6) + ((d[(a(i11, r6, 0) * e) + a4] & 255) * d5 * d3) + ((d[(a(i11, r6, 0) * e) + a(i10, r14, 0)] & 255) * d2 * d3))) & 255);
            i5 = i6 + 1;
            i4 = 0;
            i3 = i7;
            i = i9;
            c = i8;
            i2 = i2;
            bArr = bArr2;
        }
        return new p(new e4(new e6(bArr, i, i2, 0, 0, i, i2, false)));
    }

    public static p a(boolean z, p pVar, float f) {
        if (f == 1.0f) {
            return pVar;
        }
        int c = pVar.c();
        int e = pVar.e();
        int i = (int) (e / f);
        int i2 = (int) (c / f);
        return new p(new e4(new e6(LoadOpencvJNIUtil.imageResize(pVar.d(), c, e, i2, i), i, i2, 0, 0, i, i2, false)));
    }
}

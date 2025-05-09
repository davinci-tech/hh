package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;

/* loaded from: classes9.dex */
final class o7 {
    private static final int[] c = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};

    /* renamed from: a, reason: collision with root package name */
    private final int[] f5848a = new int[4];
    private final StringBuilder b = new StringBuilder();

    o7() {
    }

    s6 a(int i, r rVar, int[] iArr) throws a {
        StringBuilder sb = this.b;
        sb.setLength(0);
        float f = i;
        return new s6(sb.toString(), null, new u6[]{new u6((iArr[0] + iArr[1]) / 2.0f, f), new u6(a(rVar, iArr, sb), f)}, BarcodeFormat.UPC_EAN_EXTENSION);
    }

    private int a(r rVar, int[] iArr, StringBuilder sb) throws a {
        int[] iArr2 = this.f5848a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int e = rVar.e();
        int i = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 5 && i < e; i3++) {
            int a2 = q7.a(rVar, iArr2, i, q7.g);
            sb.append((char) ((a2 % 10) + 48));
            for (int i4 : iArr2) {
                i += i4;
            }
            if (a2 >= 10) {
                i2 |= 1 << (4 - i3);
            }
            if (i3 != 4) {
                i = rVar.d(rVar.c(i));
            }
        }
        if (sb.length() == 5) {
            if (a(sb.toString()) == a(i2)) {
                return i;
            }
            throw a.a();
        }
        throw a.a();
    }

    private static int a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            i += charSequence.charAt(i2) - '0';
        }
        int i3 = i * 3;
        for (int i4 = length - 1; i4 >= 0; i4 -= 2) {
            i3 += charSequence.charAt(i4) - '0';
        }
        return (i3 * 3) % 10;
    }

    private static int a(int i) throws a {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == c[i2]) {
                return i2;
            }
        }
        throw a.a();
    }
}

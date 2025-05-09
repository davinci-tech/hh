package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;

/* loaded from: classes4.dex */
public final class o2 extends q7 {
    public static final int[] j = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private String i = "";
    private final int[] h = new int[4];

    @Override // com.huawei.hms.scankit.p.q7
    boolean a(int i, int i2, r rVar) {
        return rVar.a(i2, (i2 - i) + i2, false, false);
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
        int i2 = 0;
        for (int i3 = 0; i3 < 6 && i < e; i3++) {
            int a2 = q7.a(rVar, iArr2, i, q7.g);
            sb.append((char) ((a2 % 10) + 48));
            for (int i4 : iArr2) {
                i += i4;
            }
            if (a2 >= 10) {
                i2 |= 1 << (5 - i3);
            }
        }
        a(sb, i2);
        this.i = sb.substring(0, 1);
        int i5 = q7.a(rVar, i, true, q7.d)[1];
        for (int i6 = 0; i6 < 6 && i5 < e; i6++) {
            sb.append((char) (q7.a(rVar, iArr2, i5, q7.f) + 48));
            for (int i7 : iArr2) {
                i5 += i7;
            }
        }
        if (a(sb)) {
            return i5;
        }
        throw a.a();
    }

    @Override // com.huawei.hms.scankit.p.q7
    BarcodeFormat a() {
        return BarcodeFormat.EAN_13;
    }

    private static void a(StringBuilder sb, int i) throws a {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == j[i2]) {
                sb.insert(0, (char) (i2 + 48));
                return;
            }
        }
        throw a.a();
    }

    private static boolean a(StringBuilder sb) {
        char charAt = sb.charAt(sb.length() - 1);
        int i = 0;
        for (int i2 = 0; i2 < sb.length() - 1; i2++) {
            i += i2 % 2 == 0 ? sb.charAt(i2) - '0' : (sb.charAt(i2) - '0') * 3;
        }
        return (i + (charAt + 65488)) % 10 == 0;
    }

    @Override // com.huawei.hms.scankit.p.q7
    boolean a(int[] iArr, int[] iArr2) throws a {
        int i = iArr2[1];
        int i2 = iArr2[0];
        int i3 = iArr[1];
        int i4 = iArr[0];
        int round = (int) Math.round((i - i4) / (((i - i2) + (i3 - i4)) / 6.0d));
        return this.i.equals("0") ? ((double) Math.abs(round + (-95))) <= 18.05d || ((double) Math.abs(round + (-113))) <= 21.47d : ((double) Math.abs(round + (-95))) <= 18.05d;
    }
}

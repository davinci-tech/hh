package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes4.dex */
public class w0 extends h5 {
    @Override // com.huawei.hms.scankit.p.h5, com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_93) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_93, but got " + barcodeFormat);
    }

    @Override // com.huawei.hms.scankit.p.h5
    public boolean[] a(String str) {
        int length = str.length();
        if (length <= 80) {
            int[] iArr = new int[9];
            int length2 = str.length();
            a(v0.d[47], iArr);
            boolean[] zArr = new boolean[((length2 + 4) * 9) + 1];
            int a2 = a(zArr, 0, iArr);
            for (int i = 0; i < length; i++) {
                int indexOf = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(i));
                if (indexOf >= 0) {
                    a(v0.d[indexOf], iArr);
                    a2 += a(zArr, a2, iArr);
                } else {
                    throw new IllegalArgumentException("Bad contents: please check contents");
                }
            }
            int a3 = a(str, 20);
            int[] iArr2 = v0.d;
            a(iArr2[a3], iArr);
            int a4 = a2 + a(zArr, a2, iArr);
            a(iArr2[a(str + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(a3), 15)], iArr);
            int a5 = a4 + a(zArr, a4, iArr);
            a(iArr2[47], iArr);
            zArr[a5 + a(zArr, a5, iArr)] = true;
            return zArr;
        }
        throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
    }

    private static void a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) == 0) {
                i3 = 0;
            }
            iArr[i2] = i3;
        }
    }

    private static int a(boolean[] zArr, int i, int[] iArr) {
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            zArr[i] = iArr[i2] != 0;
            i2++;
            i++;
        }
        return 9;
    }

    private static int a(String str, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            i2 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * i3;
            i3++;
            if (i3 > i) {
                i3 = 1;
            }
        }
        return i2 % 47;
    }
}

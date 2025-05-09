package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes4.dex */
public final class p2 extends r7 {
    @Override // com.huawei.hms.scankit.p.h5, com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + barcodeFormat);
    }

    @Override // com.huawei.hms.scankit.p.h5
    public boolean[] a(String str) {
        int length = str.length();
        if (length == 12) {
            try {
                str = str + q7.b(str);
            } catch (a e) {
                throw new IllegalArgumentException(e);
            }
        } else if (length == 13) {
            try {
                if (!q7.a((CharSequence) str)) {
                    throw new IllegalArgumentException("Contents do not pass checksum");
                }
            } catch (a unused) {
                throw new IllegalArgumentException("Illegal contents");
            }
        } else {
            throw new IllegalArgumentException("Requested contents should be 12 or 13 digits long, but got " + length);
        }
        int i = o2.j[Character.digit(str.charAt(0), 10)];
        boolean[] zArr = new boolean[95];
        int a2 = h5.a(zArr, 0, q7.c, true);
        for (int i2 = 1; i2 <= 6; i2++) {
            int digit = Character.digit(str.charAt(i2), 10);
            if (((i >> (6 - i2)) & 1) == 1) {
                digit += 10;
            }
            a2 += h5.a(zArr, a2, q7.g[digit], false);
        }
        int a3 = a2 + h5.a(zArr, a2, q7.d, false);
        for (int i3 = 7; i3 <= 12; i3++) {
            a3 += h5.a(zArr, a3, q7.f[Character.digit(str.charAt(i3), 10)], true);
        }
        h5.a(zArr, a3, q7.c, true);
        return zArr;
    }
}

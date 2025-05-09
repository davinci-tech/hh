package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes4.dex */
public final class r2 extends r7 {
    @Override // com.huawei.hms.scankit.p.h5, com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_8, but got " + barcodeFormat);
    }

    @Override // com.huawei.hms.scankit.p.h5
    public boolean[] a(String str) {
        int length = str.length();
        if (length == 7) {
            try {
                str = str + q7.b(str);
            } catch (a e) {
                throw new IllegalArgumentException(e);
            }
        } else if (length == 8) {
            try {
                if (!q7.a((CharSequence) str)) {
                    throw new IllegalArgumentException("Contents do not pass checksum");
                }
            } catch (a unused) {
                throw new IllegalArgumentException("Illegal contents");
            }
        } else {
            throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + length);
        }
        boolean[] zArr = new boolean[67];
        int a2 = h5.a(zArr, 0, q7.c, true);
        for (int i = 0; i <= 3; i++) {
            a2 += h5.a(zArr, a2, q7.f[Character.digit(str.charAt(i), 10)], false);
        }
        int a3 = a2 + h5.a(zArr, a2, q7.d, false);
        for (int i2 = 4; i2 <= 7; i2++) {
            a3 += h5.a(zArr, a3, q7.f[Character.digit(str.charAt(i2), 10)], true);
        }
        h5.a(zArr, a3, q7.c, true);
        return zArr;
    }
}

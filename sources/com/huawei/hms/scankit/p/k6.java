package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes4.dex */
public final class k6 implements l8 {
    @Override // com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        }
        b3 b3Var = b3.L;
        Boolean bool = Boolean.FALSE;
        if (map != null) {
            u2 u2Var = u2.ERROR_CORRECTION;
            if (map.containsKey(u2Var)) {
                b3Var = b3.valueOf(map.get(u2Var).toString());
            }
            u2 u2Var2 = u2.MARGIN;
            r1 = map.containsKey(u2Var2) ? Integer.parseInt(map.get(u2Var2).toString()) : 4;
            u2 u2Var3 = u2.LOGO;
            if (map.containsKey(u2Var3)) {
                bool = (Boolean) map.get(u2Var3);
            }
        }
        return a(w2.a(str, b3Var, map), i, i2, r1, bool.booleanValue());
    }

    private static s a(h6 h6Var, int i, int i2, int i3, boolean z) {
        int max;
        int max2;
        int min;
        c0 a2 = h6Var.a();
        if (a2 != null) {
            int c = a2.c();
            int b = a2.b();
            if (z) {
                max = Math.max(i, c);
                max2 = Math.max(i2, b);
                int i4 = i3 * 2;
                min = Math.min((max - i4) / c, (max2 - i4) / b);
            } else {
                int i5 = i3 * 2;
                int i6 = c + i5;
                int i7 = i5 + b;
                max = Math.max(i, i6);
                max2 = Math.max(i2, i7);
                min = Math.min(max / i6, max2 / i7);
            }
            int i8 = (max - (c * min)) / 2;
            int i9 = (max2 - (b * min)) / 2;
            s sVar = new s(max, max2);
            int i10 = 0;
            while (i10 < b) {
                int i11 = 0;
                int i12 = i8;
                while (i11 < c) {
                    if (a2.a(i11, i10) == 1) {
                        sVar.a(i12, i9, min, min);
                    }
                    i11++;
                    i12 += min;
                }
                i10++;
                i9 += min;
            }
            return sVar;
        }
        throw new IllegalStateException();
    }
}

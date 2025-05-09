package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/* loaded from: classes4.dex */
public final class i implements l8 {
    @Override // com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) {
        Charset charset = StandardCharsets.ISO_8859_1;
        int i3 = 4;
        if (map != null) {
            u2 u2Var = u2.CHARACTER_SET;
            if (map.containsKey(u2Var)) {
                charset = Charset.forName(map.get(u2Var).toString());
            }
            u2 u2Var2 = u2.ERROR_CORRECTION;
            r2 = map.containsKey(u2Var2) ? Integer.parseInt(map.get(u2Var2).toString()) : 33;
            u2 u2Var3 = u2.AZTEC_LAYERS;
            r3 = map.containsKey(u2Var3) ? Integer.parseInt(map.get(u2Var3).toString()) : 0;
            u2 u2Var4 = u2.MARGIN;
            if (map.containsKey(u2Var4)) {
                i3 = Integer.parseInt(map.get(u2Var4).toString());
            }
        }
        return a(str, barcodeFormat, i, i2, charset, r2, r3, i3);
    }

    private static s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Charset charset, int i3, int i4, int i5) {
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return a(x2.a(str.getBytes(charset), i3, i4), i, i2, i5);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got " + barcodeFormat);
    }

    private static s a(f fVar, int i, int i2, int i3) {
        s a2 = fVar.a();
        if (a2 != null) {
            int e = a2.e();
            int c = a2.c();
            int i4 = i3 * 2;
            int i5 = e + i4;
            int i6 = i4 + c;
            int max = Math.max(i, i5);
            int max2 = Math.max(i2, i6);
            int min = Math.min(max / i5, max2 / i6);
            int i7 = (max - (e * min)) / 2;
            int i8 = (max2 - (c * min)) / 2;
            s sVar = new s(max, max2);
            int i9 = 0;
            while (i9 < c) {
                int i10 = 0;
                int i11 = i7;
                while (i10 < e) {
                    if (a2.b(i10, i9)) {
                        sVar.a(i11, i8, min, min);
                    }
                    i10++;
                    i11 += min;
                }
                i9++;
                i8 += min;
            }
            return sVar;
        }
        throw new IllegalStateException();
    }
}

package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes4.dex */
public final class u5 implements l8 {
    @Override // com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got " + barcodeFormat);
        }
        l5 l5Var = new l5();
        if (map != null) {
            u2 u2Var = u2.PDF417_COMPACT;
            if (map.containsKey(u2Var)) {
                l5Var.a(Boolean.valueOf(map.get(u2Var).toString()).booleanValue());
            }
            u2 u2Var2 = u2.PDF417_COMPACTION;
            if (map.containsKey(u2Var2)) {
                l5Var.a(y0.valueOf(map.get(u2Var2).toString()));
            }
            u2 u2Var3 = u2.PDF417_DIMENSIONS;
            if (map.containsKey(u2Var3)) {
                m2 m2Var = (m2) map.get(u2Var3);
                l5Var.b(m2Var.a(), m2Var.c(), m2Var.b(), m2Var.d());
            }
            u2 u2Var4 = u2.MARGIN;
            r9 = map.containsKey(u2Var4) ? Integer.parseInt(map.get(u2Var4).toString()) : 30;
            u2 u2Var5 = u2.ERROR_CORRECTION;
            r0 = map.containsKey(u2Var5) ? Integer.parseInt(map.get(u2Var5).toString()) : 2;
            u2 u2Var6 = u2.CHARACTER_SET;
            if (map.containsKey(u2Var6)) {
                l5Var.a(Charset.forName(map.get(u2Var6).toString()));
            }
        }
        return a(l5Var, str, r0, i, i2, r9);
    }

    private static s a(l5 l5Var, String str, int i, int i2, int i3, int i4) throws WriterException {
        l5Var.a(str, i);
        byte[][] a2 = l5Var.a().a(1, 4);
        int length = i2 / a2[0].length;
        int length2 = i3 / a2.length;
        if (length >= length2) {
            length = length2;
        }
        if (length > 1) {
            return a(l5Var.a().a(length, length * 4), i4);
        }
        return a(a2, i4);
    }

    private static s a(byte[][] bArr, int i) {
        int i2 = i * 2;
        s sVar = new s(bArr[0].length + i2, bArr.length + i2);
        sVar.a();
        int c = (sVar.c() - i) - 1;
        int i3 = 0;
        while (i3 < bArr.length) {
            byte[] bArr2 = bArr[i3];
            for (int i4 = 0; i4 < bArr[0].length; i4++) {
                if (bArr2[i4] == 1) {
                    sVar.c(i4 + i, c);
                }
            }
            i3++;
            c--;
        }
        return sVar;
    }
}

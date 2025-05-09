package com.huawei.watchface;

import java.util.Locale;

/* loaded from: classes7.dex */
public final class al {
    public static String a(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        if (i <= 0 || i > bArr.length) {
            i = bArr.length;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            stringBuffer.append(hexString.toUpperCase(Locale.ENGLISH));
        }
        return stringBuffer.toString();
    }

    public static String a(byte[] bArr) {
        return a(bArr, 0);
    }
}

package com.huawei.haf.common.utils;

import java.util.Locale;

/* loaded from: classes.dex */
public final class HexUtils {
    private HexUtils() {
    }

    public static String d(byte[] bArr) {
        return c(bArr, true);
    }

    public static String c(byte[] bArr, boolean z) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length + bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        String sb2 = sb.toString();
        return z ? sb2.toUpperCase(Locale.ENGLISH) : sb2;
    }
}

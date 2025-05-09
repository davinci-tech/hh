package com.amap.api.col.p0003sl;

import java.text.SimpleDateFormat;

/* loaded from: classes2.dex */
public final class mx {

    /* renamed from: a, reason: collision with root package name */
    private static SimpleDateFormat f1344a;
    private static String b;

    public static byte[] a(long j) {
        byte[] bArr = new byte[6];
        for (int i = 0; i < 6; i++) {
            bArr[i] = (byte) ((j >> ((5 - i) * 8)) & 255);
        }
        return bArr;
    }

    public static String a(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() < 2) {
                sb.append("0");
            }
            sb.append(hexString);
            if (str.length() > 0 && i < bArr.length - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}

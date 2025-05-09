package com.huawei.secure.android.common.detect.b;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8579a = "";
    private static final String b = "HexUtil";

    private a() {
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        try {
            byte[] bytes = upperCase.getBytes("UTF-8");
            for (int i = 0; i < length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("0x");
                int i2 = i * 2;
                sb.append(new String(new byte[]{bytes[i2]}, "UTF-8"));
                bArr[i] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i2 + 1]}, "UTF-8")).byteValue());
            }
        } catch (UnsupportedEncodingException | NumberFormatException e) {
            c.b(b, "hex string 2 byte array exception : " + e.getMessage());
        }
        return bArr;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return a(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            c.b(b, "byte array 2 hex string exception : " + e.getMessage());
            return "";
        }
    }
}

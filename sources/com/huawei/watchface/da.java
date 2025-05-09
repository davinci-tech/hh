package com.huawei.watchface;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.utils.HwLog;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: classes7.dex */
public class da {
    public static int a(byte b) {
        if ((b & 128) <= 0) {
            return b;
        }
        int i = b - 128;
        return i < 0 ? b + 128 : i;
    }

    public static String a(int i) {
        if (i < 0) {
            String hexString = Integer.toHexString(i);
            return SafeString.substring(hexString, hexString.length() - 2, hexString.length());
        }
        if (16 <= i) {
            String hexString2 = Integer.toHexString(i);
            if (hexString2.length() % 2 == 0) {
                return hexString2;
            }
            return "0" + hexString2;
        }
        return "0" + Integer.toHexString(i);
    }

    public static String a(long j) {
        return ((((((a((int) ((j >> 56) & 255)) + a((int) ((j >> 48) & 255))) + a((int) ((j >> 40) & 255))) + a((int) ((j >> 32) & 255))) + a((int) ((j >> 24) & 255))) + a((int) ((j >> 16) & 255))) + a((int) ((j >> 8) & 255))) + a((int) (j & 255));
    }

    public static String b(long j) {
        return ((a((int) ((j >> 24) & 255)) + a((int) ((j >> 16) & 255))) + a((int) ((j >> 8) & 255))) + a((int) (j & 255));
    }

    public static byte[] a(String str) {
        if (str == null) {
            return new byte[0];
        }
        String replace = SafeString.replace(str, " ", "");
        int length = replace.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            try {
                bArr[i] = (byte) Integer.parseInt(SafeString.substring(replace, i2, i2 + 2), 16);
            } catch (NumberFormatException unused) {
                HwLog.i("HEXUtils", "hexToBytes NumberFormatException");
            }
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append("0");
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString().toUpperCase(Locale.US).trim();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((("0123456789ABCDEF".indexOf(charArray[i2]) * 16) + "0123456789ABCDEF".indexOf(charArray[i2 + 1])) & 255);
        }
        try {
            return new String(bArr, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            HwLog.d("HEXUtils", "stringToHex() UnsupportedEncodingException occured");
            return null;
        }
    }

    public static String c(String str) {
        if (str == null) {
            HwLog.d("stringToHex:", "string is null");
            return "";
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bArr = new byte[0];
        try {
            bArr = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            HwLog.d("HEXUtils", "stringToHex() UnsupportedEncodingException occured");
        }
        for (int i = 0; i < bArr.length; i++) {
            sb.append(charArray[(bArr[i] & 240) >> 4]);
            sb.append(charArray[bArr[i] & BaseType.Obj]);
        }
        return sb.toString().trim();
    }

    public static String b(int i) {
        if (i <= 127) {
            return a(i);
        }
        int i2 = i & 127;
        int i3 = i >> 7;
        if (i3 > 127) {
            return a((i >> 14) + 128) + a(i3 & 255) + a(i2);
        }
        return a(i3 + 128) + a(i2);
    }
}

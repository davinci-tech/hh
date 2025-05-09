package defpackage;

import android.util.Log;
import com.google.flatbuffers.reflection.BaseType;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: classes5.dex */
public class mbg {
    public static String a(int i) {
        if (i < 0) {
            String hexString = Integer.toHexString(i);
            return hexString.substring(hexString.length() - 2, hexString.length());
        }
        if (i >= 16) {
            String hexString2 = Integer.toHexString(i);
            if (hexString2.length() % 2 == 0) {
                return hexString2;
            }
            return "0" + hexString2;
        }
        return "0" + Integer.toHexString(i);
    }

    public static String c(byte[] bArr) {
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

    public static String e(int i) {
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

    public static byte[] a(String str) {
        if (str == null) {
            return new byte[0];
        }
        String replace = str.replace(" ", "");
        int length = replace.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            try {
                bArr[i] = (byte) Integer.parseInt(replace.substring(i2, i2 + 2), 16);
            } catch (NumberFormatException unused) {
                Log.e("HexUtil", "hexToBytes NumberFormatException");
            }
        }
        return bArr;
    }

    public static String e(String str) {
        if (str == null) {
            Log.d("HexUtil", "stringToHex string is null");
            return "";
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder(16);
        byte[] bArr = new byte[0];
        try {
            bArr = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            Log.e("HexUtil", "stringToHex UnsupportedEncodingException");
        }
        for (int i = 0; i < bArr.length; i++) {
            sb.append(charArray[(bArr[i] & 240) >> 4]);
            sb.append(charArray[bArr[i] & BaseType.Obj]);
        }
        return sb.toString().trim();
    }
}

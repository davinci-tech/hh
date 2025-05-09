package health.compact.a;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: classes.dex */
public class HEXUtils {
    private HEXUtils() {
    }

    public static String e(int i) {
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

    public static String c(long j) {
        return e((int) ((j >> 56) & 255)) + e((int) ((j >> 48) & 255)) + e((int) ((j >> 40) & 255)) + e((int) ((j >> 32) & 255)) + e((int) ((j >> 24) & 255)) + e((int) ((j >> 16) & 255)) + e((int) ((j >> 8) & 255)) + e((int) (j & 255));
    }

    public static String e(long j) {
        return e((int) ((j >> 24) & 255)) + e((int) ((j >> 16) & 255)) + e((int) ((j >> 8) & 255)) + e((int) (j & 255));
    }

    public static String d(int i) {
        return e((i >> 8) & 255) + e(i & 255);
    }

    public static byte[] c(String str) {
        if (str == null) {
            return null;
        }
        String replace = str.replace(" ", "");
        int length = replace.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            try {
                bArr[i] = (byte) Integer.parseInt(replace.substring(i2, i2 + 2), 16);
            } catch (NumberFormatException unused) {
                com.huawei.hwlogsmodel.LogUtil.b("HEXUtils", "hexToBytes NumberFormatException");
            }
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return b(bArr, bArr.length);
    }

    public static String b(byte[] bArr, int i) {
        if (bArr == null || bArr.length < i) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                sb.append("0");
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString().toUpperCase(Locale.US).trim();
    }

    public static String d(String str) {
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
            com.huawei.hwlogsmodel.LogUtil.b("HEXUtils", "hexToString UnsupportedEncodingException");
            return null;
        }
    }

    public static String b(String str) {
        if (str == null) {
            com.huawei.hwlogsmodel.LogUtil.h("HEXUtils", "stringToHex string is null");
            return "";
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bArr = new byte[0];
        try {
            bArr = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HEXUtils", "stringToHex UnsupportedEncodingException");
        }
        for (int i = 0; i < bArr.length; i++) {
            sb.append(charArray[(bArr[i] & 240) >> 4]);
            sb.append(charArray[bArr[i] & BaseType.Obj]);
        }
        return sb.toString().trim();
    }

    public static String c(int i) {
        if (i <= 127) {
            return e(i);
        }
        int i2 = i & 127;
        int i3 = i >> 7;
        if (i3 > 127) {
            return e((i >> 14) + 128) + e((i3 & 127) + 128) + e(i2);
        }
        return e(i3 + 128) + e(i2);
    }

    public static String a(double d) {
        return a(b(d));
    }

    private static byte[] b(double d) {
        byte[] bArr = new byte[8];
        long doubleToLongBits = Double.doubleToLongBits(d);
        for (int i = 0; i < 8; i++) {
            bArr[i] = Long.valueOf(doubleToLongBits).byteValue();
            doubleToLongBits >>= 8;
        }
        return bArr;
    }

    public static byte[] e(String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("HEXUtils", "parseHexStr2Byte hexString is empty");
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            try {
                bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
            } catch (NumberFormatException unused) {
                bArr[i] = 0;
            }
        }
        return bArr;
    }

    public static String b(float f) {
        return e(Float.floatToIntBits(f));
    }
}

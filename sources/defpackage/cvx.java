package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cvx {
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

    public static String b(long j) {
        return e((int) ((j >> 24) & 255)) + e((int) ((j >> 16) & 255)) + e((int) ((j >> 8) & 255)) + e((int) (j & 255));
    }

    public static String a(int i) {
        return e((i >> 8) & 255) + e(i & 255);
    }

    public static String g(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            String e = e(charAt);
            if (charAt > 255) {
                sb.append(e);
            } else {
                sb.append("00" + e);
            }
        }
        return sb.toString();
    }

    public static byte[] a(String str) {
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
                LogUtil.b("HEXUtils", "hexToBytes NumberFormatException");
            }
        }
        return bArr;
    }

    public static String d(byte[] bArr) {
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

    public static String e(String str) {
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
            LogUtil.b("HEXUtils", "hexToString UnsupportedEncodingException");
            return null;
        }
    }

    public static String c(String str) {
        if (str == null) {
            LogUtil.h("HEXUtils", "stringToHex string is null");
            return "";
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bArr = new byte[0];
        try {
            bArr = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("HEXUtils", "stringToHex UnsupportedEncodingException");
        }
        for (int i = 0; i < bArr.length; i++) {
            sb.append(charArray[(bArr[i] & 240) >> 4]);
            sb.append(charArray[bArr[i] & BaseType.Obj]);
        }
        return sb.toString().trim();
    }

    public static int j(int i) {
        int i2;
        if (i < 100) {
            i2 = 0;
        } else {
            i2 = i / 100;
            i %= 100;
        }
        try {
            return Integer.parseInt(e(i2) + e(i), 16);
        } catch (NumberFormatException unused) {
            LogUtil.b("HEXUtils", "timeTransferIntToHexStr NumberFormatException");
            return 0;
        }
    }

    public static String d(int i) {
        return d(c(i));
    }

    public static byte[] c(int i) {
        byte[] bArr;
        if (i < 128 && i >= 0) {
            return new byte[]{(byte) (i & 127)};
        }
        if (i < 16384) {
            bArr = new byte[]{(byte) (((i >>> 7) & 127) | 128), (byte) (i & 127)};
        } else {
            if (i >= 2097152) {
                throw new RuntimeException("length overflow, input : " + i + " bigger than 2097152 or smaller than 0");
            }
            bArr = new byte[]{(byte) (((i >>> 14) & 127) | 128), (byte) (((i >>> 7) & 127) | 128), (byte) (i & 127)};
        }
        return bArr;
    }

    public static String c(double d) {
        return d(d(d));
    }

    public static byte[] d(double d) {
        byte[] bArr = new byte[8];
        long doubleToLongBits = Double.doubleToLongBits(d);
        for (int i = 0; i < 8; i++) {
            bArr[i] = Long.valueOf(doubleToLongBits).byteValue();
            doubleToLongBits >>= 8;
        }
        return bArr;
    }

    public static int c(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || bArr.length > 4) {
            return i;
        }
        int length = bArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i2 |= (bArr[i3] & 255) << (((length - i3) - 1) * 8);
        }
        return i2;
    }

    public static String a(float f) {
        return b(Float.floatToIntBits(f));
    }

    public static float i(String str) {
        try {
            return Float.intBitsToFloat((int) Long.parseLong(str, 16));
        } catch (NumberFormatException unused) {
            LogUtil.b("HEXUtils", "unit32ToFloat NumberFormatException");
            return 0.0f;
        }
    }

    public static Double b(String str) {
        try {
            return Double.valueOf(Double.longBitsToDouble(new BigInteger(str, 16).longValue()));
        } catch (NumberFormatException unused) {
            LogUtil.b("HEXUtils", "float64ToDouble NumberFormatException");
            return Double.valueOf(0.0d);
        }
    }

    public static byte[] d(String str) {
        if (str == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[0];
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            return bArr;
        }
    }

    public static byte[] g(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] f(int i) {
        byte b = (byte) ((i >> 24) & 255);
        byte b2 = (byte) ((i >> 16) & 255);
        byte b3 = (byte) ((i >> 8) & 255);
        byte b4 = (byte) (i & 255);
        if (b != 0) {
            return new byte[]{b, b2, b3, b4};
        }
        return b2 != 0 ? new byte[]{b2, b3, b4} : b3 != 0 ? new byte[]{b3, b4} : new byte[]{b4};
    }

    public static byte[] b(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }
}

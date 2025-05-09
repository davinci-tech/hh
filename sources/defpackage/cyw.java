package defpackage;

import health.compact.a.util.LogUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/* loaded from: classes3.dex */
public class cyw {
    private static int a(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }

    public static int e(byte b) {
        return b & 255;
    }

    private static int e(int i) {
        return i & 15;
    }

    private static int d(byte b, byte b2) {
        return e(b) + (e(b2) << 8);
    }

    private static int a(byte b, byte b2, byte b3, byte b4) {
        return e(b) + (e(b2) << 8) + (e(b3) << 16) + (e(b4) << 24);
    }

    public static int e(byte[] bArr, int i, int i2) {
        if (bArr == null || e(i) + i2 > bArr.length) {
            return -1;
        }
        if (i == 17) {
            return e(bArr[i2]);
        }
        if (i == 18) {
            return d(bArr[i2], bArr[i2 + 1]);
        }
        if (i == 20) {
            return a(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]);
        }
        if (i == 36) {
            return a(a(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]), 32);
        }
        if (i == 33) {
            return a(e(bArr[i2]), 8);
        }
        if (i == 34) {
            return a(d(bArr[i2], bArr[i2 + 1]), 16);
        }
        LogUtil.e("PDROPE_GattParser", "no formatType");
        return -1;
    }

    public static String b(byte[] bArr, int i) {
        if (bArr == null || i > bArr.length) {
            return "";
        }
        byte[] bArr2 = new byte[bArr.length - i];
        for (int i2 = 0; i2 != bArr.length - i; i2++) {
            bArr2[i2] = bArr[i + i2];
        }
        try {
            return new String(bArr2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.e("ByteUtils", "getStringValue UnsupportedEncodingException ", e.getMessage());
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static void a(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 >= bArr.length) {
            return;
        }
        bArr[i2] = (byte) (i & 255);
    }

    public static void d(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        if (bArr == null || iArr == null || iArr.length - i2 < i3) {
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            a(bArr, iArr[i2], i);
            i4++;
            i2++;
            i++;
        }
    }

    public static void c(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 >= bArr.length) {
            return;
        }
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
    }

    public static void e(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        if (bArr == null || iArr == null || iArr.length - i2 < i3) {
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            c(bArr, iArr[i2], i);
            i += 2;
            i4++;
            i2++;
        }
    }

    public static String a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[bArr.length];
        for (int length = bArr.length - 1; length >= 0; length--) {
            bArr2[(bArr.length - 1) - length] = bArr[length];
        }
        return new BigInteger(1, bArr2).toString(i);
    }
}

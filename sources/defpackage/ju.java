package defpackage;

import android.util.Base64;
import java.security.SecureRandom;
import java.util.Arrays;

/* loaded from: classes7.dex */
public class ju {
    public static volatile SecureRandom b;
    public static final char[] c = "0123456789ABCDEF".toCharArray();

    public static byte[] a() {
        byte[] bArr = new byte[4];
        b().nextBytes(bArr);
        return bArr;
    }

    public static byte[] e() {
        byte[] bArr = new byte[2];
        b().nextBytes(bArr);
        return bArr;
    }

    public static SecureRandom b() {
        if (b != null) {
            return b;
        }
        synchronized (ju.class) {
            if (b == null) {
                b = new SecureRandom();
            }
        }
        return b;
    }

    public static byte[] b(byte[]... bArr) {
        int i = 0;
        for (byte[] bArr2 : bArr) {
            i += bArr2.length;
        }
        byte[] bArr3 = null;
        int i2 = 0;
        for (byte[] bArr4 : bArr) {
            if (bArr3 == null) {
                bArr3 = Arrays.copyOf(bArr4, i);
                i2 = bArr4.length;
            } else {
                System.arraycopy(bArr4, 0, bArr3, i2, bArr4.length);
                i2 += bArr4.length;
            }
        }
        return bArr3;
    }

    public static String b(byte[] bArr) {
        return Base64.encodeToString(bArr, 3);
    }

    public static byte[] c(short s) {
        return new byte[]{(byte) s, (byte) (s >> 8)};
    }

    public static byte[] d(long j) {
        return new byte[]{(byte) j, (byte) (j >> 8), (byte) (j >> 16), (byte) (j >> 24), (byte) (j >> 32), (byte) (j >> 40), (byte) (j >> 48), (byte) (j >> 56)};
    }

    public static byte[] b(char c2, char c3) {
        return new byte[]{(byte) (c2 & 255), (byte) (c3 & 255)};
    }

    public static byte[] d(byte b2) {
        return new byte[]{b2};
    }
}

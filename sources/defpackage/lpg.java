package defpackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class lpg {

    /* renamed from: a, reason: collision with root package name */
    private static int f14827a = -271733879;
    private static int b = 1732584193;
    private static final String c = "MultiSimEncode";
    private static int d = -1732584194;
    private static int e = 271733878;
    private static int i = -1009589776;

    private static int a(int i2, int i3, int i4) {
        return (i2 & (i3 ^ i4)) ^ i4;
    }

    private static int b(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    private static int c(int i2, int i3) {
        return (i3 << i2) | (i3 >>> (32 - i2));
    }

    private static int c(int i2, int i3, int i4) {
        return ((i2 | i3) & i4) | (i2 & i3);
    }

    private static void b() {
        b = 1732584193;
        f14827a = -271733879;
        d = -1732584194;
        e = 271733878;
        i = -1009589776;
    }

    private static int[] b(int[] iArr) {
        int i2;
        b();
        int[] iArr2 = new int[81];
        int i3 = 0;
        while (true) {
            if (i3 >= 16) {
                break;
            }
            if (i3 < iArr.length) {
                iArr2[i3] = iArr[i3];
            } else {
                iArr2[i3] = 0;
            }
            i3++;
        }
        for (i2 = 16; i2 < 80; i2++) {
            iArr2[i2] = c(1, ((iArr2[i2 - 3] ^ iArr2[i2 - 8]) ^ iArr2[i2 - 14]) ^ iArr2[i2 - 16]);
        }
        return e(iArr2, 1518500249, 1859775393, -1894007588, -899497514);
    }

    private static int[] e(int[] iArr, int i2, int i3, int i4, int i5) {
        int i6 = b;
        int i7 = f14827a;
        int i8 = d;
        int i9 = e;
        int i10 = i;
        int i11 = 0;
        int i12 = 0;
        while (i11 < 4) {
            int a2 = i10 + ((i6 << 5) | (i6 >>> 27)) + a(i7, i8, i9) + iArr[i12] + i2;
            int i13 = (i7 << 30) | (i7 >>> 2);
            int a3 = i9 + ((a2 << 5) | (a2 >>> 27)) + a(i6, i13, i8) + iArr[i12 + 1] + i2;
            int i14 = (i6 << 30) | (i6 >>> 2);
            int a4 = i8 + ((a3 << 5) | (a3 >>> 27)) + a(a2, i14, i13) + iArr[i12 + 2] + i2;
            i10 = (a2 << 30) | (a2 >>> 2);
            i7 = i13 + ((a4 << 5) | (a4 >>> 27)) + a(a3, i10, i14) + iArr[i12 + 3] + i2;
            i9 = (a3 << 30) | (a3 >>> 2);
            i6 = i14 + ((i7 << 5) | (i7 >>> 27)) + a(a4, i9, i10) + iArr[i12 + 4] + i2;
            i8 = (a4 << 30) | (a4 >>> 2);
            i11++;
            i12 += 5;
        }
        int i15 = 0;
        while (i15 < 4) {
            int b2 = i10 + ((i6 << 5) | (i6 >>> 27)) + b(i7, i8, i9) + iArr[i12] + i3;
            int i16 = (i7 << 30) | (i7 >>> 2);
            int b3 = i9 + ((b2 << 5) | (b2 >>> 27)) + b(i6, i16, i8) + iArr[i12 + 1] + i3;
            int i17 = (i6 << 30) | (i6 >>> 2);
            int b4 = i8 + ((b3 << 5) | (b3 >>> 27)) + b(b2, i17, i16) + iArr[i12 + 2] + i3;
            i10 = (b2 << 30) | (b2 >>> 2);
            i7 = i16 + ((b4 << 5) | (b4 >>> 27)) + b(b3, i10, i17) + iArr[i12 + 3] + i3;
            i9 = (b3 << 30) | (b3 >>> 2);
            i6 = i17 + ((i7 << 5) | (i7 >>> 27)) + b(b4, i9, i10) + iArr[i12 + 4] + i3;
            i8 = (b4 << 30) | (b4 >>> 2);
            i15++;
            i12 += 5;
        }
        int i18 = 0;
        while (i18 < 4) {
            int c2 = i10 + ((i6 << 5) | (i6 >>> 27)) + c(i7, i8, i9) + iArr[i12] + i4;
            int i19 = (i7 >>> 2) | (i7 << 30);
            int c3 = i9 + ((c2 << 5) | (c2 >>> 27)) + c(i6, i19, i8) + iArr[i12 + 1] + i4;
            int i20 = (i6 << 30) | (i6 >>> 2);
            int c4 = i8 + ((c3 << 5) | (c3 >>> 27)) + c(c2, i20, i19) + iArr[i12 + 2] + i4;
            i10 = (c2 << 30) | (c2 >>> 2);
            i7 = i19 + ((c4 << 5) | (c4 >>> 27)) + c(c3, i10, i20) + iArr[i12 + 3] + i4;
            i9 = (c3 << 30) | (c3 >>> 2);
            i6 = i20 + ((i7 << 5) | (i7 >>> 27)) + c(c4, i9, i10) + iArr[i12 + 4] + i4;
            i8 = (c4 << 30) | (c4 >>> 2);
            i18++;
            i12 += 5;
        }
        for (int i21 = 0; i21 <= 3; i21++) {
            int b5 = i10 + ((i6 << 5) | (i6 >>> 27)) + b(i7, i8, i9) + iArr[i12] + i5;
            int i22 = (i7 >>> 2) | (i7 << 30);
            int b6 = i9 + ((b5 << 5) | (b5 >>> 27)) + b(i6, i22, i8) + iArr[i12 + 1] + i5;
            int i23 = (i6 >>> 2) | (i6 << 30);
            int b7 = i8 + ((b6 << 5) | (b6 >>> 27)) + b(b5, i23, i22) + iArr[i12 + 2] + i5;
            i10 = (b5 << 30) | (b5 >>> 2);
            int i24 = i12 + 4;
            i7 = i22 + ((b7 << 5) | (b7 >>> 27)) + b(b6, i10, i23) + iArr[i12 + 3] + i5;
            i9 = (b6 >>> 2) | (b6 << 30);
            i12 += 5;
            i6 = i23 + ((i7 << 5) | (i7 >>> 27)) + b(b7, i9, i10) + iArr[i24] + i5;
            i8 = (b7 >>> 2) | (b7 << 30);
        }
        return new int[]{b + i6, f14827a + i7, d + i8, e + i9, i + i10};
    }

    public static byte[] a(int[] iArr, int i2) {
        int i3 = i2 / 40;
        byte[] bArr = new byte[64];
        byte[] a2 = a(iArr);
        System.arraycopy(a2, 0, bArr, 0, a2.length);
        byte[] bArr2 = null;
        for (int i4 = 0; i4 < i3; i4++) {
            for (int i5 = 0; i5 < 2; i5++) {
                byte[] a3 = a(b(d(bArr)));
                bArr2 = d(bArr2, a3);
                int i6 = 1;
                for (int i7 = 19; i7 >= 0; i7--) {
                    int i8 = i6 + (bArr[i7] & 255) + (a3[i7] & 255);
                    bArr[i7] = (byte) (i8 & 255);
                    i6 = i8 >>> 8;
                }
            }
        }
        return bArr2;
    }

    private static byte[] a(int[] iArr) {
        byte[] bArr = null;
        for (int i2 : iArr) {
            bArr = d(bArr, e(i2));
        }
        return bArr;
    }

    public static int[] d(byte[] bArr) {
        int[] iArr = new int[bArr.length / 4];
        for (int i2 = 0; i2 < bArr.length / 4; i2++) {
            int i3 = i2 * 4;
            iArr[i2] = a(Arrays.copyOfRange(bArr, i3, i3 + 4), 0);
        }
        return iArr;
    }

    private static int a(byte[] bArr, int i2) {
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        int length;
        int i2;
        if (bArr != null && bArr2 != null) {
            length = bArr.length + bArr2.length;
        } else if (bArr != null) {
            length = bArr.length;
        } else {
            if (bArr2 == null) {
                return new byte[0];
            }
            length = bArr2.length;
        }
        byte[] bArr3 = new byte[length];
        if (bArr == null || bArr.length == 0) {
            i2 = 0;
        } else {
            i2 = 0;
            for (byte b2 : bArr) {
                bArr3[i2] = b2;
                i2++;
            }
        }
        if (bArr2 != null && bArr2.length != 0) {
            for (byte b3 : bArr2) {
                bArr3[i2] = b3;
                i2++;
            }
        }
        return bArr3;
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = {0};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException unused) {
            loq.b(c, "encodeBySha1 no such algorithm error");
            return bArr2;
        }
    }

    public static byte[] e(int i2) {
        return new byte[]{(byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)};
    }
}

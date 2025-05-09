package defpackage;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.google.flatbuffers.reflection.BaseType;
import okio.Utf8;

/* loaded from: classes7.dex */
public final class ku {
    public static final byte[] c = new byte[128];
    public static final char[] d = new char[64];

    static {
        int i = 0;
        for (int i2 = 0; i2 < 128; i2++) {
            c[i2] = -1;
        }
        for (int i3 = 90; i3 >= 65; i3--) {
            c[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 122; i4 >= 97; i4--) {
            c[i4] = (byte) (i4 - 71);
        }
        for (int i5 = 57; i5 >= 48; i5--) {
            c[i5] = (byte) (i5 + 4);
        }
        byte[] bArr = c;
        bArr[43] = 62;
        bArr[47] = Utf8.REPLACEMENT_BYTE;
        for (int i6 = 0; i6 <= 25; i6++) {
            d[i6] = (char) (i6 + 65);
        }
        int i7 = 26;
        int i8 = 0;
        while (i7 <= 51) {
            d[i7] = (char) (i8 + 97);
            i7++;
            i8++;
        }
        int i9 = 52;
        while (i9 <= 61) {
            d[i9] = (char) (i + 48);
            i9++;
            i++;
        }
        char[] cArr = d;
        cArr[62] = '+';
        cArr[63] = '/';
    }

    public static boolean a(char c2) {
        return c2 < 128 && c[c2] != -1;
    }

    public static boolean b(char c2) {
        return c2 == ' ' || c2 == '\r' || c2 == '\n' || c2 == '\t';
    }

    public static boolean d(char c2) {
        return c2 == '=';
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr = new char[(i != 0 ? i2 + 1 : i2) * 4];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i2) {
            byte b = bArr[i4];
            byte b2 = bArr[i4 + 1];
            byte b3 = bArr[i4 + 2];
            byte b4 = (byte) (b2 & BaseType.Obj);
            byte b5 = (byte) (b & 3);
            int i6 = b >> 2;
            if ((b & Byte.MIN_VALUE) != 0) {
                i6 ^= 192;
            }
            byte b6 = (byte) i6;
            int i7 = b2 >> 4;
            if ((b2 & Byte.MIN_VALUE) != 0) {
                i7 ^= GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
            }
            byte b7 = (byte) i7;
            int i8 = (b3 & Byte.MIN_VALUE) == 0 ? b3 >> 6 : (b3 >> 6) ^ 252;
            char[] cArr2 = d;
            cArr[i5] = cArr2[b6];
            cArr[i5 + 1] = cArr2[b7 | (b5 << 4)];
            cArr[i5 + 2] = cArr2[(b4 << 2) | ((byte) i8)];
            cArr[i5 + 3] = cArr2[b3 & Utf8.REPLACEMENT_BYTE];
            i3++;
            i5 += 4;
            i4 += 3;
        }
        if (i == 8) {
            byte b8 = bArr[i4];
            byte b9 = (byte) (b8 & 3);
            int i9 = b8 >> 2;
            if ((b8 & Byte.MIN_VALUE) != 0) {
                i9 ^= 192;
            }
            char[] cArr3 = d;
            cArr[i5] = cArr3[(byte) i9];
            cArr[i5 + 1] = cArr3[b9 << 4];
            cArr[i5 + 2] = '=';
            cArr[i5 + 3] = '=';
        } else if (i == 16) {
            byte b10 = bArr[i4];
            byte b11 = bArr[i4 + 1];
            byte b12 = (byte) (b11 & BaseType.Obj);
            byte b13 = (byte) (b10 & 3);
            int i10 = b10 >> 2;
            if ((b10 & Byte.MIN_VALUE) != 0) {
                i10 ^= 192;
            }
            byte b14 = (byte) i10;
            int i11 = b11 >> 4;
            if ((b11 & Byte.MIN_VALUE) != 0) {
                i11 ^= GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
            }
            char[] cArr4 = d;
            cArr[i5] = cArr4[b14];
            cArr[i5 + 1] = cArr4[((byte) i11) | (b13 << 4)];
            cArr[i5 + 2] = cArr4[b12 << 2];
            cArr[i5 + 3] = '=';
        }
        return new String(cArr);
    }

    public static byte[] d(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int e = e(charArray);
        if (e % 4 != 0) {
            return null;
        }
        int i = e / 4;
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i * 3];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i - 1) {
            int i5 = i3 + 1;
            char c2 = charArray[i3];
            if (a(c2)) {
                int i6 = i3 + 2;
                char c3 = charArray[i5];
                if (a(c3)) {
                    int i7 = i3 + 3;
                    char c4 = charArray[i6];
                    if (a(c4)) {
                        char c5 = charArray[i7];
                        if (a(c5)) {
                            byte[] bArr2 = c;
                            byte b = bArr2[c2];
                            byte b2 = bArr2[c3];
                            byte b3 = bArr2[c4];
                            byte b4 = bArr2[c5];
                            bArr[i4] = (byte) ((b << 2) | (b2 >> 4));
                            int i8 = i4 + 2;
                            bArr[i4 + 1] = (byte) (((b2 & BaseType.Obj) << 4) | ((b3 >> 2) & 15));
                            i4 += 3;
                            bArr[i8] = (byte) ((b3 << 6) | b4);
                            i2++;
                            i3 += 4;
                        }
                    }
                }
            }
            return null;
        }
        int i9 = i3 + 1;
        char c6 = charArray[i3];
        if (!a(c6)) {
            return null;
        }
        int i10 = i3 + 2;
        char c7 = charArray[i9];
        if (!a(c7)) {
            return null;
        }
        byte[] bArr3 = c;
        byte b5 = bArr3[c6];
        byte b6 = bArr3[c7];
        char c8 = charArray[i10];
        char c9 = charArray[i3 + 3];
        if (a(c8) && a(c9)) {
            byte b7 = bArr3[c8];
            byte b8 = bArr3[c9];
            bArr[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            bArr[i4 + 1] = (byte) (((b6 & BaseType.Obj) << 4) | ((b7 >> 2) & 15));
            bArr[i4 + 2] = (byte) (b8 | (b7 << 6));
            return bArr;
        }
        if (d(c8) && d(c9)) {
            if ((b6 & BaseType.Obj) != 0) {
                return null;
            }
            int i11 = i2 * 3;
            byte[] bArr4 = new byte[i11 + 1];
            System.arraycopy(bArr, 0, bArr4, 0, i11);
            bArr4[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            return bArr4;
        }
        if (d(c8) || !d(c9)) {
            return null;
        }
        byte b9 = bArr3[c8];
        if ((b9 & 3) != 0) {
            return null;
        }
        int i12 = i2 * 3;
        byte[] bArr5 = new byte[i12 + 2];
        System.arraycopy(bArr, 0, bArr5, 0, i12);
        bArr5[i4] = (byte) ((b5 << 2) | (b6 >> 4));
        bArr5[i4 + 1] = (byte) (((b9 >> 2) & 15) | ((b6 & BaseType.Obj) << 4));
        return bArr5;
    }

    public static int e(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!b(cArr[i2])) {
                cArr[i] = cArr[i2];
                i++;
            }
        }
        return i;
    }
}

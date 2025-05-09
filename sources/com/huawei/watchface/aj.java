package com.huawei.watchface;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.utils.HwLog;
import java.io.FileInputStream;
import java.io.IOException;
import okio.Utf8;

/* loaded from: classes7.dex */
public final class aj {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f10892a = new char[64];
    private static final byte[] b = new byte[128];

    private static boolean a(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private static boolean b(char c) {
        return c == '=';
    }

    static {
        int i = 0;
        for (int i2 = 0; i2 < 128; i2++) {
            b[i2] = -1;
        }
        for (int i3 = 90; i3 >= 65; i3--) {
            b[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 122; i4 >= 97; i4--) {
            b[i4] = (byte) (i4 - 71);
        }
        for (int i5 = 57; i5 >= 48; i5--) {
            b[i5] = (byte) (i5 + 4);
        }
        byte[] bArr = b;
        bArr[43] = 62;
        bArr[47] = Utf8.REPLACEMENT_BYTE;
        for (int i6 = 0; i6 <= 25; i6++) {
            f10892a[i6] = (char) (i6 + 65);
        }
        int i7 = 26;
        int i8 = 0;
        while (i7 <= 51) {
            f10892a[i7] = (char) (i8 + 97);
            i7++;
            i8++;
        }
        int i9 = 52;
        while (i9 <= 61) {
            f10892a[i9] = (char) (i + 48);
            i9++;
            i++;
        }
        char[] cArr = f10892a;
        cArr[62] = '+';
        cArr[63] = '/';
    }

    private static boolean c(char c) {
        return c < 128 && b[c] != -1;
    }

    public static String a(byte[] bArr) {
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
            byte b2 = bArr[i4];
            byte b3 = bArr[i4 + 1];
            byte b4 = bArr[i4 + 2];
            byte b5 = (byte) (b3 & BaseType.Obj);
            byte b6 = (byte) (b2 & 3);
            int i6 = b2 >> 2;
            if ((b2 & Byte.MIN_VALUE) != 0) {
                i6 ^= 192;
            }
            byte b7 = (byte) i6;
            int i7 = b3 >> 4;
            if ((b3 & Byte.MIN_VALUE) != 0) {
                i7 ^= GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
            }
            byte b8 = (byte) i7;
            int i8 = (b4 & Byte.MIN_VALUE) == 0 ? b4 >> 6 : (b4 >> 6) ^ 252;
            char[] cArr2 = f10892a;
            cArr[i5] = cArr2[b7];
            cArr[i5 + 1] = cArr2[b8 | (b6 << 4)];
            cArr[i5 + 2] = cArr2[(b5 << 2) | ((byte) i8)];
            cArr[i5 + 3] = cArr2[b4 & Utf8.REPLACEMENT_BYTE];
            i3++;
            i5 += 4;
            i4 += 3;
        }
        if (i == 8) {
            byte b9 = bArr[i4];
            byte b10 = (byte) (b9 & 3);
            int i9 = b9 >> 2;
            if ((b9 & Byte.MIN_VALUE) != 0) {
                i9 ^= 192;
            }
            char[] cArr3 = f10892a;
            cArr[i5] = cArr3[(byte) i9];
            cArr[i5 + 1] = cArr3[b10 << 4];
            cArr[i5 + 2] = '=';
            cArr[i5 + 3] = '=';
        } else if (i == 16) {
            byte b11 = bArr[i4];
            byte b12 = bArr[i4 + 1];
            byte b13 = (byte) (b12 & BaseType.Obj);
            byte b14 = (byte) (b11 & 3);
            int i10 = b11 >> 2;
            if ((b11 & Byte.MIN_VALUE) != 0) {
                i10 ^= 192;
            }
            byte b15 = (byte) i10;
            int i11 = b12 >> 4;
            if ((b12 & Byte.MIN_VALUE) != 0) {
                i11 ^= GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
            }
            char[] cArr4 = f10892a;
            cArr[i5] = cArr4[b15];
            cArr[i5 + 1] = cArr4[((byte) i11) | (b14 << 4)];
            cArr[i5 + 2] = cArr4[b13 << 2];
            cArr[i5 + 3] = '=';
        }
        return new String(cArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006b A[LOOP:0: B:15:0x0026->B:28:0x006b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0068 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.aj.a(java.lang.String):byte[]");
    }

    private static byte[] a(byte[] bArr, byte b2, byte b3, char c, char c2, int i, int i2) {
        if (b(c) && b(c2)) {
            if ((b3 & BaseType.Obj) != 0) {
                return new byte[0];
            }
            int i3 = i * 3;
            byte[] bArr2 = new byte[i3 + 1];
            System.arraycopy(bArr, 0, bArr2, 0, i3);
            bArr2[i2] = (byte) ((b2 << 2) | (b3 >> 4));
            return bArr2;
        }
        if (b(c) || !b(c2)) {
            return new byte[0];
        }
        byte b4 = b[c];
        if ((b4 & 3) != 0) {
            return new byte[0];
        }
        int i4 = i * 3;
        byte[] bArr3 = new byte[i4 + 2];
        System.arraycopy(bArr, 0, bArr3, 0, i4);
        bArr3[i2] = (byte) ((b2 << 2) | (b3 >> 4));
        bArr3[i2 + 1] = (byte) (((b3 & BaseType.Obj) << 4) | ((b4 >> 2) & 15));
        return bArr3;
    }

    private static int a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!a(cArr[i2])) {
                cArr[i] = cArr[i2];
                i++;
            }
        }
        return i;
    }

    public static String b(byte[] bArr) {
        String a2 = a(bArr);
        return (a2 == null || !a2.endsWith("\n")) ? a2 : SafeString.substring(a2, 0, a2.length() - 1);
    }

    public static String b(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException e) {
            e = e;
        } catch (Exception e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            String b2 = b(bArr);
            cq.a(fileInputStream);
            return b2;
        } catch (IOException e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            HwLog.e("Base64", "imageFileToBase64 IOException:" + HwLog.printException((Exception) e));
            cq.a(fileInputStream2);
            return "";
        } catch (Exception e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            HwLog.e("Base64", "imageFileToBase64 Exception:" + HwLog.printException(e));
            cq.a(fileInputStream2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            cq.a(fileInputStream);
            throw th;
        }
    }
}

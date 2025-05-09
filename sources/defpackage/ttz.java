package defpackage;

import com.google.flatbuffers.reflection.BaseType;

/* loaded from: classes7.dex */
public final class ttz {
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String c(byte[] bArr, boolean z) {
        return new String(d(bArr, z));
    }

    public static byte[] e(String str) throws ttm {
        return b(str.toCharArray());
    }

    private static char[] d(byte[] bArr, boolean z) {
        return a(bArr, z ? c : b);
    }

    private static char[] a(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[bArr.length << 1];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b2 & 240) >>> 4];
            i += 2;
            cArr2[i2] = cArr[b2 & BaseType.Obj];
        }
        return cArr2;
    }

    private static byte[] b(char[] cArr) throws ttm {
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new ttm("Odd number of characters.");
        }
        byte[] bArr = new byte[length >> 1];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int digit = Character.digit(cArr[i], 16);
            if (digit == -1) {
                throw new ttm("Illegal hexadecimal character at index " + i);
            }
            int i3 = i + 1;
            int digit2 = Character.digit(cArr[i3], 16);
            if (digit2 == -1) {
                throw new ttm("Illegal hexadecimal character at index " + i3);
            }
            i += 2;
            bArr[i2] = (byte) (((digit << 4) | digit2) & 255);
            i2++;
        }
        return bArr;
    }
}

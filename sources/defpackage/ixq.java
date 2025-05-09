package defpackage;

import com.google.flatbuffers.reflection.BaseType;

/* loaded from: classes.dex */
public final class ixq {
    private static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static char[] e(byte[] bArr, boolean z) {
        return d(bArr, z ? b : d);
    }

    private static char[] d(byte[] bArr, char[] cArr) {
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

    public static String c(byte[] bArr, boolean z) {
        return new String(e(bArr, z));
    }
}

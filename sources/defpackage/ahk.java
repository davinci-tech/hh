package defpackage;

import com.google.flatbuffers.reflection.BaseType;

/* loaded from: classes3.dex */
public final class ahk {
    private static final char[] e = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String b(byte[] bArr, boolean z) {
        return new String(e(bArr, z));
    }

    private static char[] e(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[bArr.length << 1];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b & 240) >>> 4];
            i += 2;
            cArr2[i2] = cArr[b & BaseType.Obj];
        }
        return cArr2;
    }

    public static char[] e(byte[] bArr, boolean z) {
        return e(bArr, z ? d : e);
    }
}

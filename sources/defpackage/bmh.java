package defpackage;

import java.nio.charset.Charset;

/* loaded from: classes3.dex */
public class bmh {
    private static byte[] c(String str, Charset charset) {
        return str == null ? new byte[0] : str.getBytes(charset);
    }

    public static byte[] d(String str) {
        return c(str, bne.b);
    }

    private static String c(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }

    public static String e(byte[] bArr) {
        return c(bArr, bne.b);
    }

    public static String b(Object obj) {
        return obj == null ? "" : obj.toString().trim();
    }
}

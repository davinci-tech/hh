package health.compact.a;

import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class StringUtils {
    private StringUtils() {
    }

    private static byte[] a(String str, Charset charset) {
        return str == null ? new byte[0] : str.getBytes(charset);
    }

    public static byte[] b(String str) {
        return a(str, Charsets.f13108a);
    }

    private static String c(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }

    public static String b(byte[] bArr) {
        return c(bArr, Charsets.f13108a);
    }
}

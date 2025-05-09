package defpackage;

import android.util.Base64;
import java.nio.charset.StandardCharsets;

/* loaded from: classes7.dex */
public class twe {
    public static String a(byte[] bArr, int i) throws twc {
        if (bArr == null) {
            throw new twc(1005L, "base64 encodeToString error: data is null");
        }
        try {
            return Base64.encodeToString(bArr, i);
        } catch (Exception unused) {
            throw new twc(1005L, "base64 encodeToString error");
        }
    }

    public static String a(String str, int i) throws twc {
        if (str == null) {
            return "";
        }
        try {
            return new String(Base64.decode(str, i), StandardCharsets.UTF_8);
        } catch (Exception unused) {
            throw new twc(1005L, "base64 decode to string error");
        }
    }

    public static byte[] c(String str, int i) throws twc {
        if (str == null) {
            return new byte[0];
        }
        try {
            return Base64.decode(str, i);
        } catch (Exception unused) {
            throw new twc(1005L, "base64 decode error");
        }
    }
}

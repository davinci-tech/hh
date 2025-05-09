package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes10.dex */
public class uwr {
    private static final char[] b = "0123456789ABCDEF".toCharArray();

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            uwn.e("StringUtils", "Input parameter is empty.");
            return new byte[0];
        }
        try {
            return MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException unused) {
            uwn.e("StringUtils", "SHA-256 is not supported.");
            return new byte[0];
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = b;
            cArr[i2] = cArr2[(b2 & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b2 & BaseType.Obj];
        }
        return new String(cArr);
    }
}

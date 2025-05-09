package defpackage;

import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: classes3.dex */
public class bmv {
    public static byte[] e(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.e("Sha256", "digest occur NoSuchAlgorithmException.");
            return new byte[0];
        }
    }

    public static String c(String str, String str2) {
        if (str == null || str2 == null) {
            LogUtil.a("Sha256", "getHashCodeForString source or type is empty.");
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes("UTF-8"));
            return b(messageDigest.digest());
        } catch (UnsupportedEncodingException unused) {
            LogUtil.a("Sha256", "getHashCodeForString occur UnsupportedEncodingException.");
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            LogUtil.a("Sha256", "getHashCodeForString occur NoSuchAlgorithmException.");
            return "";
        }
    }

    private static String b(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            LogUtil.a("Sha256", "getHashCodeForString sourceByte is empty.");
            return "";
        }
        StringBuilder sb = new StringBuilder((bArr.length * 2) + 1);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase(Locale.ENGLISH);
    }
}

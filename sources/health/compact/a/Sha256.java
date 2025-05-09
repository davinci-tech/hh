package health.compact.a;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: classes.dex */
public final class Sha256 {
    private Sha256() {
    }

    public static byte[] e(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("Sha256", "digest, NoSuchAlgorithmException");
            return new byte[0];
        }
    }

    public static String e(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes("UTF-8"));
            return b(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused) {
            com.huawei.hwlogsmodel.LogUtil.h("Sha256", "getHashCodeForString Exception");
            return "";
        }
    }

    private static String b(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
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

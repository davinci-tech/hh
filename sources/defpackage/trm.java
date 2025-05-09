package defpackage;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes8.dex */
public class trm {
    public static String c(String str, SecretKey secretKey) {
        if (str == null || secretKey == null) {
            return "";
        }
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            return d(mac.doFinal(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            tos.e("HmacSha256Util", "getHmacSha256 UnsupportedEncodingException");
            return "";
        } catch (InvalidKeyException unused2) {
            tos.e("HmacSha256Util", "getHmacSha256 InvalidKeyException");
            return "";
        } catch (NoSuchAlgorithmException unused3) {
            tos.e("HmacSha256Util", "getHmacSha256 NoSuchAlgorithmException");
            return "";
        }
    }

    public static String a(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                return c(str, new SecretKeySpec(str2.getBytes("UTF-8"), "HmacSHA256"));
            } catch (UnsupportedEncodingException unused) {
                tos.e("HmacSha256Util", "getHmacSha256 UnsupportedEncodingException");
            }
        }
        return "";
    }

    private static String d(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append("0");
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString().toUpperCase(Locale.US).trim();
    }
}

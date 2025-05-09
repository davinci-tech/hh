package defpackage;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;
import net.openid.appauth.internal.Logger;

/* loaded from: classes7.dex */
public final class utm {
    private static final Pattern c = Pattern.compile("^[0-9a-zA-Z\\-\\.\\_\\~]{43,128}$");

    public static void a(String str) {
        utq.e(43 <= str.length(), "codeVerifier length is shorter than allowed by the PKCE specification");
        utq.e(str.length() <= 128, "codeVerifier length is longer than allowed by the PKCE specification");
        utq.e(c.matcher(str).matches(), "codeVerifier string contains illegal characters");
    }

    public static String c() {
        return e(new SecureRandom(), 64);
    }

    public static String e(SecureRandom secureRandom, int i) {
        utq.d(secureRandom, "entropySource cannot be null");
        utq.e(32 <= i, "entropyBytes is less than the minimum permitted");
        utq.e(i <= 96, "entropyBytes is greater than the maximum permitted");
        byte[] bArr = new byte[i];
        secureRandom.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }

    public static String e(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("ISO_8859_1"));
            return Base64.encodeToString(messageDigest.digest(), 11);
        } catch (UnsupportedEncodingException e) {
            Logger.e("ISO-8859-1 encoding not supported on this device!", e);
            throw new IllegalStateException("ISO-8859-1 encoding not supported", e);
        } catch (NoSuchAlgorithmException e2) {
            Logger.c("SHA-256 is not supported on this device! Using plain challenge", e2);
            return str;
        }
    }

    public static String b() {
        try {
            MessageDigest.getInstance("SHA-256");
            return "S256";
        } catch (NoSuchAlgorithmException unused) {
            return "plain";
        }
    }
}

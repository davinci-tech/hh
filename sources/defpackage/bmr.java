package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes3.dex */
public class bmr {
    private static final Object d = new Object();

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains(":")) {
            str = str.replace(":", "");
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return blq.d(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(messageDigest.digest(), 2);
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }
}

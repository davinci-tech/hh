package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class dis {
    public static String b(String str) {
        if (str != null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(str.getBytes("UTF-8"));
                return Base64.encodeToString(messageDigest.digest(), 2);
            } catch (UnsupportedEncodingException e) {
                LogUtil.a("EncryptUtil", "EncryptUtil encryptMacSHA256 e2=", e.getMessage());
            } catch (NoSuchAlgorithmException e2) {
                LogUtil.a("EncryptUtil", "EncryptUtil encryptMacSHA256 e1=", e2.getMessage());
            }
        }
        return "";
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("EncryptUtil", "in == null || length == 0");
            return "";
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        return e(upperCase) ? upperCase : c(upperCase);
    }

    public static boolean e(String str) {
        if (!TextUtils.isEmpty(str) && str.length() <= 12) {
            return Pattern.compile("^([A-F0-9]{2}){6}$").matcher(str).find();
        }
        LogUtil.b("EncryptUtil", "in == null || length >12");
        return false;
    }

    public static String d(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder(16);
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            if (str != null) {
                sb.append(hexString);
                sb.append(str);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString();
    }

    private static String c(String str) {
        String[] split;
        if (!str.contains(":") || (split = str.split(":")) == null || split.length != 6) {
            return "";
        }
        String str2 = split[0] + split[1] + split[2] + split[3] + split[4] + split[5];
        return e(str2) ? str2 : "";
    }
}

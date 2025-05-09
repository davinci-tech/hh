package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: classes3.dex */
class cdw {
    public static String a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return e(MessageDigest.getInstance(str2).digest(str.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                LogUtil.b("ResourcePathChecker", "getHashCodeString ", e.getMessage());
            }
        }
        return "";
    }

    private static String e(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
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

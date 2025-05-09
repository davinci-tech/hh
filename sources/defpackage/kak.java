package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class kak {
    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactSync", 1, "HashUtils", "hash(String) : null parameter.");
            return "";
        }
        return Integer.toString(e(str), 16);
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactSync", 1, "HashUtils", "hash(String,String) : null parameter");
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            return d(str);
        }
        return new BigInteger(e(str, str2)).toString(16);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactSync", 1, "HashUtils", "anonymize(String) : null parameter");
        }
        return d(a(str, "SHA-256")).replaceFirst(Constants.LINK, "0_");
    }

    private static int e(String str) {
        if (str == null) {
            LogUtil.h("ContactSync", 1, "HashUtils", "null parameter.");
            return 0;
        }
        return str.hashCode();
    }

    private static byte[] d(byte[] bArr, String str) {
        byte[] bArr2 = new byte[0];
        if (bArr == null || bArr.length == 0 || TextUtils.isEmpty(str)) {
            LogUtil.h("ContactSync", 1, "HashUtils", "invalid parameters in encrypt(byte[],String).");
            return bArr2;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NumberFormatException | NoSuchAlgorithmException e) {
            LogUtil.b("ContactSync", 1, "HashUtils", ExceptionUtils.d(e));
            return bArr2;
        }
    }

    private static byte[] e(String str, String str2) {
        byte[] bArr = new byte[0];
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("ContactSync", 1, "HashUtils", "invalid parameters in encrypt(String,String).");
            return bArr;
        }
        return d(str.getBytes(StandardCharsets.UTF_8), str2);
    }
}

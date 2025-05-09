package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class knq {
    public static String e(String str) {
        return d("SHA-256", str);
    }

    private static String d(String str, String str2) {
        try {
            return HEXUtils.a(MessageDigest.getInstance(str).digest(str2.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("ShaCryptUtils", "encode UnsupportedEncodingException code:", str);
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            LogUtil.b("ShaCryptUtils", "encode NoSuchAlgorithmException code:", str);
            return null;
        }
    }
}

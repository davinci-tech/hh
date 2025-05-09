package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Base64;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public final class jrz {
    public static String e(String str, String str2) {
        byte[] d = Base64.d(c(str, str2), false);
        if (d == null) {
            return null;
        }
        return new String(d, Charset.forName("UTF-8"));
    }

    public static byte[] c(String str, String str2) {
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(Charset.forName("UTF-8")), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            return mac.doFinal(bytes);
        } catch (InvalidKeyException e) {
            LogUtil.b("InvalidKeyException, ", e.getMessage());
            return new byte[0];
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.b("NoSuchAlgorithmException, ", e2.getMessage());
            return new byte[0];
        }
    }
}

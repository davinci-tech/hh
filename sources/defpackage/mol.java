package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes6.dex */
public final class mol {
    public static byte[] c(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            if (digest != null) {
                return digest;
            }
        } catch (UnsupportedEncodingException e) {
            LogUtil.e("Sha256", "UnsupportedEncodingException", ExceptionUtils.d(e));
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.e("Sha256", "NoSuchAlgorithmException", ExceptionUtils.d(e2));
        }
        return new byte[0];
    }

    public static byte[] a(InputStream inputStream) {
        if (inputStream == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[4096];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            if (digest != null) {
                return digest;
            }
        } catch (IOException e) {
            LogUtil.e("Sha256", "IOException", ExceptionUtils.d(e));
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.e("Sha256", "NoSuchAlgorithmException", ExceptionUtils.d(e2));
        }
        return new byte[0];
    }

    public static String b(String str) {
        return mok.c(c(str));
    }

    public static String e(InputStream inputStream) {
        return mok.c(a(inputStream));
    }
}

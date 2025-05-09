package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.HEXUtils;
import health.compact.a.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class knl {
    public static byte[] e(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return a(bArr, bArr2, bArr3);
    }

    public static byte[] c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return b(bArr, bArr2, bArr3);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
        return cipher.doFinal(bArr3);
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        LogUtil.c("EncryptUtil", "decryptByAesCbc begin");
        try {
            if (LogConfig.a()) {
                LogUtil.c("EncryptUtil", "decryptByAesCbc key is:", HEXUtils.a(bArr), ";vector is:", HEXUtils.a(bArr2), ";data is:", HEXUtils.a(bArr3));
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(2, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
            return cipher.doFinal(bArr3);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LogUtil.c("EncryptUtil", e.getClass().getName(), " e = ", a(e));
            return null;
        }
    }

    private static String a(Exception exc) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Deprecated
    public static String d(String str) {
        return b(str);
    }

    public static String e(String str) {
        return b(jfa.d() + str);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("EncryptUtil", "encryptSha256Base64 text ", str);
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(messageDigest.digest(), 2);
        } catch (NoSuchAlgorithmException e) {
            ReleaseLogUtil.c("EncryptUtil", "encryptSha256Base64 exception ", ExceptionUtils.d(e));
            return "";
        }
    }

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
            return HEXUtils.a(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            LogUtil.b("EncryptUtil", "EncryptUtil getEncryption ", e.getMessage());
            return "";
        }
    }
}

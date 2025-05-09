package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Base64;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class ctu {
    private static final byte[] e = {-107, BaseType.Double, 91, 10, -123, -117, -97, 113, -99, -117, -45, 51, -10, -66, -92, 39, 89};

    public static byte[] b(byte[] bArr, String str, byte[] bArr2) {
        boolean z = TextUtils.isEmpty(str) || bArr2 == null;
        if (bArr == null || z) {
            LogUtil.h("AesCryptUtils", "encrypt content or key or iv is null");
            return new byte[0];
        }
        try {
            return knl.e(Base64.e(str), bArr2, bArr);
        } catch (Exception unused) {
            LogUtil.b("AesCryptUtils", "encrypt is exception");
            return new byte[0];
        }
    }

    public static byte[] b(byte[] bArr) {
        if (bArr == null || bArr.length == 0 || bArr.length > 64) {
            return new byte[0];
        }
        byte[] a2 = ctx.a(1024);
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            a2[((bArr.length + i2) * 8) - 2] = bArr[i2];
        }
        a2[0] = (byte) bArr.length;
        return a2;
    }

    public static byte[] b() {
        byte[] bArr = new byte[8];
        nsg.b().nextBytes(bArr);
        return bArr;
    }

    public static byte[] d() {
        return (byte[]) e.clone();
    }

    public static byte[] c(String str, byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        boolean z = bArr == null || bArr2 == null;
        if (TextUtils.isEmpty(str) || z) {
            LogUtil.h("AesCryptUtils", "Input Argument illegal ...");
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bytes = str.trim().getBytes(Charset.forName("UTF-8"));
            int length = bytes.length;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bytes, 0, bArr3, 0, length);
            cipher.init(1, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
            return cipher.doFinal(bArr3);
        } catch (NoSuchPaddingException unused) {
            LogUtil.b("AesCryptUtils", "encrypt NoSuchPaddingException");
            return null;
        }
    }

    public static String b(byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        boolean z = bArr2 == null || bArr3 == null;
        if (bArr == null || z) {
            LogUtil.h("AesCryptUtils", "Input Argument illegal ...");
            return "";
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(bArr3));
            return new String(cipher.doFinal(bArr), "UTF-8").trim();
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.b("AesCryptUtils", "encrypt NoSuchAlgorithmException");
            return "";
        } catch (NoSuchPaddingException unused2) {
            LogUtil.b("AesCryptUtils", "encrypt NoSuchPaddingException");
            return "";
        }
    }
}

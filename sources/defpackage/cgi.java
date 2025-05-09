package defpackage;

import android.util.Base64;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.WhiteBoxManager;
import java.io.UnsupportedEncodingException;
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
public class cgi {
    private static final byte[] d = new byte[16];
    private static final byte[] b = new byte[16];

    static {
        String b2 = b(25, 1025, 2025);
        String b3 = b(26, 1026, 2026);
        String[] split = b2.split(" ");
        String[] split2 = b3.split(" ");
        for (int i = 0; i < 16; i++) {
            try {
                if (i < split.length) {
                    d[i] = (byte) Integer.parseInt(split[i], 16);
                }
                if (i < split2.length) {
                    b[i] = (byte) Integer.parseInt(split2[i], 16);
                }
            } catch (NumberFormatException e) {
                LogUtil.b("EncryptUtils", "EncryptUtils numException: ", e.getMessage());
            }
        }
    }

    public static String b(int i, int i2, int i3) {
        String str;
        UnsupportedEncodingException e;
        WhiteBoxManager d2 = WhiteBoxManager.d();
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(d2.d(1, i));
        stringBuffer.append(d2.d(1, i2));
        stringBuffer.append(d2.d(1, i3));
        String stringBuffer2 = stringBuffer.toString();
        LogUtil.c("EncryptUtils", "getKeyAndIv before decrypt: ", stringBuffer2);
        try {
            str = new String(d2.a(Base64.decode(stringBuffer2, 2)), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            str = stringBuffer2;
            e = e2;
        }
        try {
            LogUtil.c("EncryptUtils", "getKeyAndIv decrypt: ", str);
        } catch (UnsupportedEncodingException e3) {
            e = e3;
            LogUtil.b("EncryptUtils", "getKeyAndIv: exception:", e.getMessage());
            return str;
        }
        return str;
    }

    public static void d(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("EncryptUtils", "setKeyBytes bytes is null");
        } else {
            System.arraycopy(bArr, 0, d, 0, bArr.length);
        }
    }

    public static byte[] c(byte[] bArr) {
        try {
        } catch (InvalidAlgorithmParameterException e) {
            LogUtil.b("EncryptUtils", "encrypt ", e.getMessage());
        } catch (InvalidKeyException e2) {
            LogUtil.b("EncryptUtils", "encrypt ", e2.getMessage());
        } catch (NoSuchAlgorithmException e3) {
            LogUtil.b("EncryptUtils", "encrypt ", e3.getMessage());
        } catch (BadPaddingException e4) {
            LogUtil.b("EncryptUtils", "encrypt ", e4.getMessage());
        } catch (IllegalBlockSizeException e5) {
            LogUtil.b("EncryptUtils", "encrypt ", e5.getMessage());
        } catch (NoSuchPaddingException e6) {
            LogUtil.b("EncryptUtils", "encrypt ", e6.getMessage());
        }
        if (bArr != null) {
            Cipher cipher = Cipher.getInstance("AES/CTR/PKCS7Padding");
            cipher.init(1, new SecretKeySpec(d, "AES"), new IvParameterSpec(b));
            return cipher.doFinal(bArr);
        }
        LogUtil.a("EncryptUtils", "encrypt sSrc is null");
        return null;
    }

    public static byte[] b(byte[] bArr) {
        try {
            if (bArr != null) {
                SecretKeySpec secretKeySpec = new SecretKeySpec(d, "AES");
                Cipher cipher = Cipher.getInstance("AES/CTR/PKCS7Padding");
                cipher.init(2, secretKeySpec, new IvParameterSpec(b));
                return cipher.doFinal(bArr);
            }
            LogUtil.a("EncryptUtils", "decrypt sSrc is null");
            return null;
        } catch (InvalidAlgorithmParameterException e) {
            LogUtil.b("EncryptUtils", "decrypt ", e.getMessage());
            return null;
        } catch (InvalidKeyException e2) {
            LogUtil.b("EncryptUtils", "decrypt ", e2.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e3) {
            LogUtil.b("EncryptUtils", "decrypt ", e3.getMessage());
            return null;
        } catch (BadPaddingException e4) {
            LogUtil.b("EncryptUtils", "decrypt ", e4.getMessage());
            return null;
        } catch (IllegalBlockSizeException e5) {
            LogUtil.b("EncryptUtils", "decrypt ", e5.getMessage());
            return null;
        } catch (NoSuchPaddingException e6) {
            LogUtil.b("EncryptUtils", "decrypt ", e6.getMessage());
            return null;
        }
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[16];
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            try {
                bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, i2 * 2), 16);
            } catch (NumberFormatException e) {
                LogUtil.b("EncryptUtils", "stringToByte numException: ", e.getMessage());
            }
            i = i2;
        }
        return bArr;
    }
}

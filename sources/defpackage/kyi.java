package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
class kyi {
    public static int e(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (c(bArr, bArr2, bArr3, bArr4)) {
            return d(bArr, bArr2, bArr3, bArr4);
        }
        return -1;
    }

    private static int d(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] a2 = a(bArr2, bArr3);
        if (e(a2)) {
            return -2;
        }
        byte[] a3 = a(12);
        byte[] a4 = a(10);
        if (e(a3) || e(a4)) {
            return -3;
        }
        byte[] b = b(a2, a3, a4, bArr);
        if (e(b)) {
            return -4;
        }
        System.arraycopy(a3, 0, bArr4, 0, 12);
        System.arraycopy(a4, 0, bArr4, 12, 10);
        System.arraycopy(b, 0, bArr4, 22, bArr.length + 16);
        return 0;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
            return mac.doFinal(bArr2);
        } catch (NullPointerException unused) {
            LogUtil.b("AirActiveEncryptUtils", "getGcmKey occur NullPointerException");
            return new byte[0];
        } catch (InvalidKeyException unused2) {
            LogUtil.b("AirActiveEncryptUtils", "getGcmKey occur InvalidKeyException");
            return new byte[0];
        } catch (NoSuchAlgorithmException unused3) {
            LogUtil.b("AirActiveEncryptUtils", "getGcmKey occur NoSuchAlgorithmException");
            return new byte[0];
        }
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, secretKeySpec, new GCMParameterSpec(128, Arrays.copyOf(bArr2, 12)));
            cipher.updateAAD(bArr3);
            return cipher.doFinal(bArr4);
        } catch (InvalidAlgorithmParameterException unused) {
            LogUtil.b("AirActiveEncryptUtils", "encryptByAesGcm occur InvalidAlgorithmParameterException");
            return new byte[0];
        } catch (InvalidKeyException unused2) {
            LogUtil.b("AirActiveEncryptUtils", "encryptByAesGcm occur InvalidKeyException");
            return new byte[0];
        } catch (InvalidParameterException unused3) {
            LogUtil.b("AirActiveEncryptUtils", "encryptByAesGcm occur InvalidParameterException");
            return new byte[0];
        } catch (NoSuchAlgorithmException unused4) {
            LogUtil.b("AirActiveEncryptUtils", "encryptByAesGcm occur NoSuchAlgorithmException");
            return new byte[0];
        } catch (BadPaddingException unused5) {
            LogUtil.b("AirActiveEncryptUtils", "encryptByAesGcm occur BadPaddingException");
            return new byte[0];
        } catch (IllegalBlockSizeException unused6) {
            LogUtil.b("AirActiveEncryptUtils", "encryptByAesGcm occur IllegalBlockSizeException");
            return new byte[0];
        } catch (NoSuchPaddingException unused7) {
            LogUtil.b("AirActiveEncryptUtils", "encryptByAesGcm occur NoSuchPaddingException");
            return new byte[0];
        }
    }

    private static byte[] a(int i) {
        try {
            return b(i).getEncoded();
        } catch (InvalidParameterException | NoSuchAlgorithmException unused) {
            try {
                SecretKey b = b(16);
                if (e(b.getEncoded())) {
                    return new byte[0];
                }
                byte[] bArr = new byte[i];
                System.arraycopy(b.getEncoded(), 0, bArr, 0, i);
                return bArr;
            } catch (InvalidParameterException | NoSuchAlgorithmException unused2) {
                LogUtil.b("AirActiveEncryptUtils", "generateRandomBytes occur GCM_TAG_LENGTH exception");
                LogUtil.b("AirActiveEncryptUtils", "generateRandomBytes occur exception");
                return new byte[0];
            }
        }
    }

    private static SecretKey b(int i) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(i * 8, secureRandom);
        return keyGenerator.generateKey();
    }

    private static boolean c(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        return (bArr == null || bArr2 == null || bArr3 == null || bArr4 == null || bArr.length == 0 || bArr2.length == 0 || bArr3.length == 0 || bArr4.length == 0 || bArr.length > 256 || bArr4.length > bArr.length + 38) ? false : true;
    }

    private static boolean e(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }
}

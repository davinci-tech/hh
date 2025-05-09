package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btcommon.EncryptionBase;
import health.compact.a.LogUtil;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public class iyi extends EncryptionBase {
    private String b;

    public iyi(int i) {
        b(i);
    }

    private void b(int i) {
        if (i == 1) {
            this.b = "AES/CBC/PKCS5Padding";
        } else {
            this.b = "AES/CBC/PKCS5Padding";
        }
    }

    @Override // com.huawei.hwbtsdk.btcommon.EncryptionBase
    public byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return (byte[]) new byte[0].clone();
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher cipher = Cipher.getInstance(this.b);
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException e) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "encrypt InvalidAlgorithmParameterException is:", ExceptionUtils.d(e));
            return (byte[]) new byte[0].clone();
        } catch (IllegalBlockSizeException e2) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "encrypt IllegalBlockSizeException is:", ExceptionUtils.d(e2));
            return (byte[]) new byte[0].clone();
        } catch (NoSuchAlgorithmException e3) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "encrypt NoSuchAlgorithmException is:", ExceptionUtils.d(e3));
            return (byte[]) new byte[0].clone();
        } catch (BadPaddingException e4) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "encrypt BadPaddingException is:", ExceptionUtils.d(e4));
            return (byte[]) new byte[0].clone();
        } catch (InvalidKeyException e5) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "encrypt InvalidKeyException is:", ExceptionUtils.d(e5));
            return (byte[]) new byte[0].clone();
        } catch (NoSuchPaddingException e6) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "encrypt NoSuchPaddingException is:", ExceptionUtils.d(e6));
            return (byte[]) new byte[0].clone();
        } finally {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "encrypt finally");
        }
    }

    @Override // com.huawei.hwbtsdk.btcommon.EncryptionBase
    public byte[] desEncrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return (byte[]) new byte[0].clone();
        }
        try {
            Cipher cipher = Cipher.getInstance(this.b);
            cipher.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(bArr3));
            return cipher.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException e) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "desEncrypt InvalidAlgorithmParameterException is:", ExceptionUtils.d(e));
            return (byte[]) new byte[0].clone();
        } catch (BadPaddingException e2) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "desEncrypt BadPaddingException is:", ExceptionUtils.d(e2));
            return (byte[]) new byte[0].clone();
        } catch (NoSuchAlgorithmException e3) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "desEncrypt NoSuchAlgorithmException is:", ExceptionUtils.d(e3));
            return (byte[]) new byte[0].clone();
        } catch (IllegalBlockSizeException e4) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "desEncrypt IllegalBlockSizeException is:", ExceptionUtils.d(e4));
            return (byte[]) new byte[0].clone();
        } catch (InvalidKeyException e5) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "desEncrypt InvalidKeyException is:", ExceptionUtils.d(e5));
            return (byte[]) new byte[0].clone();
        } catch (NoSuchPaddingException e6) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "desEncrypt NoSuchPaddingException is:", ExceptionUtils.d(e6));
            return (byte[]) new byte[0].clone();
        } finally {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "AesCbcPkCS5Padding", "desEncrypt finally");
        }
    }
}

package com.huawei.wisecloud.drmclient.utils;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/* loaded from: classes7.dex */
public class RSAUtil {
    private static final String RSA_DECRYPT_MGF1 = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final String RSA_DECRYPT_PKCS1 = "RSA/ECB/PKCS1Padding";

    public static boolean verifyRSASignature(String str, PublicKey publicKey, int i, byte[] bArr) throws HwDrmException {
        try {
            byte[] bytes = str.getBytes(HwDrmConstant.DRM_CHARSET);
            Signature signature = Signature.getInstance(RSASignAlgo.getAlgoName(i));
            signature.initVerify(publicKey);
            signature.update(bytes);
            return signature.verify(bArr);
        } catch (InvalidKeyException e) {
            String str2 = "fail to verify, InvalidKeyException: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e("RSAUtil", str2);
            throw new HwDrmException(str2);
        } catch (NoSuchAlgorithmException e2) {
            String str3 = "fail to verify, NoSuchAlgorithmException: " + HwDrmLog.printException((Exception) e2);
            HwDrmLog.e("RSAUtil", str3);
            throw new HwDrmException(str3);
        } catch (SignatureException e3) {
            String str4 = "fail to verify, SignatureException: " + HwDrmLog.printException((Exception) e3);
            HwDrmLog.e("RSAUtil", str4);
            throw new HwDrmException(str4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0060 A[Catch: IllegalBlockSizeException -> 0x0068, InvalidAlgorithmParameterException -> 0x006a, BadPaddingException -> 0x006c, InvalidKeyException -> 0x006e, NoSuchPaddingException -> 0x0070, NoSuchAlgorithmException -> 0x0072, TryCatch #2 {InvalidAlgorithmParameterException -> 0x006a, InvalidKeyException -> 0x006e, NoSuchAlgorithmException -> 0x0072, BadPaddingException -> 0x006c, IllegalBlockSizeException -> 0x0068, NoSuchPaddingException -> 0x0070, blocks: (B:4:0x0006, B:13:0x0034, B:16:0x0049, B:17:0x005f, B:18:0x0060, B:20:0x001a, B:23:0x0024, B:26:0x0075, B:27:0x007d), top: B:2:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] decrypt(byte[] r7, java.security.PrivateKey r8, java.lang.String r9) throws com.huawei.wisecloud.drmclient.exception.HwDrmException {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.wisecloud.drmclient.utils.RSAUtil.decrypt(byte[], java.security.PrivateKey, java.lang.String):byte[]");
    }
}

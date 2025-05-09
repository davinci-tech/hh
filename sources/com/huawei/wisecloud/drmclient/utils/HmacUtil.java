package com.huawei.wisecloud.drmclient.utils;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes9.dex */
public class HmacUtil {
    private static final int BUFFER_SIZE = 4096;

    public static String digest2Base64Url(String str, int i, byte[] bArr) throws GeneralSecurityException {
        String base64UrlEncode = Base64Util.base64UrlEncode(digest2Byte(str, i, bArr));
        if (base64UrlEncode == null) {
            return null;
        }
        return base64UrlEncode;
    }

    public static byte[] digest2Byte(String str, int i, byte[] bArr) throws GeneralSecurityException {
        byte[] bytes = str.getBytes(HwDrmConstant.DRM_CHARSET);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, HmacAlgo.getAlgoName(i));
        Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(bytes);
    }

    public static byte[] digest2Byte(byte[] bArr, String str, byte[] bArr2) throws HwDrmException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            return mac.doFinal(bArr);
        } catch (InvalidKeyException e) {
            String str2 = "fail to gigest, InvalidKeyException: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e("HmacUtil", str2);
            throw new HwDrmException(str2);
        } catch (NoSuchAlgorithmException e2) {
            String str3 = "fail to gigest, NoSuchAlgorithmException: " + HwDrmLog.printException((Exception) e2);
            HwDrmLog.e("HmacUtil", str3);
            throw new HwDrmException(str3);
        }
    }

    public static byte[] digest2Inputstream(InputStream inputStream, String str, byte[] bArr) {
        try {
            try {
                try {
                    try {
                        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, str);
                        Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
                        mac.init(secretKeySpec);
                        byte[] bArr2 = new byte[4096];
                        while (true) {
                            int read = inputStream.read(bArr2);
                            if (read <= 0) {
                                break;
                            }
                            mac.update(bArr2, 0, read);
                        }
                        return mac.doFinal();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        HwDrmLog.e("HmacUtil", "NoSuchAlgorithmException is caught in digest2Inputstream ：" + HwDrmLog.printException((Exception) e));
                        if (inputStream == null) {
                            return null;
                        }
                        try {
                            inputStream.close();
                            return null;
                        } catch (IOException unused) {
                            HwDrmLog.e("HmacUtil", "IOException is caught in digest2Inputstream about inputStream close");
                            return null;
                        }
                    }
                } catch (IOException e2) {
                    HwDrmLog.e("HmacUtil", "IOException is caught in digest2Inputstream ：" + HwDrmLog.printException((Exception) e2));
                    if (inputStream == null) {
                        return null;
                    }
                    inputStream.close();
                    return null;
                }
            } catch (InvalidKeyException unused2) {
                HwDrmLog.e("HmacUtil", "fail to gigest, InvalidKeyException: ");
                if (inputStream == null) {
                    return null;
                }
                inputStream.close();
                return null;
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                    HwDrmLog.e("HmacUtil", "IOException is caught in digest2Inputstream about inputStream close");
                }
            }
        }
    }
}

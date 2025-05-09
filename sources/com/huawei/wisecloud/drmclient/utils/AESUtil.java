package com.huawei.wisecloud.drmclient.utils;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class AESUtil {
    private static final String AES_ALGORITHM_NAME = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String AES_ECB = "AES/ECB/PKCS5Padding";
    private static final String AES_GCM = "AES/GCM/NoPadding";
    private static final int AES_GCM_DEFAULT_TAG_LENGTH = 128;
    private static final String AES_OFB = "AES/OFB/PKCS5Padding";
    private static final String AES_OFB_NO_PADDING = "AES/OFB/NoPadding";
    private static final String TAG = "AESUtil";

    public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, String str) throws HwDrmException {
        char c;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, AES_ALGORITHM_NAME);
            Cipher cipher = Cipher.getInstance(str);
            switch (str.hashCode()) {
                case -1593046894:
                    if (str.equals(AES_CBC)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1183691157:
                    if (str.equals(AES_OFB)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -601004814:
                    if (str.equals(AES_ECB)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 64687:
                    if (str.equals(AES_ALGORITHM_NAME)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 403528690:
                    if (str.equals(AES_GCM)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1030148556:
                    if (str.equals(AES_OFB_NO_PADDING)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                cipher.init(2, secretKeySpec);
                return cipher.doFinal(bArr);
            }
            if (c == 1 || c == 2) {
                cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
                return cipher.doFinal(bArr);
            }
            if (c == 3) {
                cipher.init(2, secretKeySpec, new GCMParameterSpec(128, bArr3));
                return cipher.doFinal(bArr);
            }
            if (c == 4) {
                cipher.init(2, secretKeySpec);
                return cipher.doFinal(bArr);
            }
            if (c == 5) {
                cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
                return cipher.doFinal(bArr);
            }
            throw new HwDrmException(logErrorMsg("unsupported AES algorithm" + str));
        } catch (Exception e) {
            HwDrmLog.e(TAG, "fail to decrypt, NoSuchAlgorithmException :" + HwDrmLog.printException(e));
            throw new HwDrmException(logErrorMsg("fail to decrypt, NoSuchAlgorithmException :"));
        }
    }

    private static String logErrorMsg(String str) {
        HwDrmLog.e(TAG, str);
        return str;
    }
}

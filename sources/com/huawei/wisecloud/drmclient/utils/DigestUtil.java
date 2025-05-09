package com.huawei.wisecloud.drmclient.utils;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes7.dex */
public class DigestUtil {
    private static final String SHA_256 = "SHA-256";

    public static String sha256UrlFromString(String str) throws HwDrmException {
        return Base64Util.base64UrlEncode(sha256(str.getBytes(HwDrmConstant.DRM_CHARSET)));
    }

    public static String sha256FromBytes(byte[] bArr) throws HwDrmException {
        return Base64Util.base64Encode(sha256(bArr));
    }

    private static byte[] sha256(byte[] bArr) throws HwDrmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            String str = "fail to digest, NoSuchAlgorithmException: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e("DigestUtil", str);
            throw new HwDrmException(str);
        }
    }
}

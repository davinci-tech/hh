package com.huawei.up.utils;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes9.dex */
public class HMACSHA256 {
    private static final String TAG = "HMACSHA256";

    private HMACSHA256() {
    }

    public static String getHmacSha256(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            return HEXUtils.a(mac.doFinal(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            LogUtil.b(TAG, "exception e=", e.getMessage());
            return null;
        } catch (InvalidKeyException e2) {
            LogUtil.b(TAG, "exception e=", e2.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e3) {
            LogUtil.b(TAG, "exception e=", e3.getMessage());
            return null;
        }
    }
}

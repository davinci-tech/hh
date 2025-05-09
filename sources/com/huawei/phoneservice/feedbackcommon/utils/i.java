package com.huawei.phoneservice.feedbackcommon.utils;

import android.text.TextUtils;
import android.util.Base64;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes6.dex */
public final class i {
    private static byte[] b(String str, String str2) {
        StringBuilder sb;
        String message;
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(Charset.forName("UTF-8")), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            return mac.doFinal(bytes);
        } catch (InvalidKeyException e) {
            sb = new StringBuilder("digest2Byte failed because of InvalidKeyException. ");
            message = e.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.c("HMAC256Utils", sb.toString());
            return new byte[0];
        } catch (NoSuchAlgorithmException e2) {
            sb = new StringBuilder("digest2Byte failed because of NoSuchAlgorithmException. ");
            message = e2.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.c("HMAC256Utils", sb.toString());
            return new byte[0];
        }
    }

    public static String c(String str, String str2) {
        byte[] encode = !TextUtils.isEmpty(str2) ? Base64.encode(b(str, str2), 2) : null;
        if (encode == null) {
            return null;
        }
        return new String(encode, Charset.forName("UTF-8"));
    }
}

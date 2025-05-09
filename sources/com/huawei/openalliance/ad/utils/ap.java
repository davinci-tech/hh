package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public abstract class ap {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7588a = "ap";

    public static String a(String str, String str2) {
        String str3;
        String str4;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(str.getBytes("UTF-8"), "HmacSHA256"));
            return an.a(mac.doFinal(str2.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            str3 = f7588a;
            str4 = "fail to cipher UnsupportedEncodingException";
            ho.d(str3, str4);
            return null;
        } catch (InvalidKeyException unused2) {
            str3 = f7588a;
            str4 = "fail to cipher InvalidKeyException";
            ho.d(str3, str4);
            return null;
        } catch (NoSuchAlgorithmException unused3) {
            str3 = f7588a;
            str4 = "fail to cipher NoSuchAlgorithmException";
            ho.d(str3, str4);
            return null;
        }
    }
}

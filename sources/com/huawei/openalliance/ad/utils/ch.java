package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.openalliance.ad.ho;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes5.dex */
public class ch {
    private static boolean a(RSAPublicKey rSAPublicKey) {
        return rSAPublicKey != null && rSAPublicKey.getModulus().bitLength() >= 3072;
    }

    public static boolean a(String str, String str2, RSAPublicKey rSAPublicKey) {
        String str3;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !a(rSAPublicKey)) {
            str3 = "content or public key or sign value is null";
        } else {
            try {
                Signature signature = Signature.getInstance("SHA256WithRSA");
                signature.initVerify(rSAPublicKey);
                signature.update(str.getBytes("UTF-8"));
                return signature.verify(Base64.decode(str2, 0));
            } catch (Throwable th) {
                str3 = "check sign exception: " + th.getClass().getSimpleName();
            }
        }
        ho.c("RSAEncryptUtil", str3);
        return false;
    }

    public static boolean a(Context context, String str, String str2, RSAPublicKey rSAPublicKey) {
        String str3;
        if (context != null && x.j(context)) {
            return true;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !a(rSAPublicKey)) {
            str3 = "content or public key or sign value is null";
        } else {
            try {
                Signature signature = Signature.getInstance("SHA256withRSA/PSS");
                signature.initVerify(rSAPublicKey);
                signature.update(str.getBytes("UTF-8"));
                return signature.verify(Base64.decode(str2, 0));
            } catch (Throwable th) {
                str3 = "check sign exception: " + th.getClass().getSimpleName();
            }
        }
        ho.c("RSAEncryptUtil", str3);
        return false;
    }

    public static RSAPublicKey a(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(JceNames.RSA).generatePublic(new X509EncodedKeySpec(an.a(str)));
        } catch (Throwable th) {
            ho.c("RSAEncryptUtil", "load public key err:" + th.getClass().getSimpleName());
            return null;
        }
    }
}

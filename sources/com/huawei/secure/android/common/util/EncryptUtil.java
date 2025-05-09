package com.huawei.secure.android.common.util;

import android.util.Base64;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Deprecated
/* loaded from: classes9.dex */
public class EncryptUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8641a = "EncryptUtil";
    private static final String b = "RSA";

    @Deprecated
    public static byte[] generateSecureRandom(int i) {
        SecureRandom secureRandom;
        byte[] bArr = new byte[i];
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException unused) {
            LogsUtil.e(f8641a, "getSecureRandomBytes: NoSuchAlgorithmException");
            secureRandom = null;
        }
        if (secureRandom == null) {
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException unused2) {
                LogsUtil.e(f8641a, "getSecureRandomBytes getInstance: NoSuchAlgorithmException");
                return new byte[0];
            } catch (Exception e) {
                LogsUtil.e(f8641a, "getSecureRandomBytes getInstance: exception : " + e.getMessage());
                return new byte[0];
            }
        }
        secureRandom.nextBytes(bArr);
        return bArr;
    }

    @Deprecated
    public static String generateSecureRandomStr(int i) {
        return HexUtil.byteArray2HexStr(generateSecureRandom(i));
    }

    @Deprecated
    public static PrivateKey getPrivateKey(String str) {
        try {
            try {
                return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                LogsUtil.e(f8641a, "load Key Exception:" + e.getMessage(), true);
                return null;
            }
        } catch (IllegalArgumentException unused) {
            LogsUtil.e(f8641a, "base64 decode IllegalArgumentException", true);
            return null;
        } catch (Exception unused2) {
            LogsUtil.e(f8641a, "base64 decode Exception", true);
            return null;
        }
    }

    @Deprecated
    public static RSAPublicKey getPublicKey(String str) {
        try {
            try {
                return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                LogsUtil.e(f8641a, "load Key Exception:" + e.getMessage(), true);
                return null;
            }
        } catch (IllegalArgumentException unused) {
            LogsUtil.e(f8641a, "base64 decode IllegalArgumentException", true);
            return null;
        } catch (Exception unused2) {
            LogsUtil.e(f8641a, "base64 decode Exception", true);
            return null;
        }
    }
}

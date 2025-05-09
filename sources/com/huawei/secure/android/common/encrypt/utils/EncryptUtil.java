package com.huawei.secure.android.common.encrypt.utils;

import android.util.Base64;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;

/* loaded from: classes6.dex */
public class EncryptUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8601a = "EncryptUtil";
    private static final String b = "RSA";
    private static boolean c = true;
    private static boolean d = true;

    private static SecureRandom a() {
        SecureRandom secureRandom;
        b.a(f8601a, "generateSecureRandomNew ");
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException unused) {
            b.b(f8601a, "getSecureRandomBytes: NoSuchAlgorithmException");
            secureRandom = null;
        }
        if (secureRandom == null) {
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException unused2) {
                b.b(f8601a, "NoSuchAlgorithmException");
                return secureRandom;
            } catch (Throwable th) {
                if (d) {
                    b.b(f8601a, "exception : " + th.getMessage() + " , you should implementation bcprov-jdk15on library");
                    d = false;
                }
                return secureRandom;
            }
        }
        AESEngine aESEngine = new AESEngine();
        byte[] bArr = new byte[32];
        secureRandom.nextBytes(bArr);
        return new SP800SecureRandomBuilder(secureRandom, true).setEntropyBitsRequired(384).buildCTR(aESEngine, 256, bArr, false);
    }

    public static SecureRandom genSecureRandom() {
        if (c) {
            return a();
        }
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException unused) {
            b.b(f8601a, "genSecureRandom: NoSuchAlgorithmException");
            return null;
        }
    }

    public static byte[] generateSecureRandom(int i) {
        SecureRandom secureRandom;
        if (c) {
            return a(i);
        }
        byte[] bArr = new byte[i];
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException unused) {
            b.b(f8601a, "getSecureRandomBytes: NoSuchAlgorithmException");
            secureRandom = null;
        }
        if (secureRandom == null) {
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException unused2) {
                b.b(f8601a, "getSecureRandomBytes getInstance: NoSuchAlgorithmException");
                return new byte[0];
            } catch (Exception e) {
                b.b(f8601a, "getSecureRandomBytes getInstance: exception : " + e.getMessage());
                return new byte[0];
            }
        }
        secureRandom.nextBytes(bArr);
        return bArr;
    }

    public static String generateSecureRandomStr(int i) {
        return HexUtil.byteArray2HexStr(generateSecureRandom(i));
    }

    public static PrivateKey getPrivateKey(String str) {
        try {
            try {
                return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                b.b(f8601a, "load Key Exception:" + e.getMessage());
                return null;
            }
        } catch (IllegalArgumentException unused) {
            b.b(f8601a, "base64 decode IllegalArgumentException");
            return null;
        } catch (Exception e2) {
            b.b(f8601a, "base64 decode Exception" + e2.getMessage());
            return null;
        }
    }

    public static RSAPublicKey getPublicKey(String str) {
        try {
            try {
                return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
            } catch (GeneralSecurityException e) {
                b.b(f8601a, "load Key Exception:" + e.getMessage());
                return null;
            }
        } catch (IllegalArgumentException unused) {
            b.b(f8601a, "base64 decode IllegalArgumentException");
            return null;
        } catch (Exception e2) {
            b.b(f8601a, "base64 decode Exception" + e2.getMessage());
            return null;
        }
    }

    public static boolean isBouncycastleFlag() {
        return c;
    }

    public static void setBouncycastleFlag(boolean z) {
        b.c(f8601a, "setBouncycastleFlag: " + z);
        c = z;
    }

    private static byte[] a(int i) {
        SecureRandom a2 = a();
        if (a2 == null) {
            return new byte[0];
        }
        try {
            byte[] bArr = new byte[i];
            a2.nextBytes(bArr);
            return bArr;
        } catch (NullPointerException e) {
            b.b(f8601a, "secureRandom nextBytes error is : " + e.getMessage());
            return new byte[0];
        }
    }
}

package com.huawei.secure.android.common.encrypt.keystore.aes;

import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import com.huawei.secure.android.common.encrypt.utils.b;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/* loaded from: classes6.dex */
public class AesGcmKS {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8593a = "GCMKS";
    private static final String b = "AndroidKeyStore";
    private static final String c = "AES/GCM/NoPadding";
    private static final String d = "";
    private static final int e = 12;
    private static final int f = 256;
    private static Map<String, SecretKey> g = new ConcurrentHashMap();

    private static boolean a() {
        return true;
    }

    private static SecretKey b(String str, Boolean bool) throws AesKsParamException {
        if (TextUtils.isEmpty(str)) {
            throw new AesKsParamException("alias is null");
        }
        if (g.get(str) == null) {
            synchronized (AesGcmKS.class) {
                if (g.get(str) == null) {
                    SecretKey a2 = a(str, bool);
                    if (a2 == null) {
                        throw new AesKsParamException("AES GCM is decrypt,but alias is null");
                    }
                    g.put(str, a2);
                }
            }
        }
        return g.get(str);
    }

    public static String decrypt(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(f8593a, "alias or encrypt content is null");
            return "";
        }
        try {
            return new String(decrypt(str, HexUtil.hexStr2ByteArray(str2)), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            b.b(f8593a, "decrypt: UnsupportedEncodingException : " + e2.getMessage());
            return "";
        }
    }

    public static String decryptNew(String str, String str2) throws AesKsParamException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(f8593a, "alias or encrypt content is null");
            throw new AesKsParamException("alias or encrypt content is null");
        }
        try {
            return new String(decryptNew(str, HexUtil.hexStr2ByteArray(str2)), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            b.b(f8593a, "decrypt: UnsupportedEncodingException : " + e2.getMessage());
            throw new AesKsParamException("decrypt: UnsupportedEncodingException");
        }
    }

    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(f8593a, "alias or encrypt content is null");
            return "";
        }
        try {
            return HexUtil.byteArray2HexStr(encrypt(str, str2.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e2) {
            b.b(f8593a, "encrypt: UnsupportedEncodingException : " + e2.getMessage());
            return "";
        }
    }

    public static String encryptNew(String str, String str2) throws AesKsParamException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(f8593a, "alias or encrypt content is null");
            throw new AesKsParamException("alias or encrypt content is null");
        }
        try {
            return HexUtil.byteArray2HexStr(encryptNew(str, str2.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e2) {
            b.b(f8593a, "encrypt: UnsupportedEncodingException : " + e2.getMessage());
            throw new AesKsParamException("encrypt: UnsupportedEncodingException");
        }
    }

    private static SecretKey a(String str, Boolean bool) throws AesKsParamException {
        b.c(f8593a, "load key");
        try {
            KeyStore keyStore = KeyStore.getInstance(b);
            keyStore.load(null);
            Key key = keyStore.getKey(str, null);
            if (key instanceof SecretKey) {
                return (SecretKey) key;
            }
            if (!bool.booleanValue()) {
                return null;
            }
            b.c(f8593a, "generate key");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", b);
            keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).build());
            return keyGenerator.generateKey();
        } catch (IOException e2) {
            b.b(f8593a, "IOException : " + e2.getMessage());
            throw new AesKsParamException("IOException : " + e2.getMessage());
        } catch (InvalidAlgorithmParameterException e3) {
            b.b(f8593a, "InvalidAlgorithmParameterException : " + e3.getMessage());
            throw new AesKsParamException("InvalidAlgorithmParameterException : " + e3.getMessage());
        } catch (KeyStoreException e4) {
            b.b(f8593a, "KeyStoreException : " + e4.getMessage());
            throw new AesKsParamException("KeyStoreException : " + e4.getMessage());
        } catch (NoSuchAlgorithmException e5) {
            b.b(f8593a, "NoSuchAlgorithmException : " + e5.getMessage());
            throw new AesKsParamException("NoSuchAlgorithmException : " + e5.getMessage());
        } catch (NoSuchProviderException e6) {
            b.b(f8593a, "NoSuchProviderException : " + e6.getMessage());
            throw new AesKsParamException("NoSuchProviderException : " + e6.getMessage());
        } catch (UnrecoverableKeyException e7) {
            b.b(f8593a, "UnrecoverableKeyException : " + e7.getMessage());
            throw new AesKsParamException("UnrecoverableKeyException : " + e7.getMessage());
        } catch (CertificateException e8) {
            b.b(f8593a, "CertificateException : " + e8.getMessage());
            throw new AesKsParamException("CertificateException : " + e8.getMessage());
        } catch (Exception e9) {
            b.b(f8593a, "Exception: " + e9.getMessage());
            throw new AesKsParamException("Exception : " + e9.getMessage());
        }
    }

    public static byte[] decrypt(String str, byte[] bArr) {
        SecretKey secretKey;
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!a()) {
                b.b(f8593a, "sdk version is too low");
                return bArr2;
            }
            if (bArr.length <= 12) {
                b.b(f8593a, "Decrypt source data is invalid.");
                return bArr2;
            }
            try {
                secretKey = b(str, (Boolean) false);
            } catch (AesKsParamException e2) {
                b.b(f8593a, "e : " + e2.getMessage());
                secretKey = null;
            }
            return secretKey == null ? bArr2 : decrypt(secretKey, bArr);
        }
        b.b(f8593a, "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] encrypt(String str, byte[] bArr) {
        SecretKey secretKey;
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!a()) {
                b.b(f8593a, "sdk version is too low");
                return bArr2;
            }
            try {
                secretKey = b(str, (Boolean) true);
            } catch (AesKsParamException e2) {
                b.b(f8593a, "e : " + e2.getMessage());
                secretKey = null;
            }
            return secretKey == null ? bArr2 : encrypt(secretKey, bArr);
        }
        b.b(f8593a, "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] decryptNew(String str, byte[] bArr) throws AesKsParamException {
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (a()) {
                if (bArr.length > 12) {
                    return decryptNew(b(str, (Boolean) false), bArr);
                }
                b.b(f8593a, "Decrypt source data is invalid.");
                throw new AesKsParamException("Decrypt source data is invalid");
            }
            b.b(f8593a, "sdk version is too low");
            throw new AesKsParamException("sdk version is too low");
        }
        b.b(f8593a, "alias or encrypt content is null");
        throw new AesKsParamException("alias or encrypt content is null");
    }

    public static byte[] encryptNew(String str, byte[] bArr) throws AesKsParamException {
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (a()) {
                return encryptNew(b(str, (Boolean) true), bArr);
            }
            b.b(f8593a, "sdk version is too low");
            throw new AesKsParamException("sdk version is too low");
        }
        b.b(f8593a, "alias or encrypt content is null");
        throw new AesKsParamException("alias or encrypt content is null");
    }

    private static byte[] b(SecretKey secretKey, byte[] bArr) throws AesKsParamException {
        byte[] bArr2 = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance(c);
            cipher.init(1, secretKey);
            byte[] doFinal = cipher.doFinal(bArr);
            byte[] iv = cipher.getIV();
            if (iv != null && iv.length == 12) {
                byte[] copyOf = Arrays.copyOf(iv, iv.length + doFinal.length);
                System.arraycopy(doFinal, 0, copyOf, iv.length, doFinal.length);
                return copyOf;
            }
            b.b(f8593a, "IV is invalid.");
            return bArr2;
        } catch (InvalidKeyException e2) {
            b.b(f8593a, "InvalidKeyException : " + e2.getMessage());
            throw new AesKsParamException("InvalidKeyException : " + e2.getMessage());
        } catch (NoSuchAlgorithmException e3) {
            b.b(f8593a, "NoSuchAlgorithmException : " + e3.getMessage());
            throw new AesKsParamException("NoSuchAlgorithmException : " + e3.getMessage());
        } catch (BadPaddingException e4) {
            b.b(f8593a, "BadPaddingException : " + e4.getMessage());
            throw new AesKsParamException("BadPaddingException : " + e4.getMessage());
        } catch (IllegalBlockSizeException e5) {
            b.b(f8593a, "IllegalBlockSizeException : " + e5.getMessage());
            throw new AesKsParamException("IllegalBlockSizeException : " + e5.getMessage());
        } catch (NoSuchPaddingException e6) {
            b.b(f8593a, "NoSuchPaddingException : " + e6.getMessage());
            throw new AesKsParamException("NoSuchPaddingException : " + e6.getMessage());
        } catch (Exception e7) {
            b.b(f8593a, "Exception: " + e7.getMessage());
            throw new AesKsParamException("Exception : " + e7.getMessage());
        }
    }

    public static byte[] encryptNew(SecretKey secretKey, byte[] bArr) throws AesKsParamException {
        if (bArr == null) {
            b.b(f8593a, "content is null");
            throw new AesKsParamException("content is null.");
        }
        if (secretKey != null) {
            if (a()) {
                return b(secretKey, bArr);
            }
            b.b(f8593a, "sdk version is too low");
            throw new AesKsParamException("sdk version is too low");
        }
        b.b(f8593a, "secret key is null");
        throw new AesKsParamException("secret key is null.");
    }

    public static byte[] decryptNew(SecretKey secretKey, byte[] bArr) throws AesKsParamException {
        if (secretKey == null) {
            b.b(f8593a, "Decrypt secret key is null");
            throw new AesKsParamException("Decrypt secret key is null");
        }
        if (bArr != null) {
            if (a()) {
                if (bArr.length > 12) {
                    return a(secretKey, bArr);
                }
                b.b(f8593a, "Decrypt source data is invalid.");
                throw new AesKsParamException("Decrypt source data is invalid.");
            }
            b.b(f8593a, "sdk version is too low");
            throw new AesKsParamException("sdk version is too low.");
        }
        b.b(f8593a, "content is null");
        throw new AesKsParamException("content is null");
    }

    public static byte[] encrypt(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (bArr == null) {
            b.b(f8593a, "content is null");
            return bArr2;
        }
        if (secretKey == null) {
            b.b(f8593a, "secret key is null");
            return bArr2;
        }
        if (!a()) {
            b.b(f8593a, "sdk version is too low");
            return bArr2;
        }
        try {
            return b(secretKey, bArr);
        } catch (AesKsParamException e2) {
            b.b(f8593a, "e : " + e2.getMessage());
            return bArr2;
        }
    }

    public static byte[] decrypt(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (secretKey == null) {
            b.b(f8593a, "Decrypt secret key is null");
            return bArr2;
        }
        if (bArr == null) {
            b.b(f8593a, "content is null");
            return bArr2;
        }
        if (!a()) {
            b.b(f8593a, "sdk version is too low");
            return bArr2;
        }
        if (bArr.length <= 12) {
            b.b(f8593a, "Decrypt source data is invalid.");
            return bArr2;
        }
        try {
            return a(secretKey, bArr);
        } catch (AesKsParamException e2) {
            b.b(f8593a, "e : " + e2.getMessage());
            return bArr2;
        }
    }

    private static byte[] a(SecretKey secretKey, byte[] bArr) throws AesKsParamException {
        byte[] copyOf = Arrays.copyOf(bArr, 12);
        try {
            Cipher cipher = Cipher.getInstance(c);
            cipher.init(2, secretKey, new GCMParameterSpec(128, copyOf));
            return cipher.doFinal(bArr, 12, bArr.length - 12);
        } catch (InvalidAlgorithmParameterException e2) {
            b.b(f8593a, "InvalidAlgorithmParameterException : " + e2.getMessage());
            throw new AesKsParamException("InvalidAlgorithmParameterException : " + e2.getMessage());
        } catch (InvalidKeyException e3) {
            b.b(f8593a, "InvalidKeyException : " + e3.getMessage());
            throw new AesKsParamException("InvalidKeyException : " + e3.getMessage());
        } catch (NoSuchAlgorithmException e4) {
            b.b(f8593a, "NoSuchAlgorithmException : " + e4.getMessage());
            throw new AesKsParamException("NoSuchAlgorithmException : " + e4.getMessage());
        } catch (BadPaddingException e5) {
            b.b(f8593a, "BadPaddingException : " + e5.getMessage());
            throw new AesKsParamException("BadPaddingException : " + e5.getMessage());
        } catch (IllegalBlockSizeException e6) {
            b.b(f8593a, "IllegalBlockSizeException : " + e6.getMessage());
            throw new AesKsParamException("IllegalBlockSizeException : " + e6.getMessage());
        } catch (NoSuchPaddingException e7) {
            b.b(f8593a, "NoSuchPaddingException : " + e7.getMessage());
            throw new AesKsParamException("NoSuchPaddingException : " + e7.getMessage());
        } catch (Exception e8) {
            b.b(f8593a, "Exception: " + e8.getMessage());
            throw new AesKsParamException("Exception: " + e8.getMessage());
        }
    }
}

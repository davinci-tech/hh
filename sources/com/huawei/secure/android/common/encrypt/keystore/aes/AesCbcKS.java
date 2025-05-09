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
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes6.dex */
public class AesCbcKS {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8592a = "CBCKS";
    private static final String b = "AndroidKeyStore";
    private static final String c = "AES/CBC/PKCS7Padding";
    private static final String d = "";
    private static final int e = 16;
    private static final int f = 256;
    private static Map<String, SecretKey> g = new ConcurrentHashMap();

    private static SecretKey a(String str, Boolean bool) {
        b.c(f8592a, "load key");
        SecretKey secretKey = null;
        try {
            KeyStore keyStore = KeyStore.getInstance(b);
            keyStore.load(null);
            Key key = keyStore.getKey(str, null);
            if (key != null && (key instanceof SecretKey)) {
                secretKey = (SecretKey) key;
            } else if (bool.booleanValue()) {
                b.c(f8592a, "generate key");
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", b);
                keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").setKeySize(256).build());
                secretKey = keyGenerator.generateKey();
            }
        } catch (IOException e2) {
            b.b(f8592a, "IOException: " + e2.getMessage());
        } catch (InvalidAlgorithmParameterException e3) {
            b.b(f8592a, "InvalidAlgorithmParameterException: " + e3.getMessage());
        } catch (KeyStoreException e4) {
            b.b(f8592a, "KeyStoreException: " + e4.getMessage());
        } catch (NoSuchAlgorithmException e5) {
            b.b(f8592a, "NoSuchAlgorithmException: " + e5.getMessage());
        } catch (NoSuchProviderException e6) {
            b.b(f8592a, "NoSuchProviderException: " + e6.getMessage());
        } catch (UnrecoverableKeyException e7) {
            b.b(f8592a, "UnrecoverableKeyException: " + e7.getMessage());
        } catch (CertificateException e8) {
            b.b(f8592a, "CertificateException: " + e8.getMessage());
        } catch (Exception e9) {
            b.b(f8592a, "Exception: " + e9.getMessage());
        }
        return secretKey;
    }

    private static boolean a() {
        return true;
    }

    private static SecretKey b(String str, Boolean bool) throws AesKsParamException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (g.get(str) == null) {
            synchronized (AesCbcKS.class) {
                if (g.get(str) == null) {
                    SecretKey a2 = a(str, bool);
                    if (a2 == null) {
                        throw new AesKsParamException("AES CBC current is decrypt,but alias is null");
                    }
                    g.put(str, a2);
                }
            }
        }
        return g.get(str);
    }

    public static String decrypt(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(f8592a, "alias or encrypt content is null");
            return "";
        }
        try {
            return new String(decrypt(str, HexUtil.hexStr2ByteArray(str2)), "UTF-8");
        } catch (AesKsParamException e2) {
            b.b(f8592a, "encrypt: AesKsParamException : " + e2.getMessage());
            return "";
        } catch (UnsupportedEncodingException e3) {
            b.b(f8592a, "encrypt: UnsupportedEncodingException : " + e3.getMessage());
            return "";
        }
    }

    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            b.b(f8592a, "encrypt 1 content is null");
            return "";
        }
        try {
            return HexUtil.byteArray2HexStr(encrypt(str, str2.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            b.b(f8592a, "encrypt: UnsupportedEncodingException");
            return "";
        }
    }

    public static byte[] encrypt(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!a()) {
                b.b(f8592a, "sdk version is too low");
                return bArr2;
            }
            try {
                Cipher cipher = Cipher.getInstance(c);
                SecretKey b2 = b(str, true);
                if (b2 == null) {
                    b.b(f8592a, "encrypt secret key is null");
                    return bArr2;
                }
                cipher.init(1, b2);
                byte[] doFinal = cipher.doFinal(bArr);
                byte[] iv = cipher.getIV();
                if (iv != null && iv.length == 16) {
                    byte[] copyOf = Arrays.copyOf(iv, iv.length + doFinal.length);
                    System.arraycopy(doFinal, 0, copyOf, iv.length, doFinal.length);
                    return copyOf;
                }
                b.b(f8592a, "IV is invalid.");
                return bArr2;
            } catch (InvalidKeyException e2) {
                b.b(f8592a, "InvalidKeyException: " + e2.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e3) {
                b.b(f8592a, "NoSuchAlgorithmException: " + e3.getMessage());
                return bArr2;
            } catch (BadPaddingException e4) {
                b.b(f8592a, "BadPaddingException: " + e4.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e5) {
                b.b(f8592a, "IllegalBlockSizeException: " + e5.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e6) {
                b.b(f8592a, "NoSuchPaddingException: " + e6.getMessage());
                return bArr2;
            } catch (Exception e7) {
                b.b(f8592a, "Exception: " + e7.getMessage());
                return bArr2;
            }
        }
        b.b(f8592a, "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] decrypt(String str, byte[] bArr) throws AesKsParamException {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!a()) {
                b.b(f8592a, "sdk version is too low");
                return bArr2;
            }
            if (bArr.length <= 16) {
                b.b(f8592a, "Decrypt source data is invalid.");
                return bArr2;
            }
            SecretKey b2 = b(str, false);
            if (b2 == null) {
                b.b(f8592a, "decrypt secret key is null");
                return bArr2;
            }
            byte[] copyOf = Arrays.copyOf(bArr, 16);
            try {
                Cipher cipher = Cipher.getInstance(c);
                cipher.init(2, b2, new IvParameterSpec(copyOf));
                return cipher.doFinal(bArr, 16, bArr.length - 16);
            } catch (InvalidAlgorithmParameterException e2) {
                b.b(f8592a, "InvalidAlgorithmParameterException: " + e2.getMessage());
                return bArr2;
            } catch (InvalidKeyException e3) {
                b.b(f8592a, "InvalidKeyException: " + e3.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e4) {
                b.b(f8592a, "NoSuchAlgorithmException: " + e4.getMessage());
                return bArr2;
            } catch (BadPaddingException e5) {
                b.b(f8592a, "BadPaddingException: " + e5.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e6) {
                b.b(f8592a, "IllegalBlockSizeException: " + e6.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e7) {
                b.b(f8592a, "NoSuchPaddingException: " + e7.getMessage());
                return bArr2;
            } catch (Exception e8) {
                b.b(f8592a, "Exception: " + e8.getMessage());
                return bArr2;
            }
        }
        b.b(f8592a, "alias or encrypt content is null");
        return bArr2;
    }
}

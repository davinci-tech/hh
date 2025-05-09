package com.huawei.secure.android.common.encrypt.keystore.rsa;

import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.secure.android.common.encrypt.utils.b;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes9.dex */
public abstract class RSAEncryptKS {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8594a = "RSAEncryptKS";
    private static final String b = "AndroidKeyStore";
    private static final String c = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final String d = "";
    private static final int e = 2048;
    private static final int f = 3072;

    private static boolean a() {
        return true;
    }

    private static PublicKey b(String str, boolean z) {
        if (!b(str)) {
            a(str, z);
        }
        Certificate c2 = c(str);
        if (c2 != null) {
            return c2.getPublicKey();
        }
        return null;
    }

    private static Certificate c(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance(b);
            keyStore.load(null);
            return keyStore.getCertificate(str);
        } catch (IOException e2) {
            b.b(f8594a, "IOException: " + e2.getMessage());
            return null;
        } catch (KeyStoreException e3) {
            b.b(f8594a, "KeyStoreException: " + e3.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e4) {
            b.b(f8594a, "NoSuchAlgorithmException: " + e4.getMessage());
            return null;
        } catch (CertificateException e5) {
            b.b(f8594a, "CertificateException: " + e5.getMessage());
            return null;
        } catch (Exception e6) {
            b.b(f8594a, "Exception: " + e6.getMessage());
            return null;
        }
    }

    @Deprecated
    public static String decrpyt(String str, String str2) {
        try {
            return new String(decrpyt(str, Base64.decode(str2, 0)), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            b.b(f8594a, "UnsupportedEncodingException: " + e2.getMessage());
            return "";
        } catch (Exception e3) {
            b.b(f8594a, "Exception: " + e3.getMessage());
            return "";
        }
    }

    public static String decrpytNew(String str, String str2) {
        try {
            return new String(decrpytNew(str, Base64.decode(str2, 0)), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            b.b(f8594a, "UnsupportedEncodingException: " + e2.getMessage());
            return "";
        } catch (Exception e3) {
            b.b(f8594a, "Exception: " + e3.getMessage());
            return "";
        }
    }

    @Deprecated
    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return Base64.encodeToString(encrypt(str, str2.getBytes("UTF-8")), 0);
        } catch (UnsupportedEncodingException e2) {
            b.b(f8594a, "UnsupportedEncodingException: " + e2.getMessage());
            return "";
        }
    }

    public static String encryptNew(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return Base64.encodeToString(encryptNew(str, str2.getBytes("UTF-8")), 0);
        } catch (UnsupportedEncodingException e2) {
            b.b(f8594a, "UnsupportedEncodingException: " + e2.getMessage());
            return "";
        }
    }

    private static byte[] a(String str, byte[] bArr, boolean z) {
        byte[] bArr2 = new byte[0];
        if (TextUtils.isEmpty(str) || bArr == null) {
            b.b(f8594a, "alias or content is null");
            return bArr2;
        }
        if (!a()) {
            b.b(f8594a, "sdk version is too low");
            return bArr2;
        }
        PublicKey b2 = b(str, z);
        if (b2 == null) {
            b.b(f8594a, "Public key is null");
            return bArr2;
        }
        try {
            Cipher cipher = Cipher.getInstance(c);
            cipher.init(1, b2, new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT));
            return cipher.doFinal(bArr);
        } catch (InvalidAlgorithmParameterException e2) {
            b.b(f8594a, "InvalidAlgorithmParameterException: " + e2.getMessage());
            return bArr2;
        } catch (InvalidKeyException e3) {
            b.b(f8594a, "InvalidKeyException: " + e3.getMessage());
            return bArr2;
        } catch (NoSuchAlgorithmException e4) {
            b.b(f8594a, "NoSuchAlgorithmException: " + e4.getMessage());
            return bArr2;
        } catch (BadPaddingException e5) {
            b.b(f8594a, "BadPaddingException: " + e5.getMessage());
            return bArr2;
        } catch (IllegalBlockSizeException e6) {
            b.b(f8594a, "IllegalBlockSizeException: " + e6.getMessage());
            return bArr2;
        } catch (NoSuchPaddingException e7) {
            b.b(f8594a, "NoSuchPaddingException: " + e7.getMessage());
            return bArr2;
        } catch (Exception e8) {
            b.b(f8594a, "Exception: " + e8.getMessage());
            return bArr2;
        }
    }

    @Deprecated
    public static byte[] encrypt(String str, byte[] bArr) {
        return a(str, bArr, false);
    }

    public static byte[] encryptNew(String str, byte[] bArr) {
        return a(str, bArr, true);
    }

    private static boolean b(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance(b);
            keyStore.load(null);
            return keyStore.getKey(str, null) != null;
        } catch (IOException e2) {
            b.b(f8594a, "IOException: " + e2.getMessage());
            return false;
        } catch (KeyStoreException e3) {
            b.b(f8594a, "KeyStoreException: " + e3.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e4) {
            b.b(f8594a, "NoSuchAlgorithmException: " + e4.getMessage());
            return false;
        } catch (UnrecoverableKeyException e5) {
            b.b(f8594a, "UnrecoverableKeyException: " + e5.getMessage());
            return false;
        } catch (CertificateException e6) {
            b.b(f8594a, "CertificateException: " + e6.getMessage());
            return false;
        } catch (Exception e7) {
            b.b(f8594a, "Exception: " + e7.getMessage());
            return false;
        }
    }

    public static byte[] decrpytNew(String str, byte[] bArr) {
        return decrpyt(str, bArr);
    }

    @Deprecated
    public static byte[] decrpyt(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!a()) {
                b.b(f8594a, "sdk version is too low");
                return bArr2;
            }
            PrivateKey a2 = a(str);
            if (a2 == null) {
                b.b(f8594a, "Private key is null");
                return bArr2;
            }
            try {
                Cipher cipher = Cipher.getInstance(c);
                cipher.init(2, a2, new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT));
                return cipher.doFinal(bArr);
            } catch (InvalidAlgorithmParameterException e2) {
                b.b(f8594a, "InvalidAlgorithmParameterException: " + e2.getMessage());
                return bArr2;
            } catch (InvalidKeyException e3) {
                b.b(f8594a, "InvalidKeyException: " + e3.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e4) {
                b.b(f8594a, "NoSuchAlgorithmException: " + e4.getMessage());
                return bArr2;
            } catch (BadPaddingException e5) {
                b.b(f8594a, "BadPaddingException: " + e5.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e6) {
                b.b(f8594a, "IllegalBlockSizeException: " + e6.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e7) {
                b.b(f8594a, "NoSuchPaddingException: " + e7.getMessage());
                return bArr2;
            } catch (Exception e8) {
                b.b(f8594a, "Exception: " + e8.getMessage());
                return bArr2;
            }
        }
        b.b(f8594a, "alias or encrypted content is null");
        return bArr2;
    }

    private static KeyPair a(String str, boolean z) {
        synchronized (RSAEncryptKS.class) {
            KeyPair keyPair = null;
            if (b(str)) {
                b.b(f8594a, "Key pair exits");
                return null;
            }
            b.c(f8594a, "generate key pair.");
            try {
                try {
                    try {
                        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JceNames.RSA, b);
                        if (!z) {
                            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 2).setDigests("SHA-256", "SHA-512").setEncryptionPaddings("OAEPPadding").setKeySize(2048).build());
                        } else {
                            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 2).setDigests("SHA-256", "SHA-512").setEncryptionPaddings("OAEPPadding").setKeySize(3072).build());
                        }
                        keyPair = keyPairGenerator.generateKeyPair();
                    } catch (NoSuchAlgorithmException e2) {
                        b.b(f8594a, "NoSuchAlgorithmException: " + e2.getMessage());
                    }
                } catch (Exception e3) {
                    b.b(f8594a, "Exception: " + e3.getMessage());
                }
            } catch (InvalidAlgorithmParameterException e4) {
                b.b(f8594a, "InvalidAlgorithmParameterException: " + e4.getMessage());
            } catch (NoSuchProviderException e5) {
                b.b(f8594a, "NoSuchProviderException: " + e5.getMessage());
            }
            return keyPair;
        }
    }

    private static PrivateKey a(String str) {
        if (!b(str)) {
            return null;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance(b);
            keyStore.load(null);
            return (PrivateKey) keyStore.getKey(str, null);
        } catch (IOException e2) {
            b.b(f8594a, "IOException: " + e2.getMessage());
            return null;
        } catch (KeyStoreException e3) {
            b.b(f8594a, "KeyStoreException: " + e3.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e4) {
            b.b(f8594a, "NoSuchAlgorithmException: " + e4.getMessage());
            return null;
        } catch (UnrecoverableKeyException e5) {
            b.b(f8594a, "UnrecoverableKeyException: " + e5.getMessage());
            return null;
        } catch (CertificateException e6) {
            b.b(f8594a, "CertificateException: " + e6.getMessage());
            return null;
        } catch (Exception e7) {
            b.b(f8594a, "Exception: " + e7.getMessage());
            return null;
        }
    }
}

package defpackage;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes8.dex */
public class trp {
    private trp() {
    }

    private static KeyStore e() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore;
        } catch (IOException unused) {
            tos.e("KeystoreUtil", "getKeyStore IOException");
            return null;
        } catch (KeyStoreException unused2) {
            tos.e("KeystoreUtil", "getKeyStore KeyStoreException");
            return null;
        } catch (NoSuchAlgorithmException unused3) {
            tos.e("KeystoreUtil", "getKeyStore NoSuchAlgorithmException");
            return null;
        } catch (CertificateException unused4) {
            tos.e("KeystoreUtil", "getKeyStore CertificateException");
            return null;
        }
    }

    private static KeyStore e(Context context, String str) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore;
        synchronized (trp.class) {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (!keyStore.containsAlias(str)) {
                b(context, str);
            }
        }
        return keyStore;
    }

    private static void b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JceNames.RSA, "AndroidKeyStore");
            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 3).setDigests("SHA-256", "SHA-512").setEncryptionPaddings("PKCS1Padding").build());
            keyPairGenerator.generateKeyPair();
            tos.b("KeystoreUtil", "AboveApi23 generateKeyStoreKey success!");
        } catch (InvalidAlgorithmParameterException unused) {
            tos.e("KeystoreUtil", "createNewKeys InvalidAlgorithmParameterException");
        } catch (NoSuchAlgorithmException unused2) {
            tos.e("KeystoreUtil", "createNewKeys NoSuchAlgorithmException");
        } catch (NoSuchProviderException unused3) {
            tos.e("KeystoreUtil", "createNewKeys NoSuchProviderException");
        }
    }

    public static String e(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                Key key = e(context, str2).getKey(str2, null);
                if (key instanceof PrivateKey) {
                    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                    cipher.init(2, (PrivateKey) key);
                    return new String(cipher.doFinal(trh.a(str)), "UTF-8");
                }
            } catch (IOException unused) {
                tos.e("KeystoreUtil", "decryptString IOException");
            } catch (InvalidKeyException unused2) {
                tos.e("KeystoreUtil", "decryptString InvalidKeyException");
            } catch (KeyStoreException unused3) {
                tos.e("KeystoreUtil", "decryptString KeyStoreException");
            } catch (NoSuchAlgorithmException unused4) {
                tos.e("KeystoreUtil", "decryptString NoSuchAlgorithmException");
            } catch (UnrecoverableKeyException unused5) {
                tos.e("KeystoreUtil", "decryptString UnrecoverableKeyException");
            } catch (CertificateException unused6) {
                tos.e("KeystoreUtil", "decryptString CertificateException");
            } catch (BadPaddingException unused7) {
                tos.e("KeystoreUtil", "decryptString BadPaddingException");
            } catch (IllegalBlockSizeException unused8) {
                tos.e("KeystoreUtil", "decryptString IllegalBlockSizeException");
            } catch (NoSuchPaddingException unused9) {
                tos.e("KeystoreUtil", "decryptString NoSuchPaddingException");
            }
        }
        return "";
    }

    public static SecretKey d() {
        KeyStore e = e();
        if (e == null) {
            tos.d("KeystoreUtil", "getSecretKeyInternal:keyStore is null");
            return null;
        }
        try {
            if (e.containsAlias("com.huawei.wearengine.repository.sign")) {
                Key key = e.getKey("com.huawei.wearengine.repository.sign", null);
                if (key instanceof SecretKey) {
                    tos.a("KeystoreUtil", "getSecretKeyForRepositorySign: from keyStore ");
                    return (SecretKey) key;
                }
            }
            return c();
        } catch (InvalidAlgorithmParameterException unused) {
            tos.e("KeystoreUtil", "getSecretKeyForRepositorySign InvalidAlgorithmParameterException");
            return null;
        } catch (KeyStoreException unused2) {
            tos.e("KeystoreUtil", "getSecretKeyForRepositorySign KeyStoreException");
            return null;
        } catch (NoSuchAlgorithmException unused3) {
            tos.e("KeystoreUtil", "getSecretKeyForRepositorySign NoSuchAlgorithmException");
            return null;
        } catch (NoSuchProviderException unused4) {
            tos.e("KeystoreUtil", "getSecretKeyForRepositorySign NoSuchProviderException");
            return null;
        } catch (UnrecoverableKeyException unused5) {
            tos.e("KeystoreUtil", "getSecretKeyForRepositorySign UnrecoverableKeyException");
            return null;
        }
    }

    private static SecretKey c() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder("com.huawei.wearengine.repository.sign", 4).build());
        SecretKey generateKey = keyGenerator.generateKey();
        tos.a("KeystoreUtil", "getSecretKeyForRepositorySign: from KeyGenerator ");
        return generateKey;
    }
}

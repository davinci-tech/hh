package health.compact.a;

import android.security.keystore.KeyGenParameterSpec;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.ProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes.dex */
public class KeyStoreUtils {
    public static final boolean b = true;
    private static volatile SecretKey d;
    private static final Object e = new Object();
    private static final WhiteBoxManager c = WhiteBoxManager.d();

    private KeyStoreUtils() {
    }

    public static String b(String str) {
        if (!b) {
            return Base64.a(c.b(str));
        }
        return e(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String e(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (!b) {
            return Base64.a(c.b(new String(bArr, StandardCharsets.UTF_8)));
        }
        SecretKey a2 = a();
        if (a2 == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_KeyStoreUtils", "encrypt:secretKey is null");
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, a2);
            return Base64.a(cipher.getIV()).concat(Base64.a(cipher.doFinal(bArr)));
        } catch (InvalidKeyException e2) {
            e = e2;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (ProviderException unused) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ProviderException");
            return null;
        } catch (BadPaddingException e4) {
            e = e4;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (IllegalBlockSizeException e5) {
            e = e5;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (NoSuchPaddingException e6) {
            e = e6;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (Exception unused2) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt Exception");
            return null;
        }
    }

    public static byte[] e(String str) {
        if (!b) {
            return c.a(Base64.e(str));
        }
        if (health.compact.a.utils.StringUtils.g(str) || str.length() <= 24) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_KeyStoreUtils", "decrypt:encryptText is invalid");
            return null;
        }
        SecretKey a2 = a();
        if (a2 == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_KeyStoreUtils", "decrypt:secretKey is null");
            return null;
        }
        String substring = str.substring(0, 24);
        String substring2 = str.substring(24);
        byte[] e2 = Base64.e(substring);
        byte[] e3 = Base64.e(substring2);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(2, a2, new IvParameterSpec(e2));
            return cipher.doFinal(e3);
        } catch (InvalidAlgorithmParameterException e4) {
            e = e4;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt exception text:", Integer.valueOf(str.length()), ",vectorByte:", Integer.valueOf(e2.length), ",encryptBytes:", Integer.valueOf(e3.length));
            return null;
        } catch (InvalidKeyException e5) {
            e = e5;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt exception text:", Integer.valueOf(str.length()), ",vectorByte:", Integer.valueOf(e2.length), ",encryptBytes:", Integer.valueOf(e3.length));
            return null;
        } catch (NoSuchAlgorithmException e6) {
            e = e6;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt exception text:", Integer.valueOf(str.length()), ",vectorByte:", Integer.valueOf(e2.length), ",encryptBytes:", Integer.valueOf(e3.length));
            return null;
        } catch (ProviderException unused) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ProviderException");
            return null;
        } catch (BadPaddingException e7) {
            e = e7;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt exception text:", Integer.valueOf(str.length()), ",vectorByte:", Integer.valueOf(e2.length), ",encryptBytes:", Integer.valueOf(e3.length));
            return null;
        } catch (IllegalBlockSizeException e8) {
            e = e8;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt exception text:", Integer.valueOf(str.length()), ",vectorByte:", Integer.valueOf(e2.length), ",encryptBytes:", Integer.valueOf(e3.length));
            return null;
        } catch (NoSuchPaddingException e9) {
            e = e9;
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt exception text:", Integer.valueOf(str.length()), ",vectorByte:", Integer.valueOf(e2.length), ",encryptBytes:", Integer.valueOf(e3.length));
            return null;
        } catch (Exception unused2) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt Exception");
            return null;
        }
    }

    private static SecretKey a() {
        if (d != null) {
            return d;
        }
        synchronized (e) {
            if (d != null) {
                return d;
            }
            d = d();
            return d;
        }
    }

    private static SecretKey d() {
        Object e2;
        SecretKey secretKey;
        KeyStore c2 = c();
        if (c2 == null) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_KeyStoreUtils", "getSecretKeyInternal:keyStore is null");
            return null;
        }
        String packageName = BaseApplication.getContext().getPackageName();
        try {
            if (c2.containsAlias(packageName)) {
                Key key = c2.getKey(packageName, null);
                if (!(key instanceof SecretKey)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_KeyStoreUtils", "getSecretKeyInternal not instance of SecretKey");
                    return null;
                }
                secretKey = (SecretKey) key;
                try {
                    com.huawei.hwlogsmodel.LogUtil.a("R_KeyStoreUtils", "getSecretKeyInternal: from keyStore ");
                    return secretKey;
                } catch (InvalidAlgorithmParameterException e3) {
                    e2 = e3;
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e2.getClass().getSimpleName());
                    return secretKey;
                } catch (KeyStoreException e4) {
                    e2 = e4;
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e2.getClass().getSimpleName());
                    return secretKey;
                } catch (NoSuchAlgorithmException e5) {
                    e2 = e5;
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e2.getClass().getSimpleName());
                    return secretKey;
                } catch (NoSuchProviderException e6) {
                    e2 = e6;
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e2.getClass().getSimpleName());
                    return secretKey;
                } catch (UnrecoverableKeyException e7) {
                    e2 = e7;
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e2.getClass().getSimpleName());
                    return secretKey;
                }
            }
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(packageName, 3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").build());
            SecretKey generateKey = keyGenerator.generateKey();
            com.huawei.hwlogsmodel.LogUtil.a("R_KeyStoreUtils", "getSecretKeyInternal: from KeyGenerator ");
            return generateKey;
        } catch (InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableKeyException e8) {
            e2 = e8;
            secretKey = null;
        }
    }

    private static KeyStore c() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_KeyStoreUtils", "getKeyStore ", e2.getClass().getSimpleName());
            return null;
        }
    }
}

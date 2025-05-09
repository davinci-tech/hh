package defpackage;

import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
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

/* loaded from: classes3.dex */
public class bmy {

    /* renamed from: a, reason: collision with root package name */
    private static volatile SecretKey f440a;
    private static final Object b = new Object();

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("R_KeyStoreUtils", "encrypt:plainText is null");
            return "";
        }
        return a(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        SecretKey a2 = a();
        if (a2 == null) {
            ReleaseLogUtil.a("R_KeyStoreUtils", "encrypt:secretKey is null");
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, a2);
            return bnc.a(cipher.getIV()).concat(bnc.a(cipher.doFinal(bArr)));
        } catch (InvalidKeyException e) {
            e = e;
            ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (ProviderException unused) {
            ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ProviderException");
            return null;
        } catch (BadPaddingException e3) {
            e = e3;
            ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (IllegalBlockSizeException e4) {
            e = e4;
            ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (NoSuchPaddingException e5) {
            e = e5;
            ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt ", e.getClass().getSimpleName());
            return null;
        } catch (Exception unused2) {
            ReleaseLogUtil.c("R_KeyStoreUtils", "encrypt Exception");
            return null;
        }
    }

    public static byte[] c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.c("R_KeyStoreUtils", "encryptData data is empty.");
            return null;
        }
        try {
            byte[] d = bgv.d(16);
            byte[] d2 = bgv.d(16);
            byte[] e = bgv.e("AES/CBC/PKCS5Padding", bArr, d, d2);
            ByteBuffer allocate = ByteBuffer.allocate(e.length + 32);
            allocate.put(d).put(d2).put(e);
            LogUtil.c("R_KeyStoreUtils", "encryptData success");
            return allocate.array();
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.e("R_KeyStoreUtils", "encryptData : ", ExceptionUtils.d(e2));
            LogUtil.c("R_KeyStoreUtils", "encryptData fail, please check.");
            return null;
        }
    }

    public static byte[] c(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 24) {
            ReleaseLogUtil.a("R_KeyStoreUtils", "decrypt:encryptText is invalid");
            return null;
        }
        SecretKey a2 = a();
        if (a2 == null) {
            ReleaseLogUtil.a("R_KeyStoreUtils", "decrypt:secretKey is null");
            return null;
        }
        try {
            String substring = str.substring(0, 24);
            String substring2 = str.substring(24);
            byte[] e = bnc.e(substring);
            byte[] e2 = bnc.e(substring2);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(2, a2, new IvParameterSpec(e));
            return cipher.doFinal(e2);
        } catch (InvalidAlgorithmParameterException e3) {
            e = e3;
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            return null;
        } catch (InvalidKeyException e4) {
            e = e4;
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            return null;
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            return null;
        } catch (ProviderException unused) {
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ProviderException");
            return null;
        } catch (BadPaddingException e6) {
            e = e6;
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            return null;
        } catch (IllegalBlockSizeException e7) {
            e = e7;
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            return null;
        } catch (NoSuchPaddingException e8) {
            e = e8;
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt ", e.getClass().getSimpleName());
            return null;
        } catch (Exception unused2) {
            ReleaseLogUtil.c("R_KeyStoreUtils", "decrypt Exception");
            return null;
        }
    }

    private static SecretKey a() {
        if (f440a != null) {
            return f440a;
        }
        synchronized (b) {
            if (f440a != null) {
                return f440a;
            }
            f440a = d();
            return f440a;
        }
    }

    private static SecretKey d() {
        Object e;
        SecretKey secretKey;
        KeyStore c = c();
        if (c == null) {
            ReleaseLogUtil.a("R_KeyStoreUtils", "getSecretKeyInternal:keyStore is null");
            return null;
        }
        String packageName = BaseApplication.e().getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            ReleaseLogUtil.a("R_KeyStoreUtils", "getSecretKeyInternal:PackageName is null");
            return null;
        }
        try {
            if (c.containsAlias(packageName)) {
                Key key = c.getKey(packageName, null);
                if (!(key instanceof SecretKey)) {
                    ReleaseLogUtil.a("R_KeyStoreUtils", "getSecretKeyInternal not instance of SecretKey");
                    return null;
                }
                secretKey = (SecretKey) key;
                try {
                    LogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal: from keyStore ");
                    return secretKey;
                } catch (InvalidAlgorithmParameterException e2) {
                    e = e2;
                    ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e.getClass().getSimpleName());
                    return secretKey;
                } catch (KeyStoreException e3) {
                    e = e3;
                    ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e.getClass().getSimpleName());
                    return secretKey;
                } catch (NoSuchAlgorithmException e4) {
                    e = e4;
                    ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e.getClass().getSimpleName());
                    return secretKey;
                } catch (NoSuchProviderException e5) {
                    e = e5;
                    ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e.getClass().getSimpleName());
                    return secretKey;
                } catch (UnrecoverableKeyException e6) {
                    e = e6;
                    ReleaseLogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal ", e.getClass().getSimpleName());
                    return secretKey;
                }
            }
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(packageName, 3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").build());
            SecretKey generateKey = keyGenerator.generateKey();
            LogUtil.c("R_KeyStoreUtils", "getSecretKeyInternal: from KeyGenerator ");
            return generateKey;
        } catch (InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableKeyException e7) {
            e = e7;
            secretKey = null;
        }
    }

    private static KeyStore c() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            ReleaseLogUtil.c("R_KeyStoreUtils", "getKeyStore ", e.getClass().getSimpleName());
            return null;
        }
    }
}

package com.huawei.healthcloud.plugintrack.golf.cloud;

import com.huawei.health.device.open.data.model.HealthData;
import health.compact.a.util.LogUtil;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes4.dex */
public class GolfEncryptionUtils {
    private static final Object LOCK = new Object();
    private static final String TAG = "GolfEncryptionUtils";
    private static GolfEncryptionUtils instance;
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    private GolfEncryptionUtils() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JceNames.RSA);
            keyPairGenerator.initialize(HealthData.ECG, new SecureRandom());
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            this.privateKey = (RSAPrivateKey) generateKeyPair.getPrivate();
            this.publicKey = (RSAPublicKey) generateKeyPair.getPublic();
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.e(TAG, "GolfEncryptionUtils: No Such Encryption Algorithm");
        }
    }

    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(this.publicKey.getEncoded());
    }

    public String decryption(String str) {
        try {
            byte[] decode = Base64.getDecoder().decode(str);
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
            cipher.init(2, this.privateKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT));
            return new String(cipher.doFinal(decode), StandardCharsets.UTF_8);
        } catch (InvalidAlgorithmParameterException unused) {
            LogUtil.e(TAG, "GolfEncryptionUtils: InvalidParameter");
            return "";
        } catch (InvalidKeyException unused2) {
            LogUtil.e(TAG, "GolfEncryptionUtils: Invalid Key Exception");
            return "";
        } catch (NoSuchAlgorithmException unused3) {
            LogUtil.e(TAG, "GolfEncryptionUtils: No Such Algorithm Exception");
            return "";
        } catch (BadPaddingException unused4) {
            LogUtil.e(TAG, "GolfEncryptionUtils: Bad Padding Exception");
            return "";
        } catch (IllegalBlockSizeException unused5) {
            LogUtil.e(TAG, "GolfEncryptionUtils: Illegal Block Size Exception");
            return "";
        } catch (NoSuchPaddingException unused6) {
            LogUtil.e(TAG, "GolfEncryptionUtils: No Such Padding Exception");
            return "";
        }
    }

    public static GolfEncryptionUtils getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    return new GolfEncryptionUtils();
                }
            }
        }
        return instance;
    }
}

package com.huawei.operation.h5pro.jsmodules.crypto;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.common.util.Base64Utils;
import health.compact.a.util.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class CryptoOperation {
    private static final int HEADER_LENGTH = 9;
    private static final int INVALID_SIZE = 0;
    private static final int IV_LENGTH = 12;
    private static final int MIN_KEY_SIZE = 1024;
    private static final int READ_OFFSET = 0;
    private static final String TAG = "H5PRO_CryptoOperation";

    CryptoOperation() {
    }

    public JSONObject generateKeyPair(String str, int i) {
        KeyPair generateKeyPair;
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str) || i < 1024) {
            LogUtil.c(TAG, "generateKeyPair: Invalid algorithm or keySize");
            return jSONObject;
        }
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str);
            keyPairGenerator.initialize(i, new SecureRandom());
            generateKeyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException | JSONException e) {
            LogUtil.e(TAG, "generateKeyPair: exception -> " + e.getMessage());
        }
        if (generateKeyPair == null) {
            return jSONObject;
        }
        PrivateKey privateKey = generateKeyPair.getPrivate();
        if (privateKey instanceof RSAPrivateKey) {
            jSONObject.put("privateKey", Base64Utils.encode(((RSAPrivateKey) privateKey).getEncoded()));
        }
        PublicKey publicKey = generateKeyPair.getPublic();
        if (publicKey instanceof RSAPublicKey) {
            jSONObject.put("publicKey", Base64Utils.encode(((RSAPublicKey) publicKey).getEncoded()));
        }
        return jSONObject;
    }

    public JSONObject decryptByPrivateKey(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.c(TAG, "decryptByPrivateKey: Invalid parameter");
            return jSONObject;
        }
        try {
            Cipher cipher = Cipher.getInstance(str);
            Key parseKey = parseKey(str2, str, false);
            if (str.startsWith("RSA/ECB/OAEP")) {
                cipher.init(2, parseKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT));
            } else {
                cipher.init(2, parseKey);
            }
            jSONObject.put("plaintext", new String(cipher.doFinal(Base64Utils.decode(str3)), StandardCharsets.UTF_8));
        } catch (GeneralSecurityException | JSONException e) {
            LogUtil.e(TAG, "decryptByPrivateKey: exception -> " + e.getMessage());
        }
        return jSONObject;
    }

    private Key parseKey(String str, String str2, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                if (str2.contains("/")) {
                    str2 = str2.split("/")[0];
                }
                KeyFactory keyFactory = KeyFactory.getInstance(str2);
                byte[] decode = Base64Utils.decode(str);
                if (z) {
                    return keyFactory.generatePublic(new X509EncodedKeySpec(decode));
                }
                return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                LogUtil.e(TAG, "EncryptionOperation_getRsaKey: exception -> " + e.getMessage());
            }
        }
        return null;
    }

    public JSONObject aesDecrypt(String str, String str2, String str3, String str4) {
        int i;
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.c(TAG, "aesDecrypt: Invalid parameter");
            return jSONObject;
        }
        if ("DECRYPT_MODE".equalsIgnoreCase(str4)) {
            i = 2;
        } else {
            i = "UNWRAP_MODE".equalsIgnoreCase(str4) ? 4 : -1;
        }
        if (i == -1) {
            LogUtil.c(TAG, "aesDecrypt: Invalid opMode -> " + str4);
            return jSONObject;
        }
        try {
            Cipher cipher = Cipher.getInstance(str);
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), str);
            byte[] decode = Base64Utils.decode(str3);
            byte[] copyOfRange = Arrays.copyOfRange(decode, 9, decode.length);
            cipher.init(i, secretKeySpec, new IvParameterSpec(Arrays.copyOf(copyOfRange, 12)));
            jSONObject.put("plaintext", new String(cipher.doFinal(Arrays.copyOfRange(copyOfRange, 12, copyOfRange.length)), StandardCharsets.UTF_8));
        } catch (GeneralSecurityException | JSONException e) {
            LogUtil.e(TAG, "aesDecrypt: exception -> " + e.getMessage());
        }
        return jSONObject;
    }

    public JSONObject decryptOnlineContent(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.c(TAG, "decryptOnlineContent: Invalid parameter");
            return jSONObject;
        }
        byte[] contentByUrl = getContentByUrl(str3);
        if (contentByUrl == null) {
            LogUtil.e(TAG, "get origin content failed");
            return jSONObject;
        }
        try {
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(2, new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), str), new IvParameterSpec(Arrays.copyOf(contentByUrl, 12)));
            jSONObject.put("base64", Base64Utils.encode(cipher.doFinal(Arrays.copyOfRange(contentByUrl, 12, contentByUrl.length))));
        } catch (GeneralSecurityException | JSONException e) {
            LogUtil.e(TAG, "decryptOnlineContent: exception -> " + e.getMessage());
        }
        return jSONObject;
    }

    private byte[] getContentByUrl(String str) {
        try {
            URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    int contentLength = openConnection.getContentLength();
                    if (inputStream != null && contentLength > 0 && contentLength <= 8388608) {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        LogUtil.d(TAG, "download success");
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        return byteArray;
                    }
                    byteArrayOutputStream.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return null;
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            LogUtil.e(TAG, "download error: " + e.getMessage());
            return null;
        }
    }
}

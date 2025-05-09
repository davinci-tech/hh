package com.huawei.wisecloud.drmclient.client;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import com.huawei.wisecloud.drmclient.R$string;
import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.interfaces.SecurityLabel;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.license.HwDrmLicense;
import com.huawei.wisecloud.drmclient.license.HwDrmLicenseImpl;
import com.huawei.wisecloud.drmclient.license.entry.CommonHeaderEntry;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;
import com.huawei.wisecloud.drmclient.license.entry.CommonPayloadEntry;
import com.huawei.wisecloud.drmclient.license.entry.PayloadLicenseEntry;
import com.huawei.wisecloud.drmclient.license.verify.LicenseVerifierFactory;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import com.huawei.wisecloud.drmclient.utils.AesGcmUtils;
import com.huawei.wisecloud.drmclient.utils.Base64Util;
import com.huawei.wisecloud.drmclient.utils.DigestUtil;
import com.huawei.wisecloud.drmclient.utils.FileUtil;
import com.huawei.wisecloud.drmclient.utils.HexUtil;
import com.huawei.wisecloud.drmclient.utils.JsonUtil;
import com.huawei.wisecloud.drmclient.utils.JwtUtil;
import com.huawei.wisecloud.drmclient.utils.RSASignAlgo;
import com.huawei.wisecloud.drmclient.utils.RSAUtil;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.security.auth.x500.X500Principal;
import org.eclipse.californium.elements.util.JceNames;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HwDrmClient implements HwDrmClientProvider {
    private static final String ALIAS = "ThemeManager";
    public static final int DEFAULT_KEY_SIZE = 2048;
    private static final String LICENSE_PATH = "license_path";
    private static final int SERIAL_NUMBER = 1337;
    private static final String TAG = "HWDRMClient";
    private static File file;
    private static volatile HwDrmClient mInstance;
    private static String workPath;
    private Map<String, HwDrmLicense> licenseMap = new ConcurrentHashMap();
    private Context mContext;
    private PrivateKey mDrmPrivateKey;
    private PublicKey mDrmPublicKey;
    private PublicKey mServerPubKey;

    public native byte[] decryptAesGcm(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    public native byte[] genDrmReqSignature(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5);

    static {
        try {
            System.loadLibrary("drmlib");
        } catch (UnsatisfiedLinkError e) {
            HwDrmLog.e(TAG, "UnsatisfiedLinkError when loadLibrary." + HwDrmLog.printException((Error) e));
        }
    }

    public void setmServerPubKey(PublicKey publicKey) {
        this.mServerPubKey = publicKey;
    }

    private HwDrmClient(Context context) {
        this.mContext = context;
        workPath = file.getPath();
        initKeyStore();
    }

    public static HwDrmClient newHWDRMClient(Context context) {
        initLicensePath(context);
        if (mInstance == null) {
            synchronized (HwDrmClient.class) {
                if (mInstance == null) {
                    mInstance = new HwDrmClient(context);
                }
            }
        }
        return mInstance;
    }

    private static void initLicensePath(Context context) {
        File dir = context.getDir(LICENSE_PATH, 0);
        file = dir;
        if (dir.exists() || file.mkdirs()) {
            return;
        }
        HwDrmLog.e(TAG, "mWorkPath mkdirs failed in NewHWDRMClient");
    }

    private KeyPair generateRSAKeyPair() {
        HwDrmLog.i(TAG, "generateRSAKeyPair enter");
        KeyPair keyPair = null;
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
            boolean z = true;
            gregorianCalendar2.add(1, 20);
            KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(ALIAS, 7).setKeySize(2048).setUserAuthenticationRequired(false).setCertificateSubject(new X500Principal("CN=ThemeManager")).setDigests("SHA-256", "SHA-1").setCertificateNotBefore(gregorianCalendar.getTime()).setCertificateNotAfter(gregorianCalendar2.getTime()).setSignaturePaddings("PKCS1").setEncryptionPaddings("OAEPPadding").build();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JceNames.RSA, "AndroidKeyStore");
            keyPairGenerator.initialize(build);
            keyPair = keyPairGenerator.generateKeyPair();
            this.mDrmPrivateKey = keyPair.getPrivate();
            this.mDrmPublicKey = keyPair.getPublic();
            StringBuilder sb = new StringBuilder("generateRSAKeyPair mDrmPublicKey is null ");
            sb.append(this.mDrmPublicKey == null);
            HwDrmLog.e(TAG, sb.toString());
            StringBuilder sb2 = new StringBuilder("generateRSAKeyPair mDrmPrivateKey is null ");
            if (this.mDrmPrivateKey != null) {
                z = false;
            }
            sb2.append(z);
            HwDrmLog.e(TAG, sb2.toString());
        } catch (RuntimeException e) {
            HwDrmLog.i(TAG, "RuntimeException is caught in GenerateRSAKeyPair " + HwDrmLog.printException((Exception) e));
        } catch (InvalidAlgorithmParameterException e2) {
            HwDrmLog.e(TAG, "InvalidAlgorithmParameterException is catching in GenerateRSAKeyPair" + HwDrmLog.printException((Exception) e2));
        } catch (NoSuchAlgorithmException e3) {
            HwDrmLog.e(TAG, "NoSuchAlgorithmException is catching in GenerateRSAKeyPair" + HwDrmLog.printException((Exception) e3));
        } catch (NoSuchProviderException e4) {
            HwDrmLog.e(TAG, "NoSuchProviderException is catching in GenerateRSAKeyPair" + HwDrmLog.printException((Exception) e4));
        }
        return keyPair;
    }

    private void initKeyStore() {
        if (loadCertificate() != null) {
            getPublicKeyAndPrivateKey();
            if (this.mDrmPublicKey != null && this.mDrmPrivateKey != null) {
                HwDrmLog.i(TAG, "publicKey and privateKey is created in InitKeyStore");
                return;
            }
        }
        generateRSAKeyPair();
        checkPublicKeyAndPrivateKey();
    }

    private void checkPublicKeyAndPrivateKey() {
        HwDrmLog.i(TAG, "checkPublicKeyAndPrivateKey enter");
        if (this.mDrmPublicKey != null && this.mDrmPrivateKey != null) {
            HwDrmLog.i(TAG, "publicKey and privateKey is created in InitKeyStore");
            return;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (Build.VERSION.SDK_INT >= 28) {
                Key key = keyStore.getKey(ALIAS, null);
                if (key instanceof PrivateKey) {
                    this.mDrmPrivateKey = (PrivateKey) key;
                }
                Certificate certificate = keyStore.getCertificate(ALIAS);
                if (certificate != null) {
                    this.mDrmPublicKey = this.mDrmPrivateKey != null ? certificate.getPublicKey() : null;
                }
            } else {
                KeyStore.Entry entry = keyStore.getEntry(ALIAS, null);
                if (entry instanceof KeyStore.PrivateKeyEntry) {
                    this.mDrmPrivateKey = ((KeyStore.PrivateKeyEntry) entry).getPrivateKey();
                    Certificate certificate2 = ((KeyStore.PrivateKeyEntry) entry).getCertificate();
                    if (certificate2 != null) {
                        this.mDrmPublicKey = this.mDrmPrivateKey != null ? certificate2.getPublicKey() : null;
                    }
                }
            }
            StringBuilder sb = new StringBuilder("checkPublicKeyAndPrivateKey mDrmPublicKey is null ");
            sb.append(this.mDrmPublicKey == null);
            HwDrmLog.e(TAG, sb.toString());
            StringBuilder sb2 = new StringBuilder("checkPublicKeyAndPrivateKey mDrmPrivateKey is null ");
            sb2.append(this.mDrmPrivateKey == null);
            HwDrmLog.e(TAG, sb2.toString());
        } catch (IOException e) {
            HwDrmLog.e(TAG, "IOException is catching in checkPublicKeyAndPrivateKey" + HwDrmLog.printException((Exception) e));
        } catch (UnsupportedOperationException e2) {
            HwDrmLog.e(TAG, "UnsupportedOperationException is catching in checkPublicKeyAndPrivateKey" + HwDrmLog.printException((Exception) e2));
        } catch (KeyStoreException e3) {
            HwDrmLog.e(TAG, "KeyStoreException is catching in checkPublicKeyAndPrivateKey" + HwDrmLog.printException((Exception) e3));
        } catch (NoSuchAlgorithmException e4) {
            HwDrmLog.e(TAG, "NoSuchAlgorithmException is catching in checkPublicKeyAndPrivateKey" + HwDrmLog.printException((Exception) e4));
        } catch (UnrecoverableEntryException e5) {
            HwDrmLog.e(TAG, "UnrecoverableEntryException is catching in checkPublicKeyAndPrivateKey" + HwDrmLog.printException((Exception) e5));
        } catch (CertificateException e6) {
            HwDrmLog.e(TAG, "CertificateException is catching in checkPublicKeyAndPrivateKey" + HwDrmLog.printException((Exception) e6));
        } catch (Exception e7) {
            HwDrmLog.e(TAG, "Exception is catching in checkPublicKeyAndPrivateKey" + HwDrmLog.printException(e7));
        }
    }

    private Certificate loadCertificate() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.getCertificate(ALIAS);
        } catch (IOException e) {
            e = e;
            HwDrmLog.e(TAG, "load certificate  exception : " + HwDrmLog.printException(e));
            return null;
        } catch (GeneralSecurityException e2) {
            e = e2;
            HwDrmLog.e(TAG, "load certificate  exception : " + HwDrmLog.printException(e));
            return null;
        } catch (Exception e3) {
            HwDrmLog.e(TAG, "load Exception  : " + HwDrmLog.printException(e3));
            return null;
        }
    }

    private void getPublicKeyAndPrivateKey() {
        HwDrmLog.i(TAG, "getPublicKeyAndPrivateKey enter");
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyStore.Entry entry = keyStore.getEntry(ALIAS, null);
            if (entry == null) {
                HwDrmLog.e(TAG, "the entry is null in GetPublicKey");
                return;
            }
            if (!(entry instanceof KeyStore.PrivateKeyEntry)) {
                HwDrmLog.e(TAG, "the entry is not instanceof KeyStore.PrivateKeyEntry in GetPublicKey");
                return;
            }
            this.mDrmPublicKey = ((KeyStore.PrivateKeyEntry) entry).getCertificate().getPublicKey();
            this.mDrmPrivateKey = ((KeyStore.PrivateKeyEntry) entry).getPrivateKey();
            StringBuilder sb = new StringBuilder("getPublicKeyAndPrivateKey mDrmPublicKey is null ");
            sb.append(this.mDrmPublicKey == null);
            HwDrmLog.e(TAG, sb.toString());
            StringBuilder sb2 = new StringBuilder("getPublicKeyAndPrivateKey mDrmPrivateKey is null ");
            sb2.append(this.mDrmPrivateKey == null);
            HwDrmLog.e(TAG, sb2.toString());
        } catch (IOException e) {
            HwDrmLog.e(TAG, "IOException is catching in GetPublicKey" + HwDrmLog.printException((Exception) e));
        } catch (UnsupportedOperationException e2) {
            HwDrmLog.e(TAG, "UnsupportedOperationException is catching in GetPublicKey" + HwDrmLog.printException((Exception) e2));
        } catch (KeyStoreException e3) {
            HwDrmLog.e(TAG, "KeyStoreException is catching in GetPublicKey" + HwDrmLog.printException((Exception) e3));
        } catch (NoSuchAlgorithmException e4) {
            HwDrmLog.e(TAG, "NoSuchAlgorithmException is catching in GetPublicKey" + HwDrmLog.printException((Exception) e4));
        } catch (UnrecoverableEntryException e5) {
            HwDrmLog.e(TAG, "UnrecoverableEntryException is catching in GetPublicKey" + HwDrmLog.printException((Exception) e5));
        } catch (CertificateException e6) {
            HwDrmLog.e(TAG, "CertificateException is catching in GetPublicKey" + HwDrmLog.printException((Exception) e6));
        }
    }

    public String generateLicenseReq(String str) {
        HwDrmLog.i(TAG, "generateLicenseReq enter");
        String headerJsonStr = getHeaderJsonStr(HexUtil.byteArray2HexStr(JwtUtil.generateSecureRandom(24)));
        if (TextUtils.isEmpty(headerJsonStr)) {
            return "";
        }
        String base64UrlEncode = Base64Util.base64UrlEncode(headerJsonStr);
        JSONObject clientInfoJsonObj = getClientInfoJsonObj(new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis())));
        JSONObject contentInfoJsonObj = getContentInfoJsonObj(str);
        if (clientInfoJsonObj == null || contentInfoJsonObj == null) {
            return "";
        }
        String payLoadJsonStr = getPayLoadJsonStr(clientInfoJsonObj, contentInfoJsonObj);
        if (TextUtils.isEmpty(payLoadJsonStr)) {
            return "";
        }
        String base64UrlEncode2 = Base64Util.base64UrlEncode(payLoadJsonStr);
        String str2 = base64UrlEncode + "." + base64UrlEncode2 + "." + Base64Util.base64UrlEncode(genDrmReqSignature(HexUtil.hexStr2ByteArray(AesGcmUtils.decryptStrByGCM(this.mContext.getString(R$string.SHARE_KEY_SECRET), this.mContext)), HexUtil.hexStr2ByteArray(this.mContext.getString(R$string.SHARE_KEY_IV)), HexUtil.hexStr2ByteArray("00"), HexUtil.hexStr2ByteArray(this.mContext.getString(R$string.SHARE_KEY_TAG)), (base64UrlEncode + "." + base64UrlEncode2).getBytes(HwDrmConstant.DRM_CHARSET)));
        HwDrmLog.i(TAG, "generateLicenseReq end");
        return str2;
    }

    private String getPayLoadJsonStr(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("clientInfo", jSONObject);
            jSONObject3.put(BaseEvent.KEY_CONTENTINFO, jSONObject2);
        } catch (JSONException e) {
            HwDrmLog.e(TAG, "JSONException is caught in payloadJSONObject" + HwDrmLog.printException((Exception) e));
            jSONObject3 = null;
        }
        return jSONObject3 == null ? "" : jSONObject3.toString();
    }

    private JSONObject getContentInfoJsonObj(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("keyid", str);
            return jSONObject;
        } catch (JSONException e) {
            HwDrmLog.e(TAG, "JSONException is caught in contentInfoJSONObject" + HwDrmLog.printException((Exception) e));
            return null;
        }
    }

    private JSONObject getClientInfoJsonObj(String str) {
        if (this.mDrmPublicKey == null) {
            HwDrmLog.e(TAG, "getClientInfoJsonObj mDrmPublicKey == null");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("clientTimestamp", str);
            jSONObject.put("clientVersion", "1.0");
            jSONObject.put("clientOSAPIVersion", Build.VERSION.SDK_INT);
            jSONObject.put("clientPubKey", Base64Util.base64Encode(this.mDrmPublicKey.getEncoded()));
            return jSONObject;
        } catch (JSONException e) {
            HwDrmLog.e(TAG, "JSONException is caught in clientInfoJSONObject" + HwDrmLog.printException((Exception) e));
            return null;
        }
    }

    private String getHeaderJsonStr(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", "1.0");
            jSONObject.put(Constants.NONCE, str);
            jSONObject.put("signAlg", 1);
        } catch (JSONException e) {
            HwDrmLog.e(TAG, "JSONException is caught in headerJSONObject" + HwDrmLog.printException((Exception) e));
            jSONObject = null;
        }
        return jSONObject == null ? "" : jSONObject.toString();
    }

    @Override // com.huawei.wisecloud.drmclient.client.HwDrmClientProvider
    @Deprecated
    public HwDrmLicense praseLicenseRSP(String str) throws HwDrmException {
        return praseLicenseRSP(str, null);
    }

    @Override // com.huawei.wisecloud.drmclient.client.HwDrmClientProvider
    public HwDrmLicense praseLicenseRSP(String str, SecurityLabel securityLabel) throws HwDrmException {
        HwDrmLicense praseLicense = praseLicense(str);
        saveLicenseToMap(praseLicense);
        saveLicenseToLocal(str, praseLicense, securityLabel);
        return praseLicense;
    }

    @Override // com.huawei.wisecloud.drmclient.client.HwDrmClientProvider
    public HwDrmLicense getLocalLicense(String str) throws HwDrmException {
        String sha256UrlFromString = DigestUtil.sha256UrlFromString(str);
        if (this.licenseMap.containsKey(sha256UrlFromString)) {
            HwDrmLog.i(TAG, "return license from licenseMap");
            return this.licenseMap.get(sha256UrlFromString);
        }
        try {
            HwDrmLicense praseLicense = praseLicense(FileUtil.getStrFromLicenseFile(sha256UrlFromString, workPath));
            saveLicenseToMap(praseLicense);
            HwDrmLog.i(TAG, "return license from licenseFile");
            return praseLicense;
        } catch (HwDrmException e) {
            HwDrmLog.i(TAG, "HwDrmException is caught in getLocalLicense " + HwDrmLog.printException((Exception) e));
            FileUtil.deleteLicenseFile(sha256UrlFromString, workPath);
            return null;
        } catch (RuntimeException e2) {
            HwDrmLog.i(TAG, "HwDrmException is caught in getLocalLicense " + HwDrmLog.printException((Exception) e2));
            FileUtil.deleteLicenseFile(sha256UrlFromString, workPath);
            return null;
        }
    }

    private HwDrmLicense praseLicense(String str) throws HwDrmException {
        HwDrmLicense generateLicense = generateLicense(JwtUtil.handleJwtString(str));
        verifyDrmClientID(generateLicense);
        return generateLicense;
    }

    private HwDrmLicense generateLicense(String[] strArr) throws HwDrmException {
        CommonHeaderEntry commonHeaderEntry = (CommonHeaderEntry) JsonUtil.parseJsonText2Object(CommonHeaderEntry.class, Base64Util.base64Decode2String(strArr[0]));
        if (!RSASignAlgo.getAlgoNameList().contains(Integer.valueOf(commonHeaderEntry.getSignAlg()))) {
            throw new HwDrmException("license check error: unsupported signAlg");
        }
        setmServerPubKey(getServerPublicKeyString2Key());
        verifyLicenseSignature(strArr, this.mServerPubKey, commonHeaderEntry.getSignAlg());
        CommonPayloadEntry commonPayloadEntry = (CommonPayloadEntry) JsonUtil.parseJsonText2Object(CommonPayloadEntry.class, Base64Util.base64Decode2String(strArr[1]));
        LicenseVerifierFactory.getVerifier().verify(new CommonLicenseEntry(commonHeaderEntry, commonPayloadEntry));
        return new HwDrmLicenseImpl(commonPayloadEntry.getLicense(), this.mDrmPrivateKey);
    }

    private void verifyLicenseSignature(String[] strArr, PublicKey publicKey, int i) throws HwDrmException {
        if (RSAUtil.verifyRSASignature(strArr[0] + "." + strArr[1], publicKey, i, Base64Util.base64UrlDecode2Bytes(strArr[2]))) {
            return;
        }
        HwDrmLog.e("HwDrmClient", "verify license signature result: false");
        throw new HwDrmException("verify license signature result: false");
    }

    private void saveLicenseToMap(HwDrmLicense hwDrmLicense) throws HwDrmException {
        this.licenseMap.put(DigestUtil.sha256UrlFromString(hwDrmLicense.getLicense().getKeyInfo().getKeyId()), hwDrmLicense);
    }

    private void saveLicenseToLocal(String str, HwDrmLicense hwDrmLicense, SecurityLabel securityLabel) throws HwDrmException {
        PayloadLicenseEntry license = hwDrmLicense.getLicense();
        if (license.getLicensePolicy().isPersistence()) {
            String sha256UrlFromString = DigestUtil.sha256UrlFromString(license.getKeyInfo().getKeyId());
            FileUtil.saveStrToLicenseFile(str, sha256UrlFromString, workPath);
            if (securityLabel != null) {
                securityLabel.setLabel(workPath + File.separator + sha256UrlFromString);
            }
            StringBuilder sb = new StringBuilder("Server Kms LicensePolicy is persistence, securityLabel is null ? ");
            sb.append(securityLabel == null);
            HwDrmLog.i(TAG, sb.toString());
            return;
        }
        HwDrmLog.i(TAG, "Server Kms LicensePolicy is not persistence");
    }

    private void verifyDrmClientID(HwDrmLicense hwDrmLicense) throws HwDrmException {
        if (hwDrmLicense.getLicense().getDrmClientID() != null) {
            String drmClientID = hwDrmLicense.getLicense().getDrmClientID();
            PublicKey publicKey = this.mDrmPublicKey;
            if (publicKey == null) {
                throw new HwDrmException("mDrmPublicKey == null");
            }
            if (DigestUtil.sha256FromBytes(publicKey.getEncoded()).equals(drmClientID)) {
                return;
            }
            HwDrmLog.e("HwDrmClient", "fail to verify, drmClientID error");
            throw new HwDrmException("fail to verify, drmClientID error");
        }
        HwDrmLog.e("HwDrmClient", "fail to verify, drmClientID is null");
        throw new HwDrmException("fail to verify, drmClientID is null");
    }

    public void deleteCache(String str) {
        try {
            String sha256UrlFromString = DigestUtil.sha256UrlFromString(str);
            if (this.licenseMap.containsKey(sha256UrlFromString)) {
                this.licenseMap.remove(sha256UrlFromString);
            }
            FileUtil.deleteLicenseFile(sha256UrlFromString, workPath);
        } catch (HwDrmException unused) {
            HwDrmLog.e("HwDrmClient", "sha256 is caught in deleteHWDRMClient");
        }
    }

    private String getServerPublicKeyString() {
        return new String(decryptAesGcm(HexUtil.hexStr2ByteArray(this.mContext.getResources().getString(R$string.SERVER_PUBLIC_KEY_SECRET)), HexUtil.hexStr2ByteArray(this.mContext.getResources().getString(R$string.SERVER_PUBLIC_KEY_IV)), HexUtil.hexStr2ByteArray(this.mContext.getString(R$string.SERVER_PUBLIC_KEY_AAD)), HexUtil.hexStr2ByteArray(this.mContext.getString(R$string.SERVER_PUBLIC_KEY_TAG))), Charset.defaultCharset());
    }

    private PublicKey getServerPublicKeyString2Key() {
        try {
            return KeyFactory.getInstance(JceNames.RSA).generatePublic(new X509EncodedKeySpec(Base64Util.base64Decode2Bytes(getServerPublicKeyString())));
        } catch (NoSuchAlgorithmException e) {
            HwDrmLog.e(TAG, "NoSuchAlgorithmException is caught in getPublicKeyString2Key" + HwDrmLog.printException((Exception) e));
            return null;
        } catch (InvalidKeySpecException e2) {
            HwDrmLog.e(TAG, "InvalidKeySpecException is caught in getPublicKeyString2Key" + HwDrmLog.printException((Exception) e2));
            return null;
        }
    }
}

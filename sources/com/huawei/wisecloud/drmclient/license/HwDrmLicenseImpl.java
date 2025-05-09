package com.huawei.wisecloud.drmclient.license;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.entry.PayloadKeyInfoEntry;
import com.huawei.wisecloud.drmclient.license.entry.PayloadLicenseEntry;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import com.huawei.wisecloud.drmclient.utils.AESAlgo;
import com.huawei.wisecloud.drmclient.utils.AESUtil;
import com.huawei.wisecloud.drmclient.utils.Base64Util;
import com.huawei.wisecloud.drmclient.utils.HexUtil;
import com.huawei.wisecloud.drmclient.utils.HmacUtil;
import com.huawei.wisecloud.drmclient.utils.JsonUtil;
import com.huawei.wisecloud.drmclient.utils.RSAEncryptAlgo;
import com.huawei.wisecloud.drmclient.utils.RSAUtil;
import com.huawei.wisecloud.drmclient.utils.StreamUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HwDrmLicenseImpl implements HwDrmLicense {
    private static final String IV_NAME = "iv";
    private static final String KEY_NAME = "key";
    private static final String SEP = ":";
    private static final int SEP_NUM = 2;
    private final int ckEncryptAlg;
    private final HashMap<String, byte[]> contentKeyMap = new HashMap<>();
    private final PayloadLicenseEntry license;
    private final String secretInfo;
    private final byte[] sessionKey;

    public HwDrmLicenseImpl(PayloadLicenseEntry payloadLicenseEntry, PrivateKey privateKey) throws HwDrmException {
        this.license = payloadLicenseEntry;
        PayloadKeyInfoEntry keyInfo = payloadLicenseEntry.getKeyInfo();
        this.sessionKey = RSAUtil.decrypt(Base64Util.base64Decode2Bytes(keyInfo.getSessionKey()), privateKey, RSAEncryptAlgo.getAlgoName(keyInfo.getSkEncryptAlg()));
        this.ckEncryptAlg = keyInfo.getCkEncryptAlg();
        decryptContentKey(keyInfo.getContentKey());
        this.secretInfo = keyInfo.getSecretInfo();
    }

    @Override // com.huawei.wisecloud.drmclient.license.HwDrmLicense
    public void decrypt(InputStream inputStream, OutputStream outputStream, String str, String str2, byte[] bArr) throws HwDrmException {
        try {
            byte[] bArr2 = this.contentKeyMap.get(str2);
            if (bArr2 == null) {
                throw new HwDrmException("invalid key name: " + str2);
            }
            outputStream.write(AESUtil.decrypt(StreamUtil.toByteArray(inputStream), bArr2, bArr, str));
        } catch (HwDrmException | IOException e) {
            String str3 = "fail to decrypt, Exception: " + HwDrmLog.printException(e);
            HwDrmLog.e("HwDrmLicenseImpl", str3);
            throw new HwDrmException(str3);
        }
    }

    @Override // com.huawei.wisecloud.drmclient.license.HwDrmLicense
    public byte[] decryptForLiveWallPager(byte[] bArr, String str, String str2, byte[] bArr2) throws HwDrmException {
        try {
            byte[] bArr3 = this.contentKeyMap.get(str2);
            if (bArr3 == null) {
                throw new HwDrmException("invalid key name: " + str2);
            }
            return AESUtil.decrypt(bArr, bArr3, bArr2, str);
        } catch (HwDrmException e) {
            String str3 = "fail to decrypt, Exception: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e("HwDrmLicenseImpl", str3);
            throw new HwDrmException(str3);
        }
    }

    @Override // com.huawei.wisecloud.drmclient.license.HwDrmLicense
    public PayloadLicenseEntry getLicense() {
        return this.license;
    }

    @Override // com.huawei.wisecloud.drmclient.license.HwDrmLicense
    public byte[] calHmac(InputStream inputStream, String str, String str2) throws HwDrmException {
        byte[] bytes2Hex2UpperCase2bytes = HexUtil.bytes2Hex2UpperCase2bytes(this.contentKeyMap.get(str2));
        if (bytes2Hex2UpperCase2bytes == null) {
            throw new HwDrmException("invalid key name: " + str2);
        }
        return (byte[]) HmacUtil.digest2Inputstream(inputStream, str, bytes2Hex2UpperCase2bytes).clone();
    }

    @Override // com.huawei.wisecloud.drmclient.license.HwDrmLicense
    public byte[] getSecretInfo() throws HwDrmException {
        String str = this.secretInfo;
        if (str == null) {
            return new byte[0];
        }
        String[] split = str.split(":", 2);
        return (byte[]) AESUtil.decrypt(Base64Util.base64Decode2Bytes(split[1]), this.sessionKey, Base64Util.base64Decode2Bytes(split[0]), AESAlgo.getAlgoName(this.ckEncryptAlg)).clone();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void decryptContentKey(JSONObject jSONObject) throws HwDrmException {
        if (jSONObject == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        JsonUtil.parseJson2Map(hashMap, jSONObject);
        for (Map.Entry entry : hashMap.entrySet()) {
            String[] split = ((String) entry.getValue()).split(":", 2);
            this.contentKeyMap.put(entry.getKey(), AESUtil.decrypt(Base64Util.base64Decode2Bytes(split[1]), this.sessionKey, Base64Util.base64Decode2Bytes(split[0]), AESAlgo.getAlgoName(this.ckEncryptAlg)));
        }
    }

    @Override // com.huawei.wisecloud.drmclient.license.HwDrmLicense
    public byte[] getEncrkey() {
        return this.contentKeyMap.get("ENCR_KEY");
    }
}

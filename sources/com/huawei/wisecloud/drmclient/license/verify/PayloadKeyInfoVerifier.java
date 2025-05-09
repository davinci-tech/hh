package com.huawei.wisecloud.drmclient.license.verify;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;
import com.huawei.wisecloud.drmclient.license.entry.PayloadKeyInfoEntry;
import com.huawei.wisecloud.drmclient.utils.AESAlgo;
import com.huawei.wisecloud.drmclient.utils.RSAEncryptAlgo;

/* loaded from: classes7.dex */
public class PayloadKeyInfoVerifier extends AbstractLicenseVerifier {
    @Override // com.huawei.wisecloud.drmclient.license.verify.AbstractLicenseVerifier
    public void doVerify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException {
        PayloadKeyInfoEntry keyInfo = commonLicenseEntry.getPayload().getLicense().getKeyInfo();
        if (keyInfo.getKeyId() == null) {
            throw new HwDrmException("license check error: keyId is null");
        }
        if (!RSAEncryptAlgo.getAlgoNameList().contains(Integer.valueOf(keyInfo.getSkEncryptAlg()))) {
            throw new HwDrmException("license check error: unsupported skEncryptAlg");
        }
        if (keyInfo.getSessionKey() == null) {
            throw new HwDrmException("license check error: sessionKey is null");
        }
        if (!AESAlgo.getAlgoNameList().contains(Integer.valueOf(keyInfo.getCkEncryptAlg()))) {
            throw new HwDrmException("license check error: unsupported ckEncryptAlg");
        }
    }
}

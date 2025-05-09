package com.huawei.wisecloud.drmclient.license.verify;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;

/* loaded from: classes7.dex */
public abstract class AbstractLicenseVerifier implements LicenseVerifier {
    private LicenseVerifier next = null;

    public abstract void doVerify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException;

    @Override // com.huawei.wisecloud.drmclient.license.verify.LicenseVerifier
    public void verify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException {
        try {
            doVerify(commonLicenseEntry);
            LicenseVerifier licenseVerifier = this.next;
            if (licenseVerifier != null) {
                licenseVerifier.verify(commonLicenseEntry);
            }
        } catch (HwDrmException e) {
            HwDrmLog.e("LicenseVerifier", "verify license error: " + HwDrmLog.printException((Exception) e));
            throw e;
        }
    }

    @Override // com.huawei.wisecloud.drmclient.license.verify.LicenseVerifier
    public void setNext(LicenseVerifier licenseVerifier) {
        LicenseVerifier licenseVerifier2 = this.next;
        if (licenseVerifier2 == null) {
            this.next = licenseVerifier;
        } else {
            licenseVerifier2.setNext(licenseVerifier);
        }
    }
}

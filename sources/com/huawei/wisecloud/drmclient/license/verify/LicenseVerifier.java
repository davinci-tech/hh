package com.huawei.wisecloud.drmclient.license.verify;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;

/* loaded from: classes7.dex */
public interface LicenseVerifier {
    void setNext(LicenseVerifier licenseVerifier);

    void verify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException;
}

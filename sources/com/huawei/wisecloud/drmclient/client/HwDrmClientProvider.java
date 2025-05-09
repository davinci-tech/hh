package com.huawei.wisecloud.drmclient.client;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.interfaces.SecurityLabel;
import com.huawei.wisecloud.drmclient.license.HwDrmLicense;

/* loaded from: classes7.dex */
public interface HwDrmClientProvider {
    HwDrmLicense getLocalLicense(String str) throws HwDrmException;

    HwDrmLicense praseLicenseRSP(String str) throws HwDrmException;

    HwDrmLicense praseLicenseRSP(String str, SecurityLabel securityLabel) throws HwDrmException;
}

package com.huawei.wisecloud.drmclient.license.verify;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.entry.CommonHeaderEntry;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class LicenseHeaderVerifier extends AbstractLicenseVerifier {
    private static List<String> VERSION_LIST;

    static {
        ArrayList arrayList = new ArrayList();
        VERSION_LIST = arrayList;
        arrayList.add("1.0");
    }

    @Override // com.huawei.wisecloud.drmclient.license.verify.AbstractLicenseVerifier
    public void doVerify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException {
        CommonHeaderEntry header = commonLicenseEntry.getHeader();
        if (header.getVersion() == null) {
            throw new HwDrmException("license check error: version is null");
        }
        if (!VERSION_LIST.contains(header.getVersion())) {
            throw new HwDrmException("license check error: unsupported version");
        }
        if (header.getNonce() == null) {
            throw new HwDrmException("license check error: nonce is null");
        }
        if (header.getNonce().length() < 24) {
            throw new HwDrmException("license check error: nonce length illegal");
        }
    }
}

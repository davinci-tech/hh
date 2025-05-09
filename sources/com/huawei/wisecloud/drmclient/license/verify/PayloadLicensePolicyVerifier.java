package com.huawei.wisecloud.drmclient.license.verify;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;
import com.huawei.wisecloud.drmclient.license.entry.PayloadLicensePolicyEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* loaded from: classes7.dex */
public class PayloadLicensePolicyVerifier extends AbstractLicenseVerifier {
    @Override // com.huawei.wisecloud.drmclient.license.verify.AbstractLicenseVerifier
    public void doVerify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException {
        PayloadLicensePolicyEntry licensePolicy = commonLicenseEntry.getPayload().getLicense().getLicensePolicy();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT);
        simpleDateFormat.setLenient(false);
        if (licensePolicy.getStartTime() != null) {
            try {
                simpleDateFormat.parse(licensePolicy.getStartTime());
            } catch (ParseException unused) {
                throw new HwDrmException("license check error: startTime format error");
            }
        }
        if (licensePolicy.getEndTime() != null) {
            try {
                simpleDateFormat.parse(licensePolicy.getEndTime());
            } catch (ParseException unused2) {
                throw new HwDrmException("license check error: endTime format error");
            }
        }
    }
}

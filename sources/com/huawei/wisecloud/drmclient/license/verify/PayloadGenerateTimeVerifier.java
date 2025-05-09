package com.huawei.wisecloud.drmclient.license.verify;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* loaded from: classes7.dex */
public class PayloadGenerateTimeVerifier extends AbstractLicenseVerifier {
    @Override // com.huawei.wisecloud.drmclient.license.verify.AbstractLicenseVerifier
    public void doVerify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT);
        simpleDateFormat.setLenient(false);
        String generateTime = commonLicenseEntry.getPayload().getLicense().getGenerateTime();
        if (generateTime == null) {
            throw new HwDrmException("license check error: generateTime is null");
        }
        try {
            simpleDateFormat.parse(generateTime);
        } catch (ParseException unused) {
            throw new HwDrmException("license check error: generateTime format error");
        }
    }
}

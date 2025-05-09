package com.huawei.wisecloud.drmclient.license.verify;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.license.entry.CommonLicenseEntry;
import com.huawei.wisecloud.drmclient.license.entry.PayloadServerInfoEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* loaded from: classes7.dex */
public class PayloadServerInfoVerifier extends AbstractLicenseVerifier {
    @Override // com.huawei.wisecloud.drmclient.license.verify.AbstractLicenseVerifier
    public void doVerify(CommonLicenseEntry commonLicenseEntry) throws HwDrmException {
        PayloadServerInfoEntry serverInfo = commonLicenseEntry.getPayload().getServerInfo();
        if (serverInfo == null) {
            throw new HwDrmException("license check error: serverInfo is null");
        }
        if (serverInfo.getServerTimeStamp() == null) {
            throw new HwDrmException("license check error: serverTimeStamp is null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(serverInfo.getServerTimeStamp());
        } catch (ParseException unused) {
            throw new HwDrmException("license check error: serverTimeStamp format error");
        }
    }
}

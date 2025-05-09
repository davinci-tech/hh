package com.huawei.watchface;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.wisecloud.drmclient.client.HwDrmClient;
import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.HwDrmLicense;

/* loaded from: classes7.dex */
public class g {
    public static HwDrmLicense a(Context context, String str) throws HwDrmException {
        if (TextUtils.isEmpty(str)) {
            HwLog.e("DRMManager", "parseLicenseRSP() TextUtils.isEmpty(licenseResp)");
            return null;
        }
        HwDrmLicense praseLicenseRSP = HwDrmClient.newHWDRMClient(context).praseLicenseRSP(str);
        if (praseLicenseRSP == null) {
            HwLog.e("DRMManager", "parseLicenseRSP() drmSdkParseLicenseResp == null");
        }
        return praseLicenseRSP;
    }

    public static String b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.e("DRMManager", "generateLicenseReq() TextUtils.isEmpty(keyID)");
            return "";
        }
        return HwDrmClient.newHWDRMClient(context).generateLicenseReq(str);
    }

    public static HwDrmLicense c(Context context, String str) throws HwDrmException {
        if (TextUtils.isEmpty(str)) {
            HwLog.e("DRMManager", "getLocalLicense() TextUtils.isEmpty(keyID)");
            return null;
        }
        return HwDrmClient.newHWDRMClient(context).getLocalLicense(str);
    }
}

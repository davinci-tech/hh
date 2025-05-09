package com.huawei.hms.ml.grs;

import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.ml.common.utils.SmartLog;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.hms.mlsdk.common.MLApplicationSetting;

/* loaded from: classes4.dex */
public class GrsRegionUtils {
    private static final String TAG = "GrsRegionUtils";

    public static String getGrsCountryCode() {
        String countryCode = MLApplication.getInstance().getCountryCode();
        String str = TAG;
        SmartLog.i(str, "user region countryCode is " + countryCode);
        MLApplicationSetting appSetting = MLApplication.getInstance().getAppSetting();
        if (TextUtils.isEmpty(appSetting.getAppMLKitGrsPolicy()) || "0".equals(appSetting.getAppMLKitGrsPolicy())) {
            SmartLog.e(str, "AppMLKitGrsPolicy is " + appSetting.getAppMLKitGrsPolicy());
            SmartLog.i(str, "AGC Connect region is " + appSetting.getRegion());
            return GrsApp.getInstance().getIssueCountryCode(MLApplication.getInstance().getAppContext());
        }
        return getGrsPolicyCountry(countryCode, appSetting);
    }

    private static String getGrsPolicyCountry(String str, MLApplicationSetting mLApplicationSetting) {
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(mLApplicationSetting.getRegion())) {
                return GrsApp.getInstance().getIssueCountryCode(MLApplication.getInstance().getAppContext());
            }
            SmartLog.i(TAG, "AGC Connect region is " + mLApplicationSetting.getRegion());
            return mLApplicationSetting.getRegion();
        }
        SmartLog.i(TAG, "App setting countryCode " + str);
        return str;
    }
}

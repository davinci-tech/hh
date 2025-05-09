package com.huawei.hms.support.picker.common;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import defpackage.ksy;

/* loaded from: classes4.dex */
public class HMSAPKVersionCheckUtil {
    private static final String TAG = "HMSAPKVersionCheckUtil";

    private static int getMinApkVersion() {
        return AuthInternalPickerConstant.HMS_APK_VERSION_MIN;
    }

    public static void checkAvailabilityAndConnect(Activity activity, int i, AvailableAdapter.AvailableCallBack availableCallBack) {
        ksy.b(TAG, "====== HMSSDK version: 61200300 ======", true);
        Context applicationContext = activity.getApplicationContext();
        int minApkVersion = getMinApkVersion();
        ksy.b(TAG, "check minVersion:" + minApkVersion, true);
        AvailableAdapter availableAdapter = new AvailableAdapter(minApkVersion);
        int isHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(applicationContext);
        if (2 == isHuaweiMobileServicesAvailable || ((1 == isHuaweiMobileServicesAvailable && i == 2) || (1 == isHuaweiMobileServicesAvailable && i == 3))) {
            availableAdapter.startResolution(activity, availableCallBack);
        } else {
            availableCallBack.onComplete(isHuaweiMobileServicesAvailable);
        }
    }
}

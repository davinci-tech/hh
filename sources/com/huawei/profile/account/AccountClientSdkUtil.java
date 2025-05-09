package com.huawei.profile.account;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.utils.logger.DsLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public final class AccountClientSdkUtil {
    private static final int DEFAULT_WRONG_APP_ID = -1;
    private static final String TAG = "AccountClientSdkUtil";

    private AccountClientSdkUtil() {
    }

    public static Map<String, String> generateRequestHeader(Context context, String str, String str2) throws ProfileRequestException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.UNEXPECTED_NULL_POINTER, "user id or at is null");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + str);
        hashMap.put("x-huid", str2);
        hashMap.put("Content-Type", "application/json");
        hashMap.put(ProfileRequestConstants.X_APPID_KEY, Integer.toString(getAppIdFromMetaData(context)));
        hashMap.put("x-version", ProfileRequestConstants.X_VERSION_VALUE);
        hashMap.put(ProfileRequestConstants.X_LANGUAGE_KEY, ProfileRequestConstants.X_LANGUAGE_VALUE);
        return hashMap;
    }

    private static int getAppIdFromMetaData(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            DsLog.et(TAG, "Failed to get packageManager.", new Object[0]);
            return -1;
        }
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            DsLog.et(TAG, "name not found: " + e.getMessage(), new Object[0]);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            DsLog.et(TAG, "Failed to get applicationInfo.", new Object[0]);
            return -1;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null) {
            DsLog.et(TAG, "Failed to get bundle.", new Object[0]);
            return -1;
        }
        Object obj = bundle.get("com.huawei.hms.client.appid");
        if (obj instanceof Integer) {
            DsLog.dt(TAG, "get app id from meta data", new Object[0]);
            return ((Integer) obj).intValue();
        }
        AGConnectServicesConfig fromContext = AGConnectServicesConfig.fromContext(context);
        if (fromContext == null) {
            DsLog.et(TAG, "Failed to get agConnectServicesConfig.", new Object[0]);
            return -1;
        }
        DsLog.dt(TAG, "get app id from AGConnectServiceConfig", new Object[0]);
        return fromContext.getInt(AgConnectInfo.AgConnectKey.APPLICATION_ID);
    }
}

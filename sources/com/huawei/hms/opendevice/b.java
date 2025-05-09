package com.huawei.hms.opendevice;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.entity.DeleteTokenReq;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.huawei.hms.push.HmsMessaging;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;
import java.util.UUID;

/* loaded from: classes4.dex */
public class b {
    public static DeleteTokenReq a(Context context, String str, String str2, String str3, String str4) {
        DeleteTokenReq deleteTokenReq = new DeleteTokenReq();
        deleteTokenReq.setAppId(str);
        deleteTokenReq.setScope(str4);
        deleteTokenReq.setProjectId(str2);
        deleteTokenReq.setPkgName(context.getPackageName());
        deleteTokenReq.setSubjectId(str3);
        if (TextUtils.isEmpty(str)) {
            deleteTokenReq.setAppId(Util.getAppId(context));
        }
        if (TextUtils.isEmpty(str4)) {
            deleteTokenReq.setScope(HmsMessaging.DEFAULT_TOKEN_SCOPE);
        }
        if (TextUtils.isEmpty(str2)) {
            deleteTokenReq.setProjectId(c(context));
        }
        return deleteTokenReq;
    }

    public static TokenReq b(Context context, String str, String str2) {
        return b(context, str, null, null, str2);
    }

    public static String c(Context context) {
        return AGConnectServicesConfig.fromContext(context).getString(AgConnectInfo.AgConnectKey.PROJECT_ID);
    }

    public static boolean d(Context context) {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                return !TextUtils.isEmpty(bundle.getString("com.huawei.hms.client.service.name:base"));
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.e(HmsInstanceId.TAG, "isIntegratedBaseSdk failed.");
            return true;
        }
    }

    public static TokenReq b(Context context, String str) {
        return b(context, null, null, str, null);
    }

    public static TokenReq b(Context context, String str, String str2, String str3, String str4) {
        TokenReq tokenReq = new TokenReq();
        tokenReq.setPackageName(context.getPackageName());
        tokenReq.setAppId(str);
        tokenReq.setScope(str4);
        tokenReq.setProjectId(str2);
        tokenReq.setSubjectId(str3);
        tokenReq.setMultiSender(false);
        if (TextUtils.isEmpty(str)) {
            tokenReq.setAppId(Util.getAppId(context));
        }
        if (TextUtils.isEmpty(str2)) {
            tokenReq.setProjectId(c(context));
        }
        if (TextUtils.isEmpty(str4)) {
            tokenReq.setScope(HmsMessaging.DEFAULT_TOKEN_SCOPE);
        }
        i a2 = i.a(context);
        if (!a2.getBoolean("hasRequestAgreement")) {
            tokenReq.setFirstTime(true);
            a2.saveBoolean("hasRequestAgreement", true);
        } else {
            tokenReq.setFirstTime(false);
        }
        return tokenReq;
    }

    public static DeleteTokenReq a(Context context, String str, String str2) {
        return a(context, str, null, null, str2);
    }

    public static DeleteTokenReq a(Context context, String str) {
        return a(context, null, null, str, null);
    }

    public static DeleteTokenReq a(Context context) {
        return a(context, null, null, null, null);
    }

    public static String b(Context context) {
        String uuid;
        synchronized (b.class) {
            PushPreferences pushPreferences = new PushPreferences(context, com.huawei.agconnect.credential.obs.c.f1765a);
            if (pushPreferences.containsKey(com.huawei.agconnect.credential.obs.c.f1765a)) {
                uuid = pushPreferences.getString(com.huawei.agconnect.credential.obs.c.f1765a);
            } else {
                uuid = UUID.randomUUID().toString();
                pushPreferences.saveString(com.huawei.agconnect.credential.obs.c.f1765a, uuid);
                pushPreferences.saveLong(com.huawei.agconnect.credential.obs.c.b, Long.valueOf(System.currentTimeMillis()));
            }
        }
        return uuid;
    }
}

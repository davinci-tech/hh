package com.huawei.hms.push;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.plugin.PushProxy;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.utils.ha.PushAnalyticsCenter;
import com.huawei.hms.push.utils.ha.PushBaseAnalytics;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;

/* loaded from: classes9.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static final String f5678a = "l";

    public static void a(Context context, String str, String str2, String str3) {
        PushBaseAnalytics pushAnalytics = PushAnalyticsCenter.getInstance().getPushAnalytics();
        if (pushAnalytics == null) {
            return;
        }
        Bundle a2 = a(context, str, str2);
        HMSLog.i(f5678a, "eventId:" + str3);
        pushAnalytics.report(context, str3, a2);
    }

    private static Bundle a(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(JsbMapKeyNames.JSSDK_VER, String.valueOf(61200300));
        bundle.putString(SdkCfgSha256Record.PKGNAME, context.getPackageName());
        bundle.putString(com.huawei.agconnect.credential.obs.c.f1765a, HmsInstanceId.getInstance(context).getId());
        PushProxy proxy = ProxyCenter.getProxy();
        if (proxy != null) {
            bundle.putString("proxyType", proxy.getProxyType());
        }
        bundle.putString("msgId", str);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(RemoteMessageConst.ANALYTIC_INFO, str2);
        }
        return bundle;
    }

    public static void a(Context context, Bundle bundle, String str) {
        PushBaseAnalytics pushAnalytics;
        if (bundle == null || (pushAnalytics = PushAnalyticsCenter.getInstance().getPushAnalytics()) == null) {
            return;
        }
        bundle.putString("sdk_version", String.valueOf(61200300));
        HMSLog.i(f5678a, "eventId:" + str);
        pushAnalytics.report(context, str, bundle);
    }
}

package com.huawei.health.marketing.request;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.eie;
import defpackage.eil;
import defpackage.jdl;
import defpackage.lql;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class SleepAudioInfoFactory implements ParamsFactory {
    private static final String API_SUPPORT_VERSION = "1";
    private static final String CONTENT_TYPE = "application/json";
    private static final String HEALTH_SOURCE_VALUE = "1";
    private static final String SOURCE = "source";
    private static final String TAG = "SleepAudioInfoFactory";
    private static final String TIMESTAMP = "timestamp";
    private Context context;

    public SleepAudioInfoFactory(Context context) {
        if (context == null) {
            this.context = BaseApplication.getContext();
        } else {
            this.context = context;
        }
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        LogUtil.a(TAG, "getHeaders");
        LoginInit loginInit = LoginInit.getInstance(this.context);
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-Type", "application/json");
        String accountInfo = loginInit.getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
            hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            hashMap.put(CloudParamKeys.X_TOKEN, loginInit.getAccountInfo(1008));
        }
        hashMap.put("x-version", CommonUtil.c(this.context));
        hashMap.put("siteId", loginInit.getAccountInfo(1009));
        hashMap.put("appId", BaseApplication.getAppPackage());
        String deviceId = loginInit.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("deviceType", loginInit.getDeviceType());
        hashMap.put("ts", String.valueOf(getUtcTime()));
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("x-caller-trace-id", String.valueOf(getUtcTime()) + Math.random());
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        LogUtil.a(TAG, "getBody");
        Map<String, Object> d = lql.d(obj);
        d.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        d.put("token", LoginInit.getInstance(this.context).getAccountInfo(1008));
        if (LoginInit.getInstance(this.context).isLoginedByWear()) {
            d.put("appId", "com.huawei.bone");
        } else {
            d.put("appId", BaseApplication.getAppPackage());
        }
        d.put("deviceType", PhoneInfoUtils.getDeviceModel());
        String deviceId = LoginInit.getInstance(this.context).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        d.put("source", "1");
        d.put("timestamp", String.valueOf(eie.b()));
        d.put("deviceId", deviceId);
        d.put("sysVersion", Build.VERSION.RELEASE);
        d.put("bindDeviceType", String.valueOf(CommonUtil.h(this.context)));
        d.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        d.put("ts", String.valueOf(eie.b()));
        d.put("iVersion", "1");
        d.put("language", mtj.e(null));
        d.put("environment", Integer.valueOf(CommonUtil.l(this.context)));
        d.put("siteId", String.valueOf(LoginInit.getInstance(this.context).getSiteId()));
        d.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        d.put("timeZone", jdl.q(System.currentTimeMillis()));
        d.put("countryCode", LoginInit.getInstance(this.context).getAccountInfo(1010));
        return d;
    }

    private static long getUtcTime() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar.getTimeInMillis();
    }
}

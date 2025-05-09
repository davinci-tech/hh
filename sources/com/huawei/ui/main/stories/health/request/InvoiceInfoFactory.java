package com.huawei.ui.main.stories.health.request;

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
import defpackage.lql;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class InvoiceInfoFactory implements ParamsFactory {

    /* renamed from: a, reason: collision with root package name */
    private Context f10221a;

    public InvoiceInfoFactory(Context context) {
        if (context == null) {
            this.f10221a = BaseApplication.getContext();
        } else {
            this.f10221a = context;
        }
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        LogUtil.a("InvoiceInfoFactory", "getHeaders");
        LoginInit loginInit = LoginInit.getInstance(this.f10221a);
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-type", "application/json");
        String accountInfo = loginInit.getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
            hashMap.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            hashMap.put(CloudParamKeys.X_TOKEN, loginInit.getAccountInfo(1008));
        }
        hashMap.put(CloudParamKeys.X_CLIENT_VERSION, CommonUtil.c(this.f10221a));
        hashMap.put(CloudParamKeys.X_SITE_ID, loginInit.getAccountInfo(1009));
        hashMap.put(CloudParamKeys.X_APP_ID, BaseApplication.getAppPackage());
        String deviceId = loginInit.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put(CloudParamKeys.X_DEVICE_ID, deviceId);
        hashMap.put(CloudParamKeys.X_DEVICE_TYPE, loginInit.getDeviceType());
        hashMap.put(CloudParamKeys.X_TS, String.valueOf(d()));
        hashMap.put(CloudParamKeys.X_APP_TYPE, String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("x-caller-trace-id", String.valueOf(d()) + Math.random());
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        LogUtil.a("InvoiceInfoFactory", "getBody");
        Map<String, Object> d = lql.d(obj);
        d.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        d.put("token", LoginInit.getInstance(this.f10221a).getAccountInfo(1008));
        if (LoginInit.getInstance(this.f10221a).isLoginedByWear()) {
            d.put("appId", "com.huawei.bone");
        } else {
            d.put("appId", BaseApplication.getAppPackage());
        }
        d.put("deviceType", PhoneInfoUtils.getDeviceModel());
        String deviceId = LoginInit.getInstance(this.f10221a).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        d.put("source", "1");
        d.put("timestamp", String.valueOf(eie.b()));
        d.put("deviceId", deviceId);
        d.put("sysVersion", Build.VERSION.RELEASE);
        d.put("bindDeviceType", String.valueOf(CommonUtil.h(this.f10221a)));
        d.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        d.put("ts", String.valueOf(eie.b()));
        d.put("iVersion", "1");
        d.put("language", mtj.e(null));
        d.put("environment", Integer.valueOf(CommonUtil.l(this.f10221a)));
        d.put("siteId", String.valueOf(LoginInit.getInstance(this.f10221a).getSiteId()));
        d.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        d.put("timeZone", HiDateUtil.d((String) null));
        d.put("countryCode", LoginInit.getInstance(this.f10221a).getAccountInfo(1010));
        return d;
    }

    private static long d() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar.getTimeInMillis();
    }
}

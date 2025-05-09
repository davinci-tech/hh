package com.huawei.health.marketing.request;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.eie;
import defpackage.knx;
import defpackage.lql;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class CustomConfigFactory implements ParamsFactory {
    private static final String API_SUPPORT_VERSION = "1";
    private static final String TAG = "CustomConfigFactory";
    private Context mContext;

    public CustomConfigFactory(Context context) {
        this.mContext = context;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap(16);
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        if (accountInfo != null) {
            hashMap.put("x-huid", accountInfo);
        }
        hashMap.put("x-version", CommonUtil.c(this.mContext));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put(j2.v, "gzip, deflate");
        hashMap.put("Connection", w9.j);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map hashMap = obj == null ? new HashMap() : lql.d(obj);
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("phoneType", PhoneInfoUtils.getHuaweiManufaturerOrEmui());
        String deviceId = LoginInit.getInstance(this.mContext).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("bindDeviceType", String.valueOf(CommonUtil.h(this.mContext)));
        hashMap.put("wearType", "");
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", "1");
        hashMap.put("countryCode", LoginInit.getInstance(this.mContext).getAccountInfo(1010));
        hashMap.put("language", mtj.e(null));
        hashMap.put("ts", String.valueOf(eie.b()));
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("token", accountInfo);
        }
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("upDeviceType", LoginInit.getInstance(this.mContext).getDeviceType());
        if (LoginInit.getInstance(this.mContext).isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
            hashMap.put("source", 2);
        } else {
            hashMap.put("appId", BaseApplication.getAppPackage());
            hashMap.put("source", 1);
        }
        hashMap.put("siteId", LoginInit.getInstance(this.mContext).getAccountInfo(1009));
        hashMap.put("isManually", Integer.valueOf(lql.c()));
        hashMap.put("oaId", "");
        String e = KeyValDbManager.b(this.mContext).e("cloud_user_privacy10");
        if (e != null && "true".equals(e)) {
            hashMap.put("isTrackingEnabled", 1);
        } else {
            hashMap.put("isTrackingEnabled", 0);
        }
        hashMap.put("improveState", Boolean.valueOf(knx.e()));
        hashMap.put("currentManufacturer", Build.MANUFACTURER);
        return hashMap;
    }
}

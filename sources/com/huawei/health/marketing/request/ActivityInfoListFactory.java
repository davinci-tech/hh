package com.huawei.health.marketing.request;

import android.content.Context;
import android.os.Build;
import com.huawei.hwcommonmodel.application.BaseApplication;
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
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class ActivityInfoListFactory implements ParamsFactory {
    private static final String API_SUPPORT_VERSION = "1";
    private static final String TAG = "ActivityInfoListFactory";
    private Context mContext;

    public ActivityInfoListFactory(Context context) {
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
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> hashMap = obj == null ? new HashMap<>() : lql.d(obj);
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
        hashMap.put("token", LoginInit.getInstance(this.mContext).getAccountInfo(1008));
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("upDeviceType", LoginInit.getInstance(this.mContext).getDeviceType());
        if (LoginInit.getInstance(this.mContext).isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", BaseApplication.getAppPackage());
        }
        hashMap.put("siteId", LoginInit.getInstance(this.mContext).getAccountInfo(1009));
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        return hashMap;
    }
}

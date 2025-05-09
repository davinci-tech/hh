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
import defpackage.lql;
import defpackage.mtj;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class GlobalSearchFactory implements ParamsFactory {
    private static final String API_SUPPORT_VERSION = "1";
    private static final String HEALTH_SOURCE_VALUE = "1";
    private static final String SOURCE = "source";
    private static final String TAG = "GlobalSearchFactory";
    private static final String TIMESTAMP = "timestamp";
    private Context mContext;

    public GlobalSearchFactory(Context context) {
        this.mContext = context;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap(16);
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() && !TextUtils.isEmpty(accountInfo)) {
            LogUtil.a(TAG, "userId is not null");
            hashMap.put("x-huid", accountInfo);
        }
        hashMap.put("x-version", CommonUtil.c(this.mContext));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> d = lql.d(obj);
        d.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        d.put("token", LoginInit.getInstance(this.mContext).getAccountInfo(1008));
        if (LoginInit.getInstance(this.mContext).isLoginedByWear()) {
            d.put("appId", "com.huawei.bone");
        } else {
            d.put("appId", BaseApplication.getAppPackage());
        }
        d.put("deviceType", PhoneInfoUtils.getDeviceModel());
        String deviceId = LoginInit.getInstance(this.mContext).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        d.put("source", "1");
        d.put("timestamp", String.valueOf(eie.b()));
        d.put("deviceId", deviceId);
        d.put("sysVersion", Build.VERSION.RELEASE);
        d.put("bindDeviceType", String.valueOf(CommonUtil.h(this.mContext)));
        d.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        d.put("ts", String.valueOf(eie.b()));
        d.put("iVersion", "1");
        String e = mtj.e(null);
        if (e != null) {
            e = e.toLowerCase();
        }
        d.put("language", e);
        d.put("environment", Integer.valueOf(CommonUtil.l(this.mContext)));
        d.put("siteId", String.valueOf(LoginInit.getInstance(this.mContext).getSiteId()));
        d.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        return d;
    }
}

package com.huawei.ui.homehealth.healthheadlinecard;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
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

/* loaded from: classes6.dex */
public class HealthHeadlinesFactory implements ParamsFactory {
    private Context d;

    public HealthHeadlinesFactory(Context context) {
        if (context == null) {
            this.d = BaseApplication.e();
        } else {
            this.d = context.getApplicationContext();
        }
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap(16);
        String usetId = LoginInit.getInstance(this.d).getUsetId();
        if (!TextUtils.isEmpty(usetId)) {
            LogUtil.a("HealthHeadlinesFactory: userId is not null", new Object[0]);
            hashMap.put("x-huid", usetId);
        } else {
            LogUtil.h("HealthHeadlinesFactory: userId is null", new Object[0]);
            hashMap.put("x-huid", "");
        }
        hashMap.put("x-version", CommonUtil.c(this.d));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> d = lql.d(obj);
        d.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        d.put("token", LoginInit.getInstance(this.d).getAccountInfo(1008));
        if (LoginInit.getInstance(this.d).isLoginedByWear()) {
            d.put("appId", "com.huawei.bone");
        } else {
            d.put("appId", com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage());
        }
        d.put("deviceType", PhoneInfoUtils.getDeviceModel());
        String deviceId = LoginInit.getInstance(this.d).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        d.put("source", "1");
        d.put("timestamp", String.valueOf(eie.b()));
        d.put("deviceId", deviceId);
        d.put("sysVersion", Build.VERSION.RELEASE);
        d.put("bindDeviceType", String.valueOf(CommonUtil.h(this.d)));
        d.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        d.put("ts", String.valueOf(eie.b()));
        d.put("iVersion", "5");
        String e = mtj.e(null);
        if (e != null) {
            e = e.toLowerCase();
        }
        d.put("lang", e);
        d.put("siteId", LoginInit.getInstance(this.d).getAccountInfo(1009));
        d.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        d.put("countryCode", LoginInit.getInstance(this.d).getAccountInfo(1010));
        return d;
    }
}

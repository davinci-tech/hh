package com.huawei.hwcloudmodel.healthdatacloud;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.knx;
import defpackage.lql;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class HealthDataCloudFactory implements ParamsFactory {
    private Context e;

    public HealthDataCloudFactory(Context context) {
        if (context == null) {
            this.e = BaseApplication.e();
        } else {
            this.e = context.getApplicationContext();
        }
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put(j2.v, "gzip, deflate");
        String e = KeyValDbManager.b(this.e).e("user_id");
        if (TextUtils.isEmpty(e)) {
            LogUtil.a("HealthDataCloudFactory", "huid isEmpty");
            LoginInit.getInstance(this.e).moveInfoFromSPTODB();
        }
        if ("0".equals(e)) {
            LogUtil.b("HealthDataCloudFactory", "huid is 0");
        }
        hashMap.put("x-huid", e);
        hashMap.put("x-version", d(this.e));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("Connection", w9.j);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> c = lql.c(obj);
        c(c);
        boolean d = CloudUtils.d();
        LogUtil.a("HealthDataCloudFactory", "callService isOverSea =", Boolean.valueOf(d));
        if (d) {
            c.put("siteId", String.valueOf(LoginInit.getInstance(this.e).getSiteId()));
            LogUtil.a("HealthDataCloudFactory", "callService getSiteId=", Integer.valueOf(LoginInit.getInstance(this.e).getSiteId()));
        }
        c.put("deviceType", String.valueOf(CommonUtil.h(this.e)));
        String deviceType = LoginInit.getInstance(this.e).getDeviceType();
        LogUtil.c("HealthDataCloudFactory", "callService upDeviceType = ", deviceType);
        c.put("upDeviceType", deviceType);
        String deviceId = LoginInit.getInstance(this.e).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        LogUtil.c("HealthDataCloudFactory", "callService deviceId=", deviceId);
        c.put("deviceId", deviceId);
        c.put("sysVersion", Build.VERSION.RELEASE);
        c.put("isManually", Integer.valueOf(lql.c()));
        c.put("language", MLAsrConstants.LAN_ZH);
        c.put("oaId", "");
        if ("true".equals(KeyValDbManager.b(this.e).e("cloud_user_privacy10"))) {
            c.put("isTrackingEnabled", 1);
        } else {
            c.put("isTrackingEnabled", 0);
        }
        c.put("improveState", Boolean.valueOf(knx.e()));
        c.put("currentManufacturer", Build.MANUFACTURER);
        c.put("rom", Build.DISPLAY);
        if (Utils.i()) {
            return c;
        }
        LogUtil.b("HealthDataCloudFactory", "request HEALTH_CLOUD in no-cloud country");
        return new HashMap(0);
    }

    private String d(Context context) {
        String c = CommonUtil.c(context);
        String b = SharedPreferenceManager.b(context, String.valueOf(10008), "key_download_config");
        return (b == null || !b.contains("lfhealthtest2")) ? CommonUtil.cg() ? c.replace("-Test3rdDevice", "-wearBeta") : c : "and_health_10.1.1.999";
    }

    private void c(Map<String, Object> map) {
        Object d;
        map.put("ts", Long.valueOf(System.currentTimeMillis()));
        map.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        String accountInfo = LoginInit.getInstance(this.e).getAccountInfo(1008);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("HealthDataCloudFactory", "callService severToken is null!");
            LoginInit.getInstance(this.e).moveInfoFromSPTODB();
            if (LoginInit.getInstance(this.e).getIsLogined()) {
                LoginInit.tryLoginWhenTokenInvalid();
            }
        }
        map.put("token", accountInfo);
        if (LoginInit.getInstance(this.e).isLoginedByWear()) {
            LogUtil.a("HealthDataCloudFactory", "callService appid wear logined");
            map.put("source", 2);
            d = "com.huawei.bone";
        } else {
            LogUtil.a("HealthDataCloudFactory", "callService appid health logined");
            d = BaseApplication.d();
            map.put("source", 1);
        }
        map.put("appId", d);
    }
}

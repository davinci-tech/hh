package com.huawei.hwcloudmodel.model;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.knx;
import defpackage.lql;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class WifiDataCloudFactory implements ParamsFactory {
    private static final String APP_ID = "appId";
    private static final String BI_UPLOAD_ENABLE = "improveState";
    private static final String CURRENT_MANUFACTURER = "currentManufacturer";
    private static final String DEVICE_IMEI = "deviceId";
    private static final String DEVICE_TYPE = "deviceType";
    private static final String IS_MANUALLY = "isManually";
    private static final String IS_TRACKING_ENABLED = "isTrackingEnabled";
    private static final String I_VERSION = "iVersion";
    private static final String LANGUAGE = "language";
    private static final String OAID = "oaId";
    private static final int PRIVACY_GOODS = 10;
    private static final String SITE_ID = "siteId";
    private static final String SOURCE = "source";
    private static final String SYS_VERSION = "sysVersion";
    private static final String TAG = "WifiDataCloudFactory";
    private static final String TOKEN = "token";
    private static final String TOKEN_TYPE = "tokenType";
    private static final String TS = "ts";
    private static final String UP_DEVICE_TYPE = "upDeviceType";
    private static final String USER_PRIVACY_KEY = "cloud_user_privacy";
    private Context context;

    public WifiDataCloudFactory(Context context) {
        if (context == null) {
            this.context = BaseApplication.getContext();
        } else {
            this.context = context.getApplicationContext();
        }
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put(j2.v, "gzip, deflate");
        String e = KeyValDbManager.b(this.context).e("user_id");
        if (TextUtils.isEmpty(e)) {
            LogUtil.h(TAG, "huid isEmpty");
            LoginInit.getInstance(this.context).moveInfoFromSPTODB();
        }
        if ("0".equals(e)) {
            LogUtil.h(TAG, "huid is 0");
        }
        hashMap.put("x-huid", e);
        hashMap.put("x-version", dealAiLifeBetaName(this.context));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("Connection", w9.j);
        return hashMap;
    }

    private String dealAiLifeBetaName(Context context) {
        String c = CommonUtil.c(context);
        String b = SharedPreferenceManager.b(context, String.valueOf(10008), "key_download_config");
        return (b == null || !b.contains("lfhealthtest2")) ? c : "and_health_10.1.1.999";
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> hashMap = new HashMap<>(16);
        if (obj != null && (hashMap = lql.d(obj)) == null) {
            hashMap = new HashMap<>(16);
        }
        putBodyParam(hashMap);
        boolean d = CloudUtils.d();
        LogUtil.a(TAG, "callService isOverSea =", Boolean.valueOf(d));
        if (d) {
            hashMap.put("siteId", LoginInit.getInstance(this.context).getAccountInfo(1009));
        }
        hashMap.put("deviceType", String.valueOf(CommonUtil.h(this.context)));
        hashMap.put("upDeviceType", LoginInit.getInstance(this.context).getDeviceType());
        String deviceId = LoginInit.getInstance(this.context).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("iVersion", Integer.valueOf(Utils.b()));
        hashMap.put(IS_MANUALLY, Integer.valueOf(Utils.c()));
        hashMap.put("language", MLAsrConstants.LAN_ZH);
        hashMap.put(OAID, "");
        if ("true".equals(KeyValDbManager.b(this.context).e("cloud_user_privacy10"))) {
            hashMap.put(IS_TRACKING_ENABLED, 1);
        } else {
            hashMap.put(IS_TRACKING_ENABLED, 0);
        }
        hashMap.put(BI_UPLOAD_ENABLE, Boolean.valueOf(knx.e()));
        hashMap.put(CURRENT_MANUFACTURER, Build.MANUFACTURER);
        return hashMap;
    }

    private void putBodyParam(Map<String, Object> map) {
        Object appPackage;
        map.put("ts", Long.valueOf(System.currentTimeMillis()));
        map.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        String accountInfo = LoginInit.getInstance(this.context).getAccountInfo(1008);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b(TAG, "callService severToken is null!");
            LoginInit.getInstance(this.context).moveInfoFromSPTODB();
            if (LoginInit.getInstance(this.context).getIsLogined()) {
                LoginInit.tryLoginWhenTokenInvalid();
            }
        }
        map.put("token", accountInfo);
        if (LoginInit.getInstance(this.context).isLoginedByWear()) {
            LogUtil.a(TAG, "callService appid wear logined");
            map.put("source", 2);
            appPackage = "com.huawei.bone";
        } else {
            LogUtil.a(TAG, "callService appid health logined");
            appPackage = BaseApplication.getAppPackage();
            map.put("source", 1);
        }
        map.put("appId", appPackage);
    }

    public String getRequestUrl(String str) {
        String b;
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("healthDeviceUrl");
        if (CommonUtil.as() && (b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "key_download_config")) != null && b.contains("lfhealthtest2")) {
            url = b;
        }
        return url + str;
    }
}

package com.huawei.operation.h5pro.jsmodules;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.h5pro.DataTransferInterceptorImpl;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.eil;
import defpackage.lqi;
import defpackage.lql;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
class HealthCloudOperate {
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String TAG = "HealthCloudOperate";
    private final lqi mNetworkClientMgr = lqi.d();
    private final HealthDataCloudFactory mHealthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());

    HealthCloudOperate() {
    }

    void request(String str, final ResultCallback<String> resultCallback) {
        LogUtil.a(TAG, "request");
        if (resultCallback == null) {
            LogUtil.h(TAG, "callback is null");
            return;
        }
        final ParamObj paramObj = (ParamObj) GsonUtil.parseContainsMapJson(str, ParamObj.class);
        if (paramObj == null) {
            resultCallback.onFailure(new RuntimeException("post: failed to parse the parameter"));
            return;
        }
        String str2 = paramObj.urlKey;
        if (TextUtils.isEmpty(str2)) {
            resultCallback.onFailure(new RuntimeException("post: urlKey is empty"));
            return;
        }
        final String str3 = paramObj.urlPath;
        final String str4 = paramObj.method;
        GRSManager.a(BaseApplication.getContext()).e(str2, new GrsQueryCallback() { // from class: com.huawei.operation.h5pro.jsmodules.HealthCloudOperate.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str5) {
                if (TextUtils.isEmpty(str5)) {
                    resultCallback.onFailure(new RuntimeException(String.format(Locale.ENGLISH, "%s: baseUrl(GRS) is empty", str4)));
                } else if ("POST".equalsIgnoreCase(str4) || "GET".equalsIgnoreCase(str4)) {
                    HealthCloudOperate.this.request(str4, String.format(Locale.ENGLISH, "%s%s", str5, str3), paramObj, resultCallback);
                } else {
                    resultCallback.onFailure(new RuntimeException(String.format(Locale.ENGLISH, "this method(%s) is not supported currently", str4)));
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                resultCallback.onFailure(new RuntimeException(String.format(Locale.ENGLISH, "%s: failed to obtain the GRS, code is %d", str4, Integer.valueOf(i))));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void request(String str, String str2, ParamObj paramObj, ResultCallback<String> resultCallback) {
        LogUtil.c(TAG, str + ": url -> " + str2);
        boolean isTrustedNetworkApi = DataTransferInterceptorImpl.isTrustedNetworkApi(str2);
        Map<String, String> commonHeaders = getCommonHeaders();
        Map<? extends String, ? extends String> map = paramObj.headers;
        String str3 = paramObj.body;
        Map<String, Object> map2 = paramObj.bodyMap;
        if (map != null && !map.isEmpty()) {
            if (isTrustedNetworkApi) {
                commonHeaders.putAll(DataTransferInterceptorImpl.initHeaders(map));
            } else {
                commonHeaders.putAll(map);
            }
        }
        LogUtil.c(TAG, str + ": requestHeaders -> " + commonHeaders);
        if (!TextUtils.isEmpty(str3)) {
            map2 = GsonUtil.parseMapJson(str3);
        }
        if (isTrustedNetworkApi) {
            map2 = DataTransferInterceptorImpl.initBody(map2);
        }
        if ("GET".equalsIgnoreCase(str)) {
            Map<String, String> initGetParams = initGetParams(this.mHealthDataCloudFactory.getBody(map2));
            LogUtil.c(TAG, "get: requestBody -> " + initGetParams);
            this.mNetworkClientMgr.c(str2, commonHeaders, initGetParams, String.class, resultCallback);
            return;
        }
        if ((map2 == null || map2.isEmpty()) && !TextUtils.isEmpty(str3)) {
            LogUtil.c(TAG, "post: body -> " + str3);
            this.mNetworkClientMgr.b(str2, commonHeaders, str3, String.class, resultCallback);
            return;
        }
        Map<String, Object> commonParams = getCommonParams();
        if (map2 != null && !map2.isEmpty()) {
            commonParams.putAll(map2);
        }
        String b = lql.b(commonParams);
        LogUtil.c(TAG, "post: requestBody -> " + b);
        this.mNetworkClientMgr.b(str2, commonHeaders, b, String.class, resultCallback);
    }

    private Map<String, String> getCommonHeaders() {
        Map<String, String> headers = this.mHealthDataCloudFactory.getHeaders();
        Context context = BaseApplication.getContext();
        if (!LoginInit.getInstance(context).isBrowseMode()) {
            headers.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            headers.put(CloudParamKeys.X_TOKEN, LoginInit.getInstance(context).getAccountInfo(1008));
        }
        headers.put(CloudParamKeys.X_CLIENT_VERSION, CommonUtil.c(context));
        headers.put(CloudParamKeys.X_TS, String.valueOf(System.currentTimeMillis()));
        headers.put(CloudParamKeys.X_APP_TYPE, String.valueOf(AppTypeUtils.getAppType()));
        headers.put(CloudParamKeys.X_SITE_ID, LoginInit.getInstance(context).getAccountInfo(1009));
        headers.put(CloudParamKeys.X_APP_ID, BaseApplication.getAppPackage());
        String deviceId = LoginInit.getInstance(context).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        headers.put(CloudParamKeys.X_DEVICE_ID, deviceId);
        headers.put(CloudParamKeys.X_DEVICE_TYPE, LoginInit.getInstance(context).getDeviceType());
        return headers;
    }

    private Map<String, Object> getCommonParams() {
        Map<String, Object> body = this.mHealthDataCloudFactory.getBody("");
        body.put("phoneType", PhoneInfoUtils.getHuaweiManufaturerOrEmui());
        body.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        String language = InnerApi.getLanguage();
        body.put("language", language);
        body.put("lang", language);
        body.put("manufacturer", Build.MANUFACTURER);
        body.put("timeZone", HiDateUtil.d((String) null));
        body.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        body.put("countryCode", loginInit.getAccountInfo(1010));
        body.put("siteId", loginInit.getAccountInfo(1009));
        return body;
    }

    private Map<String, String> initGetParams(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof String) {
                    hashMap.put(entry.getKey(), (String) value);
                } else {
                    hashMap.put(entry.getKey(), value == null ? "" : String.valueOf(value));
                }
            }
        }
        return hashMap;
    }

    static class ParamObj {

        @SerializedName("body")
        private String body;

        @SerializedName("bodyMap")
        private Map<String, Object> bodyMap;

        @SerializedName("headers")
        private Map<String, String> headers;

        @SerializedName("method")
        private String method;

        @SerializedName("urlKey")
        private String urlKey;

        @SerializedName("urlPath")
        private String urlPath;

        private ParamObj() {
        }
    }
}

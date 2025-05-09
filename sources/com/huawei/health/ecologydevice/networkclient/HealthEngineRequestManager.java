package com.huawei.health.ecologydevice.networkclient;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealthservice.util.HttpRequestApi;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.hianalytics.RequestFinishedInfo;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.embedded.w;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.lqi;
import defpackage.lql;
import defpackage.mtj;
import health.compact.a.BuildConfigProperties;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class HealthEngineRequestManager {
    private static final String APP_NAME;
    private static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    public static final String CONTENT_TYPE_KEY = "Content-type";
    private static final String CONTENT_TYPE_PARSE = "application/json";
    private static final String GRS_COUNTRYCODE_KEY = "grs_countrycode_key";
    private static final String GRS_INIT_INFO = "grs_init_info";
    public static final String GRS_KEY = "hmsAuthUrl";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    private static final int MAX_RETRY_COUNT = 2;
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    static final String METHOD_PUT = "PUT";
    private static final String NO_CACHE = "no-cache";
    public static final String PARAMS_DEVICE_CODE = "deviceCode";
    public static final String PARAMS_DEVICE_SN = "deviceSn";
    public static final String PARAMS_NAME_PROD_ID = "prodId";
    public static final String PARAMS_NAME_USER_ID = "uId";
    public static final String PARAMS_SINO_AUTH = "Sino-Auth";
    public static final String PARAMS_UNIQUE_ID = "uniqueId";
    public static final String PREFIX_ACCESS_TOKEN = "Bearer ";
    private static final int RESPONSE_CODE_TOKEN_ERR = 401;
    public static final String SERVICE_NAME = "com.huawei.health";
    public static final String SERVICE_SUFFIX;
    private static final String SERVICE_SUFFIX_NEW;
    private static final String TAG = "HealthEngineRequestManager";
    private static final String TAG_RELEASE = "DEVMGR_HealthEngineRequestManager";
    public static final String URI_CHECK_BIND_STATUS = "/healthkit-healthapp/v1/devices:bindStatusCheck";
    public static final String URI_UN_BIND = "/healthkit-healthapp/v1/devices:unBind";
    public static final String URL_BLOODSUGAR_UNBINDDEVICE = "/unBindDevice";
    public static final String URL_THIRDACCESSTOKENS_SINOCARE = "/healthkit/v1/thirdAccessTokens/sinocare";
    private int GRS_FORCEEXPIRE_INTERVAL;
    private RequestParamsBuilder mBuilder;
    private Handler mHandler;
    private ConcurrentHashMap<String, Submit<String>> mRequestTasks;
    private int mRetryCount;

    static {
        String str = "";
        SERVICE_SUFFIX = CommonUtil.cc() ? BleConstants.WEIGHT_KEY : "";
        if (BuildConfigProperties.b() == "customTest") {
            str = BleConstants.WEIGHT_KEY;
        } else if (BuildConfigProperties.e("IS_GREEN_VERSION", false)) {
            str = "green";
        }
        SERVICE_SUFFIX_NEW = str;
        APP_NAME = "healthcloud" + str;
    }

    private HealthEngineRequestManager() {
        this.GRS_FORCEEXPIRE_INTERVAL = 3600000;
        this.mRequestTasks = new ConcurrentHashMap<>();
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static class RequestParamsBuilder {
        private Map<String, Object> mBodyParams;
        private Map<String, Object> mHeaderParams;
        private String mMethod;
        private OnRequestCallBack mOnRequestCallBack;
        private Map<String, Object> mQueryParams;
        private String mTag;
        private String mUrl;

        public RequestParamsBuilder setRequestCallBack(OnRequestCallBack onRequestCallBack) {
            this.mOnRequestCallBack = onRequestCallBack;
            return this;
        }

        public RequestParamsBuilder setMethod(String str) {
            this.mMethod = str;
            return this;
        }

        public RequestParamsBuilder setHeaderParam(Map<String, Object> map) {
            this.mHeaderParams = map;
            return this;
        }

        public RequestParamsBuilder setQueryParams(Map<String, Object> map) {
            this.mQueryParams = map;
            return this;
        }

        public RequestParamsBuilder setBodyParam(Map<String, Object> map) {
            this.mBodyParams = map;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getUrl() {
            return this.mUrl;
        }

        public RequestParamsBuilder setUrl(String str) {
            this.mUrl = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getTag() {
            return this.mTag;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getMethod() {
            return this.mMethod;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<String, Object> getHeaderParams() {
            return this.mHeaderParams;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<String, Object> getQueryParams() {
            return this.mQueryParams;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<String, Object> getBodyParams() {
            return this.mBodyParams;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public OnRequestCallBack getOnRequestCallBack() {
            return this.mOnRequestCallBack;
        }

        public RequestParamsBuilder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public HealthEngineRequestManager create() {
            HealthEngineRequestManager.getInstance().setBuilder(this);
            return HealthEngineRequestManager.getInstance();
        }
    }

    public static HealthEngineRequestManager getInstance() {
        return Instance.INSTANCE;
    }

    public static Map<String, String> addPublicHeaders() {
        HashMap hashMap = new HashMap(10);
        Context context = BaseApplication.getContext();
        hashMap.put("x-version", CommonUtil.e(context));
        hashMap.put("Content-type", CONTENT_TYPE);
        hashMap.put(CACHE_CONTROL, NO_CACHE);
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
        }
        return hashMap;
    }

    public static Map<String, Object> addPublicParams() {
        HashMap hashMap = new HashMap(10);
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        Context context = BaseApplication.getContext();
        LoginInit loginInit = LoginInit.getInstance(context);
        hashMap.put("token", loginInit.getAccountInfo(1008));
        hashMap.put("appId", OperationUtils.getAppId(context));
        hashMap.put("deviceType", PhoneInfoUtils.getPhoneType());
        String deviceId = loginInit.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", OperationUtils.getSysVersion());
        hashMap.put("bindDeviceType", String.valueOf(CommonUtil.h(context)));
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        hashMap.put("iVersion", String.valueOf(CommonUtil.d(context)));
        hashMap.put("language", mtj.e(null));
        hashMap.put("environment", String.valueOf(CommonUtil.l(context)));
        if (Utils.o()) {
            hashMap.put("siteId", loginInit.getAccountInfo(1009));
        }
        hashMap.put("upDeviceType", loginInit.getDeviceType());
        return hashMap;
    }

    public void request() {
        RequestParamsBuilder requestParamsBuilder = this.mBuilder;
        if (requestParamsBuilder == null) {
            return;
        }
        this.mRetryCount = 0;
        if (!TextUtils.isEmpty(requestParamsBuilder.getUrl()) && this.mBuilder.getUrl().startsWith("http")) {
            buildRequest(this.mBuilder.getTag(), this.mBuilder);
        } else {
            LogUtil.h(TAG, "url is err");
            onFailCallBack(this.mBuilder.getTag(), this.mBuilder, -1, new Exception("url is err"));
        }
    }

    private void buildRequest(String str, RequestParamsBuilder requestParamsBuilder) {
        char c;
        LogUtil.a(TAG, "buildRequest, process access to the network in the child thread. tag = ", str);
        String method = this.mBuilder.getMethod();
        method.hashCode();
        int hashCode = method.hashCode();
        if (hashCode == 70454) {
            if (method.equals("GET")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 79599) {
            if (hashCode == 2461856 && method.equals("POST")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (method.equals("PUT")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            createGetRequestRunnable(str, requestParamsBuilder);
            return;
        }
        if (c == 1) {
            createPutRequestRunnable(str, requestParamsBuilder);
        } else if (c != 2) {
            LogUtil.h(TAG, "the request is not support:", requestParamsBuilder.getMethod());
        } else {
            createPostRequestRunnable(str, requestParamsBuilder);
        }
    }

    private void createPutRequestRunnable(String str, RequestParamsBuilder requestParamsBuilder) {
        HttpRequestApi httpRequestApi = (HttpRequestApi) lqi.d().b(HttpRequestApi.class);
        if (httpRequestApi == null) {
            LogUtil.b(TAG, "createPutRequestRunnable HttpRequestApi is null");
            return;
        }
        Submit<String> putReq = httpRequestApi.putReq(requestParamsBuilder.getUrl(), createRequestHeader(requestParamsBuilder.getHeaderParams()), RequestBody.create(MediaType.parse("application/json"), lql.b(requestParamsBuilder.getBodyParams())));
        this.mRequestTasks.put(str, putReq);
        createRequestRunnable(str, requestParamsBuilder, putReq);
    }

    private void createPostRequestRunnable(String str, RequestParamsBuilder requestParamsBuilder) {
        HttpRequestApi httpRequestApi = (HttpRequestApi) lqi.d().b(HttpRequestApi.class);
        if (httpRequestApi == null) {
            LogUtil.b(TAG, "createPostRequestRunnable HttpRequestApi is null");
            return;
        }
        Submit<String> postReq = httpRequestApi.getPostReq(requestParamsBuilder.getUrl(), createRequestHeader(requestParamsBuilder.getHeaderParams()), RequestBody.create(MediaType.parse("application/json"), lql.b(requestParamsBuilder.getBodyParams())));
        this.mRequestTasks.put(str, postReq);
        createRequestRunnable(str, requestParamsBuilder, postReq);
    }

    private void createGetRequestRunnable(String str, RequestParamsBuilder requestParamsBuilder) {
        Submit<String> req;
        HttpRequestApi httpRequestApi = (HttpRequestApi) lqi.d().b(HttpRequestApi.class);
        if (httpRequestApi != null) {
            if (requestParamsBuilder.getQueryParams() == null || requestParamsBuilder.getQueryParams().size() <= 0) {
                req = httpRequestApi.getReq(requestParamsBuilder.getUrl(), createRequestHeader(requestParamsBuilder.getHeaderParams()));
            } else {
                req = httpRequestApi.getReq(requestParamsBuilder.getUrl(), createRequestHeader(requestParamsBuilder.getHeaderParams()), requestParamsBuilder.getQueryParams());
            }
            this.mRequestTasks.put(str, req);
            createRequestRunnable(str, requestParamsBuilder, req);
            return;
        }
        LogUtil.b(TAG, "createGetRequestRunnable HttpRequestApi is null");
    }

    private void createRequestRunnable(final String str, final RequestParamsBuilder requestParamsBuilder, final Submit<String> submit) {
        final long currentTimeMillis = System.currentTimeMillis();
        submit.enqueue(new ResultCallback<String>() { // from class: com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager.1
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                LogUtil.a(HealthEngineRequestManager.TAG, "Http request needs ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " ms");
                HealthEngineRequestManager.this.onResponses(str, requestParamsBuilder, response);
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(HealthEngineRequestManager.TAG, "http request onFailure ", th.toString());
                if (submit.isCanceled()) {
                    return;
                }
                HealthEngineRequestManager.this.onFailCallBack(str, requestParamsBuilder, -1, th);
                HealthEngineRequestManager.this.requestFail(submit);
            }
        });
    }

    public static boolean isCanGrsForce(long j) {
        return SystemClock.elapsedRealtime() >= j;
    }

    public void requestFail(Submit<?> submit) {
        try {
            RequestFinishedInfo requestFinishedInfo = submit.request().getRequestFinishedInfo();
            RequestFinishedInfo.Metrics metrics = requestFinishedInfo.getMetrics();
            String dnsType = metrics.getDnsType();
            LogUtil.a(TAG, "DnsType : " + metrics.getDnsType() + " url: " + requestFinishedInfo.getUrl() + " : ");
            if ((w.l.equalsIgnoreCase(dnsType) || w.m.equalsIgnoreCase(dnsType)) && isCanGrsForce(this.GRS_FORCEEXPIRE_INTERVAL)) {
                forceExpire();
            }
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG, " fail to requestFail", ExceptionUtils.d(e));
        }
    }

    private void forceExpire() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager.2
            @Override // java.lang.Runnable
            public void run() {
                GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
                grsBaseInfo.setAppName(HealthEngineRequestManager.APP_NAME);
                String string = BaseApplication.getContext().getSharedPreferences(HealthEngineRequestManager.GRS_INIT_INFO, 0).getString(HealthEngineRequestManager.GRS_COUNTRYCODE_KEY, "");
                ReleaseLogUtil.e(HealthEngineRequestManager.TAG, "AppName : " + HealthEngineRequestManager.APP_NAME + " CountrySource:" + string);
                if (TextUtils.isEmpty(string)) {
                    ReleaseLogUtil.e(HealthEngineRequestManager.TAG, "countryCode is null");
                    return;
                }
                grsBaseInfo.setCountrySource(string);
                grsBaseInfo.setSerCountry(string);
                grsBaseInfo.setRegCountry(string);
                grsBaseInfo.setVersionName(Build.VERSION.RELEASE);
                GrsClient grsClient = new GrsClient(BaseApplication.getContext(), grsBaseInfo);
                ReleaseLogUtil.e(HealthEngineRequestManager.TAG, "grsClient.forceExpire()");
                grsClient.forceExpire();
            }
        });
    }

    public void cancelRequest(String str) {
        if (!this.mRequestTasks.containsKey(str)) {
            LogUtil.a(TAG, "HealthEngine http manager not contains tag");
            return;
        }
        Submit<String> submit = this.mRequestTasks.get(str);
        if (!submit.isExecuted()) {
            submit.cancel();
        }
        this.mRequestTasks.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void onResponses(String str, final RequestParamsBuilder requestParamsBuilder, Response<String> response) {
        String body = response.getRawResponse().getBody();
        if (response.isSuccessful() && !TextUtils.isEmpty(body)) {
            if (requestParamsBuilder.getOnRequestCallBack() != null) {
                ReleaseLogUtil.e(TAG_RELEASE, "tag:", str, "request success.");
                final Object fromJson = new Gson().fromJson(body, requestParamsBuilder.getOnRequestCallBack().getGenericType());
                this.mHandler.post(new Runnable() { // from class: com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthEngineRequestManager.RequestParamsBuilder.this.getOnRequestCallBack().onSuccess(fromJson);
                    }
                });
            }
            this.mRetryCount = 0;
            return;
        }
        if (response.getCode() == 401 && this.mRetryCount < 2) {
            LogUtil.h(TAG, "AT is expired");
            this.mRetryCount++;
            refreshToken(str, requestParamsBuilder);
        } else {
            this.mRetryCount = 0;
            onFailCallBack(str, requestParamsBuilder, response.getCode(), new Exception(response.getMessage()));
        }
    }

    private void refreshToken(String str, RequestParamsBuilder requestParamsBuilder) {
        createGetRequestRunnable(str, requestParamsBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFailCallBack(String str, final RequestParamsBuilder requestParamsBuilder, final int i, final Throwable th) {
        ReleaseLogUtil.d(TAG_RELEASE, "tag:", str, "errorCode:", Integer.valueOf(i), ", Exception:", th.getMessage());
        if (requestParamsBuilder.getOnRequestCallBack() != null) {
            this.mHandler.post(new Runnable() { // from class: com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    HealthEngineRequestManager.RequestParamsBuilder.this.getOnRequestCallBack().onFail(i, th);
                }
            });
        }
    }

    private Map<String, Object> createRequestHeader(Map<String, Object> map) {
        map.put("Content-type", CONTENT_TYPE);
        map.put(CACHE_CONTROL, NO_CACHE);
        return map;
    }

    static class Instance {
        private static final HealthEngineRequestManager INSTANCE = new HealthEngineRequestManager();

        private Instance() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBuilder(RequestParamsBuilder requestParamsBuilder) {
        this.mBuilder = requestParamsBuilder;
    }
}

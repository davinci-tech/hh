package com.huawei.hms.framework.network.restclient;

import android.content.Context;
import com.huawei.hms.framework.common.ExtLogger;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyResponse;
import com.huawei.hms.network.NetworkKit;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes.dex */
public class RestClientGlobalInstance {
    private static final String TAG = "RestClientGlobalInstance";
    private static RestClientGlobalInstance instance = new RestClientGlobalInstance();
    private RestClient restClient;

    private RestClientGlobalInstance() {
    }

    public static RestClientGlobalInstance getInstance() {
        return instance;
    }

    public RestClientGlobalInstance init(Context context) {
        HttpClientGlobalInstance.getInstance().init(context);
        return this;
    }

    @Deprecated
    public RestClientGlobalInstance setNetSDKType(String str) {
        HttpClientGlobalInstance.getInstance().setNetSDKType(str);
        return this;
    }

    public RestClientGlobalInstance initConnectionPool(int i, long j, TimeUnit timeUnit) {
        if (i > 0 && j > 0) {
            NetworkKit.getInstance().initConnectionPool(i, j, timeUnit);
        }
        return this;
    }

    public RestClient getRestClient() {
        RestClient restClient;
        synchronized (this) {
            if (this.restClient == null) {
                this.restClient = new RestClient.Builder().httpClient(HttpClientGlobalInstance.getInstance().getHttpClient()).addConverterFactory(new ToStringConverterFactory()).build();
            }
            restClient = this.restClient;
        }
        return restClient;
    }

    public void connect(String str, int i, ResultCallback<Void> resultCallback) {
        connect(getRestClient(), str, i, resultCallback);
    }

    public void connect(RestClient restClient, String str, int i, final ResultCallback<Void> resultCallback) {
        if (resultCallback == null) {
            Logger.w(TAG, "callback is null");
        }
        if (restClient == null || restClient.getHttpClient() == null) {
            Logger.w(TAG, "restClient is null or restClient.getHttpClient() is null");
        } else {
            HttpClientGlobalInstance.getInstance().connect(restClient.getHttpClient(), str, i, new Callback() { // from class: com.huawei.hms.framework.network.restclient.RestClientGlobalInstance.1
                @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
                public void onResponse(com.huawei.hms.framework.network.restclient.hwhttp.Submit submit, com.huawei.hms.framework.network.restclient.hwhttp.Response response) throws IOException {
                    try {
                        if (resultCallback == null) {
                            return;
                        }
                        resultCallback.onResponse(new Response(ProxyResponse.response2New(response)));
                    } finally {
                        response.close();
                    }
                }

                @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
                public void onFailure(com.huawei.hms.framework.network.restclient.hwhttp.Submit submit, Throwable th) {
                    ResultCallback resultCallback2 = resultCallback;
                    if (resultCallback2 == null) {
                        return;
                    }
                    resultCallback2.onFailure(th);
                }
            });
        }
    }

    @Deprecated
    public RestClientGlobalInstance enableHAFullReport() {
        Logger.w(TAG, "This deprecated method setting is invalid.");
        return this;
    }

    public RestClientGlobalInstance setHaTag(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("core_ha_tag", str);
            NetworkKit.getInstance().setOptions(jSONObject.toString());
        } catch (JSONException unused) {
            Logger.w(TAG, "HttpClientGlobalInstance setHaTag catch a JSONException");
        }
        return this;
    }

    public boolean checkConnectivity(String str) {
        return HttpClientGlobalInstance.getInstance().checkConnectivity(str);
    }

    @Deprecated
    public void enableNetDiag(boolean z) {
        Logger.w(TAG, "This deprecated method setting is invalid.");
    }

    public void enablePrivacyPolicy(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("core_enable_privacy_policy", z);
            NetworkKit.getInstance().setOptions(jSONObject.toString());
        } catch (JSONException unused) {
            Logger.w(TAG, "HttpClientGlobalInstance enablePrivacyPolicy catch a JSONException");
        }
    }

    public void addQuicHint(String str) {
        NetworkKit.getInstance().addQuicHint(false, str);
    }

    public void addQuicHint(List<String> list) {
        if (list == null) {
            return;
        }
        NetworkKit.getInstance().addQuicHint(false, (String[]) list.toArray(new String[0]));
    }

    public void addQuicHint(String str, boolean z) {
        NetworkKit.getInstance().addQuicHint(z, str);
    }

    public void addQuicHint(List<String> list, boolean z) {
        if (list == null) {
            return;
        }
        NetworkKit.getInstance().addQuicHint(z, (String[]) list.toArray(new String[0]));
    }

    public void evictAll() {
        NetworkKit.getInstance().evictAllConnections();
    }

    public void setExtLogger(ExtLogger extLogger, boolean z) {
        HttpClientGlobalInstance.getInstance().setExtLogger(extLogger, z);
    }
}

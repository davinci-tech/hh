package com.huawei.hms.network.httpclient.util;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.inner.DomainManager;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class PreConnectManager {
    public static final long CONNECT_INTERNAL = 10000;
    public static final long CONNECT_SUCCESS_INTERNAL = 180000;
    public static final String HTTPS_PRE = "https://";
    public static final String HTTP_METHOD_GET = "GET";
    public static final String TAG = "PreConnectManager";
    public volatile HttpClient httpClient;
    public Map<String, Long[]> preConnectInfos;

    public static class LazyHolder {
        public static final PreConnectManager INSTANCE = new PreConnectManager();
    }

    public HttpClient getHttpClient() {
        if (this.httpClient == null) {
            synchronized (PreConnectManager.class) {
                if (this.httpClient == null) {
                    NetworkKit.init(ContextHolder.getAppContext(), null);
                    this.httpClient = new HttpClient.Builder().retryTimeOnConnectionFailure(0).build();
                }
            }
        }
        return this.httpClient;
    }

    public void connect(String str, Callback callback) {
        if (DomainManager.getInstance().isExcludePrefetch(str)) {
            Logger.v(TAG, "exclude prefetch");
            return;
        }
        connect(getHttpClient(), "https://" + str, callback);
    }

    public void connect(HttpClient httpClient, String str, Callback callback) {
        if (callback == null) {
            Logger.w(TAG, "callback is null connect no effect");
            return;
        }
        if (!ifCanConnect(str)) {
            Logger.d(TAG, "return without do connect action");
            return;
        }
        updatePreConInfo(str, 0L);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = new JSONObject().put("inner_connect_empty_body", true);
        } catch (JSONException e) {
            Logger.w(TAG, "recordMap fail to put:", e);
        }
        httpClient.newSubmit(httpClient.newRequest().url(str).method("GET").options(jSONObject.toString()).build()).enqueue(callback);
    }

    public void clearInfo() {
        this.preConnectInfos.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreConInfo(String str, Long l) {
        this.preConnectInfos.put(str, new Long[]{l, Long.valueOf(System.currentTimeMillis())});
    }

    private boolean ifCanConnect(String str) {
        String str2;
        if (!this.preConnectInfos.containsKey(str)) {
            return true;
        }
        Long[] lArr = this.preConnectInfos.get(str);
        if (lArr[0].longValue() == 1 && System.currentTimeMillis() - lArr[1].longValue() <= 180000) {
            str2 = "this still a live connect, no need to new preconnect";
        } else {
            if (lArr[0].longValue() == 1 || System.currentTimeMillis() - lArr[1].longValue() > CONNECT_INTERNAL) {
                return true;
            }
            str2 = "has preconnect within 10 seconds, try so frequently";
        }
        Logger.d(TAG, str2);
        return false;
    }

    public static class ConnectCallBack extends Callback {
        @Override // com.huawei.hms.network.httpclient.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            response.close();
            if (submit.getRequestFinishedInfo() == null) {
                Logger.w(PreConnectManager.TAG, "RequestFinishedInfo is null");
                return;
            }
            PreConnectManager.getInstance().updatePreConInfo("https://" + submit.getRequestFinishedInfo().getHost(), 1L);
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onFailure(Submit submit, Throwable th) {
            if (submit.getRequestFinishedInfo() == null) {
                Logger.w(PreConnectManager.TAG, "RequestFinishedInfo is null");
                return;
            }
            PreConnectManager.getInstance().updatePreConInfo("https://" + submit.getRequestFinishedInfo().getHost(), -1L);
        }
    }

    public static PreConnectManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public PreConnectManager() {
        this.preConnectInfos = new ConcurrentHashMap();
    }
}

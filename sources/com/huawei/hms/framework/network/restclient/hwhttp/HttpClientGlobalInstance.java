package com.huawei.hms.framework.network.restclient.hwhttp;

import android.content.Context;
import com.huawei.hms.framework.common.ExtLogger;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.restclient.config.DefaultNetworkConfig;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.api.advance.AdvanceNetworkKit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes4.dex */
public class HttpClientGlobalInstance {
    private static final String TAG = "HttpClientGlobalInstance";
    private static HttpClientGlobalInstance instance = new HttpClientGlobalInstance();
    private HttpClient httpClient;
    private boolean isInit = false;
    private String netSdkType;

    private HttpClientGlobalInstance() {
    }

    public static HttpClientGlobalInstance getInstance() {
        return instance;
    }

    public HttpClientGlobalInstance init(Context context) {
        if (!this.isInit) {
            synchronized (this) {
                Logger.w(TAG, "com.huawei.hms:network-restclient and com.huawei.hms:network-restclient-anno will not receive new requirements in the future. The maintenance deadline is December 31, 2021. You are advised to switch services You are advised to switch services to com.huawei.hms:network-embedded before this time point");
                NetworkKit.init(context, null);
                this.isInit = true;
            }
        }
        return this;
    }

    public HttpClient getHttpClient() {
        synchronized (this) {
            if (!this.isInit) {
                return null;
            }
            if (this.httpClient == null) {
                this.httpClient = new HttpClient.Builder().isReportable(true).enableQuic(false).build();
            }
            return this.httpClient;
        }
    }

    public void setExtLogger(ExtLogger extLogger, boolean z) {
        AdvanceNetworkKit.getInstance().setWrapperLogger(new WrapExtLogger(extLogger), z);
    }

    public void connect(String str, int i, Callback callback) {
        connect(getHttpClient(), str, i, callback);
    }

    public void connect(HttpClient httpClient, String str, int i, Callback callback) {
        if (callback == null) {
            Logger.w(TAG, "callback is null connect no effect");
            return;
        }
        Request build = new Request.Builder().url(str).method("GET").onlyConnect(true).build();
        for (int i2 = 0; i2 < i; i2++) {
            httpClient.newSubmit(build).enqueue(callback);
        }
    }

    public void enableDetect(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DefaultNetworkConfig.ENABLE_DETECT_WITH_HTTP, z);
            NetworkKit.getInstance().setOptions(jSONObject.toString());
        } catch (JSONException unused) {
            Logger.w(TAG, "HttpClientGlobalInstance detectEnable catch a JSONException");
        }
    }

    @Deprecated
    public void enableNetDiag(boolean z) {
        Logger.w(TAG, "This deprecated method setting is invalid.");
    }

    public boolean checkConnectivity(String str) {
        return AdvanceNetworkKit.getInstance().checkConnectivity();
    }

    @Deprecated
    public void setNetSDKType(String str) {
        this.netSdkType = str;
    }

    @Deprecated
    public String getNetSdkType() {
        return this.netSdkType;
    }

    public HttpClientGlobalInstance setHaTag(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("core_ha_tag", str);
            NetworkKit.getInstance().setOptions(jSONObject.toString());
        } catch (JSONException unused) {
            Logger.w(TAG, "HttpClientGlobalInstance setHaTag catch a JSONException");
        }
        return this;
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

    @Deprecated
    public HttpClientGlobalInstance enableHAFullReport() {
        Logger.w(TAG, "This deprecated method setting is invalid.");
        return this;
    }

    public HttpClientGlobalInstance initConnectionPool(int i, long j, TimeUnit timeUnit) {
        if (i > 0 && j > 0) {
            NetworkKit.getInstance().initConnectionPool(i, j, timeUnit);
        }
        return this;
    }
}

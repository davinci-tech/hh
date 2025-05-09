package com.huawei.health.h5pro.jsbridge.system.network;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.network.RequestObj;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import defpackage.lqi;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class NetWorkEntry extends JsBaseModule {
    public static DataTransferInterceptor e;
    public lqi c;

    @JavascriptInterface
    public void request(long j, String str) {
        H5ProInstance h5ProInstance;
        LogUtil.i(this.TAG, "request");
        RequestObj requestObj = (RequestObj) GsonUtil.parseContainsMapJson(str, RequestObj.class);
        if (requestObj == null || TextUtils.isEmpty(requestObj.getUrl())) {
            onFailureCallback(j, "Invalid parameter.");
            return;
        }
        DataTransferInterceptor dataTransferInterceptor = e;
        if (dataTransferInterceptor != null && (h5ProInstance = this.mH5ProInstance) != null) {
            requestObj = dataTransferInterceptor.dataIntercept(h5ProInstance.getAppInfo(), this.mH5ProInstance.getUrl(), requestObj);
        }
        String upperCase = requestObj.getRequestType().toUpperCase(Locale.ENGLISH);
        RequestObj.RequestType requestType = RequestObj.RequestType.POST;
        if (TextUtils.equals(upperCase, "POST")) {
            b(j, requestObj);
            return;
        }
        String upperCase2 = requestObj.getRequestType().toUpperCase(Locale.ENGLISH);
        RequestObj.RequestType requestType2 = RequestObj.RequestType.GET;
        if (TextUtils.equals(upperCase2, "GET")) {
            c(j, requestObj);
            return;
        }
        String upperCase3 = requestObj.getRequestType().toUpperCase(Locale.ENGLISH);
        RequestObj.RequestType requestType3 = RequestObj.RequestType.DELETE;
        if (TextUtils.equals(upperCase3, ProfileRequestConstants.DELETE_TYPE)) {
            a(j, requestObj);
            return;
        }
        String upperCase4 = requestObj.getRequestType().toUpperCase(Locale.ENGLISH);
        RequestObj.RequestType requestType4 = RequestObj.RequestType.PUT;
        if (TextUtils.equals(upperCase4, ProfileRequestConstants.PUT_TYPE)) {
            d(j, requestObj);
            return;
        }
        LogUtil.w(this.TAG, requestObj.getRequestType() + ": Not implemented");
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.c = null;
    }

    public static void setDataTransferInterceptor(DataTransferInterceptor dataTransferInterceptor) {
        e = dataTransferInterceptor;
    }

    private void d(long j, RequestObj requestObj) {
        LogUtil.i(this.TAG, "put");
        if (this.c == null) {
            this.c = lqi.d();
        }
        String requestBody = requestObj.getRequestBody();
        lqi lqiVar = this.c;
        String url = requestObj.getUrl();
        Map<String, String> headers = requestObj.getHeaders();
        if (TextUtils.isEmpty(requestBody)) {
            requestBody = GsonUtil.toJson(requestObj.getBody());
        }
        lqiVar.c(url, headers, requestBody, String.class, new InnerResultCallback(j, this));
    }

    private void b(long j, RequestObj requestObj) {
        LogUtil.i(this.TAG, "post");
        if (this.c == null) {
            this.c = lqi.d();
        }
        String requestBody = requestObj.getRequestBody();
        lqi lqiVar = this.c;
        String url = requestObj.getUrl();
        Map<String, String> headers = requestObj.getHeaders();
        if (TextUtils.isEmpty(requestBody)) {
            requestBody = GsonUtil.toJson(requestObj.getBody());
        }
        lqiVar.b(url, headers, requestBody, String.class, new InnerResultCallback(j, this));
    }

    private String e(String str, RequestObj requestObj) {
        if (!Patterns.WEB_URL.matcher(str).matches()) {
            LogUtil.w(this.TAG, "initUrlParams: invalid url -> " + str);
            return str;
        }
        if (!TextUtils.isEmpty(Uri.parse(str).getQuery())) {
            return str;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        e(hashMap, requestObj.getBody());
        e(hashMap, GsonUtil.parseMapJson(requestObj.getRequestBody()));
        if (hashMap.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            sb.append(TextUtils.concat(entry.getKey(), "=", entry.getValue(), "&"));
        }
        return TextUtils.concat(str, "?", sb.substring(0, sb.length() - 1)).toString();
    }

    private void e(HashMap<String, String> hashMap, Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            hashMap.put(str, obj instanceof String ? (String) obj : obj == null ? "" : String.valueOf(obj));
        }
    }

    /* loaded from: classes8.dex */
    public static class InnerResultCallback implements ResultCallback<String> {

        /* renamed from: a, reason: collision with root package name */
        public final long f2411a;
        public final WeakReference<NetWorkEntry> e;

        @Override // com.huawei.networkclient.ResultCallback
        public void onSuccess(String str) {
            NetWorkEntry netWorkEntry = (NetWorkEntry) GeneralUtil.getReferent(this.e);
            if (netWorkEntry != null) {
                netWorkEntry.onSuccessCallback(this.f2411a, str);
                return;
            }
            LogUtil.w("InnerResultCallback", "onSuccess: netWorkEntry is null, " + this.f2411a);
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            NetWorkEntry netWorkEntry = (NetWorkEntry) GeneralUtil.getReferent(this.e);
            if (netWorkEntry != null) {
                netWorkEntry.onFailureCallback(this.f2411a, th == null ? Constants.NULL : th.getMessage());
                return;
            }
            LogUtil.w("InnerResultCallback", "onFailure: netWorkEntry is null, " + this.f2411a);
        }

        public InnerResultCallback(long j, NetWorkEntry netWorkEntry) {
            this.f2411a = j;
            this.e = new WeakReference<>(netWorkEntry);
        }
    }

    private void c(long j, RequestObj requestObj) {
        LogUtil.i(this.TAG, "get");
        HashMap<String, String> hashMap = new HashMap<>();
        e(hashMap, requestObj.getBody());
        e(hashMap, GsonUtil.parseMapJson(requestObj.getRequestBody()));
        if (this.c == null) {
            this.c = lqi.d();
        }
        this.c.c(requestObj.getUrl(), requestObj.getHeaders(), hashMap, String.class, new InnerResultCallback(j, this));
    }

    private void a(long j, RequestObj requestObj) {
        LogUtil.i(this.TAG, "delete");
        if (this.c == null) {
            this.c = lqi.d();
        }
        this.c.e(e(requestObj.getUrl(), requestObj), requestObj.getHeaders(), String.class, new InnerResultCallback(j, this));
    }
}

package com.huawei.up.request;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.hwbasemgr.CloudUrlManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.utils.HttpConnectionAdaptor;
import com.huawei.up.utils.NSPException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes7.dex */
public abstract class HttpRequestBase {
    private static final int CONNECT_SECONDS_TIMEOUT = 30;
    public static final String REQUEST_CLIENT_TYPE = "39";
    private static final String TAG = "HttpRequestBase";
    public static final String TAG_ERROR_DESC = "errorDesc";
    protected static final String TAG_RESULT = "result";
    public static final String TAG_RESULT_CODE = "resultCode";
    public static final String UP_SERVER_URL_CHINA = "/AccountServer";
    private static String sBaseUrl = "";
    private static String sBaseUrlKey;
    private static CloudUrlManager sCloudUrlManager;
    private Context mContext;
    private String mRequestURL;

    protected abstract String getRealRequestUrl(String str);

    public abstract HashMap<String, String> packageRequest();

    public abstract String packageXML() throws IllegalArgumentException, IllegalStateException, IOException;

    public abstract Bundle unPackageRequest(HttpResponse httpResponse);

    public abstract Bundle unPackageXML(String str) throws XmlPullParserException, IOException;

    public HttpRequestBase(Context context) {
        this.mContext = context;
    }

    public static void resetBaseUrl(CloudUrlManager cloudUrlManager, String str) {
        LogUtil.a(TAG, "resetBaseUrl baseUrlKey=", str);
        sCloudUrlManager = cloudUrlManager;
        sBaseUrlKey = str;
        sBaseUrl = "";
    }

    private static String getBaseUrl() {
        if (TextUtils.isEmpty(sBaseUrl)) {
            CloudUrlManager cloudUrlManager = sCloudUrlManager;
            String url = cloudUrlManager != null ? cloudUrlManager.getUrl(sBaseUrlKey) : null;
            if (!TextUtils.isEmpty(url)) {
                sBaseUrl = url + UP_SERVER_URL_CHINA;
            } else {
                LogUtil.b(TAG, "getBaseUrl is empty, baseUrlKey=", sBaseUrlKey);
            }
        }
        return sBaseUrl;
    }

    public final String getRequestUrl() {
        if (TextUtils.isEmpty(this.mRequestURL)) {
            this.mRequestURL = getRealRequestUrl(getBaseUrl());
        }
        return this.mRequestURL;
    }

    public HttpResponse execute(HashMap<String, String> hashMap) {
        HttpHost httpHost;
        HttpClient httpClient;
        HttpResponse execute;
        HttpPost httpPost = HttpConnectionAdaptor.getHttpPost(getRequestUrl(), 30, 30, false);
        try {
            httpHost = HttpConnectionAdaptor.getHttpHost(getRequestUrl());
        } catch (NSPException e) {
            LogUtil.c(TAG, "execute NSPException() e:", e.getMessage());
            httpHost = null;
        }
        if (httpPost == null) {
            LogUtil.c(TAG, "execute httpPost is null");
            return null;
        }
        if (httpHost == null) {
            LogUtil.c(TAG, "execute httpHost is null");
            return null;
        }
        String valueOf = String.valueOf(System.currentTimeMillis());
        if (valueOf != null && valueOf.length() > 0) {
            httpPost.addHeader("Authorization", valueOf);
        }
        try {
            httpClient = HttpConnectionAdaptor.getHttpClient(this.mContext, getRequestUrl());
        } catch (NSPException e2) {
            LogUtil.c(TAG, "execute NSPException() e:", e2.getMessage());
            httpClient = null;
        }
        if (httpClient == null) {
            LogUtil.c(TAG, "execute httpClient is null");
            return null;
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePairParam(hashMap), "UTF-8"));
        } catch (UnsupportedEncodingException e3) {
            LogUtil.c(TAG, "execute UnsupportedEncodingException() e:", e3.getMessage());
        }
        try {
            if (httpClient instanceof HttpClient) {
                HttpClient httpClient2 = httpClient;
                execute = ApacheClientInstrumentation.execute(httpClient, httpHost, httpPost);
            } else {
                execute = httpClient.execute(httpHost, httpPost);
            }
            return execute;
        } catch (IOException e4) {
            LogUtil.c(TAG, "execute IOException() e:", e4.getMessage());
            return null;
        }
    }

    private List<NameValuePair> getNameValuePairParam(HashMap<String, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if (hashMap != null && hashMap.size() > 0) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                arrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                sb.append("&" + entry.getKey() + "=" + entry.getValue());
            }
        }
        LogUtil.c(TAG, "execute() url=", getRequestUrl(), ", data=", sb.toString());
        return arrayList;
    }

    public String execute(String str) {
        HttpHost httpHost;
        HttpClient httpClient;
        HttpResponse execute;
        LogUtil.c(TAG, "execute requestURL=", getRequestUrl(), ", xml=", str);
        HttpPost httpPost = HttpConnectionAdaptor.getHttpPost(getRequestUrl(), 30, 30, false);
        HttpResponse httpResponse = null;
        try {
            httpHost = HttpConnectionAdaptor.getHttpHost(getRequestUrl());
        } catch (NSPException e) {
            LogUtil.c(TAG, "execute NSPException() e=", e.getMessage());
            httpHost = null;
        }
        if (httpPost == null) {
            LogUtil.h(TAG, "execute httpPost == null");
            return "";
        }
        if (httpHost == null) {
            LogUtil.h(TAG, "execute httpPost == null");
            return "";
        }
        httpPost.addHeader("Content-Type", "text/xml");
        try {
            httpPost.setEntity(new StringEntity(str));
        } catch (UnsupportedEncodingException e2) {
            LogUtil.c(TAG, "execute UnsupportedEncodingException e=", e2.getMessage());
        }
        try {
            httpClient = HttpConnectionAdaptor.getHttpClient(this.mContext, getRequestUrl());
        } catch (NSPException e3) {
            LogUtil.c(TAG, "execute NSPException() e=", e3.getMessage());
            httpClient = null;
        }
        if (httpClient == null) {
            LogUtil.h(TAG, "execute httpClient == null");
            return "";
        }
        try {
            if (httpClient instanceof HttpClient) {
                HttpClient httpClient2 = httpClient;
                execute = ApacheClientInstrumentation.execute(httpClient, httpHost, httpPost);
            } else {
                execute = httpClient.execute(httpHost, httpPost);
            }
            httpResponse = execute;
        } catch (IOException e4) {
            LogUtil.c(TAG, "execute IOException() e=", e4.getMessage());
        }
        if (httpResponse == null) {
            return "";
        }
        try {
            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e5) {
            LogUtil.c(TAG, "execute IOException() e1=", e5.getMessage());
            return "";
        }
    }
}

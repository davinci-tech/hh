package com.huawei.up.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwnetworkmodel.TrafficMonitoringService;
import com.huawei.secure.android.common.ssl.SecureApacheSSLSocketFactory;
import defpackage.kub;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/* loaded from: classes7.dex */
public class HttpConnectionAdaptor {
    private static final int DEFAULTHTTPPORT = 80;
    private static final int DEFAULTHTTPSPORT = 443;
    public static final int DEFAULT_MAX_CONNECTIONS_LOCAL_ROUTE = 100;
    public static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 20;
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 800;
    private static final int DEFULT_GROWTH = 2;
    private static final int DEFULT_MS = 1000;
    private static final String HTTPS_STR = "https";
    private static final String HTTP_HOST_NAME = "api.vmall.com";
    private static final int INVALID_CODE = -1;
    private static final Object LOCK_OBJECT = new Object();
    private static final String TAG = "HttpConnetionAdaptor";
    private static HttpClient httpClient;
    private static HttpParams httpParams;
    private static kub limitedHttpClient;
    private static Context mContext;
    private static PlainSocketFactory plainSocketFactory;
    private static TrafficMonitoringService service;
    private static SSLSocketFactory sf;
    private static SchemeRegistry supportedSchemes;

    private HttpConnectionAdaptor() {
    }

    public static HttpPost getHttpPost(String str, int i, int i2, boolean z) {
        HttpPost httpPost = (HttpPost) new WeakReference(new HttpPost(str)).get();
        if (httpPost == null) {
            return httpPost;
        }
        setHttpParams(httpPost, i, i2, z);
        return httpPost;
    }

    public static HttpClient getHttpClient(Context context, String str) throws NSPException {
        HttpClient httpClient2;
        synchronized (LOCK_OBJECT) {
            mContext = context.getApplicationContext();
            if (httpClient == null) {
                httpClient = createHttpClient(context);
            }
            setScheme(str);
            httpClient.getParams().setParameter("accept-encoding", com.huawei.openalliance.ad.constant.Constants.GZIP);
            httpClient2 = httpClient;
        }
        return httpClient2;
    }

    public static kub getLimitedHttpClient(Context context, String str) throws NSPException {
        kub kubVar;
        synchronized (LOCK_OBJECT) {
            mContext = context.getApplicationContext();
            if (limitedHttpClient == null) {
                limitedHttpClient = createLimitedHttpClient(context);
            }
            setLimitedScheme(str);
            limitedHttpClient.a().setParameter("accept-encoding", com.huawei.openalliance.ad.constant.Constants.GZIP);
            kubVar = limitedHttpClient;
        }
        return kubVar;
    }

    private static void setScheme(String str) throws NSPException {
        URLInfo uRLInfo = getURLInfo(str);
        judgeRegistryName(uRLInfo, getSchemeName(uRLInfo), httpClient.getConnectionManager().getSchemeRegistry());
    }

    private static void judgeRegistryName(URLInfo uRLInfo, String str, SchemeRegistry schemeRegistry) {
        if (schemeRegistry.get(str) == null) {
            if (uRLInfo.isHttps) {
                schemeRegistry.register(new Scheme(str, sf, uRLInfo.port));
            } else {
                schemeRegistry.register(new Scheme(str, plainSocketFactory, uRLInfo.port));
            }
        }
    }

    private static void setLimitedScheme(String str) throws NSPException {
        URLInfo uRLInfo = getURLInfo(str);
        judgeRegistryName(uRLInfo, getSchemeName(uRLInfo), limitedHttpClient.b().getSchemeRegistry());
    }

    private static HttpClient createHttpClient(Context context) throws NSPException {
        try {
            initSocketFactory(context);
            return (HttpClient) new WeakReference(new DefaultHttpClient(new ThreadSafeClientConnManager(httpParams, supportedSchemes), httpParams)).get();
        } catch (Exception e) {
            throw new NSPException(2, "Service unavailable.", e);
        }
    }

    private static kub createLimitedHttpClient(Context context) throws NSPException {
        try {
            TrafficMonitoringService trafficMonitoringService = new TrafficMonitoringService(mContext);
            service = trafficMonitoringService;
            trafficMonitoringService.f();
            initSocketFactory(context);
            return (kub) new WeakReference(new kub(new ThreadSafeClientConnManager(httpParams, supportedSchemes), httpParams)).get();
        } catch (Exception e) {
            throw new NSPException(2, "Service unavailable.", e);
        }
    }

    private static void initSocketFactory(Context context) {
        try {
            if (sf == null) {
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                httpParams = basicHttpParams;
                HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
                HttpProtocolParams.setUseExpectContinue(httpParams, false);
                ConnPerRouteBean connPerRouteBean = new ConnPerRouteBean(20);
                connPerRouteBean.setMaxForRoute(new HttpRoute(new HttpHost("localhost", 80)), 100);
                ConnManagerParams.setMaxConnectionsPerRoute(httpParams, connPerRouteBean);
                ConnManagerParams.setMaxTotalConnections(httpParams, 800);
                sf = SecureApacheSSLSocketFactory.getInstance(null, context);
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                supportedSchemes = schemeRegistry;
                schemeRegistry.register(new Scheme("https", sf, 443));
                plainSocketFactory = PlainSocketFactory.getSocketFactory();
                supportedSchemes.register(new Scheme("http", plainSocketFactory, 80));
            }
        } catch (Exception unused) {
            LogUtil.b(TAG, "SSLContext initSocketFactory failed");
        }
    }

    public static HttpHost getHttpHost(String str) throws NSPException {
        URLInfo uRLInfo = getURLInfo(str);
        return (HttpHost) new WeakReference(new HttpHost(uRLInfo.host, uRLInfo.port, getSchemeName(uRLInfo))).get();
    }

    private static String getSchemeName(URLInfo uRLInfo) {
        if (uRLInfo.isHttps) {
            return "https" + uRLInfo.port;
        }
        return "http" + uRLInfo.port;
    }

    private static URLInfo getURLInfo(String str) throws NSPException {
        int i;
        if (TextUtils.isEmpty(str)) {
            throw new NSPException(2, "Url is empty.");
        }
        URLInfo uRLInfo = new URLInfo();
        String str2 = "";
        try {
            URL url = new URL(str);
            str2 = url.getHost();
            i = url.getPort();
        } catch (MalformedURLException unused) {
            LogUtil.b(TAG, "MalformedURLException");
            i = 443;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = getUrlHost(str);
        }
        boolean startsWith = str.toLowerCase(Locale.getDefault()).startsWith("https");
        if (i <= 0) {
            i = startsWith ? 443 : 80;
        }
        if (startsWith) {
            uRLInfo.isHttps = true;
        } else {
            uRLInfo.isHttps = false;
        }
        uRLInfo.port = i;
        if (!TextUtils.isEmpty(str2)) {
            uRLInfo.host = str2;
        }
        return uRLInfo;
    }

    private static String getUrlHost(String str) {
        int indexOf = str.indexOf("//");
        int i = indexOf == -1 ? 0 : indexOf + 2;
        int indexOf2 = str.indexOf(58, i);
        if (indexOf2 == -1 && (indexOf2 = str.indexOf(47, i)) < 0) {
            indexOf2 = str.length();
        }
        return str.substring(i, indexOf2);
    }

    static class URLInfo {
        String host;
        boolean isHttps;
        int port;

        private URLInfo() {
            this.isHttps = true;
            this.port = 443;
            this.host = HttpConnectionAdaptor.HTTP_HOST_NAME;
        }
    }

    private static void setHttpParams(HttpRequestBase httpRequestBase, int i, int i2, boolean z) {
        httpRequestBase.getParams().setParameter("http.connection.timeout", Integer.valueOf(i * 1000));
        httpRequestBase.getParams().setParameter("http.socket.timeout", Integer.valueOf(i2 * 1000));
        httpRequestBase.getParams().setParameter("http.protocol.handle-redirects", Boolean.valueOf(z));
    }
}

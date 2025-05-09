package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import android.util.LruCache;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;
import java.net.URL;

/* loaded from: classes9.dex */
public class h3 extends Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5285a = "MultiHostChange";
    public static final int b = 256;
    public static final LruCache<String, e3> c = new LruCache<>(256);
    public static final String d = "Multi-Cloud-Svc";
    public static final String e = "host";
    public static final String f = ";";
    public static final String g = "=";
    public static final String h = ":";
    public static final int i = -1;

    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.getUrl();
        URL url2 = new URL(url);
        String host = url2.getHost();
        int port = url2.getPort();
        LruCache<String, e3> lruCache = c;
        e3 e3Var = lruCache.get(host);
        boolean isHealthy = e3Var != null ? e3Var.isHealthy(!request.getMethod().equals("GET")) : false;
        Logger.v(f5285a, "whether the connection is OK ? %s", Boolean.valueOf(isHealthy));
        Logger.v(f5285a, "Before the MultiHostChange,the request is = %s", request);
        if (isHealthy) {
            String host2 = e3Var.getHost();
            if (host2 != null) {
                if (port != -1) {
                    host = host + ":" + port;
                }
                request = request.newBuilder().url(new d3(url.replaceFirst(host, host2)).getUrl()).build();
            }
        } else {
            lruCache.remove(host);
        }
        Logger.v(f5285a, "after the MultiHost,the request is = %s", request);
        try {
            Response<ResponseBody> proceed = chain.proceed(request);
            if (proceed != null) {
                String str = Headers.of(proceed.getHeaders()).get(d);
                Logger.v(f5285a, "the headers that you need is : %s", str);
                String a2 = a(str, "host");
                if (TextUtils.isEmpty(a2) || !(chain instanceof h1.b)) {
                    return proceed;
                }
                d1 requestTask = ((h1.b) chain).getRequestTask();
                if (requestTask != null) {
                    e3 connectionInfo = requestTask.getConnectionInfo();
                    if (connectionInfo != null) {
                        lruCache.put(a2, connectionInfo);
                    } else {
                        Logger.w(f5285a, "cacheMapFailed,because the message is null!");
                    }
                }
                Logger.v(f5285a, "the cache size is %d \n %s", Integer.valueOf(lruCache.size()), lruCache.snapshot());
            }
            return proceed;
        } catch (Exception e2) {
            c.remove(host);
            throw e2;
        }
    }

    private String a(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        for (String str3 : str.split(";")) {
            String[] split = str3.split("=");
            if (split.length == 2 && str2.equals(split[0].trim())) {
                return split[1].trim();
            }
        }
        return "";
    }
}

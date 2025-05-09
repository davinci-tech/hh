package com.huawei.agconnect.apms.instrument.okhttp2;

import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.cba;
import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.dcb;
import com.huawei.agconnect.apms.fed;
import com.huawei.agconnect.apms.k0;
import com.huawei.agconnect.apms.lkj;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.wxy;
import com.huawei.agconnect.apms.zab;
import com.huawei.agconnect.apms.zyx;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes8.dex */
public class OkHttp2Instrumentation {
    private static final String CACHED_RESPONSE_CLASS = "com.squareup.okhttp.Cache$CacheResponseBody";
    private static final AgentLog LOG = AgentLogManager.getAgentLog();

    private OkHttp2Instrumentation() {
    }

    private static void addNuwaHeaderId(Request.Builder builder, String str, String str2) {
        if (TextUtils.isEmpty(builder.build().header("net-msg-id"))) {
            builder.addHeader("net-msg-id", str);
        }
        if (TextUtils.isEmpty(builder.build().header(HeaderType.X_NUWA_SPAN_ID))) {
            builder.addHeader(HeaderType.X_NUWA_SPAN_ID, str2);
        }
        if (TextUtils.isEmpty(builder.build().header(HeaderType.X_NUWA_MICROSERVICE_NAME))) {
            builder.addHeader(HeaderType.X_NUWA_MICROSERVICE_NAME, HiAnalyticsManager.APM_EVENT_ID);
        }
    }

    public static ResponseBody body(Response response) {
        ResponseBody body = response.body();
        if (body != null && !Agent.isDisabled()) {
            try {
                if (body instanceof dcb) {
                    dcb dcbVar = (dcb) body;
                    if (CACHED_RESPONSE_CLASS.equalsIgnoreCase(dcbVar.bcd.getClass().getName())) {
                        return dcbVar.bcd;
                    }
                }
            } catch (Throwable th) {
                LOG.warn("skipping APMS OkHttp2 proxy: " + th.getMessage());
            }
        }
        return body;
    }

    public static Request build(Request.Builder builder) {
        if (Agent.isDisabled()) {
            return builder.build();
        }
        try {
            if (Agent.isNuwaTracerEnable()) {
                addNuwaHeaderId(builder, k0.bcd(), k0.abc());
            }
            return new fed(builder).abc.build();
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp2 proxy: " + th.getMessage());
            return builder.build();
        }
    }

    private static String getValueFromHeader(Request request, String str) {
        if (!TextUtils.isEmpty(str)) {
            String header = request.header(str);
            if (!TextUtils.isEmpty(header)) {
                return header;
            }
        }
        return "";
    }

    public static Response.Builder newBuilder(Response.Builder builder) {
        if (Agent.isDisabled()) {
            return builder;
        }
        try {
            return new cba(builder);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp2 proxy: " + th.getMessage());
            return builder;
        }
    }

    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        if (Agent.isDisabled()) {
            return okHttpClient.newCall(request);
        }
        try {
            wxy wxyVar = new wxy();
            wxyVar.ghi = 1;
            wxyVar.cde(System.currentTimeMillis());
            if (Agent.isNuwaTracerEnable()) {
                wxyVar.fed = getValueFromHeader(request, "net-msg-id");
                wxyVar.edc = getValueFromHeader(request, HeaderType.X_NUWA_SPAN_ID);
            }
            return new lkj(okHttpClient, request, wxyVar);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp2 proxy: " + th.getMessage());
            return okHttpClient.newCall(request);
        }
    }

    public static HttpURLConnection open(OkUrlFactory okUrlFactory, URL url) {
        HttpURLConnection open = okUrlFactory.open(url);
        if (Agent.isDisabled()) {
            return open;
        }
        try {
            return (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol()) && (open instanceof HttpsURLConnection)) ? new zyx((HttpsURLConnection) open) : new zab(open);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp2 proxy: " + th.getMessage());
            return open;
        }
    }

    public static Response.Builder body(Response.Builder builder, ResponseBody responseBody) {
        if (Agent.isDisabled()) {
            return builder.body(responseBody);
        }
        try {
            return new cba(builder).abc.body(responseBody);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp2 proxy: " + th.getMessage());
            return builder.body(responseBody);
        }
    }
}

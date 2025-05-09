package com.huawei.agconnect.apms.instrument.apacheclient;

import android.text.TextUtils;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.k0;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.uts;
import com.huawei.agconnect.apms.wxy;
import com.huawei.agconnect.apms.xwv;
import com.huawei.agconnect.apms.xyz;
import com.huawei.agconnect.apms.yxw;
import java.io.IOException;
import java.net.URL;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class ApacheClientInstrumentation {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();

    private static void addNuwaTraceIdToHeaderAndEvent(HttpRequest httpRequest, wxy wxyVar) {
        String bcd = k0.bcd();
        String abc = k0.abc();
        if (Agent.isNuwaTracerEnable()) {
            if (TextUtils.isEmpty(getInfoFromRequestHeaders("net-msg-id", httpRequest))) {
                httpRequest.addHeader("net-msg-id", bcd);
            }
            if (TextUtils.isEmpty(getInfoFromRequestHeaders(HeaderType.X_NUWA_SPAN_ID, httpRequest))) {
                httpRequest.addHeader(HeaderType.X_NUWA_SPAN_ID, abc);
            }
            if (TextUtils.isEmpty(getInfoFromRequestHeaders(HeaderType.X_NUWA_MICROSERVICE_NAME, httpRequest))) {
                httpRequest.addHeader(HeaderType.X_NUWA_MICROSERVICE_NAME, HiAnalyticsManager.APM_EVENT_ID);
            }
            wxyVar.fed = bcd;
            wxyVar.edc = abc;
        }
    }

    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        if (Agent.isDisabled()) {
            return (T) httpClient.execute(httpUriRequest, responseHandler);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpUriRequest, wxyVar);
        prepareDnsInfo(httpUriRequest, wxyVar);
        try {
            return (T) httpClient.execute(warp(httpUriRequest, wxyVar), warp(responseHandler, wxyVar));
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    private static String getInfoFromRequestHeaders(String str, HttpRequest httpRequest) {
        try {
            Header[] headers = httpRequest.getHeaders(str);
            if (headers != null && headers.length > 0 && headers[0].getValue() != null) {
                return headers[0].getValue();
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    private static void prepareDnsInfo(HttpRequest httpRequest, wxy wxyVar) {
        String str;
        if (httpRequest == null || httpRequest.getRequestLine() == null) {
            return;
        }
        try {
            String uri = httpRequest.getRequestLine().getUri();
            try {
                str = new URL(uri).getHost();
            } catch (Throwable th) {
                LOG.warn(uri + " dns resolve failed, detail: " + th.getMessage());
                str = "";
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            xyz.abc(str, wxyVar);
        } catch (Throwable th2) {
            LOG.error(" dns resolve failed, detail: " + th2.getMessage());
        }
    }

    private static HttpRequest warp(HttpHost httpHost, HttpRequest httpRequest, wxy wxyVar) {
        AgentLog agentLog = yxw.abc;
        try {
            RequestLine requestLine = httpRequest.getRequestLine();
            if (requestLine != null) {
                wxyVar.def(requestLine.getUri());
                wxyVar.bcd(requestLine.getMethod());
            } else if (httpHost != null) {
                wxyVar.def(httpHost.toURI());
            }
            wxyVar.cde(System.currentTimeMillis());
            wxyVar.ghi = 4;
            if (httpRequest instanceof HttpEntityEnclosingRequest) {
                HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpRequest;
                if (httpEntityEnclosingRequest.getEntity() != null) {
                    httpEntityEnclosingRequest.setEntity(new xwv(httpEntityEnclosingRequest.getEntity(), wxyVar));
                }
            }
        } catch (Throwable th) {
            yxw.abc.warn("failed to get http host info: " + th.getMessage());
        }
        return httpRequest;
    }

    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        if (Agent.isDisabled()) {
            return (T) httpClient.execute(httpHost, httpRequest, responseHandler);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpRequest, wxyVar);
        prepareDnsInfo(httpRequest, wxyVar);
        try {
            return (T) httpClient.execute(httpHost, warp(httpHost, httpRequest, wxyVar), warp(responseHandler, wxyVar));
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        if (Agent.isDisabled()) {
            return (T) httpClient.execute(httpUriRequest, responseHandler, httpContext);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpUriRequest, wxyVar);
        prepareDnsInfo(httpUriRequest, wxyVar);
        try {
            return (T) httpClient.execute(warp(httpUriRequest, wxyVar), warp(responseHandler, wxyVar), httpContext);
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        if (Agent.isDisabled()) {
            return (T) httpClient.execute(httpHost, httpRequest, responseHandler, httpContext);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpRequest, wxyVar);
        prepareDnsInfo(httpRequest, wxyVar);
        try {
            return (T) httpClient.execute(httpHost, warp(httpHost, httpRequest, wxyVar), warp(responseHandler, wxyVar), httpContext);
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        if (Agent.isDisabled()) {
            return httpClient.execute(httpUriRequest);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpUriRequest, wxyVar);
        prepareDnsInfo(httpUriRequest, wxyVar);
        try {
            return warp(httpClient.execute(warp(httpUriRequest, wxyVar)), wxyVar);
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    private static <T> ResponseHandler<? extends T> warp(ResponseHandler<? extends T> responseHandler, wxy wxyVar) {
        AgentLog agentLog = yxw.abc;
        return new uts(responseHandler, wxyVar);
    }

    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        if (Agent.isDisabled()) {
            return httpClient.execute(httpHost, httpRequest);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpRequest, wxyVar);
        prepareDnsInfo(httpRequest, wxyVar);
        try {
            return warp(httpClient.execute(httpHost, warp(httpHost, httpRequest, wxyVar)), wxyVar);
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        if (Agent.isDisabled()) {
            return httpClient.execute(httpUriRequest, httpContext);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpUriRequest, wxyVar);
        prepareDnsInfo(httpUriRequest, wxyVar);
        try {
            return warp(httpClient.execute(warp(httpUriRequest, wxyVar), httpContext), wxyVar);
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        if (Agent.isDisabled()) {
            return httpClient.execute(httpHost, httpRequest, httpContext);
        }
        wxy wxyVar = new wxy();
        addNuwaTraceIdToHeaderAndEvent(httpRequest, wxyVar);
        prepareDnsInfo(httpRequest, wxyVar);
        try {
            return warp(httpClient.execute(httpHost, warp(httpHost, httpRequest, wxyVar), httpContext), wxyVar);
        } catch (IOException e) {
            AgentLog agentLog = yxw.abc;
            wxyVar.bcd(0);
            wxyVar.abc(e.toString());
            yxw.abc(wxyVar);
            throw e;
        }
    }

    private static HttpUriRequest warp(HttpUriRequest httpUriRequest, wxy wxyVar) {
        AgentLog agentLog = yxw.abc;
        try {
            wxyVar.def(httpUriRequest.getURI().toString());
            wxyVar.bcd(httpUriRequest.getMethod());
            wxyVar.cde(System.currentTimeMillis());
            wxyVar.ghi = 4;
            if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
                HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpUriRequest;
                if (httpEntityEnclosingRequest.getEntity() != null) {
                    httpEntityEnclosingRequest.setEntity(new xwv(httpEntityEnclosingRequest.getEntity(), wxyVar));
                }
            }
        } catch (Throwable th) {
            yxw.abc.warn("failed to get request info: " + th.getMessage());
        }
        return httpUriRequest;
    }

    private static HttpResponse warp(HttpResponse httpResponse, wxy wxyVar) {
        return yxw.abc(httpResponse, wxyVar);
    }
}

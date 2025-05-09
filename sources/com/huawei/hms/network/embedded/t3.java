package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.io.IOException;

/* loaded from: classes9.dex */
public class t3 extends Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5497a = "RouteInterceptor";
    public static final String b = "alt-svc";
    public static final String c = ";";
    public static final String d = "=";
    public static final String e = ",";

    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        RequestFinishedInfo requestFinishedInfo;
        if (!(chain instanceof h1.b)) {
            throw new ClassCastException("the classType has error!,please use SafeApi.SafeChain");
        }
        h1.b bVar = (h1.b) chain;
        d1 requestTask = bVar.getRequestTask();
        Request a2 = s3.getInstance().a((h1.d) chain.request());
        d3 d3Var = new d3(a2.getUrl());
        String host = d3Var.getHost();
        int port = d3Var.getPort();
        z2 rCEventListener = bVar.getRCEventListener();
        rCEventListener.cpNetworkInterceptorReqStart();
        RequestFinishedInfo requestFinishedInfo2 = null;
        requestFinishedInfo2 = null;
        try {
            try {
                Response<ResponseBody> proceed = chain.proceed(a2);
                if (requestTask != null) {
                    requestFinishedInfo = requestTask.getRequestFinishedInfo();
                    if (requestFinishedInfo != null) {
                        try {
                            Logger.v(f5497a, "the net lib is %s", requestFinishedInfo.getNetworkSdkType());
                            Logger.v(f5497a, "the protocol is %s", requestFinishedInfo.getMetrics().getProtocol());
                        } catch (Exception e2) {
                            e = e2;
                            requestFinishedInfo2 = requestFinishedInfo;
                            if ((requestTask instanceof j2) && !requestTask.isCanceled() && requestTask.getRequestFinishedInfo().getException() == null) {
                                ((i2) requestTask.getRequestFinishedInfo()).setException(e);
                            }
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            requestFinishedInfo2 = requestFinishedInfo;
                            if (requestFinishedInfo2 != null) {
                                RequestFinishedInfo.MetricsTime metricsRealTime = requestFinishedInfo2.getMetricsRealTime();
                                long secureConnectStartTime = metricsRealTime.getSecureConnectStartTime() - metricsRealTime.getConnectStartTime();
                                if (secureConnectStartTime <= 0) {
                                    secureConnectStartTime = metricsRealTime.getConnectEndTime() - metricsRealTime.getConnectStartTime();
                                }
                                s3.getInstance().a(secureConnectStartTime, host);
                            }
                            throw th;
                        }
                    }
                } else {
                    requestFinishedInfo = null;
                }
                Headers of = proceed != null ? Headers.of(proceed.getHeaders()) : null;
                a1 realHttpclient = ((h1.b) chain).getRealHttpclient();
                if (of != null && realHttpclient != null && realHttpclient.isHttpClientEnableQuic()) {
                    String str = of.get(b);
                    String altSvcType = g2.getInstance().getAltSvcType();
                    if (str != null && str.contains(altSvcType) && (requestTask instanceof j3)) {
                        Logger.i(f5497a, "has alt-svc, try to use quic");
                        port = a(str, altSvcType);
                        Logger.v(f5497a, "the port is %s", Integer.valueOf(port));
                        g2.getInstance().updateQuicHints(host, port, true);
                    }
                    if (str == null && (requestTask instanceof j2)) {
                        String host2 = NetworkUtil.getHost(proceed.getUrl());
                        Logger.i(f5497a, "hasn't alt-svc, disable quic");
                        Logger.v(f5497a, "Disable quic %s %d", host2, Integer.valueOf(port));
                        g2.getInstance().updateQuicHints(host2, port, false);
                        PreConnectManager.getInstance().connect(host, new PreConnectManager.ConnectCallBack());
                    }
                }
                if (requestFinishedInfo != null) {
                    RequestFinishedInfo.MetricsTime metricsRealTime2 = requestFinishedInfo.getMetricsRealTime();
                    long secureConnectStartTime2 = metricsRealTime2.getSecureConnectStartTime() - metricsRealTime2.getConnectStartTime();
                    if (secureConnectStartTime2 <= 0) {
                        secureConnectStartTime2 = metricsRealTime2.getConnectEndTime() - metricsRealTime2.getConnectStartTime();
                    }
                    s3.getInstance().a(secureConnectStartTime2, host);
                }
                rCEventListener.cpNetworkInterceptorResEnd();
                return proceed;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private int a(String[] strArr) {
        String replace = StringUtils.replace(StringUtils.replace(strArr[1].trim(), "\"", ""), ":", "");
        if (replace.length() <= 0) {
            return 0;
        }
        Logger.v(f5497a, "the temport is %s", replace);
        return StringUtils.stringToInteger(replace, 0);
    }

    private int a(String str, String str2) {
        Logger.v(f5497a, "the quicFiled is %s-%s", str, str2);
        if (str != null && !str2.isEmpty()) {
            String[] split = str.split(";");
            for (int i = 0; i < split.length; i++) {
                if (split[i].contains(",")) {
                    for (String str3 : split[i].split(",")) {
                        String[] split2 = str3.split("=");
                        if (split2.length == 2 && str2.equals(split2[0].trim())) {
                            return a(split2);
                        }
                    }
                } else {
                    String[] split3 = split[i].split("=");
                    if (split3.length == 2 && str2.equals(split3[0].trim())) {
                        return a(split3);
                    }
                }
            }
        }
        return 0;
    }
}

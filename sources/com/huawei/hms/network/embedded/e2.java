package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.i2;
import java.util.Collection;
import java.util.concurrent.Executor;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes9.dex */
public class e2 extends RequestFinishedInfo.Listener {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5225a = "CronetEventListener";
    public static final long b = 0;
    public static final String c = "unknown";

    public void onRequestFinished(RequestFinishedInfo requestFinishedInfo) {
        Logger.v(f5225a, "onRequestFinished");
        if (requestFinishedInfo == null) {
            Logger.e(f5225a, "requestInfo == null at onRequestFinished");
            return;
        }
        try {
            j2 a2 = a(requestFinishedInfo);
            if (a2 == null) {
                Logger.e(f5225a, "fail to get CronetRequestTask from getAnnotations");
                return;
            }
            i2 i2Var = (i2) a2.getRequestFinishedInfo();
            if (i2Var == null) {
                Logger.w(f5225a, "requestFinishedInfo == null at onRequestFinished");
            } else {
                a(requestFinishedInfo, i2Var, a2.getResponseInfo());
                a2.a();
            }
        } catch (Throwable th) {
            Logger.e(f5225a, "onRequestFinished occur exception, exception name:" + th.getClass().getSimpleName());
        }
    }

    private void a(RequestFinishedInfo requestFinishedInfo, i2 i2Var, UrlResponseInfo urlResponseInfo) {
        if (requestFinishedInfo == null || i2Var == null || urlResponseInfo == null) {
            Logger.e(f5225a, "invalid parameters");
            return;
        }
        i2Var.setUrl(requestFinishedInfo.getUrl());
        i2Var.setException(requestFinishedInfo.getException());
        a(requestFinishedInfo.getMetrics(), i2Var.getMetricsTime());
        a(requestFinishedInfo.getMetrics(), requestFinishedInfo, i2Var.getMetrics());
    }

    private void a(RequestFinishedInfo.Metrics metrics, RequestFinishedInfo requestFinishedInfo, u2 u2Var) {
        if (metrics == null || requestFinishedInfo == null || u2Var == null) {
            Logger.e(f5225a, "invalid parameters");
            return;
        }
        u2Var.setRequestByteCount(metrics.getSentByteCount() == null ? 0L : metrics.getSentByteCount().longValue());
        u2Var.setResponseByteCount(metrics.getReceivedByteCount() != null ? metrics.getReceivedByteCount().longValue() : 0L);
        u2Var.setProtocol(requestFinishedInfo.getResponseInfo() == null ? "unknown" : requestFinishedInfo.getResponseInfo().getNegotiatedProtocol());
    }

    private void a(RequestFinishedInfo.Metrics metrics, v2 v2Var) {
        if (metrics == null || v2Var == null) {
            Logger.e(f5225a, "invalid parameters");
            return;
        }
        v2Var.setCallStartTime(metrics.getRequestStart() == null ? 0L : metrics.getRequestStart().getTime());
        v2Var.setCallEndTime(metrics.getRequestEnd() == null ? 0L : metrics.getRequestEnd().getTime());
        v2Var.setDnsStartTime(metrics.getDnsStart() == null ? 0L : metrics.getDnsStart().getTime());
        v2Var.setDnsEndTime(metrics.getDnsEnd() == null ? 0L : metrics.getDnsEnd().getTime());
        v2Var.setConnectStartTime(metrics.getConnectStart() == null ? 0L : metrics.getConnectStart().getTime());
        v2Var.setConnectEndTime(metrics.getConnectEnd() == null ? 0L : metrics.getConnectEnd().getTime());
        v2Var.setSecureConnectStartTime(metrics.getSslStart() == null ? 0L : metrics.getSslStart().getTime());
        v2Var.setSecureConnectEndTime(metrics.getSslEnd() == null ? 0L : metrics.getSslEnd().getTime());
        v2Var.setRequestHeadersStartTime(metrics.getSendingStart() == null ? 0L : metrics.getSendingStart().getTime());
        v2Var.setRequestHeadersEndTime(metrics.getSendingEnd() == null ? 0L : metrics.getSendingEnd().getTime());
        v2Var.setResponseHeadersStartTime(metrics.getResponseStart() == null ? 0L : metrics.getResponseStart().getTime());
        v2Var.setResponseBodyEndTime(metrics.getRequestEnd() == null ? 0L : metrics.getRequestEnd().getTime());
        if (v2Var instanceof i2.a) {
            i2.a aVar = (i2.a) v2Var;
            aVar.setTtfb(metrics.getTtfbMs() == null ? 0L : metrics.getTtfbMs().longValue());
            aVar.setTotalTime(metrics.getTotalTimeMs() != null ? metrics.getTotalTimeMs().longValue() : 0L);
        }
    }

    private j2 a(RequestFinishedInfo requestFinishedInfo) {
        Collection annotations = requestFinishedInfo.getAnnotations();
        Object obj = (annotations == null || annotations.isEmpty()) ? null : annotations.toArray()[0];
        if (!(obj instanceof j2)) {
            return null;
        }
        Logger.v(f5225a, "call getAnnotations return CronetRequestTask instance");
        return (j2) obj;
    }

    public e2(Executor executor) {
        super(executor);
    }
}

package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes9.dex */
public class q3 {
    public static final String g = "WebSocketEventListener";
    public static final String h = "trigger_type";
    public static final String i = "websocket_open_time";

    /* renamed from: a, reason: collision with root package name */
    public l3 f5431a;
    public h1.d b;
    public x2 c = new x2();
    public LinkedList<Long> d = new LinkedList<>();
    public long e;
    public r3 f;

    private long a(long j, long j2) {
        if (j2 <= 0 || j <= 0) {
            return 0L;
        }
        return j - j2;
    }

    public void setRequestFinishedInfo(l3 l3Var) {
        this.f5431a = l3Var;
    }

    public void setPingPongDelayList(LinkedList<Long> linkedList) {
        this.d.addAll(linkedList);
    }

    public void setPingIntervalManager(r3 r3Var) {
        this.f = r3Var;
    }

    public void setOnOpenTime(long j) {
        this.e = j;
    }

    public <T> void reportData(T t, String str) {
        HianalyticsHelper.getInstance().getReportExecutor().execute(new a(t, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        u2 metrics = this.f5431a.getMetrics();
        v2 metricsRealTime = this.f5431a.getMetricsRealTime();
        a(metrics);
        this.c.put("trigger_type", str).put(i, this.e).put("domain", this.f5431a.getHost()).put("req_start_time", this.f5431a.getMetricsTime().getCallStartTime()).put("protocol_impl", this.f5431a.getNetworkSdkType()).put(x2.CONNECT_OUT_IP, a(metrics, this.f5431a)).put(x2.TTFB, this.f5431a.getMetricsRealTime().getTtfb()).put(x2.WEBSOCKET_CLIENT_PING_INTERVAL, this.f5431a.getPingInterval()).put(x2.WEBSOCKET_PING_DELAY, this.d.toString()).put(x2.CONNECT_RETRY, metrics.getConnectRetryTime() < 0 ? 0L : metrics.getConnectRetryTime()).put(x2.TLS_VERSION, metrics.getTlsVersion()).put(x2.DNS_TYPE, metrics.getDnsType()).put(x2.DNS_CACHE, metrics.getDnsCache()).put(x2.DNS_TTL, metrics.getDnsTtl()).put(x2.DNS_STATUS, metrics.getDnsStatus()).put(x2.TLS_CIPHERSUITE, metrics.getCipherSuite()).put("dns_server_ips", NetworkUtil.getDnsServerIps(ContextHolder.getAppContext())).put("req_total_time", metricsRealTime.getRequestBodyEndTime() - metricsRealTime.getCallStartTime()).put("total_time", metricsRealTime.getTotalTime()).put("tcpconn_time", a(metricsRealTime.getSecureConnectStartTime(), metricsRealTime.getConnectStartTime())).put("ssl_time", a(metricsRealTime.getSecureConnectEndTime(), metricsRealTime.getSecureConnectStartTime())).put("connect_time", a(metricsRealTime.getConnectEndTime(), metricsRealTime.getConnectStartTime())).put(x2.REQ_START_TRANSFER, a(metricsRealTime.getRequestHeadersStartTime(), metricsRealTime.getCallStartTime())).put(x2.DNS_TIME, a(metricsRealTime.getDnsEndTime(), metricsRealTime.getDnsStartTime())).put(this.f.getReporterData());
    }

    private void a(u2 u2Var) {
        x2 x2Var;
        String str;
        List<String> connectIps = u2Var.getConnectIps();
        String arrays = connectIps.isEmpty() ? null : Arrays.toString(connectIps.toArray());
        String successIp = this.f5431a.getMetrics().getSuccessIp();
        if (!TextUtils.isEmpty(successIp)) {
            arrays = arrays + "/" + successIp;
            if (CheckParamUtils.isIpV6(successIp)) {
                this.c.put(x2.IP_TYPE, "AAAA");
            }
            if (CheckParamUtils.isIpV4(successIp)) {
                x2Var = this.c;
                str = "A";
            }
            this.c.put("server_ip", arrays);
        }
        x2Var = this.c;
        str = "unknown";
        x2Var.put(x2.IP_TYPE, str);
        this.c.put("server_ip", arrays);
    }

    private void a() {
        try {
            URL url = new URL(this.b.getUrl());
            this.c.put("domain", url.getHost());
            this.c.put(x2.API_ID, StringUtils.anonymizeMessage(url.getPath()));
        } catch (MalformedURLException unused) {
            Logger.w(g, "Create Url error");
            this.c.put(x2.API_ID, "unknown");
        }
        this.c.put("sdk_version", "8.0.1.307");
        this.c.put("network_type", NetworkUtil.getCurrentNetworkType());
        String str = this.b.getNetConfig().getMap("core_metrics_data").get("trace_id");
        if (TextUtils.isEmpty(str)) {
            this.c.put("trace_id", UUID.randomUUID().toString());
        } else {
            this.c.put("trace_id", str);
        }
    }

    private String a(u2 u2Var, RequestFinishedInfo requestFinishedInfo) {
        if (requestFinishedInfo.getNetworkSdkType() != "type_okhttp") {
            return null;
        }
        List<String> connectIps = u2Var.getConnectIps();
        if (connectIps.isEmpty()) {
            Logger.d(g, "connect ip is empty");
            return null;
        }
        String successIp = u2Var.getSuccessIp();
        if (TextUtils.isEmpty(successIp)) {
            Logger.d(g, "success ip is empty, all connect ip expire");
            return StringUtils.collection2String(connectIps);
        }
        LinkedList linkedList = new LinkedList();
        for (String str : connectIps) {
            if (successIp.equals(str)) {
                break;
            }
            linkedList.add(str);
        }
        return StringUtils.collection2String(linkedList);
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f5432a;
        public final /* synthetic */ String b;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f5432a instanceof Integer) {
                if (!HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
                    return;
                } else {
                    q3.this.c.put("error_code", ((Integer) this.f5432a).intValue());
                }
            }
            q3.this.a(this.b);
            Exception exception = q3.this.f5431a.getException();
            if (exception != null) {
                if (!HianalyticsHelper.getInstance().isEnableReportNoSeed(ContextHolder.getAppContext())) {
                    return;
                }
                q3.this.c.put("error_code", i4.getErrorCodeFromException(exception)).put("exception_name", exception.getClass().getSimpleName()).put("message", StringUtils.anonymizeMessage(exception.getMessage())).put(x2.INRATE, "" + (HianalyticsHelper.getInstance().inRate() ? 1 : 0));
            }
            HianalyticsHelper.getInstance().onEvent(q3.this.c.get(), x2.WEBSOCKET_ID);
        }

        public a(Object obj, String str) {
            this.f5432a = obj;
            this.b = str;
        }
    }

    public q3(l3 l3Var, h1.d dVar) {
        this.f5431a = l3Var;
        this.b = dVar;
        a();
    }
}

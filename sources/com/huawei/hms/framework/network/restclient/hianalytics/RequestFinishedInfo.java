package com.huawei.hms.framework.network.restclient.hianalytics;

import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyResponse;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import java.util.List;

@Deprecated
/* loaded from: classes4.dex */
public class RequestFinishedInfo {
    private final com.huawei.hms.network.httpclient.RequestFinishedInfo requestFinishedInfo;

    public RequestFinishedInfo(com.huawei.hms.network.httpclient.RequestFinishedInfo requestFinishedInfo) {
        this.requestFinishedInfo = requestFinishedInfo;
    }

    @Deprecated
    public static class MetricsTime {
        private final RequestFinishedInfo.MetricsTime metricsTime;

        private MetricsTime(RequestFinishedInfo.MetricsTime metricsTime) {
            this.metricsTime = metricsTime;
        }

        public long getCallStartTime() {
            return this.metricsTime.getCallStartTime();
        }

        public long getDnsStartTime() {
            return this.metricsTime.getDnsStartTime();
        }

        public long getDnsEndTime() {
            return this.metricsTime.getDnsEndTime();
        }

        public long getConnectStartTime() {
            return this.metricsTime.getConnectStartTime();
        }

        public long getSecureConnectStartTime() {
            return this.metricsTime.getSecureConnectStartTime();
        }

        public long getSecureConnectEndTime() {
            return this.metricsTime.getSecureConnectEndTime();
        }

        public long getConnectEndTime() {
            return this.metricsTime.getConnectEndTime();
        }

        public long getConnectionAcquiredTime() {
            return this.metricsTime.getConnectionAcquiredTime();
        }

        public long getConnectionReleasedTime() {
            return this.metricsTime.getConnectionReleasedTime();
        }

        public long getRequestHeadersStartTime() {
            return this.metricsTime.getRequestHeadersStartTime();
        }

        public long getRequestHeadersEndTime() {
            return this.metricsTime.getRequestHeadersEndTime();
        }

        public long getRequestBodyStartTime() {
            return this.metricsTime.getRequestBodyStartTime();
        }

        public long getRequestBodyEndTime() {
            return this.metricsTime.getRequestBodyEndTime();
        }

        public long getResponseHeadersStartTime() {
            return this.metricsTime.getResponseHeadersStartTime();
        }

        public long getResponseHeadersEndTime() {
            return this.metricsTime.getResponseHeadersEndTime();
        }

        public long getResponseBodyStartTime() {
            return this.metricsTime.getResponseBodyStartTime();
        }

        public long getResponseBodyEndTime() {
            return this.metricsTime.getResponseBodyEndTime();
        }

        public long getCallEndTime() {
            return this.metricsTime.getCallEndTime();
        }

        public long getTotalTime() {
            return this.metricsTime.getTotalTime();
        }

        public long getTtfb() {
            return this.metricsTime.getTtfb();
        }
    }

    @Deprecated
    public static class Metrics {
        private final RequestFinishedInfo.Metrics metrics;

        private Metrics(RequestFinishedInfo.Metrics metrics) {
            this.metrics = metrics;
        }

        public int getConnectRetryTime() {
            return this.metrics.getConnectRetryTime();
        }

        public long getRequestByteCount() {
            return this.metrics.getRequestByteCount();
        }

        public long getResponseByteCount() {
            return this.metrics.getResponseByteCount();
        }

        public String getDnsType() {
            return this.metrics.getDnsType();
        }

        public String getProtocol() {
            return this.metrics.getProtocol();
        }

        public String getSuccessIp() {
            return this.metrics.getSuccessIp();
        }

        public List<String> getConnectIps() {
            return this.metrics.getConnectIps();
        }

        public int getDnsCache() {
            return this.metrics.getDnsCache();
        }
    }

    public MetricsTime getMetricsTime() {
        return new MetricsTime(this.requestFinishedInfo.getMetricsTime());
    }

    public Metrics getMetrics() {
        return new Metrics(this.requestFinishedInfo.getMetrics());
    }

    public MetricsTime getMetricsRealTime() {
        return new MetricsTime(this.requestFinishedInfo.getMetricsRealTime());
    }

    public String getNetworkSdkType() {
        return this.requestFinishedInfo.getNetworkSdkType();
    }

    public String getUrl() {
        return this.requestFinishedInfo.getUrl();
    }

    public String getHost() {
        return this.requestFinishedInfo.getHost();
    }

    public Response getResponse() {
        return ProxyResponse.response2Old(this.requestFinishedInfo.getResponse());
    }

    public Exception getException() {
        return this.requestFinishedInfo.getException();
    }

    public long getPingInterval() {
        return this.requestFinishedInfo.getPingInterval();
    }
}

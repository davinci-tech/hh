package com.huawei.hms.network.httpclient;

import java.util.List;

/* loaded from: classes4.dex */
public abstract class RequestFinishedInfo {

    public static abstract class Metrics {
        public abstract String getCipherSuite();

        public abstract String getCongestionControlType();

        public abstract List<String> getConnectIps();

        public abstract int getConnectRetryTime();

        public abstract int getDnsCache();

        public abstract int getDnsStatus();

        public abstract long getDnsTtl();

        public abstract String getDnsType();

        public abstract int getMultipathAlgorithm();

        public abstract String getProtocol();

        public abstract long getRequestByteCount();

        public abstract long getResponseByteCount();

        public abstract String getSuccessIp();

        public abstract String getTlsVersion();
    }

    public static abstract class MetricsTime {
        public abstract long getCallEndTime();

        public abstract long getCallStartTime();

        public abstract long getConnectEndTime();

        public abstract long getConnectStartTime();

        public abstract long getConnectionAcquiredTime();

        public abstract long getConnectionReleasedTime();

        public abstract long getDnsEndTime();

        public abstract long getDnsStartTime();

        public abstract long getRequestBodyEndTime();

        public abstract long getRequestBodyStartTime();

        public abstract long getRequestHeadersEndTime();

        public abstract long getRequestHeadersStartTime();

        public abstract long getResponseBodyEndTime();

        public abstract long getResponseBodyStartTime();

        public abstract long getResponseHeadersEndTime();

        public abstract long getResponseHeadersStartTime();

        public abstract long getSecureConnectEndTime();

        public abstract long getSecureConnectStartTime();

        public abstract long getTotalTime();

        public abstract long getTtfb();

        public abstract long getTtfbV1();
    }

    public abstract Exception getException();

    public abstract String getHost();

    public abstract Metrics getMetrics();

    public abstract MetricsTime getMetricsRealTime();

    public abstract MetricsTime getMetricsTime();

    public abstract String getNetMsgId();

    public abstract String getNetworkSdkType();

    public abstract long getPingInterval();

    public abstract Response getResponse();

    public abstract String getUrl();
}

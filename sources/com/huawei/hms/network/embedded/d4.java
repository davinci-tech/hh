package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public class d4 extends r2 {
    public static final String j = "URLConnRequestFinishedInfo";
    public v2 g = new a(false);
    public v2 h = new a(true);
    public u2 i = new u2();

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getNetworkSdkType() {
        return "type_urlconnection";
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public v2 getMetricsTime() {
        return this.g;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public v2 getMetricsRealTime() {
        return this.h;
    }

    public static class a extends v2 {
        @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTtfb() {
            return 0L;
        }

        @Override // com.huawei.hms.network.embedded.v2, com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTtfbV1() {
            return 0L;
        }

        @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTotalTime() {
            return getAndCheckEndTime(getCallStartTime(), getCallEndTime()) - getCallStartTime();
        }

        public a(boolean z) {
            super(z);
        }
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public u2 getMetrics() {
        return this.i;
    }
}

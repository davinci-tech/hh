package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public class i2 extends r2 {
    public static final String i = "CronetRequestFinishedInfo";
    public v2 g = new a();
    public u2 h = new u2();

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getNetworkSdkType() {
        return "type_cronet";
    }

    public static class a extends v2 {
        public long u;
        public long v;

        @Override // com.huawei.hms.network.embedded.v2
        public void setTtfb(long j) {
            this.v = j;
        }

        public void setTotalTime(long j) {
            this.u = j;
        }

        @Override // com.huawei.hms.network.embedded.v2, com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTtfbV1() {
            return this.v;
        }

        @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTtfb() {
            return this.v;
        }

        @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTotalTime() {
            return this.u;
        }
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public v2 getMetricsTime() {
        return this.g;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public v2 getMetricsRealTime() {
        return this.g;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public u2 getMetrics() {
        return this.h;
    }
}

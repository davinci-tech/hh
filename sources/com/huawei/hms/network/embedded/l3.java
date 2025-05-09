package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes9.dex */
public class l3 extends r2 {
    public static final String l = "OkhttpConnRequestFinish";
    public String g;
    public String h;
    public v2 i = new a(false);
    public v2 j = new a(true);
    public u2 k = new u2();

    @Override // com.huawei.hms.network.embedded.r2
    public void setUrl(String str) {
        this.h = str;
        try {
            this.g = new URL(str).getHost();
        } catch (MalformedURLException unused) {
            Logger.i(l, "fail to get hostname from url");
        }
    }

    @Override // com.huawei.hms.network.embedded.r2, com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getUrl() {
        return this.h;
    }

    @Override // com.huawei.hms.network.embedded.r2, com.huawei.hms.network.httpclient.RequestFinishedInfo
    public long getPingInterval() {
        return this.i.getPingInterval();
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getNetworkSdkType() {
        return "type_okhttp";
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public v2 getMetricsTime() {
        return this.i;
    }

    public static class a extends v2 {
        @Override // com.huawei.hms.network.embedded.v2, com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTtfbV1() {
            return getAndCheckEndTime(getCallStartTime(), this.ttfbV1) - getCallStartTime();
        }

        @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
        public long getTtfb() {
            return getAndCheckEndTime(getCallStartTime(), this.ttfb) - getCallStartTime();
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
    public v2 getMetricsRealTime() {
        return this.j;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo
    public u2 getMetrics() {
        return this.k;
    }

    @Override // com.huawei.hms.network.embedded.r2, com.huawei.hms.network.httpclient.RequestFinishedInfo
    public String getHost() {
        return this.g;
    }
}

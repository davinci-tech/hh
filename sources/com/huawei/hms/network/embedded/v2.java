package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Utils;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;

/* loaded from: classes9.dex */
public abstract class v2 extends RequestFinishedInfo.MetricsTime {

    /* renamed from: a, reason: collision with root package name */
    public long f5536a;
    public long b;
    public long c;
    public long d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    public long j;
    public long k;
    public long l;
    public long m;
    public long n;
    public long o;
    public long p;
    public long q;
    public long r;
    public boolean s;
    public long t;
    public long ttfb;
    public long ttfbV1;

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public abstract long getTtfbV1();

    public void setTtfbV1(long j) {
        this.ttfbV1 = j;
    }

    public void setTtfb(long j) {
        this.ttfb = j;
    }

    public void setSecureConnectStartTime(long j) {
        this.e = j;
    }

    public void setSecureConnectStartTime() {
        this.e = getCurrentTime();
    }

    public void setSecureConnectEndTime(long j) {
        this.f = j;
    }

    public void setSecureConnectEndTime() {
        this.f = getCurrentTime();
    }

    public void setResponseHeadersStartTime(long j) {
        this.n = j;
    }

    public void setResponseHeadersStartTime() {
        this.n = getCurrentTime();
    }

    public void setResponseHeadersEndTime(long j) {
        this.o = j;
    }

    public void setResponseHeadersEndTime() {
        this.o = getCurrentTime();
    }

    public void setResponseBodyStartTime(long j) {
        this.p = j;
    }

    public void setResponseBodyStartTime() {
        this.p = getCurrentTime();
    }

    public void setResponseBodyEndTime(long j) {
        this.q = j;
    }

    public void setResponseBodyEndTime() {
        this.q = getCurrentTime();
    }

    public void setRequestHeadersStartTime(long j) {
        this.j = j;
    }

    public void setRequestHeadersStartTime() {
        this.j = getCurrentTime();
    }

    public void setRequestHeadersEndTime(long j) {
        this.k = j;
    }

    public void setRequestHeadersEndTime() {
        this.k = getCurrentTime();
    }

    public void setRequestBodyStartTime(long j) {
        this.l = j;
    }

    public void setRequestBodyStartTime() {
        this.l = getCurrentTime();
    }

    public void setRequestBodyEndTime(long j) {
        this.m = j;
    }

    public void setRequestBodyEndTime() {
        this.m = getCurrentTime();
    }

    public void setPingInterval(long j) {
        this.t = j;
    }

    public void setDnsStartTime(long j) {
        this.b = j;
    }

    public void setDnsStartTime() {
        this.b = getCurrentTime();
    }

    public void setDnsEndTime(long j) {
        this.c = j;
    }

    public void setDnsEndTime() {
        this.c = getCurrentTime();
    }

    public void setConnectionReleasedTime(long j) {
        this.i = j;
    }

    public void setConnectionReleasedTime() {
        this.i = getCurrentTime();
    }

    public void setConnectionAcquiredTime(long j) {
        this.h = j;
    }

    public void setConnectionAcquiredTime() {
        this.h = getCurrentTime();
    }

    public void setConnectStartTime(long j) {
        this.d = j;
    }

    public void setConnectStartTime() {
        this.d = getCurrentTime();
    }

    public void setConnectEndTime(long j) {
        this.g = j;
    }

    public void setConnectEndTime() {
        this.g = getCurrentTime();
    }

    public void setCallStartTime(long j) {
        this.f5536a = j;
    }

    public void setCallStartTime() {
        this.f5536a = getCurrentTime();
    }

    public void setCallEndTime(long j) {
        this.r = j;
    }

    public void setCallEndTime() {
        this.r = getCurrentTime();
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getSecureConnectStartTime() {
        return this.e;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getSecureConnectEndTime() {
        return this.f;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getResponseHeadersStartTime() {
        return this.n;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getResponseHeadersEndTime() {
        return this.o;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getResponseBodyStartTime() {
        return this.p;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getResponseBodyEndTime() {
        return this.q;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getRequestHeadersStartTime() {
        return this.j;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getRequestHeadersEndTime() {
        return this.k;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getRequestBodyStartTime() {
        return this.l;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getRequestBodyEndTime() {
        return this.m;
    }

    public long getPingInterval() {
        return this.t;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getDnsStartTime() {
        return this.b;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getDnsEndTime() {
        return this.c;
    }

    public long getCurrentTime() {
        return Utils.getCurrentTime(this.s);
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getConnectionReleasedTime() {
        return this.i;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getConnectionAcquiredTime() {
        return this.h;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getConnectStartTime() {
        return this.d;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getConnectEndTime() {
        return this.g;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getCallStartTime() {
        return this.f5536a;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.MetricsTime
    public long getCallEndTime() {
        return this.r;
    }

    public long getAndCheckEndTime(long j, long j2) {
        return (j == 0 || j2 != 0) ? j2 : Utils.getCurrentTime(this.s);
    }

    public v2(boolean z) {
        this.s = z;
    }

    public v2() {
    }
}

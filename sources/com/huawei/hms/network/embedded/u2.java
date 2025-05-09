package com.huawei.hms.network.embedded;

import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class u2 extends RequestFinishedInfo.Metrics {

    /* renamed from: a, reason: collision with root package name */
    public final List<String> f5513a = new ArrayList();
    public String b = "";
    public String c = "unknown";
    public int d = -1;
    public long e = -1;
    public long f = -1;
    public String g = "Reuse";
    public long h = -1;
    public int i = -1;
    public String j = "unknown";
    public String k = "unknown";
    public String l = "unknown";
    public int m = 0;
    public int n = -1;

    public void setTlsVersion(String str) {
        this.j = str;
    }

    public void setSuccessIp(String str) {
        this.b = str;
    }

    public void setResponseByteCount(long j) {
        this.f = j;
    }

    public void setRequestByteCount(long j) {
        this.e = j;
    }

    public void setProtocol(String str) {
        this.c = str;
    }

    public void setMultipathAlgorithm(int i) {
        this.m = i;
    }

    public void setDnsType(String str) {
        this.g = str;
    }

    public void setDnsTtl(long j) {
        this.h = j;
    }

    public void setDnsStatus(int i) {
        this.i = i;
    }

    public void setDnsCache(int i) {
        this.n = i;
    }

    public void setConnectRetryTime(int i) {
        this.d = i;
    }

    public void setConnectIps(List<String> list) {
        this.f5513a.addAll(list);
    }

    public void setCongestionControlType(String str) {
        this.l = str;
    }

    public void setCipherSuite(String str) {
        this.k = str;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public String getTlsVersion() {
        return this.j;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public String getSuccessIp() {
        return this.b;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public long getResponseByteCount() {
        return this.f;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public long getRequestByteCount() {
        return this.e;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public String getProtocol() {
        return this.c;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public int getMultipathAlgorithm() {
        return this.m;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public String getDnsType() {
        return this.g;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public long getDnsTtl() {
        return this.h;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public int getDnsStatus() {
        return this.i;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public int getDnsCache() {
        return this.n;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public int getConnectRetryTime() {
        return this.d;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public List<String> getConnectIps() {
        return this.f5513a;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public String getCongestionControlType() {
        return this.l;
    }

    @Override // com.huawei.hms.network.httpclient.RequestFinishedInfo.Metrics
    public String getCipherSuite() {
        return this.k;
    }

    public void addConnectIps(String str) {
        this.f5513a.add(str);
    }
}

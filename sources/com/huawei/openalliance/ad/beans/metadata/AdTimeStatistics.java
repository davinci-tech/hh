package com.huawei.openalliance.ad.beans.metadata;

/* loaded from: classes5.dex */
public class AdTimeStatistics {
    long adInfoPrepEndTS;
    long adLoadEndTS;
    long adLoadStartTS;
    long adNetReqEndTS;
    long adNetReqStartTS;
    long adPhyReqEndTS;
    long adPhyReqStartTS;
    long adRspParseEndTS;
    long adRspParseStartTS;
    long splashAdDownloadTS;
    long splashAdMaterialLoadedTS;

    public void k(long j) {
        this.splashAdMaterialLoadedTS = j;
    }

    public void j(long j) {
        this.splashAdDownloadTS = j;
    }

    public void i(long j) {
        this.adRspParseEndTS = j;
    }

    public long i() {
        return this.splashAdMaterialLoadedTS;
    }

    public void h(long j) {
        this.adRspParseStartTS = j;
    }

    public long h() {
        return this.splashAdDownloadTS;
    }

    public void g(long j) {
        this.adNetReqEndTS = j;
    }

    public long g() {
        return this.adRspParseEndTS;
    }

    public void f(long j) {
        this.adNetReqStartTS = j;
    }

    public long f() {
        return this.adRspParseStartTS;
    }

    public void e(long j) {
        this.adPhyReqEndTS = j;
    }

    public long e() {
        return this.adPhyReqEndTS;
    }

    public void d(long j) {
        this.adPhyReqStartTS = j;
    }

    public long d() {
        return this.adPhyReqStartTS;
    }

    public void c(long j) {
        this.adInfoPrepEndTS = j;
    }

    public long c() {
        return this.adInfoPrepEndTS;
    }

    public void b(long j) {
        this.adLoadEndTS = j;
    }

    public long b() {
        return this.adLoadEndTS;
    }

    public void a(long j) {
        this.adLoadStartTS = j;
    }

    public long a() {
        return this.adLoadStartTS;
    }
}

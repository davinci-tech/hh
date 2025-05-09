package com.huawei.openalliance.ad.beans.inner;

import com.huawei.openalliance.ad.beans.metadata.DelayInfo;

/* loaded from: classes5.dex */
public class ApiStatisticsReq {
    private int adType;
    private String additionId;
    private String apiName;
    private long callTime;
    private String contentId;
    private long costTime;
    private DelayInfo delayInfo;
    private String isLimitTracking;
    private String oaid;
    private String params;
    private String requestId;
    private int requestType;
    private int result;
    private int resultCode;
    private String service;

    public DelayInfo n() {
        return this.delayInfo;
    }

    public String m() {
        return this.contentId;
    }

    public int l() {
        return this.requestType;
    }

    public int k() {
        return this.adType;
    }

    public String j() {
        return this.requestId;
    }

    public String i() {
        return this.isLimitTracking;
    }

    public String h() {
        return this.additionId;
    }

    public String g() {
        return this.params;
    }

    public long f() {
        return this.costTime;
    }

    public long e() {
        return this.callTime;
    }

    public void d(String str) {
        this.requestId = str;
    }

    public void d(int i) {
        this.requestType = i;
    }

    public int d() {
        return this.resultCode;
    }

    public void c(String str) {
        this.params = str;
    }

    public void c(int i) {
        this.adType = i;
    }

    public int c() {
        return this.result;
    }

    public void b(String str) {
        this.apiName = str;
    }

    public void b(long j) {
        this.costTime = j;
    }

    public void b(int i) {
        this.resultCode = i;
    }

    public String b() {
        return this.apiName;
    }

    public void a(String str) {
        this.service = str;
    }

    public void a(DelayInfo delayInfo) {
        this.delayInfo = delayInfo;
    }

    public void a(long j) {
        this.callTime = j;
    }

    public void a(int i) {
        this.result = i;
    }

    public String a() {
        return this.service;
    }

    public ApiStatisticsReq(com.huawei.hms.ads.consent.bean.network.ApiStatisticsReq apiStatisticsReq) {
        this.callTime = System.currentTimeMillis();
        this.adType = -1;
        this.requestType = 0;
        if (apiStatisticsReq != null) {
            this.service = apiStatisticsReq.getService();
            this.apiName = apiStatisticsReq.getApiName();
            this.result = apiStatisticsReq.getResult();
            this.resultCode = apiStatisticsReq.getResultCode();
            this.callTime = apiStatisticsReq.getCallTime();
            this.costTime = apiStatisticsReq.getCostTime();
            this.params = apiStatisticsReq.getParams();
        }
    }

    public ApiStatisticsReq() {
        this.callTime = System.currentTimeMillis();
        this.adType = -1;
        this.requestType = 0;
    }
}

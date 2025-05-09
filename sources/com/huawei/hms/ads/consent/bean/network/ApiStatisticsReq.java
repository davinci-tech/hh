package com.huawei.hms.ads.consent.bean.network;

/* loaded from: classes9.dex */
public class ApiStatisticsReq {
    private String apiName;
    private long callTime = System.currentTimeMillis();
    private long costTime;
    private String params;
    private int result;
    private int resultCode;
    private String service;

    public void setService(String str) {
        this.service = str;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public void setResult(int i) {
        this.result = i;
    }

    public void setParams(String str) {
        this.params = str;
    }

    public void setCostTime(long j) {
        this.costTime = j;
    }

    public void setCallTime(long j) {
        this.callTime = j;
    }

    public void setApiName(String str) {
        this.apiName = str;
    }

    public String getService() {
        return this.service;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public int getResult() {
        return this.result;
    }

    public String getParams() {
        return this.params;
    }

    public long getCostTime() {
        return this.costTime;
    }

    public long getCallTime() {
        return this.callTime;
    }

    public String getApiName() {
        return this.apiName;
    }

    public com.huawei.openalliance.ad.beans.inner.ApiStatisticsReq convert() {
        com.huawei.openalliance.ad.beans.inner.ApiStatisticsReq apiStatisticsReq = new com.huawei.openalliance.ad.beans.inner.ApiStatisticsReq();
        apiStatisticsReq.a(getService());
        apiStatisticsReq.b(getApiName());
        apiStatisticsReq.a(getResult());
        apiStatisticsReq.b(getResultCode());
        apiStatisticsReq.a(getCallTime());
        apiStatisticsReq.b(getCostTime());
        apiStatisticsReq.c(getParams());
        return apiStatisticsReq;
    }
}

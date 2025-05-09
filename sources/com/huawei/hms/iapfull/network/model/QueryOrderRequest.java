package com.huawei.hms.iapfull.network.model;

/* loaded from: classes4.dex */
public class QueryOrderRequest {
    private String noiseTamp;
    private String orderNo;
    private String requestId;
    private String sign;
    private String signType;
    private long time;
    private String userID;

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public void setSignType(String str) {
        this.signType = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public void setNoiseTamp(String str) {
        this.noiseTamp = str;
    }

    public String getUserID() {
        return this.userID;
    }

    public long getTime() {
        return this.time;
    }

    public String getSignType() {
        return this.signType;
    }

    public String getSign() {
        return this.sign;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public String getNoiseTamp() {
        return this.noiseTamp;
    }
}

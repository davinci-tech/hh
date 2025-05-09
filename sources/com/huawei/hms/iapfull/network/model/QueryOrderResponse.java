package com.huawei.hms.iapfull.network.model;

/* loaded from: classes9.dex */
public class QueryOrderResponse extends BaseResponse {
    private String errCode;
    private String errMsg;
    private String orderID;
    private String orderTime;
    private String payType;
    private int status;
    private String tradeNo;
    private String tradeTime;

    public void setTradeTime(String str) {
        this.tradeTime = str;
    }

    public void setTradeNo(String str) {
        this.tradeNo = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public void setOrderTime(String str) {
        this.orderTime = str;
    }

    public void setOrderId(String str) {
        this.orderID = str;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setErrCode(String str) {
        this.errCode = str;
    }

    public String getTradeTime() {
        return this.tradeTime;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public int getStatus() {
        return this.status;
    }

    public String getPayType() {
        return this.payType;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public String getOrderId() {
        return this.orderID;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }
}

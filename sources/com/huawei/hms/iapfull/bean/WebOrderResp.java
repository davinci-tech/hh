package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;
import com.huawei.hms.iapfull.network.model.WebBasePayResponse;

/* loaded from: classes9.dex */
public class WebOrderResp extends WebBasePayResponse implements b1 {
    private String newSign;
    private String orderID;
    private String orderTime;
    private String requestId;
    private String sign;
    private String signatureAlgorithm;
    private String status;
    private String tradeTime;

    public void setTradeTime(String str) {
        this.tradeTime = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setOrderTime(String str) {
        this.orderTime = str;
    }

    public void setOrderID(String str) {
        this.orderID = str;
    }

    public void setNewSign(String str) {
        this.newSign = str;
    }

    public String getTradeTime() {
        return this.tradeTime;
    }

    public String getStatus() {
        return this.status;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public String getSign() {
        return this.sign;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public String getNewSign() {
        return this.newSign;
    }
}

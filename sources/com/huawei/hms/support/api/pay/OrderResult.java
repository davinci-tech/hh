package com.huawei.hms.support.api.pay;

import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.entity.pay.OrderResp;

/* loaded from: classes9.dex */
public class OrderResult extends Result {
    private String newSign;
    private String orderID;
    private String orderTime;
    private String requestId;
    private int returnCode;
    private String returnDesc;
    private String sign;
    private String signatureAlgorithm;
    private String status;
    private String tradeTime;

    private static <T> T get(T t) {
        return t;
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setNewSign(String str) {
        this.newSign = str;
    }

    public String getTradeTime() {
        return (String) get(this.tradeTime);
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public String getSign() {
        return (String) get(this.sign);
    }

    public String getReturnDesc() {
        return (String) get(this.returnDesc);
    }

    public int getReturnCode() {
        return ((Integer) get(Integer.valueOf(this.returnCode))).intValue();
    }

    public String getRequestId() {
        return (String) get(this.requestId);
    }

    public String getOrderTime() {
        return (String) get(this.orderTime);
    }

    public String getOrderStatus() {
        return (String) get(this.status);
    }

    public String getOrderID() {
        return (String) get(this.orderID);
    }

    public String getNewSign() {
        return this.newSign;
    }

    private void setTradeTime(String str) {
        this.tradeTime = str;
    }

    private void setSign(String str) {
        this.sign = str;
    }

    private void setReturnDesc(String str) {
        this.returnDesc = str;
    }

    private void setReturnCode(int i) {
        this.returnCode = i;
    }

    private void setRequestId(String str) {
        this.requestId = str;
    }

    private void setOrderTime(String str) {
        this.orderTime = str;
    }

    private void setOrderStatus(String str) {
        this.status = str;
    }

    private void setOrderID(String str) {
        this.orderID = str;
    }

    public OrderResult(OrderResp orderResp) {
        setReturnCode(orderResp.getReturnCode());
        setReturnDesc(orderResp.getReturnDesc());
        setRequestId(orderResp.getRequestId());
        setOrderID(orderResp.getOrderID());
        setOrderTime(orderResp.getOrderTime());
        setTradeTime(orderResp.getTradeTime());
        setOrderStatus(orderResp.getStatus());
        setSign(orderResp.getSign());
        setNewSign(orderResp.getNewSign());
        setSignatureAlgorithm(orderResp.getSignatureAlgorithm());
    }

    public OrderResult() {
    }
}

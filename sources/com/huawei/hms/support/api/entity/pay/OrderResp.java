package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class OrderResp extends AbstractMessageEntity {

    @Packed
    public String newSign;

    @Packed
    public String orderID;

    @Packed
    public String orderTime;

    @Packed
    public String requestId;

    @Packed
    public int returnCode;

    @Packed
    public String returnDesc;

    @Packed
    public String sign;

    @Packed
    public String signatureAlgorithm;

    @Packed
    public String status;

    @Packed
    public String tradeTime;

    private static <T> T get(T t) {
        return t;
    }

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

    public void setReturnDesc(String str) {
        this.returnDesc = str;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
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
        return (String) get(this.tradeTime);
    }

    public String getStatus() {
        return (String) get(this.status);
    }

    public String getSignatureAlgorithm() {
        return (String) get(this.signatureAlgorithm);
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

    public String getOrderID() {
        return (String) get(this.orderID);
    }

    public String getNewSign() {
        return this.newSign;
    }
}

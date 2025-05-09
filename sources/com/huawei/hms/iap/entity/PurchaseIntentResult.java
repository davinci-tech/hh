package com.huawei.hms.iap.entity;

import com.huawei.hms.support.api.client.Result;

/* loaded from: classes4.dex */
public class PurchaseIntentResult extends Result {
    private String errMsg;

    @Deprecated
    private String paymentData;

    @Deprecated
    private String paymentSignature;
    private int returnCode;
    private String signatureAlgorithm;

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    @Deprecated
    public void setPaymentSignature(String str) {
        this.paymentSignature = str;
    }

    @Deprecated
    public void setPaymentData(String str) {
        this.paymentData = str;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    @Deprecated
    public String getPaymentSignature() {
        return this.paymentSignature;
    }

    @Deprecated
    public String getPaymentData() {
        return this.paymentData;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}

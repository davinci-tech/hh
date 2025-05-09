package com.huawei.hms.iap.entity;

import com.huawei.hms.support.api.client.Result;

/* loaded from: classes9.dex */
public class ConsumeOwnedPurchaseResult extends Result {
    private String consumePurchaseData;
    private String dataSignature;
    private String errMsg;
    private int returnCode;
    private String signatureAlgorithm;

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setDataSignature(String str) {
        this.dataSignature = str;
    }

    public void setConsumePurchaseData(String str) {
        this.consumePurchaseData = str;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getDataSignature() {
        return this.dataSignature;
    }

    public String getConsumePurchaseData() {
        return this.consumePurchaseData;
    }
}

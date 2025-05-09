package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;
import com.huawei.hms.iapfull.network.model.WebBaseResponse;

/* loaded from: classes9.dex */
public class WebConsumeOwnedPurchaseResponse extends WebBaseResponse implements b1 {
    private String consumedPurchaseData;
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

    public void setConsumedPurchaseData(String str) {
        this.consumedPurchaseData = str;
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

    public String getConsumedPurchaseData() {
        return this.consumedPurchaseData;
    }
}

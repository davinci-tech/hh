package com.huawei.hms.iapfull.bean;

/* loaded from: classes4.dex */
public class WebOwnedPurchasesRequest extends BaseWebRequest {
    private String continuationToken;
    private int priceType;
    private String signatureAlgorithm;

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setPriceType(int i) {
        this.priceType = i;
    }

    public void setContinuationToken(String str) {
        this.continuationToken = str;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int getPriceType() {
        return this.priceType;
    }

    public String getContinuationToken() {
        return this.continuationToken;
    }
}

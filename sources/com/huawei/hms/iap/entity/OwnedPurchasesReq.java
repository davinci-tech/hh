package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes4.dex */
public class OwnedPurchasesReq extends BaseReq {

    @Packed
    private String continuationToken;

    @Packed
    private int priceType;

    @Packed
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

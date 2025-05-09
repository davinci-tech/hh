package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class ConsumeOwnedPurchaseReq extends BaseReq {

    @Packed
    private String developerChallenge;

    @Packed
    private String purchaseToken;

    @Packed
    private String signatureAlgorithm;

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setPurchaseToken(String str) {
        this.purchaseToken = str;
    }

    public void setDeveloperChallenge(String str) {
        this.developerChallenge = str;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public String getPurchaseToken() {
        return this.purchaseToken;
    }

    public String getDeveloperChallenge() {
        return this.developerChallenge;
    }
}

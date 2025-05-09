package com.huawei.hms.iapfull.bean;

/* loaded from: classes9.dex */
public class WebConsumeOwnedPurchaseRequest extends BaseWebRequest {
    private String developerChallenge;
    private String purchaseToken;
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

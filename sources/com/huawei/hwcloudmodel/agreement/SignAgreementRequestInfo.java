package com.huawei.hwcloudmodel.agreement;

/* loaded from: classes5.dex */
public class SignAgreementRequestInfo {
    private int agrType;
    private String country;
    private boolean isAgree;
    private String language;

    public SignAgreementRequestInfo(int i, boolean z, String str, String str2) {
        this.agrType = i;
        this.isAgree = z;
        this.country = str;
        this.language = str2;
    }

    public int getAgrType() {
        return this.agrType;
    }

    public void setAgrType(int i) {
        this.agrType = i;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public boolean isAgree() {
        return this.isAgree;
    }

    public void setAgree(boolean z) {
        this.isAgree = z;
    }
}

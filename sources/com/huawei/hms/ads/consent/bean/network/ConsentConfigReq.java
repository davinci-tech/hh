package com.huawei.hms.ads.consent.bean.network;

/* loaded from: classes9.dex */
public class ConsentConfigReq {
    private String consentVersion;
    private String countryCode;
    private Integer debugFlag;
    private String langCode;
    private String pkgName;

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public void setLangCode(String str) {
        this.langCode = str;
    }

    public void setDebugFlag(Integer num) {
        this.debugFlag = num;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setConsentVersion(String str) {
        this.consentVersion = str;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public String getLangCode() {
        return this.langCode;
    }

    public Integer getDebugFlag() {
        return this.debugFlag;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getConsentVersion() {
        return this.consentVersion;
    }
}

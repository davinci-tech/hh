package com.huawei.wisecloud.drmclient.license.entry;

/* loaded from: classes7.dex */
public class PayloadLicenseEntry {
    private String drmClientID;
    private String generateTime;
    private PayloadKeyInfoEntry keyInfo;
    private PayloadLicensePolicyEntry licensePolicy;
    private String licenseType;

    public String getLicenseType() {
        return this.licenseType;
    }

    public void setLicenseType(String str) {
        this.licenseType = str;
    }

    public String getDrmClientID() {
        return this.drmClientID;
    }

    public void setDrmClientID(String str) {
        this.drmClientID = str;
    }

    public PayloadKeyInfoEntry getKeyInfo() {
        return this.keyInfo;
    }

    public void setKeyInfo(PayloadKeyInfoEntry payloadKeyInfoEntry) {
        this.keyInfo = payloadKeyInfoEntry;
    }

    public PayloadLicensePolicyEntry getLicensePolicy() {
        return this.licensePolicy;
    }

    public void setLicensePolicy(PayloadLicensePolicyEntry payloadLicensePolicyEntry) {
        this.licensePolicy = payloadLicensePolicyEntry;
    }

    public String getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(String str) {
        this.generateTime = str;
    }
}

package com.huawei.maps.offlinedata.service.cloud.dto;

/* loaded from: classes5.dex */
public class AuthRequestDTO extends BaseRequestDTO {
    private String appId;
    private String certificateFingerprint;
    private String deviceModel;
    private String localeCountry;
    private String networkCountry;
    private String packageName;
    private String simCardCountry;
    private String vendorCountry;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getCertificateFingerprint() {
        return this.certificateFingerprint;
    }

    public void setCertificateFingerprint(String str) {
        this.certificateFingerprint = str;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getNetworkCountry() {
        return this.networkCountry;
    }

    public void setNetworkCountry(String str) {
        this.networkCountry = str;
    }

    public String getSimCardCountry() {
        return this.simCardCountry;
    }

    public void setSimCardCountry(String str) {
        this.simCardCountry = str;
    }

    public String getVendorCountry() {
        return this.vendorCountry;
    }

    public void setVendorCountry(String str) {
        this.vendorCountry = str;
    }

    public String getDeviceLocaleCountry() {
        return this.localeCountry;
    }

    public void setDeviceLocaleCountry(String str) {
        this.localeCountry = str;
    }

    public String toString() {
        return "AuthRequestDTO{appId='" + this.appId + "', packageName='" + this.packageName + "', deviceModel='" + this.deviceModel + "', networkCountry='" + this.networkCountry + "', simCardCountry='" + this.simCardCountry + "', vendorCountry='" + this.vendorCountry + "', localeCountry='" + this.localeCountry + "', requestId='" + getRequestId() + "'}";
    }
}

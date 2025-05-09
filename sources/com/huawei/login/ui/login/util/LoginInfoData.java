package com.huawei.login.ui.login.util;

/* loaded from: classes9.dex */
public class LoginInfoData {
    private String accoutName;
    private String countryCode;
    private String deviceId;
    private String deviceType;
    private String serviceCountryCode;
    private int siteId;
    private String strServiceToken;
    private String strUserId;

    public void configStrUserId(String str) {
        this.strUserId = str;
    }

    public String fetchStrUserId() {
        return this.strUserId;
    }

    public void configStrServiceToken(String str) {
        this.strServiceToken = str;
    }

    public String fetchStrServiceToken() {
        return this.strServiceToken;
    }

    public void configSiteId(int i) {
        this.siteId = i;
    }

    public int fetchSiteid() {
        return this.siteId;
    }

    public void configCountryCode(String str) {
        this.countryCode = str;
    }

    public String fetchCountryCode() {
        return this.countryCode;
    }

    public void configAccoutName(String str) {
        this.accoutName = str;
    }

    public String fetchAccoutName() {
        return this.accoutName;
    }

    public void configDeviceType(String str) {
        this.deviceType = str;
    }

    public String fetchDeviceType() {
        return this.deviceType;
    }

    public void configServiceCountryCode(String str) {
        this.serviceCountryCode = str;
    }

    public String fetchServiceCountryCode() {
        return this.serviceCountryCode;
    }

    public String fetchDeviceId() {
        return this.deviceId;
    }

    public void configDeviceId(String str) {
        this.deviceId = str;
    }
}

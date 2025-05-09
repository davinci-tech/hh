package com.huawei.hms.support.hwid.bean;

/* loaded from: classes9.dex */
public class CheckPasswordByUserIdReq {
    private String accountType;
    private String appId;
    private String deviceId;
    private String deviceId2;
    private String deviceType;
    private String guardianUserID;
    private String packageName;
    private String password;
    private int siteId;
    private String userId;

    public CheckPasswordByUserIdReq(String str, String str2, String str3, String str4, int i, String str5, String str6, String str7, String str8, String str9) {
        this.packageName = str;
        this.userId = str2;
        this.password = str3;
        this.accountType = str4;
        this.siteId = i;
        this.appId = str5;
        this.guardianUserID = str6;
        this.deviceId = str7;
        this.deviceId2 = str8;
        this.deviceType = str9;
    }

    public CheckPasswordByUserIdReq() {
        this.packageName = "";
        this.userId = "";
        this.password = "";
        this.accountType = "";
        this.appId = "";
        this.guardianUserID = "";
        this.deviceId = "";
        this.deviceId2 = "";
        this.deviceType = "";
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String str) {
        this.accountType = str;
    }

    public int getSiteId() {
        return this.siteId;
    }

    public void setSiteId(int i) {
        this.siteId = i;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getGuardianUserID() {
        return this.guardianUserID;
    }

    public void setGuardianUserID(String str) {
        this.guardianUserID = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getDeviceId2() {
        return this.deviceId2;
    }

    public void setDeviceId2(String str) {
        this.deviceId2 = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String toString() {
        return "CheckPasswordByUserIdReq{packageName='" + this.packageName + "', userId='" + this.userId + "', password='" + this.password + "', accountType='" + this.accountType + "', deviceId='" + this.deviceId + "', deviceId2='" + this.deviceId2 + "', deviceType='" + this.deviceType + "', appId='" + this.appId + "', guardianUserID='" + this.guardianUserID + "', siteId=" + this.siteId + '}';
    }
}

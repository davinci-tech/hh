package com.huawei.profile.coordinator;

/* loaded from: classes6.dex */
public class ProfileRequestInputParams {
    private String cloudDevId;
    private String devType;
    private String localDevId;
    private String pkgNames;
    private String sid;
    private String timestamp;

    public String getLocalDevId() {
        return this.localDevId;
    }

    public void setLocalDevId(String str) {
        this.localDevId = str;
    }

    public String getCloudDevId() {
        return this.cloudDevId;
    }

    public void setCloudDevId(String str) {
        this.cloudDevId = str;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String str) {
        this.sid = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getDevType() {
        return this.devType;
    }

    public void setDevType(String str) {
        this.devType = str;
    }

    public String getPkgNames() {
        return this.pkgNames;
    }

    public void setPkgNames(String str) {
        this.pkgNames = str;
    }
}

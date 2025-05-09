package com.huawei.hms.kit.awareness.quickapp;

/* loaded from: classes9.dex */
public class SubAppInfo {
    private String mSubAppId;
    private String mSubAppPackageName;
    private String mUrls;
    private String mVersionName = null;

    public void setVersionName(String str) {
        this.mVersionName = str;
    }

    public String getVersionName() {
        return this.mVersionName;
    }

    public String getUrls() {
        return this.mUrls;
    }

    public String getSubAppPackageName() {
        return this.mSubAppPackageName;
    }

    public String getSubAppId() {
        return this.mSubAppId;
    }

    public SubAppInfo(String str, String str2, String str3) {
        this.mSubAppId = str;
        this.mSubAppPackageName = str2;
        this.mUrls = str3;
    }
}

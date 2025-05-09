package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class Platform {

    @SerializedName("app_version")
    @Expose
    private String appVersion;

    @SerializedName("os")
    @Expose
    private String os;

    @SerializedName("os_version")
    @Expose
    private String osVersion;

    public void setOs(String str) {
        this.os = str;
    }

    public String getOs() {
        return this.os;
    }

    public Platform withOs(String str) {
        this.os = str;
        return this;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public Platform withOsVersion(String str) {
        this.osVersion = str;
        return this;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public Platform withAppVersion(String str) {
        this.appVersion = str;
        return this;
    }
}

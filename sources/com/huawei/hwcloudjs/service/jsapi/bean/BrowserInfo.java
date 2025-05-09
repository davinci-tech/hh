package com.huawei.hwcloudjs.service.jsapi.bean;

/* loaded from: classes9.dex */
public class BrowserInfo {
    private String browserType;
    private String clientVersion;
    private String clientVersionCode;

    public void setClientVersionCode(String str) {
        this.clientVersionCode = str;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public void setBrowserType(String str) {
        this.browserType = str;
    }

    public String getClientVersionCode() {
        return this.clientVersionCode;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public String getBrowserType() {
        return this.browserType;
    }
}

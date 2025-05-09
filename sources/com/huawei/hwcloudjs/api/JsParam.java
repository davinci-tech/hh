package com.huawei.hwcloudjs.api;

import com.huawei.hwcloudjs.support.enables.INoProguard;

/* loaded from: classes5.dex */
public class JsParam implements INoProguard {
    private String appId;
    private String bridgeId;
    private String webUrl;

    public void setWebUrl(String str) {
        this.webUrl = str;
    }

    public void setBridgeId(String str) {
        this.bridgeId = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public String getBridgeId() {
        return this.bridgeId;
    }

    public String getAppId() {
        return this.appId;
    }

    public JsParam(String str, String str2, String str3) {
        this.appId = str;
        this.webUrl = str2;
        this.bridgeId = str3;
    }

    public JsParam() {
    }
}

package com.huawei.picture.security.account.bean;

/* loaded from: classes6.dex */
public class HitopRespInfo {
    private String resultCode;
    private String resultInfo;
    private String url;

    public HitopRespInfo(String str, String str2, String str3) {
        this.url = str;
        this.resultCode = str2;
        this.resultInfo = str3;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String str) {
        this.resultInfo = str;
    }
}

package com.huawei.picture.security.account.bean;

/* loaded from: classes6.dex */
public class SignInRequest {
    private String appId;
    private String authCode;
    private String publicKey;
    private String redirectUri;
    private String sign;
    private String timestamp;
    private String userRefreshToken;

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String str) {
        this.authCode = str;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public String getUserRefreshToken() {
        return this.userRefreshToken;
    }

    public void setUserRefreshToken(String str) {
        this.userRefreshToken = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public void setRedirectUri(String str) {
        this.redirectUri = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }
}

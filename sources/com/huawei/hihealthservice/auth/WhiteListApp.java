package com.huawei.hihealthservice.auth;

/* loaded from: classes7.dex */
public class WhiteListApp {
    private String mAccessToken;
    private String mAppId;
    private String mAppScopes;
    private String mPackageName;
    private String mUserScopes;

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public void setAppId(String str) {
        this.mAppId = str;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public String getAppScopes() {
        return this.mAppScopes;
    }

    public void setAppScopes(String str) {
        this.mAppScopes = str;
    }

    public String getUserScopes() {
        return this.mUserScopes;
    }

    public void setUserScopes(String str) {
        this.mUserScopes = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("WhiteListApp{packageName='");
        stringBuffer.append(this.mPackageName).append("', appId='");
        stringBuffer.append(this.mAppId).append("', appScopes='");
        stringBuffer.append(this.mAppScopes).append("', userScopes='");
        stringBuffer.append(this.mUserScopes).append("'}");
        return stringBuffer.toString();
    }
}

package com.huawei.profile.account;

import android.os.Bundle;

/* loaded from: classes6.dex */
public class AccountInfo {
    private String accessToken;
    private long expireTime;
    private Bundle extraInfo;
    private int retCode;
    private String userId;

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public Bundle getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(Bundle bundle) {
        this.extraInfo = bundle;
    }

    public int getRetCode() {
        return this.retCode;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }
}

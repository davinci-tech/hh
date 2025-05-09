package com.huawei.hwcloudjs.service.hms.bean;

import com.huawei.hwcloudjs.api.Result;

/* loaded from: classes9.dex */
public class AccessTokenInfo implements Result {
    private String accessToken;
    private Status status;

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public boolean isSuccess() {
        return this.status.isSuccess();
    }

    @Override // com.huawei.hwcloudjs.api.Result
    public Status getStatus() {
        return this.status;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public AccessTokenInfo(String str, Status status) {
        this.accessToken = str;
        this.status = status;
    }
}

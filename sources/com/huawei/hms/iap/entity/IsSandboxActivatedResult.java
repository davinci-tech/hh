package com.huawei.hms.iap.entity;

import com.huawei.hms.support.api.client.Result;

/* loaded from: classes9.dex */
public class IsSandboxActivatedResult extends Result {
    private String errMsg;
    private Boolean isSandboxApk;
    private Boolean isSandboxUser;
    private int returnCode;
    private String versionFrMarket;
    private String versionInApk;

    public void setVersionInApk(String str) {
        this.versionInApk = str;
    }

    public void setVersionFrMarket(String str) {
        this.versionFrMarket = str;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setIsSandboxUser(Boolean bool) {
        this.isSandboxUser = bool;
    }

    public void setIsSandboxApk(Boolean bool) {
        this.isSandboxApk = bool;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public String getVersionInApk() {
        return this.versionInApk;
    }

    public String getVersionFrMarket() {
        return this.versionFrMarket;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public Boolean getIsSandboxUser() {
        return this.isSandboxUser;
    }

    public Boolean getIsSandboxApk() {
        return this.isSandboxApk;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}

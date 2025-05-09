package com.huawei.hms.iap.entity;

import com.huawei.hms.support.api.client.Result;

/* loaded from: classes4.dex */
public class IsEnvReadyResult extends Result {
    private int accountFlag;
    private String carrierId;
    private String country;
    private String errMsg;
    private int returnCode;

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setCarrierId(String str) {
        this.carrierId = str;
    }

    public void setAccountFlag(int i) {
        this.accountFlag = i;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCarrierId() {
        return this.carrierId;
    }

    @Deprecated
    public int getAccountFlag() {
        return this.accountFlag;
    }
}

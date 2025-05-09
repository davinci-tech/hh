package com.huawei.hms.iapfull.network.model;

/* loaded from: classes4.dex */
public class BaseResponse {
    protected String returnCode;
    protected String returnDesc;

    public void setReturnDesc(String str) {
        this.returnDesc = str;
    }

    public void setReturnCode(String str) {
        this.returnCode = str;
    }

    public boolean isSuccess() {
        return "0".equals(this.returnCode);
    }

    public String getReturnDesc() {
        return this.returnDesc;
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public BaseResponse(String str, String str2) {
        this.returnCode = str;
        this.returnDesc = str2;
    }

    public BaseResponse(String str) {
        this.returnCode = str;
    }

    public BaseResponse() {
        this.returnCode = "0";
        this.returnDesc = "success";
    }
}

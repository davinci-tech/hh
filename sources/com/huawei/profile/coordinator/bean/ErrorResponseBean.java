package com.huawei.profile.coordinator.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class ErrorResponseBean {

    @SerializedName("error_code")
    private int errorCode;

    @SerializedName("error_desc")
    private String errorDesc;

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String str) {
        this.errorDesc = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }
}

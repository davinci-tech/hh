package com.huawei.hwcloudmodel.model;

/* loaded from: classes.dex */
public class CloudCommonReponse {
    public static final int ERROR_AUTH_FAILED = 1002;
    public static final int ERROR_CLOUD_DATA_ERROR = 1;
    public static final int ERROR_TOKEN_TIMEOUT = 1004;
    private Integer resultCode;
    private String resultDesc;

    public Integer getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(Integer num) {
        this.resultCode = num;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public String toString() {
        return "CloudCommonReponse{resultCode=" + this.resultCode + ", resultDesc='" + this.resultDesc + "'}";
    }
}

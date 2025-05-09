package com.huawei.picture.security.account.bean;

/* loaded from: classes6.dex */
public class ErrorStatus {
    private int errorCode;
    private String errorReason;

    public ErrorStatus(int i, String str) {
        this.errorCode = i;
        this.errorReason = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getErrorReason() {
        return this.errorReason;
    }

    public void setErrorReason(String str) {
        this.errorReason = str;
    }
}

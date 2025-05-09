package com.huawei.hwcloudjs.service.hms.bean;

import com.huawei.hwcloudjs.api.Result;

/* loaded from: classes9.dex */
public final class Status implements Result {
    private boolean isSuccess;
    private int stateCode;
    private String stateMessage;

    @Override // com.huawei.hwcloudjs.api.Result
    public Status getStatus() {
        return null;
    }

    public void setSuccessFlag(boolean z) {
        this.isSuccess = z;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public String getStateMessage() {
        return this.stateMessage;
    }

    public int getStateCode() {
        return this.stateCode;
    }

    public Status(int i, String str) {
        this.stateCode = i;
        this.stateMessage = str;
    }

    public Status(int i) {
        this.stateCode = i;
    }
}

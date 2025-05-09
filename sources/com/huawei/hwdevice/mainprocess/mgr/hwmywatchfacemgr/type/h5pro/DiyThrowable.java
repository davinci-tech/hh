package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

/* loaded from: classes9.dex */
public class DiyThrowable extends Throwable {
    private int errorCode;
    private String message;

    public DiyThrowable(int i, String str) {
        this.errorCode = i;
        this.message = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}

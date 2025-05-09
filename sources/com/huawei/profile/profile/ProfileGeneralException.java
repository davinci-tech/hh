package com.huawei.profile.profile;

/* loaded from: classes9.dex */
public class ProfileGeneralException extends RuntimeException {
    private static final long serialVersionUID = 9100309738464449714L;
    String message;
    int retCode;

    public ProfileGeneralException(int i, String str) {
        this(i, str, null);
    }

    public ProfileGeneralException(int i, String str, Throwable th) {
        super(str, th);
        this.retCode = i;
        this.message = str;
    }

    public int getRetCode() {
        return this.retCode;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}

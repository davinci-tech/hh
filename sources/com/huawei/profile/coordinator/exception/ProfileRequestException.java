package com.huawei.profile.coordinator.exception;

/* loaded from: classes6.dex */
public class ProfileRequestException extends Exception {
    private static final long serialVersionUID = 4316174546223099322L;
    private String message;
    private ProfileRequestExceptionType type;

    public ProfileRequestException(ProfileRequestExceptionType profileRequestExceptionType, String str) {
        this.type = profileRequestExceptionType;
        this.message = str;
    }

    public ProfileRequestExceptionType getErrorType() {
        return this.type;
    }

    public int getErrorTypeValue() {
        return this.type.getExceptionTypeValue();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}

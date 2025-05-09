package com.huawei.hms.network.file.core;

import com.huawei.hms.network.file.core.Constants;

/* loaded from: classes4.dex */
public class FileManagerException extends RuntimeException {
    int errorCode;
    String errorMessage;

    @Override // java.lang.Throwable
    public String toString() {
        return "FileManagerException{errorCode=" + this.errorCode + ", errorMessage='" + this.errorMessage + "'}";
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.errorMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public FileManagerException(String str) {
        super(str);
        this.errorMessage = str;
    }

    public FileManagerException(Constants.ErrorCode errorCode, Throwable th) {
        super(th);
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public FileManagerException(Constants.ErrorCode errorCode, String str) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage() + ":" + str;
    }

    public FileManagerException(Constants.ErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public FileManagerException(int i, String str, Throwable th) {
        super(th);
        this.errorMessage = str;
        this.errorCode = i;
    }

    public FileManagerException(int i, String str) {
        this.errorMessage = str;
        this.errorCode = i;
    }
}

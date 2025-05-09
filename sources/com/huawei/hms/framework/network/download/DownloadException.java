package com.huawei.hms.framework.network.download;

import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;

/* loaded from: classes9.dex */
public class DownloadException extends Exception {
    private static final long serialVersionUID = 2192976228383004484L;
    private int errorCode;
    private String errorMessage;

    public DownloadException() {
    }

    public DownloadException(String str) {
        super(str);
        this.errorMessage = str;
    }

    public DownloadException(int i, String str) {
        this(str);
        Integer num = ExceptionCode.ERROR_CODE_MAP.get(Integer.valueOf(i));
        if (num != null) {
            this.errorCode = num.intValue();
        } else {
            this.errorCode = i;
        }
    }

    public DownloadException(String str, Throwable th) {
        super(str, th);
        this.errorMessage = str;
    }

    public DownloadException(int i, String str, Throwable th) {
        this(str, th);
        Integer num = ExceptionCode.ERROR_CODE_MAP.get(Integer.valueOf(i));
        if (num != null) {
            this.errorCode = num.intValue();
        } else {
            this.errorCode = i;
        }
    }

    public DownloadException(Throwable th) {
        super(th);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "errorCode:" + this.errorCode + ", errorMessage:" + this.errorMessage;
    }
}

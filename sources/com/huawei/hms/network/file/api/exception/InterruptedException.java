package com.huawei.hms.network.file.api.exception;

/* loaded from: classes4.dex */
public class InterruptedException extends NetworkException {
    private int statusCode;

    public int getStatusCode() {
        return this.statusCode;
    }

    public InterruptedException(String str, Throwable th) {
        super(str, th);
    }

    public InterruptedException(String str) {
        super(str);
    }

    public InterruptedException(int i, String str, Throwable th) {
        super(str, th);
        this.statusCode = i;
    }
}

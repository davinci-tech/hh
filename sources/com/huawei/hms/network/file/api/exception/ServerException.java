package com.huawei.hms.network.file.api.exception;

/* loaded from: classes4.dex */
public class ServerException extends HttpException {
    private int statusCode;

    public int getStatusCode() {
        return this.statusCode;
    }

    public ServerException(String str, Throwable th) {
        super(str, th);
    }

    public ServerException(String str) {
        super(str);
    }

    public ServerException(int i, String str, Throwable th) {
        super(str, th);
        this.statusCode = i;
    }
}

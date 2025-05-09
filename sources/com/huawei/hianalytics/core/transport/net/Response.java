package com.huawei.hianalytics.core.transport.net;

/* loaded from: classes4.dex */
public class Response {
    public final String content;
    public final int httpCode;

    /* loaded from: classes8.dex */
    public interface Code {
        public static final int CONNECTION_ERROR = -103;
        public static final int HOST_ERROR = -104;
        public static final int INTERNET_PERMISSION_ERROR = -101;
        public static final int SSL_VALIDATION_ERROR = -105;
        public static final int TIMEOUT_OR_OTHER_ERROR = -102;
        public static final int UNKNOWN_ERROR = -108;
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    public String getContent() {
        return this.content;
    }

    public Response(int i, String str) {
        this.httpCode = i;
        this.content = str;
    }
}

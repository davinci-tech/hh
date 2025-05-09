package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.network.httpclient.Response;

/* loaded from: classes9.dex */
public class HttpException extends RuntimeException {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    public HttpException(Response<?> response) {
        super(getMessage(response));
        this.code = response.getCode();
        this.message = response.getMessage();
        this.response = response;
    }

    private static String getMessage(Response<?> response) {
        CheckParamUtils.checkNotNull(response, "response == null");
        return "HTTP " + response.getCode() + " " + response.getMessage();
    }

    public int getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public Response<?> getResponse() {
        return this.response;
    }
}

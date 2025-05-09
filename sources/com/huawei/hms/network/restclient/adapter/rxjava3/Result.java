package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.network.httpclient.Response;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class Result<T> {

    @Nullable
    private final Throwable error;

    @Nullable
    private final Response<T> response;

    private Result(@Nullable Response<T> response, @Nullable Throwable th) {
        this.error = th;
        this.response = response;
    }

    public static <T> Result<T> error(Throwable th) {
        if (th == null) {
            throw new NullPointerException("error == null");
        }
        return new Result<>(null, th);
    }

    public static <T> Result<T> response(Response<T> response) {
        if (response == null) {
            throw new NullPointerException("response == null");
        }
        return new Result<>(response, null);
    }

    @Nullable
    public Response<T> response() {
        return this.response;
    }

    @Nullable
    public Throwable error() {
        return this.error;
    }

    public boolean isError() {
        return this.error != null;
    }
}

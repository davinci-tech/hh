package com.huawei.hms.network.httpclient;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class Response<T> implements Closeable {

    /* loaded from: classes9.dex */
    public static abstract class Builder<T> {
        public abstract Builder body(T t);

        public abstract Response<T> build();

        public abstract Builder code(int i);

        public abstract Builder errorBody(ResponseBody responseBody);

        public abstract Builder headers(Map<String, List<String>> map);

        public abstract Builder message(String str);

        public abstract Builder url(String str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public abstract Builder createBuilder();

    public abstract T getBody();

    public abstract int getCode();

    public abstract ResponseBody getErrorBody();

    public abstract Map<String, List<String>> getHeaders();

    public abstract String getMessage();

    public abstract String getUrl();

    public boolean isSuccessful() {
        int code = getCode();
        return code >= 200 && code < 300;
    }

    public boolean isOK() {
        return getCode() == 200;
    }
}

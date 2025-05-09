package com.huawei.hms.framework.network.restclient.proxy.new2old;

import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class ResponseImp<T> extends Response<T> {
    private T body;
    private int code;
    private ResponseBody errorBody;
    private Map<String, List<String>> headers;
    private String message;
    private String url;

    @Override // com.huawei.hms.network.httpclient.Response
    public Builder createBuilder() {
        return new Builder(this);
    }

    private ResponseImp(Builder<T> builder) {
        this.body = (T) ((Builder) builder).body;
        this.errorBody = ((Builder) builder).errorBody;
        this.headers = ((Builder) builder).headers;
        this.code = ((Builder) builder).code;
        this.message = ((Builder) builder).message;
        this.url = ((Builder) builder).url;
        reBuildBody();
    }

    private void reBuildBody() {
        if (this.errorBody == null && (this.body instanceof ResponseBody) && !isSuccessful()) {
            this.errorBody = (ResponseBody) this.body;
            this.body = null;
        }
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public int getCode() {
        return this.code;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public String getMessage() {
        return this.message;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public T getBody() {
        return this.body;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public ResponseBody getErrorBody() {
        return this.errorBody;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public String getUrl() {
        return this.url;
    }

    @Override // com.huawei.hms.network.httpclient.Response, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        T t = this.body;
        if (t instanceof Closeable) {
            ((Closeable) t).close();
            this.body = null;
        }
        ResponseBody responseBody = this.errorBody;
        if (responseBody != null) {
            responseBody.close();
            this.errorBody = null;
        }
    }

    public static class Builder<T> extends Response.Builder<T> {
        private T body;
        private int code;
        private ResponseBody errorBody;
        private Map<String, List<String>> headers;
        private String message;
        private String url;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public /* bridge */ /* synthetic */ Response.Builder body(Object obj) {
            return body((Builder<T>) obj);
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public /* bridge */ /* synthetic */ Response.Builder headers(Map map) {
            return headers((Map<String, List<String>>) map);
        }

        public Builder() {
        }

        Builder(ResponseImp<T> responseImp) {
            this.body = (T) ((ResponseImp) responseImp).body;
            this.headers = ((ResponseImp) responseImp).headers;
            this.code = ((ResponseImp) responseImp).code;
            this.message = ((ResponseImp) responseImp).message;
            this.url = ((ResponseImp) responseImp).url;
            this.errorBody = ((ResponseImp) responseImp).errorBody;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Builder body(T t) {
            this.body = t;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Builder url(String str) {
            this.url = str;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Builder headers(Map<String, List<String>> map) {
            this.headers = map;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Builder code(int i) {
            this.code = i;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Builder message(String str) {
            this.message = str;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Builder errorBody(ResponseBody responseBody) {
            this.errorBody = responseBody;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public ResponseImp<T> build() {
            return new ResponseImp<>(this);
        }
    }
}

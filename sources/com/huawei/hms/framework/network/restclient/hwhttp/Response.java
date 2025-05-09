package com.huawei.hms.framework.network.restclient.hwhttp;

import com.huawei.hms.framework.network.restclient.Headers;
import java.io.Closeable;
import java.io.IOException;
import java.net.URL;

@Deprecated
/* loaded from: classes4.dex */
public class Response implements Closeable {
    private static final String TAG = "Response";
    private ResponseBody body;
    private int code;
    private Headers headers;
    private String message;
    private URL url;

    private Response(Builder builder) {
        this.body = builder.body;
        this.headers = builder.headers;
        this.code = builder.code;
        this.message = builder.message;
        this.url = builder.url;
    }

    public Builder newBuilder() {
        return new Builder();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ResponseBody responseBody = this.body;
        if (responseBody != null) {
            responseBody.close();
            this.body = null;
        }
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public ResponseBody getBody() {
        return this.body;
    }

    public boolean isOK() {
        return this.code == 200;
    }

    public boolean isSuccessful() {
        int i = this.code;
        return i >= 200 && i < 300;
    }

    public URL getUrl() {
        return this.url;
    }

    @Deprecated
    public static final class Builder {
        private ResponseBody body;
        private int code;
        private Headers headers;
        private String message;
        private URL url;

        public Builder() {
        }

        private Builder(Response response) {
            this.body = response.body;
            this.headers = response.headers;
            this.code = response.code;
            this.message = response.message;
            this.url = response.url;
        }

        public Builder headers(Headers headers) {
            this.headers = headers;
            return this;
        }

        public Builder body(ResponseBody responseBody) {
            this.body = responseBody;
            return this;
        }

        public Builder code(int i) {
            this.code = i;
            return this;
        }

        public Builder message(String str) {
            this.message = str;
            return this;
        }

        public Builder url(URL url) {
            this.url = url;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}

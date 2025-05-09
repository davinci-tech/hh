package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class u0<T> extends Response<T> {
    public static final int g = 100;

    /* renamed from: a, reason: collision with root package name */
    public T f5509a;
    public h1.g b;
    public Map<String, List<String>> c;
    public int d;
    public String e;
    public String f;

    public b newBuilder() {
        return new b(this);
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public String getUrl() {
        return this.f;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public String getMessage() {
        return this.e;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public Map<String, List<String>> getHeaders() {
        return this.c;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public ResponseBody getErrorBody() {
        return this.b;
    }

    public static class b<T> {

        /* renamed from: a, reason: collision with root package name */
        public T f5510a;
        public h1.g b;
        public Map<String, List<String>> c;
        public int d;
        public String e;
        public String f;

        public b url(String str) {
            this.f = str;
            return this;
        }

        public b message(String str) {
            this.e = str;
            return this;
        }

        public b headers(Map<String, List<String>> map) {
            this.c = map;
            return this;
        }

        public b errorBody(ResponseBody responseBody) {
            if (responseBody == null || (responseBody instanceof h1.g)) {
                this.b = (h1.g) responseBody;
            } else {
                this.b = new h1.g(responseBody);
            }
            return this;
        }

        public b code(int i) {
            this.d = i;
            return this;
        }

        public u0<T> build() {
            return new u0<>(this);
        }

        public b body(T t) {
            this.f5510a = t;
            return this;
        }

        public b(u0<T> u0Var) {
            this.f5510a = (T) u0Var.f5509a;
            this.c = u0Var.c;
            this.d = u0Var.d;
            this.e = u0Var.e;
            this.f = u0Var.f;
            this.b = u0Var.b;
        }

        public b() {
        }
    }

    public static class c<T> extends Response.Builder<T> {

        /* renamed from: a, reason: collision with root package name */
        public T f5511a;
        public h1.g b;
        public Map<String, List<String>> c;
        public int d;
        public String e;
        public String f;

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Response.Builder url(String str) {
            this.f = str;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Response.Builder message(String str) {
            this.e = str;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Response.Builder headers(Map<String, List<String>> map) {
            this.c = map;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Response.Builder errorBody(ResponseBody responseBody) {
            if (responseBody == null || (responseBody instanceof h1.g)) {
                this.b = (h1.g) responseBody;
            } else {
                this.b = new h1.g(responseBody);
            }
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Response.Builder code(int i) {
            this.d = i;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Response<T> build() {
            return new u0(this);
        }

        @Override // com.huawei.hms.network.httpclient.Response.Builder
        public Response.Builder body(T t) {
            this.f5511a = t;
            return this;
        }

        public c(u0<T> u0Var) {
            this.f5511a = (T) u0Var.f5509a;
            this.c = u0Var.c;
            this.d = u0Var.d;
            this.e = u0Var.e;
            this.f = u0Var.f;
            this.b = u0Var.b;
        }

        public c() {
        }
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public int getCode() {
        return this.d;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public T getBody() {
        return this.f5509a;
    }

    @Override // com.huawei.hms.network.httpclient.Response
    public Response.Builder createBuilder() {
        return new c(this);
    }

    @Override // com.huawei.hms.network.httpclient.Response, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        T t = this.f5509a;
        if (t instanceof Closeable) {
            ((Closeable) t).close();
            this.f5509a = null;
        }
        h1.g gVar = this.b;
        if (gVar != null) {
            gVar.close();
            this.b = null;
        }
    }

    private void s() {
        if (this.b == null && (this.f5509a instanceof h1.g) && !isSuccessful()) {
            this.b = (h1.g) this.f5509a;
            this.f5509a = null;
        }
    }

    public static boolean hasBody(Response<ResponseBody> response) {
        int code = response.getCode();
        if ((code < 100 || code >= 200) && code != 204 && code != 304) {
            return true;
        }
        Headers of = Headers.of(response.getHeaders());
        String str = of.get("Content-Length");
        return !(str.isEmpty() || StringUtils.stringToLong(str, -1L) == -1) || "chunked".equalsIgnoreCase(of.get("Transfer-Encoding"));
    }

    public u0(c<T> cVar) {
        this.f5509a = (T) cVar.f5511a;
        this.b = cVar.b;
        this.c = cVar.c;
        this.d = cVar.d;
        this.e = cVar.e;
        this.f = cVar.f;
        s();
    }

    public u0(b<T> bVar) {
        this.f5509a = (T) bVar.f5510a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.e = bVar.e;
        this.f = bVar.f;
        s();
    }
}

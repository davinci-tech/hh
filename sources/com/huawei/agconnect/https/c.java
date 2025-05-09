package com.huawei.agconnect.https;

import com.huawei.openalliance.ad.constant.Constants;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/* loaded from: classes8.dex */
class c implements Interceptor {

    static class a extends RequestBody {

        /* renamed from: a, reason: collision with root package name */
        private final RequestBody f1801a;

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return -1L;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            BufferedSink buffer = Okio.buffer(new GzipSink(bufferedSink));
            this.f1801a.writeTo(buffer);
            buffer.close();
        }

        @Override // okhttp3.RequestBody
        /* renamed from: contentType */
        public MediaType get$contentType() {
            return MediaType.parse("application/x-gzip");
        }

        public a(RequestBody requestBody) {
            this.f1801a = requestBody;
        }
    }

    static class b extends RequestBody {

        /* renamed from: a, reason: collision with root package name */
        RequestBody f1802a;
        Buffer b;

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(this.b.snapshot());
        }

        @Override // okhttp3.RequestBody
        /* renamed from: contentType */
        public MediaType get$contentType() {
            return this.f1802a.get$contentType();
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return this.b.size();
        }

        b(RequestBody requestBody) throws IOException {
            this.b = null;
            this.f1802a = requestBody;
            Buffer buffer = new Buffer();
            this.b = buffer;
            requestBody.writeTo(buffer);
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        return (request.body() == null || request.header("Content-Encoding") != null) ? chain.proceed(request) : chain.proceed(request.newBuilder().header("Content-Encoding", Constants.GZIP).method(request.method(), a(b(request.body()))).build());
    }

    private RequestBody b(RequestBody requestBody) {
        return new a(requestBody);
    }

    private RequestBody a(RequestBody requestBody) throws IOException {
        return new b(requestBody);
    }

    c() {
    }
}

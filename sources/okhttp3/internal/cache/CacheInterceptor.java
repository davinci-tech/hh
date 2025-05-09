package okhttp3.internal.cache;

import com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation;
import com.huawei.hms.network.embedded.y;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.uhy;
import defpackage.uib;
import defpackage.ujy;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor;", "Lokhttp3/Interceptor;", "cache", "Lokhttp3/Cache;", "(Lokhttp3/Cache;)V", "getCache$okhttp", "()Lokhttp3/Cache;", "cacheWritingResponse", "Lokhttp3/Response;", "cacheRequest", "Lokhttp3/internal/cache/CacheRequest;", TrackConstants$Opers.RESPONSE, "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class CacheInterceptor implements Interceptor {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Cache cache;

    public CacheInterceptor(Cache cache) {
        this.cache = cache;
    }

    /* renamed from: getCache$okhttp, reason: from getter */
    public final Cache getCache() {
        return this.cache;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
    
        if (r2 == null) goto L15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink body = cacheRequest.getBody();
        ResponseBody body2 = response.body();
        uhy.d(body2);
        final BufferedSource source = body2.getSource();
        final BufferedSink buffer = Okio.buffer(body);
        Source source2 = new Source() { // from class: okhttp3.internal.cache.CacheInterceptor$cacheWritingResponse$cacheWritingSource$1
            private boolean cacheRequestClosed;

            @Override // okio.Source
            public long read(Buffer sink, long byteCount) throws IOException {
                uhy.e((Object) sink, "");
                try {
                    long read = BufferedSource.this.read(sink, byteCount);
                    if (read == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            buffer.close();
                        }
                        return -1L;
                    }
                    sink.copyTo(buffer.getBuffer(), sink.size() - read, read);
                    buffer.emitCompleteSegments();
                    return read;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            @Override // okio.Source
            /* renamed from: timeout */
            public Timeout getTimeout() {
                return BufferedSource.this.getTimeout();
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                BufferedSource.this.close();
            }
        };
        String header$default = Response.header$default(response, "Content-Type", null, 2, null);
        long contentLength = response.body().getContentLength();
        Response.Builder newBuilder = !(response instanceof Response.Builder) ? response.newBuilder() : OkHttp3Instrumentation.newBuilder((Response.Builder) response);
        RealResponseBody realResponseBody = new RealResponseBody(header$default, contentLength, Okio.buffer(source2));
        return (!(newBuilder instanceof Response.Builder) ? newBuilder.body(realResponseBody) : OkHttp3Instrumentation.body(newBuilder, realResponseBody)).build();
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0014\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor$Companion;", "", "()V", "combine", "Lokhttp3/Headers;", "cachedHeaders", "networkHeaders", "isContentSpecificHeader", "", "fieldName", "", "isEndToEnd", "stripBody", "Lokhttp3/Response;", TrackConstants$Opers.RESPONSE, y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public final Response stripBody(Response response) {
            if ((response != 0 ? response.body() : null) == null) {
                return response;
            }
            Response.Builder newBuilder = !(response instanceof Response.Builder) ? response.newBuilder() : OkHttp3Instrumentation.newBuilder((Response.Builder) response);
            return (!(newBuilder instanceof Response.Builder) ? newBuilder.body(null) : OkHttp3Instrumentation.body(newBuilder, null)).build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Headers combine(Headers cachedHeaders, Headers networkHeaders) {
            Headers.Builder builder = new Headers.Builder();
            int size = cachedHeaders.size();
            for (int i = 0; i < size; i++) {
                String name = cachedHeaders.name(i);
                String value = cachedHeaders.value(i);
                if ((!ujy.c("Warning", name, true) || !ujy.c(value, "1", false, 2, (Object) null)) && (isContentSpecificHeader(name) || !isEndToEnd(name) || networkHeaders.get(name) == null)) {
                    builder.addLenient$okhttp(name, value);
                }
            }
            int size2 = networkHeaders.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String name2 = networkHeaders.name(i2);
                if (!isContentSpecificHeader(name2) && isEndToEnd(name2)) {
                    builder.addLenient$okhttp(name2, networkHeaders.value(i2));
                }
            }
            return builder.build();
        }

        private final boolean isEndToEnd(String fieldName) {
            return (ujy.c("Connection", fieldName, true) || ujy.c("Keep-Alive", fieldName, true) || ujy.c("Proxy-Authenticate", fieldName, true) || ujy.c("Proxy-Authorization", fieldName, true) || ujy.c("TE", fieldName, true) || ujy.c("Trailers", fieldName, true) || ujy.c("Transfer-Encoding", fieldName, true) || ujy.c("Upgrade", fieldName, true)) ? false : true;
        }

        private final boolean isContentSpecificHeader(String fieldName) {
            return ujy.c("Content-Length", fieldName, true) || ujy.c("Content-Encoding", fieldName, true) || ujy.c("Content-Type", fieldName, true);
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }
}

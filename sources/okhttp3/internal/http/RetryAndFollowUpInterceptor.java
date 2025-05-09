package okhttp3.internal.http;

import androidx.core.app.NotificationCompat;
import com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation;
import com.huawei.hms.network.embedded.q0;
import com.huawei.hms.network.embedded.y;
import defpackage.uhy;
import defpackage.ujw;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J(\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "buildRedirectRequest", "Lokhttp3/Request;", "userResponse", "Lokhttp3/Response;", "method", "", "followUpRequest", "exchange", "Lokhttp3/internal/connection/Exchange;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "isRecoverable", "", "e", "Ljava/io/IOException;", "requestSendStarted", "recover", NotificationCompat.CATEGORY_CALL, "Lokhttp3/internal/connection/RealCall;", "userRequest", "requestIsOneShot", "retryAfter", "", "defaultDelay", "Companion", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient) {
        uhy.e((Object) okHttpClient, "");
        this.client = okHttpClient;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
    
        if (r5 == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
    
        if ((r0 instanceof okhttp3.Response.Builder) != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
    
        r0 = r0.newBuilder();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
    
        if ((r5 instanceof okhttp3.Response.Builder) != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
    
        r5 = r5.newBuilder();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0048, code lost:
    
        if ((r5 instanceof okhttp3.Response.Builder) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
    
        r5 = r5.body(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
    
        r0 = r0.priorResponse(r5.build()).build();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004f, code lost:
    
        r8 = r5;
        r5 = com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation.body(r5, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        r5 = com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation.newBuilder((okhttp3.Response.Builder) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0031, code lost:
    
        r0 = com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation.newBuilder((okhttp3.Response.Builder) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0062, code lost:
    
        r5 = r0;
        r0 = r1.getInterceptorScopedExchange();
        r8 = followUpRequest(r5, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006b, code lost:
    
        if (r8 != null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007c, code lost:
    
        r0 = r8.body();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0080, code lost:
    
        if (r0 == null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0086, code lost:
    
        if (r0.isOneShot() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0088, code lost:
    
        r1.exitNetworkInterceptorExchange$okhttp(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008b, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008c, code lost:
    
        r0 = r5.body();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0090, code lost:
    
        if (r0 == null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0092, code lost:
    
        okhttp3.internal.Util.closeQuietly(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0097, code lost:
    
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x009a, code lost:
    
        if (r6 > 20) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b8, code lost:
    
        throw new java.net.ProtocolException("Too many follow-up requests: " + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006d, code lost:
    
        if (r0 == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0073, code lost:
    
        if (r0.getIsDuplex() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0075, code lost:
    
        r1.timeoutEarlyExit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0078, code lost:
    
        r1.exitNetworkInterceptorExchange$okhttp(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x007b, code lost:
    
        return r5;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v23, types: [okhttp3.Response] */
    /* JADX WARN: Type inference failed for: r0v8, types: [okhttp3.Response] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [okhttp3.Response] */
    /* JADX WARN: Type inference failed for: r5v11 */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private final boolean recover(IOException e, RealCall call, Request userRequest, boolean requestSendStarted) {
        if (this.client.retryOnConnectionFailure()) {
            return !(requestSendStarted && requestIsOneShot(e, userRequest)) && isRecoverable(e, requestSendStarted) && call.retryAfterFailure();
        }
        return false;
    }

    private final boolean requestIsOneShot(IOException e, Request userRequest) {
        RequestBody body = userRequest.body();
        return (body != null && body.isOneShot()) || (e instanceof FileNotFoundException);
    }

    private final boolean isRecoverable(IOException e, boolean requestSendStarted) {
        if (e instanceof ProtocolException) {
            return false;
        }
        return e instanceof InterruptedIOException ? (e instanceof SocketTimeoutException) && !requestSendStarted : (((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) || (e instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private final Request followUpRequest(Response userResponse, Exchange exchange) throws IOException {
        RealConnection connection;
        Route route = (exchange == null || (connection = exchange.getConnection()) == null) ? null : connection.getRoute();
        int code = userResponse.code();
        String method = userResponse.request().method();
        if (code != 307 && code != 308) {
            if (code == 401) {
                return this.client.authenticator().authenticate(route, userResponse);
            }
            if (code == 421) {
                RequestBody body = userResponse.request().body();
                if ((body != null && body.isOneShot()) || exchange == null || !exchange.isCoalescedConnection$okhttp()) {
                    return null;
                }
                exchange.getConnection().noCoalescedConnections$okhttp();
                return userResponse.request();
            }
            if (code == 503) {
                Response priorResponse = userResponse.priorResponse();
                if ((priorResponse == null || priorResponse.code() != 503) && retryAfter(userResponse, Integer.MAX_VALUE) == 0) {
                    return userResponse.request();
                }
                return null;
            }
            if (code == 407) {
                uhy.d(route);
                if (route.proxy().type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return this.client.proxyAuthenticator().authenticate(route, userResponse);
            }
            if (code == 408) {
                if (!this.client.retryOnConnectionFailure()) {
                    return null;
                }
                RequestBody body2 = userResponse.request().body();
                if (body2 != null && body2.isOneShot()) {
                    return null;
                }
                Response priorResponse2 = userResponse.priorResponse();
                if ((priorResponse2 == null || priorResponse2.code() != 408) && retryAfter(userResponse, 0) <= 0) {
                    return userResponse.request();
                }
                return null;
            }
            switch (code) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        return buildRedirectRequest(userResponse, method);
    }

    private final Request buildRedirectRequest(Response userResponse, String method) {
        String header$default;
        HttpUrl resolve;
        if (!this.client.followRedirects() || (header$default = Response.header$default(userResponse, "Location", null, 2, null)) == null || (resolve = userResponse.request().url().resolve(header$default)) == null) {
            return null;
        }
        if (!uhy.e((Object) resolve.scheme(), (Object) userResponse.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder newBuilder = userResponse.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            int code = userResponse.code();
            boolean z = HttpMethod.INSTANCE.redirectsWithBody(method) || code == 308 || code == 307;
            if (HttpMethod.INSTANCE.redirectsToGet(method) && code != 308 && code != 307) {
                newBuilder.method("GET", null);
            } else {
                newBuilder.method(method, z ? userResponse.request().body() : null);
            }
            if (!z) {
                newBuilder.removeHeader("Transfer-Encoding");
                newBuilder.removeHeader("Content-Length");
                newBuilder.removeHeader("Content-Type");
            }
        }
        if (!Util.canReuseConnectionFor(userResponse.request().url(), resolve)) {
            newBuilder.removeHeader("Authorization");
        }
        Request.Builder url = newBuilder.url(resolve);
        return !(url instanceof Request.Builder) ? url.build() : OkHttp3Instrumentation.build(url);
    }

    private final int retryAfter(Response userResponse, int defaultDelay) {
        String header$default = Response.header$default(userResponse, q0.f, null, 2, null);
        if (header$default == null) {
            return defaultDelay;
        }
        if (!new ujw("\\d+").b(header$default)) {
            return Integer.MAX_VALUE;
        }
        Integer valueOf = Integer.valueOf(header$default);
        uhy.a(valueOf, "");
        return valueOf.intValue();
    }
}

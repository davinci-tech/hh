package okhttp3.internal.http;

import com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.y;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.ufe;
import defpackage.uhy;
import defpackage.ujy;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.GzipSource;
import okio.Okio;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/http/BridgeInterceptor;", "Lokhttp3/Interceptor;", "cookieJar", "Lokhttp3/CookieJar;", "(Lokhttp3/CookieJar;)V", "cookieHeader", "", "cookies", "", "Lokhttp3/Cookie;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar) {
        uhy.e((Object) cookieJar, "");
        this.cookieJar = cookieJar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        ResponseBody body;
        uhy.e((Object) chain, "");
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        RequestBody body2 = request.body();
        if (body2 != null) {
            MediaType contentType = body2.getContentType();
            if (contentType != null) {
                newBuilder.header("Content-Type", contentType.getMediaType());
            }
            long contentLength = body2.contentLength();
            if (contentLength != -1) {
                newBuilder.header("Content-Length", String.valueOf(contentLength));
                newBuilder.removeHeader("Transfer-Encoding");
            } else {
                newBuilder.header("Transfer-Encoding", "chunked");
                newBuilder.removeHeader("Content-Length");
            }
        }
        boolean z = false;
        if (request.header("Host") == null) {
            newBuilder.header("Host", Util.toHostHeader$default(request.url(), false, 1, null));
        }
        if (request.header("Connection") == null) {
            newBuilder.header("Connection", "Keep-Alive");
        }
        if (request.header(j2.v) == null && request.header("Range") == null) {
            newBuilder.header(j2.v, Constants.GZIP);
            z = true;
        }
        List<Cookie> loadForRequest = this.cookieJar.loadForRequest(request.url());
        if (!loadForRequest.isEmpty()) {
            newBuilder.header("Cookie", cookieHeader(loadForRequest));
        }
        if (request.header("User-Agent") == null) {
            newBuilder.header("User-Agent", Util.userAgent);
        }
        Response proceed = chain.proceed(!(newBuilder instanceof Request.Builder) ? newBuilder.build() : OkHttp3Instrumentation.build(newBuilder));
        HttpHeaders.receiveHeaders(this.cookieJar, request.url(), proceed.headers());
        Response.Builder request2 = (!(proceed instanceof Response.Builder) ? proceed.newBuilder() : OkHttp3Instrumentation.newBuilder((Response.Builder) proceed)).request(request);
        if (z && ujy.c(Constants.GZIP, Response.header$default(proceed, "Content-Encoding", null, 2, null), true) && HttpHeaders.promisesBody(proceed) && (body = proceed.body()) != null) {
            GzipSource gzipSource = new GzipSource(body.getSource());
            request2.headers(proceed.headers().newBuilder().removeAll("Content-Encoding").removeAll("Content-Length").build());
            RealResponseBody realResponseBody = new RealResponseBody(Response.header$default(proceed, "Content-Type", null, 2, null), -1L, Okio.buffer(gzipSource));
            if (request2 instanceof Response.Builder) {
                OkHttp3Instrumentation.body(request2, realResponseBody);
            } else {
                request2.body(realResponseBody);
            }
        }
        return request2.build();
    }

    private final String cookieHeader(List<Cookie> cookies) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object obj : cookies) {
            if (i < 0) {
                ufe.e();
            }
            Cookie cookie = (Cookie) obj;
            if (i > 0) {
                sb.append("; ");
            }
            sb.append(cookie.name());
            sb.append('=');
            sb.append(cookie.value());
            i++;
        }
        String sb2 = sb.toString();
        uhy.a(sb2, "");
        return sb2;
    }
}

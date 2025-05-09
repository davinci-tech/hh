package com.huawei.phoneservice.faq.base.network;

import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.hwhttp.Interceptor;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.phoneservice.faq.base.util.i;
import defpackage.uhy;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public final class a implements Interceptor {
    private String e = "Xcallback";

    /* renamed from: a, reason: collision with root package name */
    private final Charset f8233a = Charset.forName("UTF-8");
    private EnumC0227a b = EnumC0227a.NONE;

    /* renamed from: com.huawei.phoneservice.faq.base.network.a$a, reason: collision with other inner class name */
    public enum EnumC0227a {
        NONE,
        HEADERS,
        BODY
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        EnumC0227a enumC0227a = this.b;
        uhy.d(chain);
        Request request = chain.request();
        uhy.a(request, "");
        if (enumC0227a == EnumC0227a.NONE) {
            Response proceed = chain.proceed(chain.request());
            uhy.a(proceed, "");
            return proceed;
        }
        d(request);
        long nanoTime = System.nanoTime();
        Response proceed2 = chain.proceed(request);
        uhy.a(proceed2, "");
        return e(request, proceed2, TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
    }

    public final a d(EnumC0227a enumC0227a) {
        uhy.e((Object) enumC0227a, "");
        this.b = enumC0227a;
        return this;
    }

    public final Response c(Response response, byte[] bArr) {
        uhy.e((Object) response, "");
        ResponseBody body = response.getBody();
        ResponseBody build = new ResponseBody.Builder().contentLength(body.getContentLength()).contentType(body.getContentType()).inputStream(new ByteArrayInputStream(bArr)).charSet(this.f8233a).build();
        uhy.a(build, "");
        Response build2 = new Response.Builder().body(build).code(response.getCode()).headers(response.getHeaders()).message(response.getMessage()).build();
        uhy.a(build2, "");
        return build2;
    }

    private final void d(Request request) {
        EnumC0227a enumC0227a = this.b;
        boolean z = enumC0227a == EnumC0227a.BODY || enumC0227a == EnumC0227a.HEADERS;
        RequestBody body = request.getBody();
        StringBuilder sb = new StringBuilder("Request");
        sb.append(" ");
        sb.append(request.getMethod());
        sb.append(" ");
        sb.append(request.getUrl().getUrl());
        uhy.a(sb, "");
        if (z) {
            Headers headers = request.getHeaders();
            uhy.a(headers, "");
            int size = headers.size();
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb2.append(headers.name(i) + ' ' + headers.value(i));
                sb2.append(" ");
            }
            i.e(this.e, sb2.toString(), new Object[0]);
        }
        sb.append(" ");
        sb.append(StringUtils.byte2Str(body != null ? body.body() : null));
        i.e(this.e, sb.toString(), new Object[0]);
    }

    private final Response e(Request request, Response response, long j) {
        Response.Builder newBuilder = response.newBuilder();
        uhy.a(newBuilder, "");
        Response build = newBuilder.build();
        uhy.a(build, "");
        ResponseBody body = build.getBody();
        uhy.a(body, "");
        byte[] bytes = body.bytes();
        EnumC0227a enumC0227a = this.b;
        if (enumC0227a == EnumC0227a.BODY || enumC0227a == EnumC0227a.HEADERS) {
            Headers headers = build.getHeaders();
            uhy.a(headers, "");
            int size = headers.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(headers.name(i) + ':' + headers.value(i));
                sb.append(" ");
            }
            sb.append(build.getUrl());
            i.e(this.e, sb.toString(), new Object[0]);
        }
        StringBuilder sb2 = new StringBuilder("Response");
        sb2.append(" ");
        sb2.append(request.getMethod());
        sb2.append(" ");
        sb2.append(j);
        sb2.append("ms ");
        sb2.append(response.getUrl());
        sb2.append(" response params :");
        sb2.append(StringUtils.byte2Str(bytes));
        uhy.a(sb2, "");
        i.e(this.e, sb2.toString(), new Object[0]);
        return c(response, bytes);
    }
}

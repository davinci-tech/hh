package com.huawei.hms.network.embedded;

import android.os.UserManager;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.Utils;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.f1;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.u0;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class r1 extends Interceptor {
    public static final String b = "CacheInterceptor";
    public static final String c = "com.huawei.secure.android.common.encrypt.aes.AesGcm";
    public static final ResponseBody d = new h1.g(new f1.b().build());
    public static final int e = 307;
    public static final int f = 308;
    public static final int g = 504;
    public static final int h = 0;
    public static final int i = 1;
    public static final int j = 2;
    public static final int k = 3;
    public static final int l = 4;

    /* renamed from: a, reason: collision with root package name */
    public n1 f5447a;

    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (request == null) {
            throw new IOException("cacheInterceptor: request == null");
        }
        n1 n1Var = this.f5447a;
        if (n1Var == null || n1Var.isInvalid() || !a(c)) {
            return chain.proceed(request);
        }
        if (!d(request.getMethod())) {
            return chain.proceed(request);
        }
        p1 parse = p1.parse(Headers.of(request.getHeaders()));
        if (!a()) {
            Logger.i(b, "The device is locked");
            if (!parse.onlyIfCached()) {
                return chain.proceed(request);
            }
            Logger.w(b, "Device first started and unlocked, only-If-Cached:gateway timeout, 504");
            return new u0.b().code(504).message("Unsatisfiable Request (only-if-cached)").body(d).url(request.getUrl()).build();
        }
        if (parse.noStore()) {
            Logger.i(b, "request with header:no-store");
            return chain.proceed(request);
        }
        if (parse.noCache() || a(request)) {
            long currentTime = Utils.getCurrentTime(false);
            Response<ResponseBody> proceed = chain.proceed(request);
            Logger.i(b, "request with header:no-cache or if-modified-since/if-none-match");
            return a(new c(currentTime, Utils.getCurrentTime(false), request, proceed));
        }
        if (parse.onlyIfCached()) {
            c cVar = this.f5447a.get(request);
            if (cVar == null || cVar.d() == null || !a(cVar.d().getBody())) {
                Logger.i(b, "only-If-Cached:gateway timeout, 504");
                return new u0.b().url(request.getUrl()).code(504).message("Unsatisfiable Request (only-if-cached)").body(d).build();
            }
            Logger.i(b, "only-If-Cached: available response");
            return cVar.d();
        }
        c cVar2 = this.f5447a.get(request);
        if (cVar2 == null || cVar2.d() == null) {
            long currentTime2 = Utils.getCurrentTime(false);
            Response<ResponseBody> proceed2 = chain.proceed(request);
            Logger.i(b, "Cache response is null, send the request to server");
            return a(new c(currentTime2, Utils.getCurrentTime(false), request, proceed2));
        }
        b bVar = new b(cVar2);
        int c2 = bVar.c();
        Logger.i(b, "cacheStrategy cacheStrategy code is: " + c2);
        u0.b errorBody = new u0.b().headers(cVar2.d().getHeaders()).url(cVar2.d().getUrl()).message(cVar2.d().getMessage()).code(cVar2.d().getCode()).body(cVar2.d().getBody()).errorBody(cVar2.d().getErrorBody());
        if (c2 == 0) {
            long currentTime3 = Utils.getCurrentTime(false);
            Response<ResponseBody> proceed3 = chain.proceed(request);
            IoUtils.closeSecure(cVar2.d());
            Logger.i(b, "Cache response is expried， and without Etag");
            return a(new c(currentTime3, Utils.getCurrentTime(false), request, proceed3));
        }
        if (c2 == 1 && a(cVar2.d().getBody())) {
            Logger.i(b, "Cached response is not expried");
            return errorBody.build();
        }
        if (c2 == 2 && a(cVar2.d().getBody())) {
            errorBody.headers(Headers.of("Warning", "110 HttpURLConnection \"Response is stale\"").toMultimap());
            Logger.i(b, "Warning: 110 HttpURLConnection \"Response is stale\"");
            return errorBody.build();
        }
        if (c2 == 3 && a(cVar2.d().getBody())) {
            errorBody.url(request.getUrl()).headers(Headers.of("Warning", "113 HttpURLConnection \"Heuristic expiration\"").toMultimap());
            Logger.i(b, "Warning: 113 HttpURLConnection \"Heuristic expiration\"");
            return errorBody.build();
        }
        if (c2 != 4) {
            return chain.proceed(request);
        }
        long currentTime4 = Utils.getCurrentTime(false);
        Response<ResponseBody> proceed4 = chain.proceed(bVar.d());
        int code = proceed4.getCode();
        Response<ResponseBody> d2 = cVar2.d();
        if (code != 304) {
            IoUtils.closeSecure(d2);
            return a(new c(currentTime4, Utils.getCurrentTime(false), request, proceed4));
        }
        u0 build = errorBody.headers(a(Headers.of(d2.getHeaders()), Headers.of(proceed4.getHeaders())).toMultimap()).build();
        Logger.i(b, "http not modified: 304, update the cached response");
        IoUtils.closeSecure(proceed4.getBody());
        this.f5447a.update(new c(currentTime4, Utils.getCurrentTime(false), request, build));
        return build;
    }

    public static boolean d(String str) {
        return str.equals("GET");
    }

    public static boolean c(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static class b {
        public static final String n = "If-None-Match";
        public static final String o = "If-Modified-Since";

        /* renamed from: a, reason: collision with root package name */
        public final long f5448a;
        public final c b;
        public Request c;
        public Date d;
        public String e;
        public Date f;
        public String g;
        public Date h;
        public long i;
        public long j;
        public p1 k;
        public String l;
        public int m;

        private boolean e() {
            return this.b.e().maxAgeSeconds() == -1 && this.h == null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Request d() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int c() {
            String str;
            long a2 = a();
            long b = b();
            if (this.k.maxAgeSeconds() != -1) {
                b = Math.min(b, TimeUnit.SECONDS.toMillis(this.k.maxAgeSeconds()));
            }
            long j = 0;
            long millis = this.k.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis(this.k.minFreshSeconds()) : 0L;
            if (!this.k.mustRevalidate() && this.k.maxStaleSeconds() != -1) {
                j = TimeUnit.SECONDS.toMillis(this.k.maxStaleSeconds());
            }
            if (!this.b.e().noCache()) {
                long j2 = millis + a2;
                if (j2 < j + b) {
                    if (j2 >= b) {
                        return 2;
                    }
                    return (a2 <= 86400000 || !e()) ? 1 : 3;
                }
            }
            String str2 = this.l;
            if (str2 != null) {
                str = n;
            } else {
                Date date = this.f;
                str = o;
                if (date != null) {
                    str2 = this.g;
                } else {
                    if (this.d == null) {
                        return 0;
                    }
                    str2 = this.e;
                }
            }
            Headers build = Headers.of(this.b.c().getHeaders()).newBuilder().add(str, str2).build();
            Request.Builder newBuilder = this.b.c().newBuilder();
            for (int i = 0; i < build.size(); i++) {
                newBuilder.addHeader(build.name(i), build.value(i));
            }
            this.c = newBuilder.build();
            return 4;
        }

        private long b() {
            if (this.b.e().maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.maxAgeSeconds());
            }
            if (this.h != null) {
                Date date = this.d;
                long time = this.h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f == null) {
                return 0L;
            }
            Date date2 = this.d;
            long time2 = (date2 != null ? date2.getTime() : this.i) - this.f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        private long a() {
            Date date = this.d;
            long max = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i = this.m;
            if (i != -1) {
                max = Math.max(max, TimeUnit.SECONDS.toMillis(i));
            }
            return max + (this.f5448a - this.i);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public b(c cVar) {
            char c;
            this.m = -1;
            this.f5448a = System.currentTimeMillis();
            this.b = cVar;
            if (cVar != null) {
                this.k = cVar.b();
                this.i = cVar.f();
                this.j = cVar.a();
                Headers of = Headers.of(cVar.d().getHeaders());
                int size = of.size();
                for (int i = 0; i < size; i++) {
                    String lowerCase = of.name(i).toLowerCase(Locale.ROOT);
                    String value = of.value(i);
                    lowerCase.hashCode();
                    switch (lowerCase.hashCode()) {
                        case -1309235404:
                            if (lowerCase.equals("expires")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 96511:
                            if (lowerCase.equals("age")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 3076014:
                            if (lowerCase.equals("date")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 3123477:
                            if (lowerCase.equals("etag")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 150043680:
                            if (lowerCase.equals("last-modified")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c == 0) {
                        this.h = u1.parse(value);
                    } else if (c == 1) {
                        this.m = u1.parseSeconds(value, -1);
                    } else if (c == 2) {
                        this.d = u1.parse(value);
                        this.e = value;
                    } else if (c == 3) {
                        this.l = value;
                    } else if (c == 4) {
                        this.f = u1.parse(value);
                        this.g = value;
                    }
                }
            }
        }
    }

    public static boolean b(String str) {
        return "Content-Length".equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0062, code lost:
    
        if (r3.e().isPrivate() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(com.huawei.hms.network.embedded.r1.c r3) {
        /*
            com.huawei.hms.network.httpclient.Response r0 = r3.d()
            int r0 = r0.getCode()
            r1 = 200(0xc8, float:2.8E-43)
            r2 = 0
            if (r0 == r1) goto L66
            r1 = 410(0x19a, float:5.75E-43)
            if (r0 == r1) goto L66
            r1 = 414(0x19e, float:5.8E-43)
            if (r0 == r1) goto L66
            r1 = 501(0x1f5, float:7.02E-43)
            if (r0 == r1) goto L66
            r1 = 203(0xcb, float:2.84E-43)
            if (r0 == r1) goto L66
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 == r1) goto L66
            r1 = 307(0x133, float:4.3E-43)
            if (r0 == r1) goto L35
            r1 = 308(0x134, float:4.32E-43)
            if (r0 == r1) goto L66
            r1 = 404(0x194, float:5.66E-43)
            if (r0 == r1) goto L66
            r1 = 405(0x195, float:5.68E-43)
            if (r0 == r1) goto L66
            switch(r0) {
                case 300: goto L66;
                case 301: goto L66;
                case 302: goto L35;
                default: goto L34;
            }
        L34:
            goto L65
        L35:
            com.huawei.hms.network.httpclient.Response r0 = r3.d()
            java.util.Map r0 = r0.getHeaders()
            java.lang.String r1 = "Expires"
            java.lang.Object r0 = r0.get(r1)
            if (r0 != 0) goto L66
            com.huawei.hms.network.embedded.p1 r0 = r3.e()
            int r0 = r0.maxAgeSeconds()
            r1 = -1
            if (r0 != r1) goto L66
            com.huawei.hms.network.embedded.p1 r0 = r3.e()
            boolean r0 = r0.isPublic()
            if (r0 != 0) goto L66
            com.huawei.hms.network.embedded.p1 r0 = r3.e()
            boolean r0 = r0.isPrivate()
            if (r0 == 0) goto L65
            goto L66
        L65:
            return r2
        L66:
            com.huawei.hms.network.embedded.p1 r0 = r3.e()
            boolean r0 = r0.noStore()
            if (r0 != 0) goto L7b
            com.huawei.hms.network.embedded.p1 r3 = r3.b()
            boolean r3 = r3.noStore()
            if (r3 != 0) goto L7b
            r2 = 1
        L7b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.r1.b(com.huawei.hms.network.embedded.r1$c):boolean");
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        public final long f5449a;
        public final long b;
        public final Response<ResponseBody> c;
        public final Request d;

        public long f() {
            return this.f5449a;
        }

        public p1 e() {
            return p1.parse(Headers.of(this.c.getHeaders()));
        }

        public Response<ResponseBody> d() {
            return this.c;
        }

        public Request c() {
            return this.d;
        }

        public p1 b() {
            return p1.parse(Headers.of(this.d.getHeaders()));
        }

        public long a() {
            return this.b;
        }

        public c(long j, long j2, Request request, Response<ResponseBody> response) {
            CheckParamUtils.checkNotNull(response, "response == null");
            CheckParamUtils.checkNotNull(request, "request == null");
            this.f5449a = j;
            this.b = j2;
            this.c = response;
            this.d = request;
        }
    }

    private boolean a(String str) {
        StringBuilder sb;
        String simpleName;
        try {
            ClassLoader classLoader = r1.class.getClassLoader();
            if (classLoader == null) {
                throw new ClassNotFoundException("not found classloader");
            }
            classLoader.loadClass(str);
            return true;
        } catch (ClassNotFoundException unused) {
            sb = new StringBuilder();
            sb.append(str);
            simpleName = " ClassNotFoundException";
            sb.append(simpleName);
            Logger.w(b, sb.toString());
            return false;
        } catch (Exception e2) {
            sb = new StringBuilder();
            sb.append(str);
            sb.append(" exception: ");
            simpleName = e2.getClass().getSimpleName();
            sb.append(simpleName);
            Logger.w(b, sb.toString());
            return false;
        }
    }

    private boolean a(ResponseBody responseBody) {
        String str;
        if (responseBody == null) {
            str = "cacheResponseBody is null";
        } else {
            InputStream inputStream = responseBody.getInputStream();
            if (inputStream != null) {
                return ((t1) inputStream).isDecryptable();
            }
            str = "inputStream is null";
        }
        Logger.i(b, str);
        return false;
    }

    public static boolean a(Request request) {
        return (request.getHeaders().get(b.o) == null && request.getHeaders().get(b.n) == null) ? false : true;
    }

    private boolean a() {
        UserManager userManager = (UserManager) ContextHolder.getAppContext().getSystemService("user");
        if (userManager != null) {
            return userManager.isUserUnlocked();
        }
        return true;
    }

    private Response<ResponseBody> a(c cVar) {
        Response<ResponseBody> d2 = cVar.d();
        if (u0.hasBody(d2) && b(cVar)) {
            o1 put = this.f5447a.put(cVar);
            String str = Headers.of(d2.getHeaders()).get("Content-Length");
            if (put != null && u1.stringToLong(str, -1L) <= ua.H) {
                q1 q1Var = new q1(d2.isSuccessful() ? d2.getBody() : d2.getErrorBody(), put);
                ResponseBody body = d2.getBody();
                f1 build = new f1.b().contentLength(body.getContentLength()).contentType(body.getContentType()).charSet(body.charSet).inputStream(q1Var).build();
                Logger.i(b, "The response has been cached to the file");
                return new u0.b().url(d2.getUrl()).code(d2.getCode()).headers(d2.getHeaders()).message(d2.getMessage()).body(new h1.g(build)).build();
            }
        }
        Logger.i(b, "The response isn't cached to the file");
        return d2;
    }

    public static Headers a(Headers headers, Headers headers2) {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i2 = 0; i2 < size; i2++) {
            String name = headers.name(i2);
            String value = headers.value(i2);
            if ((!"Warning".equalsIgnoreCase(name) || !value.startsWith("1")) && (b(name) || !c(name) || headers2.get(name) == null)) {
                builder.add(name, value);
            }
        }
        int size2 = headers2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            String name2 = headers2.name(i3);
            if (!b(name2) && c(name2)) {
                builder.add(name2, headers2.value(i3));
            }
        }
        return builder.build();
    }

    public r1(n1 n1Var) {
        if (n1Var != null) {
            this.f5447a = n1Var;
        }
    }
}

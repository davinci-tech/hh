package com.tencent.open.a;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation;
import com.huawei.operation.OpAnalyticsConstants;
import com.tencent.open.log.SLog;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Version;

/* loaded from: classes7.dex */
class e implements com.tencent.open.a.a {

    /* renamed from: a, reason: collision with root package name */
    private OkHttpClient f11329a;

    private void a(OkHttpClient.Builder builder) {
    }

    public e(String str) throws NoClassDefFoundError {
        a(str);
    }

    private void a(String str) {
        String userAgent = Version.userAgent();
        if (userAgent == null || !userAgent.startsWith("okhttp/3")) {
            throw new NoClassDefFoundError();
        }
        OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS)).connectTimeout(15000L, TimeUnit.MILLISECONDS).readTimeout(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS).writeTimeout(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS).cache(null).addInterceptor(new a(str));
        a(addInterceptor);
        this.f11329a = addInterceptor.build();
    }

    @Override // com.tencent.open.a.a
    public void a(long j, long j2) {
        if (j <= 0 || j2 <= 0) {
            return;
        }
        if (this.f11329a.connectTimeoutMillis() == j && this.f11329a.readTimeoutMillis() == j2) {
            return;
        }
        SLog.i("OkHttpServiceImpl", "setTimeout changed.");
        this.f11329a = this.f11329a.newBuilder().connectTimeout(j, TimeUnit.MILLISECONDS).readTimeout(j2, TimeUnit.MILLISECONDS).writeTimeout(j2, TimeUnit.MILLISECONDS).build();
    }

    @Override // com.tencent.open.a.a
    public g a(String str, String str2) throws IOException {
        SLog.i("OkHttpServiceImpl", "get.");
        if (!TextUtils.isEmpty(str2)) {
            int indexOf = str2.indexOf("?");
            if (indexOf == -1) {
                str = str + "?";
            } else if (indexOf != str.length() - 1) {
                str = str + "&";
            }
            str = str + str2;
        }
        Request.Builder builder = new Request.Builder().url(str).get();
        Request build = !(builder instanceof Request.Builder) ? builder.build() : OkHttp3Instrumentation.build(builder);
        OkHttpClient okHttpClient = this.f11329a;
        return new d((!(okHttpClient instanceof OkHttpClient) ? okHttpClient.newCall(build) : OkHttp3Instrumentation.newCall(okHttpClient, build)).execute(), str2.length());
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map) throws IOException {
        SLog.i("OkHttpServiceImpl", "post data");
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                if (str3 != null) {
                    builder.add(str2, str3);
                }
            }
        }
        FormBody build = builder.build();
        Request.Builder post = new Request.Builder().url(str).post(build);
        Request build2 = !(post instanceof Request.Builder) ? post.build() : OkHttp3Instrumentation.build(post);
        OkHttpClient okHttpClient = this.f11329a;
        return new d((!(okHttpClient instanceof OkHttpClient) ? okHttpClient.newCall(build2) : OkHttp3Instrumentation.newCall(okHttpClient, build2)).execute(), (int) build.contentLength());
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map, Map<String, byte[]> map2) throws IOException {
        if (map2 == null || map2.size() == 0) {
            return a(str, map);
        }
        SLog.i("OkHttpServiceImpl", "post data, has byte data");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                if (str3 != null) {
                    builder.addFormDataPart(str2, str3);
                }
            }
        }
        for (String str4 : map2.keySet()) {
            byte[] bArr = map2.get(str4);
            if (bArr != null && bArr.length > 0) {
                builder.addFormDataPart(str4, str4, RequestBody.create(MediaType.get("content/unknown"), bArr));
                SLog.w("OkHttpServiceImpl", "post byte data.");
            }
        }
        MultipartBody build = builder.build();
        Request.Builder post = new Request.Builder().url(str).post(build);
        Request build2 = !(post instanceof Request.Builder) ? post.build() : OkHttp3Instrumentation.build(post);
        OkHttpClient okHttpClient = this.f11329a;
        return new d((!(okHttpClient instanceof OkHttpClient) ? okHttpClient.newCall(build2) : OkHttp3Instrumentation.newCall(okHttpClient, build2)).execute(), (int) build.contentLength());
    }

    static class a implements Interceptor {

        /* renamed from: a, reason: collision with root package name */
        private final String f11330a;

        public a(String str) {
            this.f11330a = str;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request.Builder header = chain.request().newBuilder().header("User-Agent", this.f11330a);
            return chain.proceed(!(header instanceof Request.Builder) ? header.build() : OkHttp3Instrumentation.build(header));
        }
    }
}

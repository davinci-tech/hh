package com.huawei.phoneservice.faq.base.network;

import android.content.Context;
import com.google.gson.Gson;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.hms.framework.network.restclient.hwhttp.trans.MultipartBody;
import com.huawei.hms.framework.network.util.HttpUtils;
import com.huawei.phoneservice.faq.base.network.a;
import com.huawei.phoneservice.faq.base.util.j;
import defpackage.uhy;
import defpackage.uib;
import java.io.File;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.JvmStatic;

/* loaded from: classes5.dex */
public class FaqRestClient {
    public static final Companion Companion = new Companion(null);
    private static volatile FaqRestClient instance;
    private HttpClient restClient;
    private final Gson gson = new Gson();
    private final Map<String, List<Submit>> submitMap = new ConcurrentHashMap();
    private final int CONNECT_TIME_OUT = 8000;
    private final int UPLOAD_ZIP_TIME_OUT = 30000;
    private final int UPLOAD_ZIP_RETRY_TIME = 3;
    private final String HEAD_CHANNEL_KEY = "channelId";
    private final String HEAD_VERSION_KEY = "sdkVersion";
    private final String HEAD_SWG_KEY = "SGW-APP-ID";
    private final String SGW_APP_ID = "26C7C8265E4924A6757A52FD571E03F4";

    public Submit uploadZipFileToService(Context context, String str, Map<String, String> map, String str2, File file, String str3) {
        uhy.e((Object) str, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) file, "");
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            return null;
        }
        Request build = new Request.Builder().method(str3).headers(Headers.of(map).newBuilder()).url(str).connectTimeout(this.UPLOAD_ZIP_TIME_OUT).readTimeout(this.UPLOAD_ZIP_TIME_OUT).writeTimeout(this.UPLOAD_ZIP_TIME_OUT).retryTimeOnConnectionFailure(this.UPLOAD_ZIP_RETRY_TIME).requestBody(RequestBody.create(MediaType.parse(str2), file)).build();
        HttpClient httpClient = this.restClient;
        uhy.d(httpClient);
        Submit newSubmit = httpClient.newSubmit(build);
        uhy.a(newSubmit, "");
        putSubmit(context, newSubmit);
        return newSubmit;
    }

    public Submit uploadZipFile(Context context, String str, Map<String, String> map, String str2, File file, String str3) {
        uhy.e((Object) str, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str3, "");
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            return null;
        }
        Request build = new Request.Builder().method("POST").headers(Headers.of(map).newBuilder()).url(str).requestBody(RequestBody.create(str3)).build();
        HttpClient httpClient = this.restClient;
        uhy.d(httpClient);
        Submit newSubmit = httpClient.newSubmit(build);
        uhy.a(newSubmit, "");
        putSubmit(context, newSubmit);
        return newSubmit;
    }

    public Submit uploadAttachs(Context context, String str, Headers headers, String str2, File file, Callback callback) {
        uhy.e((Object) str, "");
        uhy.e((Object) headers, "");
        uhy.e((Object) file, "");
        uhy.e((Object) callback, "");
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            return null;
        }
        Request.Builder url = new Request.Builder().method("POST").headers(headers.newBuilder()).url(str);
        url.requestBody(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse(str2), file)).build()).build();
        HttpClient httpClient = this.restClient;
        uhy.d(httpClient);
        Submit newSubmit = httpClient.newSubmit(url.build());
        uhy.a(newSubmit, "");
        newSubmit.enqueue(callback);
        putSubmit(context, newSubmit);
        return newSubmit;
    }

    public final void setRestClient(HttpClient httpClient) {
        this.restClient = httpClient;
    }

    public final HttpClient getRestClient() {
        return this.restClient;
    }

    public final Gson getGson() {
        return this.gson;
    }

    public final Submit downloadFile(Context context, String str, String str2, Callback callback) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) callback, "");
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            return null;
        }
        Request build = new Request.Builder().url(str).addHeader("accessToken", str2).build();
        HttpClient httpClient = this.restClient;
        uhy.d(httpClient);
        Submit newSubmit = httpClient.newSubmit(build);
        newSubmit.enqueue(callback);
        uhy.a(newSubmit, "");
        putSubmit(context, newSubmit);
        return newSubmit;
    }

    public void canceledSubmit(Context context) {
        String name = context != null ? context.getClass().getName() : null;
        if (name == null) {
            return;
        }
        List<Submit> list = this.submitMap.get(name);
        if (com.huawei.phoneservice.faq.base.util.b.b(list)) {
            return;
        }
        uhy.d(list);
        for (Submit submit : list) {
            if (!submit.isCanceled()) {
                submit.cancel();
            }
        }
    }

    public Submit asyncRequestWithJson(Context context, String str, String str2, Headers headers, Callback callback) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) headers, "");
        uhy.e((Object) callback, "");
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            return null;
        }
        Request build = new Request.Builder().headers(headers.newBuilder()).method("POST").requestBody(RequestBody.create(MediaType.parse("application/json"), str2)).url(str).build();
        HttpClient httpClient = this.restClient;
        uhy.d(httpClient);
        Submit newSubmit = httpClient.newSubmit(build);
        newSubmit.enqueue(callback);
        return newSubmit;
    }

    public Submit asyncRequestWitHead(Context context, String str, Headers headers, String str2, Callback callback) {
        uhy.e((Object) str, "");
        uhy.e((Object) headers, "");
        uhy.e((Object) callback, "");
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            return null;
        }
        Request build = new Request.Builder().method("POST").headers(headers.newBuilder()).requestBody(RequestBody.create(str2)).url(str).callTimeout(this.CONNECT_TIME_OUT).build();
        HttpClient httpClient = this.restClient;
        uhy.d(httpClient);
        Submit newSubmit = httpClient.newSubmit(build);
        newSubmit.enqueue(callback);
        uhy.a(newSubmit, "");
        putSubmit(context, newSubmit);
        return newSubmit;
    }

    public Submit asyncRequest(Context context, String str, String str2, Callback callback) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) callback, "");
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            return null;
        }
        Request build = new Request.Builder().headers(new Headers.Builder().add(this.HEAD_CHANNEL_KEY, j.c().getSdk("channel")).add(this.HEAD_VERSION_KEY, j.c().getSdkVersion()).add(this.HEAD_SWG_KEY, this.SGW_APP_ID).build().newBuilder()).method("POST").requestBody(RequestBody.create(str2)).url(str).build();
        HttpClient httpClient = this.restClient;
        uhy.d(httpClient);
        Submit newSubmit = httpClient.newSubmit(build);
        newSubmit.enqueue(callback);
        return newSubmit;
    }

    static final class c implements X509TrustManager {
        public String toString() {
            return "TrustAllManager";
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            uhy.e((Object) x509CertificateArr, "");
            uhy.e((Object) str, "");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            uhy.e((Object) x509CertificateArr, "");
            uhy.e((Object) str, "");
        }
    }

    private final void putSubmit(Context context, Submit submit) {
        String name = context != null ? context.getClass().getName() : null;
        if (name == null) {
            return;
        }
        List<Submit> list = this.submitMap.get(name);
        if (list == null) {
            list = new ArrayList<>();
            this.submitMap.put(name, list);
        }
        list.add(submit);
    }

    @JvmStatic
    public static final FaqRestClient initRestClientAnno(Context context) {
        return Companion.initRestClientAnno(context);
    }

    public static final class Companion {
        @JvmStatic
        public final FaqRestClient initRestClientAnno(Context context) {
            if (FaqRestClient.instance == null) {
                FaqRestClient.instance = new FaqRestClient(context);
            }
            return FaqRestClient.instance;
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }

        private Companion() {
        }
    }

    public FaqRestClient(Context context) {
        new c();
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
            sSLContext.init(null, new TrustManager[]{new c()}, new SecureRandom());
            sSLContext.getSocketFactory();
        } catch (Throwable unused) {
        }
        HttpClientGlobalInstance.getInstance().init(context);
        this.restClient = new HttpClient.Builder().callTimeout(151000).readTimeout(151000).retryTimeOnConnectionFailure(2).addInterceptor(new a().d(a.EnumC0227a.BODY)).isReportable(true).build();
    }
}

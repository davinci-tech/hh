package com.huawei.openalliance.ad.download;

import android.content.Context;
import com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.net.http.HttpCallerFactory;
import com.huawei.openalliance.ad.net.http.HttpsConfig;
import com.huawei.openalliance.ad.utils.ac;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes5.dex */
public class j extends c {

    /* renamed from: a, reason: collision with root package name */
    private static OkHttpClient f6812a;
    private static OkHttpClient b;
    private static com.huawei.openalliance.ad.net.http.i c;
    private static final byte[] d = new byte[0];
    private Response e;
    private Request f;
    private ResponseBody g;
    private Context h;

    @Override // com.huawei.openalliance.ad.download.c
    public HttpConnection d() {
        com.huawei.openalliance.ad.net.http.i iVar = c;
        return iVar != null ? iVar.a(this.f) : new HttpConnection();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Response response = this.e;
        if (response == null) {
            throw new IOException("close stream error");
        }
        response.close();
    }

    @Override // com.huawei.openalliance.ad.download.c
    public int c() {
        ResponseBody responseBody = this.g;
        if (responseBody == null) {
            return -1;
        }
        return (int) responseBody.getContentLength();
    }

    @Override // com.huawei.openalliance.ad.download.c
    public int b() {
        Response response = this.e;
        if (response != null) {
            return response.code();
        }
        throw new IOException("get response code error");
    }

    @Override // com.huawei.openalliance.ad.download.c
    public String a(String str) {
        Response response = this.e;
        return response == null ? "" : response.header(str);
    }

    @Override // com.huawei.openalliance.ad.download.c
    public InputStream a() {
        ResponseBody responseBody = this.g;
        if (responseBody != null) {
            return responseBody.byteStream();
        }
        throw new IOException("get input stream error");
    }

    private boolean a(Request request, boolean z) {
        Call newCall;
        OkHttpClient a2 = a(this.h, z);
        try {
            if (a2 instanceof OkHttpClient) {
                OkHttpClient okHttpClient = a2;
                newCall = OkHttp3Instrumentation.newCall(a2, request);
            } else {
                newCall = a2.newCall(request);
            }
            Response execute = newCall.execute();
            this.e = execute;
            r1 = 8 == ac.a(execute.code());
            this.g = this.e.body();
        } catch (IOException e) {
            ho.c("OkHttpNetworkConnection", "http execute encounter IOException:" + e.getClass().getSimpleName());
            if (ac.a(e)) {
                return true;
            }
        }
        return r1;
    }

    private static OkHttpClient a(Context context, boolean z) {
        OkHttpClient okHttpClient;
        synchronized (d) {
            if (f6812a == null || b == null || c == null) {
                OkHttpClient.Builder protocols = new OkHttpClient.Builder().connectionPool(new ConnectionPool(8, 10L, TimeUnit.MINUTES)).readTimeout(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS).connectTimeout(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS).protocols(Collections.unmodifiableList(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1)));
                com.huawei.openalliance.ad.net.http.i a2 = HttpCallerFactory.a();
                c = a2;
                a2.a(protocols);
                HttpsConfig.a(protocols, false, false);
                try {
                    protocols.dispatcher(protocols.createDispatcher(Protocol.HTTP_2));
                } catch (Throwable th) {
                    ho.c("OkHttpNetworkConnection", "create dispatcher error, " + th.getClass().getSimpleName());
                }
                f6812a = protocols.build();
                b = protocols.dns(new com.huawei.openalliance.ad.net.http.l(context, true)).build();
            }
            okHttpClient = z ? b : f6812a;
        }
        return okHttpClient;
    }

    public j(Context context, String str, long j) {
        this.h = context;
        Request.Builder url = new Request.Builder().url(str);
        if (j > 0) {
            url.header("Range", "bytes=" + j + Constants.LINK);
        }
        url.header(j2.v, "identity");
        url.cacheControl(CacheControl.FORCE_NETWORK);
        Request build = !(url instanceof Request.Builder) ? url.build() : OkHttp3Instrumentation.build(url);
        this.f = build;
        if (a(build, false)) {
            a(build, true);
        }
    }
}

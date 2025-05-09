package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.embedded.c1;
import com.huawei.hms.network.embedded.d1;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.t2;
import com.huawei.hms.network.embedded.z2;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.httpclient.config.NetworkConfig;
import com.huawei.hms.network.httpclient.excutor.PolicyExecutor;
import com.huawei.hms.network.httpclient.internal.IHttpClientBuilder;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.httpclient.websocket.WebSocketListener;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class a1 extends HttpClient {
    public static final String p = "RealHttpClient";
    public static final int q = 101;
    public static volatile X509TrustManager r;
    public static volatile HostnameVerifier s;

    /* renamed from: a, reason: collision with root package name */
    public final List<Interceptor> f5149a;
    public final List<Interceptor> b;
    public d1.a c;
    public volatile d1.a d;
    public volatile X509TrustManager e;
    public volatile SSLSocketFactory f;
    public volatile HostnameVerifier g;
    public z2.c h;
    public boolean i;
    public o2 j;
    public Proxy k;
    public ProxySelector l;
    public n1 m;
    public boolean n;
    public final PolicyExecutor o;

    @Override // com.huawei.hms.network.httpclient.HttpClient
    public WebSocket newWebSocket(Request request, WebSocketListener webSocketListener) {
        d3 d3Var = new d3(request.getUrl());
        String a2 = a(d3Var.getHost());
        h1.d a3 = a(request, this.o.getRequestPramas(true, request, a2, d3Var.getHost()), a2);
        j4 j4Var = new j4(a3, new h1.j(webSocketListener));
        a(a3, new h1.i(j4Var));
        return j4Var;
    }

    @Override // com.huawei.hms.network.httpclient.HttpClient, com.huawei.hms.network.httpclient.Submit.Factory
    public Submit<ResponseBody> newSubmit(Request request) {
        CheckParamUtils.checkNotNull(request, "request == null");
        d3 d3Var = new d3(request.getUrl());
        String a2 = a(d3Var.getHost());
        return new h1.h(new v0(this, a(request, this.o.getRequestPramas(false, request, a2, d3Var.getHost()), a2), null));
    }

    public static final class b extends IHttpClientBuilder {

        /* renamed from: a, reason: collision with root package name */
        public final List<Interceptor> f5150a;
        public final List<Interceptor> b;
        public X509TrustManager c;
        public SSLSocketFactory d;
        public HostnameVerifier e;
        public boolean f;
        public boolean g;
        public Proxy h;
        public ProxySelector i;
        public n1 j;
        public final PolicyExecutor k;

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b writeTimeout(int i) {
            this.k.setValue("core_write_timeout", Integer.valueOf(i));
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            CheckParamUtils.checkNotNull(sSLSocketFactory, "sslSocketFactory == null");
            CheckParamUtils.checkNotNull(x509TrustManager, "trustManager == null");
            this.d = sSLSocketFactory;
            this.c = x509TrustManager;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b retryTimeOnConnectionFailure(int i) {
            this.k.setValue("core_retry_time", Integer.valueOf(i));
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b readTimeout(int i) {
            this.k.setValue("core_read_timeout", Integer.valueOf(i));
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b proxySelector(ProxySelector proxySelector) {
            if (proxySelector == null) {
                Logger.w(a1.p, "proxySelector == null");
                return this;
            }
            this.i = proxySelector;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b proxy(Proxy proxy) {
            this.h = proxy;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b pingInterval(int i) {
            this.k.setValue("core_ping_interval", Integer.valueOf(i));
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b options(String str) {
            if (str == null) {
                Logger.w(a1.p, "RealHttpclient options == null");
                return this;
            }
            this.k.setOptions(str);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b hostnameVerifier(HostnameVerifier hostnameVerifier) {
            CheckParamUtils.checkNotNull(hostnameVerifier, "hostnameVerifier == null");
            this.e = hostnameVerifier;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b enableQuic(boolean z) {
            this.f = z;
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b connectTimeout(int i) {
            this.k.setValue("core_connect_timeout", Integer.valueOf(i));
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b callTimeout(int i) {
            this.k.setValue("core_call_timeout", Integer.valueOf(i));
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b cache(String str, long j) {
            this.j = new n1(str, j);
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public HttpClient build() {
            return new a1(this);
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b addNetworkInterceptor(Interceptor interceptor) {
            if (interceptor == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.b.add(new h1.c(interceptor));
            return this;
        }

        @Override // com.huawei.hms.network.httpclient.internal.IHttpClientBuilder
        public b addInterceptor(Interceptor interceptor) {
            if (interceptor == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f5150a.add(new h1.c(interceptor));
            return this;
        }

        public b(a1 a1Var) {
            ArrayList arrayList = new ArrayList();
            this.f5150a = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.b = arrayList2;
            this.g = true;
            PolicyExecutor policyExecutor = new PolicyExecutor();
            this.k = policyExecutor;
            arrayList.addAll(a1Var.f5149a);
            arrayList2.addAll(a1Var.b);
            this.c = a1Var.e;
            this.d = a1Var.f;
            this.e = a1Var.g;
            this.f = a1Var.i;
            this.j = a1Var.m;
            this.g = a1Var.n;
            this.h = a1Var.k;
            this.i = a1Var.l;
            policyExecutor.setOptions(a1Var.o.getProcessPolicyNetworkServiceParams());
        }

        public b() {
            this.f5150a = new ArrayList();
            this.b = new ArrayList();
            this.g = true;
            this.k = new PolicyExecutor();
        }
    }

    @Override // com.huawei.hms.network.httpclient.HttpClient
    public Request.Builder newRequest() {
        return new c1.b();
    }

    @Override // com.huawei.hms.network.httpclient.HttpClient
    public IHttpClientBuilder newBuilder() {
        return new b(this);
    }

    public boolean isHttpClientEnableQuic() {
        return this.i;
    }

    public X509TrustManager getTrustManager() {
        return this.e;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.f;
    }

    public ProxySelector getProxySelector() {
        return this.l;
    }

    public Proxy getProxy() {
        return this.k;
    }

    public PolicyExecutor getPolicyExecutor() {
        return this.o;
    }

    public String getNi() {
        return this.o.getValue("", PolicyNetworkService.ClientConstants.NI_NAME);
    }

    public List<Interceptor> getNetworkInterceptors() {
        return Collections.unmodifiableList(this.b);
    }

    public List<Interceptor> getInterceptors() {
        return Collections.unmodifiableList(this.f5149a);
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.g;
    }

    public d1.a getFactory(Request request) {
        if (b(request)) {
            if (this.d != null) {
                return this.d;
            }
            this.d = a();
            if (this.d.isAvailable()) {
                return this.d;
            }
        }
        return this.c;
    }

    public z2.c getEventListenerFactory() {
        return this.h;
    }

    public o2 getDns() {
        return this.j;
    }

    public n1 getCache() {
        return this.m;
    }

    public boolean disableWeakNetworkRetry() {
        return this.o.getBoolean("", "core_disable_weaknetwork_retry");
    }

    public void a(NetworkConfig networkConfig) {
        String str = networkConfig.get("core_connect_timeout");
        String str2 = networkConfig.get("core_concurrent_connect_delay");
        String userConfigValue = this.o.getUserConfigValue("core_connect_timeout");
        String userConfigValue2 = this.o.getUserConfigValue("core_concurrent_connect_delay");
        Logger.v(p, "request: ConnectTimeout:" + str + ", ConcurrentConnectDelay:" + str2 + "httpclient: ConnectTimeout:" + userConfigValue + ", ConcurrentConnectDelay:" + userConfigValue2);
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(userConfigValue) && TextUtils.isEmpty(userConfigValue2)) {
            return;
        }
        if ((TextUtils.isEmpty(str) && TextUtils.isEmpty(userConfigValue)) || (TextUtils.isEmpty(str2) && TextUtils.isEmpty(userConfigValue2))) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(userConfigValue)) {
                networkConfig.setValue("core_concurrent_connect_delay", k.b().a("core_concurrent_connect_delay"));
                return;
            } else {
                if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(userConfigValue2)) {
                    return;
                }
                networkConfig.setValue("core_connect_timeout", k.b().a("core_connect_timeout"));
                return;
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = userConfigValue;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = userConfigValue2;
        }
        if (StringUtils.stringToInteger(str2, -1) >= StringUtils.stringToInteger(str, -1)) {
            Logger.w(p, "Concurrent Connect Delay " + str2 + " ms is bigger than Connect Timeout " + str + " ms, please check. This request will use the default value.");
            networkConfig.setValue("core_connect_timeout", k.b().a("core_connect_timeout"));
            networkConfig.setValue("core_concurrent_connect_delay", k.b().a("core_concurrent_connect_delay"));
        }
    }

    public static void reloadQuic() {
        Logger.i(p, "reloadQuic");
        g2.getInstance().lazyInitHmsQuicLoader(true);
    }

    private void f() {
        String str;
        try {
            if (r == null) {
                synchronized (HttpClient.class) {
                    if (r == null) {
                        r = new SecureX509TrustManager(ContextHolder.getResourceContext());
                    }
                }
            }
            this.e = r;
            this.f = SecureSSLSocketFactoryNew.getInstance(ContextHolder.getResourceContext(), EncryptUtil.genSecureRandom());
        } catch (IOException e) {
            e = e;
            str = "catch exception when create sslSocketFactory";
            Logger.w(p, str, e);
        } catch (IllegalAccessException e2) {
            e = e2;
            str = "catch exception when create sslSocketFactory";
            Logger.w(p, str, e);
        } catch (KeyManagementException e3) {
            e = e3;
            str = "catch exception when create sslSocketFactory";
            Logger.w(p, str, e);
        } catch (KeyStoreException e4) {
            e = e4;
            str = "catch exception when create sslSocketFactory";
            Logger.w(p, str, e);
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            str = "catch exception when create sslSocketFactory";
            Logger.w(p, str, e);
        } catch (CertificateException e6) {
            e = e6;
            str = "catch exception when create sslSocketFactory";
            Logger.w(p, str, e);
        } catch (Throwable th) {
            e = th;
            HianalyticsHelper.getInstance().reportException(e, CrashHianalyticsData.EVENT_ID_CRASH);
            str = "aegis has unexcept error";
            Logger.w(p, str, e);
        }
    }

    private HostnameVerifier e() {
        if (s == null) {
            synchronized (HttpClient.class) {
                if (s == null) {
                    s = new StrictHostnameVerifier();
                }
            }
        }
        return s;
    }

    private void d() {
        if (this.e == null || this.f == null) {
            f();
        }
        if (this.g == null) {
            this.g = e();
        }
    }

    private d1.a c() {
        try {
            q7.E();
            Logger.v(p, "OkHttpClient create success");
            return new k3(this);
        } catch (NoClassDefFoundError | NoSuchMethodError e) {
            Logger.w(p, "is this type you want?", e);
            return null;
        }
    }

    private boolean b(Request request) {
        String str;
        if (!isHttpClientEnableQuic()) {
            str = "httpclient not enable quic, not use quic";
        } else if (!g2.getInstance().isAvailable()) {
            str = "load quic apk fail, not use quic";
        } else if (this.d != null && !this.d.isAvailable()) {
            str = "create cronet engine fail, not use quic";
        } else {
            if (a(request)) {
                return true;
            }
            str = "domain not enable quic, not use quic";
        }
        Logger.v(p, str);
        return false;
    }

    private Submit<ResponseBody> b(h1.d dVar, WebSocket webSocket) {
        return new h1.h(new v0(this, dVar, webSocket));
    }

    private d1.a b() {
        d1.a c2 = c();
        return c2 == null ? new f4(this) : c2;
    }

    private boolean a(Request request) {
        d3 d3Var = new d3(request.getUrl());
        return g2.getInstance().isEnableQuic(d3Var.getHost(), d3Var.getPort()).booleanValue();
    }

    private void a(h1.d dVar, WebSocket webSocket) {
        b(dVar, webSocket).enqueue(new c());
    }

    public static class c extends Callback {
        @Override // com.huawei.hms.network.httpclient.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            if (response == null || response.getCode() != 101) {
                Logger.w(a1.p, "websocket response exception");
            } else {
                Logger.i(a1.p, "websocket response ok");
            }
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onFailure(Submit submit, Throwable th) {
            Logger.w(a1.p, "websocket request fail");
        }

        public c() {
        }
    }

    private String a(String str) {
        String value = this.o.getValue(str, PolicyNetworkService.ProfileConstants.SCENE_TYPE);
        return (!TextUtils.equals(value, PolicyNetworkService.ProfileConstants.RESTFUL) && TextUtils.equals(value, PolicyNetworkService.ProfileConstants.FILE_MANAGER)) ? PolicyNetworkService.ProfileConstants.FILE_MANAGER : PolicyNetworkService.ProfileConstants.RESTFUL;
    }

    private k2 a() {
        k2 k2Var;
        synchronized (this) {
            k2Var = new k2(new z1(ContextHolder.getResourceContext()), this.o);
        }
        return k2Var;
    }

    private h1.d a(Request request, String str, String str2) {
        NetworkConfig networkConfig = new NetworkConfig(request.getOptions());
        Logger.v(p, "requestOptions: " + request.getOptions());
        Logger.v(p, "clientOptions: " + str);
        a(networkConfig);
        Logger.v(p, "requestOptions: " + networkConfig.toString());
        networkConfig.appendOption(str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PolicyNetworkService.ProfileConstants.SCENE_TYPE, str2);
        } catch (JSONException unused) {
            Logger.w(p, "appendSceneType error " + str2);
        }
        networkConfig.appendOption(jSONObject.toString());
        Logger.v(p, "newRequestOptions: " + networkConfig.toString());
        return new h1.d(request.newBuilder().options(networkConfig.toString()).build());
    }

    public a1(b bVar) {
        ArrayList arrayList = new ArrayList();
        this.f5149a = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.b = arrayList2;
        this.n = true;
        PolicyExecutor policyExecutor = bVar.k;
        this.o = policyExecutor;
        this.e = bVar.c;
        this.f = bVar.d;
        this.g = bVar.e;
        this.i = bVar.f;
        arrayList.addAll(bVar.f5150a);
        arrayList2.addAll(bVar.b);
        if (this.h == null) {
            this.h = new t2.b(policyExecutor.getBoolean("", "core_enable_plaintext_url_path"));
        }
        if (this.j == null) {
            o2 o2Var = o2.DEFAULT;
            this.j = o2Var;
            o2Var.setDnstime(policyExecutor.getInt("", "core_connect_timeout"));
        }
        if (this.i) {
            if (g2.getInstance().isSupportCronet()) {
                g2.getInstance().loadQuicConf();
                g2.getInstance().lazyInitHmsQuicLoader(false);
            } else {
                Logger.i(p, "system don't support cronet, so diable quic!!!");
                this.i = false;
            }
        }
        this.k = bVar.h;
        this.l = bVar.i;
        this.m = bVar.j;
        this.n = bVar.g;
        if (this.e == null || this.f == null || this.g == null) {
            d();
        }
        this.c = b();
    }
}

package com.huawei.secure.android.common.ssl;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import androidx.webkit.ProxyConfig;
import com.huawei.hms.network.embedded.g2;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import com.huawei.secure.android.common.ssl.util.d;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

/* loaded from: classes6.dex */
public class WebViewSSLCheckThread extends Thread {
    private static final String i = "WebViewSSLCheckThread";

    /* renamed from: a, reason: collision with root package name */
    private SSLSocketFactory f8624a;
    private HostnameVerifier b;
    private org.apache.http.conn.ssl.SSLSocketFactory c;
    private X509HostnameVerifier d;
    private SslErrorHandler e;
    private String f;
    private Callback g;
    private Context h;

    public interface Callback {
        void onCancel(Context context, String str);

        void onProceed(Context context, String str);
    }

    static final class a implements okhttp3.Callback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Callback f8625a;
        final /* synthetic */ Context b;
        final /* synthetic */ String c;
        final /* synthetic */ SslErrorHandler d;

        a(Callback callback, Context context, String str, SslErrorHandler sslErrorHandler) {
            this.f8625a = callback;
            this.b = context;
            this.c = str;
            this.d = sslErrorHandler;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            d.b(WebViewSSLCheckThread.i, "onFailure , IO Exception : " + iOException.getMessage());
            Callback callback = this.f8625a;
            if (callback != null) {
                callback.onCancel(this.b, this.c);
            } else {
                this.d.cancel();
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            d.b(WebViewSSLCheckThread.i, "onResponse . proceed");
            Callback callback = this.f8625a;
            if (callback != null) {
                callback.onProceed(this.b, this.c);
            } else {
                this.d.proceed();
            }
        }
    }

    public WebViewSSLCheckThread() {
    }

    private void b() throws Exception {
        this.c.setHostnameVerifier(this.d);
        org.apache.http.conn.ssl.SSLSocketFactory sSLSocketFactory = this.c;
        if (sSLSocketFactory instanceof SecureApacheSSLSocketFactory) {
            ((SecureApacheSSLSocketFactory) sSLSocketFactory).setContext(this.h);
        }
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(ProxyConfig.MATCH_HTTPS, this.c, g2.n));
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(this.f));
        HttpResponse execute = defaultHttpClient.execute(httpGet);
        d.c(i, "status code is : " + execute.getStatusLine().getStatusCode());
    }

    private void c() {
        String str = i;
        d.c(str, "callbackCancel: ");
        Callback callback = this.g;
        if (callback != null) {
            callback.onCancel(this.h, this.f);
        } else if (this.e != null) {
            d.c(str, "callbackCancel 2: ");
            this.e.cancel();
        }
    }

    public static void checkServerCertificateWithOK(SslErrorHandler sslErrorHandler, String str, Context context) {
        checkServerCertificateWithOK(sslErrorHandler, str, context, null);
    }

    private void d() {
        d.c(i, "callbackProceed: ");
        Callback callback = this.g;
        if (callback != null) {
            callback.onProceed(this.h, this.f);
            return;
        }
        SslErrorHandler sslErrorHandler = this.e;
        if (sslErrorHandler != null) {
            sslErrorHandler.proceed();
        }
    }

    private void e() throws IOException {
        HttpsURLConnection httpsURLConnection = null;
        try {
            URLConnection openConnection = new URL(this.f).openConnection();
            if (openConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) openConnection;
                try {
                    httpsURLConnection2.setSSLSocketFactory(this.f8624a);
                    httpsURLConnection2.setHostnameVerifier(this.b);
                    httpsURLConnection2.setRequestMethod("GET");
                    httpsURLConnection2.setConnectTimeout(10000);
                    httpsURLConnection2.setReadTimeout(20000);
                    httpsURLConnection2.connect();
                    httpsURLConnection = httpsURLConnection2;
                } catch (Throwable th) {
                    th = th;
                    httpsURLConnection = httpsURLConnection2;
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th;
                }
            }
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public X509HostnameVerifier getApacheHostnameVerifier() {
        return this.d;
    }

    public org.apache.http.conn.ssl.SSLSocketFactory getApacheSSLSocketFactory() {
        return this.c;
    }

    public Callback getCallback() {
        return this.g;
    }

    public Context getContext() {
        return this.h;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.b;
    }

    public SslErrorHandler getSslErrorHandler() {
        return this.e;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.f8624a;
    }

    public String getUrl() {
        return this.f;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        if (this.c == null || this.d == null) {
            if (this.f8624a == null || this.b == null) {
                c();
                return;
            }
            try {
                e();
                d();
                return;
            } catch (Exception e) {
                d.b(i, "exception : " + e.getMessage());
                c();
                return;
            }
        }
        if (this.e == null || TextUtils.isEmpty(this.f)) {
            d.b(i, "sslErrorHandler or url is null");
            c();
            return;
        }
        try {
            b();
            d();
        } catch (Exception e2) {
            d.b(i, "run: exception : " + e2.getMessage());
            c();
        }
    }

    public void setApacheHostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        this.d = x509HostnameVerifier;
    }

    public void setApacheSSLSocketFactory(org.apache.http.conn.ssl.SSLSocketFactory sSLSocketFactory) {
        this.c = sSLSocketFactory;
    }

    public void setCallback(Callback callback) {
        this.g = callback;
    }

    public void setContext(Context context) {
        this.h = context;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.b = hostnameVerifier;
    }

    public void setSslErrorHandler(SslErrorHandler sslErrorHandler) {
        this.e = sslErrorHandler;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.f8624a = sSLSocketFactory;
    }

    public void setUrl(String str) {
        this.f = str;
    }

    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, Context context) throws CertificateException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException, IllegalAccessException {
        setSslErrorHandler(sslErrorHandler);
        setUrl(str);
        setContext(context);
        setSslSocketFactory(new SecureSSLSocketFactoryNew(new c(context)));
        setHostnameVerifier(new StrictHostnameVerifier());
        try {
            setApacheSSLSocketFactory(new SecureApacheSSLSocketFactory(null, new c(context)));
        } catch (UnrecoverableKeyException e) {
            d.b(i, "WebViewSSLCheckThread: UnrecoverableKeyException : " + e.getMessage());
        }
        setApacheHostnameVerifier(SecureApacheSSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
    }

    public static void checkServerCertificateWithOK(SslErrorHandler sslErrorHandler, String str, Context context, Callback callback) {
        if (sslErrorHandler == null || TextUtils.isEmpty(str) || context == null) {
            d.b(i, "checkServerCertificateWithOK: handler or url or context is null");
            return;
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            SecureSSLSocketFactoryNew secureSSLSocketFactoryNew = new SecureSSLSocketFactoryNew(new c(context));
            secureSSLSocketFactoryNew.setContext(context);
            builder.sslSocketFactory(secureSSLSocketFactoryNew, new c(context));
            builder.hostnameVerifier(new StrictHostnameVerifier());
            builder.build().newCall(new Request.Builder().url(str).build()).enqueue(new a(callback, context, str, sslErrorHandler));
        } catch (Exception e) {
            d.b(i, "checkServerCertificateWithOK: exception : " + e.getMessage());
            sslErrorHandler.cancel();
        }
    }

    @Deprecated
    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier) {
        setSslErrorHandler(sslErrorHandler);
        setUrl(str);
        setSslSocketFactory(sSLSocketFactory);
        setHostnameVerifier(hostnameVerifier);
    }

    @Deprecated
    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, org.apache.http.conn.ssl.SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier) {
        setSslErrorHandler(sslErrorHandler);
        setUrl(str);
        setApacheSSLSocketFactory(sSLSocketFactory);
        setApacheHostnameVerifier(x509HostnameVerifier);
    }

    @Deprecated
    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, org.apache.http.conn.ssl.SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier, Callback callback, Context context) {
        this.e = sslErrorHandler;
        this.f = str;
        this.c = sSLSocketFactory;
        this.d = x509HostnameVerifier;
        this.g = callback;
        this.h = context;
    }
}

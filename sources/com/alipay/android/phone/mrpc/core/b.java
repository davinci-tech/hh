package com.alipay.android.phone.mrpc.core;

import android.net.SSLCertificateSocketFactory;
import android.util.Base64;
import android.util.Log;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.security.Security;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes7.dex */
public final class b implements HttpClient {

    /* renamed from: a, reason: collision with root package name */
    public static long f817a = 160;
    public static String[] b = {"text/", "application/xml", "application/json"};
    public static final HttpRequestInterceptor c = new c();
    public final HttpClient d;
    public RuntimeException e = new IllegalStateException("AndroidHttpClient created and never closed");
    public volatile C0007b f;

    @Override // org.apache.http.client.HttpClient
    public final HttpParams getParams() {
        return this.d.getParams();
    }

    @Override // org.apache.http.client.HttpClient
    public final ClientConnectionManager getConnectionManager() {
        return this.d.getConnectionManager();
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest, httpContext) : ApacheClientInstrumentation.execute(httpClient, httpUriRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest) : ApacheClientInstrumentation.execute(httpClient, httpUriRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest, httpContext) : ApacheClientInstrumentation.execute(httpClient, httpHost, httpRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest) : ApacheClientInstrumentation.execute(httpClient, httpHost, httpRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpUriRequest, responseHandler, httpContext) : (T) ApacheClientInstrumentation.execute(httpClient, httpUriRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpUriRequest, responseHandler) : (T) ApacheClientInstrumentation.execute(httpClient, httpUriRequest, responseHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpHost, httpRequest, responseHandler, httpContext) : (T) ApacheClientInstrumentation.execute(httpClient, httpHost, httpRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        HttpClient httpClient = this.d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpHost, httpRequest, responseHandler) : (T) ApacheClientInstrumentation.execute(httpClient, httpHost, httpRequest, responseHandler);
    }

    public final void a(HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient) this.d).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }

    public static boolean b(HttpUriRequest httpUriRequest) {
        Header[] headers = httpUriRequest.getHeaders("content-encoding");
        if (headers != null) {
            for (Header header : headers) {
                if (Constants.GZIP.equalsIgnoreCase(header.getValue())) {
                    return true;
                }
            }
        }
        Header[] headers2 = httpUriRequest.getHeaders(com.alipay.sdk.m.p.e.f);
        if (headers2 != null) {
            for (Header header2 : headers2) {
                for (String str : b) {
                    if (header2.getValue().startsWith(str)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void b(HttpRequest httpRequest) {
        httpRequest.addHeader("Connection", "Keep-Alive");
    }

    public static long b(String str) {
        return k.a(str);
    }

    public static void a(HttpRequest httpRequest) {
        httpRequest.addHeader(j2.v, Constants.GZIP);
    }

    public static AbstractHttpEntity a(byte[] bArr) {
        if (bArr.length < f817a) {
            return new ByteArrayEntity(bArr);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
        byteArrayEntity.setContentEncoding(Constants.GZIP);
        int length = bArr.length;
        byteArrayEntity.getContentLength();
        return byteArrayEntity;
    }

    public static /* synthetic */ String a(HttpUriRequest httpUriRequest) {
        HttpEntity entity;
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("curl ");
        for (Header header : httpUriRequest.getAllHeaders()) {
            if (!header.getName().equals("Authorization") && !header.getName().equals("Cookie")) {
                sb.append("--header \"");
                sb.append(header.toString().trim());
                sb.append("\" ");
            }
        }
        URI uri = httpUriRequest.getURI();
        if (httpUriRequest instanceof RequestWrapper) {
            HttpRequest original = ((RequestWrapper) httpUriRequest).getOriginal();
            if (original instanceof HttpUriRequest) {
                uri = ((HttpUriRequest) original).getURI();
            }
        }
        sb.append("\"");
        sb.append(uri);
        sb.append("\"");
        if ((httpUriRequest instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) httpUriRequest).getEntity()) != null && entity.isRepeatable()) {
            if (entity.getContentLength() < 1024) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                entity.writeTo(byteArrayOutputStream);
                if (b(httpUriRequest)) {
                    sb.insert(0, "echo '" + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2) + "' | base64 -d > /tmp/$$.bin; ");
                    str = " --data-binary @/tmp/$$.bin";
                } else {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    sb.append(" --data-ascii \"");
                    sb.append(byteArrayOutputStream2);
                    sb.append("\"");
                }
            } else {
                str = " [TOO MUCH DATA TO INCLUDE]";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static InputStream a(HttpEntity httpEntity) {
        Header contentEncoding;
        String value;
        InputStream content = httpEntity.getContent();
        return (content == null || (contentEncoding = httpEntity.getContentEncoding()) == null || (value = contentEncoding.getValue()) == null || !value.contains(Constants.GZIP)) ? content : new GZIPInputStream(content);
    }

    public final class a implements HttpRequestInterceptor {
        @Override // org.apache.http.HttpRequestInterceptor
        public final void process(HttpRequest httpRequest, HttpContext httpContext) {
            C0007b c0007b = b.this.f;
            if (c0007b != null && C0007b.a(c0007b) && (httpRequest instanceof HttpUriRequest)) {
                C0007b.a(c0007b, b.a((HttpUriRequest) httpRequest));
            }
        }

        public /* synthetic */ a(b bVar, byte b) {
            this();
        }

        public a() {
        }
    }

    public static b a(String str) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpClientParams.setAuthenticating(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(ProxyConfig.MATCH_HTTPS, SSLCertificateSocketFactory.getHttpSocketFactory(30000, null), g2.n));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout(basicHttpParams, 60000L);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
        Security.setProperty("networkaddress.cache.ttl", "-1");
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        return new b(threadSafeClientConnManager, basicHttpParams);
    }

    /* renamed from: com.alipay.android.phone.mrpc.core.b$b, reason: collision with other inner class name */
    public static final class C0007b {

        /* renamed from: a, reason: collision with root package name */
        public final String f819a;
        public final int b;

        public static /* synthetic */ boolean a(C0007b c0007b) {
            return Log.isLoggable(c0007b.f819a, c0007b.b);
        }

        public static /* synthetic */ void a(C0007b c0007b, String str) {
            Log.println(c0007b.b, c0007b.f819a, str);
        }
    }

    public b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.d = new d(this, clientConnectionManager, httpParams);
    }
}

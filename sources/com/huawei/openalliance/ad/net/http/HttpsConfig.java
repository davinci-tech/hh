package com.huawei.openalliance.ad.net.http;

import java.net.HttpURLConnection;
import java.security.KeyStore;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes5.dex */
public abstract class HttpsConfig {

    /* renamed from: a, reason: collision with root package name */
    private static SSLSocketFactory f7298a;
    private static X509TrustManager b;

    private static X509TrustManager b() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(KeyStore.getInstance(KeyStore.getDefaultType()));
        for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    public static TrustManager[] a() {
        return new TrustManager[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0011, code lost:
    
        if (r0 == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(okhttp3.OkHttpClient.Builder r1, boolean r2, boolean r3) {
        /*
            if (r2 == 0) goto L7
            javax.net.ssl.SSLSocketFactory r2 = com.huawei.openalliance.ad.net.http.m.a(r3)
            goto L13
        L7:
            javax.net.ssl.SSLSocketFactory r2 = com.huawei.openalliance.ad.net.http.HttpsConfig.f7298a
            javax.net.ssl.X509TrustManager r0 = com.huawei.openalliance.ad.net.http.HttpsConfig.b
            if (r2 != 0) goto L11
            javax.net.ssl.SSLSocketFactory r2 = com.huawei.openalliance.ad.net.http.m.a(r3)
        L11:
            if (r0 != 0) goto L17
        L13:
            javax.net.ssl.X509TrustManager r0 = b()
        L17:
            if (r2 == 0) goto L1f
            if (r0 == 0) goto L1f
            r1.sslSocketFactory(r2, r0)
            return
        L1f:
            com.huawei.openalliance.ad.exception.d r1 = new com.huawei.openalliance.ad.exception.d
            java.lang.String r2 = "No ssl socket factory or trust manager set"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.net.http.HttpsConfig.a(okhttp3.OkHttpClient$Builder, boolean, boolean):void");
    }

    public static void a(X509TrustManager x509TrustManager) {
        b = x509TrustManager;
    }

    public static void a(SSLSocketFactory sSLSocketFactory) {
        f7298a = sSLSocketFactory;
    }

    public static void a(HttpURLConnection httpURLConnection, boolean z, boolean z2) {
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            SSLSocketFactory sSLSocketFactory = f7298a;
            if (z || sSLSocketFactory == null) {
                sSLSocketFactory = m.a(z2);
            }
            if (sSLSocketFactory == null) {
                throw new com.huawei.openalliance.ad.exception.d("No ssl socket factory set");
            }
            httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0011, code lost:
    
        if (r0 == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.httpclient.HttpClient.Builder a(com.huawei.hms.network.httpclient.HttpClient.Builder r1, boolean r2, boolean r3) {
        /*
            if (r2 == 0) goto L7
            javax.net.ssl.SSLSocketFactory r2 = com.huawei.openalliance.ad.net.http.m.a(r3)
            goto L13
        L7:
            javax.net.ssl.SSLSocketFactory r2 = com.huawei.openalliance.ad.net.http.HttpsConfig.f7298a
            javax.net.ssl.X509TrustManager r0 = com.huawei.openalliance.ad.net.http.HttpsConfig.b
            if (r2 != 0) goto L11
            javax.net.ssl.SSLSocketFactory r2 = com.huawei.openalliance.ad.net.http.m.a(r3)
        L11:
            if (r0 != 0) goto L17
        L13:
            javax.net.ssl.X509TrustManager r0 = b()
        L17:
            if (r2 == 0) goto L1f
            if (r0 == 0) goto L1f
            r1.sslSocketFactory(r2, r0)
            return r1
        L1f:
            com.huawei.openalliance.ad.exception.d r1 = new com.huawei.openalliance.ad.exception.d
            java.lang.String r2 = "No ssl socket factory or trust manager set"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.net.http.HttpsConfig.a(com.huawei.hms.network.httpclient.HttpClient$Builder, boolean, boolean):com.huawei.hms.network.httpclient.HttpClient$Builder");
    }
}

package defpackage;

import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes3.dex */
public final class bem {
    private static boolean e() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0043 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.httpclient.HttpClient b() {
        /*
            java.lang.String r0 = "HttpClientUtil"
            r1 = 0
            android.content.Context r2 = defpackage.bec.e()     // Catch: java.security.KeyManagementException -> L12 java.lang.IllegalAccessException -> L1a java.io.IOException -> L22 java.security.KeyStoreException -> L2a java.security.NoSuchAlgorithmException -> L32 java.security.cert.CertificateException -> L3a
            com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew r3 = com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew.getInstance(r2)     // Catch: java.security.KeyManagementException -> L12 java.lang.IllegalAccessException -> L1a java.io.IOException -> L22 java.security.KeyStoreException -> L2a java.security.NoSuchAlgorithmException -> L32 java.security.cert.CertificateException -> L3a
            com.huawei.secure.android.common.ssl.SecureX509TrustManager r4 = new com.huawei.secure.android.common.ssl.SecureX509TrustManager     // Catch: java.security.KeyManagementException -> L13 java.lang.IllegalAccessException -> L1b java.io.IOException -> L23 java.security.KeyStoreException -> L2b java.security.NoSuchAlgorithmException -> L33 java.security.cert.CertificateException -> L3b
            r4.<init>(r2)     // Catch: java.security.KeyManagementException -> L13 java.lang.IllegalAccessException -> L1b java.io.IOException -> L23 java.security.KeyStoreException -> L2b java.security.NoSuchAlgorithmException -> L33 java.security.cert.CertificateException -> L3b
            r1 = r4
            goto L41
        L12:
            r3 = r1
        L13:
            java.lang.String r2 = "sslSocketFactory KeyManagementException exception"
            defpackage.bes.d(r0, r2)
            goto L41
        L1a:
            r3 = r1
        L1b:
            java.lang.String r2 = "sslSocketFactory IllegalAccessException exception"
            defpackage.bes.d(r0, r2)
            goto L41
        L22:
            r3 = r1
        L23:
            java.lang.String r2 = "sslSocketFactory IOException exception"
            defpackage.bes.d(r0, r2)
            goto L41
        L2a:
            r3 = r1
        L2b:
            java.lang.String r2 = "sslSocketFactory KeyStoreException exception"
            defpackage.bes.d(r0, r2)
            goto L41
        L32:
            r3 = r1
        L33:
            java.lang.String r2 = "sslSocketFactory NoSuchAlgorithmException exception"
            defpackage.bes.d(r0, r2)
            goto L41
        L3a:
            r3 = r1
        L3b:
            java.lang.String r2 = "sslSocketFactory CertificateException exception"
            defpackage.bes.d(r0, r2)
        L41:
            if (r3 == 0) goto L52
            if (r1 == 0) goto L52
            com.huawei.hms.network.httpclient.HttpClient$Builder r0 = d()
            com.huawei.hms.network.httpclient.HttpClient$Builder r0 = r0.sslSocketFactory(r3, r1)
            com.huawei.hms.network.httpclient.HttpClient r0 = r0.build()
            return r0
        L52:
            com.huawei.hms.network.httpclient.HttpClient r0 = a()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bem.b():com.huawei.hms.network.httpclient.HttpClient");
    }

    private static HttpClient a() {
        return d().sslSocketFactory((SSLSocketFactory) new bej(), (X509TrustManager) new bep()).build();
    }

    private static HttpClient.Builder d() {
        HttpClient.Builder retryTimeOnConnectionFailure = new HttpClient.Builder().connectTimeout(10000).readTimeout(10000).writeTimeout(10000).retryTimeOnConnectionFailure(0);
        return e() ? retryTimeOnConnectionFailure.addInterceptor((Interceptor) new bei()) : retryTimeOnConnectionFailure;
    }
}

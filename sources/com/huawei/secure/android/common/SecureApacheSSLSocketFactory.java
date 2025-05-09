package com.huawei.secure.android.common;

import android.content.Context;
import com.huawei.secure.android.common.ssl.SSLUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.X509HostnameVerifier;

@Deprecated
/* loaded from: classes9.dex */
public class SecureApacheSSLSocketFactory extends SSLSocketFactory {
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    private static volatile SecureApacheSSLSocketFactory c = null;

    /* renamed from: a, reason: collision with root package name */
    private SSLContext f8554a;
    private Context b;

    private SecureApacheSSLSocketFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
    }

    private void a(Socket socket) {
        SSLSocket sSLSocket = (SSLSocket) socket;
        SSLUtil.setEnabledProtocols(sSLSocket);
        SSLUtil.setEnableSafeCipherSuites(sSLSocket);
    }

    @Deprecated
    public static SecureApacheSSLSocketFactory getInstance(KeyStore keyStore, Context context) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalAccessException {
        if (c == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (c == null) {
                    c = new SecureApacheSSLSocketFactory(keyStore, context, (SecureRandom) null);
                }
            }
        }
        return c;
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket createSocket = this.f8554a.getSocketFactory().createSocket(socket, str, i, z);
        a(createSocket);
        return createSocket;
    }

    private SecureApacheSSLSocketFactory(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalAccessException {
        super(keyStore);
        this.b = context;
        this.f8554a = SSLUtil.setSSLContext();
        this.f8554a.init(null, new X509TrustManager[]{new SecureX509TrustManager(this.b)}, secureRandom);
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
    public Socket createSocket() throws IOException {
        Socket createSocket = this.f8554a.getSocketFactory().createSocket();
        a(createSocket);
        return createSocket;
    }

    @Deprecated
    public SecureApacheSSLSocketFactory(KeyStore keyStore, InputStream inputStream, String str) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
        super(keyStore);
        this.f8554a = SSLUtil.setSSLContext();
        this.f8554a.init(null, new X509TrustManager[]{new HiCloudX509TrustManager(inputStream, str)}, null);
    }

    @Deprecated
    public static SecureApacheSSLSocketFactory getInstance(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalAccessException {
        if (c == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (c == null) {
                    c = new SecureApacheSSLSocketFactory(keyStore, context, secureRandom);
                }
            }
        }
        return c;
    }

    @Deprecated
    public SecureApacheSSLSocketFactory(KeyStore keyStore, InputStream inputStream, String str, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
        super(keyStore);
        this.f8554a = SSLUtil.setSSLContext();
        this.f8554a.init(null, new X509TrustManager[]{new HiCloudX509TrustManager(inputStream, str)}, secureRandom);
    }
}

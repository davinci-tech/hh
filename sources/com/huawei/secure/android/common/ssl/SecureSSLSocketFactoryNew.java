package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import com.huawei.secure.android.common.ssl.util.d;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes6.dex */
public class SecureSSLSocketFactoryNew extends SSLSocketFactory {
    private static final String i = "SSLFNew";
    private static volatile SecureSSLSocketFactoryNew j;

    /* renamed from: a, reason: collision with root package name */
    protected SSLContext f8620a;
    protected SSLSocket b;
    protected Context c;
    protected String[] d;
    protected X509TrustManager e;
    protected String[] f;
    protected String[] g;
    protected String[] h;

    @Deprecated
    public SecureSSLSocketFactoryNew(InputStream inputStream, String str) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        this.f8620a = null;
        this.b = null;
        this.f8620a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f8620a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, null);
    }

    private void a(Socket socket) {
        boolean z;
        boolean z2 = false;
        if (com.huawei.secure.android.common.ssl.util.a.a(this.h)) {
            z = false;
        } else {
            d.c(i, "set protocols");
            z = SSLUtil.setEnabledProtocols((SSLSocket) socket, this.h);
        }
        if (!com.huawei.secure.android.common.ssl.util.a.a(this.g) || !com.huawei.secure.android.common.ssl.util.a.a(this.f)) {
            d.c(i, "set cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLUtil.setEnabledProtocols(sSLSocket);
            z2 = !com.huawei.secure.android.common.ssl.util.a.a(this.g) ? SSLUtil.setWhiteListCipherSuites(sSLSocket, this.g) : SSLUtil.setBlackListCipherSuites(sSLSocket, this.f);
        }
        if (!z) {
            d.c(i, "set default protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        d.c(i, "set default cipher");
        SSLUtil.setEnableSafeCipherSuites((SSLSocket) socket);
    }

    @Deprecated
    public static SecureSSLSocketFactoryNew getInstance(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalAccessException, KeyManagementException, IllegalArgumentException {
        long currentTimeMillis = System.currentTimeMillis();
        ContextUtil.setContext(context);
        if (j == null) {
            synchronized (SecureSSLSocketFactoryNew.class) {
                if (j == null) {
                    j = new SecureSSLSocketFactoryNew(context, (SecureRandom) null);
                }
            }
        }
        if (j.c == null && context != null) {
            j.setContext(context);
        }
        d.a(i, "getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return j;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) throws IOException {
        d.c(i, "createSocket: host , port");
        Socket createSocket = this.f8620a.getSocketFactory().createSocket(str, i2);
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.d = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public String[] getBlackCiphers() {
        return this.f;
    }

    public X509Certificate[] getChain() {
        X509TrustManager x509TrustManager = this.e;
        return x509TrustManager instanceof SecureX509TrustManager ? ((SecureX509TrustManager) x509TrustManager).getChain() : new X509Certificate[0];
    }

    public Context getContext() {
        return this.c;
    }

    public String[] getProtocols() {
        return this.h;
    }

    public SSLContext getSslContext() {
        return this.f8620a;
    }

    public SSLSocket getSslSocket() {
        return this.b;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.d;
        return strArr != null ? strArr : new String[0];
    }

    public String[] getWhiteCiphers() {
        return this.g;
    }

    public X509TrustManager getX509TrustManager() {
        return this.e;
    }

    public void setBlackCiphers(String[] strArr) {
        this.f = strArr;
    }

    public void setContext(Context context) {
        this.c = context.getApplicationContext();
    }

    public void setProtocols(String[] strArr) {
        this.h = strArr;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.f8620a = sSLContext;
    }

    public void setWhiteCiphers(String[] strArr) {
        this.g = strArr;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.e = x509TrustManager;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) throws IOException {
        return createSocket(inetAddress.getHostAddress(), i2);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) throws IOException, UnknownHostException {
        return createSocket(str, i2);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) throws IOException {
        return createSocket(inetAddress.getHostAddress(), i2);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) throws IOException {
        d.c(i, "createSocket");
        Socket createSocket = this.f8620a.getSocketFactory().createSocket(socket, str, i2, z);
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.d = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public static SecureSSLSocketFactoryNew getInstance(Context context, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalAccessException, KeyManagementException, IllegalArgumentException {
        long currentTimeMillis = System.currentTimeMillis();
        ContextUtil.setContext(context);
        if (j == null) {
            synchronized (SecureSSLSocketFactoryNew.class) {
                if (j == null) {
                    j = new SecureSSLSocketFactoryNew(context, secureRandom);
                }
            }
        }
        if (j.c == null && context != null) {
            j.setContext(context);
        }
        d.a(i, "getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return j;
    }

    public SecureSSLSocketFactoryNew(InputStream inputStream, String str, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        this.f8620a = null;
        this.b = null;
        this.f8620a = SSLUtil.setSSLContext();
        HiCloudX509TrustManager hiCloudX509TrustManager = new HiCloudX509TrustManager(inputStream, str);
        setX509TrustManager(hiCloudX509TrustManager);
        this.f8620a.init(null, new X509TrustManager[]{hiCloudX509TrustManager}, secureRandom);
    }

    private SecureSSLSocketFactoryNew(Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, KeyManagementException, NullPointerException {
        this.f8620a = null;
        this.b = null;
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        SecureX509TrustManager secureX509SingleInstance = SecureX509SingleInstance.getInstance(context);
        this.e = secureX509SingleInstance;
        this.f8620a.init(null, new X509TrustManager[]{secureX509SingleInstance}, secureRandom);
    }

    @Deprecated
    public SecureSSLSocketFactoryNew(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f8620a = null;
        this.b = null;
        this.f8620a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f8620a.init(null, new X509TrustManager[]{x509TrustManager}, null);
    }

    public SecureSSLSocketFactoryNew(X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f8620a = null;
        this.b = null;
        this.f8620a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f8620a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }
}

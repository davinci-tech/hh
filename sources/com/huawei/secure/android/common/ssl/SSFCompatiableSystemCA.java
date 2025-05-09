package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import com.huawei.secure.android.common.ssl.util.d;
import java.io.IOException;
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
public class SSFCompatiableSystemCA extends SSLSocketFactory {
    private static final String i = "SSFCompatiableSystemCA";
    private static volatile SSFCompatiableSystemCA j;

    /* renamed from: a, reason: collision with root package name */
    private SSLContext f8615a;
    private SSLSocket b;
    private Context c;
    private String[] d;
    private X509TrustManager e;
    private String[] f;
    private String[] g;
    private String[] h;

    private SSFCompatiableSystemCA(Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, KeyManagementException, NullPointerException {
        this.f8615a = null;
        this.b = null;
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        SecureX509TrustManager sSFSecureX509SingleInstance = SSFSecureX509SingleInstance.getInstance(context);
        this.e = sSFSecureX509SingleInstance;
        this.f8615a.init(null, new X509TrustManager[]{sSFSecureX509SingleInstance}, secureRandom);
    }

    @Deprecated
    static void a(X509TrustManager x509TrustManager) {
        d.c(i, "ssfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            j = new SSFCompatiableSystemCA(x509TrustManager);
        } catch (KeyManagementException unused) {
            d.b(i, "KeyManagementException");
        } catch (NoSuchAlgorithmException unused2) {
            d.b(i, "NoSuchAlgorithmException");
        }
        d.a(i, "SSF system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public static SSFCompatiableSystemCA getInstance(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (j == null) {
            synchronized (SSFCompatiableSystemCA.class) {
                if (j == null) {
                    j = new SSFCompatiableSystemCA(context, (SecureRandom) null);
                }
            }
        }
        if (j.c == null && context != null) {
            j.setContext(context);
        }
        return j;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) throws IOException {
        d.c(i, "createSocket: host , port");
        Socket createSocket = this.f8615a.getSocketFactory().createSocket(str, i2);
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
        return this.f8615a;
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
        this.f8615a = sSLContext;
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
        d.c(i, "createSocket: s , host , port , autoClose");
        Socket createSocket = this.f8615a.getSocketFactory().createSocket(socket, str, i2, z);
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.d = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public static SSFCompatiableSystemCA getInstance(Context context, SecureRandom secureRandom) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (j == null) {
            synchronized (SSFCompatiableSystemCA.class) {
                if (j == null) {
                    j = new SSFCompatiableSystemCA(context, secureRandom);
                }
            }
        }
        if (j.c == null && context != null) {
            j.setContext(context);
        }
        return j;
    }

    static void a(X509TrustManager x509TrustManager, SecureRandom secureRandom) {
        d.c(i, "ssfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            j = new SSFCompatiableSystemCA(x509TrustManager, secureRandom);
        } catch (KeyManagementException unused) {
            d.b(i, "KeyManagementException");
        } catch (NoSuchAlgorithmException unused2) {
            d.b(i, "NoSuchAlgorithmException");
        }
        d.a(i, "SSF system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public SSFCompatiableSystemCA(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f8615a = null;
        this.b = null;
        this.f8615a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f8615a.init(null, new X509TrustManager[]{x509TrustManager}, null);
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
            d.c(i, "set white cipher or black cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLUtil.setEnabledProtocols(sSLSocket);
            if (!com.huawei.secure.android.common.ssl.util.a.a(this.g)) {
                z2 = SSLUtil.setWhiteListCipherSuites(sSLSocket, this.g);
            } else {
                z2 = SSLUtil.setBlackListCipherSuites(sSLSocket, this.f);
            }
        }
        if (!z) {
            d.c(i, "set default protocols");
            SSLUtil.setEnabledProtocols((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        d.c(i, "set default cipher suites");
        SSLUtil.setEnableSafeCipherSuites((SSLSocket) socket);
    }

    public SSFCompatiableSystemCA(X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.f8615a = null;
        this.b = null;
        this.f8615a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f8615a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }
}

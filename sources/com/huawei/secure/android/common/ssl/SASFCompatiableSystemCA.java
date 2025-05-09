package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import com.huawei.secure.android.common.ssl.util.d;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: classes9.dex */
public class SASFCompatiableSystemCA extends SSLSocketFactory {
    private static final String i = "SASFCompatiableSystemCA";
    private static volatile SASFCompatiableSystemCA j;

    /* renamed from: a, reason: collision with root package name */
    private SSLContext f8614a;
    private SSLSocket b;
    private Context c;
    private String[] d;
    private X509TrustManager e;
    private String[] f;
    private String[] g;
    private String[] h;

    private SASFCompatiableSystemCA(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        this.b = null;
    }

    @Deprecated
    static void a(X509TrustManager x509TrustManager) {
        d.c(i, "sasfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            j = new SASFCompatiableSystemCA(null, x509TrustManager);
        } catch (KeyManagementException unused) {
            d.b(i, "KeyManagementException");
        } catch (KeyStoreException unused2) {
            d.b(i, "KeyStoreException");
        } catch (NoSuchAlgorithmException unused3) {
            d.b(i, "NoSuchAlgorithmException");
        } catch (UnrecoverableKeyException unused4) {
            d.b(i, "UnrecoverableKeyException");
        }
        d.a(i, "sasf system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public static SASFCompatiableSystemCA getInstance(KeyStore keyStore, Context context) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (j == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (j == null) {
                    j = new SASFCompatiableSystemCA(keyStore, context, (SecureRandom) null);
                }
            }
        }
        return j;
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) throws IOException {
        d.c(i, "createSocket: socket host port autoClose");
        Socket createSocket = this.f8614a.getSocketFactory().createSocket(socket, str, i2, z);
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
        return this.f8614a;
    }

    public SSLSocket getSslSocket() {
        return this.b;
    }

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
        this.f8614a = sSLContext;
    }

    public void setSslSocket(SSLSocket sSLSocket) {
        this.b = sSLSocket;
    }

    public void setWhiteCiphers(String[] strArr) {
        this.g = strArr;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.e = x509TrustManager;
    }

    private SASFCompatiableSystemCA(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException, NullPointerException {
        super(keyStore);
        this.b = null;
        setContext(context);
        setSslContext(SSLUtil.setSSLContext());
        SecureX509TrustManager sSFSecureX509SingleInstance = SSFSecureX509SingleInstance.getInstance(context);
        this.e = sSFSecureX509SingleInstance;
        this.f8614a.init(null, new X509TrustManager[]{sSFSecureX509SingleInstance}, secureRandom);
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
    public Socket createSocket() throws IOException {
        d.c(i, "createSocket: ");
        Socket createSocket = this.f8614a.getSocketFactory().createSocket();
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.d = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public static SASFCompatiableSystemCA getInstance(KeyStore keyStore, Context context, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException, IllegalArgumentException {
        ContextUtil.setContext(context);
        if (j == null) {
            synchronized (SecureApacheSSLSocketFactory.class) {
                if (j == null) {
                    j = new SASFCompatiableSystemCA(keyStore, context, secureRandom);
                }
            }
        }
        return j;
    }

    static void a(X509TrustManager x509TrustManager, SecureRandom secureRandom) {
        d.c(i, "sasfc update socket factory trust manager");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            j = new SASFCompatiableSystemCA((KeyStore) null, x509TrustManager, secureRandom);
        } catch (KeyManagementException unused) {
            d.b(i, "KeyManagementException");
        } catch (KeyStoreException unused2) {
            d.b(i, "KeyStoreException");
        } catch (NoSuchAlgorithmException unused3) {
            d.b(i, "NoSuchAlgorithmException");
        } catch (UnrecoverableKeyException unused4) {
            d.b(i, "UnrecoverableKeyException");
        }
        d.a(i, "sasf system ca update: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Deprecated
    public SASFCompatiableSystemCA(KeyStore keyStore, X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException, UnrecoverableKeyException, KeyStoreException {
        super(keyStore);
        this.b = null;
        this.f8614a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f8614a.init(null, new X509TrustManager[]{x509TrustManager}, null);
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

    public SASFCompatiableSystemCA(KeyStore keyStore, X509TrustManager x509TrustManager, SecureRandom secureRandom) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException, UnrecoverableKeyException, KeyStoreException {
        super(keyStore);
        this.b = null;
        this.f8614a = SSLUtil.setSSLContext();
        setX509TrustManager(x509TrustManager);
        this.f8614a.init(null, new X509TrustManager[]{x509TrustManager}, secureRandom);
    }
}

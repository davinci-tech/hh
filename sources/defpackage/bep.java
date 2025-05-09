package defpackage;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes3.dex */
public class bep implements X509TrustManager {
    private X509TrustManager e;

    public bep() {
        this.e = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidCAStore");
            keyStore.load(null, null);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    bes.e("HuaweiTrustManagerImpl", "X509TrustManager");
                    this.e = (X509TrustManager) trustManager;
                    return;
                }
            }
        } catch (IOException unused) {
            bes.d("HuaweiTrustManagerImpl", "HuaweiTrustManagerImpl IOException");
        } catch (KeyStoreException e) {
            e = e;
            bes.d("HuaweiTrustManagerImpl", "HuaweiTrustManagerImpl detail exception " + e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            bes.d("HuaweiTrustManagerImpl", "HuaweiTrustManagerImpl detail exception " + e.getMessage());
        } catch (CertificateException e3) {
            e = e3;
            bes.d("HuaweiTrustManagerImpl", "HuaweiTrustManagerImpl detail exception " + e.getMessage());
        } catch (Exception e4) {
            bes.d("HuaweiTrustManagerImpl", "HuaweiTrustManagerImpl exception " + e4.getMessage());
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.e.checkClientTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        try {
            this.e.checkServerTrusted(x509CertificateArr, str);
        } catch (CertificateException e) {
            for (Throwable th = e; th != null; th = th.getCause()) {
                if ((th instanceof CertificateExpiredException) || (th instanceof CertificateNotYetValidException)) {
                    bes.d("HuaweiTrustManagerImpl", "CertificateExpiredException or CertificateNotYetValidException");
                }
            }
            bes.d("HuaweiTrustManagerImpl", "checkServerTrusted exception throw");
            throw e;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.e.getAcceptedIssuers();
    }
}

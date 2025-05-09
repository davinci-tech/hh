package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.Conscrypt;

/* loaded from: classes9.dex */
public class ia extends ma {
    @Override // com.huawei.hms.network.embedded.ma
    @Nullable
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        if (!Conscrypt.isConscrypt(sSLSocketFactory)) {
            return super.c(sSLSocketFactory);
        }
        try {
            Object a2 = ma.a(sSLSocketFactory, (Class<Object>) Object.class, "sslParameters");
            if (a2 != null) {
                return (X509TrustManager) ma.a(a2, X509TrustManager.class, "x509TrustManager");
            }
            return null;
        } catch (Exception e) {
            throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on Conscrypt", e);
        }
    }

    @Override // com.huawei.hms.network.embedded.ma
    public void b(SSLSocketFactory sSLSocketFactory) {
        if (Conscrypt.isConscrypt(sSLSocketFactory)) {
            Conscrypt.setUseEngineSocket(sSLSocketFactory, true);
        }
    }

    @Override // com.huawei.hms.network.embedded.ma
    public SSLContext b() {
        try {
            return SSLContext.getInstance("TLSv1.3", j());
        } catch (NoSuchAlgorithmException e) {
            try {
                return SSLContext.getInstance("TLS", j());
            } catch (NoSuchAlgorithmException unused) {
                throw new IllegalStateException("No TLS provider", e);
            }
        }
    }

    @Override // com.huawei.hms.network.embedded.ma
    @Nullable
    public String b(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket) ? Conscrypt.getApplicationProtocol(sSLSocket) : super.b(sSLSocket);
    }

    @Override // com.huawei.hms.network.embedded.ma
    public void a(SSLSocket sSLSocket, String str, List<r7> list) throws IOException {
        if (!Conscrypt.isConscrypt(sSLSocket)) {
            super.a(sSLSocket, str, list);
            return;
        }
        if (str != null) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            Conscrypt.setHostname(sSLSocket, str);
        }
        Conscrypt.setApplicationProtocols(sSLSocket, (String[]) ma.a(list).toArray(new String[0]));
    }

    private Provider j() {
        return Conscrypt.newProviderBuilder().provideTrustManager().build();
    }

    public static ia i() {
        try {
            Class.forName("org.conscrypt.Conscrypt");
            if (Conscrypt.isAvailable()) {
                return new ia();
            }
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}

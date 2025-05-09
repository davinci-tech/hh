package com.huawei.hms.network.embedded;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes9.dex */
public abstract class qa {
    public abstract List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException;

    public static qa a(X509Certificate... x509CertificateArr) {
        return new oa(new pa(x509CertificateArr));
    }

    public static qa a(X509TrustManager x509TrustManager) {
        return ma.f().a(x509TrustManager);
    }
}

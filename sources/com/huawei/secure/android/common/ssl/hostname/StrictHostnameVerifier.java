package com.huawei.secure.android.common.ssl.hostname;

import com.huawei.secure.android.common.ssl.util.d;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* loaded from: classes6.dex */
public class StrictHostnameVerifier implements HostnameVerifier {
    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            X509Certificate x509Certificate = (X509Certificate) sSLSession.getPeerCertificates()[0];
            d.c("", "verify: certificate is : " + x509Certificate.getSubjectDN().getName());
            b.a(str, x509Certificate, true);
            return true;
        } catch (SSLException e) {
            d.b("", "SSLException : " + e.getMessage());
            return false;
        }
    }
}

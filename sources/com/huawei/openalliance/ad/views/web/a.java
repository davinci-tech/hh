package com.huawei.openalliance.ad.views.web;

import android.net.http.SslCertificate;
import com.huawei.openalliance.ad.ho;
import java.io.ByteArrayInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;

/* loaded from: classes5.dex */
public class a {
    public static boolean a(X509Certificate[] x509CertificateArr) {
        Date date = new Date();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            try {
                x509Certificate.checkValidity(date);
            } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                ho.d("CertificateChainVerify", "verifyCertificateDate: exception : " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        StringBuilder sb;
        String message;
        String sb2;
        if (x509Certificate != null && x509Certificate2 != null) {
            try {
                x509Certificate2.verify(x509Certificate.getPublicKey());
            } catch (InvalidKeyException e) {
                sb = new StringBuilder("verify: publickey InvalidKeyException");
                message = e.getMessage();
                sb.append(message);
                sb2 = sb.toString();
                ho.d("CertificateChainVerify", sb2);
                return false;
            } catch (NoSuchAlgorithmException e2) {
                sb = new StringBuilder("verify: publickey NoSuchAlgorithmException");
                message = e2.getMessage();
                sb.append(message);
                sb2 = sb.toString();
                ho.d("CertificateChainVerify", sb2);
                return false;
            } catch (NoSuchProviderException e3) {
                sb = new StringBuilder("verify: publickey NoSuchProviderException");
                message = e3.getMessage();
                sb.append(message);
                sb2 = sb.toString();
                ho.d("CertificateChainVerify", sb2);
                return false;
            } catch (SignatureException e4) {
                sb = new StringBuilder("verify: publickey SignatureException");
                message = e4.getMessage();
                sb.append(message);
                sb2 = sb.toString();
                ho.d("CertificateChainVerify", sb2);
                return false;
            } catch (CertificateException e5) {
                sb = new StringBuilder("verify: publickey CertificateException");
                message = e5.getMessage();
                sb.append(message);
                sb2 = sb.toString();
                ho.d("CertificateChainVerify", sb2);
                return false;
            }
            if (a(new X509Certificate[]{x509Certificate, x509Certificate2})) {
                Principal subjectDN = x509Certificate.getSubjectDN();
                Principal issuerDN = x509Certificate2.getIssuerDN();
                if (subjectDN.equals(issuerDN)) {
                    return true;
                }
                if (ho.a()) {
                    ho.d("CertificateChainVerify", "verify: first principal is : " + subjectDN.getName());
                    ho.d("CertificateChainVerify", "verify: seconde principal is : " + issuerDN.getName());
                }
                return false;
            }
            sb2 = "verify: date not right";
            ho.d("CertificateChainVerify", sb2);
        }
        return false;
    }

    public static X509Certificate a(SslCertificate sslCertificate) {
        try {
            byte[] byteArray = SslCertificate.saveState(sslCertificate).getByteArray("x509-certificate");
            if (byteArray == null) {
                return null;
            }
            try {
                return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(byteArray));
            } catch (CertificateException unused) {
                ho.d("CertificateChainVerify", "generate cert exception");
                return null;
            }
        } catch (Throwable th) {
            ho.c("CertificateChainVerify", "convertSSL err: %s", th.getClass().getSimpleName());
            return null;
        }
    }
}

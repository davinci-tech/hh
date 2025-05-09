package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* loaded from: classes9.dex */
public final class i7 {

    /* renamed from: a, reason: collision with root package name */
    public final y7 f5308a;
    public final x6 b;
    public final List<Certificate> c;
    public final List<Certificate> d;

    public String toString() {
        return "Handshake{tlsVersion=" + this.f5308a + " cipherSuite=" + this.b + " peerCertificates=" + a(this.c) + " localCertificates=" + a(this.d) + '}';
    }

    public int hashCode() {
        return ((((((this.f5308a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public y7 f() {
        return this.f5308a;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof i7)) {
            return false;
        }
        i7 i7Var = (i7) obj;
        return this.f5308a.equals(i7Var.f5308a) && this.b.equals(i7Var.b) && this.c.equals(i7Var.c) && this.d.equals(i7Var.d);
    }

    @Nullable
    public Principal e() {
        if (this.c.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.c.get(0)).getSubjectX500Principal();
    }

    public List<Certificate> d() {
        return this.c;
    }

    @Nullable
    public Principal c() {
        if (this.d.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.d.get(0)).getSubjectX500Principal();
    }

    public List<Certificate> b() {
        return this.d;
    }

    public x6 a() {
        return this.b;
    }

    private List<String> a(List<Certificate> list) {
        ArrayList arrayList = new ArrayList();
        for (Certificate certificate : list) {
            arrayList.add(certificate instanceof X509Certificate ? String.valueOf(((X509Certificate) certificate).getSubjectDN()) : certificate.getType());
        }
        return arrayList;
    }

    public static i7 a(SSLSession sSLSession) throws IOException {
        Certificate[] certificateArr;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if ("SSL_NULL_WITH_NULL_NULL".equals(cipherSuite)) {
            throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
        }
        x6 a2 = x6.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        y7 a3 = y7.a(protocol);
        try {
            certificateArr = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            certificateArr = null;
        }
        List a4 = certificateArr != null ? f8.a(certificateArr) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new i7(a3, a2, a4, localCertificates != null ? f8.a(localCertificates) : Collections.emptyList());
    }

    public static i7 a(y7 y7Var, x6 x6Var, List<Certificate> list, List<Certificate> list2) {
        if (y7Var == null) {
            throw new NullPointerException("tlsVersion == null");
        }
        if (x6Var != null) {
            return new i7(y7Var, x6Var, f8.a(list), f8.a(list2));
        }
        throw new NullPointerException("cipherSuite == null");
    }

    public i7(y7 y7Var, x6 x6Var, List<Certificate> list, List<Certificate> list2) {
        this.f5308a = y7Var;
        this.b = x6Var;
        this.c = list;
        this.d = list2;
    }
}

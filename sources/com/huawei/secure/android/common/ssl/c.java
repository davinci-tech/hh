package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import com.huawei.secure.android.common.ssl.util.d;
import com.huawei.secure.android.common.ssl.util.g;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes9.dex */
public class c implements X509TrustManager {
    private static final String c = "WebViewX509TrustManger";

    /* renamed from: a, reason: collision with root package name */
    private X509Certificate f8628a;
    private List<X509TrustManager> b = new ArrayList();

    public c(Context context) {
        if (context == null) {
            throw new NullPointerException("WebViewX509TrustManger context is null");
        }
        ContextUtil.setContext(context);
        X509Certificate b = new g(context).b();
        this.f8628a = b;
        if (b == null) {
            throw new NullPointerException("WebViewX509TrustManger cannot get cbg root ca");
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        d.c(c, "checkClientTrusted");
        if (this.b.isEmpty()) {
            throw new CertificateException("checkClientTrusted CertificateException");
        }
        this.b.get(0).checkClientTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        d.c(c, "checkServerTrusted");
        boolean z = false;
        for (int i = 0; i < x509CertificateArr.length; i++) {
            d.a(c, "checkServerTrusted " + i + " : " + x509CertificateArr[i].getIssuerDN().getName());
        }
        X509Certificate[] x509CertificateArr2 = new X509Certificate[x509CertificateArr.length];
        for (int i2 = 0; i2 < x509CertificateArr.length; i2++) {
            x509CertificateArr2[i2] = x509CertificateArr[(x509CertificateArr.length - 1) - i2];
        }
        CertificateException e = new CertificateException("CBG root CA CertificateException");
        try {
            z = com.huawei.secure.android.common.ssl.util.b.a(this.f8628a, x509CertificateArr2);
        } catch (InvalidKeyException e2) {
            d.b(c, "checkServerTrusted InvalidKeyException: " + e2.getMessage());
        } catch (NoSuchAlgorithmException e3) {
            d.b(c, "checkServerTrusted NoSuchAlgorithmException: " + e3.getMessage());
        } catch (NoSuchProviderException e4) {
            d.b(c, "checkServerTrusted NoSuchProviderException: " + e4.getMessage());
        } catch (SignatureException e5) {
            d.b(c, "checkServerTrusted SignatureException: " + e5.getMessage());
        } catch (CertificateException e6) {
            e = e6;
            d.b(c, "checkServerTrusted CertificateException: " + e.getMessage());
        }
        if (!z) {
            throw e;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<X509TrustManager> it = this.b.iterator();
            while (it.hasNext()) {
                arrayList.addAll(Arrays.asList(it.next().getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception e) {
            d.b(c, "getAcceptedIssuers exception : " + e.getMessage());
            return new X509Certificate[0];
        }
    }
}

package com.huawei.openalliance.ad.views.web;

import android.content.Context;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import com.huawei.openalliance.ad.utils.cy;
import com.huawei.openalliance.ad.views.web.g;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes5.dex */
public class f {
    private static X509Certificate[] a(X509TrustManager x509TrustManager) {
        return x509TrustManager == null ? new X509Certificate[0] : x509TrustManager.getAcceptedIssuers();
    }

    public static void a(SslErrorHandler sslErrorHandler, SslError sslError, String str, Context context, g.a aVar) {
        X509Certificate a2 = a.a(sslError.getCertificate());
        Iterator<X509TrustManager> it = a(context).iterator();
        while (it.hasNext()) {
            for (X509Certificate x509Certificate : a(it.next())) {
                if (a.a(x509Certificate, a2)) {
                    Log.e("WebViewSSLCheck", "checkServerCertificateNew: proceed");
                    if (aVar != null) {
                        aVar.a(context, str);
                        return;
                    } else {
                        sslErrorHandler.proceed();
                        return;
                    }
                }
            }
        }
        Log.e("WebViewSSLCheck", "checkServerCertificateNew: cancel");
        if (aVar != null) {
            aVar.b(context, str);
        } else {
            sslErrorHandler.cancel();
        }
    }

    private static List<X509TrustManager> a(Context context) {
        ArrayList arrayList = new ArrayList();
        InputStream inputStream = null;
        try {
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
                KeyStore keyStore = KeyStore.getInstance(com.huawei.secure.android.common.ssl.util.g.e);
                inputStream = context.getAssets().open("hiadincas.bks");
                inputStream.reset();
                keyStore.load(inputStream, "".toCharArray());
                trustManagerFactory.init(keyStore);
                for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                    if (trustManager instanceof X509TrustManager) {
                        arrayList.add((X509TrustManager) trustManager);
                    }
                }
            } finally {
                cy.a((Closeable) null);
            }
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            Log.e("WebViewSSLCheck", "loadBksCA: exception : " + e.getMessage());
        }
        return arrayList;
    }
}

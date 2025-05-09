package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.BksUtil;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import com.huawei.secure.android.common.ssl.util.d;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

/* loaded from: classes6.dex */
public class SecureX509SingleInstance {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8621a = "SecureX509SingleInstance";
    private static volatile SecureX509TrustManager b;

    private SecureX509SingleInstance() {
    }

    public static SecureX509TrustManager getInstance(Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        InputStream inputStream;
        long currentTimeMillis = System.currentTimeMillis();
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        ContextUtil.setContext(context);
        if (b == null) {
            synchronized (SecureX509SingleInstance.class) {
                if (b == null) {
                    try {
                        inputStream = BksUtil.getFilesBksIS(context);
                    } catch (RuntimeException unused) {
                        d.b(f8621a, "get files bks error");
                        inputStream = null;
                    }
                    if (inputStream == null) {
                        d.c(f8621a, "get assets bks");
                        inputStream = context.getAssets().open("hmsrootcas.bks");
                    } else {
                        d.c(f8621a, "get files bks");
                    }
                    b = new SecureX509TrustManager(inputStream, "");
                }
            }
        }
        d.a(f8621a, "SecureX509TrustManager getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return b;
    }

    @Deprecated
    public static void updateBks(InputStream inputStream) {
        String str = f8621a;
        d.c(str, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && b != null) {
            b = new SecureX509TrustManager(inputStream, "");
            SecureSSLSocketFactory.a(b);
            SecureApacheSSLSocketFactory.a(b);
        }
        d.c(str, "SecureX509TrustManager update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    public static void updateBks(InputStream inputStream, SecureRandom secureRandom) {
        String str = f8621a;
        d.c(str, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && b != null) {
            b = new SecureX509TrustManager(inputStream, "");
            SecureSSLSocketFactory.a(b, secureRandom);
            SecureApacheSSLSocketFactory.a(b, secureRandom);
        }
        d.c(str, "SecureX509TrustManager update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }
}

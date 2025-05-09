package com.huawei.agconnect.credential.obs;

import android.content.Context;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.https.OKHttpBuilder;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/* loaded from: classes2.dex */
public class ae {

    /* renamed from: a, reason: collision with root package name */
    static final long f1742a = 5000;
    private static final String b = "ClientImpl";
    private OKHttpBuilder c;

    public OkHttpClient a(long j, TimeUnit timeUnit) {
        return this.c.buildWithTimeOut(j, timeUnit);
    }

    public OkHttpClient a() {
        return this.c.build();
    }

    public ae a(Authenticator authenticator) {
        if (authenticator != null) {
            this.c.authenticator(authenticator);
        }
        return this;
    }

    private OKHttpBuilder a(Context context) {
        OKHttpBuilder oKHttpBuilder;
        try {
            try {
                try {
                    try {
                        this.c = new OKHttpBuilder().sslSocketFactory(SecureSSLSocketFactoryNew.getInstance(context), new SecureX509TrustManager(context));
                    } catch (IllegalAccessException e) {
                        Logger.e(b, "IllegalAccessException", e);
                        oKHttpBuilder = new OKHttpBuilder();
                        this.c = oKHttpBuilder;
                        return this.c;
                    } catch (KeyManagementException e2) {
                        Logger.e(b, "KeyManagementException", e2);
                        oKHttpBuilder = new OKHttpBuilder();
                        this.c = oKHttpBuilder;
                        return this.c;
                    }
                } catch (KeyStoreException e3) {
                    Logger.e(b, "KeyStoreException", e3);
                    oKHttpBuilder = new OKHttpBuilder();
                    this.c = oKHttpBuilder;
                    return this.c;
                } catch (Throwable th) {
                    Logger.e(b, "Throwable", th);
                    oKHttpBuilder = new OKHttpBuilder();
                    this.c = oKHttpBuilder;
                    return this.c;
                }
            } catch (IOException e4) {
                Logger.e(b, "IOException", e4);
                oKHttpBuilder = new OKHttpBuilder();
                this.c = oKHttpBuilder;
                return this.c;
            } catch (NoSuchAlgorithmException e5) {
                Logger.e(b, "NoSuchAlgorithmException", e5);
                oKHttpBuilder = new OKHttpBuilder();
                this.c = oKHttpBuilder;
                return this.c;
            } catch (CertificateException e6) {
                Logger.e(b, "CertificateException", e6);
                oKHttpBuilder = new OKHttpBuilder();
                this.c = oKHttpBuilder;
                return this.c;
            }
            return this.c;
        } catch (Throwable th2) {
            this.c = new OKHttpBuilder();
            throw th2;
        }
    }

    public ae(Context context, List<Interceptor> list, boolean z) {
        this.c = a(context);
        if (list != null && list.size() > 0) {
            Iterator<Interceptor> it = list.iterator();
            while (it.hasNext()) {
                this.c.addInterceptor(it.next());
            }
        }
        if (z) {
            this.c.connectTimeout(5000L).readTimeout(5000L).writeTimeout(5000L);
        }
    }
}

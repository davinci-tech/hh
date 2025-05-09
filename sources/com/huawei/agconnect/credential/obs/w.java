package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.secure.android.common.ssl.SSLUtil;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes8.dex */
public class w extends Platform {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1781a = "OkhttpAndroidPlatform";
    private final c b = c.a();
    private final Context c;

    @Override // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    @Override // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    @Override // okhttp3.internal.platform.Platform
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        try {
            return new SecureX509TrustManager(this.c);
        } catch (Throwable unused) {
            Log.e("TAG", "new SecureX509TrustManager error");
            return null;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void logCloseableLeak(String str, Object obj) {
        if (this.b.a(obj)) {
            return;
        }
        log(5, str, (Throwable) null);
    }

    public void log(int i, String str, Throwable th) {
        super.log(i, str, th);
    }

    @Override // okhttp3.internal.platform.Platform
    public boolean isCleartextTrafficPermitted(String str) {
        return super.isCleartextTrafficPermitted(str);
    }

    @Override // okhttp3.internal.platform.Platform
    public Object getStackTraceForCloseable(String str) {
        return this.b.a(str);
    }

    public SSLContext getSSLContext() {
        try {
            return SSLUtil.setSSLContext();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("get TLS provider error", e);
        }
    }

    static final class b implements TrustRootIndex {

        /* renamed from: a, reason: collision with root package name */
        private final X509TrustManager f1783a;
        private final Method b;

        public int hashCode() {
            return (this.b.hashCode() * 31) + this.f1783a.hashCode();
        }

        @Override // okhttp3.internal.tls.TrustRootIndex
        public X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.b.invoke(this.f1783a, x509Certificate);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
            } catch (Throwable unused) {
            }
            return null;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f1783a.equals(bVar.f1783a) && this.b.equals(bVar.b);
        }

        b(X509TrustManager x509TrustManager, Method method) {
            this.b = method;
            this.f1783a = x509TrustManager;
        }
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        private final Method f1784a;
        private final Method b;
        private final Method c;

        boolean a(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception e) {
                Logger.e(w.f1781a, e.getMessage());
                return false;
            }
        }

        Object a(String str) {
            Method method = this.f1784a;
            if (method != null) {
                try {
                    Object invoke = method.invoke(null, new Object[0]);
                    this.b.invoke(invoke, str);
                    return invoke;
                } catch (Exception e) {
                    Logger.e(w.f1781a, e.getMessage());
                }
            }
            return null;
        }

        static c a() {
            Method method;
            Method method2;
            Method method3;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                method = cls.getMethod("get", new Class[0]);
                method3 = cls.getMethod(JsbMapKeyNames.H5_TEXT_DOWNLOAD_OPEN, String.class);
                method2 = cls.getMethod("warnIfOpen", new Class[0]);
            } catch (Exception unused) {
                method = null;
                method2 = null;
                method3 = null;
            }
            return new c(method, method3, method2);
        }

        c(Method method, Method method2, Method method3) {
            this.f1784a = method;
            this.b = method2;
            this.c = method3;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) {
        socket.connect(inetSocketAddress, i);
    }

    static final class a extends CertificateChainCleaner {

        /* renamed from: a, reason: collision with root package name */
        private final Object f1782a;
        private final Method b;

        public int hashCode() {
            return 0;
        }

        public boolean equals(Object obj) {
            return obj instanceof a;
        }

        @Override // okhttp3.internal.tls.CertificateChainCleaner
        public List<Certificate> clean(List<Certificate> list, String str) {
            try {
                return (List) this.b.invoke(this.f1782a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), JceNames.RSA, str);
            } catch (Throwable unused) {
                return null;
            }
        }

        a(Object obj, Method method) {
            this.f1782a = obj;
            this.b = method;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public TrustRootIndex buildTrustRootIndex(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.buildTrustRootIndex(x509TrustManager);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new a(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.buildCertificateChainCleaner(x509TrustManager);
        }
    }

    public w(Context context) {
        this.c = context;
    }
}

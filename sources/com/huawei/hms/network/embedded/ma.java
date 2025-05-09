package com.huawei.hms.network.embedded;

import com.huawei.openalliance.ad.constant.ParamConstants;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes9.dex */
public class ma {
    public static final int b = 4;
    public static final int c = 5;

    /* renamed from: a, reason: collision with root package name */
    public static final ma f5381a = e();
    public static final Logger d = Logger.getLogger(q7.class.getName());

    public void a(SSLSocket sSLSocket) {
    }

    public void a(SSLSocket sSLSocket, @Nullable String str, List<r7> list) throws IOException {
    }

    @Nullable
    public String b(SSLSocket sSLSocket) {
        return null;
    }

    public void b(SSLSocketFactory sSLSocketFactory) {
    }

    public boolean b(String str) {
        return true;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    @Nullable
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        try {
            Object a2 = a(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), ParamConstants.Param.CONTEXT);
            if (a2 == null) {
                return null;
            }
            return (X509TrustManager) a(a2, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public SSLContext b() {
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No TLS provider", e);
        }
    }

    public ta b(X509TrustManager x509TrustManager) {
        return new pa(x509TrustManager.getAcceptedIssuers());
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        a(5, str, (Throwable) obj);
    }

    public void a(int i, String str, @Nullable Throwable th) {
        d.log(i == 5 ? Level.WARNING : Level.INFO, str, e8.a(th));
    }

    public String a() {
        return "OkHttp";
    }

    @Nullable
    public Object a(String str) {
        if (d.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public qa a(X509TrustManager x509TrustManager) {
        return new oa(b(x509TrustManager));
    }

    public qa a(SSLSocketFactory sSLSocketFactory) {
        X509TrustManager c2 = c(sSLSocketFactory);
        if (c2 != null) {
            return a(c2);
        }
        throw new IllegalStateException("Unable to extract the trust manager on " + f() + ", sslSocketFactory is " + sSLSocketFactory.getClass());
    }

    public static boolean h() {
        if ("conscrypt".equals(f8.a("okhttp.platform", (String) null))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    public static boolean g() {
        return "Dalvik".equals(System.getProperty("java.vm.name"));
    }

    public static ma f() {
        return f5381a;
    }

    public static ma e() {
        return g() ? c() : d();
    }

    public static ma d() {
        ia i;
        if (h() && (i = ia.i()) != null) {
            return i;
        }
        ka i2 = ka.i();
        if (i2 != null) {
            return i2;
        }
        ma i3 = ja.i();
        return i3 != null ? i3 : new ma();
    }

    public static ma c() {
        ma i = fa.i();
        if (i != null) {
            return i;
        }
        ma i2 = ga.i();
        if (i2 != null) {
            return i2;
        }
        ma i3 = ha.i();
        if (i3 != null) {
            return i3;
        }
        throw new NullPointerException("No platform found on Android");
    }

    public static byte[] b(List<r7> list) {
        bb bbVar = new bb();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            r7 r7Var = list.get(i);
            if (r7Var != r7.HTTP_1_0) {
                bbVar.writeByte(r7Var.toString().length());
                bbVar.a(r7Var.toString());
            }
        }
        return bbVar.q();
    }

    public static List<String> a(List<r7> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            r7 r7Var = list.get(i);
            if (r7Var != r7.HTTP_1_0) {
                arrayList.add(r7Var.toString());
            }
        }
        return arrayList;
    }

    @Nullable
    public static <T> T a(Object obj, Class<T> cls, String str) {
        Object a2;
        for (Class<?> cls2 = obj.getClass(); cls2 != Object.class; cls2 = cls2.getSuperclass()) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (cls.isInstance(obj2)) {
                    return cls.cast(obj2);
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (NoSuchFieldException unused2) {
            }
        }
        if (str.equals("delegate") || (a2 = a(obj, (Class<Object>) Object.class, "delegate")) == null) {
            return null;
        }
        return (T) a(a2, cls, str);
    }
}

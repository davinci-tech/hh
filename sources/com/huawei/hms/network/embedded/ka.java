package com.huawei.hms.network.embedded;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes9.dex */
public final class ka extends ma {
    public final Method e;
    public final Method f;

    @Override // com.huawei.hms.network.embedded.ma
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    @Override // com.huawei.hms.network.embedded.ma
    @Nullable
    public String b(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f.invoke(sSLSocket, new Object[0]);
            if (str != null) {
                if (!str.equals("")) {
                    return str;
                }
            }
            return null;
        } catch (IllegalAccessException e) {
            throw new AssertionError("failed to get ALPN selected protocol", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof UnsupportedOperationException) {
                return null;
            }
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        }
    }

    @Override // com.huawei.hms.network.embedded.ma
    public void a(SSLSocket sSLSocket, String str, List<r7> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> a2 = ma.a(list);
            this.e.invoke(sSLParameters, a2.toArray(new String[a2.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError("failed to set SSL parameters", e);
        }
    }

    public static ka i() {
        try {
            return new ka(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public ka(Method method, Method method2) {
        this.e = method;
        this.f = method2;
    }
}

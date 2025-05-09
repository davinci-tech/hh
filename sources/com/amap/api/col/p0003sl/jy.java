package com.amap.api.col.p0003sl;

import android.content.Context;
import android.net.SSLSessionCache;
import android.os.Build;
import com.amap.api.col.p0003sl.ho;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public final class jy extends SSLSocketFactory {

    /* renamed from: a, reason: collision with root package name */
    private SSLSocketFactory f1244a;
    private Context b;
    private SSLContext c;

    public jy(Context context, SSLContext sSLContext) {
        if (context != null) {
            try {
                this.b = context.getApplicationContext();
            } catch (Throwable th) {
                try {
                    iv.c(th, "myssl", "<init>");
                    try {
                        if (this.c == null) {
                            this.c = SSLContext.getDefault();
                        }
                    } catch (Throwable th2) {
                        iv.c(th2, "myssl", "<init2>");
                    }
                    try {
                        if (this.f1244a == null) {
                            this.f1244a = (SSLSocketFactory) SSLSocketFactory.getDefault();
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        iv.c(th3, "myssl", "<init3>");
                        return;
                    }
                } catch (Throwable th4) {
                    try {
                        if (this.c == null) {
                            this.c = SSLContext.getDefault();
                        }
                    } catch (Throwable th5) {
                        iv.c(th5, "myssl", "<init2>");
                    }
                    try {
                        if (this.f1244a == null) {
                            this.f1244a = (SSLSocketFactory) SSLSocketFactory.getDefault();
                            throw th4;
                        }
                        throw th4;
                    } catch (Throwable th6) {
                        iv.c(th6, "myssl", "<init3>");
                        throw th4;
                    }
                }
            }
        }
        this.c = sSLContext;
        if (sSLContext != null) {
            this.f1244a = sSLContext.getSocketFactory();
        }
        try {
            if (this.c == null) {
                this.c = SSLContext.getDefault();
            }
        } catch (Throwable th7) {
            iv.c(th7, "myssl", "<init2>");
        }
        try {
            if (this.f1244a == null) {
                this.f1244a = (SSLSocketFactory) SSLSocketFactory.getDefault();
            }
        } catch (Throwable th8) {
            iv.c(th8, "myssl", "<init3>");
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory != null) {
                return sSLSocketFactory.getDefaultCipherSuites();
            }
        } catch (Throwable th) {
            iv.c(th, "myssl", "gdcs");
        }
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory != null) {
                return sSLSocketFactory.getSupportedCipherSuites();
            }
        } catch (Throwable th) {
            iv.c(th, "myssl", "gscs");
        }
        return new String[0];
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() throws IOException {
        boolean z;
        IOException iOException;
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory == null) {
                return null;
            }
            Socket a2 = a(sSLSocketFactory.createSocket());
            b(a2);
            return a2;
        } finally {
            if (!z) {
            }
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        boolean z2;
        IOException iOException;
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory == null) {
                return null;
            }
            Socket a2 = a(sSLSocketFactory.createSocket(socket, str, i, z));
            b(a2);
            return a2;
        } finally {
            if (!z2) {
            }
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory == null) {
                return null;
            }
            Socket a2 = a(sSLSocketFactory.createSocket(str, i));
            b(a2);
            return a2;
        } catch (Throwable th) {
            iv.c(th, "myssl", "cs3");
            if (th instanceof UnknownHostException) {
                throw th;
            }
            if (th instanceof IOException) {
                throw th;
            }
            return null;
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory == null) {
                return null;
            }
            Socket a2 = a(sSLSocketFactory.createSocket(str, i, inetAddress, i2));
            b(a2);
            return a2;
        } catch (Throwable th) {
            iv.c(th, "myssl", "cs4");
            if (th instanceof UnknownHostException) {
                throw th;
            }
            if (th instanceof IOException) {
                throw th;
            }
            return null;
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        boolean z;
        IOException iOException;
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory == null) {
                return null;
            }
            Socket a2 = a(sSLSocketFactory.createSocket(inetAddress, i));
            b(a2);
            return a2;
        } finally {
            if (!z) {
            }
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        boolean z;
        IOException iOException;
        try {
            SSLSocketFactory sSLSocketFactory = this.f1244a;
            if (sSLSocketFactory == null) {
                return null;
            }
            Socket a2 = a(sSLSocketFactory.createSocket(inetAddress, i, inetAddress2, i2));
            b(a2);
            return a2;
        } finally {
            if (!z) {
            }
        }
    }

    private static Socket a(Socket socket) {
        try {
            if (ho.f.b && (socket instanceof SSLSocket)) {
                ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.2"});
            }
        } catch (Throwable th) {
            iv.c(th, "myssl", "stlv2");
        }
        return socket;
    }

    private static void b(Socket socket) {
        int i;
        if (ho.f.c && ho.f.e && (socket instanceof SSLSocket)) {
            if (ho.f.f > ho.f.d) {
                i = ho.f.d;
            } else {
                i = ho.f.f;
            }
            if (i <= 17 || Build.VERSION.SDK_INT <= i) {
                try {
                    socket.getClass().getMethod(ia.c("Cc2V0VXNlU2Vzc2lvblRpY2tldHM"), Boolean.TYPE).invoke(socket, Boolean.TRUE);
                } catch (Throwable th) {
                    iv.c(th, "myssl", "sust");
                }
            }
        }
    }

    public final void a() {
        if (!ho.f.c || this.b == null || this.c == null) {
            return;
        }
        int i = ho.f.d;
        if (i <= 17 || Build.VERSION.SDK_INT <= i) {
            SSLSessionCache sSLSessionCache = new SSLSessionCache(this.b);
            if (Build.VERSION.SDK_INT >= 28) {
                a(sSLSessionCache);
                return;
            }
            try {
                sSLSessionCache.getClass().getMethod(ia.c("MaW5zdGFsbA"), SSLSessionCache.class, SSLContext.class).invoke(sSLSessionCache, sSLSessionCache, this.c);
            } catch (Throwable th) {
                iv.c(th, "myssl", "isc1");
                a(sSLSessionCache);
            }
        }
    }

    private void a(SSLSessionCache sSLSessionCache) {
        SSLContext sSLContext = this.c;
        if (sSLContext == null) {
            return;
        }
        try {
            SSLSessionContext clientSessionContext = sSLContext.getClientSessionContext();
            Field declaredField = sSLSessionCache.getClass().getDeclaredField(ia.c("UbVNlc3Npb25DYWNoZQ"));
            declaredField.setAccessible(true);
            Object obj = declaredField.get(sSLSessionCache);
            Method[] methods = clientSessionContext.getClass().getMethods();
            String c = ia.c("Yc2V0UGVyc2lzdGVudENhY2hl");
            for (Method method : methods) {
                if (method.getName().equals(c)) {
                    method.invoke(clientSessionContext, obj);
                    return;
                }
            }
        } catch (Throwable th) {
            iv.c(th, "myssl", "isc2");
        }
    }
}

package com.huawei.openalliance.ad.net.http;

import android.os.Build;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.co;
import com.huawei.openalliance.ad.utils.cp;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes5.dex */
public final class m extends SSLSocketFactory {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7310a = "m";
    private static SSLSocketFactory c;
    private static final byte[] d = new byte[0];
    private final SSLContext b;
    private boolean e;

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        Socket createSocket = this.b.getSocketFactory().createSocket(socket, str, i, z);
        a(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        Socket createSocket = this.b.getSocketFactory().createSocket(inetAddress, i, inetAddress2, i2);
        a(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) {
        Socket createSocket = this.b.getSocketFactory().createSocket(inetAddress, i);
        a(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        Socket createSocket = this.b.getSocketFactory().createSocket(str, i, inetAddress, i2);
        a(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) {
        Socket createSocket = this.b.getSocketFactory().createSocket(str, i);
        a(createSocket);
        return createSocket;
    }

    private static void b(SSLSocket sSLSocket) {
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        if (enabledCipherSuites == null || enabledCipherSuites.length == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : enabledCipherSuites) {
            if (!cp.a(str)) {
                arrayList.add(str);
            }
        }
        sSLSocket.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    private void a(SSLSocket sSLSocket) {
        if (sSLSocket != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                sSLSocket.setEnabledProtocols(new String[]{"TLSv1.3", "TLSv1.2"});
            } else if (Build.VERSION.SDK_INT < 29) {
                sSLSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            }
        }
    }

    private void a(Socket socket) {
        if ((socket instanceof SSLSocket) && this.e) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            a(sSLSocket);
            b(sSLSocket);
        }
    }

    public static SSLSocketFactory a(boolean z) {
        String str;
        String str2;
        synchronized (d) {
            try {
                try {
                    try {
                        try {
                            try {
                                if (c == null) {
                                    c = new m(z);
                                }
                                return c;
                            } catch (Exception unused) {
                                str = f7310a;
                                str2 = "Failed to new SSLSocketFactory instance. Exception";
                                ho.c(str, str2);
                                return null;
                            }
                        } catch (GeneralSecurityException unused2) {
                            str = f7310a;
                            str2 = "Failed to new SSLSocketFactory instance. GeneralSecurityException";
                            ho.c(str, str2);
                            return null;
                        }
                    } catch (KeyStoreException unused3) {
                        str = f7310a;
                        str2 = "Failed to new SSLSocketFactory instance. KeyStoreException";
                        ho.c(str, str2);
                        return null;
                    }
                } catch (KeyManagementException unused4) {
                    str = f7310a;
                    str2 = "Failed to new SSLSocketFactory instance. KeyManagementException";
                    ho.c(str, str2);
                    return null;
                } catch (NoSuchAlgorithmException unused5) {
                    str = f7310a;
                    str2 = "Failed to new SSLSocketFactory instance. NoSuchAlgorithmException";
                    ho.c(str, str2);
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private m(boolean z) {
        SecureRandom secureRandom;
        this.e = true;
        this.b = z ? co.a() : SSLContext.getInstance("TLS");
        this.e = z;
        TrustManager[] a2 = HttpsConfig.a();
        a2 = a2.length == 0 ? null : a2;
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (Exception unused) {
            secureRandom = new SecureRandom();
        }
        this.b.init(null, a2, secureRandom);
    }
}

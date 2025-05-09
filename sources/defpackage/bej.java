package defpackage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes3.dex */
public class bej extends SSLSocketFactory {
    private SSLSocketFactory c;

    public bej() {
        bes.e("HuaweiOpenSSLSocketFactory", "init HuaweiOpenSSLSocketFactory");
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
            sSLContext.init(null, new TrustManager[]{new bep()}, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            if (socketFactory == null) {
                bes.d("HuaweiOpenSSLSocketFactory", "socketFactory is null error!");
            }
            this.c = socketFactory;
        } catch (KeyManagementException e) {
            bes.d("HuaweiOpenSSLSocketFactory", "sslContext init failed." + e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            bes.d("HuaweiOpenSSLSocketFactory", "get SSLContext instance failed. " + e2.getMessage());
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        Socket createSocket = this.c.createSocket();
        b(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        Socket createSocket = this.c.createSocket(inetAddress, i, inetAddress2, i2);
        b(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        Socket createSocket = this.c.createSocket(inetAddress, i);
        b(createSocket);
        return createSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket createSocket = this.c.createSocket(socket, str, i, z);
        b(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        Socket createSocket = this.c.createSocket(str, i, inetAddress, i2);
        b(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        Socket createSocket = this.c.createSocket(str, i);
        b(createSocket);
        return createSocket;
    }

    private void b(Socket socket) {
        bes.e("HuaweiOpenSSLSocketFactory", "enter setEnableSafeCipherSuites");
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
            if (enabledCipherSuites == null || enabledCipherSuites.length == 0) {
                bes.d("HuaweiOpenSSLSocketFactory", "Current enabled cipherSuites is invalid!");
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (String str : enabledCipherSuites) {
                if (bek.f345a.contains(str)) {
                    arrayList.add(str);
                }
            }
            sSLSocket.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
            return;
        }
        bes.d("HuaweiOpenSSLSocketFactory", "socket is not instanceof SSLSocket");
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.c.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.c.getSupportedCipherSuites();
    }
}

package defpackage;

import android.content.Context;
import com.huawei.wearengine.common.SecureX509TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Locale;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes8.dex */
public class ton extends SSLSocketFactory {
    private SSLContext d = SSLContext.getInstance("TLSv1.2");
    private Context e;
    private static final String[] c = {"TEA", "SHA0", "MD2", "MD4", "RIPEMD", "NULL", "RC4", "DES", "DESX", "DES40", "RC2", "MD5", "ANON", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV", "TLS_RSA"};

    /* renamed from: a, reason: collision with root package name */
    private static volatile ton f17281a = null;
    private static String[] b = null;

    private ton(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        this.e = context;
        this.d.init(null, new X509TrustManager[]{new SecureX509TrustManager(this.e)}, null);
    }

    public static ton b(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, KeyManagementException, IllegalArgumentException {
        if (f17281a == null) {
            synchronized (ton.class) {
                if (f17281a == null) {
                    f17281a = new ton(context);
                }
            }
        }
        return f17281a;
    }

    private static void e(SSLSocket sSLSocket) {
        if (sSLSocket == null) {
            return;
        }
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        ArrayList arrayList = new ArrayList();
        for (String str : enabledCipherSuites) {
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            int i = 0;
            while (true) {
                String[] strArr = c;
                if (i < strArr.length) {
                    if (upperCase.contains(strArr[i].toUpperCase(Locale.ENGLISH))) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    arrayList.add(str);
                    break;
                }
            }
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        b = strArr2;
        sSLSocket.setEnabledCipherSuites(strArr2);
    }

    private void e(Socket socket) {
        if (socket == null || !(socket instanceof SSLSocket)) {
            return;
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        b(sSLSocket);
        e(sSLSocket);
    }

    private void b(SSLSocket sSLSocket) {
        if (sSLSocket != null) {
            sSLSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        String[] strArr = b;
        return strArr != null ? (String[]) strArr.clone() : new String[0];
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        Socket createSocket = this.d.getSocketFactory().createSocket(str, i);
        e(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        if (inetAddress == null) {
            return this.d.getSocketFactory().createSocket();
        }
        return createSocket(inetAddress.getHostAddress(), i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return createSocket(str, i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        if (inetAddress == null) {
            return this.d.getSocketFactory().createSocket();
        }
        return createSocket(inetAddress.getHostAddress(), i);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket createSocket = this.d.getSocketFactory().createSocket(socket, str, i, z);
        e(createSocket);
        return createSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }
}

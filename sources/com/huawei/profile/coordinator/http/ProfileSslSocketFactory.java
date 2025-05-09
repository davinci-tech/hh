package com.huawei.profile.coordinator.http;

import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.utils.logger.DsLog;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes6.dex */
public class ProfileSslSocketFactory extends SSLSocketFactory {
    private static final String[] SECURITY_SUITES = {"TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256"};
    private static final String TAG = "ProfileSslSocketFactory";
    private static final String TLS_PROTOCOL = "TLSv1.2";
    private final SSLSocketFactory defaultFactory;

    private ProfileSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.defaultFactory = sSLSocketFactory;
    }

    public static SSLSocketFactory getSocketFactory() throws ProfileRequestException {
        try {
            SSLContext sSLContext = SSLContext.getInstance(TLS_PROTOCOL);
            sSLContext.init(null, null, null);
            return new ProfileSslSocketFactory(sSLContext.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            DsLog.et(TAG, "failed to create ssl socket factory, caused by " + e.getMessage(), new Object[0]);
            throw new ProfileRequestException(ProfileRequestExceptionType.TLS_ERROR, "failed to create ssl socket factory");
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.defaultFactory.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.defaultFactory.getSupportedCipherSuites();
    }

    private Socket setProtocolAndCipherSuites(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.setEnabledProtocols(new String[]{TLS_PROTOCOL});
            sSLSocket.setEnabledCipherSuites(filterCipherSuites(sSLSocket.getEnabledCipherSuites()));
        }
        return socket;
    }

    private static String[] filterCipherSuites(String[] strArr) {
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (isInSecuritySuites(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static boolean isInSecuritySuites(String str) {
        for (String str2 : SECURITY_SUITES) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return setProtocolAndCipherSuites(this.defaultFactory.createSocket(socket, str, i, z));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        return setProtocolAndCipherSuites(this.defaultFactory.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return setProtocolAndCipherSuites(this.defaultFactory.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return setProtocolAndCipherSuites(this.defaultFactory.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return setProtocolAndCipherSuites(this.defaultFactory.createSocket(inetAddress, i, inetAddress2, i2));
    }
}

package com.huawei.hms.network.embedded;

import android.net.ssl.SSLSockets;
import com.android.org.conscrypt.OpenSSLSocketImpl;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* loaded from: classes9.dex */
public class fa extends ha {
    @Override // com.huawei.hms.network.embedded.ha, com.huawei.hms.network.embedded.ma
    @Nullable
    public String b(SSLSocket sSLSocket) {
        String applicationProtocol = sSLSocket.getApplicationProtocol();
        if (applicationProtocol == null || applicationProtocol.isEmpty()) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // com.huawei.hms.network.embedded.ha, com.huawei.hms.network.embedded.ma
    public void a(SSLSocket sSLSocket, String str, List<r7> list) throws IOException {
        try {
            c(sSLSocket);
            a(sSLSocket, str);
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            sSLParameters.setApplicationProtocols((String[]) ma.a(list).toArray(new String[0]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalArgumentException e) {
            throw new IOException("Android internal error", e);
        }
    }

    @Nullable
    public static ma i() {
        if (!ma.g()) {
            return null;
        }
        try {
            if (ha.j() >= 29) {
                return new fa(Class.forName("com.android.org.conscrypt.SSLParametersImpl"));
            }
        } catch (ReflectiveOperationException unused) {
        }
        return null;
    }

    private void c(SSLSocket sSLSocket) {
        if (SSLSockets.isSupportedSocket(sSLSocket)) {
            SSLSockets.setUseSessionTickets(sSLSocket, true);
        }
    }

    private void a(SSLSocket sSLSocket, String str) {
        try {
            if (sSLSocket instanceof OpenSSLSocketImpl) {
                ((OpenSSLSocketImpl) sSLSocket).setHostname(str);
            }
        } catch (Throwable unused) {
            ma.f().a(5, "Android10Platform sethostname error", (Throwable) null);
        }
    }

    public fa(Class<?> cls) {
        super(cls, null, null, null, null, null);
    }
}

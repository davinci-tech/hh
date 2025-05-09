package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public abstract class g7 {
    public static final g7 NONE = new a();

    public class a extends g7 {
    }

    public interface b {
        g7 create(t6 t6Var);
    }

    public static /* synthetic */ g7 a(g7 g7Var, t6 t6Var) {
        return g7Var;
    }

    public void callEnd(t6 t6Var) {
    }

    public void callFailed(t6 t6Var, IOException iOException) {
    }

    public void callStart(t6 t6Var) {
    }

    public void connectEnd(t6 t6Var, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable r7 r7Var) {
    }

    public void connectFailed(t6 t6Var, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable r7 r7Var, IOException iOException) {
    }

    public void connectStart(t6 t6Var, InetSocketAddress inetSocketAddress, Proxy proxy) {
    }

    public void connectionAcquired(t6 t6Var, y6 y6Var) {
    }

    public void connectionReleased(t6 t6Var, y6 y6Var) {
    }

    public void dnsEnd(t6 t6Var, String str, List<InetAddress> list) {
    }

    public void dnsStart(t6 t6Var, String str) {
    }

    public void requestBodyEnd(t6 t6Var, long j) {
    }

    public void requestBodyStart(t6 t6Var) {
    }

    public void requestFailed(t6 t6Var, IOException iOException) {
    }

    public void requestHeadersEnd(t6 t6Var, t7 t7Var) {
    }

    public void requestHeadersStart(t6 t6Var) {
    }

    public void responseBodyEnd(t6 t6Var, long j) {
    }

    public void responseBodyStart(t6 t6Var) {
    }

    public void responseFailed(t6 t6Var, IOException iOException) {
    }

    public void responseHeadersEnd(t6 t6Var, v7 v7Var) {
    }

    public void responseHeadersStart(t6 t6Var) {
    }

    public void secureConnectEnd(t6 t6Var, @Nullable i7 i7Var) {
    }

    public void secureConnectStart(t6 t6Var) {
    }

    public static b a(final g7 g7Var) {
        return new b() { // from class: com.huawei.hms.network.embedded.g7$$ExternalSyntheticLambda0
            @Override // com.huawei.hms.network.embedded.g7.b
            public final g7 create(t6 t6Var) {
                return g7.a(g7.this, t6Var);
            }
        };
    }
}

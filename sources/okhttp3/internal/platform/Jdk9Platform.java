package okhttp3.internal.platform;

import com.huawei.hms.network.embedded.y;
import defpackage.uhy;
import defpackage.uib;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import okhttp3.Protocol;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0011\u0010\t\u001a\r\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\f0\nH\u0017J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0013"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public class Jdk9Platform extends Platform {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(0 == true ? 1 : 0);
    private static final boolean isAvailable;

    @Override // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        uhy.e((Object) sslSocket, "");
        uhy.e((Object) protocols, "");
        SSLParameters sSLParameters = sslSocket.getSSLParameters();
        sSLParameters.setApplicationProtocols((String[]) Platform.INSTANCE.alpnProtocolNames(protocols).toArray(new String[0]));
        sslSocket.setSSLParameters(sSLParameters);
    }

    @Override // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket sslSocket) {
        uhy.e((Object) sslSocket, "");
        try {
            String applicationProtocol = sslSocket.getApplicationProtocol();
            if (applicationProtocol != null) {
                if (!uhy.e((Object) applicationProtocol, (Object) "")) {
                    return applicationProtocol;
                }
            }
        } catch (UnsupportedOperationException unused) {
        }
        return null;
    }

    @Override // okhttp3.internal.platform.Platform
    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        uhy.e((Object) sslSocketFactory, "");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005¨\u0006\b"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform$Companion;", "", "()V", "isAvailable", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Jdk9Platform;", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final boolean isAvailable() {
            return Jdk9Platform.isAvailable;
        }

        public final Jdk9Platform buildIfSupported() {
            if (isAvailable()) {
                return new Jdk9Platform();
            }
            return null;
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
    
        if (r1.intValue() >= 9) goto L11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    static {
        /*
            okhttp3.internal.platform.Jdk9Platform$Companion r0 = new okhttp3.internal.platform.Jdk9Platform$Companion
            r1 = 0
            r0.<init>(r1)
            okhttp3.internal.platform.Jdk9Platform.INSTANCE = r0
            java.lang.String r0 = "java.specification.version"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            if (r0 == 0) goto L14
            java.lang.Integer r1 = defpackage.ujy.c(r0)
        L14:
            r0 = 0
            if (r1 == 0) goto L20
            int r1 = r1.intValue()
            r2 = 9
            if (r1 < r2) goto L2a
            goto L29
        L20:
            java.lang.Class[] r1 = new java.lang.Class[r0]     // Catch: java.lang.NoSuchMethodException -> L2a
            java.lang.Class<javax.net.ssl.SSLSocket> r2 = javax.net.ssl.SSLSocket.class
            java.lang.String r3 = "getApplicationProtocol"
            r2.getMethod(r3, r1)     // Catch: java.lang.NoSuchMethodException -> L2a
        L29:
            r0 = 1
        L2a:
            okhttp3.internal.platform.Jdk9Platform.isAvailable = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.platform.Jdk9Platform.<clinit>():void");
    }
}

package defpackage;

import java.net.InetSocketAddress;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import org.eclipse.californium.elements.EndpointContext;

/* loaded from: classes7.dex */
public class uzw implements EndpointContext {
    private final String b;
    private final Principal c;
    private final InetSocketAddress e;

    @Override // org.eclipse.californium.elements.EndpointContext
    public <T> T get(uzx<T> uzxVar) {
        return null;
    }

    @Override // org.eclipse.californium.elements.EndpointContext
    public boolean hasCriticalEntries() {
        return false;
    }

    public uzw(InetSocketAddress inetSocketAddress, String str, Principal principal) {
        if (inetSocketAddress == null) {
            throw new NullPointerException("missing peer socket address, must not be null!");
        }
        this.e = inetSocketAddress;
        this.b = str == null ? null : str.toLowerCase();
        this.c = principal;
    }

    @Override // org.eclipse.californium.elements.EndpointContext
    public <T> String getString(uzx<T> uzxVar) {
        Object obj = get(uzxVar);
        if (obj == null) {
            return null;
        }
        if (obj instanceof vbj) {
            return ((vbj) obj).e();
        }
        return obj.toString();
    }

    @Override // org.eclipse.californium.elements.EndpointContext
    public Map<uzx<?>, Object> entries() {
        return Collections.emptyMap();
    }

    @Override // org.eclipse.californium.elements.EndpointContext
    public final Principal getPeerIdentity() {
        return this.c;
    }

    @Override // org.eclipse.californium.elements.EndpointContext
    public final InetSocketAddress getPeerAddress() {
        return this.e;
    }

    @Override // org.eclipse.californium.elements.EndpointContext
    public final String getVirtualHost() {
        return this.b;
    }

    public String toString() {
        return String.format("IP(%s)", e());
    }

    protected final String e() {
        return vcb.a(this.e);
    }
}

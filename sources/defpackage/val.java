package defpackage;

import java.net.InetSocketAddress;
import org.eclipse.californium.elements.DefinitionsEndpointContextMatcher;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.util.NetworkInterfacesUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class val extends DefinitionsEndpointContextMatcher {
    private final boolean b;
    private static final Logger c = vha.a((Class<?>) val.class);

    /* renamed from: a, reason: collision with root package name */
    private static final vac<uzx<?>> f17633a = new vac("udp context").d(van.c);

    public val() {
        this(true);
    }

    public val(boolean z) {
        super(f17633a);
        this.b = z;
    }

    @Override // org.eclipse.californium.elements.DefinitionsEndpointContextMatcher, org.eclipse.californium.elements.EndpointIdentityResolver
    public Object getEndpointIdentity(EndpointContext endpointContext) {
        Object endpointIdentity = super.getEndpointIdentity(endpointContext);
        return ((endpointIdentity instanceof InetSocketAddress) && NetworkInterfacesUtil.b(((InetSocketAddress) endpointIdentity).getAddress())) ? "MULTICAST" : endpointIdentity;
    }

    @Override // org.eclipse.californium.elements.DefinitionsEndpointContextMatcher, org.eclipse.californium.elements.EndpointContextMatcher
    public boolean isResponseRelatedToRequest(EndpointContext endpointContext, EndpointContext endpointContext2) {
        if (this.b) {
            InetSocketAddress peerAddress = endpointContext.getPeerAddress();
            InetSocketAddress peerAddress2 = endpointContext2.getPeerAddress();
            if (!peerAddress.equals(peerAddress2) && !NetworkInterfacesUtil.b(peerAddress.getAddress())) {
                c.info("request {}:{} doesn't match {}:{}!", peerAddress.getAddress().getHostAddress(), Integer.valueOf(peerAddress.getPort()), peerAddress2.getAddress().getHostAddress(), Integer.valueOf(peerAddress2.getPort()));
                return false;
            }
        }
        return super.isResponseRelatedToRequest(endpointContext, endpointContext2);
    }
}

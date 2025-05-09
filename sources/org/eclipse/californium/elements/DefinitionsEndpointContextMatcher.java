package org.eclipse.californium.elements;

import defpackage.uzx;
import defpackage.vaa;
import defpackage.vac;
import defpackage.vcb;
import java.net.InetSocketAddress;

/* loaded from: classes7.dex */
public abstract class DefinitionsEndpointContextMatcher implements EndpointContextMatcher {
    private final boolean compareHostname;
    private final vac<uzx<?>> definitions;
    private final String recvTag;
    private final String sendTag;

    public DefinitionsEndpointContextMatcher(vac<uzx<?>> vacVar) {
        this(vacVar, false);
    }

    public DefinitionsEndpointContextMatcher(vac<uzx<?>> vacVar, boolean z) {
        if (vacVar == null) {
            throw new NullPointerException("Definitions must not be null!");
        }
        this.sendTag = vacVar.d() + " sending";
        this.recvTag = vacVar.d() + " receiving";
        this.definitions = vacVar;
        this.compareHostname = z;
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher, org.eclipse.californium.elements.EndpointIdentityResolver
    public String getName() {
        return this.definitions.d();
    }

    @Override // org.eclipse.californium.elements.EndpointIdentityResolver
    public Object getEndpointIdentity(EndpointContext endpointContext) {
        InetSocketAddress peerAddress = endpointContext.getPeerAddress();
        if (!peerAddress.isUnresolved()) {
            return peerAddress;
        }
        throw new IllegalArgumentException(vcb.a(peerAddress) + " must be resolved!");
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher
    public boolean isResponseRelatedToRequest(EndpointContext endpointContext, EndpointContext endpointContext2) {
        return (!this.compareHostname || isSameVirtualHost(endpointContext, endpointContext2)) && internalMatch(this.recvTag, endpointContext, endpointContext2);
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher
    public boolean isToBeSent(EndpointContext endpointContext, EndpointContext endpointContext2) {
        if (endpointContext2 == null) {
            return !endpointContext.hasCriticalEntries();
        }
        return (!this.compareHostname || isSameVirtualHost(endpointContext, endpointContext2)) && internalMatch(this.sendTag, endpointContext, endpointContext2);
    }

    private final boolean internalMatch(String str, EndpointContext endpointContext, EndpointContext endpointContext2) {
        if (endpointContext.hasCriticalEntries()) {
            return vaa.b(str, this.definitions, endpointContext, endpointContext2);
        }
        return true;
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher
    public String toRelevantState(EndpointContext endpointContext) {
        return endpointContext == null ? "n.a." : endpointContext.toString();
    }

    public static final boolean isSameVirtualHost(EndpointContext endpointContext, EndpointContext endpointContext2) {
        String virtualHost;
        String virtualHost2;
        if (endpointContext == null) {
            throw new NullPointerException("first context must not be null");
        }
        if (endpointContext2 == null || (virtualHost = endpointContext.getVirtualHost()) == (virtualHost2 = endpointContext2.getVirtualHost())) {
            return true;
        }
        return virtualHost != null && virtualHost.equals(virtualHost2);
    }
}

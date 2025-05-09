package defpackage;

import java.security.Principal;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.EndpointContextMatcher;

/* loaded from: classes7.dex */
public class vad implements EndpointContextMatcher {
    private final boolean c;

    public vad() {
        this(false);
    }

    public vad(boolean z) {
        this.c = z;
    }

    @Override // org.eclipse.californium.elements.EndpointIdentityResolver
    public Object getEndpointIdentity(EndpointContext endpointContext) {
        if (this.c) {
            Principal peerIdentity = endpointContext.getPeerIdentity();
            if (peerIdentity != null) {
                return peerIdentity;
            }
            throw new IllegalArgumentException("Principal identity missing in provided endpoint context!");
        }
        return endpointContext.getPeerAddress();
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher
    public boolean isResponseRelatedToRequest(EndpointContext endpointContext, EndpointContext endpointContext2) {
        return d(endpointContext, endpointContext2);
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher
    public boolean isToBeSent(EndpointContext endpointContext, EndpointContext endpointContext2) {
        if (endpointContext2 == null) {
            return true;
        }
        return d(endpointContext, endpointContext2);
    }

    private final boolean d(EndpointContext endpointContext, EndpointContext endpointContext2) {
        if (endpointContext.getPeerIdentity() != null && (endpointContext2.getPeerIdentity() == null || !b(endpointContext.getPeerIdentity(), endpointContext2.getPeerIdentity()))) {
            return false;
        }
        String string = endpointContext.getString(uzz.j);
        return string == null || string.equals(endpointContext2.getString(uzz.j));
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher
    public String toRelevantState(EndpointContext endpointContext) {
        if (endpointContext == null) {
            return "n.a.";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append(endpointContext.getPeerIdentity());
        String string = endpointContext.getString(uzz.j);
        if (string != null) {
            sb.append(",");
            sb.append(string);
        }
        sb.append("]");
        return sb.toString();
    }

    protected boolean b(Principal principal, Principal principal2) {
        return principal.equals(principal2);
    }

    @Override // org.eclipse.californium.elements.EndpointContextMatcher, org.eclipse.californium.elements.EndpointIdentityResolver
    public String getName() {
        return "principal correlation";
    }
}

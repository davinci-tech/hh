package defpackage;

import org.eclipse.californium.elements.EndpointContext;

/* loaded from: classes7.dex */
public final class uzp {
    private final uxt b;
    private final EndpointContext e;

    public uzp(uxt uxtVar, EndpointContext endpointContext) {
        if (uxtVar == null) {
            throw new NullPointerException("request must not be null");
        }
        if (!uxtVar.o()) {
            throw new IllegalArgumentException("request has no observe=0 option");
        }
        this.b = uxtVar;
        this.e = endpointContext;
    }

    public uxt c() {
        return this.b;
    }

    public EndpointContext d() {
        return this.e;
    }

    public String toString() {
        return this.b.toString();
    }
}

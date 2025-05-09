package defpackage;

import org.eclipse.californium.core.coap.CoAP;

/* loaded from: classes7.dex */
public class uxi {

    /* renamed from: a, reason: collision with root package name */
    private uxr f17573a;

    protected uxi(uxr uxrVar) {
        if (uxrVar == null) {
            throw new NullPointerException("Response must not be null!");
        }
        this.f17573a = uxrVar;
    }

    public CoAP.ResponseCode c() {
        return this.f17573a.a();
    }

    public String a() {
        return this.f17573a.getPayloadString();
    }

    public uxr b() {
        return this.f17573a;
    }

    public String toString() {
        return this.f17573a.toString();
    }
}

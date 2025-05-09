package defpackage;

import org.eclipse.californium.core.coap.CoAP;

/* loaded from: classes7.dex */
public class uyp {

    /* renamed from: a, reason: collision with root package name */
    private final int f17603a;
    private final int b;
    private final CoAP.Type c;
    private final int d;
    private final uxu e;
    private final int j;

    public uyp(int i, CoAP.Type type, uxu uxuVar, int i2, int i3, int i4) {
        this.j = i;
        this.c = type;
        this.e = uxuVar;
        this.f17603a = i2;
        this.d = i3;
        this.b = i4;
    }

    public int b() {
        int i = this.b;
        if (i >= 0) {
            return i;
        }
        throw new IllegalStateException("body length not available!");
    }

    public CoAP.Type c() {
        return this.c;
    }

    public uxu e() {
        return this.e;
    }

    public int d() {
        return this.f17603a;
    }

    public int a() {
        return this.d;
    }
}

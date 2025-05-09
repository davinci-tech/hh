package defpackage;

import org.eclipse.californium.core.coap.CoAP;

/* loaded from: classes7.dex */
public class uxk extends uxq {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    private final int f17575a;
    private final uxu b;
    private final CoAP.ResponseCode c;
    private final boolean d;
    private final int e;

    public uxk(String str, uxu uxuVar, int i, int i2, boolean z) {
        this(str, uxuVar, i, i2, z, CoAP.ResponseCode.BAD_OPTION);
    }

    public uxk(String str, uxu uxuVar, int i, int i2, boolean z, CoAP.ResponseCode responseCode) {
        super(str);
        this.b = uxuVar;
        this.e = i;
        this.f17575a = i2;
        this.d = z;
        this.c = responseCode;
    }

    public uxu c() {
        return this.b;
    }

    public final boolean d() {
        return this.e > -1;
    }

    public final int a() {
        return this.e;
    }

    public final int b() {
        return this.f17575a;
    }

    public final CoAP.ResponseCode e() {
        return this.c;
    }

    public final boolean i() {
        return this.d;
    }
}

package defpackage;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.serialization.DataParser;

/* loaded from: classes7.dex */
public class uyq extends DataParser {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f17604a;

    public uyq() {
        this(false, null);
    }

    public uyq(boolean z, int[] iArr) {
        super(iArr);
        this.f17604a = z;
    }

    @Override // org.eclipse.californium.core.network.serialization.DataParser
    public uyp parseHeader(vbn vbnVar) {
        if (!vbnVar.g(4)) {
            throw new uxq("UDP Message too short! " + (vbnVar.a() / 8) + " must be at least 4 bytes!");
        }
        int c = vbnVar.c(2);
        d(c);
        CoAP.Type valueOf = CoAP.Type.valueOf(vbnVar.c(2));
        boolean z = valueOf == CoAP.Type.CON;
        int c2 = vbnVar.c(4);
        if (c2 > 8) {
            throw new uxq("UDP Message has invalid token length (> 8) " + c2);
        }
        int c3 = vbnVar.c(8);
        int c4 = vbnVar.c(16);
        if (this.f17604a && c3 == 0 && vbnVar.e()) {
            throw new uxk("UDP malformed Empty Message!", null, c4, c3, z);
        }
        if (!vbnVar.g(c2)) {
            throw new uxk("UDP Message too short for token! " + (vbnVar.a() / 8) + " must be at least " + c2 + " bytes!", null, c4, c3, z);
        }
        return new uyp(c, valueOf, uxu.d(vbnVar.a(c2)), c3, c4, 0);
    }

    @Override // org.eclipse.californium.core.network.serialization.DataParser
    public void assertValidOptions(uxv uxvVar) {
        d(uxvVar);
    }

    private void d(int i) {
        if (i == 1) {
            return;
        }
        throw new uxq("UDP Message has invalid version: " + i);
    }

    public static void d(uxv uxvVar) {
        uxh f = uxvVar.f();
        if (f != null && f.g()) {
            throw new IllegalArgumentException("Block1 BERT used for UDP!");
        }
        uxh j = uxvVar.j();
        if (j != null && j.g()) {
            throw new IllegalArgumentException("Block2 BERT used for UDP!");
        }
    }
}

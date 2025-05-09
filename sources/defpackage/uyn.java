package defpackage;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.serialization.DataParser;

/* loaded from: classes7.dex */
public class uyn extends DataParser {
    public uyn() {
    }

    public uyn(int[] iArr) {
        super(iArr);
    }

    @Override // org.eclipse.californium.core.network.serialization.DataParser
    public uyp parseHeader(vbn vbnVar) {
        if (!vbnVar.g(1)) {
            throw new uxq("TCP Message too short! " + (vbnVar.a() / 8) + " must be at least 1 byte!");
        }
        int c = vbnVar.c(4);
        int c2 = vbnVar.c(4);
        if (c2 > 8) {
            throw new uxq("TCP Message has invalid token length (> 8) " + c2);
        }
        int i = c != 13 ? c == 14 ? 2 : c == 15 ? 4 : 0 : 1;
        int i2 = i + 1 + c2;
        if (!vbnVar.g(i2)) {
            throw new uxq("TCP Message too short! " + (vbnVar.a() / 8) + " must be at least " + i2 + " bytes!");
        }
        vbnVar.a(i);
        return new uyp(1, CoAP.Type.CON, uxu.d(vbnVar.a(c2)), vbnVar.c(8), -1, 0);
    }
}

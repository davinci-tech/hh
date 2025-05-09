package defpackage;

import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.network.serialization.DataSerializer;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class uyr extends DataSerializer {
    private static final Logger c = vha.a((Class<?>) uyr.class);

    @Override // org.eclipse.californium.core.network.serialization.DataSerializer
    public void serializeMessage(vbo vboVar, Message message) {
        int mid = message.getMID();
        if (mid == -1) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("MID required for UDP serialization!");
            c.warn("UDP, {}:", message, illegalArgumentException);
            throw illegalArgumentException;
        }
        serializeHeader(vboVar, new uyp(1, message.getType(), message.getToken(), message.getRawCode(), mid, -1));
        vboVar.i();
        serializeOptionsAndPayload(vboVar, message.getOptions(), message.getPayload());
    }

    @Override // org.eclipse.californium.core.network.serialization.DataSerializer
    public void serializeHeader(vbo vboVar, uyp uypVar) {
        vboVar.b(1, 2);
        vboVar.b(uypVar.c().value, 2);
        vboVar.b(uypVar.e().b(), 4);
        vboVar.b(uypVar.d(), 8);
        vboVar.b(uypVar.a(), 16);
        vboVar.d(uypVar.e().c());
    }

    @Override // org.eclipse.californium.core.network.serialization.DataSerializer
    public void assertValidOptions(uxv uxvVar) {
        uyq.d(uxvVar);
    }
}

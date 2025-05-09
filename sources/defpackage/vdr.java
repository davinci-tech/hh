package defpackage;

import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.ServerKeyExchange;

/* loaded from: classes7.dex */
public final class vdr extends ServerKeyExchange {
    private final vdt c;

    private vdr(byte[] bArr) {
        this.c = vdt.e(bArr);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.c.b() + 2;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "PSK Identity Hint: " + this.c + vcb.a();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo(this.c.b() + 2);
        vboVar.b(this.c, 16);
        return vboVar.c();
    }

    public static HandshakeMessage d(vbn vbnVar) {
        return new vdr(vbnVar.h(16));
    }
}

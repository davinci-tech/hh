package defpackage;

import org.eclipse.californium.scandium.dtls.ClientKeyExchange;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;

/* loaded from: classes7.dex */
public final class vds extends ClientKeyExchange {
    private final vdt e;

    public vds(vdt vdtVar) {
        this.e = vdtVar;
    }

    private vds(byte[] bArr) {
        this.e = vdt.e(bArr);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.e.b() + 2;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "PSK Identity: " + this.e + vcb.a();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo(this.e.b() + 2);
        vboVar.b(this.e, 16);
        return vboVar.c();
    }

    public static HandshakeMessage b(vbn vbnVar) {
        return new vds(vbnVar.h(16));
    }

    public vdt d() {
        return this.e;
    }
}

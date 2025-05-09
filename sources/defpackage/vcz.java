package defpackage;

import org.eclipse.californium.scandium.dtls.HandshakeMessage;

/* loaded from: classes7.dex */
public final class vcz extends vcy {
    private final vdt c;

    public vcz(vdt vdtVar, byte[] bArr) {
        super(bArr);
        if (vdtVar == null) {
            throw new NullPointerException("identity cannot be null");
        }
        this.c = vdtVar;
    }

    @Override // defpackage.vcy
    protected void b(vbo vboVar) {
        vboVar.b(this.c, 16);
        super.b(vboVar);
    }

    public static HandshakeMessage e(vbn vbnVar) {
        return new vcz(vdt.e(vbnVar.h(16)), c(vbnVar));
    }

    @Override // defpackage.vcy, org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.c.b() + 2 + super.getMessageLength();
    }

    @Override // defpackage.vcy, org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "Encoded identity value: " + this.c + vcb.a();
    }

    public vdt d() {
        return this.c;
    }
}

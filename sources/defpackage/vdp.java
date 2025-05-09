package defpackage;

import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;

/* loaded from: classes7.dex */
public final class vdp extends HandshakeMessage {

    /* renamed from: a, reason: collision with root package name */
    private final vdu f17686a;
    private final byte[] b;

    public vdp(vdu vduVar, byte[] bArr) {
        this.f17686a = vduVar;
        this.b = bArr;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo(this.b.length + 3);
        vboVar.b(this.f17686a.c(), 8);
        vboVar.b(this.f17686a.b(), 8);
        vboVar.d(this.b, 8);
        return vboVar.c();
    }

    public static HandshakeMessage d(vbn vbnVar) {
        return new vdp(vdu.c(vbnVar.c(8), vbnVar.c(8)), vbnVar.h(8));
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.HELLO_VERIFY_REQUEST;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.b.length + 3;
    }

    public byte[] a() {
        return this.b;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(i));
        String b = vcb.b(i + 1);
        sb.append(b);
        sb.append("Server Version: ");
        sb.append(this.f17686a.c());
        sb.append(", ");
        sb.append(this.f17686a.b());
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Cookie Length: ");
        sb.append(this.b.length);
        sb.append(" bytes");
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Cookie: ");
        sb.append(vcb.b(this.b));
        sb.append(vcb.a());
        return sb.toString();
    }
}

package defpackage;

import org.eclipse.californium.scandium.dtls.ECDHServerKeyExchange;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public final class vda extends ECDHServerKeyExchange {
    private final vdt d;

    public vda(vdt vdtVar, XECDHECryptography xECDHECryptography) {
        super(xECDHECryptography.i(), xECDHECryptography.g());
        if (vdtVar == null) {
            throw new NullPointerException("PSK hint must not be null");
        }
        this.d = vdtVar;
    }

    private vda(byte[] bArr, XECDHECryptography.SupportedGroup supportedGroup, byte[] bArr2) throws vdb {
        super(supportedGroup, bArr2);
        this.d = vdt.e(bArr);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo();
        vboVar.b(this.d, 16);
        writeNamedCurve(vboVar);
        return vboVar.c();
    }

    public static HandshakeMessage b(vbn vbnVar) throws vdb {
        byte[] h = vbnVar.h(16);
        ECDHServerKeyExchange.c readNamedCurve = readNamedCurve(vbnVar);
        return new vda(h, readNamedCurve.b, readNamedCurve.d);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.d.b() + 2 + getNamedCurveLength();
    }

    @Override // org.eclipse.californium.scandium.dtls.ECDHServerKeyExchange, org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(i));
        sb.append(vcb.b(i + 1));
        sb.append("PSK Identity Hint: ");
        if (this.d.d()) {
            sb.append("not present");
        } else {
            sb.append(this.d);
        }
        sb.append(vcb.a());
        return sb.toString();
    }
}

package defpackage;

import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.HelloHandshakeMessage;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public final class vee extends HelloHandshakeMessage {
    private final CompressionMethod b;
    private final CipherSuite e;

    public vee(vdu vduVar, vej vejVar, CipherSuite cipherSuite, CompressionMethod compressionMethod) {
        super(vduVar, vejVar);
        if (cipherSuite == null) {
            throw new NullPointerException("Negotiated cipher suite must not be null");
        }
        if (compressionMethod == null) {
            throw new NullPointerException("Negotiated compression method must not be null");
        }
        this.e = cipherSuite;
        this.b = compressionMethod;
    }

    private vee(vbn vbnVar) throws vdb {
        super(vbnVar);
        int c = vbnVar.c(16);
        CipherSuite typeByCode = CipherSuite.getTypeByCode(c);
        this.e = typeByCode;
        if (typeByCode == null) {
            throw new vdb(String.format("Server selected unknown cipher suite [%s]", Integer.toHexString(c)), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
        }
        if (!typeByCode.isValidForNegotiation()) {
            throw new vdb("Server tries to negotiate a cipher suite invalid for negotiation", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
        }
        this.b = CompressionMethod.getMethodByCode(vbnVar.c(8));
        this.extensions.a(vbnVar);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo();
        writeHeader(vboVar);
        vboVar.b(this.e.getCode(), 16);
        vboVar.b(this.b.getCode(), 8);
        this.extensions.a(vboVar);
        return vboVar.c();
    }

    public static HandshakeMessage d(vbn vbnVar) throws vdb {
        return new vee(vbnVar);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.SERVER_HELLO;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.sessionId.b() + 38 + this.extensions.a();
    }

    public CipherSuite b() {
        return this.e;
    }

    public CompressionMethod a() {
        return this.b;
    }

    @Override // org.eclipse.californium.scandium.dtls.HelloHandshakeMessage, org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder(super.toString(i));
        int i2 = i + 1;
        String b = vcb.b(i2);
        sb.append(b);
        sb.append("Cipher Suite: ");
        sb.append(this.e);
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Compression Method: ");
        sb.append(this.b);
        sb.append(vcb.a());
        sb.append(this.extensions.a(i2));
        return sb.toString();
    }
}

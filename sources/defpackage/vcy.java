package defpackage;

import java.util.Arrays;
import org.eclipse.californium.scandium.dtls.ClientKeyExchange;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;

/* loaded from: classes7.dex */
public class vcy extends ClientKeyExchange {
    private final byte[] b;

    public vcy(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("encoded point cannot be null");
        }
        this.b = bArr;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo();
        b(vboVar);
        return vboVar.c();
    }

    protected void b(vbo vboVar) {
        vboVar.d(this.b, 8);
    }

    protected static byte[] c(vbn vbnVar) {
        return vbnVar.h(8);
    }

    public static HandshakeMessage b(vbn vbnVar) {
        return new vcy(c(vbnVar));
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.b.length + 1;
    }

    public byte[] a() {
        byte[] bArr = this.b;
        return Arrays.copyOf(bArr, bArr.length);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "Diffie-Hellman public value: " + vcb.b(this.b, (char) 0, 16) + vcb.a();
    }
}

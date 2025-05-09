package defpackage;

import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.cipher.PseudoRandomFunction;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class vde extends HandshakeMessage {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17677a = vha.a((Class<?>) vde.class);
    private final byte[] d;

    public vde(Mac mac, SecretKey secretKey, boolean z, byte[] bArr) {
        this.d = c(mac, secretKey, z, bArr);
    }

    private vde(vbn vbnVar) {
        this.d = vbnVar.i();
    }

    public void d(Mac mac, SecretKey secretKey, boolean z, byte[] bArr) throws vdb {
        byte[] c = c(mac, secretKey, z, bArr);
        if (MessageDigest.isEqual(c, this.d)) {
            return;
        }
        StringBuilder sb = new StringBuilder("Verification of FINISHED message failed");
        Logger logger = f17677a;
        if (logger.isTraceEnabled()) {
            sb.append(vcb.a());
            sb.append("Expected: ");
            sb.append(vcb.b(c));
            sb.append(vcb.a());
            sb.append("Received: ");
            sb.append(vcb.b(this.d));
        }
        logger.debug(sb.toString());
        throw new vdb("Verification of FINISHED message failed", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECRYPT_ERROR));
    }

    private byte[] c(Mac mac, SecretKey secretKey, boolean z, byte[] bArr) {
        if (z) {
            return PseudoRandomFunction.a(mac, secretKey, PseudoRandomFunction.Label.CLIENT_FINISHED_LABEL, bArr);
        }
        return PseudoRandomFunction.a(mac, secretKey, PseudoRandomFunction.Label.SERVER_FINISHED_LABEL, bArr);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.FINISHED;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.d.length;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        return super.toString(i) + vcb.b(i + 1) + "Verify Data: " + vcb.b(this.d) + vcb.a();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        return this.d;
    }

    public static HandshakeMessage c(vbn vbnVar) {
        return new vde(vbnVar);
    }
}

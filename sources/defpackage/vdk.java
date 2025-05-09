package defpackage;

import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;

/* loaded from: classes7.dex */
public final class vdk extends HandshakeMessage {
    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return 0;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.HELLO_REQUEST;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        return vbj.c;
    }
}

package defpackage;

import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;

/* loaded from: classes7.dex */
public class vdn extends HandshakeMessage {
    private int b;
    private int c;
    private HandshakeMessage e = this;

    protected vdn() {
    }

    public int c() {
        return this.c;
    }

    public void d(HandshakeMessage handshakeMessage) {
        this.e.setNextHandshakeMessage(handshakeMessage);
        this.e = handshakeMessage;
        this.b += handshakeMessage.size();
        this.c++;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        HandshakeMessage nextHandshakeMessage = getNextHandshakeMessage();
        if (nextHandshakeMessage == null) {
            return null;
        }
        return nextHandshakeMessage.getMessageType();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.b - 12;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        throw new RuntimeException("not supported!");
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public byte[] toByteArray() {
        vbo vboVar = new vbo(1500);
        for (HandshakeMessage nextHandshakeMessage = getNextHandshakeMessage(); nextHandshakeMessage != null; nextHandshakeMessage = nextHandshakeMessage.getNextHandshakeMessage()) {
            nextHandshakeMessage.writeTo(vboVar);
        }
        return vboVar.c();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public String getImplementationTypePrefix() {
        return "Multi ";
    }
}

package defpackage;

import java.util.Arrays;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;

/* loaded from: classes7.dex */
public class vdd extends HandshakeMessage {
    private final HandshakeType b;

    protected vdd(HandshakeType handshakeType) {
        this.b = handshakeType;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return this.b;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return getRawMessage().length - 12;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        byte[] rawMessage = getRawMessage();
        return Arrays.copyOfRange(rawMessage, 12, rawMessage.length);
    }

    public static vdd c(HandshakeType handshakeType) {
        return new vdd(handshakeType);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public String getImplementationTypePrefix() {
        return "Generic ";
    }
}

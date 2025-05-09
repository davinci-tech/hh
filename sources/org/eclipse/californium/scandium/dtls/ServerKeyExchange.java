package org.eclipse.californium.scandium.dtls;

/* loaded from: classes7.dex */
public abstract class ServerKeyExchange extends HandshakeMessage {
    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public final HandshakeType getMessageType() {
        return HandshakeType.SERVER_KEY_EXCHANGE;
    }
}

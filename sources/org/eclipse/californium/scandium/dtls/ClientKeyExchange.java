package org.eclipse.californium.scandium.dtls;

/* loaded from: classes7.dex */
public abstract class ClientKeyExchange extends HandshakeMessage {
    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public final HandshakeType getMessageType() {
        return HandshakeType.CLIENT_KEY_EXCHANGE;
    }
}

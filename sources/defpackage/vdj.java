package defpackage;

import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.DTLSMessage;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;

/* loaded from: classes7.dex */
public class vdj {
    private final boolean b;
    private final HandshakeType c;
    private final ContentType d;

    public vdj(ContentType contentType) {
        this(contentType, null, false);
    }

    public vdj(HandshakeType handshakeType) {
        this(ContentType.HANDSHAKE, handshakeType, false);
    }

    public vdj(HandshakeType handshakeType, boolean z) {
        this(ContentType.HANDSHAKE, handshakeType, z);
    }

    private vdj(ContentType contentType, HandshakeType handshakeType, boolean z) {
        this.d = contentType;
        this.c = handshakeType;
        this.b = z;
    }

    public boolean e() {
        return this.b;
    }

    public boolean d(DTLSMessage dTLSMessage) {
        if (dTLSMessage.getContentType() != this.d) {
            return false;
        }
        return !(dTLSMessage instanceof HandshakeMessage) || ((HandshakeMessage) dTLSMessage).getMessageType() == this.c;
    }

    public String toString() {
        return c(this.d, this.c);
    }

    public static String b(DTLSMessage dTLSMessage) {
        if (dTLSMessage instanceof HandshakeMessage) {
            return c(dTLSMessage.getContentType(), ((HandshakeMessage) dTLSMessage).getMessageType());
        }
        return c(dTLSMessage.getContentType(), null);
    }

    private static String c(ContentType contentType, HandshakeType handshakeType) {
        if (handshakeType != null) {
            return contentType.name() + "/" + handshakeType.name();
        }
        return contentType.name();
    }
}

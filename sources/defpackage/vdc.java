package defpackage;

import java.util.Arrays;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;

/* loaded from: classes7.dex */
public final class vdc extends HandshakeMessage {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f17676a;
    private final int b;
    private final HandshakeType d;
    private final int e;

    public vdc(HandshakeType handshakeType, int i, int i2, int i3, byte[] bArr) {
        this.d = handshakeType;
        this.e = i;
        this.f17676a = Arrays.copyOf(bArr, bArr.length);
        this.b = i3;
        setMessageSeq(i2);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return this.d;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.e;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getFragmentOffset() {
        return this.b;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getFragmentLength() {
        return this.f17676a.length;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(i));
        String b = vcb.b(i);
        sb.append(b);
        sb.append("Fragment Offset: ");
        sb.append(getFragmentOffset());
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Fragment Length: ");
        sb.append(getFragmentLength());
        sb.append(" bytes");
        sb.append(vcb.a());
        return sb.toString();
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        return this.f17676a;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public String getImplementationTypePrefix() {
        return "Fragmented ";
    }
}

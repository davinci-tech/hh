package defpackage;

import org.eclipse.californium.scandium.dtls.NodeConnectionIdGenerator;

/* loaded from: classes7.dex */
public class vdo implements NodeConnectionIdGenerator {
    private final int d;
    private final int e;

    @Override // org.eclipse.californium.scandium.dtls.ConnectionIdGenerator
    public boolean useConnectionId() {
        return true;
    }

    public vdo(int i, int i2) {
        if (i2 < 2) {
            throw new IllegalArgumentException("cid length must be at least 2 bytes!");
        }
        this.d = i;
        this.e = i2;
    }

    @Override // org.eclipse.californium.scandium.dtls.ConnectionIdGenerator
    public vcp createConnectionId() {
        byte[] bArr = new byte[this.e];
        vep.e().nextBytes(bArr);
        bArr[0] = (byte) this.d;
        return new vcp(bArr);
    }

    @Override // org.eclipse.californium.scandium.dtls.ConnectionIdGenerator
    public vcp read(vbn vbnVar) {
        return new vcp(vbnVar.a(this.e));
    }

    @Override // org.eclipse.californium.scandium.dtls.NodeConnectionIdGenerator
    public int getNodeId() {
        return this.d;
    }

    @Override // org.eclipse.californium.scandium.dtls.NodeConnectionIdGenerator
    public int getNodeId(vcp vcpVar) {
        return vcpVar.c()[0] & 255;
    }
}

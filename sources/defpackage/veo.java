package defpackage;

import org.eclipse.californium.scandium.dtls.ConnectionIdGenerator;

/* loaded from: classes7.dex */
public class veo implements ConnectionIdGenerator {
    private final int d;

    public veo(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("cid length must not be less than 0 bytes!");
        }
        this.d = i;
    }

    @Override // org.eclipse.californium.scandium.dtls.ConnectionIdGenerator
    public boolean useConnectionId() {
        return this.d > 0;
    }

    @Override // org.eclipse.californium.scandium.dtls.ConnectionIdGenerator
    public vcp createConnectionId() {
        if (!useConnectionId()) {
            return null;
        }
        byte[] bArr = new byte[this.d];
        vep.e().nextBytes(bArr);
        return new vcp(bArr);
    }

    @Override // org.eclipse.californium.scandium.dtls.ConnectionIdGenerator
    public vcp read(vbn vbnVar) {
        if (useConnectionId()) {
            return new vcp(vbnVar.a(this.d));
        }
        return null;
    }
}

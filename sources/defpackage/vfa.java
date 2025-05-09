package defpackage;

import java.net.InetSocketAddress;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import org.eclipse.californium.scandium.dtls.HandshakeResultHandler;
import org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore;

/* loaded from: classes7.dex */
public class vfa implements AdvancedPskStore, Destroyable {
    private final vdt d;
    private final SecretKey e;

    @Override // org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore
    public boolean hasEcdhePskSupported() {
        return true;
    }

    @Override // org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore
    public void setResultHandler(HandshakeResultHandler handshakeResultHandler) {
    }

    public vfa(String str, byte[] bArr) {
        this(new vdt(str), bArr);
    }

    public vfa(vdt vdtVar, byte[] bArr) {
        this.d = vdtVar;
        this.e = vfh.e(bArr, "PSK");
    }

    @Override // org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore
    public vdq requestPskSecretResult(vcp vcpVar, vfe vfeVar, vdt vdtVar, String str, SecretKey secretKey, byte[] bArr, boolean z) {
        return new vdq(vcpVar, this.d, this.d.equals(vdtVar) ? vfh.a(this.e) : null);
    }

    @Override // org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore
    public vdt getIdentity(InetSocketAddress inetSocketAddress, vfe vfeVar) {
        return this.d;
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vfh.e(this.e);
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return vfh.b(this.e);
    }
}

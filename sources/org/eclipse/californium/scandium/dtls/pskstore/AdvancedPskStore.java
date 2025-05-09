package org.eclipse.californium.scandium.dtls.pskstore;

import defpackage.vcp;
import defpackage.vdq;
import defpackage.vdt;
import defpackage.vfe;
import java.net.InetSocketAddress;
import javax.crypto.SecretKey;
import org.eclipse.californium.scandium.dtls.HandshakeResultHandler;

/* loaded from: classes7.dex */
public interface AdvancedPskStore {
    vdt getIdentity(InetSocketAddress inetSocketAddress, vfe vfeVar);

    boolean hasEcdhePskSupported();

    vdq requestPskSecretResult(vcp vcpVar, vfe vfeVar, vdt vdtVar, String str, SecretKey secretKey, byte[] bArr, boolean z);

    void setResultHandler(HandshakeResultHandler handshakeResultHandler);
}

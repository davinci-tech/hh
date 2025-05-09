package org.eclipse.californium.scandium.dtls.x509;

import defpackage.vce;
import defpackage.vcl;
import defpackage.vcp;
import defpackage.vfe;
import java.net.InetSocketAddress;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.HandshakeResultHandler;

/* loaded from: classes7.dex */
public interface NewAdvancedCertificateVerifier {
    List<X500Principal> getAcceptedIssuers();

    List<CertificateType> getSupportedCertificateTypes();

    void setResultHandler(HandshakeResultHandler handshakeResultHandler);

    vcl verifyCertificate(vcp vcpVar, vfe vfeVar, InetSocketAddress inetSocketAddress, boolean z, boolean z2, boolean z3, vce vceVar);
}

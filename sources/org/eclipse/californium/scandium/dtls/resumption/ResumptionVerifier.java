package org.eclipse.californium.scandium.dtls.resumption;

import defpackage.vcp;
import defpackage.ved;
import defpackage.vej;
import defpackage.vfe;
import org.eclipse.californium.scandium.dtls.HandshakeResultHandler;

/* loaded from: classes7.dex */
public interface ResumptionVerifier {
    void setResultHandler(HandshakeResultHandler handshakeResultHandler);

    boolean skipRequestHelloVerify(vej vejVar);

    ved verifyResumptionRequest(vcp vcpVar, vfe vfeVar, vej vejVar);
}

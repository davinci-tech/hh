package org.eclipse.californium.scandium.dtls;

import defpackage.vbn;
import defpackage.vbo;
import defpackage.vcb;
import defpackage.vch;
import defpackage.vcq;
import defpackage.vdh;
import defpackage.vdu;
import defpackage.vdv;
import defpackage.vdx;
import defpackage.vea;
import defpackage.vef;
import defpackage.veg;
import defpackage.vej;
import org.eclipse.californium.scandium.dtls.HelloExtension;

/* loaded from: classes7.dex */
public abstract class HelloHandshakeMessage extends HandshakeMessage {
    protected static final int RANDOM_BYTES = 32;
    protected static final int SESSION_ID_LENGTH_BITS = 8;
    protected static final int VERSION_BITS = 8;
    protected final vdh extensions = new vdh();
    protected final vdu protocolVersion;
    protected final vdv random;
    protected final vej sessionId;

    public HelloHandshakeMessage(vdu vduVar, vej vejVar) {
        if (vduVar == null) {
            throw new NullPointerException("Negotiated protocol version must not be null");
        }
        if (vejVar == null) {
            throw new NullPointerException("ServerHello must be associated with a session ID");
        }
        this.protocolVersion = vduVar;
        this.sessionId = vejVar;
        this.random = new vdv();
    }

    public HelloHandshakeMessage(vbn vbnVar) {
        this.protocolVersion = vdu.c(vbnVar.c(8), vbnVar.c(8));
        this.random = new vdv(vbnVar.a(32));
        this.sessionId = new vej(vbnVar.h(8));
    }

    protected void writeHeader(vbo vboVar) {
        vboVar.b(this.protocolVersion.c(), 8);
        vboVar.b(this.protocolVersion.b(), 8);
        vboVar.d(this.random.c());
        vboVar.b(this.sessionId, 8);
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(i));
        String b = vcb.b(i + 1);
        sb.append(b);
        sb.append("Version: ");
        sb.append(this.protocolVersion.c());
        sb.append(", ");
        sb.append(this.protocolVersion.b());
        sb.append(vcb.a());
        sb.append(b);
        sb.append("Random:");
        sb.append(vcb.a());
        sb.append(this.random.c(i + 2));
        sb.append(b);
        sb.append("Session ID Length: ");
        sb.append(this.sessionId.b());
        sb.append(" bytes");
        sb.append(vcb.a());
        if (this.sessionId.b() > 0) {
            sb.append(b);
            sb.append("Session ID: ");
            sb.append(this.sessionId);
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    public vdu getProtocolVersion() {
        return this.protocolVersion;
    }

    public vdv getRandom() {
        return this.random;
    }

    public vej getSessionId() {
        return this.sessionId;
    }

    public boolean hasSessionId() {
        return !this.sessionId.d();
    }

    public void addExtension(HelloExtension helloExtension) {
        this.extensions.d(helloExtension);
    }

    public vdh getExtensions() {
        return this.extensions;
    }

    public SupportedPointFormatsExtension getSupportedPointFormatsExtension() {
        return (SupportedPointFormatsExtension) this.extensions.e(HelloExtension.ExtensionType.EC_POINT_FORMATS);
    }

    public vch getClientCertificateTypeExtension() {
        return (vch) this.extensions.e(HelloExtension.ExtensionType.CLIENT_CERT_TYPE);
    }

    public vea getServerCertificateTypeExtension() {
        return (vea) this.extensions.e(HelloExtension.ExtensionType.SERVER_CERT_TYPE);
    }

    public vef getSupportedSignatureAlgorithmsExtension() {
        return (vef) this.extensions.e(HelloExtension.ExtensionType.SIGNATURE_ALGORITHMS);
    }

    public MaxFragmentLengthExtension getMaxFragmentLengthExtension() {
        return (MaxFragmentLengthExtension) this.extensions.e(HelloExtension.ExtensionType.MAX_FRAGMENT_LENGTH);
    }

    public vdx getRecordSizeLimitExtension() {
        return (vdx) this.extensions.e(HelloExtension.ExtensionType.RECORD_SIZE_LIMIT);
    }

    public veg getServerNameExtension() {
        return (veg) this.extensions.e(HelloExtension.ExtensionType.SERVER_NAME);
    }

    public vcq getConnectionIdExtension() {
        return (vcq) this.extensions.e(HelloExtension.ExtensionType.CONNECTION_ID);
    }

    public boolean hasExtendedMasterSecretExtension() {
        return this.extensions.e(HelloExtension.ExtensionType.EXTENDED_MASTER_SECRET) != null;
    }
}

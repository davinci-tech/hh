package defpackage;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import javax.crypto.SecretKey;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.CertificateRequest;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.ChangeCipherSpecMessage;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.ECDHServerKeyExchange;
import org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.Handshaker;
import org.eclipse.californium.scandium.dtls.HelloExtension;
import org.eclipse.californium.scandium.dtls.MaxFragmentLengthExtension;
import org.eclipse.californium.scandium.dtls.RecordLayer;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.SupportedPointFormatsExtension;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.PseudoRandomFunction;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public class vci extends Handshaker {

    /* renamed from: a, reason: collision with root package name */
    protected vcs f17663a;
    protected byte[] c;
    protected vck d;
    protected final List<XECDHECryptography.SupportedGroup> f;
    protected final List<CertificateType> g;
    protected final MaxFragmentLengthExtension.Length h;
    protected final List<CertificateType> i;
    protected final List<SignatureAndHashAlgorithm> j;
    private boolean k;
    private vdu l;
    protected final boolean n;
    private CertificateRequest o;
    private boolean p;
    private ECDHServerKeyExchange q;
    private final Integer r;
    private final boolean s;
    private final List<CipherSuite> t;
    protected static final vdj[] e = {new vdj(HandshakeType.HELLO_VERIFY_REQUEST, true), new vdj(HandshakeType.SERVER_HELLO)};
    protected static final vdj[] b = {new vdj(HandshakeType.CERTIFICATE), new vdj(HandshakeType.SERVER_KEY_EXCHANGE), new vdj(HandshakeType.CERTIFICATE_REQUEST, true), new vdj(HandshakeType.SERVER_HELLO_DONE), new vdj(ContentType.CHANGE_CIPHER_SPEC), new vdj(HandshakeType.FINISHED)};
    private static final vdj[] m = {new vdj(HandshakeType.SERVER_KEY_EXCHANGE, true), new vdj(HandshakeType.SERVER_HELLO_DONE), new vdj(ContentType.CHANGE_CIPHER_SPEC), new vdj(HandshakeType.FINISHED)};

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public boolean isClient() {
        return true;
    }

    public vci(String str, RecordLayer recordLayer, ScheduledExecutorService scheduledExecutorService, vcm vcmVar, vcd vcdVar, boolean z) {
        super(0L, 0, recordLayer, scheduledExecutorService, vcmVar, vcdVar);
        this.l = vdu.f17688a;
        this.t = vcdVar.ab();
        this.f = vcdVar.ac();
        this.h = (MaxFragmentLengthExtension.Length) vcdVar.d(DtlsConfig.r);
        this.n = ((Boolean) vcdVar.d(DtlsConfig.aw)).booleanValue();
        this.i = vcdVar.ad();
        this.g = vcdVar.p();
        this.j = vcdVar.z();
        this.r = (Integer) vcdVar.d(DtlsConfig.ba);
        this.s = ((Boolean) vcdVar.d(DtlsConfig.bj)).booleanValue();
        this.k = z;
        getSession().a(str);
    }

    /* renamed from: vci$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f17664a;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HandshakeType.values().length];
            f17664a = iArr;
            try {
                iArr[HandshakeType.HELLO_VERIFY_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17664a[HandshakeType.SERVER_HELLO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17664a[HandshakeType.CERTIFICATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17664a[HandshakeType.SERVER_KEY_EXCHANGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17664a[HandshakeType.CERTIFICATE_REQUEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f17664a[HandshakeType.SERVER_HELLO_DONE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f17664a[HandshakeType.FINISHED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[CipherSuite.KeyExchangeAlgorithm.values().length];
            e = iArr2;
            try {
                iArr2[CipherSuite.KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                e[CipherSuite.KeyExchangeAlgorithm.PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                e[CipherSuite.KeyExchangeAlgorithm.ECDHE_PSK.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void doProcessMessage(HandshakeMessage handshakeMessage) throws vdb {
        switch (AnonymousClass5.f17664a[handshakeMessage.getMessageType().ordinal()]) {
            case 1:
                e((vdp) handshakeMessage);
                return;
            case 2:
                a((vee) handshakeMessage);
                return;
            case 3:
                a((vce) handshakeMessage);
                return;
            case 4:
                int i = AnonymousClass5.e[getSession().f().ordinal()];
                if (i == 1) {
                    b((vcx) handshakeMessage);
                    return;
                } else {
                    if (i != 2) {
                        if (i == 3) {
                            this.q = (vda) handshakeMessage;
                            return;
                        }
                        throw new vdb(String.format("Unsupported key exchange algorithm %s", getSession().f().name()), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
                    }
                    return;
                }
            case 5:
                b((CertificateRequest) handshakeMessage);
                return;
            case 6:
                c();
                return;
            case 7:
                d((vde) handshakeMessage);
                return;
            default:
                throw new vdb(String.format("Received unexpected handshake message [%s] from peer %s", handshakeMessage.getMessageType(), this.peerToLog), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
        }
    }

    private void d(vde vdeVar) throws vdb {
        verifyFinished(vdeVar, this.c);
        contextEstablished();
        handshakeCompleted();
    }

    protected void e(vdp vdpVar) {
        this.handshakeMessages.clear();
        if (CipherSuite.containsEccBasedCipherSuite(this.d.d())) {
            expectEcc();
        }
        this.d.c(vdpVar.a());
        this.flightNumber = 3;
        vcs createFlight = createFlight();
        wrapMessage(createFlight, this.d);
        sendFlight(createFlight);
        setExpectedStates(e);
    }

    protected void a(vee veeVar) throws vdb {
        vdu protocolVersion = veeVar.getProtocolVersion();
        if (!protocolVersion.equals(vdu.f17688a)) {
            throw new vdb("The client only supports DTLS v1.2, not " + protocolVersion + "!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.PROTOCOL_VERSION));
        }
        this.serverRandom = veeVar.getRandom();
        vct session = getSession();
        session.e(veeVar.getSessionId());
        session.e(protocolVersion);
        CipherSuite b2 = veeVar.b();
        if (!this.t.contains(b2)) {
            throw new vdb("Server wants to use not supported cipher suite " + b2, new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        session.d(b2);
        CompressionMethod a2 = veeVar.a();
        if (a2 != CompressionMethod.NULL) {
            throw new vdb("Server wants to use not supported compression method " + a2, new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        session.a(veeVar.a());
        b(veeVar);
        if (supportsConnectionId()) {
            c(veeVar.getConnectionIdExtension());
        }
        if (veeVar.hasExtendedMasterSecretExtension()) {
            session.e(true);
        } else if (this.extendedMasterSecretMode == ExtendedMasterSecretMode.REQUIRED) {
            throw new vdb("Extended Master Secret required!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
        }
        session.b(veeVar.getServerNameExtension() != null);
        setExpectedStates(b2.requiresServerCertificateMessage() ? b : m);
    }

    protected void c(vcq vcqVar) throws vdb {
        if (vcqVar != null) {
            vcp a2 = vcqVar.a();
            vcn dtlsContext = getDtlsContext();
            dtlsContext.d(a2);
            dtlsContext.a(getReadConnectionId());
            dtlsContext.e(vcqVar.c());
        }
    }

    protected void b(vee veeVar) throws vdb {
        vdh extensions = veeVar.getExtensions();
        if (extensions != null && !extensions.c()) {
            vdh extensions2 = this.d.getExtensions();
            if (extensions2 == null || extensions2.c()) {
                throw new vdb("Server wants extensions, but client not!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNSUPPORTED_EXTENSION));
            }
            for (HelloExtension helloExtension : extensions.e()) {
                if (extensions2.e(helloExtension.getType()) == null) {
                    throw new vdb("Server wants " + helloExtension.getType() + ", but client didn't propose it!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNSUPPORTED_EXTENSION));
                }
            }
        }
        SupportedPointFormatsExtension supportedPointFormatsExtension = veeVar.getSupportedPointFormatsExtension();
        if (supportedPointFormatsExtension != null && !supportedPointFormatsExtension.b(SupportedPointFormatsExtension.ECPointFormat.UNCOMPRESSED)) {
            throw new vdb("Server wants to use only not supported EC point formats!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        vct session = getSession();
        vdx recordSizeLimitExtension = veeVar.getRecordSizeLimitExtension();
        if (recordSizeLimitExtension != null) {
            session.c(recordSizeLimitExtension.a());
        }
        MaxFragmentLengthExtension maxFragmentLengthExtension = veeVar.getMaxFragmentLengthExtension();
        if (maxFragmentLengthExtension != null) {
            if (recordSizeLimitExtension != null) {
                throw new vdb("Server wants to use record size limit and max. fragment size", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
            }
            MaxFragmentLengthExtension.Length d = maxFragmentLengthExtension.d();
            if (this.h == d) {
                session.b(d.length());
            } else {
                throw new vdb("Server wants to use other max. fragment size than proposed", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
            }
        }
        vea serverCertificateTypeExtension = veeVar.getServerCertificateTypeExtension();
        if (serverCertificateTypeExtension != null) {
            CertificateType certificateType = serverCertificateTypeExtension.getCertificateType();
            if (!c(certificateType, this.i)) {
                throw new vdb("Server wants to use not supported server certificate type " + certificateType, new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
            }
            session.e(certificateType);
        }
        vch clientCertificateTypeExtension = veeVar.getClientCertificateTypeExtension();
        if (clientCertificateTypeExtension != null) {
            CertificateType certificateType2 = clientCertificateTypeExtension.getCertificateType();
            if (!c(certificateType2, this.g)) {
                throw new vdb("Server wants to use not supported client certificate type " + certificateType2, new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
            }
            session.c(certificateType2);
        }
    }

    private void a(vce vceVar) throws vdb {
        if (vceVar.b()) {
            this.LOGGER.debug("Certificate validation failed: empty server certificate!");
            throw new vdb("Empty server certificate!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
        }
        verifyCertificate(vceVar, this.s);
    }

    private void b(vcx vcxVar) throws vdb {
        vcxVar.c(this.otherPeersPublicKey, this.clientRandom, this.serverRandom);
        this.q = vcxVar;
        setOtherPeersSignatureVerified();
    }

    private void b(CertificateRequest certificateRequest) throws vdb {
        this.o = certificateRequest;
        requestCertificateIdentity(certificateRequest.a(), getServerNames(), this.o.d(), this.o.c(), null);
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void processCertificateIdentityAvailable() throws vdb {
        if (this.p) {
            e();
        }
    }

    private void c() throws vdb {
        this.p = true;
        if (this.o == null || this.certificateIdentityAvailable) {
            e();
        }
    }

    private void e() throws vdb {
        XECDHECryptography xECDHECryptography;
        SecretKey a2;
        byte[] g;
        this.flightNumber += 2;
        vcs createFlight = createFlight();
        this.f17663a = createFlight;
        d(createFlight);
        vct session = getSession();
        CipherSuite.KeyExchangeAlgorithm f = session.f();
        if (CipherSuite.KeyExchangeAlgorithm.ECDHE_PSK == f || CipherSuite.KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN == f) {
            try {
                XECDHECryptography.SupportedGroup supportedGroup = this.q.getSupportedGroup();
                if (this.f.contains(supportedGroup)) {
                    xECDHECryptography = new XECDHECryptography(supportedGroup);
                    a2 = xECDHECryptography.a(this.q.getEncodedPoint());
                    g = xECDHECryptography.g();
                    session.e(supportedGroup);
                } else {
                    throw new vdb("Cannot process handshake message, ec-group not offered! ", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
                }
            } catch (GeneralSecurityException e2) {
                throw new vdb("Cannot process handshake message, caused by " + e2.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER), e2);
            }
        } else {
            xECDHECryptography = null;
            a2 = null;
            g = null;
        }
        int i = AnonymousClass5.e[f.ordinal()];
        if (i == 1) {
            wrapMessage(this.f17663a, new vcy(g));
            SecretKey b2 = PseudoRandomFunction.b(session.e().getThreadLocalPseudoRandomFunctionMac(), a2, generateMasterSecretSeed(), session.s());
            applyMasterSecret(b2);
            vfh.e(b2);
            processMasterSecret();
        } else if (i == 2) {
            vdt b3 = b();
            this.LOGGER.trace("Using PSK identity: {}", b3);
            wrapMessage(this.f17663a, new vds(b3));
            requestPskSecretResult(b3, null, generateMasterSecretSeed());
        } else if (i == 3) {
            vdt b4 = b();
            this.LOGGER.trace("Using ECDHE PSK identity: {}", b4);
            wrapMessage(this.f17663a, new vcz(b4, g));
            requestPskSecretResult(b4, a2, generateMasterSecretSeed());
        }
        vfh.a(xECDHECryptography);
        vfh.e(a2);
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void processMasterSecret() throws vdb {
        if (!isExpectedStates(b) || this.otherPeersCertificateVerified) {
            a();
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void processCertificateVerified() throws vdb {
        if (hasMasterSecret()) {
            a();
        }
    }

    protected void a() throws vdb {
        vct session = getSession();
        if (session.e().isEccBased()) {
            expectEcc();
        }
        SignatureAndHashAlgorithm r = session.r();
        if (r != null) {
            wrapMessage(this.f17663a, new vcj(r, this.privateKey, this.handshakeMessages));
        }
        wrapMessage(this.f17663a, new ChangeCipherSpecMessage());
        setCurrentWriteState();
        MessageDigest handshakeMessageDigest = getHandshakeMessageDigest();
        MessageDigest cloneMessageDigest = cloneMessageDigest(handshakeMessageDigest);
        vde createFinishedMessage = createFinishedMessage(handshakeMessageDigest.digest());
        wrapMessage(this.f17663a, createFinishedMessage);
        cloneMessageDigest.update(createFinishedMessage.toByteArray());
        this.c = cloneMessageDigest.digest();
        sendFlight(this.f17663a);
        expectChangeCipherSpecMessage();
    }

    private void d(vcs vcsVar) {
        SignatureAndHashAlgorithm d;
        if (this.o != null) {
            List<SignatureAndHashAlgorithm> list = this.j;
            if (list.isEmpty()) {
                list = SignatureAndHashAlgorithm.c;
            }
            CertificateType p = getSession().p();
            if (CertificateType.RAW_PUBLIC_KEY == p) {
                PublicKey publicKey = this.publicKey;
                if (publicKey != null) {
                    d = this.o.a(publicKey, list);
                    if (d != null) {
                        r3 = new vce(publicKey);
                        if (this.LOGGER.isDebugEnabled()) {
                            this.LOGGER.debug("sending CERTIFICATE message with client RawPublicKey [{}] to server", vcb.b(publicKey.getEncoded()));
                        }
                    }
                }
                d = null;
            } else if (CertificateType.X_509 == p) {
                if (this.certificateChain != null) {
                    d = this.o.d(this.certificateChain, list);
                    if (d != null) {
                        vce vceVar = new vce(this.certificateChain, this.n ? this.o.a() : null);
                        d = vceVar.b() ? null : d;
                        r3 = vceVar;
                    }
                }
                d = null;
            } else {
                throw new IllegalArgumentException("Certificate type " + p + " not supported!");
            }
            if (r3 == null && d == null) {
                r3 = new vce();
            }
            wrapMessage(vcsVar, r3);
            getSession().c(d);
        }
    }

    protected static boolean c(CertificateType certificateType, List<CertificateType> list) {
        if (list != null) {
            return list.contains(certificateType);
        }
        return certificateType == CertificateType.X_509;
    }

    public void d() throws vdb {
        handshakeStarted();
        vck vckVar = new vck(this.l, this.t, this.j, this.g, this.i, this.f);
        if (CipherSuite.containsEccBasedCipherSuite(vckVar.d())) {
            expectEcc();
        }
        this.clientRandom = vckVar.getRandom();
        vckVar.e(CompressionMethod.NULL);
        if (this.extendedMasterSecretMode != ExtendedMasterSecretMode.NONE) {
            vckVar.addExtension(vdf.b);
        }
        e(vckVar);
        b(vckVar);
        a(vckVar);
        c(vckVar);
        this.flightNumber = 1;
        this.d = vckVar;
        vcs createFlight = createFlight();
        wrapMessage(createFlight, vckVar);
        sendFlight(createFlight);
        setExpectedStates(e);
    }

    protected void b(vck vckVar) {
        if (this.recordSizeLimit != null) {
            vckVar.addExtension(new vdx(this.recordSizeLimit.intValue()));
            this.LOGGER.debug("Indicating record size limit [{}] to server [{}]", this.recordSizeLimit, this.peerToLog);
        }
    }

    protected void a(vck vckVar) {
        if (this.h != null) {
            vckVar.addExtension(new MaxFragmentLengthExtension(this.h));
            this.LOGGER.debug("Indicating max. fragment length [{}] to server [{}]", this.h, this.peerToLog);
        }
    }

    protected void e(vck vckVar) {
        vcp vcpVar;
        HelloExtension.ExtensionType extensionType;
        if (supportsConnectionId()) {
            if (this.connectionIdGenerator.useConnectionId()) {
                vcpVar = getConnection().c();
            } else {
                vcpVar = vcp.b;
            }
            Integer num = this.r;
            if (num != null) {
                extensionType = HelloExtension.ExtensionType.getExtensionTypeById(num.intValue());
            } else {
                extensionType = HelloExtension.ExtensionType.CONNECTION_ID;
            }
            vckVar.addExtension(vcq.b(vcpVar, extensionType));
        }
    }

    protected void c(vck vckVar) {
        vfe serverNames = getServerNames();
        if (serverNames != null) {
            this.LOGGER.debug("adding SNI extension to CLIENT_HELLO message [{}]", getSession().h());
            vckVar.addExtension(veg.e(serverNames));
        }
    }

    protected vdt b() throws vdb {
        vfe serverNames = getServerNames();
        if (serverNames != null && !getSession().q()) {
            this.LOGGER.warn("client is configured to use SNI but server does not support it, PSK authentication is likely to fail");
        }
        vdt identity = this.advancedPskStore.getIdentity(getPeerAddress(), serverNames);
        if (identity != null) {
            return identity;
        }
        AlertMessage alertMessage = new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR);
        if (serverNames != null) {
            throw new vdb(String.format("No Identity found for peer [address: %s, virtual host: %s]", this.peerToLog, getSession().h()), alertMessage);
        }
        throw new vdb(String.format("No Identity found for peer [address: %s]", this.peerToLog), alertMessage);
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public boolean isProbing() {
        return this.k;
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void resetProbing() {
        this.k = false;
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public boolean isRemovingConnection() {
        return !this.k && super.isRemovingConnection();
    }
}

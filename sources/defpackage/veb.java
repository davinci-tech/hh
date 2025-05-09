package defpackage;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import org.eclipse.californium.elements.config.CertificateAuthenticationMode;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.CertificateRequest;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.CertificateTypeExtension;
import org.eclipse.californium.scandium.dtls.ChangeCipherSpecMessage;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.Handshaker;
import org.eclipse.californium.scandium.dtls.MaxFragmentLengthExtension;
import org.eclipse.californium.scandium.dtls.RecordLayer;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.SupportedPointFormatsExtension;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuiteParameters;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuiteSelector;
import org.eclipse.californium.scandium.dtls.cipher.PseudoRandomFunction;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class veb extends Handshaker {
    private final Logger d;
    private vcj f;
    private XECDHECryptography g;
    private final CertificateAuthenticationMode h;
    private final CipherSuiteSelector i;
    private CipherSuiteParameters j;
    private vdt k;
    private vck l;
    private final List<CipherSuite> m;
    private final List<CipherSuite.CertificateKeyAlgorithm> n;
    private final boolean o;
    private final List<CertificateType> p;
    private final boolean q;
    private final List<CertificateType> r;
    private final List<XECDHECryptography.SupportedGroup> s;
    private final List<SignatureAndHashAlgorithm> t;
    private final boolean w;
    private final boolean x;
    private static final vdj[] b = {new vdj(HandshakeType.CLIENT_HELLO)};

    /* renamed from: a, reason: collision with root package name */
    private static final vdj[] f17690a = {new vdj(HandshakeType.CERTIFICATE), new vdj(HandshakeType.CLIENT_KEY_EXCHANGE), new vdj(HandshakeType.CERTIFICATE_VERIFY), new vdj(ContentType.CHANGE_CIPHER_SPEC), new vdj(HandshakeType.FINISHED)};
    private static final vdj[] c = {new vdj(HandshakeType.CLIENT_KEY_EXCHANGE), new vdj(ContentType.CHANGE_CIPHER_SPEC), new vdj(HandshakeType.FINISHED)};
    protected static final vdj[] e = {new vdj(HandshakeType.CLIENT_KEY_EXCHANGE), new vdj(ContentType.CHANGE_CIPHER_SPEC), new vdj(HandshakeType.FINISHED)};

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public boolean isClient() {
        return false;
    }

    public veb(long j, int i, RecordLayer recordLayer, ScheduledExecutorService scheduledExecutorService, vcm vcmVar, vcd vcdVar) {
        super(j, i, recordLayer, scheduledExecutorService, vcmVar, vcdVar);
        this.d = vha.d(this.LOGGER.getName() + ".negotiation");
        this.i = vcdVar.j();
        this.m = vcdVar.ab();
        this.s = vcdVar.ac();
        this.h = (CertificateAuthenticationMode) vcdVar.d(DtlsConfig.f);
        this.x = ((Boolean) vcdVar.d(DtlsConfig.ar)).booleanValue();
        boolean booleanValue = ((Boolean) vcdVar.d(DtlsConfig.bg)).booleanValue();
        this.q = booleanValue;
        this.w = booleanValue && ((Boolean) vcdVar.d(DtlsConfig.be)).booleanValue();
        this.r = vcdVar.ad();
        this.p = vcdVar.p();
        this.t = vcdVar.z();
        this.n = vcdVar.u();
        this.o = ((Boolean) vcdVar.d(DtlsConfig.av)).booleanValue();
        setExpectedStates(b);
    }

    /* renamed from: veb$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f17691a;
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HandshakeType.values().length];
            f17691a = iArr;
            try {
                iArr[HandshakeType.CLIENT_HELLO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17691a[HandshakeType.CERTIFICATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17691a[HandshakeType.CLIENT_KEY_EXCHANGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17691a[HandshakeType.CERTIFICATE_VERIFY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17691a[HandshakeType.FINISHED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[CipherSuite.KeyExchangeAlgorithm.values().length];
            d = iArr2;
            try {
                iArr2[CipherSuite.KeyExchangeAlgorithm.PSK.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                d[CipherSuite.KeyExchangeAlgorithm.ECDHE_PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                d[CipherSuite.KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void doProcessMessage(HandshakeMessage handshakeMessage) throws vdb {
        int i = AnonymousClass4.f17691a[handshakeMessage.getMessageType().ordinal()];
        if (i == 1) {
            handshakeStarted();
            b((vck) handshakeMessage);
            return;
        }
        if (i == 2) {
            b((vce) handshakeMessage);
            return;
        }
        if (i != 3) {
            if (i != 4) {
                if (i == 5) {
                    e((vde) handshakeMessage);
                    return;
                }
                throw new vdb(String.format("Received unexpected %s message from peer %s", handshakeMessage.getMessageType(), this.peerToLog), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
            }
            d((vcj) handshakeMessage);
            if (hasMasterSecret() && this.otherPeersCertificateVerified) {
                expectChangeCipherSpecMessage();
                return;
            }
            return;
        }
        int i2 = AnonymousClass4.d[getSession().f().ordinal()];
        if (i2 == 1) {
            a((vds) handshakeMessage);
            return;
        }
        if (i2 == 2) {
            b((vcz) handshakeMessage);
        } else {
            if (i2 != 3) {
                return;
            }
            SecretKey d = d((vcy) handshakeMessage);
            applyMasterSecret(d);
            vfh.e(d);
            processMasterSecret();
        }
    }

    protected void b() {
        vcs createFlight = createFlight();
        createFlight.n();
        sendFlight(createFlight);
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void processMasterSecret() {
        if (isExpectedStates(e) || isExpectedStates(c) || (isExpectedStates(f17690a) && this.otherPeersCertificateVerified && this.f != null)) {
            expectChangeCipherSpecMessage();
        }
    }

    private void b(vce vceVar) throws vdb {
        if (vceVar.b()) {
            if (this.h == CertificateAuthenticationMode.NEEDED) {
                this.LOGGER.debug("Client authentication failed: missing certificate!");
                throw new vdb("Client Certificate required!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
            }
            setExpectedStates(c);
            return;
        }
        verifyCertificate(vceVar, false);
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void processCertificateVerified() {
        if (!hasMasterSecret() || this.f == null) {
            return;
        }
        expectChangeCipherSpecMessage();
    }

    private void d(vcj vcjVar) throws vdb {
        this.f = vcjVar;
        this.handshakeMessages.remove(this.handshakeMessages.size() - 1);
        vcjVar.c(this.otherPeersPublicKey, this.handshakeMessages);
        this.handshakeMessages.add(vcjVar);
        if (setOtherPeersSignatureVerified() && hasMasterSecret()) {
            expectChangeCipherSpecMessage();
        }
    }

    private void e(vde vdeVar) throws vdb {
        if (this.h == CertificateAuthenticationMode.NEEDED && isExpectedStates(c)) {
            throw new vdb("Client did not send required authentication messages.", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
        }
        this.flightNumber += 2;
        vcs createFlight = createFlight();
        MessageDigest handshakeMessageDigest = getHandshakeMessageDigest();
        MessageDigest cloneMessageDigest = cloneMessageDigest(handshakeMessageDigest);
        verifyFinished(vdeVar, handshakeMessageDigest.digest());
        wrapMessage(createFlight, new ChangeCipherSpecMessage());
        setCurrentWriteState();
        cloneMessageDigest.update(vdeVar.toByteArray());
        wrapMessage(createFlight, createFinishedMessage(cloneMessageDigest.digest()));
        sendLastFlight(createFlight);
        contextEstablished();
    }

    protected void b(vck vckVar) throws vdb {
        a(vckVar.getProtocolVersion());
        if (!vckVar.e().contains(CompressionMethod.NULL)) {
            throw new vdb("Client does not support NULL compression method", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
        }
        List<CipherSuite> c2 = c(vckVar);
        if (c2.isEmpty()) {
            this.LOGGER.trace("Server cipher suites: {}", this.m);
            throw new vdb("Client does not propose a common cipher suite", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
        }
        if (this.q && !this.w && !vckVar.i()) {
            vej n = getSession().n();
            if (n.d() || !n.equals(vckVar.getSessionId())) {
                ArrayList arrayList = new ArrayList();
                for (CipherSuite cipherSuite : c2) {
                    if (cipherSuite.isPskBased()) {
                        arrayList.add(cipherSuite);
                    }
                }
                c2 = arrayList;
            }
        }
        List<CertificateType> c3 = c(vckVar.getServerCertificateTypeExtension());
        List<CertificateType> b2 = b(vckVar.getClientCertificateTypeExtension());
        List<XECDHECryptography.SupportedGroup> e2 = e(vckVar.a());
        List<SignatureAndHashAlgorithm> d = d(vckVar.getSupportedSignatureAlgorithmsExtension());
        SupportedPointFormatsExtension.ECPointFormat a2 = a(vckVar.getSupportedPointFormatsExtension());
        veg serverNameExtension = vckVar.getServerNameExtension();
        if (serverNameExtension != null) {
            if (this.sniEnabled) {
                vct session = getSession();
                session.b(serverNameExtension.b());
                session.b(true);
                this.LOGGER.debug("using server name indication received from peer [{}]", this.peerToLog);
            } else {
                this.LOGGER.debug("client [{}] included SNI in HELLO but SNI support is disabled", this.peerToLog);
            }
        }
        this.j = new CipherSuiteParameters(null, null, this.h, c2, c3, b2, e2, d, a2);
        if (CipherSuite.containsCipherSuiteRequiringCertExchange(c2)) {
            this.l = vckVar;
            if (requestCertificateIdentity(null, getServerNames(), CipherSuite.getCertificateKeyAlgorithms(c2), d, e2)) {
                b();
                return;
            }
            return;
        }
        e(vckVar);
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void processCertificateIdentityAvailable() throws vdb {
        this.j = new CipherSuiteParameters(this.publicKey, this.certificateChain, this.j);
        vck vckVar = this.l;
        this.l = null;
        e(vckVar);
    }

    protected void e(vck vckVar) throws vdb {
        d(vckVar);
        this.flightNumber = vckVar.i() ? 4 : 2;
        vcs createFlight = createFlight();
        b(vckVar, createFlight);
        e(createFlight);
        b(createFlight);
        setExpectedStates(d(createFlight) ? f17690a : e);
        wrapMessage(createFlight, new veh());
        sendFlight(createFlight);
    }

    private void b(vck vckVar, vcs vcsVar) throws vdb {
        vdu a2 = a(vckVar.getProtocolVersion());
        this.clientRandom = vckVar.getRandom();
        vct session = getSession();
        vej vejVar = ((!this.extendedMasterSecretMode.is(ExtendedMasterSecretMode.ENABLED) || vckVar.hasExtendedMasterSecretExtension()) && this.x) ? new vej() : vej.a();
        session.e(vejVar);
        session.e(a2);
        session.a(CompressionMethod.NULL);
        vee veeVar = new vee(a2, vejVar, session.e(), session.c());
        a(vckVar, veeVar);
        if (veeVar.b().isEccBased()) {
            expectEcc();
        }
        wrapMessage(vcsVar, veeVar);
        this.serverRandom = veeVar.getRandom();
    }

    private void e(vcs vcsVar) {
        vce vceVar;
        vct session = getSession();
        if (session.e().requiresServerCertificateMessage()) {
            CertificateType p = session.p();
            if (CertificateType.RAW_PUBLIC_KEY == p) {
                vceVar = new vce(this.j.h());
            } else if (CertificateType.X_509 == p) {
                vceVar = new vce(this.j.c());
            } else {
                throw new IllegalArgumentException("Certificate type " + p + " not supported!");
            }
            wrapMessage(vcsVar, vceVar);
        }
    }

    private void b(vcs vcsVar) throws vdb {
        HandshakeMessage vdaVar;
        vct session = getSession();
        CipherSuite.KeyExchangeAlgorithm f = session.f();
        if (CipherSuite.KeyExchangeAlgorithm.ECDHE_PSK == f || CipherSuite.KeyExchangeAlgorithm.EC_DIFFIE_HELLMAN == f) {
            try {
                XECDHECryptography.SupportedGroup m = this.j.m();
                this.g = new XECDHECryptography(m);
                session.e(m);
            } catch (GeneralSecurityException e2) {
                throw new vdb("Cannot process handshake message, caused by " + e2.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER), e2);
            }
        }
        int i = AnonymousClass4.d[f.ordinal()];
        if (i != 2) {
            vdaVar = i != 3 ? null : new vcx(session.r(), this.g, this.privateKey, this.clientRandom, this.serverRandom);
        } else {
            vdaVar = new vda(vdt.e, this.g);
        }
        if (vdaVar != null) {
            wrapMessage(vcsVar, vdaVar);
        }
    }

    private boolean d(vcs vcsVar) {
        vct session = getSession();
        CertificateType t = session.t();
        if (!this.h.useCertificateRequest() || !session.e().requiresServerCertificateMessage() || t == null) {
            return false;
        }
        CertificateRequest certificateRequest = new CertificateRequest();
        List<SignatureAndHashAlgorithm> list = this.t;
        List<CipherSuite.CertificateKeyAlgorithm> list2 = this.n;
        if (CertificateType.X_509 == t) {
            certificateRequest.d(list);
            if (this.certificateVerifier != null) {
                certificateRequest.c(this.certificateVerifier.getAcceptedIssuers());
            }
        } else if (CertificateType.RAW_PUBLIC_KEY == t) {
            CipherSuite.CertificateKeyAlgorithm algorithm = CipherSuite.CertificateKeyAlgorithm.getAlgorithm(this.publicKey);
            if (list2.get(0) != algorithm && list2.contains(algorithm)) {
                ArrayList arrayList = new ArrayList(list2);
                arrayList.remove(algorithm);
                arrayList.add(0, algorithm);
                list2 = arrayList;
            }
            list = SignatureAndHashAlgorithm.a(list, list2);
            certificateRequest.d(list);
        }
        this.LOGGER.trace("Certificate Type: {}", t);
        this.LOGGER.trace("Signature and hash algorithms {}/{}", list, this.t);
        this.LOGGER.trace("Certificate key algorithms {}/{}", list2, this.n);
        for (CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm : list2) {
            if (SignatureAndHashAlgorithm.b(list, certificateKeyAlgorithm)) {
                certificateRequest.e(certificateKeyAlgorithm);
            }
        }
        wrapMessage(vcsVar, certificateRequest);
        return true;
    }

    private SecretKey d(vcy vcyVar) throws vdb {
        try {
            vct session = getSession();
            SecretKey a2 = this.g.a(vcyVar.a());
            SecretKey b2 = PseudoRandomFunction.b(session.e().getThreadLocalPseudoRandomFunctionMac(), a2, generateMasterSecretSeed(), session.s());
            vfh.e(a2);
            return b2;
        } catch (GeneralSecurityException e2) {
            throw new vdb("Cannot process handshake message, caused by " + e2.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER), e2);
        }
    }

    private void a(vds vdsVar) throws vdb {
        this.k = vdsVar.d();
        requestPskSecretResult(this.k, null, generateMasterSecretSeed());
    }

    private void b(vcz vczVar) throws vdb {
        SecretKey secretKey = null;
        try {
            try {
                this.k = vczVar.d();
                secretKey = this.g.a(vczVar.a());
                requestPskSecretResult(this.k, secretKey, generateMasterSecretSeed());
            } catch (GeneralSecurityException e2) {
                throw new vdb("Cannot process handshake message, caused by " + e2.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER), e2);
            }
        } finally {
            vfh.e(secretKey);
        }
    }

    protected void a(vck vckVar, vee veeVar) throws vdb {
        vcq connectionIdExtension;
        MaxFragmentLengthExtension maxFragmentLengthExtension;
        vea serverCertificateTypeExtension;
        CertificateType t;
        vch clientCertificateTypeExtension;
        vct session = getSession();
        if (vckVar.hasExtendedMasterSecretExtension()) {
            if (this.extendedMasterSecretMode != ExtendedMasterSecretMode.NONE) {
                session.e(true);
                veeVar.addExtension(vdf.b);
            }
        } else if (this.extendedMasterSecretMode == ExtendedMasterSecretMode.REQUIRED) {
            throw new vdb("Extended Master Secret required!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
        }
        if (session.e().requiresServerCertificateMessage()) {
            if (this.h.useCertificateRequest() && (t = session.t()) != null && (clientCertificateTypeExtension = vckVar.getClientCertificateTypeExtension()) != null && clientCertificateTypeExtension.contains(t)) {
                veeVar.addExtension(new vch(t));
            }
            CertificateType p = session.p();
            if (p != null && (serverCertificateTypeExtension = vckVar.getServerCertificateTypeExtension()) != null && serverCertificateTypeExtension.contains(p)) {
                veeVar.addExtension(new vea(p));
            }
        }
        if (session.e().isEccBased() && vckVar.getSupportedPointFormatsExtension() != null) {
            veeVar.addExtension(SupportedPointFormatsExtension.d);
        }
        vdx recordSizeLimitExtension = vckVar.getRecordSizeLimitExtension();
        if (recordSizeLimitExtension != null) {
            session.c(recordSizeLimitExtension.a());
            int j = this.recordSizeLimit == null ? session.j() : this.recordSizeLimit.intValue();
            veeVar.addExtension(new vdx(j));
            this.LOGGER.debug("Received record size limit [{} bytes] from peer [{}]", Integer.valueOf(j), this.peerToLog);
        }
        if (recordSizeLimitExtension == null && (maxFragmentLengthExtension = vckVar.getMaxFragmentLengthExtension()) != null) {
            session.b(maxFragmentLengthExtension.d().length());
            veeVar.addExtension(maxFragmentLengthExtension);
            this.LOGGER.debug("Negotiated max. fragment length [{} bytes] with peer [{}]", Integer.valueOf(maxFragmentLengthExtension.d().length()), this.peerToLog);
        }
        if (vckVar.getServerNameExtension() != null && this.sniEnabled) {
            veeVar.addExtension(veg.e());
        }
        if (!supportsConnectionId() || (connectionIdExtension = vckVar.getConnectionIdExtension()) == null) {
            return;
        }
        boolean c2 = connectionIdExtension.c();
        if (!c2 || this.o) {
            vcp readConnectionId = getReadConnectionId();
            veeVar.addExtension(vcq.b(readConnectionId, connectionIdExtension.getType()));
            vcn dtlsContext = getDtlsContext();
            dtlsContext.d(connectionIdExtension.a());
            dtlsContext.a(readConnectionId);
            dtlsContext.e(c2);
        }
    }

    private vdu a(vdu vduVar) throws vdb {
        if (vduVar.compareTo(vdu.f17688a) >= 0) {
            return vdu.f17688a;
        }
        throw new vdb("The server only supports DTLS v1.2, not " + vduVar + "!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.PROTOCOL_VERSION, vduVar.compareTo(vdu.c) < 0 ? vdu.c : vduVar));
    }

    private void d(vck vckVar) throws vdb {
        this.LOGGER.trace("Negotiate on: {}", this.j);
        if (this.i.select(this.j)) {
            this.LOGGER.debug("Negotiated: {}", this.j);
            vct session = getSession();
            CipherSuite f = this.j.f();
            session.d(f);
            if (f.requiresServerCertificateMessage()) {
                session.c(this.j.l());
                CertificateType k = this.j.k();
                if (k == null) {
                    throw new vdb("No common server certificate type!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNSUPPORTED_CERTIFICATE));
                }
                session.c(k);
                CertificateType n = this.j.n();
                if (this.h == CertificateAuthenticationMode.NEEDED) {
                    if (n == null) {
                        throw new vdb("No common client certificate type!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNSUPPORTED_CERTIFICATE));
                    }
                    session.e(n);
                } else if (this.h == CertificateAuthenticationMode.WANTED && n != null) {
                    session.e(n);
                }
            }
            this.LOGGER.debug("Negotiated cipher suite [{}] with peer [{}]", f.name(), this.peerToLog);
            return;
        }
        if (this.d.isDebugEnabled()) {
            this.d.debug("{}", vckVar);
            this.d.debug("{}", this.j.g());
            this.d.trace("Parameters: {}", this.j);
        }
        String i = this.j.i();
        if (i == null) {
            i = "Client proposed unsupported cipher suites or parameters only";
        }
        this.j = null;
        throw new vdb(i, new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
    }

    private List<XECDHECryptography.SupportedGroup> e(ven venVar) {
        ArrayList arrayList = new ArrayList();
        if (venVar == null) {
            arrayList.addAll(this.s);
        } else {
            for (XECDHECryptography.SupportedGroup supportedGroup : venVar.b()) {
                if (this.s.contains(supportedGroup)) {
                    arrayList.add(supportedGroup);
                }
            }
        }
        return arrayList;
    }

    private SupportedPointFormatsExtension.ECPointFormat a(SupportedPointFormatsExtension supportedPointFormatsExtension) {
        if (supportedPointFormatsExtension == null) {
            return SupportedPointFormatsExtension.ECPointFormat.UNCOMPRESSED;
        }
        if (supportedPointFormatsExtension.b(SupportedPointFormatsExtension.ECPointFormat.UNCOMPRESSED)) {
            return SupportedPointFormatsExtension.ECPointFormat.UNCOMPRESSED;
        }
        return null;
    }

    private List<SignatureAndHashAlgorithm> d(vef vefVar) {
        if (vefVar == null) {
            return new ArrayList(this.t);
        }
        return SignatureAndHashAlgorithm.e(vefVar.a(), this.t);
    }

    private List<CipherSuite> c(vck vckVar) {
        List<CipherSuite> list = this.m;
        CipherSuite e2 = getSession().e();
        if (e2.isValidForNegotiation()) {
            list = Arrays.asList(e2);
        }
        return vckVar.a(list);
    }

    private List<CertificateType> b(vch vchVar) {
        List list = this.r;
        Principal l = getSession().l();
        if (l != null) {
            list = new ArrayList();
            if (l instanceof vas) {
                list.add(CertificateType.RAW_PUBLIC_KEY);
            } else if (l instanceof vao) {
                list.add(CertificateType.X_509);
            }
        }
        return a(vchVar, (List<CertificateType>) list);
    }

    private List<CertificateType> c(vea veaVar) {
        return a(veaVar, this.p);
    }

    private static List<CertificateType> a(CertificateTypeExtension certificateTypeExtension, List<CertificateType> list) {
        if (list != null) {
            if (certificateTypeExtension != null) {
                return certificateTypeExtension.getCommonCertificateTypes(list);
            }
            if (list.contains(CertificateType.X_509)) {
                return CertificateTypeExtension.DEFAULT_X509;
            }
        }
        return CertificateTypeExtension.EMPTY;
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker, javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vfh.a(this.g);
        this.g = null;
    }
}

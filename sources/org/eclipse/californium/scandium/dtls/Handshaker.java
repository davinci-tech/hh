package org.eclipse.californium.scandium.dtls;

import defpackage.vaf;
import defpackage.vao;
import defpackage.vap;
import defpackage.var;
import defpackage.vas;
import defpackage.vbj;
import defpackage.vcb;
import defpackage.vcc;
import defpackage.vcd;
import defpackage.vce;
import defpackage.vcl;
import defpackage.vcm;
import defpackage.vcn;
import defpackage.vcp;
import defpackage.vcs;
import defpackage.vct;
import defpackage.vcu;
import defpackage.vdb;
import defpackage.vdc;
import defpackage.vdd;
import defpackage.vde;
import defpackage.vdg;
import defpackage.vdi;
import defpackage.vdj;
import defpackage.vdq;
import defpackage.vdt;
import defpackage.vdv;
import defpackage.vdy;
import defpackage.vdz;
import defpackage.vep;
import defpackage.vfe;
import defpackage.vff;
import defpackage.vfh;
import defpackage.vha;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import javax.security.auth.x500.X500Principal;
import org.eclipse.californium.elements.auth.ExtensiblePrincipal;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.SerialExecutor;
import org.eclipse.californium.scandium.auth.ApplicationLevelInfoSupplier;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.PseudoRandomFunction;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;
import org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore;
import org.eclipse.californium.scandium.dtls.x509.CertificateProvider;
import org.eclipse.californium.scandium.dtls.x509.NewAdvancedCertificateVerifier;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class Handshaker implements Destroyable {
    private final int additionalTimeoutForEcc;
    protected final AdvancedPskStore advancedPskStore;
    private ApplicationLevelInfoSupplier applicationLevelInfoSupplier;
    private final int backOffRetransmission;
    private Throwable cause;
    protected List<X509Certificate> certificateChain;
    protected boolean certificateIdentityAvailable;
    private boolean certificateIdentityPending;
    protected final CertificateProvider certificateIdentityProvider;
    private boolean certificateVerificationPending;
    protected final NewAdvancedCertificateVerifier certificateVerifier;
    private boolean changeCipherSuiteMessageExpected;
    protected vdv clientRandom;
    private vff clientWriteIV;
    private SecretKey clientWriteKey;
    private SecretKey clientWriteMACKey;
    private final vcm connection;
    protected final ConnectionIdGenerator connectionIdGenerator;
    private final vcn context;
    private boolean contextEstablished;
    private Object customArgument;
    private int deferredIncomingRecordsSize;
    private boolean destroyed;
    private boolean eccExpected;
    private vdj[] expectedStates;
    protected final ExtendedMasterSecretMode extendedMasterSecretMode;
    private long flightSendNanos;
    private volatile boolean generateClusterMacKeys;
    private boolean handshakeAborted;
    private boolean handshakeCompleted;
    private boolean handshakeFailed;
    private d inboundMessageBuffer;
    private final boolean ipv6;
    private SecretKey masterSecret;
    private byte[] masterSecretSeed;
    private final int maxDeferredProcessedIncomingRecordsSize;
    private final int maxDeferredProcessedOutgoingApplicationDataMessages;
    private final int maxFragmentedHandshakeMessageLength;
    private final int maxRetransmissionTimeout;
    private final int maxRetransmissions;
    private long nanosExpireTime;
    private final long nanosExpireTimeout;
    private int nextReceiveMessageSequence;
    private CertPath otherPeersCertPath;
    protected boolean otherPeersCertificateVerified;
    protected PublicKey otherPeersPublicKey;
    private boolean otherPeersSignatureVerified;
    private SecretKey otherSecret;
    private final InetSocketAddress peer;
    protected final Object peerToLog;
    protected PrivateKey privateKey;
    private boolean pskRequestPending;
    protected PublicKey publicKey;
    private vdy reassembledMessage;
    private final RecordLayer recordLayer;
    protected Integer recordSizeLimit;
    private final float retransmissionRandomFactor;
    private final int retransmissionTimeout;
    private final float retransmissionTimeoutScale;
    private Runnable retransmitFlight;
    private int sendMessageSequence;
    protected vdv serverRandom;
    private vff serverWriteIV;
    private SecretKey serverWriteKey;
    private SecretKey serverWriteMACKey;
    protected final boolean sniEnabled;
    private boolean statesChanged;
    private int statesIndex;
    private ScheduledFuture<?> timeoutLastFlight;
    private final ScheduledExecutorService timer;
    private final boolean useEarlyStopRetransmission;
    private Boolean useMultiHandshakeMessagesRecord;
    private Boolean useMultiRecordMessages;
    protected final boolean useTruncatedCertificatePathForVerification;
    protected final Logger LOGGER = vha.a(getClass());
    private final ReentrantLock recursionProtection = new ReentrantLock();
    protected int flightNumber = 0;
    private final List<vaf> deferredApplicationData = new ArrayList();
    private final List<vdz> nextEpochDeferredRecords = new ArrayList();
    private final AtomicReference<vcs> pendingFlight = new AtomicReference<>();
    protected final List<HandshakeMessage> handshakeMessages = new ArrayList();
    private final Set<SessionListener> sessionListeners = new LinkedHashSet();

    protected abstract void doProcessMessage(HandshakeMessage handshakeMessage) throws vdb;

    protected abstract boolean isClient();

    public boolean isProbing() {
        return false;
    }

    protected abstract void processCertificateIdentityAvailable() throws vdb;

    protected abstract void processCertificateVerified() throws vdb;

    protected abstract void processMasterSecret() throws vdb;

    public void resetProbing() {
    }

    public Handshaker(long j, int i, RecordLayer recordLayer, ScheduledExecutorService scheduledExecutorService, vcm vcmVar, vcd vcdVar) {
        this.sendMessageSequence = 0;
        this.nextReceiveMessageSequence = 0;
        if (recordLayer == null) {
            throw new NullPointerException("Record layer must not be null");
        }
        if (scheduledExecutorService == null) {
            throw new NullPointerException("Timer must not be null");
        }
        if (vcmVar == null) {
            throw new NullPointerException("Connection must not be null");
        }
        if (vcdVar == null) {
            throw new NullPointerException("Dtls Connector Config must not be null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Initial message sequence number must not be negative");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Initial record sequence number must not be negative");
        }
        this.sendMessageSequence = i;
        this.nextReceiveMessageSequence = i;
        this.context = new vcn(j);
        this.recordLayer = recordLayer;
        this.timer = scheduledExecutorService;
        this.connection = vcmVar;
        InetSocketAddress o = vcmVar.o();
        this.peer = o;
        this.peerToLog = vcb.b((SocketAddress) o);
        this.connectionIdGenerator = vcdVar.l();
        int c = vcdVar.c(DtlsConfig.am, TimeUnit.MILLISECONDS);
        this.retransmissionTimeout = c;
        int c2 = vcdVar.c(DtlsConfig.y, TimeUnit.MILLISECONDS);
        this.maxRetransmissionTimeout = c2;
        int c3 = vcdVar.c(DtlsConfig.f15899a, TimeUnit.MILLISECONDS);
        this.additionalTimeoutForEcc = c3;
        float floatValue = ((Float) vcdVar.d(DtlsConfig.an)).floatValue();
        this.retransmissionRandomFactor = floatValue;
        float floatValue2 = ((Float) vcdVar.d(DtlsConfig.ak)).floatValue();
        this.retransmissionTimeoutScale = floatValue2;
        this.backOffRetransmission = vcdVar.h().intValue();
        this.maxRetransmissions = ((Integer) vcdVar.d(DtlsConfig.w)).intValue();
        this.recordSizeLimit = (Integer) vcdVar.d(DtlsConfig.ag);
        this.maxFragmentedHandshakeMessageLength = ((Integer) vcdVar.d(DtlsConfig.t)).intValue();
        this.useMultiRecordMessages = (Boolean) vcdVar.d(DtlsConfig.bh);
        this.useMultiHandshakeMessagesRecord = (Boolean) vcdVar.d(DtlsConfig.bd);
        this.maxDeferredProcessedOutgoingApplicationDataMessages = ((Integer) vcdVar.d(DtlsConfig.p)).intValue();
        this.maxDeferredProcessedIncomingRecordsSize = ((Integer) vcdVar.d(DtlsConfig.q)).intValue();
        this.sniEnabled = ((Boolean) vcdVar.d(DtlsConfig.bf)).booleanValue();
        this.extendedMasterSecretMode = (ExtendedMasterSecretMode) vcdVar.d(DtlsConfig.o);
        this.useTruncatedCertificatePathForVerification = ((Boolean) vcdVar.d(DtlsConfig.ax)).booleanValue();
        this.useEarlyStopRetransmission = ((Boolean) vcdVar.d(DtlsConfig.bb)).booleanValue();
        this.certificateIdentityProvider = vcdVar.i();
        this.certificateVerifier = vcdVar.d();
        this.advancedPskStore = vcdVar.c();
        this.applicationLevelInfoSupplier = vcdVar.b();
        this.inboundMessageBuffer = new d();
        this.ipv6 = vcmVar.o().getAddress() instanceof Inet6Address;
        int round = Math.round(c * floatValue);
        int min = Math.min(CipherSuite.containsEccBasedCipherSuite(vcdVar.ab()) ? round + c3 : round, c2);
        int min2 = Math.min(Math.round(min * floatValue2), c2);
        for (int i2 = 0; i2 < this.maxRetransmissions; i2++) {
            min = vcs.d(min, this.retransmissionTimeoutScale, this.maxRetransmissionTimeout);
            min2 += min;
        }
        this.nanosExpireTimeout = TimeUnit.MILLISECONDS.toNanos(min2);
        addSessionListener(vcmVar.n());
    }

    class d {
        private SortedSet<vdz> b;
        private vdz d;

        private d() {
            this.d = null;
            this.b = new TreeSet(new Comparator<vdz>() { // from class: org.eclipse.californium.scandium.dtls.Handshaker.d.5
                @Override // java.util.Comparator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public int compare(vdz vdzVar, vdz vdzVar2) {
                    return Handshaker.compareRecords(vdzVar, vdzVar2);
                }
            });
        }

        boolean b() {
            return this.b.isEmpty();
        }

        vdz a() {
            vdz vdzVar;
            if (Handshaker.this.isChangeCipherSpecMessageExpected() && (vdzVar = this.d) != null) {
                this.d = null;
                return vdzVar;
            }
            while (!this.b.isEmpty()) {
                vdz first = this.b.first();
                int messageSeq = ((HandshakeMessage) first.c()).getMessageSeq();
                if (messageSeq > Handshaker.this.nextReceiveMessageSequence) {
                    break;
                }
                Handshaker.this.removeDeferredProcessedRecord(first, this.b);
                if (messageSeq == Handshaker.this.nextReceiveMessageSequence) {
                    return first;
                }
            }
            return null;
        }

        vdz c(vdz vdzVar) {
            int e = vdzVar.e();
            int b = Handshaker.this.context.b();
            if (e == b) {
                DTLSMessage c = vdzVar.c();
                int i = AnonymousClass4.f15902a[c.getContentType().ordinal()];
                if (i == 1) {
                    if (Handshaker.this.isChangeCipherSpecMessageExpected()) {
                        return vdzVar;
                    }
                    if (this.d == null) {
                        Handshaker.this.LOGGER.debug("Change Cipher Spec is not expected and therefore kept for later processing!");
                        this.d = vdzVar;
                        return null;
                    }
                    Handshaker.this.LOGGER.debug("Change Cipher Spec is received again!");
                    return null;
                }
                if (i == 2) {
                    HandshakeMessage handshakeMessage = (HandshakeMessage) c;
                    int messageSeq = handshakeMessage.getMessageSeq();
                    if (messageSeq == Handshaker.this.nextReceiveMessageSequence) {
                        return vdzVar;
                    }
                    if (messageSeq > Handshaker.this.nextReceiveMessageSequence) {
                        Handshaker.this.LOGGER.debug("Queued newer {} message from current epoch, message_seq [{}] > next_receive_seq [{}]", handshakeMessage.getMessageType(), Integer.valueOf(messageSeq), Integer.valueOf(Handshaker.this.nextReceiveMessageSequence));
                        Handshaker.this.addDeferredProcessedRecord(vdzVar, this.b);
                        return null;
                    }
                    Handshaker.this.LOGGER.debug("Discarding old {} message_seq [{}] < next_receive_seq [{}]", handshakeMessage.getMessageType(), Integer.valueOf(messageSeq), Integer.valueOf(Handshaker.this.nextReceiveMessageSequence));
                    return null;
                }
                Handshaker.this.LOGGER.info("Cannot process message of type [{}], discarding...", c.getContentType());
                return null;
            }
            throw new IllegalArgumentException("record epoch " + e + " doesn't match dtls context " + b);
        }
    }

    /* renamed from: org.eclipse.californium.scandium.dtls.Handshaker$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f15902a;

        static {
            int[] iArr = new int[ContentType.values().length];
            f15902a = iArr;
            try {
                iArr[ContentType.CHANGE_CIPHER_SPEC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15902a[ContentType.HANDSHAKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareRecords(vdz vdzVar, vdz vdzVar2) {
        if (vdzVar.e() != vdzVar2.e()) {
            throw new IllegalArgumentException("records with different epoch! " + vdzVar.e() + " != " + vdzVar2.e());
        }
        HandshakeMessage handshakeMessage = (HandshakeMessage) vdzVar.c();
        HandshakeMessage handshakeMessage2 = (HandshakeMessage) vdzVar2.c();
        if (handshakeMessage.getMessageSeq() < handshakeMessage2.getMessageSeq()) {
            return -1;
        }
        if (handshakeMessage.getMessageSeq() > handshakeMessage2.getMessageSeq()) {
            return 1;
        }
        if (vdzVar.h() < vdzVar2.h()) {
            return -1;
        }
        return vdzVar.h() > vdzVar2.h() ? 1 : 0;
    }

    public boolean isInboundMessageProcessed() {
        return this.inboundMessageBuffer.b();
    }

    public final void processMessage(vdz vdzVar) throws vdb {
        int b2 = this.context.b();
        if (b2 != vdzVar.e()) {
            this.LOGGER.debug("Discarding {} message with wrong epoch received from peer [{}]:{}{}", vdzVar.j(), vdzVar.i(), vcb.a(), vdzVar);
            throw new IllegalArgumentException("processing record with wrong epoch! " + vdzVar.e() + " expected " + b2);
        }
        if (this.pendingFlight.get() != null && this.flightSendNanos - vdzVar.f() > 0) {
            this.LOGGER.debug("Discarding {} message received from peer [{}] before last flight was sent:{}{}", vdzVar.j(), vdzVar.i(), vcb.a(), vdzVar);
            return;
        }
        vdz c = this.inboundMessageBuffer.c(vdzVar);
        if (c != null) {
            processNextMessages(c);
        }
    }

    private void processNextMessages(vdz vdzVar) throws vdb {
        if (this.recursionProtection.isHeldByCurrentThread()) {
            this.LOGGER.warn("Called from doProcessMessage, return immediately to process next message!", new Throwable("recursion-protection"));
            return;
        }
        try {
            int b2 = this.context.b();
            if (vdzVar == null) {
                vdzVar = this.inboundMessageBuffer.a();
            }
            int i = 0;
            while (vdzVar != null) {
                if (this.useMultiRecordMessages == null && vdzVar.l()) {
                    this.useMultiRecordMessages = true;
                }
                DTLSMessage c = vdzVar.c();
                if (c.getContentType() == ContentType.CHANGE_CIPHER_SPEC) {
                    expectMessage(c);
                    this.LOGGER.debug("Processing {} message from peer [{}]", c.getContentType(), this.peerToLog);
                    setCurrentReadState();
                    this.statesIndex++;
                    this.LOGGER.debug("Processed {} message from peer [{}]", c.getContentType(), this.peerToLog);
                } else if (c.getContentType() == ContentType.HANDSHAKE) {
                    boolean processNextHandshakeMessages = processNextHandshakeMessages(vdzVar.e(), i, (HandshakeMessage) c);
                    this.context.e(vdzVar.e(), vdzVar.h());
                    if (!processNextHandshakeMessages) {
                        break;
                    }
                } else {
                    throw new vdb(String.format("Received unexpected message type [%s] from peer %s", c.getContentType(), this.peerToLog), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
                }
                vdzVar = this.inboundMessageBuffer.a();
                if (b2 < this.context.b() && (vdzVar != null || !this.inboundMessageBuffer.b())) {
                    throw new vdb(String.format("Unexpected handshake message left from peer %s", this.peerToLog), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.HANDSHAKE_FAILURE));
                }
                i++;
            }
            if (b2 < this.context.b()) {
                SerialExecutor g = this.connection.g();
                List<vdz> takeDeferredRecordsOfNextEpoch = takeDeferredRecordsOfNextEpoch();
                if (this.deferredIncomingRecordsSize > 0) {
                    throw new vdb(String.format("Received message of next epoch left from peer %s", this.peerToLog), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR));
                }
                for (final vdz vdzVar2 : takeDeferredRecordsOfNextEpoch) {
                    if (g != null && !g.isShutdown()) {
                        try {
                            g.execute(new Runnable() { // from class: org.eclipse.californium.scandium.dtls.Handshaker.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    Handshaker.this.recordLayer.processRecord(vdzVar2, Handshaker.this.connection);
                                }
                            });
                        } catch (RejectedExecutionException e2) {
                            this.LOGGER.debug("Execution rejected while processing record [type: {}, peer: {}]", vdzVar2.j(), vdzVar2.i(), e2);
                        }
                    }
                    this.recordLayer.processRecord(vdzVar2, this.connection);
                }
            }
        } catch (RuntimeException e3) {
            this.LOGGER.warn("Cannot process handshake message from peer [{}] due to [{}]", this.peerToLog, e3.getMessage(), e3);
            throw new vdb("Cannot process handshake message, caused by " + e3.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR), e3);
        }
    }

    private boolean processNextHandshakeMessages(int i, int i2, HandshakeMessage handshakeMessage) throws vdb {
        if (this.recursionProtection.isHeldByCurrentThread()) {
            this.LOGGER.warn("Called from doProcessMessage, return immediately to process next message!", new Throwable("recursion-protection"));
            return false;
        }
        vcs vcsVar = this.pendingFlight.get();
        if (vcsVar != null) {
            this.LOGGER.debug("response for flight {} started", Integer.valueOf(vcsVar.b()));
            vcsVar.n();
        }
        while (handshakeMessage != null) {
            expectMessage(handshakeMessage);
            if (handshakeMessage.getMessageType() == HandshakeType.FINISHED && i == 0) {
                this.LOGGER.debug("FINISH with epoch 0 from peer [{}]!", this.peerToLog);
                throw new vdb("FINISH with epoch 0!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
            }
            if ((handshakeMessage instanceof vdc) && (handshakeMessage = reassembleFragment((vdc) handshakeMessage)) == null) {
                break;
            }
            if (handshakeMessage instanceof vdd) {
                handshakeMessage = HandshakeMessage.fromGenericHandshakeMessage((vdd) handshakeMessage, getParameter());
            }
            if (this.timeoutLastFlight != null) {
                if (vcsVar == null) {
                    Throwable th = this.cause;
                    if (th != null) {
                        this.LOGGER.error("last flight missing, handshake already failed! {}", handshakeMessage, th);
                    } else if (i2 == 0) {
                        this.LOGGER.error("last flight missing, resend failed! {}", handshakeMessage);
                    } else {
                        this.LOGGER.error("last flight missing, resend for buffered message {} failed! {}", Integer.valueOf(i2), handshakeMessage);
                    }
                    return false;
                }
                this.LOGGER.debug("Received ({}) FINISHED message again, retransmitting last flight...", this.peerToLog);
                vcsVar.g();
                sendFlight(vcsVar);
                return false;
            }
            if (this.LOGGER.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Processing %s message from peer [%s], seqn: [%d]", handshakeMessage.getMessageType(), this.peerToLog, Integer.valueOf(handshakeMessage.getMessageSeq())));
                if (this.LOGGER.isTraceEnabled()) {
                    sb.append(":");
                    sb.append(vcb.a());
                    sb.append(handshakeMessage);
                }
                this.LOGGER.debug(sb.toString());
            }
            if (i == 0) {
                this.handshakeMessages.add(handshakeMessage);
            }
            this.statesChanged = false;
            this.recursionProtection.lock();
            try {
                doProcessMessage(handshakeMessage);
                this.recursionProtection.unlock();
                this.LOGGER.debug("Processed {} message from peer [{}]", handshakeMessage.getMessageType(), this.peerToLog);
                if (this.timeoutLastFlight == null) {
                    this.nextReceiveMessageSequence++;
                    if (!this.statesChanged) {
                        this.statesIndex++;
                    }
                }
                handshakeMessage = handshakeMessage.getNextHandshakeMessage();
                if (this.useMultiHandshakeMessagesRecord == null && handshakeMessage != null) {
                    this.useMultiHandshakeMessagesRecord = true;
                }
            } catch (Throwable th2) {
                this.recursionProtection.unlock();
                throw th2;
            }
        }
        return true;
    }

    protected boolean isExpectedStates(vdj[] vdjVarArr) {
        return this.expectedStates == vdjVarArr;
    }

    protected void setExpectedStates(vdj[] vdjVarArr) {
        this.expectedStates = vdjVarArr;
        this.statesIndex = 0;
        this.statesChanged = true;
    }

    protected void expectMessage(DTLSMessage dTLSMessage) throws vdb {
        vdj[] vdjVarArr = this.expectedStates;
        if (vdjVarArr == null) {
            this.LOGGER.warn("Cannot process {} message from peer [{}], not expected!", vdj.b(dTLSMessage), this.peerToLog);
            throw new vdb("Cannot process " + vdj.b(dTLSMessage) + " handshake message, not expected!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR));
        }
        if (vdjVarArr.length == 0) {
            return;
        }
        int i = this.statesIndex;
        if (i >= vdjVarArr.length) {
            this.LOGGER.warn("Cannot process {} message from peer [{}], no more expected!", vdj.b(dTLSMessage), this.peerToLog);
            throw new vdb("Cannot process " + vdj.b(dTLSMessage) + " handshake message, no more expected!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR));
        }
        vdj vdjVar = vdjVarArr[i];
        boolean d2 = vdjVar.d(dTLSMessage);
        if (!d2 && vdjVar.e()) {
            int i2 = this.statesIndex;
            vdj[] vdjVarArr2 = this.expectedStates;
            int i3 = i2 + 1;
            if (i3 < vdjVarArr2.length && vdjVarArr2[i3].d(dTLSMessage)) {
                this.statesIndex++;
                return;
            }
        }
        if (d2) {
            return;
        }
        vcs vcsVar = this.pendingFlight.get();
        if (vcsVar != null && vcsVar.d(dTLSMessage)) {
            this.LOGGER.debug("Cannot process {} message from itself [{}]!", vdj.b(dTLSMessage), this.peerToLog);
        } else {
            this.LOGGER.debug("Cannot process {} message from peer [{}], {} expected!", vdj.b(dTLSMessage), this.peerToLog, vdjVar);
        }
        throw new vdb("Cannot process " + vdj.b(dTLSMessage) + " handshake message, " + vdjVar + " expected!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
    }

    public void processAsyncHandshakeResult(vdg vdgVar) throws vdb {
        if (vdgVar instanceof vdq) {
            processPskSecretResult((vdq) vdgVar);
        } else if (vdgVar instanceof vcl) {
            processCertificateVerificationResult((vcl) vdgVar);
        } else if (vdgVar instanceof vcc) {
            processCertificateIdentityResult((vcc) vdgVar);
        }
        if (this.changeCipherSuiteMessageExpected) {
            processNextMessages(null);
        }
    }

    protected void processPskSecretResult(vdq vdqVar) throws vdb {
        vap vapVar;
        if (!this.pskRequestPending) {
            throw new IllegalStateException("psk secret not pending!");
        }
        this.pskRequestPending = false;
        try {
            ensureUndestroyed();
            vct session = getSession();
            String h = this.sniEnabled ? session.h() : null;
            vdt b2 = vdqVar.b();
            SecretKey c = vdqVar.c();
            if (c != null) {
                if (h != null) {
                    this.LOGGER.trace("client [{}] uses PSK identity [{}] for server [{}]", this.peerToLog, b2, h);
                } else {
                    this.LOGGER.trace("client [{}] uses PSK identity [{}]", this.peerToLog, b2);
                }
                if (this.sniEnabled) {
                    vapVar = new vap(h, b2.a());
                } else {
                    vapVar = new vap(b2.a());
                }
                session.d(vapVar);
                if ("PSK".equals(c.getAlgorithm())) {
                    Mac threadLocalPseudoRandomFunctionMac = session.e().getThreadLocalPseudoRandomFunctionMac();
                    SecretKey a2 = PseudoRandomFunction.a(this.otherSecret, c);
                    SecretKey b3 = PseudoRandomFunction.b(threadLocalPseudoRandomFunctionMac, a2, this.masterSecretSeed, session.s());
                    vfh.e(a2);
                    vfh.e(c);
                    c = b3;
                }
                setCustomArgument(vdqVar);
                applyMasterSecret(c);
                vfh.e(c);
                processMasterSecret();
                return;
            }
            AlertMessage alertMessage = new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNKNOWN_PSK_IDENTITY);
            if (h != null) {
                throw new vdb(String.format("No pre-shared key found for [virtual host: %s, identity: %s]", h, b2), alertMessage);
            }
            throw new vdb(String.format("No pre-shared key found for [identity: %s]", b2), alertMessage);
        } finally {
            vfh.e(this.otherSecret);
            this.otherSecret = null;
        }
    }

    protected void processCertificateVerificationResult(vcl vclVar) throws vdb {
        if (!this.certificateVerificationPending) {
            throw new IllegalStateException("certificate verification not pending!");
        }
        ensureUndestroyed();
        this.certificateVerificationPending = false;
        this.LOGGER.debug("Process result of certificate verification.");
        if (vclVar.b() != null) {
            this.otherPeersCertificateVerified = true;
            this.otherPeersCertPath = vclVar.b();
            if (this.otherPeersSignatureVerified) {
                getSession().d(new vao(this.otherPeersCertPath));
            }
            setCustomArgument(vclVar);
            processCertificateVerified();
            return;
        }
        if (vclVar.e() != null) {
            this.otherPeersCertificateVerified = true;
            if (this.otherPeersSignatureVerified) {
                getSession().d(new vas(this.otherPeersPublicKey));
            }
            setCustomArgument(vclVar);
            processCertificateVerified();
            return;
        }
        if (vclVar.c() != null) {
            throw vclVar.c();
        }
        throw new vdb("Bad Certificate", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
    }

    protected void processCertificateIdentityResult(vcc vccVar) throws vdb {
        if (!this.certificateIdentityPending) {
            throw new IllegalStateException("certificate identity not pending!");
        }
        ensureUndestroyed();
        this.certificateIdentityPending = false;
        this.LOGGER.debug("Process result of certificate identity.");
        this.privateKey = vccVar.d();
        this.publicKey = vccVar.c();
        this.certificateChain = vccVar.b();
        this.certificateIdentityAvailable = true;
        processCertificateIdentityAvailable();
    }

    protected void setCustomArgument(vdg vdgVar) {
        Object j = vdgVar.j();
        if (j != null) {
            this.customArgument = j;
        }
    }

    public boolean hasPendingApiCall() {
        return this.certificateIdentityPending || this.certificateVerificationPending || this.pskRequestPending;
    }

    protected boolean setOtherPeersSignatureVerified() {
        this.otherPeersSignatureVerified = true;
        if (this.otherPeersCertificateVerified) {
            if (this.otherPeersCertPath != null) {
                getSession().d(new vao(this.otherPeersCertPath));
            } else if (this.otherPeersPublicKey != null) {
                getSession().d(new vas(this.otherPeersPublicKey));
            }
        }
        return this.otherPeersCertificateVerified;
    }

    protected final MessageDigest getHandshakeMessageDigest() {
        MessageDigest threadLocalPseudoRandomFunctionMessageDigest = getSession().e().getThreadLocalPseudoRandomFunctionMessageDigest();
        int i = 0;
        for (HandshakeMessage handshakeMessage : this.handshakeMessages) {
            threadLocalPseudoRandomFunctionMessageDigest.update(handshakeMessage.toByteArray());
            this.LOGGER.trace("  [{}] - {}", Integer.valueOf(i), handshakeMessage.getMessageType());
            i++;
        }
        return threadLocalPseudoRandomFunctionMessageDigest;
    }

    protected final MessageDigest cloneMessageDigest(MessageDigest messageDigest) throws vdb {
        try {
            return (MessageDigest) messageDigest.clone();
        } catch (CloneNotSupportedException unused) {
            throw new vdb("Cannot create hash for second FINISHED message", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR));
        }
    }

    protected void applyMasterSecret(SecretKey secretKey) {
        ensureUndestroyed();
        this.masterSecret = vfh.a(secretKey);
        calculateKeys(secretKey);
        getSession().e(secretKey);
    }

    protected void resumeMasterSecret() {
        ensureUndestroyed();
        SecretKey g = getSession().g();
        this.masterSecret = g;
        calculateKeys(g);
    }

    protected void calculateKeys(SecretKey secretKey) {
        ensureUndestroyed();
        CipherSuite e2 = this.context.a().e();
        int macKeyLength = e2.getMacKeyLength();
        int encKeyLength = e2.getEncKeyLength();
        int fixedIvLength = e2.getFixedIvLength();
        int i = this.generateClusterMacKeys ? encKeyLength : 0;
        byte[] e3 = PseudoRandomFunction.e(e2.getThreadLocalPseudoRandomFunctionMac(), secretKey, PseudoRandomFunction.Label.KEY_EXPANSION_LABEL, vbj.e(this.serverRandom, this.clientRandom), (macKeyLength + encKeyLength + fixedIvLength + i) * 2);
        this.clientWriteMACKey = vfh.b(e3, 0, macKeyLength, "Mac");
        this.serverWriteMACKey = vfh.b(e3, macKeyLength, macKeyLength, "Mac");
        int i2 = macKeyLength + macKeyLength;
        this.clientWriteKey = vfh.b(e3, i2, encKeyLength, "AES");
        int i3 = i2 + encKeyLength;
        this.serverWriteKey = vfh.b(e3, i3, encKeyLength, "AES");
        int i4 = i3 + encKeyLength;
        this.clientWriteIV = vfh.b(e3, i4, fixedIvLength);
        int i5 = i4 + fixedIvLength;
        this.serverWriteIV = vfh.b(e3, i5, fixedIvLength);
        if (this.generateClusterMacKeys) {
            SecretKey b2 = vfh.b(e3, i5, i, "Mac");
            SecretKey b3 = vfh.b(e3, i5 + i, i, "Mac");
            if (isClient()) {
                this.context.a(b2, b3);
            } else {
                this.context.a(b3, b2);
            }
            vfh.e(b2);
            vfh.e(b3);
        }
        vbj.b(e3);
    }

    protected byte[] generateMasterSecretSeed() {
        if (getSession().s()) {
            MessageDigest threadLocalPseudoRandomFunctionMessageDigest = getSession().e().getThreadLocalPseudoRandomFunctionMessageDigest();
            int i = 0;
            for (HandshakeMessage handshakeMessage : this.handshakeMessages) {
                threadLocalPseudoRandomFunctionMessageDigest.update(handshakeMessage.toByteArray());
                this.LOGGER.trace("  [{}] - {}", Integer.valueOf(i), handshakeMessage.getMessageType());
                if (handshakeMessage.getMessageType() == HandshakeType.CLIENT_KEY_EXCHANGE) {
                    return threadLocalPseudoRandomFunctionMessageDigest.digest();
                }
                i++;
            }
            throw new IllegalArgumentException("client key exchange missing!");
        }
        return vbj.e(this.clientRandom, this.serverRandom);
    }

    protected void requestPskSecretResult(vdt vdtVar, SecretKey secretKey, byte[] bArr) throws vdb {
        if (bArr == null) {
            throw new NullPointerException("seed must not be null!");
        }
        vct session = getSession();
        vfe serverNames = getServerNames();
        String pseudoRandomFunctionMacName = session.e().getPseudoRandomFunctionMacName();
        this.pskRequestPending = true;
        this.masterSecretSeed = bArr;
        this.otherSecret = vfh.a(secretKey);
        vdq requestPskSecretResult = this.advancedPskStore.requestPskSecretResult(this.connection.c(), serverNames, vdtVar, pseudoRandomFunctionMacName, secretKey, this.masterSecretSeed, session.s());
        if (requestPskSecretResult != null) {
            processPskSecretResult(requestPskSecretResult);
        }
    }

    protected final void setCurrentReadState() {
        if (isClient()) {
            this.context.e(this.serverWriteKey, this.serverWriteIV, this.serverWriteMACKey);
        } else {
            this.context.e(this.clientWriteKey, this.clientWriteIV, this.clientWriteMACKey);
        }
    }

    protected final void setCurrentWriteState() {
        if (isClient()) {
            this.context.a(this.clientWriteKey, this.clientWriteIV, this.clientWriteMACKey);
        } else {
            this.context.a(this.serverWriteKey, this.serverWriteIV, this.serverWriteMACKey);
        }
    }

    protected final vde createFinishedMessage(byte[] bArr) {
        if (this.masterSecret == null) {
            throw new IllegalStateException("master secret not available!");
        }
        return new vde(getSession().e().getThreadLocalPseudoRandomFunctionMac(), this.masterSecret, isClient(), bArr);
    }

    protected final void verifyFinished(vde vdeVar, byte[] bArr) throws vdb {
        if (this.masterSecret == null) {
            throw new IllegalStateException("master secret not available!");
        }
        vdeVar.d(getSession().e().getThreadLocalPseudoRandomFunctionMac(), this.masterSecret, !isClient(), bArr);
    }

    public final boolean hasMasterSecret() {
        return this.masterSecret != null;
    }

    protected final void wrapMessage(vcs vcsVar, HandshakeMessage handshakeMessage) {
        handshakeMessage.setMessageSeq(this.sendMessageSequence);
        this.sendMessageSequence++;
        int h = this.context.h();
        if (h == 0) {
            this.handshakeMessages.add(handshakeMessage);
        }
        vcsVar.a(h, handshakeMessage);
    }

    protected final void wrapMessage(vcs vcsVar, ChangeCipherSpecMessage changeCipherSpecMessage) {
        vcsVar.a(this.context.h(), changeCipherSpecMessage);
    }

    protected vdd reassembleFragment(vdc vdcVar) throws vdb {
        this.LOGGER.debug("Processing {} message fragment ...", vdcVar.getMessageType());
        try {
            if (vdcVar.getMessageLength() > this.maxFragmentedHandshakeMessageLength) {
                throw new IllegalArgumentException("Fragmented message length exceeded (" + vdcVar.getMessageLength() + " > " + this.maxFragmentedHandshakeMessageLength + ")!");
            }
            int messageSeq = vdcVar.getMessageSeq();
            vdy vdyVar = this.reassembledMessage;
            if (vdyVar == null) {
                this.reassembledMessage = new vdy(vdcVar);
            } else {
                if (vdyVar.getMessageSeq() != messageSeq) {
                    throw new IllegalArgumentException("Current reassemble message has different seqn " + this.reassembledMessage.getMessageSeq() + " != " + messageSeq);
                }
                this.reassembledMessage.e(vdcVar);
            }
            if (!this.reassembledMessage.a()) {
                return null;
            }
            vdy vdyVar2 = this.reassembledMessage;
            this.LOGGER.debug("Successfully re-assembled {} message", vdyVar2.getMessageType());
            this.reassembledMessage = null;
            return vdyVar2;
        } catch (IllegalArgumentException e2) {
            throw new vdb(e2.getMessage(), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECODE_ERROR));
        }
    }

    vdi getParameter() {
        vct session = getSession();
        return new vdi(session.f(), session.t());
    }

    public final vfe getServerNames() {
        if (this.sniEnabled) {
            return getSession().k();
        }
        return null;
    }

    public final vct getSession() {
        return this.context.a();
    }

    public final vcn getDtlsContext() {
        return this.context;
    }

    public final InetSocketAddress getPeerAddress() {
        return this.peer;
    }

    public final vcm getConnection() {
        return this.connection;
    }

    public vcs createFlight() {
        return new vcs(this.context, this.flightNumber, getPeerAddress());
    }

    public boolean supportsConnectionId() {
        return vcp.c(this.connectionIdGenerator);
    }

    public vcp getReadConnectionId() {
        if (vcp.b(this.connectionIdGenerator)) {
            return this.connection.c();
        }
        if (vcp.c(this.connectionIdGenerator)) {
            return vcp.b;
        }
        return null;
    }

    public vdv getClientRandom() {
        return this.clientRandom;
    }

    public vdv getServerRandom() {
        return this.serverRandom;
    }

    final int getNextReceiveMessageSequenceNumber() {
        return this.nextReceiveMessageSequence;
    }

    public void addApplicationDataForDeferredProcessing(vaf vafVar) {
        if (this.deferredApplicationData.size() < this.maxDeferredProcessedOutgoingApplicationDataMessages) {
            this.deferredApplicationData.add(vafVar);
        }
    }

    public void addRecordsOfNextEpochForDeferredProcessing(vdz vdzVar) {
        addDeferredProcessedRecord(vdzVar, this.nextEpochDeferredRecords);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addDeferredProcessedRecord(vdz vdzVar, Collection<vdz> collection) {
        int o = vdzVar.o();
        int i = this.deferredIncomingRecordsSize + o;
        if (i < this.maxDeferredProcessedIncomingRecordsSize) {
            this.deferredIncomingRecordsSize = i;
            collection.add(vdzVar);
            return true;
        }
        this.LOGGER.debug("Dropped incoming record from peer [{}], limit of {} bytes exceeded by {}+{} bytes!", vdzVar.i(), Integer.valueOf(this.maxDeferredProcessedIncomingRecordsSize), Integer.valueOf(this.deferredIncomingRecordsSize), Integer.valueOf(o));
        this.recordLayer.dropReceivedRecord(vdzVar);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDeferredProcessedRecord(vdz vdzVar, Collection<vdz> collection) {
        if (collection.remove(vdzVar)) {
            int o = vdzVar.o();
            int i = this.deferredIncomingRecordsSize;
            if (i < o) {
                this.LOGGER.warn("deferred processed incoming records corrupted for peer [{}]! Removing {} bytes exceeds available {} bytes!", vdzVar.i(), Integer.valueOf(o), Integer.valueOf(this.deferredIncomingRecordsSize));
                throw new IllegalArgumentException("deferred processing of incoming records corrupted!");
            }
            this.deferredIncomingRecordsSize = i - o;
        }
    }

    public List<vaf> takeDeferredApplicationData() {
        ArrayList arrayList = new ArrayList(this.deferredApplicationData);
        this.deferredApplicationData.clear();
        return arrayList;
    }

    public List<vdz> takeDeferredRecordsOfNextEpoch() {
        ArrayList arrayList = new ArrayList(this.nextEpochDeferredRecords);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            removeDeferredProcessedRecord((vdz) it.next(), this.nextEpochDeferredRecords);
        }
        if (!this.nextEpochDeferredRecords.isEmpty()) {
            this.LOGGER.warn("{} left deferred records", Integer.valueOf(this.nextEpochDeferredRecords.size()));
            this.nextEpochDeferredRecords.clear();
        }
        return arrayList;
    }

    public void takeDeferredApplicationData(Handshaker handshaker) {
        this.deferredApplicationData.addAll(handshaker.takeDeferredApplicationData());
    }

    public void completePendingFlight() {
        this.retransmitFlight = null;
        vcs vcsVar = this.pendingFlight.get();
        if (vcsVar != null) {
            vcsVar.i();
        }
    }

    public void sendLastFlight(vcs vcsVar) {
        this.timeoutLastFlight = this.timer.schedule(new e(), this.nanosExpireTimeout, TimeUnit.NANOSECONDS);
        vcsVar.e(false);
        sendFlight(vcsVar);
    }

    public void sendFlight(vcs vcsVar) {
        completePendingFlight();
        try {
            int i = this.retransmissionTimeout;
            float f = this.retransmissionRandomFactor - 1.0f;
            if (f > 0.0d) {
                i += vep.e().nextInt(Math.round(i * f));
            }
            if (this.eccExpected) {
                i += this.additionalTimeoutForEcc;
                this.eccExpected = false;
            }
            int min = Math.min(i, this.maxRetransmissionTimeout);
            vcsVar.e(min);
            long d2 = ClockUtil.d();
            this.flightSendNanos = d2;
            this.nanosExpireTime = this.nanosExpireTimeout + d2;
            int maxDatagramSize = this.recordLayer.getMaxDatagramSize(this.ipv6);
            List<DatagramPacket> e2 = vcsVar.e(maxDatagramSize, getSession().b(), this.useMultiHandshakeMessagesRecord, this.useMultiRecordMessages, false);
            this.LOGGER.trace("Sending flight of {} message(s) to peer [{}] using {} datagram(s) of max. {} bytes and {} ms timeout.", Integer.valueOf(vcsVar.a()), this.peerToLog, Integer.valueOf(e2.size()), Integer.valueOf(maxDatagramSize), Integer.valueOf(min));
            this.recordLayer.sendFlight(e2);
            this.pendingFlight.set(vcsVar);
            if (vcsVar.j()) {
                b bVar = new b(vcsVar);
                this.retransmitFlight = bVar;
                vcsVar.c(this.timer, bVar);
            }
            int d3 = vcsVar.d();
            if (d3 > 0) {
                this.context.b(d3);
            }
        } catch (IOException e3) {
            handshakeFailed(new Exception("handshake flight " + vcsVar.b() + " failed!", e3));
        } catch (vdb e4) {
            handshakeFailed(new Exception("handshake flight " + vcsVar.b() + " failed!", e4));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleTimeout(defpackage.vcs r15) {
        /*
            Method dump skipped, instructions count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.dtls.Handshaker.handleTimeout(vcs):void");
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final Runnable f15903a;
        private final boolean e;

        private a(Runnable runnable, boolean z) {
            this.f15903a = runnable;
            this.e = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Handshaker.this.connection.g().execute(this.f15903a);
            } catch (RejectedExecutionException e) {
                Handshaker.this.LOGGER.debug("Execution rejected while execute task of peer: {}", Handshaker.this.connection.o(), e);
                if (this.e) {
                    this.f15903a.run();
                }
            }
        }
    }

    class b extends a {
        private b(final vcs vcsVar) {
            super(new Runnable() { // from class: org.eclipse.californium.scandium.dtls.Handshaker.b.3
                @Override // java.lang.Runnable
                public void run() {
                    Handshaker.this.handleTimeout(vcsVar);
                }
            }, true);
        }
    }

    class e extends a {
        private e() {
            super(new Runnable() { // from class: org.eclipse.californium.scandium.dtls.Handshaker.e.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Handshaker.this.recordLayer.isRunning()) {
                        Handshaker.this.handshakeCompleted();
                    }
                }
            }, false);
        }
    }

    public final void addSessionListener(SessionListener sessionListener) {
        if (sessionListener != null) {
            this.sessionListeners.add(sessionListener);
        }
    }

    public final void removeSessionListener(SessionListener sessionListener) {
        if (sessionListener != null) {
            this.sessionListeners.remove(sessionListener);
        }
    }

    protected final void handshakeStarted() throws vdb {
        this.LOGGER.debug("handshake started {}", this.connection);
        Iterator<SessionListener> it = this.sessionListeners.iterator();
        while (it.hasNext()) {
            it.next().handshakeStarted(this);
        }
    }

    protected final void contextEstablished() throws vdb {
        if (this.contextEstablished) {
            return;
        }
        if (this.context.i().hasValidCipherSuite()) {
            this.LOGGER.debug("dtls context established {}", this.connection);
            amendPeerPrincipal();
            this.contextEstablished = true;
            Iterator<SessionListener> it = this.sessionListeners.iterator();
            while (it.hasNext()) {
                it.next().contextEstablished(this, this.context);
            }
            return;
        }
        handshakeFailed(new vcu("Failed establishing a incomplete session."));
    }

    public final void handshakeCompleted() {
        if (this.handshakeCompleted) {
            return;
        }
        ScheduledFuture<?> scheduledFuture = this.timeoutLastFlight;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.handshakeCompleted = true;
        completePendingFlight();
        Iterator<SessionListener> it = this.sessionListeners.iterator();
        while (it.hasNext()) {
            it.next().handshakeCompleted(this);
        }
        vfh.a(this);
        this.LOGGER.debug("handshake completed {}", this.connection);
    }

    public final void handshakeFailed(Throwable th) {
        if (this.cause == null) {
            this.cause = th;
        }
        if (this.handshakeFailed || this.cause != th) {
            return;
        }
        this.LOGGER.debug("handshake failed {}", this.connection, th);
        this.handshakeFailed = true;
        completePendingFlight();
        Iterator<SessionListener> it = this.sessionListeners.iterator();
        while (it.hasNext()) {
            it.next().handshakeFailed(this, th);
        }
        vfh.a(this.context);
        vfh.a(this);
    }

    public final void handshakeAborted(Throwable th) {
        this.handshakeAborted = true;
        handshakeFailed(th);
    }

    public boolean hasContextEstablished() {
        return this.contextEstablished;
    }

    public boolean isExpired() {
        return (this.contextEstablished || this.pendingFlight.get() == null || ClockUtil.d() - this.nanosExpireTime <= 0) ? false : true;
    }

    public boolean isPskRequestPending() {
        return this.pskRequestPending;
    }

    public boolean isRemovingConnection() {
        return (this.handshakeAborted || this.connection.q()) ? false : true;
    }

    public Throwable getFailureCause() {
        return this.cause;
    }

    public void setFailureCause(Throwable th) {
        completePendingFlight();
        this.cause = th;
    }

    public void setGenerateClusterMacKeys(boolean z) {
        this.generateClusterMacKeys = z;
    }

    public final void handshakeFlightRetransmitted(int i) {
        Iterator<SessionListener> it = this.sessionListeners.iterator();
        while (it.hasNext()) {
            it.next().handshakeFlightRetransmitted(this, i);
        }
        Iterator<vaf> it2 = this.deferredApplicationData.iterator();
        while (it2.hasNext()) {
            it2.next().c(i);
        }
    }

    public final boolean isChangeCipherSpecMessageExpected() {
        return this.changeCipherSuiteMessageExpected;
    }

    protected final void expectChangeCipherSpecMessage() {
        this.changeCipherSuiteMessageExpected = true;
    }

    protected void expectEcc() {
        this.eccExpected = true;
    }

    public void verifyCertificate(vce vceVar, boolean z) throws vdb {
        if (this.certificateVerifier == null) {
            this.LOGGER.debug("Certificate validation failed: no verifier available!");
            throw new vdb("Trust is not possible!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
        }
        this.LOGGER.info("Start certificate verification.");
        this.certificateVerificationPending = true;
        this.otherPeersPublicKey = vceVar.e();
        vcl verifyCertificate = this.certificateVerifier.verifyCertificate(this.connection.c(), getServerNames(), getPeerAddress(), !isClient(), z, this.useTruncatedCertificatePathForVerification, vceVar);
        if (verifyCertificate != null) {
            processCertificateVerificationResult(verifyCertificate);
        }
    }

    public boolean requestCertificateIdentity(List<X500Principal> list, vfe vfeVar, List<CipherSuite.CertificateKeyAlgorithm> list2, List<SignatureAndHashAlgorithm> list3, List<XECDHECryptography.SupportedGroup> list4) throws vdb {
        vcc requestCertificateIdentity;
        this.certificateIdentityPending = true;
        if (this.certificateIdentityProvider == null) {
            requestCertificateIdentity = new vcc(this.connection.c(), null);
        } else {
            this.LOGGER.info("Start certificate identity.");
            requestCertificateIdentity = this.certificateIdentityProvider.requestCertificateIdentity(this.connection.c(), isClient(), list, vfeVar, list2, list3, list4);
        }
        if (requestCertificateIdentity == null) {
            return true;
        }
        processCertificateIdentityResult(requestCertificateIdentity);
        return false;
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vfh.e(this.otherSecret);
        this.otherSecret = null;
        vfh.e(this.masterSecret);
        this.masterSecret = null;
        vfh.e(this.clientWriteKey);
        this.clientWriteKey = null;
        vfh.e(this.clientWriteMACKey);
        this.clientWriteMACKey = null;
        vfh.a((Destroyable) this.clientWriteIV);
        this.clientWriteIV = null;
        vfh.e(this.serverWriteKey);
        this.serverWriteKey = null;
        vfh.e(this.serverWriteMACKey);
        this.serverWriteMACKey = null;
        vfh.a((Destroyable) this.serverWriteIV);
        this.serverWriteIV = null;
        this.destroyed = true;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }

    protected void ensureUndestroyed() {
        if (this.destroyed) {
            if (this.handshakeFailed) {
                throw new IllegalStateException("secrets destroyed after failure!", this.cause);
            }
            if (this.contextEstablished) {
                throw new IllegalStateException("secrets destroyed after success!");
            }
            throw new IllegalStateException("secrets destroyed ???");
        }
    }

    private void amendPeerPrincipal() {
        vct session = getSession();
        Principal l = session.l();
        if (l instanceof ExtensiblePrincipal) {
            ExtensiblePrincipal extensiblePrincipal = (ExtensiblePrincipal) l;
            var additionalPeerInfo = getAdditionalPeerInfo(l);
            if (additionalPeerInfo != null) {
                session.d(extensiblePrincipal.amend(additionalPeerInfo));
            }
        }
    }

    private var getAdditionalPeerInfo(Principal principal) {
        ApplicationLevelInfoSupplier applicationLevelInfoSupplier = this.applicationLevelInfoSupplier;
        if (applicationLevelInfoSupplier == null || principal == null) {
            return null;
        }
        return applicationLevelInfoSupplier.getInfo(principal, this.customArgument);
    }
}

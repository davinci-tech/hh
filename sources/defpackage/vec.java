package defpackage;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.ChangeCipherSpecMessage;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.RecordLayer;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vec extends veb {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17692a = vha.a((Class<?>) vec.class);
    private static final vdj[] d = {new vdj(ContentType.CHANGE_CIPHER_SPEC), new vdj(HandshakeType.FINISHED)};
    private boolean b;
    private byte[] c;
    private final ResumptionVerifier f;
    private vck i;

    public vec(long j, int i, RecordLayer recordLayer, ScheduledExecutorService scheduledExecutorService, vcm vcmVar, vcd vcdVar) {
        super(j, i, recordLayer, scheduledExecutorService, vcmVar, vcdVar);
        ResumptionVerifier x = vcdVar.x();
        this.f = x;
        if (x == null) {
            throw new IllegalArgumentException("Resumption verifier missing!");
        }
    }

    @Override // defpackage.veb, org.eclipse.californium.scandium.dtls.Handshaker
    public void doProcessMessage(HandshakeMessage handshakeMessage) throws vdb {
        if (this.b) {
            super.doProcessMessage(handshakeMessage);
            return;
        }
        int i = AnonymousClass4.e[handshakeMessage.getMessageType().ordinal()];
        if (i == 1) {
            handshakeStarted();
            a((vck) handshakeMessage);
        } else {
            if (i == 2) {
                e((vde) handshakeMessage);
                return;
            }
            throw new vdb(String.format("Received unexpected handshake message [%s] from peer %s", handshakeMessage.getMessageType(), this.peerToLog), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
        }
    }

    /* renamed from: vec$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HandshakeType.values().length];
            e = iArr;
            try {
                iArr[HandshakeType.CLIENT_HELLO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[HandshakeType.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void a(vck vckVar) throws vdb {
        if (!vckVar.hasSessionId()) {
            throw new IllegalArgumentException("Client hello doesn't contain session id required for resumption!");
        }
        this.i = vckVar;
        ved verifyResumptionRequest = this.f.verifyResumptionRequest(getConnection().c(), vckVar.c(), vckVar.getSessionId());
        if (verifyResumptionRequest != null) {
            f17692a.debug("Process client hello synchronous");
            e(verifyResumptionRequest);
        } else {
            b();
        }
    }

    private void e(vde vdeVar) throws vdb {
        verifyFinished(vdeVar, this.c);
        contextEstablished();
        handshakeCompleted();
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public boolean hasPendingApiCall() {
        return this.i != null || super.hasPendingApiCall();
    }

    @Override // org.eclipse.californium.scandium.dtls.Handshaker
    public void processAsyncHandshakeResult(vdg vdgVar) throws vdb {
        if (vdgVar instanceof ved) {
            f17692a.debug("Process client hello asynchronous");
            ensureUndestroyed();
            e((ved) vdgVar);
        }
        super.processAsyncHandshakeResult(vdgVar);
    }

    private void e(ved vedVar) throws vdb {
        vck vckVar = this.i;
        if (vckVar == null) {
            throw new IllegalStateException("resumption verification not pending!");
        }
        this.i = null;
        vct c = vedVar.c();
        boolean z = !d(c, vckVar, this.sniEnabled, this.extendedMasterSecretMode);
        this.b = z;
        if (z) {
            f17692a.debug("DTLS session {} not available, switch to full-handshake with peer [{}]!", vckVar.getSessionId(), this.peerToLog);
            vfh.a(c);
            b(vckVar);
        } else {
            getSession().d(c);
            vfh.a(c);
            setCustomArgument(vedVar);
            c(vckVar);
        }
    }

    private void c(vck vckVar) throws vdb {
        vct session = getSession();
        CipherSuite e = session.e();
        f17692a.debug("Start resumption-handshake with peer [{}].", this.peerToLog);
        this.clientRandom = vckVar.getRandom();
        this.flightNumber += 2;
        vcs createFlight = createFlight();
        vee veeVar = new vee(vckVar.getProtocolVersion(), session.n(), e, session.c());
        a(vckVar, veeVar);
        wrapMessage(createFlight, veeVar);
        this.serverRandom = veeVar.getRandom();
        wrapMessage(createFlight, new ChangeCipherSpecMessage());
        MessageDigest handshakeMessageDigest = getHandshakeMessageDigest();
        MessageDigest cloneMessageDigest = cloneMessageDigest(handshakeMessageDigest);
        resumeMasterSecret();
        setCurrentWriteState();
        vde createFinishedMessage = createFinishedMessage(handshakeMessageDigest.digest());
        wrapMessage(createFlight, createFinishedMessage);
        cloneMessageDigest.update(createFinishedMessage.toByteArray());
        this.c = cloneMessageDigest.digest();
        sendFlight(createFlight);
        setExpectedStates(d);
        expectChangeCipherSpecMessage();
    }

    public static boolean d(vct vctVar, vck vckVar, boolean z, ExtendedMasterSecretMode extendedMasterSecretMode) {
        if (vctVar == null) {
            f17692a.debug("DTLS session {} not available, switch to full-handshake!", vckVar.getSessionId());
            return false;
        }
        CipherSuite e = vctVar.e();
        CompressionMethod c = vctVar.c();
        if (!vckVar.d().contains(e)) {
            f17692a.debug("Cipher-suite {} changed by client hello, switch to full-handshake!", e);
            return false;
        }
        if (!vctVar.m().equals(vckVar.getProtocolVersion())) {
            f17692a.debug("Protocol version {} changed by client hello {}, switch to full-handshake!", vctVar.m(), vckVar.getProtocolVersion());
            return false;
        }
        if (!vckVar.e().contains(c)) {
            f17692a.debug("Compression method {} changed by client hello, switch to full-handshake!", vctVar.c());
            return false;
        }
        if (extendedMasterSecretMode.is(ExtendedMasterSecretMode.ENABLED) && !vckVar.hasExtendedMasterSecretExtension()) {
            f17692a.debug("Missing extended master secret extension in client hello, switch to full-handshake!");
            return false;
        }
        if (extendedMasterSecretMode == ExtendedMasterSecretMode.OPTIONAL && vctVar.s() && !vckVar.hasExtendedMasterSecretExtension()) {
            f17692a.debug("Disabled extended master secret extension in client hello, switch to full-handshake!");
            return false;
        }
        if (!z) {
            return true;
        }
        vfe k = vctVar.k();
        vfe c2 = vckVar.c();
        if (Objects.equals(k, c2)) {
            return true;
        }
        f17692a.debug("SNI {} changed by client hello {}, switch to full-handshake!", k, c2);
        return false;
    }
}

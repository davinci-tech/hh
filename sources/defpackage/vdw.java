package defpackage;

import java.security.MessageDigest;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.ChangeCipherSpecMessage;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.RecordLayer;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public class vdw extends vci {
    private static final vdj[] l = {new vdj(ContentType.CHANGE_CIPHER_SPEC), new vdj(HandshakeType.FINISHED)};
    private boolean m;

    public vdw(vct vctVar, RecordLayer recordLayer, ScheduledExecutorService scheduledExecutorService, vcm vcmVar, vcd vcdVar, boolean z) {
        super(null, recordLayer, scheduledExecutorService, vcmVar, vcdVar, z);
        this.m = false;
        vej n = vctVar.n();
        if (n == null || n.d()) {
            throw new IllegalArgumentException("Session must contain the ID of the session to resume");
        }
        getSession().d(vctVar);
    }

    @Override // defpackage.vci, org.eclipse.californium.scandium.dtls.Handshaker
    public void doProcessMessage(HandshakeMessage handshakeMessage) throws vdb {
        if (this.m) {
            super.doProcessMessage(handshakeMessage);
            return;
        }
        int i = AnonymousClass4.b[handshakeMessage.getMessageType().ordinal()];
        if (i == 1) {
            e((vdp) handshakeMessage);
        } else if (i == 2) {
            a((vee) handshakeMessage);
        } else {
            if (i == 3) {
                b((vde) handshakeMessage);
                return;
            }
            throw new vdb(String.format("Received unexpected handshake message [%s] from peer %s", handshakeMessage.getMessageType(), this.peerToLog), new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNEXPECTED_MESSAGE));
        }
    }

    /* renamed from: vdw$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[HandshakeType.values().length];
            b = iArr;
            try {
                iArr[HandshakeType.HELLO_VERIFY_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[HandshakeType.SERVER_HELLO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[HandshakeType.FINISHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // defpackage.vci
    protected void a(vee veeVar) throws vdb {
        vct session = getSession();
        if (!session.n().equals(veeVar.getSessionId())) {
            this.LOGGER.debug("Server [{}] refuses to resume session [{}], performing full handshake instead...", this.peerToLog, session.n());
            this.m = true;
            vfh.a(session);
            super.a(veeVar);
            return;
        }
        if (!veeVar.a().equals(session.c())) {
            throw new vdb("Server wants to change compression method in resumed session", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        if (!veeVar.b().equals(session.e())) {
            throw new vdb("Server wants to change cipher suite in resumed session", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        if (session.s() && !veeVar.hasExtendedMasterSecretExtension()) {
            throw new vdb("Server wants to change extended master secret in resumed session", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        if (!session.m().equals(veeVar.getProtocolVersion())) {
            throw new vdb("Server wants to change protocol version in resumed session", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.ILLEGAL_PARAMETER));
        }
        b(veeVar);
        this.serverRandom = veeVar.getRandom();
        if (supportsConnectionId()) {
            c(veeVar.getConnectionIdExtension());
        }
        setExpectedStates(l);
        expectChangeCipherSpecMessage();
        resumeMasterSecret();
    }

    private void b(vde vdeVar) throws vdb {
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

    @Override // defpackage.vci
    public void d() throws vdb {
        handshakeStarted();
        vct session = getSession();
        vck vckVar = new vck(vdu.f17688a, session, this.j, this.g, this.i, this.f);
        if (CipherSuite.containsEccBasedCipherSuite(vckVar.d())) {
            expectEcc();
        }
        this.clientRandom = vckVar.getRandom();
        if (session.s()) {
            vckVar.addExtension(vdf.b);
        }
        e(vckVar);
        b(vckVar);
        a(vckVar);
        c(vckVar);
        this.d = vckVar;
        this.flightNumber = 1;
        vcs createFlight = createFlight();
        wrapMessage(createFlight, vckVar);
        sendFlight(createFlight);
        setExpectedStates(e);
    }
}

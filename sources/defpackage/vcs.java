package defpackage;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.DTLSMessage;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vcs {
    private static final Logger d = vha.a((Class<?>) vcs.class);

    /* renamed from: a, reason: collision with root package name */
    private int f17671a;
    private int b;
    private final List<c> c;
    private final vcn e;
    private int f;
    private vdn g;
    private int h;
    private int i;
    private final int j;
    private final InetSocketAddress k;
    private boolean l;
    private final Object m;
    private final List<vdz> n;
    private volatile boolean o;
    private ScheduledFuture<?> p;
    private int q;
    private int r;
    private volatile boolean s;
    private boolean t;
    private boolean x;

    public vcs(vcn vcnVar, int i, InetSocketAddress inetSocketAddress) {
        this.t = false;
        if (vcnVar == null) {
            throw new NullPointerException("Session must not be null");
        }
        this.e = vcnVar;
        this.k = inetSocketAddress;
        this.m = vcb.b((SocketAddress) inetSocketAddress);
        this.n = new ArrayList();
        this.c = new ArrayList();
        this.t = true;
        this.j = i;
    }

    public void a(int i, DTLSMessage dTLSMessage) {
        if (dTLSMessage == null) {
            throw new NullPointerException("message must not be null!");
        }
        this.c.add(new c(i, dTLSMessage, null));
    }

    public int a() {
        return this.c.size();
    }

    public boolean d(DTLSMessage dTLSMessage) {
        Iterator<c> it = this.c.iterator();
        while (it.hasNext()) {
            if (Arrays.equals(dTLSMessage.toByteArray(), it.next().f17672a.toByteArray())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: vcs$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[ContentType.values().length];
            e = iArr;
            try {
                iArr[ContentType.HANDSHAKE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[ContentType.CHANGE_CIPHER_SPEC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    protected final void e(c cVar) throws vdb {
        try {
            DTLSMessage dTLSMessage = cVar.f17672a;
            int i = AnonymousClass2.e[dTLSMessage.getContentType().ordinal()];
            if (i == 1) {
                a(cVar);
                return;
            }
            if (i == 2) {
                m();
                this.n.add(new vdz(dTLSMessage.getContentType(), cVar.b, dTLSMessage, this.e, false, 0));
                d.debug("Add CCS message of {} bytes for [{}]", Integer.valueOf(dTLSMessage.size()), this.m);
            } else {
                throw new vdb("Cannot create " + dTLSMessage.getContentType() + " record for flight", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR));
            }
        } catch (GeneralSecurityException e) {
            throw new vdb("Cannot create record", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR), e);
        }
    }

    private void a(c cVar) throws GeneralSecurityException {
        boolean z;
        vcp j;
        HandshakeMessage handshakeMessage = (HandshakeMessage) cVar.f17672a;
        int i = this.h - 13;
        if (cVar.b <= 0 || (j = this.e.j()) == null || j.d()) {
            z = false;
        } else {
            i -= j.b();
            z = true;
        }
        int i2 = this.f;
        if (i2 >= i) {
            this.f17671a = this.h;
        } else {
            this.f17671a = (this.h - i) + i2;
            i = i2;
        }
        if (cVar.b > 0) {
            i -= this.e.a().i();
            if (z) {
                i--;
            }
        }
        this.b = i;
        int size = handshakeMessage.size();
        if (size <= i) {
            if (this.x) {
                if (this.g != null) {
                    if (this.i == cVar.b && this.l == z && this.g.size() + size < i) {
                        this.g.d(handshakeMessage);
                        d.debug("Add multi-handshake-message {} message of {} bytes, resulting in {} bytes for [{}]", handshakeMessage.getMessageType(), Integer.valueOf(size), Integer.valueOf(this.g.getMessageLength()), this.m);
                        return;
                    }
                    m();
                }
                if (this.g == null && size < i) {
                    vdn vdnVar = new vdn();
                    this.g = vdnVar;
                    vdnVar.d(handshakeMessage);
                    this.i = cVar.b;
                    this.l = z;
                    d.debug("Start multi-handshake-message with {} message of {} bytes for [{}]", handshakeMessage.getMessageType(), Integer.valueOf(size), this.m);
                    return;
                }
            }
            this.n.add(new vdz(ContentType.HANDSHAKE, cVar.b, handshakeMessage, this.e, z, 0));
            d.debug("Add {} message of {} bytes for [{}]", handshakeMessage.getMessageType(), Integer.valueOf(size), this.m);
            return;
        }
        m();
        d.debug("Splitting up {} message of {} bytes for [{}] into multiple handshake fragments of max. {} bytes", handshakeMessage.getMessageType(), Integer.valueOf(size), this.m, Integer.valueOf(i));
        byte[] fragmentToByteArray = handshakeMessage.fragmentToByteArray();
        int messageLength = handshakeMessage.getMessageLength();
        int i3 = i - 12;
        if (fragmentToByteArray.length != messageLength) {
            throw new IllegalStateException("message length " + messageLength + " differs from message " + fragmentToByteArray.length + "!");
        }
        int messageSeq = handshakeMessage.getMessageSeq();
        int i4 = 0;
        while (i4 < messageLength) {
            int i5 = i4 + i3 > messageLength ? messageLength - i4 : i3;
            byte[] bArr = new byte[i5];
            System.arraycopy(fragmentToByteArray, i4, bArr, 0, i5);
            vdc vdcVar = new vdc(handshakeMessage.getMessageType(), messageLength, messageSeq, i4, bArr);
            d.debug("fragment for offset {}, {} bytes", Integer.valueOf(i4), Integer.valueOf(vdcVar.size()));
            i4 += i5;
            this.n.add(new vdz(ContentType.HANDSHAKE, cVar.b, vdcVar, this.e, false, 0));
        }
    }

    private void m() throws GeneralSecurityException {
        if (this.g != null) {
            this.n.add(new vdz(ContentType.HANDSHAKE, this.i, this.g, this.e, this.l, 0));
            d.debug("Add {} multi handshake message, epoch {} of {} bytes (max. {}) for [{}]", Integer.valueOf(this.g.c()), Integer.valueOf(this.i), Integer.valueOf(this.g.getMessageLength()), Integer.valueOf(this.b), this.m);
            this.g = null;
            this.i = 0;
            this.l = false;
        }
    }

    public List<vdz> a(int i, int i2, boolean z) throws vdb {
        try {
            if (this.h == i && this.f == i2 && this.x == z) {
                for (int i3 = 0; i3 < this.n.size(); i3++) {
                    vdz vdzVar = this.n.get(i3);
                    this.n.set(i3, new vdz(vdzVar.j(), vdzVar.e(), vdzVar.c(), this.e, vdzVar.t(), 0));
                }
            } else {
                this.f17671a = i;
                this.h = i;
                this.f = i2;
                this.x = z;
                this.n.clear();
                Iterator<c> it = this.c.iterator();
                while (it.hasNext()) {
                    e(it.next());
                }
                m();
            }
            return this.n;
        } catch (GeneralSecurityException e) {
            this.n.clear();
            throw new vdb("Cannot create record", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR), e);
        }
    }

    public List<DatagramPacket> e(int i, int i2, Boolean bool, Boolean bool2, boolean z) throws vdb {
        int i3 = i;
        vbo vboVar = new vbo(i3);
        ArrayList arrayList = new ArrayList();
        boolean equals = Boolean.TRUE.equals(bool);
        boolean z2 = !Boolean.FALSE.equals(bool2);
        if (z) {
            i3 = Math.min(512, i3);
        }
        Logger logger = d;
        logger.trace("Prepare flight {}, using max. datagram size {}, max. fragment size {} [mhm={}, mr={}]", Integer.valueOf(this.j), Integer.valueOf(i3), Integer.valueOf(i2), Boolean.valueOf(equals), Boolean.valueOf(z2));
        List<vdz> a2 = a(i3, i2, equals);
        logger.trace("Effective max. datagram size {}, max. message size {}", Integer.valueOf(this.f17671a), Integer.valueOf(this.b));
        int i4 = 0;
        while (i4 < a2.size()) {
            vdz vdzVar = a2.get(i4);
            byte[] n = vdzVar.n();
            if (n.length > this.f17671a) {
                Logger logger2 = d;
                logger2.error("{} record of {} bytes for peer [{}] exceeds max. datagram size [{}], discarding...", vdzVar.j(), Integer.valueOf(n.length), this.m, Integer.valueOf(this.f17671a));
                logger2.debug("{}", vdzVar);
            } else {
                Logger logger3 = d;
                logger3.trace("Sending record of {} bytes to peer [{}]:\n{}", Integer.valueOf(n.length), this.m, vdzVar);
                if (z2 && vdzVar.j() == ContentType.CHANGE_CIPHER_SPEC && (i4 = i4 + 1) < a2.size()) {
                    n = vbj.e(n, a2.get(i4).n());
                }
                if (vboVar.d() > ((!z2 || (z && bool2 == null)) ? 0 : this.f17671a - n.length)) {
                    byte[] c2 = vboVar.c();
                    arrayList.add(new DatagramPacket(c2, c2.length, this.k.getAddress(), this.k.getPort()));
                    logger3.debug("Sending datagram of {} bytes to peer [{}]", Integer.valueOf(c2.length), this.m);
                }
                vboVar.d(n);
            }
            i4++;
        }
        byte[] c3 = vboVar.c();
        arrayList.add(new DatagramPacket(c3, c3.length, this.k.getAddress(), this.k.getPort()));
        d.debug("Sending datagram of {} bytes to peer [{}]", Integer.valueOf(c3.length), this.m);
        return arrayList;
    }

    public int d() {
        return this.b;
    }

    public int b() {
        return this.j;
    }

    public int c() {
        return this.q;
    }

    public void g() {
        this.q++;
    }

    public int e() {
        return this.r;
    }

    public void e(int i) {
        this.r = i;
    }

    public void e(float f, int i) {
        this.r = d(this.r, f, i);
    }

    public boolean j() {
        return this.t;
    }

    public void e(boolean z) {
        this.t = z;
    }

    public boolean h() {
        return this.s;
    }

    public void n() {
        this.s = true;
    }

    private final void o() {
        ScheduledFuture<?> scheduledFuture = this.p;
        if (scheduledFuture != null) {
            if (!scheduledFuture.isDone()) {
                this.p.cancel(true);
            }
            this.p = null;
        }
    }

    public void i() {
        this.o = true;
        o();
    }

    public boolean f() {
        return this.o;
    }

    public void c(ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
        if (this.o) {
            return;
        }
        if (j()) {
            o();
            try {
                this.p = scheduledExecutorService.schedule(runnable, this.r, TimeUnit.MILLISECONDS);
                d.trace("handshake flight to peer {}, retransmission {} ms.", this.m, Integer.valueOf(this.r));
                return;
            } catch (RejectedExecutionException unused) {
                d.trace("handshake flight stopped by shutdown.");
                return;
            }
        }
        d.trace("handshake flight to peer {}, no retransmission!", this.m);
    }

    public static int d(int i, float f, int i2) {
        return i < i2 ? Math.min(Math.round(i * f), i2) : i;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private final DTLSMessage f17672a;
        private final int b;

        /* synthetic */ c(int i, DTLSMessage dTLSMessage, AnonymousClass2 anonymousClass2) {
            this(i, dTLSMessage);
        }

        private c(int i, DTLSMessage dTLSMessage) {
            this.b = i;
            this.f17672a = dTLSMessage;
        }
    }
}

package defpackage;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.hms.network.embedded.w9;
import defpackage.vab;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.SerialExecutor;
import org.eclipse.californium.scandium.ConnectionListener;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.Handshaker;
import org.eclipse.californium.scandium.dtls.SessionListener;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class vcm {
    private static final Logger b;
    private static final Logger e;

    /* renamed from: a, reason: collision with root package name */
    private vcp f17667a;
    private volatile boolean c;
    private volatile ConnectionListener d;
    private long f;
    private long g;
    private Object h;
    private final AtomicReference<Handshaker> i;
    private volatile vcn j;
    private InetSocketAddress k;
    private AlertMessage l;
    private InetSocketAddress m;
    private volatile SerialExecutor n;
    private volatile boolean o;
    private volatile e p;
    private final SessionListener r;

    static {
        Logger a2 = vha.a((Class<?>) vcm.class);
        e = a2;
        b = vha.d(a2.getName() + ".owner");
    }

    public vcm(InetSocketAddress inetSocketAddress) {
        this.i = new AtomicReference<>();
        this.r = new a();
        if (inetSocketAddress == null) {
            throw new NullPointerException("Peer address must not be null");
        }
        long d = ClockUtil.d();
        this.m = inetSocketAddress;
        this.g = d;
        this.f = d;
    }

    public void ad() {
        ConnectionListener connectionListener = this.d;
        if (connectionListener != null) {
            connectionListener.updateExecution(this);
        }
    }

    public vcm e(Executor executor, ConnectionListener connectionListener) {
        if (u()) {
            throw new IllegalStateException("Executor already available!");
        }
        this.n = new SerialExecutor(executor);
        this.d = connectionListener;
        if (connectionListener == null) {
            this.n.c((SerialExecutor.ExecutionListener) null);
        } else {
            this.n.c(new SerialExecutor.ExecutionListener() { // from class: vcm.2
                @Override // org.eclipse.californium.elements.util.SerialExecutor.ExecutionListener
                public void beforeExecution() {
                    vcm.this.d.beforeExecution(vcm.this);
                }

                @Override // org.eclipse.californium.elements.util.SerialExecutor.ExecutionListener
                public void afterExecution() {
                    vcm.this.d.afterExecution(vcm.this);
                }
            });
        }
        return this;
    }

    public SerialExecutor g() {
        return this.n;
    }

    public boolean u() {
        return (this.n == null || this.n.isShutdown()) ? false : true;
    }

    public int ac() {
        SerialExecutor g = g();
        if (g == null) {
            return 0;
        }
        List<Runnable> shutdownNow = g.shutdownNow();
        vbr.c(shutdownNow);
        return shutdownNow.size();
    }

    public final SessionListener n() {
        return this.r;
    }

    public boolean r() {
        return this.j != null;
    }

    public boolean d() {
        vcn b2 = b();
        return b2 != null && vcp.c(b2.d());
    }

    public vcp c() {
        return this.f17667a;
    }

    public void a(vcp vcpVar) {
        this.f17667a = vcpVar;
        ad();
    }

    public void e(Object obj) {
        this.h = obj;
    }

    public Object j() {
        return this.h;
    }

    public InetSocketAddress o() {
        return this.m;
    }

    public void c(InetSocketAddress inetSocketAddress) {
        if (b(inetSocketAddress)) {
            return;
        }
        if (this.j == null && inetSocketAddress != null) {
            throw new IllegalArgumentException("Address change without established dtls context is not supported!");
        }
        this.g = ClockUtil.d();
        InetSocketAddress inetSocketAddress2 = this.m;
        this.m = inetSocketAddress;
        if (inetSocketAddress == null) {
            Handshaker l = l();
            if (l != null) {
                if (this.j == null || l.getDtlsContext() != this.j) {
                    l.handshakeFailed(new IOException(vcb.a(inetSocketAddress2) + " address reused during handshake!"));
                    return;
                }
                return;
            }
            return;
        }
        ad();
    }

    public boolean b(InetSocketAddress inetSocketAddress) {
        InetSocketAddress inetSocketAddress2 = this.m;
        if (inetSocketAddress2 == inetSocketAddress) {
            return true;
        }
        if (inetSocketAddress2 == null) {
            return false;
        }
        return inetSocketAddress2.equals(inetSocketAddress);
    }

    public InetSocketAddress m() {
        return this.k;
    }

    public void a(InetSocketAddress inetSocketAddress) {
        InetSocketAddress inetSocketAddress2 = this.k;
        if (inetSocketAddress2 != inetSocketAddress) {
            if (inetSocketAddress2 == null || !inetSocketAddress2.equals(inetSocketAddress)) {
                this.k = inetSocketAddress;
                ad();
            }
        }
    }

    public uzz c(vab.d dVar) {
        if (this.j == null) {
            throw new IllegalStateException("DTLS context must be established!");
        }
        this.j.e(dVar);
        if (this.k != null) {
            dVar.d(uzz.t, "dtls-cid-router");
        }
        vct a2 = this.j.a();
        return new uzz(this.m, a2.h(), a2.l(), dVar);
    }

    public uzz e(vab.d dVar, InetSocketAddress inetSocketAddress) {
        if (this.j == null) {
            throw new IllegalStateException("DTLS context must be established!");
        }
        this.j.a(dVar);
        if (this.k != null) {
            dVar.d(uzz.t, "dtls-cid-router");
        }
        InetSocketAddress inetSocketAddress2 = this.m;
        if (inetSocketAddress2 != null) {
            inetSocketAddress = inetSocketAddress2;
        }
        vct a2 = this.j.a();
        return new uzz(inetSocketAddress, a2.h(), a2.l(), dVar);
    }

    public vej f() {
        vcn h = h();
        if (h == null) {
            return null;
        }
        return h.a().n();
    }

    public vct i() {
        vcn h = h();
        if (h == null) {
            return null;
        }
        return h.a();
    }

    public boolean q() {
        return this.j != null;
    }

    public vcn h() {
        return this.j;
    }

    public Handshaker l() {
        return this.i.get();
    }

    public boolean s() {
        return this.i.get() != null;
    }

    public boolean w() {
        return this.c;
    }

    public void aa() {
        this.c = true;
    }

    public Long p() {
        e eVar = this.p;
        if (eVar != null) {
            return Long.valueOf(eVar.f17668a);
        }
        return null;
    }

    public boolean c(vck vckVar) {
        if (vckVar == null) {
            throw new NullPointerException("client hello must not be null!");
        }
        e eVar = this.p;
        if (eVar != null) {
            return eVar.d(vckVar);
        }
        return false;
    }

    public void a(vck vckVar) {
        if (vckVar == null) {
            this.p = null;
        } else {
            this.p = new e(vckVar);
        }
    }

    public vcn e(int i) {
        vcn dtlsContext;
        vcn vcnVar = this.j;
        if (vcnVar != null && vcnVar.b() == i) {
            return vcnVar;
        }
        Handshaker handshaker = this.i.get();
        if (handshaker == null || (dtlsContext = handshaker.getDtlsContext()) == null || dtlsContext.b() != i) {
            return null;
        }
        return dtlsContext;
    }

    public vcn b() {
        Handshaker handshaker;
        vcn vcnVar = this.j;
        return (vcnVar != null || (handshaker = this.i.get()) == null) ? vcnVar : handshaker.getDtlsContext();
    }

    public void x() {
        if (this.j == null) {
            throw new IllegalStateException("No established context to resume available!");
        }
        vfh.a(this.j);
        this.j = null;
        this.o = false;
        a((vck) null);
        ad();
    }

    public boolean t() {
        vcn vcnVar = this.j;
        return vcnVar != null && vcnVar.l();
    }

    public void c(vdz vdzVar) {
        vcn vcnVar = this.j;
        if (vcnVar != null) {
            vcnVar.b(vdzVar.e(), vdzVar.h());
        }
    }

    public boolean b(vdz vdzVar) {
        vcn vcnVar = this.j;
        if (vcnVar != null) {
            return vcnVar.e(vdzVar.e(), vdzVar.h());
        }
        return false;
    }

    public boolean d(AlertMessage alertMessage) {
        if (this.l != null) {
            return false;
        }
        this.l = alertMessage;
        return true;
    }

    public boolean v() {
        return this.o;
    }

    public boolean e(Long l) {
        if (!this.o && l != null && this.j != null && ClockUtil.d() - (this.f + TimeUnit.MILLISECONDS.toNanos(l.longValue())) > 0) {
            b(true);
        }
        return this.o;
    }

    public void y() {
        this.f = ClockUtil.d();
    }

    public long k() {
        return this.f;
    }

    public void b(boolean z) {
        this.o = z;
    }

    public int hashCode() {
        vcp vcpVar = this.f17667a;
        int hashCode = vcpVar == null ? 0 : vcpVar.hashCode();
        int hashCode2 = this.j == null ? 0 : this.j.hashCode();
        long j = this.f;
        int i = (int) (j ^ (j >>> 32));
        InetSocketAddress inetSocketAddress = this.m;
        int hashCode3 = inetSocketAddress == null ? 0 : inetSocketAddress.hashCode();
        int i2 = this.o ? 1231 : 1237;
        InetSocketAddress inetSocketAddress2 = this.k;
        int hashCode4 = inetSocketAddress2 == null ? 0 : inetSocketAddress2.hashCode();
        AlertMessage alertMessage = this.l;
        return ((((((((((((hashCode + 31) * 31) + hashCode2) * 31) + i) * 31) + hashCode3) * 31) + i2) * 31) + hashCode4) * 31) + (alertMessage != null ? alertMessage.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        vcm vcmVar = (vcm) obj;
        return vbj.d(this.f17667a, vcmVar.f17667a) && this.o == vcmVar.o && this.f == vcmVar.f && Objects.equals(this.j, vcmVar.j) && Objects.equals(this.m, vcmVar.m) && Objects.equals(this.k, vcmVar.k) && Objects.equals(this.l, vcmVar.l);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("dtls-con: ");
        vcp vcpVar = this.f17667a;
        if (vcpVar != null) {
            sb.append(vcpVar);
        }
        if (this.m != null) {
            sb.append(", ");
            sb.append(vcb.a(this.m));
            Handshaker l = l();
            if (l != null) {
                sb.append(", ongoing handshake ");
                vej n = l.getDtlsContext().a().n();
                if (n != null && !n.d()) {
                    sb.append(vcb.b(n.c(), (char) 0, 6));
                }
            }
            if (v()) {
                sb.append(", resumption required");
            } else if (q()) {
                sb.append(", session established ");
                vej n2 = i().n();
                if (n2 != null && !n2.d()) {
                    sb.append(vcb.b(n2.c(), (char) 0, 6));
                }
            }
        }
        if (u()) {
            sb.append(", is alive");
        }
        return sb.toString();
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private final long f17668a;
        private final vdv b;
        private final int d;

        private e(vck vckVar) {
            this.d = vckVar.getMessageSeq();
            this.b = vckVar.getRandom();
            this.f17668a = ClockUtil.d();
        }

        private e(vbn vbnVar, long j) {
            this.d = vbnVar.c(16);
            byte[] h = vbnVar.h(8);
            if (h != null) {
                this.b = new vdv(h);
            } else {
                this.b = null;
            }
            this.f17668a = vbnVar.d(64) + j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean d(vck vckVar) {
            return this.b.equals(vckVar.getRandom()) && this.d >= vckVar.getMessageSeq();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(vbo vboVar) {
            vboVar.b(this.d, 16);
            vboVar.b(this.b, 8);
            vboVar.c(this.f17668a, 64);
        }
    }

    class a implements SessionListener {
        @Override // org.eclipse.californium.scandium.dtls.SessionListener
        public void handshakeFlightRetransmitted(Handshaker handshaker, int i) {
        }

        private a() {
        }

        @Override // org.eclipse.californium.scandium.dtls.SessionListener
        public void handshakeStarted(Handshaker handshaker) throws vdb {
            vcm.this.i.set(handshaker);
            vcm.e.debug("Handshake with [{}] has been started", vcb.b((SocketAddress) vcm.this.m));
        }

        @Override // org.eclipse.californium.scandium.dtls.SessionListener
        public void contextEstablished(Handshaker handshaker, vcn vcnVar) throws vdb {
            vcm.this.j = vcnVar;
            vcm.e.debug("Session context with [{}] has been established", vcb.b((SocketAddress) vcm.this.m));
        }

        @Override // org.eclipse.californium.scandium.dtls.SessionListener
        public void handshakeCompleted(Handshaker handshaker) {
            SerialExecutor serialExecutor = vcm.this.n;
            if (serialExecutor != null && !serialExecutor.isShutdown() && vcm.b.isErrorEnabled()) {
                try {
                    serialExecutor.d();
                } catch (ConcurrentModificationException e) {
                    vcm.b.error("on handshake completed: connection {}", e.getMessage(), e);
                    if (vcm.b.isDebugEnabled()) {
                        throw e;
                    }
                }
            }
            if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(vcm.this.i, handshaker, null)) {
                vcm.e.debug("Handshake with [{}] has been completed", vcb.b((SocketAddress) vcm.this.m));
            }
        }

        @Override // org.eclipse.californium.scandium.dtls.SessionListener
        public void handshakeFailed(Handshaker handshaker, Throwable th) {
            SerialExecutor serialExecutor = vcm.this.n;
            if (serialExecutor != null && !serialExecutor.isShutdown() && vcm.b.isErrorEnabled()) {
                try {
                    serialExecutor.d();
                } catch (ConcurrentModificationException e) {
                    vcm.b.error("on handshake failed: connection {}", e.getMessage(), e);
                    if (vcm.b.isDebugEnabled()) {
                        throw e;
                    }
                }
            }
            if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(vcm.this.i, handshaker, null)) {
                vcm.this.p = null;
                vcm.e.debug("Handshake with [{}] has failed", vcb.b((SocketAddress) vcm.this.m));
            }
        }
    }

    public boolean e(vbo vboVar) {
        byte b2 = 0;
        if (this.j == null || this.j.l() || this.l != null) {
            return false;
        }
        int c = vbt.c(vboVar, 1, 16);
        vboVar.d(this.o ? (byte) 1 : (byte) 0);
        vboVar.c(this.f, 64);
        vboVar.b(this.f17667a, 8);
        vbt.b(vboVar, this.m);
        e eVar = this.p;
        if (eVar == null) {
            vboVar.d((byte) 0);
        } else {
            vboVar.d((byte) 1);
            eVar.e(vboVar);
        }
        this.j.e(vboVar);
        vcp vcpVar = this.f17667a;
        if (vcpVar != null && vcpVar.equals(this.j.d())) {
            b2 = 1;
        }
        vboVar.d(b2);
        vboVar.d(this.c ? (byte) 1 : (byte) 0);
        vbt.d(vboVar, c, 16);
        return true;
    }

    public static vcm b(vbq vbqVar, long j) {
        int a2 = vbt.a(vbqVar, 1, 16);
        if (a2 > 0) {
            return new vcm(vbqVar.b(a2), j);
        }
        return null;
    }

    private vcm(vbn vbnVar, long j) {
        this.i = new AtomicReference<>();
        this.r = new a();
        this.o = vbnVar.c() == 1;
        this.f = vbnVar.d(64) + j;
        byte[] h = vbnVar.h(8);
        if (h == null) {
            throw new IllegalArgumentException("CID must not be null!");
        }
        this.f17667a = new vcp(h);
        this.m = vbt.a(vbnVar);
        if (vbnVar.c() == 1) {
            this.p = new e(vbnVar, j);
        }
        this.j = vcn.a(vbnVar);
        if (this.j == null) {
            throw new IllegalArgumentException("DTLS Context must not be null!");
        }
        if (vbnVar.c() == 1) {
            this.j.a(this.f17667a);
        }
        if (vbnVar.e() && vbnVar.c() == 1) {
            this.c = true;
        }
        vbnVar.b(w9.h);
    }
}

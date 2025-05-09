package org.eclipse.californium.core.network;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import defpackage.uxh;
import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.uxu;
import defpackage.uxx;
import defpackage.uyc;
import defpackage.uyf;
import defpackage.vcb;
import defpackage.vha;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.observe.ObserveRelation;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.SerialExecutor;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class Exchange {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f15861a;
    private static final AtomicInteger b;
    static final boolean d;
    private volatile ObserveRelation aa;
    private volatile RemoveHandler ab;
    private volatile uxr ac;
    private volatile uxt ad;
    private float ae;
    private volatile long af;
    private volatile ScheduledFuture<?> ag;
    private volatile boolean ah;
    private boolean ai;
    private long aj;
    private boolean an;
    private Throwable c;
    private volatile uxh e;
    private uyc f;
    private uyf g;
    private final AtomicBoolean h;
    private volatile uxt i;
    private volatile uxr j;
    private int k;
    private volatile Endpoint l;
    private volatile EndpointContextOperator m;
    private final SerialExecutor n;
    private final AtomicReference<EndpointContext> o;
    private volatile int p;
    private final boolean q;
    private final long r;
    private final int s;
    private long t;
    private final boolean u;
    private volatile Integer v;
    private uyc w;
    private volatile List<c> x;
    private final Origin y;
    private final Object z;

    public interface EndpointContextOperator {
        EndpointContext apply(EndpointContext endpointContext);
    }

    public enum Origin {
        LOCAL,
        REMOTE
    }

    static {
        Logger a2 = vha.a((Class<?>) Exchange.class);
        f15861a = a2;
        d = a2.isTraceEnabled();
        b = new AtomicInteger();
    }

    public Exchange(uxt uxtVar, Object obj, Origin origin, Executor executor) {
        this(uxtVar, obj, origin, executor, null, false);
    }

    public Exchange(uxt uxtVar, Object obj, Origin origin, Executor executor, EndpointContext endpointContext, boolean z) {
        this.h = new AtomicBoolean();
        boolean z2 = false;
        this.p = 0;
        AtomicReference<EndpointContext> atomicReference = new AtomicReference<>();
        this.o = atomicReference;
        if (uxtVar == null) {
            throw new NullPointerException("request must not be null!");
        }
        if (executor == null) {
            throw new NullPointerException("executor must not be null");
        }
        this.s = b.incrementAndGet();
        this.n = new SerialExecutor(executor);
        this.i = uxtVar;
        this.ad = uxtVar;
        this.y = origin;
        this.z = obj;
        atomicReference.set(endpointContext);
        if (!z && uxtVar.o() && origin == Origin.LOCAL) {
            z2 = true;
        }
        this.q = z2;
        this.u = z;
        this.r = ClockUtil.d();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Exchange[");
        sb.append(this.y == Origin.LOCAL ? 'L' : 'R');
        sb.append(this.s);
        sb.append(", ");
        Object obj = this.z;
        if (obj instanceof InetSocketAddress) {
            sb.append(vcb.c((InetSocketAddress) obj));
        } else {
            sb.append(obj);
        }
        if (this.h.get()) {
            sb.append(", complete");
        }
        sb.append(']');
        return sb.toString();
    }

    public void z() {
        e(this.i.getSourceContext());
    }

    public void e(EndpointContext endpointContext) {
        uxt uxtVar = this.i;
        if (!uxtVar.hasMID() || uxtVar.isRejected() || uxtVar.isAcknowledged()) {
            return;
        }
        uxtVar.setRejected(true);
        if (uxtVar.h()) {
            return;
        }
        this.l.sendEmptyMessage(this, uxn.b(uxtVar, endpointContext));
    }

    public Origin n() {
        return this.y;
    }

    public boolean w() {
        return this.y == Origin.LOCAL;
    }

    public boolean y() {
        return this.u;
    }

    public uxt p() {
        return this.ad;
    }

    public void b(uxt uxtVar) {
        uxu token;
        ai();
        if (this.ad != uxtVar) {
            if (this.q && (token = this.ad.getToken()) != null && !token.equals(uxtVar.getToken())) {
                throw new IllegalArgumentException(this + " token missmatch (" + token + "!=" + uxtVar.getToken() + ")!");
            }
            this.ad = uxtVar;
        }
    }

    public uxt i() {
        return this.i;
    }

    public void c(uxt uxtVar) {
        ai();
        if (this.i != uxtVar) {
            c((ScheduledFuture<?>) null);
            this.p = 0;
            f15861a.debug("{} replace {} by {}", this, this.i, uxtVar);
            this.i = uxtVar;
        }
    }

    public uxr t() {
        return this.ac;
    }

    public void b(uxr uxrVar) {
        ai();
        this.ac = uxrVar;
    }

    public uxr f() {
        return this.j;
    }

    public void e(uxr uxrVar) {
        ai();
        if (this.j != uxrVar) {
            if (!w() && this.g != null && this.j != null && this.j.getType() == CoAP.Type.NON && this.j.g()) {
                f15861a.info("{} store NON notification: {}", this, this.g);
                long d2 = ClockUtil.d();
                RemoveHandler removeHandler = this.ab;
                while (true) {
                    if (!this.x.isEmpty()) {
                        c cVar = this.x.get(0);
                        if (!cVar.a(d2)) {
                            break;
                        }
                        this.x.remove(0);
                        if (removeHandler != null) {
                            uyf a2 = cVar.a();
                            f15861a.info("{} removing expired NON notification: {}", this, a2);
                            removeHandler.remove(this, null, a2);
                        }
                    } else {
                        break;
                    }
                }
                if (this.t == 0) {
                    Endpoint endpoint = this.l;
                    if (endpoint != null) {
                        this.t = endpoint.getConfig().a(CoapConfig.af, TimeUnit.NANOSECONDS).longValue();
                    } else {
                        this.t = TimeUnit.SECONDS.toNanos(145L);
                    }
                }
                this.x.add(new c(this.g, d2 + this.t));
                this.g = null;
            }
            this.j = uxrVar;
        }
    }

    public uyf m() {
        return this.g;
    }

    public void c(uyf uyfVar) {
        uyf uyfVar2;
        ai();
        if (uyfVar.equals(this.g)) {
            return;
        }
        RemoveHandler removeHandler = this.ab;
        if (removeHandler != null && (uyfVar2 = this.g) != null) {
            removeHandler.remove(this, null, uyfVar2);
        }
        this.g = uyfVar;
    }

    public void e(uyc uycVar) {
        uyc uycVar2;
        ai();
        if (!w()) {
            throw new IllegalStateException("Token is only supported for local exchanges!");
        }
        if (uycVar.equals(this.f)) {
            return;
        }
        RemoveHandler removeHandler = this.ab;
        if (removeHandler != null && (uycVar2 = this.f) != null && !uycVar2.equals(this.w)) {
            removeHandler.remove(this, this.f, null);
        }
        this.f = uycVar;
        if (this.q && this.w == null) {
            this.w = uycVar;
        }
    }

    public uyc k() {
        return this.f;
    }

    public uxh d() {
        return this.e;
    }

    public void a(uxh uxhVar) {
        this.e = uxhVar;
    }

    public void d(Endpoint endpoint) {
        this.l = endpoint;
    }

    public Object q() {
        return this.z;
    }

    public void d(Message message) {
        ai();
        f15861a.debug("{} timed out {}!", this, message);
        if (v()) {
            return;
        }
        af();
        this.ah = true;
        message.setTimedOut(true);
        if (this.ad == null || this.ad == message || this.i != message) {
            return;
        }
        this.ad.setTimedOut(true);
    }

    public int o() {
        return this.p;
    }

    public int x() {
        ai();
        int i = this.p + 1;
        this.p = i;
        return i;
    }

    public float u() {
        return this.ae;
    }

    public void c(float f) {
        if (f < 1.0f) {
            throw new IllegalArgumentException("Timeout scale factor must be at least 1.0, not " + f);
        }
        this.ae = f;
    }

    public int h() {
        return this.k;
    }

    public void b(int i) {
        if (i <= 1) {
            throw new IllegalArgumentException("Timeout  must be larger than 1 ms, not " + i);
        }
        this.k = i;
    }

    public boolean ac() {
        return this.ag != null;
    }

    public void c(ScheduledFuture<?> scheduledFuture) {
        ai();
        if (!this.h.get() || scheduledFuture == null) {
            ScheduledFuture<?> scheduledFuture2 = this.ag;
            this.ag = scheduledFuture;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
            }
        }
    }

    public void ad() {
        ai();
        if (this.y == Origin.REMOTE) {
            this.c = null;
            this.h.set(false);
        } else {
            throw new IllegalStateException(this + " retransmit on local exchange not allowed!");
        }
    }

    public void a(int i) {
        if (i < 0 || i > 16777215) {
            throw new IllegalArgumentException(this + " illegal observe number");
        }
        this.v = Integer.valueOf(i);
    }

    public Integer l() {
        return this.v;
    }

    public void a(RemoveHandler removeHandler) {
        this.ab = removeHandler;
    }

    public boolean v() {
        return this.h.get();
    }

    public Throwable j() {
        return this.c;
    }

    public boolean af() {
        ai();
        if (this.h.compareAndSet(false, true)) {
            if (d) {
                this.c = new Throwable(toString());
                Logger logger = f15861a;
                if (logger.isTraceEnabled()) {
                    logger.trace("{}!", this, this.c);
                } else {
                    logger.debug("{}!", this);
                }
            } else {
                f15861a.debug("{}!", this);
            }
            c((ScheduledFuture<?>) null);
            RemoveHandler removeHandler = this.ab;
            if (removeHandler != null) {
                if (this.y == Origin.LOCAL) {
                    uyc uycVar = this.f;
                    if (uycVar != null || this.g != null) {
                        removeHandler.remove(this, uycVar, this.g);
                    }
                    uyc uycVar2 = this.f;
                    uyc uycVar3 = this.w;
                    if (uycVar2 != uycVar3) {
                        removeHandler.remove(this, uycVar3, null);
                    }
                    Logger logger2 = f15861a;
                    if (logger2.isDebugEnabled()) {
                        uxt i = i();
                        uxt p = p();
                        if (p == i) {
                            logger2.debug("local {} completed {}!", this, p);
                        } else {
                            logger2.debug("local {} completed {} -/- {}!", this, p, i);
                        }
                    }
                } else {
                    uxr f = f();
                    if (f == null) {
                        f15861a.debug("remote {} rejected (without response)!", this);
                    } else {
                        uyf uyfVar = this.g;
                        if (uyfVar != null) {
                            removeHandler.remove(this, null, uyfVar);
                        }
                        ab();
                        uxr t = t();
                        if (t == f || t == null) {
                            f15861a.debug("Remote {} completed {}!", this, f);
                        } else {
                            f15861a.debug("Remote {} completed {} -/- {}!", this, t, f);
                        }
                    }
                }
            }
            return true;
        }
        throw new uxx(this + " already complete!", this.c);
    }

    public boolean c() {
        if (this.h.get()) {
            return false;
        }
        if (b()) {
            af();
            return true;
        }
        d(new Runnable() { // from class: org.eclipse.californium.core.network.Exchange.1
            @Override // java.lang.Runnable
            public void run() {
                if (Exchange.this.h.get()) {
                    return;
                }
                Exchange.this.af();
            }
        });
        return true;
    }

    public long s() {
        return this.af;
    }

    public void d(long j) {
        this.af = j;
    }

    public void ae() {
        this.an = true;
        this.ai = false;
        this.aj = ClockUtil.d();
    }

    public long e() {
        boolean z = this.ai;
        if (!z && !this.an) {
            throw new IllegalStateException("startTransmissionRtt must be called before!");
        }
        if (!z) {
            this.ai = true;
            this.an = false;
            long d2 = ClockUtil.d() - this.aj;
            this.aj = d2;
            if (d2 == 0) {
                this.aj = 1L;
            }
        }
        return this.aj;
    }

    public long a() {
        return ClockUtil.d() - this.r;
    }

    public ObserveRelation r() {
        return this.aa;
    }

    public void ab() {
        ai();
        if (this.x == null || this.x.isEmpty()) {
            return;
        }
        RemoveHandler removeHandler = this.ab;
        if (removeHandler != null) {
            Iterator<c> it = this.x.iterator();
            while (it.hasNext()) {
                uyf a2 = it.next().a();
                f15861a.info("{} removing NON notification: {}", this, a2);
                removeHandler.remove(this, null, a2);
            }
        }
        this.x.clear();
        f15861a.debug("{} removed all remaining NON-notifications of observe relation with {}", this, this.aa.f());
    }

    public void c(EndpointContext endpointContext) {
        EndpointContextOperator endpointContextOperator = this.m;
        if (endpointContextOperator != null) {
            endpointContext = endpointContextOperator.apply(endpointContext);
        }
        if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.o, null, endpointContext)) {
            i().onContextEstablished(endpointContext);
        } else {
            this.o.set(endpointContext);
        }
    }

    public void aa() {
        this.o.set(null);
    }

    public EndpointContext g() {
        return this.o.get();
    }

    public void d(Runnable runnable) {
        try {
            if (b()) {
                runnable.run();
            } else {
                this.n.execute(runnable);
            }
        } catch (RejectedExecutionException e) {
            f15861a.debug("{} execute:", this, e);
        } catch (Throwable th) {
            f15861a.error("{} execute:", this, th);
        }
    }

    public void b(Object obj) {
        ai();
        if (this.h.get()) {
            throw new uxx(this + " is already complete! " + obj, this.c);
        }
    }

    private void ai() {
        this.n.d();
    }

    public boolean b() {
        return this.n.a();
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private long f15862a;
        private uyf e;

        private c(uyf uyfVar, long j) {
            this.e = uyfVar;
            this.f15862a = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(long j) {
            return j - this.f15862a > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public uyf a() {
            return this.e;
        }
    }
}

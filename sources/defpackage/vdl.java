package defpackage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache;
import org.eclipse.californium.elements.util.SerialExecutor;
import org.eclipse.californium.scandium.ConnectionListener;
import org.eclipse.californium.scandium.dtls.ConnectionIdGenerator;
import org.eclipse.californium.scandium.dtls.Handshaker;
import org.eclipse.californium.scandium.dtls.ReadWriteLockConnectionStore;
import org.eclipse.californium.scandium.dtls.SessionStore;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vdl implements ReadWriteLockConnectionStore {
    private static final vbw f;
    private static final Logger g;
    private static boolean h;

    /* renamed from: a, reason: collision with root package name */
    protected final ConcurrentMap<Principal, vcm> f17679a;
    protected final LeastRecentlyUpdatedCache<vcp, vcm> b;
    protected final ConcurrentMap<vej, vcm> c;
    protected final ConcurrentMap<InetSocketAddress, vcm> e;
    private ConnectionIdGenerator i;
    private ConnectionListener j;
    private volatile ExecutorService k;
    private final SessionStore l;
    private volatile long o;
    private final AtomicBoolean n = new AtomicBoolean();
    protected String d = "";

    static {
        Logger a2 = vha.a((Class<?>) vdl.class);
        g = a2;
        f = new vbw(a2, 3L, TimeUnit.SECONDS.toNanos(10L));
        h = true;
    }

    public vdl(int i, long j, SessionStore sessionStore, boolean z) {
        LeastRecentlyUpdatedCache<vcp, vcm> leastRecentlyUpdatedCache = new LeastRecentlyUpdatedCache<>(i, j, TimeUnit.SECONDS);
        this.b = leastRecentlyUpdatedCache;
        this.e = new ConcurrentHashMap();
        this.f17679a = z ? new ConcurrentHashMap() : null;
        this.l = sessionStore;
        if (h && sessionStore != null) {
            this.c = null;
        } else {
            this.c = new ConcurrentHashMap();
        }
        leastRecentlyUpdatedCache.b(new LeastRecentlyUpdatedCache.EvictionListener<vcm>() { // from class: vdl.2
            @Override // org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache.EvictionListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onEviction(final vcm vcmVar) {
                Runnable runnable = new Runnable() { // from class: vdl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Handshaker l = vcmVar.l();
                        if (l != null) {
                            l.handshakeFailed(new vco("Evicted!"));
                        }
                        synchronized (vdl.this) {
                            vdl.this.b(vcmVar);
                            vdl.this.a(vcmVar.f(), vcmVar);
                            ConnectionListener connectionListener = vdl.this.j;
                            if (connectionListener != null) {
                                connectionListener.onConnectionRemoved(vcmVar);
                            }
                        }
                    }
                };
                if (vcmVar.u()) {
                    vcmVar.g().execute(runnable);
                } else {
                    runnable.run();
                }
            }
        });
        g.info("Created new InMemoryConnectionStore [capacity: {}, connection expiration threshold: {}s]", Integer.valueOf(i), Long.valueOf(j));
    }

    public vdl e(String str) {
        synchronized (this) {
            this.d = vcb.h(str);
        }
        return this;
    }

    private vcp d() {
        for (int i = 0; i < 10; i++) {
            vcp createConnectionId = this.i.createConnectionId();
            if (this.b.c(createConnectionId) == null) {
                return createConnectionId;
            }
        }
        return null;
    }

    @Override // org.eclipse.californium.scandium.dtls.ReadWriteLockConnectionStore
    public ReentrantReadWriteLock.ReadLock readLock() {
        return this.b.d();
    }

    @Override // org.eclipse.californium.scandium.dtls.ReadWriteLockConnectionStore
    public ReentrantReadWriteLock.WriteLock writeLock() {
        return this.b.g();
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void setConnectionListener(ConnectionListener connectionListener) {
        this.j = connectionListener;
    }

    @Override // org.eclipse.californium.scandium.dtls.ReadWriteLockConnectionStore
    public void setExecutor(ExecutorService executorService) {
        this.k = executorService;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void attach(ConnectionIdGenerator connectionIdGenerator) {
        if (this.i != null) {
            throw new IllegalStateException("Connection id generator already attached!");
        }
        if (connectionIdGenerator == null || !connectionIdGenerator.useConnectionId()) {
            int numberOfLeadingZeros = (39 - Integer.numberOfLeadingZeros(this.b.e())) / 8;
            this.i = new veo(numberOfLeadingZeros + (numberOfLeadingZeros < 3 ? 2 : 3));
        } else {
            this.i = connectionIdGenerator;
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public boolean put(vcm vcmVar) {
        SessionStore sessionStore;
        boolean z = false;
        if (vcmVar == null) {
            return false;
        }
        if (!vcmVar.u()) {
            throw new IllegalStateException("Connection is not executing!");
        }
        vcp c = vcmVar.c();
        if (c == null) {
            if (this.i == null) {
                throw new IllegalStateException("Connection id generator must be attached before!");
            }
            c = d();
            if (c == null) {
                throw new IllegalStateException("Connection ids exhausted!");
            }
            vcmVar.a(c);
        } else {
            if (c.d()) {
                throw new IllegalStateException("Connection must have a none empty connection id!");
            }
            if (this.b.c(c) != null) {
                throw new IllegalStateException("Connection id already used! " + c);
            }
        }
        vct i = vcmVar.i();
        this.b.g().lock();
        try {
            if (this.b.d(c, vcmVar)) {
                Logger logger = g;
                if (logger.isTraceEnabled()) {
                    logger.trace("{}connection: add {} (size {})", this.d, vcmVar, Integer.valueOf(this.b.j()), new Throwable("connection added!"));
                } else {
                    logger.debug("{}connection: add {} (size {})", this.d, c, Integer.valueOf(this.b.j()));
                }
                d(vcmVar);
                if (i != null) {
                    if (i.l() != null) {
                        b(i.l(), vcmVar, false);
                    }
                    d(i.n(), vcmVar);
                }
                z = true;
            } else {
                f.a("{}connection store is full! {} max. entries.", this.d, Integer.valueOf(this.b.e()));
            }
            if (z && (sessionStore = this.l) != null && i != null) {
                sessionStore.put(i);
            }
            return z;
        } finally {
            this.b.g().unlock();
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public boolean update(vcm vcmVar, InetSocketAddress inetSocketAddress) {
        if (vcmVar == null) {
            return false;
        }
        this.b.g().lock();
        try {
            if (this.b.e((LeastRecentlyUpdatedCache<vcp, vcm>) vcmVar.c()) != null) {
                vcmVar.y();
                if (inetSocketAddress == null) {
                    g.debug("{}connection: {} updated usage!", this.d, vcmVar.c());
                } else if (!vcmVar.b(inetSocketAddress)) {
                    InetSocketAddress o = vcmVar.o();
                    Logger logger = g;
                    if (logger.isTraceEnabled()) {
                        logger.trace("{}connection: {} updated, address changed from {} to {}!", this.d, vcmVar.c(), vcb.b((SocketAddress) o), vcb.b((SocketAddress) inetSocketAddress), new Throwable("connection updated!"));
                    } else {
                        logger.debug("{}connection: {} updated, address changed from {} to {}!", this.d, vcmVar.c(), vcb.b((SocketAddress) o), vcb.b((SocketAddress) inetSocketAddress));
                    }
                    if (o != null) {
                        this.e.remove(o, vcmVar);
                        vcmVar.c((InetSocketAddress) null);
                    }
                    vcmVar.c(inetSocketAddress);
                    d(vcmVar);
                }
                return true;
            }
            g.debug("{}connection: {} - {} update failed!", this.d, vcmVar.c(), vcb.b((SocketAddress) inetSocketAddress));
            return false;
        } finally {
            this.b.g().unlock();
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void putEstablishedSession(vcm vcmVar) {
        SessionStore sessionStore;
        vct i = vcmVar.i();
        if (i == null) {
            throw new IllegalArgumentException("connection has no established session!");
        }
        ConnectionListener connectionListener = this.j;
        if (connectionListener != null) {
            connectionListener.onConnectionEstablished(vcmVar);
        }
        Principal l = i.l();
        vej n = i.n();
        boolean z = !n.d();
        if (l != null || z) {
            this.b.g().lock();
            if (l != null) {
                try {
                    b(l, vcmVar, false);
                } catch (Throwable th) {
                    this.b.g().unlock();
                    throw th;
                }
            }
            if (z) {
                d(n, vcmVar);
            }
            this.b.g().unlock();
            if (!z || (sessionStore = this.l) == null) {
                return;
            }
            sessionStore.put(i);
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void removeFromEstablishedSessions(vcm vcmVar) {
        vej f2 = vcmVar.f();
        if (f2 == null) {
            throw new IllegalArgumentException("connection has no established session!");
        }
        this.b.g().lock();
        try {
            a(f2, vcmVar);
        } finally {
            this.b.g().unlock();
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public vct find(vej vejVar) {
        if (vejVar == null || vejVar.d()) {
            return null;
        }
        SessionStore sessionStore = this.l;
        vct vctVar = sessionStore != null ? sessionStore.get(vejVar) : null;
        vcm d = d(vejVar);
        if (d == null) {
            return vctVar;
        }
        if (this.l == null) {
            vct i = d.i();
            return i != null ? new vct(i) : vctVar;
        }
        if (vctVar != null) {
            return vctVar;
        }
        remove(d, false);
        return null;
    }

    private vcm d(vej vejVar) {
        if (vejVar == null) {
            throw new NullPointerException("DTLS Session ID must not be null!");
        }
        ConcurrentMap<vej, vcm> concurrentMap = this.c;
        if (concurrentMap == null) {
            return null;
        }
        vcm vcmVar = concurrentMap.get(vejVar);
        if (vcmVar != null) {
            vej f2 = vcmVar.f();
            if (f2 != null) {
                if (!vejVar.equals(f2)) {
                    g.warn("{}connection {} changed session {}!={}!", this.d, vcmVar.c(), vejVar, f2);
                }
            } else {
                g.warn("{}connection {} lost session {}!", this.d, vcmVar.c(), vejVar);
            }
            this.b.e((LeastRecentlyUpdatedCache<vcp, vcm>) vcmVar.c());
        }
        return vcmVar;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void markAllAsResumptionRequired() {
        for (vcm vcmVar : this.b.f()) {
            if (vcmVar.o() != null && !vcmVar.v()) {
                vcmVar.b(true);
                g.trace("{}connection: mark for resumption {}!", this.d, vcmVar);
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public int remainingCapacity() {
        int b = this.b.b();
        g.debug("{}connection: size {}, remaining {}!", this.d, Integer.valueOf(this.b.j()), Integer.valueOf(b));
        return b;
    }

    @Override // org.eclipse.californium.scandium.dtls.ReadWriteLockConnectionStore
    public void shrink(int i, AtomicBoolean atomicBoolean) {
        int j;
        if (this.f17679a == null || 1024 >= (j = this.b.j())) {
            return;
        }
        int size = this.f17679a.size();
        if (size * 2 < j || i % 12 == 9) {
            if (this.n.compareAndSet(false, true)) {
                g.info("{}: start shrinking {}/{}", this.d, Integer.valueOf(size), Integer.valueOf(j));
                a(atomicBoolean, false);
                return;
            } else {
                g.info("{}: shrinking {}/{} ...", this.d, Integer.valueOf(size), Integer.valueOf(j));
                return;
            }
        }
        g.info("{}: no shrinking {}/{}", this.d, Integer.valueOf(size), Integer.valueOf(j));
    }

    private void a(AtomicBoolean atomicBoolean, boolean z) {
        int max = Math.max(10000, this.b.j() / 5);
        this.o = ClockUtil.d();
        Iterator<vcm> c = this.b.c();
        int i = 0;
        int i2 = 0;
        while (atomicBoolean.get() && c.hasNext()) {
            try {
                final vcm next = c.next();
                i++;
                if (i % max == 0) {
                    g.info("{}shrink {}: {}", this.d, Integer.valueOf(i), next.c());
                }
                if (next.w()) {
                    if (this.b.b((LeastRecentlyUpdatedCache<vcp, vcm>) next.c())) {
                        Runnable runnable = new Runnable() { // from class: vdl.1
                            @Override // java.lang.Runnable
                            public void run() {
                                vdl.g.trace("{}Remove connection from stale principals", vdl.this.d);
                                vdl.this.remove(next, false);
                            }
                        };
                        if (next.u()) {
                            next.g().execute(runnable);
                        } else {
                            remove(next, false);
                        }
                        i2++;
                    } else if (!z) {
                        break;
                    }
                }
            } catch (Throwable th) {
                this.o = ClockUtil.d() - this.o;
                g.error("{}: shrinking failed, {} of {}/{} in {} ms", this.d, Integer.valueOf(i2), Integer.valueOf(this.f17679a.size()), Integer.valueOf(this.b.j()), Long.valueOf(TimeUnit.NANOSECONDS.toMillis(this.o)), th);
            }
        }
        this.o = ClockUtil.d() - this.o;
        int j = this.b.j();
        int size = this.f17679a.size();
        if (i2 > 0) {
            g.info("{}: shrinked {} of {}/{} in {} ms", this.d, Integer.valueOf(i2), Integer.valueOf(size), Integer.valueOf(j), Long.valueOf(TimeUnit.NANOSECONDS.toMillis(this.o)), null);
        } else {
            g.info("{}: nothing shrinked, {}/{} in {} ms", this.d, Integer.valueOf(size), Integer.valueOf(j), Long.valueOf(TimeUnit.NANOSECONDS.toMillis(this.o)), null);
        }
        this.n.set(false);
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public vcm get(InetSocketAddress inetSocketAddress) {
        vcm vcmVar = this.e.get(inetSocketAddress);
        if (vcmVar == null) {
            g.trace("{}connection: missing connection for {}!", this.d, vcb.b((SocketAddress) inetSocketAddress));
        } else {
            InetSocketAddress o = vcmVar.o();
            if (o == null) {
                g.warn("{}connection {} lost ip-address {}!", this.d, vcmVar.c(), vcb.b((SocketAddress) inetSocketAddress));
            } else if (!o.equals(inetSocketAddress)) {
                g.warn("{}connection {} changed ip-address {}!={}!", this.d, vcmVar.c(), vcb.b((SocketAddress) inetSocketAddress), vcb.b((SocketAddress) o));
            }
        }
        return vcmVar;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public vcm get(vcp vcpVar) {
        vcm c = this.b.c(vcpVar);
        if (c == null) {
            g.debug("{}connection: missing connection for {}!", this.d, vcpVar);
        } else {
            vcp c2 = c.c();
            if (c2 == null) {
                g.warn("{}connection lost cid {}!", this.d, vcpVar);
            } else if (!c2.equals(vcpVar)) {
                g.warn("{}connection changed cid {}!={}!", this.d, c2, vcpVar);
            }
        }
        return c;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public boolean remove(vcm vcmVar, boolean z) {
        vct i = vcmVar.i();
        vej n = i == null ? null : i.n();
        Principal l = i == null ? null : i.l();
        this.b.g().lock();
        try {
            boolean z2 = this.b.a(vcmVar.c(), vcmVar) == vcmVar;
            if (z2) {
                int ac = vcmVar.ac();
                Logger logger = g;
                if (logger.isTraceEnabled()) {
                    logger.trace("{}connection: remove {} (size {}, left jobs: {})", this.d, vcmVar, Integer.valueOf(this.b.j()), Integer.valueOf(ac), new Throwable("connection removed!"));
                } else if (ac == 0) {
                    logger.debug("{}connection: remove {} (size {})", this.d, vcmVar, Integer.valueOf(this.b.j()));
                } else {
                    logger.debug("{}connection: remove {} (size {}, left jobs: {})", this.d, vcmVar, Integer.valueOf(this.b.j()), Integer.valueOf(ac));
                }
                vcmVar.a((vck) null);
                b(vcmVar);
                a(n, vcmVar);
                a(l, vcmVar);
                ConnectionListener connectionListener = this.j;
                if (connectionListener != null) {
                    connectionListener.onConnectionRemoved(vcmVar);
                }
                vfh.a(vcmVar.b());
            }
            if (z) {
                c(n);
            }
            return z2;
        } finally {
            this.b.g().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(vej vejVar, vcm vcmVar) {
        if (this.c == null || vejVar == null || vejVar.d()) {
            return;
        }
        this.c.remove(vejVar, vcmVar);
    }

    private void a(Principal principal, vcm vcmVar) {
        ConcurrentMap<Principal, vcm> concurrentMap = this.f17679a;
        if (concurrentMap == null || principal == null) {
            return;
        }
        concurrentMap.remove(principal, vcmVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(vcm vcmVar) {
        InetSocketAddress o = vcmVar.o();
        if (o != null) {
            this.e.remove(o, vcmVar);
            vcmVar.c((InetSocketAddress) null);
        }
    }

    private void c(vej vejVar) {
        if (this.l == null || vejVar == null || vejVar.d()) {
            return;
        }
        this.l.remove(vejVar);
    }

    private void d(vcm vcmVar) {
        final InetSocketAddress o = vcmVar.o();
        if (o != null) {
            final vcm put = this.e.put(o, vcmVar);
            if (put != null && put != vcmVar) {
                Runnable runnable = new Runnable() { // from class: vdl.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (put.b(o)) {
                            put.c((InetSocketAddress) null);
                            if (vdl.this.c != null || put.d()) {
                                return;
                            }
                            vdl.this.remove(put, false);
                        }
                    }
                };
                g.debug("{}connection: {} - {} added! {} removed from address.", this.d, vcmVar.c(), vcb.b((SocketAddress) o), put.c());
                if (put.u()) {
                    put.g().execute(runnable);
                    return;
                } else {
                    runnable.run();
                    return;
                }
            }
            g.debug("{}connection: {} - {} added!", this.d, vcmVar.c(), vcb.b((SocketAddress) o));
            return;
        }
        g.debug("{}connection: {} - missing address!", this.d, vcmVar.c());
    }

    private boolean d(vej vejVar, vcm vcmVar) {
        vcm put;
        ConcurrentMap<vej, vcm> concurrentMap = this.c;
        if (concurrentMap == null || (put = concurrentMap.put(vejVar, vcmVar)) == null || put == vcmVar) {
            return false;
        }
        b("session", put);
        return true;
    }

    private boolean b(Principal principal, vcm vcmVar, boolean z) {
        vcm put;
        ConcurrentMap<Principal, vcm> concurrentMap = this.f17679a;
        if (concurrentMap == null || (put = concurrentMap.put(principal, vcmVar)) == null || put == vcmVar) {
            return false;
        }
        if (z) {
            b("principal", put);
            return true;
        }
        put.aa();
        put.i().d(principal);
        return false;
    }

    private void b(final String str, final vcm vcmVar) {
        Runnable runnable = new Runnable() { // from class: vdl.4
            @Override // java.lang.Runnable
            public void run() {
                vdl.g.debug("{}Remove connection from {}", vdl.this.d, str);
                vdl.this.remove(vcmVar, false);
            }
        };
        if (vcmVar.u()) {
            vcmVar.g().execute(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public final void clear() {
        Iterator<vcm> it = this.b.f().iterator();
        while (it.hasNext()) {
            SerialExecutor g2 = it.next().g();
            if (g2 != null) {
                g2.shutdownNow();
            }
        }
        this.b.a();
        ConcurrentMap<vej, vcm> concurrentMap = this.c;
        if (concurrentMap != null) {
            concurrentMap.clear();
        }
        this.e.clear();
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public final void stop(List<Runnable> list) {
        Iterator<vcm> it = this.b.f().iterator();
        while (it.hasNext()) {
            SerialExecutor g2 = it.next().g();
            if (g2 != null) {
                g2.a(list);
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public Iterator<vcm> iterator() {
        return this.b.i();
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore, org.eclipse.californium.elements.PersistentConnector
    public int saveConnections(OutputStream outputStream, long j) throws IOException {
        long j2;
        Iterator<LeastRecentlyUpdatedCache.a<vcm>> it;
        int j3 = this.b.j();
        int i = j3 / 20;
        vbo vboVar = new vbo(4096);
        long d = ClockUtil.d();
        Iterator<LeastRecentlyUpdatedCache.a<vcm>> h2 = this.b.h();
        long j4 = d;
        int i2 = 0;
        boolean z = false;
        while (h2.hasNext()) {
            LeastRecentlyUpdatedCache.a<vcm> next = h2.next();
            long d2 = next.d();
            int i3 = i2;
            long seconds = TimeUnit.NANOSECONDS.toSeconds(d - d2);
            if (seconds > j) {
                g.trace("{}skip {} ts, {}s too quiet!", this.d, Long.valueOf(d2), Long.valueOf(seconds));
                j2 = d;
                it = h2;
                i2 = i3;
            } else {
                Logger logger = g;
                j2 = d;
                logger.trace("{}write {} ts, {}s ", this.d, Long.valueOf(d2), Long.valueOf(seconds));
                if (next.b().e(vboVar)) {
                    vboVar.e(outputStream);
                    i2 = i3 + 1;
                } else {
                    vboVar.b();
                    i2 = i3;
                }
                if (i > 100 && i2 % i == 0) {
                    z = true;
                }
                if (z) {
                    long d3 = ClockUtil.d();
                    if (z) {
                        it = h2;
                        if (d3 - j4 > TimeUnit.SECONDS.toNanos(2L)) {
                            logger.info("{}written {} connections of {}", this.d, Integer.valueOf(i2), Integer.valueOf(j3));
                            j4 = d3;
                            z = false;
                        }
                    }
                }
                it = h2;
            }
            h2 = it;
            d = j2;
        }
        int i4 = i2;
        vbt.a(outputStream);
        outputStream.flush();
        vboVar.a();
        clear();
        return i4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0056, code lost:
    
        if ((!r20.b.b((org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache<defpackage.vcp, defpackage.vcm>) r13.c())) == false) goto L36;
     */
    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore, org.eclipse.californium.elements.PersistentConnector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int loadConnections(java.io.InputStream r21, long r22) throws java.io.IOException {
        /*
            r20 = this;
            r1 = r20
            long r2 = org.eclipse.californium.elements.util.ClockUtil.d()
            vbq r0 = new vbq
            r4 = r21
            r0.<init>(r4)
            r4 = 0
            r6 = r2
            r5 = r4
        L10:
            r9 = 3
            r11 = r22
            vcm r13 = defpackage.vcm.b(r0, r11)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            if (r13 == 0) goto Lb3
            long r14 = r13.k()     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            long r16 = r14 - r2
            r18 = 0
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 <= 0) goto L44
            vbw r8 = defpackage.vdl.f     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.String r9 = r1.d     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r10[r4] = r9     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.Long r9 = java.lang.Long.valueOf(r14)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r16 = 1
            r10[r16] = r9     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.Long r9 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r18 = 2
            r10[r18] = r9     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.String r9 = "{}read {} ts is after {} (future)"
            r8.d(r9, r10)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            goto L58
        L44:
            boolean r8 = r13.w()     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            if (r8 == 0) goto L58
            org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache<vcp, vcm> r8 = r1.b     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            vcp r9 = r13.c()     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            boolean r8 = r8.b(r9)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r9 = 1
            r8 = r8 ^ r9
            if (r8 == 0) goto L83
        L58:
            org.slf4j.Logger r8 = defpackage.vdl.g     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r9 = 3
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.String r9 = r1.d     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r10[r4] = r9     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.Long r9 = java.lang.Long.valueOf(r14)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r16 = 1
            r10[r16] = r9     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            long r14 = r2 - r14
            long r14 = r9.toSeconds(r14)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.Long r9 = java.lang.Long.valueOf(r14)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r14 = 2
            r10[r14] = r9     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            java.lang.String r9 = "{}read {} ts, {}s"
            r8.trace(r9, r10)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            r1.restore(r13)     // Catch: java.lang.Throwable -> Lb4 java.lang.IllegalArgumentException -> Lb6
            int r5 = r5 + 1
        L83:
            long r8 = org.eclipse.californium.elements.util.ClockUtil.d()     // Catch: java.lang.IllegalArgumentException -> Laf java.lang.Throwable -> Lb4
            long r13 = r8 - r6
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.IllegalArgumentException -> Laf java.lang.Throwable -> Lb4
            r18 = r5
            r4 = 2
            long r4 = r10.toNanos(r4)     // Catch: java.lang.IllegalArgumentException -> Lab java.lang.Throwable -> Lb4
            int r4 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r4 <= 0) goto La6
            org.slf4j.Logger r4 = defpackage.vdl.g     // Catch: java.lang.IllegalArgumentException -> Lab java.lang.Throwable -> Lb4
            java.lang.String r5 = "{}read {} connections"
            java.lang.String r6 = r1.d     // Catch: java.lang.IllegalArgumentException -> Lab java.lang.Throwable -> Lb4
            java.lang.Integer r7 = java.lang.Integer.valueOf(r18)     // Catch: java.lang.IllegalArgumentException -> Lab java.lang.Throwable -> Lb4
            r4.info(r5, r6, r7)     // Catch: java.lang.IllegalArgumentException -> Lab java.lang.Throwable -> Lb4
            r6 = r8
        La6:
            r5 = r18
            r4 = 0
            goto L10
        Lab:
            r0 = move-exception
            r5 = r18
            goto Lb7
        Laf:
            r0 = move-exception
            r18 = r5
            goto Lb7
        Lb3:
            return r5
        Lb4:
            r0 = move-exception
            goto Ld5
        Lb6:
            r0 = move-exception
        Lb7:
            org.slf4j.Logger r2 = defpackage.vdl.g     // Catch: java.lang.Throwable -> Lb4
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r4 = r1.d     // Catch: java.lang.Throwable -> Lb4
            r6 = 0
            r3[r6] = r4     // Catch: java.lang.Throwable -> Lb4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> Lb4
            r5 = 1
            r3[r5] = r4     // Catch: java.lang.Throwable -> Lb4
            r4 = 2
            r3[r4] = r0     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r4 = "{}reading failed after {} connections"
            r2.warn(r4, r3)     // Catch: java.lang.Throwable -> Lb4
            r20.clear()     // Catch: java.lang.Throwable -> Lb4
            throw r0     // Catch: java.lang.Throwable -> Lb4
        Ld5:
            r20.clear()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vdl.loadConnections(java.io.InputStream, long):int");
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public boolean restore(vcm vcmVar) {
        vcp c = vcmVar.c();
        if (c == null) {
            throw new IllegalStateException("Connection must have a connection id!");
        }
        if (c.d()) {
            throw new IllegalStateException("Connection must have a none empty connection id!");
        }
        if (this.b.c(c) != null) {
            throw new IllegalStateException("Connection id already used! " + c);
        }
        this.b.g().lock();
        try {
            boolean z = false;
            if (this.b.b(c, vcmVar, vcmVar.k())) {
                Logger logger = g;
                if (logger.isTraceEnabled()) {
                    logger.trace("{}connection: add {} (size {})", this.d, vcmVar, Integer.valueOf(this.b.j()), new Throwable("connection added!"));
                } else {
                    logger.debug("{}connection: add {} (size {})", this.d, c, Integer.valueOf(this.b.j()));
                }
                d(vcmVar);
                if (!vcmVar.u()) {
                    vcmVar.e(this.k, this.j);
                }
                z = true;
            } else {
                g.warn("{}connection store is full! {} max. entries.", this.d, Integer.valueOf(this.b.e()));
            }
            if (z && vcmVar.q()) {
                putEstablishedSession(vcmVar);
            }
            return z;
        } finally {
            this.b.g().unlock();
        }
    }
}

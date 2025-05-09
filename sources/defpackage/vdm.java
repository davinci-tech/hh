package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.LeastRecentlyUsedCache;
import org.eclipse.californium.elements.util.SerialExecutor;
import org.eclipse.californium.scandium.ConnectionListener;
import org.eclipse.californium.scandium.dtls.ConnectionIdGenerator;
import org.eclipse.californium.scandium.dtls.Handshaker;
import org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore;
import org.eclipse.californium.scandium.dtls.SessionStore;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vdm implements ResumptionSupportingConnectionStore {
    private static final Logger c;
    private static final vbw g;
    private static boolean i;

    /* renamed from: a, reason: collision with root package name */
    protected String f17683a;
    protected final ConcurrentMap<InetSocketAddress, vcm> b;
    protected final ConcurrentMap<vej, vcm> d;
    protected final LeastRecentlyUsedCache<vcp, vcm> e;
    private ConnectionListener f;
    private ConnectionIdGenerator h;
    private final SessionStore j;

    static {
        Logger a2 = vha.a((Class<?>) vdm.class);
        c = a2;
        g = new vbw(a2, 3L, TimeUnit.SECONDS.toNanos(10L));
        i = true;
    }

    public vdm() {
        this(150000, 129600L, null);
    }

    public vdm(int i2, long j, SessionStore sessionStore) {
        this.f17683a = "";
        LeastRecentlyUsedCache<vcp, vcm> leastRecentlyUsedCache = new LeastRecentlyUsedCache<>(i2, j);
        this.e = leastRecentlyUsedCache;
        leastRecentlyUsedCache.c(false);
        leastRecentlyUsedCache.e(false);
        this.b = new ConcurrentHashMap();
        this.j = sessionStore;
        if (i && sessionStore != null) {
            this.d = null;
        } else {
            this.d = new ConcurrentHashMap();
        }
        leastRecentlyUsedCache.a(new LeastRecentlyUsedCache.EvictionListener<vcm>() { // from class: vdm.5
            @Override // org.eclipse.californium.elements.util.LeastRecentlyUsedCache.EvictionListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onEviction(final vcm vcmVar) {
                Runnable runnable = new Runnable() { // from class: vdm.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Handshaker l = vcmVar.l();
                        if (l != null) {
                            l.handshakeFailed(new vco("Evicted!"));
                        }
                        synchronized (vdm.this) {
                            vdm.this.e(vcmVar);
                            vdm.this.d(vcmVar.f(), vcmVar);
                            ConnectionListener connectionListener = vdm.this.f;
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
        c.info("Created new InMemoryConnectionStore [capacity: {}, connection expiration threshold: {}s]", Integer.valueOf(i2), Long.valueOf(j));
    }

    public vdm a(String str) {
        synchronized (this) {
            this.f17683a = vcb.h(str);
        }
        return this;
    }

    private vcp c() {
        for (int i2 = 0; i2 < 10; i2++) {
            vcp createConnectionId = this.h.createConnectionId();
            if (this.e.a((LeastRecentlyUsedCache<vcp, vcm>) createConnectionId) == null) {
                return createConnectionId;
            }
        }
        return null;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void setConnectionListener(ConnectionListener connectionListener) {
        this.f = connectionListener;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void attach(ConnectionIdGenerator connectionIdGenerator) {
        if (this.h != null) {
            throw new IllegalStateException("Connection id generator already attached!");
        }
        if (connectionIdGenerator == null || !connectionIdGenerator.useConnectionId()) {
            int numberOfLeadingZeros = (39 - Integer.numberOfLeadingZeros(this.e.b())) / 8;
            this.h = new veo(numberOfLeadingZeros + (numberOfLeadingZeros < 3 ? 2 : 3));
        } else {
            this.h = connectionIdGenerator;
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
        vcp c2 = vcmVar.c();
        if (c2 == null) {
            if (this.h == null) {
                throw new IllegalStateException("Connection id generator must be attached before!");
            }
            c2 = c();
            if (c2 == null) {
                throw new IllegalStateException("Connection ids exhausted!");
            }
            vcmVar.a(c2);
        } else {
            if (c2.d()) {
                throw new IllegalStateException("Connection must have a none empty connection id!");
            }
            if (this.e.a((LeastRecentlyUsedCache<vcp, vcm>) c2) != null) {
                throw new IllegalStateException("Connection id already used! " + c2);
            }
        }
        vct i2 = vcmVar.i();
        synchronized (this) {
            if (this.e.c((LeastRecentlyUsedCache<vcp, vcm>) c2, (vcp) vcmVar)) {
                Logger logger = c;
                if (logger.isTraceEnabled()) {
                    logger.trace("{}connection: add {} (size {})", this.f17683a, vcmVar, Integer.valueOf(this.e.e()), new Throwable("connection added!"));
                } else {
                    logger.debug("{}connection: add {} (size {})", this.f17683a, c2, Integer.valueOf(this.e.e()));
                }
                c(vcmVar);
                if (i2 != null) {
                    c(i2.n(), vcmVar);
                }
                z = true;
            } else {
                g.a("{}connection store is full! {} max. entries.", this.f17683a, Integer.valueOf(this.e.b()));
            }
        }
        if (z && (sessionStore = this.j) != null && i2 != null) {
            sessionStore.put(i2);
        }
        return z;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public boolean update(vcm vcmVar, InetSocketAddress inetSocketAddress) {
        synchronized (this) {
            if (vcmVar == null) {
                return false;
            }
            if (this.e.c((LeastRecentlyUsedCache<vcp, vcm>) vcmVar.c())) {
                vcmVar.y();
                if (inetSocketAddress == null) {
                    c.debug("{}connection: {} updated usage!", this.f17683a, vcmVar.c());
                } else if (!vcmVar.b(inetSocketAddress)) {
                    InetSocketAddress o = vcmVar.o();
                    Logger logger = c;
                    if (logger.isTraceEnabled()) {
                        logger.trace("{}connection: {} updated, address changed from {} to {}!", this.f17683a, vcmVar.c(), vcb.b((SocketAddress) o), vcb.b((SocketAddress) inetSocketAddress), new Throwable("connection updated!"));
                    } else {
                        logger.debug("{}connection: {} updated, address changed from {} to {}!", this.f17683a, vcmVar.c(), vcb.b((SocketAddress) o), vcb.b((SocketAddress) inetSocketAddress));
                    }
                    if (o != null) {
                        this.b.remove(o, vcmVar);
                        vcmVar.c((InetSocketAddress) null);
                    }
                    vcmVar.c(inetSocketAddress);
                    c(vcmVar);
                }
                return true;
            }
            c.debug("{}connection: {} - {} update failed!", this.f17683a, vcmVar.c(), vcb.b((SocketAddress) inetSocketAddress));
            return false;
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void putEstablishedSession(vcm vcmVar) {
        vct i2 = vcmVar.i();
        if (i2 == null) {
            throw new IllegalArgumentException("connection has no established session!");
        }
        ConnectionListener connectionListener = this.f;
        if (connectionListener != null) {
            connectionListener.onConnectionEstablished(vcmVar);
        }
        vej n = i2.n();
        if (n.d()) {
            return;
        }
        synchronized (this) {
            c(n, vcmVar);
        }
        SessionStore sessionStore = this.j;
        if (sessionStore != null) {
            sessionStore.put(i2);
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void removeFromEstablishedSessions(vcm vcmVar) {
        synchronized (this) {
            vej f = vcmVar.f();
            if (f == null) {
                throw new IllegalArgumentException("connection has no established session!");
            }
            d(f, vcmVar);
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public vct find(vej vejVar) {
        if (vejVar == null || vejVar.d()) {
            return null;
        }
        SessionStore sessionStore = this.j;
        vct vctVar = sessionStore != null ? sessionStore.get(vejVar) : null;
        synchronized (this) {
            vcm b = b(vejVar);
            if (b != null) {
                if (this.j == null) {
                    vct i2 = b.i();
                    if (i2 != null) {
                        vctVar = new vct(i2);
                    }
                } else if (vctVar == null) {
                    remove(b, false);
                    return null;
                }
            }
            return vctVar;
        }
    }

    private vcm b(vej vejVar) {
        if (vejVar == null) {
            throw new NullPointerException("DTLS Session ID must not be null!");
        }
        ConcurrentMap<vej, vcm> concurrentMap = this.d;
        if (concurrentMap == null) {
            return null;
        }
        vcm vcmVar = concurrentMap.get(vejVar);
        if (vcmVar != null) {
            vej f = vcmVar.f();
            if (f != null) {
                if (!vejVar.equals(f)) {
                    c.warn("{}connection {} changed session {}!={}!", this.f17683a, vcmVar.c(), vejVar, f);
                }
            } else {
                c.warn("{}connection {} lost session {}!", this.f17683a, vcmVar.c(), vejVar);
            }
            this.e.c((LeastRecentlyUsedCache<vcp, vcm>) vcmVar.c());
        }
        return vcmVar;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public void markAllAsResumptionRequired() {
        synchronized (this) {
            for (vcm vcmVar : this.e.j()) {
                if (vcmVar.o() != null && !vcmVar.v()) {
                    vcmVar.b(true);
                    c.trace("{}connection: mark for resumption {}!", this.f17683a, vcmVar);
                }
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public int remainingCapacity() {
        int d;
        synchronized (this) {
            d = this.e.d();
            c.debug("{}connection: size {}, remaining {}!", this.f17683a, Integer.valueOf(this.e.e()), Integer.valueOf(d));
        }
        return d;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public vcm get(InetSocketAddress inetSocketAddress) {
        vcm vcmVar;
        synchronized (this) {
            vcmVar = this.b.get(inetSocketAddress);
            if (vcmVar == null) {
                c.trace("{}connection: missing connection for {}!", this.f17683a, vcb.b((SocketAddress) inetSocketAddress));
            } else {
                InetSocketAddress o = vcmVar.o();
                if (o == null) {
                    c.warn("{}connection {} lost ip-address {}!", this.f17683a, vcmVar.c(), vcb.b((SocketAddress) inetSocketAddress));
                } else if (!o.equals(inetSocketAddress)) {
                    c.warn("{}connection {} changed ip-address {}!={}!", this.f17683a, vcmVar.c(), vcb.b((SocketAddress) inetSocketAddress), vcb.b((SocketAddress) o));
                }
            }
        }
        return vcmVar;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public vcm get(vcp vcpVar) {
        vcm a2;
        synchronized (this) {
            a2 = this.e.a((LeastRecentlyUsedCache<vcp, vcm>) vcpVar);
        }
        if (a2 == null) {
            c.debug("{}connection: missing connection for {}!", this.f17683a, vcpVar);
        } else {
            vcp c2 = a2.c();
            if (c2 == null) {
                c.warn("{}connection lost cid {}!", this.f17683a, vcpVar);
            } else if (!c2.equals(vcpVar)) {
                c.warn("{}connection changed cid {}!={}!", this.f17683a, c2, vcpVar);
            }
        }
        return a2;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public boolean remove(vcm vcmVar, boolean z) {
        boolean z2;
        vej f = vcmVar.f();
        synchronized (this) {
            z2 = this.e.a((LeastRecentlyUsedCache<vcp, vcm>) vcmVar.c(), (vcp) vcmVar) == vcmVar;
            if (z2) {
                int ac = vcmVar.ac();
                Logger logger = c;
                if (logger.isTraceEnabled()) {
                    logger.trace("{}connection: remove {} (size {}, left jobs: {})", this.f17683a, vcmVar, Integer.valueOf(this.e.e()), Integer.valueOf(ac), new Throwable("connection removed!"));
                } else if (ac == 0) {
                    logger.debug("{}connection: remove {} (size {})", this.f17683a, vcmVar, Integer.valueOf(this.e.e()));
                } else {
                    logger.debug("{}connection: remove {} (size {}, left jobs: {})", this.f17683a, vcmVar, Integer.valueOf(this.e.e()), Integer.valueOf(ac));
                }
                vcmVar.a((vck) null);
                e(vcmVar);
                d(f, vcmVar);
                ConnectionListener connectionListener = this.f;
                if (connectionListener != null) {
                    connectionListener.onConnectionRemoved(vcmVar);
                }
                vfh.a(vcmVar.b());
            }
        }
        if (z) {
            d(f);
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(vej vejVar, vcm vcmVar) {
        if (this.d == null || vejVar == null || vejVar.d()) {
            return;
        }
        this.d.remove(vejVar, vcmVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(vcm vcmVar) {
        InetSocketAddress o = vcmVar.o();
        if (o != null) {
            this.b.remove(o, vcmVar);
            vcmVar.c((InetSocketAddress) null);
        }
    }

    private void d(vej vejVar) {
        if (this.j == null || vejVar == null || vejVar.d()) {
            return;
        }
        this.j.remove(vejVar);
    }

    private void c(vcm vcmVar) {
        final InetSocketAddress o = vcmVar.o();
        if (o != null) {
            final vcm put = this.b.put(o, vcmVar);
            if (put != null && put != vcmVar) {
                Runnable runnable = new Runnable() { // from class: vdm.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (put.b(o)) {
                            put.c((InetSocketAddress) null);
                            if (vdm.this.d != null || put.d()) {
                                return;
                            }
                            vdm.this.remove(put, false);
                        }
                    }
                };
                c.debug("{}connection: {} - {} added! {} removed from address.", this.f17683a, vcmVar.c(), vcb.b((SocketAddress) o), put.c());
                if (put.u()) {
                    put.g().execute(runnable);
                    return;
                } else {
                    runnable.run();
                    return;
                }
            }
            c.debug("{}connection: {} - {} added!", this.f17683a, vcmVar.c(), vcb.b((SocketAddress) o));
            return;
        }
        c.debug("{}connection: {} - missing address!", this.f17683a, vcmVar.c());
    }

    private void c(vej vejVar, vcm vcmVar) {
        final vcm put;
        ConcurrentMap<vej, vcm> concurrentMap = this.d;
        if (concurrentMap == null || (put = concurrentMap.put(vejVar, vcmVar)) == null || put == vcmVar) {
            return;
        }
        Runnable runnable = new Runnable() { // from class: vdm.1
            @Override // java.lang.Runnable
            public void run() {
                vdm.this.remove(put, false);
            }
        };
        if (put.u()) {
            put.g().execute(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public final void clear() {
        synchronized (this) {
            Iterator<vcm> it = this.e.j().iterator();
            while (it.hasNext()) {
                SerialExecutor g2 = it.next().g();
                if (g2 != null) {
                    g2.shutdownNow();
                }
            }
            this.e.c();
            ConcurrentMap<vej, vcm> concurrentMap = this.d;
            if (concurrentMap != null) {
                concurrentMap.clear();
            }
            this.b.clear();
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public final void stop(List<Runnable> list) {
        synchronized (this) {
            Iterator<vcm> it = this.e.j().iterator();
            while (it.hasNext()) {
                SerialExecutor g2 = it.next().g();
                if (g2 != null) {
                    g2.a(list);
                }
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public Iterator<vcm> iterator() {
        return this.e.a(false);
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore, org.eclipse.californium.elements.PersistentConnector
    public int saveConnections(OutputStream outputStream, long j) throws IOException {
        int i2;
        long j2;
        int e = this.e.e();
        int i3 = e / 20;
        vbo vboVar = new vbo(4096);
        long d = ClockUtil.d();
        synchronized (this.e) {
            Iterator<LeastRecentlyUsedCache.e<vcm>> a2 = this.e.a();
            long j3 = d;
            i2 = 0;
            boolean z = false;
            while (a2.hasNext()) {
                LeastRecentlyUsedCache.e<vcm> next = a2.next();
                long a3 = next.a();
                boolean z2 = z;
                long j4 = j3;
                long seconds = TimeUnit.NANOSECONDS.toSeconds(d - a3);
                if (seconds > j) {
                    c.trace("{}skip {} ts, {}s too quiet!", this.f17683a, Long.valueOf(a3), Long.valueOf(seconds));
                    j2 = d;
                    z = z2;
                } else {
                    Logger logger = c;
                    j2 = d;
                    logger.trace("{}write {} ts, {}s ", this.f17683a, Long.valueOf(a3), Long.valueOf(seconds));
                    if (next.b().e(vboVar)) {
                        vboVar.e(outputStream);
                        i2++;
                    } else {
                        vboVar.b();
                    }
                    z = (i3 <= 100 || i2 % i3 != 0) ? z2 : true;
                    if (z) {
                        long d2 = ClockUtil.d();
                        if (z && d2 - j4 > TimeUnit.SECONDS.toNanos(2L)) {
                            logger.info("{}written {} connections of {}", this.f17683a, Integer.valueOf(i2), Integer.valueOf(e));
                            z = false;
                            j4 = d2;
                        }
                    }
                }
                j3 = j4;
                d = j2;
            }
        }
        vbt.a(outputStream);
        outputStream.flush();
        vboVar.a();
        clear();
        return i2;
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore, org.eclipse.californium.elements.PersistentConnector
    public int loadConnections(InputStream inputStream, long j) throws IOException {
        long d = ClockUtil.d();
        vbq vbqVar = new vbq(inputStream);
        char c2 = 0;
        long j2 = d;
        int i2 = 0;
        while (true) {
            try {
                try {
                    vcm b = vcm.b(vbqVar, j);
                    if (b == null) {
                        return i2;
                    }
                    long k = b.k();
                    if (k - d > 0) {
                        vbw vbwVar = g;
                        Object[] objArr = new Object[3];
                        objArr[c2] = this.f17683a;
                        objArr[1] = Long.valueOf(k);
                        objArr[2] = Long.valueOf(d);
                        vbwVar.d("{}read {} ts is after {} (future)", objArr);
                    }
                    Logger logger = c;
                    Object[] objArr2 = new Object[3];
                    objArr2[c2] = this.f17683a;
                    objArr2[1] = Long.valueOf(k);
                    objArr2[2] = Long.valueOf(TimeUnit.NANOSECONDS.toSeconds(d - k));
                    logger.trace("{}read {} ts, {}s", objArr2);
                    restore(b);
                    i2++;
                    try {
                        long d2 = ClockUtil.d();
                        try {
                            if (d2 - j2 > TimeUnit.SECONDS.toNanos(2L)) {
                                logger.info("{}read {} connections", this.f17683a, Integer.valueOf(i2));
                                j2 = d2;
                            }
                            i2 = i2;
                            c2 = 0;
                        } catch (IllegalArgumentException e) {
                            e = e;
                            i2 = i2;
                            c.warn("{}reading failed after {} connections", this.f17683a, Integer.valueOf(i2), e);
                            throw e;
                        }
                    } catch (IllegalArgumentException e2) {
                        e = e2;
                    }
                } catch (IllegalArgumentException e3) {
                    e = e3;
                }
            } finally {
                clear();
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore
    public boolean restore(vcm vcmVar) {
        boolean z;
        vcp c2 = vcmVar.c();
        if (c2 == null) {
            throw new IllegalStateException("Connection must have a connection id!");
        }
        if (c2.d()) {
            throw new IllegalStateException("Connection must have a none empty connection id!");
        }
        if (this.e.a((LeastRecentlyUsedCache<vcp, vcm>) c2) != null) {
            throw new IllegalStateException("Connection id already used! " + c2);
        }
        synchronized (this.e) {
            z = false;
            if (this.e.b((LeastRecentlyUsedCache<vcp, vcm>) c2, (vcp) vcmVar, vcmVar.k())) {
                Logger logger = c;
                if (logger.isTraceEnabled()) {
                    logger.trace("{}connection: add {} (size {})", this.f17683a, vcmVar, Integer.valueOf(this.e.e()), new Throwable("connection added!"));
                } else {
                    logger.debug("{}connection: add {} (size {})", this.f17683a, c2, Integer.valueOf(this.e.e()));
                }
                c(vcmVar);
                z = true;
            } else {
                c.warn("{}connection store is full! {} max. entries.", this.f17683a, Integer.valueOf(this.e.b()));
            }
        }
        if (z && vcmVar.q()) {
            putEstablishedSession(vcmVar);
        }
        return z;
    }
}

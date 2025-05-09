package defpackage;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.observe.ObservationStore;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class uzt implements ObservationStore {
    private static final Logger b;
    private static final Logger e;

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f17622a;
    private final Configuration c;
    private ScheduledExecutorService d;
    private ScheduledFuture<?> g;
    private final ConcurrentMap<uxu, uzp> h = new ConcurrentHashMap();

    static {
        Logger a2 = vha.a((Class<?>) uzt.class);
        e = a2;
        b = vha.d(a2.getName() + ".health");
    }

    public uzt(Configuration configuration) {
        this.c = configuration;
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public void setExecutor(ScheduledExecutorService scheduledExecutorService) {
        this.d = scheduledExecutorService;
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public uzp putIfAbsent(uxu uxuVar, uzp uzpVar) {
        if (uxuVar == null) {
            throw new NullPointerException("token must not be null");
        }
        if (uzpVar == null) {
            throw new NullPointerException("observation must not be null");
        }
        this.f17622a = true;
        uzp putIfAbsent = this.h.putIfAbsent(uxuVar, uzpVar);
        if (putIfAbsent == null) {
            e.debug("added observation for {}", uxuVar);
        } else {
            e.debug("kept observation {} for {}", putIfAbsent, uxuVar);
        }
        return putIfAbsent;
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public uzp put(uxu uxuVar, uzp uzpVar) {
        if (uxuVar == null) {
            throw new NullPointerException("token must not be null");
        }
        if (uzpVar == null) {
            throw new NullPointerException("observation must not be null");
        }
        this.f17622a = true;
        uzp put = this.h.put(uxuVar, uzpVar);
        if (put == null) {
            e.debug("added observation for {}", uxuVar);
        } else {
            e.debug("replaced observation {} for {}", put, uxuVar);
        }
        return put;
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public uzp get(uxu uxuVar) {
        if (uxuVar == null) {
            return null;
        }
        uzp uzpVar = this.h.get(uxuVar);
        e.debug("looking up observation for token {}: {}", uxuVar, uzpVar);
        return uzs.b(uzpVar);
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public void remove(uxu uxuVar) {
        if (uxuVar != null) {
            if (this.h.remove(uxuVar) != null) {
                e.debug("removed observation for token {}", uxuVar);
            } else {
                e.debug("Already removed observation for token {}", uxuVar);
            }
        }
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public void setContext(uxu uxuVar, EndpointContext endpointContext) {
        uzp uzpVar;
        if (uxuVar == null || endpointContext == null || (uzpVar = this.h.get(uxuVar)) == null) {
            return;
        }
        this.h.replace(uxuVar, uzpVar, new uzp(uzpVar.c(), endpointContext));
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public void start() {
        ScheduledExecutorService scheduledExecutorService;
        synchronized (this) {
            long longValue = this.c.a(vbc.b, TimeUnit.MILLISECONDS).longValue();
            if (longValue > 0 && b.isDebugEnabled() && (scheduledExecutorService = this.d) != null) {
                this.g = scheduledExecutorService.scheduleAtFixedRate(new Runnable() { // from class: uzt.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (uzt.this.f17622a) {
                            uzt.b.debug("{} observes", Integer.valueOf(uzt.this.h.size()));
                            Iterator it = uzt.this.h.keySet().iterator();
                            int i = 5;
                            while (it.hasNext()) {
                                uzt.b.debug("   observe {}", it.next());
                                i--;
                                if (i == 0) {
                                    return;
                                }
                            }
                        }
                    }
                }, longValue, longValue, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // org.eclipse.californium.core.observe.ObservationStore
    public void stop() {
        synchronized (this) {
            ScheduledFuture<?> scheduledFuture = this.g;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                this.g = null;
            }
        }
    }
}

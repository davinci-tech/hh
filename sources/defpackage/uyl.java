package defpackage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.deduplication.Deduplicator;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uyl implements Deduplicator {
    private static final Logger e = vha.a((Class<?>) uyl.class);

    /* renamed from: a, reason: collision with root package name */
    final ConcurrentMap<uyf, c> f17601a = new ConcurrentHashMap();
    Runnable b;
    final long c;
    final boolean d;
    private final long g;
    private ScheduledExecutorService i;
    private volatile ScheduledFuture<?> j;

    protected void a(uyf uyfVar, boolean z) {
    }

    static class c {
        public final long c = ClockUtil.d();
        public final Exchange e;

        public c(Exchange exchange) {
            this.e = exchange;
        }

        public int hashCode() {
            return this.e.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                return this.e.equals(((c) obj).e);
            }
            return false;
        }
    }

    public uyl(Configuration configuration) {
        this.g = configuration.a(CoapConfig.r, TimeUnit.MILLISECONDS).longValue();
        this.c = configuration.a(CoapConfig.p, TimeUnit.MILLISECONDS).longValue();
        this.d = ((Boolean) configuration.a((BasicDefinition) CoapConfig.l)).booleanValue();
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void start() {
        synchronized (this) {
            if (this.b == null) {
                this.b = new d();
            }
            if (this.j == null) {
                ScheduledExecutorService scheduledExecutorService = this.i;
                Runnable runnable = this.b;
                long j = this.g;
                this.j = scheduledExecutorService.scheduleAtFixedRate(runnable, j, j, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void stop() {
        synchronized (this) {
            if (this.j != null) {
                this.j.cancel(false);
                this.j = null;
                clear();
            }
        }
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void setExecutor(ScheduledExecutorService scheduledExecutorService) {
        synchronized (this) {
            if (this.j != null) {
                throw new IllegalStateException("executor service can not be set on running Deduplicator");
            }
            this.i = scheduledExecutorService;
        }
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public Exchange findPrevious(uyf uyfVar, Exchange exchange) {
        c cVar = new c(exchange);
        c putIfAbsent = this.f17601a.putIfAbsent(uyfVar, cVar);
        boolean z = false;
        if (this.d && putIfAbsent != null && putIfAbsent.e.n() != exchange.n()) {
            if (this.f17601a.replace(uyfVar, putIfAbsent, cVar)) {
                e.debug("replace exchange for {}", uyfVar);
                z = true;
                putIfAbsent = null;
            } else {
                putIfAbsent = this.f17601a.putIfAbsent(uyfVar, cVar);
            }
        }
        if (putIfAbsent == null) {
            e.debug("add exchange for {}", uyfVar);
            a(uyfVar, z);
            return null;
        }
        e.debug("found exchange for {}", uyfVar);
        return putIfAbsent.e;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public boolean replacePrevious(uyf uyfVar, Exchange exchange, Exchange exchange2) {
        boolean z;
        c cVar = new c(exchange);
        c cVar2 = new c(exchange2);
        boolean replace = this.f17601a.replace(uyfVar, cVar, cVar2);
        boolean z2 = true;
        if (replace) {
            z = true;
        } else {
            z = false;
            if (this.f17601a.putIfAbsent(uyfVar, cVar2) != null) {
                z2 = false;
            }
        }
        if (z2) {
            a(uyfVar, z);
        }
        return z2;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public Exchange find(uyf uyfVar) {
        c cVar = this.f17601a.get(uyfVar);
        if (cVar == null) {
            return null;
        }
        return cVar.e;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void clear() {
        this.f17601a.clear();
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public boolean isEmpty() {
        return this.f17601a.isEmpty();
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public int size() {
        return this.f17601a.size();
    }

    class d implements Runnable {
        private d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                uyl.e.trace("Start Mark-And-Sweep with {} entries", Integer.valueOf(uyl.this.f17601a.size()));
                c();
            } catch (Throwable th) {
                uyl.e.warn("Exception in Mark-and-Sweep algorithm", th);
            }
        }

        private void c() {
            if (uyl.this.f17601a.isEmpty()) {
                return;
            }
            long d = ClockUtil.d();
            long nanos = TimeUnit.MILLISECONDS.toNanos(uyl.this.c);
            for (Map.Entry<uyf, c> entry : uyl.this.f17601a.entrySet()) {
                if (entry.getValue().c - (d - nanos) < 0) {
                    uyl.e.trace("Mark-And-Sweep removes {}", entry.getKey());
                    uyl.this.f17601a.remove(entry.getKey());
                }
            }
            uyl.e.debug("Sweep run took {}ms", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(ClockUtil.d() - d)));
        }
    }
}

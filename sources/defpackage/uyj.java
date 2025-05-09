package defpackage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.deduplication.Deduplicator;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uyj implements Deduplicator {
    private static final Logger e = vha.a((Class<?>) uyj.class);
    private volatile ScheduledFuture<?> b;
    private ScheduledExecutorService c;
    private final e[] d;
    private final long f;
    private final boolean g;
    private final d i;

    /* renamed from: a, reason: collision with root package name */
    private volatile int f17599a = 0;
    private volatile int h = 1;

    public uyj(Configuration configuration) {
        this.i = new d();
        this.d = new e[]{new e(), new e(), new e()};
        this.f = configuration.a(CoapConfig.k, TimeUnit.MILLISECONDS).longValue();
        this.g = ((Boolean) configuration.a((BasicDefinition) CoapConfig.l)).booleanValue();
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void start() {
        synchronized (this) {
            if (this.b == null) {
                ScheduledExecutorService scheduledExecutorService = this.c;
                d dVar = this.i;
                long j = this.f;
                this.b = scheduledExecutorService.scheduleAtFixedRate(dVar, j, j, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void stop() {
        synchronized (this) {
            if (this.b != null) {
                this.b.cancel(false);
                this.b = null;
                clear();
            }
        }
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void setExecutor(ScheduledExecutorService scheduledExecutorService) {
        synchronized (this) {
            if (this.b != null) {
                throw new IllegalStateException("executor service can not be set on running Deduplicator");
            }
            this.c = scheduledExecutorService;
        }
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public Exchange findPrevious(uyf uyfVar, Exchange exchange) {
        int i = this.f17599a;
        int i2 = this.h;
        Exchange putIfAbsent = this.d[i].putIfAbsent(uyfVar, exchange);
        if (putIfAbsent != null || i == i2) {
            return putIfAbsent;
        }
        Exchange putIfAbsent2 = this.d[i2].putIfAbsent(uyfVar, exchange);
        if (!this.g || putIfAbsent2 == null || putIfAbsent2.n() == exchange.n()) {
            return putIfAbsent2;
        }
        e.debug("replace exchange for {}", uyfVar);
        if (this.d[i2].replace(uyfVar, putIfAbsent2, exchange)) {
            return null;
        }
        return this.d[i2].putIfAbsent(uyfVar, exchange);
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public boolean replacePrevious(uyf uyfVar, Exchange exchange, Exchange exchange2) {
        int i = this.h;
        return this.d[i].replace(uyfVar, exchange, exchange2) || this.d[i].putIfAbsent(uyfVar, exchange2) == null;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public Exchange find(uyf uyfVar) {
        int i = this.f17599a;
        int i2 = this.h;
        Exchange exchange = this.d[i2].get(uyfVar);
        return (exchange != null || i == i2) ? exchange : this.d[i].get(uyfVar);
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public void clear() {
        synchronized (this.d) {
            this.d[0].clear();
            this.d[1].clear();
            this.d[2].clear();
        }
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public int size() {
        int size;
        int size2;
        int size3;
        synchronized (this.d) {
            size = this.d[0].size();
            size2 = this.d[1].size();
            size3 = this.d[2].size();
        }
        return size + size2 + size3;
    }

    @Override // org.eclipse.californium.core.network.deduplication.Deduplicator
    public boolean isEmpty() {
        for (e eVar : this.d) {
            if (!eVar.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    class d implements Runnable {
        private d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b();
            } catch (Throwable th) {
                uyj.e.warn("Exception in Crop-Rotation algorithm", th);
            }
        }

        private void b() {
            synchronized (uyj.this.d) {
                int i = uyj.this.f17599a;
                uyj uyjVar = uyj.this;
                uyjVar.f17599a = uyjVar.h;
                uyj uyjVar2 = uyj.this;
                uyjVar2.h = (uyjVar2.h + 1) % 3;
                uyj.this.d[i].clear();
            }
        }
    }

    static class e extends ConcurrentHashMap<uyf, Exchange> {
        private static final long serialVersionUID = 1504940670839294042L;

        private e() {
        }
    }
}

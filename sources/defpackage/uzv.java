package defpackage;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.observe.ObserveHealth;
import org.eclipse.californium.core.observe.ObserveRelation;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uzv {

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<uyc, ObserveRelation> f17625a;
    private final Configuration b;
    private volatile ObserveHealth c;
    private final ConcurrentHashMap<InetSocketAddress, uzu> d;
    private final int e;

    @Deprecated
    public uzv() {
        this(null);
    }

    public uzv(Configuration configuration) {
        this.d = new ConcurrentHashMap<>();
        this.f17625a = new ConcurrentHashMap<>();
        this.b = configuration;
        this.e = configuration != null ? ((Integer) configuration.a((BasicDefinition) CoapConfig.z)).intValue() : 0;
    }

    public void b(ObserveRelation observeRelation) {
        ObserveHealth observeHealth = this.c;
        if (observeHealth != null) {
            observeHealth.receivingReject();
        }
        observeRelation.b();
    }

    public void a(ObserveRelation observeRelation) {
        boolean z;
        boolean remove = this.f17625a.remove(observeRelation.i(), observeRelation);
        uzu d = observeRelation.d();
        if (d != null) {
            d.e(observeRelation);
            synchronized (this) {
                if (d.a()) {
                    if (!this.d.remove(observeRelation.f(), d) && !remove) {
                        z = false;
                        remove = z;
                    }
                    z = true;
                    remove = z;
                }
            }
        }
        ObserveHealth observeHealth = this.c;
        if (!remove || observeHealth == null) {
            return;
        }
        observeHealth.setObserveRelations(this.f17625a.size());
        observeHealth.setObserveEndpoints(this.d.size());
    }
}

package org.eclipse.californium.core.observe;

import defpackage.uxr;
import defpackage.uyc;
import defpackage.uzu;
import defpackage.uzv;
import defpackage.vha;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.server.resources.ObservableResource;
import org.eclipse.californium.elements.util.ClockUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class ObserveRelation {
    private static final Logger b = vha.a((Class<?>) ObserveRelation.class);

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f15867a;
    private volatile boolean c;
    private final long d;
    private final int e;
    private int f;
    private long g;
    private final uyc h;
    private final Exchange i;
    private final uzv j;
    private volatile uzu k;
    private uxr l;
    private final ObservableResource m;
    private final CoAP.Type n;
    private uxr o;
    private final InetSocketAddress t;

    public enum State {
        NONE,
        INIT,
        ESTABILSHED,
        CANCELED
    }

    public boolean o() {
        return this.f15867a;
    }

    public void k() {
        boolean z;
        synchronized (this) {
            z = this.c;
            if (!z) {
                this.f15867a = true;
            }
        }
        if (z) {
            throw new IllegalStateException(String.format("Could not establish observe relation %s with %s, already canceled (%s)!", j(), this.m.getURI(), this.i));
        }
    }

    public boolean h() {
        return this.c;
    }

    public void l() {
        uzv uzvVar = this.j;
        if (uzvVar != null) {
            uzvVar.b(this);
        } else {
            b();
        }
    }

    public void b() {
        b(true);
    }

    public void c() {
        this.k.c();
    }

    public Exchange e() {
        return this.i;
    }

    public InetSocketAddress f() {
        return this.t;
    }

    public uzu d() {
        return this.k;
    }

    public CoAP.Type g() {
        CoAP.Type observeType = this.m.getObserveType();
        if (observeType == null) {
            observeType = this.n;
        }
        if (observeType != CoAP.Type.CON && !a()) {
            return CoAP.Type.NON;
        }
        return CoAP.Type.CON;
    }

    public State a(uxr uxrVar) {
        if (h()) {
            return State.CANCELED;
        }
        if (o()) {
            this.i.ad();
            if (uxrVar.i()) {
                uxrVar.getOptions().e(this.m.getNotificationSequenceNumber());
            }
            return State.ESTABILSHED;
        }
        if (uxrVar.i()) {
            k();
            this.m.addObserveRelation(this);
            if (!h()) {
                uxrVar.getOptions().e(this.m.getNotificationSequenceNumber());
                return State.INIT;
            }
        }
        return State.CANCELED;
    }

    public boolean a() {
        boolean z;
        int i;
        boolean z2;
        long d = ClockUtil.d();
        synchronized (this) {
            z = true;
            int i2 = this.f + 1;
            this.f = i2;
            i = this.e;
            z2 = i2 >= i;
            if (z2) {
                this.g = d;
                this.f = 0;
            }
        }
        if (z2) {
            b.trace("Observe-relation check, {} notifications reached.", Integer.valueOf(i));
            return z2;
        }
        synchronized (this) {
            if ((d - this.g) - this.d <= 0) {
                z = false;
            }
            if (z) {
                this.g = d;
                this.f = 0;
            }
        }
        if (z) {
            b.trace("Observe-relation check, {}s interval reached.", Long.valueOf(TimeUnit.NANOSECONDS.toSeconds(this.d)));
        }
        return z;
    }

    public boolean b(uxr uxrVar) {
        if (d(this.l)) {
            b.trace("in transit {}", this.l);
            uxr uxrVar2 = this.o;
            if (uxrVar2 != null) {
                if (!uxrVar2.g()) {
                    return true;
                }
                this.o.onTransferComplete();
            }
            this.o = uxrVar;
            return true;
        }
        this.l = uxrVar;
        this.o = null;
        c(uxrVar);
        return false;
    }

    public uxr e(uxr uxrVar, boolean z) {
        if (this.l != uxrVar) {
            return null;
        }
        uxr uxrVar2 = this.o;
        if (uxrVar2 != null) {
            this.l = uxrVar2;
            this.o = null;
            c(uxrVar2);
        } else if (z) {
            this.l = null;
            this.o = null;
        }
        return uxrVar2;
    }

    public void c(uxr uxrVar) {
        if (uxrVar.g()) {
            return;
        }
        b(false);
    }

    public uyc i() {
        return this.h;
    }

    @Deprecated
    public String j() {
        return this.h.toString();
    }

    private void b(boolean z) {
        boolean z2;
        boolean z3;
        synchronized (this) {
            z2 = false;
            if (this.c) {
                z3 = false;
            } else {
                this.c = true;
                z3 = this.f15867a;
                this.f15867a = false;
                z2 = true;
            }
        }
        if (z2) {
            b.debug("Canceling observe relation {} with {} ({})", j(), this.m.getURI(), this.i);
            if (z3) {
                this.m.removeObserveRelation(this);
            }
            uzv uzvVar = this.j;
            if (uzvVar != null) {
                uzvVar.a(this);
            } else {
                this.k.e(this);
            }
            if (z) {
                this.i.c();
            }
        }
    }

    public static State c(ObserveRelation observeRelation, uxr uxrVar) {
        State state = State.NONE;
        if (observeRelation != null) {
            state = observeRelation.a(uxrVar);
        }
        boolean z = state == State.NONE || state == State.CANCELED;
        if (uxrVar.g() && (!uxrVar.i() || z)) {
            b.warn("Application notification, not longer observing, remove observe-option {}", uxrVar);
            uxrVar.getOptions().at();
        }
        return state;
    }

    private static boolean d(uxr uxrVar) {
        return (uxrVar == null || !uxrVar.isConfirmable() || uxrVar.isAcknowledged() || uxrVar.isTimedOut() || uxrVar.isRejected()) ? false : true;
    }
}

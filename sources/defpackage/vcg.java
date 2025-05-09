package defpackage;

import defpackage.vca;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.californium.elements.util.CounterStatisticManager;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.DtlsHealth;
import org.eclipse.californium.scandium.DtlsHealthExtended;
import org.eclipse.californium.scandium.DtlsHealthExtended2;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vcg extends CounterStatisticManager implements DtlsHealth, DtlsHealthExtended, DtlsHealthExtended2 {
    private static final Logger c = vha.d(DTLSConnector.class.getCanonicalName() + ".health");

    /* renamed from: a, reason: collision with root package name */
    protected final vca.a f17662a;
    private final vca b;
    private final vca d;
    private final vca e;
    private final vca f;
    private final vca g;
    private final vca h;
    private final vca i;
    private final AtomicInteger j;
    private final vca k;
    private final vca l;
    private final vca m;
    private final vca n;
    private final vca o;

    protected void c(String str, StringBuilder sb) {
    }

    public vcg() {
        this("");
    }

    public vcg(String str) {
        super(str);
        this.j = new AtomicInteger();
        vca.a aVar = new vca.a();
        this.f17662a = aVar;
        this.d = new vca("connections", aVar);
        this.l = new vca("handshakes succeeded", aVar);
        this.g = new vca("handshakes failed", aVar);
        this.k = new vca("received records", aVar);
        this.i = new vca("dropped received records", aVar);
        this.b = new vca("dropped received mac-errors", aVar);
        this.m = new vca("sending records", aVar);
        this.h = new vca("dropped sending records", aVar);
        this.e = new vca("dropped udp messages", aVar);
        this.n = new vca("pending in jobs", aVar);
        this.o = new vca("pending out jobs", aVar);
        this.f = new vca("pending handshake jobs", aVar);
        d();
    }

    private void d() {
        add(this.d);
        add(this.l);
        add(this.g);
        add(this.k);
        add(this.i);
        add(this.b);
        add(this.m);
        add(this.h);
        add(this.e);
        add(this.n);
        add(this.o);
        add(this.f);
    }

    @Override // org.eclipse.californium.elements.util.CounterStatisticManager
    public void dump() {
        try {
            if (isEnabled()) {
                if (b()) {
                    Logger logger = c;
                    if (logger.isDebugEnabled()) {
                        String a2 = vcb.a();
                        String str = "   " + this.tag;
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.tag);
                        sb.append("dtls statistic:");
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.d);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.l);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.g);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.m);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.h);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.k);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.i);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.b);
                        if (this.e.c()) {
                            sb.append(a2);
                            sb.append(str);
                            sb.append(this.e);
                        }
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.n);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.o);
                        sb.append(a2);
                        sb.append(str);
                        sb.append(this.f);
                        c(str, sb);
                        logger.debug("{}", sb);
                    }
                }
                transferCounter();
            }
        } catch (Throwable th) {
            c.error("{}", this.tag, th);
        }
    }

    @Override // org.eclipse.californium.scandium.DtlsHealth
    public void dump(String str, int i, int i2, int i3) {
        try {
            if (isEnabled()) {
                this.d.h();
                long j = i - i2;
                this.d.a(j);
                if (b()) {
                    Logger logger = c;
                    if (logger.isDebugEnabled()) {
                        String a2 = vcb.a();
                        String str2 = "   " + str;
                        this.f17662a.e("associations");
                        this.f17662a.e("handshakes pending");
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append("dtls statistic:");
                        sb.append(a2);
                        String c2 = vca.c(this.f17662a.b(), "associations", j);
                        sb.append(str2);
                        sb.append(c2);
                        sb.append(" (");
                        sb.append(i2);
                        sb.append(" remaining capacity).");
                        sb.append(a2);
                        String c3 = vca.c(this.f17662a.b(), "handshakes pending", this.j.get());
                        sb.append(str2);
                        sb.append(c3);
                        sb.append(" (");
                        sb.append(i3);
                        sb.append(" without verify).");
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.l);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.g);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.m);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.h);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.k);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.i);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.b);
                        if (this.e.c()) {
                            sb.append(a2);
                            sb.append(str2);
                            sb.append(this.e);
                        }
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.n);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.o);
                        sb.append(a2);
                        sb.append(str2);
                        sb.append(this.f);
                        c(str2, sb);
                        logger.debug("{}", sb);
                    }
                }
                transferCounter();
            }
        } catch (Throwable th) {
            c.error("{}", str, th);
        }
    }

    protected boolean b() {
        return this.k.d() || this.m.d();
    }

    @Override // org.eclipse.californium.elements.util.CounterStatisticManager
    public boolean isEnabled() {
        return c.isInfoEnabled();
    }

    @Override // org.eclipse.californium.scandium.DtlsHealth
    public void startHandshake() {
        this.j.incrementAndGet();
    }

    @Override // org.eclipse.californium.scandium.DtlsHealth
    public void endHandshake(boolean z) {
        this.j.decrementAndGet();
        if (z) {
            this.l.a();
        } else {
            this.g.a();
        }
    }

    @Override // org.eclipse.californium.scandium.DtlsHealth
    public void receivingRecord(boolean z) {
        if (z) {
            this.i.a();
        } else {
            this.k.a();
        }
    }

    @Override // org.eclipse.californium.scandium.DtlsHealth
    public void sendingRecord(boolean z) {
        if (z) {
            this.h.a();
        } else {
            this.m.a();
        }
    }

    @Override // org.eclipse.californium.scandium.DtlsHealthExtended2
    public void receivingMacError() {
        this.b.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsHealthExtended
    public void setConnections(int i) {
        this.d.a(i);
    }

    @Override // org.eclipse.californium.scandium.DtlsHealthExtended2
    public void setPendingIncomingJobs(int i) {
        this.n.a(i);
    }

    @Override // org.eclipse.californium.scandium.DtlsHealthExtended2
    public void setPendingOutgoingJobs(int i) {
        this.o.a(i);
    }

    @Override // org.eclipse.californium.scandium.DtlsHealthExtended2
    public void setPendingHandshakeJobs(int i) {
        this.f.a(i);
    }
}

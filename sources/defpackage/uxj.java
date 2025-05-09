package defpackage;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.elements.EndpointContext;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uxj {
    private static final Logger e = vha.a((Class<?>) uxj.class);

    /* renamed from: a, reason: collision with root package name */
    private Endpoint f17574a;
    private int b;
    private String c;
    private final AtomicReference<EndpointContext> d;
    private boolean g;
    private CoAP.Type h;
    private String i;
    private Long j;

    public uxj() {
        this("");
    }

    public uxj(String str) {
        this.d = new AtomicReference<>();
        this.h = CoAP.Type.CON;
        this.b = 0;
        this.i = str;
    }

    public Long d() {
        return this.j;
    }

    public uxj a(Long l) {
        this.j = l;
        return this;
    }

    public String a() {
        return this.i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
    
        if (java.util.Objects.equals(r0.getHost(), r1.getHost()) == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.uxj b(java.lang.String r5) {
        /*
            r4 = this;
            boolean r0 = r4.g
            if (r0 != 0) goto L40
            java.lang.String r0 = r4.c
            if (r0 != 0) goto L40
            java.lang.String r0 = r4.i
            boolean r0 = java.util.Objects.equals(r0, r5)
            if (r0 != 0) goto L40
            java.lang.String r0 = r4.i
            if (r0 == 0) goto L3a
            if (r5 == 0) goto L3a
            java.net.URI r0 = new java.net.URI     // Catch: java.net.URISyntaxException -> L3a
            java.lang.String r1 = r4.i     // Catch: java.net.URISyntaxException -> L3a
            r0.<init>(r1)     // Catch: java.net.URISyntaxException -> L3a
            java.net.URI r1 = new java.net.URI     // Catch: java.net.URISyntaxException -> L3a
            r1.<init>(r5)     // Catch: java.net.URISyntaxException -> L3a
            int r2 = r0.getPort()     // Catch: java.net.URISyntaxException -> L3a
            int r3 = r1.getPort()     // Catch: java.net.URISyntaxException -> L3a
            if (r2 != r3) goto L3a
            java.lang.String r0 = r0.getHost()     // Catch: java.net.URISyntaxException -> L3a
            java.lang.String r1 = r1.getHost()     // Catch: java.net.URISyntaxException -> L3a
            boolean r0 = java.util.Objects.equals(r0, r1)     // Catch: java.net.URISyntaxException -> L3a
            if (r0 != 0) goto L40
        L3a:
            java.util.concurrent.atomic.AtomicReference<org.eclipse.californium.elements.EndpointContext> r0 = r4.d
            r1 = 0
            r0.set(r1)
        L40:
            r4.i = r5
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uxj.b(java.lang.String):uxj");
    }

    public Endpoint b() {
        Endpoint endpoint;
        synchronized (this) {
            endpoint = this.f17574a;
        }
        return endpoint;
    }

    public uxj d(Endpoint endpoint) {
        synchronized (this) {
            this.f17574a = endpoint;
        }
        if (!endpoint.isStarted()) {
            try {
                endpoint.start();
                e.info("started set client endpoint {}", endpoint.getAddress());
            } catch (IOException e2) {
                e.error("could not set and start client endpoint", (Throwable) e2);
            }
        }
        return this;
    }

    public uxi e() throws vbe, IOException {
        uxt c = c();
        c(c);
        return e(c);
    }

    public uxi a(String str, int i) throws vbe, IOException {
        uxt j = j();
        j.setPayload(str);
        j.getOptions().b(i);
        c(j);
        return e(j);
    }

    private uxi e(uxt uxtVar) throws vbe, IOException {
        return d(uxtVar, b(uxtVar));
    }

    private uxi d(uxt uxtVar, Endpoint endpoint) throws vbe, IOException {
        try {
            Long d = d();
            if (d == null) {
                d = endpoint.getConfig().a(CoapConfig.p, TimeUnit.MILLISECONDS);
            }
            uxr d2 = a(uxtVar, endpoint).d(d.longValue());
            if (d2 == null) {
                uxtVar.cancel();
                Throwable sendError = uxtVar.getSendError();
                if (sendError == null) {
                    sendError = uxtVar.d();
                }
                if (sendError == null) {
                    return null;
                }
                if (sendError instanceof vbe) {
                    throw ((vbe) sendError);
                }
                throw new IOException(sendError);
            }
            if (!uxtVar.h()) {
                c(d2);
            }
            return new uxi(d2);
        } catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
    }

    protected uxt a(uxt uxtVar, Endpoint endpoint) {
        if (this.b != 0) {
            uxtVar.getOptions().a(new uxh(uxh.e(this.b), false, 0));
        }
        endpoint.sendRequest(uxtVar);
        return uxtVar;
    }

    protected Endpoint b(uxt uxtVar) {
        Endpoint b = b();
        return b != null ? b : uxy.c().e(uxtVar.c());
    }

    private uxt c() {
        return d(uxt.a());
    }

    private uxt j() {
        return d(uxt.b());
    }

    private uxt d(uxt uxtVar) {
        uxtVar.setType(this.h);
        return uxtVar;
    }

    private uxt c(uxt uxtVar) {
        EndpointContext endpointContext = this.d.get();
        if (endpointContext != null && uxtVar.getDestinationContext() == null) {
            uxtVar.setDestinationContext(endpointContext);
            if (this.g && this.c == null) {
                String d = CoAP.d(this.i);
                if (d != null && !CoAP.c(d)) {
                    uxtVar.c(this.i);
                } else {
                    uxtVar.a(this.i);
                }
            } else {
                uxtVar.a(this.i);
            }
        } else if (!uxtVar.j() && !uxtVar.f()) {
            uxtVar.a(this.i);
        }
        if (this.c != null && !uxtVar.f()) {
            uxtVar.b(this.c);
        }
        return uxtVar;
    }

    private void c(uxr uxrVar) {
        ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.d, null, uxrVar.getSourceContext());
    }
}

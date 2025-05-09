package defpackage;

import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.AbstractLayer;
import org.eclipse.californium.core.network.stack.BlockwiseStatus;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.LeastRecentlyUsedCache;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uyv extends AbstractLayer {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17606a;
    private static final Logger e;
    private final LeastRecentlyUsedCache<uze, uys> b;
    private final LeastRecentlyUsedCache<uze, uyt> c;
    private final int d;
    private final int f;
    private final long g;
    private volatile boolean h;
    private final boolean i;
    private ScheduledFuture<?> j;
    private final int l;
    private final int m;
    private final EndpointContextMatcher n;
    private final int o;
    private final boolean q;
    private final boolean r;
    private final int s;
    private ScheduledFuture<?> t;
    private final String w;
    private final BlockwiseStatus.RemoveHandler p = new BlockwiseStatus.RemoveHandler() { // from class: uyv.2
        @Override // org.eclipse.californium.core.network.stack.BlockwiseStatus.RemoveHandler
        public void remove(BlockwiseStatus blockwiseStatus) {
            if (blockwiseStatus instanceof uyt) {
                uyv.this.d((uyt) blockwiseStatus);
            } else if (blockwiseStatus instanceof uys) {
                uyv.this.b((uys) blockwiseStatus);
            }
        }
    };
    private final AtomicInteger k = new AtomicInteger();

    static {
        Logger a2 = vha.a((Class<?>) uyv.class);
        e = a2;
        f17606a = vha.d(a2.getName() + ".health");
    }

    public uyv(String str, boolean z, Configuration configuration, EndpointContextMatcher endpointContextMatcher) {
        this.w = str;
        this.n = endpointContextMatcher;
        int intValue = ((Integer) configuration.a((BasicDefinition) CoapConfig.ak)).intValue();
        int e2 = uxh.e(intValue);
        String valueOf = String.valueOf(intValue);
        int intValue2 = z ? ((Integer) configuration.a((BasicDefinition) CoapConfig.ao)).intValue() : 1;
        this.m = intValue2;
        if (intValue2 > 1) {
            e2 = 7;
            valueOf = "1024(BERT)";
        }
        String str2 = valueOf;
        int intValue3 = ((Integer) configuration.a((BasicDefinition) CoapConfig.x)).intValue();
        this.o = intValue3;
        this.s = e2;
        int d = configuration.d(CoapConfig.j, TimeUnit.MILLISECONDS);
        this.f = d;
        this.d = configuration.d(CoapConfig.b, TimeUnit.MILLISECONDS);
        int intValue4 = ((Integer) configuration.a((BasicDefinition) CoapConfig.ad)).intValue();
        this.l = intValue4;
        int intValue5 = ((Integer) configuration.a((BasicDefinition) CoapConfig.w)).intValue();
        int i = intValue5 / 10;
        long j = d;
        LeastRecentlyUsedCache<uze, uyt> leastRecentlyUsedCache = new LeastRecentlyUsedCache<>(i, intValue5, j, TimeUnit.MILLISECONDS);
        this.c = leastRecentlyUsedCache;
        leastRecentlyUsedCache.c(false);
        leastRecentlyUsedCache.a(new LeastRecentlyUsedCache.EvictionListener<uyt>() { // from class: uyv.5
            @Override // org.eclipse.californium.elements.util.LeastRecentlyUsedCache.EvictionListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onEviction(uyt uytVar) {
                if (uytVar.complete()) {
                    uyv.e.debug("{}block1 transfer timed out!", uyv.this.w);
                    uytVar.timeoutCurrentTranfer();
                }
            }
        });
        LeastRecentlyUsedCache<uze, uys> leastRecentlyUsedCache2 = new LeastRecentlyUsedCache<>(i, intValue5, j, TimeUnit.MILLISECONDS);
        this.b = leastRecentlyUsedCache2;
        leastRecentlyUsedCache2.c(false);
        leastRecentlyUsedCache2.a(new LeastRecentlyUsedCache.EvictionListener<uys>() { // from class: uyv.3
            @Override // org.eclipse.californium.elements.util.LeastRecentlyUsedCache.EvictionListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onEviction(uys uysVar) {
                if (uysVar.complete()) {
                    uyv.e.debug("{}block2 transfer timed out!", uyv.this.w);
                    uysVar.timeoutCurrentTranfer();
                }
            }
        });
        this.q = ((Boolean) configuration.a((BasicDefinition) CoapConfig.i)).booleanValue();
        boolean booleanValue = ((Boolean) configuration.a((BasicDefinition) CoapConfig.h)).booleanValue();
        this.r = booleanValue;
        this.g = configuration.a(vbc.b, TimeUnit.MILLISECONDS).longValue();
        this.i = ((Boolean) configuration.a((BasicDefinition) CoapConfig.e)).booleanValue();
        e.info("{}BlockwiseLayer uses MAX_MESSAGE_SIZE={}, PREFERRED_BLOCK_SIZE={}, BLOCKWISE_STATUS_LIFETIME={}, MAX_RESOURCE_BODY_SIZE={}, BLOCKWISE_STRICT_BLOCK2_OPTION={}", str, Integer.valueOf(intValue3), str2, Integer.valueOf(d), Integer.valueOf(intValue4), Boolean.valueOf(booleanValue));
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void start() {
        if (this.g > 0 && f17606a.isDebugEnabled() && this.t == null) {
            ScheduledExecutorService scheduledExecutorService = this.secondaryExecutor;
            Runnable runnable = new Runnable() { // from class: uyv.4
                @Override // java.lang.Runnable
                public void run() {
                    if (uyv.this.h) {
                        uyv.f17606a.debug("{}{} block1 transfers", uyv.this.w, Integer.valueOf(uyv.this.c.e()));
                        Iterator a2 = uyv.this.c.a(false);
                        int i = 5;
                        int i2 = 5;
                        while (a2.hasNext()) {
                            uyv.f17606a.debug("   block1 {}", a2.next());
                            i2--;
                            if (i2 == 0) {
                                break;
                            }
                        }
                        uyv.f17606a.debug("{}{} block2 transfers", uyv.this.w, Integer.valueOf(uyv.this.b.e()));
                        Iterator a3 = uyv.this.b.a(false);
                        while (a3.hasNext()) {
                            uyv.f17606a.debug("   block2 {}", a3.next());
                            i--;
                            if (i == 0) {
                                break;
                            }
                        }
                        uyv.f17606a.debug("{}{} block2 responses ignored", uyv.this.w, Integer.valueOf(uyv.this.k.get()));
                        uyv.this.b(true);
                    }
                }
            };
            long j = this.g;
            this.t = scheduledExecutorService.scheduleAtFixedRate(runnable, j, j, TimeUnit.MILLISECONDS);
        }
        ScheduledExecutorService scheduledExecutorService2 = this.secondaryExecutor;
        Runnable runnable2 = new Runnable() { // from class: uyv.1
            @Override // java.lang.Runnable
            public void run() {
                uyv.this.b(false);
            }
        };
        long j2 = this.d;
        this.j = scheduledExecutorService2.scheduleAtFixedRate(runnable2, j2, j2, TimeUnit.MILLISECONDS);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void destroy() {
        ScheduledFuture<?> scheduledFuture = this.t;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.t = null;
        }
        ScheduledFuture<?> scheduledFuture2 = this.j;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(false);
            this.j = null;
        }
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        if (e() && !uxtVar.h() && !e(exchange)) {
            uze d = uze.d(exchange);
            uys d2 = d(d);
            if (d2 != null) {
                b(d2);
                d2.b((Exchange) null);
            }
            if (a(uxtVar)) {
                try {
                    uxtVar = e(d, exchange, uxtVar, this.s);
                } catch (uyx e2) {
                    e.debug("{}{} {}", this.w, d, e2.getMessage());
                    if (!e2.c()) {
                        uxtVar.setSendError(e2);
                    }
                }
            }
        }
        exchange.c(uxtVar);
        lower().sendRequest(exchange, uxtVar);
    }

    private uxt e(uze uzeVar, Exchange exchange, uxt uxtVar, int i) throws uyx {
        uxt c = e(uzeVar, exchange, uxtVar, true).c(i);
        uxu token = uxtVar.getToken();
        if (token != null) {
            c.setToken(token);
        }
        return c;
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveRequest(Exchange exchange, uxt uxtVar) {
        if (e()) {
            if (uxtVar.getOptions().ai()) {
                c(exchange, uxtVar);
                return;
            }
            uxh j = uxtVar.getOptions().j();
            if (j != null && j.b() > 0) {
                uze d = uze.d(exchange);
                uys d2 = d(d);
                if (d2 == null) {
                    e.debug("{}peer wants to retrieve individual block2 {} of {}, delivering request to application layer", this.w, j, d);
                } else {
                    if (this.n != null) {
                        EndpointContext sourceContext = uxtVar.getSourceContext();
                        if (!this.n.isResponseRelatedToRequest(d2.firstMessage.getDestinationContext(), sourceContext)) {
                            b(d2);
                            e.debug("{}peer wants to retrieve block2 {} of {} with new security context, delivering request to application layer", this.w, j, d);
                        }
                    }
                    d(exchange, uxtVar, d2);
                    return;
                }
            }
        }
        upper().receiveRequest(exchange, uxtVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0114 -> B:15:0x0135). Please report as a decompilation issue!!! */
    private void c(Exchange exchange, uxt uxtVar) {
        if (d(uxtVar)) {
            int a2 = a((Message) uxtVar);
            uxr uxrVar = new uxr(CoAP.ResponseCode.REQUEST_ENTITY_TOO_LARGE, true);
            uxrVar.setDestinationContext(uxtVar.getSourceContext());
            uxrVar.setPayload(String.format("body too large, max. %d bytes", Integer.valueOf(a2)));
            uxrVar.getOptions().h(a2);
            lower().sendResponse(exchange, uxrVar);
            return;
        }
        uxh f = uxtVar.getOptions().f();
        Logger logger = e;
        logger.debug("{}inbound request contains block1 option {}", this.w, f);
        uze d = uze.d(exchange);
        uyt b = b(d, exchange, uxtVar, false);
        if (f.a() == 0 && !b.isStarting()) {
            b = b(d, exchange, uxtVar, true);
        } else if (!b.hasContentFormat(uxtVar.getOptions().h())) {
            a(b, exchange, uxtVar, CoAP.ResponseCode.REQUEST_ENTITY_INCOMPLETE, "unexpected Content-Format");
            return;
        }
        try {
            b.b(uxtVar);
            if (f.f()) {
                logger.debug("{}acknowledging incoming block1 [num={}], expecting more blocks to come", this.w, Integer.valueOf(f.b()));
                uxr uxrVar2 = new uxr(CoAP.ResponseCode.CONTINUE);
                uxrVar2.setDestinationContext(uxtVar.getSourceContext());
                uxh d2 = d(f);
                uxrVar2.getOptions().b(d2.d(), true, d2.b());
                lower().sendResponse(exchange, uxrVar2);
                d = d;
            } else {
                logger.debug("{}peer has sent last block1 [num={}], delivering request to application layer", this.w, Integer.valueOf(f.b()));
                exchange.a(f);
                uxt uxtVar2 = new uxt(uxtVar.e());
                b.assembleReceivedMessage(uxtVar2);
                uxtVar2.setMID(uxtVar.getMID());
                uxtVar2.setToken(uxtVar.getToken());
                uxtVar2.e(uxtVar.c());
                uxtVar2.getOptions().a(uxtVar.getOptions().j());
                d(b);
                exchange.b(uxtVar2);
                upper().receiveRequest(exchange, uxtVar2);
                d = d;
            }
        } catch (uyx e2) {
            CoAP.ResponseCode a3 = e2.a();
            e.debug("{}peer {} {}. Responding with {}", this.w, d, e2.getMessage(), a3);
            uxt uxtVar3 = uxtVar;
            a(b, exchange, uxtVar3, a3, e2.getMessage());
            d = uxtVar3;
        }
    }

    private void a(uyt uytVar, Exchange exchange, uxt uxtVar, CoAP.ResponseCode responseCode, String str) {
        uxr uxrVar = new uxr(responseCode, true);
        if (this.q) {
            uxrVar.getOptions().d(uxtVar.getOptions().f());
        }
        uxrVar.setDestinationContext(uxtVar.getSourceContext());
        uxrVar.setPayload(str);
        d(uytVar);
        lower().sendResponse(exchange, uxrVar);
    }

    private void d(Exchange exchange, uxt uxtVar, uys uysVar) {
        uxr e2 = uysVar.e(d(uxtVar.getOptions().j()));
        if (e2.getOptions().j().f()) {
            e.debug("{}peer has requested intermediary block of blockwise transfer: {}", this.w, uysVar);
        } else {
            e.debug("{}peer has requested last block of blockwise transfer: {}", this.w, uysVar);
            b(uysVar);
        }
        lower().sendResponse(exchange, e2);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0103  */
    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendResponse(org.eclipse.californium.core.network.Exchange r7, defpackage.uxr r8) {
        /*
            r6 = this;
            boolean r0 = r6.e()
            if (r0 == 0) goto L10e
            uxt r0 = r7.p()
            uxv r0 = r0.getOptions()
            uxh r0 = r0.j()
            boolean r1 = r6.e(r7)
            r2 = 1
            if (r1 == 0) goto Lba
            uxv r1 = r8.getOptions()
            uxh r1 = r1.j()
            if (r1 == 0) goto L7b
            int r3 = r0.a()
            int r4 = r1.a()
            if (r3 == r4) goto Lfd
            org.slf4j.Logger r3 = defpackage.uyv.e
            java.lang.String r4 = r6.w
            uxt r5 = r7.p()
            java.lang.String r5 = r5.i()
            int r0 = r0.a()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r1 = r1.a()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r0 = new java.lang.Object[]{r4, r5, r0, r1}
            java.lang.String r1 = "{}resource [{}] implementation error, peer requested block offset {} but resource returned block offest {}"
            r3.warn(r1, r0)
            uxr r0 = new uxr
            org.eclipse.californium.core.coap.CoAP$ResponseCode r1 = org.eclipse.californium.core.coap.CoAP.ResponseCode.INTERNAL_SERVER_ERROR
            r0.<init>(r1, r2)
            uxt r1 = r7.p()
            org.eclipse.californium.elements.EndpointContext r1 = r1.getSourceContext()
            r0.setDestinationContext(r1)
            org.eclipse.californium.core.coap.CoAP$Type r1 = r8.getType()
            r0.setType(r1)
            int r1 = r8.getMID()
            r0.setMID(r1)
            java.util.List r8 = r8.getMessageObservers()
            r0.addMessageObservers(r8)
            goto Lec
        L7b:
            boolean r1 = r8.hasBlock(r0)
            if (r1 == 0) goto L8c
            uxh r0 = r6.d(r0)
            int r1 = r6.m
            defpackage.uys.b(r8, r0, r1)
            goto Lfd
        L8c:
            boolean r0 = r8.f()
            if (r0 != 0) goto Lfd
            uxr r0 = new uxr
            org.eclipse.californium.core.coap.CoAP$ResponseCode r1 = org.eclipse.californium.core.coap.CoAP.ResponseCode.BAD_OPTION
            r0.<init>(r1, r2)
            uxt r1 = r7.p()
            org.eclipse.californium.elements.EndpointContext r1 = r1.getSourceContext()
            r0.setDestinationContext(r1)
            org.eclipse.californium.core.coap.CoAP$Type r1 = r8.getType()
            r0.setType(r1)
            int r1 = r8.getMID()
            r0.setMID(r1)
            java.util.List r8 = r8.getMessageObservers()
            r0.addMessageObservers(r8)
            goto Lec
        Lba:
            boolean r1 = r6.d(r8, r0)
            if (r1 == 0) goto Lee
            uze r1 = defpackage.uze.d(r7)
            uys r8 = r6.e(r1, r7, r8, r2)
            if (r0 == 0) goto Lcf
            uxh r0 = r6.d(r0)
            goto Ld7
        Lcf:
            uxh r0 = new uxh
            int r1 = r6.s
            r2 = 0
            r0.<init>(r1, r2, r2)
        Ld7:
            uxr r0 = r8.e(r0)
            uxv r1 = r0.getOptions()
            uxh r1 = r1.j()
            boolean r1 = r1.f()
            if (r1 != 0) goto Lec
            r6.b(r8)
        Lec:
            r8 = r0
            goto Lfd
        Lee:
            boolean r1 = r6.a(r0)
            if (r1 == 0) goto Lfd
            uxh r0 = r6.d(r0)
            int r1 = r6.m
            defpackage.uys.b(r8, r0, r1)
        Lfd:
            uxh r0 = r7.d()
            if (r0 == 0) goto L10e
            r1 = 0
            r7.a(r1)
            uxv r1 = r8.getOptions()
            r1.d(r0)
        L10e:
            org.eclipse.californium.core.network.stack.Layer r0 = r6.lower()
            r0.sendResponse(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uyv.sendResponse(org.eclipse.californium.core.network.Exchange, uxr):void");
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        if (e() && !exchange.p().h()) {
            if (uxrVar.f()) {
                e.debug("{} received error {}:", this.w, uxrVar);
                int i = AnonymousClass6.b[uxrVar.a().ordinal()];
                if (i == 1 || i == 2) {
                    if (a(exchange, uxrVar)) {
                        return;
                    }
                    uyt e2 = e(uze.d(exchange));
                    if (e2 != null) {
                        d(e2);
                    }
                }
                if (exchange.p() != exchange.i()) {
                    uxr uxrVar2 = new uxr(uxrVar.a());
                    uxrVar2.setToken(exchange.p().getToken());
                    if (exchange.p().getType() == CoAP.Type.CON) {
                        uxrVar2.setType(CoAP.Type.ACK);
                        uxrVar2.setMID(exchange.p().getMID());
                    } else {
                        uxrVar2.setType(CoAP.Type.NON);
                    }
                    uxrVar2.setSourceContext(uxrVar.getSourceContext());
                    uxrVar2.setPayload(uxrVar.getPayload());
                    uxrVar2.setOptions(uxrVar.getOptions());
                    uxrVar2.e(exchange.a());
                    Long e3 = uxrVar.e();
                    if (e3 != null) {
                        uxrVar2.d(e3.longValue());
                    }
                    exchange.b(uxrVar2);
                    upper().receiveResponse(exchange, uxrVar2);
                    return;
                }
                upper().receiveResponse(exchange, uxrVar);
                return;
            }
            if (uxrVar.getMaxResourceBodySize() == 0) {
                uxrVar.setMaxResourceBodySize(exchange.p().getMaxResourceBodySize());
            }
            if (!e(exchange)) {
                uze d = uze.d(exchange);
                if (d(d, d(d), exchange, uxrVar)) {
                    return;
                }
            }
            if (!uxrVar.c()) {
                exchange.b(uxrVar);
                upper().receiveResponse(exchange, uxrVar);
                return;
            }
            if (uxrVar.getOptions().ai()) {
                b(exchange, uxrVar);
            }
            if (uxrVar.getOptions().ae()) {
                e(exchange, uxrVar);
                return;
            }
            return;
        }
        exchange.b(uxrVar);
        upper().receiveResponse(exchange, uxrVar);
    }

    /* renamed from: uyv$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[CoAP.ResponseCode.values().length];
            b = iArr;
            try {
                iArr[CoAP.ResponseCode.REQUEST_ENTITY_INCOMPLETE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[CoAP.ResponseCode.REQUEST_ENTITY_TOO_LARGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean a(Exchange exchange, uxr uxrVar) {
        uyt e2;
        if (this.i) {
            uze d = uze.d(exchange);
            try {
                uxt p = exchange.p();
                if (uxrVar.getOptions().ai()) {
                    uxh f = uxrVar.getOptions().f();
                    boolean z = !p.isCanceled() && f.b() == 0 && f.c() < p.getPayloadSize();
                    synchronized (this.c) {
                        e2 = e(d);
                        if (e2 == null && z) {
                            r4 = e(d, exchange, p, Math.min(f.d(), this.s));
                        }
                    }
                    if (e2 == null) {
                        if (r4 != null) {
                            exchange.c(r4);
                            lower().sendRequest(exchange, r4);
                            return true;
                        }
                    } else {
                        if (!e2.d(uxrVar)) {
                            e.debug("{}discarding obsolete block1 response: {}", this.w, uxrVar);
                            return true;
                        }
                        if (p.isCanceled()) {
                            d(e2);
                            return true;
                        }
                        if (e2.isStarting() && f.d() < this.s) {
                            e2.restart();
                            c(exchange, uxrVar, e2);
                            return true;
                        }
                    }
                } else if (!exchange.p().isCanceled()) {
                    Integer s = uxrVar.getOptions().s();
                    if (s != null && (s.intValue() < 16 || s.intValue() >= p.getPayloadSize())) {
                        s = null;
                    }
                    if (s == null && p.getPayloadSize() > 16) {
                        s = Integer.valueOf(p.getPayloadSize() - 1);
                    }
                    if (s != null) {
                        synchronized (this.c) {
                            r4 = e(d) == null ? e(d, exchange, p, Math.min(uxh.e(s.intValue()), this.s)) : null;
                        }
                    }
                    if (r4 != null) {
                        exchange.c(r4);
                        lower().sendRequest(exchange, r4);
                        return true;
                    }
                }
            } catch (uyx e3) {
                e.debug("{}{} {}", this.w, d, e3.getMessage());
            }
            e.debug("{}{} {}", this.w, d, e3.getMessage());
        }
        return false;
    }

    private void b(Exchange exchange, uxr uxrVar) {
        uxh f = uxrVar.getOptions().f();
        Logger logger = e;
        logger.debug("{}received response acknowledging block1 {}", this.w, f);
        uyt e2 = e(uze.d(exchange));
        if (e2 == null) {
            logger.debug("{}discarding unexpected block1 response: {}", this.w, uxrVar);
            return;
        }
        if (!e2.d(uxrVar)) {
            logger.debug("{}discarding obsolete block1 response: {}", this.w, uxrVar);
            return;
        }
        if (exchange.p().isCanceled()) {
            d(e2);
            return;
        }
        if (!e2.isComplete()) {
            if (f.f()) {
                if (uxrVar.a() == CoAP.ResponseCode.CONTINUE) {
                    c(exchange, uxrVar, e2);
                    return;
                } else {
                    d(e2);
                    exchange.p().setRejected(true);
                    return;
                }
            }
            c(exchange, uxrVar, e2);
            return;
        }
        d(e2);
        if (uxrVar.getOptions().ae()) {
            logger.debug("{}Block1 followed by Block2 transfer", this.w);
        } else {
            exchange.b(uxrVar);
            upper().receiveResponse(exchange, uxrVar);
        }
    }

    private void c(Exchange exchange, uxr uxrVar, uyt uytVar) {
        uxt uxtVar = null;
        try {
            if (uytVar.isComplete()) {
                e.debug("{}stopped block1 transfer, droping request.", this.w);
            } else {
                uxtVar = uytVar.c(Math.min(uxrVar.getOptions().f().d(), this.s));
                uxtVar.setToken(uxrVar.getToken());
                uxtVar.setDestinationContext(uytVar.getFollowUpEndpointContext(uxrVar.getSourceContext()));
                e.debug("{}sending (next) Block1 [num={}]: {}", this.w, Integer.valueOf(uxtVar.getOptions().f().b()), uxtVar);
                exchange.c(uxtVar);
                lower().sendRequest(exchange, uxtVar);
            }
        } catch (RuntimeException e2) {
            e.warn("{}cannot process next block request, aborting request!", this.w, e2);
            if (uxtVar != null) {
                uxtVar.setSendError(e2);
            } else {
                exchange.p().setSendError(e2);
            }
        } catch (uyx e3) {
            e.warn("{}cannot process next block request, aborting request!", this.w, e3);
            if (e3.c()) {
                return;
            }
            exchange.p().setSendError(e3);
        }
    }

    private boolean d(uze uzeVar, uys uysVar, Exchange exchange, uxr uxrVar) {
        uxh j = uxrVar.getOptions().j();
        if (uysVar != null) {
            if (j == null || j.b() == 0) {
                if (uysVar.b(uxrVar)) {
                    e.debug("{}discarding outdated block2 transfer {}, current is [{}]", this.w, uysVar.d(), uxrVar);
                    b(uysVar);
                    uysVar.b(exchange);
                    return false;
                }
                e.debug("{}discarding old block2 transfer [{}], received during ongoing block2 transfer {}", this.w, uxrVar, uysVar.d());
                uysVar.d(exchange);
                return true;
            }
            if (uysVar.c(exchange)) {
                return false;
            }
            e.debug("{}discarding outdate block2 response [{}, {}] received during ongoing block2 transfer {}", this.w, exchange.l(), uxrVar, uysVar.d());
            uysVar.d(exchange);
            return true;
        }
        if (j == null || j.b() == 0) {
            return false;
        }
        e.debug("{}discarding stale block2 response [{}, {}] received without ongoing block2 transfer for {}", this.w, exchange.l(), uxrVar, uzeVar);
        exchange.af();
        return true;
    }

    private void e(Exchange exchange, uxr uxrVar) {
        uxh j = uxrVar.getOptions().j();
        uze d = uze.d(exchange);
        if (exchange.p().isCanceled()) {
            uys d2 = d(d);
            if (d2 != null) {
                b(d2);
            }
            if (uxrVar.g()) {
                upper().receiveResponse(exchange, uxrVar);
                return;
            }
            return;
        }
        if (e(uxrVar)) {
            String format = String.format("requested resource body [%d bytes] exceeds max buffer size [%d bytes], aborting request", uxrVar.getOptions().v(), Integer.valueOf(a(uxrVar)));
            e.debug("{}{}", this.w, format);
            exchange.p().d(new IllegalStateException(format));
            return;
        }
        if (e(exchange)) {
            exchange.b(uxrVar);
            upper().receiveResponse(exchange, uxrVar);
            return;
        }
        synchronized (this.b) {
            if (d(d, d(d), exchange, uxrVar)) {
                return;
            }
            uys b = b(d, exchange, uxrVar);
            try {
                b.d(uxrVar);
                if (j.f()) {
                    b(exchange, uxrVar, b);
                    return;
                }
                Logger logger = e;
                logger.debug("{}all blocks have been retrieved, assembling response and delivering to application layer", this.w);
                uxr uxrVar2 = new uxr(uxrVar.a());
                b.assembleReceivedMessage(uxrVar2);
                uxrVar2.e(exchange.a());
                Long e2 = uxrVar.e();
                if (e2 != null) {
                    uxrVar2.d(e2.longValue());
                }
                b(b);
                logger.debug("{}assembled response: {}", this.w, uxrVar2);
                exchange.c(exchange.p());
                exchange.b(uxrVar2);
                upper().receiveResponse(exchange, uxrVar2);
            } catch (uyx e3) {
                this.k.incrementAndGet();
                e.debug("{}peer {}{}. Ignores response", this.w, d, e3.getMessage());
                if (e3.c()) {
                    return;
                }
                exchange.p().d(e3);
            }
        }
    }

    private void b(Exchange exchange, uxr uxrVar, uys uysVar) {
        int min = Math.min(uxrVar.getOptions().j().d(), this.s);
        if (uxrVar.g() && exchange.y()) {
            exchange.p().addMessageObserver(new uyw(exchange));
        }
        try {
            uxt a2 = uysVar.a(min);
            a2.setDestinationContext(uysVar.getFollowUpEndpointContext(uxrVar.getSourceContext()));
            if (!uxrVar.g()) {
                a2.setToken(uxrVar.getToken());
            }
            if (uysVar.isComplete()) {
                e.debug("{}stopped block2 transfer, droping response.", this.w);
                return;
            }
            e.debug("{}requesting next Block2 [num={}]: {}", this.w, Integer.valueOf(a2.getOptions().j().b()), a2);
            exchange.c(a2);
            lower().sendRequest(exchange, a2);
        } catch (RuntimeException e2) {
            e.debug("{}cannot process next block request, aborting request!", this.w, e2);
            if (exchange.v()) {
                return;
            }
            exchange.p().setSendError(e2);
        } catch (uyx e3) {
            e.debug("{}{} Stop next block request!", this.w, e3.getMessage());
            if (e3.c()) {
                return;
            }
            exchange.p().setSendError(e3);
        }
    }

    private uyt e(uze uzeVar, Exchange exchange, uxt uxtVar, boolean z) {
        Integer num;
        uyt a2;
        uyt uytVar;
        synchronized (this.c) {
            num = null;
            if (z) {
                uytVar = this.c.e((LeastRecentlyUsedCache<uze, uyt>) uzeVar);
                a2 = null;
            } else {
                a2 = this.c.a((LeastRecentlyUsedCache<uze, uyt>) uzeVar);
                uytVar = null;
            }
            if (a2 == null) {
                a2 = uyt.e(uzeVar, this.p, exchange, uxtVar, this.m);
                this.c.c((LeastRecentlyUsedCache<uze, uyt>) uzeVar, (uze) a2);
                this.h = true;
                num = Integer.valueOf(this.c.e());
            }
        }
        if (uytVar != null && uytVar.d()) {
            e.debug("{}stop previous block1 transfer {} {} for new {}", this.w, uzeVar, uytVar, uxtVar);
        }
        if (num != null) {
            e.debug("{}created tracker for outbound block1 transfer {}, transfers in progress: {}", this.w, a2, num);
        } else {
            e.debug("{}block1 transfer {} for {}", this.w, uzeVar, uxtVar);
        }
        return a2;
    }

    private uyt b(uze uzeVar, Exchange exchange, uxt uxtVar, boolean z) {
        Integer num;
        uyt a2;
        uyt uytVar;
        boolean z2 = !z;
        int a3 = a((Message) uxtVar);
        synchronized (this.c) {
            num = null;
            if (z) {
                uytVar = this.c.e((LeastRecentlyUsedCache<uze, uyt>) uzeVar);
                a2 = null;
            } else {
                a2 = this.c.a((LeastRecentlyUsedCache<uze, uyt>) uzeVar);
                uytVar = null;
            }
            if (a2 == null) {
                a2 = uyt.e(uzeVar, this.p, exchange, uxtVar, a3, this.m);
                this.c.c((LeastRecentlyUsedCache<uze, uyt>) uzeVar, (uze) a2);
                this.h = true;
                num = Integer.valueOf(this.c.e());
                z2 = false;
            }
        }
        if (uytVar != null && uytVar.complete()) {
            e.debug("{}stop previous block1 transfer {} {} for new {}", this.w, uzeVar, uytVar, uxtVar);
        }
        if (z2 && this.n != null) {
            if (!this.n.isResponseRelatedToRequest(a2.firstMessage.getSourceContext(), uxtVar.getSourceContext())) {
                e.debug("{}stop block1 transfer {} {} by context mismatch!", this.w, uzeVar, uytVar);
                return b(uzeVar, exchange, uxtVar, true);
            }
        }
        if (num != null) {
            e.debug("{}created tracker for inbound block1 transfer {}, transfers in progress: {}", this.w, a2, num);
        } else {
            e.debug("{}block1 transfer {} for {}", this.w, uzeVar, uxtVar);
        }
        return a2;
    }

    private uys e(uze uzeVar, Exchange exchange, uxr uxrVar, boolean z) {
        Integer num;
        uys a2;
        uys uysVar;
        synchronized (this.b) {
            num = null;
            if (z) {
                uysVar = this.b.e((LeastRecentlyUsedCache<uze, uys>) uzeVar);
                a2 = null;
            } else {
                a2 = this.b.a((LeastRecentlyUsedCache<uze, uys>) uzeVar);
                uysVar = null;
            }
            if (a2 == null) {
                a2 = uys.c(uzeVar, this.p, exchange, uxrVar, this.m);
                this.b.c((LeastRecentlyUsedCache<uze, uys>) uzeVar, (uze) a2);
                this.h = true;
                num = Integer.valueOf(this.b.e());
            }
        }
        if (uysVar != null && uysVar.c()) {
            e.debug("{}stop previous block2 transfer {} {} for new {}", this.w, uzeVar, uysVar, uxrVar);
        }
        if (num != null) {
            e.debug("{}created tracker for outbound block2 transfer {}, transfers in progress: {}", this.w, a2, num);
        } else {
            e.debug("{}block2 transfer {} for {}", this.w, uzeVar, uxrVar);
        }
        return a2;
    }

    private uys b(uze uzeVar, Exchange exchange, uxr uxrVar) {
        uys a2;
        Integer num;
        int a3 = a(uxrVar);
        synchronized (this.b) {
            a2 = this.b.a((LeastRecentlyUsedCache<uze, uys>) uzeVar);
            if (a2 == null) {
                a2 = uys.e(uzeVar, this.p, exchange, uxrVar, a3, this.m);
                this.b.c((LeastRecentlyUsedCache<uze, uys>) uzeVar, (uze) a2);
                this.h = true;
                num = Integer.valueOf(this.b.e());
            } else {
                num = null;
            }
        }
        if (num != null) {
            e.debug("{}created tracker for {} inbound block2 transfer {}, transfers in progress: {}, {}", this.w, uzeVar, a2, num, uxrVar);
        }
        return a2;
    }

    private uyt e(uze uzeVar) {
        uyt a2;
        synchronized (this.c) {
            a2 = this.c.a((LeastRecentlyUsedCache<uze, uyt>) uzeVar);
        }
        return a2;
    }

    private uys d(uze uzeVar) {
        uys a2;
        synchronized (this.b) {
            a2 = this.b.a((LeastRecentlyUsedCache<uze, uys>) uzeVar);
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        int a2;
        int a3;
        synchronized (this.c) {
            a2 = this.c.a(128);
        }
        synchronized (this.b) {
            a3 = a2 + this.b.a(128);
        }
        if (z) {
            f17606a.debug("{}cleaned up {} block transfers!", this.w, Integer.valueOf(a3));
        } else {
            if (!this.h || a3 <= 0) {
                return;
            }
            e.info("{}cleaned up {} block transfers!", this.w, Integer.valueOf(a3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public uyt d(uyt uytVar) {
        uyt a2;
        int e2;
        synchronized (this.c) {
            a2 = this.c.a((LeastRecentlyUsedCache<uze, uyt>) uytVar.getKeyUri(), (uze) uytVar);
            e2 = this.c.e();
        }
        if (a2 != null && a2.complete()) {
            e.debug("{}removing block1 tracker [{}], block1 transfers still in progress: {}", this.w, uytVar.getKeyUri(), Integer.valueOf(e2));
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public uys b(uys uysVar) {
        uys a2;
        int e2;
        synchronized (this.b) {
            a2 = this.b.a((LeastRecentlyUsedCache<uze, uys>) uysVar.getKeyUri(), (uze) uysVar);
            e2 = this.b.e();
        }
        if (a2 != null && a2.complete()) {
            e.debug("{}removing block2 tracker [{}], block2 transfers still in progress: {}", this.w, uysVar.getKeyUri(), Integer.valueOf(e2));
        }
        return a2;
    }

    private boolean a(uxt uxtVar) {
        boolean z = uxtVar.getPayloadSize() > this.o;
        if (z) {
            e.debug("{}request body [{}/{}] requires blockwise transfer", this.w, Integer.valueOf(uxtVar.getPayloadSize()), Integer.valueOf(this.o));
        }
        return z;
    }

    private boolean d(uxr uxrVar, uxh uxhVar) {
        boolean z = true;
        boolean z2 = uxrVar.getPayloadSize() > this.o;
        if (z2 || uxhVar == null) {
            z = z2;
        } else {
            if (uxrVar.getPayloadSize() <= uxh.a(Math.min(uxhVar.d(), this.s))) {
                z = false;
            }
        }
        if (z) {
            e.debug("{}response body [{}/{}] requires blockwise transfer", this.w, Integer.valueOf(uxrVar.getPayloadSize()), Integer.valueOf(this.o));
        }
        return z;
    }

    private boolean a(uxh uxhVar) {
        boolean z = this.r && uxhVar != null;
        if (z) {
            e.debug("{}response requires requested {} blockwise transfer", this.w, uxhVar);
        }
        return z;
    }

    private boolean e(Exchange exchange) {
        uxh j = exchange.p().getOptions().j();
        return j != null && j.b() > 0;
    }

    private boolean e() {
        return this.l > 0;
    }

    private boolean e(uxr uxrVar) {
        return uxrVar.getOptions().ap() && uxrVar.getOptions().v().intValue() > a(uxrVar);
    }

    private boolean d(uxt uxtVar) {
        return uxtVar.getOptions().ao() && uxtVar.getOptions().s().intValue() > a((Message) uxtVar);
    }

    private int a(Message message) {
        int maxResourceBodySize = message.getMaxResourceBodySize();
        return maxResourceBodySize == 0 ? this.l : maxResourceBodySize;
    }

    private uxh d(uxh uxhVar) {
        if (this.s >= uxhVar.d()) {
            return uxhVar;
        }
        int a2 = uxhVar.a();
        int a3 = uxh.a(this.s);
        if (a2 % a3 != 0) {
            throw new IllegalStateException("Block offset " + a2 + " doesn't align with preferred blocksize " + a3 + "!");
        }
        return new uxh(this.s, uxhVar.f(), a2 / a3);
    }
}

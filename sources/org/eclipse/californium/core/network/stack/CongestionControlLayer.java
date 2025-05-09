package org.eclipse.californium.core.network.stack;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.uzf;
import defpackage.uzj;
import defpackage.uzk;
import defpackage.uzl;
import defpackage.uzm;
import defpackage.uzn;
import java.net.InetSocketAddress;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.coap.MessageObserverAdapter;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.RemoteEndpoint;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.LeastRecentlyUsedCache;

/* loaded from: classes7.dex */
public abstract class CongestionControlLayer extends ReliabilityLayer {
    private static final int EXCHANGELIMIT = 50;
    private static final int MAX_RTO = 60000;
    private static final int MIN_RTO = 500;
    private boolean appliesDithering;
    protected final Configuration config;
    private LeastRecentlyUsedCache<InetSocketAddress, RemoteEndpoint> remoteEndpoints;
    private uzm statistic;
    protected final String tag;

    protected float calculateVBF(long j, float f) {
        return f;
    }

    protected abstract RemoteEndpoint createRemoteEndpoint(InetSocketAddress inetSocketAddress);

    public CongestionControlLayer(String str, Configuration configuration) {
        super(configuration);
        this.tag = str;
        this.config = configuration;
        LeastRecentlyUsedCache<InetSocketAddress, RemoteEndpoint> leastRecentlyUsedCache = new LeastRecentlyUsedCache<>(((Integer) configuration.a((BasicDefinition) CoapConfig.w)).intValue(), configuration.a(CoapConfig.u, TimeUnit.SECONDS).longValue());
        this.remoteEndpoints = leastRecentlyUsedCache;
        leastRecentlyUsedCache.c(false);
        setDithering(false);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void start() {
        uzm uzmVar = new uzm(this.tag, 5000, TimeUnit.MILLISECONDS, this.executor);
        this.statistic = uzmVar;
        uzmVar.start();
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void destroy() {
        uzm uzmVar = this.statistic;
        if (uzmVar != null) {
            if (uzmVar.stop()) {
                uzmVar.dump();
            }
            this.statistic = null;
        }
    }

    protected RemoteEndpoint getRemoteEndpoint(Exchange exchange) {
        Message f;
        RemoteEndpoint a2;
        if (exchange.w()) {
            f = exchange.i();
        } else {
            f = exchange.f();
        }
        InetSocketAddress peerAddress = f.getDestinationContext().getPeerAddress();
        synchronized (this.remoteEndpoints) {
            a2 = this.remoteEndpoints.a((LeastRecentlyUsedCache<InetSocketAddress, RemoteEndpoint>) peerAddress);
            if (a2 == null) {
                a2 = createRemoteEndpoint(peerAddress);
                this.remoteEndpoints.c((LeastRecentlyUsedCache<InetSocketAddress, RemoteEndpoint>) peerAddress, (InetSocketAddress) a2);
            }
        }
        return a2;
    }

    public boolean appliesDithering() {
        return this.appliesDithering;
    }

    public void setDithering(boolean z) {
        this.appliesDithering = z;
    }

    public RemoteEndpoint.RtoType getExchangeEstimatorState(Exchange exchange) {
        int o = exchange.o();
        if (o == 0) {
            return RemoteEndpoint.RtoType.STRONG;
        }
        if (o == 1 || o == 2) {
            return RemoteEndpoint.RtoType.WEAK;
        }
        return RemoteEndpoint.RtoType.NONE;
    }

    private boolean processResponse(RemoteEndpoint remoteEndpoint, Exchange exchange, uxr uxrVar) {
        int size;
        boolean z;
        CoAP.Type type = uxrVar.getType();
        if (!uxrVar.g()) {
            if (type == CoAP.Type.CON) {
                return checkNSTART(remoteEndpoint, exchange);
            }
            return true;
        }
        Queue<e> notifyQueue = remoteEndpoint.getNotifyQueue();
        synchronized (remoteEndpoint) {
            e eVar = new e(exchange, uxrVar);
            notifyQueue.remove(eVar);
            size = notifyQueue.size();
            if (size < 50) {
                notifyQueue.add(eVar);
                z = remoteEndpoint.startProcessingNotifies();
            } else {
                z = false;
            }
        }
        if (size >= 50) {
            LOGGER.debug("{}drop outgoing notify, queue full {}", this.tag, Integer.valueOf(size));
        } else if (z) {
            this.executor.execute(new d(remoteEndpoint));
        }
        return false;
    }

    private boolean checkNSTART(RemoteEndpoint remoteEndpoint, Exchange exchange) {
        String str;
        CoAP.Type type;
        Queue<Exchange> responseQueue;
        int size;
        boolean z;
        boolean z2;
        Message f;
        if (exchange.w()) {
            str = "req.-";
            type = exchange.i().getType();
            responseQueue = remoteEndpoint.getRequestQueue();
        } else {
            str = "resp.-";
            type = exchange.f().getType();
            responseQueue = remoteEndpoint.getResponseQueue();
        }
        synchronized (remoteEndpoint) {
            size = responseQueue.size();
            if (remoteEndpoint.registerExchange(exchange)) {
                z = true;
                z2 = false;
            } else if (size < 50) {
                responseQueue.add(exchange);
                z2 = true;
                z = false;
            } else {
                z = false;
                z2 = false;
            }
        }
        if (!z) {
            if (z2) {
                uzm uzmVar = this.statistic;
                if (uzmVar != null) {
                    uzmVar.c();
                }
            } else {
                LOGGER.debug("{}drop {}{}, queue full {}", this.tag, str, type, Integer.valueOf(size));
            }
            return false;
        }
        if (exchange.w()) {
            f = exchange.i();
        } else {
            f = exchange.f();
        }
        f.addMessageObserver(new c(remoteEndpoint, exchange));
        LOGGER.trace("{}send {}{}", this.tag, str, type);
        uzm uzmVar2 = this.statistic;
        if (uzmVar2 != null) {
            uzmVar2.b();
        }
        return true;
    }

    private void processRttMeasurement(Exchange exchange) {
        Long e2;
        RemoteEndpoint.RtoType exchangeEstimatorState;
        RemoteEndpoint remoteEndpoint = getRemoteEndpoint(exchange);
        uxr f = exchange.f();
        if (f != null && (e2 = f.e()) != null && (exchangeEstimatorState = getExchangeEstimatorState(exchange)) != RemoteEndpoint.RtoType.NONE) {
            remoteEndpoint.processRttMeasurement(exchangeEstimatorState, Math.max(TimeUnit.NANOSECONDS.toMillis(e2.longValue()), 1L));
        }
        nextQueuedExchange(remoteEndpoint, exchange);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextQueuedExchange(final RemoteEndpoint remoteEndpoint, Exchange exchange) {
        final Exchange exchange2;
        String str;
        CoAP.Type type;
        int size;
        synchronized (remoteEndpoint) {
            if (remoteEndpoint.removeExchange(exchange)) {
                exchange2 = remoteEndpoint.getResponseQueue().poll();
                if (exchange2 == null) {
                    exchange2 = remoteEndpoint.getRequestQueue().poll();
                }
                if (exchange2 != null) {
                    remoteEndpoint.registerExchange(exchange2);
                }
            } else {
                exchange2 = null;
            }
        }
        if (exchange2 != null) {
            this.statistic.d();
            if (exchange2.w()) {
                str = "req.-";
                type = exchange2.i().getType();
                size = remoteEndpoint.getRequestQueue().size();
            } else {
                str = "resp.-";
                type = exchange2.f().getType();
                size = remoteEndpoint.getResponseQueue().size();
            }
            LOGGER.trace("{}send from queue {}{}, queue left {}", this.tag, str, type, Integer.valueOf(size));
            exchange2.d(new Runnable() { // from class: org.eclipse.californium.core.network.stack.CongestionControlLayer.2
                @Override // java.lang.Runnable
                public void run() {
                    if (exchange2.v()) {
                        CongestionControlLayer.this.nextQueuedExchange(remoteEndpoint, exchange2);
                        return;
                    }
                    if (exchange2.w()) {
                        CongestionControlLayer congestionControlLayer = CongestionControlLayer.this;
                        Exchange exchange3 = exchange2;
                        congestionControlLayer.sendRequest(exchange3, exchange3.i());
                    } else {
                        CongestionControlLayer congestionControlLayer2 = CongestionControlLayer.this;
                        Exchange exchange4 = exchange2;
                        congestionControlLayer2.sendResponse(exchange4, exchange4.f());
                    }
                }
            });
        }
    }

    @Override // org.eclipse.californium.core.network.stack.ReliabilityLayer, org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        if (exchange.o() > 0) {
            LOGGER.warn("{}retransmission in sendRequest", this.tag, new Throwable("retransmission"));
            return;
        }
        prepareRequest(exchange, uxtVar);
        RemoteEndpoint remoteEndpoint = getRemoteEndpoint(exchange);
        if (checkNSTART(remoteEndpoint, exchange)) {
            remoteEndpoint.checkAging();
            LOGGER.debug("{}send request", this.tag);
            if (!remoteEndpoint.inFlightExchange(exchange)) {
                LOGGER.warn("{}unregistered request", this.tag, new Throwable("unregistered request"));
            }
            lower().sendRequest(exchange, uxtVar);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.ReliabilityLayer, org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendResponse(Exchange exchange, uxr uxrVar) {
        RemoteEndpoint remoteEndpoint = getRemoteEndpoint(exchange);
        prepareResponse(exchange, uxrVar);
        if (exchange.o() > 0) {
            if (uxrVar.g()) {
                lower().sendResponse(exchange, uxrVar);
                return;
            } else {
                LOGGER.warn("{}retransmission in sendResponse", this.tag, new Throwable("retransmission"));
                return;
            }
        }
        if (processResponse(remoteEndpoint, exchange, uxrVar)) {
            remoteEndpoint.checkAging();
            lower().sendResponse(exchange, uxrVar);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.ReliabilityLayer
    protected void updateRetransmissionTimeout(Exchange exchange, uzj uzjVar) {
        int min;
        int e2;
        int min2 = Math.min(uzjVar.d(), 60000);
        RemoteEndpoint remoteEndpoint = getRemoteEndpoint(exchange);
        if (exchange.o() == 0) {
            if (this.defaultReliabilityLayerParameters == uzjVar) {
                e2 = (int) remoteEndpoint.getRTO();
            } else {
                e2 = uzjVar.e();
            }
            if (appliesDithering()) {
                e2 = getRandomTimeout(e2, uzjVar.a());
            }
            min = Math.min(min2, Math.max(500, e2));
            exchange.c(calculateVBF(min, uzjVar.b()));
        } else {
            min = Math.min(min2, (int) (exchange.u() * exchange.h()));
        }
        exchange.b(min);
    }

    @Override // org.eclipse.californium.core.network.stack.ReliabilityLayer, org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        LOGGER.debug("{}receive response", this.tag);
        if (processResponse(exchange, uxrVar)) {
            processRttMeasurement(exchange);
            uzm uzmVar = this.statistic;
            if (uzmVar != null) {
                uzmVar.b(uxrVar);
            }
            upper().receiveResponse(exchange, uxrVar);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.ReliabilityLayer, org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
        if (processEmptyMessage(exchange, uxnVar)) {
            processRttMeasurement(exchange);
            upper().receiveEmptyMessage(exchange, uxnVar);
        }
    }

    class d implements Runnable {
        final AtomicInteger d = new AtomicInteger();
        final RemoteEndpoint e;

        public d(RemoteEndpoint remoteEndpoint) {
            this.e = remoteEndpoint;
        }

        @Override // java.lang.Runnable
        public void run() {
            final e peek;
            int size;
            synchronized (this.e) {
                peek = this.e.getNotifyQueue().peek();
                if (peek == null) {
                    this.e.stopProcessingNotifies();
                    size = 0;
                } else {
                    this.d.incrementAndGet();
                    size = this.e.getNotifyQueue().size();
                }
            }
            if (peek != null) {
                final long rto = this.e.getRTO();
                ReliabilityLayer.LOGGER.trace("{}send notify from queue, left {}, next {} ms", CongestionControlLayer.this.tag, Integer.valueOf(size), Long.valueOf(rto));
                peek.e.d(new Runnable() { // from class: org.eclipse.californium.core.network.stack.CongestionControlLayer.d.2
                    /* JADX WARN: Removed duplicated region for block: B:30:0x009f  */
                    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad A[DONT_GENERATE] */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void run() {
                        /*
                            r5 = this;
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.RemoteEndpoint r0 = r0.e     // Catch: java.lang.Throwable -> Lbc
                            monitor-enter(r0)     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r1 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this     // Catch: java.lang.Throwable -> Lb9
                            org.eclipse.californium.core.network.stack.RemoteEndpoint r1 = r1.e     // Catch: java.lang.Throwable -> Lb9
                            java.util.Queue r1 = r1.getNotifyQueue()     // Catch: java.lang.Throwable -> Lb9
                            java.lang.Object r1 = r1.peek()     // Catch: java.lang.Throwable -> Lb9
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$e r2 = r2     // Catch: java.lang.Throwable -> Lb9
                            if (r1 == r2) goto L22
                            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb9
                        L16:
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            org.eclipse.californium.core.network.stack.CongestionControlLayer r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.this
                            java.util.concurrent.ScheduledExecutorService r0 = r0.executor
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r1 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            r0.execute(r1)
                            return
                        L22:
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r1 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this     // Catch: java.lang.Throwable -> Lb9
                            org.eclipse.californium.core.network.stack.RemoteEndpoint r1 = r1.e     // Catch: java.lang.Throwable -> Lb9
                            java.util.Queue r1 = r1.getNotifyQueue()     // Catch: java.lang.Throwable -> Lb9
                            r1.remove()     // Catch: java.lang.Throwable -> Lb9
                            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb9
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$e r0 = r2     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.Exchange r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.e.e(r0)     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.observe.ObserveRelation r0 = r0.r()     // Catch: java.lang.Throwable -> Lbc
                            r1 = 0
                            if (r0 == 0) goto L9a
                            boolean r0 = r0.h()     // Catch: java.lang.Throwable -> Lbc
                            if (r0 != 0) goto L9a
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$e r0 = r2     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.Exchange r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.e.e(r0)     // Catch: java.lang.Throwable -> Lbc
                            uxr r0 = r0.f()     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$e r3 = r2     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.coap.Message r3 = org.eclipse.californium.core.network.stack.CongestionControlLayer.e.d(r3)     // Catch: java.lang.Throwable -> Lbc
                            if (r3 == r0) goto L78
                            boolean r0 = r0.g()     // Catch: java.lang.Throwable -> Lbc
                            if (r0 == 0) goto L69
                            org.slf4j.Logger r0 = org.eclipse.californium.core.network.stack.ReliabilityLayer.LOGGER     // Catch: java.lang.Throwable -> Lbc
                            java.lang.String r1 = "{} notify changed!"
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r2 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.CongestionControlLayer r2 = org.eclipse.californium.core.network.stack.CongestionControlLayer.this     // Catch: java.lang.Throwable -> Lbc
                            java.lang.String r2 = r2.tag     // Catch: java.lang.Throwable -> Lbc
                            r0.warn(r1, r2)     // Catch: java.lang.Throwable -> Lbc
                            goto L16
                        L69:
                            org.slf4j.Logger r0 = org.eclipse.californium.core.network.stack.ReliabilityLayer.LOGGER     // Catch: java.lang.Throwable -> Lbc
                            java.lang.String r1 = "{} notification finished!"
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r2 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.CongestionControlLayer r2 = org.eclipse.californium.core.network.stack.CongestionControlLayer.this     // Catch: java.lang.Throwable -> Lbc
                            java.lang.String r2 = r2.tag     // Catch: java.lang.Throwable -> Lbc
                            r0.warn(r1, r2)     // Catch: java.lang.Throwable -> Lbc
                            goto L16
                        L78:
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$e r3 = r2     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.Exchange r3 = org.eclipse.californium.core.network.stack.CongestionControlLayer.e.e(r3)     // Catch: java.lang.Throwable -> Lbc
                            boolean r3 = r3.v()     // Catch: java.lang.Throwable -> Lbc
                            if (r3 != 0) goto L9a
                            boolean r3 = r0.isCanceled()     // Catch: java.lang.Throwable -> Lbc
                            if (r3 != 0) goto L9a
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r3 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.CongestionControlLayer r3 = org.eclipse.californium.core.network.stack.CongestionControlLayer.this     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$e r4 = r2     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.Exchange r4 = org.eclipse.californium.core.network.stack.CongestionControlLayer.e.e(r4)     // Catch: java.lang.Throwable -> Lbc
                            org.eclipse.californium.core.network.stack.CongestionControlLayer.access$301(r3, r4, r0)     // Catch: java.lang.Throwable -> Lbc
                            long r3 = r3     // Catch: java.lang.Throwable -> Lbc
                            goto L9b
                        L9a:
                            r3 = r1
                        L9b:
                            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                            if (r0 <= 0) goto Lad
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            org.eclipse.californium.core.network.stack.CongestionControlLayer r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.this
                            java.util.concurrent.ScheduledExecutorService r0 = r0.executor
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r1 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
                            r0.schedule(r1, r3, r2)
                            goto Lb8
                        Lad:
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            org.eclipse.californium.core.network.stack.CongestionControlLayer r0 = org.eclipse.californium.core.network.stack.CongestionControlLayer.this
                            java.util.concurrent.ScheduledExecutorService r0 = r0.executor
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r1 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            r0.execute(r1)
                        Lb8:
                            return
                        Lb9:
                            r1 = move-exception
                            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb9
                            throw r1     // Catch: java.lang.Throwable -> Lbc
                        Lbc:
                            r0 = move-exception
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r1 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            org.eclipse.californium.core.network.stack.CongestionControlLayer r1 = org.eclipse.californium.core.network.stack.CongestionControlLayer.this
                            java.util.concurrent.ScheduledExecutorService r1 = r1.executor
                            org.eclipse.californium.core.network.stack.CongestionControlLayer$d r2 = org.eclipse.californium.core.network.stack.CongestionControlLayer.d.this
                            r1.execute(r2)
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.core.network.stack.CongestionControlLayer.d.AnonymousClass2.run():void");
                    }
                });
            } else {
                ReliabilityLayer.LOGGER.debug("{}queue for outgoing notify stopped after {} jobs!", CongestionControlLayer.this.tag, Integer.valueOf(this.d.getAndSet(0)));
            }
        }
    }

    class c extends MessageObserverAdapter {
        final Exchange d;
        final RemoteEndpoint e;

        public c(RemoteEndpoint remoteEndpoint, Exchange exchange) {
            this.e = remoteEndpoint;
            this.d = exchange;
        }

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
        public void onTimeout() {
            CongestionControlLayer.this.nextQueuedExchange(this.e, this.d);
        }
    }

    public static class e {
        private final Message d;
        private final Exchange e;

        e(Exchange exchange, Message message) {
            this.e = exchange;
            this.d = message;
        }

        public int hashCode() {
            return this.e.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof e) {
                return this.e.equals(((e) obj).e);
            }
            return false;
        }
    }

    /* renamed from: org.eclipse.californium.core.network.stack.CongestionControlLayer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[CoapConfig.CongestionControlMode.values().length];
            b = iArr;
            try {
                iArr[CoapConfig.CongestionControlMode.COCOA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[CoapConfig.CongestionControlMode.COCOA_STRONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[CoapConfig.CongestionControlMode.BASIC_RTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[CoapConfig.CongestionControlMode.LINUX_RTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[CoapConfig.CongestionControlMode.PEAKHOPPER_RTO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[CoapConfig.CongestionControlMode.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static ReliabilityLayer newImplementation(String str, Configuration configuration) {
        ReliabilityLayer uzlVar;
        ReliabilityLayer reliabilityLayer;
        CoapConfig.CongestionControlMode congestionControlMode = (CoapConfig.CongestionControlMode) configuration.a((BasicDefinition) CoapConfig.m);
        switch (AnonymousClass1.b[congestionControlMode.ordinal()]) {
            case 1:
                uzlVar = new uzl(str, configuration, false);
                reliabilityLayer = uzlVar;
                break;
            case 2:
                uzlVar = new uzl(str, configuration, true);
                reliabilityLayer = uzlVar;
                break;
            case 3:
                uzlVar = new uzf(str, configuration);
                reliabilityLayer = uzlVar;
                break;
            case 4:
                uzlVar = new uzn(str, configuration);
                reliabilityLayer = uzlVar;
                break;
            case 5:
                uzlVar = new uzk(str, configuration);
                reliabilityLayer = uzlVar;
                break;
            case 6:
                reliabilityLayer = new ReliabilityLayer(configuration);
                break;
            default:
                reliabilityLayer = null;
                break;
        }
        if (reliabilityLayer != null) {
            if (congestionControlMode != CoapConfig.CongestionControlMode.NULL) {
                LOGGER.info("Enabling congestion control: {}", reliabilityLayer.getClass().getSimpleName());
            }
            return reliabilityLayer;
        }
        throw new IllegalArgumentException("Unsupported " + CoapConfig.m.getKey());
    }
}

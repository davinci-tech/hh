package org.eclipse.californium.core.network;

import defpackage.uxk;
import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.uxu;
import defpackage.uxw;
import defpackage.uxy;
import defpackage.uya;
import defpackage.uyg;
import defpackage.uyh;
import defpackage.uyi;
import defpackage.uyn;
import defpackage.uyq;
import defpackage.uyr;
import defpackage.uyu;
import defpackage.uyy;
import defpackage.uyz;
import defpackage.uzt;
import defpackage.vaf;
import defpackage.vam;
import defpackage.vbp;
import defpackage.vbr;
import defpackage.vcb;
import defpackage.vha;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.interceptors.MalformedMessageInterceptor;
import org.eclipse.californium.core.network.interceptors.MessageInterceptor;
import org.eclipse.californium.core.network.serialization.DataParser;
import org.eclipse.californium.core.network.serialization.DataSerializer;
import org.eclipse.californium.core.network.stack.CoapStack;
import org.eclipse.californium.core.observe.NotificationListener;
import org.eclipse.californium.core.observe.ObservationStore;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.elements.Connector;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.EndpointIdentityResolver;
import org.eclipse.californium.elements.MessageCallback;
import org.eclipse.californium.elements.RawDataChannel;
import org.eclipse.californium.elements.UDPConnector;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class CoapEndpoint implements Endpoint, Executor {
    private static CoapStackFactory h;

    /* renamed from: a, reason: collision with root package name */
    protected final CoapStack f15854a;
    private final String ab;
    private ScheduledFuture<?> ac;
    private final MessageExchangeStore g;
    private final Connector i;
    private final Configuration j;
    private ExecutorService l;
    private final Matcher m;
    private final EndpointIdentityResolver o;
    private final DataParser q;
    private final int r;
    private final ObservationStore s;
    private final DataSerializer u;
    private final String w;
    private volatile boolean x;
    private ScheduledExecutorService y;
    private final boolean z;
    private static final Logger d = vha.a((Class<?>) CoapEndpoint.class);
    private static final Logger e = vha.d("org.eclipse.californium.ban");
    private static final AtomicBoolean b = new AtomicBoolean();
    public static final CoapStackFactory c = new ExtendedCoapStackFactory() { // from class: org.eclipse.californium.core.network.CoapEndpoint.6
        @Override // org.eclipse.californium.core.network.ExtendedCoapStackFactory
        public CoapStack createCoapStack(String str, String str2, Configuration configuration, EndpointContextMatcher endpointContextMatcher, Outbox outbox, Object obj) {
            if (CoAP.a(str)) {
                return new uyz(str2, configuration, endpointContextMatcher, outbox);
            }
            return new uyy(str2, configuration, endpointContextMatcher, outbox);
        }

        @Override // org.eclipse.californium.core.network.CoapStackFactory
        public CoapStack createCoapStack(String str, String str2, Configuration configuration, Outbox outbox, Object obj) {
            return createCoapStack(str, str2, configuration, null, outbox, obj);
        }
    };
    private List<EndpointObserver> p = new CopyOnWriteArrayList();
    private List<MessageInterceptor> k = new CopyOnWriteArrayList();
    private List<MessageInterceptor> v = new CopyOnWriteArrayList();
    private List<MalformedMessageInterceptor> n = new CopyOnWriteArrayList();
    private List<NotificationListener> t = new CopyOnWriteArrayList();
    private final EndpointReceiver f = new EndpointReceiver() { // from class: org.eclipse.californium.core.network.CoapEndpoint.3
        @Override // org.eclipse.californium.core.network.EndpointReceiver
        public void receiveRequest(Exchange exchange, uxt uxtVar) {
            if (CoapEndpoint.this.x) {
                exchange.d(CoapEndpoint.this);
                CoapEndpoint.this.f15854a.receiveRequest(exchange, uxtVar);
                CoapEndpoint coapEndpoint = CoapEndpoint.this;
                coapEndpoint.b((List<MessageInterceptor>) coapEndpoint.v, uxtVar);
            }
        }

        @Override // org.eclipse.californium.core.network.EndpointReceiver
        public void receiveResponse(Exchange exchange, uxr uxrVar) {
            if (CoapEndpoint.this.x) {
                if (exchange != null && !uxrVar.isCanceled()) {
                    exchange.d(CoapEndpoint.this);
                    if (!exchange.y()) {
                        uxrVar.e(exchange.a());
                        uxrVar.d(exchange.e());
                    }
                    CoapEndpoint.this.f15854a.receiveResponse(exchange, uxrVar);
                }
                CoapEndpoint coapEndpoint = CoapEndpoint.this;
                coapEndpoint.a((List<MessageInterceptor>) coapEndpoint.v, uxrVar);
            }
        }

        @Override // org.eclipse.californium.core.network.EndpointReceiver
        public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
            uxr f;
            if (CoapEndpoint.this.x) {
                if (exchange != null && !uxnVar.isCanceled()) {
                    exchange.d(CoapEndpoint.this);
                    if (!exchange.w() && (f = exchange.f()) != null && f.isConfirmable()) {
                        f.d(exchange.e());
                    }
                    CoapEndpoint.this.f15854a.receiveEmptyMessage(exchange, uxnVar);
                }
                CoapEndpoint coapEndpoint = CoapEndpoint.this;
                coapEndpoint.d(coapEndpoint.v, uxnVar);
            }
        }

        @Override // org.eclipse.californium.core.network.EndpointReceiver
        public void reject(Message message) {
            if (CoapEndpoint.this.x) {
                CoapEndpoint.this.f15854a.sendEmptyMessage(null, uxn.b(message));
            }
        }
    };

    protected CoapEndpoint(Connector connector, Configuration configuration, TokenGenerator tokenGenerator, ObservationStore observationStore, MessageExchangeStore messageExchangeStore, EndpointContextMatcher endpointContextMatcher, DataSerializer dataSerializer, DataParser dataParser, String str, CoapStackFactory coapStackFactory, Object obj) {
        Logger logger = e;
        if (logger.isInfoEnabled() && b.compareAndSet(false, true)) {
            logger.info("Started.");
        }
        this.j = configuration;
        this.i = connector;
        connector.setRawDataReceiver(new a());
        this.w = CoAP.e(connector.getProtocol());
        this.r = ((Integer) configuration.a((BasicDefinition) CoapConfig.ai)).intValue();
        String h2 = vcb.h(str);
        this.ab = h2;
        TokenGenerator uyhVar = tokenGenerator == null ? new uyh(configuration) : tokenGenerator;
        CoapStackFactory d2 = coapStackFactory == null ? d() : coapStackFactory;
        MessageExchangeStore uxwVar = messageExchangeStore != null ? messageExchangeStore : new uxw(h2, configuration, uyhVar);
        this.g = uxwVar;
        ObservationStore uztVar = observationStore != null ? observationStore : new uzt(configuration);
        this.s = uztVar;
        EndpointContextMatcher d3 = endpointContextMatcher == null ? uya.d(connector, configuration) : endpointContextMatcher;
        this.o = d3;
        connector.setEndpointContextMatcher(d3);
        d.info("{}{} uses {}", h2, getClass().getSimpleName(), d3.getName());
        if (d2 instanceof ExtendedCoapStackFactory) {
            this.f15854a = ((ExtendedCoapStackFactory) d2).createCoapStack(connector.getProtocol(), h2, configuration, d3, new b(), obj);
        } else {
            this.f15854a = d2.createCoapStack(connector.getProtocol(), h2, configuration, new b(), obj);
        }
        if (CoAP.a(connector.getProtocol())) {
            this.z = false;
            this.m = new uyi(configuration, new c(), uyhVar, uztVar, uxwVar, d3, this);
            this.u = dataSerializer != null ? dataSerializer : new uyu();
            this.q = dataParser != null ? dataParser : new uyn();
            return;
        }
        this.z = ((Boolean) configuration.a((BasicDefinition) CoapConfig.at)).booleanValue();
        this.m = new uyg(configuration, new c(), uyhVar, uztVar, uxwVar, this, d3);
        this.u = dataSerializer != null ? dataSerializer : new uyr();
        this.q = dataParser != null ? dataParser : new uyq();
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void start() throws IOException {
        synchronized (this) {
            if (this.x) {
                d.debug("{}Endpoint at {} is already started", this.ab, getUri());
                return;
            }
            if (!this.f15854a.hasDeliverer()) {
                setMessageDeliverer(new uxy.a());
            }
            if (this.l == null) {
                d.info("{}Endpoint [{}] requires an executor to start, using default single-threaded daemon executor", this.ab, getUri());
                final ScheduledExecutorService b2 = vbr.b(new vbp(":CoapEndpoint-" + this.i + '#'));
                setExecutors(b2, b2);
                addObserver(new EndpointObserver() { // from class: org.eclipse.californium.core.network.CoapEndpoint.4
                    @Override // org.eclipse.californium.core.network.EndpointObserver
                    public void started(Endpoint endpoint) {
                    }

                    @Override // org.eclipse.californium.core.network.EndpointObserver
                    public void stopped(Endpoint endpoint) {
                    }

                    @Override // org.eclipse.californium.core.network.EndpointObserver
                    public void destroyed(Endpoint endpoint) {
                        vbr.b(1000L, b2);
                    }
                });
            }
            try {
                d.debug("{}Starting endpoint at {}", this.ab, getUri());
                this.m.start();
                this.i.start();
                this.f15854a.start();
                this.x = true;
                Iterator<EndpointObserver> it = this.p.iterator();
                while (it.hasNext()) {
                    it.next().started(this);
                }
                d.info("{}Started endpoint at {}", this.ab, getUri());
            } catch (IOException e2) {
                stop();
                throw e2;
            }
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void stop() {
        synchronized (this) {
            URI uri = getUri();
            if (!this.x) {
                d.debug("{}Endpoint at {} is already stopped", this.ab, uri);
            } else {
                d.debug("{}Stopping endpoint at {}", this.ab, uri);
                this.x = false;
                ScheduledFuture<?> scheduledFuture = this.ac;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                    this.ac = null;
                }
                this.i.stop();
                this.m.stop();
                Iterator<EndpointObserver> it = this.p.iterator();
                while (it.hasNext()) {
                    it.next().stopped(this);
                }
                d.debug("{}Stopped endpoint at {}", this.ab, uri);
            }
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void destroy() {
        synchronized (this) {
            d.info("{}Destroying endpoint at {}", this.ab, getUri());
            if (this.x) {
                stop();
            }
            this.i.destroy();
            this.f15854a.destroy();
            Iterator<EndpointObserver> it = this.p.iterator();
            while (it.hasNext()) {
                it.next().destroyed(this);
            }
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void clear() {
        this.m.clear();
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public boolean isStarted() {
        return this.x;
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void setExecutors(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2) {
        if (scheduledExecutorService == null || scheduledExecutorService2 == null) {
            throw new IllegalArgumentException("executors must not be null");
        }
        if (this.l == scheduledExecutorService && this.y == scheduledExecutorService2) {
            return;
        }
        if (this.x) {
            throw new IllegalStateException("endpoint already started!");
        }
        this.l = scheduledExecutorService;
        this.y = scheduledExecutorService2;
        this.f15854a.setExecutors(scheduledExecutorService, scheduledExecutorService2);
        this.g.setExecutor(this.y);
        this.s.setExecutor(this.y);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void addNotificationListener(NotificationListener notificationListener) {
        this.t.add(notificationListener);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void removeNotificationListener(NotificationListener notificationListener) {
        this.t.remove(notificationListener);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void addObserver(EndpointObserver endpointObserver) {
        this.p.add(endpointObserver);
        if (isStarted()) {
            endpointObserver.started(this);
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void removeObserver(EndpointObserver endpointObserver) {
        this.p.remove(endpointObserver);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void addInterceptor(MessageInterceptor messageInterceptor) {
        this.k.add(messageInterceptor);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void removeInterceptor(MessageInterceptor messageInterceptor) {
        this.k.remove(messageInterceptor);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public List<MessageInterceptor> getInterceptors() {
        return Collections.unmodifiableList(this.k);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void addPostProcessInterceptor(MessageInterceptor messageInterceptor) {
        this.v.add(messageInterceptor);
        if (messageInterceptor instanceof MalformedMessageInterceptor) {
            this.n.add((MalformedMessageInterceptor) messageInterceptor);
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void removePostProcessInterceptor(MessageInterceptor messageInterceptor) {
        this.v.remove(messageInterceptor);
        if (messageInterceptor instanceof MalformedMessageInterceptor) {
            this.n.remove((MalformedMessageInterceptor) messageInterceptor);
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public List<MessageInterceptor> getPostProcessInterceptors() {
        return Collections.unmodifiableList(this.v);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void sendRequest(final uxt uxtVar) {
        Object peerAddress;
        if (!this.x) {
            uxtVar.cancel();
            return;
        }
        InetSocketAddress peerAddress2 = uxtVar.getDestinationContext().getPeerAddress();
        int mid = uxtVar.getMID();
        if (uxtVar.h()) {
            if (this.r <= 0) {
                d.warn("{}multicast messaging to destination {} is not enabled! Please enable it configuring \"" + CoapConfig.ai.getKey() + "\" greater than 0", this.ab, vcb.b((SocketAddress) peerAddress2));
                uxtVar.setSendError(new IllegalArgumentException("multicast is not enabled!"));
                return;
            }
            if (uxtVar.getType() == CoAP.Type.CON) {
                d.warn("{}CON request to multicast destination {} is not allowed, as per RFC 7252, 8.1, a client MUST use NON message type for multicast requests", this.ab, vcb.b((SocketAddress) peerAddress2));
                uxtVar.setSendError(new IllegalArgumentException("multicast is not supported for CON!"));
                return;
            } else if (uxtVar.hasMID() && mid < this.r) {
                d.warn("{}multicast request to group {} has mid {} which is not in the MULTICAST_MID range [{}-65535]", this.ab, vcb.b((SocketAddress) peerAddress2), Integer.valueOf(mid), Integer.valueOf(this.r));
                uxtVar.setSendError(new IllegalArgumentException("multicast mid is not in range [" + this.r + "-65535]"));
                return;
            }
        } else if (c(mid)) {
            d.warn("{}request to {} has mid {}, which is in the MULTICAST_MID range [{}-65535]", this.ab, vcb.b((SocketAddress) peerAddress2), Integer.valueOf(mid), Integer.valueOf(this.r));
            uxtVar.setSendError(new IllegalArgumentException("unicast mid is in multicast range [" + this.r + "-65535]"));
            return;
        }
        if (peerAddress2.isUnresolved()) {
            String a2 = vcb.a(peerAddress2);
            d.warn("{}request has unresolved destination address {}", this.ab, a2);
            uxtVar.setSendError(new IllegalArgumentException(a2 + " is a unresolved address!"));
            return;
        }
        if (uxtVar.isSent()) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Request already sent!");
            d.error("{}request was already sent!", this.ab, illegalArgumentException);
            uxtVar.setSendError(illegalArgumentException);
            return;
        }
        try {
            peerAddress = this.o.getEndpointIdentity(uxtVar.getDestinationContext());
        } catch (IllegalArgumentException e2) {
            if (uxtVar.getRawCode() == 0) {
                peerAddress = uxtVar.getDestinationContext().getPeerAddress();
            } else {
                throw e2;
            }
        }
        final Exchange exchange = new Exchange(uxtVar, peerAddress, Exchange.Origin.LOCAL, this.l);
        exchange.d(this);
        exchange.d(new Runnable() { // from class: org.eclipse.californium.core.network.CoapEndpoint.1
            @Override // java.lang.Runnable
            public void run() {
                CoapEndpoint.this.f15854a.sendRequest(exchange, uxtVar);
            }
        });
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void sendResponse(final Exchange exchange, final uxr uxrVar) {
        if (!this.x) {
            uxrVar.cancel();
            return;
        }
        if (uxrVar.isSent()) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Response already sent!");
            d.error("{}response was already sent!", this.ab, illegalArgumentException);
            uxrVar.setSendError(illegalArgumentException);
        } else if (exchange.b()) {
            this.f15854a.sendResponse(exchange, uxrVar);
        } else {
            exchange.d(new Runnable() { // from class: org.eclipse.californium.core.network.CoapEndpoint.5
                @Override // java.lang.Runnable
                public void run() {
                    CoapEndpoint.this.f15854a.sendResponse(exchange, uxrVar);
                }
            });
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void sendEmptyMessage(final Exchange exchange, final uxn uxnVar) {
        if (!this.x) {
            uxnVar.cancel();
            return;
        }
        if (uxnVar.isSent()) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Empty message already sent!");
            d.error("{}empty message was already sent!", this.ab, illegalArgumentException);
            uxnVar.setSendError(illegalArgumentException);
        } else if (exchange.b()) {
            this.f15854a.sendEmptyMessage(exchange, uxnVar);
        } else {
            exchange.d(new Runnable() { // from class: org.eclipse.californium.core.network.CoapEndpoint.2
                @Override // java.lang.Runnable
                public void run() {
                    CoapEndpoint.this.f15854a.sendEmptyMessage(exchange, uxnVar);
                }
            });
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void setMessageDeliverer(MessageDeliverer messageDeliverer) {
        this.f15854a.setDeliverer(messageDeliverer);
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public InetSocketAddress getAddress() {
        return this.i.getAddress();
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public URI getUri() {
        try {
            InetSocketAddress address = getAddress();
            return new URI(this.w, null, vcb.d(address.getAddress()), address.getPort(), null, null, null);
        } catch (URISyntaxException e2) {
            d.warn("{}URI", this.ab, e2);
            return null;
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public Configuration getConfig() {
        return this.j;
    }

    class c implements NotificationListener {
        private c() {
        }

        @Override // org.eclipse.californium.core.observe.NotificationListener
        public void onNotification(uxt uxtVar, uxr uxrVar) {
            Iterator it = CoapEndpoint.this.t.iterator();
            while (it.hasNext()) {
                ((NotificationListener) it.next()).onNotification(uxtVar, uxrVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<MessageInterceptor> list, uxt uxtVar) {
        Iterator<MessageInterceptor> it = list.iterator();
        while (it.hasNext()) {
            it.next().sendRequest(uxtVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<MessageInterceptor> list, uxr uxrVar) {
        Iterator<MessageInterceptor> it = list.iterator();
        while (it.hasNext()) {
            it.next().sendResponse(uxrVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<MessageInterceptor> list, uxn uxnVar) {
        Iterator<MessageInterceptor> it = list.iterator();
        while (it.hasNext()) {
            it.next().sendEmptyMessage(uxnVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<MessageInterceptor> list, uxt uxtVar) {
        Iterator<MessageInterceptor> it = list.iterator();
        while (it.hasNext()) {
            it.next().receiveRequest(uxtVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<MessageInterceptor> list, uxr uxrVar) {
        Iterator<MessageInterceptor> it = list.iterator();
        while (it.hasNext()) {
            it.next().receiveResponse(uxrVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<MessageInterceptor> list, uxn uxnVar) {
        Iterator<MessageInterceptor> it = list.iterator();
        while (it.hasNext()) {
            it.next().receiveEmptyMessage(uxnVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(vaf vafVar) {
        Iterator<MalformedMessageInterceptor> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().receivedMalformedMessage(vafVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(int i) {
        int i2 = this.r;
        return i2 > 0 && i2 <= i && i <= 65535;
    }

    public class b implements Outbox {
        public b() {
        }

        @Override // org.eclipse.californium.core.network.Outbox
        public void sendRequest(Exchange exchange, uxt uxtVar) {
            a(uxtVar);
            exchange.c(uxtVar);
            CoapEndpoint.this.m.sendRequest(exchange);
            CoapEndpoint coapEndpoint = CoapEndpoint.this;
            coapEndpoint.e((List<MessageInterceptor>) coapEndpoint.k, uxtVar);
            uxtVar.setReadyToSend();
            if (!CoapEndpoint.this.x) {
                uxtVar.cancel();
            }
            if (uxtVar.isCanceled() || uxtVar.getSendError() != null) {
                exchange.c();
                return;
            }
            if (exchange.o() == 0) {
                exchange.ae();
            }
            CoapEndpoint.this.i.send(CoapEndpoint.this.u.serializeRequest(uxtVar, new ExchangeCallback<uxt>(exchange, uxtVar) { // from class: org.eclipse.californium.core.network.CoapEndpoint.b.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // org.eclipse.californium.core.network.CoapEndpoint.SendingCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void notifyPostProcess(uxt uxtVar2) {
                    CoapEndpoint.this.e((List<MessageInterceptor>) CoapEndpoint.this.v, uxtVar2);
                }
            }));
        }

        @Override // org.eclipse.californium.core.network.Outbox
        public void sendResponse(Exchange exchange, uxr uxrVar) {
            a(uxrVar);
            exchange.e(uxrVar);
            CoapEndpoint.this.m.sendResponse(exchange);
            CoapEndpoint coapEndpoint = CoapEndpoint.this;
            coapEndpoint.e((List<MessageInterceptor>) coapEndpoint.k, uxrVar);
            uxrVar.setReadyToSend();
            if (!CoapEndpoint.this.x) {
                uxrVar.cancel();
            }
            if (!uxrVar.isCanceled() && uxrVar.getSendError() == null) {
                vaf serializeResponse = CoapEndpoint.this.u.serializeResponse(uxrVar, new ExchangeCallback<uxr>(exchange, uxrVar) { // from class: org.eclipse.californium.core.network.CoapEndpoint.b.3
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // org.eclipse.californium.core.network.CoapEndpoint.SendingCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void notifyPostProcess(uxr uxrVar2) {
                        CoapEndpoint.this.e((List<MessageInterceptor>) CoapEndpoint.this.v, uxrVar2);
                        if (CoapEndpoint.this.z) {
                            this.exchange.i().offload(Message.OffloadMode.FULL);
                            uxrVar2.offload(Message.OffloadMode.PAYLOAD);
                        }
                    }
                });
                if (uxrVar.isConfirmable() && exchange.o() == 0) {
                    exchange.ae();
                }
                CoapEndpoint.this.i.send(serializeResponse);
                return;
            }
            exchange.c();
        }

        @Override // org.eclipse.californium.core.network.Outbox
        public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
            a(uxnVar);
            CoapEndpoint.this.m.sendEmptyMessage(exchange, uxnVar);
            CoapEndpoint coapEndpoint = CoapEndpoint.this;
            coapEndpoint.b((List<MessageInterceptor>) coapEndpoint.k, uxnVar);
            uxnVar.setReadyToSend();
            if (!CoapEndpoint.this.x) {
                uxnVar.cancel();
            }
            if (uxnVar.isCanceled() || uxnVar.getSendError() != null) {
                if (exchange != null) {
                    exchange.c();
                }
            } else if (exchange != null) {
                CoapEndpoint.this.i.send(CoapEndpoint.this.u.serializeEmptyMessage(uxnVar, new ExchangeCallback<uxn>(exchange, uxnVar) { // from class: org.eclipse.californium.core.network.CoapEndpoint.b.5
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // org.eclipse.californium.core.network.CoapEndpoint.SendingCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void notifyPostProcess(uxn uxnVar2) {
                        CoapEndpoint.this.b((List<MessageInterceptor>) CoapEndpoint.this.v, uxnVar2);
                    }
                }));
            } else {
                CoapEndpoint.this.i.send(CoapEndpoint.this.u.serializeEmptyMessage(uxnVar, new SendingCallback<uxn>(uxnVar) { // from class: org.eclipse.californium.core.network.CoapEndpoint.b.4
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // org.eclipse.californium.core.network.CoapEndpoint.SendingCallback
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public void notifyPostProcess(uxn uxnVar2) {
                        CoapEndpoint.this.b((List<MessageInterceptor>) CoapEndpoint.this.v, uxnVar2);
                    }
                }));
            }
        }

        private void a(Message message) {
            if (message.getDestinationContext() == null) {
                throw new IllegalArgumentException("Message has no endpoint context");
            }
        }
    }

    class a implements RawDataChannel {
        private a() {
        }

        @Override // org.eclipse.californium.elements.RawDataChannel
        public void receiveData(final vaf vafVar) {
            if (vafVar.c() == null) {
                throw new IllegalArgumentException("received message that does not have a endpoint context");
            }
            if (vafVar.c().getPeerAddress() == null) {
                throw new IllegalArgumentException("received message that does not have a source address");
            }
            if (vafVar.c().getPeerAddress().getPort() != 0) {
                if (CoapEndpoint.this.x) {
                    CoapEndpoint.this.execute(new Runnable() { // from class: org.eclipse.californium.core.network.CoapEndpoint.a.4
                        @Override // java.lang.Runnable
                        public void run() {
                            a.this.c(vafVar);
                        }
                    });
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("received message that does not have a source port");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0144  */
        /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void c(defpackage.vaf r7) {
            /*
                Method dump skipped, instructions count: 444
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.core.network.CoapEndpoint.a.c(vaf):void");
        }

        private void a(EndpointContext endpointContext, uxk uxkVar) {
            uxr uxrVar = new uxr(uxkVar.e(), true);
            uxrVar.setDestinationContext(endpointContext);
            uxrVar.setToken(uxkVar.c());
            uxrVar.setMID(uxkVar.a());
            uxrVar.setType(CoAP.Type.ACK);
            uxrVar.setPayload(uxkVar.getMessage());
            CoapEndpoint coapEndpoint = CoapEndpoint.this;
            coapEndpoint.e((List<MessageInterceptor>) coapEndpoint.k, uxrVar);
            uxrVar.setReadyToSend();
            if (!CoapEndpoint.this.x) {
                uxrVar.cancel();
            }
            CoapEndpoint.this.i.send(CoapEndpoint.this.u.serializeResponse(uxrVar, new SendingCallback<uxr>(uxrVar) { // from class: org.eclipse.californium.core.network.CoapEndpoint.a.5
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // org.eclipse.californium.core.network.CoapEndpoint.SendingCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void notifyPostProcess(uxr uxrVar2) {
                    CoapEndpoint.this.e((List<MessageInterceptor>) CoapEndpoint.this.v, uxrVar2);
                }
            }));
        }

        private void c(vaf vafVar, uxk uxkVar) {
            uxn uxnVar = new uxn(CoAP.Type.RST);
            uxnVar.setMID(uxkVar.a());
            uxnVar.setDestinationContext(vafVar.c());
            CoapEndpoint.this.f15854a.sendEmptyMessage(null, uxnVar);
        }

        private void a(uxt uxtVar) {
            uxtVar.e(CoapEndpoint.this.w);
            if (!CoapEndpoint.this.x) {
                CoapEndpoint.d.debug("{}not running, drop request {}", CoapEndpoint.this.ab, uxtVar);
                return;
            }
            CoapEndpoint coapEndpoint = CoapEndpoint.this;
            coapEndpoint.b((List<MessageInterceptor>) coapEndpoint.k, uxtVar);
            if (uxtVar.isCanceled()) {
                return;
            }
            CoapEndpoint.this.m.receiveRequest(uxtVar, CoapEndpoint.this.f);
        }

        private void e(uxr uxrVar) {
            CoapEndpoint coapEndpoint = CoapEndpoint.this;
            coapEndpoint.a((List<MessageInterceptor>) coapEndpoint.k, uxrVar);
            if (uxrVar.isCanceled()) {
                return;
            }
            CoapEndpoint.this.m.receiveResponse(uxrVar, CoapEndpoint.this.f);
        }

        private void d(uxn uxnVar) {
            CoapEndpoint coapEndpoint = CoapEndpoint.this;
            coapEndpoint.d(coapEndpoint.k, uxnVar);
            if (uxnVar.isCanceled()) {
                return;
            }
            if ((uxnVar.getType() == CoAP.Type.CON || uxnVar.getType() == CoAP.Type.NON) && uxnVar.hasMID()) {
                CoapEndpoint.d.debug("{}responding to ping from {}", CoapEndpoint.this.ab, uxnVar.getSourceContext());
                CoapEndpoint.this.f.reject(uxnVar);
            } else {
                if (CoapEndpoint.this.c(uxnVar.getMID())) {
                    CoapEndpoint.d.debug("{} silently ignoring empty messages for multicast request {}", CoapEndpoint.this.ab, uxnVar.getSourceContext());
                    uxnVar.setCanceled(true);
                    CoapEndpoint.this.f.receiveEmptyMessage(null, uxnVar);
                    return;
                }
                CoapEndpoint.this.m.receiveEmptyMessage(uxnVar, CoapEndpoint.this.f);
            }
        }
    }

    static abstract class SendingCallback<T extends Message> implements MessageCallback {
        private final T message;

        protected abstract void notifyPostProcess(T t);

        protected void onContextEstablished(EndpointContext endpointContext, long j) {
        }

        public SendingCallback(T t) {
            if (t == null) {
                throw new NullPointerException("message must not be null");
            }
            this.message = t;
        }

        @Override // org.eclipse.californium.elements.MessageCallback
        public void onConnecting() {
            this.message.onConnecting();
        }

        @Override // org.eclipse.californium.elements.MessageCallback
        public void onDtlsRetransmission(int i) {
            this.message.onDtlsRetransmission(i);
        }

        @Override // org.eclipse.californium.elements.MessageCallback
        public final void onContextEstablished(EndpointContext endpointContext) {
            long d = ClockUtil.d();
            this.message.setNanoTimestamp(d);
            onContextEstablished(endpointContext, d);
        }

        @Override // org.eclipse.californium.elements.MessageCallback
        public void onSent() {
            if (this.message.isSent()) {
                this.message.setDuplicate(true);
            }
            this.message.setSent(true);
            notifyPostProcess(this.message);
        }

        @Override // org.eclipse.californium.elements.MessageCallback
        public void onError(Throwable th) {
            this.message.setSendError(th);
            notifyPostProcess(this.message);
        }
    }

    static abstract class ExchangeCallback<T extends Message> extends SendingCallback<T> {
        protected final Exchange exchange;

        public ExchangeCallback(Exchange exchange, T t) {
            super(t);
            if (exchange == null) {
                throw new NullPointerException("exchange must not be null");
            }
            this.exchange = exchange;
        }

        @Override // org.eclipse.californium.core.network.CoapEndpoint.SendingCallback
        protected void onContextEstablished(EndpointContext endpointContext, long j) {
            Exchange exchange = this.exchange;
            if (j == 0) {
                j = -1;
            }
            exchange.d(j);
            this.exchange.c(endpointContext);
        }
    }

    @Override // org.eclipse.californium.core.network.Endpoint
    public void cancelObservation(uxu uxuVar) {
        this.m.cancelObserve(uxuVar);
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        ExecutorService executorService = this.l;
        if (executorService == null) {
            d.error("{}Executor not ready!", this.ab, new Throwable("execution failed!"));
            return;
        }
        try {
            executorService.execute(new Runnable() { // from class: org.eclipse.californium.core.network.CoapEndpoint.9
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } catch (Throwable th) {
                        CoapEndpoint.d.error("{}exception in protocol stage thread: {}", CoapEndpoint.this.ab, th.getMessage(), th);
                    }
                }
            });
        } catch (RejectedExecutionException e2) {
            d.debug("{} execute:", this.ab, e2);
        }
    }

    public static class e {
        private CoapStackFactory e;
        private DataParser f;
        private Object g;
        private String k;
        private DataSerializer m;
        private TokenGenerator n;
        private Configuration b = null;
        private InetSocketAddress c = null;
        private Connector d = null;
        private ObservationStore i = null;
        private MessageExchangeStore j = null;
        private EndpointContextMatcher h = null;

        /* renamed from: a, reason: collision with root package name */
        private int[] f15860a = new int[0];

        public e d(Connector connector) {
            if (this.c != null || this.d != null) {
                throw new IllegalStateException("bind address already defined!");
            }
            if ((connector instanceof vam) && ((vam) connector).b()) {
                throw new IllegalStateException("connector must not be a multicast receiver!");
            }
            this.d = connector;
            return this;
        }

        public e e(DataSerializer dataSerializer, DataParser dataParser) {
            this.m = dataSerializer;
            this.f = dataParser;
            return this;
        }

        public CoapEndpoint e() {
            if (this.b == null) {
                this.b = Configuration.d();
            }
            if (this.d == null) {
                if (this.c == null) {
                    this.c = new InetSocketAddress(0);
                }
                this.d = new UDPConnector(this.c, this.b);
            }
            if (this.n == null) {
                this.n = new uyh(this.b);
            }
            if (this.i == null) {
                this.i = new uzt(this.b);
            }
            if (this.h == null) {
                this.h = uya.d(this.d, this.b);
            }
            if (this.k == null) {
                this.k = CoAP.e(this.d.getProtocol());
            }
            this.k = vcb.h(this.k);
            if (this.j == null) {
                this.j = new uxw(this.k, this.b, this.n);
            }
            if (this.e == null) {
                this.e = CoapEndpoint.d();
            }
            if (this.f == null) {
                if (CoAP.a(this.d.getProtocol())) {
                    this.f = new uyn(this.f15860a);
                } else {
                    this.f = new uyq(((Boolean) this.b.a((BasicDefinition) CoapConfig.aq)).booleanValue(), this.f15860a);
                }
            }
            return new CoapEndpoint(this.d, this.b, this.n, this.i, this.j, this.h, this.m, this.f, this.k, this.e, this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CoapStackFactory d() {
        CoapStackFactory coapStackFactory;
        synchronized (CoapEndpoint.class) {
            if (h == null) {
                h = c;
            }
            coapStackFactory = h;
        }
        return coapStackFactory;
    }
}

package org.eclipse.californium.core.network;

import defpackage.uxr;
import defpackage.uxt;
import defpackage.uxu;
import defpackage.uzp;
import defpackage.vha;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.californium.core.coap.MessageObserverAdapter;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.TokenGenerator;
import org.eclipse.californium.core.observe.NotificationListener;
import org.eclipse.californium.core.observe.ObservationStore;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.EndpointIdentityResolver;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class BaseMatcher implements Matcher {
    private static final Logger LOG = vha.a((Class<?>) BaseMatcher.class);
    protected final Configuration config;
    public final MessageExchangeStore exchangeStore;
    protected final Executor executor;
    private final EndpointIdentityResolver identityResolver;
    private final NotificationListener notificationListener;
    protected final ObservationStore observationStore;
    public boolean running = false;
    protected final TokenGenerator tokenGenerator;

    @Override // org.eclipse.californium.core.network.Matcher
    public void clear() {
    }

    public BaseMatcher(Configuration configuration, NotificationListener notificationListener, TokenGenerator tokenGenerator, ObservationStore observationStore, MessageExchangeStore messageExchangeStore, EndpointIdentityResolver endpointIdentityResolver, Executor executor) {
        if (configuration == null) {
            throw new NullPointerException("Config must not be null");
        }
        if (notificationListener == null) {
            throw new NullPointerException("NotificationListener must not be null");
        }
        if (tokenGenerator == null) {
            throw new NullPointerException("TokenGenerator must not be null");
        }
        if (messageExchangeStore == null) {
            throw new NullPointerException("MessageExchangeStore must not be null");
        }
        if (observationStore == null) {
            throw new NullPointerException("ObservationStore must not be null");
        }
        if (endpointIdentityResolver == null) {
            throw new NullPointerException("EndpointIdentityResolver must not be null");
        }
        if (executor == null) {
            throw new NullPointerException("Executor must not be null");
        }
        this.config = configuration;
        this.notificationListener = notificationListener;
        this.exchangeStore = messageExchangeStore;
        this.observationStore = observationStore;
        this.tokenGenerator = tokenGenerator;
        this.identityResolver = endpointIdentityResolver;
        this.executor = executor;
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void start() {
        synchronized (this) {
            if (!this.running) {
                this.exchangeStore.start();
                this.observationStore.start();
                this.running = true;
            }
        }
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void stop() {
        synchronized (this) {
            if (this.running) {
                this.exchangeStore.stop();
                this.observationStore.stop();
                clear();
                this.running = false;
            }
        }
    }

    protected final void registerObserve(uxt uxtVar) {
        if (!uxtVar.getOptions().ae() || uxtVar.getOptions().j().b() == 0) {
            LOG.debug("registering observe request {}", uxtVar);
            uxu token = uxtVar.getToken();
            if (token == null) {
                do {
                    token = this.tokenGenerator.createToken(TokenGenerator.Scope.LONG_TERM);
                    uxtVar.setToken(token);
                } while (this.observationStore.putIfAbsent(token, new uzp(uxtVar, null)) != null);
            } else {
                this.observationStore.put(token, new uzp(uxtVar, null));
            }
            uxtVar.addMessageObserver(new e(token) { // from class: org.eclipse.californium.core.network.BaseMatcher.4
                @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
                public void onCancel() {
                    e();
                }

                @Override // org.eclipse.californium.core.coap.MessageObserverAdapter
                public void failed() {
                    e();
                }

                @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
                public void onContextEstablished(EndpointContext endpointContext) {
                    BaseMatcher.this.observationStore.setContext(this.g, endpointContext);
                }
            });
        }
    }

    protected final Exchange matchNotifyResponse(uxr uxrVar) {
        uxu token;
        uzp uzpVar;
        if ((uxrVar.i() && !uxrVar.getOptions().an()) || (uzpVar = this.observationStore.get((token = uxrVar.getToken()))) == null) {
            return null;
        }
        final uxt c = uzpVar.c();
        Exchange exchange = new Exchange(c, this.identityResolver.getEndpointIdentity(uxrVar.getSourceContext()), Exchange.Origin.LOCAL, this.executor, uzpVar.d(), true);
        LOG.debug("re-created exchange from original observe request: {}", c);
        c.addMessageObserver(new e(token) { // from class: org.eclipse.californium.core.network.BaseMatcher.5
            @Override // org.eclipse.californium.core.network.BaseMatcher.e, org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
            public void onResponse(uxr uxrVar2) {
                try {
                    BaseMatcher.this.notificationListener.onNotification(c, uxrVar2);
                } finally {
                    if (!uxrVar2.g()) {
                        BaseMatcher.LOG.debug("observation with token {} removed, removing from observation store", this.g);
                        e();
                    }
                }
            }
        });
        return exchange;
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void cancelObserve(uxu uxuVar) {
        boolean z = false;
        for (Exchange exchange : this.exchangeStore.findByToken(uxuVar)) {
            uxt p = exchange.p();
            if (p.o()) {
                p.cancel();
                if (!exchange.y()) {
                    z = true;
                }
                exchange.c();
            }
        }
        if (z) {
            return;
        }
        this.observationStore.remove(uxuVar);
    }

    class e extends MessageObserverAdapter {

        /* renamed from: a, reason: collision with root package name */
        protected final AtomicBoolean f15853a;
        protected final uxu g;

        public e(uxu uxuVar) {
            super(true);
            this.f15853a = new AtomicBoolean();
            this.g = uxuVar;
        }

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
        public void onResponse(uxr uxrVar) {
            if (BaseMatcher.this.observationStore.get(this.g) != null) {
                if (uxrVar.f() || !uxrVar.g()) {
                    BaseMatcher.LOG.debug("observation with token {} not established, removing from observation store", this.g);
                    e();
                }
            }
        }

        protected void e() {
            if (this.f15853a.compareAndSet(false, true)) {
                BaseMatcher.this.observationStore.remove(this.g);
            }
        }
    }
}

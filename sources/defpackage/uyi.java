package defpackage;

import java.util.concurrent.Executor;
import org.eclipse.californium.core.coap.MessageObserverAdapter;
import org.eclipse.californium.core.network.BaseMatcher;
import org.eclipse.californium.core.network.EndpointReceiver;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.MessageExchangeStore;
import org.eclipse.californium.core.network.RemoveHandler;
import org.eclipse.californium.core.network.TokenGenerator;
import org.eclipse.californium.core.observe.NotificationListener;
import org.eclipse.californium.core.observe.ObservationStore;
import org.eclipse.californium.core.observe.ObserveRelation;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class uyi extends BaseMatcher {
    private static final Logger e = vha.a((Class<?>) uyi.class);
    private final RemoveHandler b;
    private final EndpointContextMatcher c;

    @Override // org.eclipse.californium.core.network.Matcher
    public void receiveEmptyMessage(uxn uxnVar, EndpointReceiver endpointReceiver) {
    }

    public uyi(Configuration configuration, NotificationListener notificationListener, TokenGenerator tokenGenerator, ObservationStore observationStore, MessageExchangeStore messageExchangeStore, EndpointContextMatcher endpointContextMatcher, Executor executor) {
        super(configuration, notificationListener, tokenGenerator, observationStore, messageExchangeStore, endpointContextMatcher, executor);
        this.b = new e();
        this.c = endpointContextMatcher;
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void sendRequest(Exchange exchange) {
        uxt i = exchange.i();
        if (i.o()) {
            registerObserve(i);
        }
        exchange.a(this.b);
        this.exchangeStore.registerOutboundRequestWithTokenOnly(exchange);
        e.debug("tracking open request using [{}]", exchange.k());
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void sendResponse(Exchange exchange) {
        uxr f = exchange.f();
        final ObserveRelation r = exchange.r();
        f.e(exchange.i().getToken());
        if (r != null) {
            f.addMessageObserver(new MessageObserverAdapter() { // from class: uyi.3
                @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
                public void onSendError(Throwable th) {
                    r.b();
                }
            });
        }
        exchange.af();
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
        if (uxnVar.isConfirmable()) {
            uxnVar.setToken(uxu.f17579a);
            return;
        }
        throw new UnsupportedOperationException("sending empty message (ACK/RST) over tcp is not supported!");
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void receiveRequest(final uxt uxtVar, final EndpointReceiver endpointReceiver) {
        final Exchange exchange = new Exchange(uxtVar, this.c.getEndpointIdentity(uxtVar.getSourceContext()), Exchange.Origin.REMOTE, this.executor);
        exchange.a(this.b);
        exchange.d(new Runnable() { // from class: uyi.4
            @Override // java.lang.Runnable
            public void run() {
                endpointReceiver.receiveRequest(exchange, uxtVar);
            }
        });
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void receiveResponse(final uxr uxrVar, final EndpointReceiver endpointReceiver) {
        final uyc keyToken = this.tokenGenerator.getKeyToken(uxrVar.getToken(), this.c.getEndpointIdentity(uxrVar.getSourceContext()));
        Exchange exchange = this.exchangeStore.get(keyToken);
        if (exchange == null) {
            exchange = matchNotifyResponse(uxrVar);
        }
        if (exchange == null) {
            e.trace("discarding by [{}] unmatchable response from [{}]: {}", keyToken, uxrVar.getSourceContext(), uxrVar);
            e(uxrVar, endpointReceiver);
        } else {
            final Exchange exchange2 = exchange;
            exchange.d(new Runnable() { // from class: uyi.5
                @Override // java.lang.Runnable
                public void run() {
                    if ((!exchange2.y() || exchange2.p() != exchange2.i()) && uyi.this.exchangeStore.get(keyToken) != exchange2) {
                        if (uyi.this.running) {
                            uyi.e.debug("ignoring response {}, exchange not longer matching!", uxrVar);
                        }
                        uyi.this.e(uxrVar, endpointReceiver);
                        return;
                    }
                    EndpointContext g = exchange2.g();
                    if (g == null) {
                        uyi.e.error("ignoring response from [{}]: {}, request pending to sent!", uxrVar.getSourceContext(), uxrVar);
                        uyi.this.e(uxrVar, endpointReceiver);
                        return;
                    }
                    try {
                    } catch (Exception e2) {
                        uyi.e.error("error receiving response from [{}]: {} for {}", uxrVar.getSourceContext(), uxrVar, exchange2, e2);
                    }
                    if (!uyi.this.c.isResponseRelatedToRequest(g, uxrVar.getSourceContext())) {
                        if (uyi.e.isDebugEnabled()) {
                            uyi.e.debug("ignoring potentially forged response from [{}]: {} for {} with non-matching endpoint context", uyi.this.c.toRelevantState(uxrVar.getSourceContext()), uxrVar, exchange2);
                        }
                        uyi.this.e(uxrVar, endpointReceiver);
                        return;
                    }
                    uxt i = exchange2.i();
                    if (exchange2.y() && uxrVar.g() && i.m()) {
                        uyi.e.debug("ignoring notify for pending cancel {}!", uxrVar);
                        uyi.this.e(uxrVar, endpointReceiver);
                    } else {
                        endpointReceiver.receiveResponse(exchange2, uxrVar);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(uxr uxrVar, EndpointReceiver endpointReceiver) {
        uxrVar.setCanceled(true);
        endpointReceiver.receiveResponse(null, uxrVar);
    }

    class e implements RemoveHandler {
        private e() {
        }

        @Override // org.eclipse.californium.core.network.RemoveHandler
        public void remove(Exchange exchange, uyc uycVar, uyf uyfVar) {
            if (uycVar != null) {
                uyi.this.exchangeStore.remove(uycVar, exchange);
            }
        }
    }
}

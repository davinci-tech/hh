package defpackage;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.BaseMatcher;
import org.eclipse.californium.core.network.EndpointReceiver;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.MessageExchangeStore;
import org.eclipse.californium.core.network.RemoveHandler;
import org.eclipse.californium.core.network.TokenGenerator;
import org.eclipse.californium.core.observe.NotificationListener;
import org.eclipse.californium.core.observe.ObservationStore;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.NetworkInterfacesUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class uyg extends BaseMatcher {
    private static final Logger b = vha.a((Class<?>) uyg.class);
    private final RemoveHandler c;
    private final EndpointContextMatcher e;

    public uyg(Configuration configuration, NotificationListener notificationListener, TokenGenerator tokenGenerator, ObservationStore observationStore, MessageExchangeStore messageExchangeStore, Executor executor, EndpointContextMatcher endpointContextMatcher) {
        super(configuration, notificationListener, tokenGenerator, observationStore, messageExchangeStore, endpointContextMatcher, executor);
        this.c = new a();
        this.e = endpointContextMatcher;
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void sendRequest(Exchange exchange) {
        uxt i = exchange.i();
        if (i.o() && exchange.o() == 0) {
            if (this.exchangeStore.assignMessageId(i) != -1) {
                registerObserve(i);
            } else {
                b.debug("message IDs exhausted, could not register outbound observe request for tracking");
                i.setSendError(new IllegalStateException("automatic message IDs exhausted"));
                return;
            }
        }
        try {
            if (this.exchangeStore.registerOutboundRequest(exchange)) {
                exchange.a(this.c);
                b.debug("tracking open request [{}, {}]", exchange.m(), exchange.k());
            } else {
                b.debug("message IDs exhausted, could not register outbound request for tracking");
                i.setSendError(new IllegalStateException("automatic message IDs exhausted"));
            }
        } catch (IllegalArgumentException e) {
            i.setSendError(e);
        }
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void sendResponse(Exchange exchange) {
        uxr f = exchange.f();
        f.e(exchange.i().getToken());
        if (f.getType() == CoAP.Type.CON) {
            exchange.ab();
            if (this.exchangeStore.registerOutboundResponse(exchange)) {
                b.debug("tracking open response [{}]", exchange.m());
                return;
            }
            f.setSendError(new IllegalStateException("automatic message IDs exhausted"));
        } else if (f.getType() == CoAP.Type.NON) {
            if (f.g()) {
                if (this.exchangeStore.registerOutboundResponse(exchange)) {
                    return;
                } else {
                    f.setSendError(new IllegalStateException("automatic message IDs exhausted"));
                }
            } else if (this.exchangeStore.assignMessageId(f) == -1) {
                f.setSendError(new IllegalStateException("automatic message IDs exhausted"));
            }
        }
        exchange.af();
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
        uxnVar.setToken(uxu.f17579a);
        if (uxnVar.getType() != CoAP.Type.RST || exchange == null) {
            return;
        }
        exchange.c();
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void receiveRequest(final uxt uxtVar, final EndpointReceiver endpointReceiver) {
        EndpointContext sourceContext;
        Object endpointIdentity = this.e.getEndpointIdentity(uxtVar.getSourceContext());
        uyf uyfVar = new uyf(uxtVar.getMID(), endpointIdentity);
        final Exchange exchange = new Exchange(uxtVar, endpointIdentity, Exchange.Origin.REMOTE, this.executor);
        final Exchange findPrevious = this.exchangeStore.findPrevious(uyfVar, exchange);
        boolean z = findPrevious != null;
        if (z) {
            EndpointContext sourceContext2 = uxtVar.getSourceContext();
            uxt i = findPrevious.i();
            if (findPrevious.w()) {
                sourceContext = i.getDestinationContext();
            } else {
                sourceContext = i.getSourceContext();
            }
            z = this.e.isToBeSent(sourceContext, sourceContext2);
            if (!z) {
                if (this.exchangeStore.replacePrevious(uyfVar, findPrevious, exchange)) {
                    b.debug("replaced request {} by new request {}!", i, uxtVar);
                } else {
                    b.warn("new request {} could not be registered! Deduplication disabled!", uxtVar);
                }
            } else if (i.h() || uxtVar.h()) {
                InetSocketAddress localAddress = uxtVar.getLocalAddress();
                InetSocketAddress localAddress2 = i.getLocalAddress();
                if (!NetworkInterfacesUtil.c(localAddress, localAddress2)) {
                    boolean d = vbj.d(uxtVar.getToken(), i.getToken());
                    long millis = TimeUnit.NANOSECONDS.toMillis(Math.abs(uxtVar.getNanoTimestamp() - i.getNanoTimestamp()));
                    if (!d) {
                        b.info("received different requests {} with same MID via different multicast groups ({} != {}) within {}ms!", uxtVar, vcb.b((SocketAddress) localAddress), vcb.b((SocketAddress) localAddress2), Long.valueOf(millis));
                    } else {
                        b.warn("received requests {} via different multicast groups ({} != {}) within {}ms!", uxtVar, vcb.b((SocketAddress) localAddress), vcb.b((SocketAddress) localAddress2), Long.valueOf(millis));
                    }
                }
            }
        }
        if (z && findPrevious != null) {
            b.trace("duplicate request: {}", uxtVar);
            uxtVar.setDuplicate(true);
            findPrevious.d(new Runnable() { // from class: uyg.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        endpointReceiver.receiveRequest(findPrevious, uxtVar);
                    } catch (RuntimeException e) {
                        uyg.b.warn("error receiving again request {}", uxtVar, e);
                        if (uxtVar.h()) {
                            return;
                        }
                        endpointReceiver.reject(uxtVar);
                    }
                }
            });
        } else {
            exchange.a(this.c);
            exchange.d(new Runnable() { // from class: uyg.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        endpointReceiver.receiveRequest(exchange, uxtVar);
                    } catch (RuntimeException e) {
                        uyg.b.warn("error receiving request {}", uxtVar, e);
                        if (uxtVar.h()) {
                            return;
                        }
                        endpointReceiver.reject(uxtVar);
                    }
                }
            });
        }
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void receiveResponse(final uxr uxrVar, final EndpointReceiver endpointReceiver) {
        final Object endpointIdentity = this.e.getEndpointIdentity(uxrVar.getSourceContext());
        final uyc keyToken = this.tokenGenerator.getKeyToken(uxrVar.getToken(), endpointIdentity);
        Logger logger = b;
        logger.trace("received response {} from {}", uxrVar, uxrVar.getSourceContext());
        Exchange exchange = this.exchangeStore.get(keyToken);
        if (exchange == null) {
            if (uxrVar.getType() != CoAP.Type.ACK) {
                final Exchange find = this.exchangeStore.find(new uyf(uxrVar.getMID(), endpointIdentity));
                if (find != null) {
                    find.d(new Runnable() { // from class: uyg.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (find.i().h()) {
                                uyg.b.debug("Ignore delayed response {} to multicast request {}", uxrVar, vcb.b((SocketAddress) find.i().getDestinationContext().getPeerAddress()));
                                uyg.this.b(uxrVar, endpointReceiver);
                                return;
                            }
                            try {
                            } catch (RuntimeException e) {
                                uyg.b.warn("error receiving response {} for {}", uxrVar, find, e);
                            }
                            if (uyg.this.e.isResponseRelatedToRequest(find.g(), uxrVar.getSourceContext())) {
                                uyg.b.trace("received response {} for already completed {}", uxrVar, find);
                                uxrVar.setDuplicate(true);
                                uxr f = find.f();
                                if (f != null) {
                                    uxrVar.setRejected(f.isRejected());
                                }
                                endpointReceiver.receiveResponse(find, uxrVar);
                                return;
                            }
                            uyg.b.debug("ignoring potentially forged response {} for already completed {}", uxrVar, find);
                            uyg.this.e(uxrVar, endpointReceiver);
                        }
                    });
                    return;
                }
            }
            exchange = matchNotifyResponse(uxrVar);
            if (exchange == null) {
                if (uxrVar.getType() == CoAP.Type.ACK) {
                    logger.trace("discarding by [{}] unmatchable piggy-backed response from [{}]: {}", keyToken, uxrVar.getSourceContext(), uxrVar);
                    b(uxrVar, endpointReceiver);
                    return;
                } else {
                    logger.trace("discarding by [{}] unmatchable response from [{}]: {}", keyToken, uxrVar.getSourceContext(), uxrVar);
                    e(uxrVar, endpointReceiver);
                    return;
                }
            }
        }
        final Exchange exchange2 = exchange;
        exchange.d(new Runnable() { // from class: uyg.4
            @Override // java.lang.Runnable
            public void run() {
                Exchange findPrevious;
                if ((!exchange2.y() || exchange2.p() != exchange2.i()) && uyg.this.exchangeStore.get(keyToken) != exchange2) {
                    if (uyg.this.running) {
                        uyg.b.debug("ignoring response {}, exchange not longer matching!", uxrVar);
                    }
                    uyg.this.b(uxrVar, endpointReceiver);
                    return;
                }
                EndpointContext g = exchange2.g();
                if (g == null) {
                    uyg.b.debug("ignoring response {}, request pending to sent!", uxrVar);
                    uyg.this.b(uxrVar, endpointReceiver);
                    return;
                }
                try {
                } catch (RuntimeException e) {
                    uyg.b.warn("error receiving response {} for {}", uxrVar, exchange2, e);
                }
                if (!uyg.this.e.isResponseRelatedToRequest(g, uxrVar.getSourceContext())) {
                    uyg.b.debug("ignoring potentially forged response for token {} with non-matching endpoint context", keyToken);
                    uyg.this.e(uxrVar, endpointReceiver);
                    return;
                }
                CoAP.Type type = uxrVar.getType();
                uxt i = exchange2.i();
                int mid = i.getMID();
                if (i.h()) {
                    if (type != CoAP.Type.NON) {
                        uyg.b.debug("ignoring response of type {} for multicast request with token [{}], from {}", uxrVar.getType(), uxrVar.getTokenString(), vcb.b((SocketAddress) uxrVar.getSourceContext().getPeerAddress()));
                        uyg.this.b(uxrVar, endpointReceiver);
                        return;
                    }
                } else if (type == CoAP.Type.ACK && mid != uxrVar.getMID()) {
                    uyg.b.debug("ignoring ACK, possible MID reuse before lifetime end for token {}, expected MID {} but received {}", uxrVar.getTokenString(), Integer.valueOf(mid), Integer.valueOf(uxrVar.getMID()));
                    uyg.this.b(uxrVar, endpointReceiver);
                    return;
                }
                if (type != CoAP.Type.ACK && !exchange2.y() && uxrVar.g() && i.m()) {
                    uyg.b.debug("ignoring notify for pending cancel {}!", uxrVar);
                    uyg.this.b(uxrVar, endpointReceiver);
                    return;
                }
                if ((type == CoAP.Type.CON || type == CoAP.Type.NON) && (findPrevious = uyg.this.exchangeStore.findPrevious(new uyf(uxrVar.getMID(), endpointIdentity), exchange2)) != null) {
                    uyg.b.trace("received duplicate response for open {}: {}", exchange2, uxrVar);
                    uxrVar.setDuplicate(true);
                    uxr f = findPrevious.f();
                    if (f != null) {
                        uxrVar.setRejected(f.isRejected());
                    }
                }
                endpointReceiver.receiveResponse(exchange2, uxrVar);
            }
        });
    }

    @Override // org.eclipse.californium.core.network.Matcher
    public void receiveEmptyMessage(final uxn uxnVar, final EndpointReceiver endpointReceiver) {
        uyf uyfVar;
        EndpointContext sourceContext = uxnVar.getSourceContext();
        Object endpointIdentity = this.e.getEndpointIdentity(sourceContext);
        uyf uyfVar2 = new uyf(uxnVar.getMID(), endpointIdentity);
        Exchange exchange = this.exchangeStore.get(uyfVar2);
        final uyf uyfVar3 = (exchange != null || endpointIdentity == sourceContext.getPeerAddress() || (exchange = this.exchangeStore.get((uyfVar = new uyf(uxnVar.getMID(), sourceContext.getPeerAddress())))) == null) ? uyfVar2 : uyfVar;
        if (exchange == null) {
            b.debug("ignoring {} message unmatchable by {}", uxnVar.getType(), uyfVar3);
            c(uxnVar, endpointReceiver);
        } else {
            final Exchange exchange2 = exchange;
            exchange.d(new Runnable() { // from class: uyg.1
                @Override // java.lang.Runnable
                public void run() {
                    if (exchange2.i().h()) {
                        uyg.b.debug("ignoring {} message for multicast request {}", uxnVar.getType(), uyfVar3);
                        uyg.this.c(uxnVar, endpointReceiver);
                        return;
                    }
                    if (uyg.this.exchangeStore.get(uyfVar3) == exchange2) {
                        try {
                        } catch (RuntimeException e) {
                            uyg.b.warn("error receiving {} message for {}", uxnVar.getType(), exchange2, e);
                        }
                        if (!uyg.this.e.isResponseRelatedToRequest(exchange2.g(), uxnVar.getSourceContext())) {
                            uyg.b.debug("ignoring potentially forged {} reply for {} with non-matching endpoint context", uxnVar.getType(), uyfVar3);
                            uyg.this.c(uxnVar, endpointReceiver);
                            return;
                        } else {
                            uyg.this.exchangeStore.remove(uyfVar3, exchange2);
                            uyg.b.debug("received expected {} reply for {}", uxnVar.getType(), uyfVar3);
                            endpointReceiver.receiveEmptyMessage(exchange2, uxnVar);
                            return;
                        }
                    }
                    if (uyg.this.running) {
                        uyg.b.debug("ignoring {} message not longer matching by {}", uxnVar.getType(), uyfVar3);
                    }
                    uyg.this.c(uxnVar, endpointReceiver);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(uxr uxrVar, EndpointReceiver endpointReceiver) {
        if (uxrVar.getType() != CoAP.Type.ACK && uxrVar.hasMID()) {
            endpointReceiver.reject(uxrVar);
        }
        b(uxrVar, endpointReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(uxr uxrVar, EndpointReceiver endpointReceiver) {
        uxrVar.setCanceled(true);
        endpointReceiver.receiveResponse(null, uxrVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(uxn uxnVar, EndpointReceiver endpointReceiver) {
        uxnVar.setCanceled(true);
        endpointReceiver.receiveEmptyMessage(null, uxnVar);
    }

    class a implements RemoveHandler {
        private a() {
        }

        @Override // org.eclipse.californium.core.network.RemoveHandler
        public void remove(Exchange exchange, uyc uycVar, uyf uyfVar) {
            if (uycVar != null) {
                uyg.this.exchangeStore.remove(uycVar, exchange);
            }
            if (uyfVar != null) {
                uyg.this.exchangeStore.remove(uyfVar, exchange);
            }
        }
    }
}

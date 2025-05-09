package defpackage;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.MessageExchangeStore;
import org.eclipse.californium.core.network.MessageIdProvider;
import org.eclipse.californium.core.network.TokenGenerator;
import org.eclipse.californium.core.network.deduplication.Deduplicator;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uxw implements MessageExchangeStore {
    private static final Logger b;
    private static final Logger e;

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f17581a;
    private final Configuration c;
    private volatile Deduplicator d;
    private ScheduledExecutorService g;
    private volatile MessageIdProvider j;
    private final TokenGenerator l;
    private final String m;
    private ScheduledFuture<?> n;
    private final ConcurrentMap<uyf, Exchange> f = new ConcurrentHashMap();
    private final ConcurrentMap<uyc, Exchange> h = new ConcurrentHashMap();
    private volatile boolean i = false;

    static {
        Logger a2 = vha.a((Class<?>) uxw.class);
        e = a2;
        b = vha.d(a2.getName() + ".health");
    }

    public uxw(String str, Configuration configuration, TokenGenerator tokenGenerator) {
        if (configuration == null) {
            throw new NullPointerException("Configuration must not be null");
        }
        if (tokenGenerator == null) {
            throw new NullPointerException("TokenProvider must not be null");
        }
        this.l = tokenGenerator;
        this.c = configuration;
        this.m = vcb.h(str);
        e.debug("{}using TokenProvider {}", str, tokenGenerator.getClass().getName());
    }

    private void e() {
        ScheduledExecutorService scheduledExecutorService;
        long longValue = this.c.a(vbc.b, TimeUnit.MILLISECONDS).longValue();
        if (longValue <= 0 || !b.isDebugEnabled() || (scheduledExecutorService = this.g) == null) {
            return;
        }
        this.n = scheduledExecutorService.scheduleAtFixedRate(new Runnable() { // from class: uxw.3
            @Override // java.lang.Runnable
            public void run() {
                if (uxw.this.f17581a) {
                    uxw.this.e(5);
                }
            }
        }, longValue, longValue, TimeUnit.MILLISECONDS);
    }

    private String d() {
        return this.m + "MessageExchangeStore contents: " + this.f.size() + " exchanges by MID, " + this.h.size() + " exchanges by token, " + this.d.size() + " MIDs.";
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public void setExecutor(ScheduledExecutorService scheduledExecutorService) {
        synchronized (this) {
            if (this.i) {
                throw new IllegalStateException("Cannot set messageIdProvider when store is already started");
            }
            this.g = scheduledExecutorService;
        }
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public boolean isEmpty() {
        return this.f.isEmpty() && this.h.isEmpty() && this.d.isEmpty();
    }

    public String toString() {
        return d();
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public int assignMessageId(Message message) {
        int mid = message.getMID();
        if (-1 != mid) {
            return mid;
        }
        InetSocketAddress peerAddress = message.getDestinationContext().getPeerAddress();
        try {
            mid = this.j.getNextMessageId(peerAddress);
            message.setMID(mid);
            return mid;
        } catch (IllegalStateException e2) {
            e.debug("{}cannot send message {}-{} to {}, {}", this.m, message.getType(), CoAP.j(message.getRawCode()), vcb.b((SocketAddress) peerAddress), e2.getMessage());
            return mid;
        }
    }

    private uyf b(Exchange exchange, Message message) {
        uyf uyfVar;
        this.f17581a = true;
        exchange.b(message);
        int mid = message.getMID();
        if (-1 == mid) {
            int assignMessageId = assignMessageId(message);
            if (-1 != assignMessageId) {
                uyfVar = new uyf(assignMessageId, exchange.q());
                if (this.f.putIfAbsent(uyfVar, exchange) != null) {
                    throw new IllegalArgumentException(String.format("generated mid [%d] already in use, cannot register %s", Integer.valueOf(assignMessageId), exchange));
                }
                e.debug("{}{} added with generated mid {}, {}", this.m, exchange, uyfVar, message);
            } else {
                uyfVar = null;
            }
        } else {
            uyfVar = new uyf(mid, exchange.q());
            Exchange putIfAbsent = this.f.putIfAbsent(uyfVar, exchange);
            if (putIfAbsent == null) {
                e.debug("{}{} added with {}, {}", this.m, exchange, uyfVar, message);
            } else {
                if (putIfAbsent != exchange) {
                    throw new IllegalArgumentException(String.format("mid [%d] already in use, cannot register %s", Integer.valueOf(mid), exchange));
                }
                if (exchange.o() == 0) {
                    throw new IllegalArgumentException(String.format("message with already registered mid [%d] is not a re-transmission, cannot register %s", Integer.valueOf(mid), exchange));
                }
            }
        }
        if (uyfVar != null) {
            exchange.c(uyfVar);
        }
        return uyfVar;
    }

    private void d(Exchange exchange) {
        uyc keyToken;
        this.f17581a = true;
        uxt i = exchange.i();
        exchange.b((Object) i);
        uxu token = i.getToken();
        if (token == null) {
            TokenGenerator.Scope scope = i.h() ? TokenGenerator.Scope.SHORT_TERM : TokenGenerator.Scope.SHORT_TERM_CLIENT_LOCAL;
            do {
                uxu createToken = this.l.createToken(scope);
                i.setToken(createToken);
                keyToken = this.l.getKeyToken(createToken, exchange.q());
            } while (this.h.putIfAbsent(keyToken, exchange) != null);
            e.debug("{}{} added with generated token {}, {}", this.m, exchange, keyToken, i);
        } else {
            if (token.d() && i.e() == null) {
                return;
            }
            keyToken = this.l.getKeyToken(token, exchange.q());
            Exchange put = this.h.put(keyToken, exchange);
            if (put == null) {
                uxh j = i.getOptions().j();
                if (j != null) {
                    e.debug("{}block2 {} for block {} add with token {}", this.m, exchange, Integer.valueOf(j.b()), keyToken);
                } else {
                    e.debug("{}{} added with token {}, {}", this.m, exchange, keyToken, i);
                }
            } else if (put != exchange) {
                if (exchange.o() == 0 && !i.getOptions().ai() && !i.getOptions().ae() && !i.getOptions().an()) {
                    e.warn("{}{} with manual token overrides existing {} with open request: {}", this.m, exchange, put, keyToken);
                } else {
                    e.debug("{}{} replaced with token {}, {}", this.m, exchange, keyToken, i);
                }
            } else {
                e.debug("{}{} keep for {}, {}", this.m, exchange, keyToken, i);
            }
        }
        if (keyToken != null) {
            exchange.e(keyToken);
        }
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public boolean registerOutboundRequest(Exchange exchange) {
        if (exchange == null) {
            throw new NullPointerException("exchange must not be null");
        }
        if (exchange.i() == null) {
            throw new IllegalArgumentException("exchange does not contain a request");
        }
        uxt i = exchange.i();
        if (b(exchange, i) == null) {
            return false;
        }
        d(exchange);
        if (exchange.i() == i) {
            return true;
        }
        throw new ConcurrentModificationException("Current request modified!");
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public boolean registerOutboundRequestWithTokenOnly(Exchange exchange) {
        if (exchange == null) {
            throw new NullPointerException("exchange must not be null");
        }
        if (exchange.i() == null) {
            throw new IllegalArgumentException("exchange does not contain a request");
        }
        uxt i = exchange.i();
        d(exchange);
        if (exchange.i() == i) {
            return true;
        }
        throw new ConcurrentModificationException("Current request modified!");
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public void remove(uyc uycVar, Exchange exchange) {
        if (this.h.remove(uycVar, exchange)) {
            e.debug("{}removing {} for token {}", this.m, exchange, uycVar);
        }
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public Exchange remove(uyf uyfVar, Exchange exchange) {
        if (exchange == null) {
            exchange = this.f.remove(uyfVar);
        } else if (!this.f.remove(uyfVar, exchange)) {
            exchange = null;
        }
        if (exchange != null) {
            e.debug("{}removing {} for MID {}", this.m, exchange, uyfVar);
        }
        return exchange;
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public Exchange get(uyc uycVar) {
        if (uycVar == null) {
            return null;
        }
        return this.h.get(uycVar);
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public Exchange get(uyf uyfVar) {
        if (uyfVar == null) {
            return null;
        }
        return this.f.get(uyfVar);
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public boolean registerOutboundResponse(Exchange exchange) {
        if (exchange == null) {
            throw new NullPointerException("exchange must not be null");
        }
        if (exchange.f() == null) {
            throw new IllegalArgumentException("exchange does not contain a response");
        }
        uxr f = exchange.f();
        if (b(exchange, f) == null) {
            return false;
        }
        if (exchange.f() == f) {
            return true;
        }
        throw new ConcurrentModificationException("Current response modified!");
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public void start() {
        synchronized (this) {
            if (!this.i) {
                e();
                if (this.d == null) {
                    this.d = uyk.b().e(this.c);
                }
                this.d.setExecutor(this.g);
                this.d.start();
                if (this.j == null) {
                    e.debug("{}no MessageIdProvider set, using default {}", this.m, uyd.class.getName());
                    this.j = new uyd(this.c);
                }
                this.i = true;
            }
        }
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public void stop() {
        synchronized (this) {
            if (this.i) {
                this.i = false;
                Iterator<Exchange> it = this.f.values().iterator();
                while (it.hasNext()) {
                    it.next().p().setCanceled(true);
                }
                ScheduledFuture<?> scheduledFuture = this.n;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                    this.n = null;
                }
                this.d.stop();
                this.f.clear();
                this.h.clear();
            }
        }
    }

    public void e(int i) {
        Logger logger = b;
        if (logger.isDebugEnabled()) {
            logger.debug(d());
            if (i > 0) {
                if (!this.f.isEmpty()) {
                    a(i, this.f.entrySet());
                }
                if (this.h.isEmpty()) {
                    return;
                }
                a(i, this.h.entrySet());
            }
        }
    }

    private <K> void a(int i, Set<Map.Entry<K, Exchange>> set) {
        for (Map.Entry<K, Exchange> entry : set) {
            Exchange value = entry.getValue();
            uxt p = value.p();
            uxt i2 = value.i();
            String str = value.ac() ? "/pending" : "";
            if (p != null && p != i2 && !p.getToken().equals(i2.getToken())) {
                b.debug("  {}, {}, retransmission {}{}, org {}, {}, {}", entry.getKey(), value, Integer.valueOf(value.o()), str, p.getToken(), i2, value.f());
            } else {
                b.debug("  {}, {}, retransmission {}{}, {}{}, {}", entry.getKey(), value, Integer.valueOf(value.o()), str, p == null ? "(missing origin request) " : "", i2, value.f());
            }
            Throwable j = value.j();
            if (j != null) {
                b.trace("  ", j);
            }
            i--;
            if (i <= 0) {
                return;
            }
        }
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public Exchange findPrevious(uyf uyfVar, Exchange exchange) {
        return this.d.findPrevious(uyfVar, exchange);
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public boolean replacePrevious(uyf uyfVar, Exchange exchange, Exchange exchange2) {
        return this.d.replacePrevious(uyfVar, exchange, exchange2);
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public Exchange find(uyf uyfVar) {
        return this.d.find(uyfVar);
    }

    @Override // org.eclipse.californium.core.network.MessageExchangeStore
    public List<Exchange> findByToken(uxu uxuVar) {
        uxt p;
        ArrayList arrayList = new ArrayList();
        if (uxuVar != null) {
            if (this.l.getScope(uxuVar) == TokenGenerator.Scope.SHORT_TERM_CLIENT_LOCAL) {
                throw new IllegalArgumentException("token must not have client-local scope!");
            }
            for (Map.Entry<uyc, Exchange> entry : this.h.entrySet()) {
                if (entry.getValue().w() && (p = entry.getValue().p()) != null && uxuVar.equals(p.getToken())) {
                    arrayList.add(entry.getValue());
                }
            }
        }
        return arrayList;
    }
}

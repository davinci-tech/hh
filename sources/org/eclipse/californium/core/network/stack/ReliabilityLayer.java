package org.eclipse.californium.core.network.stack;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.uzj;
import defpackage.vaa;
import defpackage.vha;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.coap.MessageObserverAdapter;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class ReliabilityLayer extends AbstractLayer {
    public static final Logger LOGGER = vha.a((Class<?>) ReliabilityLayer.class);
    protected final uzj defaultReliabilityLayerParameters;
    private final int maxLeisureMillis;
    private final Random rand = new Random();
    private final AtomicInteger counter = new AtomicInteger();

    public ReliabilityLayer(Configuration configuration) {
        uzj c = uzj.c().a(configuration).c();
        this.defaultReliabilityLayerParameters = c;
        int d = configuration.d(CoapConfig.q, TimeUnit.MILLISECONDS);
        this.maxLeisureMillis = d;
        Logger logger = LOGGER;
        logger.trace("Max. leisure for multicast server={}ms", Integer.valueOf(d));
        logger.trace("ReliabilityLayer uses ACK_TIMEOUT={}ms, MAX_ACK_TIMEOUT={}ms, ACK_RANDOM_FACTOR={}, and ACK_TIMEOUT_SCALE={} as default", Integer.valueOf(c.e()), Integer.valueOf(c.d()), Float.valueOf(c.a()), Float.valueOf(c.b()));
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        LOGGER.debug("{} send request", exchange);
        prepareRequest(exchange, uxtVar);
        lower().sendRequest(exchange, uxtVar);
    }

    protected void prepareRequest(Exchange exchange, uxt uxtVar) {
        if (uxtVar.getType() == null) {
            uxtVar.setType(CoAP.Type.CON);
        }
        if (uxtVar.getType() == CoAP.Type.CON) {
            LOGGER.debug("{} prepare retransmission for {}", exchange, uxtVar);
            prepareRetransmission(exchange, new RetransmissionTask(exchange, uxtVar) { // from class: org.eclipse.californium.core.network.stack.ReliabilityLayer.3
                @Override // org.eclipse.californium.core.network.stack.ReliabilityLayer.RetransmissionTask
                public void retransmit() {
                    uxt uxtVar2 = (uxt) this.message;
                    if (uxtVar2.getEffectiveDestinationContext() != uxtVar2.getDestinationContext()) {
                        this.exchange.aa();
                    }
                    ReliabilityLayer.LOGGER.debug("{} send request, failed transmissions: {}", this.exchange, Integer.valueOf(this.exchange.o()));
                    ReliabilityLayer.this.updateRetransmissionTimeout(this.exchange, getReliabilityLayerParameters());
                    ReliabilityLayer.this.lower().sendRequest(this.exchange, uxtVar2);
                }
            });
        }
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendResponse(Exchange exchange, uxr uxrVar) {
        int nextInt;
        LOGGER.debug("{} send response {}", exchange, uxrVar);
        prepareResponse(exchange, uxrVar);
        if (exchange.i().h() && uxrVar.getType() == CoAP.Type.NON) {
            synchronized (this.rand) {
                nextInt = this.rand.nextInt(this.maxLeisureMillis);
            }
            exchange.c(this.executor.schedule(new e(exchange, uxrVar), nextInt, TimeUnit.MILLISECONDS));
            return;
        }
        lower().sendResponse(exchange, uxrVar);
    }

    protected void prepareResponse(Exchange exchange, uxr uxrVar) {
        uxt i = exchange.i();
        CoAP.Type type = uxrVar.getType();
        if (type == null) {
            CoAP.Type type2 = i.getType();
            if (i.acknowledge()) {
                uxrVar.setType(CoAP.Type.ACK);
            } else {
                uxrVar.setType(type2);
            }
            CoAP.Type type3 = uxrVar.getType();
            LOGGER.trace("{} set response type to {} (request was {})", exchange, type3, type2);
            type = type3;
        } else if (type == CoAP.Type.RST) {
            i.setRejected(true);
        } else if (type == CoAP.Type.ACK) {
            i.acknowledge();
        } else {
            i.setAcknowledged(true);
        }
        if (type == CoAP.Type.ACK || type == CoAP.Type.RST) {
            uxrVar.setMID(i.getMID());
        }
        if (type == CoAP.Type.CON) {
            LOGGER.debug("{} prepare retransmission for {}", exchange, uxrVar);
            prepareRetransmission(exchange, new RetransmissionTask(exchange, uxrVar) { // from class: org.eclipse.californium.core.network.stack.ReliabilityLayer.5
                @Override // org.eclipse.californium.core.network.stack.ReliabilityLayer.RetransmissionTask
                public void retransmit() {
                    uxr uxrVar2 = (uxr) this.message;
                    ReliabilityLayer.LOGGER.debug("{} send response {}, failed transmissions: {}", this.exchange, uxrVar2, Integer.valueOf(this.exchange.o()));
                    ReliabilityLayer.this.updateRetransmissionTimeout(this.exchange, getReliabilityLayerParameters());
                    ReliabilityLayer.this.lower().sendResponse(this.exchange, uxrVar2);
                }
            });
        }
    }

    private void prepareRetransmission(Exchange exchange, RetransmissionTask retransmissionTask) {
        if (this.executor.isShutdown()) {
            LOGGER.info("Endpoint is being destroyed: skipping retransmission");
            return;
        }
        exchange.c((ScheduledFuture<?>) null);
        updateRetransmissionTimeout(exchange, retransmissionTask.getReliabilityLayerParameters());
        retransmissionTask.message.addMessageObserver(retransmissionTask);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveRequest(Exchange exchange, uxt uxtVar) {
        if (uxtVar.isDuplicate()) {
            long s = exchange.s();
            if (s == 0 || s - uxtVar.getNanoTimestamp() > 0) {
                LOGGER.debug("{}: {} duplicate request {}, server sent response delayed, ignore request", Integer.valueOf(this.counter.incrementAndGet()), exchange, uxtVar);
                return;
            }
            exchange.ad();
            uxt i = exchange.i();
            uxr f = exchange.f();
            if (f != null) {
                CoAP.Type type = f.getType();
                if (type == CoAP.Type.NON || type == CoAP.Type.CON) {
                    if (uxtVar.acknowledge()) {
                        sendEmptyMessage(exchange, uxn.d(uxtVar));
                    }
                    if (type == CoAP.Type.CON) {
                        if (f.isAcknowledged()) {
                            LOGGER.debug("{} request duplicate: ignore, response already acknowledged!", exchange);
                            return;
                        }
                        LOGGER.debug("{} request duplicate: retransmit response, failed: {}, response: {}", exchange, Integer.valueOf(exchange.x()), f);
                        f.retransmitting();
                        sendResponse(exchange, f);
                        return;
                    }
                    if (f.g()) {
                        exchange.x();
                    }
                }
                LOGGER.debug("{} respond with the current response to the duplicate request", exchange);
                lower().sendResponse(exchange, f);
                return;
            }
            if (i.isAcknowledged()) {
                LOGGER.debug("{} duplicate request was acknowledged but no response computed yet. Retransmit ACK", exchange);
                sendEmptyMessage(exchange, uxn.d(uxtVar));
                return;
            } else if (i.isRejected()) {
                LOGGER.debug("{} duplicate request was rejected. Reject again", exchange);
                sendEmptyMessage(exchange, uxn.b(uxtVar));
                return;
            } else {
                LOGGER.debug("{} server has not yet decided what to do with the request. We ignore the duplicate.", exchange);
                return;
            }
        }
        exchange.c(uxtVar);
        upper().receiveRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        if (processResponse(exchange, uxrVar)) {
            upper().receiveResponse(exchange, uxrVar);
        }
    }

    protected boolean processResponse(Exchange exchange, uxr uxrVar) {
        uxn d;
        exchange.c((ScheduledFuture<?>) null);
        if (uxrVar.getType() == CoAP.Type.CON) {
            if (uxrVar.isDuplicate()) {
                long s = exchange.s();
                if (s == 0 || s - uxrVar.getNanoTimestamp() > 0) {
                    LOGGER.debug("{}: {} duplicate response {}, server sent ACK delayed, ignore response", Integer.valueOf(this.counter.incrementAndGet()), exchange, uxrVar);
                    return false;
                }
                if (uxrVar.isRejected()) {
                    LOGGER.debug("{} reject duplicate CON response, request canceled.", exchange);
                    d = uxn.b(uxrVar);
                    uxrVar.setRejected(true);
                } else {
                    LOGGER.debug("{} acknowledging duplicate CON response", exchange);
                    d = uxn.d(uxrVar);
                    uxrVar.setAcknowledged(true);
                }
            } else if (exchange.p().isCanceled()) {
                LOGGER.debug("{} reject CON response, request canceled.", exchange);
                d = uxn.b(uxrVar);
                uxrVar.setRejected(true);
            } else {
                LOGGER.debug("{} acknowledging CON response", exchange);
                d = uxn.d(uxrVar);
                uxrVar.setAcknowledged(true);
            }
            sendEmptyMessage(exchange, d);
        }
        if (uxrVar.isDuplicate()) {
            if (uxrVar.getType() != CoAP.Type.CON) {
                LOGGER.debug("{} ignoring duplicate response", exchange);
            }
            return false;
        }
        exchange.i().setAcknowledged(true);
        exchange.e(uxrVar);
        return true;
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
        if (processEmptyMessage(exchange, uxnVar)) {
            upper().receiveEmptyMessage(exchange, uxnVar);
        }
    }

    protected boolean processEmptyMessage(Exchange exchange, uxn uxnVar) {
        Message f;
        String str;
        exchange.c((ScheduledFuture<?>) null);
        if (exchange.w()) {
            f = exchange.i();
            str = "request";
        } else {
            f = exchange.f();
            str = TrackConstants$Opers.RESPONSE;
        }
        int size = f.getMessageObservers().size();
        if (uxnVar.getType() == CoAP.Type.ACK) {
            LOGGER.debug("{} acknowledge {} for {} {} ({} msg observer)", exchange, uxnVar, str, f, Integer.valueOf(size));
            f.acknowledge();
        } else if (uxnVar.getType() == CoAP.Type.RST) {
            LOGGER.debug("{} reject {} for {} {} ({} msg observer)", exchange, uxnVar, str, f, Integer.valueOf(size));
            f.setRejected(true);
        } else {
            LOGGER.warn("{} received empty message that is neither ACK nor RST: {}", exchange, uxnVar);
            return false;
        }
        return true;
    }

    protected void updateRetransmissionTimeout(Exchange exchange, uzj uzjVar) {
        int u;
        if (exchange.o() == 0) {
            exchange.c(uzjVar.b());
            u = getRandomTimeout(uzjVar.e(), uzjVar.a());
        } else {
            u = (int) (exchange.u() * exchange.h());
        }
        exchange.b(Math.min(uzjVar.d(), u));
    }

    protected int getRandomTimeout(int i, float f) {
        int nextInt;
        if (f <= 1.0d) {
            return i;
        }
        int i2 = (int) (i * f);
        synchronized (this.rand) {
            nextInt = this.rand.nextInt((i2 - i) + 1);
        }
        return i + nextInt;
    }

    class e implements Runnable {
        protected final Exchange c;
        protected final uxr e;

        private e(Exchange exchange, uxr uxrVar) {
            this.c = exchange;
            this.e = uxrVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.c.d(new Runnable() { // from class: org.eclipse.californium.core.network.stack.ReliabilityLayer.e.3
                @Override // java.lang.Runnable
                public void run() {
                    ReliabilityLayer.this.lower().sendResponse(e.this.c, e.this.e);
                }
            });
        }
    }

    protected abstract class RetransmissionTask extends MessageObserverAdapter implements Runnable {
        protected final Exchange exchange;
        protected final Message message;

        public abstract void retransmit();

        private RetransmissionTask(Exchange exchange, Message message) {
            super(true);
            this.exchange = exchange;
            this.message = message;
        }

        public uzj getReliabilityLayerParameters() {
            uzj reliabilityLayerParameters = this.message.getReliabilityLayerParameters();
            return reliabilityLayerParameters == null ? ReliabilityLayer.this.defaultReliabilityLayerParameters : reliabilityLayerParameters;
        }

        private boolean isInTransit() {
            Message f;
            if (!this.message.isAcknowledged() && !this.exchange.v()) {
                if (this.exchange.w()) {
                    f = this.exchange.i();
                } else {
                    f = this.exchange.f();
                }
                if (this.message == f) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
        public void onSent(boolean z) {
            if (isInTransit()) {
                this.exchange.d(new Runnable() { // from class: org.eclipse.californium.core.network.stack.ReliabilityLayer.RetransmissionTask.2
                    @Override // java.lang.Runnable
                    public void run() {
                        RetransmissionTask.this.startTimer();
                    }
                });
            }
        }

        public void startTimer() {
            if (isInTransit()) {
                this.exchange.c(ReliabilityLayer.this.executor.schedule(this, this.exchange.h(), TimeUnit.MILLISECONDS));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.exchange.d(new Runnable() { // from class: org.eclipse.californium.core.network.stack.ReliabilityLayer.RetransmissionTask.3
                @Override // java.lang.Runnable
                public void run() {
                    RetransmissionTask.this.retry();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void retry() {
            Message f;
            try {
                this.exchange.c((ScheduledFuture<?>) null);
                if (this.exchange.v()) {
                    ReliabilityLayer.LOGGER.debug("Timeout: for {}, {}", this.exchange, this.message);
                    return;
                }
                if (this.exchange.w()) {
                    f = this.exchange.i();
                } else {
                    f = this.exchange.f();
                }
                Message message = this.message;
                if (message != f) {
                    ReliabilityLayer.LOGGER.debug("Timeout: for {}, message has changed!", this.exchange);
                    return;
                }
                if (message.isAcknowledged()) {
                    ReliabilityLayer.LOGGER.trace("Timeout: for {} message already acknowledged, cancel retransmission of {}", this.exchange, this.message);
                    return;
                }
                if (this.message.isRejected()) {
                    ReliabilityLayer.LOGGER.trace("Timeout: for {} message already rejected, cancel retransmission of {}", this.exchange, this.message);
                    return;
                }
                if (this.message.isCanceled()) {
                    ReliabilityLayer.LOGGER.trace("Timeout: for {}, {} is canceled, do not retransmit", this.exchange, this.message);
                    return;
                }
                int x = this.exchange.x();
                if (x == 1) {
                    this.message.setEffectiveDestinationContext(vaa.b(this.message.getDestinationContext(), this.exchange.g()));
                }
                ReliabilityLayer.LOGGER.debug("Timeout: for {} retry {} of {}", this.exchange, Integer.valueOf(x), this.message);
                int f2 = getReliabilityLayerParameters().f();
                if (x <= f2) {
                    ReliabilityLayer.LOGGER.debug("Timeout: for {} retransmit message, failed-count: {}, message: {}", this.exchange, Integer.valueOf(x), this.message);
                    this.message.retransmitting();
                    if (this.message.isCanceled()) {
                        ReliabilityLayer.LOGGER.trace("Timeout: for {}, {} got canceled, do not retransmit", this.exchange, this.message);
                        return;
                    } else if (this.exchange.v()) {
                        ReliabilityLayer.LOGGER.debug("Timeout: for {}, {} got completed, do not retransmit", this.exchange, this.message);
                        return;
                    } else {
                        retransmit();
                        return;
                    }
                }
                ReliabilityLayer.LOGGER.debug("Timeout: for {} retransmission limit {} reached, exchange failed with timeout {} ms, message: {}", this.exchange, Integer.valueOf(f2), Integer.valueOf(this.exchange.h()), this.message);
                this.exchange.d(this.message);
            } catch (Exception e) {
                ReliabilityLayer.LOGGER.error("Exception for {} in MessageObserver: {}", this.exchange, e.getMessage(), e);
            }
        }
    }
}

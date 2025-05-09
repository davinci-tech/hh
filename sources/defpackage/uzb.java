package defpackage;

import java.net.SocketAddress;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MessageObserverAdapter;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.AbstractLayer;
import org.eclipse.californium.core.observe.ObserveRelation;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uzb extends AbstractLayer {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17611a = vha.a((Class<?>) uzb.class);

    public uzb(Configuration configuration) {
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendResponse(Exchange exchange, uxr uxrVar) {
        CoAP.Type type;
        ObserveRelation r = exchange.r();
        ObserveRelation.State c2 = ObserveRelation.c(r, uxrVar);
        if (c2 == ObserveRelation.State.INIT || c2 == ObserveRelation.State.ESTABILSHED) {
            if (uxrVar.getType() == null) {
                if (c2 == ObserveRelation.State.INIT) {
                    uxt i = exchange.i();
                    if (i.acknowledge()) {
                        uxrVar.setType(CoAP.Type.ACK);
                    } else if (i.isConfirmable()) {
                        uxrVar.setType(r.g());
                    } else {
                        uxrVar.setType(CoAP.Type.NON);
                    }
                    f17611a.trace("{} set initial notify type to {}", exchange, uxrVar.getType());
                } else if (c2 == ObserveRelation.State.ESTABILSHED) {
                    if (uxrVar.i()) {
                        type = r.g();
                    } else {
                        type = CoAP.Type.CON;
                    }
                    uxrVar.setType(type);
                    f17611a.trace("{} set notify type to {}", exchange, uxrVar.getType());
                }
            }
            if (uxrVar.getType() == CoAP.Type.CON) {
                a(exchange, uxrVar);
            }
            if (r.b(uxrVar)) {
                f17611a.debug("a former notification is still in transit. Postponing {}", uxrVar);
                return;
            }
        } else if (r != null) {
            if (exchange.v()) {
                f17611a.debug("drop notification {}, relation was canceled!", uxrVar);
                uxrVar.setCanceled(true);
                return;
            } else {
                CoAP.Type type2 = uxrVar.getType();
                if (type2 == null || type2 == CoAP.Type.CON) {
                    uxrVar.addMessageObserver(new uyw(exchange));
                }
            }
        }
        lower().sendResponse(exchange, uxrVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        if (uxrVar.g() && exchange.p().isCanceled()) {
            f17611a.debug("rejecting notification for canceled Exchange");
            sendEmptyMessage(exchange, uxn.b(uxrVar));
        } else {
            upper().receiveResponse(exchange, uxrVar);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
        ObserveRelation r = exchange.r();
        if (r != null && uxnVar.getType() == CoAP.Type.RST && exchange.n() == Exchange.Origin.REMOTE && exchange.f().g()) {
            r.l();
        }
        upper().receiveEmptyMessage(exchange, uxnVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Exchange exchange, uxr uxrVar) {
        uxrVar.addMessageObserver(new c(exchange, uxrVar));
    }

    class c extends MessageObserverAdapter {
        private final uxr d;
        private final Exchange e;

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
        public boolean isInternal() {
            return true;
        }

        public c(Exchange exchange, uxr uxrVar) {
            this.e = exchange;
            this.d = uxrVar;
        }

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
        public void onAcknowledgement() {
            this.e.d(new Runnable() { // from class: uzb.c.2
                @Override // java.lang.Runnable
                public void run() {
                    ObserveRelation r = c.this.e.r();
                    boolean h = r.h();
                    uxr e = r.e(c.this.d, true);
                    if (e != null) {
                        uzb.f17611a.debug("notification has been acknowledged, send the next one");
                        if (!h) {
                            uzb.super.sendResponse(c.this.e, e);
                        } else {
                            e.cancel();
                        }
                    }
                }
            });
        }

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
        public void onRetransmission() {
            ObserveRelation r = this.e.r();
            boolean h = r.h();
            uxr e = r.e(this.d, false);
            if (h) {
                this.d.cancel();
                if (e != null) {
                    e.cancel();
                    e = null;
                }
            }
            if (e != null) {
                uzb.f17611a.debug("notification has timed out and there is a fresher notification for the retransmission");
                if (e.getType() != CoAP.Type.CON) {
                    e.setType(CoAP.Type.CON);
                    uzb.this.a(this.e, e);
                }
                this.d.cancel();
                uzb.super.sendResponse(this.e, e);
            }
        }

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter, org.eclipse.californium.core.coap.MessageObserver
        public void onTimeout() {
            ObserveRelation r = this.e.r();
            uzb.f17611a.info("notification for token [{}] timed out. Canceling all relations with source [{}]", r.e().p().getToken(), vcb.b((SocketAddress) r.f()));
            r.c();
        }

        @Override // org.eclipse.californium.core.coap.MessageObserverAdapter
        public void failed() {
            ObserveRelation r = this.e.r();
            uzb.f17611a.info("notification for token [{}] failed. Source [{}].", r.e().p().getToken(), vcb.b((SocketAddress) r.f()));
            r.b();
        }
    }
}

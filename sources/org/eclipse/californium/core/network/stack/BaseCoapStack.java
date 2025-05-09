package org.eclipse.californium.core.network.stack;

import defpackage.uxh;
import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.uxx;
import defpackage.uzr;
import defpackage.vha;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.Outbox;
import org.eclipse.californium.core.network.stack.Layer;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class BaseCoapStack implements CoapStack, ExtendedCoapStack {
    private static final Logger LOGGER = vha.a((Class<?>) BaseCoapStack.class);
    private final d bottom;
    private MessageDeliverer deliverer;
    private List<Layer> layers;
    private final Outbox outbox;
    private final e top;

    public BaseCoapStack(Outbox outbox) {
        this.top = new e();
        this.bottom = new d();
        this.outbox = outbox;
    }

    protected final void setLayers(Layer[] layerArr) {
        Layer.c c = new Layer.c().c(this.top);
        for (Layer layer : layerArr) {
            c.c(layer);
        }
        c.c(this.bottom);
        this.layers = c.d();
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        try {
            this.top.sendRequest(exchange, uxtVar);
        } catch (uzr e2) {
            LOGGER.debug("error send request {} - {}", uxtVar, e2.getMessage());
            uxtVar.setSendError(e2);
        } catch (RuntimeException e3) {
            LOGGER.warn("error send request {}", uxtVar, e3);
            uxtVar.setSendError(e3);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void sendResponse(Exchange exchange, uxr uxrVar) {
        try {
            this.top.sendResponse(exchange, uxrVar);
        } catch (uxx e2) {
            LOGGER.warn("error send response {}", uxrVar, e2);
            uxrVar.setSendError(e2);
        } catch (RuntimeException e3) {
            LOGGER.warn("error send response {}", uxrVar, e3);
            exchange.z();
            uxrVar.setSendError(e3);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
        try {
            this.top.sendEmptyMessage(exchange, uxnVar);
        } catch (RuntimeException e2) {
            LOGGER.warn("error send empty message {}", uxnVar, e2);
            uxnVar.setSendError(e2);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void receiveRequest(Exchange exchange, uxt uxtVar) {
        this.bottom.receiveRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        this.bottom.receiveResponse(exchange, uxrVar);
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
        this.bottom.receiveEmptyMessage(exchange, uxnVar);
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public final void setExecutors(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2) {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            it.next().setExecutors(scheduledExecutorService, scheduledExecutorService2);
        }
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public final void setDeliverer(MessageDeliverer messageDeliverer) {
        this.deliverer = messageDeliverer;
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public final boolean hasDeliverer() {
        return this.deliverer != null;
    }

    @Override // org.eclipse.californium.core.network.stack.ExtendedCoapStack
    public <T extends Layer> T getLayer(Class<T> cls) {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (cls.isInstance(t)) {
                return t;
            }
        }
        return null;
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void start() {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
    }

    @Override // org.eclipse.californium.core.network.stack.CoapStack
    public void destroy() {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
    }

    class e extends AbstractLayer {
        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
        }

        private e() {
        }

        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void sendRequest(Exchange exchange, uxt uxtVar) {
            exchange.b(uxtVar);
            lower().sendRequest(exchange, uxtVar);
        }

        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void sendResponse(Exchange exchange, uxr uxrVar) {
            exchange.b(uxrVar);
            lower().sendResponse(exchange, uxrVar);
        }

        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void receiveRequest(Exchange exchange, uxt uxtVar) {
            if (exchange.p() == null) {
                exchange.b(uxtVar);
            }
            if (BaseCoapStack.this.hasDeliverer()) {
                BaseCoapStack.this.deliverer.deliverRequest(exchange);
            } else {
                BaseCoapStack.LOGGER.error("Top of CoAP stack has no deliverer to deliver request");
            }
        }

        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void receiveResponse(Exchange exchange, uxr uxrVar) {
            if (BaseCoapStack.this.hasDeliverer()) {
                BaseCoapStack.this.deliverer.deliverResponse(exchange, uxrVar);
            } else {
                BaseCoapStack.LOGGER.error("Top of CoAP stack has no deliverer to deliver response");
            }
        }
    }

    class d extends AbstractLayer {
        private d() {
        }

        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void sendRequest(Exchange exchange, uxt uxtVar) {
            BaseCoapStack.this.outbox.sendRequest(exchange, uxtVar);
        }

        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void sendResponse(Exchange exchange, uxr uxrVar) {
            BaseCoapStack.this.outbox.sendResponse(exchange, uxrVar);
            uxh j = uxrVar.getOptions().j();
            if (j == null || !j.f()) {
                uxrVar.onTransferComplete();
            }
        }

        @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
        public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
            BaseCoapStack.this.outbox.sendEmptyMessage(exchange, uxnVar);
        }
    }
}

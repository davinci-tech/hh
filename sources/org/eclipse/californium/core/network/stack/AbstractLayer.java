package org.eclipse.californium.core.network.stack;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.vha;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.network.Exchange;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class AbstractLayer implements Layer {
    private static final Logger LOGGER = vha.a((Class<?>) AbstractLayer.class);
    protected ScheduledExecutorService executor;
    protected ScheduledExecutorService secondaryExecutor;
    private Layer upperLayer = e.a();
    private Layer lowerLayer = e.a();

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void destroy() {
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void start() {
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        this.lowerLayer.sendRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void sendResponse(Exchange exchange, uxr uxrVar) {
        this.lowerLayer.sendResponse(exchange, uxrVar);
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
        this.lowerLayer.sendEmptyMessage(exchange, uxnVar);
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void receiveRequest(Exchange exchange, uxt uxtVar) {
        this.upperLayer.receiveRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        this.upperLayer.receiveResponse(exchange, uxrVar);
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
        this.upperLayer.receiveEmptyMessage(exchange, uxnVar);
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public final void setLowerLayer(Layer layer) {
        Layer layer2 = this.lowerLayer;
        if (layer2 != layer) {
            if (layer2 != null) {
                layer2.setUpperLayer(null);
            }
            this.lowerLayer = layer;
            layer.setUpperLayer(this);
        }
    }

    protected final Layer lower() {
        return this.lowerLayer;
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public final void setUpperLayer(Layer layer) {
        Layer layer2 = this.upperLayer;
        if (layer2 != layer) {
            if (layer2 != null) {
                layer2.setLowerLayer(null);
            }
            this.upperLayer = layer;
            layer.setLowerLayer(this);
        }
    }

    protected final Layer upper() {
        return this.upperLayer;
    }

    @Override // org.eclipse.californium.core.network.stack.Layer
    public final void setExecutors(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2) {
        this.executor = scheduledExecutorService;
        this.secondaryExecutor = scheduledExecutorService2;
    }

    public final void reject(Exchange exchange, Message message) {
        if (message.getType() == CoAP.Type.ACK || message.getType() == CoAP.Type.RST) {
            throw new IllegalArgumentException("Can only reject CON/NON messages");
        }
        lower().sendEmptyMessage(exchange, uxn.b(message));
    }

    public static final class e implements Layer {
        private static final e c = new e();

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void destroy() {
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void setExecutors(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2) {
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void setLowerLayer(Layer layer) {
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void setUpperLayer(Layer layer) {
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void start() {
        }

        public static e a() {
            return c;
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void sendRequest(Exchange exchange, uxt uxtVar) {
            AbstractLayer.LOGGER.error("No lower layer set for sending request [{}]", uxtVar);
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void sendResponse(Exchange exchange, uxr uxrVar) {
            AbstractLayer.LOGGER.error("No lower layer set for sending response [{}]", uxrVar);
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
            AbstractLayer.LOGGER.error("No lower layer set for sending empty message [{}]", uxnVar);
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void receiveRequest(Exchange exchange, uxt uxtVar) {
            AbstractLayer.LOGGER.error("No upper layer set for receiving request [{}]", uxtVar);
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void receiveResponse(Exchange exchange, uxr uxrVar) {
            AbstractLayer.LOGGER.error("No lower layer set for receiving response [{}]", uxrVar);
        }

        @Override // org.eclipse.californium.core.network.stack.Layer
        public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
            AbstractLayer.LOGGER.error("No lower layer set for receiving empty message [{}]", uxnVar);
        }
    }
}

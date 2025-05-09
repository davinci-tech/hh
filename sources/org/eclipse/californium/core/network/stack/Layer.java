package org.eclipse.californium.core.network.stack;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.network.Exchange;

/* loaded from: classes7.dex */
public interface Layer {
    void destroy();

    void receiveEmptyMessage(Exchange exchange, uxn uxnVar);

    void receiveRequest(Exchange exchange, uxt uxtVar);

    void receiveResponse(Exchange exchange, uxr uxrVar);

    void sendEmptyMessage(Exchange exchange, uxn uxnVar);

    void sendRequest(Exchange exchange, uxt uxtVar);

    void sendResponse(Exchange exchange, uxr uxrVar);

    void setExecutors(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2);

    void setLowerLayer(Layer layer);

    void setUpperLayer(Layer layer);

    void start();

    public static final class c {
        private final List<Layer> b = new ArrayList();
        private Layer c;

        public c c(Layer layer) {
            Layer layer2 = this.c;
            if (layer2 != null) {
                layer2.setLowerLayer(layer);
            }
            this.b.add(layer);
            this.c = layer;
            return this;
        }

        public List<Layer> d() {
            return Collections.unmodifiableList(new ArrayList(this.b));
        }
    }
}

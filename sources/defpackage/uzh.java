package defpackage;

import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.AbstractLayer;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uzh extends AbstractLayer {
    private static final Logger b = vha.a((Class<?>) uzh.class);

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendEmptyMessage(Exchange exchange, uxn uxnVar) {
        if (uxnVar.isConfirmable()) {
            lower().sendEmptyMessage(exchange, uxnVar);
        } else if (exchange != null) {
            b.warn("attempting to send empty message ({}) in TCP mode {}", uxnVar, exchange, new Throwable());
        } else {
            b.warn("attempting to send empty message ({}) in TCP mode.", uxnVar, new Throwable());
        }
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveRequest(Exchange exchange, uxt uxtVar) {
        uxtVar.setAcknowledged(true);
        upper().receiveRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        uxrVar.setAcknowledged(true);
        upper().receiveResponse(exchange, uxrVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveEmptyMessage(Exchange exchange, uxn uxnVar) {
        b.info("discarding empty message received in TCP mode: {}", uxnVar);
    }
}

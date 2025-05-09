package defpackage;

import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.AbstractLayer;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uzi extends AbstractLayer {
    static final Logger c = vha.a((Class<?>) uzi.class);

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        uxtVar.addMessageObserver(new uyw(exchange));
        super.sendRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        exchange.af();
        exchange.p().onTransferComplete();
        super.receiveResponse(exchange, uxrVar);
    }
}

package defpackage;

import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.AbstractLayer;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uzc extends AbstractLayer {
    static final Logger b = vha.a((Class<?>) uzc.class);
    private final int c;

    public uzc(Configuration configuration) {
        this.c = configuration.d(CoapConfig.af, TimeUnit.MILLISECONDS) + configuration.d(CoapConfig.y, TimeUnit.MILLISECONDS) + configuration.d(CoapConfig.ab, TimeUnit.MILLISECONDS);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        if (uxtVar.h()) {
            uxtVar.addMessageObserver(new uzd(exchange, this.executor, this.c));
        } else if (uxtVar.getOptions().al()) {
            uxtVar.addMessageObserver(new uza(exchange, this.executor, this.c));
        } else {
            uxtVar.addMessageObserver(new uyw(exchange));
        }
        super.sendRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendResponse(Exchange exchange, uxr uxrVar) {
        CoAP.Type type;
        if (exchange.r() == null && ((type = uxrVar.getType()) == null || type == CoAP.Type.CON)) {
            uxrVar.addMessageObserver(new uyw(exchange));
        }
        super.sendResponse(exchange, uxrVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        if (!exchange.p().h()) {
            exchange.af();
            exchange.p().onTransferComplete();
        }
        super.receiveResponse(exchange, uxrVar);
    }
}

package defpackage;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.AbstractLayer;
import org.eclipse.californium.core.observe.ObserveRelation;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uzg extends AbstractLayer {
    private static final Logger b = vha.a((Class<?>) uzg.class);

    /* renamed from: a, reason: collision with root package name */
    private static final Integer f17614a = 1;

    public uzg(Configuration configuration) {
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendRequest(Exchange exchange, uxt uxtVar) {
        f17614a.equals(uxtVar.getOptions().t());
        lower().sendRequest(exchange, uxtVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void sendResponse(Exchange exchange, uxr uxrVar) {
        uxrVar.setType(CoAP.Type.CON);
        ObserveRelation r = exchange.r();
        ObserveRelation.State c = ObserveRelation.c(r, uxrVar);
        if (r != null) {
            if (c == ObserveRelation.State.CANCELED && exchange.v()) {
                b.debug("drop notification {}, relation was canceled!", uxrVar);
                uxrVar.setCanceled(true);
                return;
            }
            r.c(uxrVar);
        }
        lower().sendResponse(exchange, uxrVar);
    }

    @Override // org.eclipse.californium.core.network.stack.AbstractLayer, org.eclipse.californium.core.network.stack.Layer
    public void receiveResponse(Exchange exchange, uxr uxrVar) {
        if (uxrVar.getOptions().an() && exchange.p().isCanceled()) {
            b.debug("ignoring notification for canceled TCP Exchange");
        } else {
            upper().receiveResponse(exchange, uxrVar);
        }
    }
}

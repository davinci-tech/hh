package org.eclipse.californium.core.network;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import org.eclipse.californium.core.coap.Message;

/* loaded from: classes7.dex */
public interface EndpointReceiver {
    void receiveEmptyMessage(Exchange exchange, uxn uxnVar);

    void receiveRequest(Exchange exchange, uxt uxtVar);

    void receiveResponse(Exchange exchange, uxr uxrVar);

    void reject(Message message);
}

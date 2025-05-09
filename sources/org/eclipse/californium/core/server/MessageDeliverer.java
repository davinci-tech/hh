package org.eclipse.californium.core.server;

import defpackage.uxr;
import org.eclipse.californium.core.network.Exchange;

/* loaded from: classes7.dex */
public interface MessageDeliverer {
    void deliverRequest(Exchange exchange);

    void deliverResponse(Exchange exchange, uxr uxrVar);
}

package org.eclipse.californium.core.network;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.uxu;

/* loaded from: classes7.dex */
public interface Matcher {
    void cancelObserve(uxu uxuVar);

    void clear();

    void receiveEmptyMessage(uxn uxnVar, EndpointReceiver endpointReceiver);

    void receiveRequest(uxt uxtVar, EndpointReceiver endpointReceiver);

    void receiveResponse(uxr uxrVar, EndpointReceiver endpointReceiver);

    void sendEmptyMessage(Exchange exchange, uxn uxnVar);

    void sendRequest(Exchange exchange);

    void sendResponse(Exchange exchange);

    void start();

    void stop();
}

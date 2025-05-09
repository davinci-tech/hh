package org.eclipse.californium.core.network.stack;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.server.MessageDeliverer;

/* loaded from: classes7.dex */
public interface CoapStack {
    void destroy();

    boolean hasDeliverer();

    void receiveEmptyMessage(Exchange exchange, uxn uxnVar);

    void receiveRequest(Exchange exchange, uxt uxtVar);

    void receiveResponse(Exchange exchange, uxr uxrVar);

    void sendEmptyMessage(Exchange exchange, uxn uxnVar);

    void sendRequest(Exchange exchange, uxt uxtVar);

    void sendResponse(Exchange exchange, uxr uxrVar);

    void setDeliverer(MessageDeliverer messageDeliverer);

    void setExecutors(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2);

    void start();
}

package org.eclipse.californium.core.network;

import defpackage.uxu;
import defpackage.uyc;
import defpackage.uyf;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.coap.Message;

/* loaded from: classes7.dex */
public interface MessageExchangeStore {
    int assignMessageId(Message message);

    Exchange find(uyf uyfVar);

    List<Exchange> findByToken(uxu uxuVar);

    Exchange findPrevious(uyf uyfVar, Exchange exchange);

    Exchange get(uyc uycVar);

    Exchange get(uyf uyfVar);

    boolean isEmpty();

    boolean registerOutboundRequest(Exchange exchange);

    boolean registerOutboundRequestWithTokenOnly(Exchange exchange);

    boolean registerOutboundResponse(Exchange exchange);

    Exchange remove(uyf uyfVar, Exchange exchange);

    void remove(uyc uycVar, Exchange exchange);

    boolean replacePrevious(uyf uyfVar, Exchange exchange, Exchange exchange2);

    void setExecutor(ScheduledExecutorService scheduledExecutorService);

    void start();

    void stop();
}

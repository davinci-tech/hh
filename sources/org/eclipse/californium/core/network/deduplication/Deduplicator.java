package org.eclipse.californium.core.network.deduplication;

import defpackage.uyf;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.network.Exchange;

/* loaded from: classes7.dex */
public interface Deduplicator {
    void clear();

    Exchange find(uyf uyfVar);

    Exchange findPrevious(uyf uyfVar, Exchange exchange);

    boolean isEmpty();

    boolean replacePrevious(uyf uyfVar, Exchange exchange, Exchange exchange2);

    void setExecutor(ScheduledExecutorService scheduledExecutorService);

    int size();

    void start();

    void stop();
}

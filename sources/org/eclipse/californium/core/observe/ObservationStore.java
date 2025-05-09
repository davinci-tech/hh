package org.eclipse.californium.core.observe;

import defpackage.uxu;
import defpackage.uzp;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.elements.EndpointContext;

/* loaded from: classes7.dex */
public interface ObservationStore {
    uzp get(uxu uxuVar);

    uzp put(uxu uxuVar, uzp uzpVar);

    uzp putIfAbsent(uxu uxuVar, uzp uzpVar);

    void remove(uxu uxuVar);

    void setContext(uxu uxuVar, EndpointContext endpointContext);

    void setExecutor(ScheduledExecutorService scheduledExecutorService);

    void start();

    void stop();
}

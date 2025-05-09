package org.eclipse.californium.core.network;

/* loaded from: classes7.dex */
public interface EndpointObserver {
    void destroyed(Endpoint endpoint);

    void started(Endpoint endpoint);

    void stopped(Endpoint endpoint);
}

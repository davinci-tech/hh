package org.eclipse.californium.core.server;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.server.resources.Resource;

/* loaded from: classes10.dex */
public interface ServerInterface {
    ServerInterface add(Resource... resourceArr);

    void addEndpoint(Endpoint endpoint);

    void destroy();

    Endpoint getEndpoint(int i);

    Endpoint getEndpoint(InetSocketAddress inetSocketAddress);

    Endpoint getEndpoint(URI uri);

    List<Endpoint> getEndpoints();

    String getTag();

    boolean isRunning();

    boolean remove(Resource resource);

    void start();

    void stop();
}

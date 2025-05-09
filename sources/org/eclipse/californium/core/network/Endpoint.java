package org.eclipse.californium.core.network;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;
import defpackage.uxu;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.californium.core.network.interceptors.MessageInterceptor;
import org.eclipse.californium.core.observe.NotificationListener;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public interface Endpoint {
    void addInterceptor(MessageInterceptor messageInterceptor);

    void addNotificationListener(NotificationListener notificationListener);

    void addObserver(EndpointObserver endpointObserver);

    void addPostProcessInterceptor(MessageInterceptor messageInterceptor);

    void cancelObservation(uxu uxuVar);

    void clear();

    void destroy();

    InetSocketAddress getAddress();

    Configuration getConfig();

    List<MessageInterceptor> getInterceptors();

    List<MessageInterceptor> getPostProcessInterceptors();

    URI getUri();

    boolean isStarted();

    void removeInterceptor(MessageInterceptor messageInterceptor);

    void removeNotificationListener(NotificationListener notificationListener);

    void removeObserver(EndpointObserver endpointObserver);

    void removePostProcessInterceptor(MessageInterceptor messageInterceptor);

    void sendEmptyMessage(Exchange exchange, uxn uxnVar);

    void sendRequest(uxt uxtVar);

    void sendResponse(Exchange exchange, uxr uxrVar);

    void setExecutors(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2);

    void setMessageDeliverer(MessageDeliverer messageDeliverer);

    void start() throws IOException;

    void stop();
}

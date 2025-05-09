package org.eclipse.californium.elements;

import defpackage.uzx;
import java.net.InetSocketAddress;
import java.security.Principal;
import java.util.Map;

/* loaded from: classes7.dex */
public interface EndpointContext {
    Map<uzx<?>, Object> entries();

    <T> T get(uzx<T> uzxVar);

    InetSocketAddress getPeerAddress();

    Principal getPeerIdentity();

    <T> String getString(uzx<T> uzxVar);

    String getVirtualHost();

    boolean hasCriticalEntries();
}

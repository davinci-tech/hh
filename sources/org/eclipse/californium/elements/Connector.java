package org.eclipse.californium.elements;

import defpackage.vaf;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

/* loaded from: classes7.dex */
public interface Connector {
    void destroy();

    InetSocketAddress getAddress();

    String getProtocol();

    boolean isRunning();

    void processDatagram(DatagramPacket datagramPacket);

    void send(vaf vafVar);

    void setEndpointContextMatcher(EndpointContextMatcher endpointContextMatcher);

    void setRawDataReceiver(RawDataChannel rawDataChannel);

    void start() throws IOException;

    void stop();
}

package org.eclipse.californium.scandium;

/* loaded from: classes10.dex */
public interface DtlsClusterHealth extends DtlsHealth {
    void backwardMessage();

    void badBackwardMessage();

    void badForwardMessage();

    void dropBackwardMessage();

    void dropForwardMessage();

    void forwardMessage();

    void processForwardedMessage();

    void receivingClusterManagementMessage();

    void sendBackwardedMessage();

    void sendingClusterManagementMessage();
}

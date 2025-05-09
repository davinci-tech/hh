package org.eclipse.californium.scandium.dtls;

import defpackage.vcn;
import defpackage.vdb;

/* loaded from: classes7.dex */
public interface SessionListener {
    void contextEstablished(Handshaker handshaker, vcn vcnVar) throws vdb;

    void handshakeCompleted(Handshaker handshaker);

    void handshakeFailed(Handshaker handshaker, Throwable th);

    void handshakeFlightRetransmitted(Handshaker handshaker, int i);

    void handshakeStarted(Handshaker handshaker) throws vdb;
}

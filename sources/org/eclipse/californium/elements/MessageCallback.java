package org.eclipse.californium.elements;

/* loaded from: classes7.dex */
public interface MessageCallback {
    void onConnecting();

    void onContextEstablished(EndpointContext endpointContext);

    void onDtlsRetransmission(int i);

    void onError(Throwable th);

    void onSent();
}

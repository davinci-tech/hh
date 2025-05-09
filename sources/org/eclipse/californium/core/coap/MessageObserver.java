package org.eclipse.californium.core.coap;

import defpackage.uxr;
import org.eclipse.californium.elements.EndpointContext;

/* loaded from: classes7.dex */
public interface MessageObserver {
    boolean isInternal();

    void onAcknowledgement();

    void onCancel();

    void onConnecting();

    void onContextEstablished(EndpointContext endpointContext);

    void onDtlsRetransmission(int i);

    void onReadyToSend();

    void onReject();

    void onResponse(uxr uxrVar);

    void onResponseHandlingError(Throwable th);

    void onRetransmission();

    void onSendError(Throwable th);

    void onSent(boolean z);

    void onTimeout();

    void onTransferComplete();
}

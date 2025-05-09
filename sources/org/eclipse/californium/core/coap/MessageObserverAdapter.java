package org.eclipse.californium.core.coap;

import defpackage.uxr;
import org.eclipse.californium.elements.EndpointContext;

/* loaded from: classes7.dex */
public abstract class MessageObserverAdapter implements MessageObserver {
    private final boolean isInternal;

    protected void failed() {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onAcknowledgement() {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onCancel() {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onConnecting() {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onContextEstablished(EndpointContext endpointContext) {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onDtlsRetransmission(int i) {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onReadyToSend() {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onResponse(uxr uxrVar) {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onRetransmission() {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onSent(boolean z) {
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onTransferComplete() {
    }

    public MessageObserverAdapter() {
        this(false);
    }

    public MessageObserverAdapter(boolean z) {
        this.isInternal = z;
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public boolean isInternal() {
        return this.isInternal;
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onReject() {
        failed();
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onTimeout() {
        failed();
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onSendError(Throwable th) {
        failed();
    }

    @Override // org.eclipse.californium.core.coap.MessageObserver
    public void onResponseHandlingError(Throwable th) {
        failed();
    }
}

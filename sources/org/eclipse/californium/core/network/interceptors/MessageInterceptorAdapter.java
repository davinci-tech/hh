package org.eclipse.californium.core.network.interceptors;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;

/* loaded from: classes10.dex */
public abstract class MessageInterceptorAdapter implements MessageInterceptor {
    @Override // org.eclipse.californium.core.network.interceptors.MessageInterceptor
    public void receiveEmptyMessage(uxn uxnVar) {
    }

    @Override // org.eclipse.californium.core.network.interceptors.MessageInterceptor
    public void receiveRequest(uxt uxtVar) {
    }

    @Override // org.eclipse.californium.core.network.interceptors.MessageInterceptor
    public void receiveResponse(uxr uxrVar) {
    }

    @Override // org.eclipse.californium.core.network.interceptors.MessageInterceptor
    public void sendEmptyMessage(uxn uxnVar) {
    }

    @Override // org.eclipse.californium.core.network.interceptors.MessageInterceptor
    public void sendRequest(uxt uxtVar) {
    }

    @Override // org.eclipse.californium.core.network.interceptors.MessageInterceptor
    public void sendResponse(uxr uxrVar) {
    }
}

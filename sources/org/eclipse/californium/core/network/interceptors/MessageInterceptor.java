package org.eclipse.californium.core.network.interceptors;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;

/* loaded from: classes7.dex */
public interface MessageInterceptor {
    void receiveEmptyMessage(uxn uxnVar);

    void receiveRequest(uxt uxtVar);

    void receiveResponse(uxr uxrVar);

    void sendEmptyMessage(uxn uxnVar);

    void sendRequest(uxt uxtVar);

    void sendResponse(uxr uxrVar);
}

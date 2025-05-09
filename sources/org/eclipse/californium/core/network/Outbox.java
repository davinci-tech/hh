package org.eclipse.californium.core.network;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxt;

/* loaded from: classes7.dex */
public interface Outbox {
    void sendEmptyMessage(Exchange exchange, uxn uxnVar);

    void sendRequest(Exchange exchange, uxt uxtVar);

    void sendResponse(Exchange exchange, uxr uxrVar);
}

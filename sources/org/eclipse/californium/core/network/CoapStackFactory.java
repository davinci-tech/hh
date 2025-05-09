package org.eclipse.californium.core.network;

import org.eclipse.californium.core.network.stack.CoapStack;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public interface CoapStackFactory {
    CoapStack createCoapStack(String str, String str2, Configuration configuration, Outbox outbox, Object obj);
}

package org.eclipse.californium.core.network;

import java.net.InetSocketAddress;

/* loaded from: classes7.dex */
public interface MessageIdProvider {
    int getNextMessageId(InetSocketAddress inetSocketAddress);
}

package org.eclipse.californium.core.network;

/* loaded from: classes7.dex */
public interface MessageIdTracker {
    public static final int TOTAL_NO_OF_MIDS = 65536;

    int getNextMessageId();
}
